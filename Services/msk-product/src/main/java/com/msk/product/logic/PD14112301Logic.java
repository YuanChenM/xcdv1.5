package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.product.bean.PD141123Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * PD14112301Logic
 *
 * @author xhy
 */

@Service
public class PD14112301Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(PD14112301Logic.class);



    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_SAVE_PD_MACHININGREF = "saveMacRef"; // 保存对照表中数据
        static final String SQL_ID_SAVE_PD_MACHINING = "saveMac"; // 保存加工度表数据
        static final String SQL_ID_GET_MAX_CODE = "getMaxCode";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int saveMacANDMacRef(PD141123Bean bean) {

        /**Modify: 修正获取machingCode 2016/09/28   BY  任强  Start */
        /*//获取单个类别中的加工最大值
        Long maxId = commonLogic.maxId("PD_MACHININGREF", "MACHINING_CODE");
        bean.setMachiningCode(maxId.toString());*/
        /**Modify: 修正获取machingCode 2016/09/28   BY  任强  End */
        BaseParam param = new BaseParam();
        param.setFilter("machiningName",bean.getMachiningName());
        param.setFilter("classesCode",bean.getClassesCode());
        PD141123Bean findBean = this.findOne(param);
        if (findBean!=null) throw new BusinessException("该条数据已经存在!");
        /**Add: 修正获取machingCode 2016/09/28   BY  任强  Start */
        PD141123Bean pd141123Bean = super.findOne(SqlId.SQL_ID_GET_MAX_CODE,param);
        if (Integer.valueOf(pd141123Bean.getMachiningCode()) >= NumberConst.IntDef.INT_TEN) {
            throw new BusinessException("二级分类新增已超过最大数,请联系管理员!");
        }
        bean.setMachiningCode(pd141123Bean.getMachiningCode());
        /**Add: 修正获取machingCode 2016/09/28   BY  任强  End */
        Long macRefMaxId =  this.commonLogic.maxId("PD_MACHININGREF","MACHININGREF_ID");
        bean.setMachiningRefId(macRefMaxId.toString());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        bean.setCrtTime(new Date());
        bean.setUpdTime(new Date());
        bean.setActTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        int saveMacRef = this.save(SqlId.SQL_ID_SAVE_PD_MACHININGREF, bean);
        if(saveMacRef<= NumberConst.IntDef.INT_ZERO) throw new BusinessException("插入数据失败!请联系管理员");
        return saveMacRef;
    }

    /**
     * 修改加工类别数据
     * @param bean
     * @return int
     */

    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int modifyMacRef(PD141123Bean bean) {
        BaseParam param1 = new BaseParam();
        param1.setFilter("machiningName",bean.getMachiningName());
        param1.setFilter("classesCode",bean.getClassesCode());
        PD141123Bean findBean = this.findOne(param1);
        if (findBean!=null) throw new BusinessException("该条数据已经存在!");
        BaseParam param = new BaseParam();
        param.setFilter("machiningName",bean.getMachiningName());
        param.setFilter("classesCode",bean.getClassesCode());
        param.setFilter("machiningRefId",bean.getMachiningRefId());
        param.setUpdTime(new Date());
        int modiOne = this.modify(param);
        if (modiOne<= NumberConst.IntDef.INT_ZERO) throw new BusinessException("修改失败!");
        return modiOne;
    }



}
