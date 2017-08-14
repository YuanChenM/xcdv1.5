package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * ISO151406RsParam接受参数.
 *
 * @author sjj
 */
public class ISO151406RestParam extends BaseParam {
    // 参数列表
    private List<ISO151406HouseAccountParam> houseAccountList;

    public List<ISO151406HouseAccountParam> getHouseAccountList() {
        return houseAccountList;
    }

    public void setHouseAccountList(List<ISO151406HouseAccountParam> houseAccountList) {
        this.houseAccountList = houseAccountList;
    }
}
