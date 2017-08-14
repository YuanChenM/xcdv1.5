package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBA2141112Param;
import com.msk.bs.bean.IBA2141112Result;
import com.msk.bs.logic.IBA2141112RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/13.
 */
@RestController
@Api(description = "冻品管家收货地址增删改查RestController", tags = {"IBA2141112RsController"})
public class IBA2141112RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBA2141112RsController.class);

    @Autowired
    private IBA2141112RsLogic iba2141111RsLogic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 查询冻品管家收货地址
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "查询收货地址", notes = "查询冻品管家、买手收货地址接口")
    @RequestMapping(value = "/bs/search/houseBook", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141112Result>> searchHouseInfo(@RequestBody RsRequest<IBA2141112Param> request) {
        logger.info("开始查询冻品管家、买手收货地址AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        RsResponse<List<IBA2141112Result>> response = new RsResponse<List<IBA2141112Result>>();
        IBA2141112Param param = request.getParam();
        List<IBA2141112Result> resultList = iba2141111RsLogic.findAddInfo(param);
        response.setResult(resultList);
        response.setMessage("处理成功");
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        logger.info("查询冻品管家的收货地址结束AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        return response;
    }


    /**
     * 删除收货地址
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "删除收货地址", notes = "根据slRecbookId删除收货地址接口")
    @RequestMapping(value = "/bs/delete/houseBook", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> deleteHouseInfo(@RequestBody RsRequest<IBA2141112Param> request) {
        logger.info("开始删除冻品管家、买手收货地址AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        RsResponse<Integer> response = new RsResponse<Integer>();
        IBA2141112Param param = request.getParam();
        param.setUpdTime(new Date());
        int i = iba2141111RsLogic.deleteHouseAddress(param);
        if (i > 0) {
            response.setResult(i);
            response.setMessage("删除成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("删除失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("删除冻品管家、买手收货地址结束AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        return response;
    }


    /**
     * 新增管家收货地址
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "新增收货地址", notes = "新增管家、买手收货地址接口")
    @RequestMapping(value = "/bs/add/houseBook", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> addHouseAddress(@RequestBody RsRequest<IBA2141112Param> request) throws UnsupportedEncodingException {
        logger.info("开始新增管家、买手的收货地址AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        RsResponse<Integer> response = new RsResponse<Integer>();
        IBA2141112Param param = request.getParam();
        // 2-管家，3-买手
        if(param.getAccessType().equals("2")){
            String slcode = iba2141111RsLogic.selectHouseAccount(param);
            Long slRecbookId = commonLogic.maxId("SL_HOUSE_RECEIVE_BOOK", "SL_RECBOOK_ID");
            if (!StringUtil.isNullOrEmpty(slcode)) {
                param.setSlRecbookId(slRecbookId);
                param.setUpdId(param.getHouseCode());
                param.setUpdTime(new Date());
                param.setCrtId(param.getHouseCode());
                param.setCrtTime(new Date());
                param.setActId(param.getHouseCode());
                param.setActTime(new Date());
                param.setFullAddress(URLDecoder.decode(param.getFullAddress(), "UTF-8"));
                param.setAddress(URLDecoder.decode(param.getAddress(), "UTF-8"));
                param.setBuyerName(URLDecoder.decode(param.getBuyerName(), "UTF-8"));
                param.setSlCode(slcode);
            } else {
                logger.info("买手店ID（SL_CODE）:", slcode);
                response.setMessage("新增失败");
                response.setStatus(SystemConst.RsStatus.FAIL);
                return response;
            }
        }else{
            Long slRecbookId = commonLogic.maxId("SL_BUYERS_RECEIVE_BOOK", "SL_RECBOOK_ID");
            param.setSlRecbookId(slRecbookId);
            param.setUpdId(param.getSlCode());
            param.setUpdTime(new Date());
            param.setCrtId(param.getSlCode());
            param.setCrtTime(new Date());
            param.setActId(param.getSlCode());
            param.setActTime(new Date());
            param.setFullAddress(param.getFullAddress());
            param.setAddress(param.getAddress());
            param.setBuyerName(param.getBuyerName());
            param.setSlCode(param.getSlCode());
        }
        int i = iba2141111RsLogic.addHouseAddress(param);
        if (i > 0) {
            response.setResult(i);
            response.setMessage("新增成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("新增失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("新增管家、买手收货地址结束AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        return response;
    }


    /**
     * 编辑收货地址信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "编辑收货地址", notes = "编辑管家、买手收货地址信息接口")
    @RequestMapping(value = "/bs/modify/houseBook", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> modifyHouseInfo(@RequestBody RsRequest<IBA2141112Param> request) throws UnsupportedEncodingException {
        logger.info("开始更新冻品管家、买手收货地址AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        RsResponse<Integer> response = new RsResponse<Integer>();
        IBA2141112Param param = request.getParam();
        param.setUpdTime(new Date());
        int i = iba2141111RsLogic.modifyHouseAddress(param);
        if (i > 0) {
            response.setResult(i);
            response.setMessage("更新成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("更新失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("更新冻品管家、买手收货地址结束AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        return response;
    }

    /**
     * 根据收货地址ID查询收货地址信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "根据收货地址ID查询信息", notes = "根据收货地址ID查询收货地址信息接口")
    @RequestMapping(value = "/bs/find/HouseBookByRecbookId", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBA2141112Result> findHouseBookByRecbookId(@RequestBody RsRequest<IBA2141112Param> request) {
        logger.info("开始根据收货地址ID查询冻品管家、买手收货地址AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        RsResponse<IBA2141112Result> response = new RsResponse<IBA2141112Result>();
        IBA2141112Param param = request.getParam();
        IBA2141112Result iba2141112Result = iba2141111RsLogic.findHouseBookInfo(param);
        if (null != iba2141112Result) {
            response.setResult(iba2141112Result);
            response.setMessage("处理成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("处理失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("根据收货地址ID冻品管家、买手收货地址结束AccessType:{},2-管家，3-买手",request.getParam().getAccessType());
        return response;
    }


}
