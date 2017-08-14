package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * Created by Administrator on 2016/2/23.
 */
public class PD141137StautsBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String featureName;

    private Date statusMonitorDate;

    private String featureDate;

    /**
     * Getter method for property <tt>featureName</tt>.
     *
     * @return property value of featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * Setter method for property <tt>featureName</tt>.
     *
     * @param featureName value to be assigned to property featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * Getter method for property <tt>statusMonitorDate</tt>.
     *
     * @return property value of statusMonitorDate
     */
    public Date getStatusMonitorDate() {
        return statusMonitorDate;
    }

    /**
     * Setter method for property <tt>statusMonitorDate</tt>.
     *
     * @param statusMonitorDate value to be assigned to property statusMonitorDate
     */
    public void setStatusMonitorDate(Date statusMonitorDate) {
        this.statusMonitorDate = statusMonitorDate;
    }

    /**
     * Getter method for property <tt>featureDate</tt>.
     *
     * @return property value of featureDate
     */
    public String getFeatureDate() {
        return featureDate;
    }

    /**
     * Setter method for property <tt>featureDate</tt>.
     *
     * @param featureDate value to be assigned to property featureDate
     */
    public void setFeatureDate(String featureDate) {
        this.featureDate = featureDate;
    }
}
