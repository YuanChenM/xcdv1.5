package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.BR121401Param;
import com.msk.br.bean.BR121401RsPageResult;
import com.msk.br.logic.BR121401Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrByPoolFileInfo;
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
 * Created by zhao_chen on 2016/07/18.
 */
@RestController
public class IBR121401RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121401RsController.class);

    @Autowired
    private BR121401Logic br121401Logic;


    /**
     * 买家标准产品池列表查询接口
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/findBrByPoolFileInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121401RsPageResult> findBrByPoolFileInfo(@RequestBody RsRequest<BR121401Param> param) {

        if (param.getParam().getFilterMap().get("fileStartTime") != null && param.getParam().getFilterMap().get("fileStartTime") != "") {
            String Start = param.getParam().getFilterMap().get("fileStartTime").toString().replaceAll("-", "");
            Date fileStartTime1 = DateTimeUtil.firstDay(DateTimeUtil.parseDate(Start, DateTimeUtil.FORMAT_YEAR_MONTH));
            String fileStartTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, fileStartTime1);
            param.getParam().getFilterMap().put("fileStartTime", fileStartTime);
        }
        if (param.getParam().getFilterMap().get("fileEndTime") != null && param.getParam().getFilterMap().get("fileEndTime") != "") {
            String End = param.getParam().getFilterMap().get("fileEndTime").toString().replaceAll("-", "");
            Date fileEndTime2 = DateTimeUtil.lastDay(DateTimeUtil.parseDate(End, DateTimeUtil.FORMAT_YEAR_MONTH));
            String fileEndTime = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, fileEndTime2);
            param.getParam().getFilterMap().put("fileEndTime", fileEndTime);
        }

        RsResponse<BR121401RsPageResult> response = new RsResponse<>();
        BR121401RsPageResult br121401RsPageResult = new BR121401RsPageResult();
        int count = this.br121401Logic.getPageCount(param.getParam());
        List<BrByPoolFileInfo> fileList = null;
        fileList = this.br121401Logic.findPageList(param.getParam(), BrByPoolFileInfo.class);
        br121401RsPageResult.setTotalCount(count);
        br121401RsPageResult.setBrByPoolFileInfos(fileList);
        response.setResult(br121401RsPageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }


}


