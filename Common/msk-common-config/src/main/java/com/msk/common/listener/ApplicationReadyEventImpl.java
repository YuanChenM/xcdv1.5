package com.msk.common.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;

/**
 * Created by jackjiang on 16/8/13.
 */
public class ApplicationReadyEventImpl implements IApplicationEvent<ApplicationReadyEvent>{
    public void init(ApplicationReadyEvent applicationEvent) {
    }



    private void initApplicationUtils(ApplicationReadyEvent applicationEvent){

    }
}
