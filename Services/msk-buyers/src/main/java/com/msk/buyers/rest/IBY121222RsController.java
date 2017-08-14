package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.IBY121222RsParam;
import com.msk.buyers.logic.IBY121222Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 买家产品第三方品牌调研用IBY121222RsController
 *
 * @author zhou_ling
 */
@RestController
public class IBY121222RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121222RsController.class);

    @Autowired
    private IBY121222Logic iby121222Logic;

    /**
     * 买家产品第三方品牌调研查询接口
     *
     * @param param param
     * @return 结果
     * @author zhou_ling
     */
    @RequestMapping(value = "/by/researchBrand/findList",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IBY121222RsParam>> findBuyerBrand(@RequestBody RsRequest<IBY121222RsParam> param) {
        logger.debug("调用买家产品第三方品牌调研查询接口");

        String classesCode = param.getParam().getClassesCode();
        String machiningCode = param.getParam().getMachiningCode();
        String breedCode = param.getParam().getBreedCode();
        String saleName =  param.getParam().getSaleName();
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("buyerId", param.getParam().getBuyerId());
        baseParam.getFilterMap().put("classesCode", classesCode);
        baseParam.getFilterMap().put("machiningCode", machiningCode);
        baseParam.getFilterMap().put("breedCode", breedCode);
        baseParam.getFilterMap().put("saleName", saleName);
        List<IBY121222RsParam> byResearchBrand = iby121222Logic.findBuyerBrandList(baseParam);

        RsResponse<List<IBY121222RsParam>> rs = new RsResponse<>();
        rs.setResult(byResearchBrand);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询成功！");
        return rs;
    }

    /**
     * 买家产品品类和需求调研明细表更新接口
     *
     * @param param param
     * @return 结果
     * @author zhou_ling
     */
    @RequestMapping(value = "/by/researchBrand/update",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<String> researchBrandUpdate(@RequestBody RsRequest<IBY121222RsParam> param){
        logger.debug("调用买家产品第三方品牌调研更新接口");

        RsResponse<String> rs = new RsResponse<>();
        if (param.getParam()!=null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);

            int resultCount = iby121222Logic.updateResearchBrand(param.getParam());
            if (resultCount == NumberConst.IntDef.INT_ZERO) {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("更新失败,未找到可以被更新的数据!");
            } else {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("买家产品第三方品牌调研更新成功!");
            }
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("参数为空，更新失败!");
        }
        return rs;
    }

    /**
     * 买家产品品类和需求调研明细表删除接口
     *
     * @param param param
     * @return 结果
     * @author zhou_ling
     */
    @RequestMapping(value = "/by/researchBrand/delete",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IBY121222RsParam>> researchBrandDelete(@RequestBody RsRequest<IBY121222RsParam> param){
        logger.debug("调用买家产品第三方品牌调研更新接口");
        RsResponse<List<IBY121222RsParam>> rs = new RsResponse<>();
        if (param.getParam()!=null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);

        int resultCount = iby121222Logic.deleteResearchBrand(param.getParam());
        if(resultCount == NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("删除失败,未找到可以被删除的数据!");
        }else {
            String classesCode = param.getParam().getClassesCode();
            String machiningCode = param.getParam().getMachiningCode();
            String breedCode = param.getParam().getBreedCode();
            String saleName =  param.getParam().getSaleName();
            BaseParam baseParam = new BaseParam();
            baseParam.getFilterMap().put("buyerId", param.getParam().getBuyerId());
            baseParam.getFilterMap().put("classesCode", classesCode);
            baseParam.getFilterMap().put("machiningCode", machiningCode);
            baseParam.getFilterMap().put("breedCode", breedCode);
            baseParam.getFilterMap().put("saleName", saleName);
            List<IBY121222RsParam> byResearchBrand = iby121222Logic.findBuyerBrandList(baseParam);
            rs.setResult(byResearchBrand);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家产品第三方品牌调研删除成功!");
        }
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("参数为空，买家产品第三方品牌调研删除失敗!");
        }
        return rs;
    }

}
