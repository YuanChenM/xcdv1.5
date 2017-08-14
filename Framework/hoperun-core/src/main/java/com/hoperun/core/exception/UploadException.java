package com.hoperun.core.exception;

import com.hoperun.core.bean.ExceptionMessage;

import java.util.List;


public class UploadException extends SystemException {

	private static final long serialVersionUID = 1L;
	public List<ExceptionMessage> getValidatorMessages(){
		return null;
	}
}
