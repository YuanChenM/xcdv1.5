/**
 * @screen core
 * @author liu_yinchuan
 */
package com.hoperun.core.utils;

import com.hoperun.core.consts.NumberConst;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Pattern;

/**
 * The utils for Decimal.
 */
public class DecimalUtil {

	/**
	 * 
	 * <p>
	 * round
	 * </p>
	 * 
	 * @param source
	 *            source
	 * @param scale
	 *            scale
	 * @return BigDecimal
	 */
	public static BigDecimal round(BigDecimal source, int scale) {
		if (source == null) {
			return null;
		} else {
			return source.setScale(scale);
		}
	}

	/**
	 * 
	 * <p>
	 * round
	 * </p>
	 * 
	 * @param source
	 *            source
	 * @param scale
	 *            scale
	 * @param roundingMode
	 *            int
	 * @return BigDecimal
	 */
	public static BigDecimal round(BigDecimal source, int scale, int roundingMode) {
		if (source == null) {
			return null;
		} else {
			return source.setScale(scale, roundingMode);
		}
	}

	/**
	 * 
	 * <p>
	 * add
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal add(BigDecimal bd1, BigDecimal bd2) {

		if (bd1 != null) {
			if (bd2 != null) {
				return bd1.add(bd2);
			} else {
				return bd1;
			}
		} else {
			if (bd2 != null) {
				return bd2;
			} else {
				return BigDecimal.ZERO;
			}
		}
	}

	/**
	 * 
	 * <p>
	 * subtract
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal subtract(BigDecimal bd1, BigDecimal bd2) {

		if (bd1 != null) {
			if (bd2 != null) {
				return bd1.subtract(bd2);
			} else {
				return bd1;
			}
		} else {
			if (bd2 != null) {
				return multiply(new BigDecimal(-1), bd2);
			} else {
				return BigDecimal.ZERO;
			}
		}
	}

	/**
	 * 
	 * <p>
	 * multiply
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal multiply(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null || bd2 == null) {
			return BigDecimal.ZERO;
		}

		return bd1.multiply(bd2);
	}

	/**
	 * 
	 * <p>
	 * multiply
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @param nScale
	 *            scale
	 * @return BigDecimal
	 */
	public static BigDecimal multiply(BigDecimal bd1, BigDecimal bd2, int nScale) {
		if (bd1 == null || bd2 == null) {
			return BigDecimal.ZERO;
		}

		return round(bd1.multiply(bd2), nScale);
	}

