package com.hoperun.web.request;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.hoperun.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 请求Request缓存Filter。
 */
public class HttpServletRequestReplacedFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(HttpServletRequestReplacedFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 是否 web service，当url以/api开始时，表示是
        boolean isRsService = false;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getServletPath();
        if (!StringUtil.isEmpty(url) && url.startsWith("/api")) {
            isRsService = true;
        }

        // web service时，为了输入请求参数，对request做了处理，避免读完inputstream后，后续得不到request内容的问题
        ServletRequest requestWrapper = null;
        if (isRsService && request instanceof HttpServletRequest) {
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if (null != requestWrapper) {
            // 打印service的参数
            String body = HttpHelper.getBodyString(requestWrapper);
            logger.trace("\n[Url]:{}\n[RsRequest:]{}", url, body);
            // web service时，特殊处理
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }
    public void destroy() {
    }

}
