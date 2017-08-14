/**
 * @screen core
 * @author ma_b
 */
package com.msk.common.utils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The common utils for string.
 * 
 */
public final class StringUtil {

    /** _100 */
    private static final int PERC_100 = 100;

    private static final int ORACLE_SQL_IN_MAX_LENGTH = 1000;

    /** NUM_FORMAT */
    private static final DecimalFormat NUM_FORMAT = new DecimalFormat("#,##0");

    /** QTY_FORMAT */
    private static final DecimalFormat QTY_FORMAT = new DecimalFormat("#,##0.000000");

    /** WEIGHT_FORMAT */
    private static final DecimalFormat WEIGHT_FORMAT = new DecimalFormat("#,##0.000");

    /** LENTH_FORMAT */
    private static final DecimalFormat LENGTH_FORMAT = new DecimalFormat("#,##0.00");

    /** DECIMAL0_FORMAT */
    private static final DecimalFormat DECIMAL0_FORMAT = new DecimalFormat("#,##0");

    /** DECIMAL1_FORMAT */
    private static final DecimalFormat DECIMAL1_FORMAT = new DecimalFormat("#,##0.0");

    /** DECIMAL2_FORMAT */
    private static final DecimalFormat DECIMAL2_FORMAT = new DecimalFormat("#,##0.00");

    /** DECIMAL3_FORMAT */
    private static final DecimalFormat DECIMAL3_FORMAT = new DecimalFormat("#,##0.000");

    /** DECIMAL4_FORMAT */
    private static final DecimalFormat DECIMAL4_FORMAT = new DecimalFormat("#,##0.0000");

    /** DECIMAL5_FORMAT */
    private static final DecimalFormat DECIMAL5_FORMAT = new DecimalFormat("#,##0.00000");

    /** DECIMAL6_FORMAT */
    private static final DecimalFormat DECIMAL6_FORMAT = new DecimalFormat("#,##0.000000");

    /**
     * Check empty string
     * 
     * <pre>
     * null: true
     * "": true
     * " ":true
     * </>
     * 
     * @param value a string value
     * @return true is a empty string
     */
    public static boolean isEmpty(String value) {
        boolean emptyFlg = false;
        if (null == value || value.trim().length() <= 0) {
            emptyFlg = true;
        }
        return emptyFlg;
    }

    /**
     * Check numeric string. If the string is numeric then return true.
     * 
     * @param value the string will be checked
     * @return true is numeric
     */
    public static boolean isNumeric(String value) {
        boolean isNumeric = false;

        String strValue = value;
        if (!isEmpty(strValue)) {
            if (strValue.startsWith("+") || strValue.startsWith("-")) {
                strValue = strValue.substring(1);
            }

            for (int i = strValue.length(); --i >= 0;) {
                if (!Character.isDigit(strValue.charAt(i))) {
                    return false;
                }
            }
            isNumeric = true;
        }

        return isNumeric;
    }


    /**
     * 
     * <p>
     * Convert parameter to String. When parameter is null, return null.
     * </p>
     * 
     * @param param any object value
     * @return a string value (null is possible)
     * 
     */
    public static String toString(Object param) {
        String value = null;

        if (param != null) {
            value = param.toString();
        }

        return value;
    }

    /**
     * 
     * <p>
     * Convert parameter to String. When parameter is null, return "".
     * </p>
     * 
     * @param param any object value
     * @return a safe string value (null is impossible)
     * 
     */
    public static String toSafeString(Object param) {
        String value = "";

        if (param != null) {
            value = param.toString();
        }

        return value;
    }

