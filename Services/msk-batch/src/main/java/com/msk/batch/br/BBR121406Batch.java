package com.msk.batch.br;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.br.logic.BBR121406Logic;
import com.msk.common.bean.RsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhou_yajun on 2016/8/30.
 */
@Component("BBR121406")
public class BBR121406Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121406Batch.class);

    @Autowired
    private BBR121406Logic bbr121406Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {
        BaseParam param = new BaseParam();
        //设置共通参数
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId("admin");
        param.setCrtTime(currentDate);
        param.setActId("admin");
        param.setActTime(currentDate);
        param.setUpdId("admin");
        param.setUpdTime(currentDate);
        return param;
    }

    /**
     * 业务处理
     *
     * @param param
     */
    @Override
    public void doOperate(BaseParam param) {
        RsRequest<BaseParam> request=new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        //更新买家池分池信息
        bbr121406Logic.synByPoolRelationShip(param);
        //更新管家买家关系
        bbr121406Logic.synByHkRelationShip(param);
    }
}
