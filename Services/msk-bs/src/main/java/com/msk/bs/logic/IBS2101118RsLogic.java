package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101118Bean;
import com.msk.bs.bean.IBS2101118Param;
import com.msk.bs.bean.IBS2101118Result;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.BsConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.SlHouseTelmarkingRec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by zhu_kai1 on 2016/8/18.
 */
public class IBS2101118RsLogic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(IBS2101118RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询冻品管家销售/营销信息
     * @param param
     * @return
     */
    public IBS2101118Result getSaleBuyerInfo(IBS2101118Param param){
        IBS2101118Result result = new IBS2101118Result();
        result.setTotalCount(this.getCount(param));
        result.setPageNo(param.getPageNo());
        result.setTotalPage(result.getTotalCount(), param.getPageCount());
        List<IBS2101118Bean> ibs2101118BeanList =  this.findPageList(param,IBS2101118Bean.class);
            for (IBS2101118Bean ibs2101118Bean:ibs2101118BeanList){
                String applySide = CodeMasterManager.getCodeMasterName(BsConstant.ApplySide.Type, ibs2101118Bean.getDevelopmentWay());
                ibs2101118Bean.setDevelopmentWay(applySide);
                // 锁定期买家查询上线概率评分用
                if("1".equals(ibs2101118Bean.getApplyStatus())){
                    ibs2101118Bean.setBuyerOnlineScore("");
                    BaseParam baseParam = new BaseParam();
                    baseParam.getFilterMap().put("slCode",ibs2101118Bean.getSlCode());
                    baseParam.getFilterMap().put("houseCode",ibs2101118Bean.getHouseCode());
                    baseParam.getFilterMap().put("startTime",ibs2101118Bean.getStartTime());
                    baseParam.getFilterMap().put("endTime",ibs2101118Bean.getEndTime());
                    SlHouseTelmarkingRec telmarkingRec = this.findOne(baseParam);
                    if(null !=telmarkingRec){
                        ibs2101118Bean.setBuyerOnlineScore(telmarkingRec.getOnlineRec());
                    }
                }
        }
        result.setSlHouseSaleList(ibs2101118BeanList);
        return result;
    }

}
