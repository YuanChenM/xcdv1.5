package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.seller.bean.SlEpAgentAuthBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SL241101010Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_UPDATE_AGENT_DATA = "updateAgentData";
        String SQL_ID_UPDATE_OEM_DATA = "updateOemData";
        String SQL_ID_DELETE_AGENT_DATA = "deleteAgentData";
        String SQL_ID_DELETE_OEM_DATA = "deleteOemData";
    }

    //保存数据
    @Transactional
    public int saveData(SlEpAgentAuthBean slEpAgentAuthBean) {
        int count=0;
        if(null!=slEpAgentAuthBean){
            //判断是生产商添加还是OEM添加
            if(NumberConst.IntDef.INT_ONE==slEpAgentAuthBean.getMarkFlg()){
                count=super.modify(SqlId.SQL_ID_UPDATE_AGENT_DATA, slEpAgentAuthBean);
            }else if(NumberConst.IntDef.INT_TWO==slEpAgentAuthBean.getMarkFlg()){
                count=super.modify(SqlId.SQL_ID_UPDATE_OEM_DATA, slEpAgentAuthBean);
            }
        }
        return count;
    }

    //删除数据
    @Transactional
    public int deleteData(String slCode,String epId,String markFlg) {
        BaseParam param = new BaseParam();
        int count=0;
            param.setFilter("slCode",slCode);
            param.setFilter("epId",epId);
            param.setFilter("delFlg","1");
            //判断是生产商添加还是OEM添加
            if("1".equals(markFlg)){
                count=super.modify(SqlId.SQL_ID_DELETE_AGENT_DATA, param);
            }else if("2".equals(markFlg)){
                count=super.modify(SqlId.SQL_ID_DELETE_OEM_DATA, param);
            }
        return count;
    }
}