package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryPdControl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liu_yan2 on 2016/8/9.
 */
public class SSC11326RsResult implements Serializable {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /**查询生产商生产计划详细集合*/
    private List<SSC11326RsBean> ssc11326RsBeanList = new ArrayList<SSC11326RsBean>();

    /**查询按日统计集合*/
    private List<SSC11326RsBean> totalByDateList = new ArrayList<SSC11326RsBean>();

    /**查询产品统计集合*/
    private Map<String, SSC11326RsBean> totalByNameList = new HashMap<String, SSC11326RsBean>();

    /**查询运输期产品管控集合*/
    private List<SSC1132601RsBean> deliveryPdControls = new ArrayList<>();

    public List<SSC11326RsBean> getSsc11326RsBeanList() {
        return ssc11326RsBeanList;
    }

    public void setSsc11326RsBeanList(List<SSC11326RsBean> ssc11326RsBeanList) {
        this.ssc11326RsBeanList = ssc11326RsBeanList;
    }

    public List<SSC11326RsBean> getTotalByDateList() {
        return totalByDateList;
    }

    public void setTotalByDateList(List<SSC11326RsBean> totalByDateList) {
        this.totalByDateList = totalByDateList;
    }

    public Map<String, SSC11326RsBean> getTotalByNameList() {
        return totalByNameList;
    }

    public void setTotalByNameList(Map<String, SSC11326RsBean> totalByNameList) {
        this.totalByNameList = totalByNameList;
    }

    public List<SSC1132601RsBean> getDeliveryPdControls() {
        return deliveryPdControls;
    }

    public void setDeliveryPdControls(List<SSC1132601RsBean> deliveryPdControls) {
        this.deliveryPdControls = deliveryPdControls;
    }
}
