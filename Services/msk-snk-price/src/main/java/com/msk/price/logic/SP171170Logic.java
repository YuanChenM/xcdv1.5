package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseUploadLogic;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170ExcelBean;
import com.msk.price.bean.SP171170Param;
import com.msk.price.excel.read.SP171170ReadExcelService;
import com.msk.price.excel.validator.SP171170ExcelValidator;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhu_kai1 on 2016/6/23.
 */
@Service("SP171170")
public class SP171170Logic extends BaseUploadLogic<SP171170ExcelBean> {

    private static Logger logger = LoggerFactory.getLogger(SP171170Logic.class);

    private SP171170ReadExcelService readExcelService;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    private interface SqlId {
        // 查询最新价盘信息
        static final String FIND_NEWEST_PRICEPLATE = "findNewestPriceplate";
        static final String GET_PRICEID = "getPriceId";
        //根据产品CODE和物流区名字List 查询对应产品详情
        static final String FIND_PRODUCT = "findProduct";

        static final String FIND_WAY = "getWayInfo";
        static final String BATCH_SAVE_PRICEINFO = "batchSavePriceinfo";
        static final String MODIFY_PRICE = "modifyPrice";
        static final String GET_MAX_UPDTIME = "getMaxUpdTime";
    }

    @Override
    public List<SP171170ExcelBean> createExcelData(Workbook workbook) {
        List<SP171170ExcelBean> list = new ArrayList<>();
        try {
            // 需求等级信息
            Map<String, String> temp = getWayInfo();
            readExcelService = new SP171170ReadExcelService(workbook, temp);
            list.addAll(readExcelService.readExcel());
        } catch (BusinessException e1) {
            throw new BusinessException(e1.getMessage());
        } catch (Exception e) {
            throw new BusinessException("读取excel失败");
        }
        return list;
    }

    /**
     * 价盘报表文件上传主处理（异步）
     *
     * @param excelData
     */
    @Override
    @Transactional
    public void doHandle(List<SP171170ExcelBean> excelData) {
        try {
            // 产品信息
            Map<String, SP171170Bean> excelMap = getProductInfo(excelData);
            //Error Message
            SP171170ExcelValidator excelValidator = new SP171170ExcelValidator();
            List<SP171170Bean> outPriceList = excelValidator.excelDataValidator(readExcelService, excelData, excelMap);
            saveOrUpdate(outPriceList);
        } catch (BusinessException e1) {
            throw new BusinessException(e1.getMessage());
        } catch (Exception e) {
            throw new BusinessException("上传失败");
        }
    }

    /**
     * 获取产品信息
     *
     * @param excelData
     * @return
     */
    @Transactional(readOnly = true)
    private Map<String, SP171170Bean> getProductInfo(List<SP171170ExcelBean> excelData) {
        List<SP171170Bean> tempProductList = new ArrayList<>();
        int index = 0;
        // 防止excel中产品信息过多，传入参数过长
        for (int i = 0; i < excelData.size(); i += 500) {
            index = i + 500;
            if (index > excelData.size()) {
                index = excelData.size();
            }
            //根据条件查询出来的产品相关信息
            SP171170ExcelBean pdtSearchConditon = new SP171170ExcelBean();
            pdtSearchConditon.setExcelBeans(excelData.subList(i, index));
            List<SP171170Bean> productList = this.findList(SqlId.FIND_PRODUCT, pdtSearchConditon);
            tempProductList.addAll(productList);
        }
        // 根据条件查询出来的产品相关信息,构造map
        Map<String, SP171170Bean> excelMap = new HashMap<>();
        for (SP171170ExcelBean sp171170ExcelBean : excelData) {
            //Map的KEY为产品编码 + 物流区名称
            for (SP171170Bean bean : tempProductList) {
                excelMap.put(bean.getPdCode() + bean.getLogiareaName(), bean);
                if (bean.getPdCode().equals(sp171170ExcelBean.getPdCode())
                        && bean.getLogiareaName().equals(sp171170ExcelBean.getLgcsAreaName())) {
                    sp171170ExcelBean.setLgcsAreaCode(bean.getLogiareaCode());
                    break;
                }
            }
        }
        return excelMap;
    }

    /**
     * 获取营销状态信息
     *
     * @return
     */
    @Transactional(readOnly = true)
    private Map<String, String> getWayInfo() {
        Map<String, String> temp = new HashMap<>();
        // 获取营销状态的信息（WAY_NAME、WAY_CODE）
        BaseParam baseParam = new BaseParam();
        List<SP171170Bean> sp171170BeanList = this.findList(SqlId.FIND_WAY, baseParam);
        // 营销状态
        for (SP171170Bean sp171170 : sp171170BeanList) {
            temp.put(sp171170.getWayName(), sp171170.getWayCode());
        }
        return temp;
    }

