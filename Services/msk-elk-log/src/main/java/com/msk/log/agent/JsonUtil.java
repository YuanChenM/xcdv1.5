package com.msk.log.agent;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/** 
 * JsonUtil.
 *
 *
 */ 
public class JsonUtil {
	/** objectMapper */
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 对象转换成json格式
	 * 
	 * 
	 * @param obj
	 *            任意对象
	 * 
	 * @return String
	 */
	public static String objectToJson(Object obj) {
		StringWriter tmp_w = new StringWriter();
		try {
			objectMapper.configure(
					SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.setDateFormat(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss"));

			objectMapper.writeValue(tmp_w, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp_w.toString();
	}
	
	/**
	 * 取得转换mapper.
	 *
	 * @return objectMapper
	 */
	public static <T> T jsonToObject(String json, Class<T> cls) {
	    try {
            return objectMapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
}
