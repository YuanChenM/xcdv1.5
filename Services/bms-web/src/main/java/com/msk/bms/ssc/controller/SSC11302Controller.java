package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCBidRestUtil;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;

import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.product.bean.*;
import com.msk.seller.bean.ISLEnterpriseRsPageResult;
import com.msk.seller.bean.ISLEnterpriseRsParam;
import com.msk.seller.bean.ISLEnterpriseRsResult;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 中标确认书详细Controller
 *
 * @author zhao_chen1
 */
@Controller
@RequestMapping("SSC11302")
public class SSC11302Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11302Controller.class);

    /**
     * 根据中标书ID，初始化中标书详情页面
     */
    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String show(SSC11301RsBean ssc11301RsBean, Model model) {
        model.addAttribute("contractId", ssc11301RsBean.getContractId());

        SSC11301RsParam ssc11301RsParam = new SSC11301RsParam();
        ssc11301RsParam.setBidId(ssc11301RsBean.getBidId());
        SSC11301RsPageResult response = ISSCBidRestUtil.findSscBidBasicInfoList(ssc11301RsParam);
        List<SSC11301RsBean> ssc11301RsBeans = response.getSSC11301RsBean();

        if (!CollectionUtils.isEmpty(ssc11301RsBeans)) {
            ssc11301RsBean = ssc11301RsBeans.get(0);
        }
        return this.init(ssc11301RsBean, model, "1");   //1：从合同详情跳转到中标书详情
    }

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{type}", method = RequestMethod.POST)
    public String init(SSC11301RsBean ssc11301RsBean, Model model, @PathVariable(value = "type") String type) {
        if (type.equals("2")) {
            if(ssc11301RsBean != null && ssc11301RsBean.getBidId()!=null){
                SSC11301RsParam ssc11301RsParam = new SSC11301RsParam();
                ssc11301RsParam.setBidId(ssc11301RsBean.getBidId());
                SSC11301RsPageResult response = ISSCBidRestUtil.findSscBidBasicInfoList(ssc11301RsParam);
                ssc11301RsBean = response.getSSC11301RsBean().get(NumberConst.IntDef.INT_ZERO);

                model.addAttribute("ssc11301RsBean", ssc11301RsBean);
                model.addAttribute("type", type);
                return "ssc/SSC11302";
            }

            logger.debug("新建");
            logger.debug("调取买家名称接口,获取采供商（招标方）");


            List<Integer> sellerCodeList=new ArrayList<>();
            sellerCodeList.add(NumberConst.IntDef.INT_ONE);
            sellerCodeList.add(NumberConst.IntDef.INT_TWO);
            sellerCodeList.add(NumberConst.IntDef.INT_THREE);
            ISLSellerRsParam param=new ISLSellerRsParam();
            param.setSlMainClassList(sellerCodeList);
            List<ISLEnterpriseRsResult> sellers=  ISSCRestUtil.getSlEnterpriseList(param);

            model.addAttribute("purchaserNameList", sellers);//采购商
            model.addAttribute("type", "2");
        } else {
            logger.debug("中标确认书详细初始化");
            model.addAttribute("ssc11301RsBean", ssc11301RsBean);
            model.addAttribute("type", "1");
        }
        return "ssc/SSC11302";
    }


    /**
     * 获取甲方关联生产商
     * @return
     */
    @RequestMapping(value = "getProEnterprises", method = RequestMethod.POST)
    @ResponseBody
    public List<ISLEnterpriseRsResult> getProEnterprises(ISLEnterpriseRsParam param)  {
        ISLEnterpriseRsParam selfParam = new ISLEnterpriseRsParam();
        selfParam.setPaging(false);
        param.setSelfParam(selfParam);
        RsResponse<ISLEnterpriseRsPageResult> response = ISSCRestUtil.getProEnterpriseList(param);
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus()) && response.getResult().getSelfResult()!= null) {
            return response.getResult().getSelfResult().getIslEnterpriseList();
        }
        return new ArrayList<ISLEnterpriseRsResult>();
    }

    /**
     * 中标确认书详细
     *
     * @param
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SSC11302RsBeen> search(SSC11302Param ssc11302Param) {
        logger.info("开始查询中标确认书详细");
        ssc11302Param.setBidId(ssc11302Param.getBidCode());
        if (ssc11302Param.getType().equals("1")) {
            ssc11302Param.setBidId(ssc11302Param.getBidCode());
        }
        PageResult<SSC11302RsBeen> result = new PageResult();
        SSC11302RsPageResult response = ISSCBidRestUtil.findBidProductDetail(ssc11302Param);
        result.setData(response.getSSC11302RsBeen());
        result.setRecordsTotal(response.getTotalCount());

        List<SSC11302RsBeen> list = response.getSSC11302RsBeen();

        ssc11302Param.setPaging(false);

        SSC11302RsPageResult res = ISSCBidRestUtil.findBidProductDetail(ssc11302Param);

        //发货箱数合计
        BigDecimal sumProductBox = BigDecimal.ZERO;
        //发货数量合计
        BigDecimal sumProductQua = BigDecimal.ZERO;
        //货值合计
        BigDecimal sumProductValue = BigDecimal.ZERO;

        if(res != null){
            List<SSC11302RsBeen> allList = res.getSSC11302RsBeen();
            for (SSC11302RsBeen bean : allList) {
                sumProductBox = DecimalUtil.add(sumProductBox,new BigDecimal(bean.getProductBox()));
                sumProductQua = DecimalUtil.add(sumProductQua,bean.getProductQua());
                sumProductValue = DecimalUtil.add(sumProductValue,bean.getProductValue());
            }
            if(!CollectionUtils.isEmpty(list)){
                list.get(NumberConst.IntDef.INT_ZERO).setSumProductBox(sumProductBox);
                list.get(NumberConst.IntDef.INT_ZERO).setSumProductQua(sumProductQua);
                list.get(NumberConst.IntDef.INT_ZERO).setSumProductValue(sumProductValue);
            }
        }
        return result;
    }

    /**
     * 超链接
     *
     * @return 页面
     */
    @RequestMapping(value = "showProduct", method = RequestMethod.POST)
    public String showProduct(SSC11302RsBeen ssc11302RsBeen, Model model) {
        logger.debug("产品详细信息");
        model.addAttribute("ssc11302RsBeen", ssc11302RsBeen);
        return "ssc/SSC1130201";
    }


    /**
     * 配置管理画面删除
     *
     * @param ssc11302RsBeen
     * @param model
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteProduct(SSC11302RsBeen ssc11302RsBeen, Model model) {
        SSC11302Param ssc11302Param = new SSC11302Param();
        ssc11302Param.setDetailId(StringUtil.toLong(ssc11302RsBeen.getDetailId()));
        ssc11302Param.setVer(ssc11302RsBeen.getVer());
        super.setCommonParam(ssc11302Param);
        ssc11302Param.setUpdTime(DateTimeUtil.getCustomerDate());

        int response = ISSCBidRestUtil.deleteBidProduct(ssc11302Param);
        if (response == NumberConst.IntDef.INT_ONE) {
            return SystemConst.RsStatus.SUCCESS;
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }

    /**
     * 编辑保存数据
     *
     * @param ssc11302RsBeen
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String modifyProduct(SSC11302RsBeen ssc11302RsBeen) {

        BigDecimal weight = DecimalUtil.multiply( new BigDecimal(ssc11302RsBeen.getProductBox()),ssc11302RsBeen.getWeightVal()).multiply(ssc11302RsBeen.getSettkementStandardPrice());
        ssc11302RsBeen.setProductValue(weight);
        ssc11302RsBeen.setProductQua((DecimalUtil.multiply(new BigDecimal(ssc11302RsBeen.getProductBox()), ssc11302RsBeen.getWeightVal())));

        super.setCommonParam(ssc11302RsBeen);
        ssc11302RsBeen.setUpdTime(DateTimeUtil.getCustomerDate());

        Integer response = ISSCBidRestUtil.modifyBidProduct(ssc11302RsBeen);
        if (response > NumberConst.IntDef.INT_ZERO) {
            return SystemConst.RsStatus.SUCCESS;
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }

    /**
     * 保存中标基本信息表(上表格)
     *
     * @param ssc11301Param
     * @param model
     * @return
     */
    @RequestMapping(value = "insertBidBasicInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    String insertBidBasicInfo(SSC11301RsParam ssc11301Param, Model model) {
        logger.info("新建页面新增中标确认书基本信息");
        if(!StringUtil.isNullOrEmpty(ssc11301Param.getStartDate())){
            ssc11301Param.setBidStartDate(DateTimeUtil.parseDate(ssc11301Param.getStartDate().replaceAll("-", ""), DateTimeUtil.FORMAT_YYYYMMDD));
        }
        if(!StringUtil.isNullOrEmpty(ssc11301Param.getEndDate())){
            ssc11301Param.setBidEndDate(DateTimeUtil.parseDate(ssc11301Param.getEndDate().replaceAll("-", ""), DateTimeUtil.FORMAT_YYYYMMDD));
        }

        super.setCommonParam(ssc11301Param);
        ssc11301Param.setUpdTime(DateTimeUtil.getCustomerDate());
        if(ssc11301Param.getBidId()==null){
            ssc11301Param.setCrtTime(DateTimeUtil.getCustomerDate());
        }

        RsResponse<SSC11301RsPageResult> response = ISSCBidRestUtil.insertBidBasicInfo(ssc11301Param);
        JSONObject jsonObject = new JSONObject();

        if (response.getResult()!=null && response.getResult().getLine() == NumberConst.IntDef.INT_ONE) {
            jsonObject.put("bid",response.getResult().getBidId().toString());
            jsonObject.put("bidProjectNo",response.getResult().getBidProjectNo().toString());
        } else {
            jsonObject.put("rsStatus",SystemConst.RsStatus.FAIL);
        }
        jsonObject.put("rsStatus",response.getStatus());
        jsonObject.put("message",response.getMessage());

        if (SystemConst.RsStatus.FAIL.equals(response.getStatus())){
            throw new BusinessException(response.getMessage());
        }
        return jsonObject.toString();
    }

    /**
     * 确认状态变化
     *
     * @param ssc11301RsBean
     * @return
     */
    @RequestMapping(value = "modifyBidStatus/{bidStatus}", method = RequestMethod.POST)
    public
    @ResponseBody
    String modifyBidStatus(Model model,@PathVariable("bidStatus") String bidStatus, SSC11301RsBean ssc11301RsBean) {
        logger.info("新建页面新增中标确认书基本信息");
        LoginUser loginUser=super.getLoginUser();
        if(bidStatus.equals(String.valueOf(SscConstant.BidStatus.PUR_CONFIRMED))||bidStatus.equals(String.valueOf(SscConstant.BidStatus.BID_CONFIRMED))){
            ssc11301RsBean.setPurchaserConfirmId(loginUser.getEmplId());
            ssc11301RsBean.setPurchaserConfirmName(loginUser.getEmplName());
            ssc11301RsBean.setPurchaserConfirmTime( DateTimeUtil.getCustomerDate());
        }
        if(bidStatus.equals(String.valueOf(SscConstant.BidStatus.SUPP_CONFIRMED))){
            ssc11301RsBean.setSupplierConfirmId(loginUser.getEmplId());
            ssc11301RsBean.setSupplierConfirmName(loginUser.getEmplName());
            ssc11301RsBean.setSupplierConfirmTime(DateTimeUtil.getCustomerDate());
        }
        if(bidStatus.equals(String.valueOf(NumberConst.IntDef.INT_EIGHT))){
            bidStatus=String.valueOf(SscConstant.BidStatus.BID_CONFIRMED);
            ssc11301RsBean.setSupplierConfirmId(loginUser.getEmplId());
            ssc11301RsBean.setSupplierConfirmName(loginUser.getEmplName());
            ssc11301RsBean.setSupplierConfirmTime(DateTimeUtil.getCustomerDate());
        }
        //再编辑，检查是否已经生成合同
        if(bidStatus.equals(String.valueOf(SscConstant.BidStatus.PENDING_CONFIRM))){

            SSC11301RsParam ssc11301RsParam = new SSC11301RsParam();
            ssc11301RsParam.setBidId(ssc11301RsBean.getBidId());
            RsResponse<Integer> response = ISSCBidRestUtil.getCheckIsContract(ssc11301RsParam);
            if(response.getStatus().equals(SystemConst.RsStatus.FAIL)){
                return  SystemConst.RsStatus.FAIL;
            }
        }

        ssc11301RsBean.setBidStatus(bidStatus);

        super.setCommonParam(ssc11301RsBean);
        ssc11301RsBean.setUpdTime(DateTimeUtil.getCustomerDate());
        Integer response = ISSCBidRestUtil.modifyBidStatus(ssc11301RsBean);
        if (response > NumberConst.IntDef.INT_ZERO) {
            return SystemConst.RsStatus.SUCCESS;
        } else {
            return SystemConst.RsStatus.FAIL;
        }
    }




    /**
     * 删除中标成交书
     *
     * @param ssc11301RsParam
     * @return
     */
    @RequestMapping(value = "deleteBidBase", method = RequestMethod.POST)
    @ResponseBody
    public String modifyBidStatus(Model model, SSC11301RsParam ssc11301RsParam) {

        logger.info("中标确认成交书删除");
        super.setCommonParam(ssc11301RsParam);
        ssc11301RsParam.setUpdTime(DateTimeUtil.getCustomerDate());
        RsResponse<Integer> response = ISSCBidRestUtil.deleteBidBase(ssc11301RsParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rsStatus",response.getStatus());
        jsonObject.put("message",response.getMessage());
        return jsonObject.toString();
    }

    /**
     * 查询合同是否生成
     *
     * @param ssc11301RsParam
     * @return
     */
    @RequestMapping(value = "checkIsContract", method = RequestMethod.POST)
    @ResponseBody
    public String checkIsContract(Model model, SSC11301RsParam ssc11301RsParam) {
        logger.info("查询合同是否生成");
        RsResponse<Integer> response = ISSCBidRestUtil.getCheckIsContract(ssc11301RsParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rsStatus",response.getStatus());
        jsonObject.put("contractId",response.getResult());
        jsonObject.put("message",response.getMessage());
        return jsonObject.toString();

    }

    /**
     * 产品质量标准： 加工技术标准指标+ 加工质量标准指标 + 通用质量标准指标
     * 1.产品技术标准 5.通用质量 8.标准质量
     * @param model
     * @return
     */
    @RequestMapping(value = "getPdStandards", method = RequestMethod.POST)
    public String getPdStandards(Model model, IPD141146RsParam param) {
        logger.debug("获取产品质量标准");

        /**加工技术标准指标*/
        List<IPD141121RsResult> mctStdList = new ArrayList<>();
        param.setType(1);
        IPD141121RsResult[] mctResponse = (IPD141121RsResult[])ISSCRestUtil.getPdStandards(param);
        if(null != mctResponse) {
            mctStdList = Arrays.asList(mctResponse);
        }

        /**加工质量标准指标*/
        List<IPD141107RsResult> tncStdList = new ArrayList<>();
        param.setType(8);
        IPD141107RsResult[] tncResponse = (IPD141107RsResult[])ISSCRestUtil.getPdStandards(param);
        if(null != tncResponse) {
            tncStdList = Arrays.asList(tncResponse);
        }

        /**通用质量标准指标*/
        List<IPD141125RsResult> gnqStdList = new ArrayList<>();
        param.setType(5);
        IPD141125RsResult[] gnqResponse = (IPD141125RsResult[])ISSCRestUtil.getPdStandards(param);
        if(null != gnqResponse) {
            gnqStdList = Arrays.asList(gnqResponse);
        }

        model.addAttribute("mctStdList", mctStdList);
        model.addAttribute("tncStdList", tncStdList);
        model.addAttribute("gnqStdList", gnqStdList);
        return "ssc/SSC1130202";
    }

    /**
     * 合同选择中标
     * @param model
     * @return
     */
    @RequestMapping(value = "choseBidBaseInit", method = RequestMethod.POST)
    public String choseBidBaseInit(Model model,SSC11301Param ssc11301Param){
        logger.info("选择中标页面初始化");
        model.addAttribute("ssc11301Param",ssc11301Param);
        return "ssc/SSC1130204";
    }



    /**
     * 查询未关联合同的中标信息
     * @param model
     * @return
     */
    @RequestMapping(value = "findNoRelatedBidBase", method = RequestMethod.POST)
    @ResponseBody
    public SSC1130204RsBean findNoRelatedBidBase(Model model,SSC11301Param ssc11301Param){
        logger.info("查询未关联合同的中标信息");

        List<SSC11301RsBean> list = null;

        RsResponse<SSC11301RsPageResult> response =  ISSCBidRestUtil.findNoRelatedBidBase(ssc11301Param);
        list = response.getResult().getSSC11301RsBean();
        List<String> bidBaseList = new ArrayList<String>();
        SSC1130204RsBean result = new SSC1130204RsBean();
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(list)){
            for (SSC11301RsBean rsResult:list){
                String bidProjectNo = "";
                String bidProjectName = "";
                if(rsResult != null){
                    if(rsResult.getBidProjectNo() != null){
                        bidProjectNo = rsResult.getBidProjectNo();
                    }
                    if(rsResult.getBidProjectName() != null){
                        bidProjectName = rsResult.getBidProjectName();
                    }
                    String bidBase = bidProjectNo + "("+bidProjectName+")";
                    bidBaseList.add(bidBase);
                }
            }
            result.setBidList(bidBaseList);
        }else{
            result.setBidFlag(SystemConst.RsStatus.FAIL);
        }

        return result;
    }


    /**
     * 验证合同信息是否存在
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "checkBidBaseExist", method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<SSC11301Bean> checkBidBaseExist(Model model, SSC11301Param param) throws Exception {
        logger.info("查询中标是否存在");
        RsResponse<SSC11301Bean> rsResponse = ISSCBidRestUtil.checkBidBaseExist(param);
        return rsResponse;
    }
}
