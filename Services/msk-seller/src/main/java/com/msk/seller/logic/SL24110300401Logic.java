package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEpHonor;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
@Service
public class SL24110300401Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static final String SQL_ID_SAVE_SLEPHONOR = "saveSlEpHonor";
        static final String SQL_ID_SELECT_HONORDES = "selectHonorDes";
    }

    /**
     * 保存企业荣誉信息
     * 
     * @param slEpHonor 企业荣誉数据
     * @param slEpId 企业ID
     */
    @Transactional
    public void saveSlEpHonor(SlEpHonor slEpHonor, Long slEpId) {
        slEpHonor.setEpId(slEpId);
        slEpHonor.setDelFlg("0");
        slEpHonor.setCrtTime(DateTimeUtil.getCustomerDate());
        super.save(SqlId.SQL_ID_SAVE_SLEPHONOR, slEpHonor);
    }

    /**
     * 查询同意个企业中的荣誉描述是否有重复
     * 
     * @param slEpId 企业ID
     */
    @Transactional(readOnly = true)
    public void findIfExistHonor(Long slEpId, String des) {
        BaseParam base = new BaseParam();
        base.setFilter("epId", StringUtil.toSafeString(slEpId));
        base.setFilter("honorDes", des);
        List<SlEpHonor> list = this.findList(SqlId.SQL_ID_SELECT_HONORDES, base);
        if (list.size() > 0) {
            throw new BusinessException("录入了已经存在的企业荣誉");
        }
    }

}
