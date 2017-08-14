package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * PD141101Logic
 *
 * @author pxg
 */
@Service
public class PD14112601Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     *
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_FIND_CLASS_DATA = "findClassData";
        String SQL_ID_FIND_FIND_THREE_CODE_DATA = "findThreeCodeData";
        String SQL_ID_FIND_FIND_DATA = "findData";
        String SQL_ID_FIND_FIND_MARKET = "findMarket";
        String SQL_ID_FIND_DELETE_MARKET = "deleteMarket";
        String SQL_ID_FIND_UPDATE_MARKET = "updateMarket";
        String SQL_ID_FIND_QUERY_PROVIDER = "queryProvider";
        String SQL_ID_FIND_DELETE_PROVIDER = "deleteProvider";
        String SQL_ID_FIND_UPDATE_PROVIDER = "updateProvider";
        String SQL_ID_FIND_QUERT_ONLINE = "queryOnLine";
        String SQL_ID_FIND_DELETE_ONLINE = "deleteOnLine";
        String SQL_ID_FIND_QUERY_PROVIDER_CONTENT = "queryProviderContent";
        String SQL_ID_FIND_DELETE_PROVIDER_CONTENT = "deleteProviderContent";
        String SQL_ID_FIND_QUERY_ONLINE_OEM = "queryOnlineOem";
        String SQL_ID_FIND_DELETE_ONLINE_OEM = "deleteOnlineOem";
        String SQL_ID_FIND_LEVEN4_LIST= "findListLevel4List";
        String SQL_ID_FIND_LEVEN5_LIST= "findListLevel5List";
        String SQL_ID_FIND_LEVEN6_LIST= "findListLevel6List";
        String SQL_ID_FIND_QUERY_PDTCFORBID = "queryTcForBid";
        String SQL_ID_FIND_DELETE_TCFORDID = "deleteTcForDid";
    }


    /**
     * 获取原料描述信息
     *
     * @return
     */
    @Transactional(readOnly = true)
    public PdClassestreeMat queryClassData(String classCode) {
        BaseParam param = new BaseParam();
        param.setFilter("classCode", classCode);
        param.setFilter("delFlg", "0");
        PdClassestreeMat pdClassestreeMat = super.findOne(SqlId.SQL_ID_FIND_FIND_CLASS_DATA, param);
        return pdClassestreeMat;
    }

    /**
     * 选择二级分类获取三级分类Code
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdClassestree> queryThreeCodeData(String classCodeTwo) {
        BaseParam param = new BaseParam();
        param.setFilter("classCode", classCodeTwo);
        param.setFilter("treeLevel", "3");
        param.setFilter("delFlg", "0");
        List<PdClassestree> list = super.findList(SqlId.SQL_ID_FIND_FIND_THREE_CODE_DATA, param);
        return list;
    }

    /**
     * 选择二级分类获取产品总控目录数据
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD14112601Param> queryTwoData(String classCodeTwo) {
        List<PD14112601Param> listMat = new ArrayList<>();
        List<PD141128Param> listMarket = new ArrayList<>();
        List<PdTcProviderPackageParam> listProvider = new ArrayList<>();
        List<PdTcOnlineParam> listOnline = new ArrayList<>();
        List<PdTcProviderContentParam> listProviderContent = new ArrayList<>();
        List<PdTcOnlineOemParam> pdTcOnlineOems = new ArrayList<>();
        List<PdClassestree> list = this.queryThreeCodeData(classCodeTwo);
        List<String> listDataSalesTargetRemove = new ArrayList<>();
        List<String> listDataMachiningWayRemove = new ArrayList<>();
        List<PD14112601Bean> level4BeanList=new ArrayList<>();
        List<PdTcForbidParam> pdTcForbidParamArrayList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                PdClassestree pdClassestree = list.get(i);
                BaseParam param = new BaseParam();
                param.setFilter("classestreeCode", pdClassestree.getClassestreeCode());
                param.setFilter("delFlg", "0");
                PD14112601Param pD14112601Param = super.findOne(SqlId.SQL_ID_FIND_FIND_DATA, param);
                if (null != pD14112601Param) {
                    listMarket = this.queryMarketData(pdClassestree.getClassestreeCode(), null);
                    if (!CollectionUtils.isEmpty(listMarket)) {
                        pD14112601Param.setPdTcMarketList(listMarket);
                    }
                    listProvider = this.queryProviderData(pdClassestree.getClassestreeCode());
                    if (!CollectionUtils.isEmpty(listProvider)) {
                        pD14112601Param.setPdTcProvider(listProvider);
                    }
                    listOnline = this.queryOnLineData(pdClassestree.getClassestreeCode(), null);
                    if (!CollectionUtils.isEmpty(listOnline)) {
                        pD14112601Param.setPdTcOnline(listOnline);
                    }
                    listProviderContent = this.queryProviderContent(pdClassestree.getClassestreeCode(), null);
                    if (!CollectionUtils.isEmpty(listProviderContent)) {
                        for (int a = 0; a < listProviderContent.size(); a++) {
                            PdTcProviderContentParam pdTcProviderContentParam = listProviderContent.get(a);
                            listDataSalesTargetRemove.add(pdTcProviderContentParam.getSalesTarget());
                            listDataMachiningWayRemove.add(pdTcProviderContentParam.getMachiningWay());
                        }
                        StringBuffer setSalesTarget = new StringBuffer();
                        StringBuffer setMachiningWay = new StringBuffer();
                        listDataSalesTargetRemove = new ArrayList<>(new HashSet(listDataSalesTargetRemove));
                        listDataMachiningWayRemove=new ArrayList<>(new HashSet(listDataMachiningWayRemove));
                        for (int j = 0; j < listDataSalesTargetRemove.size(); j++) {
                            String SalesTarget = listDataSalesTargetRemove.get(j);
                            if(j+1==listDataSalesTargetRemove.size()){
                                setSalesTarget.append(SalesTarget);
                            }else{
                                setSalesTarget.append(SalesTarget + ",");
                            }
                        }
                        for (int j = 0; j < listDataMachiningWayRemove.size(); j++) {
                            String machiningWay = listDataMachiningWayRemove.get(j);
                            if(j+1==listDataMachiningWayRemove.size()){
                                setMachiningWay.append(machiningWay);
                            }else{
                                setMachiningWay.append(machiningWay + ",");
                            }
                        }
                        pD14112601Param.setPdTcProviderContents(listProviderContent);
                        pD14112601Param.setSalesTargetTwo(StringUtil.toSafeString(setSalesTarget));
                        pD14112601Param.setMachiningWayTwo(StringUtil.toSafeString(setMachiningWay));
                    }
                    pdTcOnlineOems = this.queryOnlineOem(pdClassestree.getClassestreeCode(), null);
                    if (!CollectionUtils.isEmpty(pdTcOnlineOems)) {
                        pD14112601Param.setPdTcOnlineOem(pdTcOnlineOems);
                    }
                    level4BeanList=this.queryClassInfo(pdClassestree.getClassestreeCode());
                    if(!CollectionUtils.isEmpty(level4BeanList)){
                        pD14112601Param.setPd141124Level4Beans(level4BeanList);
                    }
                    pdTcForbidParamArrayList=this.queryTcForbid(pdClassestree.getClassestreeCode(),null);
                    if(!CollectionUtils.isEmpty(pdTcForbidParamArrayList)){
                        pD14112601Param.setPdTcForbidParams(pdTcForbidParamArrayList);
                    }
                }
                listMat.add(pD14112601Param);
            }
        }
        return listMat;
    }

    /**
     * 选择三级分类获取产品总控目录数据
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD14112601Param> queryThreeData(String classCodeThree) {
        BaseParam param = new BaseParam();
        List<PD14112601Param> listMat = new ArrayList<>();
        List<PD141128Param> listMarket = new ArrayList<>();
        List<PdTcProviderPackageParam> listProvider = new ArrayList<>();
        List<PdTcOnlineParam> listOnline = new ArrayList<>();
        List<PdTcProviderContentParam> listProviderContent = new ArrayList<>();
        PD14112601Param pd14112601Param = new PD14112601Param();
        List<PdTcOnlineOemParam> pdTcOnlineOems = new ArrayList<>();
        List<PD14112601Bean> level4BeanList=new ArrayList<>();
        List<String> listDataSalesTargetRemove = new ArrayList<>();
        List<String> listDataMachiningWayRemove = new ArrayList<>();
        List<PdTcForbidParam> pdTcForbidParamArrayList = new ArrayList<>();
        param.setFilter("classestreeCode", classCodeThree);
        param.setFilter("delFlg", "0");
        pd14112601Param = super.findOne(SqlId.SQL_ID_FIND_FIND_DATA, param);
        if (null != pd14112601Param) {
            listMarket = this.queryMarketData(classCodeThree, null);
            if (!CollectionUtils.isEmpty(listMarket)) {
                pd14112601Param.setPdTcMarketList(listMarket);
            }
            listProvider = this.queryProviderData(classCodeThree);
            if (!CollectionUtils.isEmpty(listProvider)) {
                pd14112601Param.setPdTcProvider(listProvider);
            }
            listOnline = this.queryOnLineData(classCodeThree, null);
            if (!CollectionUtils.isEmpty(listOnline)) {
                pd14112601Param.setPdTcOnline(listOnline);
            }
            listProviderContent = this.queryProviderContent(classCodeThree, null);
            if (!CollectionUtils.isEmpty(listProviderContent)) {
                for (int a = 0; a < listProviderContent.size(); a++) {
                    PdTcProviderContentParam pdTcProviderContentParam = listProviderContent.get(a);
                    listDataSalesTargetRemove.add(pdTcProviderContentParam.getSalesTarget());
                    listDataMachiningWayRemove.add(pdTcProviderContentParam.getMachiningWay());
                }
                StringBuffer setSalesTarget = new StringBuffer();
                StringBuffer setMachiningWay = new StringBuffer();
                listDataSalesTargetRemove = new ArrayList<>(new HashSet(listDataSalesTargetRemove));
                listDataMachiningWayRemove=new ArrayList<>(new HashSet(listDataMachiningWayRemove));
                for (int j = 0; j < listDataSalesTargetRemove.size(); j++) {
                    String SalesTarget = listDataSalesTargetRemove.get(j);
                    if(j+1==listDataSalesTargetRemove.size()){
                        setSalesTarget.append(SalesTarget);
                    }else{
                        setSalesTarget.append(SalesTarget + ",");
                    }
                }
                for (int j = 0; j < listDataMachiningWayRemove.size(); j++) {
                    String machiningWay = listDataMachiningWayRemove.get(j);
                    if(j+1==listDataMachiningWayRemove.size()){
                        setMachiningWay.append(machiningWay);
                    }else{
                        setMachiningWay.append(machiningWay + ",");
                    }
                }
                pd14112601Param.setSalesTargetTwo(StringUtil.toSafeString(setSalesTarget));
                pd14112601Param.setMachiningWayTwo(StringUtil.toSafeString(setMachiningWay));
                pd14112601Param.setPdTcProviderContents(listProviderContent);
            }
            pdTcOnlineOems = this.queryOnlineOem(classCodeThree, null);
            if (!CollectionUtils.isEmpty(pdTcOnlineOems)) {
                pd14112601Param.setPdTcOnlineOem(pdTcOnlineOems);
            }
            level4BeanList=this.queryClassInfo(classCodeThree);
            if(!CollectionUtils.isEmpty(level4BeanList)){
                pd14112601Param.setPd141124Level4Beans(level4BeanList);
            }
            pdTcForbidParamArrayList=this.queryTcForbid(classCodeThree,null);
            if(!CollectionUtils.isEmpty(pdTcForbidParamArrayList)){
                pd14112601Param.setPdTcForbidParams(pdTcForbidParamArrayList);
            }
        }
        listMat.add(pd14112601Param);
        return listMat;
    }

    /**
     * 获取分类编码获取市场需求编码注册
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141128Param> queryMarketData(String classCodeTwo, String getDivId) {
        BaseParam param = new BaseParam();
        String classesCode = null;
        String machiningCode = null;
        String breedCode = null;
        if(StringUtils.hasLength(classCodeTwo)){
            if(classCodeTwo.length() >= NumberConst.IntDef.INT_TWO){
                classesCode =classCodeTwo.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            }
            if(classCodeTwo.length() >= NumberConst.IntDef.INT_THREE){
                machiningCode =classCodeTwo.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            }
            if(classCodeTwo.length() >= NumberConst.IntDef.INT_FIVE){
                breedCode =classCodeTwo.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE);
            }
        }
        param.setFilter("classesCode", classesCode);
        param.setFilter("machiningCode", machiningCode);
        param.setFilter("breedCode", breedCode);
        /*if (classCodeTwo.length() == 5) {
            code = classCodeTwo.substring(3, 5);
            param.setFilter("breedCode", code);
        } else {
            code = classCodeTwo.substring(2, 3);
            param.setFilter("machiningCode", code);
        }*/
        param.setFilter("delFlg", "0");
        List<PD141128Param> listMatNew = new ArrayList<>();
        List<PD141128Param> listMat = super.findList(SqlId.SQL_ID_FIND_FIND_MARKET, param);
        for (int i = 0; i < listMat.size(); i++) {
            PD141128Param pd141128Param = listMat.get(i);
            pd141128Param.setGetDivId(getDivId);
            listMatNew.add(pd141128Param);
        }
        return listMatNew;
    }

    /**
     * 删除市场需求编码注册
     *
     * @return
     */
    @Transactional
    public int deleteData(String deleteValue,BaseParam param) {
        param.setFilter("tcOnlineId", deleteValue);
        param.setFilter("delFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_DELETE_MARKET, param);
        return num;
    }

    /**
     * 更新市场需求编码注册
     *
     * @return
     */
    @Transactional
    public int updateData(String updateValue,BaseParam param) {
        param.setFilter("tcOnlineId", updateValue);
        param.setFilter("featureFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_UPDATE_MARKET, param);
        return num;
    }

    /**
     * @param classCode classCode
     *                  查询卖家供应备案注册
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdTcProviderPackageParam> queryProviderData(String classCode) {
        BaseParam param = new BaseParam();
        String classesCode = null;
        String machiningCode = null;
        String breedCode = null;
        if(StringUtils.hasLength(classCode)){
            if(classCode.length() >= NumberConst.IntDef.INT_TWO){
                classesCode =classCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            }
            if(classCode.length() >= NumberConst.IntDef.INT_THREE){
                machiningCode =classCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            }
            if(classCode.length() >= NumberConst.IntDef.INT_FIVE){
                breedCode =classCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE);
            }
        }
        param.setFilter("classesCode", classesCode);
        param.setFilter("machiningCode", machiningCode);
        param.setFilter("breedCode", breedCode);

        /*if (classCode.length() == 5) {
            code = classCode.substring(3, 5);
            param.setFilter("breedCode", code);
        } else {
            code = classCode.substring(2, 3);
            param.setFilter("machiningCode", code);
        }*/
        param.setFilter("delFlg", "0");
        List<PdTcProviderPackageParam> list = super.findList(SqlId.SQL_ID_FIND_QUERY_PROVIDER, param);
        return list;
    }

    /**
     * 删除卖家供应备案注册
     *
     * @return
     */
    @Transactional
    public int deleteProvider(String tcProviderId,BaseParam param) {
        param.setFilter("tcProviderId", tcProviderId);
        param.setFilter("delFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_DELETE_PROVIDER, param);
        return num;
    }

    /**
     * 更新卖家供应备案注册
     *
     * @return
     */
    @Transactional
    public int updateProvider(String tcProviderId,BaseParam param) {
        param.setFilter("tcProviderId", tcProviderId);
        param.setFilter("featureFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_UPDATE_PROVIDER, param);
        return num;
    }

    /**
     * 查询正式上线数据
     *
     * @param classestreeCode classestreeCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdTcOnlineParam> queryOnLineData(String classestreeCode, String getDivId) {
        BaseParam param = new BaseParam();
        String classesCode = null;
        String machiningCode = null;
        String breedCode = null;
        if(StringUtils.hasLength(classestreeCode)){
            if(classestreeCode.length() >= NumberConst.IntDef.INT_TWO){
                classesCode =classestreeCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_THREE){
                machiningCode =classestreeCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_FIVE){
                breedCode =classestreeCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE);
            }
        }
        param.setFilter("classesCode", classesCode);
        param.setFilter("machiningCode", machiningCode);
        param.setFilter("breedCode", breedCode);
        /*String code = null;
        if (classestreeCode.length() == 5) {
            code = classestreeCode.substring(3, 5);
            param.setFilter("breedCode", code);
        } else {
            code = classestreeCode.substring(2, 3);
            param.setFilter("machiningCode", code);
        }*/
        param.setFilter("delFlg", "0");
        List<PdTcOnlineParam> list = new ArrayList<>();
        List<PdTcOnlineParam> listOnLine = super.findList(SqlId.SQL_ID_FIND_QUERT_ONLINE, param);
        if (!CollectionUtils.isEmpty(listOnLine)) {
            for (int i = 0; i < listOnLine.size(); i++) {
                PdTcOnlineParam pdTcOnlineParam = listOnLine.get(i);
                pdTcOnlineParam.setGetDivId(getDivId);
                list.add(pdTcOnlineParam);
            }
        }
        return list;
    }

    /**
     * 删除正式上线
     *
     * @return
     */
    @Transactional
    public int deleteOnLine(String tcOnlineId,BaseParam param) {
        param.setFilter("tcOnlineId", tcOnlineId);
        param.setFilter("delFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_DELETE_ONLINE, param);
        return num;
    }

    /**
     * 查询卖家供应备案注册标准目录
     *
     * @param classestreeCode classestreeCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdTcProviderContentParam> queryProviderContent(String classestreeCode, String getDivId) {
        BaseParam param = new BaseParam();
        String classesCode = null;
        String machiningCode = null;
        String breedCode = null;
        if(StringUtils.hasLength(classestreeCode)){
            if(classestreeCode.length() >= NumberConst.IntDef.INT_TWO){
                classesCode =classestreeCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_THREE){
                machiningCode =classestreeCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_FIVE){
                breedCode =classestreeCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE);
            }
        }
        param.setFilter("classesCode", classesCode);
        param.setFilter("machiningCode", machiningCode);
        param.setFilter("breedCode", breedCode);
        /*String code = null;
        if (classestreeCode.length() == 5) {
            code = classestreeCode.substring(3, 5);
            param.setFilter("breedCode", code);
        } else {
            code = classestreeCode.substring(2, 3);
            param.setFilter("machiningCode", code);
        }*/
/*        param.setFilter("breedCode", code);
        param.setFilter("machiningCode", code);*/
        param.setFilter("delFlg", "0");
        List<PdTcProviderContentParam> list = new ArrayList<>();
        List<PdTcProviderContentParam> listOnLine = super.findList(SqlId.SQL_ID_FIND_QUERY_PROVIDER_CONTENT, param);
        if (!CollectionUtils.isEmpty(listOnLine)) {
            for (int i = 0; i < listOnLine.size(); i++) {
                PdTcProviderContentParam pdTcProviderContent = listOnLine.get(i);
                pdTcProviderContent.setGetDivId(getDivId);
                list.add(pdTcProviderContent);
            }
        }
        return list;
    }

    /**
     * 删除卖家供应备案注册标准目录
     *
     * @return
     */
    @Transactional
    public int deleteProviderContent(String tcContentId,BaseParam param) {
        param.setFilter("tcContentId", tcContentId);
        param.setFilter("delFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_DELETE_PROVIDER_CONTENT, param);
        return num;
    }

    /**
     * 查询OEM上线产品
     *
     * @param classestreeCode classestreeCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdTcOnlineOemParam> queryOnlineOem(String classestreeCode, String getDivId) {
        BaseParam param = new BaseParam();
        String classesCode = null;
        String machiningCode = null;
        String breedCode = null;
        if(StringUtils.hasLength(classestreeCode)){
            if(classestreeCode.length() >= NumberConst.IntDef.INT_TWO){
                classesCode =classestreeCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_THREE){
                machiningCode =classestreeCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_FIVE){
                breedCode =classestreeCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE);
            }
        }
        param.setFilter("classesCode", classesCode);
        param.setFilter("machiningCode", machiningCode);
        param.setFilter("breedCode", breedCode);
       /* String code = null;
        if (classestreeCode.length() == 5) {
            code = classestreeCode.substring(3, 5);
            param.setFilter("breedCode", code);
        } else {
            code = classestreeCode.substring(2, 3);
            param.setFilter("machiningCode", code);
        }*/
        param.setFilter("delFlg", "0");
        List<PdTcOnlineOemParam> list = new ArrayList<>();
        List<PdTcOnlineOemParam> listOnLine = super.findList(SqlId.SQL_ID_FIND_QUERY_ONLINE_OEM, param);
        if (!CollectionUtils.isEmpty(listOnLine)) {
            for (int i = 0; i < listOnLine.size(); i++) {
                PdTcOnlineOemParam pdTcOnlineOemParam = listOnLine.get(i);
                pdTcOnlineOemParam.setGetDivId(getDivId);
                list.add(pdTcOnlineOemParam);
            }
        }
        return list;
    }

    /**
     * 删除OEM上线产品
     *
     * @return
     */
    @Transactional
    public int deleteOnlineOem(String tcOemId,BaseParam param) {
        param.setFilter("tcOemId", tcOemId);
        param.setFilter("delFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_DELETE_ONLINE_OEM, param);
        return num;
    }

    /**
     * 查询产品特征净重包装
     *
     * @param classestreeCode classestreeCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD14112601Bean> queryClassInfo(String classestreeCode) {
        BaseParam param = new BaseParam();
        param.setFilter("parentCode", classestreeCode);
        param.setFilter("treeLevel","4");
        List<PD14112601Bean> level4BeanList = super.findList(SqlId.SQL_ID_FIND_LEVEN4_LIST, param);
        /*if(!CollectionUtils.isEmpty(level4BeanList)){
            for (PD141124Level4Bean level4Bean :level4BeanList) {
                BaseParam param2=new BaseParam();
                param2.setFilter("parentCode",level4Bean.getClassesTreeCode());
                param.setFilter("treeLevel","5");
                List<PD141124Level5Bean> level5BeanList=super.findList(SqlId.SQL_ID_FIND_LEVEN5_LIST,param2);
                for(PD141124Level5Bean level5Bean :level5BeanList){
                    BaseParam param3=new BaseParam();
                    param3.setFilter("parentCode",level5Bean.getClassesTreeCode());
                    param3.setFilter("treeLevel","6");
                    List<PD141124Level6Bean> level6BeanList=super.findList(SqlId.SQL_ID_FIND_LEVEN6_LIST,param3);
                    level5Bean.setLevel6Beans(level6BeanList);
                }
                level4Bean.setLevel5Beans(level5BeanList);
            }
        }*/
        return level4BeanList;
    }

    /**
     * 查询禁止准入产品
     * @param classestreeCode classestreeCode
     * @param getDivId getDivId
     * @return
     */
    @Transactional(readOnly = true)
    public List<PdTcForbidParam> queryTcForbid(String classestreeCode,String getDivId) {
        BaseParam param = new BaseParam();
        String classesCode = null;
        String machiningCode = null;
        String breedCode = null;
        if(StringUtils.hasLength(classestreeCode)){
            if(classestreeCode.length() >= NumberConst.IntDef.INT_TWO){
                classesCode =classestreeCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_THREE){
                machiningCode =classestreeCode.substring(NumberConst.IntDef.INT_TWO, NumberConst.IntDef.INT_THREE);
            }
            if(classestreeCode.length() >= NumberConst.IntDef.INT_FIVE){
                breedCode =classestreeCode.substring(NumberConst.IntDef.INT_THREE, NumberConst.IntDef.INT_FIVE);
            }
        }
        param.setFilter("classesCode", classesCode);
        param.setFilter("machiningCode", machiningCode);
        param.setFilter("breedCode", breedCode);
        /*String code = null;
        if (classestreeCode.length() == 5) {
            code = classestreeCode.substring(3, 5);
            param.setFilter("breedCode", code);
        } else {
            code = classestreeCode.substring(2, 3);
            param.setFilter("machiningCode", code);
        }*/
        param.setFilter("delFlg", "0");
        List<PdTcForbidParam> list = new ArrayList<>();
        List<PdTcForbidParam> listOnLine = super.findList(SqlId.SQL_ID_FIND_QUERY_PDTCFORBID,param);
        if (!CollectionUtils.isEmpty(listOnLine)) {
            for (int i = 0; i < listOnLine.size(); i++) {
                PdTcForbidParam pdTcForbidParam = listOnLine.get(i);
                pdTcForbidParam.setGetDivId(getDivId);
                list.add(pdTcForbidParam);
            }
        }
        return listOnLine;
    }

    /**
     * 删除禁止准入产品
     *
     * @return
     */
    @Transactional
    public int deleteTcForbid(String tcForbidId,BaseParam param) {
        param.setFilter("tcForbidId", tcForbidId);
        param.setFilter("delFlg", "1");
        int num = super.modify(SqlId.SQL_ID_FIND_DELETE_TCFORDID, param);
        return num;
    }
}
