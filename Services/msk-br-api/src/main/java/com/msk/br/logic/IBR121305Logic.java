package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121305RsBean;
import com.msk.br.bean.IBR121305RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrHkGroup;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 冻品管家组接口
 * Created by yuan_zhifei on 2016/8/3.
 */
@Service
public class IBR121305Logic extends BaseLogic {

    @Autowired
    private CommonLogic commonLogic;

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121305Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQLID_UP_HK_GROUP_LIST = "updateHkGroup";
        String SQLID_GET_HKGROUP_COUNT = "getHkGroupCount";
    }
    @Transactional
    public void modifyPdGroupList(List<IBR121305RsParam> houseList){
        if(!CollectionUtils.isEmpty(houseList)){
            for (int i=0;i<houseList.size();i++){
                 this.getBaseDao().getSqlSession().update(SqlId.SQLID_UP_HK_GROUP_LIST, houseList.get(i));
            }
        }
    }

    /**
     * 新增冻品管家组数据 1、判断是否存在
     * @param brHkGroup
     * @return
     */
    @Transactional
    public int addBrHkGroupInfo(BrHkGroup brHkGroup){
        int count = this.getCount(SqlId.SQLID_GET_HKGROUP_COUNT,brHkGroup);
        if(count > 0){
            return 1;
        }else{
            Long maxId = commonLogic.maxId("BR_HK_GROUP", "HK_GROUP_ID");
            brHkGroup.setHkGroupId(maxId);
            this.save(brHkGroup);
            return 0;
        }
    }
}

