package com.msk.org.bean.param;

/**
 * Created by jackjiang on 16/8/26.
 */
public class PageParam {
    private int page;
    private int rows;

    public int getPage() {
        return page - 1;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
