package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.SO152501Bean;
import com.msk.inventory.service.SO152501Service;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/13.
 */
@Service
public class SO152501Serviceimpl extends BaseService implements SO152501Service {
    @Override
    public List<SO152501Bean> getDistributionList(SO152501Bean sqlBean) {
        List<SO152501Bean> so152501List = this.findList("getDistributionList", sqlBean);
        return so152501List;
    }
}
