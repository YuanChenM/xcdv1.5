package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.seller.bean.SL241104Bean;
import com.msk.seller.bean.SL241117Bean;
import com.msk.seller.logic.SL241104Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SL241104Controller
 *
 * @author yuan_chen
 * @version 1.0
 **/
@Controller
@RequestMapping("SL241104")
public class SL241104Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SL241104Controller.class);

    @Autowired
    private SL241104Logic sL241104Logic;

    /**
     * 获得卖家产品加工质量标准
     *
     * @param param param
     * @return 结果
     * @author gyh
     */
    @RequestMapping(value = "findSlPdTncStd", method = RequestMethod.POST)
    public
    @ResponseBody
    List<SL241117Bean> findSlPdQltStd(BaseParam param) {
        return sL241104Logic.findSlPdTncStd(param);
    }

    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SL241104Bean> search(BasePageParam param, Model model) {
        logger.debug("SL241104 search");
        PageResult<SL241104Bean> pageResult = new PageResult<SL241104Bean>();
        List<SL241104Bean> datas = new ArrayList<SL241104Bean>();
        for (int i = 0; i < 10; i++) {
            SL241104Bean bean = new SL241104Bean();
            bean.setSlCode("sl0000001");
            bean.setSlPdId("slpd00001");

            if (i == 0) {
                bean.setManufacturer("双汇集团");
                bean.setBrand("双汇");
                bean.setClassesName("鸡类");
                bean.setBreedName("鸡大腿");
                bean.setFeature("90-120g");
                bean.setPdCode("010101");
                bean.setIsDistribution("否");
                bean.setGradeName("未定级");
                bean.setGradeCode("");
                bean.setExamineResult("未审核");
            } else if (i == 1) {
                bean.setManufacturer("双汇集团");
                bean.setBrand("双汇");
                bean.setClassesName("鸡类");
                bean.setBreedName("鸡大腿");
                bean.setFeature("120-150g");
                bean.setPdCode("010102");
                bean.setIsDistribution("否");
                bean.setGradeName("A2");
                bean.setGradeCode("2");
                bean.setExamineResult("未审核");
            } else if (i == 2) {
                bean.setManufacturer("双汇集团");
                bean.setBrand("双汇");
                bean.setClassesName("鸡类");
                bean.setBreedName("鸡大腿");
                bean.setFeature("150-180g");
                bean.setPdCode("010103");
                bean.setIsDistribution("否");
                bean.setGradeName("A3");
                bean.setGradeCode("3");
                bean.setExamineResult("正确");
            } else if (i == 3) {
                bean.setManufacturer("双汇集团");
                bean.setBrand("品牌A");
                bean.setClassesName("牛羊肉类");
                bean.setBreedName("牛腱肉");
                bean.setFeature("100-200g");
                bean.setPdCode("030101");
                bean.setIsDistribution("是");
                bean.setGradeName("不通过");
                bean.setGradeCode("1");
                bean.setExamineResult("不正确");
            } else {
                bean.setManufacturer("生产商B");
                bean.setBrand("品牌B");
                bean.setClassesName("鸭类");
                bean.setBreedName("鸭脖子");
                bean.setFeature("120-150g");
                bean.setPdCode("02100" + i);
                bean.setIsDistribution("是");
                bean.setGradeName("A1");
                bean.setGradeCode("1");
                bean.setExamineResult("正确");
            }


            bean.setActTime(new Date());
            bean.setCrtTime(new Date());
            bean.setUpdTime(new Date());
            datas.add(bean);
        }

        pageResult.setData(datas);
        pageResult.setRecordsTotal(10);
        return pageResult;
    }
}
