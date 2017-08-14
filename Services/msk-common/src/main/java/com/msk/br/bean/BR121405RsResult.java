package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.ByMarketTerminal;

import java.util.List;

public class BR121405RsResult extends RsPageResult {

    private List<ByMarketTerminal> terminalList;

    private List<BR121405Bean> fileBuyerPoolList;

    /**
     * Getter method for property <tt>terminalList</tt>.
     *
     * @return property value of terminalList
     */
    public List<ByMarketTerminal> getTerminalList() {
        return terminalList;
    }

    /**
     * Setter method for property <tt>terminalList</tt>.
     *
     * @param terminalList value to be assigned to property terminalList
     */
    public void setTerminalList(List<ByMarketTerminal> terminalList) {
        this.terminalList = terminalList;
    }

    /**
     * Getter method for property <tt>fileBuyerPoolList</tt>.
     *
     * @return property value of fileBuyerPoolList
     */
    public List<BR121405Bean> getFileBuyerPoolList() {
        return fileBuyerPoolList;
    }

    /**
     * Setter method for property <tt>fileBuyerPoolList</tt>.
     *
     * @param fileBuyerPoolList value to be assigned to property fileBuyerPoolList
     */
    public void setFileBuyerPoolList(List<BR121405Bean> fileBuyerPoolList) {
        this.fileBuyerPoolList = fileBuyerPoolList;
    }
}
