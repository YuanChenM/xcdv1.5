package com.hoperun.plug.spring.web.utils;

import com.hoperun.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * MessageManager
 *
 * @author jiang_nan
 * @version 1.0
 */
@Component
public class MessageManager implements ApplicationContextAware {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(MessageManager.class);
    private static ApplicationContext context;

    /**
     * get Message
     *
     * @param key properties key
     * @return the message
     */
    public static String getMessage(String key) {
        return getMessage(key, null);
    }

    /**
     * get Message
     *
     * @param key properties key
     * @param obj the parameters
     * @return the message
     */
    public static String getMessage(String key, Object[] obj) {
        return getMessage(key, obj, getLocale());
    }

    /**
     * get Message
     *
     * @param key properties key
     * @param obj the parameters
     * @param locale Locale
     * @return the message
     */
    public static String getMessage(String key, Object[] obj, Locale locale) {
        String value = "";
        if (!StringUtil.isEmpty(key)) {
            if (context != null) {
                value = context.getMessage(key, obj, locale);
            }

            if (StringUtil.isEmpty(value)) {
                logger.warn("Resouce Code [{}] is not found.", key);
                value = key;
            }
        }
        return value;
    }

    /**
     * get the Locale info
     *
     * @return the Locale info
     */
    public static Locale getLocale() {
        return getIELanguage();
    }

    /**
     *
     * <p>
     * Get the language from IE Browser.
     * </p>
     *
     * @return the IE Browser's language
     */
    @Deprecated
    public static Locale getIELanguage() {

        return getHttpServletRequest().getLocale();
    }

    /**
     * <p>
     * get the request.
     * </p>
     *
     * @return the request
     */
    @Deprecated
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        } else {
            return null;
        }
    }

    /**
     * <p>
     * Get Message without html break.
     * </p>
     *
     * @param key key
     * @return message that replace break with space.
     */
    public static String getMessageWithoutHtmlBreak(String key) {
        String retValue = getMessage(key);
        return retValue.replaceAll("(?i)<br[/]{0,1}>", " ");
    }

    /**
     * @param arg0 ApplicationContext
     * @throws BeansException BeansException
     */
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        context = arg0;
    }

}
