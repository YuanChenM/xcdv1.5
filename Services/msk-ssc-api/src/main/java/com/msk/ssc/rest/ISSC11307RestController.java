package com.msk.ssc.rest;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11307Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询付款一览列表
 * Created by wu_honglei on 2016/7/5.
 */
@RestController
public class ISSC11307RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11307RestController.class);


    @Autowired
    private SSC11307Logic ssc11307Logic;



    /**
     * 查询采供链付款申请一览
     *
     * @param param
     * @returnREQUEST
     */
    @RequestMapping(value = "/ssc/findSscPaymentRequest",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})

    public RsResponse<PageResult<SSC11307RsBean>> findSscPaymentRequest(@RequestBody RsRequest<SSC11307RsParam> param) {
        logger.info("查询付款一览列表");
        RsResponse<PageResult<SSC11307RsBean>> response= new RsResponse<PageResult<SSC11307RsBean>>();
        //查询数据Count
        int count = this.ssc11307Logic.getPageCount(param.getParam());
        PageResult<SSC11307RsBean> res =this.ssc11307Logic.findPage(param.getParam(), SSC11307RsBean.class );

        //根据相同的合同Id,塞入已付金额
        if(res!=null){
            List<SSC11307RsBean> list=this.ssc11307Logic.getTotalPaidAmount(param.getParam());
            //Modif for Bug#2559 at 2016/09/09 by peng_hao Start
            for (int i = 0; i <res.getData().size() ; i++) {
                for (int j = 0; j < list.size(); j++) {
                    //如果通过合同发起付款申请，合同id不为空
                    if(res.getData().get(i).getContractId() != null){
                        //付款申请list匹配该合同金额已付金额list
                        if(res.getData().get(i).getContractId().equals(list.get(j).getContractId())){
                            res.getData().get(i).setTotalPaidAmount(list.get(j).getTotalPaidAmount());
                        }
                    }
                }
            }
            //Modif for Bug#2559 at 2016/09/09 by peng_han End

            //根据相同的付款Code,塞入相应最近付款时间
            List<SSC11307RsBean> dateList =this.ssc11307Logic.getMaxRemitTime(param.getParam());
            for (int i = 0; i <res.getData().size() ; i++) {
                for (int j = 0; j < dateList.size(); j++) {
                    //付款申请list匹配最近付款时间list
                    if(dateList.get(j).getPaymentRequestCode().equals(res.getData().get(i).getPaymentRequestCode())){
                        res.getData().get(i).setRemitTimeStr(dateList.get(j).getRemitTimeStr());
                    }
                    }
                }

            //根据最近付款时间查询
            List<SSC11307RsBean> searchList = new ArrayList();
            //如果最近付款时间不为空
            if(param.getParam() != null && !StringUtil.isNullOrEmpty(param.getParam().getRemitTimeStr())){
                for (int i = 0; i <res.getData().size() ; i++) {
                    for (int j = 0; j < dateList.size(); j++) {
                        if(dateList.get(j).getPaymentRequestCode().equals(res.getData().get(i).getPaymentRequestCode())){
                            res.getData().get(i).setRemitTimeStr(dateList.get(j).getRemitTimeStr());
                            searchList.add(res.getData().get(i));
            }
                    }
                }
                res.setData(searchList);
                count=searchList.size();
            }

            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            res.setRecordsTotal(count);
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }


    /**
     *删除付款申请
     *
     * @param ssc11307RsParam
     * @return
     */
    @RequestMapping(value = "/ssc/deleteSscPaymentRequest", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> deleteSscPaymentRequest(@RequestBody RsRequest<SSC11307RsParam> ssc11307RsParam) {
        logger.info("删除付款申请");
        RsResponse<Integer> rs = new RsResponse<Integer>();
           //删除
        SSC11307RsParam been = ssc11307RsParam.getParam();
        Integer requestCount = this.ssc11307Logic.deletePaymentRequest(been);
        if(requestCount != null && requestCount>0){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("删除成功");
            }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("删除失败");
        }
        rs.setResult(requestCount);
        return rs;
    }
}


