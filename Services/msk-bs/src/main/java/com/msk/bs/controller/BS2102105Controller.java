package com.msk.bs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.*;
import com.msk.bs.logic.BS2102105Logic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 冻品公家组设置
 * Created by yang_chunyan on 2016/8/1.
 */
@Controller
@RequestMapping(value="BS2102105")
public class BS2102105Controller extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BS2102105Controller.class);

    @Autowired
    private BS2102105Logic bs2102105Logic;

    /**
     * 冻品管家组设置页面初始化
     * @param param 参数
     * @param model
     * @return 结果 数据
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(BS2102105Param param,String isHk, Model model) {
        logger.debug("跳转冻品管家组设置页面");
        if(StringUtils.hasLength(isHk)){
            BR121305Param param1 = new BR121305Param();
            param1.setHkGroupId(param.getHkGroupId());
            RsResponse<BR121305Result> retsult = CommRestUtil.queryHkGroupInfo(param1);
            List<BR121305Bean> houseList = new ArrayList<>();
            if(null != retsult.getResult()){
                houseList =  retsult.getResult().getIbr121305RsBeanList();
            }

            if(CollectionUtils.isNotEmpty(houseList)){
                BR121305Bean bean = houseList.get(0);
                model.addAttribute("lgcsCode",bean.getLgcsAreaCode());
                model.addAttribute("cityCode",bean.getCityCode());
                model.addAttribute("lgcsName",bean.getLgcsAreaName());
                model.addAttribute("cityName",bean.getCityName());
                model.addAttribute("buyerType",bean.getBuyerType());
                model.addAttribute("buyerTypeName",bean.getBuyerTypeName());
                model.addAttribute("classes",bean.getClassesCode());
                model.addAttribute("machining",bean.getMachiningCodeU());
                model.addAttribute("classesName",bean.getClassesName());
                model.addAttribute("machiningName",bean.getMachiningNameU());
                model.addAttribute("groupName", bean.getHkGroupName());
                model.addAttribute("groupId",bean.getHkGroupId());
            }
        }else{
            String lgcsCode = param.getLgcsAreaCode();
            String districtCode = param.getCityCode();
            String lgcsName = param.getLgcsAreaName();
            String districtName = param.getCityName();
            String buyerType = param.getBuyerType();
            String buyerTypeName = param.getBuyerTypeName();
            String classes = param.getClassesCode();
            String machining = param.getMachiningCode();
            String classesName = param.getClassesName();
            String machiningName = param.getMachiningName();
            String groupName = param.getHkGroupName();
            String groupId = param.getHkGroupId();
            model.addAttribute("lgcsCode",lgcsCode);
            model.addAttribute("cityCode",districtCode);
            model.addAttribute("lgcsName",lgcsName);
            model.addAttribute("cityName",districtName);
            model.addAttribute("buyerType",buyerType);
            model.addAttribute("buyerTypeName",buyerTypeName);
            model.addAttribute("classes",classes);
            model.addAttribute("machining",machining);
            model.addAttribute("classesName",classesName);
            model.addAttribute("machiningName",machiningName);
            model.addAttribute("groupName", groupName);
            model.addAttribute("groupId",groupId);
        }
        return "bs/BS2102105";
    }

    /**
     * 查询管家组下的所有管家
     * @param basePageParam 分页参数
     * @return 结果 数据
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public @ResponseBody
    PageResult<BS2102105Result> search(BS2102105Param basePageParam) {
        return bs2102105Logic.findPageResult(basePageParam);
    }

    /**
     * 修改管家备注信息
     * @param jsonStr
     * @return 结果 数据
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody
    void update(Model model, String jsonStr,String hkGroupId) {
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        Map<String, BS2102105Param> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, BS2102105Param>>() {
        });
        bs2102105Logic.modifyHouseInfo(map.values(),hkGroupId, baseParam);
    }

    /**
     * 删除管家组下的管家
     * @param param
     * @return 结果 数据
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public @ResponseBody
    void delete(BS2102105Param param) {
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(param);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */

        /**Modify: Bug #2623 9.12冻品管家组总控表下载时，现9.12日添加上海物流区冻品管家组成员，
         * 冻品管家组报表下载时，选择上海物流区，所属期9.12到9.14，点击下载后报表内容为空，
         * 应显示9.12日添加的冻品管家组成员 2016/09/13   BY  朱凯  Start */
        param.setLeaveTime(DateTimeUtil.getCustomerDate());
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        param.setActTime(DateTimeUtil.getCustomerDate());
        param.setCrtTime(DateTimeUtil.getCustomerDate());
        /**Modify: Bug #2623 9.12冻品管家组总控表下载时，现9.12日添加上海物流区冻品管家组成员，
         * 冻品管家组报表下载时，选择上海物流区，所属期9.12到9.14，点击下载后报表内容为空，
         * 应显示9.12日添加的冻品管家组成员 2016/09/13   BY  朱凯  end */
        int count = CommRestUtil.deleteHouse(param);
    }

}



