package com.msk.batch.order.bean;

import com.msk.common.xml.bean.ListTemplate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by wang_jianzhou on 2016/8/24.
 */
@XmlRootElement(name="DATACOLLECTION")
public class BSO151403XMLTemplate extends ListTemplate<BSO151403Result> {
    private List<BSO151403Result> data;
    @Override
    @XmlElement(name = "DATA")
    public List<BSO151403Result> getData() {
        return data;
    }

    public void setData(List<BSO151403Result> data) {
        this.data = data;
    }
}
