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
public class ProvinceLogic extends BaseLogic{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ProvinceLogic.class);

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
     * 1.加载全部省份，根据需要加载默认市区
     * 2.根据大区加载省份列表
     * 3.通过一组省份编码，查询对应省份
     * @param param
     * @return 返回ProvinceResult
     */
    @Transactional
    public List<ProvinceBean> getProvince(DistrictParam param) {
        return this.findList(param);
    }

    /**
     * 废除数据
     *
     * @param mdProvince
     * @return
     */
    @Transactional
    public int abolish(ProvinceBean mdProvince) {
        return super.modify(SqlId.SQLID_UPDATE_ABOLISH, mdProvince);
    }

    /**
     * 恢复数据
     *
     * @param mdProvince
     * @return
     */
    @Transactional
    public int restore(ProvinceBean mdProvince) {
        return super.modify(SqlId.SQLID_UPDATE_RESTORE, mdProvince);
    }

    /**
     * sqlId
     */
    interface SqlId {
        public String SQLID_UPDATE_ABOLISH = "abolish";
        public String SQLID_UPDATE_RESTORE = "restore";
    }


}
