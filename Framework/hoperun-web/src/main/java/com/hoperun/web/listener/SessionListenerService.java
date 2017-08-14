package com.hoperun.web.listener;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/4/20.
 */
public interface SessionListenerService {
    public void handle(HttpSession session);
}
