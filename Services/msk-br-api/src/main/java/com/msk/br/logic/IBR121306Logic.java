package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121305RsParam;
import com.msk.br.bean.IBR121306RsBean;
import com.msk.br.bean.IBR121306RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 冻品管家组接口
 * Created by yuan_zhifei on 2016/8/3.
 */
@Service
public class IBR121306Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121306Logic.class);
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_FIND_HKGROUP_FOR_HKINFO = "findHkGroupForHkInfo";
        String IS_EXIST = "isExist";
    }

    @Transactional
    public int insertBrHkInfoList(IBR121306RsParam param) {
        List<IBR121306RsParam> houseList = param.getHouseList();
        int result = 0;
        for (IBR121306RsParam house : houseList) {
            house.setActId(param.getActId());
            house.setUpdId(param.getUpdId());
            house.setCrtId(param.getCrtId());
            house.setActTime(param.getActTime());
            house.setUpdTime(param.getUpdTime());
            house.setCrtTime(param.getCrtTime());
            house.setHkGroupId(param.getHkGroupId());
            int count = this.getCount(SqlId.IS_EXIST,house);
            if(count > 0){
                result = 0;
                continue;
            }else {
                Long maxId = commonLogic.maxId("br_hk_info", "ID");
                house.setId(maxId);
                int res = this.save(house);
                result++;
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<IBR121306RsBean> getHkGroupForHkInfoByParam(IBR121305RsParam param) {
       return this.findList(SqlId.SQL_FIND_HKGROUP_FOR_HKINFO,param);
    }

    @Transactional(readOnly = true)
    public int isExist(IBR121306RsParam param){
        return  this.getCount(SqlId.IS_EXIST,param);
    }
}
