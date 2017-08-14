package com.msk.bs.bean;

import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdMachining;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 二级分类和品种
 * Created by ni_shaotang on 2016/7/14.
 */
@ApiModel(value = "IBA2141106RsResult", description = "result")
public class IBA2141106RsResult extends PdMachining {

    @ApiModelProperty(value = "品种列表")
    private List<PdBreed> breedList;

    public List<PdBreed> getBreedList() {
        return breedList;
    }

    public void setBreedList(List<PdBreed> breedList) {
        this.breedList = breedList;
    }
}
