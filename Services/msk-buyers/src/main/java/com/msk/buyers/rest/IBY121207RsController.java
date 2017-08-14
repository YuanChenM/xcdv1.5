package com.msk.buyers.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.IBY121202RsBean;
import com.msk.buyers.bean.IBY121202RsParam;
import com.msk.buyers.bean.IBY121207RsParam;
import com.msk.buyers.logic.BY12130401Logic;
import com.msk.buyers.logic.IBY121202Logic;
import com.msk.buyers.logic.IBY121207Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.ByBuyerEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * IBY121207RsController.
 *
 * @author zhou_yajun
 */
@RestController
public class IBY121207RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121207RsController.class);

    @Autowired
    private BY12130401Logic by12130401Logic;

    @Autowired
    private IBY121207Logic iby121207Logic;

    @Autowired
    private IBY121202Logic iby121202Logic;

    /**
     * 买家雇员信息更新接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/employee/update",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Object> buyerEmployeeUpdate(@RequestBody RsRequest<List<IBY121207RsParam>> param){
        RsResponse<Object> rs = new RsResponse<>();
        if(!CollectionUtils.isEmpty(param.getParam())){
            for (int i = NumberConst.IntDef.INT_ZERO;i<param.getParam().size();i++){
                super.setCommonParam(param.getParam().get(i));
                Date currentDate = DateTimeUtil.getCustomerDate();
                param.getParam().get(i).setUpdTime(currentDate);
                param.getParam().get(i).setCrtTime(currentDate);
                param.getParam().get(i).setActTime(currentDate);
                param.getParam().get(i).setCrtId(param.getLoginId());
                param.getParam().get(i).setUpdId(param.getLoginId());
                param.getParam().get(i).setActId(param.getLoginId());
            }
        int resultCount = iby121207Logic.buyerEmployeeModify(param.getParam());
        if(resultCount == NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家雇员信息更新失败！");
        }else {
            for (int j = NumberConst.IntDef.INT_ZERO;j<param.getParam().size();j++) {
                IBY121202RsParam iby121202RsParam = new IBY121202RsParam();
                iby121202RsParam.setBuyerId(param.getParam().get(j).getBuyerId());
                IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
                BrOBuyerInfo brOBuyerInfo = new BrOBuyerInfo();
                brOBuyerInfo = iby121202Logic.findBaseBuyerInfo(iby121202RsParam);
                iby121202RsBean.setBrOBuyerInfo(brOBuyerInfo);
                RsRequest<IBY121202RsBean> request = new RsRequest<>();
                request.setSiteCode("1");
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setParam(iby121202RsBean);
//                   String url = "http://localhost:8180/api/br/buyerReportInfo/_update";
                String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
                RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });
            }
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家雇员信息更新成功");


        }

    }else {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("参数为空，买家雇员信息更新成功");
        }
        return rs;
    }

    /**
     * 查询boss是否存在多个
     * @return
     * @author tao_zhifa
     */
    @RequestMapping(value = "by/findBossCount/search",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    RsResponse<Integer> findBossCount(@RequestBody RsRequest<IBY121207RsParam> byBuyerEmployee){
        RsResponse<Integer> rs = new RsResponse<>();
        int updateCount = NumberConst.IntDef.INT_ZERO;
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",byBuyerEmployee.getParam().getBuyerId());
        if(byBuyerEmployee.getParam().getEmployeeStatus().equals("add")){
            updateCount = iby121207Logic.getCount(param);
        }
        if(byBuyerEmployee.getParam().getEmployeeStatus().equals("modify")){
            IBY121207RsParam iby121207RsParam= iby121207Logic.buyerEmployee(byBuyerEmployee.getParam());
            if(iby121207RsParam.getEmployeeType().equals("1")){
                if(byBuyerEmployee.getParam().getEmployeeType().equals("1")){
                    updateCount = 0;
                }else {
                    updateCount = iby121207Logic.getCount(param);
                }
            }else {
                updateCount = iby121207Logic.getCount(param);
            }
        }

        if(updateCount == NumberConst.IntDef.INT_ZERO){
            rs.setResult(updateCount);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("雇员信息更新成功!");


        }else{
            rs.setResult(updateCount);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("雇员信息更新失败,该店铺老板已存在!");
        }
        return  rs;
    }


    /**
     * 买家雇员信息查询接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/employee/findList",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IBY121207RsParam>> buyerEmployeeFindList(@RequestBody RsRequest<ByBuyerEmployee> param){
        RsResponse<List<IBY121207RsParam>> rs = new RsResponse<>();
        List<IBY121207RsParam> employeeList = iby121207Logic.buyerEmployeeFind(param.getParam());
        if(CollectionUtils.isEmpty(employeeList)){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家雇员信息不存在");
        }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家雇员信息获取成功");
            rs.setResult(employeeList);
        }
        return rs;
    }
    /**
     * 通路注册雇员信息新增
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/employee/phoneSave",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<IBY121207RsParam> buyerEmployeePhoneSave(@RequestBody RsRequest<IBY121207RsParam> param){
        RsResponse<IBY121207RsParam> rs = new RsResponse<>();
        IBY121207RsParam employee = this.iby121207Logic.buyerEmployeePhoneInsert(param.getParam());
        if(employee == null){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家雇员信息新增失败！");
        } else {
                IBY121202RsParam iby121202RsParam = new IBY121202RsParam();
                iby121202RsParam.setBuyerId(employee.getBuyerId());
                IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
                BrOBuyerInfo brOBuyerInfo = new BrOBuyerInfo();
                brOBuyerInfo =  iby121202Logic.findBaseBuyerInfo(iby121202RsParam);
                iby121202RsBean.setBrOBuyerInfo(brOBuyerInfo);
                RsRequest<IBY121202RsBean> request = new RsRequest<>();
                request.setSiteCode("1");
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setParam(iby121202RsBean);
//                String url = "http://localhost:8180/api/br/buyerReportInfo/_update";
                String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
                RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });



            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家雇员信息新增成功");
            rs.setResult(employee);
        }
        return rs;
    }
}
