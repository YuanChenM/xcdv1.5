package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.LgcsProductBean;
import com.msk.price.bean.LgcsProductResult;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/6/16.
 */
@Service
public class LgcsProductCopyLogic extends BaseLogic{
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
    public int copayProduct(List<LgcsProductBean> list,BaseParam param) throws Exception {
        int num=0;
        List<LgcsProductResult> hisList = new ArrayList<LgcsProductResult>();
        Date nowDate =  DateTimeUtil.getCustomerDate();//获取当前时间
        for (LgcsProductBean bean : list){
            long maxPriceId = commonLogic.maxId("SP_LOGS_PRODUCT_HIS", "PD_ID");//获取序列id
            LgcsProductResult his = new LgcsProductResult();
            BeanUtils.copyProperties(his, bean);//复制同步数据到履历实体
            his.setPdId(maxPriceId);
            his.setPdTime(nowDate);
            his.setCrtId(param.getCrtId());
            his.setCrtTime(nowDate);
            his.setUpdId(param.getUpdId());
            his.setUpdTime(nowDate);
            his.setActId(param.getActId());
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
