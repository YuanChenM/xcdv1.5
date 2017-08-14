package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sun_jiaju on 2016/5/9.
 */
public class SP171104Param extends BaseParam {
    // 投标ID
    private long demandId;
    // 物流区CODE
    private String lgcsCode;
    // 物流区NAME
    private String lgcsName;
    // 投标年月周期
    private String demandYearMonth;
    // 填报时间 show
    private String demandLimitedDateShow;
    // 投标年月周期 show
    private String demandYearMonthShow;
    // 是否确认
    private int isConfirm;
    // 产品编码
    private String pdCode;
    // 产品名称
    private String pdName;
    // 产品一级分类名称
    private String classesName;
    // 产品二级分类名称
    private String machiningName;
    // 等级编码
    private String gradeCode;
    // 产品品种
    private String breedName;
    // 等级名称
    private String gradeName;
    // 特征
    private String featureName;
    // 备注
    private String remark;
    // 分配数量
    private BigDecimal passNum;
    // 是否同意档案卡
    private int isAgree;
    // 申报数量
    private BigDecimal applyNum;
    // 是否更新履历表
    private String updateFlg;
    // 企业名称
    private String epName;
    // 填报月
    private String nowMonth;
    //listtable用
    // 等级占比1
    private BigDecimal gradeRatio1;
    // 等级占比2
    private BigDecimal gradeRatio2;
    // 等级占比3
    private BigDecimal gradeRatio3;
    // 标准订单通道平衡系数
    private BigDecimal wayRatio1;
    // 大额订单通道平衡系数
    private BigDecimal wayRatio2;
    // 大宗订单通道平衡系数
    private BigDecimal wayRatio3;
    // 发布总数量
    private BigDecimal publishTotalNum;

    private BigDecimal a1Way3Num;
    private BigDecimal a1Way2Num;
    private BigDecimal a1Way1Num;
    private BigDecimal a1Num;;

    private BigDecimal a2Way3Num;
    private BigDecimal a2Way2Num;
    private BigDecimal a2Way1Num;
    private BigDecimal a2Num;;

    private BigDecimal a3Way3Num;
    private BigDecimal a3Way2Num;
    private BigDecimal a3Way1Num;
    private BigDecimal a3Num;

    private BigDecimal way3Num;
    private BigDecimal way2Num;
    private BigDecimal way1Num;
    // 是否超出填报时间
    private Boolean canSetFlg;

    public long getDemandId() {
        return demandId;
    }

