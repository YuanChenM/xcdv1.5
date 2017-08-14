package com.msk.auth.tag;

import com.msk.auth.utils.AuthorityUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ButtonTag extends SimpleTagSupport implements DynamicAttributes {
    private Map<String, String> attrMap;
    private String buttonId;
    private String buttonValue;
    private String buttonType;
    private String url;
    private String param;
    private boolean needAuth = false;
    private static int INT_ZERO = 0;
    @Override
    public void doTag() throws JspException, IOException {
        HttpServletRequest request = this.getRequest();
        String id = this.buttonId.replace(".", "_");
        //Button Id必须包含"_"
        if (id.indexOf("_") < INT_ZERO) {
            return;
        }
        JspWriter out = this.getJspContext().getOut();
        StringBuffer buttonHtml = new StringBuffer();
        int index = id.indexOf("_");
        String buttonId = id.substring(index + 1, id.length());
        String pageCode = id.substring(0, index);
        boolean buttonAuth = AuthorityUtils.checkButtonAuth(request,pageCode,buttonId);
        if(!needAuth && !buttonAuth){
            return;
        }
        buttonHtml.append("<input ");
        buttonHtml.append(" type='");
        buttonHtml.append(this.buttonType);
        buttonHtml.append("'");
        if (this.attrMap != null) {
            for (Map.Entry<?, ?> entry : this.attrMap.entrySet()) {
                buttonHtml.append(" " + entry.getKey() + "='" + entry.getValue() + "'");
            }
        }
        buttonHtml.append(" value='" + buttonValue + "'");
        buttonHtml.append(" title='" + buttonValue + "'");

        buttonHtml.append(" id='" + id + "'");
        buttonHtml.append(" class='h-button' />");
        buttonHtml.append("<script type=\"text/javascript\">");
        buttonHtml.append("$(document).ready(function () {");
        buttonHtml.append("$('#" + id + "').button();");
        if (!StringUtils.isEmpty(this.url)) {
            String jsonData = "{}";
            if (this.param != null) {
                jsonData = this.param;
            }
            buttonHtml.append("$('#" + id + "').click(function(){");
            buttonHtml.append("$('#main-content').postUrl('" + this.url + "'," + jsonData + ");");
            buttonHtml.append("});");
        }
        buttonHtml.append("});");
        buttonHtml.append("</script>");
        out.write(buttonHtml.toString());
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

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public boolean isNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(boolean needAuth) {
        this.needAuth = needAuth;
    }
}
