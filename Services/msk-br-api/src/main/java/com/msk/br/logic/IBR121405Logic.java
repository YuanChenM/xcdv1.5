package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.BR121404RsBean;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.StatusConst;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrONeedFeaDetail;
import com.msk.core.entity.BrSingleByFileInfo;
import com.msk.core.entity.ByMarketTerminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;


@Service
public class IBR121405Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121405Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_zhifei
     */
    interface SqlId {
        //查询单一买家标准产品池
        static String FIND_ALL_SINGLE_BY_INFO = "findAllSingleByInfo";
        static String FIND_MACHINING_LISTS = "findMachiningLists";
        static String FIND_MARKETNAME_BY_LEVEL = "findMarketNameByLevel";
        String DELETE_FLAG = "deleteFlag";
        String MODIFY_FILE_STATUS = "modifyFileStatus";

    }
    /**
     * 根据批发市场等级查询批发市场信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketTerminal> findMarketNameList(BaseParam param){
        return super.findList(SqlId.FIND_MARKETNAME_BY_LEVEL,param);
    }

    /**
     * 删除excel
     * @param fileId
     * @return
     */
    @Transactional
    public int deleteFlag(BaseParam param){
        return super.modify(SqlId.DELETE_FLAG,param);
    }

    /**
     * 修改文件状态到不成功
     * @param fileId
     * @return
     */
    @Transactional
    public int modifyFileStatus(BaseParam param){
        return super.modify(SqlId.MODIFY_FILE_STATUS,param);
    }


    /**
     * 查询所有单一买家产品池数据
     */
    @Transactional(readOnly = true)
    private List<BR121404RsBean> findBrSingleByFileInfoList(BrSingleByFileInfo bean) {
        BaseParam param = new BaseParam();
        /**获取当前月 前一个月第一天到最后一天的订单收货时间*/
        //系统当前月
        String nowYearMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, DateTimeUtil.getCustomerDate());
        //本月
        Date nowDate = DateTimeUtil.parseDate(nowYearMonth, DateTimeUtil.FORMAT_YEAR_MONTH);
        //本月第一天
        String nReceivedTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.firstDay(nowDate));
        String nowStartReceivedTime = nReceivedTime + " 00:00:00";
        //本月最后一天
        String neReceivedTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.lastDay(nowDate));
        String nowEndReceivedTime = neReceivedTime + " 23:59:59";
        //上个月
        String lastYearMonth = DateTimeUtil.getLastMonth(nowYearMonth);
        Date lastDate = DateTimeUtil.parseDate(lastYearMonth, DateTimeUtil.FORMAT_YEAR_MONTH);
        //上个月的第一天
        String lReceivedTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.firstDay(lastDate));
        String lastStartReceivedTime = lReceivedTime + " 00:00:00";
        //上个月的最后一天
        String leReceivedTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, DateTimeUtil.lastDay(lastDate));
        String lastEndReceivedTime = leReceivedTime + " 23:59:59";
        //订单详细全部收货状态7
        String detailStatus = StringUtil.toString(StatusConst.OrderDetailStatusDef.ALL_RECEIPT);
        param.setFilter("buyerId", bean.getBuyerId());
        param.setFilter("nowStartReceivedTime", nowStartReceivedTime);
        param.setFilter("nowEndReceivedTime", nowEndReceivedTime);
        param.setFilter("lastStartReceivedTime", lastStartReceivedTime);
        param.setFilter("lastEndReceivedTime", lastEndReceivedTime);
        param.setFilter("detailStatus", detailStatus);
        List<BR121404RsBean> br121404BeanList = super.findList(SqlId.FIND_ALL_SINGLE_BY_INFO, param);
        return br121404BeanList;
    }

    /**
     * 查询买家基本信息
     */
    @Transactional(readOnly = true)
    private BrOBuyerInfo findBuyInfo(BrSingleByFileInfo fileInfo) {
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", fileInfo.getBuyerId());
        return super.findOne(param);
    }

    /**
     * 查询买家需求产品信息
     */
    @Transactional(readOnly = true)
    private List<BrONeedFeaDetail> findBuyNeedFeaDetailInfoList(BrSingleByFileInfo fileInfo) {
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", fileInfo.getBuyerId());
        return super.findList(param);
    }

    /**
     * 插入EXCEL文件信息
     *
     * @param fileInfo
     */
    @Transactional
    private void addFileInfo(BrSingleByFileInfo fileInfo) {
        BaseParam param = new BaseParam();
        param.setFilter("buyerId", fileInfo.getBuyerId());
        param.setFilter("fileId", fileInfo.getFileId());
        int count = super.getCount(param);
        if (NumberConst.IntDef.INT_ZERO == count) {
            super.save(fileInfo);
        } else {
            super.modify(fileInfo);
        }

    }

    /**
     * 生成文件
     *
     * @param bean
     * @return
     */
    @Transactional
    public void dataResolve(BrSingleByFileInfo bean) {
        //查询所有单一买家产品池数据
        List<BR121404RsBean> pdPoolLst = this.findBrSingleByFileInfoList(bean);
        //查询买家基本信息
        BrOBuyerInfo buyInfo = this.findBuyInfo(bean);
        //查询买家需求产品信息
        List<BrONeedFeaDetail> needFeaDetailList = this.findBuyNeedFeaDetailInfoList(bean);
        String fileId;
        if (StringUtil.isNullOrEmpty(bean.getFileId())) {
            //UUID
            fileId = UUID.randomUUID().toString();
            bean.setFileSuf("xlsx");
            bean.setDelFlg("0");
            bean.setActId(bean.getUpdId());
            bean.setActTime(bean.getUpdTime());
            bean.setCrtId(bean.getUpdId());
            bean.setCrtTime(bean.getUpdTime());
            bean.setUpdId(bean.getUpdId());
            bean.setUpdTime(bean.getUpdTime());
            bean.setVer(NumberConst.IntDef.INT_ONE);
        } else {
            fileId = bean.getFileId();
            bean.setUpdTime(DateTimeUtil.getCustomerDate());
        }
        bean.setFileId(fileId);
        //拼装数据
        Map<String, Object> map = new HashMap<>();
        System.out.println("输出EXCEL");
        map.put("pdList", pdPoolLst);
        map.put("title", bean.getFileName());
        map.put("buyInfo", buyInfo);
        map.put("nfdList", needFeaDetailList);
        InputStream in = null;
        OutputStream out = null;
        String inputPath = "template/BR121404.xlsx";
        String outputPath = FileUtils.getSystemTmpDir() + "/" + fileId + ".xlsx";

        try {
            File excelFile = new File(outputPath);
            in = getClass().getClassLoader().getResourceAsStream(inputPath);
            out = new FileOutputStream(excelFile);
            JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
            jew.excelWrite(map);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(fileId, excelFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
//            if(!result.isEmpty()){
//                FileUtils.deleteFile(excelFile);
//            }
            bean.setFileServerId(result.get(fileId));
            this.addFileInfo(bean);
        } catch (FileNotFoundException e) {
            logger.error("模板文件不存在");
            throw new SystemException("模板文件不存在", e);
        } catch (IOException e) {
            logger.error("IO读写错误");
            throw new SystemException("IO读写错误");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("输入流关闭错误");
                    throw new SystemException("输入流关闭错误");
                }
            }
            if (out != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("输出流关闭错误");
                    throw new SystemException("输出流关闭错误");
                }
            }
        }
    }
}
