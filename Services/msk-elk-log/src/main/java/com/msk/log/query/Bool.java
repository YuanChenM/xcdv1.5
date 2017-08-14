package com.msk.log.query;

import java.util.List;

/**
 *
 * 多个查询条件封装类
 * Created by mao_yejun on 2016/6/6.
 */
public class Bool {
    /**查询条件list*/
    private List<Should> should;
    /**查询条件中，应该至少满足其中的条数*/
    private int minimum_should_match;

    public int getMinimum_should_match() {
        return minimum_should_match;
    }

    public void setMinimum_should_match(int minimum_should_match) {
        this.minimum_should_match = minimum_should_match;
    }

    public List<Should> getShould() {
        return should;
    }

    public void setShould(List<Should> should) {
        this.should = should;
    }
}
