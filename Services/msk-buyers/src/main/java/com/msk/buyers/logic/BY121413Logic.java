package com.msk.buyers.logic;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121413Bean;
import com.msk.buyers.bean.BY121413Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.ByMarketTerminalBrSalePd;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * BY121301Logic.
 *
 * @author yuan_chen
 */
@Service
public class BY121413Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121413Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    private String checkForm = "√";

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        static String FIND_MARKET_TERMINAL_STORE = "findMarketTerminalStore";
        static String FIND_MARKET_TERMINAL_PD = "findMarketTerminalPd";
        static String DELETE_SALE_PD = "deleteSalePd";
        static String FIND_DEL_FLG = "findDelFlg";
        static String UPDATE_SALE_PD = "updateSalePd";
        static String INSERT_SALE_PD = "insertSalePd";
        static String reg_Store = "regStore";
        static String FIND_DELETE_STORE = "findDeleteStore";
        static String INSERT_STORE = "insertStore";
        static String MODIFY_MERCHANT_TYPE = "modifyMerchantType";
        static String DELETE_BR_SEARCH = "deleteBrSearch";
        static String MODIFY_REMARK = "modifyRemark";
        static String MODIFY_BASIC_STATUS = "modifyBasicStatus";
    }

    /**
     * 批发市场目标买家一览Logic
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<BY121413Bean> targetSearchList(BasePageParam param) {
        List<BY121413Bean> by121413BeanList = new ArrayList<>();
        List<BY121413Bean> list = super.findList(SqlId.FIND_MARKET_TERMINAL_STORE, param);
        BY121413Bean newBean = new BY121413Bean();
        if (!CollectionUtils.isEmpty(list)) {
            for (BY121413Bean bean : list) {
                if (!StringUtil.equals(newBean.getBuyerStoreNo(), bean.getBuyerStoreNo())) {
                    if (!StringUtil.isEmpty(newBean.getBuyerStoreNo())) {
                        by121413BeanList.add(newBean);
                    }
                    newBean = new BY121413Bean();
                    newBean.setStoreId(bean.getStoreId());
                    newBean.setMarketId(bean.getMarketId());
                    newBean.setBuyerStoreNo(bean.getBuyerStoreNo());
                    newBean.setIsTargetMerchant(bean.getIsTargetMerchant());
                    newBean.setMerchantType(bean.getMerchantType());
                    newBean.setRemark(bean.getRemark());
                }
                this.commonSetTargetBean(newBean, bean);
            }
            by121413BeanList.add(newBean);
        }

        return by121413BeanList;
    }

    /**
     * 非目标买家产品一览
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<BY121413Bean> nonTargetSearchList(BasePageParam param) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        // 非目标买家销售产品
        Map<String, String> buyersMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.NTargetPdClasses.TYPE); /*redisDao.getRedisMapValue(BuyersConstant.NTargetPdClasses.TYPE);*/
        treeMap.putAll(buyersMap);
        List<BY121413Bean> by121413BeanList = new ArrayList<>();
        List<BY121413Bean> list = super.findList(SqlId.FIND_MARKET_TERMINAL_STORE, param);
        BY121413Bean newBean = new BY121413Bean();
        if (!CollectionUtils.isEmpty(list)) {
            for (BY121413Bean bean : list) {
                if (!StringUtil.equals(newBean.getBuyerStoreNo(), bean.getBuyerStoreNo())) {
                    if (!StringUtil.isEmpty(newBean.getBuyerStoreNo())) {
                        by121413BeanList.add(newBean);
                    }
                    newBean = new BY121413Bean();
                    newBean.setStoreId(bean.getStoreId());
                    newBean.setMarketId(bean.getMarketId());
                    newBean.setBuyerStoreNo(bean.getBuyerStoreNo());
                    newBean.setIsTargetMerchant(bean.getIsTargetMerchant());
                    newBean.setMerchantType(bean.getMerchantType());
                    newBean.setRemark(bean.getRemark());
                }
                for (Map.Entry<String, String> map : treeMap.entrySet()) {
                    if (bean.getSalePdCode().indexOf(map.getKey()) >= 0) {
                        this.commonSetNonTargetBean(map, newBean);

                    }

                }
            }
            by121413BeanList.add(newBean);
        }
        return by121413BeanList;
    }


    /**
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByMarketTerminalBrSalePd findSalePd(BaseParam param) {
        logger.info("查找店铺买家下的产品信息");
        ByMarketTerminalBrSalePd salePds = super.findOne(SqlId.FIND_MARKET_TERMINAL_PD, param);
        return salePds;
    }

    /**
     * 将店铺下原有产品的del_flg变为1
     *
     * @param buyerStoreNo
     * @return
     */
    /*@Transactional(readOnly = true)
    public List<ByMarketTerminalBrSalePd> findDelFlg(String buyerStoreNo) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerStoreNo", buyerStoreNo);
        return super.findList(SqlId.FIND_DEL_FLG, param);
    }*/


    /**
     * @param param
     * @return
     */
    @Transactional
    public int updateSalePd(BaseParam param) {
        return super.modify(SqlId.UPDATE_SALE_PD, param);
    }

    /**
     * 目标买家新增
     *
     * @param param
     * @return
     */
    @Transactional
    public int insertSalePd(BaseParam param) {
        logger.info("编辑保存logic");
        return super.save(SqlId.INSERT_SALE_PD, param);
    }

    /**
     * @param by121413Param
     * @return
     */
    @Transactional
    public int insertSore(BY121413Param by121413Param) {
        logger.info("店铺表新增");

        return super.save(SqlId.INSERT_STORE, by121413Param);
    }


    /**
     * 根据买家下拥有的产品种类数量修改买家类型
     *
     * @param param
     * @return
     */
    @Transactional
    public int modifyMerchantType(BaseParam param) {
        logger.info("");
        return super.modify(SqlId.MODIFY_MERCHANT_TYPE, param);
    }

    /**
     * 删除买家市场调查表数据
     *
     * @param deleteParam
     * @return
     */
    @Transactional
    public int deleteBrSearch(BaseParam deleteParam) {
        logger.info("删除调研数据");
      /*  BaseParam deleteParam = new BaseParam();
        deleteParam.getFilterMap().put("storeId", storeId);*/
        return super.modify(SqlId.DELETE_BR_SEARCH, deleteParam);
    }

    /**
     * 删除店铺下对应的所有产品
     *
     * @param deleteParam
     * @return
     */
    @Transactional
    public int deleteSalePd(BaseParam deleteParam) {
        logger.info("删除店铺下所有产品");
   /*     BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerStoreNo", buyerStoreNo);*/
        return super.modify(SqlId.DELETE_SALE_PD, deleteParam);
    }


    /**
     * 验证传入的店铺是否存在
     *
     * @param buyerStoreNo
     * @return
     */
    @Transactional(readOnly = true)
    public int regStore(String buyerStoreNo ,String marketId) {
        logger.info("店铺表新增");
        BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerStoreNo", buyerStoreNo);
        param.getFilterMap().put("marketId", marketId);
        return super.getCount(SqlId.reg_Store, param);
    }

    /**
     * 验证该店铺之前是否被删除
     *
     * @param buyerStoreNo
     * @return
     */
   /* @Transactional(readOnly = true)
    public int findDeleteStore(String buyerStoreNo) {
        logger.info("店铺表新增");
        BaseParam param = new BaseParam();
        param.getFilterMap().put("buyerStoreNo", buyerStoreNo);
        return super.getCount(SqlId.FIND_DELETE_STORE, param);
    }*/

    /**
     * Store修改备注信息
     *
     * @param by121413Param
     * @return
     */
    @Transactional
    public int modifyRemark(BY121413Param by121413Param) {
        logger.info("修改备注");
        return super.modify(SqlId.MODIFY_REMARK, by121413Param);
    }


    /**
     * 修改基本表的状态为1
     *
     * @param param
     * @return
     */
    @Transactional
    public int modifyBasicStatus(BaseParam param) {
        logger.info("修改基本表的状态为1");
        return super.modify(SqlId.MODIFY_BASIC_STATUS, param);
    }

    /**
     * 目标买家判断给BY121413Bean塞值
     *
     * @param newBean
     * @param bean
     */
    public void commonSetTargetBean(BY121413Bean newBean, BY121413Bean bean) {
        if (bean.getSalePdCode().indexOf("01") >= 0) {
            newBean.setPdChicken(checkForm);
        }
        if (bean.getSalePdCode().indexOf("02") >= 0) {
            newBean.setPdDuck(checkForm);
        }
        if (bean.getSalePdCode().indexOf("03") >= 0) {
            newBean.setPdPork(checkForm);
        }
        if (bean.getSalePdCode().indexOf("04") >= 0) {
            newBean.setPdBeefMutton(checkForm);
        }
        if (bean.getSalePdCode().indexOf("05") >= 0) {
            newBean.setPdSea(checkForm);
        }
        if (bean.getSalePdCode().indexOf("06") >= 0) {
            newBean.setPdMeatballs(checkForm);
        }
        if (bean.getSalePdCode().indexOf("07") >= 0) {
            newBean.setPdBacon(checkForm);
        }
        if (bean.getSalePdCode().indexOf("08") >= 0) {
            newBean.setFrozenFood(checkForm);
        }
        if (bean.getSalePdCode().indexOf("09") >= 0) {
            newBean.setQuicklyFreezedSnack(checkForm);
        }
        if (bean.getSalePdCode().indexOf("10") >= 0) {
            newBean.setQuicklyFreezedVegetables(checkForm);
        }
        //粮油
        if (bean.getSalePdCode().indexOf("11") >= 0) {
            newBean.setGrainOil(checkForm);
        }
        if (bean.getSalePdCode().indexOf("12") >= 0) {
            newBean.setFlavoring(checkForm);
        }
        //干货产品
        if (bean.getSalePdCode().indexOf("13") >= 0) {
            newBean.setDrysaltery(checkForm);
        }

        //小菜产品
        if (bean.getSalePdCode().indexOf("14") >= 0) {
            newBean.setCole(checkForm);
        }

        if (bean.getSalePdCode().indexOf("15") >= 0) {
            newBean.setChildFood(checkForm);
        }

        if (bean.getSalePdCode().indexOf("16") >= 0) {
            newBean.setConvenientFood(checkForm);
        }
        //非空判断

        if (newBean.getPdChicken() == null) {
            newBean.setPdChicken("");
        }
        if (newBean.getPdDuck() == null) {
            newBean.setPdDuck("");
        }
        if (newBean.getPdPork() == null) {
            newBean.setPdPork("");
        }
        if (newBean.getPdBeefMutton() == null) {
            newBean.setPdBeefMutton("");
        }
        if (newBean.getPdSea() == null) {
            newBean.setPdSea("");
        }
        if (newBean.getPdMeatballs() == null) {
            newBean.setPdMeatballs("");
        }
        if (newBean.getPdBacon() == null) {
            newBean.setPdBacon("");
        }
        if (newBean.getFrozenFood() == null) {
            newBean.setFrozenFood("");
        }
        if (newBean.getQuicklyFreezedSnack() == null) {
            newBean.setQuicklyFreezedSnack("");
        }
        if (newBean.getQuicklyFreezedVegetables() == null) {
            newBean.setQuicklyFreezedVegetables("");
        }
        if (newBean.getFlavoring() == null) {
            newBean.setFlavoring("");
        }
        if (newBean.getConvenientFood() == null) {
            newBean.setConvenientFood("");
        }
        //
        if (newBean.getDrysaltery() == null) {
            newBean.setDrysaltery("");
        }
        if (newBean.getGrainOil() == null) {
            newBean.setGrainOil("");
        }
        if (newBean.getCole() == null) {
            newBean.setCole("");
        }
        if (newBean.getChildFood() == null) {
            newBean.setChildFood("");
        }
    }

    /**
     * 非目标买家判断给BY121413Bean塞值
     *
     * @param map
     * @param newBean
     */
    public void commonSetNonTargetBean(Map.Entry<String, String> map, BY121413Bean newBean) {
        if (map.getKey().indexOf("1") >= 0) {
            newBean.setFreshMeat(checkForm);
        }
        if (map.getKey().indexOf("2") >= 0) {
            newBean.setFreshFish(checkForm);
        }
        if (map.getKey().indexOf("3") >= 0) {
            newBean.setPoultry(checkForm);
        }
        if (map.getKey().indexOf("4") >= 0) {
            newBean.setEggs(checkForm);
        }
        if (map.getKey().indexOf("5") >= 0) {
            newBean.setFruitsVegetables(checkForm);
        }
        if (map.getKey().indexOf("6") >= 0) {
            newBean.setPdBean(checkForm);
        }
        if (map.getKey().indexOf("7") >= 0) {
            newBean.setOtherFood(checkForm);
        }
        if (map.getKey().indexOf("8") >= 0) {
            newBean.setNonFood(checkForm);
        }
        //非空判断
        if (newBean.getFreshMeat() == null) {
            newBean.setFreshMeat("");
        }
        if (newBean.getFreshFish() == null) {
            newBean.setFreshFish("");
        }
        if (newBean.getPoultry() == null) {
            newBean.setPoultry("");
        }
        if (newBean.getEggs() == null) {
            newBean.setEggs("");
        }
        if (newBean.getFruitsVegetables() == null) {
            newBean.setFruitsVegetables("");
        }
        if (newBean.getPdBean() == null) {
            newBean.setPdBean("");
        }
        if (newBean.getOtherFood() == null) {
            newBean.setOtherFood("");
        }
        if (newBean.getNonFood() == null) {
            newBean.setNonFood("");
        }

    }


}
