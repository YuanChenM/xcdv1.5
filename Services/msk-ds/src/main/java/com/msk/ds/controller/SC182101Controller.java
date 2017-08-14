package com.msk.ds.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.common.consts.DsConst;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.ds.bean.SC182101Bean;
import com.msk.ds.logic.SC182101Logic;
import com.msk.ds.rest.comm.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * SC182101Controller
 *
 * @author likai
 */
@Controller
@RequestMapping("SC182101")
public class SC182101Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182101Controller.class);

    @Autowired
    private SC182101Logic sc182101Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model) {
        logger.info("发货入库通知单一览初始化");
        BaseParam baseParam = new BaseParam();
        this.setCommonParam(baseParam);
        model.addAttribute("userType", baseParam.getUserType());

        return "ds/SC182101";
    }

    /**
     * 分页查询数据
     *
     * @param pageParam pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SC182101Bean> search(BasePageParam pageParam) {
        logger.info("发货入库通知单一览查询");

        this.setCommonParam(pageParam);
        setLikeCondition(pageParam);

        PageResult<SC182101Bean> pageResult = sc182101Logic.findPage(pageParam, SC182101Bean.class);

        List<SC182101Bean> SC182101BeanList;
        logger.info("开始计算分页合计");
        SC182101Bean current = sc182101Logic.getSumDelivery(pageParam);
        if (null != current && pageResult.getRecordsTotal() > NumberConst.IntDef.INT_ZERO) {
            SC182101BeanList = pageResult.getData();
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentSendPlanQty(current.getCurrentSendPlanQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentSendActualQty(current.getCurrentSendActualQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentRecriveQty(current.getCurrentRecriveQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentDifferQty(current.getCurrentDifferQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentSendPlanNum(current.getCurrentSendPlanNum());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentSendActualNum(current.getCurrentSendActualNum());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentReceiveNum(current.getCurrentReceiveNum());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setCurrentDifferNum(current.getCurrentDifferNum());
        }

        pageParam.setPaging(false);
        logger.info("开始计算总合计");
        current = sc182101Logic.getSumDelivery(pageParam);

        if (null != current && pageResult.getRecordsTotal() > NumberConst.IntDef.INT_ZERO) {
            SC182101BeanList = pageResult.getData();
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalSendPlanQty(current.getCurrentSendPlanQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalSendActualQty(current.getCurrentSendActualQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalRecriveQty(current.getCurrentRecriveQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalDifferQty(current.getCurrentDifferQty());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalSendPlanNum(current.getCurrentSendPlanNum());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalSendActualNum(current.getCurrentSendActualNum());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalReceiveNum(current.getCurrentReceiveNum());
            SC182101BeanList.get(NumberConst.IntDef.INT_ZERO).setTotalDifferNum(current.getCurrentDifferNum());
        }

        if (pageResult.getRecordsTotal() > NumberConst.IntDef.INT_ZERO) {
            //获取集合
            // Modify for 3367 at 2016/10/18 by likai Start
            List<SC182101Bean> list = pageResult.getData();
            List<String> slCodeList = new ArrayList<String>();
            DistrictParam districtParam = new DistrictParam();
            List<LgcsAreaBean> lgcsNameList = RestUtil.getAllLgcsList(districtParam);
            Map<String, String> lgcsNameMap = new HashMap<String, String>();
            //将供应商编码作为查询条件传入调用卖家接口获得生产商名称
            for (SC182101Bean bean : list) {
                slCodeList.add(bean.getSuppCode());
            }
            for (LgcsAreaBean lgcsAreaBean : lgcsNameList) {
                lgcsNameMap.put(lgcsAreaBean.getLgcsAreaCode(), lgcsAreaBean.getLgcsAreaName());
            }

            //调用接口获取生产商名称并放入结果集中
            Map<String, String> map = RestUtil.getEPNameBySuppCode(slCodeList);

            //在返回对象里放入调用接口得来的生产商名
            for (SC182101Bean bean : list) {
                bean.setManuName("");
                String value = map.get(bean.getSuppCode());
                if (!StringUtil.isEmpty(value)) {
                    bean.setManuName(value);
                }
                if (DsConst.DeliverySourceFlg.DELIVERY_FROM_SSC.equals(bean.getSourceFlg())) {
                    String lgcsName = lgcsNameMap.get(bean.getLgcsCode());
                    if (StringUtil.isNullOrEmpty(lgcsName)) {
                        throw new BusinessException(bean.getDeliveryStockId() + "入库单号下的物流区编码:"
                                + bean.getLgcsCode() + ",没有对应的物流区名称!");
                    }
                    bean.setLgcsName(lgcsName);
                }
            }
            // Modify for 3367 at 2016/10/18 by likai End
        }

        return pageResult;
    }

    public void setLikeCondition(BasePageParam pageParam) {
        if (CommCodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(pageParam.getUserType())) {
            pageParam.getFilterMap().put("crtId", pageParam.getCrtId());
        }

        DbUtils.buildLikeCondition(pageParam, "deliveryStockId", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "distMonth", DbUtils.LikeMode.FRONT);

        DbUtils.buildLikeCondition(pageParam, "lgcsName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "suppName", DbUtils.LikeMode.PARTIAL);
        /** #1763 添加实际收货时间 modify by likai1 2016/8/15 start */
        DbUtils.buildLikeCondition(pageParam, "deliveryReceiveStockTime", DbUtils.LikeMode.FRONT);
        /** #1763 添加实际收货时间 modify by likai1 2016/8/15 end */
        DbUtils.buildLikeCondition(pageParam, "deliveryWarehouseAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "deliveryResponName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "transportUnitName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "transportUnitResponName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "stockAddr", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "stockResponName", DbUtils.LikeMode.FRONT);

        String halfCode = StringUtil.toSafeString(pageParam.getFilterMap().get("halfCode"));
        String deliveryStockStatus = StringUtil.toSafeString(pageParam.getFilterMap().get("deliveryStockStatus"));
        String sourceFlag = StringUtil.toSafeString(pageParam.getFilterMap().get("sourceFlg"));

        if (!StringUtil.isNullOrEmpty(sourceFlag)) {
            String[] sourceFlags = sourceFlag.split(",");
            pageParam.getFilterMap().put("sourceFlg", sourceFlags);
        }

        if (!StringUtil.isNullOrEmpty(halfCode)) {
            String[] halfCodes = halfCode.split(",");
            pageParam.getFilterMap().put("halfCodes", halfCodes);
        }
        if (!StringUtil.isNullOrEmpty(deliveryStockStatus)) {
            String[] deliveryStocks = deliveryStockStatus.split(",");
            pageParam.getFilterMap().put("deliveryStocks", deliveryStocks);
        }
    }

    /**
     * 删除
     *
     * @param bean
     * @param baseParam
     * @return
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public String delete(SC182101Bean bean, BaseParam baseParam) {
        logger.info("发货入库通知单一览删除");
        //versionValidator   update前需先版本验证
        baseParam.getFilterMap().put("deliveryStockId", bean.getDeliveryStockId());
        baseParam.getFilterMap().put("sourceFlg", bean.getSourceFlg());
        bean = sc182101Logic.findOne(baseParam);
        bean.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ONE));
        this.setCommonParam(bean);
        Date currentDate = DateTimeUtil.getCustomerDate();
        bean.setUpdTime(currentDate);
        int resultCount = sc182101Logic.modify(bean);
        if (resultCount <= NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("删除失败，请重新操作！");
        }
        return "ds/SC182101";
    }

}
