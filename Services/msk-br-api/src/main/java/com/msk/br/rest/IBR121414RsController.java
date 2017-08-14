package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.IBR121414Result;
import com.msk.br.bean.IBR121414RsParam;
import com.msk.br.logic.IBR121414Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrMPdClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tao_zhifa on 2016/9/28.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class IBR121414RsController extends BaseRsController{

    private static Logger logger = LoggerFactory.getLogger(IBR121414RsController.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private IBR121414Logic ibr121414Logic;

    @RequestMapping(value = "br/mPdClasses/update",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    RsResponse<Integer> updateMPdClasses(@RequestBody RsRequest<IBR121414RsParam> rsRequest){
        RsResponse<Integer> rsResponse = new RsResponse<>();
        IBR121414RsParam param = rsRequest.getParam();
        Date currentDate =  DateTimeUtil.getCustomerDate();
        String machin = "";
        String machinName = "";
        int count = 0;
        int result = 0;
        int nameCount = 0;
        if(param != null){
            param.setActId(rsRequest.getLoginId());
            param.setActTime(currentDate);
            param.setUpdId(rsRequest.getLoginId());
            param.setUpdTime(currentDate);
            param.setCrtId(rsRequest.getLoginId());
            param.setCrtTime(currentDate);
            if(param.getProductList() != null){
                for(int i=0;i<param.getProductList().size();i++){
                    machin = machin + param.getProductList().get(i).getMachiningCode();
                    machinName = machinName + param.getProductList().get(i).getMachiningName();
                    param.setClassesCode(param.getProductList().get(i).getClassesCode());
                }
                param.setByPoolMachiningCode(param.getProductList().get(0).getMachiningCode());
                param.setMachiningCodeU(machin);
                if(param.getIsChecked() == null || !(param.getIsChecked().equals("1"))){
                    param.setMachiningNameU(machinName);
                }
                param.setClassesCode(param.getProductList().get(0).getClassesCode());
                param.setClassesName(param.getProductList().get(0).getClassesName());

                param.setMachiningCodeU(param.getProductList().get(0).getMachiningCode());
                param.setMachiningCodeU(DbUtils.buildLikeCondition(param.getMachiningCodeU(), DbUtils.LikeMode.PARTIAL));
                nameCount = ibr121414Logic.nameCount(param);

                if(nameCount != NumberConst.IntDef.INT_ZERO){
                    ibr121414Logic.updateDelFlg(param);
                }
                for(int i=0;i<param.getProductList().size();i++){
                    param.setMachiningCode(param.getProductList().get(i).getMachiningCode());
                    param.setMachiningName(param.getProductList().get(i).getMachiningName());
                    count = ibr121414Logic.getCount(param);
                    param.setMachiningCodeU(machin);
                    if(count == NumberConst.IntDef.INT_ZERO){
                        Long maxId = commonLogic.maxId("br_m_pd_classes","ID");
                        param.setId(maxId);
                        result = ibr121414Logic.save(param);
                    }else {
                        result = ibr121414Logic.modify(param);
                    }
                }
            }else{
                result = ibr121414Logic.deleteMachin(param);
            }


        }
        if(result == NumberConst.IntDef.INT_ZERO){
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("买家池产品分类基础表保存失败");
            rsResponse.setResult(result);

        }else {
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("买家池产品分类基础表保存成功");
            rsResponse.setResult(result);
        }
        return rsResponse;
    }

    @RequestMapping(value = "br/mPdClasses/select",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    RsResponse<IBR121414Result> selectMPdClasses(@RequestBody RsRequest<IBR121414RsParam> rsRequest){
        RsResponse<IBR121414Result> rsResponse = new RsResponse<>();
        List<BrMPdClasses> brMPdClassesList = new ArrayList<>();
        IBR121414Result ibr121414Result = new IBR121414Result();
        brMPdClassesList = ibr121414Logic.findList(rsRequest.getParam());
        ibr121414Result.setBrMPdClassesList(brMPdClassesList);
        if(brMPdClassesList.size() == NumberConst.IntDef.INT_ZERO){
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("买家池产品分类基础表查询失败");
            rsResponse.setResult(ibr121414Result);

        }else {
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("买家池产品分类基础表查询成功");
            rsResponse.setResult(ibr121414Result);
        }
        return rsResponse;

    }

}
