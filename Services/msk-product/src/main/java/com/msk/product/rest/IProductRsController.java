package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.*;
import com.msk.product.bean.*;
import com.msk.product.logic.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 产品共通接口
 * Created by yang_chunyan on 2016/4/29.
 */
@RestController
@Api(description = "产品共通接口RestController", tags = {"IProductRsController"})
public class IProductRsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IProductRsController.class);

    @Autowired
    private ProductStdLogic productStdLogic;
    @Autowired
    private ProductInfoLogic productInfoLogic;
    @Autowired
    private ProductLogic productLogic;
    @Autowired
    private PriceprdLogiareaLogic priceprdLogiareaLogic;
    @Autowired
    private ProviderPackageLogic providerPackageLogic;
    @Autowired
    private PD14112601Logic pd14112601Logic;
    @Autowired
    private ProductBuyerStdLogic productBuyerStdLogic;

    /**
     * 产品信息查询接口根据产品code（codes）
     *
     * @return RsResponse 结果
     * @author yang_chunyan
     */
    @ApiOperation(value = "产品信息/分类/品种", notes = "根据产品编码查询产品信息(位数不同查询相关的信息)\n" +
            "根据产品编码查询一级分类和品种")
    @RequestMapping(value = "/findProductInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<PDInfoResult> findPdInfo(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询产品信息");
        RsResponse<PDInfoResult> rs = new RsResponse<PDInfoResult>();
        PDInfoResult bean = null;
        bean = productInfoLogic.findPdInfo(param.getParam());
        if (bean != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据正常！");
            rs.setResult(bean);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品信息查询数据异常请检查!");
        return rs;
    }

    /**
     * 批量查询
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "包装规格&产品信息", notes = "根据批量产品编码和包装规格编码查询产品包装规格信息及产品信息")
    @RequestMapping(value = "/product/findProductInfos",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    // @Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<ProductBeanResult> findPdInfos(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("批量查询产品信息");
        RsResponse<ProductBeanResult> rs = new RsResponse<ProductBeanResult>();
        ProductBeanResult beanResult = new ProductBeanResult();
        /**Modify: 横展开修改模糊查询 任强 2016/09/29 Start*/
        List<PDInfoResult> res = null;
        if(param != null){
            if(param.getParam() != null){
                PDInfoParam pdInfoParam = param.getParam();
                pdInfoParam.setClassesName(DbUtils.buildLikeCondition(pdInfoParam.getClassesName(),
                        DbUtils.LikeMode.PARTIAL));
                pdInfoParam.setMachiningName(DbUtils.buildLikeCondition(pdInfoParam.getMachiningName
                        (), DbUtils.LikeMode.PARTIAL));
                pdInfoParam.setBreedName(DbUtils.buildLikeCondition(pdInfoParam.getBreedName(),
                        DbUtils.LikeMode.PARTIAL));
                pdInfoParam.setFeatureName(DbUtils.buildLikeCondition(pdInfoParam.getFeatureName(),
                        DbUtils.LikeMode.PARTIAL));
                pdInfoParam.setWeightsName(DbUtils.buildLikeCondition(pdInfoParam.getWeightsName(),
                        DbUtils.LikeMode.PARTIAL));
                res = productInfoLogic.findPdInfos(pdInfoParam);
            }
        }
        /**Modify: 横展开修改模糊查询 任强 2016/09/29 End*/
        if (res != null) {
            beanResult.setResult(res);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据正常！");
            rs.setResult(beanResult);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品批量信息查询数据异常请检查!");
        return rs;
    }

    /**
     * 根据Code获取Name
     *
     * @return RsResponse 结果
     */
    @ApiOperation(value = "产品类别名称", notes = "根据对应的产品类别编码查询对应的产品类别名称")
    @RequestMapping(value = "/pdTypeName",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<ProductBeanResult> findPdTypeName(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("根据产品Code获取产品名称信息");
        ProductBeanResult pdBean = new ProductBeanResult();
        RsResponse<ProductBeanResult> rs = new RsResponse<ProductBeanResult>();
        String msg = "";
        List<PDInfoResult> results = new ArrayList<PDInfoResult>();
        String code = param.getParam().getCode();
        Integer type = param.getParam().getType();
        if (null != type) {
            switch (type) {
                case NumberConst.IntDef.INT_ONE:
                    results = productInfoLogic.findPdClasses(code);
                    msg = "产品一级分类名称";
                    break;
                case NumberConst.IntDef.INT_TWO:
                    results = productInfoLogic.findPdMachining(code);
                    msg = "产品二级分类名称";
                    break;
                case NumberConst.IntDef.INT_THREE:
                    results = productInfoLogic.findPdBreed(code);
                    msg = "产品品种名称";
                    break;
                case NumberConst.IntDef.INT_FOUR:
                    results = productInfoLogic.findPdFeature(code);
                    msg = "产品特征名称";
                    break;
                case NumberConst.IntDef.INT_FIVE:
                    results = productInfoLogic.findPdWeight(code);
                    msg = "产品净重名称";
                    break;
                case NumberConst.IntDef.INT_SIX:
                    results = productInfoLogic.findPdGrade(code);
                    msg = "产品等级名称";
                    break;

            }
            if (results != null) {
                pdBean.setResult(results);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage(msg + "数据正常！");
                rs.setResult(pdBean);
                return rs;
            }
        }
        msg = "查询产品名称接口";
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage(msg + "数据错误查询异常!");
        return rs;
    }

    /**
     * 产品等级&卫生质量标准（八张档案卡）
     *
     * @return RsResponse 结果
     * @author yang_chunyan
     */
    @ApiOperation(value = "产品等级&卫生质量标准(八张档案卡)", notes = "产品档案卡信息接口")
    @RequestMapping(value = "/pdProductStd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IProductStdValidator")
    public RsResponse<ProductStdResult> findListStd(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询产品档案卡信息");
        RsResponse<ProductStdResult> rs = new RsResponse<ProductStdResult>();
        ProductStdResult bean = new ProductStdResult();
        String msg = "";
        boolean isExist = false;
        PDInfoParam rsParam = param.getParam();
        Integer stdType = rsParam.getType();
        switch (stdType) {
            case NumberConst.IntDef.INT_ONE:
                List<MctStdBean> mctStdBeans = new ArrayList<MctStdBean>();
                mctStdBeans = this.productStdLogic.getMctStd(param.getParam());
                msg = "产品技术标准";
                bean.setMctStdList(mctStdBeans);
                if(null != mctStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_TWO:
                List<PdClassestreeMat> result = new ArrayList<PdClassestreeMat>();
                result = this.productStdLogic.selectSourceMat(param);
                msg = "原料种源信息";
                bean.setMatStdList(result);
                if(null != result)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_THREE:
                List<OrgStdBean> orgStdBeans = new ArrayList<OrgStdBean>();
                orgStdBeans = this.productStdLogic.getOrgStdInfo(param.getParam());
                msg = "原种种源档案卡";
                bean.setOrgStdList(orgStdBeans);
                if(null != orgStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_FOUR:
                List<FedStdBean> fedStdBeans = new ArrayList<FedStdBean>();
                fedStdBeans = this.productStdLogic.getFedStdInfo(param.getParam());
                msg = "饲养指标档案卡";
                bean.setFedStdlist(fedStdBeans);
                if(null != fedStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_FIVE:
                List<GnqStdBean> gnqStdBeans = new ArrayList<GnqStdBean>();
                gnqStdBeans = this.productStdLogic.getGnqStdInfo(param.getParam());
                msg = "通用质量指标档案卡";
                bean.setGnqStdlist(gnqStdBeans);
                if(null != gnqStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_SIX:
                List<TspStdBean> tspStdBeans = new ArrayList<TspStdBean>();
                tspStdBeans = this.productStdLogic.getTspStdInfo(param.getParam());
                msg = "存储运输指标档案卡";
                bean.setTspStdlist(tspStdBeans);
                if(null != tspStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_SEVEN:
                List<SftStdBean> sftStdBeans = new ArrayList<SftStdBean>();
                sftStdBeans = this.productStdLogic.getSftStdInfo(param.getParam());
                msg = "产品等级&卫生质量标准";
                bean.setSftStdlist(sftStdBeans);
                if(null != sftStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_EIGHT:
                List<TncStdBean> tncStdBeans = new ArrayList<TncStdBean>();
                tncStdBeans = this.productStdLogic.getTncStdInfo(param.getParam());
                msg = "产品标准质量档案卡";
                bean.setTncStdList(tncStdBeans);
                if(null != tncStdBeans)
                    isExist = true;
                break;
        }
        if (isExist) {
            logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage(msg + "接口成功！");
            rs.setResult(bean);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage(msg + "接口,数据错误请检查！");
        return rs;

    }


    /**
     * 批量获取产品类型名
     *
     * @return RsResponse 结果
     */
    @ApiOperation(value = "批量查询产品类别信息", notes = "批量查询产品类别信息接口")
    @RequestMapping(value = "/pdBatchName",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<ProductBeanResult> findPdBatchName(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("批量查询产品信息");
        RsResponse<ProductBeanResult> rs = new RsResponse<ProductBeanResult>();
        ProductBeanResult bean = new ProductBeanResult();
        List<PDInfoResult> classesResults = null;
        List<PDInfoResult> machiningResults = null;
        List<PDInfoResult> breedResults = null;
        List<PDInfoResult> featureResults = null;
        List<PDInfoResult> weightResults = null;
        List<PDInfoResult> gradeResults = null;
        List<PDInfoResult> normsResults = null;

        Map<String, List<PDInfoResult>> result = new HashMap<String, List<PDInfoResult>>();

        List<PDInfoParam> classCodes = param.getParam().getClassesCodeList();
        List<PDInfoParam> machiningCodes = param.getParam().getMachiningCodeList();
        List<PDInfoParam> breedCodes = param.getParam().getBreedCodeList();
        List<PDInfoParam> featureCodes = param.getParam().getFeatureCodeList();
        List<PDInfoParam> weightCodes = param.getParam().getWeightCodeList();
        List<PDInfoParam> gradeCodes = param.getParam().getGradeCodeList();
        List<String> normsCodes = param.getParam().getNormCodes();
        if (CollectionUtils.isNotEmpty(classCodes) && classCodes.size() > 0) {
        classesResults = productInfoLogic.findPdClassesByCodes(classCodes);
        }
        if (CollectionUtils.isNotEmpty(machiningCodes)) {
            machiningResults = productInfoLogic.findPdMachiningByCodes(machiningCodes);
        }
        if (CollectionUtils.isNotEmpty(breedCodes)) {
            breedResults = productInfoLogic.findPdBreedByCodes(breedCodes);
        }
        if (CollectionUtils.isNotEmpty(featureCodes)) {
            featureResults = productInfoLogic.findPdFeatureByCodes(featureCodes);
        }
        if (CollectionUtils.isNotEmpty(weightCodes)) {
            weightResults = productInfoLogic.findPdWeightByCodes(weightCodes);
        }
        if (CollectionUtils.isNotEmpty(gradeCodes)) {
            gradeResults = productInfoLogic.findPdGradeByCodes(gradeCodes);
        }
        if(CollectionUtils.isNotEmpty(normsCodes)){
            normsResults = productLogic.findPdPackagePageList(param.getParam(), new ProductBeanResult());
        }
        result.put("classes", classesResults);
        result.put("machining", machiningResults);
        result.put("breed", breedResults);
        result.put("feature", featureResults);
        result.put("weight", weightResults);
        result.put("grade", gradeResults);
        result.put("norms",normsResults);
        bean.setPdInfo(result);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询数据正常！");
        rs.setResult(bean);
        return rs;
    }

    /**
     * 产品包装一览查询接口
     *
     * @return RsResponse 结果
     */
    @ApiOperation(value = "产品包装查询", notes = "产品包装查询接口\n" +
            "根据批量产品标准ID和包装规格编码查询产品包装规格信息")
    @RequestMapping(value = "/findProductPackage",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IProductPackageValidator")
    public RsResponse<ProductBeanResult> findBreedList(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询产品包装信息");
        ProductBeanResult bean = new ProductBeanResult();
        RsResponse<ProductBeanResult> rs = new RsResponse<ProductBeanResult>();
        //查询后台,获取List数据
        List<PDInfoResult> result = null;
        PDInfoParam rsParam = param.getParam();
        result = this.productLogic.findPdPackagePageList(rsParam,bean);
        if (null != result) {
            bean.setResult(result);
            //logger.info("查询产品包装一览查询接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品包装一览查询接口成功！");
            rs.setResult(bean);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品包装一览查询接口,数据错误！");
        return rs;

    }

    /**
     * 获取供应商产品信息
     *
     * @return RsResponse 结果
     * @author yang_chunyan
     */
    @ApiOperation(value = "供应商（OEM）需求发布", notes = "获取供应商产品信息接口")
    @RequestMapping(value = "/getPDSupp",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<ProductPageResult> findPDSupp(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询价盘供应商产品信息");
        RsResponse<ProductPageResult> rs = new RsResponse<ProductPageResult>();
        ProductPageResult pdResult = new ProductPageResult();
        PDInfoParam rsParam = param.getParam();
        /**Modify: 横展开修改模糊查询 任强 2016/09/29 Start*/
        if(rsParam != null){
            rsParam.setLgcsName(DbUtils.buildLikeCondition(rsParam.getLgcsName(),
                    DbUtils.LikeMode.PARTIAL));
            rsParam.setClassesName(DbUtils.buildLikeCondition(rsParam.getClassesName(),
                    DbUtils.LikeMode.PARTIAL));
            rsParam.setMachiningName(DbUtils.buildLikeCondition(rsParam.getMachiningName
                    (), DbUtils.LikeMode.PARTIAL));
            rsParam.setBreedName(DbUtils.buildLikeCondition(rsParam.getBreedName(),
                    DbUtils.LikeMode.PARTIAL));
            rsParam.setFeatureName(DbUtils.buildLikeCondition(rsParam.getFeatureName(),
                    DbUtils.LikeMode.PARTIAL));
            rsParam.setWeightsName(DbUtils.buildLikeCondition(rsParam.getWeightsName(),
                    DbUtils.LikeMode.PARTIAL));
            rsParam.setPdCode(DbUtils.buildLikeCondition(rsParam.getPdCode(),DbUtils.LikeMode.FRONT));
            rsParam.setPdName(DbUtils.buildLikeCondition(rsParam.getPdName(),DbUtils.LikeMode.PARTIAL));
        }
        /**Modify: 横展开修改模糊查询 任强 2016/09/29 End*/
        List<PDInfoResult> result = productInfoLogic.findPdPageList(rsParam, pdResult);
        pdResult.setPdInfo(result);
        if (result != null) {
            // logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询供应商产品接口成功！");
            rs.setResult(pdResult);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询供应商产品接口,数据错误！");
        return rs;

    }

    /**
     * 查询产品价盘区域
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "产品价盘区域", notes = "查询产品价盘区域接口")
    @RequestMapping(value = "/getPriceprdLogiarea",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ProductBeanResult> getPriceprdLogiarea(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询价盘供应商产品信息");
        RsResponse<ProductBeanResult> rs = new RsResponse<ProductBeanResult>();
        ProductBeanResult bean = new ProductBeanResult();
        BaseParam baseParam = new BaseParam();
        List<PdPriceprdLogiarea> pdPriceprdLogiareas = priceprdLogiareaLogic.findList(baseParam);
        if (null != pdPriceprdLogiareas) {
            bean.setLogiares(pdPriceprdLogiareas);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品区域信息接口成功！");
            rs.setResult(bean);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品区域信息接口,数据错误！");
        return rs;

    }

    /**
     * OEM申报数量一览产品信息接口
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "产品分类", notes = "卖家产品分类信息获取接口")
    @RequestMapping(value = "/getPdClassesTreeInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<ProductBeanResult> getPdClassesTreeInfo(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询产品分类信息");
        RsResponse<ProductBeanResult> rs = new RsResponse<ProductBeanResult>();
        PDInfoParam rsParam = param.getParam();
        ProductBeanResult bean = new ProductBeanResult();
        List<PDInfoResult> result = productInfoLogic.findPdClassesTree(rsParam);
        bean.setResult(result);
        if (result != null) {
            // logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品分类接口成功！");
            rs.setResult(bean);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品分类接口,数据错误！");
        return rs;

    }

    /**
     * 查询产品原料产地
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "分类原料", notes = "根据产品分类code查询产品分类原料")
    @RequestMapping(value = "/product/getPdClassesTreeMatInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<ProductBeanResult> getPdClassesTreeMatInfo(@RequestBody RsRequest<PDInfoParam> param) {

        logger.info("查询查询产品原料产地信息");
        RsResponse<ProductBeanResult> rs = new RsResponse<ProductBeanResult>();
        PDInfoParam rsParam = param.getParam();
        ProductBeanResult bean = new ProductBeanResult();
        List<PdClassestreeMat> result = productInfoLogic.findPdClassesTreeMat(rsParam);
        bean.setTreeMatList(result);
        if (result != null) {
            // logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询查询产品原料产地接口成功！");
            rs.setResult(bean);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询查询产品原料产地接口,数据错误！");
        return rs;

    }

    /**
     * 查询卖家申请产品审核数据
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "产品包装信息", notes = "根据产品卖家/一级分类/二级分类/品种/特征/净重的代码或名称，批量查询产品包装信息")
    @RequestMapping(value = "/product/getProviderPackageInfo",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IProductRsValidator")
    public RsResponse<ProductPageResult> getProviderPackageInfo(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询卖家申请产品审核数据信息");
        RsResponse<ProductPageResult> rs = new RsResponse<ProductPageResult>();
        ProductPageResult pdResult = new ProductPageResult();
        PDInfoParam rsParam = param.getParam();
        /**Add: 横展开设置模糊查询条件 2016/09/28   BY  任强  Start */
         if(rsParam != null){
             DbUtils.buildLikeCondition(rsParam, "providerName", DbUtils.LikeMode.PARTIAL);
             DbUtils.buildLikeCondition(rsParam, "classesName", DbUtils.LikeMode.PARTIAL);
             DbUtils.buildLikeCondition(rsParam, "machiningName", DbUtils.LikeMode.PARTIAL);
             DbUtils.buildLikeCondition(rsParam, "breedName", DbUtils.LikeMode.PARTIAL);
             DbUtils.buildLikeCondition(rsParam, "featureName", DbUtils.LikeMode.PARTIAL);
             DbUtils.buildLikeCondition(rsParam, "weightName", DbUtils.LikeMode.PARTIAL);
         }
        /**Add: 横展开设置模糊查询条件 2016/09/28   BY  任强  End */
        List<PDInfoResult> result = providerPackageLogic.findProviderPackagePageList(rsParam, pdResult);
        pdResult.setPdInfo(result);
        if (result != null) {
            // logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家申请产品审核数据信息接口成功！");
            rs.setResult(pdResult);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询卖家申请产品审核数据接口,数据错误！");
        return rs;
    }

    /**
     * 保存产品加工质量标准表
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "加工技术", notes = "批量保存产品加工技术标准表")
    @RequestMapping(value = "/product/saveMctProvider",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> insertMctProvider(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("保存产品加工质量标准表");
        RsResponse<Integer> rs = new RsResponse<Integer>();
        /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
        String loginId="";
        if(param != null){
            loginId = param.getLoginId();
        }
        PDInfoParam rsParam = param.getParam();
        rsParam.setCrtId(loginId);
        rsParam.setUpdId(loginId);
        rsParam.setActId(loginId);
        /**Add: 横展开添加共通设置 2016/09/27   BY  任强  End */
        int result = providerPackageLogic.saveMctProvider(rsParam);
        if (result > NumberConst.IntDef.INT_ZERO) {
            // logger.info(msg + "接口成功！DB连接");
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("保存产品加工质量标准表接口成功！" + "影响条数" + result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("保存产品加工质量标准表接口,数据错误！");
        return rs;
    }

    /**
     * 查询产品标准
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "产品标准", notes = "产品主码查询接口\n" +
            "查询产品标准表")
    @RequestMapping(value = "/product/findProductStandard",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<PdStandard>> findProductStandard(@RequestBody RsRequest<BaseParam> param) {
        logger.info("查询产品标准");
        RsResponse<List<PdStandard>> rs = new RsResponse<List<PdStandard>>();
        /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
        String loginId="";
        if(param != null){
            loginId = param.getLoginId();
        }
        BaseParam rsParam = param.getParam();
        rsParam.setCrtId(loginId);
        rsParam.setUpdId(loginId);
        rsParam.setActId(loginId);
        /**Add: 横展开添加共通设置 2016/09/27   BY  任强  End */
        List<PdStandard> result = productLogic.findPdStandard(rsParam);
        if (result != null) {
            // logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品标准接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品标准接口,数据错误！");
        return rs;
    }

    /**
     * 选择三级分类 或 二级分类  获取产品总控目录数据
     *
     * @param param param
     * @return
     *
     */
    @ApiOperation(value = "产品总控目录", notes = "选择三级分类或二级分类，获取产品总控目录数据")
    @RequestMapping(value = "/product/findProviderList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<PD14112601Param>> queryData(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("获取产品总控目录数据");
        RsResponse<List<PD14112601Param>> rs = new RsResponse<List<PD14112601Param>>();
        /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
        String loginId="";
        if(param != null){
            loginId = param.getLoginId();
        }
        List<PD14112601Param> result = new ArrayList<>();
        PDInfoParam rsParam = param.getParam();
        rsParam.setCrtId(loginId);
        rsParam.setUpdId(loginId);
        rsParam.setActId(loginId);
        /**Add: 横展开添加共通设置 2016/09/27   BY  任强  End */
        String classCodeThree = rsParam.getClassCodeThree();
        String classCodeTwo = rsParam.getClassCodeTwo();
        if (null != classCodeThree) {
                result = pd14112601Logic.queryThreeData(classCodeThree);
        } else {
            result = pd14112601Logic.queryTwoData(classCodeTwo);
        }
        if (!CollectionUtils.isEmpty(result)) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("获取产品总控目录数据接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("获取产品总控目录数据,数据错误！");
        return rs;
    }

    /**
     * 根据条件插入申请审核产品信息
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "产品信息", notes = "批量保存申请审核产品信息")
    @RequestMapping(value = "/product/saveProviderPackage",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> saveProviderPackage(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("根据条件插入产品信息");
        RsResponse<Integer> rs = new RsResponse<Integer>();
        /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
        String loginId="";
        if(param != null){
            loginId = param.getLoginId();
        }
        PDInfoParam rsParam = param.getParam();
        rsParam.setCrtId(loginId);
        rsParam.setUpdId(loginId);
        rsParam.setActId(loginId);
        /**Add: 横展开添加共通设置 2016/09/27   BY  任强  End */
        int result = providerPackageLogic.saveProviderPackage(rsParam);
        if (result > NumberConst.IntDef.INT_ZERO) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("根据条件插入产品信息接口成功！" + "影响条数" + result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("根据条件插入产品信息,数据错误！");
        return rs;
    }

    /**
     * 买家产品标准调研用
     *
     * @return RsResponse 结果
     * @author yang_chunyan
     */
    @ApiOperation(value = "买家调研", notes = "查询买家调研标准信息")
    @RequestMapping(value = "/product/findBuyerProductStd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IProductStdValidator")
    public RsResponse<ProductStdResult> findBuyerProductStd(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("查询买家产品标准调研信息");
        RsResponse<ProductStdResult> rs = new RsResponse<ProductStdResult>();
        ProductStdResult bean = new ProductStdResult();
        String msg = "";
        boolean isExist = false;
        PDInfoParam rsParam = param.getParam();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("classesCode",rsParam.getClassesCode());
        baseParam.setFilter("machiningCode",rsParam.getMachiningCode());
        baseParam.setFilter("breedCode",rsParam.getBreedCode());
        baseParam.setFilter("treeCode",rsParam.getTreeCode());
        Integer stdType = rsParam.getType();
        switch (stdType) {
            case NumberConst.IntDef.INT_ONE:
                List<ByResearchStdMct> mctStdBeans = new ArrayList<ByResearchStdMct>();
                mctStdBeans = this.productBuyerStdLogic.getPdMct(baseParam);
                msg = "买家产品技术标准调研";
                bean.setByResearchStdMcts(mctStdBeans);
                if(null != mctStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_TWO:
                List<ByResearchStdOrg> orgStdBeans = new ArrayList<ByResearchStdOrg>();
                orgStdBeans = this.productBuyerStdLogic.findBuyerOrgList(baseParam);
                msg = "买家原种种源标准调研";
                bean.setByResearchStdOrgs(orgStdBeans);
                if(null != orgStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_THREE:
                List<ByResearchStdFed> fedStdBeans = new ArrayList<ByResearchStdFed>();
                fedStdBeans = this.productBuyerStdLogic.findBuyerFedList(baseParam);
                msg = "买家饲养指标标准调研";
                bean.setByResearchStdFeds(fedStdBeans);
                if(null != fedStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_FOUR:
                List<ByResearchStdGnq> gnqStdBeans = new ArrayList<ByResearchStdGnq>();
                gnqStdBeans = this.productBuyerStdLogic.findBuyerGnqList(baseParam);
                msg = "买家通用质量指标标准调研";
                bean.setByResearchStdGnqs(gnqStdBeans);
                if(null != gnqStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_FIVE:
                List<ByResearchStdTsp> tspStdBeans = new ArrayList<ByResearchStdTsp>();
                tspStdBeans = this.productBuyerStdLogic.findBuyerTspList(baseParam);
                msg = "买家存储运输指标标准调研";
                bean.setByResearchStdTsps(tspStdBeans);
                if(null != tspStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_SIX:
                List<ByResearchStdSft> sftStdBeans = new ArrayList<ByResearchStdSft>();
                sftStdBeans = this.productBuyerStdLogic.findBuyerSftList(baseParam);
                msg = "买家产品等级&卫生质量标准调研";
                bean.setByResearchStdSfts(sftStdBeans);
                if(null != sftStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_SEVEN:
                List<ByResearchStdTnc> tncStdBeans = new ArrayList<ByResearchStdTnc>();
                tncStdBeans = this.productBuyerStdLogic.findBuyerTncList(baseParam);
                msg = "买家产品标准质量标准调研";
                bean.setByResearchStdTncs(tncStdBeans);
                if(null != tncStdBeans)
                    isExist = true;
                break;
            case NumberConst.IntDef.INT_EIGHT:
                baseParam.setFilter("standardId",rsParam.getStandardId());
                baseParam.setFilter("normsCode",rsParam.getNormsCode());
                List<ByResearchStdNor> norStdBeans = new ArrayList<ByResearchStdNor>();
                norStdBeans = this.productBuyerStdLogic.findBuyerNorList(baseParam);
                msg = "买家产品包装标准调研";
                bean.setByResearchStdNors(norStdBeans);
                if(null != norStdBeans)
                    isExist = true;
                break;
        }
        if (isExist) {
            logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage(msg + "接口成功！");
            rs.setResult(bean);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage(msg + "接口,数据错误请检查！");
        return rs;
    }



    /**
     * 根据条件插入技术标准信息
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "产品加工质量", notes = "批量保存产品加工质量标准表")
    @RequestMapping(value = "/product/saveTncStdDiscussProvider",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> saveTncStdDiscussProvider(@RequestBody RsRequest<PDInfoParam> param) {
        logger.info("根据条件插入技术标准信息");
        RsResponse<Integer> rs = new RsResponse<Integer>();
        /**Add: 横展开添加共通设置 2016/09/27  BY  任强  Start */
        String loginId="";
        if(param != null){
            loginId = param.getLoginId();
        }
        PDInfoParam rsParam = param.getParam();
        rsParam.setCrtId(loginId);
        rsParam.setUpdId(loginId);
        rsParam.setActId(loginId);
        /**Add: 横展开添加共通设置 2016/09/27   BY  任强  End */
        int result = providerPackageLogic.saveTncStdDiscussProvider(rsParam);
        if (result > NumberConst.IntDef.INT_ZERO) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("根据条件插入技术标准信息接口成功！" + "影响条数" + result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("根据条件插入技术标准信息,数据错误！");
        return rs;
    }
}


