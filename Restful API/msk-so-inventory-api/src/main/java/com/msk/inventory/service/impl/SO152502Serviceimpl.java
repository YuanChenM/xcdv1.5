package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.inventory.bean.SO152502Bean;
import com.msk.inventory.service.SO152502Service;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/13.
 */
@Service
public class SO152502Serviceimpl extends BaseService implements SO152502Service {
    @Override
    public List<SO152502Bean> getSellerInventoryList(SO152502Bean sqlBean) {
        List<SO152502Bean> so152502Beans = this.findList("getSellerInventoryList",sqlBean);
        return so152502Beans;
    }
}
