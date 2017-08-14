package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlProduct;

import java.util.List;

/**
 * Created by cx on 2016/2/24.
 */
public class ISL231169RsParam extends BaseEntity{

    private List<SlProduct> slPdList;
    //登陆者id
    private String loginId;

    public List<SlProduct> getSlPdList() {
        return slPdList;
    }

    public void setSlPdList(List<SlProduct> slPdList) {
        this.slPdList = slPdList;
    }

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




