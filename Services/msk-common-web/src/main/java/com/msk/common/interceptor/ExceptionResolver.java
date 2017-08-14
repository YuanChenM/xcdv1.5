package com.msk.common.interceptor;

import com.hoperun.core.bean.ExceptionMessage;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.exception.UploadException;
import com.hoperun.core.exception.ValidatorException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.web.utils.MessageManager;
import com.msk.common.aspect.RsLogAspect;
import com.msk.common.bean.RsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Web端异常处理类
 * 
 * @author ma_b
 * 
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {
    /** suffix -- .json */
    private final static String JSON_EXT = "json";
    /** suffix -- .script */
    private final static String SCRIPT_EXT = "script";
    /** error view -- .api */
    private final static String ERR_VIEW_API = "api_error.json";
    /** content type:json */
    private final static String CONTENT_TYPE_JSON = "application/json";
    /** content type:xml */
    private final static String CONTENT_TYPE_XML = "application/xml";
    /** system error message code */
    private final static String ERROR_CODE_SYS = "error.system.error";

    /** status error */
    private final static int STATUS_ERR = 300;

    private Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
    private Logger loggerRs = LoggerFactory.getLogger(RsLogAspect.class);

    private Map<String, Integer> errorCodes = new HashMap<String, Integer>();

    private Integer defaultErrorCode;

    /**
     * XML Converter that is defined in spring-mvc-web.xml
     */
    @Autowired
    @Qualifier("xmlConverter")
    MappingJackson2XmlHttpMessageConverter xmlConvert;

    /**
     * XML Converter that is defined in spring-mvc-web.xml
     */
    @Autowired
    @Qualifier("jsonConverter")
    MappingJackson2HttpMessageConverter jsonConvert;

    /**
     * 
     * <p>
     * The response status.
     * </p>
     * 
     */
    static class JsonResponse {
        /** error message array */
        public String[] message;
        /** error code */
        public String errorCode;
        /** status code */
        public int statusCode;

        /**
         * <p>
         * The Constructors Method.
         * </p>
         * 
         * @param message error message
         * @param errorCode error code
         * 
         */
        public JsonResponse(String[] message, String errorCode) {
            super();
            // error message array
            this.message = message;
            // error code
            this.errorCode = errorCode;
            // status code
            this.statusCode = STATUS_ERR;
        }
    }

    /**
     * Actually resolve the given exception that got thrown during on handler execution, returning a ModelAndView that
     * represents a specific error page if appropriate.
     * <p>
     * May be overridden in subclasses, in order to apply specific exception checks. Note that this template method will
     * be invoked <i>after</i> checking whether this resolved applies ("mappedHandlers" etc), so an implementation may
     * simply proceed with its actual exception handling.
     * 
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler, or <code>null</code> if none chosen at the time of the exception (for
     *        example, if multipart resolution failed)
     * @param ex the exception that got thrown during handler execution
     * @return a corresponding ModelAndView to forward to, or <code>null</code> for default processing
     * @see SimpleMappingExceptionResolver#doResolveException(HttpServletRequest,
     *      HttpServletResponse, Object, Exception)
     *
     */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {

        Exception error = ex;
        String viewName = null;
        if (!"org.apache.catalina.connector.ClientAbortException".equals(error.getClass().getName())) {

            String url = request.getServletPath();
            if (!StringUtil.isEmpty(url) && url.startsWith("/api")) {
                viewName = "error/" + ERR_VIEW_API;
            } else {
                // Expose ModelAndView for chosen error jsp.
                viewName = determineViewName(error, request);
            }
        }
        if (error instanceof java.net.SocketTimeoutException) {
            // viewName = null;
            logger.error("WEB AP链接超时");
        }
        if (error instanceof javax.xml.ws.WebServiceException) {
            // viewName = null;
            logger.error("WEB AP 链接超时：Could not send Message");
        }
        if (viewName != null) {
            // Apply HTTP status code for error views, if specified.
            // Only apply it if we're processing a top-level request.
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            }

            return getModelAndView(viewName, error, request, response);
        } else {
            return null;
        }
    }

    /**
     * Log the given exception at warn level, provided that warn logging has been activated through the
     * {@link #setWarnLogCategory "warnLogCategory"} property.
     * <p>
     * Calls {@link #buildLogMessage} in order to determine the concrete message to log. Always passes the full
     * exception to the logger.
     *
     * @param ex the exception that got thrown during handler execution
     * @param request current HTTP request (useful for obtaining metadata)
     * @see #setWarnLogCategory
     * @see #buildLogMessage
     * @see org.apache.commons.logging.Log#warn(Object, Throwable)
     * @see org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver#logException(Exception,
     *      HttpServletRequest)
     * 
     */
    @Override
    protected void logException(Exception ex, HttpServletRequest request) {
        if (logger != null && (logger.isErrorEnabled() || logger.isWarnEnabled())) {
            String message = StringUtil.join(buildLogMessages(ex), StringConst.SEMICOLON);
            if (ex instanceof BusinessException) {
                logger.warn(message);
            } else if (ex instanceof IllegalStateException) {
                logger.error(message);
            } else {
                if ("org.apache.catalina.connector.ClientAbortException".equals(ex.getClass().getName())) {
                    logger.warn(message);
                } else {
                    logger.error(message, ex);
                }
            }
        }
    }

    /**
     * 
     * <p>
     * Get the cause of this exception. if it is a validation exception, then return the base casue. if it is not a
     * validation exception, then return itself. Return true when this exception is caused by a validation.
     * </p>
     * 
     * @param ex the exception
     * @return true when cause by a validation.
     * 
     */
    private static Exception getCauseOfException(Exception ex) {

        // if the exception is a validation exception
        // if (causeBy instanceof org.springframework.transaction.TransactionSystemException) {
        Throwable causeEx = ex;
        while (causeEx != null) {
            if (causeEx instanceof BusinessException) {
                // validation exception
                return (BusinessException) causeEx;
            } else {
                causeEx = causeEx.getCause();
            }
        }
        // }

        return ex;
    }

    /**
     * 
     * <p>
     * Return a ModelAndView for the given request, jsp name and exception.
     * </p>
     * 
     * @param viewName the name of the error jsp
     * @param ex the exception that got thrown during handler execution
     * @param request current HTTP request (useful for obtaining metadata)
     * @param response current HTTP response (useful for transform jsondata)
     * @return the ModelAndView instance
     * 
     */
    protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request,
        HttpServletResponse response) {
        ModelAndView modelView = super.getModelAndView(viewName, ex, request);

        String filename = WebUtils.extractFullFilenameFromUrlPath(viewName);
        String ext = StringUtils.getFilenameExtension(filename);

        if (ERR_VIEW_API.equalsIgnoreCase(filename)) {
            // web service error
            try {
                String errorCode = "";
                String errorMsg;
                if (ex instanceof BusinessException) {
                    errorCode = ((BusinessException) ex).getErrorCode();
                    if (((BusinessException) ex).getExceptionMessages() != null
                            && !((BusinessException) ex).getExceptionMessages().isEmpty()) {
                        errorMsg = StringUtil.join(buildLogMessages(ex), StringConst.COMMA);
                    } else {
                        errorMsg = ex.getMessage();
                    }
                } else {
                    errorCode = "F000000";
                    errorMsg = StringUtil.join(buildLogMessages(ex), StringConst.COMMA);
                }

                // web service
                RsResponse<?> rs = new RsResponse<Object>();
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setReturnCode(errorCode);
                rs.setMessage(errorMsg);

                String result;
                if (CONTENT_TYPE_XML.equalsIgnoreCase(request.getContentType())) {
                    result = xmlConvert.getObjectMapper().writeValueAsString(rs);
                    // 设置ContentType
                    response.setContentType(CONTENT_TYPE_XML);
                } else {
                    result = jsonConvert.getObjectMapper().writeValueAsString(rs);
                    // 设置ContentType
                    response.setContentType(CONTENT_TYPE_JSON);
                }
                if (!StringUtil.isEmpty(result)) {
                    try {
                        PrintWriter writer = response.getWriter();
                        writer.write(result);
                        writer.flush();
                        loggerRs.trace("\n[RsResponse]:{}", result);
                        return new ModelAndView();
                    } catch (Exception e) {
                        logger.error("Exception process fail.", e);
                    }
                }
            } catch (Exception e) {
                logger.error("Exception process fail.", e);
            }
        } else if (JSON_EXT.equalsIgnoreCase(ext)) {
            try {
                String errorCode;
                String errorMsg;
                if (ex instanceof BusinessException) {
                    errorCode = ((BusinessException) ex).getErrorCode();
                    if (((BusinessException) ex).getExceptionMessages() != null
                            && !((BusinessException) ex).getExceptionMessages().isEmpty()) {
                        errorMsg = StringUtil.join(buildLogMessages(ex), StringConst.COMMA);
                    } else {
                        errorMsg = MessageManager.getMessage(errorCode, new String[] { ex.getMessage() });
                    }
                } else {
                    errorCode = ERROR_CODE_SYS;
                    errorMsg = MessageManager.getMessage(errorCode, new String[] { ex.getMessage() });
                }

                // web service
                JsonResponse rs = new JsonResponse(new String[] { errorMsg }, errorCode);
                String json = jsonConvert.getObjectMapper().writeValueAsString(rs);
                // 设置ContentType
                response.setContentType(CONTENT_TYPE_JSON);
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(json);
                    writer.flush();
                    return new ModelAndView();
                } catch (Exception e) {
                    logger.error("Exception process fail.", e);
                }
            } catch (Exception e) {
                logger.error("Exception process fail.", e);
            }
        } else if (SCRIPT_EXT.equalsIgnoreCase(ext)) {
            ;// TODO
             // if (ex instanceof DownloadFailException) {
             // modelView.addObject("script", ((DownloadFailException) ex).getCallback());
             // }
        } else {
            modelView.addObject("messages", buildLogMessages(ex));
        }
        if ("uploadCommon".equals(request.getParameter("actionType"))) {
            modelView.setViewName("error/uploadError");
        }
        return modelView;
    }

    /**
     * 
     * <p>
     * Determine the error code to apply for the given exception.
     * </p>
     * 
     * @param request request current HTTP request (useful for obtaining metadata)
     * @param ex the exception that got thrown during handler execution
     * @return error code
     * 
     */
    protected Integer determineErrorCode(HttpServletRequest request, Exception ex) {
        String exName = ex.getClass().getName();
        if (this.errorCodes.containsKey(exName)) {
            return this.errorCodes.get(exName);
        }
        return this.defaultErrorCode;
    }

    /**
     * Build a log message array for the given exception, occured during processing the given request.
     * 
     * @param ex the exception that got thrown during handler execution
     * @return the log message to use
     * 
     */
    public static String[] buildLogMessages(Exception ex) {
        String[] message = null;

        if (ex instanceof ValidatorException || ex instanceof UploadException) {
            // 校验异常处理
            List<ExceptionMessage> vmsgs = null;
            if (ex instanceof ValidatorException) {
                vmsgs = ((ValidatorException) ex).getValidatorMessages();
            } else {
                vmsgs = ((UploadException) ex).getValidatorMessages();
            }
            if (vmsgs != null && vmsgs.size() > 0) {
                // 做国际化处理
                message = new String[vmsgs.size()];
                int i = 0;
                for (ExceptionMessage vmsg : vmsgs) {
                    String msgCode = vmsg.getMessage(); // 消息
                    String[] msgParams = vmsg.getMessageParamArray(); // 消息参数
                    String msg = createMessage(msgParams, msgCode);

                    // 项目名处理
                    String fieldNameCode = vmsg.getFieldName();
                    if (!StringUtil.isEmpty(fieldNameCode)) {
                        // 项目名国际化转换
                        String fieldName = MessageManager.getMessage(fieldNameCode);
                        msg = fieldName + ":" + msg;
                    }

                    // 行号处理
                    int rowNum = vmsg.getRowNum();
                    if (rowNum > NumberConst.IntDef.INT_ZERO) {
                        String rowLabel = MessageManager.getMessage("validator.message.rowLabel",
                            new Object[] { rowNum });
                        msg = msg.indexOf(":") > -1 ? rowLabel + msg : rowLabel + ":" + msg;
                    }
                    message[i++] = msg;
                }
            }
        } else if (ex instanceof BusinessException) {
            // 业务异常处理
            List<ExceptionMessage> bmsgs = ((BusinessException) ex).getExceptionMessages();
            if (bmsgs != null && bmsgs.size() > 0) {
                // 自定义Message时，做国际化处理
                message = new String[bmsgs.size()];
                int i = 0;
                for (ExceptionMessage bmsg : bmsgs) {

                    // 消息key
                    String msgCode = bmsg.getMessage(); // 消息
                    String[] msgParams = bmsg.getMessageParamArray(); // 消息参数
                    String msg = createMessage(msgParams, msgCode);
                    message[i++] = msg;
                }
            } else {
                // 非自定义Message时，通常方式处理
                message = new String[1];
                message[0] = MessageManager.getMessage(ERROR_CODE_SYS, new String[] { ex.getMessage() });
            }
        } else if (ex instanceof MaxUploadSizeExceededException) {
            ; // Nothing to do
        } else {
            message = new String[1];
            message[0] = ex.getMessage() != null
                ? MessageManager.getMessage(ERROR_CODE_SYS, new String[] { ex.getMessage() })
                : MessageManager.getMessage(ERROR_CODE_SYS, new String[] { ex.getClass().getName() });
        }

        return message;
    }

    /**
     * create message by message code and parameters.
     * 
     * @param msgParams the message parameters
     * @param msgCode the message code
     * @return the message text
     */
    private static String createMessage(String[] msgParams, String msgCode) {
        String msg;
        if (msgParams != null && msgParams.length > 0) {
            // 有参数时
            String[] params = new String[msgParams.length];
            int j = 0;
            for (String paramCode : msgParams) {
                // 对参数进行国际化转换
                params[j++] = MessageManager.getMessage(paramCode);
            }
            // 对消息进行国际化转换
            msg = MessageManager.getMessage(msgCode, params);
        } else {
            // 对没有参数的消息进行国际化转换
            msg = MessageManager.getMessage(msgCode);
        }
        return msg;
    }

    /**
     * <pre>
     * Get a message array from the given exception. 
     * If this is a validation exception, more than one message is possible,
     * otherwise return one message.
     * </pre>
     * 
     * @param ex an exception
     * @return the error message array
     * 
     */
    public static String[] getExceptionMessages(Exception ex) {
        return buildLogMessages(getCauseOfException(ex));
    }

    /**
     * <p>
     * get the errorCodes.
     * </p>
     * 
     * @return errorCodes
     * 
     */
    public Map<String, Integer> getErrorCodes() {
        return this.errorCodes;
    }

    /**
     * <p>
     * set the errorCodes.
     * </p>
     * 
     * @param errorCodes errorCodes
     * 
     */
    public void setErrorCodes(Map<String, Integer> errorCodes) {
        this.errorCodes = errorCodes;
    }

    /**
     * <p>
     * get the defaultErrorCode.
     * </p>
     * 
     * @return defaultErrorCode
     * 
     */
    public Integer getDefaultErrorCode() {
        return this.defaultErrorCode;
    }

    /**
     * <p>
     * set the defaultErrorCode.
     * </p>
     * 
     * @param defaultErrorCode defaultErrorCode
     * 
     */
    public void setDefaultErrorCode(Integer defaultErrorCode) {
        this.defaultErrorCode = defaultErrorCode;
    }
}
