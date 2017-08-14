package com.msk.so.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import com.msk.cashPooling.bean.FundDetail;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlSeller;
import com.msk.core.entity.SoCpSelCharging;
import com.msk.so.bean.ISO153103Param;
import com.msk.so.bean.SOCp153141Bean;
import com.msk.so.utils.SORestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 卖家计费项接口
 *
 * @author Qiu_wenting
 * @version 1.0
 */
@Service
public class ISO153103Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153103Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private TransactionLogic transLogic;

    @Autowired
    private SO153141Logic so153141logic;

    /**
     * 新增卖家计费项
     *
     * @param request 请求参数（卖家计费项）
     */
    @Transactional
    public void createSelCharging(RsRequest<ISO153103Param> request) {
        logger.debug("记录卖家计费项", request);
        if (null != request.getParam()) {
            List<SoCpSelCharging> selChargingList = request.getParam().getSoCpSelChargingList();
            List<String> slCodeList = new ArrayList<String>();
            List<String> slCodeSeList = new ArrayList<String>();
            List<SlSeller> slCodeByList = new ArrayList<SlSeller>();
            if (CollectionUtils.isNotEmpty(selChargingList)) {
                for (SoCpSelCharging selCharging : selChargingList) {

                    // 创建必须项校验的对象
                    HashMap<String, Object> checkObj = new HashMap<String, Object>();
                    checkObj.put("交易编码", selCharging.getTransCode());
                    checkObj.put("交易类型", selCharging.getTransType());
                    checkObj.put("订单ID", selCharging.getOrderId());
                    checkObj.put("收款方ID", selCharging.getBusinessMainId());
                    checkObj.put("收款方名称", selCharging.getBusinessMainName());
                    checkObj.put("收款方角色", selCharging.getBusinessMainRole());
                    checkObj.put("付款方ID", selCharging.getBusinessAssistantId());
                    checkObj.put("付款方编码", selCharging.getBusinessAssistantCode());
                    checkObj.put("付款方名称", selCharging.getBusinessAssistantName());
                    checkObj.put("付款方角色", selCharging.getBusinessAssistantRole());
                    checkObj.put("配送单编码", selCharging.getDeliveryCode());
                    checkObj.put("签收日期", selCharging.getDeliveryTime());
                    checkObj.put("配送金额", selCharging.getShippingAmount());
                    checkObj.put("签收金额", selCharging.getPaidAmount());

                    // 必须项校验
                    ValidatorUtils.validatorRequired(checkObj);

                    if (CapitalPoolConst.RoleType.ROLE_BUYERE.equals(selCharging.getBusinessMainRole()+"")) {
                        SlSeller slSeller = new SlSeller();
                        slSeller.setSlCode(selCharging.getBusinessMainId());
                        slCodeByList.add(slSeller);
                    } else {
                        slCodeSeList.add(selCharging.getBusinessMainId());
                    }
                    slCodeList.add(selCharging.getBusinessMainId());
                }

                // 验证 USER_ID 在 so_cp_account_book 是否存在
                List<SOCp153141Bean> soCp153141BeanList = so153141logic.checkUserId(slCodeList);
                Map<String, String> userIdMap = new HashMap<String, String>();
                if (CollectionUtils.isNotEmpty(soCp153141BeanList)) {
                    for (SOCp153141Bean bean : soCp153141BeanList) {
                        userIdMap.put(bean.getUserId(), bean.getUserId());
                    }
                }

                // HashMap<String, String> slCodeDisMap = SORestUtil.getSlCodeDisList(slCodeLsit);

                HashMap<String, String> slCodeDisMap = new HashMap<String, String>();
                // 查询卖家显示编码
                if (CollectionUtils.isNotEmpty(slCodeSeList)) {
                    HashMap<String, String> slCodeDisSeMap = SORestUtil.getSlCodeDisList(slCodeSeList, null,null);
                    if (slCodeDisSeMap.size() > NumberConst.IntDef.INT_ZERO) {
                        slCodeDisMap.putAll(slCodeDisSeMap);
                    }
                }
                // 查询买手显示编码
                if (CollectionUtils.isNotEmpty(slCodeByList)) {
                    HashMap<String, String> slCodeDisSeMap = SORestUtil.getSlCodeDisList(null,slCodeByList, CapitalPoolConst.RoleType.ROLE_BUYERE);
                    if (slCodeDisSeMap.size() > NumberConst.IntDef.INT_ZERO) {
                        slCodeDisMap.putAll(slCodeDisSeMap);
                    }
                }

                for (SoCpSelCharging selCharging : selChargingList) {

                    // 主键
                    Long sellerChargingId = commonLogic.maxId("SO_CP_SEL_CHARGING", "SEL_CHARGING_ID");
                    selCharging.setSelChargingId(sellerChargingId);

                    // 收款方编码
                    selCharging.setBusinessMainCode(slCodeDisMap.get(selCharging.getBusinessMainId()));

                    // 创建者、更新者、生效者
                    Date now = DateTimeUtil.getCustomerDate();
                    selCharging.setCrtId(request.getLoginId());
                    selCharging.setCrtTime(now);
                    selCharging.setUpdId(request.getLoginId());
                    selCharging.setUpdTime(now);
                    selCharging.setActId(request.getLoginId());
                    selCharging.setActTime(now);

                    transLogic.saveSelCharging(selCharging);

                    // 新增 so_cp_account_book
                    String userId = userIdMap.get(selCharging.getBusinessMainId());
                    if (StringUtil.isEmpty(userId)) {
                        SOCp153141Bean soCp153141Bean = new SOCp153141Bean();
                        // 主键
                        Long accountBookId = commonLogic.maxId("so_cp_account_book", "ACCOUNT_BOOK_ID");
                        soCp153141Bean.setAccountBookId(accountBookId);
                        soCp153141Bean.setUserId(selCharging.getBusinessMainId());
                        soCp153141Bean.setUserNo(selCharging.getBusinessMainCode());
                        soCp153141Bean.setUserRole(selCharging.getBusinessMainRole());
                        soCp153141Bean.setUserName(selCharging.getBusinessMainName());
                        soCp153141Bean.setCrtId(request.getLoginId());
                        soCp153141Bean.setUpdId(request.getLoginId());
                        soCp153141Bean.setActId(request.getLoginId());
                        so153141logic.save(soCp153141Bean);
                    }
                }
            }
        }
    }
}
