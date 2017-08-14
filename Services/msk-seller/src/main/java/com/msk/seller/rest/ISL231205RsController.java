package com.msk.seller.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.ISL231205Result;
import com.msk.seller.bean.ISL231205RsParam;
import com.msk.seller.bean.SlProductBean;
import com.msk.seller.logic.ISL231205RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu_yan2 on 2016/5/27.
 */
@RestController
public class ISL231205RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231205RsController.class);

    @Autowired
    private ISL231205RsLogic isl231205RsLogic;

    /**产品类别*/
    private final int PD_CLASSES_CODE_AS_LEVEL_CODE = 1;
    /**产品二级分类编码*/
    private final int MACHINING_CODE_AS_LEVEL_CODE = 2;
    /**产品品种*/
    private final int PD_BREED_CODE_AS_LEVEL_CODE = 3;
    /**产品特征*/
    private final int PD_FEATURE_CODE_AS_LEVEL_CODE = 4;
    /**净重编码*/
    private final int WEIGHT_CODE_AS_LEVEL_CODE = 5;

    /**
     * 根据卖家ID查询卖家一级分类
     *
     * @return rs  根据卖家ID查询卖家一级分类
     */
    @RequestMapping(value = "/sl/slInfo/SlOneClass/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ISL231205Result>> SlOneClassSearch(@RequestBody RsRequest<ISL231205RsParam> param) {
        logger.debug("根据卖家ID查询卖家一级分类 ");

        ISL231205RsParam isl231205RsParam = param.getParam();
        RsResponse<List<ISL231205Result>> rs = new RsResponse<List<ISL231205Result>>();

        List<SlProductBean> slProductBeanList = isl231205RsLogic.querySlOneClass(isl231205RsParam);

        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setParentCode(isl231205RsParam.getParentCode());
        pdInfoParam.setTreeLevel(StringUtil.toSafeString(isl231205RsParam.getTreeLevel()));
        switch (isl231205RsParam.getTreeLevel()) {
            case PD_CLASSES_CODE_AS_LEVEL_CODE:
                pdInfoParam.setLevelCode(isl231205RsParam.getPdClassesCode());
                break;
            case MACHINING_CODE_AS_LEVEL_CODE:
                pdInfoParam.setLevelCode(isl231205RsParam.getMachiningCode());
                break;
            case PD_BREED_CODE_AS_LEVEL_CODE:
                pdInfoParam.setLevelCode(isl231205RsParam.getPdBreedCode());
                break;
            case PD_FEATURE_CODE_AS_LEVEL_CODE:
                pdInfoParam.setLevelCode(isl231205RsParam.getPdFeatureCode());
                break;
            case WEIGHT_CODE_AS_LEVEL_CODE:
                break;
            default:
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("treeLevel缺少或值不正确");
                return rs;
        }
        RsResponse<ProductBeanResult> pdClassesTreeResponse = this.getPdClassesTreeInfo(pdInfoParam);
        if (pdClassesTreeResponse.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            List<ISL231205Result> results = new ArrayList<ISL231205Result>();
            List<PDInfoResult> pdInfoResultList = pdClassesTreeResponse.getResult().getResult();
            filterResults(slProductBeanList,pdInfoResultList, isl231205RsParam.getTreeLevel(), results);
            rs.setResult(results);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("根据卖家ID查询卖家一级分类成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("产品接口调用失败" + pdClassesTreeResponse.getMessage());
        }

        return rs;
    }

    private void filterResults(List<SlProductBean> slProductBeanList, List<PDInfoResult> pdInfoResultList, int treeLevel, List<ISL231205Result> results) {
        for(PDInfoResult pdInfo: pdInfoResultList) {
            ISL231205Result isl231205Result = new ISL231205Result();
            for(SlProductBean slProductBean: slProductBeanList) {
                if(treeLevel == PD_CLASSES_CODE_AS_LEVEL_CODE && pdInfo.getLevelCode().equals(slProductBean.getPdClassesCode())) {
                    addISL231202Result(results, pdInfo);
                    break;
                }
                if(treeLevel == MACHINING_CODE_AS_LEVEL_CODE && pdInfo.getLevelCode().equals(slProductBean.getMachiningCode())) {
                    addISL231202Result(results, pdInfo);
                    break;
                }
                if(treeLevel == PD_BREED_CODE_AS_LEVEL_CODE && pdInfo.getLevelCode().equals(slProductBean.getPdBreedCode())) {
                    addISL231202Result(results, pdInfo);
                    break;
                }
                if(treeLevel == PD_FEATURE_CODE_AS_LEVEL_CODE && pdInfo.getLevelCode().equals(slProductBean.getPdFeatureCode())) {
                    addISL231202Result(results, pdInfo);
                    break;
                }
                if(treeLevel == WEIGHT_CODE_AS_LEVEL_CODE && pdInfo.getLevelCode().equals(slProductBean.getWeightCode())) {
                    addISL231202Result(results, pdInfo);
                    break;
                }
            }
        }
    }
    private void addISL231202Result(List<ISL231205Result> results, PDInfoResult pdInfo) {
        ISL231205Result isl231205Result = new ISL231205Result();
        isl231205Result.setLevelCode(pdInfo.getLevelCode());
        isl231205Result.setClassestreeCode(pdInfo.getClassestreeCode());
        isl231205Result.setLevelName(pdInfo.getLevelName());
        results.add(isl231205Result);
    }
    /**
     * 根据levelCode、treeLevel、parentCode获取分类信息
     * @param pdInfoParam
     * @return
     */
    public RsResponse<ProductBeanResult> getPdClassesTreeInfo(PDInfoParam pdInfoParam){
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(pdInfoParam);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdClassesTreeInfoSearchServices();
        RsResponse<ProductBeanResult> pdClassesTreeResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        return  pdClassesTreeResponse;
    }
}
