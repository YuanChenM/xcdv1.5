package com.msk.common.exception;

import com.hoperun.core.exception.SystemException;

/**
 * Created by jackjiang on 16/7/22.
 */
public class Restful404Exception extends SystemException{
    public Restful404Exception() {
    }

    public Restful404Exception(String message) {
        super(message);
    }

    public Restful404Exception(Throwable cause) {
        super(cause);
    }

    public Restful404Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
