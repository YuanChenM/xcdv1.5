package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.plug.spring.web.base.BaseUploadController;
import com.msk.bs.bean.IBS2101114Bean;
import com.msk.bs.bean.IBS2101114Param;
import com.msk.buyers.bean.BY121312Bean;
import com.msk.buyers.utils.RestCommUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 *买家冻品管家信息
 */
@Controller
@RequestMapping("BY121312")
public class BY121312Controller extends BaseUploadController
{

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121312Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家冻品管家信息");
        List<String> buyerIdList = new ArrayList<String>();
        IBS2101114Param param = new IBS2101114Param();

        buyerIdList.add(buyerId);
        param.setBuyerIdList(buyerIdList);
        List<IBS2101114Bean> bean =  RestCommUtil.searchHouseInfo(param).getResult().getSlBsBuyerList();

        List<BY121312Bean> by121312Beans = new ArrayList<BY121312Bean>();
        dealWithTime(by121312Beans,bean);

        model.addAttribute("buyerId",buyerId);
        model.addAttribute("by121312Beans",by121312Beans);
        return "buyers/BY121312";
    }

    /**
     * 处理日期
     * @param BeanList
     * @return
     */
    public void dealWithTime(List<BY121312Bean> by121312Beans,List<IBS2101114Bean> BeanList){

        //重写comparator方法，修改比较规则，根据startTime和endTime排序
        Comparator<IBS2101114Bean> comparator = new Comparator<IBS2101114Bean>() {
            public int compare(IBS2101114Bean s1, IBS2101114Bean s2) {
                // 排时间
                if (s1.getStartTime().getTime() > s2.getStartTime().getTime()) {
                    return 1;
                } else if(s1.getEndTime().getTime() > s2.getEndTime().getTime()){
                    return 1;
                }else{
                    return 0;
                }
            }
        };

        Collections.sort(BeanList,comparator);

        //去重，相同的开始时间去重
        for(int n=0;n<BeanList.size()-1;n++){
            for(int m=BeanList.size()-1;m>n;m--){
                if(format.format(BeanList.get(m).getStartTime()).equals(format.format(BeanList.get(n).getStartTime()))){
                    BeanList.remove(m);
                }
            }
        }

        //几个变量作为中间变量
        Long lockBeginTime = null;
        Long lockEndTime = null;
        Long endTime = null;
        BY121312Bean hisBean = new BY121312Bean();

        //遍历list,判断几种情况  锁定/锁定到转专属/专属
        if(BeanList !=null && BeanList.size()>=1){
            for(int i=0; i<BeanList.size();i++){
                BY121312Bean bean = new BY121312Bean();
                //1锁定，2专属
                if(BeanList.get(i).getApplyStatus().equals("1")){
                    bean.setHouseName(BeanList.get(i).getHouseShowName().equals("") ? BeanList.get(i).getHouseCode() : BeanList.get(i).getHouseShowName());
                    bean.setLockBeginTime(format.format(BeanList.get(i).getStartTime()));
                    bean.setLockEndTime(format.format(BeanList.get(i).getEndTime()));
                    hisBean.setHouseName(BeanList.get(i).getHouseShowName().equals("") ? BeanList.get(i).getHouseCode() : BeanList.get(i).getHouseShowName());
                    hisBean.setLockBeginTime(format.format(BeanList.get(i).getStartTime()));
                    hisBean.setLockEndTime(format.format(BeanList.get(i).getEndTime()));
                    lockBeginTime = BeanList.get(i).getStartTime().getTime();
                    lockEndTime = BeanList.get(i).getEndTime().getTime();
                }

                if(BeanList.get(i).getApplyStatus().equals("2")){
                    if(lockBeginTime != null && lockEndTime != null){
                        if(BeanList.get(i).getStartTime().getTime() >  lockBeginTime &&
                                BeanList.get(i).getStartTime().getTime() < lockEndTime){

                            by121312Beans.remove(by121312Beans.size()-1);

                            bean.setHouseName(hisBean.getHouseName());
                            bean.setLockBeginTime(hisBean.getLockBeginTime());
                            bean.setLockEndTime(hisBean.getLockEndTime());
                            bean.setExcBeginTime(format.format(BeanList.get(i).getStartTime()));
                            bean.setExcEndTime(format.format(BeanList.get(i).getEndTime()));

                            endTime = BeanList.get(i).getEndTime().getTime();

                        }
                    }

                    if(endTime != null){
                        if(BeanList.get(i).getStartTime().getTime() > endTime){
                            bean.setHouseName(BeanList.get(i).getHouseShowName().equals("") ? BeanList.get(i).getHouseCode() : BeanList.get(i).getHouseShowName());
                            bean.setExcBeginTime(format.format(BeanList.get(i).getStartTime()));
                            bean.setExcEndTime(format.format(BeanList.get(i).getEndTime()));
                        }else{
                            bean.setHouseName(BeanList.get(i).getHouseShowName().equals("") ? BeanList.get(i).getHouseCode() : BeanList.get(i).getHouseShowName());
                            bean.setExcBeginTime(format.format(BeanList.get(i).getStartTime()));
                            bean.setExcEndTime(format.format(BeanList.get(i).getEndTime()));
                        }
                    }else{
                        bean.setHouseName(BeanList.get(i).getHouseShowName().equals("") ? BeanList.get(i).getHouseCode() : BeanList.get(i).getHouseShowName());
                        bean.setExcBeginTime(format.format(BeanList.get(i).getStartTime()));
                        bean.setExcEndTime(format.format(BeanList.get(i).getEndTime()));
                    }
                }

                by121312Beans.add(bean);
            }
        }
    }

}
