package com.msk.inventory.service;

import com.msk.inventory.bean.SO152501Bean;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/13.
 */
public interface SO152501Service {

    List<SO152501Bean> getDistributionList(SO152501Bean sqlBean);

}
