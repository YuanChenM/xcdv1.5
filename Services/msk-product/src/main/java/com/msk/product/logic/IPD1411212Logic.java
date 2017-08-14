package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD1411212RsBean;
import com.msk.product.bean.IPD1411212RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gao_min on 2016/10/9.
 */
@Service
public class IPD1411212Logic extends BaseLogic {

    interface SqlId {
        static final String SQL_ID_QUERY_REPORT_INFO = "queryReportInfo";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     *
     * @return IPD1411212RsResult 举报类型
     * @author gao_min
     */
    @Transactional(readOnly = true)
    public IPD1411212RsResult queryReportInfo() {

        IPD1411212RsResult rsResult = new IPD1411212RsResult();

        List<IPD1411212RsBean> reportList = super.findList(SqlId.SQL_ID_QUERY_REPORT_INFO, null);

        rsResult.setReportList(reportList);
        return rsResult;
    }


}
