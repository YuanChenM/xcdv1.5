package com.msk.product.bean;

import com.msk.common.base.BaseBean;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * PD141121Bean.
 * @author xhy
 */
public class PD141123Bean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String machiningRefId;//产品加工程度对照id

    private String classesCode;//类别编码

    private String machiningCode;//产品加工程度编码

    private String machiningName;//产品加工程度名称

    private String classesName;//产品类别名称

    private List<ProductBreed> pdBreedList;

    private int size;

    /**
     * @return the size
     */
    public int getSize() {
        if(!CollectionUtils.isEmpty(pdBreedList)){
            return pdBreedList.size();
        }
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获得pdBreedList
     */

    public List<ProductBreed> getPdBreedList() {
        return pdBreedList;
    }

    /**
     * 设置pdBreedList
     */
    public void setPdBreedList(List<ProductBreed> pdBreedList) {
        this.pdBreedList = pdBreedList;
    }

    /**
     * 获得classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * 设置classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * 获得machiningRefId
     */
    public String getMachiningRefId() {
        return machiningRefId;
    }

    /**
     * 设置machiningRefId
     */
    public void setMachiningRefId(String machiningRefId) {
        this.machiningRefId = machiningRefId;
    }

    /**
     * 获得classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * 设置classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * 获得machiningCode
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * 设置machiningCode
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * 获得machiningName
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * 设置machiningName
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }
}
