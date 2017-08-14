package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.hoperun.core.consts.NumberConst;
import com.msk.core.entity.SlEnterprise;
import com.msk.core.entity.SlEpAgentAuth;
import com.msk.core.entity.SlEpOemAuth;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231134RsParam;
import com.msk.seller.bean.ISL231134RsResult;
import com.msk.seller.bean.ISL231180SLEpAuth;
import com.msk.seller.logic.ISL231134RsLogic;
import com.msk.seller.logic.ISL231180RsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/3/9.
 */
@RestController
public class ISL231134RsController extends BaseRsController {
    /**注入logic*/
    @Autowired
    private ISL231134RsLogic isl231134RsLogic;

    /**
     * 新增企业生产商信息
     * @param param 传入参数
     * @return 返回参数
     */
    @RequestMapping(value = "/sl/slInfo/slProducer/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231134Validator")
    public RsResponse<ISL231134RsResult> addSlEpInfo(@RequestBody RsRequest<ISL231134RsParam> param) {
        ISL231134RsParam isl231134RsParam = param.getParam();
        /**根据slCode查询用户传入的参数中卖家账号和生产商企业id是否存在，不存在 则报错*/
        if(null!=isl231134RsParam){
            /**查询卖家账号*/
            String slCode = isl231134RsParam.getSlCode();
            SlSeller slSeller=this.isl231134RsLogic.findIsl231180SLEpAuthIfExist(slCode);
            /**如果查询结果为空，录入的卖家编码不存在，报错*/
            if(null==slSeller){
                throw new BusinessException("对不起，输入了无效的卖家编码");
            }
            /**根据epID查询生产商企业ID是否存在*/
            Long epId = isl231134RsParam.getProducerEpId();
            SlEnterprise slEnterprise = this.isl231134RsLogic.findSlEnterpriseIfExist(epId);
            if(null==slEnterprise){
                throw new BusinessException("您录入的生产商企业ID不存在");
            }
            /**如果1：卖家代理及分销授权:2：卖家OEM委托授权标志*/
            if("1".equals(isl231134RsParam.getFlag())){
                /**根据slCode 和epId查询代理中是否已经存在,存在 就通知用户，不存在正常存储*/
                this.isl231134RsLogic.findIfExist(slCode,epId);
                SlEpAgentAuth slEpAgentAuth = new SlEpAgentAuth();
                slEpAgentAuth.setSlCode(slCode);
                slEpAgentAuth.setProducerEpId(epId);
                slEpAgentAuth.setContractNo(isl231134RsParam.getContractNo());
                slEpAgentAuth.setAuthEpName(isl231134RsParam.getAuthEpName());
                slEpAgentAuth.setAuthTermBegin(isl231134RsParam.getAuthTermBegin());
                slEpAgentAuth.setAuthTermEnd(isl231134RsParam.getAuthTermEnd());
                slEpAgentAuth.setAuthTermUnliimited(isl231134RsParam.getAuthTermUnliimited());
                slEpAgentAuth.setCrtId(isl231134RsParam.getCrtId());
                this.isl231134RsLogic.saveSlEpAgentAuth(slEpAgentAuth);
            }
            if("2".equals(isl231134RsParam.getFlag())){
                this.isl231134RsLogic.findOemIfExist(slCode, epId);
                SlEpOemAuth slEpOemAuth =new SlEpOemAuth();
                slEpOemAuth.setSlCode(slCode);
                slEpOemAuth.setProducerEpId(epId);
                slEpOemAuth.setContractNo(isl231134RsParam.getContractNo());
                slEpOemAuth.setAuthEpName(isl231134RsParam.getAuthEpName());
                slEpOemAuth.setAuthTermBegin(isl231134RsParam.getAuthTermBegin());
                slEpOemAuth.setAuthTermEnd(isl231134RsParam.getAuthTermEnd());
                slEpOemAuth.setAuthTermUnliimited(isl231134RsParam.getAuthTermUnliimited());
                slEpOemAuth.setCrtId(isl231134RsParam.getCrtId());
                this.isl231134RsLogic.saveSlEpOemAuth(slEpOemAuth);
            }
        }
        RsResponse<ISL231134RsResult> rs = new RsResponse<ISL231134RsResult>();
        rs.setMessage("保存成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }
}
