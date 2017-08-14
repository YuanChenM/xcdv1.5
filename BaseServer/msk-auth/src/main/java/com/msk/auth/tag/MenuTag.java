package com.msk.auth.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackjiang on 16/8/21.
 */
public class MenuTag extends SimpleTagSupport implements DynamicAttributes {
    private Map<String, String> attrMap;
    @Override
    public void doTag() throws JspException, IOException {
        StringBuffer buttonHtml = new StringBuffer();








    }


    protected HttpServletRequest getRequest() {
        PageContext pageContext = (PageContext) this.getJspContext();
        return (HttpServletRequest) pageContext.getRequest();
    }


    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (attrMap == null) {
            attrMap = new HashMap<>();
        }
        attrMap.put(localName, value.toString());
    }
}
