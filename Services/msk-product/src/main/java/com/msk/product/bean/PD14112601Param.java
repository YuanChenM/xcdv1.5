package com.msk.product.bean;

import com.msk.core.entity.PdClassestreeMat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
@ApiModel(value = "PD14112601Param",description = "result")
public class PD14112601Param extends PdClassestreeMat {

    @ApiModelProperty(value = "市场需求注册")
    private List<PD141128Param> pdTcMarketList;

    @ApiModelProperty(value = "市场需求注册")
    private List<PdTcProviderPackageParam> pdTcProvider;

    @ApiModelProperty(value = "正式上线")
    private List<PdTcOnlineParam> pdTcOnline;

    @ApiModelProperty(value = "卖家供应备案注册标准目录")
    private List<PdTcProviderContentParam> pdTcProviderContents;

    @ApiModelProperty(value = "OEM正式上线")
    private List<PdTcOnlineOemParam> pdTcOnlineOem;

    @ApiModelProperty(value = "产品使用对象")
    private String salesTargetTwo;

    @ApiModelProperty(value = "产品加工方向")
    private String machiningWayTwo;

    @ApiModelProperty(value = "产品特征分类")
    private List<PD14112601Bean> pd141124Level4Beans;

    @ApiModelProperty(value = "禁止准入产品")
    private List<PdTcForbidParam> pdTcForbidParams;


    /**
     * Getter method for property <tt>pdTcForbidParams</tt>.
     *
     * @return property value of pdTcForbidParams
     */
    public List<PdTcForbidParam> getPdTcForbidParams() {
        return pdTcForbidParams;
    }

    /**
     * Setter method for property <tt>pdTcForbidParams</tt>.
     *
     * @param pdTcForbidParams value to be assigned to property pdTcForbidParams
     */
    public void setPdTcForbidParams(List<PdTcForbidParam> pdTcForbidParams) {
        this.pdTcForbidParams = pdTcForbidParams;
    }

    /**
     * Getter method for property <tt>pd141124Level4Beans</tt>.
     *
     * @return property value of pd141124Level4Beans
     */
    public List<PD14112601Bean> getPd141124Level4Beans() {
        return pd141124Level4Beans;
    }

    /**
     * Setter method for property <tt>pd141124Level4Beans</tt>.
     *
     * @param pd141124Level4Beans value to be assigned to property pd141124Level4Beans
     */
    public void setPd141124Level4Beans(List<PD14112601Bean> pd141124Level4Beans) {
        this.pd141124Level4Beans = pd141124Level4Beans;
    }

    /**
     * Getter method for property <tt>machiningWayTwo</tt>.
     *
     * @return property value of machiningWayTwo
     */
    public String getMachiningWayTwo() {
        return machiningWayTwo;
    }

    /**
     * Setter method for property <tt>machiningWayTwo</tt>.
     *
     * @param machiningWayTwo value to be assigned to property machiningWayTwo
     */
    public void setMachiningWayTwo(String machiningWayTwo) {
        this.machiningWayTwo = machiningWayTwo;
    }

    /**
     * Getter method for property <tt>salesTargetTwo</tt>.
     *
     * @return property value of salesTargetTwo
     */
    public String getSalesTargetTwo() {
        return salesTargetTwo;
    }

    /**
     * Setter method for property <tt>salesTargetTwo</tt>.
     *
     * @param salesTargetTwo value to be assigned to property salesTargetTwo
     */
    public void setSalesTargetTwo(String salesTargetTwo) {
        this.salesTargetTwo = salesTargetTwo;
    }
    /**
     * Getter method for property <tt>pdTcOnlineOem</tt>.
     *
     * @return property value of pdTcOnlineOem
     */
    public List<PdTcOnlineOemParam> getPdTcOnlineOem() {
        return pdTcOnlineOem;
    }

    /**
     * Setter method for property <tt>pdTcOnlineOem</tt>.
     *
     * @param pdTcOnlineOem value to be assigned to property pdTcOnlineOem
     */
    public void setPdTcOnlineOem(List<PdTcOnlineOemParam> pdTcOnlineOem) {
        this.pdTcOnlineOem = pdTcOnlineOem;
    }


    /**
     * Getter method for property <tt>pdTcProviderContents</tt>.
     *
     * @return property value of pdTcProviderContents
     */
    public List<PdTcProviderContentParam> getPdTcProviderContents() {
        return pdTcProviderContents;
    }

    /**
     * Setter method for property <tt>pdTcProviderContents</tt>.
     *
     * @param pdTcProviderContents value to be assigned to property pdTcProviderContents
     */
    public void setPdTcProviderContents(List<PdTcProviderContentParam> pdTcProviderContents) {
        this.pdTcProviderContents = pdTcProviderContents;
    }

    /**
     * Getter method for property <tt>pdTcOnline</tt>.
     *
     * @return property value of pdTcOnline
     */
    public List<PdTcOnlineParam> getPdTcOnline() {
        return pdTcOnline;
    }

    /**
     * Setter method for property <tt>pdTcOnline</tt>.
     *
     * @param pdTcOnline value to be assigned to property pdTcOnline
     */
    public void setPdTcOnline(List<PdTcOnlineParam> pdTcOnline) {
        this.pdTcOnline = pdTcOnline;
    }
    /**
     * Getter method for property <tt>pdTcProvider</tt>.
     *
     * @return property value of pdTcProvider
     */
    public List<PdTcProviderPackageParam> getPdTcProvider() {
        return pdTcProvider;
    }

    /**
     * Setter method for property <tt>pdTcProvider</tt>.
     *
     * @param pdTcProvider value to be assigned to property pdTcProvider
     */
    public void setPdTcProvider(List<PdTcProviderPackageParam> pdTcProvider) {
        this.pdTcProvider = pdTcProvider;
    }

    /**
     * Getter method for property <tt>pdTcMarketList</tt>.
     *
     * @return property value of pdTcMarketList
     */
    public List<PD141128Param> getPdTcMarketList() {
        return pdTcMarketList;
    }

    /**
     * Setter method for property <tt>pdTcMarketList</tt>.
     *
     * @param pdTcMarketList value to be assigned to property pdTcMarketList
     */
    public void setPdTcMarketList(List<PD141128Param> pdTcMarketList) {
        this.pdTcMarketList = pdTcMarketList;
    }
}