    @Transactional(readOnly = true)
    public PageResult<SP171170Bean> searchPricePdInfo(SP171170Param param) {
        /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  Start */
        if (org.springframework.util.StringUtils.hasLength(param.getBreedName())) {
            param.setBreedName(DbUtils.buildLikeCondition(param.getBreedName(), DbUtils.LikeMode.PARTIAL));
        }
        /**Add: 横展开设置模糊查询条件 2016/09/12   BY  任强  End */
        PageResult<SP171170Bean> searchResult = this.findPage(param, SP171170Bean.class);
        return searchResult;
    }


    /**
     * 修改价盘公斤价和箱价
     *
     * @param beans
     * @param baseParam
     * @return
     */
    @Transactional
    public void modify(Collection<SP171170Bean> beans, BaseParam baseParam) {
        BaseParam param = null;
        Date upd_Time = DateTimeUtil.getCustomerDate();
        for (SP171170Bean bean : beans) {
            // 若价盘详情报表页面修改价盘数据时，若页面显示为“-”的数据不去更新。
            if (bean.getPriceofkg().compareTo(BigDecimal.ZERO) == -1
                    && bean.getPriceofbox().compareTo(BigDecimal.ZERO) == -1) {
                continue;
            }
            bean.setDelFlg("0");
            bean.setUpdTime(upd_Time);
            bean.setUpdId(baseParam.getUpdId());
            /**不能直接更新delflg=0的数据，是因为batch跑的数据（同一个产品）可能有delflg=0和delflg=1数据同时存在，故更新有问题**/
            param = new BaseParam();
            param.getFilterMap().put("pdCode", bean.getPdCode());
            param.getFilterMap().put("gradeCode", bean.getWayGradeCode());
            param.getFilterMap().put("pricecyclePeriod", bean.getPricecyclePeriod());
            param.getFilterMap().put("logiareaCode", bean.getLogiareaCode());
            /*param.getFilterMap().put("wayCode",bean.getWayCode());*/
            String updTime = (String) this.findObject(SqlId.GET_MAX_UPDTIME, param);
            param.getFilterMap().put("updTime", updTime);
            Long priceId = (Long) this.findObject(SqlId.GET_PRICEID, param);
            bean.setPricecycleId(priceId);
            this.modify(bean);
        }
    }


    /**
     * 查询最新价盘信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public String findNewestPriceplate(SP171170Param param) {
        return (String) this.findObject(SqlId.FIND_NEWEST_PRICEPLATE, param);
    }


    /**
     * 更新或者新增价盘表数据
     *
     * @param list 读入的Excel数据列表
     * @return 新增数量
     */
    public int saveOrUpdate(List<SP171170Bean> list) {
        List<SP171170Bean> bathSaveList = new ArrayList<>();
        Date nowTime = DateTimeUtil.getCustomerDate();

        int result = NumberConst.IntDef.INT_ZERO;
        // 如果没有异常信息，才进行更新或者新增价盘数据
        if (!CollectionUtils.isEmpty(list)) {
            BaseParam param = null;
            for (SP171170Bean bean : list) {
                // 更新
                bean.setPdCode(bean.getPdCode());
                bean.setPricecyclePeriod(bean.getPricecyclePeriod());
                bean.setUpdTime(nowTime);
                bean.setDelFlg("0");
                bean.setUpdId(getParam().get("userId"));
                param = new BaseParam();
                param.getFilterMap().put("pdCode", bean.getPdCode());
                param.getFilterMap().put("gradeCode", bean.getWayGradeCode());
                param.getFilterMap().put("pricecyclePeriod", bean.getPricecyclePeriod());
                param.getFilterMap().put("logiareaCode", bean.getLogiareaCode());
                    /*param.getFilterMap().put("wayCode",bean.getWayCode());*/
                Long priceId = (Long) this.findObject(SqlId.GET_PRICEID, param);
                bean.setPricecycleId(priceId);
                int i = this.modify(bean);
                if (i == 0) {
                    // 新增
                    long insertPriceId = commonLogic.maxId("SP_SELLER_PD_PRICEPLATE", "PRICE_ID");
                    bean.setPricecycleId(insertPriceId);
                    bean.setPricecyclePeriod(bean.getPricecyclePeriod());
                    bean.setPricePeriodStart(nowTime);
                    bean.setPricePeriodEnd(nowTime);
                    bean.setLogiareaCode(bean.getLogiareaCode());
                    bean.setPdCode(bean.getPdCode());
                    bean.setWayGradeName(bean.getWayGradeName());
                    bean.setDelFlg("0");
                    bean.setCrtId(getParam().get("userId"));
                    bean.setCrtTime(nowTime);
                    bean.setUpdId(getParam().get("userId"));
                    bean.setUpdTime(nowTime);
                    bean.setActId(getParam().get("userId"));
                    bean.setActTime(nowTime);
                    bathSaveList.add(bean);
                }
            }
        }
        // 批量新增价盘数据
        if (!CollectionUtils.isEmpty(bathSaveList)) {
            result = super.getBaseDao().batchInsert(SqlId.BATCH_SAVE_PRICEINFO, bathSaveList);
        }
        return result;
    }


}

