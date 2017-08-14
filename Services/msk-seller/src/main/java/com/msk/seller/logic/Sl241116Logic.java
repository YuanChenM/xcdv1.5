package com.msk.seller.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEnterprise;
import com.msk.core.entity.SlPdBrand;
import com.msk.core.entity.SlPdPkg;
import com.msk.core.entity.SlProduct;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.SL241116Bean;
import com.msk.seller.bean.SL241116Bean;
import com.msk.seller.utils.SlProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 卖家产品列表Logic.
 *
 * @author gyh
 */
public class Sl241116Logic extends BaseLogic {
    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL Map 中SQL ID定义
     * 
     * @author gyh
     */
    interface SqlId {
        final static String SQL_ID_FIND_SL_EP_INFO = "findSlEpInfo";
        final static String SQL_ID_FIND_SL_PD_BRAND = "findSlPdBrand";
        final static String SQL_ID_SAVE_SL_PD_PKG = "saveSlPdPkg";
        static final String SQL_ID_DELETE_SL_Product = "deleteProduct";
        static final String SQL_ID_MODIFY_SL_PKG = "modifySlPdPkg";
        static final String SQL_ID_FIND_SL_PRODUCT_LIST = "findSlProductList";
        static final String SQL_ID_UP_SL_PD_STATUS = "upSlPdStatus";
        static final String SQL_ID_SAVE_SL_PRODUCT_HIS = "saveSlProductHis";
        static final String SQL_ID_FIND_SL_PRODUCTS_LIST = "findSlProductsList";
        static final String SQL_ID_FIND_SL_PRODUCT_COUNT = "findSlProductCount";

        // 用于产品判定
        static final String SQL_ID_GET_PRO_COUNT = "getSlProCount";
        // 批量新增
        static final String SQL_ID_INSERT_SL_PRODUCT_LIST = "insertSlProductList";
        // 批量修改
        static final String SQL_ID_UPDATE_SL_PRODUCT_LIST = "updateSlProductList";
    }

    /**
     * 修改卖家产品状态
     * @param slProduct 参数
     * @return 结果
     */
    @Transactional(readOnly = false)
    public int upSlPdStatus(SlProduct slProduct){
        //每次修改卖家产品，根据id查询卖家产品，并将最新状态存入卖家产品履历表中
        BasePageParam param=new BasePageParam();
        param.setPaging(false);
        param.getFilterMap().put("slPdId", slProduct.getSlPdId());
        List<SL241116Bean> beans= this.findPageList(param, SL241116Bean.class);
        if(!CollectionUtils.isEmpty(beans)&&beans.size()>0){
            SL241116Bean product=beans.get(0);
            if(!slProduct.getStatus().equals(product.getStatus())){
                product.setStatus(slProduct.getStatus());
                //存卖家产品履历
                product.setHisId(commonLogic.maxId("SL_PRODUCT_STATUS_HIS", "HIS_ID"));
                product.setCrtTime(DateTimeUtil.getCustomerDate());
                this.save(SqlId.SQL_ID_SAVE_SL_PRODUCT_HIS, product);
            }
        }else{
            throw new BusinessException("卖家产品ID不存在，请检查后提交！");
        }
        slProduct.setUpdTime(DateTimeUtil.getCustomerDate());
        return this.modify(SqlId.SQL_ID_UP_SL_PD_STATUS,slProduct);
    }


    /**
     * 批量修改卖家产品状态
     * @param slProductList 参数
     * @return 结果
     */
    @Transactional(readOnly = false)
    public void upSlPdStatus(List<SlProduct> slProductList){
        for(SlProduct slProduct:slProductList){
            slProduct.setUpdTime(DateTimeUtil.getCustomerDate());
            this.upSlPdStatus(slProduct);
        }
    }


