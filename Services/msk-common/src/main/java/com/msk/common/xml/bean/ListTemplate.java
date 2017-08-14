package com.msk.common.xml.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.msk.core.entity.BaseEntity;

@XmlRootElement(name="DATACOLLECTION")
public abstract class ListTemplate<T> extends BaseEntity {
    @XmlElement(name = "DATA")
    public abstract List<T> getData();

}
