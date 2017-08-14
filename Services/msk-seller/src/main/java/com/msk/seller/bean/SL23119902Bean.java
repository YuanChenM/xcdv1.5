package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

/**
 * Created by geng_xingdong on 2016/6/16.
 */
public class SL23119902Bean extends BaseBean {

    private int suppCode;//卖家编码
    private String suppName;//卖家名称

    public int getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(int suppCode) {
        this.suppCode = suppCode;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }
}
