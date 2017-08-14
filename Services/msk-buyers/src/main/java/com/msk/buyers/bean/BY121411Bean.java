package com.msk.buyers.bean;

import com.msk.core.entity.ByMarketFoodFileInfo;

/**
 * Created by zhou_yajun on 2016/7/11.
 */
public class BY121411Bean extends ByMarketFoodFileInfo {

    /** 菜场图片完整路径*/
    private String marketFilePath;

    /**
     * Getter method for property <tt>marketFilePath</tt>.
     *
     * @return property value of marketFilePath
     */
    public String getMarketFilePath() {
        return marketFilePath;
    }

    /**
     * Setter method for property <tt>marketFilePath</tt>.
     *
     * @param marketFilePath value to be assigned to property marketFilePath
     */
    public void setMarketFilePath(String marketFilePath) {
        this.marketFilePath = marketFilePath;
    }
}
