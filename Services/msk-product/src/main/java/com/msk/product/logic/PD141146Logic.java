package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdOrgStd;
import com.msk.core.entity.PdOrgStdItem;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141146Bean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * PD141146Logic
 *
 * @author xhy
 */
@Service
public class PD141146Logic extends BaseLogic {




    /**
     * SQL Map 中SQL ID定义
     *
     * @author pxg
     */
    interface SqlId {
        static final String SQL_ID_FIND_FIND_PD_ORG_STD = "findOrgStdList";
        static final String SQL_ID_FIND_BREED_NAME_FEANAME = "findName";
        static final String SQL_ID_MODIFY_STD_ORG_FLG = "modifyStdOrgFlg";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询所有orgstdTime
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public List<PdOrgStdItem> findListPdOrgStd(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_FIND_PD_ORG_STD, param);
    }

    /**
     * 获取产品品种数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141124showNameBean> findBreedNameAndFea(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_BREED_NAME_FEANAME, param);
    }

    /**
     * 保存修改数据
     * @param bean
     * @param param
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void saveAndEdit(PD141146Bean bean, BaseParam param) {
        if (StringUtils.isNotBlank(bean.getStandardId().toString())) {
            param.setFilter("standardId", bean.getStandardId().toString());
            List<PD141146Bean> list = super.findList(param);
            if (list.size() <= NumberConst.IntDef.INT_ZERO) {
                //新增操作
                if (bean.getOrgStdItemIdArray().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = 0; i < bean.getOrgStdItemIdArray().length; i++) {
                        PdOrgStd pd = new PdOrgStd();
                        pd.setOrgBadVal(bean.getOrgBadValArray()[i]);
                        pd.setOrgGoodVal(bean.getOrgGoodValArray()[i]);
                        pd.setOrgNormalVal(bean.getOrgNormalValArray()[i]);
                        pd.setOrgStdItemId(bean.getOrgStdItemIdArray()[i]);
                        pd.setStandardId(bean.getStandardId());
                        pd.setCrtId(param.getCrtId());
                        pd.setUpdId(param.getUpdId());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        pd.setUpdTime(new Date());
                        pd.setCrtTime(new Date());
                        pd.setActTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        super.save(pd);
                    }
                }
               /* //新增结束改变standard表中的产品标准种源标识
                param.setFilter("flg", String.valueOf(NumberConst.IntDef.INT_ONE));
                int upStd  = super.modify(SqlId.SQL_ID_MODIFY_STD_ORG_FLG,param);
                if(upStd<= NumberConst.IntDef.INT_ZERO)throw new BusinessException("更新产品种源标准标识异常,请联系管理员!");*/
            } else {
                //修改操作
                if (bean.getOrgStdItemIdArray().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < bean.getOrgStdItemIdArray().length; i++) {
                        PdOrgStd pd = new PdOrgStd();
                        pd.setOrgBadVal(bean.getOrgBadValArray()[i]);
                        pd.setOrgGoodVal(bean.getOrgGoodValArray()[i]);
                        pd.setOrgNormalVal(bean.getOrgNormalValArray()[i]);
                        pd.setOrgStdItemId(bean.getOrgStdItemIdArray()[i]);
                        pd.setStandardId(bean.getStandardId());
                        pd.setUpdId(param.getUpdId());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        pd.setUpdTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                       super.modify(pd);
                    }
                }
            }

        }
    }
}
