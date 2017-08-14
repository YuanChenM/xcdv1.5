package com.msk.ds.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msk.common.base.BaseBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * xu_wei on 2016/03/24.
 */
public class ISC1892I1Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * DELIVERY_STOCK_ID
     */
    private Long deliveryStockId;

    private Long suppDsId;//供应商分销流水号
    private Long planFlowId;//虚拟库存计划流水号

    private String distMonth;//分销月度
    private String lgcsCode;//物流区编码
    private String suppCode;//供应商编码

    private String lgcsName;//给跳转到修改页面时传值用的冗余字段属性
    private String suppName;// 供应商名
    private String pdStockType;//产品库存类型
    private String pdStockTypeZ;
    private String halfCode;//半旬码
    private String halfCodeZ;
    private String halfCodeA;
    private String halfCodeB;

    private String classesCode;//产品类别编码
    private String breedCode;//产品品种编码
    private String featureCode;//产品特征编码
    private String gradeCode;//产品等级编码

    private String outSpec;//外包装规格
    private String outNw;//外包装净重
    private String pdCode;//产品编码
    private String normsCode;//包装编码

    private Date adjustDate;//调整日期
    private String oldPlanNum;//原计划数量
    private String newPlanNum;//现在计划数量

    public Long getDeliveryStockId() {
        return deliveryStockId;
    }

    public void setDeliveryStockId(Long deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    public Long getSuppDsId() {
        return suppDsId;
    }

    public void setSuppDsId(Long suppDsId) {
        this.suppDsId = suppDsId;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public Long getPlanFlowId() {
        return planFlowId;
    }

    public void setPlanFlowId(Long planFlowId) {
        this.planFlowId = planFlowId;
    }

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getPdStockType() {
        return pdStockType;
    }

    public void setPdStockType(String pdStockType) {
        this.pdStockType = pdStockType;
    }

    public String getPdStockTypeZ() {
        return pdStockTypeZ;
    }

    public void setPdStockTypeZ(String pdStockTypeZ) {
        this.pdStockTypeZ = pdStockTypeZ;
    }

    public String getHalfCode() {
        if (halfCode.equals("26-N日")) {
            String month = this.getDistMonth();
            String yearn = month.substring(0, 4);
            int yearm = Integer.parseInt(yearn);
            String monthN = month.substring(month.length() - 1, month.length());
            switch (monthN) {
                case "1":
                case "3":
                case "5":
                case "7":
                case "8":
                case "10":
                case "12":
                    halfCode = "26-31日";
                    break;
                case "2":
                    String x = isLeapYear(yearm) ? "29" : "28";
                    halfCode = "26-" + x + "日";
                    break;
                case "4":
                case "6":
                case "9":
                case "11":
                    halfCode = "26-30日";
                    break;
            }
            return halfCode;
        }
        return halfCode;
    }

    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

    public boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            }
        } else {
            if (year % 4 == 0) {
                return true;
            }
        }
        return false;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getOutSpec() {
        return outSpec;
    }

    public void setOutSpec(String outSpec) {
        this.outSpec = outSpec;
    }

    public String getOutNw() {
        return outNw;
    }

    public void setOutNw(String outNw) {
        this.outNw = outNw;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    public String getOldPlanNum() {
        return oldPlanNum;
    }

    public void setOldPlanNum(String oldPlanNum) {
        this.oldPlanNum = oldPlanNum;
    }

    public String getNewPlanNum() {
        return newPlanNum;
    }

    public void setNewPlanNum(String newPlanNum) {
        this.newPlanNum = newPlanNum;
    }


    public String getHalfCodeZ() {
        return halfCodeZ;
    }

    public void setHalfCodeZ(String halfCodeZ) {
        this.halfCodeZ = halfCodeZ;
    }

    public String getHalfCodeA() {
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(d);
        String A1 = date.substring(0, 8);
        String halfCodeZ = this.getHalfCodeZ();
        switch (halfCodeZ) {
            case "1":
                A1 = A1 + "21";
                break;
            case "2":
                A1 = A1 + "26";
                break;
            case "3":
                A1 = A1 + "01";
                break;
            case "4":
                A1 = A1 + "06";
                break;
            case "5":
                A1 = A1 + "11";
                break;
            case "6":
                A1 = A1 + "16";
                break;
        }

        return A1;
    }

    public void setHalfCodeA(String halfCodeA) {
        this.halfCodeA = halfCodeA;
    }

    public String getHalfCodeB() {
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(d);
        String B1 = date.substring(0, 8);
        String halfCodeZ = this.getHalfCodeZ();
        switch (halfCodeZ) {
            case "1":
                B1 = B1 + "25";
                break;
            case "2":
                B1 = B1 + "30";
                break;
            case "3":
                B1 = B1 + "05";
                break;
            case "4":
                B1 = B1 + "10";
                break;
            case "5":
                B1 = B1 + "15";
                break;
            case "6":
                B1 = B1 + "20";
                break;
        }

        return B1;
    }

    public void setHalfCodeB(String halfCodeB) {
        this.halfCodeB = halfCodeB;
    }
}
