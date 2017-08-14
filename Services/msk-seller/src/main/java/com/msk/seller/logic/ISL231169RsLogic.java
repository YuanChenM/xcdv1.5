package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cx on 2016/2/24.
 */
@Service
public class ISL231169RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static final String SQL_ID_REMOVE_SL_PD_PKG = "removeSlPdPkg";

        static final String SQL_ID_REMOVE_SL_PD_MCT_STD_NEW = "removeSLPdMctStdNew";

        static final String SQL_ID_REMOVE_PD_TNC_STD_OTHER = "removePdTncStdOther";

        static final String SQL_ID_REMOVE_SL_PD_TNC_STD_NEW = "removeSLPdTncStdNew";
    }


    /**
     * 增加卖家产品接口
     * @param slProduct
     * @return
     */
    @Transactional
    public int savaSLProduct(SlProduct slProduct) {
        slProduct.setCrtTime(DateTimeUtil.getCustomerDate());
        return super.save(slProduct);
    }

    /**
     * 修改卖家产品接口
     * @param slProduct
     * @return
     */
    @Transactional
    public int updateSlProduct(SlProduct slProduct){
        slProduct.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(slProduct);
    }

    /**
     *删除卖家产品
     * @param baseParam
     * @return
     */
    @Transactional
    public int removeSLProduct(BaseParam baseParam){
        return super.remove(baseParam);
    }
    /**
     *删除卖家产品包装规格
     * @param baseParam
     * @return
     */
    @Transactional
    public int removeSlPdPkg(BaseParam baseParam){
        return super.remove(SqlId.SQL_ID_REMOVE_SL_PD_PKG,baseParam);
    }

    /**
     *删除卖家产品加工技术标准
     * @param baseParam
     * @return
     */
    @Transactional
    public int removeSLPdMctStdNew(BaseParam baseParam){
        return super.remove(SqlId.SQL_ID_REMOVE_SL_PD_MCT_STD_NEW,baseParam);
    }

    /**
     *删除卖家产品其他标准
     * @param baseParam
     * @return
     */
    @Transactional
    public int removeSLPdTncStdOther(BaseParam baseParam){
        return super.remove(SqlId.SQL_ID_REMOVE_PD_TNC_STD_OTHER,baseParam);
    }

    /**
     *删除卖家产品加工质量标准
     * @param baseParam
     * @return
     */
    @Transactional
    public int removeSLPdTncStdNew(BaseParam baseParam){
        return super.remove(SqlId.SQL_ID_REMOVE_SL_PD_TNC_STD_NEW,baseParam);
    }
}







