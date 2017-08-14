package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdStandard;
import com.msk.core.entity.PdWeight;
import com.msk.product.bean.PD141103Param;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 五级类别操作
 * PD14112404Logic
 *
 * @author xhy
 */
@Service
public class PD14112404Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;

    public static final String KG="kg";

    /** Add: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
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
        /* 产品净重新增*/
        static final String SQL_ID_FIND_WEIGHT_EXIST = "findWeightExist";//数据是否存在
        static final String SQL_ID_FIND_WEIGHT_EXIST_TWO = "findWeightExistTwo";
        static final String SQL_ID_FIND_WEIGHT_MAXID = "findWeightMaxId";//获取产品净重最大id
        static final String SQL_ID_FIND_PD_STAND_EXIST = "findPdStand";
        static final String SQL_ID_FIND_CLASSESTREE_EXIST = "findClassesTreeExist";
        static final String SQL_ID_SAVE_WEIGHT = "saveWeight"; //保存产品净重数据
        static final String SQL_ID_SAVE_PD_STAND = "savePdStand";//保存产品标准数据
        /*产品净重修改 */

        static final String SQL_ID_FIND_LIST_PARENTNAME_IS_WEIGHTNAME = "findListParentNameIsWeightName";//保存产品标准数据
        static final String SQL_ID_MODIFY_WEIGHTNAME = "modifyWeightName";
        static final String SQL_ID_MODIFY_CLASSESTREE_PARENTNAME_IS_WEIGHT_NAME = "modifyParentNameIsWeightName";

        //删除操作
        static final String SQL_ID_REMOVE_PD_WEIGHT = "removePdWeight";
        static final String SQL_ID_REMOVE_PD_STANDARD = "removePdStandard";


        static final String SQL_ID_FIND_PD_WEIGHT_LIST = "findPdListWeightL";

    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 五级类别保存操作 产品净重新增
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int saveClassesTree(PD141103Param bean,BaseParam param) {


        /*产品净重新增数据 start*/
         /*查询产品净重数据是否存在  */
        param.setFilter("classesCode", bean.getClassestreeCode1());
        param.setFilter("machiningCode", bean.getClassestreeCode2());
        param.setFilter("breedCode", bean.getClassestreeCode3());

        if(StringUtils.isNotBlank(bean.getClassestreeName5())){
            param.setFilter("weightName", bean.getClassestreeName5());
        } else if(StringUtils.isNotBlank(bean.getWeightCode()) && !bean.getWeightCode().equals(String.valueOf(NumberConst.IntDef.INT_ZERO))){
            param.setFilter("featureCode", bean.getClassestreeCode4());
            param.setFilter("weightName",bean.getWeightName());
            param.setFilter("weightCode",bean.getWeightCode());
        }else if(StringUtils.isNotBlank(bean.getCopyCodeId())&&StringUtils.isNotBlank(bean.getCopyCodeName())){
            param.setFilter("weightCode",bean.getCopyCodeId());
            int weightExist = super.getCount(SqlId.SQL_ID_FIND_WEIGHT_EXIST, param);
            if (weightExist > NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("该条产品特征数据已经存在,请修改后重新添加!(三级查询存在)");
            }
            param.setFilter("weightVal",bean.getCopyCodeName());
            param.setFilter("weightCode",null);
            int weightExist2 = super.getCount(SqlId.SQL_ID_FIND_WEIGHT_EXIST, param);
            if (weightExist2 > NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("该条产品特征数据已经存在,请修改后重新添加!(三级查询存在)");
            }
            param.setFilter("weightCode",bean.getCopyCodeId());
        }
        //查询数据是否存在
        int weightExist = super.getCount(SqlId.SQL_ID_FIND_WEIGHT_EXIST, param);
        if (weightExist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条产品特征数据已经存在,请修改后重新添加!(三级查询存在)");
        }
        param.setFilter("featureCode", bean.getClassestreeCode4());
        //获取产品净重最大id
        PdWeight weightMaxId = super.findOne(SqlId.SQL_ID_FIND_WEIGHT_MAXID, param);
        String maxString =weightMaxId.getWeightCode();
        if(StringUtils.isNotBlank(bean.getWeightCode())&&!bean.getWeightCode().equals(String.valueOf(NumberConst.IntDef.INT_ZERO))){
            maxString=bean.getWeightCode();
        }else if(StringUtils.isNotBlank(bean.getCopyCodeId())&&!bean.getCopyCodeId().equals(String.valueOf(NumberConst.IntDef.INT_ZERO))){
            maxString=bean.getCopyCodeId();
        }
        Integer weiMaxCode = Integer.parseInt(maxString);
        if (weiMaxCode > NumberConst.IntDef.INT_NINETYNINE) {
            throw new BusinessException("产品净重已达到最大限度，请联系管理员！");
        }
        weightMaxId.setWeightCode(String.format("%02d", weiMaxCode));
        weightMaxId.setClassesCode(bean.getClassestreeCode1());
        weightMaxId.setMachiningCode(bean.getClassestreeCode2());
        weightMaxId.setBreedCode(bean.getClassestreeCode3());
        weightMaxId.setFeatureCode(bean.getClassestreeCode4());
        bean.setClassestreeCode5(String.format("%02d", weiMaxCode));

        if(StringUtils.isNotBlank(bean.getCopyCodeId()) && !bean.getCopyCodeId().equals(String.valueOf(NumberConst.IntDef.INT_ZERO))){
            weightMaxId.setWeightName(bean.getCopyCodeName());
            if(StringUtils.isNotBlank(bean.getCopyCodeVal())){
                weightMaxId.setWeightVal(new BigDecimal(bean.getCopyCodeVal()));
            }else{
                weightMaxId.setWeightVal(null);
            }

        }else if(StringUtils.isNotBlank(bean.getWeightCode()) && !bean.getWeightCode().equals(String.valueOf(NumberConst.IntDef.INT_ZERO))){
            if(bean.getWeightName().matches("\\d+(.\\d+)?")){
                    weightMaxId.setWeightName(bean.getWeightName() + KG);
                    weightMaxId.setWeightVal(new BigDecimal(bean.getWeightName()));
            }else {
                weightMaxId.setWeightName(bean.getWeightName());
                weightMaxId.setWeightVal(new BigDecimal(bean.getClassestreeName5()));

            }
        }else {
            weightMaxId.setWeightName(bean.getClassestreeName5() + KG);
            weightMaxId.setWeightVal(new BigDecimal(bean.getClassestreeName5()));
        }
        weightMaxId.setClassestreeCode(bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3() + bean.getClassestreeCode4() + weightMaxId.getWeightCode());
        weightMaxId.setCrtId(param.getCrtId());
        weightMaxId.setUpdId(param.getUpdId());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        weightMaxId.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        weightMaxId.setUpdTime(new Date());
        weightMaxId.setActTime(new Date());
        weightMaxId.setCrtTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
       /* 产品净重数据新增  end */

         /* 产品标准数据新增 start*/
        param.setFilter("classestreeCode", weightMaxId.getClassestreeCode());
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
        pdStandard.setBreedCode(weightMaxId.getBreedCode());
        pdStandard.setMachiningCode(weightMaxId.getMachiningCode());
        pdStandard.setFeatureCode(weightMaxId.getFeatureCode());
        pdStandard.setWeightCode(weightMaxId.getWeightCode());
        pdStandard.setClassestreeCode(weightMaxId.getClassestreeCode());
        pdStandard.setCrtId(param.getCrtId());
        pdStandard.setUpdId(param.getUpdId());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        pdStandard.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        pdStandard.setActTime(new Date());
        pdStandard.setUpdTime(new Date());
        pdStandard.setCrtTime(new Date());
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
        treeBean.setLevelCode(weightMaxId.getWeightCode());
        treeBean.setLevelName(weightMaxId.getWeightName());
        treeBean.setParentCode(bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3() + bean.getClassestreeCode4());
        treeBean.setParentName(bean.getClassestreeName4());
        treeBean.setTreeLevel(NumberConst.IntDef.INT_FIVE);
        treeBean.setCrtId(param.getCrtId());
        treeBean.setUpdId(param.getUpdId());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        treeBean.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        treeBean.setUpdTime(new Date());
        treeBean.setCrtTime(new Date());
        treeBean.setActTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End

        super.save(SqlId.SQL_ID_SAVE_WEIGHT, weightMaxId);
        super.save(SqlId.SQL_ID_SAVE_PD_STAND, pdStandard);
        return super.save(treeBean);
    }

  /**
     * 五级类别修改操作  产品净重
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int modifyClassesTreeLevel5(PD141103Param bean,BaseParam param) {
        /*修改产品净重数据*/
        if(bean.getClassestreeName5().matches("\\d+(.\\d+)?")){
            param.setFilter("levelName",bean.getClassestreeName5()+KG);
        }else{
            param.setFilter("levelName",bean.getClassestreeName5());
        }

        param.setFilter("parentCode",bean.getClassestreeCode1()+bean.getClassestreeCode2()+bean.getClassestreeCode3()+bean.getClassestreeCode4());
        int exist = super.getCount(SqlId.SQL_ID_FIND_PD_CLASSESTREE_EXIST, param);
        if (exist > NumberConst.IntDef.INT_ZERO){
            throw new BusinessException("该条数据已经存在,请修改!");
        }
        String  classestreeCode=bean.getClassestreeCode1()+bean.getClassestreeCode2()+bean.getClassestreeCode3()+bean.getClassestreeCode4()+bean.getClassestreeCode5();
        PdClassestree tree = new PdClassestree();
        if(bean.getClassestreeName5().matches("\\d+(.\\d+)?")){
            tree.setLevelName(bean.getClassestreeName5() + KG);
        }else{
            tree.setLevelName(bean.getClassestreeName5());
        }

        tree.setClassestreeCode(classestreeCode);
        tree.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        tree.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End

        //修改净重 名称 start
        param.setFilter("classesCode", bean.getClassestreeCode1());
        param.setFilter("machiningCode", bean.getClassestreeCode2());
        param.setFilter("breedCode", bean.getClassestreeCode3());

        if(bean.getClassestreeName5().matches("\\d+(.\\d+)?")){
            param.setFilter("weightName",bean.getClassestreeName5()+KG);
        }else{
            param.setFilter("weightName",bean.getClassestreeName5());
        }
        //查询数据是否存在 start
        int weightExist = super.getCount(SqlId.SQL_ID_FIND_WEIGHT_EXIST, param);
        if(weightExist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条产品净重数据已经存在,请修改后重新添加!");
        }
        param.setFilter("featureCode", bean.getClassestreeCode4());
        PdWeight weight = new PdWeight();
        weight.setWeightName(tree.getLevelName());
        if(bean.getClassestreeName5().matches("\\d+(.\\d+)?")){
            weight.setWeightVal(new BigDecimal(bean.getClassestreeName5()));
        }else{
            weight.setWeightVal(null);
        }
        weight.setClassestreeCode(classestreeCode);
        weight.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        weight.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Emd
        //end
        super.modify(SqlId.SQL_ID_MODIFY_WEIGHTNAME,weight);
        int modifyOk = super.modify(tree);
         /* 修改父类为weightName的所有节点的parentName */
        param.setFilter("parentWeight",classestreeCode);
        List<PdClassestree> weightList = super.findList(SqlId.SQL_ID_FIND_LIST_PARENTNAME_IS_WEIGHTNAME, param);
        if (weightList != null) {
            for (PdClassestree treeChild : weightList) {
                treeChild.setParentName(weight.getWeightName());
                treeChild.setUpdId(param.getUpdId());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                treeChild.setUpdTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                super.modify(SqlId.SQL_ID_MODIFY_CLASSESTREE_PARENTNAME_IS_WEIGHT_NAME, treeChild);
            }
        }
        if(modifyOk < NumberConst.IntDef.INT_ONE){
            throw new BusinessException("数据修改失败,请联系管理员!");
        }
        return modifyOk;
    }

    /**
     * 五级类别删除操作 同时删除3张表中数据
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int removeWeight(PD141103Param bean) {
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3() + bean.getClassestreeCode4()+bean.getClassestreeCode5());
        List<PdClassestree> listTree = super.findList(param);
        if (listTree.size() > NumberConst.IntDef.INT_ZERO) {
            //该节点有存在未删除的子节点
            return NumberConst.IntDef.INT_TWO;
        }
        //删除操作 feature表中数据 删除产品标准表中数据
        int removePdWeight = super.remove(SqlId.SQL_ID_REMOVE_PD_WEIGHT, param);//删除产品分类表中数据
        if (removePdWeight <= NumberConst.IntDef.INT_ZERO) return removePdWeight;

        //删除操作 breed表中数据 删除产品标准表中数据
        int removePdStandard = super.remove(SqlId.SQL_ID_REMOVE_PD_STANDARD, param);//删除产品分类表中数据
        if (removePdStandard <= NumberConst.IntDef.INT_ZERO) return removePdStandard;

        int removeOk = super.remove(param);//删除classestree表中数据
        return removeOk;
    }

    /**查询下拉框
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdWeight> findListWeights(PD141103Param bean) {
        BaseParam param = new BaseParam();
        List<PdWeight> list = new ArrayList<>();
        String classesCode = bean.getClassestreeCode1();
        String machiningCode = bean.getClassestreeCode2();
        String breedCode = bean.getClassestreeCode3();
        if(StringUtils.isNoneEmpty(bean.getClassestreeCode3()) && bean.getClassestreeCode3().length() > NumberConst.IntDef.INT_TWO){
            classesCode = bean.getClassestreeCode3().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            machiningCode = bean.getClassestreeCode3().substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            breedCode = bean.getClassestreeCode3().substring(NumberConst.IntDef.INT_THREE);
        }
        param.setFilter("classestreeCode1",classesCode);
        param.setFilter("classestreeCode2",machiningCode);
        param.setFilter("classestreeCode3",breedCode);
        list = super.findList(SqlId.SQL_ID_FIND_PD_WEIGHT_LIST,param);
           /* for(PdWeight beans:list){
         *//*   if(beans.getWeightName().indexOf(".") > 0){
                //正则表达
                beans.setWeightName(beans.getWeightName().replaceAll("0+?$", ""));//去掉后面无用的零
                beans.setWeightName(beans.getWeightName().replaceAll("[.]$", ""));//如小数点后面全是零则去掉小数点
            }*//*
                if(beans.getWeightName()!=null){
                    if(beans.getWeightName().endsWith(PD14112404Logic.KG)) {
                        beans.setWeightName(beans.getWeightName().replaceAll((PD14112404Logic.KG), ""));
                    }
                }
            }*/
        return list;
    }

    /**
     * 验证数据
     * @param bean
     * @return
     */
    @Transactional(readOnly = true)
    public int checkData(PD141103Param bean) {
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", bean.getClassestreeCode1());
        param.setFilter("machiningCode", bean.getClassestreeCode2());
        param.setFilter("breedCode", bean.getClassestreeCode3());
        param.setFilter("weightName",bean.getClassestreeName5().replace(KG,""));
        int weightExist = super.getCount(SqlId.SQL_ID_FIND_WEIGHT_EXIST_TWO, param);
        return weightExist;
    }

}
