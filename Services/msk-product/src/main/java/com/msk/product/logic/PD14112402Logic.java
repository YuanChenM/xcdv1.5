package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141103Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 三级类别操作
 * PD14112402Logic
 *
 * @author xhy
 */
@Service
public class PD14112402Logic extends BaseLogic {

    /**
     * Add: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start
     */
    @Autowired
    private PD141124Logic pd141124Logic;
    /**
     * Add: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End
     */
    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_BREED_EXIST = "findBreedExit";
        static final String SQL_ID_FIND_BREED_MAXID = "findBreedMaxId";
        static final String SQL_ID_SAVE_BREED = "saveBreed";
        static final String SQL_ID_FIND_STAND_EXIST = "findStandExist";
        static final String SQL_ID_SAVE_STAND = "saveStand";
        static final String SQL_ID_FIND_CLASSESTREE_EXIST = "findClassesTreeExist";
        static final String SQL_ID_FIND_PD_CLASSESTREE_EXIST = "findClassesByParentCode";
        static final String SQL_ID_MODIFY_BREED = "modifyBreedName";
        static final String SQL_ID_FIND_LIST_PD_BREED = "findListParentNameIsBreedName";
        static final String SQL_ID_MODIFY_PARENT_NAME = "modifyParentName";

        //删除操作
        static final String SQL_ID_REMOVE_PD_BREED = "removePdBreed";
        static final String SQL_ID_REMOVE_PD_STANDARD = "removePdStandard";

