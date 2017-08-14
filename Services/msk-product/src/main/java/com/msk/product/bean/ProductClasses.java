package com.msk.product.bean;

import com.msk.core.entity.PdClasses;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 * @author gyh
 *
 */
public class ProductClasses extends PdClasses {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private int size;
    private List<ProductBreed> pdBreedList;

    /**
     * @return the size
     */
    public int getSize() {
        if(!CollectionUtils.isEmpty(pdBreedList)){
            return pdBreedList.size();
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
     *
     * 品种信息
     *
     * @return
     * @author silent
     */
    public List<ProductBreed> getPdBreedList() {
        return this.pdBreedList;
    }

    public void setPdBreedList(List<ProductBreed> pdBreedList) {
        this.pdBreedList = pdBreedList;
    }

}
