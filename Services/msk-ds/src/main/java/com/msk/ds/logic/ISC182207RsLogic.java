package com.msk.ds.logic;

import com.hoperun.core.consts.NumberConst;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;

import com.msk.common.consts.DsConst;
import com.msk.common.logic.CommonLogic;
import com.msk.common.xml.JaxbXmlWrite;
import com.msk.core.entity.DsDeliveryStock;
import com.msk.core.entity.DsDeliveryStockDetail;
import com.msk.ds.bean.*;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.bean.SlPdArtnoBean;
import com.msk.utils.WmsFtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by li_kai1 on 2016/7/4.
 */
@Service
public class ISC182207RsLogic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(ISC182207RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    private interface SqlId {
        String SAVE_DELIVERY_STOCK = "saveStock";
        String SAVE_DELIVERY_STOCK_DETAIL = "saveStockDetail";
    }

    /**
     * 处理采供链数据
     */
    @Transactional
    public boolean resolveSSCData(ISC182207RsParam isc182207RsParam) {
        saveSscStockToDs(isc182207RsParam);
        Boolean result = createXML(isc182207RsParam);
        return result;
    }
    /**
     * 采供链数据保存到DB
     *
     * @param isc182207RsParam
     */
    @Transactional
    public void saveSscStockToDs(ISC182207RsParam isc182207RsParam) {
        DsDeliveryStock dsDeliveryStock = this.setSaveDeliveryInfo(isc182207RsParam);
        int saveResult = this.save(SqlId.SAVE_DELIVERY_STOCK, dsDeliveryStock);
        if (saveResult != NumberConst.IntDef.INT_ZERO) {
            List<DsDeliveryStockDetail> detailList = this.setSaveDeliveryDetailInfo(isc182207RsParam);
            for (DsDeliveryStockDetail dsDeliveryStockDetail : detailList) {
                dsDeliveryStockDetail.setDeliveryStockId(dsDeliveryStock.getDeliveryStockId());

                int saveDetailReult = this.save(SqlId.SAVE_DELIVERY_STOCK_DETAIL, dsDeliveryStockDetail);
                if (saveDetailReult == NumberConst.IntDef.INT_ZERO) {
                    throw new BusinessException("卖家采供链生成XML文件,保存至发货入库单明细表时失败!");
                }
            }
            //生成xml时,使用平台供应链 生成的seliveryStockId作为入库单号
            isc182207RsParam.setReceipt(dsDeliveryStock.getDeliveryStockId());
        } else {
            throw new BusinessException("卖家采供链生成XML文件,保存至发货入库单管理表时失败!");
        }
    }

    /**
     * 初始化发货入库单信息
     *
     * @param isc182207RsParam
     * @return
     */
    @Transactional
    public DsDeliveryStock setSaveDeliveryInfo(ISC182207RsParam isc182207RsParam) {
        DsDeliveryStock dsDeliveryStock = new DsDeliveryStock();
        Date currentDate = DateTimeUtil.getCustomerDate();
        BeanUtils.copyProperties(isc182207RsParam, dsDeliveryStock);
        long deliveryStockId = this.commonLogic.maxId("DS_DELIVERY_STOCK", "DELIVERY_STOCK_ID");
        dsDeliveryStock.setDeliveryStockId(deliveryStockId);
        dsDeliveryStock.setSourceFlg(DsConst.DeliverySourceFlg.DELIVERY_FROM_SSC);
        dsDeliveryStock.setSscDeliveryStockId(isc182207RsParam.getReceipt());
        dsDeliveryStock.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));
        dsDeliveryStock.setCrtTime(currentDate);
        dsDeliveryStock.setCrtId(isc182207RsParam.getCrtId());
        dsDeliveryStock.setUpdTime(currentDate);
        dsDeliveryStock.setUpdId(isc182207RsParam.getCrtId());
        return dsDeliveryStock;
    }

    /**
     * 初始化发货入库明细单信息
     *
     * @param isc182207RsParam
     * @return
     */
    public List<DsDeliveryStockDetail> setSaveDeliveryDetailInfo(ISC182207RsParam isc182207RsParam) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        List<ISC182207DetailRsParam> productList = isc182207RsParam.getProductList();
        List<DsDeliveryStockDetail> detailList = new ArrayList<DsDeliveryStockDetail>();
        Map<String, Object> maps = getDataMap(productList, isc182207RsParam.getSuppCode());
        Map<String, PDInfoResult> pdCodeMap = (Map<String, PDInfoResult>) maps.get("pdCodeMap");
        Map<String, SlPdArtnoBean> slCodeMap = (Map<String, SlPdArtnoBean>) maps.get("slCodeMap");
        for (ISC182207DetailRsParam param : productList) {
            DsDeliveryStockDetail dsDeliveryStockDetail = new DsDeliveryStockDetail();
            BeanUtils.copyProperties(param, dsDeliveryStockDetail);
            dsDeliveryStockDetail.setActualReceiveNum(BigDecimal.ZERO);
            dsDeliveryStockDetail.setSourceFlg(DsConst.DeliverySourceFlg.DELIVERY_FROM_SSC);
            dsDeliveryStockDetail.setSscDeliveryStockId(isc182207RsParam.getReceipt());
            dsDeliveryStockDetail.setCrtId(isc182207RsParam.getCrtId());
            dsDeliveryStockDetail.setUpdId(isc182207RsParam.getCrtId());
            dsDeliveryStockDetail.setCrtTime(currentDate);
            dsDeliveryStockDetail.setUpdTime(currentDate);
            PDInfoResult product = pdCodeMap.get(param.getPdCode());
            if (null == product) {
                product = new PDInfoResult();
            }
            String key = product.getClassesCode() + product.getMachiningCode() + product.getBreedCode()
                    + product.getFeatureCode() + product.getWeightCode() + product.getGradeCode();
            SlPdArtnoBean slPdArtno = slCodeMap.get(key);
            if (null != slPdArtno && !StringUtil.isNullOrEmpty(slPdArtno.getSlCodeDis()) && !StringUtil.isNullOrEmpty(slPdArtno.getSlPdArtno())) {
                dsDeliveryStockDetail.setSku(slPdArtno.getSlCodeDis() + slPdArtno.getSlPdArtno());
            } else {
                dsDeliveryStockDetail.setSku("");
            }
            detailList.add(dsDeliveryStockDetail);
        }

        return detailList;
    }

    /**
     * 生成美迪福接口xml文件
     *
     * @param isc182207RsParam
     */

    public Boolean createXML(ISC182207RsParam isc182207RsParam) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        SC182102XmlParam xmlParam = new SC182102XmlParam();
        xmlParam.setReceipt(StringUtil.toSafeString(isc182207RsParam.getReceipt()));
        xmlParam.setNotes(StringUtil.toSafeString(isc182207RsParam.getDeliveryMemo()));
        xmlParam.setScheduledDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(currentDate));
        xmlParam.setStartReceiptDate(StringUtil.toSafeString(isc182207RsParam.getDeliveryStockTimeReq()));

        List<SC182102XmlDetailParam> xmlDetailList = new ArrayList<SC182102XmlDetailParam>();
        xmlParam.setDetailList(xmlDetailList);
        Integer index = NumberConst.IntDef.INT_ONE;
        List<ISC182207DetailRsParam> productList = isc182207RsParam.getProductList();
        Map<String, Object> maps = getDataMap(productList, isc182207RsParam.getSuppCode());
        Map<String, PDInfoResult> pdCodeMap = (Map<String, PDInfoResult>) maps.get("pdCodeMap");
        Map<String, SlPdArtnoBean> slCodeMap = (Map<String, SlPdArtnoBean>) maps.get("slCodeMap");

        for (ISC182207DetailRsParam detail : productList) {
            SC182102XmlDetailParam xmlDetail = new SC182102XmlDetailParam();
            xmlDetail.setReceiptLine(index.toString());
            xmlDetail.setCompany(StringUtil.toSafeString(isc182207RsParam.getSuppCode()));
            xmlDetail.setArea(StringUtil.toSafeString(isc182207RsParam.getLgcsCode()));
            xmlDetail.setCompanyName(StringUtil.toSafeString(isc182207RsParam.getSuppName()));
            PDInfoResult product = pdCodeMap.get(detail.getPdCode());
            if (null == product) {
                product = new PDInfoResult();
            }
            String key = product.getClassesCode() + product.getMachiningCode() + product.getBreedCode()
                    + product.getFeatureCode() + product.getWeightCode() + product.getGradeCode();
            SlPdArtnoBean slPdArtno = slCodeMap.get(key);
            if (null != slPdArtno && !StringUtil.isNullOrEmpty(slPdArtno.getSlCodeDis()) && !StringUtil.isNullOrEmpty(slPdArtno.getSlPdArtno())) {
                xmlDetail.setSku(slPdArtno.getSlCodeDis() + slPdArtno.getSlPdArtno());
            } else {
                xmlDetail.setSku("");
                throw new BusinessException("产品对应的库号不存在!");
            }
            StringBuffer productName = new StringBuffer();
            if (!StringUtil.isNullOrEmpty(product.getClassesName())) {
                productName.append(product.getClassesName());
            }
            if (!StringUtil.isNullOrEmpty(product.getMachiningName())) {
                productName.append("-").append(product.getMachiningName());
            }
            if (!StringUtil.isNullOrEmpty(product.getBreedName())) {
                productName.append("-").append(product.getBreedName());
            }
            if (!StringUtil.isNullOrEmpty(product.getFeatureName())) {
                productName.append("-").append(product.getFeatureName());
            }
            if (!StringUtil.isNullOrEmpty(product.getWeightName())) {
                productName.append("-").append(product.getWeightName());
            }
            if (!StringUtil.isNullOrEmpty(product.getGradeName())) {
                productName.append("-").append(product.getGradeName());
            }
            xmlDetail.setSkuDesc(StringUtil.toSafeString(productName));
            xmlDetail.setSkuGroup(StringUtil.toSafeString(product.getClassesCode()));
            xmlDetail.setGroupName(StringUtil.toSafeString(product.getClassesName()));
            xmlDetail.setUom("CASE");
            xmlDetail.setGrossWeight(StringUtil.toSafeString(product.getGrossweightOut()));
            xmlDetail.setNetWeight(StringUtil.toSafeString(product.getNetweightOut()));
            xmlDetail.setLength(StringUtil.toSafeString(product.getNormsLength()));
            xmlDetail.setWidth(StringUtil.toSafeString(product.getNormsWidth()));
            xmlDetail.setHeight(StringUtil.toSafeString(product.getNormsHeight()));
            xmlDetail.setVolume(StringUtil.toSafeString(product.getNormsVolume()));
            /**新增产品规格   2016-6-8,应上海需求*/
            xmlDetail.setFeatureName(StringUtil.toSafeString(product.getFeatureName()));
            /**新增产品等级   2016-6-8,应上海需求*/
            xmlDetail.setGrade(StringUtil.toSafeString(product.getGradeName()));
            xmlDetail.setQtyExpected(StringUtil.toSafeString(detail.getActualDeliveryNum()));
            xmlDetail.setQtyOriginal(StringUtil.toSafeString(detail.getPlanDeliveryNum()));
            /**
             * 设置默认值
             */
            xmlDetail.setConsignee("0000000");
            xmlDetail.setName("神农客");
            xmlDetail.setCompanytype("SUPPLY");
            xmlDetail.setInventorystatus("AVAILABLE");
            xmlDetail.setCoo("");
            index++;
            xmlDetailList.add(xmlDetail);
        }
        //输出名
        String outputName = "SC182207" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, currentDate) + ".xml";

        List<SC182102XmlParam> xmlData = new ArrayList<SC182102XmlParam>();
        xmlData.add(xmlParam);

        List182102Template list182102Template = new List182102Template();
        list182102Template.setData(xmlData);

        JaxbXmlWrite<SC182102XmlParam> xmlWrite = new JaxbXmlWrite<>(list182102Template);
        InputStream inputStream = xmlWrite.createListTemplateXml(List182102Template.class);
        //上传生成的xml
        Boolean result = WmsFtpUtils.uploadXml("/", outputName, inputStream);

