package com.msk.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.msk.common.constant.SystemConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msk.common.bean.RestRequest;
import com.msk.common.exception.BusinessException;
import com.msk.common.constant.NumberConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151401ProductRestParam;
import com.msk.order.bean.param.ISO151401RestParam;
import com.msk.order.bean.param.ProductInfoParam;
import com.msk.order.bean.result.ISO151401RestResult;
import com.msk.order.bean.result.ISO151414ProductStandardInfo;
import com.msk.order.entity.SoPro;
import com.msk.order.entity.SoProDetail;
import com.msk.order.repository.SoProDetailRepository;
import com.msk.order.repository.SoProRepository;
import com.msk.order.service.ISO151401Service;
import com.msk.order.util.SoRestUtil;

/**
 * ISO151401_创建购物需求订单接口
 * Created by chu_jian on 2016/8/3.
 */

@Service
public class ISO151401ServiceImpl extends BaseService<SoPro, Long> implements ISO151401Service {
    private static Logger logger = LoggerFactory.getLogger(ISO151401ServiceImpl.class);
    @Autowired
    private SoProRepository proRepository;
    @Autowired
    private SoProDetailRepository proDetailRepository;

    /**
     * 创建购物需求订单
     *
     * @param param param
     * @return 返回结果
     * @author chu_jian
     */
    @Override
    @Transactional
    public ISO151401RestResult createSoPro(RestRequest<ISO151401RestParam> param) {
        logger.debug("构建需求订单");
        ISO151401RestResult rs = new ISO151401RestResult();
        ISO151401RestParam iso251401RsParam = param.getParam();
        //设置购物意愿订单参数
        SoPro soPro = this.setSoProValue(param);
        //插入购物意愿表
        this.save(soPro);
        //获取产品参数
        List<ISO151401ProductRestParam> iso151401ProductsRsParamList = iso251401RsParam.getProducts();
        //调用接口获取产品列表
        List<ISO151414ProductStandardInfo> productList = this.getProductList(iso151401ProductsRsParamList);
        int step = productList.size();
        Long maxId = super.maxId("so_pro_detail", step);
        for (ISO151401ProductRestParam iso151401ProductsRsParam : iso151401ProductsRsParamList) {

            boolean exist = false;
            //遍历产品列表
            for (ISO151414ProductStandardInfo result : productList) {
                if (!StringUtil.isEmpty(result.getPdCode()) && result.getPdCode().equals(iso151401ProductsRsParam.getPdCode())) {
                    //设置购物意愿订单详情参数
                    SoProDetail soProDetail = this.setSoProDetailValue(soPro, result, iso151401ProductsRsParam);
                    //插入SoProDetail
                    soProDetail.setProDetailId(maxId--);
                    proDetailRepository.save(soProDetail);
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                throw new BusinessException("该产品编码无记录:" + iso151401ProductsRsParam.getPdCode());
            }
        }
        rs.setProCode(soPro.getProCode());
        return rs;
    }

    /**
     * 设置SoProDetail参数
     *
     * @param
     */
    private SoProDetail setSoProDetailValue(SoPro soPro, ISO151414ProductStandardInfo result, ISO151401ProductRestParam iso251401RsParam) {
        SoProDetail soProDetail = new SoProDetail();
        soProDetail.setProId(soPro.getProId());
        soProDetail.setProCode(soPro.getProCode());
        soProDetail.setCrtId(soPro.getCrtId());
        soProDetail.setCrtTime(DateTimeUtil.getCustomerDate());
        soProDetail.setVer(NumberConstant.IntDef.INT_ONE);
        soProDetail.setDelFlg(SystemConstant.DelFlg.ENABLE);
        //类型编码
        soProDetail.setClassesCode(result.getClassesCode());
        //类型名称
        soProDetail.setClassesName(result.getClassesName());
        //品种编码
        soProDetail.setBreedCode(result.getBreedCode());
        //品种名称
        soProDetail.setBreedName(result.getBreedName());
        //特征编码
        soProDetail.setFeatureCode(result.getFeatureCode());
        //特征名称
        soProDetail.setFeatureName(result.getFeatureName());
        soProDetail.setNormsCode(result.getNormsCode());
        soProDetail.setNormsName(result.getNormsName());
        //产品编号
        soProDetail.setPdCode(result.getPdCode());
        //产品名称
        soProDetail.setPdName(result.getPdName());
        // 产品单位，暂时取得单个产品净重
        soProDetail.setPdUnit(result.getWeightVal() + "");
        //等级编码
        soProDetail.setPdGradeCode(result.getGradeCode());
        //等级名称
        soProDetail.setPdGradeName(result.getGradeName());
        //包装体积
        soProDetail.setPackingVolume(result.getNormsVolume());
        //体积
        soProDetail.setPdVolume(result.getNormsVolume());
        //净重
        soProDetail.setPdWeight(result.getWeightVal());
        // 订单价格
        if(StringUtil.isEmpty(iso251401RsParam.getOrderPrice())){
            iso251401RsParam.setOrderPrice(String.valueOf(NumberConstant.IntDef.INT_ZERO));
        }
        soProDetail.setOrderPrice(new BigDecimal(iso251401RsParam.getOrderPrice()));
        // 价盘周期
        if(StringUtil.isEmpty(iso251401RsParam.getPriceCycle())){
            iso251401RsParam.setPriceCycle("");
        }
        soProDetail.setPriceCycle(iso251401RsParam.getPriceCycle());
        // 订单数量
        if(StringUtil.isEmpty(iso251401RsParam.getOrderQty())){
            iso251401RsParam.setOrderQty(String.valueOf(NumberConstant.IntDef.INT_ZERO));
        }
        soProDetail.setOrderQty(new BigDecimal(iso251401RsParam.getOrderQty()));
        return soProDetail;
    }

    /**
     * 设置sopro参数
     *
     * @param
     */
    private SoPro setSoProValue(RestRequest<ISO151401RestParam> param) {
        SoPro soPro = new SoPro();
        ISO151401RestParam iso251401RsParam = param.getParam();
        //获取需求最大ID
        soPro.setProId(super.maxId("so_pro"));
        // 需求订单编号
        soPro.setProCode(getProCode());
        soPro.setProStatus(NumberConstant.IntDef.INT_ONE);
        soPro.setDistrictCode(iso251401RsParam.getDistrictCode());
        soPro.setBuyerId(iso251401RsParam.getBuyersId());
        soPro.setBuyerCode(iso251401RsParam.getBuyersCode());
        soPro.setBuyerName(iso251401RsParam.getBuyersName());
        soPro.setBuyerType(Integer.parseInt(iso251401RsParam.getBuyersType()));
        soPro.setSellerCode(iso251401RsParam.getSellerCode());
        soPro.setSellerName(iso251401RsParam.getSellerName());
        soPro.setProTime(DateTimeUtil.getCustomerDate());
        soPro.setCrtId(param.getLoginId());
        soPro.setCrtTime(DateTimeUtil.getCustomerDate());
        soPro.setVer(NumberConstant.IntDef.INT_ONE);
        soPro.setOrderSource(Integer.parseInt(param.getSiteCode()));
        soPro.setDelFlg(SystemConstant.DelFlg.ENABLE);
        return soPro;
    }

    /**
     * 获取产品信息
     *
     * @param
     */
    private List<ISO151414ProductStandardInfo> getProductList(List<ISO151401ProductRestParam> products) {
        //设置参数ProductInfoParam
        ProductInfoParam pdParam = new ProductInfoParam();
        List<String> pdCodes = new ArrayList<>();
        for (ISO151401ProductRestParam iso251401RsParam : products) {
            String pdCode = iso251401RsParam.getPdCode();
            pdCodes.add(pdCode);
        }
        pdParam.setPdCodes(pdCodes);
        List<ISO151414ProductStandardInfo> productBeans = SoRestUtil.getProductList(pdParam);
        return productBeans;
    }


    /**
     * 生成意愿订单编码（意愿订单表）
     *
     * @param
     * @return 需求订单编号
     */
    private String getProCode() {
        logger.debug("获得需求订单编号");
        //当前时间
        String shoppingCode = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, DateTimeUtil.getCustomerDate());
        String randomStr = "";
        //两位随机数
        int random = (int) (Math.random() * NumberConstant.IntDef.INT_HUNDRED);
        if (random < NumberConstant.IntDef.INT_TEN) {
            randomStr = NumberConstant.IntDef.INT_TEN + String.valueOf(random);
        } else {
            randomStr = String.valueOf(random);
        }
        return shoppingCode + randomStr;
    }

    @Override
    public BaseRepository getRepository() {
        return this.proRepository;
    }
}
