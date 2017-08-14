package com.msk.seller.rest;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.*;
import com.msk.seller.logic.ISLEnterpriseRsLogic;
import org.apache.commons.collections.CollectionUtils;
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
 * Created by zhang_chi on 2016/7/5.
 */
@RestController
public class ISLEnterpriseRsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISLEnterpriseRsController.class);

    @Autowired
    private ISLEnterpriseRsLogic islEnterpriseRsLogic;

    /**
     * 查询企业名称和企业ID
     *
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/slEnterprise/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLEnterpriseRsResult> searchSlEnterprise(@RequestBody RsRequest<ISLSellerRsParam> param) {
        RsResponse<ISLEnterpriseRsResult> result = new RsResponse<ISLEnterpriseRsResult>();
        ISLSellerRsParam islSellerRsParam = param.getParam();
        List<ISLEnterpriseRsResult> slEnterpriseList = islEnterpriseRsLogic.getSlEnterprise(islSellerRsParam);
        if (!CollectionUtils.isEmpty(slEnterpriseList)) {
            ISLEnterpriseRsResult islEnterpriseRsResult = new ISLEnterpriseRsResult();
            islEnterpriseRsResult.setSlEnterpriseList(slEnterpriseList);
            result.setResult(islEnterpriseRsResult);
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("查询企业名称和企业ID成功");
        } else {
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("查询企业名称和企业ID失败");
        }
        return result;
    }

    /**
     * 查询生产商列表或代理卖家OEM卖家列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/slEnterpriseBySlCode/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLEnterpriseRsPageResult> getSlEnterpriseBySlCode(@RequestBody RsRequest<ISLEnterpriseRsParam> param) {
        RsResponse<ISLEnterpriseRsPageResult> result = new RsResponse<ISLEnterpriseRsPageResult>();
        ISLEnterpriseRsParam islEnterpriseRsParam = param.getParam();
        ISLEnterpriseRsPageResult islEnterpriseRsPageResult = new ISLEnterpriseRsPageResult();

        if (null == param) {
            return result;
        }
        String slCode = StringUtil.toSafeString(islEnterpriseRsParam.getSlCode());
        if (StringUtil.isNullOrEmpty(slCode)) {
            throw new BusinessException("卖家编码不可以为空");
        }

        ISLEnterpriseRsParam oemAgentParam = islEnterpriseRsParam.getOemAgentParam();
        ISLEnterpriseRsPage oemAgentResult = new ISLEnterpriseRsPage();
        if (null != oemAgentParam) {
            // Oem  Agent 类型去查询
            oemAgentParam.setSlCode(slCode);
            oemAgentResult = islEnterpriseRsLogic.getOemAgentData(oemAgentParam);
            islEnterpriseRsPageResult.setOemAgentResult(oemAgentResult);
        }

        ISLEnterpriseRsParam selfParam = islEnterpriseRsParam.getSelfParam();
        ISLEnterpriseRsPage selfResult = new ISLEnterpriseRsPage();
        if (null != selfParam) {
            // Self 类型去查询
            selfParam.setSlCode(slCode);
            selfResult = islEnterpriseRsLogic.getSelfData(selfParam);
            islEnterpriseRsPageResult.setSelfResult(selfResult);
        }

        if (null == selfParam && null == oemAgentParam) {
            return result;
        }

        result.setStatus(SystemConst.RsStatus.SUCCESS);
        if (NumberConst.IntDef.INT_ZERO != oemAgentResult.getTotalCount() || NumberConst.IntDef.INT_ZERO != selfResult.getTotalCount()) {
            result.setResult(islEnterpriseRsPageResult);
            result.setMessage("查询成功");
        } else {
            result.setResult(new ISLEnterpriseRsPageResult());
            result.setMessage("查询不存在");
        }
        return result;
    }
}
