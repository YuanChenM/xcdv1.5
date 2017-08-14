package com.msk.district.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.district.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liu_yan2 on 2016/4/27.
 */
@Service
public class LgcsAreaLogic extends BaseLogic{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(LgcsAreaLogic.class);

    /*
     * (non-Javadoc)
     * @see com.snk.core.base.BaseLogic#setBaseDao(com.snk.core.base.BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 1.加载全部物流区，根据需要加载默认市区
     * 2.根据大区加载物流区列表
     * 3.通过一组物流区编码，查询对应物流区
     * @param param
     * @return 返回物流区数据
     */
    @Transactional
    public List<LgcsAreaBean> getLgcsArea(DistrictParam param) {
        return this.findList(param);
    }

    /**
     * 废除数据
     *
     * @param mdLogisticsArea
     * @return
     */
    @Transactional
    public int abolish(LgcsAreaBean mdLogisticsArea) {
        return super.modify(SqlId.SQLID_UPDATE_ABOLISH, mdLogisticsArea);
    }

    /**
     * 恢复数据
     *
     * @param mdLogisticsArea
     * @return
     */
    @Transactional
    public int restore(LgcsAreaBean mdLogisticsArea) {
        return super.modify(SqlId.SQLID_UPDATE_RESTORE, mdLogisticsArea);
    }
    /**
     * sqlId
     */
    interface SqlId {
        public String SQLID_UPDATE_ABOLISH = "abolish";
        public String SQLID_UPDATE_RESTORE = "restore";
    }
}
