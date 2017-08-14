package com.msk.org.bean;

/**
 * Created by jackjiang on 16/8/30.
 */
public class DepartmentDef {
    private static String DEPARTMENT_SELLER = "2";

    public static String getDepartmentSeller() {
        return DEPARTMENT_SELLER;
    }

    public static void setDepartmentSeller(String departmentSeller) {
        DEPARTMENT_SELLER = departmentSeller;
    }
}
