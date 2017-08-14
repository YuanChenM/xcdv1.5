package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101126RsBean;
import com.msk.bs.bean.IBS2101126RsParam;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ren_qiang on 2016/8/29.
 */
@Service
public class IBS2101126RsLogic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(IBS2101119RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public List<IBS2101126RsBean> findSlcodeAndDisList(IBS2101126RsParam param){
        List<IBS2101126RsBean> list = this.findList(param);
        return list;
    }
}
