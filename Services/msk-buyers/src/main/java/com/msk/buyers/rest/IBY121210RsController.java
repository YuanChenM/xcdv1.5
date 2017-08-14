package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.msk.buyers.logic.IBY121210Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByResearchCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * IBY121210RsController.
 *
 * @author zhou_ling
 */
@RestController
public class IBY121210RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121210RsController.class);

    @Autowired
    private IBY121210Logic iby121210Logic;

    /**
     * 添加新品种需求接口
     *
     * @param param param
     * @return 结果
     * @author zhou_ling
     */
    @RequestMapping(value = "/by/add/newCategory",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Object> addNewCategory(@RequestBody RsRequest<ByResearchCategory> param){
        // 判断产品表新品种数据是否录入
        boolean existFlag = iby121210Logic.researchCategoryJudge(param.getParam());
        // 判断买家表里此品种是否已经被调研
        boolean researchFlag = iby121210Logic.researchJudge(param.getParam());
        RsResponse<Object> rs = new RsResponse<>();
        if(existFlag){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("错误：添加新品种失败，品种已注册。");
        }else if(researchFlag) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("错误：添加新品种失败，此买家的品种已被调研。");
        }else {
            iby121210Logic.researchCategorySave(param.getParam());
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("添加新品种成功！");
        }
        return rs;
    }

}
