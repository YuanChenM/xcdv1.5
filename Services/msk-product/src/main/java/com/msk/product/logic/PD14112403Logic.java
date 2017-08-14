package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdFeature;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141103Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 四级类别操作
 * PD14112403Logic
 *
 * @author xhy
 */
@Service
public class PD14112403Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;
    /**Add: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
    @Autowired
    private PD141124Logic pd141124Logic;
    /** Add: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */

    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_PD_CLASSESTREE_EXIST = "findClassesByParentCode";

        /*新增 */
        static final String SQL_ID_FIND_FEA_EXIST = "findFeaExist";//数据是否存在
        static final String SQL_ID_FIND_FEA_MAXID = "findFeaMaxId";//获取产品特征最大id
        static final String SQL_ID_FIND_PD_STAND_EXIST = "findPdStand";
        static final String SQL_ID_FIND_CLASSESTREE_EXIST = "findClassesTreeExist";
        static final String SQL_ID_SAVE_FEA = "saveFea"; //保存产品特征数据
        static final String SQL_ID_SAVE_PD_STAND = "savePdStand";//保存产品标准数据
        /* 修改操作*/
        static final String SQL_ID_MODIFY_FEATURE = "modifyFeature";//保存产品标准数据
        static final String SQL_ID_FIND_LIST_PARENTNAME_IS_FEATURENAME = "findListFeatureTree";//获取所有父类名称为feature的tree对象
        static final String SQL_ID_MODIFY_CLASSESTREE_PARENTNAME_IS_FEATURE_NAME = "modifyClassesParentName";//获取所有父类名称为feature的tree对象

        //删除操作
        static final String SQL_ID_REMOVE_PD_FEATURE = "removePdFeature";
        static final String SQL_ID_REMOVE_PD_STANDARD = "removePdStandard";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 四级类别保存操作 xhy
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int saveClassesTree(PD141103Param bean, BaseParam param) {

        /*产品特征新增数据 start*/
         /*查询产品特征数据是否存在  */
        param.setFilter("classesCode", bean.getClassestreeCode1());
        param.setFilter("machiningCode", bean.getClassestreeCode2());
        param.setFilter("breedCode", bean.getClassestreeCode3());
        param.setFilter("featureName", bean.getClassestreeName4());
        //查询数据是否存在
        int feaExist = super.getCount(SqlId.SQL_ID_FIND_FEA_EXIST, param);
        if (feaExist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条产品特征数据已经存在,请修改后重新添加!");
        }

        //获取产品特征最大id
        PdFeature feaMaxId = super.findOne(SqlId.SQL_ID_FIND_FEA_MAXID, param);

        Integer feaMaxCode = Integer.parseInt(feaMaxId.getFeatureCode());
        if (feaMaxCode > NumberConst.IntDef.INT_NINETYNINE) {
            throw new BusinessException("产品特征已达到最大限度，请联系管理员！");
        }
        feaMaxId.setFeatureCode(String.format("%02d", feaMaxCode));
        feaMaxId.setFeatureName(bean.getClassestreeName4());
        feaMaxId.setClassesCode(bean.getClassestreeCode1());
        feaMaxId.setMachiningCode(bean.getClassestreeCode2());
        feaMaxId.setBreedCode(bean.getClassestreeCode3());
        feaMaxId.setClassestreeCode(bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3() + feaMaxId.getFeatureCode());
        feaMaxId.setUpdId(param.getUpdId());
        feaMaxId.setCrtId(param.getCrtId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  Start */
        feaMaxId.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        feaMaxId.setActTime(new Date());
        feaMaxId.setCrtTime(new Date());
        feaMaxId.setUpdTime(new Date());
        bean.setClassestreeCode4(String.format("%02d", feaMaxCode));
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
       /* 产品特征数据新增  end */

       /* 产品标准数据新增 start*/
        param.setFilter("classestreeCode", feaMaxId.getClassestreeCode());
        int existStand = super.getCount(SqlId.SQL_ID_FIND_PD_STAND_EXIST, param);
        if (existStand > NumberConst.IntDef.INT_ZERO) {
            new BusinessException("产品标准已经存在，请修改后添加！");
        }
        /*获取产品标准表最大id*/
        Long maxIdStand = commonLogic.maxId("PD_STANDARD", "STANDARD_ID");
        //if (maxIdStand > NumberConst.IntDef.INT_NINE_NINE_NINE_FOR_SQL_IN_LIMIT)
        //    new BusinessException("产品标准已达到最大类别，请联系管理员！");
        PdStandard pdStandard = new PdStandard();
        pdStandard.setStandardId(maxIdStand);
        pdStandard.setClassesCode(bean.getClassestreeCode1());
        pdStandard.setBreedCode(feaMaxId.getBreedCode());
        pdStandard.setMachiningCode(feaMaxId.getMachiningCode());
        pdStandard.setFeatureCode(feaMaxId.getFeatureCode());
        pdStandard.setClassestreeCode(feaMaxId.getClassestreeCode());
        pdStandard.setUpdId(param.getUpdId());
        pdStandard.setCrtId(param.getCrtId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  Start */
        pdStandard.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        pdStandard.setActTime(new Date());
        pdStandard.setCrtTime(new Date());
        pdStandard.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        /*产品标准数据 end*/

          /*产品树形表操作 start*/
        int existTree = super.getCount(SqlId.SQL_ID_FIND_CLASSESTREE_EXIST, param);
        if (existTree > NumberConst.IntDef.INT_ZERO) {
            new BusinessException("产品分类目录数据已经存在，请修改后添加！");
        }
        /*获取产品分类目录中最大id*/
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
        Long maxIdTree = pd141124Logic.getMaxPdClassesTreeId(param);//commonLogic.maxId("PD_CLASSESTREE", "CLASSESTREE_ID");
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
        PdClassestree treeBean = new PdClassestree();
        treeBean.setClassestreeId(maxIdTree);
        treeBean.setClassestreeCode(pdStandard.getClassestreeCode());
        treeBean.setLevelCode(pdStandard.getFeatureCode());
        treeBean.setLevelName(feaMaxId.getFeatureName());
        treeBean.setParentCode(bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3());
        treeBean.setParentName(bean.getClassestreeName3());
        treeBean.setTreeLevel(NumberConst.IntDef.INT_FOUR);
        treeBean.setUpdId(param.getUpdId());
        treeBean.setCrtId(param.getCrtId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  Start */
        treeBean.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        treeBean.setActTime(new Date());
        treeBean.setUpdTime(new Date());
        treeBean.setCrtTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End

        super.save(SqlId.SQL_ID_SAVE_FEA, feaMaxId);
        super.save(SqlId.SQL_ID_SAVE_PD_STAND, pdStandard);
        return super.save(treeBean);
    }

    /**
     * 四级类别修改操作
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int modifyClassesTreeLevel4(PD141103Param bean, BaseParam param) {

        param.setFilter("levelName", bean.getClassestreeName4());
        param.setFilter("parentCode", bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3());
        int exist = super.getCount(SqlId.SQL_ID_FIND_PD_CLASSESTREE_EXIST, param);
        if (exist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条数据已经存在,请修改!");
        }
        String classestreeCode = bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3() + bean.getClassestreeCode4();
        PdClassestree tree = new PdClassestree();
        tree.setLevelName(bean.getClassestreeName4());
        tree.setClassestreeCode(classestreeCode);
        tree.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        tree.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End

        //修改特征名称 start
        param.setFilter("classesCode", bean.getClassestreeCode1());
        param.setFilter("machiningCode", bean.getClassestreeCode2());
        param.setFilter("breedCode", bean.getClassestreeCode3());
        param.setFilter("featureName", bean.getClassestreeName4());
        //查询数据是否存在
        int feaExist = super.getCount(SqlId.SQL_ID_FIND_FEA_EXIST, param);
        if (feaExist > NumberConst.IntDef.INT_ZERO) throw new BusinessException("该条产品特征数据已经存在,请修改后重新添加!");
        PdFeature feature = new PdFeature();
        feature.setFeatureName(tree.getLevelName());
        feature.setClassestreeCode(classestreeCode);
        feature.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        feature.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        //end
         /* 修改父类为breedName的所有节点的parentName */
        param.setFilter("parentFeature", classestreeCode);
        List<PdClassestree> featureList = super.findList(SqlId.SQL_ID_FIND_LIST_PARENTNAME_IS_FEATURENAME, param);
        if (featureList != null) {
            for (PdClassestree treeChild : featureList) {
                treeChild.setParentName(feature.getFeatureName());
                treeChild.setUpdId(param.getUpdId());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                treeChild.setUpdTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                super.modify(SqlId.SQL_ID_MODIFY_CLASSESTREE_PARENTNAME_IS_FEATURE_NAME, treeChild);
            }
        }

        super.modify(SqlId.SQL_ID_MODIFY_FEATURE, feature);
        int modifyOk = super.modify(tree);
        if (modifyOk < NumberConst.IntDef.INT_ONE) {
            throw new BusinessException("数据修改失败,请联系管理员!");
        }
        return modifyOk;
    }

    /**
     * 四级类别删除操作 同时删除3张表中数据
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int removeFeature(PD141103Param bean) {
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3() + bean.getClassestreeCode4());
        List<PdClassestree> listTree = super.findList(param);
        if (listTree.size() > NumberConst.IntDef.INT_ZERO) {
            //该节点有存在未删除的子节点
            return NumberConst.IntDef.INT_TWO;
        }
        //删除操作 feature表中数据 删除产品标准表中数据
        int removePdFeature = super.remove(SqlId.SQL_ID_REMOVE_PD_FEATURE, param);//删除产品分类表中数据
        if (removePdFeature <= NumberConst.IntDef.INT_ZERO) return removePdFeature;

        //删除操作 breed表中数据 删除产品标准表中数据
        int removePdStandard = super.remove(SqlId.SQL_ID_REMOVE_PD_STANDARD, param);//删除产品分类表中数据
        if (removePdStandard <= NumberConst.IntDef.INT_ZERO) return removePdStandard;

        int removeOk = super.remove(param);//删除classestree表中数据
        return removeOk;
    }
}
