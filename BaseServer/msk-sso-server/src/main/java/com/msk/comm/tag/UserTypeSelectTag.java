package com.msk.comm.tag;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.alibaba.fastjson.TypeReference;
import com.msk.comm.bean.ConfigParam;
import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.utils.RestClientUtil;
import org.springframework.util.CollectionUtils;

import com.msk.comm.bean.UserTypeResult;
import com.msk.comm.utils.ConfigInfo;

public class UserTypeSelectTag  extends SimpleTagSupport implements DynamicAttributes {
    private Map<String,Object> attrMap;
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = this.getJspContext().getOut();
        StringBuffer userTypeSelectHtml = new StringBuffer();
        userTypeSelectHtml.append("<select id=\"userType\" name=\"userType\">");
        List<UserTypeResult> userTypeList = this.createUserTypeList();
        Collections.sort(userTypeList, new Comparator<UserTypeResult>() {
            @Override
            public int compare(UserTypeResult o1, UserTypeResult o2) {
                return o1.getUserType().compareTo(o2.getUserType());
            }
        });

        if(!CollectionUtils.isEmpty(userTypeList)){
            for(UserTypeResult userType : userTypeList){
                userTypeSelectHtml.append("<option value=\""+userType.getUserType()+"\">"+userType.getUserTypeName()+"</option>");
            }
        }
        userTypeSelectHtml.append("</select>");
        out.write(userTypeSelectHtml.toString());
    }

    private List<UserTypeResult> createUserTypeList(){
        List<UserTypeResult> userTypeList = new ArrayList<>();
        ConfigInfo configInfo = ConfigInfo.newConfigInfo();
        ConfigParam param = new ConfigParam();
        param.setKey("LoginUserType");
        RestRequest<ConfigParam> requestParam = new RestRequest<>();
        requestParam.setParam(param);
        String url = configInfo.getConfigServerUrl() + configInfo.getConfigLoadCodeMaster();
        TypeReference<RestResponse<HashMap<String,String>>> typeReference = new TypeReference<RestResponse<HashMap<String,String>>>(){};
        RestResponse<HashMap<String,String>> responseBody = RestClientUtil.post(url,requestParam,typeReference);
        HashMap<String,String> result = responseBody.getResult();
        for (Map.Entry<String,String> entry : result.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            UserTypeResult userType = new UserTypeResult(key,value);
            userTypeList.add(userType);
        }
        return userTypeList;
    }

    protected HttpServletRequest getRequest() {
        PageContext pageContext = (PageContext) this.getJspContext();
        return (HttpServletRequest) pageContext.getRequest();
    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (attrMap == null) {
            attrMap = new HashMap<String, Object>();
        }
        attrMap.put(localName, value);
    }
}
