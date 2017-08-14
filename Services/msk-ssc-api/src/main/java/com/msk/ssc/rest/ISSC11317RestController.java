package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ds.bean.ISC1892I1Param;
import com.msk.ds.bean.ISC1892I1RsResult;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11311Logic;
import com.msk.ssc.logic.SSC11317Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wang_shuai on 2016/7/8.
 */
@RestController
public class ISSC11317RestController extends BaseRsController {
    /**
     * 获得日志
     */
    Logger logger = LoggerFactory.getLogger(ISSC11317RestController.class);

    @Autowired
    private SSC11311Logic ssc11311Logic;
    @Autowired
    private SSC11317Logic ssc11317Logic;

    /**
     * 查询预入库基本信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findDeliveryPreInfo",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11317PreIntoBean> findDeliveryPreInfo(@RequestBody RsRequest<SSC11317RsParam> param) {
        logger.info("查询预入库通知单基本信息");
        RsResponse<SSC11317PreIntoBean> response=new RsResponse<SSC11317PreIntoBean>();
        SSC11317PreIntoBean ssc11317PreIntoBean =this.ssc11317Logic.findDeliveryIntoInfo(param.getParam());
        if(ssc11317PreIntoBean!=null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(ssc11317PreIntoBean);
        }else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;

    }

    /**
     * 查询预入库产品详情
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findDeliveryPrePd",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<PageResult<SSC11317PrePdBean>> findDeliveryPrePd(@RequestBody RsRequest<SSC11317RsParam> param) {
        logger.info("查询预入库通知单id和编号查询对应的产品详细");
        RsResponse<PageResult<SSC11317PrePdBean>> response=new RsResponse<PageResult<SSC11317PrePdBean>>();

        //如果是确认发车调用此接口，更新收货状态
        if (param.getParam().getDepartureFlg() != null && param.getParam().getDepartureFlg() == true){
            int departureStatus = this.ssc11317Logic.updateProductRecStatus(param.getParam());
            if(departureStatus != 0){
                response.setStatus(SystemConst.RsStatus.SUCCESS);
            }else {
                response.setStatus(SystemConst.RsStatus.FAIL);
            }
        }else{
            PageResult<SSC11317PrePdBean> res = this.ssc11317Logic.findPage(param.getParam(), SSC11317PrePdBean.class );
            if(res != null){
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setMessage("处理成功");
                response.setResult(res);
            }else {
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("数据不存在");
            }
        }
        return response;

    }

    /**
     * 修改预入库通知单基本信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifyDeliveryIntoInfo",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11317PreIntoBean> modifyDeliveryIntoInfo(@RequestBody RsRequest<SSC11317RsParam> param) {
        logger.info("修改预入库通知单基本信息");
        RsResponse<SSC11317PreIntoBean> response = new RsResponse<SSC11317PreIntoBean>();
        int successFlg = this.ssc11317Logic.modifyDeliveryIntoInfo(param.getParam());
        if(successFlg != NumberConst.IntDef.INT_ZERO){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        }else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("修改失败");
        }
        return response;

    }

    /**
     * 批量更新预入库实际信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/updatePreInto",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<ISC1892I1RsResult> updatePreInto(@RequestBody RsRequest<ISC1892I1Param> param) {
        logger.info("批量更新预入库实际信息");
        ISC1892I1RsResult isc1892I1RsResult = ssc11317Logic.modifyStock(param);
        ISC1892I1Param isc1892I1Param = param.getParam();
        SSC11311Bean ssc11311Bean = new SSC11311Bean();
        ssc11311Bean.setIntoStoreCode(isc1892I1Param.getDeliveryStockId());
        ssc11311Bean.setCrtId(isc1892I1Param.getCrtId());
        ssc11311Logic.generateDiffer(ssc11311Bean);

        RsResponse<ISC1892I1RsResult> response=new RsResponse<ISC1892I1RsResult>();
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(isc1892I1RsResult);
        return response;
    }

    /**
     * 插入预入库文件信息
     * @param bean
     * @return
     */
    @RequestMapping(value = "/ssc/savePreIntoFile",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11317PreIntoBean> savePreIntoFile(@RequestBody RsRequest<SSC11317PreIntoBean> bean) {
        logger.info("插入预入库文件信息");
        RsResponse<SSC11317PreIntoBean> response = new RsResponse<>();
        int successFlg  = ssc11317Logic.savePreIntoFile(bean.getParam());
        if(successFlg != NumberConst.IntDef.INT_ZERO){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("插入成功");
        }else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("插入失败");
        }
        return response;
    }

    /**
     * 查询发货订单关联的预入库单基本信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findPreIntoListByDeliveryId",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<List<SSC11317PreIntoBean>> findListByDeliveryId(@RequestBody RsRequest<SSC11317RsParam> param) {
        logger.info("查询发货订单关联的预入库单基本信息");
        RsResponse<List<SSC11317PreIntoBean>> response = new RsResponse<>();
        List<SSC11317PreIntoBean> list = this.ssc11317Logic.findListByDeliveryId(param.getParam());
        if(!CollectionUtils.isEmpty(list)){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(list);
        }else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

}