    /**
     * 批量查询卖家产品表
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SL241116Bean> findSlProductList(BaseParam param){
        return this.findList(SqlId.SQL_ID_FIND_SL_PRODUCT_LIST,param);
    }

    /**
     * 批量查询卖家产品表
     * 只限于主键唯一情况下使用
     * @param list 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SL241116Bean> findSlProductList(List<SlProduct> list){
        List<SL241116Bean> beanList = new ArrayList<SL241116Bean>();
        for(SlProduct pram:list){
            BaseParam param = new BaseParam();
            param.setFilter("slCode",pram.getSlCode());
            param.getFilterMap().put("slPdId", pram.getSlPdId());
            param.setFilter("machiningCode", pram.getMachiningCode());
            SL241116Bean sl241116Bean = this.findOne(SqlId.SQL_ID_FIND_SL_PRODUCT_LIST,param);
            if (sl241116Bean != null && !StringUtil.isNullOrEmpty(sl241116Bean.getSlCode())) {
                beanList.add(sl241116Bean);
            }else{
                throw new BusinessException("卖家产品ID为" + pram.getSlPdId() + "不存在！");
            }
        }
        return beanList;
    }

    /**
     * 校验SL_PRODUCT中数据是否存在
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public int findSlProductCount(SlProduct param){
        return this.getCount(SqlId.SQL_ID_FIND_SL_PRODUCT_COUNT, param);
    }


    /**
     * 单查卖家产品表
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SL241116Bean> findSlProductSList(BaseParam param){
        return this.findList(SqlId.SQL_ID_FIND_SL_PRODUCTS_LIST,param);
    }
    /**
     * 修改卖家包装
     * @param slPdPkg 参数
     * @return 结果
     */
    @Transactional
    public Integer modifySlPdPkg(SlPdPkg slPdPkg){
        slPdPkg.setUpdTime(DateTimeUtil.getCustomerDate());
        return this.modify(SqlId.SQL_ID_MODIFY_SL_PKG,slPdPkg);
    }

    /**
     * 删除卖家产品
     * @param product 参数
     * @return 结果
     */
    @Transactional
    public Integer deleteProduct(SlProduct product){
        product.setUpdTime(DateTimeUtil.getCustomerDate());
        return this.modify(SqlId.SQL_ID_DELETE_SL_Product,product);
    }

    /**
     * 根据卖家编码查询卖家供应商
     * @param param 参数
     * @return 卖家供应商信息
     */
    @Transactional(readOnly = true)
    public List<SlEnterprise> findEpInfo(BaseParam param){
        return super.findList(SqlId.SQL_ID_FIND_SL_EP_INFO,param);
    }
    /**
     * 根据卖家编码查询卖家品牌
     * @param param 参数
     * @return 卖家品牌
     */
    @Transactional(readOnly = true)
    public List<SlPdBrand> findSlPdBrand(BaseParam param){
        return super.findList(SqlId.SQL_ID_FIND_SL_PD_BRAND,param);
    }

