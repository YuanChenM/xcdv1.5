package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.BaseEntity;
import com.msk.seller.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geng_xingdong on 2016/6/16.
 */

@Service
public class ISL231199RsLogic extends BaseLogic {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISL231199RsLogic.class);


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gxd
     */
    interface SqlId {
        static final String SQL_ID_GET_CODE_NAME = "getCodeName";
        static final String SQL_ID_GET_CODE_SHOW_NAME = "getCodeShowName";
    }

    /**
     * 查询卖家区域的code和name
     *
     * @return 返回卖家的区域code和name
     * @paramISL231129RsParam
     * @author gengXingDong
     */
    @Transactional(readOnly = true)
    public List<ISL231199RsBean> queryCodeName() {
        logger.debug("查询卖家的区域code和name");
        BaseParam param = new BaseParam();
        List<ISL231199RsBean> isl231199Result = new ArrayList<ISL231199RsBean>();
        isl231199Result = super.findList(SqlId.SQL_ID_GET_CODE_NAME, param);
        return isl231199Result;
    }


    /**
     * 查询卖家的code和name
     * |
     *
     * @param
     * @return 返回卖家的code和name
     * @author gengXingDong
     */
    @Transactional(readOnly = true)
    public List<ISL231199RsBean> queryCodeShowName(RsRequest<ISL231199RsParam> param) {
        logger.debug("查询卖家的code和name");
        ISL231199RsParam isl231199RsParam = param.getParam();
        List<ISL231199RsBean> isl231199Result = new ArrayList<ISL231199RsBean>();
        if (null != isl231199RsParam) {
            isl231199Result = super.findList(SqlId.SQL_ID_GET_CODE_SHOW_NAME, isl231199RsParam);
        }
        return isl231199Result;
    }


    /**
     * 查询卖家产品分页信息
     *
     * @param param
     * @param <T>
     * @return
     */
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findLgcsSlPageList(ISL231199RsParam param, ISL231199RsPageBean pageResult) {
        param.setPaging(true);
        return super.findPageList(param, pageResult);
    }

}
