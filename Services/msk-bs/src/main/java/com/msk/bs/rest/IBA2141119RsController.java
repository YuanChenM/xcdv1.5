package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.IBA2141119Param;
import com.msk.bs.bean.IBA2141119Result;
import com.msk.bs.logic.IBA2141119RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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

/**根据houseCode查询该管家虚拟地址及所属的买手的code
 * Created by zhu_kai1 on 2016/7/18.
 */
@RestController
@Api(description = "根据houseCode查询该管家所属的买手RestController", tags = {"IBA2141119RsController"})
public class IBA2141119RsController extends BaseRsController{

    private static Logger logger = LoggerFactory.getLogger(IBA2141119RsController.class);

    @Autowired
    private IBA2141119RsLogic iba2141119RsLogic;

    /**
     * 根据houseCode查询该管家虚拟地址及所属的买手的code
     * @param request
     * @return
     */
    @ApiOperation(value = "查询所属买手", notes = "根据houseCode查询该管家所属的买手接口")
    @RequestMapping(value = "/bs/searchSlCode", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBA2141119Result> deleteHouseInfo(@RequestBody RsRequest<IBA2141119Param> request){
        logger.info("开始查询管家虚拟地址及所属的买手的code");
        RsResponse<IBA2141119Result> response = new  RsResponse<IBA2141119Result>();
        IBA2141119Param param = request.getParam();
        IBA2141119Result result = iba2141119RsLogic.selectHouseAccount(param);
        if(null != result){
            response.setMessage("处理成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setResult(result);
        }else {
            response.setMessage("处理失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("查询管家虚拟地址及所属的买手的code接口结束");
        return response;
    }
}
