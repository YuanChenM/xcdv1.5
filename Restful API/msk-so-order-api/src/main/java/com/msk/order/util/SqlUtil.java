package com.msk.order.util;

import com.msk.common.exception.BusinessException;
import com.msk.common.constant.NumberConstant;
import com.msk.common.utils.StringUtil;
import com.msk.common.utils.XmlUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过sqlId  获取sql
 * Created by zhang_qiang1 on 2016/9/5.
 */
public class SqlUtil {


    public static Map<String, String> map = new HashMap<>();


    /**
     * 通过sqlIds  获取map
     * @param sqlIds
     * @return
     */
    public static Map<String, String> getSqlMapBySqlIds(List<String> sqlIds) {
        Map<String,String>resultMap=new HashMap<>();
        if(CollectionUtils.isEmpty(sqlIds)){
            throw  new BusinessException("sqlIds  为空！");
        }else {
            for(String sqlId :sqlIds){
                String sql=SqlUtil.getSqlByXml(sqlId);
                resultMap.put(sqlId,sql);
            }
        }
        return resultMap;
    }


    /**
     * @param sqlId
     * @return
     */
    public static String getSqlBySqlId(String sqlId) {
        String sql = map.get(sqlId);
        if (StringUtil.isEmpty(sql)) {
            sql = getSqlByXml(sqlId);
        }
        return sql;
    }

    /**
     * @param sqlId
     * @return
     */
    private static String getSqlByXml(String sqlId) {
        String xmlPath = getXmlPath(sqlId);
        String sql = null;
        InputStream in = null;
        try {
            in = SqlUtil.class.getResourceAsStream(xmlPath);
            Map<String, String> sqlMap = XmlUtils.createXml(in);
            sql = sqlMap.get(sqlId);
            map.putAll(sqlMap);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sql;
    }


    /**
     * sqlId : ISO151501.Search
     * 根据id 获取 指定xml 的sql
     *
     * @param sqlId
     * @return
     */
    private static String getXmlPath(String sqlId) {
        String xmlPath = "";
        if (StringUtil.isEmpty(sqlId)) {
            throw new BusinessException("sqlId  为空");
        } else {
            if (!sqlId.contains(".")) {
                throw new BusinessException("sqlId  不符合规则");
            } else {
                String[] strArray = sqlId.split("\\.");
                xmlPath = "/sql" + "/" + strArray[NumberConstant.IntDef.INT_ZERO] + ".xml";
            }

        }
        return xmlPath;
    }
}
