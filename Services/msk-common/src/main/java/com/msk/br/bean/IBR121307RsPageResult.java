package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrMPdClasses;
import com.msk.core.entity.BrOClaMachiningInfo;

import java.util.List;

/**
 * Created by taozhifa on 2016/7/12.
 */
public class IBR121307RsPageResult extends RsPageResult {

    private List<BrMPdClasses> brMPdClassesList;

    public List<BrMPdClasses> getBrMPdClassesList() {
        return brMPdClassesList;
    }

    public void setBrMPdClassesList(List<BrMPdClasses> brMPdClassesList) {
        this.brMPdClassesList = brMPdClassesList;
    }
}

