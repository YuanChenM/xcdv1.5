package com.msk.product.bean;


import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * PD141147Bean.
 *
 * @author zhou_ling
 */
@ApiModel(value = "PD141147Bean", description = "bean")
public class PD141147Bean extends BaseEntity {

    private String fedGoodValFileInfo1;
    private String fedGoodValFileInfo2;
    private String fedGoodValFileInfo3;
    private String fedGoodValFileInfo4;

    private String fedGoodValFileType1;
    private String fedGoodValFileType2;
    private String fedGoodValFileType3;
    private String fedGoodValFileType4;

    private String fedNormalValFileType1;
    private String fedNormalValFileType2;
    private String fedNormalValFileType3;
    private String fedNormalValFileType4;


    private String fedBadValFileType1;
    private String fedBadValFileType2;
    private String fedBadValFileType3;
    private String fedBadValFileType4;

    private String fGoodValFile1;
    private String fGoodValFile2;
    private String fGoodValFile3;
    private String fGoodValFile4;

    private String fNormalValFile1;
    private String fNormalValFile2;
    private String fNormalValFile3;
    private String fNormalValFile4;

    private String fBadValFile2;
    private String fBadValFile1;
    private String fBadValFile3;
    private String fBadValFile4;

    private MultipartFile[] fedGoodValFile1;
    private MultipartFile[] fedGoodValFile2;
    private MultipartFile[] fedGoodValFile3;
    private MultipartFile[] fedGoodValFile4;

    private MultipartFile[] fedNormalValFile1;
    private MultipartFile[] fedNormalValFile2;
    private MultipartFile[] fedNormalValFile3;
    private MultipartFile[] fedNormalValFile4;

    private MultipartFile[] fedBadValFile1;
    private MultipartFile[] fedBadValFile2;
    private MultipartFile[] fedBadValFile3;
    private MultipartFile[] fedBadValFile4;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品标准ID")
    private Long standardId;

    @ApiModelProperty(value = "饲养标准项目ID")
    private String fedStdItemId;

    @ApiModelProperty(value = "饲养标准项目名称")
    private String fedStdItemName;

    @ApiModelProperty(value = "优良")
    private String fedGoodVal;

    @ApiModelProperty(value = "一般")
    private String fedNormalVal;

    @ApiModelProperty(value = "差")
    private String fedBadVal;

    private List<MultipartFile[]> fedGoodImgList;

    @ApiModelProperty(value = "优良")
    private MultipartFile[] fedGoodImg;

    @ApiModelProperty(value = "一般")
    private MultipartFile[] fedNormalImg;

    @ApiModelProperty(value = "差")
    private MultipartFile[] fedBadImg;

    private String[] fedGoodUrl;
    private String[] fedNormalUrl;
    private String[] fedBadUrl;

    private List<PD14114701Bean> urls;

    @ApiModelProperty(value = "保存的饲养标准项目id")
    private String [] fedStdItemIdArray;

    @ApiModelProperty(value = "保存优良值")
    private String [] fedGoodValArray;

    @ApiModelProperty(value = "保存一般值")
    private String [] fedNormalValArray;

    @ApiModelProperty(value = "差保存值")
    private String [] fedBadValArray;

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    /**
     * Getter method for property <tt>fedStdItemId</tt>.
     *
     * @return property value of fedStdItemId
     */
    public String getFedStdItemId() {
        return fedStdItemId;
    }

    /**
     * Setter method for property <tt>fedStdItemId</tt>.
     *
     * @param fedStdItemId value to be assigned to property fedStdItemId
     */
    public void setFedStdItemId(String fedStdItemId) {
        this.fedStdItemId = fedStdItemId;
    }

    /**
     * Getter method for property <tt>fedStdItemName</tt>.
     *
     * @return property value of fedStdItemName
     */
    public String getFedStdItemName() {
        return fedStdItemName;
    }

    /**
     * Setter method for property <tt>fedStdItemName</tt>.
     *
     * @param fedStdItemName value to be assigned to property fedStdItemName
     */
    public void setFedStdItemName(String fedStdItemName) {
        this.fedStdItemName = fedStdItemName;
    }

    /**
     * Getter method for property <tt>fedGoodVal</tt>.
     *
     * @return property value of fedGoodVal
     */
    public String getFedGoodVal() {
        return fedGoodVal;
    }

