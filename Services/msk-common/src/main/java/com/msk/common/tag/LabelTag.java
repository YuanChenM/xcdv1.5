package com.msk.common.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.hoperun.core.utils.StringUtil;

/**
 * Created by zhang_jian3 on 2016/10/17.
 */
public class LabelTag extends SimpleTagSupport implements DynamicAttributes {

    private Map<String, String> attrMap;

    /** 资源编号 */
    private String id;
    /** Value */
    private String value;
    /** 是否必填 */
    private boolean required = false;
    /** 规定 label 绑定到哪个表单元素ID */
    private String forId;
    /** 规定 label 字段所属的一个或多个表单ID */
    private String forForm;

    public LabelTag() {}

    @Override
    public void doTag() throws JspException, IOException {
        try {
            JspWriter out = this.getJspContext().getOut();
            StringBuffer textHtml = new StringBuffer();
            textHtml.append("<label");
            if (this.attrMap != null) {
                for (Map.Entry<?, ?> entry : this.attrMap.entrySet()) {
                    textHtml.append(" " + entry.getKey() + "='" + entry.getValue() + "'");
                }
            }
            if (!StringUtil.isNullOrEmpty(id)) {
                textHtml.append(" id=\"" + id + "\"");
            }
            if (!StringUtil.isNullOrEmpty(forId)) {
                textHtml.append(" for=\"" + forId + "\"");
            }
            if (!StringUtil.isNullOrEmpty(forForm)) {
                textHtml.append(" form=\"" + forForm + "\"");
            }
            textHtml.append(" >");
            if (required) {
                textHtml.append("<b style=\"color:red;vertical-align:middle;text-align:center;\">*&nbsp;</b>");
            }

            textHtml.append(value + ":</label>");

            // if (!StringUtil.isNullOrEmpty(buttonId) && required) {
            // textHtml.append("<script type=\"text/javascript\">");
            // textHtml.append("$(document).ready(function () {");
            // textHtml.append("$('#" + buttonId.replace(".", "_") + "').click( function (){" + "if($('#" + id
            // + "').val()==''){" + "alert('" + value + " is required!');return false;" + "}});");
            // textHtml.append("});");
            // textHtml.append("</script>");
            // }

            out.write(textHtml.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (this.attrMap == null) {
            this.attrMap = new HashMap();
        }

        this.attrMap.put(localName, value.toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getForId() {
        return forId;
    }

    public void setForId(String forId) {
        this.forId = forId;
    }

    public String getForForm() {
        return forForm;
    }

    public void setForForm(String forForm) {
        this.forForm = forForm;
    }
}
