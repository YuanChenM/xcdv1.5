package com.msk.batch.order.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.order.bean.BSO151405DetailResult;
import com.msk.batch.order.bean.BSO151405Param;
import com.msk.batch.order.bean.BSO151405Result;
import com.msk.batch.order.bean.BSO151405XMLTemplate;
import com.msk.common.base.BaseLogic;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.logic.CommonLogic;
import com.msk.common.xml.JaxbXmlWrite;
import com.msk.core.entity.SoReturnStatus;
import com.msk.utils.WmsFtpUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;


/**
 * Created by wang_shuai on 2016/4/8.
 */
@Service
public class BSO151405Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151405Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    /**
     * sqlId
     */
    interface SqlId {
        static final String SQL_ID_FIND_RETURN_LIST = "findReturnList";
        static final String SQL_ID_FIND_RETURN_DETAIL_LIST = "findReturnDetailList";
        static final String SQL_ID_UPDATE_RETURN_STATUS = "updateReturnStatus";
        static final String SQL_ID_UPDATE_RETURN_DETAIL_STATUS = "updateReturnDetailStatus";
        static final String SQL_ID_UPDATE_SO_RETURN_STATUS = "updateSoReturnStatus";
        static final String SQL_ID_SAVE_SO_RETURN_STATUS = "saveSoReturnStatus";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 生成退货入库单
     *
     * @param bso151405Param param
     */
    @Transactional
    public void createReturnXml(BSO151405Param bso151405Param) {
        logger.debug("退货入库单batch开始");
        bso151405Param.setFilterObject("waitProcessStatus", OrderCodeMasterDef.ReturnOrderStatus.WAIT_SALVE);
        bso151405Param.setFilterObject("lateReceiptStatus", OrderCodeMasterDef.ReturnOrderStatus.EARN_LATER);
        List<BSO151405Result> bso151405Results = super.findList(SqlId.SQL_ID_FIND_RETURN_LIST, bso151405Param);
        if (CollectionUtils.isNotEmpty(bso151405Results)) {
            for (BSO151405Result bso151405Result : bso151405Results) {
                writeXml(bso151405Result);
            }
        }
    }

    /**
     * 生成xml并更新退货表状态
     *
     * @param bso151405Result
     */
    @Transactional
    public void writeXml(BSO151405Result bso151405Result) {
        BSO151405Param param = new BSO151405Param();
        param.setReturnId(bso151405Result.getReturnId());
        List<BSO151405DetailResult> returnDetailList = super.findList(SqlId.SQL_ID_FIND_RETURN_DETAIL_LIST, param);
        bso151405Result.setReturnDetailList(returnDetailList);

        // 更新退货单明细表
        updateReturnDetailStatus(bso151405Result);
        // 更新退货单主表
        updateReturnStatus(bso151405Result);

        // writeXML
        bso151405Result.setVer(bso151405Result.getVer() + NumberConst.IntDef.INT_ONE);
        BSO151405XMLTemplate xmlTemplate = new BSO151405XMLTemplate();
        xmlTemplate.setData(bso151405Result);
        JaxbXmlWrite<BSO151405Result> xmlWrite = new JaxbXmlWrite<BSO151405Result>(xmlTemplate);
        InputStream inputStream = xmlWrite.createEntityTemplateXml(BSO151405XMLTemplate.class);
        boolean result = WmsFtpUtils.uploadXml("/", "RE" + bso151405Result.getReturnId() + "-" + bso151405Result.getReturnCode() + ".xml", inputStream);
        if (logger.isDebugEnabled()) {
            if (result) {
                logger.debug("RE" + bso151405Result.getReturnId() + "-" + bso151405Result.getReturnCode() + ".xml" + "文件上传成功");
            } else {
                logger.debug("RE" + bso151405Result.getReturnId() + "-" + bso151405Result.getReturnCode() + ".xml" + "文件上传失败");
            }
        }
    }

    /**
     * 更新退货单状态
     *
     * @param bso151405Result bso152405Result
     */
    @Transactional
    private void updateReturnStatus(BSO151405Result bso151405Result) {
        logger.debug("更新退货单状态");
        // 更新退货单状态
        BSO151405Param bso151405Param = new BSO151405Param();
        bso151405Param.setReturnId(bso151405Result.getReturnId());
        bso151405Param.setReturnStatus(OrderCodeMasterDef.ReturnOrderStatus.PROCESS);
        bso151405Param.setUpdTime(DateTimeUtil.getCustomerDate());
        bso151405Param.setUpdId("BSO151405Batch");
        super.modify(SqlId.SQL_ID_UPDATE_RETURN_STATUS, bso151405Param);
        super.modify(SqlId.SQL_ID_UPDATE_SO_RETURN_STATUS, bso151405Param);
        // 插入退货单状态表
        SoReturnStatus soReturnStatus = new SoReturnStatus();
        Long statusId = commonLogic.maxId("SO_RETURN_STATUS", "STATUS_ID");
        soReturnStatus.setStatusId(statusId);
        soReturnStatus.setReturnId(bso151405Result.getReturnId());
        soReturnStatus.setReturnCode(bso151405Result.getReturnCode());
        soReturnStatus.setReturnStatus(OrderCodeMasterDef.ReturnOrderStatus.PROCESS);
        soReturnStatus.setCrtId("BSO151405Batch");
        soReturnStatus.setCrtTime(DateTimeUtil.getCustomerDate());
        super.save(SqlId.SQL_ID_SAVE_SO_RETURN_STATUS, soReturnStatus);
    }

    /**
     * 更新退货单明细状态
     *
     * @param bso151405Result bso152405Result
     */
    @Transactional
    private int updateReturnDetailStatus(BSO151405Result bso151405Result) {
        logger.debug("更新退货单明细状态");
        BSO151405Param bso151405Param = new BSO151405Param();
        bso151405Param.setReturnId(bso151405Result.getReturnId());
        bso151405Param.setReturnStatus(OrderCodeMasterDef.ReturnOrderStatus.PROCESS);
        bso151405Param.setUpdTime(DateTimeUtil.getCustomerDate());
        bso151405Param.setUpdId("BSO151405Batch");
        return super.modify(SqlId.SQL_ID_UPDATE_RETURN_DETAIL_STATUS, bso151405Param);
    }
}
