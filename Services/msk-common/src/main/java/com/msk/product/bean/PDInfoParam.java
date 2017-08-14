package com.msk.product.bean;

import com.msk.common.bean.RsPageParam;
import com.msk.core.entity.PdMctStdDiscussProvider;
import com.msk.core.entity.PdTncStdDiscussProvider;
import com.msk.seller.bean.SL241130Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@ApiModel(value = "PDInfoParam", description = "param")
public class PDInfoParam extends RsPageParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "平台编码")
    private String siteCode;

    @ApiModelProperty(value = "身份识别码")
    private String auth;

    @ApiModelProperty(value = "登陆的用户ID")
    private String loginId;

    @ApiModelProperty(value = "编码（一级分类编码/二级分类编码/品种分类编码/特征分类编码/净重编码）")
    private String code;

    @ApiModelProperty(value = "产品编码（位数不固定）")
    private String pdCode;

    @ApiModelProperty(value = "产品名称")
    private String pdName;

    @ApiModelProperty(value = "区域编码")
    private String lgcsCode;

    @ApiModelProperty(value = "区域名称")
    private String lgcsName;

    @ApiModelProperty(value = "一级分类名称")
    private String classesName;

    @ApiModelProperty(value = "二级分类名称")
    private String machiningName;

    @ApiModelProperty(value = "品种名称")
    private String breedName;

    @ApiModelProperty(value = "特征名称")
    private String featureName;

    @ApiModelProperty(value = "净重名称")
    private String weightsName;

    @ApiModelProperty(value = "分页起始页")
    private Integer startSize;

    @ApiModelProperty(value = "分页每条展示条数")
    private Integer endSize;

    @ApiModelProperty(value = "是否要查询产品等级")
    private String gradeFlag;

    @ApiModelProperty(value = "一级分类Codes")
    List<PDInfoParam> classesCodeList = new ArrayList<PDInfoParam>();

    @ApiModelProperty(value = "二级分类Codes")
    List<PDInfoParam> machiningCodeList = new ArrayList<PDInfoParam>();

    @ApiModelProperty(value = "品种Codes")
    List<PDInfoParam> breedCodeList = new ArrayList<PDInfoParam>();

    @ApiModelProperty(value = "特征Codes")
    List<PDInfoParam> featureCodeList = new ArrayList<PDInfoParam>();

    @ApiModelProperty(value = "净重Codes")
    List<PDInfoParam> weightCodeList = new ArrayList<PDInfoParam>();

    @ApiModelProperty(value = "等级Codes")
    List<PDInfoParam> gradeCodeList = new ArrayList<PDInfoParam>();
    /**档案卡类型 原种种源标准指标 -- 1 原种种源标准指标 -- 2 原种饲养标准指标 -- 3 产品加工技术标准指标 -- 4 产品加工质量标准指标 -- 5 产品通用质量标准指标 -- 6 产品安全标准指标 -- 7 储存运输标准指标 -- 8 */
    /**产品类型 classes——1、 machining——2、 breed——3、 feature——4、 weight——5、 grade——6、 获取productInfo —— 7*/
    @ApiModelProperty(value = "档案卡类型")
    private Integer type;

    @ApiModelProperty(value = "一级分类编码")
    private String classesCode;

    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;

    @ApiModelProperty(value = "品种编码")
    private String breedCode;

    @ApiModelProperty(value = "特征编码")
    private String featureCode;

    @ApiModelProperty(value = "净重编码")
    private String weightCode;

    @ApiModelProperty(value = "等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "产品标准ID")
    private String standardId;

    @ApiModelProperty(value = "项目ID")
    private String stdItemId;

    @ApiModelProperty(value = "销售名称")
    private String salesName;

    @ApiModelProperty(value = "删除标识")
    private String delFlag;

    @ApiModelProperty(value = "一级分类Code")
    private String classesTypeCode;

    @ApiModelProperty(value = "分类编码")
    private String classesTreeCode;

    @ApiModelProperty(value = "批量分类编码")
    private List<String> classesTreeCodes;

    @ApiModelProperty(value = "学名")
    private String scientificName;

    @ApiModelProperty(value = "俗名")
    private String localName;

    @ApiModelProperty(value = "树编码")
    private String treeCode;

    @ApiModelProperty(value = "是否查询产品包装信息")
    private String isPackage;

    @ApiModelProperty(value = "包装编码")
    private String normsCode;

    @ApiModelProperty(value = "包装名称")
    private String normsName;

    @ApiModelProperty(value = "单个产品净重")
    private String normsSuttle;

    @ApiModelProperty(value = "单个产品规格净重误差范围")
    private String normsError;

    @ApiModelProperty(value = "内包装净重/个数")
    private String normsNumber;

    @ApiModelProperty(value = "内包装尺寸")
    private String normsSize;

    @ApiModelProperty(value = "内包装材质及技术标准")
    private String normsTexture;

    @ApiModelProperty(value = "外包装规格")
    private String normsOut;

    @ApiModelProperty(value = "外包装净重/毛重")
    private String normsKg;

    @ApiModelProperty(value = "外包装尺寸")
    private String normsOutSize;

    @ApiModelProperty(value = "外包装材质及技术标准")
    private String normsOutTexture;

    @ApiModelProperty(value = "外包装宽度")
    private String normsTen;

    @ApiModelProperty(value = "外包装长")
    private String normsLength;

    @ApiModelProperty(value = "包装宽度")
    private String normsWidth;

    @ApiModelProperty(value = "外包装宽度")
    private String normsHeight;

    @ApiModelProperty(value = "包装体积")
    private String normsVolume;

    @ApiModelProperty(value = "内包装宽度")
    private String netweightInner;

    @ApiModelProperty(value = "外包装宽度")
    private String netweightOut;

    @ApiModelProperty(value = "外包装毛重数值")
    private String grossweightOut;

    @ApiModelProperty(value = "订单数量")
    private String orderNum;

    @ApiModelProperty(value = "节点名称")
    private String treeLevel;

    @ApiModelProperty(value = "类别编码")
    private String parentCode;

    @ApiModelProperty(value = "节点Code")
    private String levelCode;

    @ApiModelProperty(value = "产品编码Codes")
    private List<String> pdCodes = new ArrayList<>();

    @ApiModelProperty(value = "产品包装Codes")
    private List<String> normCodes = new ArrayList<>();

    @ApiModelProperty(value = "批量params")
    private List<PDInfoParam> params = new ArrayList<>();

    @ApiModelProperty(value = "卖家供应商CODE")
    private String providerCode;

    @ApiModelProperty(value = "卖家供应商名称")
    private String providerName;

    @ApiModelProperty(value = "等级ID")
    private String levelId;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "档案卡参数")
    private List<StdItem> stdParams = new ArrayList<>();

    @ApiModelProperty(value = "pdMctStdDiscussProviders")
    private List<PdMctStdDiscussProvider> pdMctStdDiscussProviders = new ArrayList<>();

    @ApiModelProperty(value = "pdTcProviderPackages")
    private List<SL241130Param> pdTcProviderPackages = new ArrayList<>();

    @ApiModelProperty(value = "tncProviders")
    private List<PdTncStdDiscussProvider> tncProviders = new ArrayList<>();

    @ApiModelProperty(value = "classCodeThree")
    private String classCodeThree;

    @ApiModelProperty(value = "classCodeTwo")
    private String classCodeTwo;

    @ApiModelProperty(value = "产品标准IDs")
    private List<String> standardIds;

    @ApiModelProperty(value = "包装参数")
    private List<NormsParams> normsParamses = new ArrayList<>();

    /**
     * Getter method for property <tt>normsCode</tt>.
     *
     * @return property value of normsCode
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * Setter method for property <tt>normsCode</tt>.
     *
     * @param normsCode value to be assigned to property normsCode
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Getter method for property <tt>normsName</tt>.
     *
     * @return property value of normsName
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * Setter method for property <tt>normsName</tt>.
     *
     * @param normsName value to be assigned to property normsName
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * Getter method for property <tt>normsSuttle</tt>.
     *
     * @return property value of normsSuttle
     */
    public String getNormsSuttle() {
        return normsSuttle;
    }

    /**
     * Setter method for property <tt>normsSuttle</tt>.
     *
     * @param normsSuttle value to be assigned to property normsSuttle
     */
    public void setNormsSuttle(String normsSuttle) {
        this.normsSuttle = normsSuttle;
    }

    /**
     * Getter method for property <tt>normsError</tt>.
     *
     * @return property value of normsError
     */
    public String getNormsError() {
        return normsError;
    }

    /**
     * Setter method for property <tt>normsError</tt>.
     *
     * @param normsError value to be assigned to property normsError
     */
    public void setNormsError(String normsError) {
        this.normsError = normsError;
    }

    /**
     * Getter method for property <tt>normsNumber</tt>.
     *
     * @return property value of normsNumber
     */
    public String getNormsNumber() {
        return normsNumber;
    }

    /**
     * Setter method for property <tt>normsNumber</tt>.
     *
     * @param normsNumber value to be assigned to property normsNumber
     */
    public void setNormsNumber(String normsNumber) {
        this.normsNumber = normsNumber;
    }

    /**
     * Getter method for property <tt>normsSize</tt>.
     *
     * @return property value of normsSize
     */
    public String getNormsSize() {
        return normsSize;
    }

    /**
     * Setter method for property <tt>normsSize</tt>.
     *
     * @param normsSize value to be assigned to property normsSize
     */
    public void setNormsSize(String normsSize) {
        this.normsSize = normsSize;
    }

    /**
     * Getter method for property <tt>normsTexture</tt>.
     *
     * @return property value of normsTexture
     */
    public String getNormsTexture() {
        return normsTexture;
    }

    /**
     * Setter method for property <tt>normsTexture</tt>.
     *
     * @param normsTexture value to be assigned to property normsTexture
     */
    public void setNormsTexture(String normsTexture) {
        this.normsTexture = normsTexture;
    }

    /**
     * Getter method for property <tt>normsOut</tt>.
     *
     * @return property value of normsOut
     */
    public String getNormsOut() {
        return normsOut;
    }

    /**
     * Setter method for property <tt>normsOut</tt>.
     *
     * @param normsOut value to be assigned to property normsOut
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * Getter method for property <tt>normsKg</tt>.
     *
     * @return property value of normsKg
     */
    public String getNormsKg() {
        return normsKg;
    }

    /**
     * Setter method for property <tt>normsKg</tt>.
     *
     * @param normsKg value to be assigned to property normsKg
     */
    public void setNormsKg(String normsKg) {
        this.normsKg = normsKg;
    }

    /**
     * Getter method for property <tt>normsOutSize</tt>.
     *
     * @return property value of normsOutSize
     */
    public String getNormsOutSize() {
        return normsOutSize;
    }

    /**
     * Setter method for property <tt>normsOutSize</tt>.
     *
     * @param normsOutSize value to be assigned to property normsOutSize
     */
    public void setNormsOutSize(String normsOutSize) {
        this.normsOutSize = normsOutSize;
    }

    /**
     * Getter method for property <tt>normsOutTexture</tt>.
     *
     * @return property value of normsOutTexture
     */
    public String getNormsOutTexture() {
        return normsOutTexture;
    }

    /**
     * Setter method for property <tt>normsOutTexture</tt>.
     *
     * @param normsOutTexture value to be assigned to property normsOutTexture
     */
    public void setNormsOutTexture(String normsOutTexture) {
        this.normsOutTexture = normsOutTexture;
    }

    public List<PDInfoParam> getClassesCodeList() {
        return classesCodeList;
    }

    public void setClassesCodeList(List<PDInfoParam> classesCodeList) {
        this.classesCodeList = classesCodeList;
    }

    public List<PDInfoParam> getMachiningCodeList() {
        return machiningCodeList;
    }

    public void setMachiningCodeList(List<PDInfoParam> machiningCodeList) {
        this.machiningCodeList = machiningCodeList;
    }

    public List<PDInfoParam> getBreedCodeList() {
        return breedCodeList;
    }

    public void setBreedCodeList(List<PDInfoParam> breedCodeList) {
        this.breedCodeList = breedCodeList;
    }

    public List<PDInfoParam> getFeatureCodeList() {
        return featureCodeList;
    }

    public void setFeatureCodeList(List<PDInfoParam> featureCodeList) {
        this.featureCodeList = featureCodeList;
    }

    public List<PDInfoParam> getWeightCodeList() {
        return weightCodeList;
    }

    public void setWeightCodeList(List<PDInfoParam> weightCodeList) {
        this.weightCodeList = weightCodeList;
    }

    public List<PDInfoParam> getGradeCodeList() {
        return gradeCodeList;
    }

    public void setGradeCodeList(List<PDInfoParam> gradeCodeList) {
        this.gradeCodeList = gradeCodeList;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStdItemId() {
        return stdItemId;
    }

    public void setStdItemId(String stdItemId) {
        this.stdItemId = stdItemId;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
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

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getWeightsName() {
        return weightsName;
    }

    public void setWeightsName(String weightsName) {
        this.weightsName = weightsName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getClassesTypeCode() {
        return classesTypeCode;
    }

    public void setClassesTypeCode(String classesTypeCode) {
        this.classesTypeCode = classesTypeCode;
    }

    public String getClassesTreeCode() {
        return classesTreeCode;
    }

    public void setClassesTreeCode(String classesTreeCode) {
        this.classesTreeCode = classesTreeCode;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public Integer getStartSize() {
        return startSize;
    }

    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }

    public Integer getEndSize() {
        return endSize;
    }

    public void setEndSize(Integer endSize) {
        this.endSize = endSize;
    }

    public String getGradeFlag() {
        return gradeFlag;
    }

    public void setGradeFlag(String gradeFlag) {
        this.gradeFlag = gradeFlag;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNormsTen() {
        return normsTen;
    }

    public void setNormsTen(String normsTen) {
        this.normsTen = normsTen;
    }

    public String getNormsLength() {
        return normsLength;
    }

    public void setNormsLength(String normsLength) {
        this.normsLength = normsLength;
    }

    public String getNormsWidth() {
        return normsWidth;
    }

    public void setNormsWidth(String normsWidth) {
        this.normsWidth = normsWidth;
    }

    public String getNormsHeight() {
        return normsHeight;
    }

    public void setNormsHeight(String normsHeight) {
        this.normsHeight = normsHeight;
    }

    public String getNormsVolume() {
        return normsVolume;
    }

    public void setNormsVolume(String normsVolume) {
        this.normsVolume = normsVolume;
    }

    public String getNetweightInner() {
        return netweightInner;
    }

    public void setNetweightInner(String netweightInner) {
        this.netweightInner = netweightInner;
    }

    public String getNetweightOut() {
        return netweightOut;
    }

    public void setNetweightOut(String netweightOut) {
        this.netweightOut = netweightOut;
    }

    public String getGrossweightOut() {
        return grossweightOut;
    }

    public void setGrossweightOut(String grossweightOut) {
        this.grossweightOut = grossweightOut;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(String treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public List<String> getPdCodes() {
        return pdCodes;
    }

    public void setPdCodes(List<String> pdCodes) {
        this.pdCodes = pdCodes;
    }


    public List<PDInfoParam> getParams() {
        return params;
    }

    public void setParams(List<PDInfoParam> params) {
        this.params = params;
    }

    public String getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(String isPackage) {
        this.isPackage = isPackage;
    }

    public List<String> getNormCodes() {
        return normCodes;
    }

    public void setNormCodes(List<String> normCodes) {
        this.normCodes = normCodes;
    }

    public List<String> getClassesTreeCodes() {
        return classesTreeCodes;
    }

    public void setClassesTreeCodes(List<String> classesTreeCodes) {
        this.classesTreeCodes = classesTreeCodes;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<StdItem> getStdParams() {
        return stdParams;
    }

    public void setStdParams(List<StdItem> stdParams) {
        this.stdParams = stdParams;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<PdMctStdDiscussProvider> getPdMctStdDiscussProviders() {
        return pdMctStdDiscussProviders;
    }

    public void setPdMctStdDiscussProviders(List<PdMctStdDiscussProvider> pdMctStdDiscussProviders) {
        this.pdMctStdDiscussProviders = pdMctStdDiscussProviders;
    }

    public String getClassCodeThree() {
        return classCodeThree;
    }

    public void setClassCodeThree(String classCodeThree) {
        this.classCodeThree = classCodeThree;
    }

    public String getClassCodeTwo() {
        return classCodeTwo;
    }

    public void setClassCodeTwo(String classCodeTwo) {
        this.classCodeTwo = classCodeTwo;
    }

    public List<String> getStandardIds() { return standardIds; }

    public void setStandardIds(List<String> standardIds) { this.standardIds = standardIds; }

    public List<SL241130Param> getPdTcProviderPackages() {
        return pdTcProviderPackages;
    }

    public void setPdTcProviderPackages(List<SL241130Param> pdTcProviderPackages) {
        this.pdTcProviderPackages = pdTcProviderPackages;
    }

    public List<PdTncStdDiscussProvider> getTncProviders() {
        return tncProviders;
    }

    public void setTncProviders(List<PdTncStdDiscussProvider> tncProviders) {
        this.tncProviders = tncProviders;
    }

    public List<NormsParams> getNormsParamses() {
        return normsParamses;
    }

    public void setNormsParamses(List<NormsParams> normsParamses) {
        this.normsParamses = normsParamses;
    }
}