	/**
	 * 
	 * <p>
	 * multiply, nScale.
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @param nScale
	 *            scale
	 * @return BigDecimal
	 */
	public static BigDecimal multiplyWithNScale(BigDecimal bd1, BigDecimal bd2, int nScale) {
		if (bd1 == null || bd2 == null) {
			return BigDecimal.ZERO;
		}

		return bd1.multiply(bd2).divide(new BigDecimal(1), nScale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * <p>
	 * divide RoundingMode.HALF_UP
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @param nScale
	 *            scale
	 * @return BigDecimal
	 */
	public static BigDecimal divide(BigDecimal bd1, BigDecimal bd2, int nScale) {
		if (bd1 == null || bd2 == null) {
			return BigDecimal.ZERO;
		} else if (bd2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		return bd1.divide(bd2, nScale, RoundingMode.HALF_UP);
	}

	/**
	 * 
	 * <p>
	 * divide by rounding mode.
	 * </p>
	 * 
	 * @param bd1
	 *            the BigDecimal
	 * @param bd2
	 *            the BigDecimal
	 * @param nScale
	 *            the scale
	 * @param roundingMode
	 *            the rounding mode
	 * @return BigDecimal
	 */
	public static BigDecimal divide(BigDecimal bd1, BigDecimal bd2, int nScale, RoundingMode roundingMode) {
		if (bd1 == null || bd2 == null) {
			return BigDecimal.ZERO;
		} else if (bd2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		return bd1.divide(bd2, nScale, roundingMode);
	}

	/**
	 * 
	 * <p>
	 * divide,default scale:10
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal divide(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null || bd2 == null) {
			return BigDecimal.ZERO;
		} else if (bd2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		return divide(bd1, bd2, NumberConst.IntDef.INT_TEN);
	}

	/**
	 * 
	 * <p>
	 * divideRemainder
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal divideRemainder(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null || bd2 == null) {
			return BigDecimal.ZERO;
		} else if (bd2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal[] s = bd1.divideAndRemainder(bd2);
		return s[1];
	}

	/**
	 * 
	 * <p>
	 * isZero
	 * </p>
	 * 
	 * @param bd
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static boolean isZero(BigDecimal bd) {
		boolean b = true;
		if (bd != null && bd.abs().compareTo(BigDecimal.ZERO.abs()) <= 0) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	/**
	 * 
	 * <p>
	 * isEquals
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static boolean isEquals(BigDecimal bd1, BigDecimal bd2) {
		// BigDecimal temp = subtract(bd1, bd2);
		//
		// return isZero(temp);
		boolean isEquals = false;

		if (bd1 == null && bd2 == null) {
			isEquals = true;
		} else if (bd1 != null && bd2 != null) {
			isEquals = (bd1.compareTo(bd2) == 0);
		}

		return isEquals;
	}

	/**
	 * <p>
	 * isGreater
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static boolean isGreater(BigDecimal bd1, BigDecimal bd2) {
		BigDecimal d1 = BigDecimal.ZERO;
		if (bd1 != null) {
			d1 = bd1;
		}
		BigDecimal d2 = BigDecimal.ZERO;
		if (bd2 != null) {
			d2 = bd2;
		}
		return d1.compareTo(d2) > 0;
	}

	/**
	 * <p>
	 * isLess
	 * </p>
	 * 
	 * @param bd1
	 *            BigDecimal
	 * @param bd2
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static boolean isLess(BigDecimal bd1, BigDecimal bd2) {
		BigDecimal d1 = BigDecimal.ZERO;
		if (bd1 != null) {
			d1 = bd1;
		}
		BigDecimal d2 = BigDecimal.ZERO;
		if (bd2 != null) {
			d2 = bd2;
		}
		return d1.compareTo(d2) < 0;
	}

	/**
	 * <p>
	 * Get bigdecimal from object.
	 * </p>
	 * 
	 * @param obj
	 *            object value
	 * @return bigdecimal value
	 */
	public static BigDecimal getBigDecimal(Object obj) {
		if (obj == null) {
			return BigDecimal.ZERO;
		}
		BigDecimal ret = null;
		try {
			if (obj instanceof BigDecimal) {
				ret = (BigDecimal) obj;
			} else if (obj instanceof String) {
				ret = new BigDecimal((String) obj);
			} else if (obj instanceof BigInteger) {
				ret = new BigDecimal((BigInteger) obj);
			} else if (obj instanceof Number) {
				ret = new BigDecimal(((Number) obj).doubleValue());
			} else {
				return BigDecimal.ZERO;
			}
			return ret;
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * <p>
	 * Get bigdecimal from object with Null value.
	 * </p>
	 * 
	 * @param obj
	 *            object value
	 * @return bigdecimal value
	 */
	public static BigDecimal getBigDecimalWithNUll(Object obj) {
		BigDecimal ret = null;
		if (obj == null) {
			return ret;
		}
		try {
			if (obj instanceof BigDecimal) {
				ret = (BigDecimal) obj;
			} else if (obj instanceof String) {
				ret = new BigDecimal((String) obj);
				// Add by guoyong for UAT_No.217 on 20150206 Start
				// full angle patten, return null.
				String regEx = "^[0-9]*$";
				String str = String.valueOf(obj).replace('.', '0').replace('-', '0');
				if (Pattern.compile(regEx).matcher(str).matches() == false) {
					ret = null;
				}
				// Add by guoyong for UAT_No.217 on 20150206 End
			} else if (obj instanceof BigInteger) {
				ret = new BigDecimal((BigInteger) obj);
			} else if (obj instanceof Number) {
				ret = new BigDecimal(((Number) obj).doubleValue());
			}
			return ret;
		} catch (Exception e) {
			return ret;
		}
	}
}
