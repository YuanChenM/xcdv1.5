package com.msk.common.bean;

import com.msk.common.base.BaseBean;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 返回给服务器端的消息封装，包含了
 * <p>
 * Success：是否调用是否成功
 * <p>
 * Message：显示的消息
 * <p>
 * content：返回的参数
 * 
 * <p>
 * {
 * <p>
 * "success": true,
 * <p>
 * "data":
 * "{\"id\":\"Id\",\"name\":\"Name\",\"email\":\"email@test.com\",\"address\":\"nanjing\"}"
 * ,
 * <p>
 * 
 * @author yewenchen
 * @version 1.0
 */
@XmlRootElement(name = "ResponseMessage")
public class ResponseMessage implements Serializable {

	private static final long serialVersionUID = 677484458789332877L;
	//
	private boolean success = false;
	private String errorMessage = "";
	private Object data = new String();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setData(String data) {
		if (data != null) {
			this.data = data;
		}
	}

	public void setData() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		this.data = jsonMap;
	}

	public void setData(BaseBean data) {
		if (data != null)
		{
			this.data = data;
		}
	}

	public void setData(Object data) {
		if (data != null) {
			this.data = data;
		}
	}

	public Object getData() {
		return data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}