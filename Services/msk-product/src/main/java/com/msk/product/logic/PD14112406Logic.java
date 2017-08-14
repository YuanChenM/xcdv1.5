package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.product.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * PD141101Logic
 *
 * @author gyh
 */
@Service
public class PD14112406Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_FIND_CLASSES_LIST_BY_CLASSESID = "findListClassesList";
        static final String SQL_ID_FIND_LEVEN2_LIST = "findListLevel2List";
        static final String SQL_ID_FIND_LEVEN3_LIST = "findListLevel3List";
        static final String SQL_ID_FIND_LEVEN4_LIST = "findListLevel4List";
        static final String SQL_ID_FIND_LEVEN5_LIST = "findListLevel5List";
        static final String SQL_ID_FIND_LEVEN6_LIST = "findListLevel6List";
        //删除操作
        static final String SQL_ID_REMOVE_PD_CLASSES = "removePdClasses";

    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141124Bean> findClassesList(BaseParam param) {
        param.setFilter("level", String.valueOf(NumberConst.IntDef.INT_ONE));
        //classes
        List<PD141124Bean> classesList = this.findList(SqlId.SQL_ID_FIND_CLASSES_LIST_BY_CLASSESID, param);
        if (classesList != null && classesList.size() > NumberConst.IntDef.INT_ZERO) {
            for (PD141124Bean classesBean : classesList) {
                String children = classesBean.getClassesTreeCode();
                BaseParam param2 = new BaseParam();
                param2.setFilter("parentCode", children);
                String machingCode = (String)param.getFilterMap().get("machiningCode");
                if(StringUtils.hasLength(children) && StringUtils.hasLength(machingCode)){
                    param2.setFilter("classestreeCode", machingCode);
                }
                //mac
                List<PD141124Level2Bean> listLevel2 = this.findList(SqlId.SQL_ID_FIND_LEVEN2_LIST, param2);

                for (PD141124Level2Bean level2 : listLevel2) {
                    String children2 = level2.getClassesTreeCode();
                    BaseParam param3 = new BaseParam();
                    param3.setFilter("parentCode", children2);
                    String breedCode = (String)param.getFilterMap().get("breedCode");
                    if(StringUtils.hasLength(children2) && StringUtils.hasLength(breedCode)){
                        param3.setFilter("classestreeCode", breedCode);
                    }
                    //breed
                    List<PD141124Level3Bean> listLevel3 = this.findList(SqlId.SQL_ID_FIND_LEVEN3_LIST, param3);

                    for (PD141124Level3Bean level3 : listLevel3) {
                        String children3 = level3.getClassesTreeCode();
                        BaseParam param4 = new BaseParam();
                        param4.setFilter("parentCode", children3);
                        String featureCode = (String)param.getFilterMap().get("featureCode");
                        if(StringUtils.hasLength(children3) && StringUtils.hasLength(featureCode)){
                            param4.setFilter("classestreeCode", featureCode);
                        }
                        //feature
                        List<PD141124Level4Bean> listLevel4 = this.findList(SqlId.SQL_ID_FIND_LEVEN4_LIST, param4);

                        for (PD141124Level4Bean level4 : listLevel4) {
                            String children4 = level4.getClassesTreeCode();
                            BaseParam param5 = new BaseParam();
                            param5.setFilter("parentCode", children4);
                            String weightCode = (String)param.getFilterMap().get("weightCode");
                            if(StringUtils.hasLength(children4) && StringUtils.hasLength(weightCode)){
                                param5.setFilter("classestreeCode", weightCode);
                            }
                            List<PD141124Level5Bean> listLevel5 = this.findList(SqlId.SQL_ID_FIND_LEVEN5_LIST, param5);
                            //weight
                            for (PD141124Level5Bean level5 : listLevel5) {
                                String children5 = level5.getClassesTreeCode();
                                BaseParam param6 = new BaseParam();
                                param6.setFilter("parentCode", children5);
                                List<PD141124Level6Bean> listLevel6 = this.findList(SqlId.SQL_ID_FIND_LEVEN6_LIST, param6);
                                level5.setLevel6Beans(listLevel6);
                            }
                            level4.setLevel5Beans(listLevel5);
                        }
                        level3.setLevel4Beans(listLevel4);
                    }
                    level2.setLevel3Beans(listLevel3);
                }
                classesBean.setLevel2Beans(listLevel2);
            }
        }
        return classesList;
    }

    /**
     * 一级类别删除操作
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int removeClassesCode(PD141103Param bean) {
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", bean.getClassestreeCode1());
        List<PdClassestree> listTree = super.findList(param);
        if (listTree.size() > NumberConst.IntDef.INT_ZERO) {
            //该节点有存在未删除的子节点
            return NumberConst.IntDef.INT_TWO;
        }
        //删除操作
        int removeClasses = super.remove(SqlId.SQL_ID_REMOVE_PD_CLASSES, param);//删除产品分类表中数据
        if (removeClasses <= NumberConst.IntDef.INT_ZERO) return removeClasses;
        int removeOk = super.remove(param);//删除classestree表中数据
        return removeOk;
    }
}
