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
public class CityLogic extends BaseLogic {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(CityLogic.class);

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
     * 1.根据省编码或物流区编码加载城市列表，并根据需要加载默认区县列表
     * 2.加载对应城市，cityCodes
     * @param param 参数
     * @return 返回
     */
    @Transactional
    public List<CityBean> getCity(DistrictParam param) {
        return this.findList(param);
    }

    /**
     * sqlId
     */
    interface SqlId {
        public String SQLID_UPDATE_ABOLISH = "abolish";
        public String SQLID_UPDATE_RESTORE = "restore";
    }
    /**
     * 废除数据
     *
     * @param mdCity
     * @return
     */
    @Transactional
    public int abolish(CityBean mdCity) {
        return super.modify(SqlId.SQLID_UPDATE_ABOLISH, mdCity);
    }

    /**
     * 恢复数据
     *
     * @param mdCity
     * @return
     */
    @Transactional
    public int restore(CityBean mdCity) {
        return super.modify(SqlId.SQLID_UPDATE_RESTORE, mdCity);
    }

}
