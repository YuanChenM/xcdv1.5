package com.msk.ds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.ds.bean.SC181103Bean;
import com.msk.ds.bean.SC181103Param;
import com.msk.ds.logic.DistSuppChainLogic;
import com.msk.ds.logic.SC181103Logic;
import com.msk.ds.rest.comm.RestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * SC181103Controller
 *
 * @author li_kai1
 *
 */
@Controller
@RequestMapping("SC181103")
public class SC181103Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC181103Controller.class);

    @Autowired
    private SC181103Logic sc181103Logic;

    @Autowired
    private DistSuppChainLogic distSuppChainLogic;

    // 21号
    int DEPARTURE_1 = NumberConst.IntDef.INT_TWENTY_ONE;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(SC181103Param sc181103Param,Model model) {
        logger.debug("发货计划单录入初始化");
        BaseParam baseParam = new BaseParam();
        this.setCommonParam(baseParam);

        //获取分销月度
        Date currentDate = new Date();
        String currentDay = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DD, currentDate);
        // 分销月份
        String distMonth = null;
        // 查询卖家数据用的月份
        String searchMonth = null;
        //半旬码
        int halfCode = NumberUtils.INTEGER_ZERO;
        //半旬名称
        String halfName = null;
        if (Integer.parseInt(currentDay) >= DEPARTURE_1) {
            // 21号开始属于下个分销月度
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.MONTH, NumberConst.IntDef.INT_ONE);
            distMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, c.getTime());
            // 分销下个月时检索这个月的数据
            searchMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, currentDate);
        } else {
            distMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, currentDate);
            // 分销当前月时检索上个月的数据
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.MONTH, NumberConst.IntDef.INT_N_ONE);
            searchMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, c.getTime());
        }

        // 获取半旬
        halfCode = sc181103Logic.getHalfCode(currentDay);
        halfName = sc181103Logic.getHalfName(halfCode);

        // 获取区域下拉列表
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> areaList = RestUtil.getAllLgcsList(districtParam);
        // 获取供应商下拉框
        List<SC181103Bean> suppList = null;
        String areaCode = null;
        String suppCode = null;
        String areaName = null;
        if (!CollectionUtils.isEmpty(areaList)) {
            SC181103Param areaParam = new SC181103Param();
            areaCode = areaList.get(0).getLgcsAreaCode();
            areaParam.setLgcsCode(areaCode);
            areaName = areaList.get(0).getLgcsAreaName();
            this.setCommonParam(areaParam);
            suppList = sc181103Logic.findSuppList(areaParam);
        }
        if (!CollectionUtils.isEmpty(suppList)) {
            suppCode = suppList.get(0).getSuppCode();
        }

        model.addAttribute("userType",baseParam.getUserType());
        model.addAttribute("distMonth",distMonth);
        model.addAttribute("lgcsCode", areaCode);
        model.addAttribute("lgcsName", areaName);
        model.addAttribute("suppCode", suppCode);
        model.addAttribute("areaList",areaList);
        model.addAttribute("suppList",suppList);
        return "ds/SC181103";
    }

    /**
     * 分页查询数据
     *
     * @param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SC181103Bean> search(SC181103Param pageParam) {
        logger.debug("发货计划单录入查询");
        pageParam.setFilter("suppCode",pageParam.getSuppCode());
        pageParam.setFilter("distMonth",pageParam.getDistMonth());

        DbUtils.buildLikeCondition(pageParam, "breedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "featureName", DbUtils.LikeMode.PARTIAL);
        this.setCommonParam(pageParam);
        PageResult<SC181103Bean> result = sc181103Logic.findSC181103BeansList(pageParam);
        for(SC181103Bean bean:result.getData()){
            bean.setLgcsCode(pageParam.getLgcsCode());
            bean.setLgcsName(pageParam.getLgcsName());
        }
         return result;
    }
	
    /**
     *
     * 保存所有数据到DB
     * @param jsonStr
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    public @ResponseBody void saveParam(Model model,String jsonStr){
        logger.info("发货录入数据到DB");
        Map<String, SC181103Param> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SC181103Param>>() {
        });

        Collection<SC181103Param> paramList = map.values();
        if (CollectionUtils.isNotEmpty(paramList)) {
            for (SC181103Param sc181103Param : paramList){
                //验证分销数量
                setDistNum(sc181103Param);
                //过滤误操作
                if (!StringUtil.isNullOrEmpty(sc181103Param.getPackNum())
                        && !Integer.toString(NumberConst.IntDef.INT_ZERO).equals(sc181103Param.getPackNum())){
                    this.setCommonParam(sc181103Param);
                    sc181103Logic.saveParam(sc181103Param);
                }
            }
        } else {
            throw new BusinessException("请输入发货箱数!");
        }
    }

    /**
     * 设置分销数量
     * @param numParam
     */
    public void setDistNum (SC181103Param numParam){
        logger.info("设置分销数量");
        //有效位数
        int num = NumberConst.IntDef.INT_FOURTEEN;
        //发货箱数
        String packNum = numParam.getPackNum();
        if (!StringUtil.isNullOrEmpty(packNum)) {
            if (packNum.contains(".")) {
                num += NumberConst.IntDef.INT_THREE;

            }
            if (packNum.contains("-")) {
                num += NumberConst.IntDef.INT_ONE;
            }

            if (packNum.length() <= num   //验证有效位数
                    && ValidatorUtils.checkDecimal(packNum)     //验证Bigdecimal类型
                    && StringUtil.toBigDecimal(packNum)!=null       //过滤字符串+数字类型
                    &&!StringUtil.toBigDecimal(packNum).equals(BigDecimal.ZERO)) {  //过滤发货数量为0
                numParam.setDistNum(StringUtil.toBigDecimal(packNum));
                if (null == numParam.getOutNetWeight() || BigDecimal.ZERO == numParam.getOutNetWeight()){
                    throw new BusinessException("产品编码_" + numParam.getPdCode() + ",对应的产品净重必须为非零数字!");
                }else {
                    BigDecimal packNumB = DecimalUtil.multiply(numParam.getDistNum(), new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    BigDecimal netWeightB = DecimalUtil.multiply(numParam.getOutNetWeight(), new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    BigDecimal remainder = packNumB.remainder(netWeightB);
                    if (remainder != BigDecimal.ZERO)
                    throw new BusinessException("产品编码_" + numParam.getPdCode() + ",发货数量必须是数字,且为对应产品净重的整数倍'!");
                }
            } else {
                throw new BusinessException("产品编码_"+numParam.getPdCode()+"的发货数量必须是数字," +
                        "且为对应产品净重的整数倍,长度不能大于14(可带两位小数)!");
            }
        }
    }


    /**
     * 下拉框选择
     * @param sC181103Param sc181103Param
     * @return 画面
     */
    @RequestMapping(value = "selectChange",
            method = RequestMethod.POST)
    public @ResponseBody
    List<SC181103Bean> selectChange(SC181103Param sC181103Param) {
        logger.info("根据区域获取供应商下拉列表");
        this.setCommonParam(sC181103Param);
        // 获取供应商下拉框
        List<SC181103Bean> suppList = sc181103Logic.findSuppList(sC181103Param);
        return suppList;
    }
}
