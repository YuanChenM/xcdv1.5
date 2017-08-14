package com.msk.buyers.utils;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.utils.SpringContextUtil;
import com.msk.buyers.logic.BuyerTypeLogic;
import com.msk.core.entity.ByBuyerType;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yuan_chen1 on 2016/7/15.
 */
public final class BuyerTypeUtil {

    private BuyerTypeUtil() {
    }

    private static BuyerTypeUtil buyerTypeUtil = new BuyerTypeUtil();

    public static BuyerTypeUtil getInstance() {
        return buyerTypeUtil;
    }

    private void init() {
        if (!CollectionUtils.isEmpty(byBuyerTypesTypes)) {
            return;
        }
        BaseParam param = new BaseParam();
        BuyerTypeLogic buyerTypeLogic = SpringContextUtil.getBean("buyerTypeLogic", BuyerTypeLogic.class);
        //BuyerTypeLogic buyerTypeLogic = new BuyerTypeLogic();
        byBuyerTypesTypes = buyerTypeLogic.findList(param);

        buyerTypeMap = new TreeMap<>();
        buyerSubTypeMap = new TreeMap<>();
        for (ByBuyerType byBuyerType : byBuyerTypesTypes) {
            addToBuyerTypeMap(byBuyerType.getBuyerType(), byBuyerType.getBuyerTypeName());
            addToBuyerSubTypeMap(byBuyerType.getBuyerType(), byBuyerType.getBuyerSubType(), byBuyerType.getBuyerSubTypeName());
        }
    }

    private void addToBuyerTypeMap(String key, String value){
        if (!StringUtil.isNullOrEmpty(key) && !buyerTypeMap.containsKey(key)) {
            buyerTypeMap.put(key, value);
        }
    }

    private void addToBuyerSubTypeMap(String key, String subKey, String subValue){
        Map subMap;
        if (!StringUtil.isNullOrEmpty(key) && !StringUtil.isNullOrEmpty(subKey)) {
            if(buyerSubTypeMap.containsKey(key)){
                if (!buyerSubTypeMap.get(key).containsKey(subKey)) {
                    buyerSubTypeMap.get(key).put(subKey, subValue);
                }
            }else{
                subMap = new TreeMap<>();
                subMap.put(subKey, subValue);
                buyerSubTypeMap.put(key, subMap);
            }
        }
    }

    private List<ByBuyerType> byBuyerTypesTypes;

    /**
     * Getter method for property <tt>byBuyerTypesTypes</tt>.
     *
     * @return property value of byBuyerTypesTypes
     */
    public List<ByBuyerType> getByBuyerTypesTypes() {
        init();
        return byBuyerTypesTypes;
    }

    private Map<String, String> buyerTypeMap;

    /**
     * Getter method for property <tt>buyerTypeMap</tt>.
     *
     * @return property value of buyerTypeMap
     */
    public Map<String, String> getBuyerTypeMap() {
        init();
        return buyerTypeMap;
    }

    private Map<String, Map<String, String>> buyerSubTypeMap;

    /**
     * Getter method for property <tt>buyerSubTypeMap</tt>.
     *
     * @return property value of buyerSubTypeMap
     */
    public Map<String, Map<String, String>> getBuyerSubTypeMap() {
        init();
        return buyerSubTypeMap;
    }
}
