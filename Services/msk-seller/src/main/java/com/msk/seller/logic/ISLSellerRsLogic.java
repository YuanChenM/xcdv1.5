package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101115Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BsBasicInfo;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlSeller;
import com.msk.core.entity.SlSellerHis;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.ProvinceBean;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.seller.bean.ISLSellerRsResult;
import com.msk.seller.utils.ISLRestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang_chi on 2016/8/3.
 */
@Service
public class ISLSellerRsLogic extends BaseLogic {

    interface SqlId {
        String SQL_ID_UPDATE_SLACCOUNT = "updateSlAccount";
        String SQL_ID_UPDATE_SLSELLER = "updateSlSeller";
        String SQL_ID_SAVE_SELLER = "saveSeller";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SL241103001Logic sl241103001Logic;

    @Autowired
    private SLAccountLogic slAccountLogic;

    @Autowired
    private SL24110102Logic sl24110102Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 必须项check
     *
     * @param slAccount
     * @param slSeller
     */
    private void RequiredCheck(SlAccount slAccount, SlSeller slSeller) {
        // 创建必须项校验的对象
        HashMap<String, Object> checkObj = new HashMap<String, Object>();
        if (null == slAccount && null == slSeller) {
            checkObj.put("卖家信息", null);
        }
        // 处理 sl_account
        if (null != slAccount) {
            checkObj.put("卖家账号", slAccount.getSlAccount());
            checkObj.put("手机号", slAccount.getSlTel());
        }
        // 处理 sl_Seller
        if (null != slSeller) {
//            if (null != slSeller.getSlCode()) {
            checkObj.put("卖家账号", slSeller.getSlAccount());
//                if (null != slSeller.getCityCode()) {
//                    checkObj.put("卖家分类", slSeller.getSlMainClass());
//                    checkObj.put("地区编码", slSeller.getCityCode());
//                    checkObj.put("生产国籍", slSeller.getSlConFlg());
//                }
//            } else {
//                checkObj.put("卖家账号", slSeller.getSlAccount());
//                checkObj.put("卖家分类", slSeller.getSlMainClass());
//                checkObj.put("地区编码", slSeller.getCityCode());
//                checkObj.put("生产国籍", slSeller.getSlConFlg());
//            }
        }
        // 必须项校验
        ValidatorUtils.validatorRequired(checkObj);
    }

    /**
     * 新增或修改卖家基本信息
     *
     * @param param
     * @return
     */
    @Transactional
    public ISLSellerRsResult dealSlSellerData(RsRequest<ISLSellerRsParam> param) {
        ISLSellerRsResult  islSellerRsResult = new ISLSellerRsResult();

        SlAccount slAccount = param.getParam().getSlAccount();
        SlSeller slSeller = param.getParam().getSlSeller();
        // 必须项check
        RequiredCheck(slAccount, slSeller);

        IBS2101115Param ibs2101115Param = new IBS2101115Param();

        // 处理 sl_account
        if (null != slAccount) {
            Map<String, Object> maps = dealSlAccount(slAccount);
            Integer slAccountCount = (Integer) maps.get("slAccountCount");
            // 影响行数
            islSellerRsResult.setSlAccountCount(slAccountCount);
            // 准备接口数据
            BeanUtils.copyProperties(slAccount, ibs2101115Param);
            String operationFlg = (String) maps.get("operationFlg");
            ibs2101115Param.setOperationFlg(operationFlg);
        }
        // 处理 sl_Seller
        if (null != slSeller) {
            Map<String, Object> maps = dealSlSeller(slSeller);
            Integer slSellerCount = (Integer) maps.get("slSellerCount");
            // 影响行数
            islSellerRsResult.setSlSellerCount(slSellerCount);
            SlSeller slSellerR = (SlSeller) maps.get("slSeller");
            String operationFlg = (String) maps.get("operationFlg");
            // 准备接口数据
            BsBasicInfo bsBasicInfo = new BsBasicInfo();
            BeanUtils.copyProperties(slSellerR, bsBasicInfo);
            ibs2101115Param.setBsBasicInfo(bsBasicInfo);
            ibs2101115Param.setOperationFlg(operationFlg);
            String slCode = (String) maps.get("slCode");
            islSellerRsResult.setSlCode(slCode);
        }
        // 调接口处理
        boolean flag = ISLRestUtil.syncBuyerInfo(ibs2101115Param);
        // 更新失败
        if (!flag) {
            throw new BusinessException("买手新增或变更失败");
        }
        return islSellerRsResult;
    }