    /**
     * Setter method for property <tt>fedGoodVal</tt>.
     *
     * @param fedGoodVal value to be assigned to property fedGoodVal
     */
    public void setFedGoodVal(String fedGoodVal) {
        this.fedGoodVal = fedGoodVal;
    }

    /**
     * Getter method for property <tt>fedNormalVal</tt>.
     *
     * @return property value of fedNormalVal
     */
    public String getFedNormalVal() {
        return fedNormalVal;
    }

    /**
     * Setter method for property <tt>fedNormalVal</tt>.
     *
     * @param fedNormalVal value to be assigned to property fedNormalVal
     */
    public void setFedNormalVal(String fedNormalVal) {
        this.fedNormalVal = fedNormalVal;
    }

    /**
     * Getter method for property <tt>fedBadVal</tt>.
     *
     * @return property value of fedBadVal
     */
    public String getFedBadVal() {
        return fedBadVal;
    }


    private String goodVal;

    private String normalVal;

    private String badVal;

    /**
     * Setter method for property <tt>fedBadVal</tt>.
     *
     * @param fedBadVal value to be assigned to property fedBadVal
     */
    public void setFedBadVal(String fedBadVal) {
        this.fedBadVal = fedBadVal;
    }

    /**
     * Getter method for property <tt>fedStdItemIdArray</tt>.
     *
     * @return property value of fedStdItemIdArray
     */
    public String[] getFedStdItemIdArray() {
        return fedStdItemIdArray;
    }

    /**
     * Setter method for property <tt>fedStdItemIdArray</tt>.
     *
     * @param fedStdItemIdArray value to be assigned to property fedStdItemIdArray
     */
    public void setFedStdItemIdArray(String[] fedStdItemIdArray) {
        this.fedStdItemIdArray = fedStdItemIdArray;
    }

    /**
     * Getter method for property <tt>fedGoodValArray</tt>.
     *
     * @return property value of fedGoodValArray
     */
    public String[] getFedGoodValArray() {
        return fedGoodValArray;
    }

    /**
     * Setter method for property <tt>fedGoodValArray</tt>.
     *
     * @param fedGoodValArray value to be assigned to property fedGoodValArray
     */
    public void setFedGoodValArray(String[] fedGoodValArray) {
        this.fedGoodValArray = fedGoodValArray;
    }

    /**
     * Getter method for property <tt>fedNormalValArray</tt>.
     *
     * @return property value of fedNormalValArray
     */
    public String[] getFedNormalValArray() {
        return fedNormalValArray;
    }

    /**
     * Setter method for property <tt>fedNormalValArray</tt>.
     *
     * @param fedNormalValArray value to be assigned to property fedNormalValArray
     */
    public void setFedNormalValArray(String[] fedNormalValArray) {
        this.fedNormalValArray = fedNormalValArray;
    }

    /**
     * Getter method for property <tt>fedBadValArray</tt>.
     *
     * @return property value of fedBadValArray
     */
    public String[] getFedBadValArray() {
        return fedBadValArray;
    }

    /**
     * Setter method for property <tt>fedBadValArray</tt>.
     *
     * @param fedBadValArray value to be assigned to property fedBadValArray
     */
    public void setFedBadValArray(String[] fedBadValArray) {
        this.fedBadValArray = fedBadValArray;
    }

    public String getGoodVal() {
        return goodVal;
    }

    public void setGoodVal(String goodVal) {
        this.goodVal = goodVal;
    }

    public String getNormalVal() {
        return normalVal;
    }

    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    public String getBadVal() {
        return badVal;
    }

    public void setBadVal(String badVal) {
        this.badVal = badVal;
    }






    public MultipartFile[] getFedGoodImg() {
        return fedGoodImg;
    }

    public void setFedGoodImg(MultipartFile[] fedGoodImg) {
        this.fedGoodImg = fedGoodImg;
    }

    public MultipartFile[] getFedNormalImg() {
        return fedNormalImg;
    }

    public void setFedNormalImg(MultipartFile[] fedNormalImg) {
        this.fedNormalImg = fedNormalImg;
    }

    public MultipartFile[] getFedBadImg() {
        return fedBadImg;
    }

    public void setFedBadImg(MultipartFile[] fedBadImg) {
        this.fedBadImg = fedBadImg;
    }


    public String[] getFedGoodUrl() {
        return fedGoodUrl;
    }

