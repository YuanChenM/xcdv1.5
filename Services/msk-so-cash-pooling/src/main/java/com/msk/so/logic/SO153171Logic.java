package com.msk.so.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.excel.ExcelWrite;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.logic.CommonLogic;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.common.utils.RestClientUtil;
import com.msk.so.bean.SO153171Bean;
import com.msk.so.bean.SO153171ExportParam;
import com.msk.so.bean.SO153171Param;
import com.msk.so.utils.CheckUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by wu_honglei
 */
@Service
public class SO153171Logic extends ExcelAsyncPrintService {

    private static Logger logger = LoggerFactory.getLogger(SO153171Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    public CommonLogic commonLogic;

    // @Autowired
    // public ExcelWrite excelWrite;
    /**
     * SqlId. sql定义
     *
     */
    interface SqlId {
        String SQL_ID_BATCH_UPDATE = "batchUpdate";// 批量更新
    }

    /**
     * 导出
     *
     * @return
     */
    @Transactional(readOnly = true)
    public void export(OutputStream ouputStream, BasePageParam pageParam) throws FileNotFoundException {
        InputStream in = SO153171Logic.class.getResourceAsStream("/temp/reportTemp.xlsx");
        // File output = new File("E://Text.xlsx");
        ExcelWrite excelWrite = new JxlsExcelWrite(in, new BufferedOutputStream(ouputStream));

        pageParam.setPaging(false);
        List<SO153171Bean> list = super.findPageList(pageParam, SO153171Bean.class);
        // SO153171Bean so153171Bean = new SO153171Bean();
        // so153171Bean.setUserId("总计：");
        // so153171Bean.setUserRole(list.size());
        // list.add(so153171Bean);
        // List<Object> objects = new ArrayList<Object>();
        // if(null!=list){
        // objects.addAll(list);
        // }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", list);
        excelWrite.excelWrite(paramMap);
    }

    /**
     * 检查表中是否存在数据，如果不存在，调用接口插入数据
     */
    @Transactional
    public void checkData(LoginUser loginUser) {
        BasePageParam pageParam = new BasePageParam();
        pageParam.setPaging(false);
        List<SO153171Bean> so153171List = super.findPageList(pageParam, SO153171Bean.class);

        // 设置请求参数
        RsRequest<SO153171Param> requestParam = new RsRequest<SO153171Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        requestParam.setParam(null);
        // url
        // String url = "http://127.0.0.1:8080/msk-seller/api/sl/slInfo/slBaseInfo/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlBaseInfoSearchServices();
        // 请求接口
        RsResponse<SO153171Param[]> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<SO153171Param[]>>() {});

        List<SO153171Param> paramList = null;

        if (null != response.getResult() && "S".equals(response.getStatus())) {
            paramList = Arrays.asList(response.getResult());

            // 如果list为空,插入数据
            if (so153171List == null || so153171List.size() == 0) {

                // 插入调用接口中数据
                so153171List = this.convertToSO153171List(paramList);
                // for (int i = 0; i <so153171List.size(); i++) {
                // //设置主键
                // so153171List.get(i).setPaymentDaysId(maxId);
                // maxId ++;
                // }
                // this.batchSave(so153171List);
                for (SO153171Bean so153171Bean : so153171List) {
                    Long maxId = commonLogic.maxId("SO_CP_PAYMENT_PERIOD", "PAYMENT_DAYS_ID");
                    Date now = DateTimeUtil.getCustomerDate();
                    so153171Bean.setPaymentDaysId(maxId);
                    so153171Bean.setCrtId(loginUser.getEmplId());
                    so153171Bean.setCrtTime(now);
                    so153171Bean.setUpdId(loginUser.getEmplId());
                    so153171Bean.setUpdTime(now);
                    so153171Bean.setActId(loginUser.getEmplId());
                    so153171Bean.setActTime(now);
                    this.save(so153171Bean);
                }
            } else {
                // if(so153171List.size() < paramList.size()){
                List<SO153171Bean> so153171ParamList = this.convertToSO153171List(paramList);
                // 同步数据数据
                HashMap<String, SO153171Bean> paramMap = new HashMap<String, SO153171Bean>();
                for (SO153171Bean param : so153171ParamList) {
                    paramMap.put(param.getUserId(), param);
                }
                // 将paramMap中userId重复的去掉
                for (SO153171Bean bean : so153171List) {
                    String userId = bean.getUserId();
                    paramMap.remove(userId);
                }
                // 将paramMap中剩下的数据 则是新增的用户数据，需插入数据库
                if (paramMap.size() > NumberConst.IntDef.INT_ZERO) {
                    Set<String> set = paramMap.keySet();
                    List<SO153171Bean> batchSaveList = new ArrayList<SO153171Bean>();

                    for (String lg : set) {
                        SO153171Bean bean = paramMap.get(lg);
                        Long maxId = commonLogic.maxId("SO_CP_PAYMENT_PERIOD", "PAYMENT_DAYS_ID");
                        Date now = DateTimeUtil.getCustomerDate();
                        bean.setPaymentDaysId(maxId);
                        // batchSaveList.add(bean);
                        bean.setCrtId(loginUser.getEmplId());
                        bean.setCrtTime(now);
                        bean.setUpdId(loginUser.getEmplId());
                        bean.setUpdTime(now);
                        bean.setActId(loginUser.getEmplId());
                        bean.setActTime(now);
                        this.save(bean);
                    }
                    // this.batchSave(batchSaveList);
                }
                // }
            }

        }

    }