    /**
     * 处理 sl_account
     *
     * @param slAccount
     * @return
     */
    @Transactional
    private Map<String, Object> dealSlAccount(SlAccount slAccount) {
        Map<String, Object> maps = new HashMap<String, Object>();
        Integer slAccountCount = 0;
        String account = slAccount.getSlAccount();
        slAccount.setCrtTime(DateTimeUtil.getCustomerDate());
        slAccount.setUpdTime(DateTimeUtil.getCustomerDate());
        //判断这个账户是否已经存在，拿数据去后台查询数据库查询账号信息
        SlAccount EntitySql = this.sl241103001Logic.slAccountEntity(account);
        if (null != EntitySql) {
            // 更新sl_account
            slAccountCount = slAccountLogic.modify(SqlId.SQL_ID_UPDATE_SLACCOUNT, slAccount);
            maps.put("operationFlg", "2");
        } else {
            // 新增sl_account
            slAccountCount = sl241103001Logic.save(slAccount);
            maps.put("operationFlg", "1");
        }
        maps.put("slAccountCount", slAccountCount);
        return maps;
    }

    /**
     * 处理 sl_Seller
     *
     * @param slSeller
     * @return
     */
    @Transactional
    private Map<String, Object> dealSlSeller(SlSeller slSeller) {
        Map<String, Object> maps = new HashMap<String, Object>();
        Integer slSellerCount = 0;
        String slCode = slSeller.getSlCode();
        slSeller.setCrtTime(DateTimeUtil.getCustomerDate());
        slSeller.setUpdTime(DateTimeUtil.getCustomerDate());
        String codeDis =  this.sl241103001Logic.findAccount(slSeller.getSlMainClass().toString(), slSeller.getCityCode(), slSeller.getSlConFlg());
        // 更新sl_Seller
        if (!StringUtil.isNullOrEmpty(slCode)) {
            // slCodeDis变更新增历史表
            if (!StringUtil.isNullOrEmpty(slSeller.getSlCodeDis())) {
                /**查询该卖家编码下的slseller信息*/
                SlSellerHis slSellerHis = sl24110102Logic.findSlSellerBySlCode(slCode);
                if (null != slSellerHis) {
                    Long hisId = this.commonLogic.maxId("SL_SELLER_HIS", "HIS_ID");
                    slSellerHis.setHisId(hisId);
                    this.sl24110102Logic.saveHis(slSellerHis);
                }
            }
            slSeller = dealSlSellerCodes(slSeller);
            // 获取SL_CODE_DIS 新增则直接前逻辑获取编码   修改通过该出获取最新的编码 2017/3/23修改卖家编码生成
            if(codeDis == null || "".equals(codeDis)){
                BaseParam base = new BaseParam();
                base.setFilter("cityCode", slSeller.getCityCode());
                base.setFilter("slMainClass", StringUtil.toSafeString(slSeller.getSlMainClass()));
                base.setFilter("slConFlg", slSeller.getSlConFlg());
                SlSeller seller = sl24110102Logic.findOne("queryMax", base);
                if(seller != null){
                    slSeller.setSlCodeDis(seller.getSlCodeDis());
                }else{
                    slSeller.setSlCodeDis(codeDis);
                }
            }
            slSeller.setCrtTime(DateTimeUtil.getCustomerDate());
            slSeller.setUpdTime(DateTimeUtil.getCustomerDate());
            slSellerCount = super.modify(SqlId.SQL_ID_UPDATE_SLSELLER, slSeller);
            maps.put("operationFlg", "2");
            maps.put("slCode",slCode);
        }
        // 新增sl_Seller
        else {
            BaseParam baseparam = new BaseParam();
            baseparam.getFilterMap().put("slAccount", slSeller.getSlAccount());
            SlSeller slSellerD = slAccountLogic.findOne(baseparam);
            if (null == slSellerD) {
                slSeller = dealSlSellerCodes(slSeller);
                slSellerCount = sl241103001Logic.save(SqlId.SQL_ID_SAVE_SELLER, slSeller);

                SlSeller slSellerDB = slAccountLogic.findOne(baseparam);
                slSeller.setSlCode(slSellerDB.getSlCode());
                maps.put("slCode",slSellerDB.getSlCode());
                maps.put("operationFlg", "1");
            } else {
                maps.put("slCode",slSellerD.getSlCode());
                slSeller.setSlCode(slSellerD.getSlCode());
                // slCodeDis变更新增历史表
                if (!StringUtil.isNullOrEmpty(slSeller.getSlCodeDis())) {
                    /**查询该卖家编码下的slseller信息*/
                    SlSellerHis slSellerHis = sl24110102Logic.findSlSellerBySlCode(slSeller.getSlCode());
                    if (null != slSellerHis) {
                        Long hisId = this.commonLogic.maxId("SL_SELLER_HIS", "HIS_ID");
                        slSellerHis.setHisId(hisId);
                        this.sl24110102Logic.saveHis(slSellerHis);
                    }
                }
                slSeller = dealSlSellerCodes(slSeller);
                slSeller.setSlCodeDis(codeDis);
                slSellerCount = super.modify(SqlId.SQL_ID_UPDATE_SLSELLER, slSeller);
                maps.put("operationFlg", "2");
            }
        }
        maps.put("slSellerCount", slSellerCount);
        maps.put("slSeller", slSeller);
        return maps;
    }

