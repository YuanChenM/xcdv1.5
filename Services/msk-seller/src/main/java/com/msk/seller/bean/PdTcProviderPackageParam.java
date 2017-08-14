package com.msk.seller.bean;

import com.msk.core.entity.PdTcProviderPackage;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Administrator on 2016/3/8.
 */
public class PdTcProviderPackageParam extends PdTcProviderPackage {

    //产品分类code,用来定位区分
    private String classestreeCode;

    //显示查看审核状态
    private String status;


    //申请时间转义
    private String applyDateTime;


    /**
     * Getter method for property <tt>applyDateTime</tt>.
     *
     * @return property value of applyDateTime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String getApplyDateTime() {
        return applyDateTime;
    }

    /**
     * Setter method for property <tt>applyDateTime</tt>.
     *
     * @param applyDateTime value to be assigned to property applyDateTime
     */
    public void setApplyDateTime(String applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    /**
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**

     * Getter method for property <tt>classestreeCode</tt>.
     *
     * @return property value of classestreeCode
     */
    public String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * Setter method for property <tt>classestreeCode</tt>.
     *
     * @param classestreeCode value to be assigned to property classestreeCode
     */
    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }


}
