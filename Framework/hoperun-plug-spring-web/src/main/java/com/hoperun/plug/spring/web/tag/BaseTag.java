package com.hoperun.plug.spring.web.tag;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.PageContext;

/**
 * BaseTag
 * @author jiang_nan
 * @version 1.0
 **/
public abstract class BaseTag extends com.hoperun.web.tag.BaseTag{
    protected <T> T getApplicationBean(String name,Class<T> Class) {
        PageContext pageContext = (PageContext) this.getJspContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
        T taglogic = ctx.getBean(name, Class);
        return taglogic;
    }
}
