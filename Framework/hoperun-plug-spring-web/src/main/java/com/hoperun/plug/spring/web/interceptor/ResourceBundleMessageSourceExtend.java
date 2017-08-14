/**
 * @screen core
 * @author ma_b
 */
package com.hoperun.plug.spring.web.interceptor;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * The message resource bundle class.
 * 
 * @author ma_b
 * 
 */
public class ResourceBundleMessageSourceExtend extends ResourceBundleMessageSource {

    private String[] extendBaseNames;

    /**
     * 
     * <p>
     * Set Message properties file base names.
     * </p>
     * 
     * @param basenames
     *        message file array
     * @see ResourceBundleMessageSource#setBasenames(String[])
     */
    @Override
    public void setBasenames(String... basenames) {
        String tempBaseName = basenames[0].toString();
        int length = tempBaseName.split(",").length;
        extendBaseNames = new String[length];

        for (int i = 0; i < length; i++) {
            extendBaseNames[i] = tempBaseName.split(",")[i];
        }

        super.setBasenames(extendBaseNames);
    }

    /**
     * Create a MessageFormat for the given message and Locale.
     * 
     * @param msg
     *        the message to create a MessageFormat for
     * @param locale
     *        the Locale to create a MessageFormat for
     * @return the MessageFormat instance
     */
    @Override
    protected MessageFormat createMessageFormat(String msg, Locale locale) {
        String escapedMsg = msg;
        if (escapedMsg == null) {
            escapedMsg = "";
        }
        escapedMsg = escapedMsg.replaceAll("'", "''");
        return new MessageFormat(escapedMsg, locale);
    }

    /**
     * 获取键值集合
     * 
     * @param basename 基础名
     * @param locale 地区
     * @return 键值集合
     */
    public Set<String> getKeys(String basename, Locale locale) {
        ResourceBundle bundle = getResourceBundle(basename, locale);
        if (bundle == null) {
            return new HashSet<String>();
        } else {
            return bundle.keySet();
        }
    }
}
