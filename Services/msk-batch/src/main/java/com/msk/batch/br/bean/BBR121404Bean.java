package com.msk.batch.br.bean;

import com.msk.core.entity.BrBuyerPoolInfo;

/**
 * Created by jiang_tengfei on 2016/6/7.
 * 标准产品池买家池
 */
public class BBR121404Bean extends BrBuyerPoolInfo {

    //买家顺序码(分销买家后3位，菜场买家后2位)
    private String seqCode;
    //序号
    private String num;


    public String getSeqCode() {
        return seqCode;
    }

    public void setSeqCode(String seqCode) {
        this.seqCode = seqCode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
