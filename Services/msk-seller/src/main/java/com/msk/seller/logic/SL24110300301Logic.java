package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEpCert;
import com.msk.core.entity.SlEpCertItem;
import com.msk.core.entity.SlMstCert;
import com.msk.core.entity.SlMstCertItem;
import com.hoperun.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
@Service
public class SL24110300301Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static final String SQL_ID_FIND_SLMSTCERTITEMLIST= "findSLMstCertItemList";
        static final String SQL_ID_FIND_SLMSTCERTNAME="findMstCertName";
        static final String SQL_ID_FIND_MAXCERTSEQ="findMaxCertSeq";
        static final String SQL_ID_FIND_MAXCERTITEMSEQ="findMaxCertItemSeq";
        static final String SQL_ID_SAVE_SLEPCERT="saveSlEpCert";
        static final String SQL_ID_SAVE_SLEPCERTITEM="saveSlEpCertItem";
        static final String SQL_ID_FIND_SLEPCERTBYID="findSlEpCertById";
    }
    //根据certId查询具体的slMstCertItem集合
    @Transactional(readOnly = true)
    public List<SlMstCertItem> findSLMstCertItemList(String certId) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("certId",certId);
        return  super.findList(SqlId.SQL_ID_FIND_SLMSTCERTITEMLIST,baseParam);
    }
    //根据certID 查询具体的certName值
    @Transactional(readOnly = true)
    public SlMstCert findCertNameByCertId(Long certId) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("certId",certId.toString());
        return super.findOne(SqlId.SQL_ID_FIND_SLMSTCERTNAME,baseParam);
    }

    /**
     *获取同一个epip下的最大的certsep号
     * @param epId
     * @return 最大的certseq+1
     */
    @Transactional(readOnly = true)
    public Long findMaxCertSeq(Long epId) {
        BaseParam base = new BaseParam();
        base.setFilter("epId",epId.toString());
        SlEpCert slEpCert=super.findOne(SqlId.SQL_ID_FIND_MAXCERTSEQ, base);
        return slEpCert.getCertSeq();
    }

    /**
     * 保存slEpCert
     */
    @Transactional
    public void saveSlEpCert(SlEpCert slEpCert) {
       slEpCert.setCrtTime(DateTimeUtil.getCustomerDate());
       super.save(SqlId.SQL_ID_SAVE_SLEPCERT,slEpCert);
    }

    /**
     * 保存slEpCertItems
     * @param slEpCertItems 需要保存的slEpCertItem集合
     */
    @Transactional
    public void saveSlEpCertItem(List<SlEpCertItem> slEpCertItems) {
        Date nowDate = DateTimeUtil.getCustomerDate();
        for(int i = 0;i<slEpCertItems.size();i++){
            BaseParam base = new BaseParam();
            base.setFilter("epId",slEpCertItems.get(i).getEpId().toString());
            base.setFilter("certSeq",slEpCertItems.get(i).getCertSeq().toString());
            SlEpCertItem slEpCertItem = super.findOne(SqlId.SQL_ID_FIND_MAXCERTITEMSEQ,base);
            slEpCertItems.get(i).setCertItemSeq(slEpCertItem.getCertItemSeq());
            slEpCertItems.get(i).setCrtTime(nowDate);
            int result=super.save(SqlId.SQL_ID_SAVE_SLEPCERTITEM, slEpCertItems.get(i));
            if(result==0){
                throw new BusinessException("保存数据失败");
            }
        }
    }

    /**
     *
     * @param epId 企业id
     * @param certId 证照ID
     * @return 返回SlEpCert
     */
    @Transactional(readOnly = true)
    public SlEpCert findSlEpCertById(Long epId, String certId) {
        BaseParam base = new BaseParam();
        base.setFilter("epId",epId.toString());
        base.setFilter("certId",certId);
        SlEpCert slEpCert=super.findOne(SqlId.SQL_ID_FIND_SLEPCERTBYID, base);
        return slEpCert;
    }


}
