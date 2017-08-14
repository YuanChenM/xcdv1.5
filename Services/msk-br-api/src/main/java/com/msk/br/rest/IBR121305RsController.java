package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.br.bean.IBR121305RsBean;
import com.msk.br.bean.IBR121305RsPageResult;
import com.msk.br.bean.IBR121305RsParam;
import com.msk.br.logic.IBR121305Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrHkGroup;
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
 * 冻品管家组接口
 * Created by yuan_zhifei on 2016/8/3.
 */
@RestController
public class IBR121305RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IBR121305RsController.class);

    @Autowired
    private IBR121305Logic ibr121305Logic;
    Date currentDate = DateTimeUtil.getCustomerDate();

    /**
     * 查询冻品管家组信息
     * @param param
     * @return
     */

    @RequestMapping(value="/br/hkGroupInfo/query",
                    method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121305RsPageResult> queryHkGroupInfo(@RequestBody RsRequest<IBR121305RsParam> param){
        logger.info("查询冻品管家组信息");
        RsResponse<IBR121305RsPageResult> response = new RsResponse<>();
        IBR121305RsPageResult pageResult=new IBR121305RsPageResult();

        List<IBR121305RsBean> res =this.ibr121305Logic.findPageList(param.getParam(),IBR121305RsBean.class);
        int count=this.ibr121305Logic.getPageCount(param.getParam());
        pageResult.setIbr121305RsBeanList(res);
        pageResult.setTotalCount(count);
        response.setResult(pageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }

    /**
     * 编辑冻品管家组名称
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/hkGroupName/update",
            method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateHkGroupName(@RequestBody RsRequest<List<IBR121305RsParam>> param){
        logger.info("编辑冻品管家组名称");
        RsResponse<Integer> response = new RsResponse<>();
        List <IBR121305RsParam> houseList=param.getParam();
        if(houseList!=null) {
            for (int i = NumberConst.IntDef.INT_ZERO; i < houseList.size(); i++) {
                Date currentDate = DateTimeUtil.getCustomerDate();
                houseList.get(i).setCrtId(param.getLoginId());
                houseList.get(i).setUpdId(param.getLoginId());
                houseList.get(i).setActId(param.getLoginId());
                houseList.get(i).setUpdTime(currentDate);
                houseList.get(i).setCrtTime(currentDate);
                houseList.get(i).setActTime(currentDate);
            }


            int result = 0;
            String message = "";
            if (!CollectionUtils.isEmpty(houseList)) {
                this.ibr121305Logic.modifyPdGroupList(houseList);
                result = 1;
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                message = "处理成功!";
            } else {
                response.setStatus(SystemConst.RsStatus.FAIL);
                message = "请输入要修改的对象!";
            }
            response.setResult(result);
            response.setMessage(message);
        }else{
            response.setMessage("参数为空，处理失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }

        return response;
    }

    /**
     * 新增冻品管家组信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/hkGroupName/add",
            method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.br.validator.IBR121305Validator")
    public RsResponse<Integer> addHkGroup(@RequestBody RsRequest<BrHkGroup> param){
        logger.info("编辑冻品管家组名称");
        RsResponse<Integer> response = new RsResponse<>();
        BrHkGroup brHkGroup = param.getParam();
        int result=1;
        String message="";
        if(brHkGroup != null){
            brHkGroup.setCrtId(param.getLoginId());
            brHkGroup.setCrtTime(DateTimeUtil.getCustomerDate());
            result = this.ibr121305Logic.addBrHkGroupInfo(brHkGroup);
            if(result == NumberConst.IntDef.INT_ZERO){
                response.setResult(result);
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                message="处理成功!";
            }else{
                response.setResult(result);
                response.setStatus(SystemConst.RsStatus.FAIL);
                message="重复数据!";
            }
        }else {
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.FAIL);
            message="请输入要新增的对象!";
        }
        response.setResult(result);
        response.setMessage(message);
        return response;
    }
}
