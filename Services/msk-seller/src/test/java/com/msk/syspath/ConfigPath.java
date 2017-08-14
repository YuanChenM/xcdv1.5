/*
 * Path.java
 *
 * Version:
 *
 * Date:2011-09-13 9:50
 *
 * Copyright:
 */
package com.msk.syspath;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 功能：系统路径类
 * @author yang_yang
 * @version 1.0
 * @date 2008-11-8
 */
public final class ConfigPath {

	// 类路径
	private static String classPath = null;

	// WEB-INF路径
	private static String webInfPath = null;

	// 应用程序路径
	private static String appPath = null;

	// 应用程序名称
	private static String appName = null;

	// 应用程序URL
	private static String appURL = null;

	// 是否需要初始化过
	private static boolean blnInit = true;

	/**
	 * 初始化
	 * 
	 * @param request
	 */
	public synchronized final static void init(HttpServletRequest request) {
		if (blnInit) {
			if (request != null) {
				appName = request.getContextPath();
				appURL = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + appName + "/";
				blnInit = false;
			}
		}
	}

	/**
	 * 获得Web-Inf目录物理路径
	 * 
	 * @return String
	 */
	public synchronized final static String getWebInfPath() {
		if (webInfPath == null) {
			String strPath = null;
			String strWebInf = "web-inf";
			int intIndex = -1;
			try {
				// 获得ConfigPath.class路径
				strPath = Path.getPathFromClass(ConfigPath.class);
				if (strPath != null && strPath.trim().length() > 0) {
					intIndex = strPath.toLowerCase().lastIndexOf(strWebInf);
					if (intIndex > 0) {
						intIndex = intIndex + strWebInf.length();
						webInfPath = strPath.substring(0, intIndex)
								+ File.separator;
					}
				}
				if (intIndex <= 0) {
					System.out.println("类:ConfigPath,方法:getClassPath,"
							+ "信息:获得Web-Inf路径失败," + strPath);
				}
			} catch (IOException e) {
				System.out.println("类:ConfigPath,方法:getClassPath,"
						+ "信息:获得Web-Inf路径失败," + e);
				webInfPath = null;
			}
		}
		return webInfPath;
	}

	/**
	 * 获得classes目录物理路径
	 * 
	 * @return String
	 */
	public synchronized final static String getClassPath() {
		if (classPath == null) {
			webInfPath = getWebInfPath();
			if (webInfPath != null) {
				classPath = webInfPath + "classes" + File.separator;
			}
		}
		return classPath;
	}

	/**
	 * 获得应用程序物理路径
	 * 
	 * @return String
	 */
	public synchronized final static String getAppPath() {
		if (appPath == null) {
			webInfPath = getWebInfPath();
			if (webInfPath != null) {
				int intIndex = -1;
				intIndex = webInfPath.toLowerCase().lastIndexOf("web-inf");
				if (intIndex > 0) {
					appPath = webInfPath.substring(0, intIndex);
				}
			}
		}
		return appPath;
	}

	/**
	 * 获得应用程序名称 示例：/appName
	 * 
	 * @return String
	 */
	public synchronized final static String getAppName(
			HttpServletRequest request) {
		if (appName == null) {
			if (request != null) {
				init(request);
			} else {
				if (appPath == null) {
					appPath = getAppPath();
				}
				if (appPath != null) {
					appName = appPath;
					appName = appName.replaceAll("\\\\", "/");
					int intIndex = appName.lastIndexOf("/");
					if (intIndex == appName.length() - 1) {
						appName = appName.substring(0, intIndex);
						intIndex = appName.lastIndexOf("/");
						if (intIndex > 0) {
							appName = appName.substring(intIndex);
						}
					}
				}
			}
		} else {
			if (blnInit) {
				init(request);
			}
		}
		return appName;
	}

	/**
	 * 获得：应用程序URL
	 * 
	 * @param request
	 * @return String
	 */
	public synchronized final static String getAppURL(HttpServletRequest request) {
		if (appURL == null) {
			init(request);
		}
		return appURL;
	}
}