    /**
     * 对  省，市区,地区,卖家显示编码  处理
     *
     * @param slSeller
     * @return
     */
    @Transactional(readOnly = true)
    private SlSeller dealSlSellerCodes(SlSeller slSeller) {
        // 处理 省编码
        String provinceCode = slSeller.getProvinceCode();
        if (!StringUtil.isNullOrEmpty(provinceCode)) {
            ProvinceBean province = sl241103001Logic.findProvince(provinceCode);
            slSeller.setProvinceName(province.getProvinceName());
            slSeller.setAreaCode(province.getAreaCode());
            slSeller.setAreaName(province.getAreaName());
        }
        // 处理 地区（含地级市)
        String cityCode = slSeller.getCityCode();
        if (!StringUtil.isNullOrEmpty(cityCode)) {
            CityBean city = sl241103001Logic.findCity(cityCode);
            slSeller.setCityName(city.getCityName());
            slSeller.setLgcsAreaCode(city.getLgcsAreaCode());
            slSeller.setLgcsAreaName(city.getLgcsAreaName());

            // 处理 区（含县级市、县、区）
            String districtCode = slSeller.getDistrictCode();
            if (!StringUtil.isNullOrEmpty(districtCode)) {
                DistrictBean district = sl241103001Logic.findDistrict(cityCode, districtCode);
                slSeller.setDistrictName(district.getDistrictName());
            }

//            //  处理 codeDis
//            String codeDis = this.sl241103001Logic.findAccount(slSeller.getSlMainClass().toString(), cityCode, slSeller.getSlConFlg());
//            slSeller.setSlCodeDis(codeDis);
        }
        return slSeller;
    }


    /**
     * 根据卖家编码批量查询卖家信息
     *
     * @param rsParam
     * @return
     */
    @Transactional(readOnly = true)
    public ISLSellerRsResult queryISLSellerRsResult(ISLSellerRsParam rsParam) {
        ISLSellerRsResult islSellerRsResult = new ISLSellerRsResult();
        if (null != rsParam) {
            List<String> sellCodeList = rsParam.getSellCodeList();
            if (CollectionUtils.isNotEmpty(sellCodeList)) {
                BaseParam baseParam = new BaseParam();
                baseParam.setFilterObject("sellCodeList", sellCodeList);
                List<ISLSellerRsResult> islSellerRsResultList = super.findList(baseParam);
                if (CollectionUtils.isNotEmpty(islSellerRsResultList)) {
                    islSellerRsResult.setIslSellerRsResultList(islSellerRsResultList);
                }
            }
        }
        return islSellerRsResult;
    }


}
