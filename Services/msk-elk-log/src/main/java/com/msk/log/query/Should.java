package com.msk.log.query;

import java.util.List;

/**应该满足的搜索条件
 * Created by mao_yejun on 2016/6/6.
 */
public class Should {
    private MessageMatch match;

    public MessageMatch getMatch() {
        return match;
    }

    public void setMatch(MessageMatch match) {
        this.match = match;
    }
}
