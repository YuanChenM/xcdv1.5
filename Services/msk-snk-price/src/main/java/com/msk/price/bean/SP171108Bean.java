package com.msk.price.bean;

import com.msk.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * Created by XU_WEI
 */
public class SP171108Bean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    //序号
    private  Integer idCode;
    //投标ID
    private String demandId;
    //编辑时间
    private String editTime;
    //编辑人
    private String roleCode;
    //编辑人姓名
    private String roleName;
    //等级编码
    private String gradeCode;
    //等级名称
    private String gradeName;
    //申报数量
    private BigDecimal applyNum;
    //建议数量
    private String modifyNum;
    //备注
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer idCode) {
        this.idCode = idCode;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public BigDecimal getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(BigDecimal applyNum) {
        this.applyNum = applyNum;
    }

    public String getModifyNum() {
        return modifyNum;
    }

    public void setModifyNum(String modifyNum) {
        this.modifyNum = modifyNum;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
