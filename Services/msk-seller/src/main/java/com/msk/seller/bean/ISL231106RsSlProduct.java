package com.msk.seller.bean;

import com.msk.core.entity.SlProduct;

/**
 * Created by gyh on 2016/2/23.
 */
public class ISL231106RsSlProduct extends SlProduct {
    private String loginId;//创建者ID

    /**
     * Getter method for property <tt>loginId</tt>.
     *
     * @return property value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for property <tt>loginId</tt>.
     *
     * @param loginId value to be assigned to property loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
