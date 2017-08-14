package com.msk.seller.bean;

import com.msk.core.entity.PdTcProviderPackage;

/**
 * SL241128Param
 *
 * @author gyh
 * @version 1.0
 **/
public class SL241130Param extends PdTcProviderPackage{
    //选择申请新特性1.品种2.特征3.净重4.包装
    private String chooseInfo;
    /**
     *1品种 2特征 3净重 4包装
     */
    private String newFlag;

    /**
     * Getter method for property <tt>newFlag</tt>.
     *
     * @return property value of newFlag
     */
    public String getNewFlag() {
        return newFlag;
    }

    /**
     * Setter method for property <tt>newFlag</tt>.
     *
     * @param newFlag value to be assigned to property newFlag
     */
    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

    /**
     * Getter method for property <tt>chooseInfo</tt>.
     *
     * @return property value of chooseInfo
     */
    public String getChooseInfo() {
        return chooseInfo;
    }

    /**
     * Setter method for property <tt>chooseInfo</tt>.
     *
     * @param chooseInfo value to be assigned to property chooseInfo
     */
    public void setChooseInfo(String chooseInfo) {
        this.chooseInfo = chooseInfo;
    }
}
