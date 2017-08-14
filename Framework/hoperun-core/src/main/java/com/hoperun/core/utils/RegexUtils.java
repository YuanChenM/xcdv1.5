package com.hoperun.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex utils class.
 */
public final class RegexUtils {

    /**
     * Split string by regex.
     * 
     * @param regex the regex
     * @param value the value
     * @return the split string value
     */
    public static List<String> splitStrings(String regex, String value) {
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(value);
        if (!checkRegex(mat)) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= mat.groupCount(); i++) {
            list.add(mat.group(i));
        }
        return list;
    }

    /**
     * Check regex.
     * 
     * @param mat the matcher object
     * @return true:success,false:error
     */
    private static boolean checkRegex(Matcher mat) {
        return mat.find();
    }

    /**
     * Check regex.
     * 
     * @param regex the regex
     * @param value the value
     * @return true or false
     */
    public static boolean checkRegex(String regex, String value) {
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(value);
        return mat.find();
    }
}
