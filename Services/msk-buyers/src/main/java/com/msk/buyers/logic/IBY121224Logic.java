package com.msk.buyers.logic;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.IBY121207RsParam;
import com.msk.buyers.bean.IBY121223Result;
import com.msk.buyers.bean.IBY121224Param;
import com.msk.buyers.bean.IBY121224Result;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.ByBuyerEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
@Service
public class IBY121224Logic extends BaseLogic {

    private  static Logger logger = LoggerFactory.getLogger(IBY121224Logic.class);
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId{
        // 查询雇员信息
        static String FIND_EMPLOYEE = "findEmployee";
    }
    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    public IBY121224Result searchBuyerShop(IBY121224Param iby121224Param) throws Exception{
        Map<String,String> marketingSatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);

        iby121224Param.getFilterMap().put("buyerCode",iby121224Param.getBuyerCode());
        iby121224Param.getFilterMap().put("buyerName",iby121224Param.getBuyerName());
        iby121224Param.getFilterMap().put("buyerAddr",iby121224Param.getBuyerAddr());
        iby121224Param.getFilterMap().put("busiTel",iby121224Param.getBusiTel());
        DbUtils.buildLikeCondition(iby121224Param, "buyerCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(iby121224Param, "buyerName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(iby121224Param, "buyerAddr", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(iby121224Param, "busiTel", DbUtils.LikeMode.PARTIAL);

        List<IBY121223Result> buyerInfoList = this.findList(iby121224Param);
        for(IBY121223Result iby121223Result : buyerInfoList){
            if (marketingSatusMap.containsKey(iby121223Result.getMarketingsStatusCode())){
                iby121223Result.setMarketingsStatusName(marketingSatusMap.get(iby121223Result.getMarketingsStatusCode()));
            }else{
                // 防止匹配不上时返回null，页面报告警
                iby121223Result.setMarketingsStatusName("");
            }
        }
        IBY121224Result iby121224Result = new IBY121224Result();
        // 获取雇员信息
        iby121224Result.setBuyerShopList(getEmployee(buyerInfoList, iby121224Param));
        return iby121224Result;
    }

    /**
     * 查询雇员信息
     * @param iby121223ResultList
     * @return
     */
    @Transactional(readOnly = true)
    private List<IBY121223Result> getEmployee(List<IBY121223Result> iby121223ResultList,IBY121224Param param) {
        Map<String,String> EmployeeMap = CodeMasterManager.findCodeMasterMap("EmployeeType");
        param.setEmployeeName(DbUtils.buildLikeCondition(param.getEmployeeName(), DbUtils.LikeMode.PARTIAL));
        List<IBY121207RsParam> employeeList = this.findList(SqlId.FIND_EMPLOYEE, param);
        for(IBY121207RsParam employee : employeeList){
            employee.setEmployeeTypeName(EmployeeMap.get(employee.getEmployeeType()));
        }
        // 将雇员信息放入构造的数据中
        List<IBY121223Result> resultList =  new ArrayList<>();
        for(IBY121223Result res : iby121223ResultList){
            for(ByBuyerEmployee employee : employeeList){
                if(res.getBuyerId().equals(employee.getBuyerId())){
                    res.setEmployeeName(employee.getEmployeeName());
                    if(!StringUtil.isNullOrEmpty(param.getEmployeeName())){
                        resultList.add(res);
                        break;
                    }
                }
            }
        }
       if(!StringUtil.isNullOrEmpty(param.getEmployeeName())){
            // 若根据条件去查询雇员信息,返回的结果以雇员信息为准
           iby121223ResultList = resultList;
           if(CollectionUtils.isEmpty(employeeList)){
               iby121223ResultList = new ArrayList<>();
           }
        }
        return iby121223ResultList;
    }

}
