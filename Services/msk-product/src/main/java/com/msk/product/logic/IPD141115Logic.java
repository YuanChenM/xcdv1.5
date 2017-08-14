package com.msk.product.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141115PdInfoRsResult;
import com.msk.product.bean.IPD141115RsParam;
import com.msk.product.bean.IPD141115RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月26日 下午17:26:20
 *          产品信息接口  返回产品规格等信息
 */
@Service
public class IPD141115Logic extends BaseLogic {


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId {
        static final String SQL_ID_COUNT = "count";
    }
    /**
     * 查询所有产品标准数据
     *
     * @return List 返回list集合
     * @version xhy
     */
    @Transactional(readOnly = true)
    public IPD141115RsResult findListPdInformation(IPD141115RsParam param) {

        if (param == null) {
            param = new IPD141115RsParam();
        }
        IPD141115RsResult result = new IPD141115RsResult();

        result.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, param));
        result.setPageNo(param.getPageNo());
        List<IPD141115PdInfoRsResult> results = new ArrayList<IPD141115PdInfoRsResult>();

        if (result.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(param);
            result.setTotalPage(result.getTotalCount(), param.getPageCount());
        }
        //设置值
        result.setPdList(results);
        return result;
    }
}