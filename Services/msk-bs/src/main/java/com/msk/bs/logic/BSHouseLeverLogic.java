package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlHouseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wang_haichun on 2016/8/4.
 */
@Service
public class BSHouseLeverLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){super.setBaseDao(baseDao);}


    interface SqlId{
        static final String FIND_SL_HOUSE_TYPE = "findSlHouseType";

        static final String FIND_SL_BS_TYPE = "findSlBsType";
    }

    /**
     * 获取分类信息（一级，二级）
     * @param houseType
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlHouseType> findSlHouseType(SlHouseType houseType){
        BaseParam param = new BaseParam();
        param.getFilterMap().put("typeLever",houseType.getTypeLever());
        param.getFilterMap().put("parentTypeCode",houseType.getParentTypeCode());
        List<SlHouseType> houseTypes = this.findList(SqlId.FIND_SL_HOUSE_TYPE,param);
        return houseTypes;
    }

    /**
     * 获取买手分类信息（一级，二级）
     * @param houseType
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlHouseType> findSlBsType(SlHouseType houseType){
        BaseParam param = new BaseParam();
        param.getFilterMap().put("typeLever",houseType.getTypeLever());
        param.getFilterMap().put("parentTypeCode",houseType.getParentTypeCode());
        List<SlHouseType> houseTypes = this.findList(SqlId.FIND_SL_BS_TYPE,param);
        return houseTypes;
    }

}