    public void setDemandId(long demandId) {
        this.demandId = demandId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getDemandYearMonth() {
        return demandYearMonth;
    }

    public void setDemandYearMonth(String demandYearMonth) {
        this.demandYearMonth = demandYearMonth;
    }

    public String getDemandLimitedDateShow() {
        return demandLimitedDateShow;
    }

    public void setDemandLimitedDateShow(String demandLimitedDateShow) {
        this.demandLimitedDateShow = demandLimitedDateShow;
    }

    public String getDemandYearMonthShow() {
        return demandYearMonthShow;
    }

    public void setDemandYearMonthShow(String demandYearMonthShow) {
        this.demandYearMonthShow = demandYearMonthShow;
    }

    public int getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getPassNum() {
        return passNum;
    }

    public void setPassNum(BigDecimal passNum) {
        this.passNum = passNum;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public BigDecimal getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(BigDecimal applyNum) {
        this.applyNum = applyNum;
    }

    public String getUpdateFlg() {
        return updateFlg;
    }

    public void setUpdateFlg(String updateFlg) {
        this.updateFlg = updateFlg;
    }

    public BigDecimal getGradeRatio1() {
        return gradeRatio1;
    }

    public void setGradeRatio1(BigDecimal gradeRatio1) {
        this.gradeRatio1 = gradeRatio1;
    }

    public BigDecimal getGradeRatio2() {
        return gradeRatio2;
    }

    public void setGradeRatio2(BigDecimal gradeRatio2) {
        this.gradeRatio2 = gradeRatio2;
    }

    public BigDecimal getGradeRatio3() {
        return gradeRatio3;
    }

    public void setGradeRatio3(BigDecimal gradeRatio3) {
        this.gradeRatio3 = gradeRatio3;
    }

    public BigDecimal getWayRatio1() {
        return wayRatio1;
    }

    public void setWayRatio1(BigDecimal wayRatio1) {
        this.wayRatio1 = wayRatio1;
    }

    public BigDecimal getWayRatio2() {
        return wayRatio2;
    }

    public void setWayRatio2(BigDecimal wayRatio2) {
        this.wayRatio2 = wayRatio2;
    }

    public BigDecimal getWayRatio3() {
        return wayRatio3;
    }

    public void setWayRatio3(BigDecimal wayRatio3) {
        this.wayRatio3 = wayRatio3;
    }

    public BigDecimal getPublishTotalNum() {
        return publishTotalNum;
    }

    public void setPublishTotalNum(BigDecimal publishTotalNum) {
        this.publishTotalNum = publishTotalNum;
    }

    public BigDecimal getA1Way3Num() {
        return a1Way3Num;
    }

    public void setA1Way3Num(BigDecimal a1Way3Num) {
        this.a1Way3Num = a1Way3Num;
    }

    public BigDecimal getA1Way2Num() {
        return a1Way2Num;
    }

    public void setA1Way2Num(BigDecimal a1Way2Num) {
        this.a1Way2Num = a1Way2Num;
    }

    public BigDecimal getA1Way1Num() {
        return a1Way1Num;
    }

    public void setA1Way1Num(BigDecimal a1Way1Num) {
        this.a1Way1Num = a1Way1Num;
    }

    public BigDecimal getA1Num() {
        return a1Num;
    }

    public void setA1Num(BigDecimal a1Num) {
        this.a1Num = a1Num;
    }

    public BigDecimal getA2Way3Num() {
        return a2Way3Num;
    }

    public void setA2Way3Num(BigDecimal a2Way3Num) {
        this.a2Way3Num = a2Way3Num;
    }

    public BigDecimal getA2Way2Num() {
        return a2Way2Num;
    }

    public void setA2Way2Num(BigDecimal a2Way2Num) {
        this.a2Way2Num = a2Way2Num;
    }

    public BigDecimal getA2Way1Num() {
        return a2Way1Num;
    }

    public void setA2Way1Num(BigDecimal a2Way1Num) {
        this.a2Way1Num = a2Way1Num;
    }

    public BigDecimal getA2Num() {
        return a2Num;
    }

    public void setA2Num(BigDecimal a2Num) {
        this.a2Num = a2Num;
    }

    public BigDecimal getA3Way3Num() {
        return a3Way3Num;
    }

    public void setA3Way3Num(BigDecimal a3Way3Num) {
        this.a3Way3Num = a3Way3Num;
    }

    public BigDecimal getA3Way2Num() {
        return a3Way2Num;
    }

    public void setA3Way2Num(BigDecimal a3Way2Num) {
        this.a3Way2Num = a3Way2Num;
    }

    public BigDecimal getA3Way1Num() {
        return a3Way1Num;
    }

    public void setA3Way1Num(BigDecimal a3Way1Num) {
        this.a3Way1Num = a3Way1Num;
    }

    public BigDecimal getA3Num() {
        return a3Num;
    }

    public void setA3Num(BigDecimal a3Num) {
        this.a3Num = a3Num;
    }

    public BigDecimal getWay3Num() {
        return way3Num;
    }

    public void setWay3Num(BigDecimal way3Num) {
        this.way3Num = way3Num;
    }

    public BigDecimal getWay2Num() {
        return way2Num;
    }

    public void setWay2Num(BigDecimal way2Num) {
        this.way2Num = way2Num;
    }

    public BigDecimal getWay1Num() {
        return way1Num;
    }

    public void setWay1Num(BigDecimal way1Num) {
        this.way1Num = way1Num;
    }

    public Boolean getCanSetFlg() {
        return canSetFlg;
    }

    public void setCanSetFlg(Boolean canSetFlg) {
        this.canSetFlg = canSetFlg;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getNowMonth() {
        return nowMonth;
    }

    public void setNowMonth(String nowMonth) {
        this.nowMonth = nowMonth;
    }
}
