package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121309Bean;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by guan_zhongheng on 2016/8/23.
 */
@Service
public class IBR121309Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 获取分类买家池名称
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public void getBuyerPoolName(List<IBR121309Bean> slHouseSaleList,String buyerId){
        BaseParam base = new BaseParam();
        base.getFilterMap().put("buyerId",buyerId);
        List<IBR121309Bean> beanList =  this.findList(base);
        if(beanList.size() > 0){
            for(IBR121309Bean saleBean:slHouseSaleList){
                String poolName = "";
                for(IBR121309Bean bean:beanList){
                    // 前一个时间大于后一个时间时 大于0
                    if(saleBean.getEndTime().compareTo(bean.getEndTime()) > 0){
                        poolName += bean.getBuyerPoolName() + ",";
                    }
                }
                if(poolName.length() > 0){
                    saleBean.setBuyerPoolName(poolName.substring(0,poolName.length()-1));
                }
                /** Modif for 改善 #3535 at 2016/11/04 by yuan_zhifei Start*/
                if(StringUtil.isEmpty(saleBean.getBuyerPoolName())){
                    saleBean.setBuyerPoolName("");
                }
                /** Modif for 改善 #3535 at 2016/11/04 by yuan_zhifei End*/
            }
        }
        /** Modif for 改善 #3535 at 2016/11/04 by yuan_zhifei Start*/
        else
        {
           for (IBR121309Bean saleBean:slHouseSaleList){
               if(StringUtil.isEmpty(saleBean.getBuyerPoolName())){
                   saleBean.setBuyerPoolName("");
               }else {
                   continue;
               }
           }
        }
        /** Modif for 改善 #3535 at 2016/11/04 by yuan_zhifei End*/
    }
}