//            Map<String, File> fileMap = new HashMap<>();
//            fileMap.put(outputName, new File(uploadFile));
//            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
//        return result;
        return result;
    }

    /**
     * 填充产品包装信息
     *
     * @param product PDInfoResult
     */
    public void setPdNormsStd(PDInfoResult product) {
        logger.debug("填充产品包装信息");

        PDInfoParam param = new PDInfoParam();
        param.setClassesCode(product.getClassesCode());
        param.setMachiningCode(product.getMachiningCode());
        param.setBreedCode(product.getBreedCode());
        param.setFeatureCode(product.getFeatureCode());
        param.setWeightCode(product.getWeightCode());

        List<PDInfoResult> list = RestUtil.getPdNormsStd(param);
        if (!CollectionUtils.isEmpty(list)) {
            PDInfoResult productInfo = list.get(0);
            if (productInfo != null) {
                product.setNormsCode(productInfo.getNormsCode());
                product.setNormsName(productInfo.getNormsName());
                product.setNormsOut(productInfo.getNormsOut());
                if (productInfo.getNormsKg() != null) {
                    product.setNormsKg(productInfo.getNormsKg());
                } else {
                    product.setNormsKg("0");
                }
                product.setGrossweightOut(productInfo.getGrossweightOut());
                product.setNetweightOut(productInfo.getNetweightOut());
                product.setNormsLength(productInfo.getNormsLength());
                product.setNormsWidth(productInfo.getNormsWidth());
                product.setNormsHeight(productInfo.getNormsHeight());
                product.setNormsVolume(productInfo.getNormsVolume());
            }
        }
    }

    /**
     * 获取产品信息 + 根据供应商编码，产品编码，平台编码获取货号  批量处理
     *
     * @param productList
     */
    private Map<String, Object> getDataMap(List<ISC182207DetailRsParam> productList, String slCode) {
        Map<String, PDInfoResult> pdCodeMap = new HashMap<String, PDInfoResult>();
        Map<String, SlPdArtnoBean> slCodeMap = new HashMap<String, SlPdArtnoBean>();
        List<String> pdCodes = new ArrayList<String>();
        for (ISC182207DetailRsParam detail : productList) {
            String pdcode = detail.getPdCode();
            if (!StringUtil.isNullOrEmpty(pdcode)) {
                pdCodes.add(pdcode);
            }
        }
        // 获取产品信息 批量处理 调用接口
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setPdCodes(pdCodes);
        pdInfoParam.setGradeFlag("YES");
        List<PDInfoResult> res = RestUtil.getProductInfoList(pdInfoParam);
        if (!CollectionUtils.isEmpty(res)) {
            List<SlPdArtnoBean> slPdArtnoBeanList = new ArrayList<SlPdArtnoBean>();
            for (PDInfoResult bean : res) {
                pdCodeMap.put(bean.getPdCode(), bean);
                // 准备数据
                SlPdArtnoBean slPdArtnoBean = new SlPdArtnoBean();
                slPdArtnoBean.setSlCode(slCode);
                slPdArtnoBean.setClassesCode(bean.getClassesCode());
                slPdArtnoBean.setMachiningCode(bean.getMachiningCode());
                slPdArtnoBean.setBreedCode(bean.getBreedCode());
                slPdArtnoBean.setFeatureCode(bean.getFeatureCode());
                slPdArtnoBean.setWeightCode(bean.getWeightCode());
                slPdArtnoBean.setGradeCode(bean.getGradeCode());
                slPdArtnoBean.setSalesPlatform(StringUtil.toString(NumberConst.IntDef.INT_ONE));
                slPdArtnoBeanList.add(slPdArtnoBean);
            }

            //根据供应商编码，产品编码，平台编码获取货号
            SlPdArtnoBean slPdArtnoBean = new SlPdArtnoBean();
            slPdArtnoBean.setSlPdArtnoBeanList(slPdArtnoBeanList);
            SlPdArtnoBean slPdArtno = RestUtil.getSlPdArtNo(slPdArtnoBean);
            List<SlPdArtnoBean> slPdArtnoBeanListR = slPdArtno.getSlPdArtnoBeanList();
            if (!CollectionUtils.isEmpty(slPdArtnoBeanListR)) {
                for (SlPdArtnoBean bean : slPdArtnoBeanListR) {
                    String key = bean.getClassesCode() + bean.getMachiningCode() + bean.getBreedCode()
                            + bean.getFeatureCode() + bean.getWeightCode() + bean.getGradeCode();
                    slCodeMap.put(key, bean);
                }
            }
        }
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("pdCodeMap", pdCodeMap);
        maps.put("slCodeMap", slCodeMap);
        return maps;
    }
}
