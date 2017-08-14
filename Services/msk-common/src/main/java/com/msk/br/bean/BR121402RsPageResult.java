package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrSetting;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/18.
 */
public class BR121402RsPageResult extends RsPageResult {

    private List<BrSetting> brSettings;

    //验证数据范围结果
    private int  regResult;

    //更新受影响行数
    private int  updateLine;

    //判断数据是否已经存在
    private int  isExitResult;

    //新增受影响数据
    private int  insertLine;

    public List<BrSetting> getBrSettings() {
        return brSettings;
    }

    public void setBrSettings(List<BrSetting> brSettings) {
        this.brSettings = brSettings;
    }

    public int getRegResult() {
        return regResult;
    }

    public void setRegResult(int regResult) {
        this.regResult = regResult;
    }

    public int getUpdateLine() {
        return updateLine;
    }

    public void setUpdateLine(int updateLine) {
        this.updateLine = updateLine;
    }

    public int getIsExitResult() {
        return isExitResult;
    }

    public void setIsExitResult(int isExitResult) {
        this.isExitResult = isExitResult;
    }

    public int getInsertLine() {
        return insertLine;
    }

    public void setInsertLine(int insertLine) {
        this.insertLine = insertLine;
    }
}
