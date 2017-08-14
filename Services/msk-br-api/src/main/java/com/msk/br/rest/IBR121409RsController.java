package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.br.bean.IBR121409RsBean;
import com.msk.br.bean.IBR121409RsParam;
import com.msk.br.logic.IBR121408Logic;
import com.msk.br.logic.IBR121409Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrFileBuyerPool;
import org.apache.commons.collections.CollectionUtils;
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
 * 销售期公众买家报表
 * Created by tao_zhifa on 2016/8/8.
 */
@RestController
public class IBR121409RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121409RsController.class);

    @Autowired
    private IBR121409Logic ibr121409Logic;
    @Autowired
    private IBR121408Logic ibr121408Logic;

    /*
        * 分销分类买家池生成excel文件
        *
        * @param param
        * @return
        */
    @RequestMapping(value = "/br/createSalesPeriodExcel/generateAndUpload",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121409RsBean> createSalesPeriodExcel(@RequestBody RsRequest<IBR121409RsParam> ibr121409RsParam) {
        logger.info("销售期公众买家报表excel文件");
        String message = null;
        String fileId = null;
        Date currentDate = DateTimeUtil.getCustomerDate();
        RsResponse<IBR121409RsBean> rsResponse = new RsResponse<>();
        int count = NumberConst.IntDef.INT_ZERO;

        if(ibr121409RsParam != null){
            IBR121409RsParam param = ibr121409RsParam.getParam();
            param.setCrtId(ibr121409RsParam.getLoginId());
            param.setUpdId(ibr121409RsParam.getLoginId());
            param.setActId(ibr121409RsParam.getLoginId());
            param.setUpdTime(currentDate);
            param.setCrtTime(currentDate);
            param.setActTime(currentDate);
            if(param.getFileStatusFlag().equals("0")){
                logger.info("营销期公众买家报表数据新增");
                List<BrFileBuyerPool> res = ibr121409Logic.findList(param);
                String fileName;
                if(!CollectionUtils.isEmpty(res)){
                    fileName = res.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime();
                    BaseParam baseParam = new BaseParam();
                    baseParam.setFilter("fileName", fileName);
                    baseParam.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_FIVE));
                    baseParam.setFilter("currentDate", StringUtil.toString(currentDate));
                    int ret = this.ibr121408Logic.isExist(baseParam);
                    if (ret > NumberConst.IntDef.INT_ZERO) {
                        count = NumberConst.IntDef.INT_THREE;
                        message = "报表已存在！";
                        if (param.isFlag()) {
                            //覆盖生成新文件
                            this.ibr121408Logic.updateFileBuyerPoolByFileName(baseParam);
                            count = NumberConst.IntDef.INT_TWO;
                            message = "新文件生成成功！";
                            param.setFileName(fileName);
                            fileId = this.ibr121409Logic.dataResolve(param);
                        }
                    } else {
                        param.setFileName(fileName);
                        fileId= this.ibr121409Logic.dataResolve(param);
                        count = NumberConst.IntDef.INT_TWO;
                        message = "报表生成中";
                    }
                }else {
                    message = "文件不存在";
                }
            }
            if(param.getFileStatusFlag().equals("2")){
                this.ibr121409Logic.createFileInformatica(param);
                message = "文件生成成功";
            }
            if(param.getFileStatusFlag().equals("3")){
                count = this.ibr121409Logic.deleteFlag(param);
                if(count == NumberConst.IntDef.INT_ZERO){
                    message = "文件删除失败";
                }else {
                    message = "文件删除成功";
                }

            }
        }

        IBR121409RsBean ibr121409RsBean = new IBR121409RsBean();
        ibr121409RsBean.setDataCount(String.valueOf(count));
        ibr121409RsBean.setFileId(fileId);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage(message);
        rsResponse.setResult(ibr121409RsBean);
        return rsResponse;
    }
}


