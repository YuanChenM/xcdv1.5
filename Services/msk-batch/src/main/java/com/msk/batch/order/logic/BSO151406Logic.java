package com.msk.batch.order.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.order.bean.BSO151406Param;
import com.msk.batch.order.bean.BSO151406Result;
import com.msk.batch.order.bean.BSO151406XMLTemplate;
import com.msk.common.base.BaseLogic;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.consts.StatusConst;
import com.msk.common.xml.JaxbXmlWrite;
import com.msk.utils.WmsFtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;

/**
 * 生成发货取消单logic
 *
 * @author sunjiaju on 2016/8/18.
 */
@Service
public class BSO151406Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSO151406Logic.class);

    /**
     * SQL Map 中SQL ID定义
     */
    interface SqlId {
        static final String SQLID_FIND_SHIP_LIST = "findShipList";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 生成发货取消单
     *
     * @param bso151406Param param
     */
    @Transactional
    public void createCancelXml(BSO151406Param bso151406Param) {
        logger.debug("发货单取消");
        List<BSO151406Result> bso151406Results = super.findList(SqlId.SQLID_FIND_SHIP_LIST, bso151406Param);
        if (!CollectionUtils.isEmpty(bso151406Results)) {
            for (BSO151406Result bso151406Result : bso151406Results) {
                writeXml(bso151406Result);
            }
        }
    }

    /**
     * 生成xml并更新发货表状态
     *
     * @param bso151406Result bso152406Result
     */
    @Transactional
    private void writeXml(BSO151406Result bso151406Result) {
        BSO151406Param param = new BSO151406Param();
        param.setShipId(bso151406Result.getShipId());
        param.setShipStatus(OrderCodeMasterDef.ShipStatus.CANCELING);
        param.setUpdId("BSO151406Batch");
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        // 更新订单发货主表
        super.modify(param);

        // writeXML
        BSO151406XMLTemplate xmlTemplate = new BSO151406XMLTemplate();
        xmlTemplate.setData(bso151406Result);
        JaxbXmlWrite<BSO151406Result> xmlWrite = new JaxbXmlWrite<BSO151406Result>(xmlTemplate);
        InputStream inputStream = xmlWrite.createEntityTemplateXml(BSO151406XMLTemplate.class);
        boolean result = WmsFtpUtils.uploadXml("/", "OC" + bso151406Result.getShipCode() + ".xml", inputStream);
        if (logger.isDebugEnabled()) {
            if (result) {
                logger.debug("OC" + bso151406Result.getShipCode() + ".xml" + "文件上传成功");
            } else {
                logger.debug("OC" + bso151406Result.getShipCode() + ".xml" + "文件上传失败");
            }
        }
    }
}
