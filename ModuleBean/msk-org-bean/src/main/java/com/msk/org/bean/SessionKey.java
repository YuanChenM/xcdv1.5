package com.msk.org.bean;


public class SessionKey {
    private final static String USER_ROLE_AUTH = "USER_ROLE_AUTH";
    private final static String SYSTEM_MENU = "SYSTEM_MENU";
    public static String getUserRoleAuth() {
        return USER_ROLE_AUTH;
    }

    public static String getSystemMenu() {
        return SYSTEM_MENU;
    }
}
