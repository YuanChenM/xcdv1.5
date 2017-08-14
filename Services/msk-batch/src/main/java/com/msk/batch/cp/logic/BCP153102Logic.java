package com.msk.batch.cp.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.cp.bean.BCP153102Bean;
import com.msk.batch.cp.util.BCPUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SoCpAccountBook;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zhang_chi
 */
@Service
public class BCP153102Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(BCP153102Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    public CommonLogic commonLogic;

    interface sqlId {
        String FIND_SO_CP_ACCOUNT_BOOK = "findSoCpAccountBook";
        String SAVE_SO_CP_ACCOUNT_BOOK = "saveSoCpAccountBookPeriod";
        String UPDATE_SO_CP_ACCOUNT_BOOK = "updateSoCpAccountBook";

        String FIND_SO_CP_PAYMENT_PERIOD = "findSoCpPaymentPeriod";
        String SAVE_SO_CP_PAYMENT_PERIOD = "saveSoCpPaymentPeriod";
        String UPDATE_SO_CP_PAYMENT_PERIOD = "updateSoCpPaymentPeriod";
    }

    /**
     * 检查so_cp_account_book和SO_CP_PAYMENT_PERIOD表中是否存在数据，如果不存在，调用接口插入数据
     */
    @Transactional
    public void dataResolve() {
        // 接口查询卖家家信息
        List<BCP153102Bean> paramSeList = BCPUtil.getSellerData();
        // 接口查询买手信息
        List<BCP153102Bean> paramByList = BCPUtil.getBuyerData();
        // 整备数据
        Map<String, SoCpAccountBook> map = this.convertToList(paramSeList, paramByList);
        Map<String, SoCpAccountBook> soCpAccountBookMap = new HashMap<String, SoCpAccountBook>();
        Map<String, SoCpAccountBook> SoCpPaymentPeriodMap = new HashMap<String, SoCpAccountBook>();
        if (map.size() > NumberConst.IntDef.INT_ZERO) {
            soCpAccountBookMap.putAll(map);
            SoCpPaymentPeriodMap.putAll(map);
            this.dataResolveSoCpAccountBook(soCpAccountBookMap);
            this.dataResolveSoCpPaymentPeriod(SoCpPaymentPeriodMap);
        }
    }

    /**
     * 整备数据
     *
     * @param paramSeList
     * @param paramByList
     * @return
     */
    private Map<String, SoCpAccountBook> convertToList(List<BCP153102Bean> paramSeList, List<BCP153102Bean> paramByList) {
        Map<String, SoCpAccountBook> maps = new HashMap<String, SoCpAccountBook>();
        // 卖家数据
        if (CollectionUtils.isNotEmpty(paramSeList)) {
            for (BCP153102Bean bean : paramSeList) {
                String slMainClass = bean.getSlMainClass();
                if (!CapitalPoolConst.RoleType.ROLE_BUYERE.equals(slMainClass)) {
                    SoCpAccountBook soCpAccountBook = new SoCpAccountBook();
                    soCpAccountBook.setUserId(bean.getSlCode());
                    soCpAccountBook.setUserNo(bean.getSlCodeDis());
                    soCpAccountBook.setUserRole(Integer.parseInt(slMainClass));
                    soCpAccountBook.setUserName(bean.getSlName());
                    maps.put(bean.getSlCode() + "_" + slMainClass, soCpAccountBook);
                }
            }
        }
        // 买手数据
        if (CollectionUtils.isNotEmpty(paramByList)) {
            for (BCP153102Bean bean : paramByList) {
                SoCpAccountBook soCpAccountBook = new SoCpAccountBook();
                soCpAccountBook.setUserId(bean.getSlCode());
                if (StringUtil.isNullOrEmpty(bean.getSlCodeDis())) {
                    soCpAccountBook.setUserNo("");
                } else {
                    soCpAccountBook.setUserNo(bean.getSlCodeDis());
                }
                soCpAccountBook.setUserRole(Integer.parseInt(CapitalPoolConst.RoleType.ROLE_BUYERE));
                if (StringUtil.isNullOrEmpty(bean.getSlShowName())) {
                    soCpAccountBook.setUserName("");
                } else {
                    soCpAccountBook.setUserName(bean.getSlShowName());
                }
                maps.put(bean.getSlCode() + "_" + CapitalPoolConst.RoleType.ROLE_BUYERE, soCpAccountBook);
            }
        }
        return maps;
    }

    /**
     * 检查so_cp_account_book表中是否存在数据，如果不存在，调用接口插入数据
     */
    @Transactional
    private void dataResolveSoCpAccountBook(Map<String, SoCpAccountBook> soCpAccountBookMap) {
        BaseParam baseParam = new BaseParam();
        List<SoCpAccountBook> SoCpAccountBookList = super.findList(sqlId.FIND_SO_CP_ACCOUNT_BOOK, baseParam);
        // 如果list为空,插入数据
        if (CollectionUtils.isEmpty(SoCpAccountBookList)) {
            // 新增数据
            saveData(soCpAccountBookMap, "so_cp_account_book", "ACCOUNT_BOOK_ID", sqlId.SAVE_SO_CP_ACCOUNT_BOOK);
        } else {
            // 相同 UserId 有改动的数据
            HashMap<String, SoCpAccountBook> userIdMap = new HashMap<String, SoCpAccountBook>();
            // 将paramMap中userId重复的去掉
            for (SoCpAccountBook bean : SoCpAccountBookList) {
                String userId = bean.getUserId() + "_" + bean.getUserRole();
                SoCpAccountBook value = soCpAccountBookMap.get(userId);
                if (null != value) {
                    if (!bean.getUserNo().equals(value.getUserNo()) || !bean.getUserName().equals(value.getUserName()) ||
                            !bean.getUserRole().equals(value.getUserRole())) {
                        userIdMap.put(userId, value);
                    }
                    soCpAccountBookMap.remove(userId);
                }
            }
            // 将paramMap中剩下的数据 则是新增的用户数据，需插入数据库
            if (soCpAccountBookMap.size() > NumberConst.IntDef.INT_ZERO) {
                // 新增数据
                saveData(soCpAccountBookMap, "so_cp_account_book", "ACCOUNT_BOOK_ID", sqlId.SAVE_SO_CP_ACCOUNT_BOOK);
            }
            // 相同 UserId 有改动的数据 需要修改数据库
            if (userIdMap.size() > NumberConst.IntDef.INT_ZERO) {
                // 修改数据
                updateData(userIdMap, sqlId.UPDATE_SO_CP_ACCOUNT_BOOK);
            }
        }
    }

    /**
     * 检查SO_CP_PAYMENT_PERIOD表中是否存在数据，如果不存在，调用接口插入数据
     */
    @Transactional
    public void dataResolveSoCpPaymentPeriod(Map<String, SoCpAccountBook> soCpAccountBookMap) {
        BaseParam baseParam = new BaseParam();
        List<SoCpAccountBook> soCpPaymentPeriodList = super.findList(sqlId.FIND_SO_CP_PAYMENT_PERIOD, baseParam);
        // 如果list为空,插入数据
        if (CollectionUtils.isEmpty(soCpPaymentPeriodList)) {
            // 新增数据
            saveData(soCpAccountBookMap, "so_cp_payment_period", "PAYMENT_DAYS_ID", sqlId.SAVE_SO_CP_PAYMENT_PERIOD);
        } else {
            // 相同 UserId 有改动的数据
            HashMap<String, SoCpAccountBook> userIdMap = new HashMap<String, SoCpAccountBook>();
            // 将paramMap中userId重复的去掉
            for (SoCpAccountBook bean : soCpPaymentPeriodList) {
                String userId = bean.getUserId() + "_" + bean.getUserRole();
                SoCpAccountBook value = soCpAccountBookMap.get(userId);
                if (null != value) {
                    if (!bean.getUserNo().equals(value.getUserNo()) || !bean.getUserName().equals(value.getUserName()) ||
                            !bean.getUserRole().equals(value.getUserRole())) {
                        userIdMap.put(userId, value);
                    }
                    soCpAccountBookMap.remove(userId);
                }
            }
            // 将paramMap中剩下的数据 则是新增的用户数据，需插入数据库
            if (soCpAccountBookMap.size() > NumberConst.IntDef.INT_ZERO) {
                // 新增数据
                saveData(soCpAccountBookMap, "so_cp_payment_period", "PAYMENT_DAYS_ID", sqlId.SAVE_SO_CP_PAYMENT_PERIOD);
            }
            // 相同 UserId 有改动的数据 需要修改数据库
            if (userIdMap.size() > NumberConst.IntDef.INT_ZERO) {
                // 修改数据
                updateData(userIdMap, sqlId.UPDATE_SO_CP_PAYMENT_PERIOD);
            }
        }
    }


    /**
     * 新增数据
     *
     * @param soCpAccountBookMap
     * @param table
     * @param keyId
     * @param sqlIdKey
     */
    @Transactional
    private void saveData(Map<String, SoCpAccountBook> soCpAccountBookMap, String table, String keyId, String sqlIdKey) {
        Date now = DateTimeUtil.getCustomerDate();
        Set<String> set = soCpAccountBookMap.keySet();
        for (String lg : set) {
            SoCpAccountBook soCpAccountBook = soCpAccountBookMap.get(lg);
            Long maxId = commonLogic.maxId(table, keyId);
            soCpAccountBook.setAccountBookId(maxId);
            soCpAccountBook.setCrtTime(now);
            this.save(sqlIdKey, soCpAccountBook);
        }
    }

    /**
     * 修改数据
     *
     * @param userIdMap
     * @param sqlIdKey
     */
    @Transactional
    private void updateData(Map<String, SoCpAccountBook> userIdMap, String sqlIdKey) {
        Date now = DateTimeUtil.getCustomerDate();
        Set<String> set = userIdMap.keySet();
        for (String lg : set) {
            SoCpAccountBook bean = userIdMap.get(lg);
            bean.setUpdTime(now);
            this.modify(sqlIdKey, bean);
        }
    }

}
