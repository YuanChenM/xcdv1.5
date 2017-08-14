package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.logic.IBY121217Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByResearchStdSft;
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
 * 买家产品品种安全标准调研用Controller.
 *
 * @author zhou_ling
 */
@RestController
public class IBY121217RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121217RsController.class);

    @Autowired
    private IBY121217Logic iby121217Logic;

    /**
     * 买家产品品种安全标准调研接口
     *
     * @param param param
     * @return 结果
     * @author zhou_ling
     */
    @RequestMapping(value = "/by/researchStdSft/findList",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<ByResearchStdSft>> findBuyerStdSft(@RequestBody RsRequest<ByResearchStdSft> param) {
        logger.debug("调用买家产品品种安全标准调研查询接口");

        String classesCode = param.getParam().getClassesCode();
        String machiningCode = param.getParam().getMachiningCode();
        String breedCode = param.getParam().getBreedCode();
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("buyerId", param.getParam().getBuyerId());
        baseParam.getFilterMap().put("classesCode", classesCode);
        baseParam.getFilterMap().put("machiningCode", machiningCode);
        baseParam.getFilterMap().put("breedCode", breedCode);
        baseParam.getFilterMap().put("treeCode", classesCode + machiningCode + breedCode);

        List<ByResearchStdSft> byResearchStdSft = iby121217Logic.findBuyerSftList(baseParam);
        RsResponse<List<ByResearchStdSft>> rs = new RsResponse<>();
        rs.setResult(byResearchStdSft);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询成功！");
        return rs;
    }

    /**
     * 买家产品品种安全标准更新接口
     *
     * @param param param
     * @return 结果
     * @author zhou_ling
     */
    @RequestMapping(value = "/by/researchStdSft/update",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<String> updateBuyerStdSft(@RequestBody RsRequest<ByResearchStdSft> param) {
        logger.debug("调用买家产品品种安全标准更新接口");
        RsResponse<String> rs = new RsResponse<>();
        if (param.getParam()!=null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);

            int resultCount = iby121217Logic.updateBuyerSft(param.getParam());
            if (resultCount == NumberConst.IntDef.INT_ZERO) {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("更新失败,未找到可以被更新的数据!");
            } else {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("买家产品品种安全标准调研更新成功!");
            }
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("参数为空，数据更新失败!");
        }
        return rs;
    }

}
