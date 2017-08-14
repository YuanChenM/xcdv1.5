package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS210110501Param;
import com.msk.bs.bean.BS2102105Param;
import com.msk.bs.bean.BS2102105Result;
import com.msk.bs.bean.IBS121306RsBean;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by yang_chunyan on 2016/8/1.
 */
public class BS2102105Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static final String SQL_ID_MODIFY_HOUSE_INFO = "modifyHouseInfo";
    }

    @Transactional
    public PageResult<BS2102105Result> findPageResult(BS2102105Param pparam){
        Map<String,Object> filterMap = pparam.getFilterMap();
        String groupId = (String)filterMap.get("dpGroupId");
        if(StringUtils.hasLength(groupId)){
            pparam.setHkGroupId(groupId);
            pparam.setPaging(false);
            List<IBS121306RsBean> houseList = CommRestUtil.getHkListInHkGroup(pparam);
            List<BS210110501Param> prams = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(houseList)){
                for(IBS121306RsBean bean : houseList){
                    BS210110501Param param = new BS210110501Param();
                    param.setSlCode(bean.getSlCode());
                    param.setHouseCode(bean.getHouseCode());
                    prams.add(param);
                }
                pparam.setPaging(true);
                pparam.setHouseList(prams);
                return super.findPage(pparam, BS2102105Result.class);
            }
        }
        PageResult<BS2102105Result> pageResult = new PageResult<BS2102105Result>();
        pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
        pageResult.setData(new ArrayList<BS2102105Result>());
        return pageResult;

    }

    @Transactional
    public PageResult<BS2102105Result> findPageHouseResult(BS2102105Param pparam){
        Map<String,Object> filterMap = pparam.getFilterMap();
        String groupId = (String)filterMap.get("dpGroupId");
        List<BS210110501Param> prams = new ArrayList<>();
        if(StringUtils.hasLength(groupId)){
            pparam.setHkGroupId(groupId);
            pparam.setPaging(false);
            List<IBS121306RsBean> houseList = CommRestUtil.getHkListInHkGroup(pparam);
            if(CollectionUtils.isNotEmpty(houseList)){
                for(IBS121306RsBean bean : houseList){
                    BS210110501Param param = new BS210110501Param();
                    param.setSlCode(bean.getSlCode());
                    param.setHouseCode(bean.getHouseCode());
                    prams.add(param);
                }
            }
            pparam.setFilter("flag","true");
            pparam.setPaging(true);
            pparam.setHouseList(prams);
            return super.findPage(pparam, BS2102105Result.class);
        }
        PageResult<BS2102105Result> pageResult = new PageResult<BS2102105Result>();
        pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
        pageResult.setData(new ArrayList<BS2102105Result>());
        return pageResult;

    }

    @Transactional
    public int modifyHouseInfo(Collection<BS2102105Param> params,String groupId, BaseParam baseParam) {
        int count = 0;
        for (BS2102105Param param : params) {
            param.setHkGroupId(groupId);
            param.setUpdTime(DateTimeUtil.getCustomerDate());
            param.setUpdId(baseParam.getUpdId());
            super.modify(SqlId.SQL_ID_MODIFY_HOUSE_INFO, param);
            count++;
        }
        return count;
    }

}
