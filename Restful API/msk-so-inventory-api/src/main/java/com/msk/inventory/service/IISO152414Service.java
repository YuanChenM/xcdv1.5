package com.msk.inventory.service;

import com.msk.inventory.bean.ISO152414ParamBean;
import com.msk.inventory.bean.ISO152414ProductResultBean;

import java.util.List;

/**
 * Created by duan_kai on 2016/9/5.
 */
public interface IISO152414Service {

    List<ISO152414ProductResultBean> findSlProductIvList(ISO152414ParamBean sqlBean);

}
