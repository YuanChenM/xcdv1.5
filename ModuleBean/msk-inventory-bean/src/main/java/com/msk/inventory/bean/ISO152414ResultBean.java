package com.msk.inventory.bean;

import com.msk.comm.bean.result.RestPageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/9/5.
 */
public class ISO152414ResultBean extends RestPageResult {

    private int totalCount;
    private int totalPage;
    private int pageNo;

    private List<ISO152414ProductResultBean> products = new ArrayList<ISO152414ProductResultBean>();

    @Override
    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public int getTotalPage() {
        return totalPage;
    }

    @Override
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public int getPageNo() {
        return pageNo;
    }

    @Override
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<ISO152414ProductResultBean> getProducts() {
        return products;
    }

    public void setProducts(List<ISO152414ProductResultBean> products) {
        this.products = products;
    }
}
