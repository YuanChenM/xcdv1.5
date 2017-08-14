package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBS2101107RsResult;
import com.msk.bs.bean.IBS2101138RsResult;
import com.msk.bs.bean.publicBuyerPoolRsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhu_kai1 on 2016/11/4.
 */
@RestController
@Api(description = "查询公共买家池信息", tags = {"IBS2101138RsController"})
public class IBS2101138RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBS2101138RsController.class);

    /**
     * 为了配合伽然接口.....
     * @param request
     * @return
     */
    @ApiOperation(value = "查询公共买家池信息", notes = "查询公共买家池信息")
    @RequestMapping(value = "/bs/slInfo/searchBuyer", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101107RsResult> searchBsInfo(@RequestBody RsRequest<publicBuyerPoolRsParam> request) {
        RsResponse<IBS2101107RsResult> response = new RsResponse<IBS2101107RsResult>();
        publicBuyerPoolRsParam param = request.getParam();
        // 由于伽然处传过来的买家类型为1位，但是买家系统中买家类型是2位。故为了匹配....
        if(!StringUtil.isNullOrEmpty(param.getBuyerType()) && param.getBuyerType().length()==1){
            String buyerType = String.valueOf("0"+param.getBuyerType());
            param.setBuyerType(buyerType);
        }
        // 代表需要加载买家产品销售对象和买家经营产品类别
        param.setIsAll("1");
        IBS2101107RsResult rsResult = new IBS2101107RsResult();
        IBS2101138RsResult ibs2101138RsResult = CommRestUtil.searchBuyerPool(param);
        if(null != ibs2101138RsResult && !CollectionUtils.isEmpty(ibs2101138RsResult.getBuyerList())){
            rsResult.setSlBuyerList(ibs2101138RsResult.getBuyerList());
            rsResult.setPageNo(ibs2101138RsResult.getPageNo());
            rsResult.setTotalCount(ibs2101138RsResult.getTotalCount());
            rsResult.setTotalPage(ibs2101138RsResult.getTotalPage());
        }
        if(CollectionUtils.isEmpty(rsResult.getSlBuyerList())){
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("查询失败");
        }else{
            response.setResult(rsResult);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
        }
        return response;
    }
}
