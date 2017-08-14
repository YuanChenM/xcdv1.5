package com.msk.order.bean.result;

import com.msk.common.bean.result.RestPageResult;

import java.util.List;

/**
 * ISO151506_退货单详情
 * Created by wang_shuai on 2016/8/5.
 */
public class SO15150701RestResultList extends RestPageResult {
    private List<SO15150701BeanResult> so15150701BeanResultList;

    public List<SO15150701BeanResult> getSo15150701BeanResultList() {
        return so15150701BeanResultList;
    }

    public void setSo15150701BeanResultList(List<SO15150701BeanResult> so15150701BeanResultList) {
        this.so15150701BeanResultList = so15150701BeanResultList;
    }
}
