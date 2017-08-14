package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.product.bean.PD141124showNameBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Service
public class PD141125Logic extends BaseLogic {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(PD141125Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId {
        String SQL_ID_FIND_LEVELCODE = "findLevelCode";
        static final String SQL_ID_FIND_BREED_NAME_FEANAME = "findName";

    }

    @Transactional(readOnly = true)
    public PdClassestree findLevelCode(BaseParam param){
        return super.findOne(SqlId.SQL_ID_FIND_LEVELCODE,param);
    }

    /**
     * 获取产品品种数据和产品特征数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141124showNameBean> findBreedNameAndFea(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_BREED_NAME_FEANAME, param);
    }
}
