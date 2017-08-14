package com.msk.product.bean;

import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdFeature;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 * @author gyh
 *
 */
public class ProductBreed extends PdBreed {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private int size;
    private List<PdFeature> pdFeatureList;

    /**
     * @return the size
     */
    public int getSize() {
        if(!CollectionUtils.isEmpty(pdFeatureList)){
            return pdFeatureList.size();
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
     * 特征信息
     *
     * @return
     * @author silent
     */
    public List<PdFeature> getPdFeatureList() {
        return this.pdFeatureList;
    }

    public void setPdFeatureList(List<PdFeature> pdFeatureList) {
        this.pdFeatureList = pdFeatureList;
    }

}
