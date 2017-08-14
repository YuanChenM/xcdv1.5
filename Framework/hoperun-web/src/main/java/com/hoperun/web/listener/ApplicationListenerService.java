package com.hoperun.web.listener;
import javax.servlet.ServletContext;
/**
 * 服务器启动服务
 * @author jiang_nan
 * @version 1.0
 **/
public interface ApplicationListenerService {
    public void handle(ServletContext application);
}
