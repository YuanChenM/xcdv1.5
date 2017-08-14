package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101116Bean;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询买家对应的买手信息
 * Created by ni_shaotang on 2016/8/1.
 */
@Service
public class IBS2101116RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据买家id获取买手信息
     *
     * @param list
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBS2101116Bean> findBuyerList(List<String> list) {
        List<IBS2101116Bean> resultList = new ArrayList<IBS2101116Bean>();
        BaseParam param = new BaseParam();
        for (int i = 0; i < list.size(); i += 50) {//防止参数过长
            int length = list.size() < i + 50 ? list.size() : i + 50;
            param.getFilterMap().put("list", list.subList(i, length));
            List<IBS2101116Bean> buyerList = this.findList(param);
            resultList.addAll(buyerList);
        }
        return resultList;
    }
}
