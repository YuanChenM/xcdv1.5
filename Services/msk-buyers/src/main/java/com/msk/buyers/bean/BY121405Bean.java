package com.msk.buyers.bean;

import com.msk.core.entity.ByMarketTerminalFileInfo;

/**
 * Created by zhou_yajun on 2016/7/11.
 */
public class BY121405Bean extends ByMarketTerminalFileInfo{

    /** 批发市场图片完整路径*/
    private String marketFIlePath;

    /**
     * Getter method for property <tt>marketFIlePath</tt>.
     *
     * @return property value of marketFIlePath
     */
    public String getMarketFIlePath() {
        return marketFIlePath;
    }

    /**
     * Setter method for property <tt>marketFIlePath</tt>.
     *
     * @param marketFIlePath value to be assigned to property marketFIlePath
     */
    public void setMarketFIlePath(String marketFIlePath) {
        this.marketFIlePath = marketFIlePath;
    }
}
