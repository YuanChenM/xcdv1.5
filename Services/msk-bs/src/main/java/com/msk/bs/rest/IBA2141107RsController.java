package com.msk.bs.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.*;
import com.msk.bs.logic.IBA2141102Logic;
import com.msk.bs.logic.IBA2141103Logic;
import com.msk.bs.logic.IBA2141110RsLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.*;
import com.msk.price.bean.*;
import com.msk.product.bean.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 获取产品通道阶梯价，产品特征，产品规格，产品等级，可销售数量
 * Created by ni_shaotang on 2016/7/15.
 */
@RestController
@Api(description = "获取产品通道阶梯价，产品特征，产品规格，产品等级等RestController", tags = {"IBA2141107RsController"})
public class IBA2141107RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBA2141107RsController.class);
    @Autowired
    private IBA2141110RsLogic iba2141110RsLogic;
    @Autowired
    private IBA2141102Logic iba2141102Logic;
    @Autowired
    private IBA2141103Logic iba2141103Logic;

    /**
     * 获取产品特征列表
     *
     * @return
     */
    @ApiOperation(value = "产品特征", notes = "获取产品特征列表接口")
    @RequestMapping(value = "/ba/productFeature", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<PdFeature>> productFeature(@RequestBody RsRequest<PDInfoParam> param) {
        RsResponse<List<PdFeature>> response = new RsResponse<List<PdFeature>>();
        logger.info("查询产品特征接口");
        PDInfoParam pdInfoParam = param.getParam();
        if (null != pdInfoParam && null != pdInfoParam.getPdCode() && pdInfoParam.getPdCode().length() >= 5) {
            pdInfoParam.setPdCode(pdInfoParam.getPdCode().substring(0, 5));
            //根据条件获取品种信息
            List<PDInfoResult> pdInfoList = CommRestUtil.getPDSup(pdInfoParam);
            List<PdFeature> featureList = new ArrayList<PdFeature>();
            PdFeature pdFeature = null;
            List<String> featureCodeList = new ArrayList<String>();
            for (PDInfoResult pdInfoResult : pdInfoList) {
                pdFeature = new PdFeature();
                pdFeature.setFeatureCode(pdInfoResult.getFeatureCode());
                pdFeature.setFeatureName(pdInfoResult.getFeatureName());
                if (!featureCodeList.contains(pdFeature.getFeatureCode())) {
                    featureList.add(pdFeature);
                    featureCodeList.add(pdFeature.getFeatureCode());
                }
            }
            //Modif for Bug#2379 at 2016/09/06 by ni_shaotang End
            response.setResult(featureList);
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            if (null != pdInfoList && pdInfoList.size() > 0) {
                response.setReturnCode(pdInfoList.get(0).getBreedName());
            }
        } else {
            response.setMessage("产品code不能为空");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }

    /**
     * 获取产品净重列表
     *
     * @return
     */
    @ApiOperation(value = "产品净重", notes = "获取产品净重列表接口")
    @RequestMapping(value = "/ba/productWeight", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<PdWeight>> productWeight(@RequestBody RsRequest<PDInfoParam> param) {
        RsResponse<List<PdWeight>> response = new RsResponse<List<PdWeight>>();
        logger.info("查询产品净重接口");
        PDInfoParam pdInfoParam = param.getParam();
        if (null != pdInfoParam && null != pdInfoParam.getPdCode() && pdInfoParam.getPdCode().length() >= 7) {
            pdInfoParam.setPdCode(pdInfoParam.getPdCode().substring(0, 7));
            //根据条件获取品种信息
            List<PDInfoResult> weightList = CommRestUtil.getPDSup(pdInfoParam);
            List<PdWeight> pdWeightList = new ArrayList<PdWeight>();
            List<String> weightCodeList = new ArrayList<String>();
            PdWeight pdWeight = null;
            for (PDInfoResult pdInfoResult : weightList) {
                pdWeight = new PdWeight();
                pdWeight.setWeightCode(pdInfoResult.getWeightCode());
                pdWeight.setWeightName(pdInfoResult.getWeightName());
                if (!weightCodeList.contains(pdWeight.getWeightCode())) {
                    weightCodeList.add(pdWeight.getWeightCode());
                    pdWeightList.add(pdWeight);
                }
            }
            //Modif for Bug#2379 at 2016/09/06 by ni_shaotang End
            response.setResult(pdWeightList);
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("产品code不能为空");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }

    /**
     * 获取产品等级列表
     *
     * @return
     */
    //Modif for 产品等级获取变更 at 2016/09/9 by ni_shaotang Start
    @ApiOperation(value = "产品等级", notes = "获取产品等级列表接口")
    @RequestMapping(value = "/ba/productGrade", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<PdGrade>> productGrade(@RequestBody RsRequest<PDInfoParam> param) {
        RsResponse<List<PdGrade>> response = new RsResponse<List<PdGrade>>();
        logger.info("查询产品等级接口");
        PDInfoParam pdInfoParam = param.getParam();
        if (null != pdInfoParam && null != pdInfoParam.getPdCode() && pdInfoParam.getPdCode().length() >= 9) {
            pdInfoParam.setPdCode(pdInfoParam.getPdCode().substring(0, 9));
            //根据条件获取品种信息
            List<PDInfoResult> resultList = CommRestUtil.getPDSup(pdInfoParam);
            List<PdGrade> gradeList = new ArrayList<PdGrade>();
            List<String> gradeCodeList = new ArrayList<String>();
            PdGrade pdGrade = null;

            for (PDInfoResult pdInfoResult : resultList) {
                pdGrade = new PdGrade();
                pdGrade.setGradeCode(pdInfoResult.getGradeCode());
                pdGrade.setGradeName(pdInfoResult.getGradeName());
                if (!gradeCodeList.contains(pdInfoResult.getGradeCode())) {
                    gradeList.add(pdGrade);
                    gradeCodeList.add(pdInfoResult.getGradeCode());
                }
            }
//Modif for 产品等级获取变更 at 2016/09/9 by ni_shaotang End
            response.setResult(gradeList);
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("产品code不能为空");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }

    /**
     * 获取产品价盘通道等级
     *
     * @return
     */
    @ApiOperation(value = "产品价盘通道等级", notes = "获取产品价盘通道等级接口")
    @RequestMapping(value = "/ba/pdPriceWay", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141106RsBean>> pdPriceWay(@RequestBody RsRequest<String> param) {
        RsResponse<List<IBA2141106RsBean>> response = new RsResponse<List<IBA2141106RsBean>>();
        logger.info("查询产品等级接口");
        List<IBA2141106RsBean> list = new ArrayList<IBA2141106RsBean>();
        IBA2141106RsBean rsBean = null;

        //组织参数
        String pdCode = param.getParam();
        if (!StringUtil.isNullOrEmpty(pdCode) && pdCode.length() == 10) {

            String classCode = pdCode.substring(0, 2);
            String machiningCode = pdCode.substring(2, 3);
            String breedCode = pdCode.substring(3, 5);
            String featureCode = pdCode.substring(5, 7);
            String weightCode = pdCode.substring(7, 9);
            String gradeCode = pdCode.substring(9, 10);

            ISP171183Param params = new ISP171183Param();
            params.setClassesCode(classCode);
            params.setMachiningCode(machiningCode);
            params.setBreedCode(breedCode);
            params.setFeatureCode(featureCode);
            params.setWeightCode(weightCode);
            params.setGradeCode(gradeCode);

            List<ISP171183Bean> priceList = CommRestUtil.getGetPriceCycle(params);
            if (null != priceList && priceList.size() > 0) {
                List<ISP171184Bean> wayList = CommRestUtil.getPdPriceWay(pdCode);
                ISP171183Bean priceBean = priceList.get(0);
                response.setReturnCode(priceBean.getPricePeriod());
                List<ISP171183Bean> priceLists = priceBean.getPricelist();
                if (null != wayList && wayList.size() > 0) {
                    for (ISP171183Bean bean : priceLists) {
                        if (!bean.getOrderLevel().equals("0")) {//产品暂时没有顶级阶梯通道
                            for (ISP171184WayBean wayBean : wayList.get(0).getWaylist()) {
                                if (wayBean.getOrderLevel().equals(bean.getOrderLevel())) {
                                    rsBean = new IBA2141106RsBean();
                                    rsBean.setBoxMin(wayBean.getBoxMin());
                                    rsBean.setBoxMax(wayBean.getBoxMax());
                                    rsBean.setOrderLevel(wayBean.getOrderLevel());
                                    rsBean.setPriceOfBox(bean.getPriceOfBox());
                                    rsBean.setPriceOfKg(bean.getPriceOfKg());
                                    if (wayBean.getOrderLevel().equals("1")) {
                                        rsBean.setBoxName("≥" + wayBean.getBoxMin());
                                    } else {
                                        rsBean.setBoxName(wayBean.getBoxMin() + "-" + wayBean.getBoxMax());
                                    }
                                    list.add(rsBean);
                                }
                            }
                        }
                    }
                }
            }
            response.setResult(list);
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        }
        return response;
    }

    /**
     * 添加购物车
     *
     * @return
     */
    @ApiOperation(value = "购物车", notes = "添加购物车接口")
    @RequestMapping(value = "/ba/addCar", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ShopingCarBean> addCar(@RequestBody RsRequest<ShopingCarParam> param) throws UnsupportedEncodingException {
        RsResponse<ShopingCarBean> response = new RsResponse<ShopingCarBean>();
        if (null == param.getParam()) {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("购买失败，参数格式不正确");
            return response;
        }
        ShopingCarParam params = param.getParam();
        if (StringUtil.isNullOrEmpty(params.getSlTel())) {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("购买失败，登录信息失效请重新登录");
            return response;
        }
        if (StringUtil.isNullOrEmpty(params.getPdCode())) {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("购买失败，产品信息错误");
            return response;
        }
        if (null == params.getPdNum()) {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("购买失败，购买数量错误");
            return response;
        }
        Date newDate = DateTimeUtil.getCustomerDate();
        IBA2141102Param accountParam = new IBA2141102Param();

        params.setCrtId("买手APP");
        params.setCrtTime(newDate);
        params.setUpdId("买手APP");
        params.setUpdTime(newDate);
        params.setActId("买手APP");
        params.setActTime(newDate);

        accountParam.setSlTel(params.getSlTel());
        if (params.getBuyersType() == NumberConst.IntDef.INT_TWO) {
            SlHouseAccount slHouseAccount = iba2141103Logic.getHouseAccount(accountParam);
            params.setBuyersId(slHouseAccount.getHouseCode());
            params.setBreedName(slHouseAccount.getHouseShowName());
        } else {
            BsAccount bsAccount = iba2141102Logic.getBsAccount(accountParam);
            params.setBuyersId(bsAccount.getSlAccount());
            params.setBreedName(bsAccount.getSlShowName());
        }
        params.setSellerCode("0000");
        params.setSellerName("买手app");
        params.setOrderSource(1);
        params.setCarSource("买手APP");
        params.setRemovEeason(0);
        params.setPdName(URLDecoder.decode(params.getPdName(), "UTF-8"));
        ShopingCarBean shopingCarBean = iba2141110RsLogic.saveShopingCar(params);
        response.setMessage("添加购物车成功");
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setResult(shopingCarBean);
        return response;

    }

    /**
     * 查询产品库存
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "产品库存", notes = "查询产品库存接口")
    @RequestMapping(value = "/ba/getFindProductStock", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141107RsBean>> getFindProductStock(@RequestBody RsRequest<IBA2141107RsParam> request) {
        RsResponse<List<IBA2141107RsBean>> response = new RsResponse<List<IBA2141107RsBean>>();
        List<IBA2141107RsBean> list = new ArrayList<IBA2141107RsBean>();
        IBA2141107RsBean bean = null;
        IPD141144RsParam rsParam = new IPD141144RsParam();
        IBA2141107RsParam param = request.getParam();
        List<String> pdCodeList = param.getPdCodeList();

        //定义参数
        rsParam.setSellerCode(param.getSlCode());
        rsParam.setPlatformType("1");//默认1：神农客库存（云冻品）
        rsParam.setDistrictCode(param.getLgcsCode());
        rsParam.setSellerType(param.getSellerType());
        if (null != pdCodeList && pdCodeList.size() == 1) {
            rsParam.setPdCode(pdCodeList.get(0));
        }
        RsResponse<IBA2141107RsResult> rsResponse = CommRestUtil.getFindProductStock(rsParam);
        IBA2141107RsResult rsResult = rsResponse.getResult();
        List<IPD141144RsProductsResult> stockList = new ArrayList<IPD141144RsProductsResult>();
        if (null != rsResult) {
            stockList = rsResult.getProducts();
        }
        if (null != pdCodeList && pdCodeList.size() > 0) {
            for (String pdCode : pdCodeList) {
                bean = new IBA2141107RsBean();
                bean.setPdCode(pdCode);
                for (IPD141144RsProductsResult stock : stockList) {
                    if (pdCode.equals(stock.getPdCode())) {
                        bean.setSlCode(rsResult.getSellerCode());
                        bean.setLgcsName(rsResult.getDistrictName());
                        bean.setLgcsCode(rsResult.getDistrictCode());
                        bean.setStockCnt(stock.getStockCnt());
                        continue;
                    }
                }
                list.add(bean);
            }
        } else {
            for (IPD141144RsProductsResult stock : stockList) {
                bean = new IBA2141107RsBean();
                bean.setPdCode(stock.getPdCode());
                bean.setSlCode(rsResult.getSellerCode());
                bean.setLgcsName(rsResult.getDistrictName());
                bean.setLgcsCode(rsResult.getDistrictCode());
                bean.setStockCnt(stock.getStockCnt());
                list.add(bean);
            }
        }
        //如果查询委托订单库存：与平台库存对比取大值
        if (param.getSellerType() == 2) {
            List<IBA2141107RsBean> sysList = new ArrayList<IBA2141107RsBean>();
            rsParam.setSellerType(1);
            rsParam.setSellerCode("0000000");//默认平台编号
            RsResponse<IBA2141107RsResult> sysResponse = CommRestUtil.getFindProductStock(rsParam);
            if (sysResponse.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
                IBA2141107RsResult sysResult = sysResponse.getResult();
                List<IPD141144RsProductsResult> sysStockList = sysResult.getProducts();
                for (IPD141144RsProductsResult stock : sysStockList) {
                    bean = new IBA2141107RsBean();
                    bean.setSlCode(sysResult.getSellerCode());
                    bean.setLgcsCode(sysResult.getDistrictCode());
                    bean.setLgcsName(sysResult.getDistrictName());
                    bean.setPdCode(stock.getPdCode());
                    bean.setStockCnt(stock.getStockCnt());
                    if (null != pdCodeList && pdCodeList.size() > 0) {
                        for (String pdCode : pdCodeList) {
                            if (pdCode.equals(stock.getPdCode())) {
                                sysList.add(bean);
                            }
                        }
                    } else {
                        sysList.add(bean);
                    }
                }
            }
            if (null != list && list.size() > 0) {
                //对比平台库存与买手囤货库存取最大值
                for (IBA2141107RsBean sysBean : sysList) {
                    for (IBA2141107RsBean slBean : list) {
                        if (sysBean.getPdCode().equals(slBean.getPdCode())) {
                            slBean.setLgcsCode(sysBean.getLgcsCode());
                            if (sysBean.getStockCnt().compareTo(slBean.getStockCnt()) > 0) {
                                slBean.setStockCnt(sysBean.getStockCnt());
                            }
                        }
                    }
                }
            } else {
                list.addAll(sysList);
            }
        }
        if (null != list && list.size() > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询库存成功");
            response.setResult(list);
        } else {
            response.setStatus(rsResponse.getStatus());
            response.setMessage(rsResponse.getMessage());
        }
        return response;
    }
}