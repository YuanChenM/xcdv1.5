package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.buyers.bean.BuyerLicenceBean;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.buyers.bean.IBY121205RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerLicence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * IBY121205Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121205Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121205Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家证照信息获取
        static String SQL_FIND_LINECE = "findLinece";
        //买家证照信息更新
        static String SQL_BUYER_LINECE_MODIFY = "buyerLineceModify";
        //买家证照信息插入
        static String SQL_BUYER_LINECE_INSERT = "buyerLineceInsert";
        //买家证照信息获取
        static String SQL_FIND_LINECE_LIST = "findLineceList";
    }
    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /*
     * 买家证照信息更新接口
     * @param param
     * @return
     */
    @Transactional
    public int buyerLicenceModify(BuyerLicenceBean param){
        //根据传入参数获取买家证照信息
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId", param.getBuyerId());
        inParam.setFilter("delFlg","0");
        //营业执照类型
        Map<String,String> LicTypeMap = CodeMasterManager.findCodeMasterMap("LicType");
        // 法定代表人证件类型
        Map<String,String> LegalLicTypeMap = CodeMasterManager.findCodeMasterMap("LegalLicType");
        IBY121205RsParam iby121205RsParam = super.findOne(SqlId.SQL_FIND_LINECE, inParam);
        //获取到数据则更新表
        if (null != iby121205RsParam) {
            iby121205RsParam.setLicDes(LicTypeMap.get(iby121205RsParam.getLicName()));
            iby121205RsParam.setLegalLicDes(LegalLicTypeMap.get(iby121205RsParam.getLegalLicType()));
            //licence.setLicDesc(LicTypeMap.get(licence.getLicName()));
            //licence.setLegalLicDesc(LegalLicTypeMap.get(licence.getLegalLicType()));
            param.setId(iby121205RsParam.getId());
            //等121307  BYBaseBuyerLicController更改统一修改
            param.setUpdTime(DateTimeUtil.getCustomerDate());
            return super.modify(SqlId.SQL_BUYER_LINECE_MODIFY,param);
        }else {
            //未获取到数据则插入表
            return buyerLicenceSave(param);
        }
    }
    /**
     * 买家证照信息插入
     * @param insertParam
     * @return
     */
    @Transactional
    public int  buyerLicenceSave(ByBuyerLicence insertParam){
        Long id = commonLogic.maxId("by_buyer_Licence","ID");
        insertParam.setId(id);
    /*    insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());*/
        return super.save(SqlId.SQL_BUYER_LINECE_INSERT,insertParam);
    }

    /**
     * 获取买家证照信息
     * @param insertParam
     * @return
     */
    @Transactional(readOnly = true)
    public ByBuyerLicence buyerLicenceFind(BuyerLicenceBean insertParam){
        //根据传入参数获取买家证照信息
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId", insertParam.getBuyerId());
        inParam.setFilter("delFlg","0");
        IBY121205RsParam licence = super.findOne(SqlId.SQL_FIND_LINECE, inParam);
        if(licence!=null){
            //营业执照类型
            Map<String, String> LicTypeMap = CodeMasterManager.findCodeMasterMap("LicType");
            // 法定代表人证件类型
            Map<String,String> LegalLicTypeMap = CodeMasterManager.findCodeMasterMap("LegalLicType");
            licence.setLicDes(LicTypeMap.get(licence.getLicName()));
            licence.setLegalLicDes(LegalLicTypeMap.get(licence.getLegalLicType()));
        }
        return licence;
    }

    /**
     * 接口获取买家证照信息
     * @return
     */
    @Transactional(readOnly = true)
    public List<BuyerLicenceBean> buyerLicenceList(BuyerRelationParam param){
        //根据传入参数获取买家证照信息
        //营业执照类型
        Map<String,String> LicTypeMap = CodeMasterManager.findCodeMasterMap("LicType");
        // 法定代表人证件类型
        Map<String, String> LegalLicTypeMap = CodeMasterManager.findCodeMasterMap("LegalLicType");
        List<BuyerLicenceBean> licenceList = super.findList(SqlId.SQL_FIND_LINECE_LIST, param);
        for (BuyerLicenceBean licence : licenceList){
            licence.setLicDes(LicTypeMap.get(licence.getLicName()));
            licence.setLegalLicDes(LegalLicTypeMap.get(licence.getLegalLicType()));
        }
        return licenceList;
    }
}
