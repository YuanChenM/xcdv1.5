package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBA2141106RsResult;
import com.msk.bs.bean.IBA2141107RsBean;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdMachining;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取产品一级分类，二级分类，品种
 * Created by ni_shaotang on 2016/7/13.
 */
@RestController
@Api(description = "查询产品二级级分类列表和品种列表RestController", tags = {"IBA2141106RsController"})
public class IBA2141106RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBA2141106RsController.class);

    /**
     * 获取产品一级列表
     *
     * @return
     */
    @ApiOperation(value = "产品一级", notes = "查询产品一级接口")
    @RequestMapping(value = "/ba/productClass", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<PdClasses>> productClass(@RequestBody RsRequest<PDInfoParam> param) {
        RsResponse<List<PdClasses>> response = new RsResponse<List<PdClasses>>();
        logger.info("查询产品一级接口");
        //Modif for Bug#2379 at 2016/09/06 by ni_shaotang Start
        //根据条件获取品种信息
        List<PDInfoResult> pdInfoList = CommRestUtil.getPDSup(param.getParam());
        List<PdClasses> classList = new ArrayList<>();
        List<String> classCodeList = new ArrayList<>();
        PdClasses classes = null;
        for (PDInfoResult pdInfoResult : pdInfoList) {
            classes = new PdClasses();
            String classCode = pdInfoResult.getClassesCode();
            classes.setClassesCode(classCode);
            classes.setClassesName(pdInfoResult.getClassesName());
            if (!classCodeList.contains(classCode)) {
                classList.add(classes);
                classCodeList.add(classCode);
            }
        }
        response.setResult(classList);
        response.setMessage("查询成功");
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        return response;
    }

    /**
     * @return
     */
    @ApiOperation(value = "产品二级", notes = "查询产品二级分类，品种接口")
    @RequestMapping(value = "/ba/productInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141106RsResult>> productInfo(@RequestBody RsRequest<PDInfoParam> param) {
        RsResponse<List<IBA2141106RsResult>> response = new RsResponse<List<IBA2141106RsResult>>();
        List<IBA2141106RsResult> pdMachiningList = new ArrayList<IBA2141106RsResult>();
        PDInfoParam pdInfoParam = param.getParam();
        logger.info("查询产品二级分类，品种接口");
        if (!StringUtil.isNullOrEmpty(pdInfoParam.getPdCode())) {
            //根据一级分类编号获取二级分类列表
            param.getParam().setPdCode(param.getParam().getPdCode().substring(0, 2));
            List<PDInfoResult> pdInfoList = CommRestUtil.getPDSup(param.getParam());

            //组织二级分类数据
            List<PdMachining> machiningList = new ArrayList<>();
            List<String> machiningCodeList = new ArrayList<>();
            PdMachining pdMachining = null;
            for (PDInfoResult pdInfoResult : pdInfoList) {
                pdMachining = new PdMachining();
                String machiningCode = pdInfoResult.getMachiningCode();
                pdMachining.setMachiningCode(machiningCode);
                pdMachining.setMachiningName(pdInfoResult.getMachiningName());
                if (!machiningCodeList.contains(machiningCode)) {
                    machiningList.add(pdMachining);
                    machiningCodeList.add(machiningCode);
                }
            }

            //组织品种分类数据
            List<PdBreed> breedList = new ArrayList<PdBreed>();
            List<String> breedCodeList = new ArrayList<>();
            PdBreed breed = null;
            for (PDInfoResult pdInfoResult : pdInfoList) {
                breed = new PdBreed();
                String breedCode = pdInfoResult.getBreedCode();
                breed.setBreedCode(breedCode);
                breed.setBreedName(pdInfoResult.getBreedName());
                breed.setMachiningCode(pdInfoResult.getMachiningCode());
                if (!breedCodeList.contains(breedCode)) {
                    breedList.add(breed);
                    breedCodeList.add(breedCode);
                }
            }

            IBA2141106RsResult machiningBean = null;
            List<PdBreed> pdBreedList = null;
            for (PdMachining machining : machiningList) {
                //重新组装二级分类
                machiningBean = new IBA2141106RsResult();
                machiningBean.setMachiningCode(machining.getMachiningCode());
                machiningBean.setMachiningName(machining.getMachiningName());
                pdBreedList = new ArrayList<PdBreed>();
                //获取二级分类下品种
                for (PdBreed pdBreed : breedList) {
                    //重新组装品种
                    if (pdBreed.getMachiningCode().equals(machining.getMachiningCode())) {
                        pdBreedList.add(pdBreed);
                    }
                }
                machiningBean.setBreedList(pdBreedList);
                pdMachiningList.add(machiningBean);
            }
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("查询失败,产品一级分类编号不能为空");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        response.setResult(pdMachiningList);
        return response;
    }

    /**
     * @return
     */
    @ApiOperation(value = "产品信息", notes = "查询产品信息接口")
    @RequestMapping(value = "/ba/productInfos", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<PDInfoResult>> productInfos(@RequestBody RsRequest<String> param) {
        RsResponse<List<PDInfoResult>> response = new RsResponse<List<PDInfoResult>>();
        logger.info("查询产品信息接口");
        List<PDInfoResult> productList = CommRestUtil.searchPdInfoList(new PDInfoParam());
        response.setMessage("查询成功");
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setResult(productList);
        return response;
    }

}
