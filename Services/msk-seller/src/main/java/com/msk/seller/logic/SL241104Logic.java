package com.msk.seller.logic;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.ProductStdResult;
import com.msk.product.bean.StdItem;
import com.msk.product.bean.TncStdBean;
import com.msk.seller.bean.SL241117Bean;
import com.msk.seller.utils.ISLRestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * SL241104Logic
 *
 * @author yuan_chen
 * @version 1.0
 */
@Service
public class SL241104Logic extends BaseLogic {
    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_FIND_SL_PD_TNC_STD = "findSlPdTncStd";
    }

    /**
     * 查询卖家产品加工质量标准信息
     * 调用接口
     *
     * @param param 参数，卖家编码，卖家产品Id
     * @return
     */
    @Transactional(readOnly = true)
    public List<SL241117Bean> findSlPdTncStd(BaseParam param) {
        List<SL241117Bean> results = this.findList(SqlId.SQL_ID_FIND_SL_PD_TNC_STD, param);
        if (results == null || results.size() == 0) {
            return results;
        }
        List<StdItem> stdItemList = new ArrayList<>();
        for (SL241117Bean bean : results) {
            StdItem item = new StdItem();
            item.setStandardId(StringUtil.toSafeString(bean.getStandardId()));
            item.setStdItemId(bean.getStdItemId());
            stdItemList.add(item);
        }
        RsResponse<ProductStdResult> response = ISLRestUtil.getPdProductStd(stdItemList, null, 8);
        ProductStdResult result = response.getResult();
        if(result == null){
            result = new ProductStdResult();
        }
        List<TncStdBean> tncStdList = result.getTncStdList();
        Map<String, Object> maps = new HashMap<String, Object>();
        if(!CollectionUtils.isEmpty(tncStdList)){
            for (TncStdBean tncStdBean : tncStdList) {
                String key = tncStdBean.getTncStdItemId() + tncStdBean.getStandardId();
                maps.put(key, tncStdBean);
            }
        }
        List<SL241117Bean> afterResults = new ArrayList<>();
        for (SL241117Bean bean : results) {
            String key = bean.getStdItemId() + bean.getStandardId();
            TncStdBean tncStdBean = (TncStdBean) maps.get(key);
            if (null != tncStdBean) {
                bean.setStandardId(tncStdBean.getStandardId());
                bean.setTncStdItemId(tncStdBean.getTncStdItemId());
                bean.setTncStdVal1(tncStdBean.getTncStdVal1());
                bean.setTncStdVal2(tncStdBean.getTncStdVal2());
                bean.setTncStdVal3(tncStdBean.getTncStdVal3());
                bean.setRemark(tncStdBean.getRemark());
                bean.setTncStdItemName(tncStdBean.getTncStdItemName());
                bean.setLevelId(tncStdBean.getLevelId());
                bean.setParentId(tncStdBean.getParentId());
                bean.setIsCatalog(tncStdBean.getIsCatalog());
                afterResults.add(bean);
            }
        }
        return afterResults;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
