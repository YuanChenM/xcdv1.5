package com.msk.inventory.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.msk.comm.exception.BusinessException;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.common.utils.SplitListParamUtil;
import com.msk.inventory.bean.ISO152410InvParamBean;
import com.msk.inventory.bean.ISO152410ParamBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.service.IInventoryDetailService;
import com.msk.inventory.service.IOutboundService;

/**
 * Created by wangfan on 16/8/22.
 */
@RestController
@RequestMapping("api")
public class ISO152410RestController {

    @Autowired
    private IInventoryDetailService inventoryDetailService;

    @Autowired
    private IOutboundService outboundService;

    @RequestMapping(value = "/inv/inventory/outbound",
        method = RequestMethod.POST)
    public RestResponse<ISO152410ParamBean> outboundInventory(
        @RequestBody RestRequest<ISO152410ParamBean> requestBody) {
        RestResponse<ISO152410ParamBean> response = new RestResponse<>();
        try{
            int listSize = requestBody.getParam().getInvList().size();
        if (listSize > 0) {
                String loginId = requestBody.getLoginId();
                    List<ISO152410InvParamBean> iso151410IvParamList = requestBody.getParam().getInvList();
                List<IvmInventoryDetailBean> outboundDetailData = doOutboundForDetails(iso151410IvParamList);
                    outboundService.pickInboundData(outboundDetailData, loginId, DateTimeUtil.getCustomerDate());

                response.setStatus(SystemConstant.RsStatus.SUCCESS);
                response.setMessage("出库成功！");
            response.setReturnCode("");
            } else {
                response.setStatus(SystemConstant.RsStatus.FAIL);
                response.setMessage("无效的出库明细！");
                response.setReturnCode("F000001");
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

    private List<IvmInventoryDetailBean> doOutboundForDetails(List<ISO152410InvParamBean> ios152410InvParamBeanList) {
        IvmInventoryDetailBean ivDetailBean = null;
        ISO152410InvParamBean tempDetail = null;
        List<IvmInventoryDetailBean> result = new ArrayList<IvmInventoryDetailBean>();
        for (int j = 0; j < ios152410InvParamBeanList.size(); j++) {
            ivDetailBean = new IvmInventoryDetailBean();
            tempDetail = ios152410InvParamBeanList.get(j);

            if(tempDetail.getOutboundPrice() == null || "".equals(tempDetail.getOutboundPrice())){
                throw new BusinessException("出库价格不能为空");
            }
            if (tempDetail.getSkuCode() == null || "".equals(tempDetail.getSkuCode())) {
                throw new BusinessException("skuCode不能为空");
            }
            if (tempDetail.getOutboundId() == null || "".equals(tempDetail.getOutboundId())) {
                throw new BusinessException("出库作业单id不能为空");
            }
            if (tempDetail.getOutboundNo() == null || "".equals(tempDetail.getOutboundNo())) {
                throw new BusinessException("出库作业单号不能为空");
            }

            ivDetailBean.setPucharseBatch(tempDetail.getPurchaseBatch());
            //Modif for Bug#2656号 at 2016/09/14 by wangfan2 Start
            int r=tempDetail.getOutboundQty().compareTo(BigDecimal.ZERO);
            if(r!=0){
                ivDetailBean.setQty(tempDetail.getOutboundQty());
            }else{
                throw new BusinessException("出库库存不能为0");
            }
            //Modif for Bug#2656号 at 2016/09/14 by wangfan2 End
            ivDetailBean.setOutboundId(tempDetail.getOutboundId());
            ivDetailBean.setOutboundNo(tempDetail.getOutboundNo());
            ivDetailBean.setOutboundDetailId(tempDetail.getOutboundDetailId());
            ivDetailBean.setOutboundType(String.valueOf(InventoryCodeMasterDef.OutboundType.OT_SALE));
            ivDetailBean.setCoDetailId(tempDetail.getCoDetailId());
            ivDetailBean.setCoId(tempDetail.getCoId());
            ivDetailBean.setCoNo(tempDetail.getCoNo());
            ivDetailBean.setSoDetailId(tempDetail.getSoDetailId());
            ivDetailBean.setSoId(tempDetail.getSoId());
            ivDetailBean.setSoNo(tempDetail.getSoNo());
            ivDetailBean.setDiDetailId(tempDetail.getDiDetailId());
            ivDetailBean.setDiId(tempDetail.getDiId());
            ivDetailBean.setDiNo(tempDetail.getDiNo());
            ivDetailBean.setSellPrice(tempDetail.getOutboundPrice());
            ivDetailBean.setPlatform(tempDetail.getPlantFormId());
            ivDetailBean.setLogicArea(tempDetail.getLgcsCode());
            ivDetailBean.setSlId(tempDetail.getSlCode());
            ivDetailBean.setSlType(tempDetail.getSlType());
            ivDetailBean.setSkuCode(tempDetail.getSkuCode());
            ivDetailBean.setPdCode(tempDetail.getPdCode());
            ivDetailBean.setInnerBatch(tempDetail.getInnerBatch());
            ivDetailBean.setWhCode(tempDetail.getWarehouseCode());
            ivDetailBean.setOwnerCode(tempDetail.getSupplierCode());
            ivDetailBean.setDispatchedDate(tempDetail.getDispatchedDate());
            ivDetailBean.setDispatchedTime(tempDetail.getDispatchedTime());
            result.add(ivDetailBean);
        }
        return result;
    }

}
