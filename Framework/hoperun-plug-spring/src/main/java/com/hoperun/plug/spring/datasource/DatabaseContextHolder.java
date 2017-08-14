package com.hoperun.plug.spring.datasource;
/**
 * The Data Source Context Def.
 * @author Administrator
 *
 */
public class DatabaseContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	/**Read Only Data Source*/
	public static final String MASTER_DATA_SOURCE = "masterdataSource";
	/**The Business Data Source*/
	public static final String SLAVE_DATA_SOURCE = "slavedataSource";
	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
