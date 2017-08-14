/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition;

/**
 * 
 * <p>
 * The Database query operator type define.
 * </p>
 * 
 * @author zhou_ke
 */
public enum BaseOperatorEnum {
    /** = */
    EQUAL,
    /** <> */
    NOTEQUAL,
    /** like */
    LIKE,
    /** like */
    LIKE_IGNORE_CASE,
    /** between */
    BETWEEN,
    /** in */
    IN,
    /** not in */
    NOTIN,
    /** > */
    GREATERTHAN,
    /** < */
    LESSTHAN
}
