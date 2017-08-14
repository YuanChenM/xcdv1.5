package com.msk.seller.bean;

/**
 * rwf
 * 企业车间bean
 */
import com.msk.common.base.BaseBean;

/**
 * 企业车间
 * 
 */
public class SL2411030303Bean extends BaseBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    // 企业ID
    private Integer epId;
    // 车间ID
    private Integer workshopId;
    // 车间名称
    private String workshopName;
    // 生产产品
    private String product;
    // 工艺流程特点
    private String process;
    // 资质描述
    private String workshopDesc;
    //车间图片路径
    private String imgUrl;

    /**
     * 获得imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Get the epId.
     *
     * @return epId
     *
     * @author rwf
     */
    public Integer getEpId() {
        return this.epId;
    }

    /**
     * Set the epId.
     *
     * @param epId epId
     *
     * @author rwf
     */
    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    /**
     * Get the workshopId.
     *
     * @return workshopId
     *
     * @author rwf
     */
    public Integer getWorkshopId() {
        return this.workshopId;
    }

    /**
     * Set the workshopId.
     *
     * @param workshopId workshopId
     *
     * @author rwf
     */
    public void setWorkshopId(Integer workshopId) {
        this.workshopId = workshopId;
    }

    /**
     * Get the product.
     *
     * @return product
     *
     * @author rwf
     */
    public String getProduct() {
        return this.product;
    }

    /**
     * Set the product.
     *
     * @param product product
     *
     * @author rwf
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Get the process.
     *
     * @return process
     *
     * @author rwf
     */
    public String getProcess() {
        return this.process;
    }

    /**
     * Set the process.
     *
     * @param process process
     *
     * @author rwf
     */
    public void setProcess(String process) {
        this.process = process;
    }


    /**
     * Get the workshopName.
     *
     * @return workshopName
     *
     * @author rwf
     */
    public String getWorkshopName() {
        return this.workshopName;
    }

    /**
     * Set the workshopName.
     *
     * @param workshopName workshopName
     *
     * @author rwf
     */
    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    /**
     * Get the workshopDesc.
     *
     * @return workshopDesc
     *
     * @author rwf
     */
    public String getWorkshopDesc() {
        return this.workshopDesc;
    }

    /**
     * Set the workshopDesc.
     *
     * @param workshopDesc workshopDesc
     *
     * @author rwf
     */
    public void setWorkshopDesc(String workshopDesc) {
        this.workshopDesc = workshopDesc;
    }


}
