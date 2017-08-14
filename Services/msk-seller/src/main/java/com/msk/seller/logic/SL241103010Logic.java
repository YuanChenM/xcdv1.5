package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.hoperun.core.consts.NumberConst;
import com.msk.core.entity.SlEcTeam;
import com.msk.core.entity.SlEpAgentAuth;
import com.msk.core.entity.SlEpOemAuth;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.ISL231145RsParam;
import com.msk.seller.bean.ISL231145RsResult;
import com.msk.seller.bean.SL241103070Bean;
import com.msk.seller.bean.SlEpAgentAuthBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class SL241103010Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_SAVE_AGENT_DATA = "saveAgentData";
        String SQL_ID_SAVE_OEM_DATA = "saveOemData";
        String SQL_ID_SAVE_FIND_QUERY_AGENT_DATA = "findQueryAgentData";
        String SQL_ID_SAVE_FIND_QUERY_OEM_DATA = "findQuerysaveOemData";
    }

    //保存数据
    @Transactional
    public int saveData(String slcode,SlEpAgentAuthBean slEpAgentAuthBean) {
        BaseParam param = new BaseParam();
        int count=0;
        if(null!=slEpAgentAuthBean){
            param.setFilter("slCode",slcode);
            param.setFilter("producerEpId",StringUtil.toSafeString(slEpAgentAuthBean.getProducerEpId()));
            slEpAgentAuthBean.setSlCode(slcode);
            slEpAgentAuthBean.setDelFlg("0");
            //判断是生产商添加还是OEM添加
            if(NumberConst.IntDef.INT_ONE==slEpAgentAuthBean.getMarkFlg()){
                List<SlEpAgentAuthBean> agentNum=queryAgentData(param);
                if(CollectionUtils.isEmpty(agentNum) && agentNum.size()<=0){
                    Date nowDate = DateTimeUtil.getCustomerDate();
                    slEpAgentAuthBean.setCrtTime(nowDate);
                    count=super.save(SqlId.SQL_ID_SAVE_AGENT_DATA,slEpAgentAuthBean);
                }else{
                    throw new BusinessException("卖家ID为:"+slcode+"的,该生产商已存在");
                }
            }else if(NumberConst.IntDef.INT_TWO==slEpAgentAuthBean.getMarkFlg()){
                List<SlEpAgentAuthBean> agentNum=queryOemData(param);
                if(CollectionUtils.isEmpty(agentNum)&&agentNum.size()<=0){
                    Date nowDate = DateTimeUtil.getCustomerDate();
                    slEpAgentAuthBean.setCrtTime(nowDate);
                    count=super.save(SqlId.SQL_ID_SAVE_OEM_DATA,slEpAgentAuthBean);
                }else{
                    throw new BusinessException("卖家ID为:"+slcode+"的,该OEM商已存在");
                }
            }
        }
        return count;
    }

    //查询生产商
    @Transactional(readOnly = true)
    public List<SlEpAgentAuthBean> queryAgentData(BaseParam param){
        return super.findList(SqlId.SQL_ID_SAVE_FIND_QUERY_AGENT_DATA, param);
    }

    //查询OEM
    @Transactional(readOnly = true)
    public List<SlEpAgentAuthBean> queryOemData(BaseParam param){
        return super.findList(SqlId.SQL_ID_SAVE_FIND_QUERY_OEM_DATA, param);
    }
}