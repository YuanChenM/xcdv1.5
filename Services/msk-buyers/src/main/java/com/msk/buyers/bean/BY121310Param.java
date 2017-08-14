package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.PdMachining;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 买家买家池归属
 * Created by tao_zhifa on 2016/7/11.
 */
public class BY121310Param extends BasePageParam {
    private static final long serialVersionUID = 1L;
    /** HISTORY_ID */
    private Long historyId;

    /** OLD_STATUS_CLASS */
    private String oldStatusClass;
    /** OLD_STATUS_CLASS_NAME */
    private String oldStatusClassName;
    /** OLD_STATUS_BREED */
    private String oldStatusBreed;
    /** OLD_STATUS_BREED_NAME */
    private String oldStatusBreedName;
    /** OLD_EXCEPTION_STATUS */
    private String oldExceptionStatus;
    /** OLD_EXCEPTION_STATUS_NAME */
    private String oldExceptionStatusName;
    /** NEW_STATUS_CLASS */
    private String newStatusClass;
    /** NEW_STATUS_CLASS_NAME */
    private String newStatusClassName;
    /** NEW_STATUS_BREED */
    private String newStatusBreed;
    /** NEW_STATUS_BREED_NAME */
    private String newStatusBreedName;
    /** NEW_EXCEPTION_STATUS */
    private String newExceptionStatus;
    /** NEW_EXCEPTION_STATUS_NAME */
    private String newExceptionStatusName;
    /** 1:当前最新状态0:履历数据 */
    private String currentStatusFlg;
    /** MODIFY_TIME */
    private java.util.Date modifyTime;
    private Long Id;
    private String cityCode;
    private String lgcsAreaCode;
    private String superiorType;
    private String buyerId;
    private String classCode;
    private String className;
    private String machiningCode;
    private String machiningName;
    private Map<String,String> machiningMap;
    private Map<String,String> classesMap;

    private List<PdMachining> pdMachiningList;
    private String machiningCodeU;
    private String crtId;
    private Date crtTime;
    private String updId;
    private Date updTime;
    private String actId;
    private Date actTime;
    /** BUYER_CODE */
    private String buyerCode;
    private String marketExceptionStatus;
    // 判断颜色
    private String flag;
    /** 画面选中的销售产品 */
    private String[] buyerPdCla;
    /**画面选中的销售二级 */
    private String[] buyerPdMac;
    /** 是否选择 */
    private String isChecked;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public String getOldStatusClass() {
        return oldStatusClass;
    }

    public void setOldStatusClass(String oldStatusClass) {
        this.oldStatusClass = oldStatusClass;
    }

    public String getOldStatusClassName() {
        return oldStatusClassName;
    }

    public void setOldStatusClassName(String oldStatusClassName) {
        this.oldStatusClassName = oldStatusClassName;
    }

    public String getOldStatusBreed() {
        return oldStatusBreed;
    }

    public void setOldStatusBreed(String oldStatusBreed) {
        this.oldStatusBreed = oldStatusBreed;
    }

    public String getOldStatusBreedName() {
        return oldStatusBreedName;
    }

    public void setOldStatusBreedName(String oldStatusBreedName) {
        this.oldStatusBreedName = oldStatusBreedName;
    }

    public String getOldExceptionStatus() {
        return oldExceptionStatus;
    }

    public void setOldExceptionStatus(String oldExceptionStatus) {
        this.oldExceptionStatus = oldExceptionStatus;
    }

    public String getOldExceptionStatusName() {
        return oldExceptionStatusName;
    }

    public void setOldExceptionStatusName(String oldExceptionStatusName) {
        this.oldExceptionStatusName = oldExceptionStatusName;
    }

    public String getNewStatusClass() {
        return newStatusClass;
    }

    public void setNewStatusClass(String newStatusClass) {
        this.newStatusClass = newStatusClass;
    }

    public String getNewStatusClassName() {
        return newStatusClassName;
    }

    public void setNewStatusClassName(String newStatusClassName) {
        this.newStatusClassName = newStatusClassName;
    }

    public String getNewStatusBreed() {
        return newStatusBreed;
    }

    public void setNewStatusBreed(String newStatusBreed) {
        this.newStatusBreed = newStatusBreed;
    }

    public String getNewStatusBreedName() {
        return newStatusBreedName;
    }

    public void setNewStatusBreedName(String newStatusBreedName) {
        this.newStatusBreedName = newStatusBreedName;
    }

    public String getNewExceptionStatus() {
        return newExceptionStatus;
    }

    public void setNewExceptionStatus(String newExceptionStatus) {
        this.newExceptionStatus = newExceptionStatus;
    }

    public String getNewExceptionStatusName() {
        return newExceptionStatusName;
    }

    public void setNewExceptionStatusName(String newExceptionStatusName) {
        this.newExceptionStatusName = newExceptionStatusName;
    }

    public String getCurrentStatusFlg() {
        return currentStatusFlg;
    }

    public void setCurrentStatusFlg(String currentStatusFlg) {
        this.currentStatusFlg = currentStatusFlg;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSuperiorType() {
        return superiorType;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public void setSuperiorType(String superiorType) {
        this.superiorType = superiorType;
    }

    public List<PdMachining> getPdMachiningList() {
        return pdMachiningList;
    }

    public String getMarketExceptionStatus() {
        return marketExceptionStatus;
    }

    public void setMarketExceptionStatus(String marketExceptionStatus) {
        this.marketExceptionStatus = marketExceptionStatus;
    }

    public void setPdMachiningList(List<PdMachining> pdMachiningList) {
        this.pdMachiningList = pdMachiningList;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getCityCode() {

        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Map<String, String> getMachiningMap() {
        return machiningMap;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setMachiningMap(Map<String, String> machiningMap) {
        this.machiningMap = machiningMap;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Map<String, String> getClassesMap() {
        return classesMap;
    }

    public void setClassesMap(Map<String, String> classesMap) {
        this.classesMap = classesMap;
    }

    public String[] getBuyerPdCla() {
        return buyerPdCla;
    }

    public void setBuyerPdCla(String[] buyerPdCla) {
        this.buyerPdCla = buyerPdCla;
    }

    public String[] getBuyerPdMac() {
        return buyerPdMac;
    }

    public void setBuyerPdMac(String[] buyerPdMac) {
        this.buyerPdMac = buyerPdMac;
    }



    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    @Override
    public String getCrtId() {
        return crtId;
    }

    @Override
    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

    @Override
    public Date getCrtTime() {
        return crtTime;
    }

    @Override
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    @Override
    public String getUpdId() {
        return updId;
    }

    @Override
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    @Override
    public Date getUpdTime() {
        return updTime;
    }

    @Override
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    @Override
    public String getActId() {
        return actId;
    }

    @Override
    public void setActId(String actId) {
        this.actId = actId;
    }

    @Override
    public Date getActTime() {
        return actTime;
    }

    @Override
    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }
}
