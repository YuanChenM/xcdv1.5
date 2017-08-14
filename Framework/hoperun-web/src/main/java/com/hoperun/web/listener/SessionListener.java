package com.hoperun.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http Session Listener,监听Session
 * 
 * @author jiang_nan
 * 
 */
public class SessionListener implements HttpSessionListener {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent se) {
		logger.debug("session Created");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

   
}
