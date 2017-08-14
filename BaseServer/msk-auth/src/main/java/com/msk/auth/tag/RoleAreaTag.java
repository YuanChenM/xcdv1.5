package com.msk.auth.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.msk.auth.utils.AuthorityUtils;

public class RoleAreaTag extends TagSupport {
    private String pageCode;
    private String areaCode;
    private boolean needAuth = true;

    public RoleAreaTag() {
        super();
        this.init();
    }

    @Override
    public void release() {
        super.release();
        this.init();
    }

    private void init(){
        this.pageCode = null;
        this.areaCode = null;
        this.needAuth = false;
    }

    @Override
    public int doStartTag() throws JspException {
        System.err.println("doStartTag");
        boolean condition = this.condition();
        System.err.println("condition:"+condition);
        if (condition){
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }

    protected boolean condition() throws JspTagException {
        if(!needAuth){
            return Boolean.TRUE;
        }
        HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
        boolean buttonAuth = AuthorityUtils.checkButtonAuth(request,pageCode,areaCode);
        return buttonAuth;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public boolean isNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(boolean needAuth) {
        this.needAuth = needAuth;
    }
}
