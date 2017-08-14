package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231208PageResult;
import com.msk.seller.bean.ISL231208Param;
import com.msk.seller.bean.SL241116Bean;
import com.msk.seller.logic.ISL231208RsLogic;
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
 * Created by zhang_jiaxing on 2016/9/8.
 */
@RestController
public class ISL231208RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISL231207RsController.class);

    @Autowired
    private ISL231208RsLogic isl231208RsLogic;

    /**
     * 分页获取所有产品信息
     * @param param param
     * @return 产品信息
     */
    @RequestMapping(value = "/sl/productInfo/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231208PageResult> queryProductInfo(@RequestBody RsRequest<ISL231208Param> param){
        RsResponse<ISL231208PageResult> rs=new RsResponse<ISL231208PageResult>();
        //校验参数的准确性
        if(null == param || null == param.getParam() ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("输入参数错误");
            return rs;
        }

        ISL231208Param isl231208Param = param.getParam();
        if(StringUtil.isNullOrEmpty(isl231208Param.getSlCode())){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("卖家编码不可为空");
            return rs;
        }

        String slTncGradeCode = StringUtil.toSafeString(isl231208Param.getFilterMap().get("slTncGradeCode"));
        if (!StringUtil.isNullOrEmpty(slTncGradeCode)) {
            String[] slTncGradeCodes = slTncGradeCode.split(",");
            isl231208Param.getFilterMap().put("slTncGradeCodes", slTncGradeCodes);
        }
        String statusName = StringUtil.toSafeString(isl231208Param.getFilterMap().get("status"));
        if (!StringUtil.isNullOrEmpty(statusName)) {
            String[] statusNames = statusName.split(",");
            isl231208Param.getFilterMap().put("statusNames", statusNames);
        }

        ISL231208PageResult isc182208RsResult =new ISL231208PageResult();
        List<SL241116Bean> productPage = this.isl231208RsLogic.queryProductInfo(isl231208Param,isc182208RsResult);
        if (CollectionUtils.isNotEmpty(productPage)) {
            isc182208RsResult.setEpPdList(productPage);
            rs.setResult(isc182208RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("取得企业产品信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("企业产品信息不存在");
        }
        return rs;
    }
}
