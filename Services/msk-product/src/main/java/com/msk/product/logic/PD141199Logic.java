package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdPriceprdLogiarea;
import com.msk.product.bean.PD141199Bean;
import com.msk.product.bean.PD141199Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * 价盘查询service
 *
 * @author zhou_ling
 */
@Service
public class PD141199Logic extends BaseLogic {
    @Autowired
    private PD141120Logic pd141120Logic;

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PD141199Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_ling
     */
    interface SqlId {

        static final String SQL_ID_SEARCH_BASIC_INFO = "searchBasicInfo";

        static final String SQL_ID_SEARCH_PRICE_CYCLE = "searchPriceCycle";
    }


    /**
     * 删除价盘信息
     *
     * @param pd141199Param 参数
     * @return 结果
     */
    @Transactional
    public void deletePriceprd(PD141199Param pd141199Param) {
        BaseParam param = new BaseParam();
        //根据价盘id查询该期价盘信息
        param.getFilterMap().put("pricecycleId", pd141199Param.getPricecycleId());
        List<PdPriceprdLogiarea> priceprdLogiareas = pd141120Logic.selectPriceprd(param);
        if (!CollectionUtils.isEmpty(priceprdLogiareas) && priceprdLogiareas.size() > 0) {
            //根据该期价盘的
            PD141199Param basicParam = new PD141199Param();
            basicParam.setPricecyclePeriod(priceprdLogiareas.get(0).getPricecyclePeriod());
            basicParam.setClassesCode(priceprdLogiareas.get(0).getClassesCode());
            basicParam.setMachiningCode(priceprdLogiareas.get(0).getMachiningCode());
            basicParam.setBreedCode(priceprdLogiareas.get(0).getBreedCode());
            basicParam.setPkgCode(priceprdLogiareas.get(0).getPkgCode());
            basicParam.setFeatureCode(priceprdLogiareas.get(0).getFeatureCode());
            basicParam.setWeightCode(priceprdLogiareas.get(0).getWeightCode());
            basicParam.setGradeCode(priceprdLogiareas.get(0).getGradeCode());
            basicParam.setLogiareaCode(priceprdLogiareas.get(0).getLogiareaCode());
            basicParam.setFilter("table","0");
            basicParam.setFilter("removeOne","1");
            pd141120Logic.remove(basicParam);
            basicParam.setFilter("table","1");
            basicParam.setFilter("removeOne","1");
            pd141120Logic.remove(basicParam);
        }
    }

