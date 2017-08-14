package com.msk.org.bean;

import com.msk.core.entity.OrgPage;
import com.msk.core.entity.OrgSys;

import java.util.List;

/**
 * SystemModel
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class SystemModel extends OrgSys{
    private List<OrgPage> pageList;

    /**
     * Getter method for property <tt>pageList</tt>.
     *
     * @return property value of pageList
     */
    public List<OrgPage> getPageList() {
        return pageList;
    }

    /**
     * Setter method for property <tt>pageList</tt>.
     *
     * @param pageList value to be assigned to property pageList
     */
    public void setPageList(List<OrgPage> pageList) {
        this.pageList = pageList;
    }
}
