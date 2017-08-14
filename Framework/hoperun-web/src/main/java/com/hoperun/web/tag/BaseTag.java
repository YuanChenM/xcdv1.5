package com.hoperun.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * BaseTag
 * @author jiang_nan
 * @version 1.0
 **/
public abstract class BaseTag extends SimpleTagSupport {

    /**
     * make the tag HTML.
     * @param request
     *            the current request
     * @throws javax.servlet.jsp.JspException
     *             Subclasses can throw JspException to indicate an error
     *             occurred while processing this tag.
     * @throws IOException
     *             Subclasses can throw IOException if there was an error
     *             writing to the output stream
     */
    protected abstract void doTag(HttpServletRequest request) throws JspException, IOException;

    @Override
    public void doTag() throws JspException, IOException {
        this.doTag(this.getRequest());
    }

    protected HttpServletRequest getRequest() {
        PageContext pageContext = (PageContext) this.getJspContext();
        return (HttpServletRequest) pageContext.getRequest();
    }

}
