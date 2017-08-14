package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.br.bean.IBR121305RsParam;
import com.msk.br.bean.IBR121306RsBean;
import com.msk.br.bean.IBR121306RsPageResult;
import com.msk.br.bean.IBR121306RsParam;
import com.msk.br.logic.IBR121306Logic;
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
public class IBR121306RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBR121306RsController.class);
    @Autowired
    private IBR121306Logic ibr121306Logic;

    /**
     * 删除冻品管家组管家信息
     * 
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/hkListInHkGroup/delete",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Integer> deleteHkListInHkGroup(@RequestBody RsRequest<IBR121306RsParam> param) {
        logger.info("删除冻品管家组管家信息");
        RsResponse<Integer> response = new RsResponse<>();
        String hkGroupId = param.getParam().getHkGroupId();
        int result = 0;
        String message = "";
        if (!StringUtil.isNullOrEmpty(hkGroupId)) {
            result = this.ibr121306Logic.modify(param.getParam());
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            message = "删除成功!";
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            message = "请输入冻品管家组ID";
        }
        response.setResult(result);
        response.setMessage(message);
        return response;
    }

    /**
     * 查询冻品管家组管家信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/hkListInHkGroup/query",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<IBR121306RsPageResult> queryHkListInHkGroup(@RequestBody RsRequest<IBR121306RsParam> param) {
        logger.info("查询冻品管家组管家信息");
        RsResponse<IBR121306RsPageResult> response = new RsResponse<>();
        IBR121306RsPageResult pageResult = new IBR121306RsPageResult();
        List<IBR121306RsBean> list = this.ibr121306Logic.findPageList(param.getParam(), IBR121306RsBean.class);
        int count = this.ibr121306Logic.getPageCount(param.getParam());
        pageResult.setIbr121306RsBeanList(list);
        pageResult.setTotalCount(count);
        response.setResult(pageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }

    /**
     * 批量添加冻品管家组的冻品管家
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/hkGroupInfos/update",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Integer> updateHkGroupInfos(@RequestBody RsRequest<IBR121306RsParam> param) {
        logger.info("批量添加冻品管家组的冻品管家");
        RsResponse<Integer> response = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        int result = 0;
        String message = "";
        if (param.getParam() != null) {
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);
            String hkGroupId = param.getParam().getHkGroupId();
            List<IBR121306RsParam> houseList = param.getParam().getHouseList();
            if (!CollectionUtils.isEmpty(houseList) && !StringUtil.isNullOrEmpty(hkGroupId)) {
                result = this.ibr121306Logic.insertBrHkInfoList(param.getParam());
                if(result >0 ){
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    message = "处理成功";
                }else {
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    message = "该冻品管家已在该管家组！不允许重复插入！";
                }
            } else {
                response.setStatus(SystemConst.RsStatus.FAIL);
                message = "请输入冻品管家主键信息";
            }
            response.setResult(result);
            response.setMessage(message);
        }
        return response;
    }

    @RequestMapping(value = "/br/hkGroupForHkInfo/query",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<IBR121306RsPageResult> queryHkGroupForHkInfo(@RequestBody RsRequest<IBR121305RsParam> param) {
        RsResponse<IBR121306RsPageResult> response = new RsResponse<>();
        IBR121306RsPageResult pageResult = new IBR121306RsPageResult();
        if(param.getParam() != null ){
            if(param.getParam().getCreationEndTime() != null){
                List<IBR121306RsBean> list = this.ibr121306Logic.getHkGroupForHkInfoByParam(param.getParam());
                pageResult.setIbr121306RsBeanList(list);
                response.setResult(pageResult);
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setMessage("处理成功");
            }else{
                response.setResult(pageResult);
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("传入所属时间不能为空");
            }
        }else{
            response.setResult(pageResult);
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("传入参数为空");
        }
        return response;
    }

}
