package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11302Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao_chen1 on 2016/6/30.
 */
@RestController
public class ISSC11302RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11302RestController.class);

    @Autowired
    private SSC11302Logic ssc11302Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 查询中标确认书详细接口
     *
     * @return RsResponse 结果
     * @author zhao_chen1
     */
    @RequestMapping(value = "/ssc/findBidProductDetail",
            method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11302RsPageResult> findBidProductDetail(@RequestBody RsRequest<SSC11302Param> ssc11302Param) {
        logger.info("中标确认书详细接口");
        RsResponse<SSC11302RsPageResult> response = new RsResponse<SSC11302RsPageResult>();
        SSC11302RsPageResult ssc11302RsPageResult = new SSC11302RsPageResult();
        ssc11302Param.getParam();
        int count = this.ssc11302Logic.getPageCount(ssc11302Param.getParam());
        List<SSC11302RsBeen> bidDetaillist = null;
            bidDetaillist = ssc11302Logic.findList(ssc11302Param.getParam());
            ssc11302RsPageResult.setSSC11302RsBeen(bidDetaillist);
            ssc11302RsPageResult.setTotalCount(count);
            response.setResult(ssc11302RsPageResult);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("操作成功");
        return response;
    }

    /**
     * 中标确认书详细页面删除产品数据接口
     *
     * @param ssc11302ParamRsRequest
     * @return
     */
    @RequestMapping(value = "/ssc/deleteProduct", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> deleteProduct(@RequestBody RsRequest<SSC11302Param> ssc11302ParamRsRequest) {

        RsResponse<Integer> result = new RsResponse<Integer>();
        int deleteLine = ssc11302Logic.deleteProduct(ssc11302ParamRsRequest.getParam());
        if (deleteLine > NumberConst.IntDef.INT_ZERO) {
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("操作成功!");
            result.setResult(deleteLine);
            return result;
        }
        result.setStatus(SystemConst.RsStatus.FAIL);
        result.setMessage("操作失败!");
        return result;

    }

    /**
     * 编辑产品
     *
     * @param ssc11302RsBeen
     * @return
     */
    @RequestMapping(value = "/ssc/modifyProduct", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> modifyProduct(@RequestBody RsRequest<SSC11302RsBeen> ssc11302RsBeen) {
        RsResponse<Integer> result = new RsResponse<Integer>();
        int updateLine = ssc11302Logic.modifyProduct(ssc11302RsBeen.getParam());
        if (updateLine > NumberConst.IntDef.INT_ZERO) {
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("操作成功!");
            result.setResult(updateLine);
            return result;
        }
        result.setStatus(SystemConst.RsStatus.FAIL);
        result.setMessage("操作失败!");
        return result;
    }

    /**
     * 状态更改
     *
     * @param ssc11301RsBean
     * @return
     */
    @RequestMapping(value = "/ssc/modifyBidStatus", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> modifyBidStatus(@RequestBody RsRequest<SSC11301RsBean> ssc11301RsBean) {
        RsResponse<Integer> result = new RsResponse<Integer>();

        //如果有一方确认了，将状态改为已确认
        SSC11301RsBean ssc11301RsBeanDb  = ssc11302Logic.findBidBaseByBidId(ssc11301RsBean.getParam());
        String bidStatus = ssc11301RsBeanDb.getBidStatus();

        if(bidStatus != null){
            if(bidStatus.equals(String.valueOf(SscConstant.BidStatus.PUR_CONFIRMED))
                    || bidStatus.equals(String.valueOf(SscConstant.BidStatus.SUPP_CONFIRMED))){
                ssc11301RsBean.getParam().setBidStatus(String.valueOf(SscConstant.BidStatus.BID_CONFIRMED));
            }
        }

        int updateLine = ssc11302Logic.modifyBidStatus(ssc11301RsBean.getParam());
        if (updateLine > NumberConst.IntDef.INT_ZERO) {
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("操作成功!");
            result.setResult(updateLine);
            return result;
        }
        result.setStatus(SystemConst.RsStatus.FAIL);
        result.setMessage("状态更改失败");
        return result;

    }

    //1130202迁移过来
    /**
     * 中标basic表数据插入接口
     *
     * @param ssc11301ParamRequest
     * @return
     */
    @RequestMapping(value = "/ssc/insertBidBasicInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11301RsPageResult> insertBidBasicInfo(@RequestBody RsRequest<SSC11301RsParam> ssc11301ParamRequest) {
        logger.info("插入中标basic表数据");
        RsResponse<SSC11301RsPageResult> rs = new RsResponse<SSC11301RsPageResult>();

        //进行判断
        if(null == ssc11301ParamRequest.getParam().getBidId()){
            SSC11301RsPageResult been = new SSC11301RsPageResult();
            Long id = commonLogic.maxId("SSC_BID_BASIC_INFO", "BID_ID");
            ssc11301ParamRequest.getParam().setBidId(id);


            //招标项目编号
            String projectNo = ssc11302Logic.findMaxBidProjectNo();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String today = sdf.format(DateTimeUtil.getCustomerDate());
            if(StringUtil.isNullOrEmpty(projectNo)){
                projectNo = "ZB" + today +  NumberConst.IntDef.INT_THOUSAND;
            }else{
                if(projectNo.length()== NumberConst.IntDef.INT_FOURTEEN){

                    String todayDB = projectNo.substring(2, 10);
                    if(today.equals(todayDB)){
                        Integer number = Integer.parseInt(projectNo.substring(10));
                        number ++;
                        projectNo = projectNo.substring(0 ,10)+number;
                    }else{
                        projectNo = "ZB" + today +"1000";
                    }
                }else{
                    rs.setStatus(SystemConst.RsStatus.FAIL);
                    rs.setMessage("招标项目编号生成错误,请联系管理员!");
                    return rs;
                }

            }
            ssc11301ParamRequest.getParam().setBidProjectNo(projectNo);

            int resultLine = ssc11302Logic.insertBidBasicInfo(ssc11301ParamRequest.getParam());
            if (resultLine > NumberConst.IntDef.INT_ZERO) {
                been.setBidId(id);
                been.setBidProjectNo(projectNo);
                been.setLine(resultLine);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("操作成功!");
                rs.setResult(been);
                return rs;
            }
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("新增中标项目基础信息错误!");
            return rs;
        }else{
            SSC11301RsPageResult been = new SSC11301RsPageResult();
            ssc11301ParamRequest.getParam().setBidId(ssc11301ParamRequest.getParam().getBidId());
            int updateLine=ssc11302Logic.modifyBasicBidInfo(ssc11301ParamRequest.getParam());
            if (updateLine > NumberConst.IntDef.INT_ZERO) {
                been.setBidId(ssc11301ParamRequest.getParam().getBidId());
                been.setBidProjectNo(ssc11301ParamRequest.getParam().getBidProjectNo());
                been.setLine(updateLine);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("操作成功!");
                rs.setResult(been);
                return rs;
            }
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("新增中标项目基础信息错误!");
            return rs;
        }


    }


    /**
     *中标detail表插入数据
     *
     * @param ssc11302RsBeen
     * @return
     */
    @RequestMapping(value = "/ssc/insertBidProductDetail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> insertBidProductDetail(@RequestBody RsRequest<SSC11302RsBeen> ssc11302RsBeen) {
        RsResponse<Integer> rs = new RsResponse<Integer>();
        int resultLine = 0;
        if(ssc11302RsBeen.getParam().getDetailId()!= null){
            //修改
            logger.info("中标detail表修改数据");
            resultLine = ssc11302Logic.modifyBidProductDetail(ssc11302RsBeen.getParam());
        }else{
            //新增
            logger.info("中标detail表插入数据");
            Long id = commonLogic.maxId("SSC_BID_PRODUCT_DETAIL", "ID");
            ssc11302RsBeen.getParam().setDetailId(id);
            resultLine = ssc11302Logic.insertBidProductDetail(ssc11302RsBeen.getParam());
        }
        if (resultLine > NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("操作成功!");
            rs.setResult(resultLine);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("保存产品加工质量标准表接口,数据错误！");
        return rs;
    }

    /**
     * 查询合同是否生成
     * @param ssc11301RsParam
     * @return
     */
    @RequestMapping(value = "/ssc/checkIsContract", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> checkIsContract(@RequestBody RsRequest<SSC11301RsParam> ssc11301RsParam) {
        logger.info("查询合同是否生成");
        RsResponse<Integer> rs = new RsResponse<Integer>();
        Integer contractId = this.ssc11302Logic.findContractCountByBidId(ssc11301RsParam.getParam());
        if(contractId !=null && contractId>0){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("合同已经生成!");
        }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询成功！");
        }
        rs.setResult(contractId);
        return rs;

    }

    /**
     *删除中标成交书基础表
     *
     * @param ssc11301RsParam
     * @return
     */
    @RequestMapping(value = "/ssc/deleteBidBase", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> deleteBidBase(@RequestBody RsRequest<SSC11301RsParam> ssc11301RsParam) {
        logger.info("删除中标成交书基础表");
        RsResponse<Integer> rs = new RsResponse<Integer>();

        RsResponse<Integer>  rsContractCount = this.checkIsContract(ssc11301RsParam);

        String msg = "";
        if(rsContractCount.getResult() !=null && rsContractCount.getResult()>0){
            //已经生成合同无法删除
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setResult(NumberConst.IntDef.INT_N_ONE);//标记-1 为已经生成合同，无法删除
            msg = "已经生成合同,无法删除！";
        }else{
            //删除
            SSC11301RsParam rsBeen = ssc11301RsParam.getParam();
            rsBeen.setDelFlg("1");
            rsBeen.setBidStatus(SscConstant.BidStatus.CANCEL+"");
            Integer result = this.ssc11302Logic.modifyBasicBidInfo(rsBeen);

            if(result!= null && result>0){
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                msg = "操作成功！";
            }else{
                rs.setStatus(SystemConst.RsStatus.FAIL);
                msg = "操作失败！";
            }
            rs.setResult(result);
        }
        rs.setMessage(msg);
        return rs;
    }




    /**
     * 根据中标id,pdCode 查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findBidPd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11302RsBeen> findBidPd(@RequestBody RsRequest<SSC11302Param> param) {
        logger.info("根据中标编号查询中标产品详细信息");
        RsResponse<SSC11302RsBeen> response = new RsResponse<SSC11302RsBeen>();
        SSC11302RsBeen result = new SSC11302RsBeen();
        Long id = this.ssc11302Logic.findBidPD(param.getParam());
        if (id != null && id > NumberConst.IntDef.INT_ZERO) {
            result.setDetailId(id);
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("操作成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }



}