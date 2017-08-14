package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101114Bean;
import com.msk.bs.bean.IBS2101114Param;
import com.msk.bs.bean.IBS2101114Result;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/13.
 */
@Service
public class IBS2101114RsLogic extends BaseLogic{
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static  String FIND_BS_BUYER="findBsBuyer";
    }

    /**
     * 根据买家id查询该买家归属管家信息。
     * @param param
     * @return
     */
    public IBS2101114Result searchHouseInfo(IBS2101114Param param){
        // 查询专属会员信息（买家归属管家信息）
        List<IBS2101114Bean> slBsBuyerList = this.findList(SqlId.FIND_BS_BUYER,param);
        IBS2101114Result ibs2101114Result = new IBS2101114Result();
        ibs2101114Result.setSlBsBuyerList(slBsBuyerList);
        return  ibs2101114Result;
    }
}
