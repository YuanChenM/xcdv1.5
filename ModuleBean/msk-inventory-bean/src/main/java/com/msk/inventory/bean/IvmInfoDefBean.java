package com.msk.inventory.bean;

import com.msk.inventory.entity.IvmInfoDef;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * Created by duan_kai on 2016/8/31.
 */
public class IvmInfoDefBean extends IvmInfoDef {

    public static String  TYPE_SUPPLIER = "SUPPLIER";
    public static String  TYPE_OWNER = "OWNER";
    public static String  TYPE_PRODUCT = "PRODUCT";
    public static String  TYPE_SKU = "SKU";
    public static String  TYPE_SELLER = "SELLER";
    public static String  TYPE_PLATFORM = "PLATFORM";

    private List<IvmInfoDefBean> supplierList = new ArrayList<IvmInfoDefBean>();
    private List<IvmInfoDefBean> ownerList = new ArrayList<IvmInfoDefBean>();
    private List<IvmInfoDefBean> prodList = new ArrayList<IvmInfoDefBean>();
    private List<IvmInfoDefBean> skuList = new ArrayList<IvmInfoDefBean>();
    private List<IvmInfoDefBean> sellerList = new ArrayList<IvmInfoDefBean>();
    private List<IvmInfoDefBean> platformList = new ArrayList<IvmInfoDefBean>();

    private List<IvmInfoDefBean> sqlList = new ArrayList<IvmInfoDefBean>();

    public void getListFromMap(Map map){
        Set keys = map.keySet();
        Iterator iter = keys.iterator();
        String tempKey = null;
        IvmInfoDefBean tempInfo = null;
        String[] tempArray = null;
        while(iter.hasNext()){
            tempKey = (String)iter.next();
            tempArray = tempKey.split("_");

            tempInfo = new IvmInfoDefBean();
            tempInfo.setCodeType(tempArray[0]);
            tempInfo.setCodeValue(tempArray[1]);

            if(TYPE_SUPPLIER.equals(tempArray[0])){
                supplierList.add(tempInfo);
            }else if(TYPE_OWNER.equals(tempArray[0])){
                ownerList.add(tempInfo);
            }else if(TYPE_PRODUCT.equals(tempArray[0])){
                prodList.add(tempInfo);
            }else if(TYPE_SKU.equals(tempArray[0])){
                skuList.add(tempInfo);
            }else if(TYPE_SELLER.equals(tempArray[0])){
                sellerList.add(tempInfo);
            }else if(TYPE_PLATFORM.equals(tempArray[0])){
                platformList.add(tempInfo);
            }
        }
    }

    public List<IvmInfoDefBean> getSupplierList() {
        return supplierList;
    }

    public List<IvmInfoDefBean> getOwnerList() {
        return ownerList;
    }

    public List<IvmInfoDefBean> getProdList() {
        return prodList;
    }

    public List<IvmInfoDefBean> getSkuList() {
        return skuList;
    }

    public List<IvmInfoDefBean> getSellerList() {
        return sellerList;
    }

    public List<IvmInfoDefBean> getPlatformList() {
        return platformList;
    }

    public List<IvmInfoDefBean> getSqlList() {
        return sqlList;
    }

    public void setSqlList(List<IvmInfoDefBean> sqlList) {
        this.sqlList = sqlList;
    }
}
