package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdClassestree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品品种Logic.
 *
 * @author gyh
 */
@Service
public class PD141103Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        String SQL_ID_FIND_NAME_BY_NAME = "findByName";
        String SQL_ID_FIND_MAX_CODE = "findMaxNo";
        String SQL_ID_FIND_CLASSES_BEAN = "findClassesBean";
        String SQL_ID_FIND_LEVEN2_LIST = "findLevel2List";
        String SQL_ID_FIND_PDCLASSES_TREE = "findClassesTree";
    }

    /**
     * 根据类别名称查询记录
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PdBreed findByName(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_NAME_BY_NAME, param);
    }

    /**
     * 查询品种编码最大的记录
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PdBreed findMaxCode(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_MAX_CODE, param);
    }

    /**
     * 查询一级类别名称
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PdClasses findOneClasses(BaseParam param) {
        return this.findOne(SqlId.SQL_ID_FIND_CLASSES_BEAN, param);
    }

    /**
     * 查询二级类别名称
     *
     * @param param
     * @return PdClassestree
     */
    @Transactional(readOnly = true)
    public List<PdClassestree> findClassesTreeList(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_LEVEN2_LIST, param);
    }

    /**
     * 查询层级
     *
     * @param param2
     * @return PdClassestree 对象
     */
    @Transactional(readOnly = true)
    public PdClassestree findOneTreeBean(BaseParam param2) {
        return this.findOne(SqlId.SQL_ID_FIND_PDCLASSES_TREE, param2);
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * xhy
     * @param param1
     * @return
     */
}