    /**
     * 账套设置一览
     *
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SO153171Bean> findPageList(BasePageParam pageParam) {

        logger.info("账套设置列表");
        PageResult<SO153171Bean> result = this.findPage(pageParam, SO153171Bean.class);
        return result;
    }

    /**
     * 批量增加
     *
     * @param list
     * @return
     */
    /*
     * public int saveAll(List<SO153171Bean> list){
     * int result = NumberConst.IntDef.INT_ZERO;
     * int size = list.size();
     * for (int i = 1; i <=size ; i++) {
     * //性能改善, 每当list中有100条数据,则批量插入一次
     * if (i % NumberConst.IntDef.INT_HUNDRED == NumberConst.IntDef.INT_ZERO) {
     * //纪录更新数量
     * result += super.batchSave(list);
     * //更新完清空list
     * list = new ArrayList<SO153171Bean>();
     * }
     * }
     * //若list中还有剩余,则批量继续插入
     * if (size > NumberConst.IntDef.INT_ZERO) {
     * result += super.batchSave(list);
     * }
     * return result;
     * }
     */

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    /*
     * public int batchUpdate(List<SO153171Bean> list){
     * int result = NumberConst.IntDef.INT_ZERO;
     * int size = list.size();
     * if(size >= NumberConst.IntDef.INT_HUNDRED){
     * for (int i = 1; i <=size ; i++) {
     * //性能改善, 每当list中有100条数据,则批量插入一次
     * if (i % NumberConst.IntDef.INT_HUNDRED == NumberConst.IntDef.INT_ZERO) {
     * //纪录更新数量
     * result += this.batchUpdate(list);
     * }
     * }
     * }else {
     * result += this.batchUpdateBase(SqlId.SQL_ID_BATCH_UPDATE, list);
     * }
     * return result;
     * }
     */

