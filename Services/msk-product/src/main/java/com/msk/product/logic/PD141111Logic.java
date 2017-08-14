package com.msk.product.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdNormsStd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * PD141111Logic
 *
 * @author pxg
 */

@Service
public class PD141111Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 新增方法 修改DB返回参数中的时间值问题
     *
     * @param param 查询条件
     * @return List 返回产品包装列表
     * @author xhy
     */
    @Transactional(readOnly = true)
    public PageResult<PdNormsStd> findPdNormsPageList(BasePageParam param) {
        PageResult<PdNormsStd> page = super.findPage(param, PdNormsStd.class);
        List<PdNormsStd> list = page.getData();
        if (list != null) {
//            for (PdNormsStd pds : list) {
//                pds.setUpdateTimeString(DateTimeUtil.formatDate("yyyy-MM-dd", pds.getUpdTime()));
//                pds.setCrateTimeString(DateTimeUtil.formatDate("yyyy-MM-dd", pds.getActTime()));
//            }
            return page;
        }
        return null;
    }
}
