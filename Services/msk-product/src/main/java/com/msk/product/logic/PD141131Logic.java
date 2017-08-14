package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdClassestree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * PD141101Logic
 * @author pxg
 */
@Service
public class PD141131Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_FIND_ONE_CLASS = "findOneClass";
        String SQL_ID_FIND_FIND_TWO_CLASS = "findTWOClass";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取第一级分类
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdClassestree> queryOneClassify(){
        BaseParam param=new BaseParam();
        param.setFilter("level","1");
        List<PdClassestree> list=new ArrayList<>();
        list=super.findList(SqlId.SQL_ID_FIND_FIND_ONE_CLASS,param);
        return list;
    }
    /**
     * 获取第二级分类
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdClassestree> queryTwoClassify(String level,String parentCode){
        BaseParam param=new BaseParam();
        param.setFilter("level",level);
        param.setFilter("parentCode",parentCode);
        List<PdClassestree> list=new ArrayList<>();
        list=super.findList(SqlId.SQL_ID_FIND_FIND_TWO_CLASS,param);
        return list;
    }

}
