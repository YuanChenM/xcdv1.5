package com.msk.inventory.rest;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.ISO152405InvParamBean;
import com.msk.inventory.bean.ISO152405ParamBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.service.IInboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfan on 16/8/22.
 */
@RestController
@RequestMapping("api")
public class ISO152405RestController {

    @Autowired
    private IInboundService inboundIvDetailService;

    @RequestMapping(value = "/inv/inventory/inbound",
        method = RequestMethod.POST)
    public RestResponse<ISO152405ParamBean> inboundInventory(@RequestBody RestRequest<ISO152405ParamBean> requestBody) {
        RestResponse<ISO152405ParamBean> response = new RestResponse<>();
        try {
            int listSize = requestBody.getParam().getInvList().size();
            if (listSize > 0) {
                    String loginId = requestBody.getLoginId();
                    List<ISO152405InvParamBean> iso151405IvParamList = requestBody.getParam().getInvList();
                    List<IvmInventoryDetailBean> inoundDetailData = doInboundForDetails(iso151405IvParamList, loginId);
                    inboundIvDetailService.receiveInboundData(inoundDetailData);

                response.setStatus(SystemConstant.RsStatus.SUCCESS);
                response.setMessage("入库成功！");
                response.setReturnCode("");
            } else {
                response.setStatus(SystemConstant.RsStatus.FAIL);
                response.setMessage("无效的入库明细！");
                response.setReturnCode("");
            }
        }catch(BusinessException be){
            be.printStackTrace();
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(be.getMessage());
            response.setReturnCode("F000001");
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage("处理中发生了未知错误，请联系管理员！");
            response.setReturnCode("F000001");
        }
        return response;
    }

    private List<IvmInventoryDetailBean> doInboundForDetails(List<ISO152405InvParamBean> ios152405InvParamBeanList,
        String loginId) {
        IvmInventoryDetailBean ivDetailBean = null;
        List<IvmInventoryDetailBean> result = new ArrayList<IvmInventoryDetailBean>();
        ISO152405InvParamBean temp = null;
        for (int j = 0; j < ios152405InvParamBeanList.size(); j++) {
            ivDetailBean = new IvmInventoryDetailBean();
            temp = ios152405InvParamBeanList.get(j);

            ivDetailBean.setPlatform(temp.getPlatformId());
            ivDetailBean.setPlatformName(temp.getPlatformName());
            ivDetailBean.setLogicArea(temp.getLgcsCode());
            ivDetailBean.setWhCode(temp.getWarehouseCode());
            ivDetailBean.setPucharseBatch(temp.getPurchaseBatch());
            ivDetailBean.setInnerBatch(temp.getInnerBatch());
            ivDetailBean.setSlId(temp.getSlCode());
            ivDetailBean.setSlName(temp.getSlName());
            ivDetailBean.setSlType(temp.getSlType());
            ivDetailBean.setOwnerCode(temp.getSupplierCode());
            ivDetailBean.setOwnerName(temp.getSupplierName());
            ivDetailBean.setPdCode(temp.getPdCode());
            ivDetailBean.setPdName(temp.getPdName());
            if (temp.getSkuCode() == null || "".equals(temp.getSkuCode())) {
                throw new BusinessException("skuCode不能为空");
            }
            ivDetailBean.setSkuCode(temp.getSkuCode());
            //Modif for Bug#2656号 at 2016/09/14 by wangfan2 Start
            int r=temp.getInboundQty().compareTo(BigDecimal.ZERO);
            if(r!=0){
                ivDetailBean.setQty(temp.getInboundQty());
            }else{
                throw new BusinessException("SKU（"+temp.getSkuCode()+"）入库数量不能为0");
            }
            //Modif for Bug#2656号 at 2016/09/14 by wangfan2 End
            ivDetailBean.setBuyPrice(temp.getInboundPrice());
            ivDetailBean.setImp(temp.getInboundPrice());
            ivDetailBean.setInboundType(String.valueOf(InventoryCodeMasterDef.InboundType.IT_PUCHARSE));
            ivDetailBean.setIvFlag(temp.getInventoryStatus());
            ivDetailBean.setInboundId(temp.getInboundId());
            ivDetailBean.setInboundNo(temp.getInboundNo());
            ivDetailBean.setInboundDetailId(temp.getInboundDetailId());
            ivDetailBean.setRiId(temp.getRiId());
            ivDetailBean.setRiNo(temp.getRiNo());
            ivDetailBean.setRiDetailId(temp.getRiDetailId());
            ivDetailBean.setAsnId(temp.getAsnId());
            ivDetailBean.setAsnNo(temp.getAsnNo());
            ivDetailBean.setAsnDetailId(temp.getAsnDetailId());
            ivDetailBean.setPoId(temp.getPoId());
            ivDetailBean.setPoNo(temp.getPoNo());
            ivDetailBean.setPoDetailId(temp.getPoDetailId());
            ivDetailBean.setRecvDate(temp.getRecvDate());
            ivDetailBean.setRecvTime(temp.getRecvTime());
            ivDetailBean.setExpirationDate(temp.getExpirationDate());
            ivDetailBean.setUom(temp.getUom());
            // 默认必填，IV_STATUS DEL_FLG CRT_ID CRT_TIME
            ivDetailBean.setIvStatus(String.valueOf(InventoryCodeMasterDef.IvStatus.IS_IN_TRANSIT));
            ivDetailBean.setDelFlg("0");
            ivDetailBean.setCrtId(loginId);
            ivDetailBean.setCrtTime(DateTimeUtil.getCustomerDate());
            ivDetailBean.setDeliverTime(DateTimeUtil.getCustomerDate());
            /*
             * try {
             * // 库存产品入库
             * inboundIvDetailService.doInboundForDetail(ivDetailBean);
             * // 库存产品入库中
             * ivDetailBean.setUpdId("1");
             * ivDetailBean.setUpdTime(ios152405InvParamBeanList.get(j).getRecvDate());
             * inventoryDetailService.receiveByInbound(ivDetailBean);
             * // 库存产品在库
             * ivDetailBean.setDeliverTime(ios152405InvParamBeanList.get(j).getRecvDate());
             * inventoryDetailService.putawayByInbound(ivDetailBean);
             * } catch (Exception e) {
             * e.printStackTrace();
             * }
             */

            result.add(ivDetailBean);
        }

        return result;
    }

}
