package com.msk.buyers.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BY121413Bean;
import com.msk.buyers.bean.BY121413Param;
import com.msk.buyers.logic.BY121413Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.ByMarketTerminalBrSalePd;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 *
 */
@Controller
@RequestMapping("BY12141301")
public class BY12141301Controller extends BaseController {


    @Autowired
    private BY121413Logic by121413Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY12141301Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, BY121413Param basePageParam) {
        logger.debug("获取店铺拥有的产品list");
        String marketId = basePageParam.getMarketId();
        model.addAttribute("marketId", marketId);
        basePageParam.getFilterMap().put("marketId", marketId);
        String buyerStoreNo = basePageParam.getBuyerStoreNo();
        model.addAttribute("buyerStoreNo", buyerStoreNo);
        basePageParam.getFilterMap().put("buyerStoreNo", buyerStoreNo);

        Long storeId =  basePageParam.getStoreId();
        basePageParam.getFilterMap().put("storeId", storeId);
        model.addAttribute("storeId", storeId);


        List codeList = new ArrayList();
        if (StringUtil.isEmpty(buyerStoreNo)) {
            codeList = null;
        } else {
            //获取店铺拥有的产品
            ByMarketTerminalBrSalePd salePds = by121413Logic.findSalePd(basePageParam);
            String codes = salePds.getSalePdCode();
            String[] saleCodes = codes.split(",");
            //截取产品code
            for (int s = 0; s < saleCodes.length; s++) {
                codeList.add(saleCodes[s]);
            }
        }
        List<BY121413Bean> pdClaShowList = new ArrayList<>();
        if (basePageParam.getIsTargetMerchant().equals("0")) {
        logger.debug("获取非目标买家所有售产品");
            // 非目标买家销售产品
        Map<String, String> NTargetPdClassesMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.NTargetPdClasses.TYPE);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(NTargetPdClassesMap);
            for (Map.Entry<String, String> map : treeMap.entrySet()) {
                BY121413Bean been = new BY121413Bean();
                been.setSalePdCode(map.getKey());
                been.setSalePd(map.getValue());
                been.setIsChecked("0");
                if (!CollectionUtils.isEmpty(codeList)) {
                    for (int j = 0; j < codeList.size(); j++) {
                        if (map.getKey().equals(codeList.get(j))) {
                            been.setIsChecked("1");
                            break;
                        }
                    }
                }
                pdClaShowList.add(been);
            }
        } else {
        logger.debug("获取目标买家所有售产品");
            // 销售产品
            PDInfoParam pdInfoParam = new PDInfoParam();
            List<PDInfoResult> pdClaCommList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
            logger.debug("");
            for (int i = 0; i < pdClaCommList.size(); i++) {
                BY121413Bean been = new BY121413Bean();
                been.setSalePdCode(pdClaCommList.get(i).getClassesCode());
                been.setSalePd(pdClaCommList.get(i).getClassesName().trim());
                been.setIsChecked("0");
                if (!CollectionUtils.isEmpty(codeList)) {
                    for (int j = 0; j < codeList.size(); j++) {
                        if (pdClaCommList.get(i).getClassesCode().equals(codeList.get(j))) {
                            been.setIsChecked("1");
                            break;
                        }
                    }
                }
                pdClaShowList.add(been);
            }
        }

        model.addAttribute("pdClaShowList", pdClaShowList);
        //编辑按钮时候,店铺号是否可编辑,0不可编辑
        model.addAttribute("isChecked", basePageParam.getIsChecked());
        //目标市场买家标志  目标买家=1
        model.addAttribute("isTargetMerchant", basePageParam.getIsTargetMerchant());
        //新增按钮标志
        model.addAttribute("addFlg", basePageParam.getAddFlg());
        model.addAttribute("remark", basePageParam.getRemark());
        return "buyers/BY12141301";
    }


    /**
     * 编辑保存
     *
     * @param by121413Param
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public
    @ResponseBody
    String updateStoreAndSalePd(BY121413Param by121413Param) {
        //获取前台传的storeNumber
        String buyerStoreNo= by121413Param.getStoreNumber();
        String returnFlg = null;
        int returnLine = 0;
        by121413Param.setBuyerStoreNo(buyerStoreNo);
       String marketId=by121413Param.getMarketId();
        logger.info("插入备注");
        this.commonAddOrModify(by121413Param);
        by121413Logic.modifyRemark(by121413Param);
        String[] pdClass = by121413Param.getSalePds();
        Map<String, String> pdClassMap = new HashMap<>();
        String names = null;
        String codes = null;
        if (pdClass.length > 0) {
            logger.info("修该买家类型开始");
            //目标买家才更该买家类型
            if (by121413Param.getIsTargetMerchant().equals("1")) {
                this.modifyMerchantType(pdClass, by121413Param.getStoreId(), marketId);
            }
            for (int s = 0; s < pdClass.length; s++) {
                String[] pdCla = pdClass[s].split("_");
                pdClassMap.put(pdCla[0], pdCla[1]);
                if (!StringUtil.isEmpty(codes) && !StringUtil.isEmpty(names)) {
                    codes = codes + pdCla[0] + ',';
                    names = names + pdCla[1] + ',';
                } else {
                    codes = pdCla[0] + ',';
                    names = pdCla[1] + ',';
                }
            }
            codes = codes.substring(0, codes.length() - 1);
            names = names.substring(0, names.length() - 1);
            by121413Param.getFilterMap().put("salePdCode", codes);
            by121413Param.getFilterMap().put("salePd", names);
            by121413Param.getFilterMap().put("buyerStoreNo", by121413Param.getBuyerStoreNo());
            by121413Param.getFilterMap().put("storeId", by121413Param.getStoreId());

            //待确认传店铺id还是no
            returnLine = by121413Logic.updateSalePd(by121413Param);
            if (returnLine > 0) {
                by121413Param.getFilterMap().put("updId", by121413Param.getUpdId());
                by121413Param.getFilterMap().put("updTime",by121413Param.getUpdTime() );
                by121413Param.getFilterMap().put("isMerchantNew", "1");
                by121413Param.getFilterMap().put("marketId", marketId);
                by121413Param.getFilterMap().put("isMarketNew", "1");
                by121413Param.getFilterMap().put("isPhaseNew", "1");
                by121413Logic.modifyBasicStatus(by121413Param);
                returnFlg = "编辑成功";
            } else {
                returnFlg = "编辑失败";
            }
        }
        return returnFlg;
    }


    /**
     * 新增按钮
     *
     * @param by121413Param
     * @param by121413Param
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveStore(BY121413Param by121413Param) {
        logger.info("新增店铺买家");
        String marketId = by121413Param.getMarketId();
        by121413Param.getFilterMap().put("marketId", marketId);
        by121413Param.setIsTargetMerchant(by121413Param.getIsTargetMerchant());
        String buyerStoreNo = by121413Param.getBuyerStoreNo();
        int regLine = by121413Logic.regStore(buyerStoreNo,marketId);
        String returnFlg = null;
        if (regLine == 1) {
            //进行编辑操作
            returnFlg = "该店铺已存在,请查证";
        } else {
                this.commonAddOrModify(by121413Param);
                Long storeId = commonLogic.maxId("BY_MARKET_TERMINAL_BR_SEARCH", "STORE_ID");
                by121413Param.setStoreId(storeId);
                by121413Logic.insertSore(by121413Param);
                //买家下新增产品
                String[] pdClass = by121413Param.getSalePds();
                Map<String, String> pdClassMap = new HashMap<>();
                if (by121413Param.getIsTargetMerchant().equals("1")) {
                    this.modifyMerchantType(pdClass, storeId ,marketId);
                }
                String names = null;
                String codes = null;
                for (int s = 0; s < pdClass.length; s++) {
                    String[] pdCla = pdClass[s].split("_");
                    pdClassMap.put(pdCla[0], pdCla[1]);
                    if (!StringUtil.isEmpty(codes) && !StringUtil.isEmpty(names)) {
                        codes = codes + pdCla[0] + ',';
                        names = names + pdCla[1] + ',';
                    } else {
                        codes = pdCla[0] + ',';
                        names = pdCla[1] + ',';
                    }
                }
                codes = codes.substring(0, codes.length() - 1);
                names = names.substring(0, names.length() - 1);
                int resultLine = 0;
                resultLine = this.commonSaveSalePd(by121413Param, codes, names);
                if (resultLine == NumberConst.IntDef.INT_ONE) {
                    this.commonAddOrModify(by121413Param);
                    by121413Param.getFilterMap().put("isMerchantNew", "1");
                    by121413Param.getFilterMap().put("isMarketNew", "1");
                    by121413Param.getFilterMap().put("isPhaseNew", "1");
                    by121413Param.getFilterMap().put("updId",by121413Param.getUpdId());
                    by121413Param.getFilterMap().put("updTime",by121413Param.getUpdTime());

                    by121413Logic.modifyBasicStatus(by121413Param);
                    returnFlg = "新增成功";
                }

        }
        return returnFlg;
    }

    /**
     * 插入店铺买家下产品信息共同方法
     *
     * @param by121413Param
     * @param codes
     * @param names
     * @return
     */
    public int commonSaveSalePd(BY121413Param by121413Param, String codes, String names) {
        Long id = commonLogic.maxId("BY_MARKET_TERMINAL_BR_SALE_PD", "STORE_ID");
        by121413Param.setId(id);
        by121413Param.setSalePdCode(codes);
        by121413Param.setSalePd(names);
        this.commonAddOrModify(by121413Param);
        return by121413Logic.insertSalePd(by121413Param);

    }


    /**
     * 修该买家类型共同
     *
     * @param pdClass
     */
    public void modifyMerchantType(String[] pdClass, Long storeId ,String marketId) {
        BaseParam MerchantTypeParam = new BaseParam();
        //
        this.commonAddOrModify(MerchantTypeParam);
        MerchantTypeParam.getFilterMap().put("storeId", storeId);
        MerchantTypeParam.getFilterMap().put("marketId", marketId);
        logger.info("修该买家类型开始");
        if (pdClass.length == NumberConst.IntDef.INT_ONE) {
            MerchantTypeParam.getFilterMap().put("merchantType", "1");
        }
        if (pdClass.length == NumberConst.IntDef.INT_TWO || pdClass.length == NumberConst.IntDef.INT_THREE) {
            MerchantTypeParam.getFilterMap().put("merchantType", "2");

        }
        if (pdClass.length >= NumberConst.IntDef.INT_FOUR) {
            MerchantTypeParam.getFilterMap().put("merchantType", "3");
        }
        //更该买家类型
        by121413Logic.modifyMerchantType(MerchantTypeParam);
    }


    /**
     * 新增或者修该时候对于共同字段的set值
     *
     * @param param
     */
    public void commonAddOrModify(BaseParam param) {
      /*  super.setCommonParam(param);*/
        BaseEntity entity = new BaseEntity();
        super.setCommonParam(entity);
        Date currentDate = DateTimeUtil.getCustomerDate();
        entity.setCrtTime(currentDate);
        entity.setUpdTime(currentDate);
        entity.setActTime(currentDate);

        param.setActTime(entity.getActTime());
        param.setCrtTime(entity.getCrtTime());
        param.setUpdTime(entity.getUpdTime());
        param.setActId(entity.getActId());
        param.setCrtId(entity.getCrtId());
        param.setUpdId(entity.getUpdId());

    }


}
