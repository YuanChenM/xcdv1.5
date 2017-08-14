package com.msk.batch.consts;

/**
 * Created by jackjiang on 16/6/28.
 */
public class StatusConst {
    /**
     * Batch 状态定义
     *
     * @author jiang_nan
     */
    public final static class BatchStatusDef {
        /** Type:BatchStatus */
        public final static String TYPE = "BatchStatus";
        /** NEW:新建 */
        public final static int NEW = 1;
        /** RUN:执行中 */
        public final static int RUN = 2;
        /** EXCETION:异常终止 */
        public final static int EXCETION = 3;
        /** END:正常结束 */
        public final static int END = 4;
    }
}
