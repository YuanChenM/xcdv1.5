package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBA2141101Bean;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 买手app启动
 * Created by ni_shaotang on 2016/9/29.
 */
@Service
public class IBA2141101Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取当前有效版本信息
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBA2141101Bean> getVersions(BaseParam param) {
        return this.findList(param);
    }

}
