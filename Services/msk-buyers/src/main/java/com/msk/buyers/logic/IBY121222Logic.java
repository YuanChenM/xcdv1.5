package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121222RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByResearchCategory;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClassestreeMat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 买家产品第三方品牌调研用IBY121222Logic.
 *
 * @author zhou_ling
 */
@Service
public class IBY121222Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121222Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private IBY121206Logic iby121206Logic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_ling
     */
    interface SqlId {

        // 买家产品第三方品牌查询
        static String SQLID_GET_BY_RESEARCH_BRAND = "getByResearchBrand";
        // 插入买家产品品类和需求调研
        static final String SQLID_INSERT_BY_RESEARCH_CATEGORY = "insertByResearchCategory";
        // 查询买家产品品类和需求调研是否有数据
        static final String SQLID_COUNT_BY_RESEARCH_CATEGORY = "countByResearchCategory";
        // 查询产品pd_classestree_mat
        static String SQLID_GET_PD_CLASSESTREE_MAT = "getPdClassesMat";
        // 查询产品pd_breed
        static String SQLID_GET_PD_BREED = "getPdBreed";
    }

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
     * 买家产品第三方品牌调研查询
     *
     * @param baseParam baseParam
     * @return
     * @author zhou_ling
     */
    @Transactional(readOnly = true)
    public List<IBY121222RsParam> findBuyerBrandList(BaseParam baseParam) {
        // 查询买家所有品牌
        List<IBY121222RsParam> returnList = super.findList(SqlId.SQLID_GET_BY_RESEARCH_BRAND, baseParam);
        for (IBY121222RsParam returnParam : returnList) {
            if (!StringUtil.isNullOrEmpty(returnParam.getProductId())
                    && !StringUtil.isNullOrEmpty(returnParam.getProductSuf())) {
                // TODO
                // 先不按照原来的方式，后期需要修改
               /* String picPath = ConfigManager.getFtpHttpUrl() + "/" + BusinessConst.BYPath.BYIMAGEPATH + "/"
                        + returnParam.getBuyerId() + "/" + returnParam.getProductId() + StringConst.DOT
                        + returnParam.getProductSuf();
                returnParam.setPicturePath(picPath);*/
            }
        }
        return returnList;
    }

    /**
     * 买家产品第三方品牌调研更新
     *
     * @param param param
     * @return
     */
    @Transactional
    public int updateResearchBrand(IBY121222RsParam param) {
        logger.debug("买家产品第三方品牌调研更新");
       /* param.setUpdTime(DateTimeUtil.getCustomerDate());*/
        param.setDelFlg(SystemConst.DelFlg.ENABLE);
        param.setVer(NumberConst.IntDef.INT_ONE);

        // 判断买家产品品类和需求调研表是否有数据
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("buyerId", param.getBuyerId());
        baseParam.getFilterMap().put("classesCode", param.getClassesCode());
        baseParam.getFilterMap().put("machiningCode", param.getMachiningCode());
        baseParam.getFilterMap().put("breedCode", param.getBreedCode());
        baseParam.getFilterMap().put("saleName", param.getSaleName());
        ByResearchCategory byResearchCategory = super.findOne(SqlId.SQLID_COUNT_BY_RESEARCH_CATEGORY, baseParam);
        if (byResearchCategory == null) {
            // 插入买家产品品类和需求调研表
            Long categoryId = commonLogic.maxId("by_research_category", "CATEGORY_ID");
            param.setCategoryId(categoryId);
            baseParam.getFilterMap().put("classesTreeCode", param.getClassesCode() + param.getMachiningCode());
            // 查询产品pd_classestree_mat表
            PdClassestreeMat pdClassestreeMat = super.findOne(SqlId.SQLID_GET_PD_CLASSESTREE_MAT, baseParam);
            // 查询产品pd_breed表
            PdBreed pdBreed = super.findOne(SqlId.SQLID_GET_PD_BREED, baseParam);
            if (pdClassestreeMat != null) {
                param.setScientificName(pdClassestreeMat.getScientificName());
                param.setSaleName(pdClassestreeMat.getSalesName());
                param.setPopularName(pdClassestreeMat.getLocalName());
            } else {
                if (pdBreed != null) {
                    param.setSaleName(pdBreed.getBreedName());
                }
            }
            // 插入家产品品类和需求调研表
            super.save(SqlId.SQLID_INSERT_BY_RESEARCH_CATEGORY, param);
        } else {
            param.setCategoryId(byResearchCategory.getCategoryId());
        }
        // 插入买家第三方品牌调研表
        int updateCount = 0;
        // 获取图片的uuid和后缀名
        if (!StringUtil.isEmpty(param.getPicturePath())) {
            String[] paramStr = param.getPicturePath().split("/");
            String filePath = "/" + paramStr[3] + "/" + paramStr[4] + "/" + paramStr[5];
            String fileName = paramStr[paramStr.length - 1];
            String[] fileInfo = iby121206Logic.getFileId(filePath, fileName, param.getBuyerId());
            param.setProductId(fileInfo[NumberConst.IntDef.INT_ZERO]);
            param.setProductSuf(fileInfo[NumberConst.IntDef.INT_ONE]);
        }
        updateCount = super.save(param);
        return updateCount;
    }

    /**
     * 买家产品第三方品牌调研删除
     *
     * @param param param
     * @return
     */
    @Transactional
    public int deleteResearchBrand(IBY121222RsParam param) {
        logger.debug("买家产品第三方品牌调研删除");
        /*param.setUpdTime(DateTimeUtil.getCustomerDate());*/
        param.setDelFlg(SystemConst.DelFlg.DISABLE);
        param.setVer(NumberConst.IntDef.INT_ONE);
        return super.modify(param);
    }
}
