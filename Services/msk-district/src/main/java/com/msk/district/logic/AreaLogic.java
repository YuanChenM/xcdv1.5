package com.msk.district.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.MdArea;
import com.msk.district.bean.AreaBean;
import com.msk.district.bean.DistrictParam;
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
public class AreaLogic extends BaseLogic {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(AreaLogic.class);

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
     * 1.查询全部大区信息，并根据需要加载默认省或物流区
     * 2.通过一组大区编码，查询对应大区
     * @param param 参数
     * @return 返回
     */
    @Transactional
    public List<AreaBean> getArea(DistrictParam param) {
        return this.findList(param);
    }

    /**
     * 废除数据
     *
     * @param mdArea
     * @return
     */
    @Transactional
    public int abolish(AreaBean mdArea) {
        return super.modify(SqlId.SQLID_UPDATE_ABOLISH, mdArea);
    }

    /**
     * 恢复数据
     *
     * @param mdArea
     * @return
     */
    @Transactional
    public int restore(AreaBean mdArea) {
        return super.modify(SqlId.SQLID_UPDATE_RESTORE, mdArea);
    }
    /**
     * sqlId
     */
    interface SqlId {
        public String SQLID_UPDATE_ABOLISH = "abolish";
        public String SQLID_UPDATE_RESTORE = "restore";
    }
}
