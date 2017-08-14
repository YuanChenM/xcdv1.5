package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdNormsStd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * PD141153Logic
 *
 * @author XHY
 */

@Service
public class PD141153Logic extends BaseLogic {



    /**
     * SQL Map 中SQL ID定义
     *
     * @author XHY
     */
    interface SqlId {
        String SQL_ID_FIND_COUNT = "findCount";
        String SQL_ID_MODIFY_TREE = "modifyClassestree";
    }

    /**
     * 根据产品标准ID查询查询产品标准表信息
     * @param param param
     * @return 标准表信息
     */


    /**
     * 根据根据对象查询数据库是否有相同的数据
     *
     * @param param param
     * @return 返回查询的数量
     */
    @Transactional(readOnly = true)
    public int findNum(Map<String, Object> param) {
        return super.getCount(SqlId.SQL_ID_FIND_COUNT, param);
    }
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void modifyTree(PdNormsStd pdNorms) {
        //修改产品分类标准数据
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        pdNorms.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        super.modify(SqlId.SQL_ID_MODIFY_TREE,pdNorms);
    }


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