    /**
     * 读取excel
     *
     * @param wb
     */
    @Transactional
    public int readExcel(Workbook wb, LoginUser loginUser) {
        Sheet sheet = wb.getSheetAt(NumberConst.IntDef.INT_ZERO);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        Row row;
        row = sheet.getRow(NumberConst.IntDef.INT_ZERO);
        // 总列数
        int colNum = row.getPhysicalNumberOfCells();

        List<SO153171Bean> list = new ArrayList<SO153171Bean>();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = NumberConst.IntDef.INT_ONE; i <= rowNum; i++) {
            SO153171Bean so153171Bean = new SO153171Bean();
            row = sheet.getRow(i);
            // 用户编码
            if (row.getCell(NumberConst.IntDef.INT_ONE) != null
                    && row.getCell(NumberConst.IntDef.INT_ONE).getCellType() == NumberConst.IntDef.INT_ONE) {
                so153171Bean.setUserNo(getCellValue(row.getCell(NumberConst.IntDef.INT_ONE)));
            }
            // 启用日期
            if (row.getCell(NumberConst.IntDef.INT_FOUR) != null
                    && row.getCell(NumberConst.IntDef.INT_FOUR).getCellType() == NumberConst.IntDef.INT_ZERO) {
                if (DateUtil.isCellDateFormatted(row.getCell(NumberConst.IntDef.INT_FOUR))) {
                    String commDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD,
                        DateUtil.getJavaDate(row.getCell(NumberConst.IntDef.INT_FOUR).getNumericCellValue()));
                    so153171Bean.setCommDate(commDate);
                }
            }
            // 账期
            if (row.getCell(NumberConst.IntDef.INT_SIX) != null
                    && row.getCell(NumberConst.IntDef.INT_SIX).getCellType() == NumberConst.IntDef.INT_ZERO) {
                so153171Bean.setPeriod(StringUtil.toInteger(getCellValue(row.getCell(NumberConst.IntDef.INT_SIX))));
            }
            list.add(so153171Bean);
        }
        /**
         * 更新数据
         */
        int result = NumberConst.IntDef.INT_ZERO;
        if (CollectionUtils.isNotEmpty(list) && list.size() > NumberConst.IntDef.INT_ZERO) {
            for (SO153171Bean so153171Bean : list) {
                if (so153171Bean != null && !StringUtil.isNullOrEmpty(so153171Bean.getCommDate())
                        && so153171Bean.getPeriod() != null) {
                    so153171Bean.setUpdId(loginUser.getEmplId());
                    so153171Bean.setUpdTime(DateTimeUtil.getCustomerDate());
                    result += this.modify(so153171Bean);
                }
            }
        }
        return result;
    }

    private List<SO153171Bean> convertToSO153171List(List<SO153171Param> paramList) {
        if (CollectionUtils.isNotEmpty(paramList) && paramList.size() > NumberConst.IntDef.INT_ZERO) {
            List<SO153171Bean> resultList = new ArrayList<SO153171Bean>();
            for (int i = NumberConst.IntDef.INT_ZERO; i < paramList.size(); i++) {
                SO153171Bean so153171Bean = new SO153171Bean();
                so153171Bean.setUserId(paramList.get(i).getSlCode());
                so153171Bean.setUserNo(paramList.get(i).getSlCodeDis());
                so153171Bean.setUserRole(Integer.parseInt(paramList.get(i).getSlMainClass()));
                so153171Bean.setUserName(paramList.get(i).getSlName());
                resultList.add(so153171Bean);
            }
            return resultList;
        }

        return null;
    }

    public String getCellValue(Cell cell) {
        DecimalFormat df = new DecimalFormat("#");
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD,
                        DateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                return df.format(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue() + "";
        }
        return "";
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, ?> getDataSource(Object param) {
        /*Modif for Bug #2425 at 2016/09/06 by ren_yi  start*/
        SO153171ExportParam pageParam = new SO153171ExportParam();
        // 设置FilterMap
        pageParam.setFilterMap((Map<String, Object>) param);
        // 验证日期
        String lastPeriodEndStart = (String) pageParam.getFilterMap().get("lastPeriodEndStart");
        String lastPeriodEndEnd = (String) pageParam.getFilterMap().get("lastPeriodEndEnd");
        String commDateStart = (String) pageParam.getFilterMap().get("commDateStart");
        String commDateEnd = (String) pageParam.getFilterMap().get("commDateEnd");
        pageParam.getFilterMap().get("periodStart");
        pageParam.getFilterMap().get("periodEnd");
        boolean createFlag = CheckUtil.checkDate(StringUtil.toSafeString(lastPeriodEndStart)
                , StringUtil.toSafeString(lastPeriodEndEnd));
        boolean tranFlag = CheckUtil.checkDate(StringUtil.toSafeString(lastPeriodEndStart)
                , StringUtil.toSafeString(lastPeriodEndEnd));
        if (!createFlag || !tranFlag) {
            throw new BusinessException("上个结束日日期选择不合理");
        }
        boolean createFlag2 = CheckUtil.checkDate(StringUtil.toSafeString(commDateStart)
                ,StringUtil.toSafeString(commDateEnd));
        boolean tranFlag2 = CheckUtil.checkDate(StringUtil.toSafeString(commDateStart)
                ,StringUtil.toSafeString(commDateEnd));
        if (!createFlag2 || !tranFlag2) {
            throw new BusinessException("启用日期选择不合理");
        }
        // FILTER：用户编号
        DbUtils.buildLikeCondition(pageParam, "userNo", DbUtils.LikeMode.FRONT);
        // FILTER: 用户名称
        DbUtils.buildLikeCondition(pageParam, "userName", DbUtils.LikeMode.FRONT);
        // FILTER: 用户角色
        String userRole = StringUtil.toSafeString(pageParam.getFilterMap().get("userRole"));
        if (!StringUtil.isNullOrEmpty(userRole)) {
            String[] userRoles = userRole.split(",");
            pageParam.getFilterMap().put("userRoles", userRoles);
        }
        /*Modif for Bug #2425 at 2016/09/06 by ren_yi  end*/
        List<SO153171Bean> list = super.findPageList(pageParam, SO153171Bean.class);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("list", list);
        return paramMap;
    }
}
