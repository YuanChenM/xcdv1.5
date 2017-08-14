package com.msk.ds.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.ds.bean.SC182211Bean;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SC182211Logic.
 * @author pxg
 * @version 1.0
 **/
@Service
public class SC182211Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182211Logic.class);
    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    private interface SqlId {
        String SQL_ID_FIND_EPNAMELIST = "findEpNameList";
        String SQL_ID_FIND_SLONECLASS = "findSlOneClass";
        String SQL_ID_FIND_SLPROPEPLIST = "findSlPropEpList";
        String SQL_ID_FIND_SLBRAND = "findSlBrand";
        String SQL_ID_FIND_NORMSOUT = "findNormsOut";
        String SQL_ID_FIND_SAVEDATA = "saveData";
        String SQL_ID_FIND_SLPDARTNO = "findSlPdArtno";
        String SQL_ID_FIND_DATAEXPORT = "findDataExport";
        String SQL_ID_FIND_DELETE_DATA = "findDeleteData";
        String SQL_ID_FIND_SAVEDSSLPDLOT = "saveDsSlPdLot";
        String SQL_ID_FIND_SAVEDDSLOTSERIAL = "saveDsLotSerial";
        String SQL_ID_FIND_QUERYDATASEARCH = "queryDataSearch";
        String SQL_ID_FIND_DATAEXISTED = "dataExisted";
        String SQL_ID_FIND_DATAEXISTEDTWO = "dataExistedTwo";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 查询所有卖家列表
     * @return 卖家名称
     */
    public List<SC182211Bean> findEpNameList(BaseParam baseParam){
        ISL231193RsParam param = new ISL231193RsParam();
        List<SC182211Bean> list = new ArrayList<SC182211Bean>();
        List<ISL231193RsResult> resultList = RestUtil.slEpDataListSearch(param);
        if(!CollectionUtils.isEmpty(resultList)){
            String slCode="";
            if(CommCodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(baseParam.getUserType())){
                ISL231193RsParam param1 = new ISL231193RsParam();
                param1.setSlAccount(baseParam.getCrtId());
                slCode = RestUtil.queryslEpData(param1).getSlCode();
                for (ISL231193RsResult result : resultList) {
                   if(result.getSlCode().equals(slCode)){
                       SC182211Bean bean = new SC182211Bean();
                       bean.setSlCode(result.getSlCode());
                       bean.setSlCodeDis(result.getSlCodeDis());
                       bean.setEpName(result.getEpName());
                       list.add(bean);
                       return list;
                   }
                }
            }
            for (ISL231193RsResult result : resultList) {
                SC182211Bean bean = new SC182211Bean();
                bean.setSlCode(result.getSlCode());
                bean.setSlCodeDis(result.getSlCodeDis());
                bean.setEpName(result.getEpName());
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 根据卖家编码查询一级分类
     * @return 分类List
     */
    public List<PdClassestree> findSlOneClass(String slCode,String treeLevel,String parentCode,String classCode){
        ISL231205RsParam rsParam = new ISL231205RsParam();
        rsParam.setSlCode(slCode);
        rsParam.setParentCode(parentCode);
        if(!StringUtil.isNullOrEmpty(treeLevel) && treeLevel.equals("1")){
            rsParam.setTreeLevel(NumberConst.IntDef.INT_ONE);
        }else if(!StringUtil.isNullOrEmpty(treeLevel) && treeLevel.equals("2")){
            rsParam.setTreeLevel(NumberConst.IntDef.INT_TWO);
            rsParam.setPdClassesCode(classCode);
        }else if(!StringUtil.isNullOrEmpty(treeLevel) && treeLevel.equals("3")){
            rsParam.setTreeLevel(NumberConst.IntDef.INT_THREE);
            rsParam.setMachiningCode(classCode);
        }else if(!StringUtil.isNullOrEmpty(treeLevel) && treeLevel.equals("4")){
            rsParam.setTreeLevel(NumberConst.IntDef.INT_FOUR);
            rsParam.setPdBreedCode(classCode);
        }
        else if(!StringUtil.isNullOrEmpty(treeLevel) && treeLevel.equals("5")){
            rsParam.setTreeLevel(NumberConst.IntDef.INT_FIVE);
            rsParam.setPdFeatureCode(classCode);
        }
        List<ISL231205Result> list = RestUtil.findSlOneClass(rsParam);
        List<PdClassestree> treeList = new ArrayList<PdClassestree>();
        if(!CollectionUtils.isEmpty(list)){
            for (ISL231205Result result: list) {
                PdClassestree tree = new PdClassestree();
                tree.setLevelCode(result.getLevelCode());
                tree.setLevelName(result.getLevelName());
                tree.setClassestreeCode(result.getClassestreeCode());
                treeList.add(tree);
            }
        }
        return treeList;
    }

    /**
     * 根据卖家产品查询产品生产商
     * @return  生产商List
     */
    public List<SC182211Bean> findSlPropEpList(SC182211Bean sc182211Bean){
        ISL231206RsParam param = new ISL231206RsParam();
        param.setSlCode(sc182211Bean.getSlCode());
        param.setMachiningCode(sc182211Bean.getMachiningCode());
        param.setWeightCode(sc182211Bean.getWeightCode());
        param.setPdFeatureCode(sc182211Bean.getPdFeatureCode());
        param.setPdBreedCode(sc182211Bean.getPdBreedCode());
        param.setPdClassesCode(sc182211Bean.getPdClassesCode());
        List<ISL231206RsBean> resultList = RestUtil.findSlPropEpList(param);
        List<SC182211Bean> list = new ArrayList<SC182211Bean>();
        if(!CollectionUtils.isEmpty(resultList)){
            for (ISL231206RsBean result: resultList) {
                SC182211Bean bean = new SC182211Bean();
                bean.setProdEpId(result.getProdEpId());
                bean.setEpName(result.getEpName());
                bean.setSlCode(result.getSlCode());
                bean.setSlCodeDis(result.getSlCodeDis());
                if (!StringUtil.isEmpty(result.getSlCodeManufacture()) &&
                        NumberConst.IntDef.INT_SIX == result.getSlCodeManufacture().length()) {
                    bean.setSlCodeManufacture(result.getSlCodeManufacture());
                    list.add(bean);
                }else {
                }
            }
        }
        return list;
    }

    /**
     * 根据卖家查询品牌
     * @return  产品List
     */
    public List<SC182211Bean> querySlBrand(SC182211Bean sc182211Bean){
        SlProduct product = new SlProduct();
        product.setSlCode(sc182211Bean.getSlCode());
        product.setMachiningCode(sc182211Bean.getMachiningCode());
        product.setPdClassesCode(sc182211Bean.getPdClassesCode());
        product.setWeightCode(sc182211Bean.getWeightCode());
        product.setPdBreedCode(sc182211Bean.getPdBreedCode());
        product.setPdFeatureCode(sc182211Bean.getPdFeatureCode());
        List<SlPdBrand> resultList = RestUtil.findSlBrand(product);
        List<SC182211Bean> list = new ArrayList<SC182211Bean>();
        if(!CollectionUtils.isEmpty(resultList)){
            for (SlPdBrand result: resultList) {
                SC182211Bean bean = new SC182211Bean();
                bean.setBrandId(String.valueOf(result.getBrandId()));
                bean.setBrandName(result.getBrandName());
                bean.setBrandClass(result.getBrandClass());
                list.add(bean);
            }
        }
        return list;
    }
    /**
     * 根据卖家查询包装规格
     * @return  产品List
     */
    public List<SC182211Bean> queryNormsOut(SC182211Bean sc182211Bean){
        PDInfoParam param = new PDInfoParam();
        param.setClassesCode(sc182211Bean.getPdClassesCode());
        param.setMachiningCode(sc182211Bean.getMachiningCode());
        param.setWeightCode(sc182211Bean.getWeightCode());
        param.setBreedCode(sc182211Bean.getPdBreedCode());
        param.setFeatureCode(sc182211Bean.getPdFeatureCode());
        List<PDInfoResult> resultList = RestUtil.getPdNormsStd(param);
        List<SC182211Bean> list = new ArrayList<SC182211Bean>();
        if(!CollectionUtils.isEmpty(resultList)){
            for (PDInfoResult result: resultList) {
                SC182211Bean bean = new SC182211Bean();
                bean.setNormsCode(result.getNormsCode());
                bean.setNormsOut(result.getNormsOut());
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 保存数据
     * @return  保存结果
     */
    @Transactional
    public void saveData(SC182211Bean sc182211Bean){
        logger.info("生成打印数据开始");
        if(null!=sc182211Bean){

            //Modify start in 2016/9/7 by zhang_jiaxing with clearing null pointer exception
            DsPrintTask dsPrintTask=new DsPrintTask();
            String dateTime=sc182211Bean.getPriceDate().replace("-", "");
            sc182211Bean.setPriceDate(dateTime);
            //Modify end in 2016/9/7 by zhang_jiaxing with clearing null pointer exception
            //Modify for 2686 at 2016/09/14 by li_kai1 Start
            Integer number = StringUtil.toInteger(sc182211Bean.getNumber());
            Integer numberTwo = StringUtil.toInteger(sc182211Bean.getNumberTwo());
            //Modify for 2686 at 2016/09/14 by li_kai1 End
            //插入卖家产品的批次管理表
            DsSlPdLot dsSlPdLotOne=this.dataExistedTwo(sc182211Bean);
            DsSlPdLot dsSlPdLot=new DsSlPdLot();
            Long lotId=0L;

            if(null==dsSlPdLotOne){
                lotId=commonLogic.maxId("DS_SL_PD_LOT", "LOT_ID");
                /**为方便数据的插入和显示，获取包装规格*/
                PDInfoParam param = new PDInfoParam();
                param.setClassesCode(sc182211Bean.getPdClassesCode());
                param.setMachiningCode(sc182211Bean.getMachiningCode());
                param.setWeightCode(sc182211Bean.getWeightCode());
                param.setBreedCode(sc182211Bean.getPdBreedCode());
                param.setFeatureCode(sc182211Bean.getPdFeatureCode());
                List<PDInfoResult> resultList = RestUtil.getPdNormsStd(param);
                if(!CollectionUtils.isEmpty(resultList)){
                    for (PDInfoResult result: resultList) {
                        if(sc182211Bean.getNormsCode().equals(result.getNormsCode()));
                        dsSlPdLot.setPkgWay(result.getNormsNumber());
                        dsSlPdLot.setNetweight(result.getNormsOut());
                        break;
                    }
                }
                /**为方便数据的插入和显示，插入卖家物流区信息*/
                dsSlPdLot.setLgcsName(sc182211Bean.getLgcsAreaName());
                dsSlPdLot.setLgcsCode(sc182211Bean.getLgcsAreaCode());


                /**查询产品产地信息*/

                dsSlPdLot.setLotId(lotId);
                dsSlPdLot.setClassesCode(sc182211Bean.getPdClassesCode());
                dsSlPdLot.setMachiningCode(sc182211Bean.getMachiningCode());
                dsSlPdLot.setBreedCode(sc182211Bean.getPdBreedCode());
                dsSlPdLot.setFeatureCode(sc182211Bean.getPdFeatureCode());
                dsSlPdLot.setWeightCode(sc182211Bean.getWeightCode());
                dsSlPdLot.setPkgCode(sc182211Bean.getNormsCode());
                dsSlPdLot.setGradeCode(sc182211Bean.getGroupCode());
                dsSlPdLot.setSlCode(sc182211Bean.getSlCode());
                dsSlPdLot.setSlCodeDis(sc182211Bean.getSlCodeDis());
                dsSlPdLot.setSlCodeManufacture(sc182211Bean.getSlCodeManufacture());
                dsSlPdLot.setSalesPlatform(sc182211Bean.getSalePlatform());
                dsSlPdLot.setManufacturer(sc182211Bean.getEpName());
                dsSlPdLot.setDateCode(sc182211Bean.getPriceDate()+sc182211Bean.getHalf());
                dsSlPdLot.setPdCode(sc182211Bean.getPdClassesCode()+sc182211Bean.getMachiningCode()+sc182211Bean.getPdBreedCode()+sc182211Bean.getPdFeatureCode()+sc182211Bean.getWeightCode()+sc182211Bean.getGroupCode());
                dsSlPdLot.setClassesName(sc182211Bean.getPdClassesName());
                dsSlPdLot.setMachiningName(sc182211Bean.getMachiningName());
                dsSlPdLot.setPdName(sc182211Bean.getPdClassesName()+" "+sc182211Bean.getMachiningName()+" "+sc182211Bean.getPdBreedName()+" "+sc182211Bean.getPdFeatureName()+" "+sc182211Bean.getWeightName()+" "+sc182211Bean.getGroupName());
                dsSlPdLot.setBreedName(sc182211Bean.getPdBreedName());
                dsSlPdLot.setFeatureName(sc182211Bean.getPdFeatureName());
                dsSlPdLot.setWeightName(sc182211Bean.getWeightName());
                dsSlPdLot.setGradeName(sc182211Bean.getGroupName());
                dsSlPdLot.setSlName(sc182211Bean.getSlCodeName());
                dsSlPdLot.setBrand(sc182211Bean.getBrandName());
                dsSlPdLot.setPkgSpec(sc182211Bean.getNormsOut());
                Date currentDate = DateTimeUtil.getCustomerDate();
                dsSlPdLot.setCrtId(sc182211Bean.getCrtId());
                dsSlPdLot.setUpdId(sc182211Bean.getCrtId());
                dsSlPdLot.setUpdTime(currentDate);
                dsSlPdLot.setCrtTime(new Date());
                dsSlPdLot.setDelFlg("0");
                dsSlPdLot.setVer(NumberConst.IntDef.INT_ONE);
                /**查询产品产地信息通过货号*/
                SC182211Bean artBean = new SC182211Bean();
                artBean.setSlPdArtno(sc182211Bean.getSlPdArtno());
                SlPdArtnoBean getSLPArtNoParam = new SlPdArtnoBean();
                getSLPArtNoParam.setSlCode(sc182211Bean.getSlCode());
                getSLPArtNoParam.setBreedCode(sc182211Bean.getPdBreedCode());
                getSLPArtNoParam.setClassesCode(sc182211Bean.getPdClassesCode());
                getSLPArtNoParam.setFeatureCode(sc182211Bean.getPdFeatureCode());
                getSLPArtNoParam.setWeightCode(sc182211Bean.getWeightCode());
                getSLPArtNoParam.setSalesPlatform(sc182211Bean.getSalePlatform()+"");
                getSLPArtNoParam.setGradeCode(sc182211Bean.getGroupCode());
                getSLPArtNoParam.setMachiningCode(sc182211Bean.getMachiningCode());
                getSLPArtNoParam = RestUtil.getSlPdArtNo(getSLPArtNoParam);

                if(null != getSLPArtNoParam){
                    dsSlPdLot.setPdStatus(getSLPArtNoParam.getSaleStatus());
                    dsSlPdLot.setStorageWay(getSLPArtNoParam.getStorageCondition());
                    dsSlPdLot.setOrigin(getSLPArtNoParam.getPdCountry());
                    dsSlPdLot.setProdcingarea(getSLPArtNoParam.getPdPlace());
                    dsSlPdLot.setShelfLife(getSLPArtNoParam.getShelfLife());
                }
                super.save(SqlId.SQL_ID_FIND_SAVEDSSLPDLOT,dsSlPdLot);
            }else{
                lotId=dsSlPdLotOne.getLotId();
            }
            int count=this.dataExisted(sc182211Bean);
            if(0<count){
                throw new BusinessException("流水号已经存在，请查询上次批次号后重新设置流水号!");
            }
            for (int i = number; i <=numberTwo; i++) {
                dsPrintTask.setTableId(commonLogic.maxId("DS_PRINT_TASK","TABLE_ID"));
                SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                String aa=sim.format(new Date());
                Date date= DateTimeUtil.parseDate(aa, "yyyy-MM-dd HH-mm-ss");
                dsPrintTask.setPrintTaskId(StringUtil.toSafeString(date.getTime()));
                DecimalFormat df = new DecimalFormat("00000");
                final String numberData=df.format(i);
              /*  String numberData="00001";*/
                //生产商编码
                String epIdCode=sc182211Bean.getProdEpId().substring(sc182211Bean.getProdEpId().length()-NumberConst.IntDef.INT_SIX,sc182211Bean.getProdEpId().length());
                String mainClass=sc182211Bean.getSlCodeDis().substring(NumberConst.IntDef.INT_ZERO,NumberConst.IntDef.INT_ONE);
                String mainClassOther=sc182211Bean.getSlCodeDis().substring(NumberConst.IntDef.INT_ONE,NumberConst.IntDef.INT_SEVEN);
                 //组成阅读码
                String readBar="";
                //自产商  1     代理商  2    OEM商  3
                if("1".equals(mainClass)){
                    readBar=sc182211Bean.getPdClassesCode()+sc182211Bean.getMachiningCode()+sc182211Bean.getPdBreedCode()+sc182211Bean.getPdFeatureCode()+sc182211Bean.getGroupCode()+
                            sc182211Bean.getWeightCode()+sc182211Bean.getNormsCode()+mainClass+sc182211Bean.getBrandClass()+mainClassOther+epIdCode+sc182211Bean.getBrandId()+sc182211Bean.getPriceDate()+sc182211Bean.getHalf()+numberData;
                }else if("2".equals(mainClass)){
                    readBar=sc182211Bean.getPdClassesCode()+sc182211Bean.getMachiningCode()+sc182211Bean.getPdBreedCode()+sc182211Bean.getPdFeatureCode()+sc182211Bean.getGroupCode()+
                            sc182211Bean.getWeightCode()+sc182211Bean.getNormsCode()+mainClass+sc182211Bean.getBrandClass()+mainClassOther+epIdCode+sc182211Bean.getBrandId()+sc182211Bean.getPriceDate()+sc182211Bean.getHalf()+numberData;
                }else if("3".equals(mainClass)){
                    readBar=sc182211Bean.getPdClassesCode()+sc182211Bean.getMachiningCode()+sc182211Bean.getPdBreedCode()+sc182211Bean.getPdFeatureCode()+sc182211Bean.getGroupCode()+
                            sc182211Bean.getWeightCode()+sc182211Bean.getNormsCode()+mainClass+sc182211Bean.getBrandClass()+mainClassOther+sc182211Bean.getBrandId()+epIdCode+sc182211Bean.getPriceDate()+sc182211Bean.getHalf()+numberData;
                }
                //组成操作码
                String operateBar="92"+sc182211Bean.getSlCodeDis()+sc182211Bean.getSlPdArtno()+sc182211Bean.getPriceDate()+sc182211Bean.getHalf()+numberData;

                dsPrintTask.setOperateBar(operateBar);
                dsPrintTask.setReadBar(readBar);
                dsPrintTask.setBrandName(sc182211Bean.getBrandName());
                dsPrintTask.setGradeName(sc182211Bean.getGroupName());
                /**新添加四个属性  应上海需求*/
                dsPrintTask.setProductName(sc182211Bean.getPdBreedName());
                dsPrintTask.setFeatureName(sc182211Bean.getPdFeatureName());
                dsPrintTask.setSuppName(sc182211Bean.getEpName());
                dsPrintTask.setNetWeight(sc182211Bean.getWeightName());
                Date currentDate = DateTimeUtil.getCustomerDate();
                dsPrintTask.setCrtId(sc182211Bean.getCrtId());
                dsPrintTask.setCrtTime(currentDate);
                dsPrintTask.setUpdTime(currentDate);
                dsPrintTask.setUpdId(sc182211Bean.getCrtId());
                dsPrintTask.setVer(NumberConst.IntDef.INT_ONE);
                dsPrintTask.setDelFlg("0");
                //插入批次标签打印任务表
                super.save(SqlId.SQL_ID_FIND_SAVEDATA, dsPrintTask);
                //保存根据批次号生成的流水号
                DsLotSerial dsLotSerial=new DsLotSerial();
                dsLotSerial.setLotId(lotId);
                dsLotSerial.setSerialId(numberData);
                dsLotSerial.setLotCode(operateBar);
                dsLotSerial.setReadCode(readBar);
                dsLotSerial.setCrtId(sc182211Bean.getCrtId());
                dsLotSerial.setCrtTime(currentDate);
                dsLotSerial.setUpdId(sc182211Bean.getCrtId());
                dsLotSerial.setUpdTime(currentDate);
                dsLotSerial.setDelFlg("0");
                dsLotSerial.setVer(NumberConst.IntDef.INT_ONE);
                super.save(SqlId.SQL_ID_FIND_SAVEDDSLOTSERIAL,dsLotSerial);

            }
        }
    }

    /**
     * 根据卖家产品查询货号
     * @return  生产商List
     */
    public List<SC182211Bean> findSlPdArtno(SC182211Bean sc182211Bean){
        SlPdArtnoBean param = new SlPdArtnoBean();
        param.setSlCode(sc182211Bean.getSlCode());
        param.setClassesCode(sc182211Bean.getPdClassesCode());
        param.setMachiningCode(sc182211Bean.getMachiningCode());
        param.setWeightCode(sc182211Bean.getWeightCode());
        param.setBreedCode(sc182211Bean.getPdBreedCode());
        param.setFeatureCode(sc182211Bean.getPdFeatureCode());
        param.setSalesPlatform(String.valueOf(sc182211Bean.getSalePlatform()));
        /**新加等级作为条件*/
        param.setGradeCode(sc182211Bean.getGroupCode());
        SlPdArtnoBean result = RestUtil.getSlPdArtNo(param);
        List<SC182211Bean> list = new ArrayList<SC182211Bean>();
        if (result != null && !StringUtil.isNullOrEmpty(result.getSlPdArtno())){
            SC182211Bean bean = new SC182211Bean();
            bean.setSlPdArtno(result.getSlPdArtno());
            list.add(bean);
        }
        return list;
    }

    /**
     * 标签所有数据
     * @return  生产商List
     */
    @Transactional
    public List<DsPrintTask> findDataExport(){
        BaseParam param=new BaseParam();
        return super.findList(SqlId.SQL_ID_FIND_DATAEXPORT,param);
    }

    /**
     * 删除所有数据
     * @return  生产商List
     */
    @Transactional
    public int dataDelete(){
        BaseParam param=new BaseParam();
        return super.remove(SqlId.SQL_ID_FIND_DELETE_DATA, param);
    }


    /**
     * 查询上次批次数据
     * @return 批次最大值和最小值
     */
    @Transactional(readOnly = true)
    public String dataSearch(SC182211Bean sc182211Bean){
        String code="";
        if(null!=sc182211Bean){
            code="92"+sc182211Bean.getSlCodeDis()+sc182211Bean.getSlPdArtno()+sc182211Bean.getPriceDate().replace("-", "")+sc182211Bean.getHalf();
        }
        BaseParam param=new BaseParam();
        //Modify start in 2016/9/7 by zhang_jiaxing
        param.setFilter("lotCode",code);
        DbUtils.buildLikeCondition(param, "lotCode", DbUtils.LikeMode.FRONT);
        //Modify end in 2016/9/7 bu zhang_jiaxing
        SC182211Bean sc182211BeanTwo=super.findOne(SqlId.SQL_ID_FIND_QUERYDATASEARCH, param);
        String returnData="";
        if(null!=sc182211BeanTwo){
            returnData="最小值为:"+sc182211BeanTwo.getMinNumber()+",最大值为:"+sc182211BeanTwo.getMaxNumber();
        }
        return returnData;
    }


    /**
     * 查询批次数据是否存在
     * @return 批次数据结果值
     */
    @Transactional(readOnly = true)
    public int dataExisted(SC182211Bean sc182211Bean){
        String code="";
        if(null!=sc182211Bean){
            code="92"+sc182211Bean.getSlCodeDis()+sc182211Bean.getSlPdArtno()+sc182211Bean.getPriceDate().replace("-", "")+sc182211Bean.getHalf()+sc182211Bean.getNumber();
        }
        BaseParam param=new BaseParam();
        param.setFilter("lotCode",code);
        int num=super.getCount(SqlId.SQL_ID_FIND_DATAEXISTED,param);
        return num;
    }

    /**
     * 查询批次数据是否存在
     * @return 批次数据结果值
     */
    @Transactional(readOnly = true)
    public DsSlPdLot dataExistedTwo(SC182211Bean sc182211Bean){
        BaseParam param=new BaseParam();
        if(null!=sc182211Bean){
            sc182211Bean.setDateCode(sc182211Bean.getPriceDate().replace("-", "")+sc182211Bean.getHalf());//设置批次号
        }
        param.getFilterMap().put("sc182211Bean", sc182211Bean);
        DsSlPdLot dsSlPdLot=super.findOne(SqlId.SQL_ID_FIND_DATAEXISTEDTWO,param);
        return dsSlPdLot;
    }

}
