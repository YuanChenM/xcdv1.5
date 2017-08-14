package com.msk.bs.bean;

import java.util.List;

import com.msk.core.entity.SlHouseAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "IBS2101104RsParam",description = "冻品管家账号信息")
public class IBS2101104SlHouseAccount extends SlHouseAccount {
    @ApiModelProperty(value = "等级")
    private String greade;
    @ApiModelProperty(value = "登陆者id")
    private String loginId;
    @ApiModelProperty(value = "买手编码")
    private String slCodeDis;
    @ApiModelProperty(value = "冻品管家分类")
    private List<IBS2101104SlHouseAccountParam> houseTYPEList;

    /**
     * Getter method for property <tt>greade</tt>.
     *
     * @return property value of greade
     */
    public String getGreade() {
        return greade;
    }

    /**
     * Setter method for property <tt>greade</tt>.
     *
     * @param greade value to be assigned to property greade
     */
    public void setGreade(String greade) {
        this.greade = greade;
    }

    /**
     * Getter method for property <tt>loginId</tt>.
     *
     * @return property value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for property <tt>loginId</tt>.
     *
     * @param loginId value to be assigned to property loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public List<IBS2101104SlHouseAccountParam> getHouseTYPEList() {
        return houseTYPEList;
    }

    public void setHouseTYPEList(List<IBS2101104SlHouseAccountParam> houseTYPEList) {
        this.houseTYPEList = houseTYPEList;
    }
}
