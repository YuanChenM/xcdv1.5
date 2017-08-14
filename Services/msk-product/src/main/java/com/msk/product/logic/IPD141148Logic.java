package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141146RsParam;
import com.msk.product.bean.IPD141146RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@Service
public class IPD141148Logic extends BaseLogic {

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
        static final String SQL_ID_FIND_PD_CLASSES_BY_CODES = "findPdClasses";
        static final String SQL_ID_FIND_PD_MACHINING_BY_CODES = "findPdMachining";
        static final String SQL_ID_FIND_PD_BREED_BY_CODES = "findPdBreed";
        static final String SQL_ID_FIND_PD_FEATURE_BY_CODES = "findPdFeature";
        static final String SQL_ID_FIND_PD_WEIGHT_BY_CODES = "findPdWeight";
        static final String SQL_ID_FIND_PD_GRADE_BY_CODES = "findGrade";
    }

    /**
     * 查询产品类别信息
     *
     * @param list 参数：类别编码
     * @return 类别
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdClassesByCodes(List<IPD141146RsParam> list) {
        Map<String,Object> param = new HashMap<>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_CLASSES_BY_CODES);
    }

    /**
     * 查询产品二级分类信息
     *
     * @param list 参数：类别编码，二级分类编码
     * @return 产品二级分类
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdMachiningByCodes(List<IPD141146RsParam> list) {
        Map<String,Object> param = new HashMap<>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_MACHINING_BY_CODES);
    }

    /**
     * 查询产品品种信息
     *
     * @param list 参数
     * @return 品种
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdBreedByCodes(List<IPD141146RsParam> list) {
        Map<String,Object> param = new HashMap<>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_BREED_BY_CODES);
    }

    /**
     * 查询产品特征信息
     *
     * @param list 参数
     * @return 特征
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdFeatureByCodes(List<IPD141146RsParam> list) {
        Map<String,Object> param = new HashMap<>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_FEATURE_BY_CODES);
    }

    /**
     * 查询产品净重信息
     *
     * @param list 参数
     * @return 净重
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdWeightByCodes(List<IPD141146RsParam> list) {
        Map<String,Object> param = new HashMap<>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_WEIGHT_BY_CODES);
    }


    /**
     * 查询产品等级信息
     *
     * @param list 参数
     * @return 类别
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<IPD141146RsResult> findPdGradeByCodes(List<IPD141146RsParam> list) {
        Map<String,Object> param = new HashMap<>();
        param.put("list",list);
        return super.findList(param, SqlId.SQL_ID_FIND_PD_GRADE_BY_CODES);
    }


}
