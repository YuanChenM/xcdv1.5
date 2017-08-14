package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.br.bean.IBR121307RsPageResult;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.br.logic.IBR121307Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrMPdClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by tao_zhifa on 2016/8/10.
 */
@RestController
public class IBR121307RsController extends BaseRsController{

    private static Logger logger = LoggerFactory.getLogger(IBR121306RsController.class);
    @Autowired
    private IBR121307Logic ibr121307Logic;

    /**
     * 查询产品分类接口
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/brMPdClasses/findMachiningCodeU", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121307RsPageResult> findMachiningCodeU(@RequestBody RsRequest<IBR121307RsParam> param) {
        logger.info("查询产品分类接口");
        RsResponse<IBR121307RsPageResult> rsResponse = new RsResponse<>();
        IBR121307RsPageResult pageResult = new IBR121307RsPageResult();
        List<BrMPdClasses> brMPdClasses = new ArrayList<>();
        if(param.getParam().getType().equals("1")){
            brMPdClasses = ibr121307Logic.findMachiningList(param.getParam());
        }else {
            brMPdClasses = ibr121307Logic.findClassesList(param.getParam());
        }
        pageResult.setBrMPdClassesList(brMPdClasses);
        rsResponse.setResult(pageResult);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("处理成功");
        return rsResponse;

    }

}
