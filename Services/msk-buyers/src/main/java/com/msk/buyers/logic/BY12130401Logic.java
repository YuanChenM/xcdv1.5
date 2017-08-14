package com.msk.buyers.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.IBY121207RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.ByBuyerEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * BY121304Logic.
 *
 * @author yuan_chen
 */
@Service
public class BY12130401Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY12130401Logic.class);

    @Autowired
    private IBY121207Logic iby121207Logic;
    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取雇员详细信息
     * @param param
     * @return
     */
    public List<IBY121207RsParam> buyerEmployeeFind(ByBuyerEmployee param){
        return iby121207Logic.buyerEmployeeFind(param);
    }

    /**
     * 买家雇员信息更新
     * @param param
     * @return
     */
    public int buyerEmployeeModify(IBY121207RsParam param){
        if("on".equals(param.getBusCardFlg())){
            param.setBusCardFlg("1");
        }else {
            param.setBusCardFlg("0");
        }
        if("on".equals(param.getContactPerson())){
            param.setContactPerson("1");
        }else{
            param.setContactPerson("0");
        }
        if("on".equals(param.getPurchase())){
            param.setPurchase("1");
        }else{
            param.setPurchase("0");
        }
        if("on".equals(param.getReceivePerson())){
            param.setReceivePerson("1");
        }else{
            param.setReceivePerson("0");
        }
        List<IBY121207RsParam> employeeList = new ArrayList<>();
        employeeList.add(param);
        int count = iby121207Logic.buyerEmployeeModify(employeeList);
        return count;
    }
}
