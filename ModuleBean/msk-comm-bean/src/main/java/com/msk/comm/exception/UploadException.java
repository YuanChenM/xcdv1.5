package com.msk.comm.exception;


import com.msk.comm.bean.ExceptionMessage;

import java.util.List;

public class UploadException extends SystemException {

	private static final long serialVersionUID = 1L;
	public List<ExceptionMessage> getValidatorMessages(){
		return null;
	}
}
