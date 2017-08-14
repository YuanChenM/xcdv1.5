package com.msk.buyers.controller.comm;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.BY121304Bean;
import com.msk.buyers.bean.IBY121202RsParam;
import com.msk.buyers.logic.*;
import com.msk.common.base.BaseController;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByBuyerRecAddr;
import com.msk.core.entity.ByBuyerRecTime;
import com.msk.core.entity.ByBuyerSalestarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * 买家收货信息画面
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("by/baseBuyerRecInfo")
public class BYBaseBuyerRecController extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BYBaseBuyerRecController.class);

    @Autowired
    private BY121304Logic by121304Logic;
    @Autowired
    private IBY121209Logic iby121209Logic;
    @Autowired
    private IBY121208Logic iby121208Logic;
    @Autowired
    private IBY121204Logic iby121204Logic;
    @Autowired
    private IBY121203Logic iby121203Logic;
    @Autowired
    private IBY121202Logic iby121202Logic;
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家详细画面");
        model.addAttribute("buyerId", buyerId);

        //买家基本信息
        IBY121202RsParam buyerDetail = by121304Logic.findBuyerDetail(buyerId);
        model.addAttribute("buyerDetail", buyerDetail);

        // 买家收货地址信息
        List<ByBuyerRecAddr> recAddrList = by121304Logic.buyerReceiveAddrFind(buyerId);
        if(CollectionUtils.isEmpty(recAddrList)){
            ByBuyerRecAddr buyerRecAdd = new ByBuyerRecAddr();
            ByBuyerRecAddr buyerRecAdd1 = new ByBuyerRecAddr();
            buyerRecAdd.setBuyerId(buyerId);
            buyerRecAdd1.setBuyerId(buyerId);
            buyerRecAdd.setReceiveAddr("");
            recAddrList.add(buyerRecAdd);
            buyerRecAdd1.setReceiveAddr("");
            recAddrList.add(buyerRecAdd1);
        }
        model.addAttribute("recAddrList", recAddrList);
        // 买家收货时间信息
        List<ByBuyerRecTime> recTimeList = by121304Logic.buyerReceiveTimeFind(buyerId);
        model.addAttribute("recTimeList", recTimeList);
        // 收货时间
        //Map<String,String> recTimeCommMap = CodeMasterManager.findCodeMasterMap("ReceivePeriodType");
        TreeMap<String,String> treeMap = new TreeMap<>();
        //treeMap.putAll(recTimeCommMap);
        List<BY121304Bean> recTimeShowList = new ArrayList<>();
        for (String key :treeMap.keySet()){
            BY121304Bean recTime = new BY121304Bean();
            recTime.setRecPerType(key);
            recTime.setTimeDescribe(treeMap.get(key));
            recTime.setIsChecked("0");
            for (int j = 0; j < recTimeList.size(); j++) {
                if (key.equals(recTimeList.get(j).getRecPerType())) {
                    recTime.setIsChecked("1");
                    break;
                }
            }
            recTimeShowList.add(recTime);
        }
        model.addAttribute("recTimeShowList", recTimeShowList);
        // 买家销售对象信息
        List<ByBuyerSalestarget> salestargetList = by121304Logic.buyerSalesTargetFind(buyerId);
        model.addAttribute("salestargetList", salestargetList);
        // 销售对象
        Map<String,String> salestargetCommMap = CodeMasterManager.findCodeMasterMap("SalesTarget");
        treeMap.clear();
        treeMap.putAll(salestargetCommMap);
        List<BY121304Bean> salestargetShowList = new ArrayList<>();
        for (String key:treeMap.keySet()){
            BY121304Bean salestarget = new BY121304Bean();
            salestarget.setSalesTargetType(key);
            salestarget.setSalesTargetName(treeMap.get(key));
            salestarget.setIsChecked("0");
            for (int j = 0; j < salestargetList.size(); j++) {
                if (key.equals(salestargetList.get(j).getSalesTargetType())) {
                    salestarget.setIsChecked("1");
                    break;
                }
            }
            salestargetShowList.add(salestarget);
        }
        model.addAttribute("salestargetShowList", salestargetShowList);

        return "buyers/comm/BASE_BUYER_REC_INFO";
    }

    @RequestMapping(value = "update",
        method = RequestMethod.POST)
    public String recInfoUpdate(BY121304Bean by121304Bean, Model model) {
        logger.debug("买家收货销售对象更新");
        Date currentDate = DateTimeUtil.getCustomerDate();
        // 更新买家收货地址
        List<ByBuyerRecAddr> recAddrList = by121304Bean.getRecAddr();
        if (!CollectionUtils.isEmpty(recAddrList)) {
            for (int i = 0; i < recAddrList.size(); i++) {
                super.setCommonParam(recAddrList.get(i));
                recAddrList.get(i).setUpdTime(currentDate);
                recAddrList.get(i).setBuyerId(by121304Bean.getBuyerId());
            }
        }
        int updateRecAddrCount = iby121208Logic.buyerReceiveAddrModify(recAddrList, new ArrayList<ByBuyerRecAddr>());
        // 更新买家收货时间
        List<ByBuyerRecTime> recTimeList = new ArrayList<>();
        // 收货时间
        //Map<String,String> recTimeCommMap = CodeMasterManager.findCodeMasterMap("ReceivePeriodType");
        String[] receiveTime = by121304Bean.getSelectRecTime();
        if (receiveTime == null) {
            ByBuyerRecTime byBuyerRecTime = new ByBuyerRecTime();
            super.setCommonParam(byBuyerRecTime);
            byBuyerRecTime.setBuyerId(by121304Bean.getBuyerId());
            byBuyerRecTime.setRecPerType("");
            byBuyerRecTime.setTimeDescribe("");
            recTimeList.add(byBuyerRecTime);
        } else {
            for (int i = 0; i < receiveTime.length; i++) {
                /*if (recTimeCommMap.containsKey(receiveTime[i])){
                    ByBuyerRecTime byBuyerRecTime = new ByBuyerRecTime();
                    super.setCommonParam(byBuyerRecTime);
                    byBuyerRecTime.setBuyerId(by121304Bean.getBuyerId());
                    byBuyerRecTime.setRecPerType(receiveTime[i]);
                    byBuyerRecTime.setTimeDescribe(recTimeCommMap.get(receiveTime[i]));
                    recTimeList.add(byBuyerRecTime);
                }*/
            }
        }

        int updateRecTimeCount = iby121209Logic.buyerReceiveTimeModify(recTimeList);
        // 更新买家销售对象
        List<ByBuyerSalestarget> salestargetList = new ArrayList<>();
        // 收货时间
        Map<String,String> salestargetMap = CodeMasterManager.findCodeMasterMap("SalesTarget");
        String[] salestarget = by121304Bean.getSalesTarget();
        if (salestarget == null) {
            ByBuyerSalestarget byBuyerSalestarget = new ByBuyerSalestarget();
            setCommonFiled(byBuyerSalestarget);
            byBuyerSalestarget.setBuyerId(by121304Bean.getBuyerId());
            byBuyerSalestarget.setSalesTargetType("");
            byBuyerSalestarget.setSalesTargetName("");
            salestargetList.add(byBuyerSalestarget);
        } else {
            for (int i = 0; i < salestarget.length; i++) {
                 if(salestargetMap.containsKey(salestarget[i])){
                     ByBuyerSalestarget byBuyerSalestarget = new ByBuyerSalestarget();
                     setCommonFiled(byBuyerSalestarget);
                     byBuyerSalestarget.setBuyerId(by121304Bean.getBuyerId());
                     byBuyerSalestarget.setSalesTargetType(salestarget[i]);
                     byBuyerSalestarget.setSalesTargetName(salestargetMap.get(salestarget[i]));
                     salestargetList.add(byBuyerSalestarget);
                 }
            }
        }
        int salesTargetCount = iby121204Logic.buyerSalesTargetModify(salestargetList);

        return super.forward("/BY121304/init/" + by121304Bean.getBuyerId() + "");
    }

    /**
     * //设置共通字段
     * @param entity
     */
    public void setCommonFiled(BaseEntity entity){
        super.setCommonParam(entity);
        Date currentDate = DateTimeUtil.getCustomerDate();
        entity.setCrtTime(currentDate);
        entity.setUpdTime(currentDate);
        entity.setActTime(currentDate);
    }


}
