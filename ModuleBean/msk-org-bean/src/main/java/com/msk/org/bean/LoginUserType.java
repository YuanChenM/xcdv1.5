package com.msk.org.bean;

public final class LoginUserType {
    private static String USER_TYPE_EMPLOY = "1";
    private static String USER_TYPE_SELLER = "2";
    private static String USER_TYPE_BUYER = "3";
    private static String USER_TYPE_BANK = "4";

    public static String getUserTypeEmploy() {
        return USER_TYPE_EMPLOY;
    }

    public static void setUserTypeEmploy(String userTypeEmploy) {
        USER_TYPE_EMPLOY = userTypeEmploy;
    }

    public static String getUserTypeSeller() {
        return USER_TYPE_SELLER;
    }

    public static void setUserTypeSeller(String userTypeSeller) {
        USER_TYPE_SELLER = userTypeSeller;
    }

    public static String getUserTypeBuyer() {
        return USER_TYPE_BUYER;
    }

    public static void setUserTypeBuyer(String userTypeBuyer) {
        USER_TYPE_BUYER = userTypeBuyer;
    }

    public static String getUserTypeBank() {
        return USER_TYPE_BANK;
    }

    public static void setUserTypeBank(String userTypeBank) {
        USER_TYPE_BANK = userTypeBank;
    }
}
