package com.msk.common.listener;

import org.springframework.context.ApplicationEvent;

/**
 * Created by jackjiang on 16/8/13.
 */
public interface IApplicationEvent<T extends ApplicationEvent > {
    void init(T applicationEvent);
}
