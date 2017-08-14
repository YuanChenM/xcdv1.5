package com.msk.inventory.bean;

import com.msk.comm.exception.BusinessException;
import com.msk.inventory.entity.IvmPartsMaster;
import org.springframework.util.StringUtils;

/**
 * Created by duan_kai on 2016/8/12.
 */
public class IvmPartsMasterBean extends IvmPartsMaster {

    private String classesCode;
    private String machiningCode;
    private String breedCode;
    private String featureCode;
    private String weightCode;
    private String gradeCode;
    private String slCode;
    private String slPdCode;
    private String skuCode;
    private String pdCode;

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
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

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlPdCode() {
        return slPdCode;
    }

    public void setSlPdCode(String slPdCode) {
        this.slPdCode = slPdCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        if (StringUtils.isEmpty(skuCode)) {
            this.setSlCode(null);
            this.setSlPdCode(null);
        } else {
            if (skuCode.length() != 12) {
                throw new BusinessException("skuCode不符合规范！");
            }
            this.setSlCode(skuCode.substring(0, 7));
            this.setSlPdCode(skuCode.substring(7, 12));
        }
        this.skuCode = skuCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) throws Exception {
        if (StringUtils.isEmpty(pdCode)) {
            this.setClassesCode(null);
            this.setMachiningCode(null);
            this.setBreedCode(null);
            this.setFeatureCode(null);
            this.setWeightCode(null);
            this.setGradeCode(null);
        } else {
            if (pdCode.length() != 10) {
                throw new BusinessException("pdCode不符合规范！");
            }
            this.setClassesCode(pdCode.substring(0, 2));
            this.setMachiningCode(pdCode.substring(2, 3));
            this.setBreedCode(pdCode.substring(3, 5));
            this.setFeatureCode(pdCode.substring(5, 7));
            this.setWeightCode(pdCode.substring(7, 9));
            this.setGradeCode(pdCode.substring(9, 10));
        }
        this.pdCode = pdCode;
    }

    @Override
    public String getPmExternalXml() {
        String tempStr = (StringUtils.isEmpty(this.classesCode) ? "" : "<CLS_C>" + this.classesCode + "</CLS_C>")
                + (StringUtils.isEmpty(this.machiningCode) ? "" : "<MACH_C>" + this.machiningCode + "</MACH_C>")
                + (StringUtils.isEmpty(this.breedCode) ? "" : "<BRE_C>" + this.breedCode + "</BRE_C>")
                + (StringUtils.isEmpty(this.featureCode) ? "" : "<FEAT_C>" + this.featureCode + "</FEAT_C>")
                + (StringUtils.isEmpty(this.weightCode) ? "" : "<WEI_C>" + this.weightCode + "</WEI_C>")
                + (StringUtils.isEmpty(this.gradeCode) ? "" : "<GRAD_C>" + this.gradeCode + "</GRAD_C>")
                + (StringUtils.isEmpty(this.slCode) ? "" : "<SL_C>" + this.slCode + "</SL_C>")
                + (StringUtils.isEmpty(this.slPdCode) ? "" : "<SL_P_C>" + this.slPdCode + "</SL_P_C>");
        return tempStr;
    }

    @Override
    public String getPmCode() {
        String tempStr = (StringUtils.isEmpty(this.classesCode) ? "--" : this.classesCode)
                + (StringUtils.isEmpty(this.machiningCode) ? "-" : this.machiningCode)
                + (StringUtils.isEmpty(this.breedCode) ? "--" : this.breedCode)
                + (StringUtils.isEmpty(this.featureCode) ? "--" : this.featureCode)
                + (StringUtils.isEmpty(this.weightCode) ? "--" : this.weightCode)
                + (StringUtils.isEmpty(this.gradeCode) ? "-" : this.gradeCode)
                + (StringUtils.isEmpty(this.slCode) ? "-------" : this.slCode)
                + (StringUtils.isEmpty(this.slPdCode) ? "-----" : this.slPdCode);
        return tempStr;
    }

}
