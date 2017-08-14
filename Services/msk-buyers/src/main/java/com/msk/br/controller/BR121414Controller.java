package com.msk.br.controller;

/**
 * Created by tao_zhifa on 2016/9/28.
 */

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.msk.br.bean.BR121414Param;
import com.msk.br.bean.BR121414Result;
import com.msk.buyers.bean.BY121304Bean;
import com.msk.buyers.logic.BY121304Logic;
import com.msk.buyers.logic.BY121310Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.PdMachining;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("BR121414")
public class BR121414Controller extends BaseController {


    private static Logger logger  = LoggerFactory.getLogger(BR121414Controller.class);

    @Autowired
    private BY121304Logic by121304Logic;

    @Autowired
    private BY121310Logic by121310Logic;

    @Autowired
    private CommonLogic commonLogic;


    /**
     * 页面初始化
     * @param model
     * @return
     */
    @RequestMapping(value = "init" ,method = RequestMethod.POST)
    public String init(Model model){
        logger.debug("");

        // 销售产品
        PDInfoParam pdInfoParam = new PDInfoParam();
        List<PDInfoResult> pdClaCommList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
        List<BY121304Bean> pdClaShowList = new ArrayList<>();
        for (int i = 0; i < pdClaCommList.size(); i++) {
            BY121304Bean pdCla = new BY121304Bean();
            pdCla.setClassCode(pdClaCommList.get(i).getClassesCode());
            pdCla.setClassName(pdClaCommList.get(i).getClassesName());
            pdCla.setIsChecked("0");
            pdClaShowList.add(pdCla);
        }
        model.addAttribute("pdClaShowList", pdClaShowList);
        List<PdMachining> pdMachiningCommList = new ArrayList<>();

        BR121414Param br121414Param = new BR121414Param();
        RsRequest<BR121414Param> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(br121414Param);
//        String url = "http://localhost:8180/api/br/mPdClasses/select";
        String url = SystemServerManager.BuyersReportServerManager.getSelectMPdClasses();
        RsResponse<BR121414Result> pdmachiingList = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121414Result>>() {
        });

        List<BR121414Param> br121414List = new ArrayList<BR121414Param>();
        for (int i = 0; i < pdClaShowList.size(); i++) {
            String machiningCode = null;
            Map<String, String> pdMacMap = new HashMap<>();
            BR121414Param paramList = new BR121414Param();
            paramList.setClassesCode(pdClaShowList.get(i).getClassCode());
            paramList.setClassesName(pdClaShowList.get(i).getClassName());
            BaseParam param = new BaseParam();
            param.setFilter("classCode", pdClaShowList.get(i).getClassCode());

            //调用接口查询二级分类信息
            pdMachiningCommList = by121304Logic.findPdMachining(param);


            List<BR121414Param> pdMsCommList = new ArrayList<>();
            model.addAttribute("pdMachiningCommList", pdMachiningCommList);
            for (int k = 0; k < pdMachiningCommList.size(); k++) {
                if (pdMachiningCommList.get(k).getClassesCode().equals(pdClaShowList.get(i).getClassCode())) {
                    BR121414Param brMPdClasses = new BR121414Param();
                    for(int x=0;x<pdmachiingList.getResult().getBrMPdClassesList().size();x++){
                        BR121414Param brMPdClass = new BR121414Param();
                        if(pdMachiningCommList.get(k).getClassesCode().equals(pdmachiingList.getResult().getBrMPdClassesList().get(x).getClassesCode())){
                            if(pdmachiingList.getResult().getBrMPdClassesList().get(x).getMachiningCodeU().contains(pdMachiningCommList.get(k).getMachiningCode())){
                                brMPdClass.setMachiningCodeU(pdmachiingList.getResult().getBrMPdClassesList().get(x).getMachiningCodeU());
                                brMPdClass.setMachiningNameU(pdmachiingList.getResult().getBrMPdClassesList().get(x).getMachiningNameU());
                                brMPdClass.setIsChecked("1");
                            }else {
                                brMPdClass.setMachiningCodeU(pdMachiningCommList.get(k).getMachiningCode());
                                brMPdClass.setMachiningNameU(pdMachiningCommList.get(k).getMachiningName());
                            }
                            pdMsCommList.add(brMPdClass);
                        }

                    }
                    brMPdClasses.setMachiningCodeU(pdMachiningCommList.get(k).getMachiningCode());
                    brMPdClasses.setMachiningNameU(pdMachiningCommList.get(k).getMachiningName());
                    pdMsCommList.add(brMPdClasses);
                }
            }
            List<BR121414Param> brMPdClassesList = new ArrayList<>();
            brMPdClassesList = removalOfDuplication(pdMsCommList);
            paramList.setMachiningList(brMPdClassesList);
            br121414List.add(paramList);
        }
        model.addAttribute("br121414List", br121414List);

