package com.msk.bs.controller;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.BS2102102Bean;
import com.msk.bs.bean.BS2102103Bean;
import com.msk.bs.bean.BS2102112Bean;
import com.msk.bs.logic.BS2101107Logic;
import com.msk.bs.logic.BS2102112Logic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.SlHouseAccount;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by wang_haichun on 2016/8/19.
 */
@Controller
@RequestMapping(value = "BS2102112")
public class BS2102112Controller extends BaseController {


    @Autowired
    private BS2101107Logic bs2101107Logic;
    @Autowired
    private BS2102112Logic bs2102112Logic;

    @RequestMapping(value = "init" ,method = RequestMethod.POST)
    public String init(Model model, BS2102102Bean houseAccount) throws UnsupportedEncodingException {
        model.addAttribute("houseAccount",houseAccount);
        return "bs/BS2102112";
    }

    /**
     * 获取跳转冻品管家设置需要的数据
     * @param slHouseAccount
     * @return
     */
    @RequestMapping(value = "houseSetting",method = RequestMethod.POST)
    public @ResponseBody SlHouseAccount houseSetting(SlHouseAccount slHouseAccount){
        SlHouseAccount houseAccount = null;
        if(!StringUtil.isNullOrEmpty(slHouseAccount.getSlCode()) && !StringUtil.isNullOrEmpty(slHouseAccount.getHouseCode())){
            houseAccount = bs2101107Logic.findHouseAccount(slHouseAccount);
            if(houseAccount != null && !StringUtil.isNullOrEmpty(houseAccount.getVlgcsAreaCode())){
                //获取物流区
                DistrictParam districtParam = new DistrictParam();
                districtParam.setLgcsAreaCode(houseAccount.getVlgcsAreaCode());
                List<LgcsAreaBean> lgcsAreaBeanList = CommRestUtil.getLogisticsAreaList(districtParam);
                if(!CollectionUtils.isEmpty(lgcsAreaBeanList)){
                    houseAccount.setFlag20(lgcsAreaBeanList.get(0).getLgcsAreaName());
                }
            }

        }
        return houseAccount;
    }


    /**
     *
     * @param slHouseAccount
     * @return
     */
    @RequestMapping(value = "slManage",method = RequestMethod.POST)
    public @ResponseBody BS2102112Bean slManage(SlHouseAccount slHouseAccount){
        BS2102112Bean bs2102112Bean = null;
        if(!StringUtil.isNullOrEmpty(slHouseAccount.getSlCode()) && !StringUtil.isNullOrEmpty(slHouseAccount.getHouseCode())){
            SlHouseAccount houseAccount = bs2101107Logic.findHouseAccount(slHouseAccount);
            if(houseAccount != null){
                bs2102112Bean = bs2102112Logic.findHouseAccountBuyer(houseAccount);
                if(bs2102112Bean != null){
                    bs2102112Bean.setHouseAccount(houseAccount.getHouseAccount());
                    bs2102112Bean.setHouseCode(houseAccount.getHouseCode());
                    bs2102112Bean.setHouseContact(houseAccount.getHouseContact());
                    bs2102112Bean.setHouseCodeDis(houseAccount.getHouseCodeDis());
                    bs2102112Bean.setHouseShowName(houseAccount.getHouseShowName());
                }
            }
        }
        return bs2102112Bean;
    }

}
