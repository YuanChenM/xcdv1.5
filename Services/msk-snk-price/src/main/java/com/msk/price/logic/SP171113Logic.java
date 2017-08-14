package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171112Bean;
import com.msk.price.bean.SP171113Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * SP171113Logic.
 *
 * @author zhou_ling
 */
@Service
public class SP171113Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171113Logic.class);

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
     * @author zhou_Ling
     */
    interface SqlId {
        //查询价盘通道等级平衡系数
        static String SQL_PRICE_PERCENT_FIND = "findPricePercent";
        //查询上一个价盘周期的价格
        static String SQL_BEFORE_PRICE_FIND = "findBeforePrice";
        //查询当期价盘周期的价格
        static String SQL_PRICE_FIND = "findPrice";
        //根据ID查询净重
        static String SQL_WEIGHT_FIND = "findWeight";
    }

    /**
     * 查询价盘等级平衡系数
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171113Bean> findPricePercent(BaseParam param) {
        // 查询价盘等级平衡系数
        List<SP171113Bean> pricePercentList = super.findList(SqlId.SQL_PRICE_PERCENT_FIND, param);
        return pricePercentList;
    }

    /**
     * 查询上一个价盘周期的价格
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171113Bean> findBeforePrice(BaseParam param) {
        // 查询上一个价盘周期的价格
        List<SP171113Bean> pricePercentList = super.findList(SqlId.SQL_BEFORE_PRICE_FIND, param);
        return pricePercentList;
    }

    /**
     * 查询当期价盘周期的价格
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171113Bean> findPrice(BaseParam param) {
        // 查询当期价盘周期的价格
        List<SP171113Bean> pricePercentList = super.findList(SqlId.SQL_PRICE_FIND, param);
        return pricePercentList;
    }

    /**
     * 修改价盘价格
     *
     * @return
     */
    @Transactional
    public void modifyPrice(SP171113Bean param) {

        Integer[] priceIdArray = param.getPriceIdArray();
        BigDecimal[] wayGradePriceArray = param.getWayGradePriceArray();
        for (int i = 0; i < priceIdArray.length; i++) {
            BaseParam baseParam = new BaseParam();

            baseParam.getFilterMap().put("price", wayGradePriceArray[i]);
            baseParam.getFilterMap().put("updId", param.getUpdId());
            baseParam.getFilterMap().put("updDate", DateTimeUtil.getCustomerDate());
            baseParam.getFilterMap().put("priceId", priceIdArray[i]);

            Integer delFlg = NumberConst.IntDef.INT_ONE;
            //如果修改价格大于0则参与分销，delFlg设为0
            if(BigDecimal.ZERO.compareTo(wayGradePriceArray[i]) == -1){
                delFlg = NumberConst.IntDef.INT_ZERO;
            }
            baseParam.getFilterMap().put("delFlg", delFlg);

            //获取净重，计算箱价
            SP171112Bean bean = this.findOne(SqlId.SQL_WEIGHT_FIND,baseParam);
            BigDecimal piceBox = new BigDecimal(NumberConst.IntDef.INT_ZERO);
            BigDecimal weight = new BigDecimal(NumberConst.IntDef.INT_ZERO);
            if(bean != null && !StringUtil.isNullOrEmpty(bean.getWeightName()) &&
                    !StringConst.MIDDLE_LINE.equals(bean.getWeightName())){
                int num = bean.getWeightName().length();
                for(int j = 0 ; j < bean.getWeightName().length() ; j++){ //循环遍历字符串
                    if(Character.isLetter(bean.getWeightName().charAt(j))){     //用char包装类中的判断数字的方法判断每一个字符
                        num = j;
                        break;
                    }
                }
                weight = new BigDecimal(bean.getWeightName().substring(NumberConst.IntDef.INT_ZERO,num));
                piceBox = DecimalUtil.multiply(wayGradePriceArray[i], weight);

                baseParam.getFilterMap().put("piceBox", piceBox);
            }

            this.modify(baseParam);

        }

    }

}
