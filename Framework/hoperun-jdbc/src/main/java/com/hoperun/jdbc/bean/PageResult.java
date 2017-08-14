package com.hoperun.jdbc.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 * 
 * @param <T> List Entity
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    // 总件数
    private int recordsTotal;
    /** 数据集合 */
    private List<T> data;



    /**
     * @return the recordsTotal
     */
    public int getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * @param recordsTotal the recordsTotal to set
     */
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    /**
     * @return the data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }

}
