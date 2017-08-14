package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.BY121310RsBean;
import com.msk.br.bean.BY121310RsPageResult;
import com.msk.br.bean.BY121310RsParam;
import com.msk.br.logic.IBR121303Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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
 * 买家买家池归属
 * Created by tao_zhifa on 2016/7/11.
 */
@RestController
public class IBR121303RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IBR121303RsController.class);

    @Autowired
    private IBR121303Logic ibr121303Logic;

    @RequestMapping(value = "/br/findPoolAttribution",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BY121310RsBean> findPoolAttribution (@RequestBody RsRequest<BY121310RsParam> param){
        RsResponse<BY121310RsBean> result = new RsResponse();
        PageResult<BY121310RsBean> pageResult = ibr121303Logic.findPage(param.getParam(),BY121310RsBean.class);
        BY121310RsParam by121310Param = new BY121310RsParam();
        BY121310RsBean by121310Bean = new BY121310RsBean();
        List<BY121310RsBean> list = new ArrayList<>();
        for (int i=0;i<pageResult.getData().size();i++) {
            by121310Param.setClassesCode(pageResult.getData().get(i).getClassesCode());
            list = ibr121303Logic.findMachiningName(by121310Param);
            String machiningNameList = "";
            for (int j=0;j<list.size();j++){
                if(machiningNameList == ""){
                    machiningNameList ="□"+list.get(j).getMachiningName();
                }else {
                    machiningNameList =machiningNameList+",□"+list.get(j).getMachiningName();
                }

            }
            by121310Bean.setClassesName(pageResult.getData().get(i).getClassesName());
            by121310Bean.setMachiningName(machiningNameList);
            pageResult.getData().set(i,by121310Bean);
        }
        BY121310RsBean bean = new BY121310RsBean();
        result.setMessage("处理成功");
        result.setStatus(SystemConst.RsStatus.SUCCESS);
        bean.setPageResult(pageResult.getData());
        result.setResult(bean);
        return result;

    }
}
