package com.hoperun.web.listener;

import com.hoperun.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



/**
 * Application Listener
 * 
 * @author jiang_nan
 * 
 */
public class InitializerListener implements ServletContextListener {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(InitializerListener.class);
    private ApplicationListenerService applicationListenerService;
    /**
     * 服务启动时,加载在内存中的数据
     * 
     * @param sce
     */
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Start Application");
        String applicationService = sce.getServletContext().getInitParameter("applicationServices");
        logger.info("Application Service Class Name:"+applicationService);
        if(!StringUtil.isEmpty(applicationService)){
            try{
                this.applicationListenerService = (ApplicationListenerService)Class.forName(applicationService).newInstance();
                this.applicationListenerService.handle(sce.getServletContext());
            }catch (ClassNotFoundException ex){
                logger.error(ex.getMessage());
            }catch (InstantiationException ex){
                logger.error(ex.getMessage());
            }catch (IllegalAccessException ex){
                logger.error(ex.getMessage());
            }
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

}
