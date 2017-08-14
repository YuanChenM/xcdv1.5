package com.msk.common.tag;

import com.alibaba.fastjson.JSON;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.hoperun.plug.spring.web.tag.BaseTag;
import com.msk.common.consts.RedisDataBaseDef;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CodeMasterTag
 * @author jiang_nan
 * @version 1.0
 **/
public class CodeMasterTag extends BaseTag implements DynamicAttributes{
    private static String VIEW_TYPE_LABEL = "label";
    private static String VIEW_TYPE_SELECT = "select";
    private static String VIEW_TYPE_JSON = "json";
    /**输出类型：label,select，json*/
    private String viewType;
    /**code master的code type*/
    private String codeType;
    /**code master的code value*/
    private String codeValue;
    /**label和select标签使用的时候的ID*/
    private String id;
    /**label和select标签使用的时候的name*/
    private String name;
    /**自定义属性*/
    private Map<String,Object> attrMap;
    @Override
    protected void doTag(HttpServletRequest request) throws JspException, IOException {
        BaseRedisDao redisDao = super.getApplicationBean("redisDao",BaseRedisDao.class);
        //读取Code Master DB
        redisDao.setDatabase(RedisDataBaseDef.CODE_MASTER_DB);

        Map<String,String> codeMasterMap = redisDao.getRedisMapValue(this.codeType);
        StringBuffer html = new StringBuffer();
        if(VIEW_TYPE_LABEL.equalsIgnoreCase(viewType)){
            html.append(this.createCodeMasterLabel(codeMasterMap));
        }else if(VIEW_TYPE_SELECT.equalsIgnoreCase(viewType)){
            html.append(this.createCodeMasterSelect(codeMasterMap));
        }else if(VIEW_TYPE_JSON.equalsIgnoreCase(viewType)){
            html.append(this.createCodeMasterJson(codeMasterMap));
        }
        JspWriter out = this.getJspContext().getOut();
        out.write(html.toString());
    }
    private String createCodeMasterSelect(Map<String,String> codeMasterMap){
        StringBuffer selectHtml = new StringBuffer();
        selectHtml.append("<select id='"+this.id+"' name='"+this.name+"' ");
        if(!CollectionUtils.isEmpty(this.attrMap)){
            for (Map.Entry<String,Object> entry : this.attrMap.entrySet()){
                selectHtml.append(" "+ entry.getKey() + " ='"+entry.getValue()+"' ");
            }
        }
        selectHtml.append(" >");
        if(!CollectionUtils.isEmpty(codeMasterMap)){
            for(Map.Entry<String,String> entry : codeMasterMap.entrySet()){
                selectHtml.append("<option value='"+entry.getKey()+"'>"+entry.getValue()+"</option>");
            }
        }
        selectHtml.append("</select>");
        return selectHtml.toString();
    }

    private String createCodeMasterJson(Map<String,String> codeMasterMap){
        String jsonString = JSON.toJSONString(codeMasterMap);
        StringBuffer jsonHtml = new StringBuffer();
        jsonHtml.append("<script>");
        jsonHtml.append(this.codeType.toUpperCase() + "_JSON = "+jsonString+";");
        jsonHtml.append("</script>");
        return jsonHtml.toString();
    }
    private String createCodeMasterLabel(Map<String,String> codeMasterMap){
        String codeName = codeMasterMap.get(this.codeValue);
        StringBuffer labelHtml = new StringBuffer();
        labelHtml.append("<label id='"+this.id+"' name='"+this.name+"' ");
        if(!CollectionUtils.isEmpty(this.attrMap)){
            for (Map.Entry<String,Object> entry : this.attrMap.entrySet()){
                labelHtml.append(" "+ entry.getKey() + " ='"+entry.getValue()+"' ");
            }
        }
        labelHtml.append(" >");
        if(StringUtil.isEmpty(codeName)){
            labelHtml.append("没有对应值");
        }else{
            labelHtml.append(codeName);
        }
        labelHtml.append("</label>");
        return labelHtml.toString();
    }

    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (attrMap == null) {
            attrMap = new HashMap<String, Object>();
        }
        attrMap.put(localName, value);
    }
    /**
     * 获取viewType
     *
     * @return viewType viewType
     */
    public String getViewType() {
        return viewType;
    }

    /**
     * 设置viewType
     *
     * @param viewType viewType
     */
    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    /**
     * 获取codeType
     *
     * @return codeType codeType
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * 设置codeType
     *
     * @param codeType codeType
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    /**
     * 获取codeValue
     *
     * @return codeValue codeValue
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * 设置codeValue
     *
     * @param codeValue codeValue
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    /**
     * 获取id
     *
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取name
     *
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
}
