package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141150Param;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by air on 2016/3/4.
 */
@Service
public class PD141150Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }



    interface SqlId {
        static final String SQL_ID_SAVE_ONE = "saveOne";
        static final String SQL_ID_MODIFY_ONE = "modifyOne";
        static final String SQL_ID_FIND_LIST_PD_MCT_LEVEL2_STD_SHOW = "findStdItemLevel2ListShow";
        static final String SQL_ID_FIND_ONE_SFT_BEAN = "findOneSftStd";
        static final  String SQL_ID_FIND_List_SFT_NUMBER = "findListSize";
        static final String SQL_ID_FIND_BREED_NAME_FEANAME = "findName";

        static final String SQL_ID_MODIFY_STD_FLG = "modifyFlg";

    }

    /**
     * 查询单个stfstd对象 存在
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PD141150Param findOneSftStd(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_ONE_SFT_BEAN,param);
    }

    /**
     * 使用标准id查询数据是否为空
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public int getCountNum(BaseParam param) {
        return super.getCount(SqlId.SQL_ID_FIND_List_SFT_NUMBER,param);
    }

    /**
     * 实例化页面二级类目显示   存在
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141150Param> findListLevel2(BaseParam param) {
        return super.findList(SqlId.SQL_ID_FIND_LIST_PD_MCT_LEVEL2_STD_SHOW,param);

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
     * 保存数据
     * @param bean
     * @param param
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void saveAndEdit(PD141150Param bean, BaseParam param) {
        if (StringUtils.isNotBlank(bean.getStandardId().toString())) {
            param.setFilter("standardId", bean.getStandardId().toString());
            int exit = super.getCount(SqlId.SQL_ID_FIND_List_SFT_NUMBER, param);;
            if (exit <= NumberConst.IntDef.INT_ZERO) {

                if (bean.getGnqItemIdArray().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < bean.getGnqItemIdArray().length; i++) {
                        PD141150Param pd = new PD141150Param();
                        pd.setGnqOkVal(bean.getGnqOkValArray()[i]);
                        pd.setGnqNgVal(bean.getGnqNgValArray()[i]);
                        pd.setGnqStdItemId(bean.getGnqItemIdArray()[i]);
                        pd.setStandardId(bean.getStandardId());
                        pd.setUpdId(param.getUpdId());
                        pd.setCrtId(param.getCrtId());
                        /**Add: 横展开添加共通设置 2016/09/27   BY  任强  Start */
                        pd.setActId(param.getActId());
                        /**Add: 横展开添加共通设置 2016/09/27   BY  任强  End */
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        pd.setActTime(new Date());
                        pd.setCrtTime(new Date());
                        pd.setUpdTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        super.save(pd);
                    }
                }
              /*  param.setFilter("flg", String.valueOf(NumberConst.IntDef.INT_ONE));
                int exist =    super.modify(SqlId.SQL_ID_MODIFY_STD_FLG,param);
                if(exist<= NumberConst.IntDef.INT_ZERO)throw new BusinessException("更新产品通用质量标准标识异常,请联系管理员!");*/
            } else {
                //修改操作
                if (bean.getGnqItemIdArray().length > NumberConst.IntDef.INT_ZERO) {

                    for (int i = NumberConst.IntDef.INT_ZERO; i < bean.getGnqItemIdArray().length; i++) {
                        PD141150Param pd = new PD141150Param();
                        pd.setGnqOkVal(bean.getGnqOkValArray()[i]);
                        pd.setGnqNgVal(bean.getGnqNgValArray()[i]);
                        pd.setGnqStdItemId(bean.getGnqItemIdArray()[i]);
                        pd.setStandardId(bean.getStandardId());
                        pd.setUpdId(param.getUpdId());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        pd.setUpdTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        super.modify(pd);
                    }
                }
            }

        }
    }
}
