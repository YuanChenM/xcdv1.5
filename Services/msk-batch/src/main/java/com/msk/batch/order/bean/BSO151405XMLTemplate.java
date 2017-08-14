package com.msk.batch.order.bean;



import com.msk.common.xml.bean.EntityTemplate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wang_jianzhou on 2016/7/31.
 */
@XmlRootElement(name="DATACOLLECTION")
public class BSO151405XMLTemplate extends EntityTemplate<BSO151405Result> {
    private BSO151405Result data;
    @Override
    @XmlElement(name = "DATA")
    public BSO151405Result getData() {
        return data;
    }

    public void setData(BSO151405Result data) {
        this.data = data;
    }
}
