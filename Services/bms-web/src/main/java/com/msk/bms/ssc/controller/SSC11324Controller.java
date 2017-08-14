package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCInvoiceRequestUtil;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.server.SystemServerManager;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created on 2016/8/3.
 */
@Controller
@RequestMapping("SSC11324")
public class SSC11324Controller extends com.msk.common.base.BaseUploadController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11301Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{type}", method = RequestMethod.POST)
    public String init(SSC11324Param param, Model model, @PathVariable(value = "type") String type) {
        logger.debug("发票申请详细初始化");
        if (type.equals("1")) {
            RsResponse<SSC11324Bean> rsResponse=ISSCInvoiceRequestUtil.contractToNewInvoiceRequestDetail(param);
            rsResponse.getResult().setStatusCtr("9");//statusCtr:状态控制整合,等于9位正在申请状态,等于1时input状态栏置为disable
            rsResponse.getResult().setStatus(StringUtil.toString(SscConstant.InvoiceStatus.APPL));//初始化状态为申请中
            model.addAttribute("ssc11324Bean", rsResponse.getResult());
            return "ssc/SSC11324";
        }else{
            RsResponse<SSC11324Bean> rsResponse=ISSCInvoiceRequestUtil.findInvoiceRequestDetail(param);
            SSC11324Bean bean = rsResponse.getResult();
            if(bean.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.APPROVED))||bean.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.AUDITED))||bean.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.INVOICED))||bean.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.RECEIVED))||bean.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.DELETE))){
                bean.setStatusCtr("1");//statusCtr:状态控制整合,等于9位正在申请状态,等于1时input状态栏置为disable
            }else{
                bean.setStatusCtr("0");//statusCtr:状态控制整合,等于9位正在申请状态,等于1时input状态栏置为disable,等于0时为input可编辑
            }
        if (bean != null && SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())) {
            if(!StringUtil.isNullOrEmpty(bean.getUploadFileName())){
                try {
                    bean.setUploadFileNameStr( URLEncoder.encode(bean.getUploadFileName(), "UTF-8"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            model.addAttribute("ssc11324Bean", bean);
            model.addAttribute("downLoadUrl",SystemServerManager.CommonServerManager.getFileServerDownloadProxy());
        }
        return "ssc/SSC11324";}
    }

    /**
     * 验证合同信息是否存在
     *
     * @param model Model
     * @return 页面
     */
@RequestMapping(value = "ContractFindInvoiceRequestDetailExist", method = RequestMethod.POST)
@ResponseBody
public RsResponse<SSC11324Bean> ContractFindInvoiceRequestDetailExist(Model model, SSC11324Param param, boolean isOrder) throws Exception {
    logger.info("查询合同信息是否存在");
    //发货订单关联合同只取未删除，合同状态不为7：待核销，8：已结案 的数据
    if(isOrder){
        String[] contractStatusArr = new String[2];
        contractStatusArr[0] = String.valueOf(SscConstant.SscOrderStatus.PENDING_VERIF);
        contractStatusArr[1] = String.valueOf(SscConstant.SscOrderStatus.FINISHED);
        param.setContractStatusArr(contractStatusArr);
    }
    RsResponse<SSC11324Bean> rsResponse = ISSCInvoiceRequestUtil.contractFindInvoiceRequestDetailExist(param);
    return rsResponse;
    }

    /**
     * 新建发票申请
     *
     * @param ssc11324Param
     * @return 页面
     */
    @RequestMapping(value = "insertInvoiceRequest",method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<String> insertInvoiceRequest(SSC11324Param ssc11324Param, Model model){
        logger.info("新建发票申请");
        LoginUser loginUser=super.getLoginUser();
        ssc11324Param.setActId(loginUser.getEmplId());
        ssc11324Param.setCrtId(loginUser.getEmplId());
        ssc11324Param.setUpdId(loginUser.getEmplId());
        ssc11324Param.setActTime(DateTimeUtil.getCustomerDate());
        ssc11324Param.setCrtTime(DateTimeUtil.getCustomerDate());
        ssc11324Param.setUpdTime(DateTimeUtil.getCustomerDate());
        ssc11324Param.setDelFlg("0");
        if (!StringUtil.isEmpty(ssc11324Param.getContractActDateStr())) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ssc11324Param.setContractActDate(formatter.parse(ssc11324Param.getContractActDateStr()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!StringUtil.isEmpty(ssc11324Param.getRequestTimeStr())) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ssc11324Param.setRequestTime(formatter.parse(ssc11324Param.getRequestTimeStr()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RsResponse<String> rsResponse = ISSCInvoiceRequestUtil.insertInvoiceRequest(ssc11324Param);
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }
        return rsResponse;
    }

    /**
     * 修改发票内容
     *
     * @param ssc11324Param
     * @return 页面
     */
    @RequestMapping(value = "modifyInvoiceRequestUp",method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<SSC11324Bean> modifyInvoiceRequestUp(SSC11324Param ssc11324Param, Model mode){

        LoginUser loginUser = super.getLoginUser();

        logger.info("修改发票申请内容");
        ssc11324Param.setUpdId(loginUser.getEmplId());
        ssc11324Param.setUpdTime(DateTimeUtil.getCustomerDate());

        SSC11324Bean bean=new SSC11324Bean();
        bean.setContractId(ssc11324Param.getContractId());
        bean.setInvoiceRequestCode(ssc11324Param.getInvoiceRequestCode());
        bean.setInvoiceRequestId(ssc11324Param.getInvoiceRequestId());
        bean.setContractCode(ssc11324Param.getContractCode());
        if (!StringUtil.isEmpty(ssc11324Param.getContractActDateStr())) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ssc11324Param.setContractActDate(formatter.parse(ssc11324Param.getContractActDateStr()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!StringUtil.isEmpty(ssc11324Param.getRequestTimeStr())) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ssc11324Param.setRequestTime(formatter.parse(ssc11324Param.getRequestTimeStr()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!StringUtil.isEmpty(ssc11324Param.getReceiveDateStr())) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ssc11324Param.setReceiveDate(formatter.parse(ssc11324Param.getReceiveDateStr()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ssc11324Param.getStatus()!=null){
            //添加审批人与审批时间
            if(ssc11324Param.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.APPROVED))||ssc11324Param.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.APPROVE_NG))){
                try{
                    Date now=new Date();
                    ssc11324Param.setApprovalDate(now);
                    ssc11324Param.setApprovalId(loginUser.getEmplId());
                    ssc11324Param.setApprovalPerson(loginUser.getEmplName());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //添加审核人与审核时间
            if(ssc11324Param.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.AUDITED))||ssc11324Param.getStatus().equals(StringUtil.toString(SscConstant.InvoiceStatus.AUDIT_NG))){
                try{
                    Date now=new Date();
                    ssc11324Param.setAuditingDate(now);
                    ssc11324Param.setAuditingId(loginUser.getEmplId());
                    ssc11324Param.setAuditingPerson(loginUser.getEmplName());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        RsResponse<SSC11324Bean> rsResponse =ISSCInvoiceRequestUtil.modifyInvoiceRequestUp(ssc11324Param);
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }
        rsResponse.setResult(bean);
        return rsResponse;
    }




    /**
     * 查询存在的合同列表,用于新建发票信息
     *
     * @param
     * @return 页面
     */

    @RequestMapping(value = "searchContractForInvoice", method = RequestMethod.POST)
    @ResponseBody
    public SSC11324ChooseResult searchContractForInvoice(boolean isOrder) {
        logger.info("查询弹出框合同数据");
        SSC11324ChooseResult chooseContract = new SSC11324ChooseResult();
        List<String> contracts = new ArrayList<String>();
        SSC11324Param ssc11324Param = new SSC11324Param();
        //发货订单关联合同只取未删除，合同状态不为7：待核销，8：已结案 的数据
        if(isOrder){
            String[] contractStatusArr = new String[2];
            contractStatusArr[0] = String.valueOf(SscConstant.SscOrderStatus.PENDING_VERIF);
            contractStatusArr[1] = String.valueOf(SscConstant.SscOrderStatus.FINISHED);
            ssc11324Param.setContractStatusArr(contractStatusArr);
        }else{
            String[] contractStatusArr = new String[6];
            contractStatusArr[0] = String.valueOf(SscConstant.SscOrderStatus.CREATE);
            contractStatusArr[1] = String.valueOf(SscConstant.SscOrderStatus.PENDING_AUDIT);
            contractStatusArr[2] = String.valueOf(SscConstant.SscOrderStatus.PUR_AUDIT);
            contractStatusArr[3] = String.valueOf(SscConstant.SscOrderStatus.SUPP_AUDIT);
            contractStatusArr[4] = String.valueOf(SscConstant.SscOrderStatus.FINISHED);
            contractStatusArr[5] = String.valueOf(SscConstant.SscOrderStatus.ABANDON);
            ssc11324Param.setContractStatusArr(contractStatusArr);
        }

        RsResponse< PageResult<SSC11324Bean>> rsResponse= ISSCInvoiceRequestUtil.searchContractForInvoice(ssc11324Param);
        List<SSC11324Bean> chooseContractList = new ArrayList<SSC11324Bean>();
        if(rsResponse==null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){// 失败
            System.out.println("无合同数据");
            chooseContract.setContractFlag(SystemConst.RsStatus.FAIL);
        }else{
            chooseContractList = rsResponse.getResult().getData();
            for (SSC11324Bean rsResult:chooseContractList){
                String contractCode = "";
                String contractName = "";
                if(rsResult != null){
                    if(rsResult.getContractCode() != null){
                        contractCode = rsResult.getContractCode();
                    }else {
                        contractCode = "";
                    }
                    if(rsResult.getContractName() != null){
                        contractName = rsResult.getContractName();
                    }else {
                        contractName = "";
                    }
                    String contract = contractCode+"("+contractName+")";
                    contracts.add(contract);
                }
            }
            chooseContract.setContractList(contracts);
        }
        return chooseContract;
    }

    /**
     * 插入上传文件信息
     *
     */
    @RequestMapping(value = "saveInvoiceRequestFileInfo", method = RequestMethod.POST)
    public @ResponseBody
    RsResponse<SSC11324Bean> saveInvoiceRequestFileInfo(SSC11324Param bean){
        //状态设置 已收到发票
        bean.setStatus(StringUtil.toString(SscConstant.InvoiceStatus.RECEIVED));
        if(!StringUtil.isNullOrEmpty(bean.getReceiveDateStr())){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
                bean.setReceiveDate(sdf.parse(bean.getReceiveDateStr()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        LoginUser loginUser = super.getLoginUser();
        bean.setUpdId(loginUser.getEmplId());
        bean.setUpdTime(DateTimeUtil.getCustomerDate());
        RsResponse<SSC11324Bean> rs=ISSCInvoiceRequestUtil.saveInvoiceRequestFileInfo(bean);
        if (SystemConst.RsStatus.FAIL.equals(rs.getStatus())){
            throw new BusinessException(rs.getMessage());
        }
        return rs;
    }

    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String show(SSC11324Param param, Model model) {
        logger.debug("窗口页面发票申请详细初始化");
        model.addAttribute("disableBtn","1");
        return init(param,model,"2");
    }

/*
*   旧文件上传接口
*
*/

//    @RequestMapping(value = "fileUpload/{InvoiceRequestCode}", method = RequestMethod.POST)
//    public @ResponseBody
//    void fileUpload(@PathVariable("InvoiceRequestCode") String InvoiceRequestCode, MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
//        //文件方式上传
//        String localPath = FileUtils.getSystemTmpDir();
//        List<MultipartFile> files = request.getFiles("myfile");
//        Map<String,File> fileMap = new HashMap<>();
//        // 发票文件ID
//        String InvoiceFileId = null;
//        // 发票文件名称
//        String InvoiceFileName = null;
//        //获取文件Id,Name
//        for (int i = 0; i < files.size(); i++) {
//            if(files.get(i).getSize() > 0){
//                String fileId = UUID.randomUUID().toString();
//                String fileName = files.get(i).getOriginalFilename();
//                File textFile = new File(localPath,fileName);
//                files.get(i).transferTo(textFile);
//                fileMap.put(fileId,textFile);
//                if(i == 0){
//                    InvoiceFileId = fileId;
//                    InvoiceFileName = fileName;
//                }
//            }
//        }
//        Map<String,String> result = FileUploadUtil.uploadFiles(fileMap);
//
//        if(!result.isEmpty()){
//            //删除本地保存文件
//                    String fileName = files.get(0).getOriginalFilename();
//                    File textFile = new File(localPath,fileName);
//                    FileUtils.deleteFile(textFile);
//            for (String key : result.keySet()) {
//                if(key.equals(InvoiceFileId)){
//                    InvoiceFileId = result.get(key);
//                }
//            }
//            //插入文件信息
//            SSC11324Param bean = new SSC11324Param();
//            bean.setInvoiceRequestCode(InvoiceRequestCode);
//            bean.setUploadFileId(InvoiceFileId);
//            bean.setUploadFileName(InvoiceFileName);
//            bean.setStatus("4");
//            this.saveInvoiceRequestFile(bean);
//            super.callBack(null, "操作成功", response);
//        }else{
//
//            super.callBack(null, "上传文件失败", response);
//        }
//    }

//    /**
//     * 文件下载
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
//    public void fileDownLoad(BasePageParam param , HttpServletRequest request, HttpServletResponse response) throws IOException{
//        String fId = request.getParameter("uploadFileId");
//        String fileName = request.getParameter("uploadFileName");
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//        try{
//            String filePath = FileUploadUtil.getFile(fId,fileName);
//            File file = new File(filePath);
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1"));
//            inputStream = new FileInputStream(file);
//            outputStream = response.getOutputStream();
//            byte[] b = new byte[1024];
//            int length = 0;
//            while ((length = inputStream.read(b)) > 0) {
//                outputStream.write(b, 0, length);
//            }
//        }catch (FileNotFoundException e){
//            logger.error("下载处理失败：", e);
//        }
//        finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.flush();
//                    outputStream.close();
//                } catch (IOException e) {
//                    logger.error("下载处理失败：", e);
//                }
//            }
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    logger.error("下载处理失败：", e);
//                }
//            }
//        }
//    }
}

