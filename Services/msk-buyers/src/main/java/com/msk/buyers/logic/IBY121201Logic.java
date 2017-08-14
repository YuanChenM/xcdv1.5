package com.msk.buyers.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121201RsParam;
import com.msk.buyers.bean.IBY121202RsBean;
import com.msk.buyers.bean.IBY121202RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * IBY121201Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121201Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121201Logic.class);

    @Autowired
    private IBY121202Logic iby121202Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        // 查询账号表
        static String SQL_FIND_ACCOUNT_INFO = "findAccountInfo";
        // 插入主表
        static String SQL_SAVE_BUYER = "saveBuyer";
        // 插入账号表
        static String SQL_SAVE_ACCOUNT_INFO = "saveAccountInfo";
        // 重置密码
        static String SQL_RESET_PASSWORD = "resetPassword";
        // 通过买家id,买家账号,买家旧密码,更新新密码
        static String SQL_UPDATE_PASSWORD = "modifyPassword";
        // 前台手机验证成功后,通过手机号直接修改密码
        static String SQL_UPDATE_PASSWORD_TELNO = "modifyPasswordByTelNo";
        // 通路人员登录
        static String SQL_FIND_ACCESS_INFO = "findAccessInfo";
        //查询账号是否存在
        static String SQL_COUNT_NAME_ACCESS_INFO = "countAccountNameInfo";
        //查询手机号是否存在
        static String SQL_COUNT_TEL_ACCESS_INFO = "countAccountTelInfo";
        //根据账号名获取账号
        static String SQL_FIND_ACCOUNT_BY_NAME_AND_PWD = "findAccountByNameAndPwd";
        //根据手机号名获取账号信息
        static String SQL_FIND_ACCOUNT_BY_TEL = "findBuyerAccountByTel";

        static String QUERY_BUYERY_BASIC_INFO_BY_TEL = "queryBuyeryBasicInfoByTel";

        static String FIND_BUYER_CODE = "findBuyerCode";

        static String FIND_BUYER_ID = "findBuyerId";
        //查询买家账号是否和其他买家名称相同
        static String SQL_FIND_BUYER_NAME_NUM = "findBuyerNameNum";

    }


    /**
     * (non-Javadoc)
     *
     * @see
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 买家账号和基础信息注册
     *
     * @param param
     * @return
     */
    @Transactional
    public String buyerRegister(IBY121201RsParam param) {
        String message = "";
        // 根据手机号码查询买家的账号信息
        BaseParam findNameParam = new BaseParam();
        BaseParam findTelParam = new BaseParam();
        int nameNum = 0;
        if (!StringUtil.isNullOrEmpty(param.getAccountName())) {
            findNameParam.setFilter("accountName", param.getAccountName());
            nameNum = super.getCount(SqlId.SQL_COUNT_NAME_ACCESS_INFO, findNameParam);
        }
        findTelParam.setFilter("telNo", param.getTelNo());
        int telNum = super.getCount(SqlId.SQL_COUNT_TEL_ACCESS_INFO, findTelParam);
        int buyerNameNum = super.getCount(SqlId.SQL_FIND_BUYER_NAME_NUM, findNameParam);
        // 如果查询不到账号信息，则将注册信息插入买家主表(By_buyer)和买家账号表(By_Account)
        if (nameNum == 0 && telNum == 0 && buyerNameNum == 0) {
            UUID uuid = UUID.randomUUID();
            IBY121201RsParam saveParam = new IBY121201RsParam();
            saveParam.setBuyerId(String.valueOf(uuid));
            /*saveParam.setRegisterSource(param.getRegisterSource());*/
            /** Modif for Bug #3522 at 2016/10/31 by yuan_zhifei Start*/
            if (!StringUtil.isNullOrEmpty(param.getRegisterSource())) {
                //saveParam.setRegisterSource(BuyersConst.BuyerRegisterWay.SYSTEMREGIST);
                saveParam.setRegisterSource(param.getRegisterSource());
            } else {
                saveParam.setRegisterSource(BuyersConst.BuyerRegisterWay.SYSTEMREGIST);
            }
            /** Modif for Bug #3522 at 2016/10/31 by yuan_zhifei End*/
            saveParam.setLgcsAreaCode(param.getLgcsAreaCode());
            saveParam.setCityCode(param.getCityCode());
            saveParam.setDistrictCode(param.getDistrictCode());

            saveParam.setCrtId(param.getCrtId());
            saveParam.setCrtTime(param.getCrtTime());
            saveParam.setUpdId(param.getUpdId());
            saveParam.setUpdTime(param.getUpdTime());
            saveParam.setActId(param.getActId());
            saveParam.setActTime(param.getActTime());

            // 先插入主表
            super.save(SqlId.SQL_SAVE_BUYER, saveParam);

            saveParam.setTelNo(param.getTelNo());
            Long id = commonLogic.maxId("by_buyer_account", "ID");
            saveParam.setId(id);
            // 如果账号名称不输入，则默认插入手机号码
            if (StringUtil.isNullOrEmpty(param.getAccountName())) {
                saveParam.setAccountName(param.getTelNo());
            } else {
                saveParam.setAccountName(param.getAccountName());
            }
            // 如果账号密码不输入，则默认插入手机号码
            if (StringUtil.isNullOrEmpty(param.getAccountPass())) {
                saveParam.setAccountPass(param.getTelNo().substring(param.getTelNo().length() - 8, param.getTelNo().length()));
            } else {
                saveParam.setAccountPass(param.getAccountPass());
            }
            // 插入账号表
            super.save(SqlId.SQL_SAVE_ACCOUNT_INFO, saveParam);

            IBY121202RsParam iby121202RsParam = new IBY121202RsParam();
            iby121202RsParam.setBuyerId(saveParam.getBuyerId());
            iby121202RsParam.setCrtId(param.getCrtId());
            iby121202RsParam.setCrtTime(param.getCrtTime());
            iby121202RsParam.setUpdId(param.getUpdId());
            iby121202RsParam.setUpdTime(param.getUpdTime());
            iby121202RsParam.setActId(param.getActId());
            iby121202RsParam.setActTime(param.getActTime());
            int result = 0;
            int modifyCount = iby121202Logic.updateParams(iby121202RsParam);
            if (modifyCount == NumberConst.IntDef.INT_ONE) {
                //更新买家产品信息表
                int count = iby121202Logic.findPdClaCount(iby121202RsParam);
                if (count <= NumberConst.IntDef.INT_ZERO) {
                    long maxId = commonLogic.maxId("BY_BUYER_PD_CLA", "ID");
                    iby121202RsParam.setId(maxId);
                    result = iby121202Logic.savePdCla(iby121202RsParam);
                } else {
                    message = "买家产品信息表存在该数据";
                }
            } else {
                message = "买家基本信息更新失败";
            }

            if (modifyCount != NumberConst.IntDef.INT_ZERO) {
                IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
                BrOBuyerInfo brOBuyerInfo = new BrOBuyerInfo();
                List<BrOBuyerPdCla> brOBuyerPdClaList = new ArrayList<>();
                brOBuyerInfo = iby121202Logic.findBaseBuyerInfo(iby121202RsParam);
                brOBuyerPdClaList = iby121202Logic.findBuyerPdCla(iby121202RsParam);
                iby121202RsBean.setBrOBuyerInfo(brOBuyerInfo);
                iby121202RsBean.setBrOBuyerPdClaList(brOBuyerPdClaList);
                RsRequest<IBY121202RsBean> request = new RsRequest<>();
                request.setSiteCode("1");
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setParam(iby121202RsBean);
//             String url = "http://10.20.16.42:8180/api/br/buyerReportInfo/_update";
                String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
                RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });
                if (response == null || response.getMessage().equals("F") || response.getResult() == NumberConst.IntDef.INT_ZERO) {
                    message = ("买家买家池信息更新失败");
                }

            }

        } else if (telNum > 0) {
            /** Modif for Bug# at 2016/09/13 by zhao_chen Start*/
            BaseParam findBuyerCodeParam = new BaseParam();
            findBuyerCodeParam.getFilterMap().put("accountName", param.getTelNo());
            ByBuyerAccount account = super.findOne(SqlId.FIND_BUYER_ID, findBuyerCodeParam);
            if (account != null) {
                String buyerId = account.getBuyerId();
                findBuyerCodeParam.getFilterMap().put("buyerId", account.getBuyerId());
                ByBuyerBasicInfo basicInfo = super.findOne(SqlId.FIND_BUYER_CODE, findBuyerCodeParam);
                if (basicInfo != null) {
                    if (!StringUtil.isEmpty(basicInfo.getBuyerCode())) {
                        //账户存在，买家编码存在，进行
                        message = "手机号重复，不能使用该手机号注册";
                    } else {
                        message = "BJ" + "/" + buyerId;
                    }
                }

            }
            /** Modif for Bug# at 2016/09/13 by zhao_chen End*/

        } else if (nameNum > 0) {
            message = "买家账号重复，不能使用该买家账号注册";
        } else if (buyerNameNum > 0) {
            message = "买家账号已被其他买家名称使用";
        }
        return message;
    }

    /**
     * 根据买家账号名判断是否存在该买家
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public int findAccountByName(ByBuyerAccount param) {
        BaseParam inParam = new BaseParam();
        inParam.setFilter("accountName", param.getAccountName());
        return super.getCount(SqlId.SQL_COUNT_NAME_ACCESS_INFO, inParam);
    }

    /**
     * 买家登录接口
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByBuyerAccount buyerLogin(ByBuyerAccount param) {
        BaseParam inParam = new BaseParam();
        inParam.setFilter("accountName", param.getAccountName());
        inParam.setFilter("accountPass", param.getAccountPass());
        ByBuyerAccount accountInfo = super.findOne(SqlId.SQL_FIND_ACCOUNT_BY_NAME_AND_PWD, inParam);
        return accountInfo;
    }

    /**
     * 根据买家手机号判断是否存在该买家
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByBuyerAccount findBuyerAccountByTel(ByBuyerAccount param) {
        BaseParam inParam = new BaseParam();
        inParam.setFilter("accountName", param.getAccountName());
        ByBuyerAccount accountInfo = super.findOne(SqlId.SQL_FIND_ACCOUNT_BY_TEL, inParam);
        return accountInfo;
    }

    /**
     * 接口获取参数
     *
     * @param param
     */
    @Transactional(readOnly = true)
    public ByBuyerAccount getBuyerAccount(IBY121201RsParam param) {
        // 根据手机号码查询买家的账号信息
        BaseParam findParam = new BaseParam();
        findParam.setFilter("telNo", param.getTelNo());
        ByBuyerAccount findAccountInfo = super.findOne(SqlId.SQL_FIND_ACCOUNT_INFO, findParam);
        return findAccountInfo;
    }


    /**
     * 买家密码重置
     *
     * @param updateParam
     * @return
     */
    @Transactional
    public int resetPassword(ByBuyerAccount updateParam) {
        return super.save(SqlId.SQL_RESET_PASSWORD, updateParam);
    }

    /**
     * 通过买家id,买家账号,买家旧密码,更新新密码
     *
     * @param param
     * @return
     */
    @Transactional
    public int updatePassword(BaseParam param) {
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_UPDATE_PASSWORD, param);
    }

    /**
     * 前台手机验证成功后,通过手机号直接修改密码
     *
     * @param param
     * @return
     */
    @Transactional
    public int updatePasswordByTel(BaseParam param) {
       /* param.setUpdTime(DateTimeUtil.getCustomerDate());*/
        return super.save(SqlId.SQL_UPDATE_PASSWORD_TELNO, param);
    }

    /**
     * 通路人员登录
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByAccessAccount accessLogin(ByAccessAccount param) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("accountName", param.getAccountName());
        baseParam.setFilter("accountPwd", param.getAccountPwd());
        ByAccessAccount byAccessAccount = super.findOne(SqlId.SQL_FIND_ACCESS_INFO, baseParam);
        return byAccessAccount;
    }

    /**
     * 获取通路人员账号信息
     *
     * @param accountName
     * @return
     */
    @Transactional(readOnly = true)
    public ByAccessAccount validateSSOLogin(String accountName) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("accountName", accountName);
        ByAccessAccount byAccessAccount = super.findOne(SqlId.SQL_FIND_ACCESS_INFO, baseParam);
        return byAccessAccount;
    }

    /**
     * 通过手机号码查询买家基本信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public ByBuyerAccount queryBuyeryBasicInfoByTel(ByBuyerAccount param) {
        BaseParam inParam = new BaseParam();
        inParam.setFilter("telNo", param.getTelNo());
        ByBuyerAccount accountInfo = super.findOne(SqlId.QUERY_BUYERY_BASIC_INFO_BY_TEL, inParam);
        return accountInfo;
    }


}
