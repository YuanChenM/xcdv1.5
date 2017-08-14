package com.msk.buyers.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.*;
import com.msk.buyers.utils.BuyerTypeUtil;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * IBY121202Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121202Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121202Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        // 买家基本信息更新
        static String SQl_BUYERS_BASE_INFO = "updateBuyersBaseInfo";
        static String SQL_BUYERINFO_MODIFY = "buyerInfoModify";
        static String SQL_BUYER_TYPE_MODIFY_BY_BUYER_ID = "buyerTypeModifyByBuyerId";
        static String SQL_BUYER_BASIC_MODIFY_BY_BUYER_ID = "buyerBasicModifyByBuyerId";
        static String SQL_BUYER_DETAIL_INFO_FIND = "buyerDetailInfoFind";
        static String SQL_TERMINAL_CONDITION_INFO_FIND = "terminalConditionInfoFind";
        static String SQL_FOOD_CONDITION_INFO_FIND = "foodConditionInfoFind";
        static String SQL_BUYER_CONDITION_INFO_FIND = "buyerConditionInfoFind";
        static String SQL_COUNT_BUYER_BY_TYPE = "countBuyerByType";
        static String SQL_GET_BUYER_BY_ID = "getBuyerById";
        static String SQL_GET_BUYER_BY_MARKET_ID = "getBuyerByMarketId";
        // 批发市场信息更新
        static String SQL_BY_MARKET_TER_MODIFY = "byMarketTerModify";
        // 菜场信息更新
        static String SQL_BY_MARKET_FOOD_MODIFY = "byMarketFoodModify";
        // 查询通过买家ID查询买家的相关所有信息
        static String SQL_FIND_BUYER_INFO = "findBuyerInfo";
        //查询多少买家个数
        static String SQL_BY_FIND_COUNT_BUYER = "getcountBuyer";
        //查询批发市场个数
        static String SQL_TERMINAL_COUNT_BY_CODE = "getMarketTerminalCount";
        //查询菜场个数
        static String SQL_BY_MARKET_FOOD_COUNT = "getMarketFoodCount";
        //查询买家经营产品类别
        static String SQL_FIND_PD_CLA_COUNT = "findPdClaCount";
        //新增买家经营产品表
        static String SQL_SAVE_PD_CLA = "savePdCla";
        //查询买家基本信息表数据
        static String SQL_FIND_BASE_BUYER_INFO = "findBaseBuyerInfo";
        //查询产品表数据
        static String SQL_FIND_BUYER_PD_CLA = "findBuyerPdCla";
        //查询买家名称是否存在
        static String SQL_FIND_BUYER_NAME_IS_EXIST = "findBuyerNameIsExist";
        //查询买家名称是否和其他买家账号相同
        static String SQL_FIND_ACCOUNT_NAME_IS_EXIST = "findAccountNameIsExist";
    }

    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private BY121304Logic by121304Logic;

    @Autowired
    private BY121001Logic by121001Logic;

    /**
     * 买家基本信息更新接口
     *
     * @param param
     * @return
     */
    @Transactional
    public int buyerInfoModify(IBY121202RsParam param) {
        // 更新买家基本信息表
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        param = setBuyerDefaultInfo(param);
        int modifyCount = super.modify(SqlId.SQL_BUYERINFO_MODIFY, param);
        return modifyCount;
    }

    @Transactional
    public int buyerTypeModify(IBY121202RsParam param) {
        // 更新买家基本信息表
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        param = setBuyerDefaultInfo(param);
        int modifyCount = super.modify(SqlId.SQL_BUYER_TYPE_MODIFY_BY_BUYER_ID, param);
        return modifyCount;
    }

    @Transactional
    public int buyerBasicInfoModify(IBY121202RsParam param) {
        // 更新买家基本信息表
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        param = setBuyerDefaultInfo(param);
        int modifyCount = super.modify(SqlId.SQL_BUYER_BASIC_MODIFY_BY_BUYER_ID, param);
        return modifyCount;
    }

    /**
     * 买家基本信息更新接口
     *
     * @param param
     * @return
     */
    @Transactional
    public int buyerInfoModify(ByBuyerBasicInfo param) {
        // 更新买家基本信息表
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        int modifyCount = super.modify(SqlId.SQL_BUYERINFO_MODIFY, param);
        return modifyCount;
    }

    /**
     * 买家基本信息查询接口
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBY121202RsParam findBuyerDetailInfo(IBY121202RsParam param) {
        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("buyerId", param.getBuyerId());
        inParam.setPaging(false);
        Map<String, String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        Map<String, String> BuyerRegisterWayMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.BuyerRegisterWay.TYPE);
        Map<String, String> PaymentMethodMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.PaymentMethod.Type);
        IBY121202RsParam buyerDetail = super.findOne(SqlId.SQL_BUYER_DETAIL_INFO_FIND, inParam);
        if (null != buyerDetail) {
            if (!StringUtil.isNullOrEmpty(buyerDetail.getRegisterSource())) {
                buyerDetail.setRegisterSourceName(BuyerRegisterWayMap.get(buyerDetail.getRegisterSource()));
            }
            if (!StringUtil.isNullOrEmpty(buyerDetail.getMarketingsStatus())) {
                buyerDetail.setMarketingsStatusName(marketingSatusMap.get(buyerDetail.getMarketingsStatus()));
            }
            if (!StringUtil.isNullOrEmpty(buyerDetail.getPaymentType())) {
                buyerDetail.setPaymentTypeName(PaymentMethodMap.get(buyerDetail.getPaymentType()));
            }
        }
        return buyerDetail;
    }


    /**
     * 买家基本信息更新通路注册
     *
     * @param param
     * @return
     */
    @Transactional
    public int updateBuyersBaseInfo(IBY121202RsParam param) {
        // 更新买家基本信息表
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        param = setBuyerDefaultInfo(param);
        int modifyCount = super.modify(SqlId.SQl_BUYERS_BASE_INFO, param);
        return modifyCount;
    }

    /**
     * 根据区域编码获取买家
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121202RsParam> findBuyerList(IBY121202RsParam param) {
        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("provinceCode", param.getProvinceCode());
        inParam.setFilter("lgcsCode", param.getLgcsAreaCode());
        inParam.setFilter("cityCode", param.getCityCode());
        inParam.setFilter("districtCode", param.getDistrictCode());
        int pageNo = param.getPageNo();
        int pageSize = 10;
        int startPos = (pageNo - NumberConst.IntDef.INT_ONE) * pageSize;
        inParam.setPageSize(pageSize);
        inParam.setStartPos(startPos);
        inParam.setPaging(param.getPaging());
        Map<String, String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        Map<String, String> BuyerRegisterWayMap = CodeMasterManager.findCodeMasterMap("BuyerRegisterWay");
        Map<String, String> PaymentMethodMap = CodeMasterManager.findCodeMasterMap("PaymentMethod");
        List<IBY121202RsParam> buyerList = super.findList(SqlId.SQL_BUYER_DETAIL_INFO_FIND, inParam);
        for (IBY121202RsParam iby121202RsParam : buyerList) {
            iby121202RsParam.setRegisterSourceName(BuyerRegisterWayMap.get(iby121202RsParam.getRegisterSource()));
            iby121202RsParam.setMarketingsStatusName(marketingSatusMap.get(iby121202RsParam.getMarketingsStatus()));
            iby121202RsParam.setPaymentTypeName(PaymentMethodMap.get(iby121202RsParam.getPaymentType()));
            //iby121202RsParam.setPaymentName(PaymentMethodMap.get(iby121202RsParam.getPaymentType()));
        }
        return buyerList;
    }

    /**
     * 根据指定条件获取买家
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121202RsParam> findConditionBuyerList(IBY121202RsParam param) {
        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("buyerName", param.getBuyerName());
        DbUtils.buildLikeCondition(inParam, "buyerName", DbUtils.LikeMode.PARTIAL);

       /* if(!StringUtil.isNullOrEmpty(param.getBuyerName())){
            inParam.setFilter("buyerName", param.getBuyerName() );
            DbUtils.buildLikeCondition(inParam, "buyerName", DbUtils.LikeMode.FRONT);
        }else{
            inParam.setFilter("buyerName", param.getBuyerName());
        }*/
        inParam.setFilter("lgcsAreaCode", param.getLgcsAreaCode());
        inParam.setFilter("cityCode", param.getCityCode());
        inParam.setFilter("districtCode", param.getDistrictCode());

        List<IBY121202RsParam> buyerList = super.findList(SqlId.SQL_BUYER_CONDITION_INFO_FIND, inParam);
        return buyerList;
    }

    /**
     * 根据指定条件获取批发市场
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketTerminal> findConditionMarketTerminalList(ByMarketTerminal param) {
        BasePageParam inParam = new BasePageParam();

        inParam.setFilter("terMarketId", param.getTerMarketId());
//        DbUtils.buildLikeCondition(inParam, "terMarketId", DbUtils.LikeMode.FRONT);
        if (!StringUtil.isNullOrEmpty(param.getMarketName())) {
//            inParam.setFilter("marketName", param.getMarketName() + StringConst.PRE);
            inParam.setFilter("marketName", param.getMarketName());
            DbUtils.buildLikeCondition(inParam, "marketName", DbUtils.LikeMode.PARTIAL);
        } else {
            inParam.setFilter("marketName", param.getMarketName());
        }
        inParam.setFilter("lgcsAreaCode", param.getLgcsAreaCode());
        inParam.setFilter("cityCode", param.getCityCode());
        inParam.setFilter("districtCode", param.getDistrictCode());
        List<ByMarketTerminal> marketTerminalList = super.findList(SqlId.SQL_TERMINAL_CONDITION_INFO_FIND, inParam);
        return marketTerminalList;
    }

    /**
     * 根据指定条件获取菜场
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketFood> findConditionMarketFoodList(ByMarketFood param) {
        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("fodMarketId", param.getFodMarketId());
        inParam.setFilter("marketName", param.getMarketName());
        DbUtils.buildLikeCondition(inParam, "marketName", DbUtils.LikeMode.PARTIAL);
        /*if(!StringUtil.isNullOrEmpty(param.getMarketName())){
            inParam.setFilter("marketName", param.getMarketName() + StringConst.PRE);
        }else{
            inParam.setFilter("marketName", param.getMarketName());
        }*/
        inParam.setFilter("lgcsAreaCode", param.getLgcsAreaCode());
        inParam.setFilter("cityCode", param.getCityCode());
        inParam.setFilter("districtCode", param.getDistrictCode());
        List<ByMarketFood> marketFoodList = super.findList(SqlId.SQL_FOOD_CONDITION_INFO_FIND, inParam);
        return marketFoodList;
    }

    /**
     * 查询批发市场或菜场中的买家信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121202RsParam> findBuyerByMarketId(IBY121202RsParam param) {
        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("marketId", param.getMarketId());
        Map<String, String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        Map<String, String> BuyerRegisterWayMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.BuyerRegisterWay.TYPE);
        List<IBY121202RsParam> buyerList = super.findList(SqlId.SQL_GET_BUYER_BY_MARKET_ID, inParam);
        for (IBY121202RsParam iby121202RsParam : buyerList) {
            if (null == marketingSatusMap && StringUtil.isNullOrEmpty(iby121202RsParam.getMarketingsStatus())) {
                iby121202RsParam.setMarketingsStatusName("");
            } else {
                iby121202RsParam.setMarketingsStatusName(marketingSatusMap.get(iby121202RsParam.getMarketingsStatus()));
            }
            if (StringUtil.isNullOrEmpty(iby121202RsParam.getRegisterSource())) {
                iby121202RsParam.setRegisterSourceName("");
            } else {
                iby121202RsParam.setRegisterSourceName(BuyerRegisterWayMap.get(iby121202RsParam.getRegisterSource()));
            }
        }
        return buyerList;
    }

    /**
     * 根据id获取买家
     *
     * @param buyerId
     * @return
     */
    @Transactional(readOnly = true)
    public IBY121202RsParam findBuyerById(String buyerId) {
        // 取得该买家的信息
        IBY121202RsParam buyerInfo = new IBY121202RsParam();
        buyerInfo.setBuyerId(buyerId);
        buyerInfo = super.findOne(SqlId.SQL_GET_BUYER_BY_ID, buyerInfo);
        return buyerInfo;
    }

    /**
     * 买家物流区，省市区（县）数据处理
     */
    public int updateParams(IBY121202RsParam param) {
        if (!StringUtil.isNullOrEmpty(param.getCityName())) {
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityName(param.getCityName());
            //根据城市名称查询城市编码
            List<CityBean> cityList = RestCommUtil.getCityList(districtParam).getResult().getCityList();
            if (!CollectionUtils.isEmpty(cityList)) {
                for (CityBean city : cityList) {
                    if (city.getCityName().equals(param.getCityName())) {
                        param.setCityCode(city.getCityCode());
                        break;
                    }
                }
            }
        }
        if (!StringUtil.isNullOrEmpty(param.getCityCode())) {
            DistrictParam districtParam = new DistrictParam();
            districtParam.setFlag(0);
            districtParam.setCityCode(param.getCityCode());
            //根据城市编码查询物流区编码及名称
            List<DistrictBean> districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
            if (!CollectionUtils.isEmpty(districtList)) {
                param.setCityName(districtList.get(NumberConst.IntDef.INT_ZERO).getCityName());
                for (DistrictBean district : districtList) {
                    if (district.getCityCode().equals(param.getCityCode())) {
                        param.setLgcsAreaCode(district.getLgcsAreaCode());
                        param.setLgcsAreaName(district.getLgcsAreaName());
                        break;
                    }
                }
                districtParam = new DistrictParam();
                districtParam.setFlag(1);
                districtParam.setCityCode(param.getCityCode());
                //根据城市编码查询省编码和名称
                List<DistrictBean> provinceList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
                for (DistrictBean district : provinceList) {
                    if (district.getCityCode().equals(param.getCityCode())) {
                        param.setProvinceCode(district.getProvinceCode());
                        param.setProvinceName(district.getProvinceName());
                        break;
                    }
                }
            }
        }
        if (!StringUtil.isNullOrEmpty(param.getDistrictName()) && !StringUtil.isNullOrEmpty(param.getCityCode())) {
            //根据区县名称和城市编码查询区县编码
            DistrictParam districtParam = new DistrictParam();
            districtParam.setFlag(0);
            districtParam.setCityCode(param.getCityCode());
            districtParam.setDistrictName(param.getDistrictName());
            List<DistrictBean> districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
            for (DistrictBean district : districtList) {
                if (district.getCityCode().equals(param.getCityCode()) && district.getDistrictName().equals(param.getDistrictName())) {
                    param.setDistrictCode(district.getDistrictCode());
                    break;
                }
            }
        }
        if (!StringUtil.isNullOrEmpty(param.getDistrictCode()) && !StringUtil.isNullOrEmpty(param.getCityCode())) {
            //根据区县编码和城市编码查询区县名称
            DistrictParam districtParam = new DistrictParam();
            districtParam.setFlag(0);
            districtParam.setCityCode(param.getCityCode());
            districtParam.setDistrictCode(param.getDistrictCode());
            List<DistrictBean> districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
            for (DistrictBean district : districtList) {
                if (district.getCityCode().equals(param.getCityCode()) && district.getDistrictCode().equals(param.getDistrictCode())) {
                    param.setDistrictName(district.getDistrictName());
                    break;
                }
            }
        }
        int modifyCount = this.buyerInfoModify(param);
        return modifyCount;
    }

    @Transactional(readOnly = true)
    public int findPdClaCount(IBY121202RsParam param) {
        return super.getCount(SqlId.SQL_FIND_PD_CLA_COUNT, param);
    }

    @Transactional
    public int savePdCla(IBY121202RsParam param) {
        return super.save(SqlId.SQL_SAVE_PD_CLA, param);
    }

    /**
     * 设置买家基本信息中的默认信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    private IBY121202RsParam setBuyerDefaultInfo(IBY121202RsParam param) {
        // 取得该买家的信息
        ByBuyerBasicInfo buyerInfo = super.findOne(SqlId.SQL_GET_BUYER_BY_ID, param);
        if (null != buyerInfo) {
            if (StringUtil.isNullOrEmpty(param.getSuperiorType())) {
                if (StringUtil.isNullOrEmpty(buyerInfo.getSuperiorType())) {
                    // 如果未设置买家类型,默认设置为分销买家
                    Map<String, String> buyerTypesMap = BuyerTypeUtil.getInstance().getBuyerTypeMap();
                    String buyerTypeName = StringConst.EMPTY;
              /*  for (CommConstant buyerType : buyerTypes) {
                    if (Distribution.equals(buyerType.getConstantValue())) {
                        buyerTypeName = buyerType.getConstantName();
                        break;
                    }
                }*/
                    buyerTypeName = buyerTypesMap.get(BuyersConst.BuyerType.Distribution);
                    param.setSuperiorType(BuyersConst.BuyerType.Distribution);
                    param.setSuperiorName(buyerTypeName);
                } else {
                    // 若该买家设置了类型,则不改变该值
                    param.setSuperiorType(buyerInfo.getSuperiorType());
                }
            }

            DistrictParam districtDefaultParam = new DistrictParam();
            if (!StringUtil.isNullOrEmpty(param.getCityName())) {
                districtDefaultParam.setCityName(param.getCityName());
                List<CityBean> cityList = RestCommUtil.getCityList(districtDefaultParam).getResult().getCityList();
                for (CityBean city : cityList) {
                    if (city.getCityName().equals(param.getCityName())) {
                        param.setCityCode(city.getCityCode());
                        break;
                    }
                }
            }
            if (!StringUtil.isNullOrEmpty(param.getDistrictName()) && !StringUtil.isNullOrEmpty(param.getCityCode())) {
                districtDefaultParam = new DistrictParam();
                districtDefaultParam.setFlag(0);
                districtDefaultParam.setCityCode(param.getCityCode());
                districtDefaultParam.setDistrictName(param.getDistrictName());
                List<DistrictBean> districtList = RestCommUtil.getDistrictList(districtDefaultParam).getResult().getDistrictList();
                for (DistrictBean district : districtList) {
                    if (district.getCityCode().equals(param.getCityCode()) && district.getDistrictName().equals(param.getDistrictName())) {
                        param.setDistrictCode(district.getDistrictCode());
                        break;
                    }
                }
            }

            if (StringUtil.isNullOrEmpty(param.getCityCode())) {
                if (StringUtil.isNullOrEmpty(buyerInfo.getCityCode())) {
                    // 如果未设置城市,则默认设置为999(其他)
//                    param.setCityCode(BuyersConst.DefaultString.CITYCODEDEFAULT);
//                    param.setCityName(BuyersConst.DefaultString.NAMEDEFAULT);
                    param.setCityCode("001");
                    param.setCityName("上海");
                    // 并且把省设为01(上海)
//                    param.setProvinceCode(BuyersConst.DefaultString.CODEDEFAULT);
//                    param.setProvinceName(BuyersConst.DefaultString.NAMEDEFAULT);
                    param.setProvinceCode("01");
                    param.setProvinceName("上海");
                } else {
                    // 若该买家有城市编码,则不改变该值
                    param.setCityCode(buyerInfo.getCityCode());
                }
            } else {
                // 如果设置了城市则根据城市逆向设置物流区和省
                DistrictParam districtParam = new DistrictParam();
                districtParam.setFlag(0);
                districtParam.setCityCode(param.getCityCode());
                List<DistrictBean> districtList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
                String lgcsAreaCode = StringConst.EMPTY;
                for (DistrictBean district : districtList) {
                    if (district.getCityCode().equals(param.getCityCode())) {
                        lgcsAreaCode = district.getLgcsAreaCode();
                        break;
                    }
                }
                String provinceCode = StringConst.EMPTY;
                districtParam = new DistrictParam();
                districtParam.setFlag(1);
                districtParam.setCityCode(param.getCityCode());
                List<DistrictBean> provinceList = RestCommUtil.getDistrictList(districtParam).getResult().getDistrictList();
                for (DistrictBean district : provinceList) {
                    if (district.getCityCode().equals(param.getCityCode())) {
                        provinceCode = district.getProvinceCode();
                        param.setProvinceName(district.getProvinceName());
                        break;
                    }
                }
                if (!StringUtil.isNullOrEmpty(lgcsAreaCode)) {
                    param.setLgcsAreaCode(lgcsAreaCode);
                }
                if (!StringUtil.isNullOrEmpty(provinceCode)) {
                    param.setProvinceCode(provinceCode);
                }
            }

            if (StringUtil.isNullOrEmpty(param.getDistrictCode())) {
                if (StringUtil.isNullOrEmpty(buyerInfo.getDistrictCode())) {
                    // 如果未设置区县,则默认设置为99(其他)
//                    param.setDistrictCode(BuyersConst.DefaultString.CODEDEFAULT);
                    param.setDistrictCode("01");
                } else {
                    // 如果该买家有区县编码,则不改变该值
                    param.setDistrictCode(buyerInfo.getDistrictCode());
                }
            }
            if (StringUtil.isNullOrEmpty(param.getDistrictName())) {
                if (StringUtil.isNullOrEmpty(buyerInfo.getDistrictName())) {
//                    param.setDistrictName(BuyersConst.DefaultString.NAMEDEFAULT);
                    param.setDistrictName("浦东新区");
                } else {
                    param.setDistrictName(buyerInfo.getDistrictName());
                }
            }

            if (StringUtil.isNullOrEmpty(param.getLgcsAreaCode())) {
                if (StringUtil.isNullOrEmpty(buyerInfo.getLgcsAreaCode())) {
                    // 如果未设置物流区,则默认设置为99(其他)
//                    param.setLgcsAreaCode(BuyersConst.DefaultString.CODEDEFAULT);
                    param.setLgcsAreaCode("41");
                } else {
                    // 若该买家有物流区编码,则不改变该值
                    param.setLgcsAreaCode(buyerInfo.getLgcsAreaCode());
                }
            }
            if (StringUtil.isNullOrEmpty(param.getLgcsAreaName())) {
                if (StringUtil.isNullOrEmpty(buyerInfo.getLgcsAreaName())) {
//                    param.setLgcsAreaName(BuyersConst.DefaultString.NAMEDEFAULT);
                    param.setLgcsAreaName("上海");
                } else {
                    param.setLgcsAreaName(buyerInfo.getLgcsAreaName());
                }
            }

            //判断批发市场
            if (StringUtil.isNullOrEmpty(param.getSuperiorId())) {
                if (!StringUtil.isNullOrEmpty(buyerInfo.getSuperiorId())
                        && StringUtil.equals(param.getSuperiorType(), buyerInfo.getSuperiorType())) {
                    param.setSuperiorId(buyerInfo.getSuperiorId());
                } else {
                    //设置默认市场ID
                    if (BuyersConst.BuyerType.Distribution.equals(param.getSuperiorType())) {
                        param.setSuperiorId("defaultTerMarket");
                        param.setSuperiorQua("二级批发市场");
                    } else if (BuyersConst.BuyerType.Market.equals(param.getSuperiorType())) {
                        param.setSuperiorId("defaultFodMarket1");
                        param.setSuperiorQua("菜场");
                    } else {
                        param.setSuperiorId("");
                        param.setSuperiorQua("");
                    }
                }
            }
            //设置买家上线状态
            if (!StringUtil.isNullOrEmpty(param.getBuyerQq()) || !StringUtil.isNullOrEmpty(param.getBuyerSingleWechat())) {
                param.setMarketingsStatus(BuyersConstant.MarketingsStatus.PreRegister);
            } else {
                param.setMarketingsStatus(BuyersConstant.MarketingsStatus.NoMarket);
            }

            /*//销售状态
            if(StringUtil.isNullOrEmpty(param.getMarketingsStatus())){
                if(!StringUtil.isNullOrEmpty(buyerInfo.getMarketingsStatus())){
                    param.setMarketingsStatus(buyerInfo.getMarketingsStatus());
                }
            }*/
            //买家编码
            if (StringUtil.isNullOrEmpty(param.getBuyerCode())) {
                if (!StringUtil.isNullOrEmpty(buyerInfo.getBuyerCode())) {
                    param.setBuyerCode(buyerInfo.getBuyerCode());
                }
            }

            //菜场非菜场
            if (BuyersConst.BuyerType.Processing.equals(param.getSuperiorType())) {
                //菜场非菜场
                if (StringUtil.isNullOrEmpty(param.getIsMarketFlg())) {
                    if (!StringUtil.isNullOrEmpty(buyerInfo.getIsMarketFlg())) {
                        param.setIsMarketFlg(buyerInfo.getIsMarketFlg());
                    }
                }
            }

            if (StringUtil.isNullOrEmpty(param.getSuperiorSubType())) {
                if (!StringUtil.isNullOrEmpty(buyerInfo.getSuperiorSubType())) {
                    param.setSuperiorSubType(buyerInfo.getSuperiorSubType());
                }
            }

            //判断销售产品编码
            if (param.getBuyerPdCla() == null || param.getBuyerPdCla().length == 0) {

                List<ByBuyerPdCla> pdClaList = by121304Logic.buyerPdClassificationFind(param.getBuyerId());
                String[] pdCla = new String[pdClaList.size()];
                String[] pdMca = new String[pdClaList.size()];
                for (int i = 0; i < pdClaList.size(); i++) {

                    pdCla[i] = pdClaList.get(i).getClassCode();
                    pdMca[i] = pdClaList.get(i).getMachiningCode();
                }

                param.setBuyerPdCla(pdCla);
                param.setBuyerPdMac(pdMca);
            }

            BY121001Bean by121001Bean = new BY121001Bean();
            by121001Bean.setBuyerId(param.getBuyerId());
            by121001Bean.setSuperiorType(param.getSuperiorType());
            by121001Bean.setSuperiorSubType(param.getSuperiorSubType());
            by121001Bean.setSuperiorId(param.getSuperiorId());
            by121001Bean.setLgcsAreaCode(param.getLgcsAreaCode());
            by121001Bean.setCityCode(param.getCityCode());
            by121001Bean.setDistrictCode(param.getDistrictCode());
            by121001Bean.setMarketingsStatus(param.getMarketingsStatus());
            // 自动生成买家编码
            param.setBuyerCode(by121001Logic.getNewBuyerCode(by121001Bean));
        }
        return param;
    }

    /**
     * 生成默认买家编码
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    private String getBuyerCode(IBY121202RsParam param) {
        String buyerCode;
        //买家分类码(分销买家分类码在批发市场编码中体现)
        buyerCode = getTypecode(param);
        //获取菜场批发市场编码
        buyerCode = buyerCode.concat(getDiviSionCode(param));
        //获取买家顺序码
        buyerCode = buyerCode.concat(sequenceCode(param));
        //获取买家校验码
        buyerCode = buyerCode.concat(getSecIdenCode(param));

        return buyerCode;
    }

    /**
     * 批发市场信息更新接口
     *
     * @param param
     * @return
     */
    @Transactional
    public int byMarketTerModify(ByMarketTerminal param) {
        // 更新批发市场信息表
        param.setUpdTime(DateTimeUtil.getCustomerDate());

        int modifyCount = super.modify(SqlId.SQL_BY_MARKET_TER_MODIFY, param);
        return modifyCount;
    }

    /**
     * 菜场信息更新接口
     *
     * @param param
     * @return
     */
    @Transactional
    public int byMarketFoodModify(ByMarketFood param) {
        // 更新菜场信息表
        param.setUpdTime(DateTimeUtil.getCustomerDate());

        int modifyCount = super.modify(SqlId.SQL_BY_MARKET_FOOD_MODIFY, param);
        return modifyCount;
    }

    /**
     * 查询买家详细信息
     *
     * @param regParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<BuyerBasicInfoBean> searchBuyerInfo(BuyerRelationParam regParam) {
        if (CollectionUtils.isEmpty(regParam.getBuyerIdList())) {
            throw new BusinessException("买家ID不能为空");
        } else {
            Map<String, String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
            Map<String, String> BuyerRegisterWayMap = CodeMasterManager.findCodeMasterMap("BuyerRegisterWay");
            Map<String, String> PaymentMethodMap = CodeMasterManager.findCodeMasterMap("PaymentMethod");

            regParam.getFilterMap().put("buyerName", regParam.getBuyerName());
            regParam.getFilterMap().put("buyerAddr", regParam.getBuyerAddr());
            regParam.getFilterMap().put("busiTel", regParam.getBusiTel());
            regParam.getFilterMap().put("buyerCode", regParam.getBuyerCode());

            DbUtils.buildLikeCondition(regParam, "buyerName", DbUtils.LikeMode.PARTIAL);
            DbUtils.buildLikeCondition(regParam, "buyerAddr", DbUtils.LikeMode.PARTIAL);
            DbUtils.buildLikeCondition(regParam, "busiTel", DbUtils.LikeMode.PARTIAL);
            DbUtils.buildLikeCondition(regParam, "buyerCode", DbUtils.LikeMode.PARTIAL);

            List<BuyerBasicInfoBean> basicInfo = this.findList(SqlId.SQL_FIND_BUYER_INFO, regParam);
            for (BuyerBasicInfoBean buyerBasicInfoBean : basicInfo) {
                buyerBasicInfoBean.setMarketingsStatusName(marketingSatusMap.get(buyerBasicInfoBean.getMarketingsStatus()));
                buyerBasicInfoBean.setRegisterSourceName(BuyerRegisterWayMap.get(buyerBasicInfoBean.getRegisterSource()));
                buyerBasicInfoBean.setPaymentTypeName(PaymentMethodMap.get(buyerBasicInfoBean.getPaymentType()));
            }
            return basicInfo;
        }
    }

    /**
     * 1、根据买家ID、区域编码
     * 2、买家code、买家名字
     * 获取买家信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<BuyerBasicInfoBean> findBuyersList(BuyerRelationParam param) {
        Map<String, String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        Map<String, String> BuyerRegisterWayMap = CodeMasterManager.findCodeMasterMap("BuyerRegisterWay");
        Map<String, String> PaymentMethodMap = CodeMasterManager.findCodeMasterMap("PaymentMethod");
        param.getFilterMap().put("buyerName", param.getBuyerName());
        DbUtils.buildLikeCondition(param, "buyerName", DbUtils.LikeMode.PARTIAL);

        List<BuyerBasicInfoBean> buyerBasicInfoList = this.findList(param);
        for (BuyerBasicInfoBean basicInfoBean : buyerBasicInfoList) {
            basicInfoBean.setMarketingsStatusName(marketingSatusMap.get(basicInfoBean.getMarketingsStatus()));
            basicInfoBean.setRegisterSourceName(BuyerRegisterWayMap.get(basicInfoBean.getRegisterSource()));
            basicInfoBean.setPaymentTypeName(PaymentMethodMap.get(basicInfoBean.getPaymentType()));
        }
        return buyerBasicInfoList;
    }

    /**
     * 获取买家分类码
     *
     * @param param
     * @return
     */
    private String getTypecode(IBY121202RsParam param) {

        String typeCode = "";

        //买家类型为2,3,8是为两位，其他为4位
        switch (param.getSuperiorType()) {
            case BuyersConst.BuyerType.Market://菜场买家
                typeCode = StringUtil.PadLeft(param.getSuperiorType(), NumberConst.IntDef.INT_TWO, "0");
                break;
            case BuyersConst.BuyerType.GroupMeals://团膳买家
                typeCode = StringUtil.PadLeft(param.getSuperiorType(), NumberConst.IntDef.INT_TWO, "0");
                break;
            case BuyersConst.BuyerType.HotPot://火锅买家
                typeCode = StringUtil.PadLeft(param.getSuperiorType(), NumberConst.IntDef.INT_TWO, "0");
                break;
            case BuyersConst.BuyerType.ChineseFood://中餐买家
                typeCode = StringUtil.PadLeft(param.getSuperiorType(), NumberConst.IntDef.INT_TWO, "0").concat(StringUtil.PadLeft(param.getSuperiorSubType(), NumberConst.IntDef.INT_TWO, "0"));
                break;
//            case BuyersConst.BuyerType.WestFood://西餐买家
//                //todo
//                break ;
//            case BuyersConst.BuyerType.SnackGrill://小吃烧烤买家
//                //todo
//                break;
            case BuyersConst.BuyerType.Processing://加工厂买家
                String SedCode = "";
                if (param.getIsMarketFlg().equals("1")) {
                    SedCode = param.getSuperiorSubType();
                } else {
                    SedCode = (StringUtil.toInteger(param.getSuperiorSubType()) + 1) + "";
                }
                typeCode = StringUtil.PadLeft(param.getSuperiorType(), NumberConst.IntDef.INT_TWO, "0").concat(StringUtil.PadLeft(SedCode, NumberConst.IntDef.INT_TWO, "0"));
                break;

        }

        return typeCode;
    }

    /**
     * 获取行政区划编码和批发市场/菜场顺序码
     *
     * @param param
     * @return
     */
    private String getDiviSionCode(IBY121202RsParam param) {

        String diviSionCode = "";

        switch (param.getSuperiorType()) {
            //分销买家买家编码前10位直接从批发市场表中取
            case BuyersConst.BuyerType.Distribution:
                diviSionCode = getMarketTerCode(param);
                break;
            case BuyersConst.BuyerType.Market:
                diviSionCode = getMarketFoodCode(param);
                break;
            case BuyersConst.BuyerType.Processing://加工厂买家
                //1：是菜场买家，0：非菜场买家
                if (param.getIsMarketFlg().equals(BuyersConst.BuyerType.isMarket)) {
                    diviSionCode = getMarketFoodCode(param);
                } else {
                    diviSionCode = param.getLgcsAreaCode() + param.getCityCode() + param.getDistrictCode();
                }
            default:
                diviSionCode = param.getLgcsAreaCode() + param.getCityCode() + param.getDistrictCode();
                break;
        }
        return diviSionCode;
    }

    /**
     * 获取买家顺序码
     *
     * @param param
     * @return
     */
    private String sequenceCode(IBY121202RsParam param) {

        //买家顺序码
        String sequenceCode = "";
        //买家个数
        int buyerCount;
        //根据买家ID获取买家基本信息
        ByBuyerBasicInfo buyerInfo = new ByBuyerBasicInfo();
        buyerInfo.setBuyerId(param.getBuyerId());
        buyerInfo = super.findOne(SqlId.SQL_GET_BUYER_BY_ID, buyerInfo);
        if (null == buyerInfo) {
            throw new BusinessException("参数错误，买家信息不存在！");
        } else {
            //如果该买家还未选择批发市场或菜场,根据当前传入市场ID获取该市场当前买家数+1作为顺序码
            if (StringUtil.isNullOrEmpty(buyerInfo.getSuperiorId())) {
                buyerCount = getBuyerCountByMarketId(param);
                buyerCount = buyerCount + NumberConst.IntDef.INT_ONE;
            } else {
                //如果该买家信息已存在市场信息。
                //如果存在的市场ID和当前传入市场ID相同,则买家顺序码不变
                if (buyerInfo.getSuperiorId().equals(param.getSuperiorId())) {
                    //分销买家买家顺序码3位
                    if (BuyersConst.BuyerType.Distribution.equals(buyerInfo.getSuperiorType())) {
                        sequenceCode = buyerInfo.getBuyerCode().substring(buyerInfo.getBuyerCode().length() - 5, buyerInfo.getBuyerCode().length() - 2);
                    } else if (BuyersConst.BuyerType.Market.equals(buyerInfo.getSuperiorType())) {
                        //菜场买家买家顺序码2位
                        sequenceCode = buyerInfo.getBuyerCode().substring(buyerInfo.getBuyerCode().length() - 4, buyerInfo.getBuyerCode().length() - 2);
                    } else if (BuyersConst.BuyerType.Processing.equals(buyerInfo.getSuperiorType()) && BuyersConst.BuyerType.BraiseFood.equals(buyerInfo.getSuperiorSubType())) {
                        //加工厂卤味熟食买家买家顺序码2位
                        sequenceCode = buyerInfo.getBuyerCode().substring(buyerInfo.getBuyerCode().length() - 4, buyerInfo.getBuyerCode().length() - 2);
                    } else if (BuyersConst.BuyerType.Processing.equals(buyerInfo.getSuperiorType()) && !BuyersConst.BuyerType.BraiseFood.equals(buyerInfo.getSuperiorSubType())) {
                        //加工厂非卤味熟食买家买家顺序码2位
                        String identCode = StringUtil.PadLeft(String.valueOf(BuyersConst.BuyerType.IdentCode), NumberConst.IntDef.INT_TWO, "0");
                        sequenceCode = identCode + buyerInfo.getBuyerCode().substring(buyerInfo.getBuyerCode().length() - 4, buyerInfo.getBuyerCode().length() - 2);
                    }
                    return sequenceCode;
                } else {
                    //如果存在的市场ID和当前传入市场ID不同,则根据当前传入市场ID获取该市场当前买家数+1作为顺序码
                    buyerCount = getBuyerCountByMarketId(param);
                    buyerCount = buyerCount + NumberConst.IntDef.INT_ONE;
                }
            }
        }
        if (buyerCount > NumberConst.IntDef.INT_NINE_NINE_NINE_FOR_SQL_IN_LIMIT) {
            throw new BusinessException("人数上限，提交失败");
        }

        switch (param.getSuperiorType()) {

            case BuyersConst.BuyerType.Distribution://分销买家
                sequenceCode = StringUtil.PadLeft(String.valueOf(buyerCount), NumberConst.IntDef.INT_THREE, "0");
                break;
            case BuyersConst.BuyerType.Market://菜场买家
                sequenceCode = StringUtil.PadLeft(String.valueOf(buyerCount), NumberConst.IntDef.INT_TWO, "0");
                break;
            case BuyersConst.BuyerType.Processing://加工厂买家
                //加工厂卤味熟食买家
                if (BuyersConst.BuyerType.BraiseFood.equals(buyerInfo.getSuperiorSubType())) {
                    sequenceCode = StringUtil.PadLeft(String.valueOf(buyerCount), NumberConst.IntDef.INT_TWO, "0");
                } else {
                    sequenceCode = StringUtil.PadLeft(String.valueOf(BuyersConst.BuyerType.IdentCode), NumberConst.IntDef.INT_TWO, "0") + StringUtil.PadLeft(String.valueOf(buyerCount), NumberConst.IntDef.INT_THREE, "0");
                }
                break;
            default:
                sequenceCode = StringUtil.PadLeft(String.valueOf(buyerCount), NumberConst.IntDef.INT_THREE, "0");
                ;
        }

        return sequenceCode;
    }

    /**
     * 根据市场ID获取当前市场的买家总数
     *
     * @param param
     * @return
     */
    private int getBuyerCountByMarketId(IBY121202RsParam param) {
        BaseParam inParam = new BaseParam();
        inParam.getFilterMap().put("lgcsAreaCode", param.getLgcsAreaCode());
        inParam.getFilterMap().put("cityCode", param.getCityCode());
        inParam.getFilterMap().put("superiorId", param.getSuperiorId());
        if (!param.getSuperiorType().equals(BuyersConst.BuyerType.Distribution)) {
            inParam.getFilterMap().put("districtCode", param.getDistrictCode());
        }
        //获取该批发市场现在所有的买家数
        return super.getCount(SqlId.SQL_BY_FIND_COUNT_BUYER, inParam);
    }

    /**
     * 获取辅码和校验码
     *
     * @return
     */
    private String getSecIdenCode(IBY121202RsParam param) {

        //辅码
        String secondAryCode;
        String saleStatus;
        //销售状态码
        if (null == param.getMarketingsStatus()) {
            saleStatus = "00";
        } else {
            saleStatus = StringUtil.PadLeft(String.valueOf(param.getMarketingsStatus()), NumberConst.IntDef.INT_TWO, "0");
        }
        if (!StringUtil.isEmpty(saleStatus)) {

            saleStatus = saleStatus + StringConst.MIDDLE_LINE;
        }

        //产品分类码
        String pdClassCode = "";

        //校验码
        String identifyCode = BuyersConst.BuyerType.CheckOutCode;
        //校验码+1 修改买家信息时截取买家编码最后两位处理
        if (!StringUtil.isNullOrEmpty(param.getBuyerCode())) {
            identifyCode = param.getBuyerCode().substring((param.getBuyerCode().length() - 2));
            if (identifyCode.substring(0, 1).equals("0")) {
                identifyCode = StringUtil.toInteger(identifyCode.substring(1, 2)) + NumberConst.IntDef.INT_ONE + "";
                if (identifyCode.length() == NumberConst.IntDef.INT_ONE) {
                    identifyCode = "0" + identifyCode;
                }
            } else {
                identifyCode = StringUtil.toInteger(identifyCode) + NumberConst.IntDef.INT_ONE + "";
            }
        }

        /** 画面选中的销售产品 */
        String[] buyerPdCla = param.getBuyerPdCla();
        /**画面选中的销售二级 */
        String[] buyerPdMac = param.getBuyerPdMac();

        if (buyerPdCla != null && buyerPdCla.length > 0) {

            for (int i = 0; i < buyerPdCla.length; i++) {

                if (param.getSuperiorType().equals(BuyersConst.BuyerType.Distribution)) {

                    pdClassCode += buyerPdCla[i] + buyerPdMac[i] + StringConst.MIDDLE_LINE;

                } else {
                    pdClassCode += buyerPdCla[i] + StringConst.MIDDLE_LINE;

                }
            }
        }

        secondAryCode = saleStatus + identifyCode;

        return secondAryCode;
    }

    /**
     * 获取批发市场编码
     *
     * @param param
     * @return
     */
    private String getMarketTerCode(IBY121202RsParam param) {

        String TerminalSeq = "";

        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("terminalId", param.getSuperiorId());

        //获取批发市场的顺序码
        ByMarketTerminal terminal = super.findOne(SqlId.SQL_TERMINAL_COUNT_BY_CODE, inParam);

        return terminal.getMarketCode();
    }

    /**
     * 获取菜场编码
     *
     * @param param
     * @return
     */
    private String getMarketFoodCode(IBY121202RsParam param) {

        String marketFoodSeq = "";

        BasePageParam inParam = new BasePageParam();
        inParam.setFilter("fodMarketId", param.getSuperiorId());

        ByMarketFood Food = super.findOne(SqlId.SQL_BY_MARKET_FOOD_COUNT, inParam);

        return Food.getMarketCode();
    }


    @Transactional(readOnly = true)
    public BrOBuyerInfo findBaseBuyerInfo(IBY121202RsParam rsParam) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId", rsParam.getBuyerId());
        return super.findOne(SqlId.SQL_FIND_BASE_BUYER_INFO, baseParam);
    }

    @Transactional(readOnly = true)
    public List<BrOBuyerPdCla> findBuyerPdCla(IBY121202RsParam rsParam) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId", rsParam.getBuyerId());
        return super.findList(SqlId.SQL_FIND_BUYER_PD_CLA, baseParam);
    }

    @Transactional(readOnly = true)
    public int findBuyerNameIsExist(IBY121202RsParam buyerBasicInfo) {
        return super.getCount(SqlId.SQL_FIND_BUYER_NAME_IS_EXIST, buyerBasicInfo);
    }

    @Transactional(readOnly = true)
    public int findAccountNameIsExist(IBY121202RsParam buyerBasicInfo) {
        return super.getCount(SqlId.SQL_FIND_ACCOUNT_NAME_IS_EXIST, buyerBasicInfo);
    }
}
