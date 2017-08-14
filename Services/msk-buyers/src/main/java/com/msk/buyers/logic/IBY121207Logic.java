package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.buyers.bean.IBY121207RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
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
 * IBY121207Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121207Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121207Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private IBY121206Logic iby121206Logic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家雇员信息获取
        String SQL_FIND_EMPLOYEE = "findEmployee";
        //根据买家ID，雇员ID，雇员类型查询雇员信息
        String SQL_FIND_EMPLOYEES = "findEmployees";
        //买家雇员信息更新
        String SQL_BUYER_EMPLOYEE_MODIFY = "buyerEmployeeModify";
        //买家雇员信息插入
        String SQL_BUYER_EMPLOYEE_INSERT = "buyerEmployeeInsert";
        //接口买家雇员信息获取
        String SQL_FIND_EMPLOYEE_LIST = "findEmployeeList";
        //验证买家id是否存在
        String TEST_BUYER_ID = "testBuyerId";

    }
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
     * 买家雇员信息更新接口
     * @param param
     * @return
     */
    @Transactional
    public int buyerEmployeeModify(List<IBY121207RsParam> param){
        int resultCount = NumberConst.IntDef.INT_ZERO;
        //根据传入参数获取买家雇员信息
        for (int i = 0; i < param.size(); i++) {
            BaseParam inParam = new BaseParam();
            inParam.setFilter("buyerId", param.get(i).getBuyerId());
            if(!StringUtil.isNullOrEmpty(param.get(i).getEmployeeType())){
                if("1".equals(param.get(i).getEmployeeType())){
                    int bossCount = super.getCount(inParam);
                    if(null != param.get(i).getId()){
                        inParam.setFilter("id", String.valueOf(param.get(i).getId()));
                        ByBuyerEmployee employee = super.findOne(SqlId.SQL_FIND_EMPLOYEE, inParam);
                        if(employee!= null){
                            if(!("1".equals(employee.getEmployeeType())) && (bossCount >= 1)){
                                resultCount = -1;
                                return resultCount;
                            }
                        }

                    }else{
                        if(bossCount >= 1){
                            //老板只能有一个
                            resultCount = -1;
                            return resultCount;
                        }
                    }
                }
            }
            param.get(i).setBusCardId(param.get(i).getBusCardPicPath());
            if(null == param.get(i).getId()){
                inParam.setFilter("id", "");
                inParam.setFilter("employeeName", param.get(i).getEmployeeName());
                inParam.setFilter("employeeType",param.get(i).getEmployeeType());
            }else{
                inParam.setFilter("id", String.valueOf(param.get(i).getId()));
            }
            //ByBuyerEmployee employee = super.findOne(SqlId.SQL_FIND_EMPLOYEE, inParam);
            List<ByBuyerEmployee> employees = super.findList(SqlId.SQL_FIND_EMPLOYEES,inParam);
            /*//名片照
            String[] busCardId = new String[NumberConst.IntDef.INT_TWO];
            if(!StringUtil.isNullOrEmpty(param.get(i).getBusCardPicPath())){
                String[] paramStr = param.get(i).getBusCardPicPath().split("/");
                String filePath = "/" + paramStr[3] + "/" + paramStr[4] + "/" + paramStr[5];
                String fileName = paramStr[paramStr.length-1];
                busCardId = iby121206Logic.getFileId(filePath,fileName,param.get(i).getBuyerId());
            }
            param.get(i).setBusCardId(param.get(i).getBusCardPicPath());
            param.get(i).setBusCardSuf(busCardId[1]);*/
            //获取到数据则更新表
            if(!CollectionUtils.isEmpty(employees)){
                //修改
                if(null != param.get(i).getId()){
                    param.get(i).setId(employees.get(NumberConst.IntDef.INT_ZERO).getId());
                    param.get(i).setUpdTime(DateTimeUtil.getCustomerDate());
                    resultCount += super.modify(SqlId.SQL_BUYER_EMPLOYEE_MODIFY,param.get(i));
                }else {
                    //新增
                    //相同雇员名称相同是否继续添加
                    if(!StringUtil.isNullOrEmpty(param.get(i).getIsFlag())){
                        if(super.getCount(SqlId.TEST_BUYER_ID,inParam)> NumberConst.IntDef.INT_ZERO){
                            //未获取到数据则插入表
                            resultCount += buyerEmployeeSave(param.get(i));
                        }else{
                            resultCount=NumberConst.IntDef.INT_ZERO;
                        }
                    }else {
                        //提示信息（同类员工姓名相同，是否继续添加？）
                        resultCount = -2;
                    }
                }
            }else {
                if(super.getCount(SqlId.TEST_BUYER_ID,inParam) > NumberConst.IntDef.INT_ZERO){
                    //未获取到数据则插入表
                    resultCount += buyerEmployeeSave(param.get(i));
                }
            }
        }
        return resultCount;
    }

    /**
     * 通路注册时新增雇员
     * @param param
     * @return
     */
    @Transactional
    public IBY121207RsParam buyerEmployeePhoneInsert(IBY121207RsParam param){
        //名片照
        /*String[] busCardId = new String[NumberConst.IntDef.INT_TWO];
        if(!StringUtil.isNullOrEmpty(param.getBusCardPicPath())){
            String[] paramStr = param.getBusCardPicPath().split("/");
            String filePath = "/" + paramStr[3] + "/" + paramStr[4] + "/" + paramStr[5];
            String fileName = paramStr[paramStr.length-1];
            busCardId = iby121206Logic.getFileId(filePath,fileName,param.getBuyerId());
        }*/
        String fileDownLoadServerUrl = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        param.setBusCardId(param.getBusCardPicPath());
        //param.setBusCardSuf(busCardId[1]);
        buyerEmployeeSave(param);
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId", param.getBuyerId());
        inParam.setFilter("employeeName", param.getEmployeeName());
        inParam.setFilter("employeeTel", param.getEmployeeTel());
        IBY121207RsParam employee = super.findOne(SqlId.SQL_FIND_EMPLOYEE, inParam);
        if(!StringUtil.isNullOrEmpty(employee.getBusCardId())){
            //图片上传文件路径
            /*String http = "http://";
            String url = ConfigManager.getFtpIp();
            String uploadPicFilePath = ConfigManager.getFtpImageRootPath();
            String busCardPic = http + url + uploadPicFilePath + BuyersConst.BYPath.BYIMAGEPATH + "/" + employee.getBuyerId() +"/" + employee.getBusCardId() + StringConst.DOT + employee.getBusCardSuf();*/
            employee.setBusCardPicPath(fileDownLoadServerUrl + employee.getBusCardId());
        }
        return employee;
    }
    /**
     * 买家雇员信息插入
     * @param insertParam
     * @return
     */
    @Transactional
    public int buyerEmployeeSave(IBY121207RsParam insertParam){
        Long id = commonLogic.maxId("by_buyer_employee","ID");
        insertParam.setId(id);
        insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_BUYER_EMPLOYEE_INSERT,insertParam);
    }

    /**
     * 买家雇员信息查询
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121207RsParam> buyerEmployeeFind(ByBuyerEmployee param){
        List<IBY121207RsParam> iby121207RsParamList = new ArrayList<>();
        String fileDownLoadServerUrl = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId",param.getBuyerId());
        if(null == param.getId()){
            inParam.setFilter("id", "");
        }else{
            inParam.setFilter("id", String.valueOf(param.getId()));
        }
        List<IBY121207RsParam> employeeList = super.findList(SqlId.SQL_FIND_EMPLOYEE,inParam);
        if(!CollectionUtils.isEmpty(employeeList)){
            for(int i = 0;i < employeeList.size();i++){
                IBY121207RsParam iby121207RsParam = employeeList.get(i);
                if(!StringUtil.isNullOrEmpty(iby121207RsParam.getBusCardId())){
                    //图片上传文件路径
                    /*String http = "http://";
                    String url = ConfigManager.getFtpIp();
                    String uploadPicFilePath = ConfigManager.getFtpImageRootPath();
                    String busCardPic = http + url + uploadPicFilePath + BuyersConst.BYPath.BYIMAGEPATH + "/" + iby121207RsParam.getBuyerId() +"/" + iby121207RsParam.getBusCardId() + StringConst.DOT + iby121207RsParam.getBusCardSuf();*/
                    iby121207RsParam.setBusCardPicPath(fileDownLoadServerUrl + iby121207RsParam.getBusCardId());
                }
                iby121207RsParamList.add(iby121207RsParam);
            }
        }
        Map<String,String> employeeTypeMap = CodeMasterManager.findCodeMasterMap("EmployeeType");
        for (IBY121207RsParam iby121207RsParam : iby121207RsParamList){
            iby121207RsParam.setEmployeeTypeName(employeeTypeMap.get(iby121207RsParam.getEmployeeType()));
        }
        return iby121207RsParamList;
    }

    @Transactional(readOnly = true)
    public IBY121207RsParam buyerEmployee(IBY121207RsParam param){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("id", String.valueOf(param.getId()));
        inParam.setFilter("buyerId",param.getBuyerId());
        return  super.findOne(SqlId.SQL_FIND_EMPLOYEE, inParam);
    }

    /**
     * 查询雇员信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121207RsParam> buyerEmployeeList(BuyerRelationParam param){
        List<IBY121207RsParam> iby121207RsParamList = new ArrayList<>();
        String fileDownLoadServerUrl = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        List<IBY121207RsParam> employeeList = super.findList(SqlId.SQL_FIND_EMPLOYEE_LIST,param);
        if(!CollectionUtils.isEmpty(employeeList)){
            for(int i = 0;i < employeeList.size();i++){
                IBY121207RsParam iby121207RsParam = employeeList.get(i);
                if(!StringUtil.isNullOrEmpty(iby121207RsParam.getBusCardId())){
                    //图片上传文件路径
                    /*String http = "http://";
                    String url = ConfigManager.getFtpIp();
                    String uploadPicFilePath = ConfigManager.getFtpImageRootPath();
                    String busCardPic = http + url + uploadPicFilePath + BuyersConst.BYPath.BYIMAGEPATH + "/" + iby121207RsParam.getBuyerId() +"/" + iby121207RsParam.getBusCardId() + StringConst.DOT + iby121207RsParam.getBusCardSuf();*/
                    iby121207RsParam.setBusCardPicPath(fileDownLoadServerUrl + iby121207RsParam.getBusCardId());
                }
                iby121207RsParamList.add(iby121207RsParam);
            }
        }
        Map<String,String> employeeTypeMap = CodeMasterManager.findCodeMasterMap("EmployeeType");
        for (IBY121207RsParam iby121207RsParam : iby121207RsParamList){
            iby121207RsParam.setEmployeeTypeName(employeeTypeMap.get(iby121207RsParam.getEmployeeType()));
        }
        return iby121207RsParamList;
    }
}
