package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gyh on 2016/4/8.
 */
@ApiModel(value = "IBS2101110RsParam",description = "param")
public class IBS2101110RsParam {
    @ApiModelProperty(value = "买手联盟List")
    private List<IBS210111001RsParam> slBsLeaguesList;

    /**
     * Getter method for property <tt>slBsLeaguesList</tt>.
     *
     * @return property value of slBsLeaguesList
     */
    public List<IBS210111001RsParam> getSlBsLeaguesList() {
        return slBsLeaguesList;
    }

    /**
     * Setter method for property <tt>slBsLeaguesList</tt>.
     *
     * @param slBsLeaguesList value to be assigned to property slBsLeaguesList
     */
    public void setSlBsLeaguesList(List<IBS210111001RsParam> slBsLeaguesList) {
        this.slBsLeaguesList = slBsLeaguesList;
    }
}
