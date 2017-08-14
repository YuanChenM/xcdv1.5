package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.IBY121220Bean;
import com.msk.buyers.bean.IBY121221Bean;
import com.msk.buyers.logic.IBY121220Logic;
import com.msk.buyers.logic.IBY121221Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.consts.CodeMasterConst;
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
 * 买家产品品种调研用Controller.
 *
 * @author yuan_chen
 */
@RestController
public class IBY121221RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121221RsController.class);

    @Autowired
    private IBY121220Logic iby121220Logic;

    @Autowired
    private IBY121221Logic iby121221Logic;

    /**
     * 买家产品品种调研状态查询接口
     *
     * @param param param
     * @return 结果
     * @author yuan_chen
     */
    @RequestMapping(value = "/by/research/findResearchTypeList",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IBY121221Bean>> findBuyerResearch(@RequestBody RsRequest<IBY121220Bean> param) {
        logger.debug("调用买家产品品种调研状态查询接口");

        BaseParam baseParam = iby121220Logic.setParam(param.getParam());
        String breedCode = param.getParam().getBreedCode();
        String isStandard = param.getParam().getIsStandard();

        if(StringUtil.isNullOrEmpty((isStandard))){
            if(StringUtil.isNullOrEmpty((breedCode))){
                isStandard = CodeMasterConst.IsStandard.NO;
            }else {
                isStandard = CodeMasterConst.IsStandard.YES;
            }
        }

        baseParam.getFilterMap().put("breedCode", breedCode);
        baseParam.getFilterMap().put("isStandard", isStandard);
         List<IBY121221Bean> iby121221Beans = iby121221Logic.findBuyerResearch(baseParam);
        RsResponse<List<IBY121221Bean>> rs = new RsResponse<>();
        rs.setResult(iby121221Beans);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("买家产品品种调研状态查询成功！");
        return rs;
    }


}
