package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdNormsStd;
import com.msk.core.entity.PdStandard;
import com.msk.product.logic.PD141112Logic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 产品包装编辑信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping("PD141112")
public class PD141112Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PD141112Controller.class);
    @Autowired
    private PD141112Logic pd141112Service;

    /**
     * 进入包装信息编辑页面*
     *
     * @param normsCode  参数
     * @param standardId 产品标准ID
     * @param mav        mav
     * @return pd/PD141112页面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model mav, @RequestParam(value = "standardId", required = false) String standardId, @RequestParam(value = "normsCode", required = false) String normsCode) {

        BaseParam param = new BaseParam();
        param.setFilter("standardId", standardId);
        PdStandard pdStandard = pd141112Service.findPdStandard(param);
        mav.addAttribute("pdStandard", pdStandard);
        mav.addAttribute("standardId", standardId);
        if (null != normsCode) {
            mav.addAttribute("normsCode", normsCode);
            if (!"".equals(standardId) && null != standardId) {
                logger.info("修改查询");
                BaseParam param1 = new BaseParam();
                param1.setFilter("standardId", standardId);
                param1.setFilter("normsCode", normsCode);
                PdNormsStd pd = pd141112Service.findOne(param1);
                mav.addAttribute("pdNorms", pd);
            }
        }
        return "pd/PD141112";
    }

    /**
     * 包装添加或修改保存操作
     *
     * @param pdNorms 保存参数
     * @param mav     mav
     * @return "pd/PD141111"页面
     * @author pxg
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public String save(PdNormsStd pdNorms, Model mav) {
        String normsCode = pdNorms.getNormsCode();
        BaseParam param1 = new BaseParam();
        if (normsCode == null || normsCode == "") {
            param1.setFilter("normsId", "-1");
        } else {
            param1.setFilter("normsCode", pdNorms.getNormsCode());
            param1.setFilter("standardId", pdNorms.getStandardId().toString());
        }

        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(pdNorms);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        PdNormsStd pd = pd141112Service.findOne(param1);
        if (pd != null) {
            logger.debug("修改操作");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("pdNorms", pdNorms);
            //查询修改后的数据和数据库中是否有相同数据
            int num = pd141112Service.findNum(param);
            //执行修改操作
            if (num == NumberConst.IntDef.INT_ZERO) {
                /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
                pdNorms.setUpdTime(new Date());
                /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
                int result = pd141112Service.modify(pdNorms);
                if (result > NumberConst.IntDef.INT_ZERO) {
                    logger.debug("修改成功");
                }
            } else {
                throw new BusinessException("该条数据已经存在,请重新修改");
            }
        } else {
            logger.debug("添加操作");
            Map<String, Object> param = new HashMap<String, Object>();
            pdNorms.setUpdTime(new Date());
            param.put("pdNorms", pdNorms);
            int num = pd141112Service.findNum(param);
            //数据库中不存在相同数据,执行添加操作
            if (num >= NumberConst.IntDef.INT_ONE) {
                throw new BusinessException("该条数据已经存在,请更改后添加！");
            }
            BaseParam pa = new BaseParam();
            PdNormsStd pam = pd141112Service.findCodeMax(pa);
            if (pam == null || "".equals(pam.getNormsCode())) {
                pam = new PdNormsStd();
                pam.setNormsCode("00");
            }
            Integer maxNo = Integer.parseInt(pam.getNormsCode());
            if (maxNo >= NumberConst.IntDef.INT_NINE_NINE_NINE_FOR_SQL_IN_LIMIT) {
                throw new BusinessException("类别已达到最大限度，请联系管理员！");
            }
            pdNorms.setNormsCode(String.format("%03d", maxNo + NumberConst.IntDef.INT_ONE));
            pdNorms.setCrtTime(new Date());
            pdNorms.setActTime(new Date());
            pdNorms.setUpdTime(new Date());
            int save = pd141112Service.save(pdNorms);
            if (save > NumberConst.IntDef.INT_ZERO) {
                logger.debug("添加成功");
                PdStandard pan = new PdStandard();
                pan.setNorFlg(NumberConst.IntDef.INT_ONE + "");
                pan.setStandardId(pdNorms.getStandardId());
                /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start */
                pan.setUpdTime(new Date());
                /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
                super.setCommonParam(pan);
                /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
                /**Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End */
                pd141112Service.modify("updateStandardId", pan);
            }
        }
        return "pd/PD141111";
    }

    /**
     * 删除
     * 根据页面传入参数执行删除包装操作
     *
     * @param standardId 传入参数
     * @param standardId normsCode
     * @return model 返回页面模型
     * @author xhy
     */
    @RequestMapping(value = "close", method = RequestMethod.POST)
    public String deleteNormsOne(@RequestParam(value = "standardId", required = false) String standardId,
                                 @RequestParam(value = "normsCode", required = false) String normsCode, Model model) {
        if (StringUtils.isNotBlank(standardId) && StringUtils.isNotBlank(normsCode)) {
            BaseParam param = new BaseParam();
            param.setFilter("normsCode", normsCode);
            param.setFilter("standardId", standardId);
            int boo = this.pd141112Service.remove(param);//成功为1 失败为0
            if (boo != NumberConst.IntDef.INT_ONE) {
                throw new BusinessException("删除异常!商品编码:" + "standardId" + "包装编码:" + normsCode);
            }
        }
        model.addAttribute("standardId", standardId);
        return "pd/PD141111";
    }
}