        static final String SQL_ID_REMOVE_PD_CLASSESTREE_MAT = "removepdClassestreeMat";
        static final String SQL_ID_REMOVE_PD_ORG_STD = "removeOrgStd";
        static final String SQL_ID_REMOVE_PD_FED_STD = "removeFedStd";
        static final String SQL_ID_REMOVE_PD_MCT_STD = "removeMctStd";
        static final String SQL_ID_REMOVE_PD_TNC_STD = "removeTncStd";
        static final String SQL_ID_REMOVE_PD_GNQ_STD = "removeGnqStd";
        static final String SQL_ID_REMOVE_PD_SFT_STD = "removeSftStd";
        static final String SQL_ID_REMOVE_PD_TSP_STD = "removeTspStd";


    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 三级类别保存操作
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int saveClassesTree(PD141103Param bean, BaseParam param) {
        /*获取最大breedcode  插入产品品种信息 start*/
        param.setFilter("classesCode", bean.getClassestreeCode1());
        param.setFilter("machiningCode", bean.getClassestreeCode2());
        param.setFilter("breedName", bean.getClassestreeName3());
        //查询数据是否存在
        int breedExist = super.getCount(SqlId.SQL_ID_FIND_BREED_EXIST, param);
        if (breedExist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条产品品种数据已经存在,请修改后重新添加!");
        }
        //获取breed最大id
        PdBreed breed = super.findOne(SqlId.SQL_ID_FIND_BREED_MAXID, param);
        Integer BreedMaxCode = Integer.parseInt(breed.getBreedCode());
        if (BreedMaxCode >= NumberConst.IntDef.INT_NINETYNINE) {
            throw new BusinessException("产品品种已达到最大限度，请联系管理员！");
        }
        breed.setBreedCode(String.format("%02d", BreedMaxCode));
        breed.setClassesCode(bean.getClassestreeCode1());
        breed.setMachiningCode(bean.getClassestreeCode2());
        breed.setBreedName(bean.getClassestreeName3());
        breed.setClassestreeCode(bean.getClassestreeCode1() + bean.getClassestreeCode2() + breed.getBreedCode());
        breed.setUpdId(param.getUpdId());
        breed.setCrtId(param.getCrtId());
         /*获取最大breedcode  插入产品品种信息 end*/

        bean.setClassestreeCode3(String.format("%02d", BreedMaxCode));

        /*添加产品标准表中数据 start*/
        param.setFilter("classestreeCode", breed.getClassestreeCode());
        int existStand = super.getCount(SqlId.SQL_ID_FIND_STAND_EXIST, param);
        if (existStand > NumberConst.IntDef.INT_ZERO) {
            new BusinessException("产品标准已经存在，请修改后添加！");
        }
        /*获取产品标准表最大id*/
        Long maxIdStand = commonLogic.maxId("PD_STANDARD", "STANDARD_ID");
        //if (maxIdStand > NumberConst.IntDef.INT_NINE_NINE_NINE_FOR_SQL_IN_LIMIT) {
        //    new BusinessException("产品标准已达到最大类别，请联系管理员！");
        //}
        PdStandard pdStandard = new PdStandard();
        pdStandard.setStandardId(maxIdStand);
        pdStandard.setClassesCode(bean.getClassestreeCode1());
        pdStandard.setBreedCode(breed.getBreedCode());
        pdStandard.setMachiningCode(breed.getMachiningCode());
        pdStandard.setClassestreeCode(breed.getClassestreeCode());
        pdStandard.setUpdId(param.getUpdId());
        pdStandard.setCrtId(param.getCrtId());
        /**Add: 横展开添加共通设置 2016/09/26   BY  任强  Start */
        pdStandard.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/26   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        pdStandard.setCrtTime(new Date());
        pdStandard.setUpdTime(new Date());
        pdStandard.setActTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        /*   产品标准操作  end*/

        /*产品树形表操作 start*/
        int existTree = super.getCount(SqlId.SQL_ID_FIND_CLASSESTREE_EXIST, param);
        if (existTree > NumberConst.IntDef.INT_ZERO) {
            new BusinessException("产品分类目录数据已经存在，请修改后添加！");
        }
        /*获取产品分类目录中最大id*/
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
        Long maxIdTree = pd141124Logic.getMaxPdClassesTreeId(param);//commonLogic.maxId("PD_CLASSESTREE", "CLASSESTREE_ID");
       /* if (maxIdTree > NumberConst.IntDef.INT_NINE_NINE_NINE_FOR_SQL_IN_LIMIT)
            new BusinessException("产品分类目录已达到最大类别，请联系管理员！");*/
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
        PdClassestree treeBean = new PdClassestree();
        treeBean.setClassestreeId(maxIdTree);
        treeBean.setClassestreeCode(pdStandard.getClassestreeCode());
        treeBean.setLevelCode(pdStandard.getBreedCode());
        treeBean.setLevelName(breed.getBreedName());
        treeBean.setParentCode(bean.getClassestreeCode1() + bean.getClassestreeCode2());
        treeBean.setParentName(bean.getClassestreeName2());
        treeBean.setTreeLevel(NumberConst.IntDef.INT_THREE);
        treeBean.setUpdId(param.getUpdId());
        treeBean.setCrtId(param.getCrtId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  Start */
        pdStandard.setActId(param.getActId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        treeBean.setActTime(new Date());
        treeBean.setUpdTime(new Date());
        treeBean.setCrtTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        /*产品树形表操作 end*/
        super.save(SqlId.SQL_ID_SAVE_BREED, breed);
        super.save(SqlId.SQL_ID_SAVE_STAND, pdStandard);
        return super.save(treeBean);
    }

    /**
     * 三级类别修改操作 产品品种修改
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int modifyClassesTreeLevel2(PD141103Param bean, BaseParam param) {
        /*产品分类目录修改 start*/
        param.setFilter("levelName", bean.getClassestreeName3());
        param.setFilter("parentCode", bean.getClassestreeCode1() + bean.getClassestreeCode2());
        int exist = super.getCount(SqlId.SQL_ID_FIND_PD_CLASSESTREE_EXIST, param);
        if (exist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条数据已经存在,请修改!");
        }
        String ClassestreeCode = bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3();
        PdClassestree tree = new PdClassestree();
        tree.setLevelName(bean.getClassestreeName3());
        tree.setClassestreeCode(ClassestreeCode);
        tree.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        tree.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
       /* 产品分类目录修改 end*/

        /*产品品种修改 start*/
        PdBreed breed = new PdBreed();
        breed.setBreedName(tree.getLevelName());
        breed.setClassestreeCode(ClassestreeCode);
        breed.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        breed.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        //查询当前数据是否已经存在
        param.setFilter("classesCode", bean.getClassestreeCode1());
        param.setFilter("machiningCode", bean.getClassestreeCode2());
        param.setFilter("breedName", bean.getClassestreeName3());
        int breedExist = super.getCount(SqlId.SQL_ID_FIND_BREED_EXIST, param);
        if (breedExist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条产品品种数据已经存在,请修改后重新添加!");
        }
        /*产品品种修改 end*/

        super.modify(SqlId.SQL_ID_MODIFY_BREED, breed);
        int modifyOk = super.modify(tree);

         /* 修改父类为breedName的所有节点的parentName */
        param.setFilter("parentBreed", ClassestreeCode);
        List<PdClassestree> breedList = super.findList(SqlId.SQL_ID_FIND_LIST_PD_BREED, param);
        if (breedList != null) {
            for (PdClassestree treeChild : breedList) {
                treeChild.setParentName(breed.getBreedName());
                treeChild.setUpdId(param.getUpdId());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                treeChild.setUpdTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                super.modify(SqlId.SQL_ID_MODIFY_PARENT_NAME, treeChild);
            }
        }
        if (modifyOk < NumberConst.IntDef.INT_ONE) {
            throw new BusinessException("数据修改失败,请联系管理员!");
        }
        return modifyOk;
    }

    /**
     * 三级类别删除操作 同时删除3张表中数据
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int removeBreed(PD141103Param bean) {
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", bean.getClassestreeCode1() + bean.getClassestreeCode2() + bean.getClassestreeCode3());
        List<PdClassestree> listTree = super.findList(param);
        if (listTree.size() > NumberConst.IntDef.INT_ZERO) {
            //该节点有存在未删除的子节点
            return NumberConst.IntDef.INT_TWO;
        }
        //删除操作 breed表中数据 删除产品标准表中数据
        int removePdBreed = super.remove(SqlId.SQL_ID_REMOVE_PD_BREED, param);//删除产品分类表中数据
        if (removePdBreed <= NumberConst.IntDef.INT_ZERO) return removePdBreed;
        //查询产品标准id

        //删除产品包装下的所有标准档案卡 start
        super.remove(SqlId.SQL_ID_REMOVE_PD_CLASSESTREE_MAT, param);
        super.remove(SqlId.SQL_ID_REMOVE_PD_ORG_STD, param);
        super.remove(SqlId.SQL_ID_REMOVE_PD_FED_STD, param);
        super.remove(SqlId.SQL_ID_REMOVE_PD_MCT_STD, param);
        super.remove(SqlId.SQL_ID_REMOVE_PD_TNC_STD, param);
        super.remove(SqlId.SQL_ID_REMOVE_PD_GNQ_STD, param);
        super.remove(SqlId.SQL_ID_REMOVE_PD_SFT_STD, param);
        super.remove(SqlId.SQL_ID_REMOVE_PD_TSP_STD, param);
        //end

        //删除操作 breed表中数据 删除产品标准表中数据
        int removePdStandard = super.remove(SqlId.SQL_ID_REMOVE_PD_STANDARD, param);//删除产品分类表中数据
        if (removePdStandard <= NumberConst.IntDef.INT_ZERO) return removePdStandard;

        int removeOk = super.remove(param);//删除classestree表中数据
        return removeOk;
    }
}
