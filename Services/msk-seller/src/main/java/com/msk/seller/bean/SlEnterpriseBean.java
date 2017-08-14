package com.msk.seller.bean;

import com.msk.core.entity.SlEnterprise;

/**
 * Created by Administrator on 2016/3/23.
 */
public class SlEnterpriseBean extends SlEnterprise {

    //卖家id
    private String slCode;

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
