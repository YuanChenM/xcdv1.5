package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBA2141204RsParam;
import com.msk.bs.bean.IBA2141204RsResult;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/9/18.
 */
@Service
public class IBA2141204RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 根据买家ID查询对应的管家信息
     * @param param
     * @return
     */
    @Transactional(readOnly = false)
    public List<IBA2141204RsResult> findHouseCodeByBuyerId(IBA2141204RsParam param){
        return this.findList(param);
    }
}