    public void setFedGoodUrl(String[] fedGoodUrl) {
        this.fedGoodUrl = fedGoodUrl;
    }

    public String[] getFedNormalUrl() {
        return fedNormalUrl;
    }

    public void setFedNormalUrl(String[] fedNormalUrl) {
        this.fedNormalUrl = fedNormalUrl;
    }

    public String[] getFedBadUrl() {
        return fedBadUrl;
    }

    public void setFedBadUrl(String[] fedBadUrl) {
        this.fedBadUrl = fedBadUrl;
    }

    public List<PD14114701Bean> getUrls() {
        return urls;
    }

    public void setUrls(List<PD14114701Bean> urls) {
        this.urls = urls;
    }

   /* public MultipartFile getFedGoodValFile1() {
        return fedGoodValFile1;
    }

    public void setFedGoodValFile1(MultipartFile fedGoodValFile1) {
        this.fedGoodValFile1 = fedGoodValFile1;
    }

    public MultipartFile getFedGoodValFile2() {
        return fedGoodValFile2;
    }

    public void setFedGoodValFile2(MultipartFile fedGoodValFile2) {
        this.fedGoodValFile2 = fedGoodValFile2;
    }

    public MultipartFile getFedGoodValFile3() {
        return fedGoodValFile3;
    }

    public void setFedGoodValFile3(MultipartFile fedGoodValFile3) {
        this.fedGoodValFile3 = fedGoodValFile3;
    }

    public MultipartFile getFedGoodValFile4() {
        return fedGoodValFile4;
    }

    public void setFedGoodValFile4(MultipartFile fedGoodValFile4) {
        this.fedGoodValFile4 = fedGoodValFile4;
    }

    public MultipartFile getFedNormalValFile1() {
        return fedNormalValFile1;
    }

    public void setFedNormalValFile1(MultipartFile fedNormalValFile1) {
        this.fedNormalValFile1 = fedNormalValFile1;
    }

    public MultipartFile getFedNormalValFile2() {
        return fedNormalValFile2;
    }

    public void setFedNormalValFile2(MultipartFile fedNormalValFile2) {
        this.fedNormalValFile2 = fedNormalValFile2;
    }

    public MultipartFile getFedNormalValFile3() {
        return fedNormalValFile3;
    }

    public void setFedNormalValFile3(MultipartFile fedNormalValFile3) {
        this.fedNormalValFile3 = fedNormalValFile3;
    }

    public MultipartFile getFedNormalValFile4() {
        return fedNormalValFile4;
    }

    public void setFedNormalValFile4(MultipartFile fedNormalValFile4) {
        this.fedNormalValFile4 = fedNormalValFile4;
    }

    public MultipartFile getFedbadValFile1() {
        return fedbadValFile1;
    }

    public void setFedbadValFile1(MultipartFile fedbadValFile1) {
        this.fedbadValFile1 = fedbadValFile1;
    }

    public MultipartFile getFedbadValFile2() {
        return fedbadValFile2;
    }

    public void setFedbadValFile2(MultipartFile fedbadValFile2) {
        this.fedbadValFile2 = fedbadValFile2;
    }

    public MultipartFile getFedbadValFile3() {
        return fedbadValFile3;
    }

    public void setFedbadValFile3(MultipartFile fedbadValFile3) {
        this.fedbadValFile3 = fedbadValFile3;
    }

    public MultipartFile getFedbadValFile4() {
        return fedbadValFile4;
    }

    public void setFedbadValFile4(MultipartFile fedbadValFile4) {
        this.fedbadValFile4 = fedbadValFile4;
    }
*/
    public List<MultipartFile[]> getFedGoodImgList() {
        return fedGoodImgList;
    }

    public void setFedGoodImgList(List<MultipartFile[]> fedGoodImgList) {
        this.fedGoodImgList = fedGoodImgList;
    }


    public MultipartFile[] getFedGoodValFile1() {
        return fedGoodValFile1;
    }

    public void setFedGoodValFile1(MultipartFile[] fedGoodValFile1) {
        this.fedGoodValFile1 = fedGoodValFile1;
    }

    public MultipartFile[] getFedGoodValFile2() {
        return fedGoodValFile2;
    }

    public void setFedGoodValFile2(MultipartFile[] fedGoodValFile2) {
        this.fedGoodValFile2 = fedGoodValFile2;
    }

