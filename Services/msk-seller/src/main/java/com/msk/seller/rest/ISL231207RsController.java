package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231207PageResult;
import com.msk.seller.bean.ISL231207Param;
import com.msk.seller.bean.ISL231207Result;
import com.msk.seller.logic.ISL231207RsLogic;
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
 * Created by zhang_jiaxing on 2016/9/7.
 */
@RestController
public class ISL231207RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(ISL231207RsController.class);

    @Autowired
    private ISL231207RsLogic isl231207RsLogic;

    /**
     * 分页获取所有企业信息列表
     * @param param param
     * @return 企业信息
     */
    @RequestMapping(value = "/sl/enterpriseInfo/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231207PageResult> queryEnterpriseInfo(@RequestBody RsRequest<ISL231207Param> param){
        RsResponse<ISL231207PageResult> rs=new RsResponse<ISL231207PageResult>();
        //校验参数的准确性
        if(null == param || null == param.getParam() ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("输入参数错误");
            return rs;
        }

        ISL231207Param isl231207Param = param.getParam();
        //组装查询条件
        String slMainClass= StringUtil.toSafeString(isl231207Param.getFilterMap().get("slMainClass"));
        if(!StringUtil.isNullOrEmpty(slMainClass)){
            String[] slMainClasss=slMainClass.split(",");
            isl231207Param.getFilterMap().put("slMainClasss",slMainClasss);
        }

        ISL231207PageResult isc182207RsResult =new ISL231207PageResult();
        List<ISL231207Result> slInfoList = isl231207RsLogic.queryEnterpriseInfo(isl231207Param,isc182207RsResult);
        if (CollectionUtils.isNotEmpty(slInfoList)) {
            isc182207RsResult.setEpList(slInfoList);
            rs.setResult(isc182207RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("取得企业信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("企业信息不存在");
        }
        return rs;
    }
}
