package com.msk.seller.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlEpCap;
import com.msk.core.entity.SlEpCert;
import com.msk.core.entity.SlEpCertItem;
import com.hoperun.core.exception.BusinessException;
import com.msk.seller.bean.ISL231127CertInfoList;
import com.msk.seller.bean.ISL231129RsResult;
import com.msk.seller.bean.SL24110302_1Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
@Service
public class SL24110103Logic extends BaseLogic{
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static  final String SQL_ID_UPDATE_SLEPCERTITEM="updateSlEpCertItem";
        static  final String SQL_ID_UPDATE_CERTITEM="updateCertItem";
        static  final String SQL_ID_DELETE_SLEPCERTITEM="deleteSlEpcertItem";
        static  final String SQL_ID_DELETE_SLEPCERT="deleteSlEpcert";
        static  final String SQL_ID_FIND_SLEPCERTLIST="findSlEpcertList";
        static  final String SQL_ID_FIND_SLEPCERTITEMLIST="findSlEpcertItemList";
        static  final String SQL_ID_REMOVE_SLEPCERTITEM="removeSlEpcertItem";
        static  final String SQL_ID_REMOVE_SLEPCERT="removeSlEpcert";
    }

    /**
     * 删除企业专业资质
     * @param certInfoList ISL231127CertInfoList集合
     * 需要先删除依赖关系表，在删除资质表
     */
    @Transactional
    public void deleteSlEpCertItem(List<ISL231127CertInfoList> certInfoList) {
        for(ISL231127CertInfoList isl231127CertInfoList : certInfoList){
            super.remove(SqlId.SQL_ID_REMOVE_SLEPCERTITEM,isl231127CertInfoList);
            super.remove(SqlId.SQL_ID_REMOVE_SLEPCERT,isl231127CertInfoList);
        }
    }
    /**
     * 查询企业的所有的专业资质证照
     * @param epId 企业id
     * @return ISL231129RsResult
     */
    @Transactional(readOnly = true)
    public ISL231129RsResult findListById(Integer epId) {
        ISL231129RsResult isl231129RsResult=new ISL231129RsResult();
        List<ISL231127CertInfoList> isl231127CertInfoLists=new ArrayList<ISL231127CertInfoList>();
        BaseParam base = new BaseParam();
        base.setFilter("epId",epId.toString());
        List<SlEpCert> slEpCertList=super.findList(SqlId.SQL_ID_FIND_SLEPCERTLIST,base);
        if(null!=slEpCertList){
            for(SlEpCert slEpCert : slEpCertList){
                ISL231127CertInfoList isl231127CertList= new ISL231127CertInfoList();
                isl231127CertList.setEpId(slEpCert.getEpId());
                isl231127CertList.setCertSeq(slEpCert.getCertSeq());
                isl231127CertList.setCertId(slEpCert.getCertId());
                isl231127CertList.setCertName(slEpCert.getCertName());
                BaseParam baseParam = new BaseParam();
                baseParam.setFilter("epId",epId.toString());
                baseParam.setFilter("certSeq",slEpCert.getCertSeq().toString());
                List<SlEpCertItem> slEpCertItemList = super.findList(SqlId.SQL_ID_FIND_SLEPCERTITEMLIST,baseParam);
                isl231127CertList.setCertItemList(slEpCertItemList);
                isl231127CertInfoLists.add(isl231127CertList);
            }
        }
        isl231129RsResult.setCertInfoList(isl231127CertInfoLists);
        return isl231129RsResult;
    }
    //更新数据sl_ep_cert_item
    @Transactional
    public void updateItem(List<SL24110302_1Bean> sl24110302_1Beans) {
        for(int i=0;i<sl24110302_1Beans.size();i++){
            sl24110302_1Beans.get(i).setUpdTime(DateTimeUtil.getCustomerDate());
            int result=super.modify(SqlId.SQL_ID_UPDATE_CERTITEM, sl24110302_1Beans.get(i));
            if(result==0){
                throw new BusinessException("更新数据失败");
            }
        }
    }

    //更新数据sl_ep_cert_item
    @Transactional
    public void updateCertItem(List<SL24110302_1Bean> sl24110302_1Beans) {
        for(int i=0;i<sl24110302_1Beans.size();i++){
            int result=super.modify(SqlId.SQL_ID_UPDATE_SLEPCERTITEM, sl24110302_1Beans.get(i));
            if(result==0){
                throw new BusinessException("更新数据失败");
            }
        }
    }

    /**
     * 删除slepcertItem 表中数据
     * @param lists 传值为slEPCertItem集合
     */
    @Transactional
    public void deleteDate(List<SL24110302_1Bean> lists) {
        for(int i=0;i<lists.size();i++) {
            super.remove(SqlId.SQL_ID_DELETE_SLEPCERTITEM, lists.get(i));
        }
    }

    /**
     * 删除SlEpCert表中数据，需要先删除slepcertitem表中的依赖
     * @param slEpCerts 删除参数列表集合
     */
    @Transactional
    public void deleteSlEpCert(List<SlEpCert> slEpCerts) {
        for(int i=0;i<slEpCerts.size();i++) {
            super.remove(SqlId.SQL_ID_DELETE_SLEPCERT, slEpCerts.get(i));
        }
    }

}
