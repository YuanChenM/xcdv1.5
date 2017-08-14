package com.msk.buyers.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121001Bean;
import com.msk.buyers.bean.IBY1213141RsBean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByBuyerDelivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 买家收货信息编辑logic
 * Created by zhou_yajun on 2016/7/8.
 */
@Service
public class BY1213141Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY1213141Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BY121001Logic by121001Logic;

    interface SqlId{
        String DELETE_DELIVERY = "deleteDelivery";
        String MODIFY_DEFAULT_ADDR = "modifyDefaultAddr";
        String FIND_BUYERID_BY_DELIVERYID = "findBuyerIdByDeliveryId";
        String FIND_BUYER_CODE_BY_BUYERID = "findBuyerCodeByBuyerId";
        String MODIFY_BUYER_CODE_BY_BUYERID = "modifyBuyerCodeByBuyerId";
    }

    /**
     * 编辑配送信息
     * @param by1213141Bean
     */
    @Transactional
    public ByBuyerDelivery modifyDelivery(IBY1213141RsBean by1213141Bean){
        BaseParam param = new BaseParam();
        param.setFilter("id", String.valueOf((by1213141Bean.getId())));
       /* param.setUpdId("by");
        param.setUpdTime(DateTimeUtil.getCustomerDate());*/

        ByBuyerDelivery buyerDelivery = this.findOne(param);

        int retult;
        //如果设置了默认地址,先更新所有的配送地址不为默认地址
        if("1".equals(by1213141Bean.getIsDefault())){

           this.modify(SqlId.MODIFY_DEFAULT_ADDR,by1213141Bean);
        }
        /** 存在更新,不存在新增*/
        if(null != buyerDelivery){
            retult = this.modify(by1213141Bean);
        }else{
            Long maxId = commonLogic.maxId("by_buyer_delivery","ID");
            by1213141Bean.setId(maxId);

            /*by1213141Bean.setActTime(DateTimeUtil.getCustomerDate());
            by1213141Bean.setUpdTime(DateTimeUtil.getCustomerDate());
            by1213141Bean.setCrtTime(DateTimeUtil.getCustomerDate());*/

            retult = this.save(by1213141Bean);
            modifyBuyerCodeAddDelivery(by1213141Bean);
            param.setFilter("id",String.valueOf(maxId));
        }
        if(retult == 1){
            return this.findOne(param);
        }else{
            throw new BusinessException("配送信息编辑失败!");
        }
    }

    /**
     * 根据ID删除配送信息
     * @param
     */
    @Transactional
    public int deleteDelivery(BaseParam param){
        int count;
        count = this.modify(SqlId.DELETE_DELIVERY,param);
        if(count == NumberConst.IntDef.INT_ONE){
            modifyBuyerCodeDeleteDelivery(param);
        }
        return count;
    }

    /**
     * 删除买家配送信息时,更新买家编码
     * @param param
     */
    public void modifyBuyerCodeDeleteDelivery(BaseParam param){
        BY121001Bean buyerIdObj = this.findBuyerIdByDeliveryId(param);
        String buyerId = buyerIdObj.getBuyerId();
        modifyBuyerCode(buyerId);
    }

    /**
     * 新增买家配送信息时,更新买家编码
     * @param by1213141Bean
     */
    public void modifyBuyerCodeAddDelivery(IBY1213141RsBean by1213141Bean){
        String buyerId = by1213141Bean.getBuyerId();
        if(!StringUtil.isEmpty(buyerId)){
            modifyBuyerCode(buyerId);
        }
    }

    public void modifyBuyerCode(String buyerId){
        ByBuyerBasicInfo buyerBasicInfo = this.findBuyerBasicByBuyerId(buyerId);
        String buyerCode = buyerBasicInfo.getBuyerCode();
        BY121001Bean by121001Bean = new BY121001Bean();
        by121001Bean.setBuyerCode(buyerCode);
        String identifyCode = by121001Logic.getSecIdenCode(by121001Bean);
        buyerCode = buyerCode.substring(0,buyerCode.length() - 2) + identifyCode;
        buyerBasicInfo.setBuyerCode(buyerCode);
        buyerBasicInfo.setBuyerId(buyerId);
        //更新买家基本信息表
        this.modifyBuyerCodeByBuyerId(buyerBasicInfo);
    }

    public BY121001Bean findBuyerIdByDeliveryId(BaseParam param){
        return this.findOne(SqlId.FIND_BUYERID_BY_DELIVERYID,param);
    }

    public ByBuyerBasicInfo findBuyerBasicByBuyerId(String buyerId){
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        return this.findOne(SqlId.FIND_BUYER_CODE_BY_BUYERID,param);
    }
    public void modifyBuyerCodeByBuyerId(ByBuyerBasicInfo buyerBasicInfo){
        this.modify(SqlId.MODIFY_BUYER_CODE_BY_BUYERID,buyerBasicInfo);
    }
}
