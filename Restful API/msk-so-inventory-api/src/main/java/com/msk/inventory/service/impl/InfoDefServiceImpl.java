package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.IvmInfoDefBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.service.IInfoDefService;
import com.msk.inventory.service.IInventoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duan_kai on 2016/8/31.
 */
@Service
public class InfoDefServiceImpl extends BaseService implements IInfoDefService {

    @Autowired
    private IInventoryDetailService inventoryDetailService;

    @Override
    public void insertOne(IvmInfoDefBean sqlBean) {
        this.save("insertOne", sqlBean);
    }

    @Override
    public List<IvmInfoDefBean> selectListByCondition(IvmInfoDefBean sqlBean) {
        return this.findList("queryListByCondition", sqlBean);
    }

    @Override
    public int countByCondition(IvmInfoDefBean sqlBean) {
        return this.getCount("countByCondition", sqlBean);
    }

    @Override
    public void updateOneInfoDef(IvmInfoDefBean sqlBean) {
        this.modify("editOne", sqlBean);
    }

    @Override
    public boolean isExist(IvmInfoDefBean sqlBean) {
        boolean isExist = false;
        int countNums = this.getCount("countByCondition", sqlBean);
        if (countNums > 0) {
            isExist = true;
        }
        return isExist;
    }

    public void collectInfoDefFromInbound(long inboundId) {
        IvmInventoryDetailBean condition = new IvmInventoryDetailBean();
        condition.setInboundId(inboundId);
        List<IvmInventoryDetailBean> ivList =  inventoryDetailService.selectInventoryDetailList(condition);

        Map keyMap = new HashMap();
        IvmInventoryDetailBean temp = null;
        String key = null;
        for(int i=0;i<ivList.size();i++){
            temp = ivList.get(i);

            //平台 卖家
            if("1".equals(temp.getSlType())){
                key = IvmInfoDefBean.TYPE_PLATFORM + "_" + temp.getSlId();
            }else{
                key = IvmInfoDefBean.TYPE_SELLER + "_" + temp.getSlId();
            }
            keyMap.put(key, null);

            keyMap.put(IvmInfoDefBean.TYPE_SUPPLIER + "_" + temp.getSupplierId(), null);
            keyMap.put(IvmInfoDefBean.TYPE_OWNER + "_" + temp.getOwnerCode(), null);
            keyMap.put(IvmInfoDefBean.TYPE_PRODUCT + "_" + temp.getPdCode(), null);
            keyMap.put(IvmInfoDefBean.TYPE_SKU + "_" + temp.getSkuCode(), null);
        }

        List<IvmInfoDefBean> infoList = getDescFromOtherModule(keyMap);
        IvmInfoDefBean sqlBean = new IvmInfoDefBean();
        sqlBean.setSqlList(infoList);

        this.save("overwriteInfo", sqlBean);
    }

    public void synInfoDefList(List<IvmInfoDefBean> sqlBeanList){
        IvmInfoDefBean sqlBean = new IvmInfoDefBean();
        sqlBean.setSqlList(sqlBeanList);

        this.save("overwriteInfo", sqlBean);
    }

    private List<IvmInfoDefBean> getDescFromOtherModule(Map paramMap){
        List<IvmInfoDefBean> result = new ArrayList<IvmInfoDefBean>();
        IvmInfoDefBean infoBean = new IvmInfoDefBean();
        infoBean.getListFromMap(paramMap);//排序后的LIST

        //todo 从各模块获得CODE的描述信息，然后汇总到一个大的LIST中

        result.addAll(infoBean.getOwnerList()); //...

        return result;
    }
}
