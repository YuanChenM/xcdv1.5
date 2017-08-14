package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.br.bean.IBR121410RsBean;
import com.msk.br.bean.IBR121410RsParam;
import com.msk.br.logic.IBR121408Logic;
import com.msk.br.logic.IBR121410Logic;
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


@RestController
public class IBR121410RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121410RsController.class);

    @Autowired
    private IBR121410Logic ibr121410Logic;
    @Autowired
    private IBR121408Logic ibr121408Logic;

    /*
        * 分销分类买家池生成excel文件
        * @param param
        * @return
        */
    @RequestMapping(value = "/br/save/exclusiveExcelFiles", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121410RsBean> exclusiveExcelFilesSave(@RequestBody RsRequest<IBR121410RsParam> ibr121410param) {
        logger.debug("生成并上传excel文件");
        RsResponse<IBR121410RsBean> response = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        int result = 0;
        String message = null;
        String fileId = null;
        if (ibr121410param != null) {
            IBR121410RsParam param = ibr121410param.getParam();
            param.setCrtId(ibr121410param.getLoginId());
            param.setUpdId(ibr121410param.getLoginId());
            param.setActId(ibr121410param.getLoginId());
            param.setUpdTime(currentDate);
            param.setCrtTime(currentDate);
            param.setActTime(currentDate);

            List<IBR121410RsBean> houseList = this.ibr121410Logic.getHouseList(param);
            if (!CollectionUtils.isEmpty(houseList)) {
                param.setHouseList(houseList);
            }
            List<IBR121410RsBean> list = ibr121410Logic.getHouseBasicInfo(param);
            BaseParam baseParam = new BaseParam();
            baseParam.setUpdId(ibr121410param.getLoginId());
            List<IBR121410RsBean> buyerPoolList = this.ibr121410Logic.findOnlineVipByPool(param);


            String subTitle = "分销买家池专属会员分池买家汇总管控表";
            if (!CollectionUtils.isEmpty(buyerPoolList) && !CollectionUtils.isEmpty(list)) {
                String fileName = buyerPoolList.get(NumberConst.IntDef.INT_ZERO).getFileName()
                        + param.getClassesName() + param.getMachiningNameU() + subTitle + param.getFileStartTime() + "~"
                        + param.getFileEndTime();
                baseParam.setFilter("fileName", fileName);
                baseParam.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_SIX));

                if (ibr121410param.getParam().getFileStatusFlag().equals("0")) {

                    int reg = this.ibr121408Logic.isExist(baseParam);
                    if (reg > NumberConst.IntDef.INT_ZERO) {
                        result = NumberConst.IntDef.INT_THREE;
                        message = "报表已存在";
                        if (param.isFlag()) {
                            //覆盖生成新文件
                            this.ibr121408Logic.updateFileBuyerPoolByFileName(baseParam);
                            result = NumberConst.IntDef.INT_TWO;
                            message = "新文件生成成功！";
                            param.setFileName(fileName);
                            fileId = this.ibr121410Logic.dataResolve(param);
                        }
                    } else {
                        result = NumberConst.IntDef.INT_TWO;
                        message = "生成成功！";
                        param.setFileName(fileName);
                        fileId = this.ibr121410Logic.dataResolve(param);
                    }
                } else {
                    message = "文件不存在";
                }

            }
            if (ibr121410param.getParam().getFileStatusFlag().equals("2")) {
                this.ibr121410Logic.createFileInformatica(param, list);
                message = "文件生成成功";
            }
            if(param.getFileStatusFlag().equals("3")){
                result = this.ibr121410Logic.deleteFlag(param);
                if(result == NumberConst.IntDef.INT_ZERO){
                    message = "文件删除失败";
                }else {
                    message = "文件删除成功";
                }

            }

        }


        IBR121410RsBean ibr121410RsBean = new IBR121410RsBean();
        ibr121410RsBean.setDataCount(String.valueOf(result));
        ibr121410RsBean.setFileId(fileId);
        response.setResult(ibr121410RsBean);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage(message);


        return response;
    }

}


