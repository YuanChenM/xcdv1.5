package com.msk.common.tag;

import com.hoperun.plug.spring.web.tag.BaseTag;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.common.dao.RedisExtendsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rong_ting on 2016/8/24.
 */
public class FavoritesTag extends BaseTag implements DynamicAttributes {

    private Map<String, String> attrMap;
    /** 用户 */
    private String userId;
    /** 模块 */
    private String sysCode;

    /** 调用redis */
    private RedisExtendsUtils redisExtendsUtils;

    @Override
    protected void doTag(HttpServletRequest httpServletRequest) throws JspException, IOException {

        redisExtendsUtils = this.getApplicationBean("redisExtendsUtils", RedisExtendsUtils.class);
        // redis DB
        redisExtendsUtils.setDatabase(RedisDataBaseDef.FAVORITES_DB);
        Map<String,String> mapGet = redisExtendsUtils.getRedisMapValue(this.userId + this.sysCode);
        JspWriter out = this.getJspContext().getOut();
        StringBuffer buttonHtml = new StringBuffer();
        if(null == mapGet || mapGet.size() == 0){
            buttonHtml.append("<li style='color: darkgreen;' class='last'>");
            buttonHtml.append("点击收藏夹，添加你想收藏的一览画面！");
            buttonHtml.append("</li>");
        } else {
            for (String key : mapGet.keySet()) {
                buttonHtml.append("<li style='color: skyblue;' class='last'><a href='");
                buttonHtml.append(key);
                buttonHtml.append("' target='ajax' rel='main-content'>");
                buttonHtml.append("<span style='background: url(" + getRequest().getContextPath() + "/static/images/action/lost.png) no-repeat center;'></span>");
                buttonHtml.append(mapGet.get(key));
                buttonHtml.append("</a><span><img style='margin-left: 3px;margin-top: -2px;' src='" + getRequest().getContextPath() + "/static/images/action/close.png' alt='删除'></span></li>");
            }
        }
        out.write(buttonHtml.toString());
    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {

        if (attrMap == null) {
            attrMap = new HashMap<String, String>();
        }
        attrMap.put(localName, value.toString());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public Map<String, String> getAttrMap() {
        return attrMap;
    }

    public void setAttrMap(Map<String, String> attrMap) {
        this.attrMap = attrMap;
    }
}
