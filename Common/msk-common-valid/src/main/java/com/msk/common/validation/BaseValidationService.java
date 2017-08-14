package com.msk.common.validation;

import java.lang.annotation.Annotation;

public interface BaseValidationService {

    String getValidationMessage(Annotation annotation, Object value);

}
