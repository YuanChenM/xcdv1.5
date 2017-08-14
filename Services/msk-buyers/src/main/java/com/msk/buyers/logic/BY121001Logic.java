package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121001Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByMarketFood;
import com.msk.core.entity.ByMarketTerminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhou_yajun on 2016/8/2.
 */
public class BY121001Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        //根据买家ID查询买家基本信息
        static String SQL_GET_BUYER_BY_ID = "getBuyerById";
        //根据批发市场ID查询批发市场信息
        static String SQL_TERMINAL_BY_ID = "getMarketTerminalById";
        //根据菜场ID查询菜场信息
        static String SQL_FOOD_BY_ID = "getMarketFoodById";
        //查询批发市场菜场买家顺序码
        static String SQL_FIND_SEQUENCE_BY_SUPERIORID = "findSequenceBySuperiorId";
        //查询区县买家顺序码
        static String SQL_FIND_SEQUENCE_BY_AREA = "findSequenceByArea";
    }

    /**
     * 变更买家编码
     * @param by121001Bean
     * @return
     */
    @Transactional(readOnly = true)
    public String getNewBuyerCode(BY121001Bean by121001Bean){
        // 取得该买家的信息
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",by121001Bean.getBuyerId());
        ByBuyerBasicInfo buyerInfo = super.findOne(SqlId.SQL_GET_BUYER_BY_ID, param);
        //买家编码
        String buyerCode = buyerInfo.getBuyerCode();
        by121001Bean.setBuyerCode(buyerCode);

        if(!StringUtil.isNullOrEmpty(by121001Bean.getSuperiorType())){
            //买家类型发生变更时
            if(!buyerInfo.getSuperiorType().equals(by121001Bean.getSuperiorType())){
                buyerCode = getBuyerCode(by121001Bean);
            }
        }
        if(!StringUtil.isNullOrEmpty(by121001Bean.getSuperiorId())){
            //判断是否存在市场ID
            if(StringUtil.isNullOrEmpty(buyerInfo.getSuperiorId())){
                //物流区划发生变更时
                if(!StringUtil.isNullOrEmpty(by121001Bean.getLgcsAreaCode())){
                    if(StringUtil.isNullOrEmpty(buyerInfo.getLgcsAreaCode()) || !buyerInfo.getLgcsAreaCode().equals(by121001Bean.getLgcsAreaCode())){
                        buyerCode = getBuyerCode(by121001Bean);
                    }
                }
                if(!StringUtil.isNullOrEmpty(by121001Bean.getCityCode())){
                    if(StringUtil.isNullOrEmpty(buyerInfo.getCityCode()) || !buyerInfo.getCityCode().equals(by121001Bean.getCityCode())){
                        buyerCode = getBuyerCode(by121001Bean);
                    }
                }
                if(!StringUtil.isNullOrEmpty(by121001Bean.getDistrictCode())){
                    if(StringUtil.isNullOrEmpty(buyerInfo.getDistrictCode()) || !buyerInfo.getDistrictCode().equals(by121001Bean.getDistrictCode())){
                        buyerCode = getBuyerCode(by121001Bean);
                    }
                }
            }else {
                //市场信息发生变更时
                if(!buyerInfo.getSuperiorId().equals(by121001Bean.getSuperiorId())){
                    buyerCode = getBuyerCode(by121001Bean);
                }
            }
        }
        if(!StringUtil.isNullOrEmpty(by121001Bean.getMarketingsStatus())){
            //买家上线状态发生变更时
            if(!buyerInfo.getMarketingsStatus().equals(by121001Bean.getMarketingsStatus())){
                if(!StringUtil.isNullOrEmpty(by121001Bean.getBuyerCode())){
                    String marketingStatus = this.marketingsStatusCode(by121001Bean);
                    String identifyCode = this.getSecIdenCode(by121001Bean);
                    int index = buyerCode.indexOf("-");
                    buyerCode = buyerCode.replace(buyerCode.substring(index - 2,buyerCode.length()),marketingStatus + identifyCode);
                }else{
                    buyerCode = getBuyerCode(by121001Bean);
                }
            }
        }
        return buyerCode;
    }

    /**
     * 获取新的买家编码返回
     * @param by121001Bean
     * @return
     */
    @Transactional(readOnly = true)
    private String getBuyerCode(BY121001Bean by121001Bean){
        String buyerCode = "";
        //买家分类码
        buyerCode = buyerCode + this.getTypecode(by121001Bean.getSuperiorType(),by121001Bean.getSuperiorSubType());
        //买家行政区划编码
        if(BuyersConst.BuyerType.Distribution.equals(by121001Bean.getSuperiorType())){
            buyerCode = this.getDiviSionCode(by121001Bean);
        }else{
            buyerCode = buyerCode + this.getDiviSionCode(by121001Bean);
        }
        //买家顺序码
        buyerCode = buyerCode + this.sequenceCode(by121001Bean);
        //买家上线状态码
        buyerCode = buyerCode + this.marketingsStatusCode(by121001Bean);
        //买家校验码
        buyerCode = buyerCode + this.getSecIdenCode(by121001Bean);
        return buyerCode;
    }

    /**
     * 获取买家分类码
     * @param buyerType
     * @param buyerSubType
     * @return
     */
    private String getTypecode(String buyerType,String buyerSubType){

        String typeCode = "";

        switch (buyerType){
            //分销买家
            case BuyersConst.BuyerType.Distribution:
                typeCode = StringUtil.PadLeft(buyerType, NumberConst.IntDef.INT_TWO, "0");
                break ;
            //菜场买家
            case BuyersConst.BuyerType.Market:
                typeCode = StringUtil.PadLeft(buyerType, NumberConst.IntDef.INT_TWO, "0");
                break ;
            //团膳买家
            case BuyersConst.BuyerType.GroupMeals:
                typeCode = StringUtil.PadLeft(buyerType, NumberConst.IntDef.INT_TWO, "0");
                break ;
            //火锅买家
            case BuyersConst.BuyerType.HotPot:
                typeCode = StringUtil.PadLeft(buyerType, NumberConst.IntDef.INT_TWO, "0");
                break ;
            //中餐买家
            case BuyersConst.BuyerType.ChineseFood:
                typeCode = StringUtil.PadLeft(buyerType, NumberConst.IntDef.INT_TWO, "0").concat(StringUtil.PadLeft(buyerSubType, NumberConst.IntDef.INT_TWO, "0"));
                break ;
            //加工厂买家
            case BuyersConst.BuyerType.Processing:
                typeCode = StringUtil.PadLeft(buyerType, NumberConst.IntDef.INT_TWO, "0").concat(StringUtil.PadLeft(buyerSubType, NumberConst.IntDef.INT_TWO, "0"));
                break ;
        }

        return typeCode;
    }

    /**
     * 获取行政区划编码
     * @return
     */
    @Transactional(readOnly = true)
    public String getDiviSionCode(BY121001Bean by121001Bean){

        String diviSionCode;

        BaseParam param = new BaseParam();

        switch (by121001Bean.getSuperiorType()){
            //分销买家行政区划编码为批发市场编码
            case BuyersConst.BuyerType.Distribution:
                //如果批发市场编码已存在
                if(!StringUtil.isNullOrEmpty(by121001Bean.getMarketCode())){
                    diviSionCode = by121001Bean.getMarketCode();
                    break;
                }
                param.setFilter("terminalId", by121001Bean.getSuperiorId());
                //获取批发市场编码
                ByMarketTerminal terminal =  super.findOne(SqlId.SQL_TERMINAL_BY_ID, param);
                diviSionCode = terminal.getMarketCode();
                break;
            //菜场买家行政区划编码为菜场编码
            case BuyersConst.BuyerType.Market:
                //如果菜场编码已存在
                if(!StringUtil.isNullOrEmpty(by121001Bean.getMarketCode())){
                    diviSionCode = by121001Bean.getMarketCode();
                    break;
                }
                param.setFilter("fodMarketId", by121001Bean.getSuperiorId());
                //获取菜场编码
                ByMarketFood marketFood =  super.findOne(SqlId.SQL_FOOD_BY_ID, param);
                diviSionCode = marketFood.getMarketCode();
                break;
            //加工厂买家
            case BuyersConst.BuyerType.Processing:
                //卤肉熟食买家
                if(BuyersConst.BuyerType.BraiseFood.equals(by121001Bean.getSuperiorSubType())){
                    //如果批发市场编码已存在
                    if(!StringUtil.isNullOrEmpty(by121001Bean.getMarketCode())){
                        diviSionCode = by121001Bean.getMarketCode();
                        break;
                    }
                    param.setFilter("fodMarketId", by121001Bean.getSuperiorId());
                    //获取菜场编码
                    ByMarketFood marketBraiseFood =  super.findOne(SqlId.SQL_FOOD_BY_ID, param);
                    diviSionCode = marketBraiseFood.getMarketCode();
                    break;
                }else{
                    diviSionCode = by121001Bean.getLgcsAreaCode() + by121001Bean.getCityCode() + by121001Bean.getDistrictCode();
                    break;
                }
            default:
                diviSionCode = by121001Bean.getLgcsAreaCode() + by121001Bean.getCityCode() + by121001Bean.getDistrictCode();
                break;
        }
        return diviSionCode;
    }
    /**
     * 获取获取买家顺序码
     * @return
     */
    @Transactional(readOnly = true)
    public String sequenceCode(BY121001Bean by121001Bean){
        //买家顺序码
        String sequenceCode;

        BaseParam inParam = new BaseParam();
        inParam.getFilterMap().put("lgcsAreaCode",by121001Bean.getLgcsAreaCode());
        inParam.getFilterMap().put("cityCode",by121001Bean.getCityCode());
        inParam.getFilterMap().put("superiorId", by121001Bean.getSuperiorId());
        inParam.getFilterMap().put("districtCode",by121001Bean.getDistrictCode());
        inParam.getFilterMap().put("superiorType",by121001Bean.getSuperiorType());

        //根据买家类型不同生成不同的买家顺序码
        switch (by121001Bean.getSuperiorType()){
            //分销买家
            case BuyersConst.BuyerType.Distribution:
                inParam.getFilterMap().put("leftPlace",3);
                inParam.getFilterMap().put("rightPlace",8);
                BY121001Bean sequenceTer = this.findOne(SqlId.SQL_FIND_SEQUENCE_BY_SUPERIORID, inParam);
                Integer maxSequenceTer = 0;
                if(sequenceTer != null){
                    maxSequenceTer = StringUtil.toInteger(sequenceTer.getMaxSequenceCode());
                }
                if(maxSequenceTer != null && maxSequenceTer < 999){
                    maxSequenceTer = maxSequenceTer + 1;
                }else{
                    throw new BusinessException("买家顺序码超出上限获取失败");
                }
                sequenceCode = StringUtil.PadLeft(String.valueOf(maxSequenceTer), NumberConst.IntDef.INT_THREE, "0");
                break;
            //菜场买家
            case BuyersConst.BuyerType.Market:
                inParam.getFilterMap().put("leftPlace",2);
                inParam.getFilterMap().put("rightPlace",7);
                BY121001Bean sequenceFod = this.findOne(SqlId.SQL_FIND_SEQUENCE_BY_SUPERIORID, inParam);
                Integer maxSequenceFod = 0;
                if(sequenceFod != null){
                    maxSequenceFod = StringUtil.toInteger(sequenceFod.getMaxSequenceCode());
                }
                if(maxSequenceFod != null && maxSequenceFod < 99){
                    maxSequenceFod = maxSequenceFod + 1;
                }else{
                    throw new BusinessException("买家顺序码超出上限获取失败");
                }
                sequenceCode = StringUtil.PadLeft(String.valueOf(maxSequenceFod), NumberConst.IntDef.INT_TWO, "0");
                break;
            //加工厂买家
            case BuyersConst.BuyerType.Processing:
                //加工厂卤味熟食买家
                if(BuyersConst.BuyerType.BraiseFood.equals(by121001Bean.getSuperiorSubType())){
                    inParam.getFilterMap().put("leftPlace",2);
                    inParam.getFilterMap().put("rightPlace",7);
                    BY121001Bean sequencePro = this.findOne(SqlId.SQL_FIND_SEQUENCE_BY_SUPERIORID, inParam);
                    Integer maxSequencePro = 0;
                    if(sequencePro != null){
                        maxSequencePro = StringUtil.toInteger(sequencePro.getMaxSequenceCode());
                    }
                    if(maxSequencePro != null && maxSequencePro < 99){
                        maxSequencePro = maxSequencePro + 1;
                    }else{
                        throw new BusinessException("买家顺序码超出上限获取失败");
                    }
                    sequenceCode = StringUtil.PadLeft(String.valueOf(maxSequencePro), NumberConst.IntDef.INT_TWO, "0");
                }else{
                    inParam.getFilterMap().put("leftPlace",3);
                    inParam.getFilterMap().put("rightPlace",8);
                    BY121001Bean sequencePro = this.findOne(SqlId.SQL_FIND_SEQUENCE_BY_AREA, inParam);
                    Integer maxSequencePro = 0;
                    if(sequencePro != null){
                        maxSequencePro = StringUtil.toInteger(sequencePro.getMaxSequenceCode());
                    }
                    if(maxSequencePro != null && maxSequencePro < 999){
                        maxSequencePro = maxSequencePro + 1;
                    }else{
                        throw new BusinessException("买家顺序码超出上限获取失败");
                    }
                    sequenceCode = StringUtil.PadLeft(String.valueOf(BuyersConst.BuyerType.IdentCode), NumberConst.IntDef.INT_TWO, "0") + StringUtil.PadLeft(String.valueOf(maxSequencePro), NumberConst.IntDef.INT_THREE, "0");
                }
                break;
            default:
                inParam.getFilterMap().put("leftPlace",3);
                inParam.getFilterMap().put("rightPlace",8);
                BY121001Bean sequenceDefault = this.findOne(SqlId.SQL_FIND_SEQUENCE_BY_AREA, inParam);
                Integer maxSequenceDefault = 0;
                if(sequenceDefault != null){
                    maxSequenceDefault = StringUtil.toInteger(sequenceDefault.getMaxSequenceCode());
                }
                if(maxSequenceDefault != null && maxSequenceDefault < 999){
                    maxSequenceDefault = maxSequenceDefault + 1;
                }else{
                    throw new BusinessException("买家顺序码超出上限获取失败");
                }
                sequenceCode = StringUtil.PadLeft(String.valueOf(maxSequenceDefault), NumberConst.IntDef.INT_THREE, "0");;
        }
        return sequenceCode;
    }

    /**
     * 获取上线状态码
     * @param by121001Bean
     * @return
     */
    public String marketingsStatusCode(BY121001Bean by121001Bean){
        //上线状态码
        String saleStatusCode;
        if(null == by121001Bean.getMarketingsStatus()){
            saleStatusCode = "02" + StringConst.MIDDLE_LINE;
        }else{
            saleStatusCode = StringUtil.PadLeft(String.valueOf(by121001Bean.getMarketingsStatus()), NumberConst.IntDef.INT_TWO, "0") + StringConst.MIDDLE_LINE;
        }
        return saleStatusCode;
    }
    /**
     * 获取校验码
     * @return
     */
    public String getSecIdenCode(BY121001Bean by121001Bean){
        //校验码
        String identifyCode = BuyersConst.BuyerType.CheckOutCode;
        //校验码+1 修改买家信息时截取买家编码最后两位处理
        if(!StringUtil.isNullOrEmpty(by121001Bean.getBuyerCode())){
            identifyCode = by121001Bean.getBuyerCode().substring((by121001Bean.getBuyerCode().length()-2));
            if(identifyCode.substring(0,1).equals("0")){
                identifyCode = StringUtil.toInteger(identifyCode.substring(1, 2)) + NumberConst.IntDef.INT_ONE + "";
                if(identifyCode.length() == NumberConst.IntDef.INT_ONE){
                    identifyCode = "0" + identifyCode;
                }
            }else{
                identifyCode = StringUtil.toInteger(identifyCode) + NumberConst.IntDef.INT_ONE + "";
            }
        }
        return identifyCode;
    }
}
