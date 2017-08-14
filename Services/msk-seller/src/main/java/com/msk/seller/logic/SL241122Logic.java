package com.msk.seller.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.PdStandard;
import com.hoperun.core.utils.StringUtil;
import com.msk.core.entity.SlPdTncStdOther;
import com.msk.seller.bean.SL241117Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by gyh on 2016/3/11.
 */
public class SL241122Logic extends BaseLogic{


    @Autowired
    private ISL231106Logic isl231106Logic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 批量处理标准值数据信息
     */
    @Transactional
    public void batchProcessLogic(List<SlPdTncStdOther> otherList,String loginId){
        for(SlPdTncStdOther slPdTncStdOther:otherList){
            int count = isl231106Logic.findSlPdTncStdCount(slPdTncStdOther);
            if(count >= NumberConst.IntDef.INT_ONE){
                //修改
                slPdTncStdOther.setUpdId(loginId);
                slPdTncStdOther.setUpdTime(DateTimeUtil.getCustomerDate());
                isl231106Logic.modify(slPdTncStdOther);
            }else{
                //新增
                slPdTncStdOther.setCrtId(loginId);
                slPdTncStdOther.setCrtTime(DateTimeUtil.getCustomerDate());
                isl231106Logic.save(slPdTncStdOther);
            }
        }
    }
}