    /**
     * 保存规格信息
     * @param slPdPkg 参数
     * @return 结果
     */
    @Transactional(readOnly = false)
    public int saveSlPdPkg(SlPdPkg slPdPkg) {
        slPdPkg.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_ID_SAVE_SL_PD_PKG,slPdPkg);
    }

    /**
     * 卖家产品删除逻辑操作
     * @param list
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public String delSlProducts(List<SlProduct> list){
        String mainTail = "";
        for(SlProduct pram:list){
            BaseParam param = new BaseParam();
            param.getFilterMap().put("slPdId", pram.getSlPdId());
            SlProduct slProduct = this.findOne(param);
            if (slProduct != null && !StringUtil.isNullOrEmpty(slProduct.getSlCode())) {
                if(("1").equals(slProduct.getStatus())||("2").equals(slProduct.getStatus())){
                    mainTail = "删除卖家卖家产品成功！";
                    this.deleteProduct(slProduct);
                }else{
                    mainTail = "删除卖家卖家产品失败！该产品状态不支持被删除。";
                }
            }else{
                throw new BusinessException("卖家产品ID为" + pram.getSlPdId() + "不存在！");
            }
        }
        return mainTail;
    }

    /**
     * 管忠恒
     * 卖家产品数据信息处理
     * @return
     *  关于SL_PRODUCT表主键问题  该出业务逻辑根据业务字段确定一条数据 后期须优化
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public String slProductHandle(List<SlProduct> list){
        String mainTail = "";
        List<SlProduct> addList = new ArrayList<SlProduct>();
        List<SlProduct> addDefList = new ArrayList<SlProduct>();
        List<SlProduct> updList = new ArrayList<SlProduct>();
        Date nowDate = DateTimeUtil.getCustomerDate();
        for(SlProduct slProduct:list){
                // pdFeatureCode为 00时候 根据数据 修改或者新增
            int count = this.getCount(SqlId.SQL_ID_GET_PRO_COUNT,slProduct);
            int maxId = commonLogic.maxId("SL_PRODUCT", "SL_PD_ID").intValue();
            Map<String, Map<String, String>> map = SlProductUtils.mappingCodeToName(slProduct);
            Map<String, String> classesMap = map.get("classes");
            Map<String, String> machiningMap = map.get("machining");
            Map<String, String> breedMap = map.get("breed");
            Map<String, String> featureMap = map.get("feature");
            Map<String, String> weightMap = map.get("weight");
            slProduct.setSlPdId(maxId);
            slProduct.setPdClassesName(classesMap.get(slProduct.getPdClassesCode()));
            slProduct.setMachiningName(machiningMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode()));
            slProduct.setPdBreedName(breedMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode() + slProduct.getPdBreedCode()));
            slProduct.setPdFeatureName(featureMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode() + slProduct.getPdBreedCode() + slProduct.getPdFeatureCode()));
            slProduct.setWeightName(weightMap.get(slProduct.getPdClassesCode() + slProduct.getMachiningCode() + slProduct.getPdBreedCode() + slProduct.getPdFeatureCode() + slProduct.getWeightCode()));
            slProduct.setCrtTime(nowDate);
            if(count < NumberConst.IntDef.INT_ONE) {
                // 新增
                addList.add(slProduct);
            }else{
                // 修改
                updList.add(slProduct);
            }
            if(!"00".equals(slProduct.getPdFeatureCode())){
                addDefList.add(slProduct);
            }
        }
        // 设置新增 pdFeatureCode为 非 00数据
        for(SlProduct slProduct:addDefList){
            slProduct.setPdFeatureCode("00");
        }
        addList.addAll(addDefList);
        // 批量新增
        this.getBaseDao().batchInsert(SqlId.SQL_ID_INSERT_SL_PRODUCT_LIST,addList);
        // 批量修改
        this.getBaseDao().getSqlSession().update(SqlId.SQL_ID_UPDATE_SL_PRODUCT_LIST,updList);

        return mainTail;
    }

    /**
     * 查询分页
     *
     * @param
     * @return
     * @author
     */
    @Transactional(readOnly = true)
    public PageResult<SL241116Bean> findPageResult(BasePageParam basePageParam){
        PageResult<SL241116Bean>  result =  this.findPage(basePageParam, SL241116Bean.class);
        if(result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO){
            List<SL241116Bean>  lists = result.getData();
            // 从redis  获取 是,否
            Map<String, String> yesNoMap = CodeMasterManager.findCodeMasterMap(SellerConstant.YESNO.TYPE);
            for(SL241116Bean bean : lists){
                String distFlgNo = yesNoMap.get(SellerConstant.YESNO.NO+"");
                String distmskFlgNo = distFlgNo;

                // 处理 distFlg
                if(!StringUtil.isNullOrEmpty(bean.getDistFlg())){
                    String value = yesNoMap.get(bean.getDistFlg());
                    if(null != value){
                        distFlgNo = value;
                    }
                }
                bean.setDistFlg(distFlgNo);

                // 处理 distmskFlg
                if(!StringUtil.isNullOrEmpty(bean.getDistmskFlg())){
                    String value = yesNoMap.get(bean.getDistmskFlg());
                    if(null != value){
                        distmskFlgNo = value;
                    }
                }
                bean.setDistmskFlg(distmskFlgNo);
            }
        }
        return result;
    }

    /**
     * 查询分页数据List
     *
     * @param
     * @return
     * @author
     */
    @Transactional(readOnly = true)
    public List<SL241116Bean> findPageResultList(BasePageParam basePageParam){
        List<SL241116Bean>  lists =  this.findPageList(basePageParam, SL241116Bean.class);
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(lists)){
            // 从redis  获取 是,否
            Map<String, String> yesNoMap = CodeMasterManager.findCodeMasterMap(SellerConstant.YESNO.TYPE);
            for(SL241116Bean bean : lists){
                String distFlgNo = yesNoMap.get(SellerConstant.YESNO.NO + "");
                String distmskFlgNo = distFlgNo;
                // 处理 distFlg
                if(!StringUtil.isNullOrEmpty(bean.getDistFlg())){
                    String value = yesNoMap.get(bean.getDistFlg());
                    if(null != value){
                        distFlgNo = value;
                    }
                }
                bean.setDistFlg(distFlgNo);

                // 处理 distmskFlg
                if(!StringUtil.isNullOrEmpty(bean.getDistmskFlg())){
                    String value = yesNoMap.get(bean.getDistmskFlg());
                    if(null != value){
                        distmskFlgNo = value;
                    }
                }
                bean.setDistmskFlg(distmskFlgNo);
            }
        }
        return lists;
    }



    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