    /**
     * 
     * <p>
     * Format amount base on decimal point of currency.
     * </p>
     * 
     * 
     * @param decimalPoint Integer
     * @param amount BigDecimal
     * @return amount
     */
    public static BigDecimal formatAmount(Integer decimalPoint, BigDecimal amount) {
        return amount.setScale(decimalPoint, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Format number as #,###
     * 
     * @param num a number
     * @return formated number string
     */
    public static String formatNum(Object num) {
        if (num == null) {
            return "";
        }
        return NUM_FORMAT.format(num);
    }
    /**
     * 
     * <p>
     * BigDecimal format to '#,###.00'
     * </p>
     * 
     * @param decimalLen decimalLen
     * @param val val
     * @return String
     */
    public static String formatCurrency(int decimalLen, BigDecimal val) {
        StringBuilder deciaml = new StringBuilder();

        if (decimalLen > 0) {
            deciaml.append(".");
            for (int i = 0; i < decimalLen; i++) {
                deciaml.append("0");
            }
        }
        DecimalFormat fm = new DecimalFormat("#,##0" + deciaml.toString());
        fm.setRoundingMode(RoundingMode.HALF_UP);
        return fm.format(val.divide(new BigDecimal("1"), decimalLen, RoundingMode.HALF_UP));
    }





    /**
     * escape comma
     * 
     * 
     * @param original the original string
     * @return the escaped string
     * 
     */
    public static String escapeComma(String original) {
        if (original == null) {
            return null;
        }
        return original.replaceAll(",", "\\\\,");
    }

    /**
     * unescape comma
     * 
     * 
     * @param original the original string
     * @return the unescaped string
     * 
     */
    public static String unescapeComma(String original) {
        if (original == null) {
            return null;
        }
        return original.replaceAll("\\\\,", ",");
    }

    /**
     * 
     * replace \r \n \0 to space
     * 
     * @param original original str
     * @return String
     * 
     */
    public static String replaceReturn(String original) {
        if (original == null) {
            return "";
        }
        String out = "";

        out = original.replace('\n', ' ').replace('\r', ' ').replace('\0', ' ');
        return out;
    }

    /**
     * 
     * replace lower char to upper char
     * 
     * @param original the original string
     * @return String the upper string
     * 
     */
    public static String toUpper(String original) {
        if (isEmpty(original)) {
            return original;
        }

        return original.toUpperCase();
    }

    /**
     * Trim right space.
     * 
     * @param original original string
     * @return the trimed string
     * 
     */
    public static String rightTrim(String original) {
        if (!isEmpty(original)) {
            String regex = "(.*\\S+)(\\s+$)";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(original);
            if (m.matches()) {
                return m.group(1);
            }
            return original;
        } else {
            return "";
        }
    }

    /**
     * The create transaction history order no.<br/>
     * The orderNo=no+"/"+suNo.
     * 
     * @param no the poNo or the coNo
     * @param suNo the poSubNo or the coSubNo
     * @return the orderNo
     * 
     */
    public static String createOrderNo(String no, String suNo) {
        StringBuffer orderNo = new StringBuffer();
        orderNo.append(no);
        if (!isEmpty(suNo)) {
            orderNo.append("/");
            orderNo.append(suNo);
        }
        return orderNo.toString();
    }

    /**
     * Get a sub array.
     * 
     * @param arrayType Class of array element
     * @param originalArray Original array
     * @param start start index
     * @param length length of sub array
     * @param <CT> Type of array element
     * @return sub array
     */
    @SuppressWarnings("unchecked")
    private static <CT> CT[] getSubArray(Class<CT> arrayType, CT[] originalArray, int start, int length) {
        CT[] resultArray = (CT[]) Array.newInstance(arrayType, length);
        for (int index = 0; index < length; index++) {
            resultArray[index] = originalArray[start + index];
        }
        return resultArray;
    }

    /**
     * Split too big array into multiple for the limitation of oracle, it doesn't allow more than 1000 items in IN
     * clause.
     * 
     * @param arrayType Class of array element
     * @param originalArray Original array
     * @param <CT> Type of array element
     * @return A list contains split result
     * 
     */
    public static <CT extends Comparable<? super CT>> List<CT[]> splitArrayForOracle(Class<CT> arrayType,
        CT[] originalArray) {
        List<CT[]> subColumnsList = new ArrayList<CT[]>();
        CT[] squeezedArray = squeezeArray(arrayType, originalArray);
        Arrays.sort(squeezedArray);
        int index = 0;
        while (index < squeezedArray.length) {
            int length = squeezedArray.length - index;
            if (length > ORACLE_SQL_IN_MAX_LENGTH) {
                length = ORACLE_SQL_IN_MAX_LENGTH;
            }
            CT[] subArray = getSubArray(arrayType, squeezedArray, index, length);
            subColumnsList.add(subArray);
            index += length;
        }
        return subColumnsList;
    }

    /**
     * 
     * Split too big list into multiple for the limitation of oracle, it doesn't allow more than 1000 items in IN
     * clause.
     * 
     * @param listItemType Class of list element
     * @param originalList Original list
     * @param <CT> Type of list element
     * @return A list contains split result
     * 
     */
    public static <CT extends Comparable<? super CT>> List<List<CT>> splitListForOracle(Class<CT> listItemType,
        List<CT> originalList) {
        List<List<CT>> subColumnsList = new ArrayList<List<CT>>();
        List<CT> squeezedList = squeezeList(listItemType, originalList);
        Collections.sort(squeezedList);
        int index = 0;
        while (index < squeezedList.size()) {
            int length = squeezedList.size() - index;
            if (length > ORACLE_SQL_IN_MAX_LENGTH) {
                length = ORACLE_SQL_IN_MAX_LENGTH;
            }
            List<CT> subArray = squeezedList.subList(index, index + length);
            subColumnsList.add(subArray);
            index += length;
        }
        return subColumnsList;
    }

    /**
     * Remove duplicate items in List.
     * 
     * @param listType item type of element
     * @param originalList original list before squeeze
     * @param <CT> item type of element
     * @return List without duplicate item
     */
    private static <CT extends Comparable<? super CT>> List<CT> squeezeList(Class<CT> listType, List<CT> originalList) {
        Set<CT> tmpSet = new HashSet<CT>(originalList.size());
        for (CT ct : originalList) {
            tmpSet.add(ct);
        }
        List<CT> result = new ArrayList<CT>(tmpSet.size());
        result.addAll(tmpSet);
        return result;
    }

    /**
     * Remove duplicate items in array.
     * 
     * @param arrayType item type of element
     * @param originalArray original array before squeeze
     * @param <CT> item type of element
     * @return Array without duplicate item
     */
    @SuppressWarnings("unchecked")
    private static <CT extends Comparable<? super CT>> CT[] squeezeArray(Class<CT> arrayType, CT[] originalArray) {
        Set<CT> tmpSet = new HashSet<CT>(originalArray.length);
        for (CT ct : originalArray) {
            tmpSet.add(ct);
        }
        CT[] resultArray = (CT[]) Array.newInstance(arrayType, tmpSet.size());
        return tmpSet.toArray(resultArray);
    }







    /**
     * Indicates whether the specified String object is <b>null</b> or an Empty string.
     * 
     * @param str the string to check
     * @return <b>true</b> if the parameter is <b>null</b> or an empty string (""); otherwise, <b>false</b>.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * Transfer the <b>null</b> to an empty string ("").
     * 
     * @param str the specified String
     * @return empty string if the parameter is <b>null</b>; otherwise, the original string.
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * Transfer the empty string to an <b>null<b>.
     * 
     * @param str the specified String
     * @return <b>null</b> if the parameter is empty string; otherwise, the original string.
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }

    /**
     * Change String to Integer.
     * 
     * @param str String
     * @return Integer
     */
    public static Integer toInteger(String str) {

        Integer nResult = null;
        if (!isNullOrEmpty(str)) {

            try {
                nResult = Integer.valueOf(str);
            } catch (NumberFormatException e) {
                nResult = null;
            }
        }

        return nResult;
    }

    /**
     * Change String to BigDecimal.
     * 
     * @param value String
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(Object value) {
        BigDecimal result = null;
        try {
            result = new BigDecimal(value.toString());
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * Change Object to Integer.
     * 
     * @param str Object
     * @return Integer
     */
    public static Integer toInteger(Object str) {
        Integer nResult = null;
        if (str != null) {
            try {
                nResult = Integer.valueOf(str.toString());
            } catch (NumberFormatException e) {
                nResult = null;
            }
        }

        return nResult;
    }
    /**
     * Change Object to Long.
     *
     * @param str Object
     * @return Long
     */
    public static Long toLong(Object str) {
        Long nResult = null;
        if (str != null) {

            try {
                nResult = Long.valueOf(str.toString());
            } catch (NumberFormatException e) {
                nResult = null;
            }
        }
        return nResult;
    }

    /**
     * Return a copy of the string, with leading and trailing whitespace omitted.
     * 
     * @param str the specified String
     * @return result
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
    /**
     * Check string by regex.
     * 
     * @param str input string
     * @param regex the check regex
     * @return true is matched
     */
    public static boolean matchs(String str, String regex) {
        return Pattern.matches(regex, str);
    }

    /**
     * <p>
     * Checks if String contains a search String, handling <code>null</code>. This method uses
     * {@link String#indexOf(String)}.
     * </p>
     *
     * <p>
     * A <code>null</code> String will return <code>false</code>.
     * </p>
     *
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @param searchStr the String to find, may be null
     * @return true if the String contains the search String,
     *         false if not or <code>null</code> string input
     * @since 2.0
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    /**
     * Compares this string to the specified string.
     * If string is null, then convert to empty to compare.
     * 
     * @param str1 compare string 1
     * @param str2 compare string 2
     * @return true is same
     */
    public static boolean equals(String str1, String str2) {
        return toSafeString(str1).equals(toSafeString(str2));
    }

    /**
     * convert string to double.
     *
     * @param value double value
     * @return string
     */
    public static double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.00D;
        }
    }
}