package com.msk.batch.sp.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.sp.bean.BSP171103Bean;
import com.msk.batch.sp.bean.BSP171103Param;
import com.msk.batch.sp.bean.BSP171104Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.price.bean.PriceCycleParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductPageResult;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 物流区产品同步履历
 * Created by ni_shaotang on 2016/5/18.
 */
@Service
public class BSP171104Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSP171104Logic.class);
    /**
     * CommonLogic
     */
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }



    /**
     * 复制同步数据到履历表
     * @param list
     * @return
     */
    @Transactional
    public int copayProduct(List<BSP171103Bean> list) throws Exception {
        int num=0;
        List<BSP171104Bean> hisList = new ArrayList<BSP171104Bean>();
        Date nowDate =  DateTimeUtil.getCustomerDate();//获取当前时间
        for (BSP171103Bean bean : list){
            BSP171104Bean his = new BSP171104Bean();
            BeanUtils.copyProperties(his, bean);//复制同步数据到履历实体
            long maxPriceId = commonLogic.maxId("SP_LOGS_PRODUCT_HIS", "PD_ID");//获取序列id
            his.setPdId(maxPriceId);
            his.setPdTime(nowDate);
            his.setCrtId("batch");
            his.setCrtTime(nowDate);
            his.setUpdId("batch");
            his.setUpdTime(nowDate);
            his.setActId("batch");
            his.setActTime(nowDate);
            hisList.add(his);
        }
        int nn = 0;
        for(int i=0;i< hisList.size();i+=50){
            nn=i+50;
            if(nn > hisList.size()){
                nn = hisList.size();
            }
            num+=super.batchSave(hisList.subList(i,nn));//添加新的同步数据
        }
        return num;
    }
}
