package com.msk.seller.bean;

/**
 * Created by gyh on 2016/3/24.
 */
public class ISL231184RsResult {
    private Integer isExistLicNo;//业务返回值,0：不存在，1：已存在

    /**
     * Getter method for property <tt>isExistLicNo</tt>.
     *
     * @return property value of isExistLicNo
     */
    public Integer getIsExistLicNo() {
        return isExistLicNo;
    }

    /**
     * Setter method for property <tt>isExistLicNo</tt>.
     *
     * @param isExistLicNo value to be assigned to property isExistLicNo
     */
    public void setIsExistLicNo(Integer isExistLicNo) {
        this.isExistLicNo = isExistLicNo;
    }
}
