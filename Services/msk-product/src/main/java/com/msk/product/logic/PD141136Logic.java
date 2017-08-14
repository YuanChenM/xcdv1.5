package com.msk.product.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.product.bean.PD141136Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * PD141136Logic
 *
 * @author pxg
 */
@Service
public class PD141136Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     *
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_FIND_ONE_CLASS = "findOneClass";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询一级产品类别列表
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdClassestree> findListClasses(BaseParam param) {
        //查询列表数据
        return super.findList(param);
    }

    @Transactional(readOnly = true)
    public PageResult<PD141136Bean> findListSl(BasePageParam pageParam) {
        PageResult<PD141136Bean> result = super.findPage(pageParam, PD141136Bean.class);
        /** Modfiy: #2497 卖家产品池注册总控表列表：查询个别的产品，报错   20160907  BY  杨春艳  Start */
        if (null != result.getData() && result.getData().size() > NumberConst.IntDef.INT_ZERO) {
            result.setRecordsTotal(result.getData().size());
            List<PD141136Bean> beans = result.getData();
            for (PD141136Bean bean : beans) {
                if(null!=bean){
                    if(null!=bean.getStatusMonitorDate()){
                        bean.setShowDate(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, bean.getStatusMonitorDate()));
                        bean.setStatusMonitorDate(null);
                    }else{
                        bean.setShowDate("");
                    }
                }

            }
        }
        /** Modfiy: #2497 卖家产品池注册总控表列表：查询个别的产品，报错   20160907  BY  杨春艳  End */
        return result;
    }
}
