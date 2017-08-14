package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEnterprise;
import com.msk.core.entity.SlSeller;
import com.msk.core.entity.SlSellerHis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fjm on 2016/1/28.
 */
@Service
public class SL24110102Logic extends BaseLogic {
    /**
     * logger
     *
     */
    public static Logger logger = LoggerFactory.getLogger(SL24110102Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SLSellerLogic slSellerLogic;

    interface SqlId{
        String SQL_ID_MODIFY_EP = "modifyEp";
        String SQL_ID_MODIFY_SL = "modifySl";
        String SQL_ID_FINDSLSELLER="findSlSellerBySlCode";
        String SQL_ID_SAVE_HIS="saveHis";
    }

    /**保存履历*/
    @Transactional
    public void saveHis(SlSellerHis slSellerHis) {
        slSellerHis.setCrtTime(DateTimeUtil.getCustomerDate());
        super.save(SqlId.SQL_ID_SAVE_HIS,slSellerHis);
    }

    /**根据卖家编码查询卖家信息*/
    @Transactional(readOnly = true)
    public SlSellerHis findSlSellerBySlCode(String slCode) {
        BaseParam base = new BaseParam();
        base.setFilter("slCode",slCode);
        return super.findOne(SqlId.SQL_ID_FINDSLSELLER,base);
    }

    @Transactional
    public int updateEp(SlEnterprise slEnterprise){
        slEnterprise.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.SQL_ID_MODIFY_EP, slEnterprise);
    }

    @Transactional
    public int updateSl(SlSeller slSeller){
        slSeller.setUpdTime(DateTimeUtil.getCustomerDate());
        int result = super.modify(SqlId.SQL_ID_MODIFY_SL, slSeller);
        /** 调用接口 同步卖家模块买手信息 create by likai1 start 2016/8/3 */

        slSellerLogic.updSellerToBs(slSeller);
        /** 调用接口 同步卖家模块买手信息 create by likai1 end 2016/8/3 */
        return result;
    }

}
