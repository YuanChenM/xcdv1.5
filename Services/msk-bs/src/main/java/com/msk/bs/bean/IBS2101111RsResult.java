package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gyh on 2016/4/12.
 */
@ApiModel(value = "IBS2101111RsResult",description = "result")
public class IBS2101111RsResult extends RsPageResult {
    @ApiModelProperty(value = "买手联盟List")
    private List<IBS210111101RsResult> slBsLeaguesList;

    /**
     * Getter method for property <tt>slBsLeaguesList</tt>.
     *
     * @return property value of slBsLeaguesList
     */
    public List<IBS210111101RsResult> getSlBsLeaguesList() {
        return slBsLeaguesList;
    }

    /**
     * Setter method for property <tt>slBsLeaguesList</tt>.
     *
     * @param slBsLeaguesList value to be assigned to property slBsLeaguesList
     */
    public void setSlBsLeaguesList(List<IBS210111101RsResult> slBsLeaguesList) {
        this.slBsLeaguesList = slBsLeaguesList;
    }
}
