package com.msk.comm.dynamic;

/**
 * Created by jackjiang on 16/7/4.
 */

public interface DynamicBeanReader {
    /**
     * 动态加载bean
     * @param dynamicBean
     */
    public void loadBean(DynamicBean dynamicBean);
}
