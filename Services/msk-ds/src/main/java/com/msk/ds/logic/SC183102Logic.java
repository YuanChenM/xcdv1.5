package com.msk.ds.logic;

import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.ds.bean.SC183101Param;
import com.msk.ds.bean.SC183102Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * SC183102Logic.
 * @author fjm
 * @version 1.0
 **/
@Service
public class SC183102Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC183102Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {

        String SQL_ID_DELETE_PLANBEAN="deletePlanBean";
    }

    @Transactional
    public int deleteBean(SC183102Bean sc183102Bean){
        return super.remove(SqlId.SQL_ID_DELETE_PLANBEAN,sc183102Bean);
    }

    public PageResult<SC183101Param> findPageList(){
        PageResult<SC183101Param> pageParam=new PageResult<SC183101Param>();
        List<SC183101Param> listAll=new ArrayList<SC183101Param>();

        pageParam.setData(listAll);
        return pageParam;
    }

}
