package com.msk.bs.logic;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.ISP171185Bean;
import com.msk.price.bean.ISP171185Param;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.consts.TableNameDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/19.
 */
@Service
public class IBA2141110RsLogic extends BaseLogic {

    private  static Logger logger = LoggerFactory.getLogger(IBA2141110RsLogic.class);
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static String FIND_ORDER_DETAIL = "findOrderDetail";
        static String SAVE_DETAIL = "saveDetail";//保存用户购物车信息
        static String QUERY_SHOP_CAR = "queryShopCar";//查询用户购物车信息
        static String QUERY_SHOPCAR_DETAIL = "queryShopCarDetail";//查询购物车该产品数量
        static String UPDATE_DETAIL = "updateDetail";//更新购物车产品数量
        // 根据购物车ID与购物车明细ID获取对应的订单列表
        static String GET_ORDER_DETAIL_INFO = "getOrderDetailInfo";
        // 购物车购买时更新购物车产品数量
        static String UPDATE_DETAIL_NUM = "updateDetailNum";
    }

    @Autowired
    private CommonLogic commonLogic;

    @Transactional(readOnly = true)
    public List<IBA2141110Result> findOrderDetail(IBA2141110Param param) {
        // 根据管家id获取对应的购物车产品信息
        List<IBA2141110Result> iba2141110ResultList = this.findList(SqlId.FIND_ORDER_DETAIL, param);
        return getCommodityInfo(iba2141110ResultList,param);
    }


    /**
     * 根据购物车ID与购物车明细ID获取对应的订单列表
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBA2141110Result> getOrderDetailInfo(IBA2141110Param param) {
        // 根据购物车ID与购物车明细ID获取对应的订单列表
        List<IBA2141110Result> iba2141110ResultList = this.findList(SqlId.GET_ORDER_DETAIL_INFO, param);
        return getCommodityInfo(iba2141110ResultList,param);
    }

    /**
     * 产品列表购买商品
     * 每次购买只会传入一个pdCode
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBA2141110Result getGoodsInfo(IBA2141110Param param) {
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setPdCode(param.getProductCode());
        pdInfoParam.setGradeFlag("1");
        List<PDInfoResult> pdInfoResultList = CommRestUtil.getPDSup(pdInfoParam);
        IBA2141110Result iba2141110 = new IBA2141110Result();
        if(!CollectionUtils.isEmpty(pdInfoResultList)){
            for (PDInfoResult pdInfo : pdInfoResultList){
                iba2141110.setClassesCode(pdInfo.getClassesCode());
                iba2141110.setClassesName(pdInfo.getClassesName());
                iba2141110.setMachiningCode(pdInfo.getMachiningCode());
                iba2141110.setMachiningName(pdInfo.getMachiningName());
                iba2141110.setBreedCode(pdInfo.getBreedCode());
                iba2141110.setBreedName(pdInfo.getBreedName());
                iba2141110.setFeatureCode(pdInfo.getFeatureCode());
                iba2141110.setFeatureName(pdInfo.getFeatureName());
                iba2141110.setWeightCode(pdInfo.getWeightCode());
                iba2141110.setWeightName(pdInfo.getWeightName());
                iba2141110.setGradeCode(pdInfo.getGradeCode());
                iba2141110.setGradeName(pdInfo.getGradeName());
                // modify for 买手囤货购买空指针异常 at 2016/10/19 by yangchunyan start
                iba2141110.setPdCode(pdInfo.getPdCode());
                // modify for 买手囤货购买空指针异常 at 2016/10/19 by yangchunyan end
                // 若产品处没有对应的产品那么显示已下架，也就没必要获取价盘信息了。
            }
        }else{
            iba2141110.setIsOffTheShelf("1");
        }
        // 若产品处没有对应的产品那么显示已下架，也就没必要获取价盘信息了。
        if(StringUtil.isNullOrEmpty(iba2141110.getIsOffTheShelf())){
            List<ISP171185Bean> productPriceList = new ArrayList<>();
            ISP171185Bean isp171185Bean = new ISP171185Bean();
            ISP171185Param isp171185Param = new ISP171185Param();
            isp171185Bean.setPdCode(param.getProductCode());
            isp171185Bean.setLogiAreaCode(param.getLgcsCode());
            isp171185Bean.setOrderQty(Long.valueOf(param.getProductNum()));
            List<ISP171185Bean> isp171185BeanList = new ArrayList<>();
            isp171185BeanList.add(isp171185Bean);
            // 调用查询神农客价盘通道和价格
            if(!CollectionUtils.isEmpty(isp171185BeanList)){
                isp171185Param.setProductList(isp171185BeanList);
                productPriceList = CommRestUtil.getPriceWayListByPdCode(isp171185Param);
            }
            if(!CollectionUtils.isEmpty(productPriceList)){
                for (ISP171185Bean isp171185Bean1 : productPriceList){
                    if(iba2141110.getPdCode().equals(isp171185Bean1.getPdCode())){
                        iba2141110.setIsOffTheShelf(null);
                        iba2141110.setPriceCycle(isp171185Bean1.getPriceCycle());
                        iba2141110.setOldPrice(isp171185Bean1.getPdBoxPrice());
                        break;
                    }else {
                        iba2141110.setIsOffTheShelf("1");
                        iba2141110.setOldPrice(new BigDecimal("0"));
                    }
                }
            }else {
                // 1- 商品不存在或已下架
                iba2141110.setIsOffTheShelf("1");
                iba2141110.setPdNum("0");
                iba2141110.setOldPrice(new BigDecimal("0"));
            }
        }
        return iba2141110;
    }

    /**
     * 获取商品信息
     * @param iba2141110ResultList
     * @return
     * @author zhu_kai1
     * 优化代码 2016/9/7
     */
    private List<IBA2141110Result> getCommodityInfo(List<IBA2141110Result> iba2141110ResultList,IBA2141110Param param) {
        List<String> pdCodeList = new ArrayList<>();
        ISP171185Param isp171185Param = new ISP171185Param();
        List<ISP171185Bean> isp171185BeanList = new ArrayList<>();
        List<ISP171185Bean> productPriceList = new ArrayList<>();
        for (IBA2141110Result bean : iba2141110ResultList) {
            ISP171185Bean isp171185Bean = new ISP171185Bean();
            pdCodeList.add(bean.getPdCode());
            isp171185Bean.setPdCode(bean.getPdCode());
            isp171185Bean.setLogiAreaCode(param.getLgcsCode());
            isp171185Bean.setOrderQty(Long.valueOf(bean.getPdNum()));
            logger.info("产品Code{}",isp171185Bean.getPdCode());
            logger.info("产品数量(箱数){}",isp171185Bean.getOrderQty());
            isp171185BeanList.add(isp171185Bean);
        }
        // 调用查询神农客价盘通道和价格
        if(!CollectionUtils.isEmpty(isp171185BeanList)){
            isp171185Param.setProductList(isp171185BeanList);
            productPriceList = CommRestUtil.getPriceWayListByPdCode(isp171185Param);
        }
        if(!CollectionUtils.isEmpty(pdCodeList)){
            PDInfoParam pdInfoParam = new PDInfoParam();
            pdInfoParam.setPdCodes(pdCodeList);
            pdInfoParam.setGradeFlag("1");
            // 根据批量产品编码查询产品包装规格信息及产品信息
            List<PDInfoResult> pdInfoResultList = CommRestUtil.getPDSup(pdInfoParam);
            for (IBA2141110Result bean : iba2141110ResultList) {
                if(!CollectionUtils.isEmpty(pdInfoResultList)){
                    for (PDInfoResult pdInfo : pdInfoResultList) {
                        if (bean.getPdCode().equals(pdInfo.getPdCode())) {
                            bean.setIsOffTheShelf(null);
                            bean.setClassesCode(pdInfo.getClassesCode());
                            bean.setClassesName(pdInfo.getClassesName());
                            bean.setMachiningCode(pdInfo.getMachiningCode());
                            bean.setMachiningName(pdInfo.getMachiningName());
                            bean.setBreedCode(pdInfo.getBreedCode());
                            bean.setBreedName(pdInfo.getBreedName());
                            bean.setFeatureCode(pdInfo.getFeatureCode());
                            bean.setFeatureName(pdInfo.getFeatureName());
                            bean.setWeightCode(pdInfo.getWeightCode());
                            bean.setWeightName(pdInfo.getWeightName());
                            bean.setGradeCode(pdInfo.getGradeCode());
                            bean.setGradeName(pdInfo.getGradeName());
                            bean.setPdName("产品特征:" + pdInfo.getFeatureName() + "包装净重:" + pdInfo.getWeightName() + "产品等级:" + pdInfo.getGradeName());
                            break;
                        }else{
                            bean.setIsOffTheShelf("1");
                        }
                    }
                }else{
                    bean.setIsOffTheShelf("1");
                }
                // 若产品处没有对应的产品那么显示已下架，也就没必要获取价盘信息了。
                if(StringUtil.isNullOrEmpty(bean.getIsOffTheShelf())){
                    if(!CollectionUtils.isEmpty(productPriceList)){
                        for (ISP171185Bean isp171185Bean1 : productPriceList){
                            if(bean.getPdCode().equals(isp171185Bean1.getPdCode())){
                                bean.setIsOffTheShelf(null);
                                bean.setPriceCycle(isp171185Bean1.getPriceCycle());
                                bean.setOldPrice(isp171185Bean1.getPdBoxPrice());
                                break;
                            }else {
                                bean.setIsOffTheShelf("1");
                                bean.setOldPrice(new BigDecimal("0"));
                            }
                        }
                    }else {
                        // 1- 商品不存在或已下架
                        bean.setIsOffTheShelf("1");
                        bean.setPdNum("0");
                        bean.setOldPrice(new BigDecimal("0"));
                    }
                }
            }
        }
        return iba2141110ResultList;
    }

    /**
     * 更新购物车信息
     *
     * @param param
     * @return
     */
    @Transactional
    public int modifyShopingCardDetail(IBA2141110Param param) {
        if(!CollectionUtils.isEmpty(param.getProductList())){
            // 购物车购买时更新购物车明细中的数量
            int sum = 0;
            for (IBA2141110RsBean rsBean :param.getProductList()){
               int i =  this.modify(SqlId.UPDATE_DETAIL_NUM,rsBean);
                sum+=i;
            }
            return sum;
        }
        return this.modify(param);
    }

    /**
     * 添加购物车信息
     *
     * @param param
     */
    @Transactional
    public ShopingCarBean saveShopingCar(ShopingCarParam param) {
        Long carId = 0l;
        Long carDetailId = 0l;
        List<ShopingCarBean> beanList = this.findList(SqlId.QUERY_SHOP_CAR, param);//用户购物车信息
        if (null != beanList && beanList.size() > 0) {//是否存在购物车信息。没有就添加
            ShopingCarBean bean = beanList.get(0);
            param.setCarId(bean.getCarId());
            carId = bean.getCarId();
            param.setSellerName(bean.getSellerName());
            param.setSellerCode(bean.getSellerCode());
        } else {
            carId = commonLogic.maxId("SO_SHOPING_CAR", "CAR_ID");
            param.setCarId(carId);
            this.save(param);
        }
        List<ShopingCarBean> beanDetailList = this.findList(SqlId.QUERY_SHOPCAR_DETAIL, param);//获取购物车中产品详情
        if (null != beanDetailList && beanDetailList.size() > 0) {//购物车是否存在该产品
            ShopingCarBean detailBean = beanDetailList.get(0);
            carDetailId = detailBean.getCarDetailId();
            int pdNum = param.getPdNum();
            if (detailBean.getDelFlg().equals(SystemConst.DelFlg.ENABLE)) {//数据库是否存在未删除的数据
                //重新定义购物车数量
                pdNum += detailBean.getPdNum();
            }
            //根据数量获取对应价格
            IBA2141106RsBean[] priceWay = param.getPriceWay();
            for (int i = 0; i < priceWay.length; i++) {
                if (!priceWay[i].getOrderLevel().equals("1")) {
                    if (StringUtil.toInteger(priceWay[i].getBoxMin()) <= pdNum && pdNum <= StringUtil.toInteger(priceWay[i].getBoxMax())) {
                        param.setOldPrice(StringUtil.toBigDecimal(priceWay[i].getPriceOfBox()));
                    }
                } else {
                    if (StringUtil.toInteger(priceWay[i].getBoxMin()) <= pdNum) {
                        param.setOldPrice(StringUtil.toBigDecimal(priceWay[i].getPriceOfBox()));
                    }
                }
            }
            param.setPdNum(pdNum);
            param.setCarDetailId(detailBean.getCarDetailId());
           this.modify(SqlId.UPDATE_DETAIL,param);
        } else {
            //定义单价
            int pdNum = param.getPdNum();
            IBA2141106RsBean[] priceWay = param.getPriceWay();
            for (int i = 0; i < priceWay.length; i++) {
                if(!priceWay[i].getOrderLevel().equals("1")) {
                    if (StringUtil.toInteger(priceWay[i].getBoxMin()) <= pdNum && pdNum <= StringUtil.toInteger(priceWay[i].getBoxMax())) {
                        param.setOldPrice(StringUtil.toBigDecimal(priceWay[i].getPriceOfBox()));
                    }
                }else{
                    if(StringUtil.toInteger(priceWay[i].getBoxMin()) <= pdNum) {
                        param.setOldPrice(StringUtil.toBigDecimal(priceWay[i].getPriceOfBox()));
                    }
                }
            }
            carDetailId = commonLogic.maxId("SO_SHOPING_CAR_DETAIL", "CAR_DETAIL_ID");
            param.setCarDetailId(carDetailId);
            this.save(SqlId.SAVE_DETAIL, param);
        }
        ShopingCarBean shopingCarBean = new ShopingCarBean();
        shopingCarBean.setCarId(carId);
        shopingCarBean.setCarDetailId(carDetailId);
        return shopingCarBean;
    }
}