    public MultipartFile[] getFedGoodValFile3() {
        return fedGoodValFile3;
    }

    public void setFedGoodValFile3(MultipartFile[] fedGoodValFile3) {
        this.fedGoodValFile3 = fedGoodValFile3;
    }

    public MultipartFile[] getFedGoodValFile4() {
        return fedGoodValFile4;
    }

    public void setFedGoodValFile4(MultipartFile[] fedGoodValFile4) {
        this.fedGoodValFile4 = fedGoodValFile4;
    }

    public MultipartFile[] getFedNormalValFile1() {
        return fedNormalValFile1;
    }

    public void setFedNormalValFile1(MultipartFile[] fedNormalValFile1) {
        this.fedNormalValFile1 = fedNormalValFile1;
    }

    public MultipartFile[] getFedNormalValFile2() {
        return fedNormalValFile2;
    }

    public void setFedNormalValFile2(MultipartFile[] fedNormalValFile2) {
        this.fedNormalValFile2 = fedNormalValFile2;
    }

    public MultipartFile[] getFedNormalValFile3() {
        return fedNormalValFile3;
    }

    public void setFedNormalValFile3(MultipartFile[] fedNormalValFile3) {
        this.fedNormalValFile3 = fedNormalValFile3;
    }

    public MultipartFile[] getFedNormalValFile4() {
        return fedNormalValFile4;
    }

    public void setFedNormalValFile4(MultipartFile[] fedNormalValFile4) {
        this.fedNormalValFile4 = fedNormalValFile4;
    }



    public String getfGoodValFile1() {
        return fGoodValFile1;
    }

    public void setfGoodValFile1(String fGoodValFile1) {
        this.fGoodValFile1 = fGoodValFile1;
    }

    public String getfGoodValFile2() {
        return fGoodValFile2;
    }

    public void setfGoodValFile2(String fGoodValFile2) {
        this.fGoodValFile2 = fGoodValFile2;
    }

    public String getfGoodValFile3() {
        return fGoodValFile3;
    }

    public void setfGoodValFile3(String fGoodValFile3) {
        this.fGoodValFile3 = fGoodValFile3;
    }

    public String getfGoodValFile4() {
        return fGoodValFile4;
    }

    public void setfGoodValFile4(String fGoodValFile4) {
        this.fGoodValFile4 = fGoodValFile4;
    }

    public String getfNormalValFile1() {
        return fNormalValFile1;
    }

    public void setfNormalValFile1(String fNormalValFile1) {
        this.fNormalValFile1 = fNormalValFile1;
    }

    public String getfNormalValFile2() {
        return fNormalValFile2;
    }

    public void setfNormalValFile2(String fNormalValFile2) {
        this.fNormalValFile2 = fNormalValFile2;
    }

    public String getfNormalValFile3() {
        return fNormalValFile3;
    }

    public void setfNormalValFile3(String fNormalValFile3) {
        this.fNormalValFile3 = fNormalValFile3;
    }

    public String getfNormalValFile4() {
        return fNormalValFile4;
    }

    public void setfNormalValFile4(String fNormalValFile4) {
        this.fNormalValFile4 = fNormalValFile4;
    }



    public String getFedGoodValFileType1() {
        return fedGoodValFileType1;
    }

    public void setFedGoodValFileType1(String fedGoodValFileType1) {
        this.fedGoodValFileType1 = fedGoodValFileType1;
    }

    public String getFedGoodValFileType2() {
        return fedGoodValFileType2;
    }

    public void setFedGoodValFileType2(String fedGoodValFileType2) {
        this.fedGoodValFileType2 = fedGoodValFileType2;
    }

    public String getFedGoodValFileType3() {
        return fedGoodValFileType3;
    }

    public void setFedGoodValFileType3(String fedGoodValFileType3) {
        this.fedGoodValFileType3 = fedGoodValFileType3;
    }

    public String getFedGoodValFileType4() {
        return fedGoodValFileType4;
    }

    public void setFedGoodValFileType4(String fedGoodValFileType4) {
        this.fedGoodValFileType4 = fedGoodValFileType4;
    }



    public String getfBadValFile2() {
        return fBadValFile2;
    }

    public void setfBadValFile2(String fBadValFile2) {
        this.fBadValFile2 = fBadValFile2;
    }

    public String getfBadValFile1() {
        return fBadValFile1;
    }

