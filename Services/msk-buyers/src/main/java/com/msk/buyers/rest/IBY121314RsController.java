package com.msk.buyers.rest;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121314Param;
import com.msk.buyers.bean.BY121314RsPageResult;
import com.msk.buyers.bean.IBY121314RsBean;
import com.msk.buyers.logic.BY121314Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByBuyerDelivery;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by yuan_zhifei on 2016/7/8.
 */
@RestController
public class IBY121314RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121314RsController.class);

    @Autowired
    private BY121314Logic by121314Logic;

    /**
     * 买家收货信息画面
     *
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/by/deliveryTimeAndPay/query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121314RsBean> queryDeliveryTimeAndPay(@RequestBody RsRequest<BaseParam> requestParam) {
        logger.info("买家收货信息管控初始化");
        RsResponse<IBY121314RsBean> response = new RsResponse<>();
        IBY121314RsBean byRecTime = by121314Logic.findByRecTime(requestParam.getParam());
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(byRecTime);
        return response;
    }

    /**
     * 买家收货信息查询
     */
    @RequestMapping(value = "/by/deliveryAddr/query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BY121314RsPageResult> queryDeliveryAddr(@RequestBody RsRequest<BY121314Param> requestParam) {
        logger.info("买家收货信息查询");
        RsResponse<BY121314RsPageResult> response = new RsResponse<>();
        BY121314RsPageResult pageResult = new BY121314RsPageResult();
        BasePageParam param = new BasePageParam();
        param.setFilter("id",requestParam.getParam().getId());
        param.setFilter("buyerId",requestParam.getParam().getBuyerId());
        param.setFilter("deliveryAddr",requestParam.getParam().getDeliveryAddr());
        param.setFilter("referenceAddr",requestParam.getParam().getReferenceAddr());
        param.setFilter("manageAddr",requestParam.getParam().getManageAddr());
        param.setFilter("recPerName",requestParam.getParam().getRecPerName());
        param.setFilter("recPerTel",requestParam.getParam().getRecPerTel());
        param.setFilter("recPerWechat",requestParam.getParam().getRecPerWechat());
        param.setFilter("recPerQq",requestParam.getParam().getRecPerQq());

        DbUtils.buildLikeCondition(param, "deliveryAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "referenceAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "manageAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerTel", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerWechat", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "recPerQq", DbUtils.LikeMode.PARTIAL);


        //传入开始页数为0时,表示从页面进入
        if(requestParam.getParam().getPageNo() == NumberConst.IntDef.INT_ZERO){
            param.setStartPos(requestParam.getParam().getStartNo());
        }else{
            param.setStartPos(requestParam.getParam().getStartPos());
        }
        param.setPageSize(requestParam.getParam().getPageCount());
        //有值查询单条新增或修改
        if(!StringUtils.isNullOrEmpty(requestParam.getParam().getId())){
            param.setPaging(false);
        }else{
            param.setPaging(true);
        }
        int count = this.by121314Logic.getPageCount(param);
        List<ByBuyerDelivery> res = this.by121314Logic.findPageList(param, ByBuyerDelivery.class);
        pageResult.setBrOBuyerInfoList(res);
        pageResult.setTotalCount(count);
        pageResult.setTotalPage(count,requestParam.getParam().getPageCount());
        pageResult.setPageNo(requestParam.getParam().getPageNo());
        response.setResult(pageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }



    /**
     * 更新买家基本信息表
     */
    @RequestMapping(value = "/by/deliveryTimeAndPay/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateDeliveryTimeAndPay(@RequestBody RsRequest<IBY121314RsBean> bean) {
        logger.info("买家收货信息更新");
        RsResponse<Integer> response = new RsResponse<>();
        if(bean.getParam()!=null){
            Date currentDate = DateTimeUtil.getCustomerDate();
            bean.getParam().setUpdId(bean.getLoginId());
            bean.getParam().setUpdTime(currentDate);

        int result = this.by121314Logic.modify(bean.getParam());
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("参数为空，处理失败");
        }
        return response;
    }
}
