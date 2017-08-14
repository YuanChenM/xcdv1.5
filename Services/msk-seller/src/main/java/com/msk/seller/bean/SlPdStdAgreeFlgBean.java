package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

/**
 * Created by gyh on 2016/3/10.
 */
public class SlPdStdAgreeFlgBean extends BaseBean{
    //同意标志
    private String agreeFlg;
    //次数
    private Integer flgCount;

    /**
     * Getter method for property <tt>agreeFlg</tt>.
     *
     * @return property value of agreeFlg
     */
    public String getAgreeFlg() {
        return agreeFlg;
    }

    /**
     * Setter method for property <tt>agreeFlg</tt>.
     *
     * @param agreeFlg value to be assigned to property agreeFlg
     */
    public void setAgreeFlg(String agreeFlg) {
        this.agreeFlg = agreeFlg;
    }

    /**
     * Getter method for property <tt>flgCount</tt>.
     *
     * @return property value of flgCount
     */
    public Integer getFlgCount() {
        return flgCount;
    }

    /**
     * Setter method for property <tt>flgCount</tt>.
     *
     * @param flgCount value to be assigned to property flgCount
     */
    public void setFlgCount(Integer flgCount) {
        this.flgCount = flgCount;
    }
}