    /**
     * 获取订单页面假数据
     *
     * @param pd141199Param pd141199Param
     * @return List<PD141199Bean>
     */
    @Transactional(readOnly = true)
    public List<PD141199Bean> findPriceCycle(PD141199Param pd141199Param) {

        logger.info("获取价盘数据");
        List<PD141199Bean> list = new ArrayList<PD141199Bean>();
        //价盘周期
        if (!StringUtil.isEmpty(pd141199Param.getPricecycleDate())) {
            String[] pricecycleDates = pd141199Param.getPricecycleDate().split("-");
            String pricecycleDate = pricecycleDates[0] + pricecycleDates[1];
            pd141199Param.setPricecyclePeriod(pricecycleDate + pd141199Param.getPricecycle());
        }
        // 价盘基本信息
        List<PD141199Bean> basicInfolist = super.findList(SqlId.SQL_ID_SEARCH_BASIC_INFO, pd141199Param);

        if (basicInfolist != null && basicInfolist.size() != 0) {
            for (int i = 0; i < basicInfolist.size(); i++) {
                PD141199Param basicParam = new PD141199Param();
                basicParam.setClassesCode(basicInfolist.get(i).getClassesCode());
                basicParam.setBreedCode(basicInfolist.get(i).getBreedCode());
                basicParam.setFeatureCode(basicInfolist.get(i).getFeatureCode());
                basicParam.setMachiningCode(basicInfolist.get(i).getMachiningCode());
                basicParam.setWeightCode(basicInfolist.get(i).getWeightCode());
                basicParam.setPkgCode(basicInfolist.get(i).getPkgCode());
                basicParam.setGradeCode(basicInfolist.get(i).getGradeCode());
                basicParam.setLogiareaCode(basicInfolist.get(i).getLogiareaCode());
                basicParam.setPricecyclePeriod(basicInfolist.get(i).getPricecyclePeriod());
                // 价盘信息
                List<PD141199Bean> priceCycleList = super.findList(SqlId.SQL_ID_SEARCH_PRICE_CYCLE, basicParam);
                PD141199Bean allInfoBean = new PD141199Bean();
                // 设置基本信息
                allInfoBean.setNumber(i + 1);
                allInfoBean.setClassesCode(basicInfolist.get(i).getClassesCode());
                allInfoBean.setPricecyclePeriod(basicInfolist.get(i).getPricecyclePeriod());
                allInfoBean.setPricecycleId(priceCycleList.get(0).getPricecycleId());
                allInfoBean.setClassesName(basicInfolist.get(i).getClassesName());
                allInfoBean.setMachiningCode(basicInfolist.get(i).getMachiningCode());
                allInfoBean.setMachiningName(basicInfolist.get(i).getMachiningName());
                allInfoBean.setBreedCode(basicInfolist.get(i).getBreedCode());
                allInfoBean.setBreedName(basicInfolist.get(i).getBreedName());
                allInfoBean.setFeatureCode(basicInfolist.get(i).getFeatureCode());
                allInfoBean.setFeatureName(basicInfolist.get(i).getFeatureName());
                allInfoBean.setWeightCode(basicInfolist.get(i).getWeightCode());
                allInfoBean.setPkgCode(basicInfolist.get(i).getPkgCode());
                allInfoBean.setGradeCode(basicInfolist.get(i).getGradeCode());
                //设置价盘的10个价盘信息
                allInfoBean.setSuperPriceofkg(priceCycleList.get(0).getPriceofkg());
                allInfoBean.setSuperPriceofbox(priceCycleList.get(0).getPriceofbox());
                allInfoBean.setOnePriceofkg(priceCycleList.get(1).getPriceofkg());
                allInfoBean.setOnePriceofbox(priceCycleList.get(1).getPriceofbox());
                allInfoBean.setTwoPriceofkg(priceCycleList.get(NumberConst.IntDef.INT_TWO).getPriceofkg());
                allInfoBean.setTwoPriceofbox(priceCycleList.get(NumberConst.IntDef.INT_TWO).getPriceofbox());
                allInfoBean.setThreePriceofkg(priceCycleList.get(NumberConst.IntDef.INT_THREE).getPriceofkg());
                allInfoBean.setThreePriceofbox(priceCycleList.get(NumberConst.IntDef.INT_THREE).getPriceofbox());
                allInfoBean.setFourPriceofkg(priceCycleList.get(NumberConst.IntDef.INT_FOUR).getPriceofkg());
                allInfoBean.setFourPriceofbox(priceCycleList.get(NumberConst.IntDef.INT_FOUR).getPriceofbox());
                allInfoBean.setFivePriceofkg(priceCycleList.get(NumberConst.IntDef.INT_FIVE).getPriceofkg());
                allInfoBean.setFivePriceofbox(priceCycleList.get(NumberConst.IntDef.INT_FIVE).getPriceofbox());
                allInfoBean.setSixPriceofkg(priceCycleList.get(NumberConst.IntDef.INT_SIX).getPriceofkg());
                allInfoBean.setSixPriceofbox(priceCycleList.get(NumberConst.IntDef.INT_SIX).getPriceofbox());
                allInfoBean.setSevenPriceofkg(priceCycleList.get(NumberConst.IntDef.INT_SEVEN).getPriceofkg());
                allInfoBean.setSevenPriceofbox(priceCycleList.get(NumberConst.IntDef.INT_SEVEN).getPriceofbox());
                allInfoBean.setEightPriceofkg(priceCycleList.get(NumberConst.IntDef.INT_EIGHT).getPriceofkg());
                allInfoBean.setEightPriceofbox(priceCycleList.get(NumberConst.IntDef.INT_EIGHT).getPriceofbox());
                allInfoBean.setNinePriceofkg(priceCycleList.get(NumberConst.IntDef.INT_NINE).getPriceofkg());
                allInfoBean.setNinePriceofbox(priceCycleList.get(NumberConst.IntDef.INT_NINE).getPriceofbox());
                list.add(allInfoBean);
            }
        }
        return list;
    }


    /**
     * 获取订单页面假数据
     *
     * @return PaList<PD141199Bean>
     */
    public List<PD141199Bean> findPriceCycleList() {

        logger.info("获取假数据");
        List<PD141199Bean> list = new ArrayList<PD141199Bean>();
        PD141199Bean pD141199Bean = new PD141199Bean();// 假数据,获取订单列表中的数据
        pD141199Bean.setNumber(1);
        pD141199Bean.setBreedCode("011101");
        pD141199Bean.setFeatureCode("011101");
        pD141199Bean.setPkgCode("001");
        pD141199Bean.setSuperPriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setSuperPriceofbox(new BigDecimal(NumberConst.IntDef.INT_FOUR));
        pD141199Bean.setOnePriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setOnePriceofbox(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setTwoPriceofkg(new BigDecimal(NumberConst.IntDef.INT_FOUR));
        pD141199Bean.setTwoPriceofbox(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setThreePriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setThreePriceofbox(new BigDecimal(NumberConst.IntDef.INT_SEVEN));
        pD141199Bean.setFourPriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setFourPriceofbox(new BigDecimal(NumberConst.IntDef.INT_SEVEN));
        pD141199Bean.setFivePriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setFivePriceofbox(new BigDecimal(NumberConst.IntDef.INT_SEVEN));
        pD141199Bean.setSixPriceofkg(new BigDecimal(NumberConst.IntDef.INT_FOUR));
        pD141199Bean.setSixPriceofbox(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setSevenPriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setSevenPriceofbox(new BigDecimal(NumberConst.IntDef.INT_FOUR));
        pD141199Bean.setEightPriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setEightPriceofbox(new BigDecimal(NumberConst.IntDef.INT_SEVEN));
        pD141199Bean.setNinePriceofkg(new BigDecimal(NumberConst.IntDef.INT_TWO));
        pD141199Bean.setNinePriceofbox(new BigDecimal(NumberConst.IntDef.INT_FOUR));
        list.add(pD141199Bean);

        return list;

    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
