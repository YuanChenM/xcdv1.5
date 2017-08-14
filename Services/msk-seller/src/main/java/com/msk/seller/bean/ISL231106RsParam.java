package com.msk.seller.bean;

import com.msk.core.entity.SlPdTncStdOther;
import com.msk.core.entity.SlProduct;

import java.util.List;

/**
 * Created by gyh on 2016/2/23.
 */
public class ISL231106RsParam {
    private List<ISL231106RsSlProduct> slPdList;//卖家能销售的产品信息
    private List<ISL231106RsSlPdTnc> slPdTncList;//卖家产品加工质量标准信息List
    private List<ISL231106RsSlPdQty> slPdMctList;//卖家产品加工技术标准指标
    private List<ISL231106RsSlPdPkg> slPdPkgList;//卖家产品包装标准信息
    private List<SlPdTncStdOther> slPdOrgStdList;//卖家原种种源标准
    private List<SlPdTncStdOther> slPdFedStdList;//卖家产品饲养标准
    private List<SlPdTncStdOther> slPdGnqStdList;//卖家产品通用质量标准
    private List<SlPdTncStdOther> slPdTspStdList;//卖家产品储存运输标准
    private List<SlPdTncStdOther> slPdSftStdList;//卖家产品安全标准

    /**
     * Getter method for property <tt>slPdOrgStdList</tt>.
     *
     * @return property value of slPdOrgStdList
     */
    public List<SlPdTncStdOther> getSlPdOrgStdList() {
        return slPdOrgStdList;
    }

    /**
     * Setter method for property <tt>slPdOrgStdList</tt>.
     *
     * @param slPdOrgStdList value to be assigned to property slPdOrgStdList
     */
    public void setSlPdOrgStdList(List<SlPdTncStdOther> slPdOrgStdList) {
        this.slPdOrgStdList = slPdOrgStdList;
    }

    /**
     * Getter method for property <tt>slPdFedStdList</tt>.
     *
     * @return property value of slPdFedStdList
     */
    public List<SlPdTncStdOther> getSlPdFedStdList() {
        return slPdFedStdList;
    }

    /**
     * Setter method for property <tt>slPdFedStdList</tt>.
     *
     * @param slPdFedStdList value to be assigned to property slPdFedStdList
     */
    public void setSlPdFedStdList(List<SlPdTncStdOther> slPdFedStdList) {
        this.slPdFedStdList = slPdFedStdList;
    }

    /**
     * Getter method for property <tt>slPdGnqStdList</tt>.
     *
     * @return property value of slPdGnqStdList
     */
    public List<SlPdTncStdOther> getSlPdGnqStdList() {
        return slPdGnqStdList;
    }

    /**
     * Setter method for property <tt>slPdGnqStdList</tt>.
     *
     * @param slPdGnqStdList value to be assigned to property slPdGnqStdList
     */
    public void setSlPdGnqStdList(List<SlPdTncStdOther> slPdGnqStdList) {
        this.slPdGnqStdList = slPdGnqStdList;
    }

    /**
     * Getter method for property <tt>slPdTspStdList</tt>.
     *
     * @return property value of slPdTspStdList
     */
    public List<SlPdTncStdOther> getSlPdTspStdList() {
        return slPdTspStdList;
    }

    /**
     * Setter method for property <tt>slPdTspStdList</tt>.
     *
     * @param slPdTspStdList value to be assigned to property slPdTspStdList
     */
    public void setSlPdTspStdList(List<SlPdTncStdOther> slPdTspStdList) {
        this.slPdTspStdList = slPdTspStdList;
    }

    /**
     * Getter method for property <tt>slPdSftStdList</tt>.
     *
     * @return property value of slPdSftStdList
     */
    public List<SlPdTncStdOther> getSlPdSftStdList() {
        return slPdSftStdList;
    }

    /**
     * Setter method for property <tt>slPdSftStdList</tt>.
     *
     * @param slPdSftStdList value to be assigned to property slPdSftStdList
     */
    public void setSlPdSftStdList(List<SlPdTncStdOther> slPdSftStdList) {
        this.slPdSftStdList = slPdSftStdList;
    }

    /**
     * Getter method for property <tt>slPdList</tt>.
     *
     * @return property value of slPdList
     */
    public List<ISL231106RsSlProduct> getSlPdList() {
        return slPdList;
    }

    /**
     * Setter method for property <tt>slPdList</tt>.
     *
     * @param slPdList value to be assigned to property slPdList
     */
    public void setSlPdList(List<ISL231106RsSlProduct> slPdList) {
        this.slPdList = slPdList;
    }

    /**
     * Getter method for property <tt>slPdTncList</tt>.
     *
     * @return property value of slPdTncList
     */
    public List<ISL231106RsSlPdTnc> getSlPdTncList() {
        return slPdTncList;
    }

    /**
     * Setter method for property <tt>slPdTncList</tt>.
     *
     * @param slPdTncList value to be assigned to property slPdTncList
     */
    public void setSlPdTncList(List<ISL231106RsSlPdTnc> slPdTncList) {
        this.slPdTncList = slPdTncList;
    }

    /**
     * Getter method for property <tt>slPdMctList</tt>.
     *
     * @return property value of slPdMctList
     */
    public List<ISL231106RsSlPdQty> getSlPdMctList() {
        return slPdMctList;
    }

    /**
     * Setter method for property <tt>slPdMctList</tt>.
     *
     * @param slPdMctList value to be assigned to property slPdMctList
     */
    public void setSlPdMctList(List<ISL231106RsSlPdQty> slPdMctList) {
        this.slPdMctList = slPdMctList;
    }

    /**
     * Getter method for property <tt>slPdPkgList</tt>.
     *
     * @return property value of slPdPkgList
     */
    public List<ISL231106RsSlPdPkg> getSlPdPkgList() {
        return slPdPkgList;
    }

    /**
     * Setter method for property <tt>slPdPkgList</tt>.
     *
     * @param slPdPkgList value to be assigned to property slPdPkgList
     */
    public void setSlPdPkgList(List<ISL231106RsSlPdPkg> slPdPkgList) {
        this.slPdPkgList = slPdPkgList;
    }
}
