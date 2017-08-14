package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.BR121406RsParam;
import com.msk.br.bean.IBR121407RsBean;
import com.msk.br.bean.IBR121407RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 * 分销买家池各上线状态管控表Logic
 * <p/>
 * Created by yuan_zhifei on 2016/07/26.
 */
@Service
public class IBR121407Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121407Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    Date currentDate = DateTimeUtil.getCustomerDate();

    interface SqlId {
        String FIND_MARKETING_DISTRIBUTION_BUYERS = "findMarketingDistributionBuyers";
        String FIND_SALES_PERIOD_DISTRIBUTION_BUYERS = "findSalesPeriodDistributionBuyers";
        String DELETE_FLAG = "deleteFlag";
        String MODIFY_FILE_STATUS = "modifyFileStatus";
    }

    //营销期分销买家列表信息
    @Transactional(readOnly = true)
    public List<IBR121407RsBean> findMarketingDistributionBuyers(IBR121407RsParam param) {
        List<IBR121407RsBean> ibr121407BeanList = super.findList(SqlId.FIND_MARKETING_DISTRIBUTION_BUYERS, param);
        return ibr121407BeanList;
    }

    //销售期分销买家列表信息
    @Transactional(readOnly = true)
    public List<IBR121407RsBean> findSalesPeriodDistributionBuyers(IBR121407RsParam param) {
        List<IBR121407RsBean> ibr121407BeanList = super.findList(SqlId.FIND_SALES_PERIOD_DISTRIBUTION_BUYERS, param);
        return ibr121407BeanList;
    }

    /**
     * 删除excel
     * @param param
     * @return
     */
    @Transactional
    public int deleteFlag(IBR121407RsParam param){
        return super.modify(SqlId.DELETE_FLAG,param);
    }

    /**
     * 修改文件状态到不成功
     * @param param
     * @return
     */
    @Transactional
    public int modifyFileStatus(IBR121407RsParam param){
        return super.modify(SqlId.MODIFY_FILE_STATUS,param);
    }

    /**
     * 生成文件
     *
     * @param param
     * @return
     */
    public String dataResolve(IBR121407RsParam param) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        String fileId;
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        if (StringUtil.isNullOrEmpty(param.getFileId())) {
            //UUID
            fileId = UUID.randomUUID().toString();
            //param.setFileServerIp(fileServerIp);
            param.setFileName(param.getFileName());
            param.setFileStartTime(param.getFileStartTime());
            param.setFileEndTime(param.getFileEndTime());
            param.setFileCreateTime(currentDate);
            param.setFileSuf("xlsx");
            param.setDelFlg("0");
            param.setVer(NumberConst.IntDef.INT_ONE);
        } else {
            fileId = param.getFileId();
            param.setUpdTime(DateTimeUtil.getCustomerDate());
        }
        param.setFileId(fileId);
        addFileInfo(param);
        return fileId;

    }

    /**
     * 创建excel
     *
     * @param param
     */
    public void createFileInformatica(IBR121407RsParam param) {
        //查询买家产品池数据
        List<IBR121407RsBean> pdPoolList = null;
        String marketingsStatusName = "";
        String fileName = "";
        //从CodeMaster中获取买家收货时间段
        Map<String, String> recTimeMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.HabitReceivePeriodType.TYPE);
        //从CodeMaster中获取支付方式
        Map<String, String> paymentTypeMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.PaymentMethod.Type);
        if (param.getMarketingsStatusCla().equals("1")) {
            //营销期
            if (StringUtil.isNullOrEmpty(param.getMarketingsStatus())) {
                marketingsStatusName = "营销期买家";
            } else if (param.getMarketingsStatus().equals("01")) {
                marketingsStatusName = "营销期预注册买家";
            } else if (param.getMarketingsStatus().equals("02")) {
                marketingsStatusName = "营销期未营销成功买家";
            }
            pdPoolList = this.findMarketingDistributionBuyers(param);
        } else if (param.getMarketingsStatusCla().equals("2")) {
            //销售期
            if (StringUtil.isNullOrEmpty(param.getMarketingsStatus())) {
                marketingsStatusName = "销售期买家";
            } else if (param.getMarketingsStatus().equals("11")) {
                marketingsStatusName = "销售期激活期买家";
            } else if (param.getMarketingsStatus().equals("13")) {
                marketingsStatusName = "销售期预警期买家";
            } else if (param.getMarketingsStatus().equals("14")) {
                marketingsStatusName = "销售期休眠期买家";
            } else if (param.getMarketingsStatus().equals("21")) {
                marketingsStatusName = "销售期稳定期核心买家";
            } else if (param.getMarketingsStatus().equals("22")) {
                marketingsStatusName = "销售期稳定期标准买家";
            }
            pdPoolList = this.findSalesPeriodDistributionBuyers(param);
        } else if (param.getMarketingsStatusCla().equals("98")) {
            //营销期期异常
            if (StringUtil.isNullOrEmpty(param.getMarketingsStatus())) {
                marketingsStatusName = "营销期异常买家";
            } else if (param.getMarketingsStatus().equals("31")) {
                marketingsStatusName = "营销期异常停业买家";
            } else if (param.getMarketingsStatus().equals("32")) {
                marketingsStatusName = "营销期异常信息错误买家";
            }
            pdPoolList = this.findSalesPeriodDistributionBuyers(param);
        } else {
            //销售期异常
            if (StringUtil.isNullOrEmpty(param.getMarketingsStatus())) {
                marketingsStatusName = "销售期异常买家";
            } else if (param.getMarketingsStatus().equals("31")) {
                marketingsStatusName = "销售期异常停业买家";
            } else if (param.getMarketingsStatus().equals("32")) {
                marketingsStatusName = "销售期异常信息错误买家";
            }
            pdPoolList = this.findSalesPeriodDistributionBuyers(param);
        }
        /** Modif for Bug#2582 at 2016/09/18 by yuan_zhifei Start*/
        if (!CollectionUtils.isEmpty(pdPoolList)) {
            fileName = pdPoolList.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime();
            //获取习惯收货时间段code对应的值
            for (IBR121407RsBean ibr121407RsBean : pdPoolList) {
                if (BuyersConstant.HabitReceivePeriodType.SIX_TO_EIGHT.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.EIGHT_TO_TEN.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.TEN_TO_TWELVE.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.TWELVE_TO_FOURTEEN.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.FOURTEEN_TO_SIXTEEN.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.SIXTEEN_TO_EIGHTEEN.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.EIGHTEEN_TO_TWENTY.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.TWENTY_TO_TWENTY_TWO.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.TWENTY_TWO_TO_TWENTY_FOUR.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.ZERO_TO_TWO.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.TWO_TO_FOUR.equals(ibr121407RsBean.getHabitRecTime()) ||
                        BuyersConstant.HabitReceivePeriodType.FOUR_TO_SIX.equals(ibr121407RsBean.getHabitRecTime())) {
                    ibr121407RsBean.setHabitRecTime(recTimeMap.get(ibr121407RsBean.getHabitRecTime()));
                } else {
                    ibr121407RsBean.setHabitRecTime("");
                }
                //获取支付方式code对应的值
                String paymentTypes = ibr121407RsBean.getPaymentType();
                String payTypes = "";
                if (!StringUtil.isNullOrEmpty(paymentTypes)) {
                    String[] paymentType = paymentTypes.split(",");
                    for (int i = NumberConst.IntDef.INT_ZERO; i < paymentType.length; i++) {
                        if (StringUtil.toString(BuyersConstant.PaymentMethod.ONLINEBANKING).equals(paymentType[i]) ||
                                StringUtil.toString(BuyersConstant.PaymentMethod.VIPCARD).equals(paymentType[i]) ||
                                StringUtil.toString(BuyersConstant.PaymentMethod.POS).equals(paymentType[i])) {
                            if (i == NumberConst.IntDef.INT_ZERO) {
                                payTypes += paymentTypeMap.get(paymentType[i]);
                            } else {
                                payTypes += "," + paymentTypeMap.get(paymentType[i]);
                            }

                        }
                    }
                    ibr121407RsBean.setPaymentType(payTypes);
                } else {
                    ibr121407RsBean.setPaymentType("");
                }
            }
        }
        /** Modif for Bug#2582 at 2016/09/18 by yuan_zhifei End*/
        //拼装数据
        Map<String, Object> map = new HashMap<>();
        System.out.println("输出EXCEL");
        map.put("pdList", pdPoolList);
        map.put("title", fileName);
        map.put("marketingsStatusName", marketingsStatusName);
        InputStream in = null;
        OutputStream out = null;
        String inputPath = null;
        if (param.getMarketingsStatusCla().equals("1")) {
            //营销期
            inputPath = "template/BR12140701.xlsx";
        } else if (param.getMarketingsStatusCla().equals("2") || param.getMarketingsStatusCla().equals("99")) {
            //销售期或销售期异常
            inputPath = "template/BR12140703.xlsx";
        } else if (param.getMarketingsStatusCla().equals("98")) {
            //营销期异常
            inputPath = "template/BR12140702.xlsx";
        }
        String outputPath = FileUtils.getSystemTmpDir() + "/" + param.getFileId() + ".xlsx";
        try {
            File excelFile = new File(outputPath);
            in = getClass().getClassLoader().getResourceAsStream(inputPath);
            out = new FileOutputStream(excelFile);
            JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
            jew.excelWrite(map);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(param.getFileId(), excelFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
            if (!result.isEmpty()) {
                FileUtils.deleteFile(excelFile);
            }
            param.setFileServerId(result.get(param.getFileId()));
            if(param.getFileServerId() == null){
                modifyFileStatus(param);
                return ;
            }

            this.addFileInfo(param);
        } catch (FileNotFoundException e) {
            logger.error("模板文件不存在");
            modifyFileStatus(param);
            return ;
//            throw new SystemException("模板文件不存在", e);
        } catch (IOException e) {
            logger.error("IO读写错误");
            modifyFileStatus(param);
            return ;
//            throw new SystemException("IO读写错误");
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


    /**
     * 插入EXCEL文件信息
     *
     * @param ibr121407RsParam
     */
    @Transactional
    private void addFileInfo(IBR121407RsParam ibr121407RsParam) {
        BaseParam param = new BaseParam();
        param.setFilter("fileId", ibr121407RsParam.getFileId());
        int count = super.getCount(param);
        if (NumberConst.IntDef.INT_ZERO == count) {
            ibr121407RsParam.setFileStartTime(ibr121407RsParam.getFileStartTime()+" 00:00:00");
            ibr121407RsParam.setFileEndTime(ibr121407RsParam.getFileEndTime() + " 23:59:59");
            super.save(ibr121407RsParam);
        } else {
            super.modify(ibr121407RsParam);
        }

    }
}
