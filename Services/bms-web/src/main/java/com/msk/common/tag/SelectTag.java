package com.msk.common.tag;

import java.util.Map;

/**
 * Created by jackjiang on 16/6/20.
 */
public abstract class SelectTag {
    public String createSelectHtml(Map<String,Object> attrMap,String id,String name){
        StringBuffer selectHtml = new StringBuffer();
        selectHtml.append("<select name='"+name+"' id='"+id+"' ");
        selectHtml.append(">");
    /*    selectHtml.append("<option value=\"\">--请选择--</option>");*/
        if(attrMap!=null){
            for (Map.Entry<String,Object> entry:attrMap.entrySet()) {
                selectHtml.append(" "+entry.getKey()+"='"+entry.getValue()+"' ");
            }
        }
        selectHtml.append(this.createSelectOption());
        selectHtml.append("</select>");
        return selectHtml.toString();
    }
    public abstract String createSelectOption();
}
