package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11305Logic;
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
 * 发货单查询接口
 */
@RestController
public class ISSC11305RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11305RestController.class);

    @Autowired
    private SSC11305Logic ssc11305Logic;
    /**
     * 查询发货单一览
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findSscDeliveryOrderBasicList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11305RsPageResult> findSscDeliveryOrderBasic(@RequestBody RsRequest<SSC11305RsParam> param) {
        logger.info("-----发货单一览-----");
        RsResponse<SSC11305RsPageResult> response = new RsResponse<>();
        SSC11305RsPageResult sSC11305RsPageResult=new SSC11305RsPageResult();
        //查询数据Count
        int count = this.ssc11305Logic.getPageCount(param.getParam());
         List<SSC11305RsBean> res=null;
        if(count != NumberConst.IntDef.INT_ZERO){
            res = ssc11305Logic.findPageList(param.getParam(), SSC11305RsBean.class);
            sSC11305RsPageResult.setSSC11305RsBeanPageResult(res);
            sSC11305RsPageResult.setTotalCount(count);
            response.setResult(sSC11305RsPageResult);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }
    /**
     * 执行合同基础表以及合同产品明细表往发货基础表和发货产品明细表的数据迁移
     * @param
     * @return
     */
    @RequestMapping(value = "/ssc/createSscDeliveryOrderInfo",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Long> createSscDeliveryOrderInfo(@RequestBody RsRequest<SSC11305RsParam> param ) {
        logger.info("发货单基本信息新增");
        RsResponse<Long> response= new RsResponse<Long>();
        Integer flag=this.ssc11305Logic.checkDeliveryOrder(param.getParam());
        if(flag==0){
            //执行合同基础表以及合同产品明细表往发货基础表和发货产品明细表的数据迁移
            Long  deliveryId = this.ssc11305Logic.insertDelivery(param.getParam());
            if(deliveryId!=null){
                if (deliveryId.equals(0L)) {
                    response.setStatus(SystemConst.RsStatus.FAIL);
                    response.setMessage("产品预付款比例不能为空！");
                }
                else {
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setResult(deliveryId);
                    response.setMessage("创建成功");
                }
            } else{
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("创建失败");
            }
        }else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("该车次已生成发货单，无法再次生成！");
        }
        return response;
    }

}


