package com.msk.bs.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by ren_qiang on 2016/8/1.
 */
@Service
public class BS2102104Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询冻品管家组信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<BR121305Bean> findIBS2102104List(BR121305Param param){
        Map<String,Object> map = param.getFilterMap();


        if(!org.springframework.util.CollectionUtils.isEmpty(map.values())){

            param.setLgcsAreaCode(map.get("lgcsAreaCode").toString());
            param.setCityCode(map.get("cityCode").toString());
            param.setBuyerType(map.get("buyerType").toString());
            param.setClassesCode(map.get("classesCode").toString());
            param.setMachiningCode( map.get("machiningCode").toString());
            /*br121305Param.setPaging(true);*/
        }
        //调用查询接口，传入查询条件
        PageResult<BR121305Bean> pageResult = new PageResult<BR121305Bean>();
        RsResponse<BR121305Result> response= CommRestUtil.queryHkGroupInfo(param);
        if (response != null && response.getResult() != null && !CollectionUtils.isEmpty(response.getResult().getIbr121305RsBeanList())) {

            pageResult.setData(response.getResult().getIbr121305RsBeanList());
            pageResult.setRecordsTotal(response.getResult().getTotalCount());
            return pageResult;
        }
        else{
            pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            pageResult.setData(new ArrayList<BR121305Bean>());
            return  pageResult;
        }

    }

    /**
     * 批量修改冻品管家组名称
     * @param paramList
     * @return
     */
    @Transactional
    public int editePdGroupName(List<BR121305Param> paramList){
        //调用接口批量编辑方法
       Integer res = CommRestUtil.updatePdGroupName(paramList);
        return res;
    }
}


