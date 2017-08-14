package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.BR121404RsPageResult;
import com.msk.br.bean.BR121404RsParam;
import com.msk.br.logic.BR121404Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrSingleByFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 产品共通接口
 * Created by yuan_zhifei on 2016/6/29.
 */
@RestController
public class IBR121404RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121404RsController.class);

    @Autowired
    private BR121404Logic br121404Logic;

    /*
     * 批量查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/findBrSingleByFileInfoList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121404RsPageResult> findBrSingleByFileInfoList(@RequestBody RsRequest<BR121404RsParam> param) {
        logger.info("单一买家标准产品池列表");
        RsResponse<BR121404RsPageResult> response = new RsResponse<>();
        BR121404RsPageResult br121404RsPageResult = new BR121404RsPageResult();
        int count = this.br121404Logic.getPageCount(param.getParam());
        List<BrSingleByFileInfo> res = br121404Logic.findPageList(param.getParam(), BrSingleByFileInfo.class);
        br121404RsPageResult.setBrSingleByFileInfoList(res);
        br121404RsPageResult.setTotalCount(count);
        response.setResult(br121404RsPageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }

    /*
    * 单一买家标准产品生成excel文件
    *
    * @param param
    * @return
    */
    @RequestMapping(value = "/br/findDataResolve",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void findDataResolve(@RequestBody BrSingleByFileInfo bean) {
        logger.info("单一买家标准产品生成excel文件");
        if (bean != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            super.setCommonParam(bean);
            bean.setUpdTime(currentDate);
            bean.setCrtTime(currentDate);
            bean.setActTime(currentDate);
        }
        this.br121404Logic.dataResolve(bean);
    }
}


