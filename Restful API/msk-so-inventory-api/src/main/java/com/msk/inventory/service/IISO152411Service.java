package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152411ParamBean;
import com.msk.inventory.bean.ISO152411ResultBean;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/23.
 */
public interface IISO152411Service {

    List<ISO152411ResultBean> getProdBySlType(ISO152411ParamBean sqlBean);
}
