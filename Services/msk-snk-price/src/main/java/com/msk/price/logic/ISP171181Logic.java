package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.BussinessConst;
import com.msk.price.bean.*;
import com.msk.price.utils.CommRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by yang_yang on 2016/5/16.
 */
@Service
public class ISP171181Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(ISP171181Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        //获取分销数量
        static String SQL_ID_GET_DIST_NUM = "getDistNum";
    }

    //数字格式化
    private static String formatStr = "0.00";

    /**
     * 获取供应商需求列表
     * @return
     */
    @Transactional(readOnly = true)
    public List<DemandResult> getDemandList(DemandParam rsParam) {

        logger.info("获取供应商产品分销数量列表");
        List<DemandResult> demandResultList = new ArrayList<DemandResult>();

        Map<String, Object> param = new HashMap<>();

        param.put("slCode",rsParam.getSlCode());
        param.put("lgcsAreaCode",rsParam.getLgcsAreaCode());

        //获取卖家接口产品信息接口
        List<DemandParam> demandList = CommRestUtil.getDemandResponse(param);

        for (DemandParam demandParam : demandList) {
            //result
            DemandResult demandResult = new DemandResult();
            demandResult.setAreaCode(demandParam.getLgcsAreaCode());

            //supList
            List<DemandSupBean> results = new ArrayList<DemandSupBean>();

            DemandSupBean demandSupBean = new DemandSupBean();
            demandSupBean.setSupCode(demandParam.getSlCode());

            //productList
            List<DemandProductBean> productList = new ArrayList<DemandProductBean>();
            DemandProductBean demandProductBean = new DemandProductBean();
            demandProductBean.setProductCode(demandParam.getPdCode()+demandParam.getSlQltGradeCode());
            demandProductBean.setCampType(Integer.valueOf(demandParam.getSlQltGradeCode()));

            //wayList
            List<DemandWayBean> wayList = new ArrayList<DemandWayBean>();
            DemandWayBean demandWayBean = new DemandWayBean();
            //默认分销通道为4:标准订单
            demandWayBean.setWayType(BussinessConst.WayType.STANDARDORDER);

            //获取分销数量
            BaseParam baseParam = new BaseParam();
            baseParam.setFilterObject("slCode", demandParam.getSlCode());
            baseParam.setFilterObject("demandMonth", rsParam.getDemandMonth());
            //同一个供应商只有一个等级，卖家接口获取的pdCode不拼接等级
            baseParam.setFilterObject("pdCode", demandParam.getPdCode());
            baseParam.setFilterObject("lgcsCode", demandParam.getLgcsAreaCode());
            baseParam.setFilterObject("isConfirm", BussinessConst.ISCONFIRM.CONFIRM);
            baseParam.setFilterObject("isAgree", BussinessConst.ISAGREE.AGREE);

            Object distNum = null;

            if(!String.valueOf(BussinessConst.SlMainClass.OEM).equals(demandParam.getSlmainClass())){
                distNum = this.findObject(SqlId.SQL_ID_GET_DIST_NUM,baseParam);
            }
            DecimalFormat df = new DecimalFormat(formatStr);

            if(distNum != null){
                demandWayBean.setPassDemand(df.format(distNum));
            }else{
                demandWayBean.setPassDemand(formatStr);
            }

            wayList.add(demandWayBean);

            demandProductBean.setWayList(wayList);

            productList.add(demandProductBean);


            demandSupBean.setProductList(productList);
            results.add(demandSupBean);

            demandResult.setSupList(results);
            demandResultList.add(demandResult);
        }

        return demandResultList;
    }

}
