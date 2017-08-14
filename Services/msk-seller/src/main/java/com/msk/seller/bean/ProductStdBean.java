package com.msk.seller.bean;
import com.hoperun.core.bean.BaseParam;
import com.msk.product.bean.MctStdBean;
import com.msk.product.bean.TncStdBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guan_zhongheng on 2016/8/24.
 */
public class ProductStdBean extends BaseParam{

    private String slCode;
    private Integer slPdId;

    private List<TncStdBean> tncList = new ArrayList<>();

    private List<MctStdBean> mctList = new ArrayList<>();

    public List<TncStdBean> getTncList() {
        return tncList;
    }

    public void setTncList(List<TncStdBean> tncList) {
        this.tncList = tncList;
    }

    public List<MctStdBean> getMctList() {
        return mctList;
    }

    public void setMctList(List<MctStdBean> mctList) {
        this.mctList = mctList;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Integer getSlPdId() {
        return slPdId;
    }

    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }
}
