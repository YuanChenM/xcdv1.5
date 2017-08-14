package com.msk.buyers.logic;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121307RsPageResult;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.br.bean.IBR121411RsParam;
import com.msk.br.bean.IBR121411RsResult;
import com.msk.buyers.bean.BY121324Bean;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrBuyerPool;
import com.msk.core.entity.BrMPdClasses;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 */
@Component("buyerRegisterReportLogic")
public class BY121324Logic extends ExcelAsyncPrintService {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121324Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        static String FIND_BUYER_BASIC = "findBuyerBasicByBuyerId";
        static String FIND_EMPLOYEE_BY_BUYERID = "findEmployeeListByBuyerId";
        static String FIND_MALL_ACCOUNT_BY_BUYERID = "findMallAccountListByBuyerId";
        static String FIND_PDCLA_BY_BUYERID = "findPdClaByBuyerId";
    }

    @Override
    public Map<String,?> getDataSource(Object objectParam){
        Map<String, Object> excelMap = new HashMap<>();
        Map<String,String> temp =(Map) objectParam;
        String buyerId = temp.get("buyerId");
        BY121324Bean buyerRegisterInfo = this.findBuyerBasic(StringUtil.toSafeString(buyerId));
        if (null == buyerRegisterInfo) {
            throw new BusinessException("买家ID不存在");
        } else {
            buyerBasicInfoSet(buyerRegisterInfo);
            String title = "分销买家注册信息总控表";
            List<BY121324Bean> employeeList = this.findEmployeeListByBuyerId(StringUtil.toSafeString(buyerId));
            List<BY121324Bean> employeeExportList = new ArrayList<>();
            if(!CollectionUtils.isEmpty(employeeList)){
                employeeExportList.addAll(employeeList);
                contactEmployeeList(employeeList, employeeExportList);
                purchaseEmployeeList(employeeList, employeeExportList);
            }
            buyerRegisterInfo.setEmployeeList(employeeExportList);

            List<BY121324Bean> mallAccountList = this.findMallAccountListByBuyerId(StringUtil.toSafeString(buyerId));
            buyerRegisterInfo.setMallAccountList(mallAccountList);

            excelMap.put("title", title);
            excelMap.put("buyerRegisterInfo", buyerRegisterInfo);
        }
        return excelMap;
    }

    /**
     * 设置excel需要显示的数据
     * @param buyerRegisterInfo
     */
    public void buyerBasicInfoSet(BY121324Bean buyerRegisterInfo) {

        //买家池归属
        IBR121411RsParam searchParam = new IBR121411RsParam();
        searchParam.setBuyerId(buyerRegisterInfo.getBuyerId());

        RsRequest<IBR121411RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("001");
        request.setSiteCode("001");
        request.setParam(searchParam);
        String url = SystemServerManager.BuyersReportServerManager.getQueryBrBuyerPoolByBuyerId();
        RsResponse<IBR121411RsResult> pageResultList = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121411RsResult>>() {
        });
        if(null != pageResultList.getResult()){
            if(!CollectionUtils.isEmpty(pageResultList.getResult().getBrBuyerPoolList())){
                List<BrBuyerPool> byBuyerPoolList = pageResultList.getResult().getBrBuyerPoolList();
                StringBuffer sb = new StringBuffer();
                for (int i=0;i< byBuyerPoolList.size();i++){
                    sb.append(byBuyerPoolList.get(i).getClassesName() + byBuyerPoolList.get(i).getMachiningNameU());
                    if(i != byBuyerPoolList.size() - 1){
                        sb.append(",");
                    }
                }
                buyerRegisterInfo.setBuyerPool(sb.toString());
            }else{
                buyerRegisterInfo.setBuyerPool("");
            }
        }else{
            buyerRegisterInfo.setBuyerPool("");
        }
        //电话营销时间
        String telMarketingTime = "";
        if (null != buyerRegisterInfo.getTelMarketingStartTime() && null != buyerRegisterInfo.getTelMarketingEndTime()) {
            telMarketingTime = buyerRegisterInfo.getTelMarketingStartTime() + "-" + buyerRegisterInfo.getTelMarketingEndTime();
        }
        buyerRegisterInfo.setTelMarketingTime(telMarketingTime);

        //买家店铺地址和店铺号
        String buyerStoreAddrAndNo = "";
        if (null != buyerRegisterInfo.getBuyerAddr() && null != buyerRegisterInfo.getStoreNo()) {
            buyerStoreAddrAndNo = buyerRegisterInfo.getBuyerAddr() + buyerRegisterInfo.getStoreNo();
        }
        buyerRegisterInfo.setBuyerStoreAddrAndNo(buyerStoreAddrAndNo);

        //买家营销工具
        if (null != buyerRegisterInfo.getMarketingTools()) {
            Map<String, String> marketingToolsMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingTool.TYPE);
            String[] marketingTools = buyerRegisterInfo.getMarketingTools().split(",");
            for (String key : marketingToolsMap.keySet()) {
                for (int i = 0; i < marketingTools.length; i++) {
                    if (key.equals(marketingTools[i])) {
                        marketingTools[i] = marketingToolsMap.get(key);
                        break;
                    }
                }
            }
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < marketingTools.length; i++){
                sb.append(marketingTools[i]);
                if(i != marketingTools.length - 1){
                    sb.append(",");
                }
            }
            String marketingToolsName = sb.toString();
            buyerRegisterInfo.setMarketingToosName(marketingToolsName);
        } else {
            buyerRegisterInfo.setMarketingToosName("");
        }

        //买家上线状态名称
        String marketingsStatusName = "";
        if (!StringUtil.isNullOrEmpty(buyerRegisterInfo.getMarketingsStatus())) {
            Map<String, String> marketingsStatusMap = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
            marketingsStatusName = marketingsStatusMap.get(buyerRegisterInfo.getMarketingsStatus());
        }
        buyerRegisterInfo.setMarketingsStatusName(marketingsStatusName);

        //买家错误信息
        String marketExceptionRemark = "";
        if (!StringUtil.isNullOrEmpty(buyerRegisterInfo.getMarketExceptionRemark())) {
            marketExceptionRemark = buyerRegisterInfo.getMarketExceptionRemark();
        }
        buyerRegisterInfo.setMarketExceptionRemark(marketExceptionRemark);
    }

    /**
     * 设置有权联系人数据
     * @param employeeList
     * @param employeeExportList
     */
    public void contactEmployeeList(List<BY121324Bean> employeeList, List<BY121324Bean> employeeExportList) {
        for (BY121324Bean employee : employeeList) {
            if ("1".equals(employee.getContactPerson())) {
                BY121324Bean employeeTemp = new BY121324Bean();
                employeeTemp.setEmployeeTypeName("有权联系人");
                employeeTemp.setEmployeeName(employee.getEmployeeName());
                employeeTemp.setEmployeeQq(employee.getEmployeeQq());
                employeeTemp.setEmployeeTel(employee.getEmployeeTel());
                employeeTemp.setEmployeeWechat(employee.getEmployeeWechat());
                employeeExportList.add(employeeTemp);
            }
        }
    }

    /**
     * 设置采购员数据
     * @param employeeList
     * @param employeeExportList
     */
    public void purchaseEmployeeList(List<BY121324Bean> employeeList, List<BY121324Bean> employeeExportList) {
        for (BY121324Bean employee : employeeList) {
            if ("1".equals(employee.getPurchase())) {
                BY121324Bean employeeTemp = new BY121324Bean();
                employeeTemp.setEmployeeTypeName("采购员");
                employeeTemp.setEmployeeName(employee.getEmployeeName());
                employeeTemp.setEmployeeQq(employee.getEmployeeQq());
                employeeTemp.setEmployeeTel(employee.getEmployeeTel());
                employeeTemp.setEmployeeWechat(employee.getEmployeeWechat());
                employeeExportList.add(employeeTemp);
            }
        }
    }

    /**
     * 获取买家池二级分类
     * @param classCode
     * @return
     */
    public List<BrMPdClasses> findMachiningList(String classCode) {
        List<BrMPdClasses> machiningList = null;
        if (!StringUtil.isEmpty(classCode)) {
            RsRequest<IBR121307RsParam> machiningRequest = new RsRequest<>();
            //传参数
            IBR121307RsParam brMPdClasses = new IBR121307RsParam();
            brMPdClasses.setClassesCode(classCode);
            brMPdClasses.setType(String.valueOf(NumberConst.IntDef.INT_ONE));
            machiningRequest.setAuth("MSK00001");
            machiningRequest.setLoginId("msk01");
            machiningRequest.setSiteCode("1");
            machiningRequest.setParam(brMPdClasses);
            String pdUrl = SystemServerManager.BuyersReportServerManager.getFindMachiningCodeU();
            ;
            RsResponse<IBR121307RsPageResult> response = RestClientUtil.post(pdUrl, machiningRequest, new TypeReference<RsResponse<IBR121307RsPageResult>>() {
            });
            machiningList = response.getResult().getBrMPdClassesList();
        }
        return machiningList;
    }

    /**
     * 获取买家基本信息
     * @param buyerId
     * @return
     */
    public BY121324Bean findBuyerBasic(String buyerId){
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        BY121324Bean buyerRegisterInfo = this.findOne(SqlId.FIND_BUYER_BASIC, param);
        return buyerRegisterInfo;
    }

    /**
     * 根据买家ID获取员工信息
     * @param buyerId
     * @return
     */
    public List<BY121324Bean> findEmployeeListByBuyerId(String buyerId){
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        List<BY121324Bean> employeeList = this.findList(SqlId.FIND_EMPLOYEE_BY_BUYERID,param);
        return employeeList;
    }

    /**
     * 根据买家ID获取商城账号
     * @param buyerId
     * @return
     */
    public List<BY121324Bean> findMallAccountListByBuyerId(String buyerId){
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        List<BY121324Bean> mallAccountList = this.findList(SqlId.FIND_MALL_ACCOUNT_BY_BUYERID,param);
        return mallAccountList;
    }

    /**
     * 根据买家ID获取买家池信息
     * @param buyerId
     * @return
     */
    public List<BY121324Bean> findPdClaByBuyerId(String buyerId){
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        List<BY121324Bean> pdCla = this.findList(SqlId.FIND_PDCLA_BY_BUYERID, param);
        return pdCla;
    }
}
