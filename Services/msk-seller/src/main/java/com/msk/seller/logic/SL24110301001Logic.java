package com.msk.seller.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.SlEnterprise;
import com.msk.seller.bean.SL241101Bean;
import com.msk.seller.bean.SL24110301001Bean;
import com.msk.seller.bean.SlEpAgentAuthBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SL24110301001Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    //查询企业数据
    @Transactional(readOnly = true)
    public PageResult<SL24110301001Bean> queryData(BasePageParam param) {
        PageResult<SL24110301001Bean>  result =  this.findPage(param, SL24110301001Bean.class);
        if(result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO){
            List<SL24110301001Bean> lists = result.getData();
            // 从redis  获取 卖家主分类
            Map<String, String> slMainClassMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlMainClass.TYPE);
            for(SL24110301001Bean bean : lists){
                // 处理 slMainClass
                if(!StringUtil.isNullOrEmpty(bean.getSlMainClass())){
                    String value = slMainClassMap.get(bean.getSlMainClass());
                    if(null != value){
                        bean.setSlMainClass(value);
                    }
                }
            }
        }
        return result;
    }
}