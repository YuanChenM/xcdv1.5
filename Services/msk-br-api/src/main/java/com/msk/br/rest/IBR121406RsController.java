package com.msk.br.rest;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.BR121405Bean;
import com.msk.br.bean.BR121406RsBean;
import com.msk.br.bean.BR121406RsPageResult;
import com.msk.br.bean.BR121406RsParam;
import com.msk.br.logic.IBR121406Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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
 * 分类买家标准产品池接口
 * Created by tao_zhifa on 2016/7/26.
 */
@RestController
public class IBR121406RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121406RsController.class);

    @Autowired
    private IBR121406Logic ibr121406Logic;

    /**
     * 分类买家标准产品池列表查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/fileBuyerPools/query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121406RsPageResult> queryFileBuyerPools(@RequestBody RsRequest<BasePageParam> param) {
        logger.debug("分类买家标准产品池管控列表查询");
        RsResponse<BR121406RsPageResult> rsResponse = new RsResponse<>();
        BR121406RsPageResult br121406RsPageResult = new BR121406RsPageResult();

        DbUtils.buildLikeCondition(param.getParam(), "fileName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param.getParam(), "marketingsPeriodName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param.getParam(), "marketName", DbUtils.LikeMode.PARTIAL);

        List<BR121405Bean> brFileBuyerPools = ibr121406Logic.findPageList(param.getParam(), BR121405Bean.class);
        int count = this.ibr121406Logic.getPageCount(param.getParam());
        br121406RsPageResult.setBrFileBuyerPoolList(brFileBuyerPools);
        br121406RsPageResult.setTotalCount(count);
        rsResponse.setResult(br121406RsPageResult);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("处理成功");
        return rsResponse;
    }

    @RequestMapping(value = "/br/excelFiles/generateAndUpload", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121406RsBean> generateAndUploadExcelFiles(@RequestBody RsRequest<BR121406RsParam> br121406RsParam) {
        logger.debug("生成并上传excel文件");
        RsResponse<BR121406RsBean> response = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        BaseParam baseParam = new BaseParam();
        //时间在Logic里面set
        super.setCommonParam(baseParam);
        String message = null;
        String fileId = null;
        List<BR121406RsBean> pdPoolList = null;
        int result = 0;
        if(br121406RsParam != null){
            BR121406RsParam param = br121406RsParam.getParam();
            param.setCrtId(br121406RsParam.getLoginId());
            param.setUpdId(br121406RsParam.getLoginId());
            param.setActId(br121406RsParam.getLoginId());
            param.setUpdTime(currentDate);
            param.setCrtTime(currentDate);
            param.setActTime(currentDate);

            if(param.getFileStatusFlag().equals("0")){
                fileId = param.getFileId();
                if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("1"))
                        || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("1"))) {
                    pdPoolList = this.ibr121406Logic.findChickenOrDuckProducts(param);
                } else if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("23"))
                        || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("23"))) {
                    pdPoolList = this.ibr121406Logic.findChickenOrDuckMachiningProducts(param);
                }
                if (!CollectionUtils.isEmpty(pdPoolList)) {
                    String fileName = pdPoolList.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime();
                    baseParam.setFilter("fileName", fileName);
                    baseParam.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_TWO));
                    baseParam.setFilter("currentDate", StringUtil.toString(currentDate));
                    int res = this.ibr121406Logic.isExist(baseParam);
                    if (res > NumberConst.IntDef.INT_ZERO) {
                        message = "报表已存在!";
                        result = NumberConst.IntDef.INT_THREE;
                        if(param.isFlag()){
                            //覆盖生成新文件
                            this.ibr121406Logic.updateFileBuyerPoolByFileName(baseParam);
                            result = NumberConst.IntDef.INT_TWO;
                            message = "新文件生成成功！";
                            param.setFileName(fileName);
                            //super.setCommonParam(param);
                            fileId = this.ibr121406Logic.dataResolve(param);
                        }
                    } else {
                        fileId =  this.ibr121406Logic.dataResolve(param);
                        result = NumberConst.IntDef.INT_TWO;
                        message = "处理成功";

                    }
                } else {
                    message = "不存在符合条件的文件！";
                }
            }

            if(param.getFileStatusFlag().equals("2")){
                this.ibr121406Logic.createFileInformatica(param);
                message = "文件上传成功";
            }
            if(param.getFileStatusFlag().equals("3")){
                result = this.ibr121406Logic.deleteFlag(param);
                if(result == NumberConst.IntDef.INT_ZERO){
                    message = "文件删除失败";
                }else {
                    message = "文件删除成功";
                }

            }
        }
       

        BR121406RsBean br121406RsBean = new BR121406RsBean();
        br121406RsBean.setDataCount(String.valueOf(result));
        br121406RsBean.setFileId(fileId);
        response.setResult(br121406RsBean);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage(message);
        return response;
    }

}
