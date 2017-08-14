package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByBuyerAccount;

/**
 * Created by zhao_chen on 2016/7/27.
 */

public class BY121413Bean extends BaseEntity{

    private static final long serialVersionUID = 1L;
    /** 批发市场ID */

    private Long storeId;

    private String marketId;
    private String buyerStoreNo;
    private String remark;

    private String isTargetMerchant;
    /** 买家类型 */
    private String merchantType;
   //
    private String pdChicken;
    //鸭产品
    private String pdDuck;
    //猪产品
    private String pdPork;
    //牛羊产品
    private String pdBeefMutton;
    //海产品:
    private String pdSea;
    //丸肠水发品:
    private String pdMeatballs;
    //腌腊产品:
    private String pdBacon;
    //冰鲜产品:
    private String frozenFood;
    //速冻点心:
    private String quicklyFreezedSnack;
    //速冻蔬菜:
    private String quicklyFreezedVegetables;
    //调理品·水煮包·方便菜:
    private String convenientFood;
    //粮油产品: 00
    private String grainOil;
    //调味品:
    private String flavoring;
    //干货产品:00
    private String drysaltery;
    // 小菜产品:
    private String cole;
    //儿童食品:
    private String childFood;


    //鲜肉产品:
    private String FreshMeat;
    //鲜鱼产品:
    private String FreshFish;
    //活禽产品:
    private String poultry;
    //禽蛋产品:
    private String eggs;
    //果蔬产品:
    private String fruitsVegetables;
    //豆制品:pdBean
    private String pdBean;
    //其它食品
    private String otherFood;
    //非食品
    private String nonFood;

    private String isChecked;
    /** 销售产品编码 */
    private String salePdCode;
    /** 销售产品名称 */
    private String salePd;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIsTargetMerchant() {
        return isTargetMerchant;
    }

    public void setIsTargetMerchant(String isTargetMerchant) {
        this.isTargetMerchant = isTargetMerchant;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getPdChicken() {
        return pdChicken;
    }

    public void setPdChicken(String pdChicken) {
        this.pdChicken = pdChicken;
    }

    public String getPdDuck() {
        return pdDuck;
    }

    public void setPdDuck(String pdDuck) {
        this.pdDuck = pdDuck;
    }

    public String getPdPork() {
        return pdPork;
    }

    public void setPdPork(String pdPork) {
        this.pdPork = pdPork;
    }

    public String getPdBeefMutton() {
        return pdBeefMutton;
    }

    public void setPdBeefMutton(String pdBeefMutton) {
        this.pdBeefMutton = pdBeefMutton;
    }

    public String getPdMeatballs() {
        return pdMeatballs;
    }

    public void setPdMeatballs(String pdMeatballs) {
        this.pdMeatballs = pdMeatballs;
    }

    public String getPdBacon() {
        return pdBacon;
    }

    public void setPdBacon(String pdBacon) {
        this.pdBacon = pdBacon;
    }

    public String getFrozenFood() {
        return frozenFood;
    }

    public void setFrozenFood(String frozenFood) {
        this.frozenFood = frozenFood;
    }

    public String getQuicklyFreezedSnack() {
        return quicklyFreezedSnack;
    }

    public void setQuicklyFreezedSnack(String quicklyFreezedSnack) {
        this.quicklyFreezedSnack = quicklyFreezedSnack;
    }

    public String getQuicklyFreezedVegetables() {
        return quicklyFreezedVegetables;
    }

    public void setQuicklyFreezedVegetables(String quicklyFreezedVegetables) {
        this.quicklyFreezedVegetables = quicklyFreezedVegetables;
    }

    public String getConvenientFood() {
        return convenientFood;
    }

    public void setConvenientFood(String convenientFood) {
        this.convenientFood = convenientFood;
    }

    public String getGrainOil() {
        return grainOil;
    }

    public void setGrainOil(String grainOil) {
        this.grainOil = grainOil;
    }

    public String getFlavoring() {
        return flavoring;
    }

    public void setFlavoring(String flavoring) {
        this.flavoring = flavoring;
    }

    public String getDrysaltery() {
        return drysaltery;
    }

    public void setDrysaltery(String drysaltery) {
        this.drysaltery = drysaltery;
    }

    public String getCole() {
        return cole;
    }

    public void setCole(String cole) {
        this.cole = cole;
    }

    public String getChildFood() {
        return childFood;
    }

    public void setChildFood(String childFood) {
        this.childFood = childFood;
    }

    public String getFreshMeat() {
        return FreshMeat;
    }

    public void setFreshMeat(String freshMeat) {
        FreshMeat = freshMeat;
    }

    public String getFreshFish() {
        return FreshFish;
    }

    public void setFreshFish(String freshFish) {
        FreshFish = freshFish;
    }

    public String getPoultry() {
        return poultry;
    }

    public void setPoultry(String poultry) {
        this.poultry = poultry;
    }

    public String getEggs() {
        return eggs;
    }

    public void setEggs(String eggs) {
        this.eggs = eggs;
    }

    public String getPdBean() {
        return pdBean;
    }

    public void setPdBean(String pdBean) {
        this.pdBean = pdBean;
    }

    public String getOtherFood() {
        return otherFood;
    }

    public void setOtherFood(String otherFood) {
        this.otherFood = otherFood;
    }

    public String getBuyerStoreNo() {
        return buyerStoreNo;
    }

    public void setBuyerStoreNo(String buyerStoreNo) {
        this.buyerStoreNo = buyerStoreNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getSalePdCode() {
        return salePdCode;
    }

    public void setSalePdCode(String salePdCode) {
        this.salePdCode = salePdCode;
    }

    public String getSalePd() {
        return salePd;
    }

    public void setSalePd(String salePd) {
        this.salePd = salePd;
    }

    public String getPdSea() {
        return pdSea;
    }

    public void setPdSea(String pdSea) {
        this.pdSea = pdSea;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getFruitsVegetables() {
        return fruitsVegetables;
    }

    public void setFruitsVegetables(String fruitsVegetables) {
        this.fruitsVegetables = fruitsVegetables;
    }

    public String getNonFood() {
        return nonFood;
    }

    public void setNonFood(String nonFood) {
        this.nonFood = nonFood;
    }
}
