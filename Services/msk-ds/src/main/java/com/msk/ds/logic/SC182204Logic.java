package com.msk.ds.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.core.entity.SlSeller;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.ds.bean.SC182204Bean;
import com.msk.ds.bean.SC182204Param;
import com.msk.ds.bean.SC182204ProductParam;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.seller.bean.ISL231193RsBean;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.seller.bean.ISL231204RsResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SC182203Logic.
 *
 * @author yi_qixiang
 * @version 1.0
 **/
@Service
public class SC182204Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182204Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        // 所有产品的合计用
        String SQL_GET_VIRTUAL_STOCK_ACTUAL_LIST = "getVirtualStockActualList";  // 实际
        String SQL_GET_LGCS_AND_SUPP = "getLgcsAndSupp";  // 实际
        String SQL_GET_MANUFACTURE_AND_BRAND = "getManufactureAndBrand";  // 实际
        String SQL_GET_DS_PRODUCT_LOT = "getDsProductLot";  // 实际
        String SQL_GET_MD_LOGISTICS_AERA = "getMdLogisticsArea";  // 实际
        String SQL_GET_SL_ENTERPRISE= "getSlEnterprise";  // 实际

    }


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 获取产品虚拟库存实际表数据
     *
     * @return DS173306Param
     */
    @Transactional
    public List<SC182204Bean> getStockActual(SC182204Param sc182204Param) {
        logger.info("获取产品虚拟库存实际表数据");
        // 产品虚拟库存实际表数据查询
        String lgcsCode = "";
        String suppCode = "";
        //调用物流区接口获得物流区信息
        List<LgcsAreaBean> list = RestUtil.getLogiticsAreaList();
        if(sc182204Param.getLgcsCode()==null){
            //初期化
            // List<SC182204Bean> lgcsList = this.findList(SqlId.SQL_GET_MD_LOGISTICS_AERA, new SC182204Param());
            List<SC182204Bean> lgcsList = new ArrayList<SC182204Bean>();
            for(LgcsAreaBean AreaBean:list){
                SC182204Bean sc182204Bean1 = new SC182204Bean();
                sc182204Bean1.setLgcsCode(AreaBean.getLgcsAreaCode());
                sc182204Bean1.setLgcsName(AreaBean.getLgcsAreaName());
                lgcsList.add(sc182204Bean1);
            }
            if (CollectionUtils.isNotEmpty(lgcsList)) {

                lgcsCode = lgcsList.get(0).getLgcsCode();
            }
            //Add for #2700 at 2016/09/21 by likai Start
            sc182204Param.setLgcsCode(lgcsCode);
            //Add for #2700 at 2016/09/21 by likai End
            List<SC182204Bean> suppList = new ArrayList<SC182204Bean>();
            SC182204Param lgcsParam = new SC182204Param();
            lgcsParam.setLgcsCode(lgcsCode);
            suppList = getSuppListByLgcs(lgcsParam);
            if (CollectionUtils.isNotEmpty(suppList) && null==sc182204Param.getSuppCode()) {
                suppCode = suppList.get(0).getSuppCode();
            }
            else{
                suppCode = sc182204Param.getSuppCode();
            }
            //Add for #2700 at 2016/09/21 by likai Start
            sc182204Param.setSuppCode(suppCode);
            //Add for #2700 at 2016/09/21 by likai End
        }
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("distMonth", sc182204Param.getDistMonth());
        //baseParam.getFilterMap().put("lgcsCode",lgcsCode);
        baseParam.getFilterMap().put("halfCode", sc182204Param.getHalfCode());
        baseParam.getFilterMap().put("pdStockType", sc182204Param.getPdStockType());
        //baseParam.getFilterMap().put("suppCode", suppCode);
        /**为了保持init和带条件检索出的结果一致后来添加两个属性suppCode和lgcsCode*/
        baseParam.getFilterMap().put("lgcsCode",sc182204Param.getLgcsCode());
        baseParam.getFilterMap().put("suppCode", sc182204Param.getSuppCode());

        List<SC182204Bean> stockActualList = new ArrayList<SC182204Bean>();
        stockActualList = this.findList(SqlId.SQL_GET_VIRTUAL_STOCK_ACTUAL_LIST,baseParam);
        //List<SC182204Bean> stockActualList = new ArrayList<SC182204Bean>();
        return stockActualList;


    }



    /**
     * 根据用户账户查到供应商和销售区域
     *
     * @return suppName
     */
    @Transactional
    public SC182204Bean getLgcsAndSupp(SC182204Param sc182204Param) {

        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("slAccount", sc182204Param.getSlAccount());

        List<SC182204Bean> list = new ArrayList<SC182204Bean>();
       // list = this.findList(SqlId.SQL_GET_LGCS_AND_SUPP, sc182204Param);
        ISL231193RsParam rsParam = new ISL231193RsParam();
        rsParam.setSlAccount(sc182204Param.getSlAccount());
        List<ISL231193RsResult> rsList = RestUtil.slEpDataListSearch(rsParam);
        for(ISL231193RsResult rsResult:rsList){
            SC182204Bean scBean = new SC182204Bean();
            scBean.setSuppCode(rsResult.getSlCode());
            scBean.setSuppName(rsResult.getEpName());
            scBean.setLgcsCode(rsResult.getLgcsAreaCode());
            scBean.setLgcsName(rsResult.getLgcsAreaName());
            list.add(scBean);
        }
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(0);
        }else{
            return null;
        }

    }



    /**
     * 保存产品批次数据表
     *
     * @return result
     */
    @Transactional
    public void saveProductLot(SC182204Param param) {

        for (SC182204ProductParam entity : param.getProductParamList()) {
            if(entity.getCheckbox()!=null&&entity.getCheckbox().equals("1")){
                SC182204Param param1 = new SC182204Param();
                param1.setLgcsCode(entity.getLgcsCode());
                param1.setSuppCode(entity.getSuppCode());
                param1.setMachiningCode(entity.getMachiningCode());
                param1.setFeatureCode(entity.getFeatureCode());
                param1.setWeightCode(entity.getWeightCode());
                param1.setGradeCode(entity.getGradeCode());
                param1.setManuDate(entity.getManuDate());
                param1.setShelfLife(entity.getShelfLife());
                param1.setStorCondition(entity.getStorCondition());
                param1.setProStanCode(entity.getProStanCode());
                param1.setFoodManuLicen(entity.getFoodManuLicen());
                param1.setClient(entity.getClient());
                param1.setUnClient(entity.getUnClient());
                param1.setClientAddr(entity.getClientAddr());
                param1.setUnClientAddr(entity.getUnClientAddr());
                param1.setClientPhone(entity.getClientPhone());
                param1.setUnClientPhone(entity.getUnClientPhone());
                param1.setManuOrigin(entity.getManuOrigin());
                param1.setBrandLogo(entity.getBrandLogo());
                param1.setProLotNum(entity.getProLotNum());
                param1.setSumNewActNum(entity.getSumNewActNum());
                param1.setClassesCode(entity.getClassesCode());
                param1.setBreedCode(entity.getBreedCode());
                param1.setNormsCode(entity.getNormsCode());
                param1.setReadProLotNum(entity.getReadProLotNum());
                param1.setHalfPeriod(param.getHalfPeriod());
                //Add for Bug#2547 at 2016/09/08 by li_kai1 Start
                Date currentDate = DateTimeUtil.getCustomerDate();
                param1.setCrtId(param.getCrtId());
                param1.setActId(param.getActId());
                param1.setUpdId(param.getCrtId());
                param1.setUpdTime(currentDate);
                param1.setCrtTime(currentDate);
                param1.setActTime(currentDate);
                //Add for Bug#2547 at 2016/09/08 by li_kai1 End
                if(super.save(param1)!= NumberConst.IntDef.INT_ONE){
                    throw new BusinessException("保存失败，请和管理员联系");
                }
            }
        }
    }


    /**
     * 根据条形主码判断ds_product_lot表是否有新增的数据
     *
     * @return suppName
     */
    @Transactional
    public SC182204Bean getDsProductLot(SC182204Param sc182204Param) {

        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("proLotNum", sc182204Param.getProLotNum());

        List<SC182204Bean> list  = this.findList(SqlId.SQL_GET_DS_PRODUCT_LOT, sc182204Param);
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(NumberConst.IntDef.INT_ZERO);
        }else{
            return null;
        }

    }


    /**
     * 根据区域和供应商得到SC182204Bean
     *
     * @return suppName
     */
    @Transactional
    public SC182204Bean getSC182204Bean(SC182204Param sc182204Param) {
        SC182204Bean sc182204Bean = new SC182204Bean();
        //调用物流区接口获得物流区信息
        List<LgcsAreaBean> list = RestUtil.getLogiticsAreaList();
        List<SC182204Bean> lgcsList = new ArrayList<SC182204Bean>();
        if (CollectionUtils.isNotEmpty(list)){
            for(LgcsAreaBean AreaBean:list){
                SC182204Bean sc182204BeanLgcs = new SC182204Bean();
                sc182204BeanLgcs.setLgcsCode(AreaBean.getLgcsAreaCode());
                sc182204BeanLgcs.setLgcsName(AreaBean.getLgcsAreaName());
                lgcsList.add(sc182204BeanLgcs);
            }
        }
        List<SC182204Bean> suppList = new ArrayList<SC182204Bean>();

        if(sc182204Param.getLgcsCode()==null||sc182204Param.getSuppCode()==null){
            //lgcsList = this.findList(SqlId.SQL_GET_MD_LOGISTICS_AERA, new SC182204Param());
            //初期化
            String lgcsCode = "";
            if (CollectionUtils.isNotEmpty(lgcsList)) {
                lgcsCode = (lgcsList.get(NumberConst.IntDef.INT_ZERO)).getLgcsCode();
            }
            SC182204Param lgcsParam = new SC182204Param();
            lgcsParam.setLgcsCode(lgcsCode);
            suppList = getSuppListByLgcs(lgcsParam);
            if (CollectionUtils.isNotEmpty(suppList)) {

                sc182204Bean.setSuppCode(suppList.get(NumberConst.IntDef.INT_ZERO).getSuppCode());
            }
            sc182204Bean.setLgcsCode(lgcsCode);
            sc182204Bean.setLgcsAreaList(lgcsList);
            sc182204Bean.setSupplyList(suppList);
        }else{
            SC182204Param lgcsParam = new SC182204Param();
            lgcsParam.setLgcsCode(sc182204Param.getLgcsCode());
            suppList = getSuppListByLgcs(lgcsParam);
            sc182204Bean.setLgcsCode(sc182204Param.getLgcsCode());
            sc182204Bean.setSuppCode(sc182204Param.getSuppCode());
            sc182204Bean.setLgcsAreaList(lgcsList);
            sc182204Bean.setSupplyList(suppList);
        }
        return sc182204Bean;
    }



    /**
     * 根据销售区域获得供应商
     *
     * @return List<SC182204Bean>
     */
    public List<SC182204Bean> getSuppListByLgcs(SC182204Param sc182204Param) {
        // 产品虚拟库存实际表数据查询

        List<SC182204Bean> suppList = new ArrayList<SC182204Bean>();
        ISL231193RsParam rsParam = new ISL231193RsParam();
        List<ISL231193RsBean> paramList = new ArrayList<ISL231193RsBean>();
        ISL231193RsBean isl231193RsBean = new ISL231193RsBean();
        paramList.add(isl231193RsBean);
        rsParam.setParamList(paramList);
        List<ISL231193RsResult> rsList = RestUtil.slEpDataListSearch(rsParam);
        for(ISL231193RsResult rsResult:rsList){
            SC182204Bean scBean = new SC182204Bean();
            scBean.setSuppCode(rsResult.getSlCode());
            scBean.setSuppName(rsResult.getEpName());
            suppList.add(scBean);
        }
        return suppList;
    }


    /**
     * 根据供应商账号查找生产商和品牌
     *
     * @return suppName
     */
    public SC182204Bean getManuFactureAndBrand(SC182204Param sc182204Param) {
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("suppCode", sc182204Param.getSuppCode());
        List<SC182204Bean> list = new ArrayList<SC182204Bean>();
        SlSeller slSeller = new SlSeller();
        slSeller.setSlCode(sc182204Param.getSuppCode());
        List<ISL231204RsResult> rsList= RestUtil.findManuAndBrand(slSeller);
        for(ISL231204RsResult rs:rsList){
            SC182204Bean sc182204Bean = new SC182204Bean();
            sc182204Bean.setBrandId(rs.getBrandId().toString());
            sc182204Bean.setSlCodeManufacture(rs.getSlCodeManufacture());
            list.add(sc182204Bean);
        }
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(0);

        }else{
            return null;
        }

    }

}

