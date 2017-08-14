package com.msk.ssc.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC11304DeliveryPlanBean;
import com.msk.ssc.bean.SSC11304PackageBean;
import com.msk.ssc.bean.SSC11304Param;
import com.msk.ssc.bean.SSC11304ProductBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/31.
 */
@Service
public class SSC1130403Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(SSC1130403Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SSC11304Logic ssc11304Logic;
    @Autowired
    private SSC1130402Logic ssc1130402Logic;
    @Autowired
    private SSC1130404Logic ssc1130404Logic;

    /**
     * SQL ID常量
     */
    private interface SqlId {
        String GET_PRODUCTS = "getProducts";
        String FIND_PRODUCTS_BY_CONTRACT_ID = "findProductsByContractId";
        String DELETE_PRODUCT_BY_ID = "deleteProductById";
        String UPDATE_PRODUCT_BY_ID = "updateProductById";
    }

    /**
     * 注入DAO
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 单表查询
     */
    @Transactional(readOnly = true)
    public List<SSC11304ProductBean> getProducts(SSC11304Param ssc11304Param) {
        return super.findList(SqlId.GET_PRODUCTS, ssc11304Param);
    }

    /**
     * 根据合同产品ID，查询产品
     */
    @Transactional(readOnly = true)
    public SSC11304ProductBean getProductById(long productId) {
        SSC11304Param ssc11304Param = new SSC11304Param();
        ssc11304Param.setProductId(productId);
        List<SSC11304ProductBean> productBeans = this.getProducts(ssc11304Param);
        if (CollectionUtils.isEmpty(productBeans)) {
            return null;
        }
        return productBeans.get(NumberConst.IntDef.INT_ZERO);
    }

    /**
     * 根据合同ID，查询合同中的产品，及其交货计划
     */
    @Transactional(readOnly = true)
    public List<SSC11304ProductBean> findProductsByContractId(SSC11304Param ssc11304Param) {
        return super.findList(SqlId.FIND_PRODUCTS_BY_CONTRACT_ID, ssc11304Param);
    }

    /**
     * 根据合同产品ID，删除产品、包装和交货计划，更新合同金额和状态
     */
    @Transactional
    public int deleteProduct(SSC11304ProductBean productBean) {
        SSC11304ProductBean dbProductBean = this.getProductById(productBean.getDetailId());
        int count = NumberConst.IntDef.INT_ZERO;
        if (null != dbProductBean) {
            dbProductBean.setUpdId(productBean.getUpdId());
            count = this.deleteProductById(dbProductBean);
            this.deletePackageByProductId(dbProductBean);
            this.deleteDeliveryPlanByCidPcode(dbProductBean);
            this.updateContractAmount(dbProductBean);
        }
        return count;
    }

    /**
     * 根据合同产品ID，删除产品
     */
    @Transactional
    private int deleteProductById(SSC11304ProductBean productBean) {
        String[] primaryKey = {"DETAIL_ID"};
        Object[] primaryKeyValue = {productBean.getDetailId()};
        super.versionValidator("ssc_contract_pr_detail", primaryKey, primaryKeyValue, productBean.getVer());
        productBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.remove(SqlId.DELETE_PRODUCT_BY_ID, productBean);
    }

    /**
     * 根据合同产品ID，删除产品的包装
     */
    @Transactional
    private int deletePackageByProductId(SSC11304ProductBean productBean) {
        SSC11304PackageBean packageBean = new SSC11304PackageBean();
        packageBean.setDetailId(productBean.getDetailId());
        packageBean.setUpdId(productBean.getUpdId());
        return ssc1130404Logic.deletePackageByProductId(packageBean);
    }

    /**
     * 根据合同ID和产品CODE，删除产品的交货计划
     */
    @Transactional
    private int deleteDeliveryPlanByCidPcode(SSC11304ProductBean productBean) {
        SSC11304DeliveryPlanBean deliveryPlanBean = new SSC11304DeliveryPlanBean();
        deliveryPlanBean.setContractId(productBean.getContractId());
        deliveryPlanBean.setPdCode(productBean.getPdCode());
        deliveryPlanBean.setUpdId(productBean.getUpdId());
        return ssc1130402Logic.deleteDeliveryPlanByCidPcode(deliveryPlanBean);
    }

    /**
     * 根据合同ID，更新合同总金额和状态
     */
    @Transactional
    private int updateContractAmount(SSC11304ProductBean productBean) {
        SSC11304Param ssc11304Param = new SSC11304Param();
        ssc11304Param.setContractId(productBean.getContractId());
        return ssc11304Logic.updateContractAmount(ssc11304Param);
    }

    /**
     * 根据合同产品ID，更新产品，更新合同金额和状态
     */
    @Transactional
    public int updateProduct(SSC11304ProductBean productBean) {
        int count = this.updateProductById(productBean);
        this.updateContractAmount(productBean);
        return count;
    }

    /**
     * 根据合同产品ID，更新产品
     */
    @Transactional
    private int updateProductById(SSC11304ProductBean productBean) {
        if (null != productBean.getVer()) {
            String[] primaryKey = {"DETAIL_ID"};
            Object[] primaryKeyValue = {productBean.getDetailId()};
            super.versionValidator("ssc_contract_pr_detail", primaryKey, primaryKeyValue, productBean.getVer());
        }
        productBean.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.UPDATE_PRODUCT_BY_ID, productBean);
    }

    /**
     * 新增产品，更新合同金额和状态
     */
    @Transactional
    public long saveProduct(SSC11304ProductBean productBean) {
        long maxId = commonLogic.maxId("ssc_contract_pr_detail", "DETAIL_ID");
        productBean.setDetailId(maxId);
        productBean.setCrtTime(DateTimeUtil.getCustomerDate());
        super.save(productBean);
        this.updateContractAmount(productBean);
        return maxId;
    }

}
