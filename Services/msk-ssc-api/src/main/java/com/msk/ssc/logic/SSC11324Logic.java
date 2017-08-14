package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SscDeliveryPreInto;
import com.msk.core.entity.SscDeliveryPrePd;
import com.msk.ssc.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2016/8/3.
 */
@Service
public class SSC11324Logic extends BaseLogic{

    /**
     * Logger
     */

    private BaseDao baseDao;

    private Logger logger = LoggerFactory.getLogger(SSC11324Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 根据发票单号查询发票详细信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11324Bean InvoiceRequestDetail(SSC11324Param param){
        SSC11324Bean result = super.findOne(SqlId.SQL_ID_FIND_DELIVERY_CONFRIM, param);
        return result;
    }
    /**
     * 根据合同号查询合同信息,生成发票单详细信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11324Bean ContractFindInvoiceRequestDetail(SSC11324Param param){
        SSC11324Bean result = super.findOne(SqlId.SQL_CONTRACT_FIND_DELIVERY_CONFRIM, param);
        return result;
    }
    /**
     * 检测信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public Integer checkDeliveryOrder(SSC11324Param param){
        return (Integer)super.findObject(SqlId.SQL_ID_CHECK_INVOICE_REQUEST, param);
    }

    /**
     * 插入发票申请
     * @param ssc11324Param
     * @return
     */
    @Transactional
    public int insertInvoiceRequest(SSC11324Param ssc11324Param) {
        Long maxIntoId = commonLogic.maxId("ssc_invoice_request", "INVOICE_REQUEST_ID");
        ssc11324Param.setInvoiceRequestId(maxIntoId);
            logger.info("新增中标确认书详细信息表");
            return super.save(SqlId.SQL_ID_INSERT_INVOICE_REQUEST_DETAIL, ssc11324Param);

    }
    /**
     * 查找最大发票申请码
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public String findInvoiceRequestCode(BaseParam param) {
        return (String) super.findObject(SqlId.SQL_ID_FIND_INVOICE_REQUEST_CODE, param);
    }
    /**
     * 更新发票信息
     * @param param
     * @return
     */
    @Transactional
    public int modifyInvoiceRequestUp(SSC11324Param param){

        if(param.getInvoiceRequestId() != null && param.getInvoiceRequestId() != 0){
            super.versionValidator("SSC_INVOICE_REQUEST", new String[]{"INVOICE_REQUEST_ID"}, new Object[]{param.getInvoiceRequestId()}, param.getVer());
        }
        return super.modify(SqlId.SQL_ID_MODIFY_INVOICE_REQUEST, param);
    }
    /**
     * 查找最大发票申请码
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public  SSC11324Bean  findInvoiceRequestOne(BaseParam param) {
            return super.findOne(SqlId.SQL_ID_GET_MAX_INVOICE_REQUEST_CODE,param);
    }
    /**
     * 保存发票文件信息
     * @param param
     * @return
     */
    @Transactional
    public int saveInvoiceRequestFile(SSC11324Param param) {
        if(param.getInvoiceRequestId() != null && param.getInvoiceRequestId() != 0){
            super.versionValidator("SSC_INVOICE_REQUEST", new String[]{"INVOICE_REQUEST_ID"}, new Object[]{param.getInvoiceRequestId()}, param.getVer());
        }
        int updateCount = this.modify(SqlId.SQL_ID_SAVE_INVOICE_REQUEST_FILE, param);
        return updateCount;
    }
    /**
     * 查询合同金额相关列表
     * @param ssc11324Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11324Bean> findContractList(SSC11324Param ssc11324Param){
        List<SSC11324Bean> resultContractList = super.findList(SqlId.SQL_ID_FIND_CONTRACT_LIST, ssc11324Param);
        return resultContractList;
    }
    /**
     * 查询同一合同下所有发票金额相关列表
     * @param ssc11324Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11324Bean> findInvoiceList(SSC11324Param ssc11324Param){
        List<SSC11324Bean> invoiceList = super.findList(SqlId.SQL_ID_FIND_INVOICE_LIST, ssc11324Param);
        return invoiceList;
    }
    /**
     * 查询同一合同下未开票发票金额相关列表
     * @param ssc11324Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11324Bean> findNoInvoiceList(SSC11324Param ssc11324Param){
        List<SSC11324Bean> resultContractList = super.findList(SqlId.SQL_ID_FIND_NO_INVOICE_LIST, ssc11324Param);
        return resultContractList;
    }
    /**
     * 查询同一合同下,已支付金额列表
     * @param ssc11324Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11324Bean> findPayAmountList(SSC11324Param ssc11324Param){
        List<SSC11324Bean> resultContractList = super.findList(SqlId.SQL_ID_FIND_PAY_AMOUNT_LIST, ssc11324Param);
        return resultContractList;
    }
    /**
     * 查询弹出框合同
     * @param ssc11324Param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11324Bean> searchContractForInvoice(SSC11324Param ssc11324Param){
        List<SSC11324Bean> resultContractList = super.findList(SqlId.SQL_ID_INVOICE_FIND_CHOOSE_CONTRACT,ssc11324Param);
        return resultContractList;
    }



    interface SqlId {
        String SQL_ID_FIND_DELIVERY_CONFRIM = "InvoiceRequestDetail";//根据信息查询发票详细
        String SQL_CONTRACT_FIND_DELIVERY_CONFRIM = "ContractFindInvoiceRequestDetail";//根据合同编号查询合同详情新建发票
        String SQL_ID_CHECK_INVOICE_REQUEST="checkInvoiceRequest";//合同重复检测,未做
        String SQL_ID_INSERT_INVOICE_REQUEST_DETAIL = "insertInvoiceRequestDetail";
        String SQL_ID_FIND_INVOICE_REQUEST_CODE="findInvoiceRequestCode";
        String SQL_ID_MODIFY_INVOICE_REQUEST = "modifyInvoiceRequestUp";
        String SQL_ID_GET_MAX_INVOICE_REQUEST_CODE = "getMaxInvoiceRequestCode";//查找最大的发票申请码
        String SQL_ID_SAVE_INVOICE_REQUEST_FILE ="saveInvoiceRequestFile";
        String SQL_ID_FIND_CONTRACT_LIST ="findContractList";
        String SQL_ID_FIND_INVOICE_LIST="findInvoiceList";
        String SQL_ID_FIND_PAY_AMOUNT_LIST="findPayAmountList";
        String SQL_ID_FIND_NO_INVOICE_LIST="findNoInvoiceList";
        String SQL_ID_INVOICE_FIND_CHOOSE_CONTRACT = "InvoiceFindChooseContract";
    }
}
