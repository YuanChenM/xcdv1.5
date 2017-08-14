package com.msk.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hoperun.core.utils.StringUtil;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.AuthoritieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * Created by jiangnan on 15/11/25.
 */
public class AuthoritieInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回FALSE
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回TRUE
     * 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller
     * 然后进入拦截器链
     * 从最后一个拦截器往回执行所有的POSTHandle()
     * 接着再从最后一个拦截器往回执行所有的AFTERCOMPLETION()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        String requestUri = request.getRequestURI();
        logger.debug("Request URL:" + requestUri);


        return super.preHandle(request, response, handler);
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作<br/>
     * 可在modelAndView中加入数据
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
