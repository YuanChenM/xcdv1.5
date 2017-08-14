package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.logic.IBY121208Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByBuyerRecAddr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * IBY121208RsController.
 *
 * @author zhou_yajun
 */
@RestController
public class IBY121208RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121208RsController.class);

    @Autowired
    private IBY121208Logic iby121208Logic;

    /**
     * 买家收货地址更新接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/receiveAddr/update",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<ByBuyerRecAddr>> buyerReceiveAddrUpdate(@RequestBody RsRequest<List<ByBuyerRecAddr>> param) {
        RsResponse<List<ByBuyerRecAddr>> rs = new RsResponse<>();
        List<ByBuyerRecAddr> recAddrList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(param.getParam())){
            for (int i = NumberConst.IntDef.INT_ZERO;i<param.getParam().size();i++){
                super.setCommonParam(param.getParam().get(i));
                Date currentDate = DateTimeUtil.getCustomerDate();
                param.getParam().get(i).setCrtTime(currentDate);
                param.getParam().get(i).setUpdTime(currentDate);
                param.getParam().get(i).setActTime(currentDate);
                param.getParam().get(i).setCrtId(param.getLoginId());
                param.getParam().get(i).setUpdId(param.getLoginId());
                param.getParam().get(i).setActId(param.getLoginId());

            }
        int resultCount = iby121208Logic.buyerReceiveAddrModify(param.getParam(), recAddrList);
        if (resultCount == NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家收货地址更新失败");
        } else {
            rs.setResult(recAddrList);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家收货地址更新成功");
        }
      }
        return rs;
    }

    /**
     * 买家收货地址查询接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/receiveAddr/findList",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<ByBuyerRecAddr>> buyerReceiveAddrFindList(@RequestBody RsRequest<ByBuyerRecAddr> param) {
        RsResponse<List<ByBuyerRecAddr>> rs = new RsResponse<>();
        List<ByBuyerRecAddr> receiveAddrList = iby121208Logic.buyerReceiveAddrFind(param.getParam());
        if (CollectionUtils.isEmpty(receiveAddrList)) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家收货地址信息不存在");
        } else {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家收货地址获取成功");
            rs.setResult(receiveAddrList);
        }
        return rs;
    }

    /**
     * 通路注册买家收货地址新增
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/receiveAddr/phoneSave",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<ByBuyerRecAddr> buyerEmployeePhoneSave(@RequestBody RsRequest<ByBuyerRecAddr> param) {
        RsResponse<ByBuyerRecAddr> rs = new RsResponse<>();
        if(param.getParam() != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);

            ByBuyerRecAddr recAddr = this.iby121208Logic.buyerEmployeePhoneInsert(param.getParam());
            if (recAddr == null) {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家收货地址新增失败！");
            } else {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("买家收货地址新增成功");
                rs.setResult(recAddr);
            }
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("參數為空，买家收货地址新增失败！");
        }
        return rs;
    }

    /**
     * 通路注册买家收货地址删除
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/receiveAddr/delete",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Object> buyerReceiveAddrDelete(@RequestBody RsRequest<ByBuyerRecAddr> param) {
        RsResponse<Object> rs = new RsResponse<>();
        int deleteResult = this.iby121208Logic.buyerReceiveAddrDelete(param.getParam());
        if (deleteResult == NumberConst.IntDef.INT_ONE) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家收货地址删除成功!");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家收货地址删除失败!");
        }
        return rs;
    }
}