    public void setfBadValFile1(String fBadValFile1) {
        this.fBadValFile1 = fBadValFile1;
    }

    public String getfBadValFile3() {
        return fBadValFile3;
    }

    public void setfBadValFile3(String fBadValFile3) {
        this.fBadValFile3 = fBadValFile3;
    }

    public String getfBadValFile4() {
        return fBadValFile4;
    }

    public void setfBadValFile4(String fBadValFile4) {
        this.fBadValFile4 = fBadValFile4;
    }

    public MultipartFile[] getFedBadValFile1() {
        return fedBadValFile1;
    }

    public void setFedBadValFile1(MultipartFile[] fedBadValFile1) {
        this.fedBadValFile1 = fedBadValFile1;
    }

    public MultipartFile[] getFedBadValFile2() {
        return fedBadValFile2;
    }

    public void setFedBadValFile2(MultipartFile[] fedBadValFile2) {
        this.fedBadValFile2 = fedBadValFile2;
    }

    public MultipartFile[] getFedBadValFile3() {
        return fedBadValFile3;
    }

    public void setFedBadValFile3(MultipartFile[] fedBadValFile3) {
        this.fedBadValFile3 = fedBadValFile3;
    }

    public MultipartFile[] getFedBadValFile4() {
        return fedBadValFile4;
    }

    public void setFedBadValFile4(MultipartFile[] fedBadValFile4) {
        this.fedBadValFile4 = fedBadValFile4;
    }

    public String getFedNormalValFileType1() {
        return fedNormalValFileType1;
    }

    public void setFedNormalValFileType1(String fedNormalValFileType1) {
        this.fedNormalValFileType1 = fedNormalValFileType1;
    }

    public String getFedNormalValFileType2() {
        return fedNormalValFileType2;
    }

    public void setFedNormalValFileType2(String fedNormalValFileType2) {
        this.fedNormalValFileType2 = fedNormalValFileType2;
    }

    public String getFedNormalValFileType3() {
        return fedNormalValFileType3;
    }

    public void setFedNormalValFileType3(String fedNormalValFileType3) {
        this.fedNormalValFileType3 = fedNormalValFileType3;
    }

    public String getFedNormalValFileType4() {
        return fedNormalValFileType4;
    }

    public void setFedNormalValFileType4(String fedNormalValFileType4) {
        this.fedNormalValFileType4 = fedNormalValFileType4;
    }

    public String getFedBadValFileType1() {
        return fedBadValFileType1;
    }

    public void setFedBadValFileType1(String fedBadValFileType1) {
        this.fedBadValFileType1 = fedBadValFileType1;
    }

    public String getFedBadValFileType2() {
        return fedBadValFileType2;
    }

    public void setFedBadValFileType2(String fedBadValFileType2) {
        this.fedBadValFileType2 = fedBadValFileType2;
    }

    public String getFedBadValFileType3() {
        return fedBadValFileType3;
    }

    public void setFedBadValFileType3(String fedBadValFileType3) {
        this.fedBadValFileType3 = fedBadValFileType3;
    }

    public String getFedBadValFileType4() {
        return fedBadValFileType4;
    }

    public void setFedBadValFileType4(String fedBadValFileType4) {
        this.fedBadValFileType4 = fedBadValFileType4;
    }

    public String getFedGoodValFileInfo1() {
        return fedGoodValFileInfo1;
    }

    public void setFedGoodValFileInfo1(String fedGoodValFileInfo1) {
        this.fedGoodValFileInfo1 = fedGoodValFileInfo1;
    }

    public String getFedGoodValFileInfo2() {
        return fedGoodValFileInfo2;
    }

    public void setFedGoodValFileInfo2(String fedGoodValFileInfo2) {
        this.fedGoodValFileInfo2 = fedGoodValFileInfo2;
    }

    public String getFedGoodValFileInfo3() {
        return fedGoodValFileInfo3;
    }

    public void setFedGoodValFileInfo3(String fedGoodValFileInfo3) {
        this.fedGoodValFileInfo3 = fedGoodValFileInfo3;
    }

    public String getFedGoodValFileInfo4() {
        return fedGoodValFileInfo4;
    }

    public void setFedGoodValFileInfo4(String fedGoodValFileInfo4) {
        this.fedGoodValFileInfo4 = fedGoodValFileInfo4;
    }
}
