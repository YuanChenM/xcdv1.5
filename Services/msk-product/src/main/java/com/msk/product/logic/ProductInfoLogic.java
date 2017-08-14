package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@Service
public class ProductInfoLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * SQL Map 中SQL ID定义
     *
     * @author yang_chunyan
     */
    interface SqlId {
        static final String SQL_ID_FIND_PD_CLASSES_BY_CODES = "findPdClassesByCodes";
        static final String SQL_ID_FIND_PD_MACHINING_BY_CODES = "findPdMachiningByCodes";
        static final String SQL_ID_FIND_PD_BREED_BY_CODES = "findPdBreedByCodes";
        static final String SQL_ID_FIND_PD_FEATURE_BY_CODES = "findPdFeatureByCodes";
        static final String SQL_ID_FIND_PD_WEIGHT_BY_CODES = "findPdWeightByCodes";
        static final String SQL_ID_FIND_PD_GRADE_BY_CODES = "findGradeByCodes";
        static final String SQL_ID_FIND_PD_CLASSES = "findPdClasses";
        static final String SQL_ID_FIND_PD_MACHINING = "findPdMachining";
        static final String SQL_ID_FIND_PD_BREED = "findPdBreed";
        static final String SQL_ID_FIND_PD_FEATURE = "findPdFeature";
        static final String SQL_ID_FIND_PD_WEIGHT = "findPdWeight";
        static final String SQL_ID_FIND_PD_GRADE = "findGrade";
        static final String SQL_ID_FIND_PD_PACKAGE = "findPackage";
        static final String SQL_ID_FIND_PD_PRICE = "findPricePdInfo";
        static final String SQL_ID_FIND_PD_CLASSESTREE = "findPdClassesTree";
        static final String SQL_ID_FIND_PD_BY_PARAMS = "findProductByParams";
        static final String SQL_ID_FIND_PD_CLASSESTREE_MAT = "findPdClassesTreeMat";
    }

    /**
     * 查找产品信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PDInfoResult findPdInfo(PDInfoParam param) {
        String pdCode = param.getPdCode();
        PDInfoResult result = new PDInfoResult();
            if (!StringUtils.hasLength(pdCode)) {
                //查询产品有两种参数方式 1.组装的pdCode 2.classesCode machiningCode breedCode featureCode weightCode
                    String classesCode = param.getClassesCode();
                    String machiningCode = param.getMachiningCode();
                    String breedCode = param.getBreedCode();
                    String featureCode = param.getFeatureCode();
                    String weightCode = param.getWeightCode();
                    if (StringUtils.hasLength(classesCode) && StringUtils.hasLength(machiningCode) && StringUtils.hasLength(breedCode) && StringUtils.hasLength(featureCode) && StringUtils.hasLength(weightCode)) {
                        String code = classesCode + machiningCode + breedCode + featureCode + weightCode;
                        result = this.setValueProduct(code);
                    }
            } else {
                result = this.setValueProduct(pdCode);
            }
        if(StringUtils.hasLength(param.getIsPackage()) && pdCode.length() >= NumberConst.IntDef.INT_NINE){
            /** 产品类别Code(1-2) */
            String classesCode = pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            /** 产品二级分类Code(2-3) */
            String machiningCode = pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            /** 产品品种Code(3-5) */
            String breedCode = pdCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE);
            /** 产品特征Code(5-7) */
            String featureCode = pdCode.substring(NumberConst.IntDef.INT_FIVE, NumberConst.IntDef.INT_SEVEN);
            /** 产品净重Code(7-9) */
            String weightCode = pdCode.substring(NumberConst.IntDef.INT_SEVEN, NumberConst.IntDef.INT_NINE);
            PDInfoParam rsParam = new PDInfoParam();
            rsParam.setClassesCode(classesCode);
            rsParam.setMachiningCode(machiningCode);
            rsParam.setBreedCode(breedCode);
            rsParam.setFeatureCode(featureCode);
            rsParam.setWeightCode(weightCode);
            List<PDInfoResult> normsResult = findListPackages(rsParam);
            if(!CollectionUtils.isEmpty(normsResult)){
                PDInfoResult rs = normsResult.get(NumberConst.IntDef.INT_ZERO);
                result.setNormsOut(rs.getNormsOut());
                result.setNormsCode(rs.getNormsCode());
                result.setNormsName(rs.getNormsName());
                result.setNormsVolume(rs.getNormsVolume());
                result.setNetweightOut(rs.getNetweightOut());
            }
        }
        return result;
    }

    /**
     * 查找产品信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PDInfoResult> findPdInfos(PDInfoParam param) {
        List<PDInfoResult> results = new ArrayList<>();
        if(null == param.getType())//如果未传类型，则默认五个编码进行查询
            param.setType(NumberConst.IntDef.INT_FIVE);
        results = super.findList(SqlId.SQL_ID_FIND_PD_BY_PARAMS,param);
        return results;
    }


    /**
     * 拼装产品信息
     * @param pdCode
     * @return
     */
    private PDInfoResult setValueProduct(String pdCode) {
        PDInfoResult result = new PDInfoResult();
        List<PDInfoResult> pdClasses = new ArrayList<PDInfoResult>();
        List<PDInfoResult> pdMachinings = new ArrayList<PDInfoResult>();
        List<PDInfoResult> pdBreeds = new ArrayList<PDInfoResult>();
        List<PDInfoResult> pdFeatures = new ArrayList<PDInfoResult>();
        List<PDInfoResult> pdWeights = new ArrayList<PDInfoResult>();
        List<PDInfoResult> pdGrades = new ArrayList<PDInfoResult>();
        result.setPdCode(pdCode);
        if (pdCode.length() >= NumberConst.IntDef.INT_TWO) {
            pdClasses = this.findPdClasses(pdCode);
            if (!CollectionUtils.isEmpty(pdClasses) && pdClasses.size() > 0) {
                result.setClassesCode(pdClasses.get(0).getClassesCode());
                result.setClassesName(pdClasses.get(0).getClassesName());
            }
        }
        if (pdCode.length() >= NumberConst.IntDef.INT_THREE) {
            pdMachinings = this.findPdMachining(pdCode);
            if (!CollectionUtils.isEmpty(pdMachinings) && pdMachinings.size() > 0) {
                result.setMachiningCode(pdMachinings.get(0).getMachiningCode());
                result.setMachiningName(pdMachinings.get(0).getMachiningName());
            } else {
                result = new PDInfoResult();
                return result;
            }
        }
        if (pdCode.length() >= NumberConst.IntDef.INT_FIVE) {
            pdBreeds = this.findPdBreed(pdCode);
            if (!CollectionUtils.isEmpty(pdBreeds) && pdBreeds.size() > 0) {
                result.setBreedCode(pdBreeds.get(0).getBreedCode());
                result.setBreedName(pdBreeds.get(0).getBreedName());
            } else {
                result = new PDInfoResult();
                return result;
            }
        }
        if (pdCode.length() >= NumberConst.IntDef.INT_SEVEN) {
            pdFeatures = this.findPdFeature(pdCode);
            if (!CollectionUtils.isEmpty(pdFeatures) && pdFeatures.size() > 0) {
                result.setFeatureCode(pdFeatures.get(0).getFeatureCode());
                result.setFeatureName(pdFeatures.get(0).getFeatureName());
            } else {
                result = new PDInfoResult();
                return result;
            }
        }
        if (pdCode.length() >= NumberConst.IntDef.INT_NINE) {
            pdWeights = this.findPdWeight(pdCode);

            if (!CollectionUtils.isEmpty(pdWeights) && pdWeights.size() > 0) {
                result.setWeightCode(pdWeights.get(0).getWeightCode());
                result.setWeightName(pdWeights.get(0).getWeightName());
                result.setWeightVal(pdWeights.get(0).getWeightVal());
            } else {
                result = new PDInfoResult();
                return result;
            }
        }
        if (pdCode.length() > NumberConst.IntDef.INT_NINE) {
            pdGrades = this.findPdGrade(pdCode);
            if (!CollectionUtils.isEmpty(pdGrades) && pdGrades.size() > 0) {
                result.setGradeCode(pdGrades.get(0).getGradeCode());
                result.setGradeName(pdGrades.get(0).getGradeName());
            } else {
                result = new PDInfoResult();
                return result;
            }
        }
        return result;
    }

    /**
     * 判断产品编码的长度
     * @param pdCode
     * @return
     */
    private boolean juidgePdCodeLength(String pdCode)
    {
        if(pdCode.length() == NumberConst.IntDef.INT_FOUR ||
                pdCode.length() == NumberConst.IntDef.INT_SIX ||
                pdCode.length() == NumberConst.IntDef.INT_EIGHT ||
                pdCode.length() > NumberConst.IntDef.INT_TEN){
            return true;
        }
        return false;
    }

    /**
     * 查询产品类别信息
     *
     * @param pdCode 参数：类别编码
     * @return 类别
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdClasses(String pdCode) {
        BaseParam param = new BaseParam();
        if(StringUtils.hasLength(pdCode) && pdCode.length() > NumberConst.IntDef.INT_TWO) {
            if(this.juidgePdCodeLength(pdCode))
                return null;
            pdCode = pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
        }
        param.setFilter("classesCode", pdCode);
        return super.findList(SqlId.SQL_ID_FIND_PD_CLASSES, param);
    }

    /**
     * 查询产品二级分类信息
     *
     * @param pdCode 参数：类别编码，二级分类编码
     * @return 产品二级分类
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdMachining(String pdCode) {
        BaseParam param = new BaseParam();
        if(StringUtils.hasLength(pdCode)){
            if(this.juidgePdCodeLength(pdCode))
                return null;
            if(pdCode.length() == NumberConst.IntDef.INT_ONE)
                param.setFilter("machiningCode", pdCode);
            else if(pdCode.length() == NumberConst.IntDef.INT_TWO){
                param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
            }else{
                param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
                param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
            }
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_MACHINING, param);
    }

    /**
     * 查询产品品种信息
     *
     * @param pdCode 参数
     * @return 品种
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdBreed(String pdCode) {
        BaseParam param = new BaseParam();
        if(StringUtils.hasLength(pdCode)) {
            if(this.juidgePdCodeLength(pdCode))
                return null;
            if (pdCode.length() == NumberConst.IntDef.INT_TWO)
                param.setFilter("breedCode", pdCode);
            else if(pdCode.length() >= NumberConst.IntDef.INT_FIVE){
                param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
                param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
                param.setFilter("breedCode", pdCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE));
            }
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_BREED, param);
    }

    /**
     * 查询产品特征信息
     *
     * @param pdCode 参数
     * @return 特征
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdFeature(String pdCode) {
        BaseParam param = new BaseParam();
        if(StringUtils.hasLength(pdCode)) {
            if(this.juidgePdCodeLength(pdCode))
                return null;
            if (pdCode.length() == NumberConst.IntDef.INT_TWO)
                param.setFilter("featureCode", pdCode);
            else if(pdCode.length() >= NumberConst.IntDef.INT_SEVEN){
                param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
                param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
                param.setFilter("breedCode", pdCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE));
                param.setFilter("featureCode", pdCode.substring(NumberConst.IntDef.INT_FIVE, NumberConst.IntDef.INT_SEVEN));
            }
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_FEATURE, param);
    }

    /**
     * 查询产品净重信息
     *
     * @param pdCode 参数
     * @return 净重
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdWeight(String pdCode) {
        BaseParam param = new BaseParam();
        if(StringUtils.hasLength(pdCode)) {
            if(this.juidgePdCodeLength(pdCode))
                return null;
            if (pdCode.length() == NumberConst.IntDef.INT_TWO)
                param.setFilter("weightCode", pdCode);
            else if(pdCode.length() >= NumberConst.IntDef.INT_NINE){
                param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
                param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
                param.setFilter("breedCode", pdCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE));
                param.setFilter("featureCode", pdCode.substring(NumberConst.IntDef.INT_FIVE, NumberConst.IntDef.INT_SEVEN));
                param.setFilter("weightCode", pdCode.substring(NumberConst.IntDef.INT_SEVEN, NumberConst.IntDef.INT_NINE));
            }
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_WEIGHT, param);
    }


    /**
     * 查询产品等级信息
     *
     * @param pdCode 参数
     * @return 类别
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdGrade(String pdCode) {
        BaseParam param = new BaseParam();
        if(StringUtils.hasLength(pdCode)) {
            if(this.juidgePdCodeLength(pdCode))
                return null;
            if (pdCode.length() == NumberConst.IntDef.INT_ONE)
                param.setFilter("gradeCode", pdCode);
            else {
                param.setFilter("gradeCode", pdCode.substring(NumberConst.IntDef.INT_NINE));
            }
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_GRADE, param);
    }

    /**
     * 查询产品类别信息
     *
     * @param list 参数：类别编码
     * @return 类别
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdClassesByCodes(List<PDInfoParam> list) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_CLASSES_BY_CODES);
    }

    /**
     * 查询产品二级分类信息
     *
     * @param list 参数：类别编码，二级分类编码
     * @return 产品二级分类
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdMachiningByCodes(List<PDInfoParam> list) {
        List<PDInfoResult> result = new ArrayList<PDInfoResult>();
        if(!CollectionUtils.isEmpty(list) && list.size() > 0)
        {
            Map<String,Object> param = new HashMap<>();
            param.put("list", list);
            result = super.findList(param,SqlId.SQL_ID_FIND_PD_MACHINING_BY_CODES);
           /* for(PDInfoParam pparam : list)
            {
                BaseParam param = new BaseParam();
                param.setFilter("classesCode",pparam.getClassesCode());
                param.setFilter("machiningCode",pparam.getMachiningCode());
                PDInfoResult rs = super.findOne(SqlId.SQL_ID_FIND_PD_MACHINING,param);
                result.add(rs);
            }*/
        }
        return result;
    }

    /**
     * 查询产品品种信息
     *
     * @param list 参数
     * @return 品种
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdBreedByCodes(List<PDInfoParam> list) {
        List<PDInfoResult> result = new ArrayList<PDInfoResult>();
        if(!CollectionUtils.isEmpty(list) && list.size() > 0)
        {
            Map<String,Object> param = new HashMap<>();
            param.put("list", list);
            result = super.findList(param,SqlId.SQL_ID_FIND_PD_BREED_BY_CODES);
            /*for(PDInfoParam pparam : list)
            {
                BaseParam param = new BaseParam();
                param.setFilter("classesCode",pparam.getClassesCode());
                param.setFilter("machiningCode",pparam.getMachiningCode());
                param.setFilter("breedCode",pparam.getBreedCode());
                PDInfoResult rs = super.findOne(SqlId.SQL_ID_FIND_PD_BREED,param);
                result.add(rs);
            }*/
        }
        return result;
    }

    /**
     * 查询产品特征信息
     *
     * @param list 参数
     * @return 特征
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdFeatureByCodes(List<PDInfoParam> list) {
        List<PDInfoResult> result = new ArrayList<PDInfoResult>();
        if(!CollectionUtils.isEmpty(list) && list.size() > 0)
        {
            Map<String,Object> param = new HashMap<>();
            param.put("list", list);
            result = super.findList(param,SqlId.SQL_ID_FIND_PD_FEATURE_BY_CODES);
           /* for(PDInfoParam pparam : list)
            {
                BaseParam param = new BaseParam();
                param.setFilter("classesCode",pparam.getClassesCode());
                param.setFilter("machiningCode",pparam.getMachiningCode());
                param.setFilter("breedCode",pparam.getBreedCode());
                param.setFilter("featureCode",pparam.getFeatureCode());
                PDInfoResult rs = super.findOne(SqlId.SQL_ID_FIND_PD_FEATURE,param);
                result.add(rs);
            }*/
        }
        return result;
    }

    /**
     * 查询产品净重信息
     *
     * @param list 参数
     * @return 净重
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdWeightByCodes(List<PDInfoParam> list) {
        List<PDInfoResult> result = new ArrayList<PDInfoResult>();
        if(!CollectionUtils.isEmpty(list) && list.size() > 0)
        {
            Map<String,Object> param = new HashMap<>();
            param.put("list", list);
            result = super.findList(param,SqlId.SQL_ID_FIND_PD_WEIGHT_BY_CODES);
            /*for(PDInfoParam pparam : list)
            {
                BaseParam param = new BaseParam();
                param.setFilter("classesCode",pparam.getClassesCode());
                param.setFilter("machiningCode",pparam.getMachiningCode());
                param.setFilter("breedCode",pparam.getBreedCode());
                param.setFilter("featureCode",pparam.getFeatureCode());
                param.setFilter("weightCode",pparam.getWeightCode());
                PDInfoResult rs = super.findOne(SqlId.SQL_ID_FIND_PD_WEIGHT,param);
                result.add(rs);
            }*/
        }
        return result;
    }


    /**
     * 查询产品等级信息
     *
     * @param list 参数
     * @return 类别
     * @author yang_chunyan
     */
    @Transactional
    public List<PDInfoResult> findPdGradeByCodes(List<PDInfoParam> list) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_GRADE_BY_CODES);
    }

    /**
     * 产品包装一览查询接口
     *
     * @param param
     * @return PDInfoResult
     */
    @Transactional(readOnly = true)
    public List<PDInfoResult> findListPackages(PDInfoParam param) {
        if (param == null)
            param = new PDInfoParam();
        List<PDInfoResult> results = findList(SqlId.SQL_ID_FIND_PD_PACKAGE,param);
        if(CollectionUtils.isEmpty(results))
            results.add(new PDInfoResult());//查询为空时，包装的信息需要返回。
        return results;
    }


    /**
     * 产品包装一览查询接口
     *
     * @param param
     * @return PDInfoResult
     */
    @Transactional(readOnly = true)
    public List<PDInfoResult> findListNorms(PDInfoParam param) {
        if (param == null) param = new PDInfoParam();
        List<PDInfoParam> params = param.getParams();
        List<PDInfoResult> results = new ArrayList<>();
        for(PDInfoParam pdInfoParam : params){
            List<PDInfoResult> resultlist =  super.findList(pdInfoParam);
            if(!CollectionUtils.isEmpty(resultlist))
                results.add(resultlist.get(0));
            else
                results.add(null);
        }
        return results;
    }

    /**
     * oem供应商查询产品信息
     * @param param
     * @param <T>
     * @return
     */
    @Transactional
    public <T extends BaseEntity> List<T> findPdPageList(PDInfoParam param,ProductPageResult pdResult) {
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
            int startPos = 0;
            int endPos = param.getPageCount();
            if(param.getPageNo() > NumberConst.IntDef.INT_ZERO){
                startPos = param.getPageCount() * (param.getPageNo() - NumberConst.IntDef.INT_ONE);
            }else{
                startPos = NumberConst.IntDef.INT_ONE;
            }
            param.setStartSize(startPos);
            param.setEndSize(endPos);
        }

        return super.findPageList(param, pdResult);
    }

    /**
     * 查询产品价盘信息（暂无人用）
     * @param param
     * @return
     */
    @Transactional
    public List<PDInfoResult> getPricePDDetailInfo(PDInfoParam param)
    {
        return super.findList(SqlId.SQL_ID_FIND_PD_PRICE, param);
    }

    /**
     * 查询产品分类表
     * @param param
     * @return
     */
    @Transactional
    public List<PDInfoResult> findPdClassesTree(PDInfoParam param)
    {
        return super.findList(SqlId.SQL_ID_FIND_PD_CLASSESTREE,param);
    }

    /**
     * 查询产品原料产地信息
     * @param param
     * @return
     */
    @Transactional
    public List<PdClassestreeMat> findPdClassesTreeMat(PDInfoParam param)
    {
        return super.findList(SqlId.SQL_ID_FIND_PD_CLASSESTREE_MAT,param);
    }
}
