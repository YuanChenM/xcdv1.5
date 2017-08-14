package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdReportInfo;
import com.msk.core.entity.PdReportType;
import com.msk.product.bean.IPD1411214RsBean;
import com.msk.product.bean.IPD1411214RsParam;
import com.msk.product.bean.IPD1411214RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gao_min on 2016/10/10.
 */
@Service
public class IPD1411214Logic extends BaseLogic {

    interface SqlId {
        // 查询举报一览列表.
        static final String SQL_ID_SEARCH_REPORT_LIST = "searchReportList";
        // 根据举报类型查询举报类型名称.
        static final String SQL_ID_QUERY_REPORT_TYPE_NAME = "queryReportTypeName";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * @return IPD1411214RsResult 举报一览
     * @author gao_min
     */
    @Transactional(readOnly = true)
    public IPD1411214RsResult searchReportList(IPD1411214RsParam param) {

        IPD1411214RsResult result = new IPD1411214RsResult();
        List<IPD1411214RsBean> reportList = new ArrayList<IPD1411214RsBean>();

        List<PdReportInfo> pdReportInfoList = new ArrayList<PdReportInfo>();
        pdReportInfoList = super.findList(SqlId.SQL_ID_SEARCH_REPORT_LIST, param);

        if (null != pdReportInfoList) {
            for (PdReportInfo pdReportInfo : pdReportInfoList) {
                IPD1411214RsBean reportInfo = new IPD1411214RsBean();
                // 举报单号.
                reportInfo.setReportId(String.valueOf(pdReportInfo.getReportId()));
                // 物流区Code.
                reportInfo.setLgcsCode(pdReportInfo.getLgcsCode());
                // 产品编码.
                reportInfo.setProductCode(pdReportInfo.getPdCode());
                // 举报类型.
                reportInfo.setReportType(String.valueOf(pdReportInfo.getReportTypeCode()));
                // 审核状态.
                reportInfo.setReportStatus(pdReportInfo.getReportStatus());
                // 根据举报类型查询举报类型名称.
                PdReportType pdReportType = super.findOne(SqlId.SQL_ID_QUERY_REPORT_TYPE_NAME, pdReportInfo);
                // 举报类型名称.
                if (null != pdReportType) {
                    reportInfo.setReportTypeName(pdReportType.getReportTitle());
                }
                // 举报内容.
                reportInfo.setReportDescription(pdReportInfo.getReportDesc());
                // 举报凭证.
                List<String> reportImgList = new ArrayList<String>();
                if (null != pdReportInfo.getReportImg1()) {
                    reportImgList.add(pdReportInfo.getReportImg1());
                }
                if (null != pdReportInfo.getReportImg2()) {
                    reportImgList.add(pdReportInfo.getReportImg2());
                }
                if (null != pdReportInfo.getReportImg3()) {
                    reportImgList.add(pdReportInfo.getReportImg3());
                }
                if (null != pdReportInfo.getReportImg4()) {
                    reportImgList.add(pdReportInfo.getReportImg4());
                }
                if (null != pdReportInfo.getReportImg5()) {
                    reportImgList.add(pdReportInfo.getReportImg5());
                }
                reportInfo.setReportImgList(reportImgList);
                reportList.add(reportInfo);
            }
            result.setReportList(reportList);
        }
        return result;

    }


}
