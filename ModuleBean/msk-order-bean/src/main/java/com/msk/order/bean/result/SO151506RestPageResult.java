package com.msk.order.bean.result;


import com.msk.common.bean.result.RestPageResult;

import java.util.List;

/**
 * ISO151506_退货单列表
 * Created by wang_shuai on 2016/8/1.
 */
public class SO151506RestPageResult extends RestPageResult {
    private List<SO151506BeanResult> so151506BeanResultList;

    public List<SO151506BeanResult> getSo151506BeanResultList() {
        return so151506BeanResultList;
    }

    public void setSo151506BeanResultList(List<SO151506BeanResult> so151506BeanResultList) {
        this.so151506BeanResultList = so151506BeanResultList;
    }
}
