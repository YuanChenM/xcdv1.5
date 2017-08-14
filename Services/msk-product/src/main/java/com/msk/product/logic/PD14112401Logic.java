package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdMachining;
import com.msk.product.bean.PD141103Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 二级类别操作
 * PD14112401Logic
 *
 * @author xhy
 */
@Service
public class PD14112401Logic extends BaseLogic {

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
        static final String SQL_ID_FIND_MAX_CODE = "findMaxCode";
        static final String SQL_ID_FIND_PD_CLASSESTREE_EXIST = "findClassesByParentCode";
        static final String SQL_ID_FIND_PD_MAC_EXIST = "findMacExist";
        static final String SQL_ID_SAVE_PD_MAC = "saveMac";
        static final String SQL_ID_MODIFY_PD_MAC = "modifyMacName";
        static final String SQL_ID_FIND_LIST_PD_MAC = "findListMac";
        static final String SQL_ID_MODIFY_TREE_PARENTNAME = "modifyParentName";

        /*删除操作*/
        String SQL_ID_REMOVE_PD_MACHINING = "removePdMachining";
        String SQL_ID_REMOVE_PD_Gnq = "removePdGnq";//删除档案卡

    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 二级类别保存操作 machining
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int saveClassesTree(PD141103Param bean, BaseParam param1) {
        //获取最大主键id
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
        Long maxId = pd141124Logic.getMaxPdClassesTreeId(param1);//commonLogic.maxId("PD_CLASSESTREE", "CLASSESTREE_ID");
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
        PdClassestree treeBean = new PdClassestree();
        treeBean.setLevelName(bean.getClassestreeName2());
        treeBean.setClassestreeId(maxId);
        treeBean.setParentCode(bean.getClassestreeCode1());
        treeBean.setParentName(bean.getClassestreeName1());
        treeBean.setTreeLevel(NumberConst.IntDef.INT_TWO);
        treeBean.setUpdId(param1.getUpdId());
        treeBean.setCrtId(param1.getCrtId());

        param1.setFilter("levelName", treeBean.getLevelName());
        param1.setFilter("parentCode", treeBean.getParentCode());
        int exist = super.getCount(SqlId.SQL_ID_FIND_PD_CLASSESTREE_EXIST, param1);
        if (exist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条数据已经存在,请修改后添加!");
        }
        //向machining表中插入数据

        int existMachining = super.getCount(SqlId.SQL_ID_FIND_PD_MAC_EXIST, param1);
        if (existMachining > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("加工类型数据已经存在,请修改后添加!");
        }

        param1.setFilter("classesCode", bean.getClassestreeCode1());
        param1.setFilter("machiningName", bean.getClassestreeName2());
        //获取最大编码macCode
        PdMachining mac = super.findOne(SqlId.SQL_ID_FIND_MAX_CODE, param1);
        /** Modfiy: Bug #2520 : 二级分类数量超过9个仍能新增   20160907  BY  杨春艳  Start */
        if (Integer.valueOf(mac.getMachiningCode()) >= NumberConst.IntDef.INT_TEN) {
            throw new BusinessException("二级分类新增已超过最大数,请联系管理员!");
        }
        /** Modfiy: Bug #2520 : 二级分类数量超过9个仍能新增  20160907   BY  杨春艳  End */
        //获取最大主键id
        mac.setMachiningName(bean.getClassestreeName2());
        mac.setClassesCode(bean.getClassestreeCode1());
        mac.setClassestreeCode(bean.getClassestreeCode1() + mac.getMachiningCode());
        mac.setUpdId(param1.getUpdId());
        mac.setCrtId(param1.getCrtId());

        bean.setClassestreeCode2(mac.getMachiningCode());

        super.save(SqlId.SQL_ID_SAVE_PD_MAC, mac);
        treeBean.setLevelCode(mac.getMachiningCode());
        treeBean.setClassestreeCode(bean.getClassestreeCode1() + treeBean.getLevelCode());
        treeBean.setUpdId(param1.getUpdId());
        treeBean.setCrtId(param1.getCrtId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  Start */
        treeBean.setActId(param1.getActId());
        /**Add: 横展开添加共通设置 2016/09/21   BY  任强  End */
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        treeBean.setCrtTime(new Date());
        treeBean.setUpdTime(new Date());
        treeBean.setActTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        return super.save(treeBean);
    }

    /**
     * 二级类别修改操作 mac 同时修改产品加工表中数据
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int modifyClassesTreeLevel2(PD141103Param bean, BaseParam param) {
        param.setFilter("levelName", bean.getClassestreeName2());
        param.setFilter("parentCode", bean.getClassestreeCode1());
        int exist = super.getCount(SqlId.SQL_ID_FIND_PD_CLASSESTREE_EXIST, param);
        if (exist > NumberConst.IntDef.INT_ZERO) {
            throw new BusinessException("该条数据已经存在,请修改!");
        }
        PdClassestree tree = new PdClassestree();
        tree.setLevelName(bean.getClassestreeName2());
        tree.setClassestreeCode(bean.getClassestreeCode1() + bean.getClassestreeCode2());
        tree.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        tree.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        //修改machining产品加工表中数据
        PdMachining machiningref = new PdMachining();
        machiningref.setMachiningName(bean.getClassestreeName2());
        machiningref.setMachiningCode(bean.getClassestreeCode2());
        machiningref.setClassesCode(bean.getClassestreeCode1());
        machiningref.setUpdId(param.getUpdId());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        machiningref.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        super.modify(SqlId.SQL_ID_MODIFY_PD_MAC, machiningref);
        int modifyOk = super.modify(tree);
        //修改所有产品分类目录parentName为MachiningName
        param.setFilter("parentMac", bean.getClassestreeCode1() + bean.getClassestreeCode2());
        List<PdClassestree> macList = super.findList(SqlId.SQL_ID_FIND_LIST_PD_MAC, param);
        if (macList != null) {
            for (PdClassestree treeChild : macList) {
                treeChild.setParentName(machiningref.getMachiningName());
                treeChild.setUpdId(param.getUpdId());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                treeChild.setUpdTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                super.modify(SqlId.SQL_ID_MODIFY_TREE_PARENTNAME, treeChild);
            }
        }

        if (modifyOk < NumberConst.IntDef.INT_ONE) {
            throw new BusinessException("数据修改失败,请联系管理员!");
        }
        return modifyOk;
    }


    /**
     * 二级类别删除操作 同时删除3张表中数据
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int removeMachining(PD141103Param bean) {
        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", bean.getClassestreeCode1() + bean.getClassestreeCode2());
        List<PdClassestree> listTree = super.findList(param);
        if (listTree.size() > NumberConst.IntDef.INT_ZERO) {
            //该节点有存在未删除的子节点
            return NumberConst.IntDef.INT_TWO;
        }
        //删除操作
        int removePdMachining = super.remove(SqlId.SQL_ID_REMOVE_PD_MACHINING, param);//删除产品分类表中数据
        if (removePdMachining <= NumberConst.IntDef.INT_ZERO) return removePdMachining;
        //删除原种标准
        super.remove(SqlId.SQL_ID_REMOVE_PD_Gnq, param);
        int removeOk = super.remove(param);//删除classestree表中数据
        return removeOk;
    }
}
