package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrMPdClasses;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/28.
 */
public class BR121414Result extends RsPageResult {
    private List<BrMPdClasses> brMPdClassesList;

    public List<BrMPdClasses> getBrMPdClassesList() {
        return brMPdClassesList;
    }

    public void setBrMPdClassesList(List<BrMPdClasses> brMPdClassesList) {
        this.brMPdClassesList = brMPdClassesList;
    }
}
