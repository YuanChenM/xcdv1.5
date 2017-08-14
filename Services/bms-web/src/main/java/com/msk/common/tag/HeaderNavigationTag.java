package com.msk.common.tag;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.web.tag.BaseTag;

/**
 * JSP页面Header导航栏
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class HeaderNavigationTag extends BaseTag {
    private String title;
    private String backTitleArray;
    private String backUrlArray;
    private String backParamArray;
    @Override
    protected void doTag(HttpServletRequest request) throws JspException, IOException {
        StringBuffer headerNavigationHtml = new StringBuffer();
        headerNavigationHtml.append("<div class=\"breadCrumb module page-header\" id=\"headBreadCrumb\">");
        headerNavigationHtml.append(" <ul>");
        headerNavigationHtml.append("   <li><a>首页</a></li>");
        if(!StringUtil.isEmpty(backTitleArray)){
            String[] backTitleList = this.backTitleArray.split(",");
            String[] backUrlList = null;
            if(!StringUtil.isEmpty(backUrlArray)){
                backUrlList = this.backUrlArray.split(",");
            }

            String [] backParamList = null;
            if(!StringUtil.isEmpty(backParamArray)){
                backParamList = this.backParamArray.split("/");
            }
            int length = backTitleList.length;
            for(int i=0;i<length;i++){
                String backTitle = backTitleList[i];
                String actionUrl = StringConst.EMPTY;
                if(backUrlList!=null&&backUrlList.length>0){
                    String backUrl = backUrlList[i];
                    if(!StringUtil.isEmpty(backUrl)){
                        actionUrl = "href='"+backUrl+"' target='ajax'";
                    }else{
                        actionUrl = "href='javascript:void(0)'";
                    }
                }
                String param = StringConst.EMPTY;
                if(backParamList!=null&&backParamList.length>0){
                    String backParam = backParamList[i];
                    if(!StringUtil.isEmpty(backParam)){
                        param = "param='"+backParam+"'";
                    }
                }
                if(StringUtil.isEmpty(backTitle)){
                    continue;
                }
                headerNavigationHtml.append("   <li>");
                headerNavigationHtml.append("       <a "+param+" "+actionUrl+" rel=\"main-content\" style=\"color: #003972;text-decoration: none;font-size: inherit;font-weight: inherit;\">"+backTitle+"</a>");
                headerNavigationHtml.append("   </li>");
            }
        }
        headerNavigationHtml.append("   <li style=\"color: skyblue\">");
        headerNavigationHtml.append(this.title);
        headerNavigationHtml.append("   </li>");
        headerNavigationHtml.append(" </ul>");
        headerNavigationHtml.append("</div>");
        JspWriter out = this.getJspContext().getOut();
        StringBuffer scriptHtml = new StringBuffer();
        scriptHtml.append("<script type=\"text/javascript\">");
        scriptHtml.append("$(document).ready(function () {");
        scriptHtml.append("  $(\"#headBreadCrumb\").jBreadCrumb();");
        scriptHtml.append("});");
        scriptHtml.append("</script>");
        out.write(scriptHtml.toString());
        out.write(headerNavigationHtml.toString());
    }

    /**
     * Getter method for property <tt>backParamArray</tt>.
     *
     * @return property value of backParamArray
     */
    public String getBackParamArray() {
        return backParamArray;
    }

    /**
     * Setter method for property <tt>backParamArray</tt>.
     *
     * @param backParamArray value to be assigned to property backParamArray
     */
    public void setBackParamArray(String backParamArray) {
        this.backParamArray = backParamArray;
    }

    /**
     * Getter method for property <tt>title</tt>.
     *
     * @return property value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for property <tt>title</tt>.
     *
     * @param title value to be assigned to property title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for property <tt>backTitleArray</tt>.
     *
     * @return property value of backTitleArray
     */
    public String getBackTitleArray() {
        return backTitleArray;
    }

    /**
     * Setter method for property <tt>backTitleArray</tt>.
     *
     * @param backTitleArray value to be assigned to property backTitleArray
     */
    public void setBackTitleArray(String backTitleArray) {
        this.backTitleArray = backTitleArray;
    }

    /**
     * Getter method for property <tt>backUrlArray</tt>.
     *
     * @return property value of backUrlArray
     */
    public String getBackUrlArray() {
        return backUrlArray;
    }

    /**
     * Setter method for property <tt>backUrlArray</tt>.
     *
     * @param backUrlArray value to be assigned to property backUrlArray
     */
    public void setBackUrlArray(String backUrlArray) {
        this.backUrlArray = backUrlArray;
    }
}
