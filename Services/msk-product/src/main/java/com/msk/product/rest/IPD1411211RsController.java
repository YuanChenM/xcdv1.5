package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.IPD141146RsParam;
import com.msk.product.logic.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 产品等级&卫生质量标准（八张档案卡）
 * @author yang_chunyan
 *
 */
@RestController
@Api(description = "产品等级&卫生质量标准(八张档案卡)RestController", tags = {"IPD1411211RsController"})
public class IPD1411211RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD1411211RsController.class);

    @Autowired
    private IPD141121Logic ipd141121Logic;
    @Autowired
    private IPD141122Logic ipd141122Logic;
    @Autowired
    private IPD141123Logic ipd141123Logic;
    @Autowired
    private IPD141124Logic ipd141124Logic;
    @Autowired
    private IPD141125Logic ipd141125Logic;
    @Autowired
    private IPD141126Logic ipd141126Logic;
    @Autowired
    private IPD141127Logic ipd141127Logic;
    @Autowired
    private IPD141107Logic ipd141107Logic;

    /**
     * 产品等级&卫生质量标准（八张档案卡）
     *
     * @return RsResponse 结果
     * @author yang_chunyan
     */
    @ApiOperation(value = "产品等级&卫生质量", notes = "产品等级&卫生质量标准(八张档案卡)")
    @RequestMapping(value = "/pd/pd_std",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD1411211Validator")
    public RsResponse<List<BaseEntity>> findListMct(@RequestBody RsRequest<IPD141146RsParam> param) {
        RsResponse<List<BaseEntity>> rs = new RsResponse<List<BaseEntity>>();
        IPD141146RsParam rsParam = param.getParam();
        Integer stdType = rsParam.getType();
        List<BaseEntity> result = new ArrayList<>();
        String msg = "";
        switch (stdType){
            case 1:result = this.ipd141121Logic.selectSourceMct(param.getParam());msg="产品技术标准";break;
            case 2:result = this.ipd141122Logic.selectSourceMat(param);msg="原料种源信息";break;
            case 3:result = this.ipd141123Logic.selectSourceOrg(param);msg="原种种源档案卡";break;
            case 4:result = this.ipd141124Logic.selectSourceFed(param);msg="饲养指标档案卡";break;
            case 5:result = this.ipd141125Logic.selectSourceGnq(param);msg="通用质量指标档案卡";break;
            case 6:result = this.ipd141126Logic.selectSourceTsp(param);msg="存储运输指标档案卡";break;
            case 7:result = this.ipd141127Logic.selectSourceSft(param.getParam());msg="产品等级&卫生质量标准";break;
            case 8:result = this.ipd141107Logic.selectSourceTnc(param.getParam());msg="产品标准质量档案卡";break;
        }
        rs.setResult(result);
        if (result != null) {
            logger.info(msg + "接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage(msg + "接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage(msg + "接口,数据错误！");
        return rs;

    }
}
