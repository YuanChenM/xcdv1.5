package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;

import com.msk.product.bean.IPD141149Param;
import com.msk.product.bean.IPD141149Result;
import com.msk.product.logic.IPD141149Logic;
import com.msk.product.logic.ProductLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 销售状态一览查询接口
 * IPD141149RsController.
 * 
 * @author ren_qiang
 */
@RestController
public class IPD141149RsController extends BaseRsController {

    @Autowired
    private IPD141149Logic ipd141149Logic;
    @Autowired
    private ProductLogic productLogic;
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IPD141149RsController.class);
    /**
     * 查询产品运行状态
     * 
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "pd/searchProYyStatus", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IPD141149Result>> searchProYyStatus(@RequestBody RsRequest<IPD141149Param> param) {
        RsResponse<List<IPD141149Result>> rs = new RsResponse<List<IPD141149Result>>();

        /*List<IPD141149Param> res =  this.productLogic.findPdGrade(param.getParam());*/
        // 查询所有销售状态信息
        List<IPD141149Result> result = ipd141149Logic.searchProYyStatus(param.getParam());
        if (result.size() > NumberConst.IntDef.INT_ZERO && result != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询正常！");
            rs.setResult(result);
            logger.info("查询到数据");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("销售状态没有查询到数据！");
        return rs;
    }
}
