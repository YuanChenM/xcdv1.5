/**
 * @screen core
 * @author ma_b
 */
package com.hoperun.core.utils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;


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
     * pad the pad to a string's right until full length.
     * 
     * @param value a string value
     * @param length pad to length
     * @param pad the pad string
     * @return a string
     */
    public static String PadRight(String value, int length, String pad) {

        String tmpStr = value;
        if (tmpStr == null) {
            tmpStr = "";
        }

        String tmpPad = "";

        if (tmpStr.length() < length) {
            for (int i = 1; i <= length - tmpStr.length(); i++) {
                tmpPad = tmpPad.concat(pad);
            }
        }

        return tmpStr.concat(tmpPad);
    }

    /**
     * pad the pad to a string's left until full length.
     * 
     * @param value a string value
     * @param length pad to length
     * @param pad the pad string
     * @return a string
     */
    public static String PadLeft(String value, int length, String pad) {

        String tmpStr = value;
        if (tmpStr == null) {
            tmpStr = "";
        }

        String tmpPad = "";

        if (tmpStr.length() < length) {
            for (int i = 1; i <= length - tmpStr.length(); i++) {
                tmpPad = pad.concat(tmpPad);
            }
        }

        return tmpPad.concat(tmpStr);
    }

    /**
     * <p>
     * Escapes the characters in a <code>String</code> using HTML entities.
     * </p>
     * 
     * <p>
     * For example:
     * </p>
     * <p>
     * <code>"bread" & "butter"</code>
     * </p>
     * becomes:
     * <p>
     * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>.
     * </p>
     * 
     * <p>
     * Supports all known HTML 4.0 entities, including funky accents. Note that the commonly used apostrophe escape
     * character (&amp;apos;) is not a legal entity and so is not supported).
     * </p>
     * 
     * @param str the <code>String</code> to escape, may be null
     * @return a new escaped <code>String</code>, <code>null</code> if null string input
     * 
     * @see #unescapeHtml(String)
     * @see <a href="http://hotwired.lycos.com/webmonkey/reference/special_characters/">ISO Entities</a>
     * @see <a href="http://www.w3.org/TR/REC-html32#latin1">HTML 3.2 Character Entities for ISO Latin-1</a>
     * @see <a href="http://www.w3.org/TR/REC-html40/sgml/entities.html">HTML 4.0 Character entity references</a>
     * @see <a href="http://www.w3.org/TR/html401/charset.html#h-5.3">HTML 4.01 Character References</a>
     * @see <a href="http://www.w3.org/TR/html401/charset.html#code-position">HTML 4.01 Code positions</a>
     * 
     */
    public static String escapeHtml(String str) {
        return StringEscapeUtils.escapeHtml4(str);
    }

    /**
     * 
     * escapeHtmlWithSpace
     * 
     * @param str str
     * @return String
     * 
     */
    public static String escapeHtmlWithSpace(String str) {
        return StringEscapeUtils.escapeHtml4(str).replaceAll(" ", "&nbsp;");
    }

    /**
     * <p>
     * Escapes the characters in a <code>String</code> using JavaScript String rules.
     * </p>
     * <p>
     * Escapes any values it finds into their JavaScript String form. Deals correctly with quotes and control-chars
     * (tab, backslash, cr, ff, etc.)
     * </p>
     * 
     * <p>
     * So a tab becomes the characters <code>'\\'</code> and <code>'t'</code>.
     * </p>
     * 
     * <p>
     * The only difference between Java strings and JavaScript strings is that in JavaScript, a single quote must be
     * escaped.
     * </p>
     * 
     * <p>
     * Example:
     * 
     * <pre>
     * input string: He didn't say, "Stop!"
     * output string: He didn\'t say, \"Stop!\"
     * </pre>
     * 
     * </p>
     * 
     * @param str String to escape values in, may be null
     * @return String with escaped values, <code>null</code> if null string input
     * 
     */
    public static String escapeJavaScript(String str) {
        return StringEscapeUtils.escapeEcmaScript(str);
    }

    /**
     * <pre>
     * Creates a MessageFormat with the given pattern and uses it to format the given arguments. 
     * This is equivalent to
     * <blockquote>
     * <code>(new {@link #MessageFormat(String) MessageFormat}(pattern)).{@link #format(Object[], StringBuffer, FieldPosition) format}(arguments, new StringBuffer(), null).toString()</code>
     * </blockquote>
     * ex:StringUtil.formatMessage("id:{0} name:{1} count:{2} flag:{3}", 1, "test", BigDecimal.TEN, true));
     *    StringUtil.formatMessage("''{0}''", "test"); => 'test'
     *    StringUtil.formatMessage("'{'{0}'}'", "test"); => {test}
     * </pre>
     * 
     * @param pattern the message pattern
     * @param arguments the arguments for message pattern
     * @return the message with
     * 
     */
    public static String formatMessage(String pattern, Object... arguments) {
        String message = "";
        if (arguments instanceof Object[]) {
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] == null) {
                    arguments[i] = "";
                }
            }
        }
        try {
            message = MessageFormat.format(pattern.replaceAll("(?<!('|\\{|\\}))'(?!('|\\{|\\}))", "''"), arguments);
        } catch (Exception ex) {
            throw new RuntimeException(MessageFormat.format("Format message[{0}] is fail.", pattern), ex);
        }
        return message;
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
    public static String formatNum(BigDecimal num) {
        if (num == null) {
            return StringConst.EMPTY;
        }
        return NUM_FORMAT.format(num);
    }

    /**
     * Format number as #,###
     * 
     * @param num a number
     * @return formated number string
     */
    public static String formatNum(Integer num) {
        if (num == null) {
            return StringConst.EMPTY;
        }
        return NUM_FORMAT.format(num);
    }

    /**
     * Format quantity number as #,###.000000
     * 
     * @param qty a qty number
     * @return formated qty string
     */
    public static String formatQty(BigDecimal qty) {
        if (qty == null) {
            return StringConst.EMPTY;
        }
        return QTY_FORMAT.format(qty);
    }

    /**
     * Format weight number as #,###.000
     * 
     * @param weight a weight numuber
     * @return formated weight string
     */
    public static String formatWeight(BigDecimal weight) {
        if (weight == null) {
            return StringConst.EMPTY;
        }
        return WEIGHT_FORMAT.format(weight);
    }

    /**
     * Format length number as #,##0.00
     * 
     * @param length a length numuber
     * @return formated length string
     */
    public static String formatLength(BigDecimal length) {
        if (length == null) {
            return StringConst.EMPTY;
        }
        return LENGTH_FORMAT.format(length);
    }

    /**
     * Format a number
     * 
     * @param decimalDigit decimaldigit
     * @param number a number
     * @return string
     */
    public static String formatBigDecimal(int decimalDigit, BigDecimal number) {
        DecimalFormat df = null;
        if (number == null) {
            return StringConst.EMPTY;
        } else if (decimalDigit <= 0) {
            df = DECIMAL0_FORMAT;
        } else if (decimalDigit == NumberConst.IntDef.INT_ONE) {
            df = DECIMAL1_FORMAT;
        } else if (decimalDigit == NumberConst.IntDef.INT_TWO) {
            df = DECIMAL2_FORMAT;
        } else if (decimalDigit == NumberConst.IntDef.INT_THREE) {
            df = DECIMAL3_FORMAT;
        } else if (decimalDigit == NumberConst.IntDef.INT_FOUR) {
            df = DECIMAL4_FORMAT;
        } else if (decimalDigit == NumberConst.IntDef.INT_FIVE) {
            df = DECIMAL5_FORMAT;
        } else if (decimalDigit == NumberConst.IntDef.INT_SIX) {
            df = DECIMAL6_FORMAT;
        } else {
            df = DECIMAL6_FORMAT;
        }
        return df.format(number);
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
     * 
     * formatPercent
     * 
     * @param val val
     * @return String
     * 
     */
    public static String formatPercent(BigDecimal val) {
        BigDecimal value = val.multiply(new BigDecimal(PERC_100));
        Format fm = new DecimalFormat("#0.00");
        return fm.format(value) + StringConst.PRE;
    }

    /**
     * join the character in array to a string.
     * 
     * @param strs String[]
     * @param delimiter delimiter
     * @return String
     */
    public static String join(String[] strs, String delimiter) {
        StringBuffer strReturn = new StringBuffer("");
        if (strs != null) {
            for (String str : strs) {
                if (strReturn.length() > 0) {
                    strReturn.append(delimiter);
                }
                strReturn.append(str);
            }
        }

        return strReturn.toString();
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
            return StringConst.EMPTY;
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
     * Escape wildcard characters by escape character.
     * 
     * @param input the input parameter
     * @param escapeCharacters the escape character array
     * @param escapeCharacter the escape character
     * @return the string after escape wildcard characters
     */
    public static String escape(String input, String[] escapeCharacters, String escapeCharacter) {

        String ret = input;
        String escapeChar = escapeCharacter;
        if (escapeChar == null || escapeChar.length() != 1) {
            escapeChar = StringConst.EXCLAMATION;
        }
        if (input != null && escapeCharacters != null) {
            List<String> al = Arrays.asList(escapeCharacters);
            HashSet<String> set = new HashSet<String>();
            set.addAll(al);
            // resolve the escape character first
            if (set.contains(escapeChar)) {
                ret = ret.replaceAll(escapeChar, escapeChar + escapeChar);
                set.remove(escapeChar);
            }

            Iterator<String> iter = set.iterator();
            while (iter.hasNext()) {
                String cur = iter.next();
                if (input.contains(cur)) {
                    ret = ret.replaceAll("\\" + cur, escapeChar + cur);
                }

            }

        }
        // ' is a exceptional case
        if (ret != null && ret.contains("!'")) {
            ret = ret.replaceAll("!\\'", "'");
        }
        return ret;
    }

    /**
     * Escape.
     * 
     * @param input the input
     * @param escapeCharacters the escape characters
     * @param escapeCharacter the escape character
     * 
     * @return the string
     */
    public static String escape(String input, HashSet<String> escapeCharacters, String escapeCharacter) {
        if (input == null || escapeCharacters == null) {
            return input;
        } else {
            String escapeChar = escapeCharacter;
            if (escapeChar == null || escapeChar.length() != 1) {
                escapeChar = StringConst.EXCLAMATION;
            }
            return escape(input, escapeCharacters.toArray(new String[] {}), escapeChar);
        }
    }

    /**
     * Escape.
     * 
     * @param input the input
     * 
     * @return the string
     */
    public static String escape(String input) {
        final String[] escapeCharacters = { "_", "%", "'", "!" };
        final String escapeCharacter = "!";
        if (input == null) {
            return input;
        } else {
            return escape(input, escapeCharacters, escapeCharacter);
        }
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
     * Get toke content.
     * 
     * @return toke content
     */
    public static String getTokenContent() {
        final int randomNumLength = 32;
        final String token = RandomStringUtils.randomAlphanumeric(randomNumLength);
        return token;
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
     * 
     * Count length of string by byte.
     * 
     * 
     * @param str a string
     * @return string's byte length
     */
    public static int countLengthByByte(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return countLengthByByte(str, null);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * 
     * Count length of string by byte.
     * 
     * @param str a string
     * @param pstrEncode encode
     * @return string's byte length
     */
    public static int countLengthByByte(String str, String pstrEncode) {
        if (str == null) {
            return 0;
        }

        final char chrLow = 65377;
        final char chrHi = 65439;
        final char maxInt = 65535;
        final int interval = 2;

        int intByteLen = 0;
        CharacterIterator ite = new StringCharacterIterator(str);
        for (char c = ite.first(); c != maxInt; c = ite.next()) {
            if (((c >= ' ') && (c <= '~')) || ((c >= chrLow) && (c <= chrHi))) {
                intByteLen++;
            } else {
                intByteLen += interval;
            }
        }

        return intByteLen;
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