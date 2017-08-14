package com.msk.seller.logic;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.SL241101Bean;
import com.msk.seller.bean.SL241130Param;
import com.msk.seller.utils.ISLRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gyh on 2016/3/22.
 */
public class SL241130Logic extends BaseLogic {


    @Autowired
    private Sl241101Logic sl241101Logic;

    /**
     * 卖家申请新品
     * @param paramList 参数
     * @return 结果
     */
    @Transactional(readOnly = false)
    public String saveNewPd(List<SL241130Param> paramList) {
        List<String> slCodeList = new ArrayList<String>();
        for(SL241130Param sl241128Param:paramList){
            //1.申请品种2.申请特征3.申请净重4.申请报装，查询是否存在
            if(!StringUtil.isNullOrEmpty(sl241128Param.getNewFlag())){
                sl241128Param.setChooseInfo(sl241128Param.getNewFlag());
            }
            if(StringUtil.isNullOrEmpty(sl241128Param.getProviderCode())){
                slCodeList.add(sl241128Param.getCrtId());
            }
        }

        if(!CollectionUtils.isEmpty(slCodeList)) {
            // 查询所有code
            List<SL241101Bean> beans = sl241101Logic.findDataList(slCodeList);
            Map<String, Object> codeMap = new HashMap<String, Object>();
            if (!CollectionUtils.isEmpty(beans)) {
                for (SL241101Bean bean : beans) {
                    codeMap.put(bean.getSlCode(), bean);
                }
            }

            for (SL241130Param sl241128Param : paramList) {
                if (StringUtil.isNullOrEmpty(sl241128Param.getProviderCode())) {
                    String crtId = sl241128Param.getCrtId();
                    sl241128Param.setProviderCode(crtId);
                    SL241101Bean bean = (SL241101Bean) codeMap.get(crtId);
                    if (null != bean) {
                        sl241128Param.setProviderName(bean.getSlShowName());
                    } else {
                        throw new BusinessException("创建者ID/卖家编码为：" + sl241128Param.getCrtId() + "不存在，请检查后提交！");
                    }
                }
            }
        }

        // 调用接口
        Integer res = ISLRestUtil.savePdTcProviderPackage(paramList);
        if (res > 0){
            return "卖家申请新品成功！";
        }else {
            return "卖家申请新品失败！";
        }
    }


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
