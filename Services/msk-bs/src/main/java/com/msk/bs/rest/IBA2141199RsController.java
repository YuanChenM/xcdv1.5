package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.logic.IBA2141199RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/10/14.
 */
@RestController
public class IBA2141199RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBA2141199RsController.class);

    @Autowired
    private IBA2141199RsLogic iba2141199RsLogic;

    /**
     * 查询所有城市信息
     * @param request
     * @return
     */
    @ApiOperation(value = "查询所有的城市信息", notes = "查询所有的城市信息接口")
    @RequestMapping(value = "/ba/allCity/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<CityBean>> searchAllCity(@RequestBody RsRequest<DistrictParam> request){
        logger.info("查询所有的城市信息开始");
        RsResponse<List<CityBean>> response = new RsResponse<List<CityBean>>();
        DistrictParam param = request.getParam();
        List<CityBean> rsResult = iba2141199RsLogic.getAllCityInfo(param);
        if(!CollectionUtils.isEmpty(rsResult)){
            response.setResult(rsResult);
            response.setMessage("处理成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        }else {
            response.setMessage("处理失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("查询所有的城市信息结束");
        return response;
    }

}
