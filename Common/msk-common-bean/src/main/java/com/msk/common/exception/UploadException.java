package com.msk.common.exception;


import java.util.List;

import com.msk.common.bean.ExceptionMessage;

public class UploadException extends SystemException {

	private static final long serialVersionUID = 1L;
	public List<ExceptionMessage> getValidatorMessages(){
		return null;
	}
}
