package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141146RsParam;
import com.msk.product.bean.IPD141146RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@Service
public class IPD141146Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_PD_CLASSES = "findPdClasses";
        static final String SQL_ID_FIND_PD_MACHINING = "findPdMachining";
        static final String SQL_ID_FIND_PD_BREED = "findPdBreed";
        static final String SQL_ID_FIND_PD_FEATURE = "findPdFeature";
        static final String SQL_ID_FIND_PD_WEIGHT = "findPdWeight";
        static final String SQL_ID_FIND_PD_GRADE = "findGrade";
    }

    /**
     * 查找产品信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IPD141146RsResult findPdInfo(IPD141146RsParam param) {
        String pdCode = param.getPdCode();
        IPD141146RsResult result = new IPD141146RsResult();
        List<IPD141146RsResult> pdClasses = new ArrayList<>();
        List<IPD141146RsResult> pdMachinings = new ArrayList<>();
        List<IPD141146RsResult> pdBreeds = new ArrayList<>();
        List<IPD141146RsResult> pdFeatures = new ArrayList<>();
        List<IPD141146RsResult> pdWeights = new ArrayList<>();
        List<IPD141146RsResult> pdGrades = new ArrayList<>();
        if (!StringUtils.hasLength(pdCode) || pdCode.length() == 1) {
            return null;
        } else {
            if (pdCode.length() >= NumberConst.IntDef.INT_TWO) {
                pdClasses = this.findPdClasses(pdCode);
            }
            if (pdCode.length() >= NumberConst.IntDef.INT_THREE) {
                pdMachinings = this.findPdMachining(pdCode);
            }
            if (pdCode.length() >= NumberConst.IntDef.INT_FIVE) {
                pdBreeds = this.findPdBreed(pdCode);
            }
            if (pdCode.length() >= NumberConst.IntDef.INT_SEVEN) {
                pdFeatures = this.findPdFeature(pdCode);
            }
            if (pdCode.length() >= NumberConst.IntDef.INT_NINE) {
                pdWeights = this.findPdWeight(pdCode);
            }
            if (pdCode.length() > NumberConst.IntDef.INT_NINE) {
                    pdGrades = this.findPdGrade(pdCode);
            }
            if (!CollectionUtils.isEmpty(pdClasses) && pdClasses.size() > 0) {
                result.setClassesCode(pdClasses.get(0).getClassesCode());
                result.setClassesName(pdClasses.get(0).getClassesName());
            }
            if (!CollectionUtils.isEmpty(pdMachinings) && pdMachinings.size() > 0) {
                result.setMachiningCode(pdMachinings.get(0).getMachiningCode());
                result.setMachiningName(pdMachinings.get(0).getMachiningName());
            }
            if (!CollectionUtils.isEmpty(pdBreeds) && pdBreeds.size() > 0) {
                result.setBreedCode(pdBreeds.get(0).getBreedCode());
                result.setBreedName(pdBreeds.get(0).getBreedName());
            }
            if (!CollectionUtils.isEmpty(pdFeatures) && pdFeatures.size() > 0) {
                result.setFeatureCode(pdFeatures.get(0).getFeatureCode());
                result.setFeatureName(pdFeatures.get(0).getFeatureName());
            }
            if (!CollectionUtils.isEmpty(pdWeights) && pdWeights.size() > 0) {
                result.setWeightCode(pdWeights.get(0).getWeightCode());
                result.setWeightName(pdWeights.get(0).getWeightName());
            }
            if (!CollectionUtils.isEmpty(pdGrades) && pdGrades.size() > 0) {
                result.setGradeCode(pdGrades.get(0).getGradeCode());
                result.setGradeName(pdGrades.get(0).getGradeName());
            }
            return result;
        }
    }



    /**
     * 查询产品类别信息
     *
     * @param pdCode 参数：类别编码
     * @return 类别
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdClasses(String pdCode) {
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
        return super.findList(SqlId.SQL_ID_FIND_PD_CLASSES, param);
    }

    /**
     * 查询产品二级分类信息
     *
     * @param pdCode 参数：类别编码，二级分类编码
     * @return 产品二级分类
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdMachining(String pdCode) {
        BaseParam param = new BaseParam();
        if(pdCode.length() == 1)
            param.setFilter("machiningCode", pdCode);
        else
        {
            param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
            param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_MACHINING, param);
    }

    /**
     * 查询产品品种信息
     *
     * @param pdCode 参数
     * @return 品种
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdBreed(String pdCode) {
        BaseParam param = new BaseParam();
        if(pdCode.length() == 2)
            param.setFilter("breedCode", pdCode);
        else {
            param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
            param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
            param.setFilter("breedCode", pdCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE));
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_BREED, param);
    }

    /**
     * 查询产品特征信息
     *
     * @param pdCode 参数
     * @return 特征
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdFeature(String pdCode) {
        BaseParam param = new BaseParam();
        if(pdCode.length() == 2)
            param.setFilter("featureCode", pdCode);
        else {
            param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
            param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
            param.setFilter("breedCode", pdCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE));
            param.setFilter("featureCode", pdCode.substring(NumberConst.IntDef.INT_FIVE, NumberConst.IntDef.INT_SEVEN));
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_FEATURE, param);
    }

    /**
     * 查询产品净重信息
     *
     * @param pdCode 参数
     * @return 净重
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdWeight(String pdCode) {
        BaseParam param = new BaseParam();
        if(pdCode.length() == 2)
            param.setFilter("weightCode", pdCode);
        else {
            param.setFilter("classesCode", pdCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO));
            param.setFilter("machiningCode", pdCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE));
            param.setFilter("breedCode", pdCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE));
            param.setFilter("featureCode", pdCode.substring(NumberConst.IntDef.INT_FIVE, NumberConst.IntDef.INT_SEVEN));
            param.setFilter("weightCode", pdCode.substring(NumberConst.IntDef.INT_SEVEN, NumberConst.IntDef.INT_NINE));
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_WEIGHT, param);
    }


    /**
     * 查询产品等级信息
     *
     * @param pdCode 参数
     * @return 类别
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdGrade(String pdCode) {
        BaseParam param = new BaseParam();
        if(pdCode.length() == 1)
            param.setFilter("gradeCode", pdCode);
        else {
            param.setFilter("gradeCode", pdCode.substring(NumberConst.IntDef.INT_NINE));
        }
        return super.findList(SqlId.SQL_ID_FIND_PD_GRADE, param);
    }


}
