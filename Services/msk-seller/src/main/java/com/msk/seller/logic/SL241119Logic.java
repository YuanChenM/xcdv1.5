package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.core.entity.PdNormsStd;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.core.entity.SlPdPkg;
import com.msk.seller.bean.SL241119Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * PD141111Logic
 *
 * @author pxg
 */

@Service
public class SL241119Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_FIND_PKG_CODES = "findPkgCodes";
        static final String SQL_ID_FIND_SL_PKG_LIST = "findslPkgList";
    }

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
        return super.findPage(param, PdNormsStd.class);
    }

    /**
     * 根据卖家代码、产品ID和标准ID，查询包装代码。
     * Created by xia_xiaojie on 2016/6/24.
     */
    @Transactional(readOnly = true)
    public List<String> findPkgCodes(BaseParam param) {
        List<SlPdPkg> pdPkgs = this.findList(SqlId.SQL_ID_FIND_PKG_CODES, param);
        List<String> pkgCodes = new ArrayList<String>();
        for (SlPdPkg pdPkg : pdPkgs) {
            if (null != pdPkg) {
                pkgCodes.add(pdPkg.getPkgCode());
            }
        }
        return pkgCodes;
    }

    /**
     * 根据卖家代码和标准ID，查询包装代码。
     * Created by xuwei on 2016/6/24.
     */
    @Transactional(readOnly = true)
    public List<SL241119Bean> findslPkgList(BasePageParam param) {
        List<SL241119Bean> list = super.findList(SqlId.SQL_ID_FIND_SL_PKG_LIST,param);
        if (list != null) {
            return list;
        }
        return null;
    }
}
