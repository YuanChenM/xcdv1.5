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
public class DistrictLogic extends BaseLogic {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(DistrictLogic.class);

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
     * 1.根据城市编码查询区县列表，用于联动
     * @param param 参数
     * @return 返回区县
     */
    @Transactional
    public List<DistrictBean> getDistrict(DistrictParam param) {
        return this.findList(param);
    }

    /**
     * 查询行政区规划信息
     * @param param
     * @return
     */
    @Transactional
    public List<DistrictBean> findFullNameList(DistrictParam param) {
        return super.findList(SqlId.SQLID_FIND_FULLNAME_LIST, param);
    }

    /**
     * 查询行政区名称，查询对应的Code
     * @param param
     * @return
     */
    @Transactional
    public List<DistrictBean> getCodesFromNames(DistrictParam param) {
        return super.findList(SqlId.SQLID_FIND_CODES_FROM_NAMES, param);
    }

    /**
     * 废除数据
     *
     * @param mdDistrict
     * @return
     */
    @Transactional
    public int abolish(DistrictBean mdDistrict) {
        return super.modify(SqlId.SQLID_UPDATE_ABOLISH, mdDistrict);
    }

    /**
     * 恢复数据
     *
     * @param mdDistrict
     * @return
     */
    @Transactional
    public int restore(DistrictBean mdDistrict) {
        return super.modify(SqlId.SQLID_UPDATE_RESTORE, mdDistrict);
    }
    /**
     * sqlId
     */
    interface SqlId {
        public String SQLID_UPDATE_ABOLISH = "abolish";
        public String SQLID_UPDATE_RESTORE = "restore";
        public String SQLID_FIND_FULLNAME_LIST = "findFullNameList";
        public String SQLID_FIND_CODES_FROM_NAMES = "getCodesFromNames";
    }

}
