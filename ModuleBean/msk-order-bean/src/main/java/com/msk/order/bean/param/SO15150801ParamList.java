package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * SO15150801_选择产品画面参数LIST
 * Created by wang_jianzhou on 2016/8/10.
 */
public class SO15150801ParamList extends BaseParam{

    private List<SO15150801Param> so15150801ParamList;

    public List<SO15150801Param> getSo15150801ParamList() {
        return so15150801ParamList;
    }

    public void setSo15150801ParamList(List<SO15150801Param> so15150801ParamList) {
        this.so15150801ParamList = so15150801ParamList;
    }
}
