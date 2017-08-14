package com.msk.inventory.service;

import com.msk.inventory.bean.SO152502Bean;

import java.util.List;

/**
 * Created by zheng_xu on 2016/9/13.
 */
public interface SO152502Service {

     List<SO152502Bean> getSellerInventoryList(SO152502Bean sqlBean);

}
