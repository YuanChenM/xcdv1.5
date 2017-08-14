package com.msk.batch.order.bean;

import com.msk.common.xml.bean.EntityTemplate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wang_jianzhou on 2016/7/31.
 */
@XmlRootElement(name="DATACOLLECTION")
public class BSO151406XMLTemplate extends EntityTemplate<BSO151406Result> {
    private BSO151406Result data;
    @Override
    @XmlElement(name = "DATA")
    public BSO151406Result getData() {
        return data;
    }

    public void setData(BSO151406Result data) {
        this.data = data;
    }
}
