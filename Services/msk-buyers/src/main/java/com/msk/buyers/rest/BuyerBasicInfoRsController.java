package com.msk.buyers.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.buyers.bean.*;
import com.msk.buyers.logic.*;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByBuyerPdCla;
import com.msk.core.entity.ByBuyerRecAddr;
import com.msk.core.entity.ByBuyerRecTime;
import com.msk.core.entity.ByBuyerSalestarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/4/27.
 */
@RestController
public class BuyerBasicInfoRsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(BuyerBasicInfoRsController.class);
    // 0代表加载，1-代表不加载
    private static int TRUE = 0;
    @Autowired
    private IBY121202Logic buyerInfoLogic;
    @Autowired
    private IBY121203Logic buyerProductClassLogic;
    @Autowired
    private IBY121207Logic employeeLogic;
    @Autowired
    private IBY121206Logic buyerPictureLogic;
    @Autowired
    private IBY121204Logic buyerSaleLogic;
    @Autowired
    private IBY121208Logic buyerRecAddrLogic;
    @Autowired
    private IBY121209Logic buyerRecTimeLogic;
    @Autowired
    private IBY121205Logic buyerLicenceLogic;

    /**
     * 根据买家ID获取买家相关信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/buyerInfo/relationInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.buyers.validator.BuyerBasicInfoValidator")
    public RsResponse<BuyerRelationResult> searchBuyerInfo(@RequestBody RsRequest<BuyerRelationParam> request) {
           BuyerRelationParam param = request.getParam();
                    BuyerRelationResult relationResult = new BuyerRelationResult();
                    // 加载买家基本信息
                    if(param.getIsLoadBuyerBasic()==TRUE){
                        logger.debug("开始加载买家基本信息");
                        List<BuyerBasicInfoBean> buyerBasicInfo = this.buyerInfoLogic.searchBuyerInfo(param);
                        relationResult.setBuyerBasicInfoList(buyerBasicInfo);
                    }
                    // 加载买家雇员信息
                    if(param.getIsLoadEmployee()==TRUE){
                        logger.debug("开始加载买家雇员信息");
                        List<IBY121207RsParam> employeeList = this.employeeLogic.buyerEmployeeList(param);
                        if(!employeeList.isEmpty()){
                            relationResult.setEmployeeList(employeeList);
                        }
                    }
                    // 加载买家经营产品类别信息
                     if(param.getIsLoadPdClass()==TRUE){
                         logger.debug("开始买家经营产品类别信息");
                         List<ByBuyerPdCla> productBeanList=  this.buyerProductClassLogic.buyerPdClassList(param);
                         if(!productBeanList.isEmpty()){
                             relationResult.setProductList(productBeanList);
                         }
                     }
                    // 加载买家收货地址信息
                    if(param.getIsLoadRecAddr()==TRUE){
                        logger.debug("开始买家收货地址信息");
                        List<ByBuyerRecAddr> recAddrList = this.buyerRecAddrLogic.buyerReceiveAddrList(param);
                        if(!recAddrList.isEmpty()){
                            relationResult.setRecAddrList(recAddrList);
                        }
                    }
                    // 加载买家收货时间信息
                    if(param.getIsLoadRecTime()==TRUE){
                        logger.debug("开始买家收货时间信息");
                        List<ByBuyerRecTime> buyerRecTimeBeanList = this.buyerRecTimeLogic.buyerReceiveTimeList(param);
                        if(!buyerRecTimeBeanList.isEmpty()){
                            relationResult.setRecTimeList(buyerRecTimeBeanList);
                        }
                    }
                    // 加载买家产品销售对象信息
                    if(param.getIsLoadSalesTarget()==TRUE){
                        logger.debug("开始买家产品销售对象信息");
                        List<ByBuyerSalestarget> buyerSaleList= this.buyerSaleLogic.buyerSalesTargetList(param);
                        if(!buyerSaleList.isEmpty()){
                            relationResult.setSaleList(buyerSaleList);
                        }
                    }
                    // 加载证照信息
                    if(param.getIsLoadLicence()==TRUE){
                        logger.debug("开始买家证照信息");
                        List<BuyerLicenceBean> licence = this.buyerLicenceLogic.buyerLicenceList(param);
                        relationResult.setLicence(licence);
                        // 当查询证照信息时，默认将证照图片查询出
                        param.setIsLoadPicture(TRUE);
                    }
                    // 加载图片信息
                    if(param.getIsLoadPicture()==TRUE){
                        logger.debug("开始买家证照图片信息");
                        List<IBY121206RsParam> buyerPicture = this.buyerPictureLogic.buyerLicencePicList(param);
                        relationResult.setBuyerPicture(buyerPicture);
                    }
                    return getRsResponse(relationResult);
    }

    private RsResponse<BuyerRelationResult> getRsResponse(BuyerRelationResult buyerInfo) {
        RsResponse<BuyerRelationResult> response = new RsResponse<BuyerRelationResult>();
        response.setResult(buyerInfo);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        logger.debug("加载信息结束");
        return response;
    }


    /**
     * 获取区域中买家的信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/disirictCode/allBuyers", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<BuyerBasicInfoBean>> findBuyerList(@RequestBody RsRequest<BuyerRelationParam> request){
        RsResponse<List<BuyerBasicInfoBean>> response = new RsResponse<List<BuyerBasicInfoBean>>();
        List<BuyerBasicInfoBean> buyerBasicInfoResult =  buyerInfoLogic.findBuyersList(request.getParam());
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(buyerBasicInfoResult);
        return response;
    }
}
