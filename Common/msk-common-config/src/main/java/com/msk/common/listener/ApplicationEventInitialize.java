package com.msk.common.listener;

import org.springframework.context.ApplicationEvent;

/**
 * Created by jackjiang on 16/8/13.
 */
public class ApplicationEventInitialize {
    private ApplicationEvent applicationEvent;

    public ApplicationEventInitialize(ApplicationEvent applicationEvent) {
        this.applicationEvent = applicationEvent;
    }

    public void initialize() {
        ApplicationEventFactory eventFactory = new ApplicationEventFactory();
        IApplicationEvent event = eventFactory.createApplicationEvent(applicationEvent);
        if (event == null) return;
        event.init(applicationEvent);
    }

}
