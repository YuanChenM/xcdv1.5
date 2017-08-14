package com.msk.common.bean.param;

public class BasePageParam extends BaseParam {
    /** 是否分页 */
    private boolean paging;
    /** 开始行数 */
    private int startPos;
    /** 每页显示的行数 */
    private int pageSize;
    /** 结束行数 */
    private int endPos;

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }
}