        return "br/BR121414";
    }

    /**
     * 修改名称和修改按钮窗口
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "updateName" ,method = RequestMethod.POST)
    public String updateName(BR121414Param param ,Model model){
        String machiningNameU = param.getProduceName().split("_")[1];
        String machiningCodeU = param.getProduceName().split("_")[0];
        String classesCode = param.getProduceName().split("_")[2];
        String classesName = param.getProduceName().split("_")[3];
        List<BR121414Param> brMPdClassesList = new ArrayList<>();


        for(int i=0;i<machiningCodeU.length();i++){
            BR121414Param br121414Param = new BR121414Param();
            String machiningCode =String.valueOf(machiningCodeU.charAt(i));
            List<PdMachining> pdMachiningCommList = new ArrayList<>();
            pdMachiningCommList = by121304Logic.findPdMachining(param);
            for(int j=0;j<pdMachiningCommList.size();j++){
                if(pdMachiningCommList.get(j).getMachiningCode().equals(machiningCode) && pdMachiningCommList.get(j).getClassesCode().equals(classesCode)){
                    br121414Param.setMachiningName(pdMachiningCommList.get(j).getMachiningName());
                }
            }

            br121414Param.setMachiningCode(machiningCode);
            br121414Param.setClassesName(classesName);
            br121414Param.setClassesCode(classesCode);
            brMPdClassesList.add(br121414Param);
        }

        model.addAttribute("classesCode",classesCode);
        model.addAttribute("machiningCodeU",machiningCodeU);
        model.addAttribute("machiningNameU",machiningNameU);
        model.addAttribute("classesName",classesName);
        model.addAttribute("brMPdClassesList",brMPdClassesList);
        return "br/BR12141401";

    }


    /**
     * 保存数据
     * @param param
     * @return
     */
    @RequestMapping(value = "save" ,method = RequestMethod.POST)
    public
    @ResponseBody int save(BR121414Param param){
        List<BR121414Param> paramList = new ArrayList<>();
        if(param.getBuyerPdMac() != null){
            for(int i=0;i<param.getBuyerPdMac().length;i++){
                BR121414Param br121414Param = new BR121414Param();
                br121414Param.setClassesCode(param.getBuyerPdMac()[i].split("_")[2]);
                br121414Param.setMachiningCode(param.getBuyerPdMac()[i].split("_")[0]);
                br121414Param.setMachiningName(param.getBuyerPdMac()[i].split("_")[1]);
                br121414Param.setClassesName(param.getBuyerPdMac()[i].split("_")[3]);
                paramList.add(br121414Param);
            }
            param.setProductList(paramList);
        }
        RsRequest<BR121414Param> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8180/api/br/mPdClasses/update";
        String url = SystemServerManager.BuyersReportServerManager.getUpdateMPdClasses();
        RsResponse<Integer> list = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        return list.getResult();
    }


    /**
     * 去除重复数据
     * @param list
     * @return
     */
    public List<BR121414Param> removalOfDuplication(List<BR121414Param> list){
        List<BR121414Param> brMPdClasses = new ArrayList<>();
        List<BR121414Param> listBefore = new ArrayList<>();
        List<BR121414Param> listAfter = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIsChecked() != null  && list.get(i).getIsChecked().equals("1")){
                listBefore.add(list.get(i));
            }
            if(list.get(i).getIsChecked() == null){
                listAfter.add(list.get(i));
            }
        }
        brMPdClasses.addAll(listBefore);
        brMPdClasses.addAll(listAfter);
        for(int i=0;i<brMPdClasses.size();i++){
            for(int j=i+1;j<brMPdClasses.size();j++){
                if(brMPdClasses.get(i).getMachiningCodeU().equals(brMPdClasses.get(j).getMachiningCodeU())){
                    brMPdClasses.remove(brMPdClasses.get(j));
                    j--;
                }else {
                    continue;
                }
            }
        }
        for(int i=0;i<brMPdClasses.size();i++){
                if(brMPdClasses.get(i).getIsChecked() != null && brMPdClasses.get(i).getIsChecked().equals("1")){
                    for(int j=0;j<brMPdClasses.size();j++){
                        if(j != i){
                            if(brMPdClasses.get(i).getMachiningCodeU().contains(brMPdClasses.get(j).getMachiningCodeU())){
                                brMPdClasses.remove(brMPdClasses.get(j));
                                if(i != NumberConst.IntDef.INT_ZERO){
                                    i--;
                                }

                                j--;
                            }
                        }
                    }
                }
        }
        return brMPdClasses;
    }
}
