package com.msk.ssc.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC11324Bean;
import com.msk.ssc.bean.SSC11324Param;
import com.msk.ssc.logic.SSC11324Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2016/8/3.
 */
@RestController
public class ISSC11324RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11324RestController.class);

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SSC11324Logic ssc11324Logic;

    /**
     * 查询发票详细信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findInvoiceRequestDetail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11324Bean> findInvoiceRequestDetail(@RequestBody RsRequest<SSC11324Param> param) {
        logger.info("根据发票申请编号查询发票详细信息");
        RsResponse<SSC11324Bean> response = new RsResponse<>();
        param.getParam().setStatus(StringUtil.toString(SscConstant.InvoiceStatus.RECEIVED));
        //查询合同开票申请信息
        List<SSC11324Bean> contractList = ssc11324Logic.findContractList(param.getParam());
        //查询合同已开票信息
        List<SSC11324Bean> invoiceList = ssc11324Logic.findInvoiceList(param.getParam());
        List<SSC11324Bean> noInvoiceList = ssc11324Logic.findNoInvoiceList(param.getParam());
        if(null==contractList&&null==invoiceList&&noInvoiceList==null){
            logger.info("查询开票信息错误");
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
            return response;
        }
        //计算合同申请开票总额
        BigDecimal contractInvoiceRequestAmount = new BigDecimal(0);
        for(int i=0;i<contractList.size();i++){
            BigDecimal  bd=contractList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
            contractInvoiceRequestAmount=contractInvoiceRequestAmount.add(bd);
        }
        //计算合同已开票总额
        BigDecimal contractInvoiceAmount = new BigDecimal(0);
        for(int i=0;i<invoiceList.size();i++){
            BigDecimal  bd=invoiceList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
            contractInvoiceAmount=contractInvoiceAmount.add(bd)  ;
        }
        //计算已开票张数
        int invoiceRequestCount=invoiceList.size();
        BigDecimal remainContractInvoiceAmount = new BigDecimal(0);
        //获取开票详细信息
        SSC11324Bean result = this.ssc11324Logic.InvoiceRequestDetail(param.getParam());
        //取出已付金额
        if(result!=null){
            param.getParam().setContractId(result.getContractId());
        }
        BigDecimal Amount = getAmount(param.getParam());

        if (result != null) {
            //添加合同已开票信息
            result.setInvoiceRequestCount(invoiceRequestCount);
            //添加申请开票总额
            result.setContractInvoiceRequestAmount(contractInvoiceRequestAmount);
            //添加已开票总额
            result.setContractInvoiceAmount(contractInvoiceAmount);
            //添加已付金额
            result.setAmount(Amount);
            //计算剩余开票金额
            remainContractInvoiceAmount =Amount.subtract(contractInvoiceRequestAmount);
            //增加本页金额
            if(!(result.getStatus().equals("4")||result.getStatus().equals("9"))){
                remainContractInvoiceAmount=remainContractInvoiceAmount.add(result.getInvoiceAmount());
            }
            //添加剩余开票金额
            result.setRemainContractInvoiceAmount(remainContractInvoiceAmount);
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }
    /**
     * 通过合同新建一个发票详细
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/contractToNewInvoiceRequestDetail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11324Bean> contractToNewInvoiceRequestDetail(@RequestBody RsRequest<SSC11324Param> param) {
        logger.info("查询合同信息准备发票数据");
        param.getParam().setStatus(StringUtil.toString(SscConstant.InvoiceStatus.RECEIVED));
        RsResponse<SSC11324Bean> response = new RsResponse<>();
        //查询合同开票申请信息
        List<SSC11324Bean> contractList = ssc11324Logic.findContractList(param.getParam());
        //查询合同已开票信息
        List<SSC11324Bean> invoiceList = ssc11324Logic.findInvoiceList(param.getParam());

        BigDecimal contractInvoiceRequestAmount = new BigDecimal(0);
        BigDecimal contractInvoiceAmount = new BigDecimal(0);
        int invoiceRequestCount=0;
        if(null!=contractList&&contractList.size()>0){
            //计算合同申请开票总额
            for(int i=0;i<contractList.size();i++){
                BigDecimal  bd=contractList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                contractInvoiceRequestAmount=contractInvoiceRequestAmount.add(bd);}
            if(null!=invoiceList&&invoiceList.size()>0){
                //计算合同已开票总额
                for(int i=0;i<invoiceList.size();i++){
                    BigDecimal  bd=invoiceList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                    contractInvoiceAmount=contractInvoiceAmount.add(bd)  ;}
                //计算已开票张数
                invoiceRequestCount=invoiceList.size();
            }
        }
        BigDecimal remainContractInvoiceAmount = new BigDecimal(0);
        SSC11324Bean result = this.ssc11324Logic.ContractFindInvoiceRequestDetail(param.getParam());
        param.getParam().setContractId(result.getContractId());
        //获取已支付总金额
        BigDecimal Amount = getAmount(param.getParam());
        if (result != null) {
            //计算剩余开票金额
            remainContractInvoiceAmount =Amount.subtract(contractInvoiceRequestAmount);
            //添加合同已开票信息
            result.setInvoiceRequestCount(invoiceRequestCount);
            //添加申请开票总额
            result.setContractInvoiceRequestAmount(contractInvoiceRequestAmount);
            //添加已开票总额
            result.setContractInvoiceAmount(contractInvoiceAmount);
            //添加已付金额
            result.setAmount(Amount);
            //添加剩余开票金额
            result.setRemainContractInvoiceAmount(remainContractInvoiceAmount);
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    //计算已付金额
     BigDecimal getAmount(SSC11324Param param){
         BigDecimal Amount = new BigDecimal(0);
         List<SSC11324Bean> PayAmountBean = ssc11324Logic.findPayAmountList(param);
         if (PayAmountBean!=null&&PayAmountBean.size()>0){
             for(int i=0;i<PayAmountBean.size();i++){

                 BigDecimal  bd=PayAmountBean.get(i).getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                 if(PayAmountBean.get(i).getPaymentType()==SscConstant.SscPaymentType.BALANCE&&DecimalUtil.isLess(PayAmountBean.get(i).getVerificationAmount(),BigDecimal.ZERO)){
                     BigDecimal minusOne=new BigDecimal(NumberConst.IntDef.INT_N_ONE);
                     bd=DecimalUtil.multiply(bd,minusOne);
                 }
                 Amount=Amount.add(bd);
             }
         }
         return Amount;
     }
    /**
     * 新建发票申请
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/insertInvoiceRequest", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> insertInvoiceRequest(@RequestBody RsRequest<SSC11324Param> param) {
        logger.info("插入发票基本信息数据");
        RsResponse<String> rs = new RsResponse<>();


        //查询合同开票申请信息
        List<SSC11324Bean> contractList = ssc11324Logic.findContractList(param.getParam());
        //查询合同已开票信息
        List<SSC11324Bean> invoiceList = ssc11324Logic.findInvoiceList(param.getParam());

        BigDecimal contractInvoiceRequestAmount = new BigDecimal(0);
        BigDecimal contractInvoiceAmount = new BigDecimal(0);
        int invoiceRequestCount=0;
        if(null!=contractList&&contractList.size()>0){
            //计算合同申请开票总额
            for(int i=0;i<contractList.size();i++){
                BigDecimal  bd=contractList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                contractInvoiceRequestAmount=contractInvoiceRequestAmount.add(bd);}
            if(null!=invoiceList&&invoiceList.size()>0){
                //计算合同已开票总额
                for(int i=0;i<invoiceList.size();i++){
                    BigDecimal  bd=invoiceList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                    contractInvoiceAmount=contractInvoiceAmount.add(bd)  ;}
                //计算已开票张数
                invoiceRequestCount=invoiceList.size();
            }
        }
        BigDecimal Amount = getAmount(param.getParam());
        contractInvoiceRequestAmount=contractInvoiceRequestAmount.add(param.getParam().getInvoiceAmount()) ;
       if(DecimalUtil.isGreater(contractInvoiceRequestAmount,Amount)){
           rs.setStatus(SystemConst.RsStatus.FAIL);
           rs.setMessage("插入发票信息,数据错误！");
           rs.setResult("F");
           throw new BusinessException("数据更新冲突,请重新加载数据进行修改");
       }
        Integer res = ssc11324Logic.checkDeliveryOrder(param.getParam());
        String invoiceRequestCode = "FP" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.getCustomerDate());
        param.getParam().setInvoiceRequestCode(invoiceRequestCode);

        SSC11324Bean rsBean = ssc11324Logic.findInvoiceRequestOne(param.getParam());

        if(rsBean != null && !StringUtil.isNullOrEmpty(rsBean.getInvoiceRequestCode())){
            invoiceRequestCode = "FP" + DecimalUtil.add(DecimalUtil.getBigDecimal(rsBean.getInvoiceRequestCode()), BigDecimal.ONE);
        }else{
            invoiceRequestCode += "0001";
        }
        param.getParam().setInvoiceRequestCode(invoiceRequestCode);


        String ret=invoiceRequestCode;
        int resultLine = 0;
//        if (0 == res){
            resultLine = ssc11324Logic.insertInvoiceRequest(param.getParam());
            if (resultLine > NumberConst.IntDef.INT_ZERO) {
                rs.setResult(ret);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("插入发票信息成功!");
                return rs;
//            }
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("插入发票信息,数据错误！");
        rs.setResult("F");
        return rs;
    }
    /**
     * 查询存在的发票申请
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findInvoiceRequestCode",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> findInvoiceRequestCode(@RequestBody RsRequest<BaseParam> param) {
        logger.info("----------");
        RsResponse<String> response = new RsResponse<>();
        String contactCode = this.ssc11324Logic.findInvoiceRequestCode(param.getParam());
        logger.info("----------");
        if (contactCode != null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(contactCode);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }
    /**
     * 修改合同信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyInvoiceRequestUp",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11324Bean> modifyInvoiceRequestUp(@RequestBody RsRequest<SSC11324Param> param) {
        logger.info("更新发票申请信息");
        /*
        *后台数据check
         */
        RsResponse<SSC11324Bean> response = new RsResponse<>();
        if(param.getParam().getInvoiceAmount()!=null){
            //查询合同开票申请信息
            List<SSC11324Bean> contractList = ssc11324Logic.findContractList(param.getParam());
            //查询合同已开票信息
            List<SSC11324Bean> invoiceList = ssc11324Logic.findInvoiceList(param.getParam());

            BigDecimal contractInvoiceRequestAmount = new BigDecimal(0);
            BigDecimal contractInvoiceAmount = new BigDecimal(0);
            int invoiceRequestCount=0;
            if(null!=contractList&&contractList.size()>0){
                //计算合同申请开票总额
                for(int i=0;i<contractList.size();i++){
                    BigDecimal  bd=contractList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                    if(contractList.get(i).getInvoiceRequestId().equals(param.getParam().getInvoiceRequestId()))
                    {
                        continue;
                    }
                    contractInvoiceRequestAmount=contractInvoiceRequestAmount.add(bd);}
                if(null!=invoiceList&&invoiceList.size()>0){
                    //计算合同已开票总额
                    for(int i=0;i<invoiceList.size();i++){
                        BigDecimal  bd=invoiceList.get(i).getInvoiceAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                        contractInvoiceAmount=contractInvoiceAmount.add(bd)  ;}
                    //计算已开票张数
                    invoiceRequestCount=invoiceList.size();
                }
            }
            BigDecimal Amount = getAmount(param.getParam());
            contractInvoiceRequestAmount=contractInvoiceRequestAmount.add(param.getParam().getInvoiceAmount()) ;
            if(DecimalUtil.isGreater(contractInvoiceRequestAmount,Amount)){
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("插入发票信息,数据错误！");
                throw new BusinessException("数据更新冲突,请重新加载数据进行修改");
            }
        }
        //验证后插入数据
        int count = this.ssc11324Logic.modifyInvoiceRequestUp(param.getParam());
        if (count == NumberConst.IntDef.INT_ONE) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("发票申请信息更新成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("发票申请信息更新失败");
        }
        return response;
    }
    /**
     * 插入上传发票文件信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/saveInvoiceRequestFileInfo",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  RsResponse<SSC11324Bean> saveInvoiceRequestFileInfo(@RequestBody RsRequest<SSC11324Param> param) {
        logger.info("插入发票文件信息");
        RsResponse<SSC11324Bean> response = new RsResponse<>();
        response.setStatus(SystemConst.RsStatus.FAIL);
        int successFlg  = ssc11324Logic.saveInvoiceRequestFile(param.getParam());
        if(successFlg != NumberConst.IntDef.INT_ZERO){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("插入发票文件信息成功");
        }else {
            response.setMessage("插入发票文件信息失败");
        }
        return response;
    }

    @RequestMapping(value = "/ssc/contractFindInvoiceRequestDetailExist",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11324Bean> contractFindInvoiceRequestDetailExist(@RequestBody RsRequest<SSC11324Param> param) {
    logger.info("查询合同信息准备发票数据");
    RsResponse<SSC11324Bean> response = new RsResponse<>();

    SSC11324Bean result = this.ssc11324Logic.ContractFindInvoiceRequestDetail(param.getParam());
    if (result != null) {
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
    } else {
        response.setStatus(SystemConst.RsStatus.FAIL);
        response.setMessage("数据不存在");
    }
    return response;
}
    /**
     * 查询存在的合同列表,用于新建发票信息
     *
     * @param
     * @return 页面
     */

    @RequestMapping(value = "/ssc/searchContractForInvoice",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<PageResult<SSC11324Bean>> searchContractForInvoice(@RequestBody RsRequest<SSC11324Param> param){
        logger.info("查询弹出框合同");
        RsResponse<PageResult<SSC11324Bean>> resultRsResponse = new RsResponse<>();
        PageResult<SSC11324Bean> pr=new PageResult<SSC11324Bean>();

        List<SSC11324Bean> chooseContractList = this.ssc11324Logic.searchContractForInvoice(param.getParam());
        pr.setData(chooseContractList);
        if(CollectionUtils.isNotEmpty(chooseContractList)){
            resultRsResponse.setResult(pr);
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("处理成功");
        } else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;

    }
}



