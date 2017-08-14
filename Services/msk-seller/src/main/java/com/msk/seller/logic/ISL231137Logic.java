package com.msk.seller.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlSeller;
import com.msk.seller.bean.ISL231137RsBean;
import com.msk.seller.bean.ISL231137RsParam;
import com.msk.seller.bean.SlSellerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gyh on 2016/3/17.
 */
public class ISL231137Logic extends BaseLogic {

    /**
     * gzh 有待优化
     * 查询卖家生产商信息
     * @param param 参数
     * @return 生产商信息
     */
    @Transactional(readOnly = true)
    public List<ISL231137RsBean> search(ISL231137RsParam param) {
        param.setFlag("1");
        List<ISL231137RsBean> auths2=this.findList(SqlId.SQL_ID_FIND_SEARCH_BY_FLG, param);
        param.setFlag("2");
        List<ISL231137RsBean> auths3=this.findList(SqlId.SQL_ID_FIND_SEARCH_BY_FLG, param);
        auths2.addAll(auths3);
        SlSellerBean slSeller=searchSelfFlg(param);
        ISL231137RsBean isl231137RsBean=new ISL231137RsBean();
        if(null!=slSeller && slSeller.getSelfFlg().equals("1")){
            isl231137RsBean.setSlCode(slSeller.getSlCode());
            isl231137RsBean.setProducerEpId(slSeller.getEpId());
            isl231137RsBean.setProducerEpName(slSeller.getEpName());
        }
        auths2.add(isl231137RsBean);
        return auths2;
    }

    /**
     * 查询是否是自产商
     * @param param 参数
     * @return 生产商信息
     */
    @Transactional(readOnly = true)
    public SlSellerBean searchSelfFlg(ISL231137RsParam param) {
        SlSellerBean slSeller=this.findOne(param);
        return slSeller;
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_FIND_SEARCH_BY_FLG = "searchByFlg";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
