package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141109RsParam;
import com.msk.product.logic.IPD141109Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 产品标准包装档案卡添加接口
 * IPD141109RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品标准包装档案卡添加接口RestController", tags = {"IPD141109RsController"})
public class IPD141109RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141109RsController.class);

    @Autowired
    private IPD141109Logic ipd141109Logic;

    /**
     * 产品标准包装档案卡添加接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "标准包装档案卡添加", notes = "把卖家定义的包装标准添加到标准产品包装档案卡里")
    @RequestMapping(value = "/pd/pd_norms_std/new",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141109Validator")
    public RsResponse<IPD141109RsParam> findPdNormsStd(@RequestBody RsRequest<IPD141109RsParam> param) {
        RsResponse<IPD141109RsParam> rs = new RsResponse<IPD141109RsParam>();
        IPD141109RsParam result = new IPD141109RsParam();

        if (isDebug) {
            // 测试数据
            logger.info("产品标准包装档案卡添加接口,测试数据");
            result.setNormsCode("01");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("保存成功！");
            rs.setResult(result);
            return rs;
        }
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        if(param != null){
            String loginId = param.getLoginId();
            if(param.getParam() != null){
                IPD141109RsParam ipd141109RsParam = param.getParam();
                Date date =  DateTimeUtil.getCustomerDate();
                ipd141109RsParam.setActId(loginId);
                ipd141109RsParam.setUpdId(loginId);
                ipd141109RsParam.setCrtId(loginId);
                ipd141109RsParam.setCrtTime(date);
                ipd141109RsParam.setUpdTime(date);
                ipd141109RsParam.setActTime(date);
                String normsCode = this.ipd141109Logic.saveNorms(ipd141109RsParam);
                if (StringUtils.isNotBlank(normsCode)) {
                    result.setNormsCode(normsCode);
                    rs.setStatus(SystemConst.RsStatus.SUCCESS);
                    rs.setMessage("保存成功！");
                    rs.setResult(result);
                    return rs;
                }
                else{
                    rs.setStatus(SystemConst.RsStatus.FAIL);
                    rs.setMessage("保存失败！");
                    return rs;
                }
            }
        }
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("保存失败！");
        return rs;

    }
}
