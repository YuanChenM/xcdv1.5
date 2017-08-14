package com.msk.common.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;

/**
 * Created by jackjiang on 16/8/13.
 */
public class ApplicationEventFactory {
    public IApplicationEvent createApplicationEvent(ApplicationEvent applicationEvent){
        if(applicationEvent instanceof ApplicationReadyEvent){
            return new ApplicationReadyEventImpl();
        }else if(applicationEvent instanceof ApplicationPreparedEvent){
            return new ApplicationPreparedEventImpl();
        }
        return null;
    }
}
