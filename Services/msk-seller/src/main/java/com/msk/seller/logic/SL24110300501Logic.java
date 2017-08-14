package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEpWorkshop;
import com.hoperun.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 保存企业车间信息
 * Created by Administrator on 2016/1/28.
 */
@Service
public class SL24110300501Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_SAVE_SLEPWORKSHOP = "saveSlEpWorkshop";
        static final String SQL_ID_SELECT_SLEPWORKSHOP = "selectSlEpWorkshop";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 保存用户生产车间信息
     * @param slEpWorkshop 车间数据信息
     * @param slEpId 企业ID
     */
    @Transactional
    public void saveSlEpWorkshop(SlEpWorkshop slEpWorkshop, Long slEpId) {
        slEpWorkshop.setEpId(slEpId);
        slEpWorkshop.setDelFlg("0");
        slEpWorkshop.setCrtTime(DateTimeUtil.getCustomerDate());
        super.save(SqlId.SQL_ID_SAVE_SLEPWORKSHOP, slEpWorkshop);
    }

    /**
     *  查询车间名称是否已经存在，如果存在，提示不必录入相同车间
     * @param slEpId 企业ID
     * @param workshopName 车间名称
     */
    @Transactional(readOnly = true)
    public void findIfExist(Long slEpId, String workshopName) {
        BaseParam base= new BaseParam();
        base.setFilter("epId",slEpId.toString());
        base.setFilter("workshopName",workshopName);
        List<SlEpWorkshop> list = super.findList(SqlId.SQL_ID_SELECT_SLEPWORKSHOP,base);
        if(list.size()>0){
            throw new BusinessException("已经存在的车间名称  无需重复录入");
        }
    }

}
