package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SscIntoDetail;
import com.msk.ssc.bean.SSC11310RsBean;
import com.msk.ssc.bean.SSC11310RsParam;
import com.msk.ssc.logic.SSC11310Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询生产商入库单详细
 * Created by liu_yan2 on 2016/7/5.
 */
@RestController
public class ISSC11310RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11310RestController.class);


    @Autowired
    private SSC11310Logic ssc11310Logic;

    /**
     * 查询生产商入库单详细
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findSscIntoDetail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.ssc.validator.SSC11310Validator")
    public RsResponse<PageResult<SSC11310RsBean>> findSscIntoDetail(@RequestBody RsRequest<SSC11310RsParam> param) {
        logger.info("查询生产商入库单详细");
        RsResponse<PageResult<SSC11310RsBean>> response=new RsResponse<>();

        PageResult<SSC11310RsBean> res =this.ssc11310Logic.findPage(param.getParam(), SSC11310RsBean.class );
        if(res!=null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

    /**
     * 修改生产商入库单详细信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/modifySscIntoDetail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> modifySscIntoDetail(@RequestBody RsRequest<SscIntoDetail> param ) {
        logger.info("根据ID修改生产商入库单详细信息");
        RsResponse<Integer> response=new RsResponse<>();
        SscIntoDetail sscIntoDetail = param.getParam();
        int result = this.ssc11310Logic.modify(sscIntoDetail);
        RsResponse<Integer> rs = new RsResponse<Integer>();
        if (result > NumberConst.IntDef.INT_ZERO) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("根据条件插入技术标准信息接口成功！" + "影响条数" + result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("根据ID修改生产商入库单详细信息,数据错误！");
        return rs;
    }
}


