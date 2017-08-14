package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.*;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * BY121304Logic.
 *
 * @author yuan_chen
 */
@Service
public class BY121304Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121304Logic.class);

    @Autowired
    private IBY121202Logic iby121202Logic;
    @Autowired
    private IBY121203Logic iby121203Logic;
    @Autowired
    private IBY121204Logic iby121204Logic;
    @Autowired
    private IBY121205Logic iby121205Logic;
    @Autowired
    private IBY121206Logic iby121206Logic;
    @Autowired
    private IBY121208Logic iby121208Logic;
    @Autowired
    private IBY121209Logic iby121209Logic;
    @Autowired
    private CommonLogic commonLogic;
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

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家证照信息获取
        static String SQL_FIND_LINECE = "findLinece";
        static String SQL_FIND_ACCOUNT_INFO = "findAccountInfo";
        static String SQL_DELETE_EMPLOYEE = "deleteEmployee";
        static String SQL_FIND_MARKET_TERMINAL_LIST = "findMarkerTerminalList";
        static String SQL_FIND_MARKET_TERMINAL = "findMarkerTerminal";
        static String SQL_FIND_MARKET_FOOD_LIST = "findMarkerFoodList";
        static String SQL_FIND_MARKET_FOOD = "findMarkerFood";
        static String SQL_FIND_ACCOUNT = "findAccount";
        static String SQL_UPDATE_ACCOUNT_PWD = "updateAccountPwd";
        static String SQL_FIND_BUYER_NAME_NUM = "findBuyerNameNum";
    }

    /**
     * 获取买家账号信息
     * @param buyerId
     * @return
     */
    @Transactional(readOnly = true)
    public ByBuyerAccount findBuyerAccount(String buyerId){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId",buyerId);
        ByBuyerAccount buyerAccount = super.findOne(SqlId.SQL_FIND_ACCOUNT_INFO,inParam);
        return buyerAccount;
    }
    /**
     * 获取买家详细信息
     * @param buyerId
     * @return
     */
    @Transactional(readOnly = true)
    public IBY121202RsParam findBuyerDetail(String buyerId){
        IBY121202RsParam iby121202RsParam = new IBY121202RsParam();
        iby121202RsParam.setBuyerId(buyerId);
        IBY121202RsParam buyerDetail = iby121202Logic.findBuyerDetailInfo(iby121202RsParam);
        if(StringUtil.isNullOrEmpty(buyerDetail.getLgcsAreaCode())){
            buyerDetail.setLgcsAreaCode("");
        }
        if(StringUtil.isNullOrEmpty(buyerDetail.getCityCode())){
            buyerDetail.setCityCode("");
        }
        if(StringUtil.isNullOrEmpty(buyerDetail.getDistrictCode())){
            buyerDetail.setDistrictCode("");
        }
        DistrictParam param = new DistrictParam();
        String[] composeCodes ={buyerDetail.getCityCode()+buyerDetail.getDistrictCode()};
        // 物流区相关数据
        param.setComposeCodes(composeCodes);
        param.setFlag(0);
        List<DistrictBean> districtList = RestCommUtil.getDistrictList(param).getResult().getDistrictList();
        for (DistrictBean districtBean :districtList){
            if(null != districtBean.getLgcsAreaCode()
                    && districtBean.getLgcsAreaCode().equals(buyerDetail.getLgcsAreaCode())){
                buyerDetail.setLgcsAreaName(districtBean.getLgcsAreaName());
            }
            if(districtBean.getCityCode().equals(buyerDetail.getCityCode())){
                buyerDetail.setCityName(districtBean.getCityName());
            }
            if (districtBean.getCityCode().equals(buyerDetail.getCityCode())
                    && districtBean.getDistrictCode().equals(buyerDetail.getDistrictCode())) {
                buyerDetail.setDistrictName(districtBean.getDistrictName());
            }
            if (null != buyerDetail.getLgcsAreaName() && null != buyerDetail.getCityName()
                    && null != buyerDetail.getDistrictName()) {
                break;
            }
        }

        return buyerDetail;
    }

    /**
     * 删除买家雇员信息
     * @param param
     * @return
     */
    @Transactional
    public int deleteEmployee(IBY121207RsParam param){
        return super.modify(SqlId.SQL_DELETE_EMPLOYEE,param);
    }
    /**
     * 获取批发市场数据
     * @param lgcsAreaCode
     * @param cityCode
     * @param districtCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketTerminal> findBuyerMarketTerminalList(String lgcsAreaCode,String cityCode,String districtCode){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("lgcsAreaCode",lgcsAreaCode);
        inParam.setFilter("cityCode",cityCode);
        inParam.setFilter("districtCode",districtCode);
        List<ByMarketTerminal> byMarketTerminal = super.findList(SqlId.SQL_FIND_MARKET_TERMINAL_LIST,inParam);
        return byMarketTerminal;
    }
    /**
     * 获取批发市场信息
     * @param terMarketId
     * @return
     */
    @Transactional(readOnly = true)
    public ByMarketTerminal findBuyerMarketTerminal(String terMarketId){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("terMarketId",terMarketId);
        ByMarketTerminal marketTerminal = super.findOne(SqlId.SQL_FIND_MARKET_TERMINAL,inParam);
        return marketTerminal;
    }
    /**
     * 获取菜场数据
     * @param lgcsAreaCode
     * @param cityCode
     * @param districtCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByMarketFood> findBuyerMarketFoodList(String lgcsAreaCode,String cityCode,String districtCode){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("lgcsAreaCode",lgcsAreaCode);
        inParam.setFilter("cityCode",cityCode);
        inParam.setFilter("districtCode",districtCode);
        List<ByMarketFood> byMarketFoods = super.findList(SqlId.SQL_FIND_MARKET_FOOD_LIST,inParam);
        return byMarketFoods;
    }
    /**
     * 获取菜场信息
     * @param fodMarketId
     * @return
     */
    @Transactional(readOnly = true)
    public ByMarketFood findByMarketFood(String fodMarketId){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("fodMarketId",fodMarketId);
        ByMarketFood marketFood = super.findOne(SqlId.SQL_FIND_MARKET_FOOD,inParam);
        return marketFood;
    }
    /**
     * 买家证照信息查询
     * @param buyerId
     * @return
     */
    @Transactional(readOnly = true)
    public IBY121205RsParam buyerLicenceFind(String buyerId){
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId",buyerId);
        //营业执照类型
        Map<String,String> LicTypeMap = CodeMasterManager.findCodeMasterMap("LicType");
        // 法定代表人证件类型
        Map<String,String> LegalLicTypeMap = CodeMasterManager.findCodeMasterMap("LegalLicType");
        IBY121205RsParam iby121205RsParam = super.findOne(SqlId.SQL_FIND_LINECE, baseParam);
        if(null !=iby121205RsParam ){
            iby121205RsParam.setLicDes(LicTypeMap.get(iby121205RsParam.getLicName()));
            iby121205RsParam.setLegalLicDes(LegalLicTypeMap.get(iby121205RsParam.getLegalLicType()));
        }
        return iby121205RsParam;
    }

    /**
     * 买家证照信息更新
     * @param param
     * @return
     */
    public int buyerLicenceModify(BuyerLicenceBean param){
        return iby121205Logic.buyerLicenceModify(param);
    }
    /**
     * 买家证照图片信息更新
     * @param param
     * @return
     */
    public int buyerLicencePicModify(IBY121206RsParam param){
        return iby121206Logic.buyerLicencePicModify(param);
    }
    /**
     * 买家图片信息查询
     * @param buyerId
     * @return
     */
    public IBY121206RsParam buyerLicencePicFind(String buyerId){
        IBY121206RsParam iby121206RsParam = new IBY121206RsParam();
        iby121206RsParam.setBuyerId(buyerId);
        IBY121206RsParam buyerLicencePic = iby121206Logic.buyerLicencePicFind(iby121206RsParam);
        return buyerLicencePic;
    }

    /**
     * 买家收货地址查询
     * @param buyerId
     * @return
     */
    public List<ByBuyerRecAddr> buyerReceiveAddrFind(String buyerId){
        ByBuyerRecAddr byBuyerRecAddr = new ByBuyerRecAddr();
        byBuyerRecAddr.setBuyerId(buyerId);
        List<ByBuyerRecAddr> recAddrList = iby121208Logic.buyerReceiveAddrFind(byBuyerRecAddr);
        return recAddrList;
    }
    /**
     * 买家收货时间查询
     * @param buyerId
     * @return
     */
    public List<ByBuyerRecTime> buyerReceiveTimeFind(String buyerId){
        ByBuyerRecTime byBuyerRecTime = new ByBuyerRecTime();
        byBuyerRecTime.setBuyerId(buyerId);
        List<ByBuyerRecTime> recTimeList = iby121209Logic.buyerReceiveTimeFind(byBuyerRecTime);
        return recTimeList;
    }
    /**
     * 买家销售对象查询
     * @param buyerId
     * @return
     */
    public List<ByBuyerSalestarget> buyerSalesTargetFind(String buyerId){
        ByBuyerSalestarget byBuyerSalestarget = new ByBuyerSalestarget();
        byBuyerSalestarget.setBuyerId(buyerId);
        List<ByBuyerSalestarget> salestargetList = iby121204Logic.buyerSalesTargetFind(byBuyerSalestarget);
        return salestargetList;
    }
    /**
     * 买家销售产品查询
     * @param buyerId
     * @return
     */
    public List<ByBuyerPdCla> buyerPdClassificationFind(String buyerId){
        ByBuyerPdCla byBuyerPdCla = new ByBuyerPdCla();
        byBuyerPdCla.setBuyerId(buyerId);
        List<ByBuyerPdCla> pdClaList = iby121203Logic.buyerPdClassificationFind(byBuyerPdCla);
        return pdClaList;
    }

    /**
     * 调用接口 查询二级分类信息
     * @param param
     * @return
     */
    public List<PdMachining> findPdMachining(BaseParam param) {
        RsResponse<PdMachining[]> response= RestCommUtil.findPdMachining(param);
        PdMachining[] results = response.getResult();
        return (null == results) ? new ArrayList<PdMachining>() : Arrays.asList(results);
    }
    /**
     *重置账号和密码
     * @param
     * @return
     */
    public int resetAccountPwd(ByBuyerAccount byBuyerAccount){
        int i = super.getCount(SqlId.SQL_FIND_ACCOUNT, byBuyerAccount);
        //查询账号名称是否和其他买家名称相同
        int buyerNameNum = super.getCount(SqlId.SQL_FIND_BUYER_NAME_NUM, byBuyerAccount);
        if(i>0){
            // 若该账号已存在
            return 0;
        }else if(buyerNameNum>0){
            return -1;
        }else{

           /* byBuyerAccount.setCrtTime(DateTimeUtil.getCustomerDate());
            byBuyerAccount.setUpdTime(DateTimeUtil.getCustomerDate());
            byBuyerAccount.setActTime(DateTimeUtil.getCustomerDate());*/
            return super.modify(SqlId.SQL_UPDATE_ACCOUNT_PWD, byBuyerAccount);
        }
    }

}
