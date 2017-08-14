package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByMarketTerminalByInfo;

/**
 * Created by guan_zhongheng on 2016/8/8.
 */
public class BY121403TotalBean extends  ByMarketTerminalByInfo{

    // 当前页面户数
    private String currentNumber;

    // 当前页面金额
    private String currentAmount;

    // 总户数
    private String totalNumber;

    // 总金额
    private String totalAmount;

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
