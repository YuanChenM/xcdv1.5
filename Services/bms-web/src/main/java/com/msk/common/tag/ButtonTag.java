package com.msk.common.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.web.tag.BaseTag;

/**
 * *ButtonTag
 * *@author jiang_nan
 * *@version 1.0
 **/
public class ButtonTag extends BaseTag implements DynamicAttributes {
    private Map<String, String> attrMap;
    /** Button 资源编号 */
    private String buttonId;
    /**Button Value*/
    private String buttonValue;
    /** button 类型 */
    private String buttonType;
    /** url */
    private String url;
    /**Param*/
    private String param;
    @Override
    protected void doTag(HttpServletRequest request) throws JspException, IOException {
        String id = this.buttonId.replace(".", "_");
        // 如果id中必须包含"_"
        if(id.indexOf("_") > NumberConst.IntDef.INT_ZERO){
            JspWriter out = this.getJspContext().getOut();
            StringBuffer buttonHtml = new StringBuffer();
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
            if(!StringUtil.isEmpty(this.url)){
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
    }

    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (attrMap == null) {
            attrMap = new HashMap<String, String>();
        }
        attrMap.put(localName, value.toString());
    }

    /**
     * @return the attrMap
     */
    public Map<String, String> getAttrMap() {
        return attrMap;
    }

    /**
     * @param attrMap the attrMap to set
     */
    public void setAttrMap(Map<String, String> attrMap) {
        this.attrMap = attrMap;
    }

    /**
     * @return the buttonId
     */
    public String getButtonId() {
        return buttonId;
    }

    /**
     * @param buttonId the buttonId to set
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    /**
     * @return the buttonType
     */
    public String getButtonType() {
        return buttonType;
    }

    /**
     * @param buttonType the buttonType to set
     */
    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the param
     */
    public String getParam() {
        return param;
    }

    /**
     * @param param the param to set
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * @return the buttonValue
     */
    public String getButtonValue() {
        return buttonValue;
    }

    /**
     * @param buttonValue the buttonValue to set
     */
    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }
}
