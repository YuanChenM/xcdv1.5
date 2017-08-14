package com.msk.br.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.BR121402Param;
import com.msk.br.bean.BR121402RsPageResult;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.TreeMap;

/**
 * 配置管理画面Controller
 *
 * @author zhao_chen1
 */
@Controller
@RequestMapping("BR121402")
public class BR121402Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BR121402Controller.class);


    /**
     * 初始化页面
     *
     * @param model Model,BR121402Param param
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("报表页面初始化");
        //读取Code Master DB
        TreeMap<String, String> treeMap = new TreeMap<>();
        // 配置类型
        Map<String, String> settingTypeMap = CodeMasterManager.findCodeMasterMap("SettingType");
        treeMap.putAll(settingTypeMap);
        model.addAttribute("settingTypeMap", treeMap);
        return "br/BR121402";
    }

    /**
     * 查询买家产品池配置列表
     *
     * @param param
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BrSetting> search(BR121402Param param) {

        logger.info("配置管理画面查询开始");
        logger.info("开始调取配置管理画面接口");
        PageResult<BrSetting> result = new PageResult();
        RsRequest<BR121402Param> request = new RsRequest<BR121402Param>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//        String url = "http://localhost:8084/msk-buyers-report-api/api/br/findBrSetting";
        String url = SystemServerManager.BuyersReportServerManager.getFindBrSetting();
        RsResponse<BR121402RsPageResult> been = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121402RsPageResult>>() {
        });
        result.setData(been.getResult().getBrSettings());
        result.setRecordsTotal(been.getResult().getTotalCount());
        return result;
    }

    /**
     * 配置管理画面编辑保存
     *
     * @param br121402Param
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(BR121402Param br121402Param) {
        logger.info("配置管理画面编辑保存开始");

        RsRequest<BR121402Param> request = new RsRequest<BR121402Param>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(br121402Param);
//        String url = "http://localhost:8084/msk-buyers-report-api/api/br/modifySetting";
        String url = SystemServerManager.BuyersReportServerManager.getModifySetting();
        RsResponse<BR121402RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121402RsPageResult>>() {
        });
        response.getResult().getRegResult();

        if (response.getResult().getRegResult() == NumberConst.IntDef.INT_ZERO) {
            if (response.getResult().getUpdateLine() > NumberConst.IntDef.INT_ZERO) {
                return SystemConst.RsStatus.SUCCESS;
            } else {
                return SystemConst.RsStatus.FAIL;
            }
        } else {
            return "numberError";
        }
    }


    /**
     * 配置管理画面删除
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public String deleteSettingDate(@PathVariable("id") Long id, Model model) {

        logger.info("根据id删除配置数据");
        RsRequest<Long> request = new RsRequest<Long>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(id);
//        String url = "http://localhost:8084/msk-buyers-report-api/api/br/deleteSettingDate";
        String url = SystemServerManager.BuyersReportServerManager.getDeleteSettingDate();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        return this.init(model);
    }


    /**
     * 配置管理画面数据新增
     *
     * @param br121402Param
     * @return
     */

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public
    @ResponseBody
    String insertDemandPublishDetail(BR121402Param br121402Param) {
        logger.info("新建页面新增中标确认书基本信息");
        super.setCommonParam(br121402Param);
        RsRequest<BR121402Param> request = new RsRequest<BR121402Param>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(br121402Param);
//        String url = "http://localhost:8084/msk-buyers-report-api/api/br/insertDemandPublishDetail";
        String url = SystemServerManager.BuyersReportServerManager.getInsertDemandPublishDetail();
        RsResponse<BR121402RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121402RsPageResult>>() {
        });

        response.getResult().getIsExitResult();
        if (response.getResult().getIsExitResult() == NumberConst.IntDef.INT_ZERO) {
            if (response.getResult().getInsertLine() == NumberConst.IntDef.INT_ONE) {
                return SystemConst.RsStatus.SUCCESS;
            } else {
                return SystemConst.RsStatus.FAIL;
            }
        } else {
            if (response.getResult().getRegResult() == NumberConst.IntDef.INT_ZERO) {
                if (response.getResult().getUpdateLine() > NumberConst.IntDef.INT_ZERO) {
                    return SystemConst.RsStatus.SUCCESS;
                } else {
                    return SystemConst.RsStatus.FAIL;
                }
            } else {
                return "numberError";
            }

        }

    }


}
