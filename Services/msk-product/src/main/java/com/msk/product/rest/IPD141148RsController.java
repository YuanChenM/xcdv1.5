package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141146RsParam;
import com.msk.product.bean.IPD141146RsResult;
import com.msk.product.logic.IPD141148Logic;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量根据Code获取Name
 * Created by yang_chunyan on 2016/4/29.
 */
@RestController
@Api(description = "批量获取产品类型名RestController", tags = {"IPD141148RsController"})
public class IPD141148RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IPD141146RsController.class);

    @Autowired
    private IPD141148Logic ipd141148Logic;
    /**
     * 批量获取产品类型名
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品类型名", notes = "批量获取产品类型名接口")
    @RequestMapping(value = "/pd/pdBatchName",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Map<String,List<IPD141146RsResult>>> findPdTypeName(@RequestBody RsRequest<IPD141146RsParam> param) {
        logger.info("查询产品信息");
        RsResponse<Map<String,List<IPD141146RsResult>>> rs = new RsResponse<Map<String,List<IPD141146RsResult>>>();
        List<IPD141146RsResult> classesResults = new ArrayList<>();
        List<IPD141146RsResult> machiningResults = new ArrayList<>();
        List<IPD141146RsResult> breedResults = new ArrayList<>();
        List<IPD141146RsResult> featureResults = new ArrayList<>();
        List<IPD141146RsResult> weightResults = new ArrayList<>();
        List<IPD141146RsResult> gradeResults = new ArrayList<>();

        Map<String,List<IPD141146RsResult>> map = new HashMap<>();

        List<IPD141146RsParam> classCodes = param.getParam().getClassesCodeList();
        List<IPD141146RsParam> machiningCodes = param.getParam().getMachiningCodeList();
        List<IPD141146RsParam> breedCodes = param.getParam().getBreedCodeList();
        List<IPD141146RsParam> featureCodes = param.getParam().getFeatureCodeList();
        List<IPD141146RsParam> weightCodes = param.getParam().getWeightCodeList();
        List<IPD141146RsParam> gradeCodes = param.getParam().getGradeCodeList();
        String msg = "";
        if(CollectionUtils.isNotEmpty(classCodes) && classCodes.size() > 0)
        {
            classesResults = ipd141148Logic.findPdClassesByCodes(classCodes);
        }
        if(CollectionUtils.isNotEmpty(machiningCodes) && machiningCodes.size() > 0)
        {
            machiningResults = ipd141148Logic.findPdMachiningByCodes(machiningCodes);
        }
        if(CollectionUtils.isNotEmpty(breedCodes) && breedCodes.size() > 0)
        {
            breedResults = ipd141148Logic.findPdBreedByCodes(breedCodes);
        }
        if(CollectionUtils.isNotEmpty(featureCodes) && featureCodes.size() > 0)
        {
            featureResults = ipd141148Logic.findPdFeatureByCodes(featureCodes);
        }
        if(CollectionUtils.isNotEmpty(weightCodes) && weightCodes.size() > 0)
        {
            weightResults = ipd141148Logic.findPdWeightByCodes(weightCodes);
        }
        if(CollectionUtils.isNotEmpty(gradeCodes) && gradeCodes.size() > 0)
        {
            gradeResults = ipd141148Logic.findPdGradeByCodes(gradeCodes);
        }

        map.put("classes",classesResults);
        map.put("machining",machiningResults);
        map.put("breed",breedResults);
        map.put("feature",featureResults);
        map.put("weight",weightResults);
        map.put("grade",gradeResults);

        if (map != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询数据正常！");
            rs.setResult(map);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询异常!");
        return rs;
    }

}
