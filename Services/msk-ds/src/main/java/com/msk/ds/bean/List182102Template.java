package com.msk.ds.bean;

import com.msk.common.xml.bean.ListTemplate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by li_kai1 on 2016/8/1.
 */

@XmlRootElement(name = "DATACOLLECTION")

public class List182102Template extends ListTemplate<SC182102XmlParam> {
    private List<SC182102XmlParam> data;
    @Override
    @XmlElement(name = "DATA")
    public List<SC182102XmlParam> getData() {
        return data;
    }
    public void setData(List<SC182102XmlParam> data){
        this.data = data;
    }

}
