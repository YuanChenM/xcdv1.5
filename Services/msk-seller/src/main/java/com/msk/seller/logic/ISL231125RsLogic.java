package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlEnterprise;
import com.msk.seller.bean.ISL231125RsParam;
import com.msk.seller.bean.ISL231125RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/15.
 */
@Service
public class ISL231125RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public List<SlEnterprise> findAllList(RsRequest<ISL231125RsParam> param) {
        // 创建输入参数
        ISL231125RsParam iSL231125RsParam = param.getParam();
        // 创建输出参数
        BaseParam params = new BaseParam();
        params.setFilter("epId", StringUtil.toSafeString(iSL231125RsParam.getEpId()));
        params.setFilter("epName", StringUtil.toSafeString(iSL231125RsParam.getEpName()));
        return super.findList(params);
    }
}
