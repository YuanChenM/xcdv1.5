package com.msk.batch.bs.logic;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.bs.bean.BBS1101101Bean;
import com.msk.batch.bs.bean.BBS1101102Bean;
import com.msk.batch.bs.bean.BBS1101102Param;
import com.msk.batch.bs.bean.BBS1101102Result;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SlHouseBuyerProduct;
import com.msk.core.entity.SlHouseGradeConst;

/**
 * Created by wang_haichun on 2016/09/18.
 * 业务处理类
 */
public class BBS1101102Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(BBS1101102Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SQL Map 中SQL ID定义
     */
    public interface SqlId {
        static final String SQL_ID_FIND_HOUSE_GRADE = "findHouseGrade";
        static final String SQL_ID_SAVE_GRADE_CODE_MONTH = "saveGradeCodeMonth";
        static final String SQL_ID_FIND_HOUSE_GRADE_CONST = "findHouseGradeConst";
        static final String SQL_ID_FIND_HOUSE_BUYER_PRODUCT = "findHouseBuyerProduct";
        static final String SQL_ID_UPDATE_GRADE_CODE_DAY = "updateGradeCodeDay";
        static final String SQL_ID_UPDATE_HOUSE_GRADE_STATUS = "updateHouseGradeStatus";
        static final String SQL_ID_UPDATE_HOUSE_GRADE_EXTENDTIME = "updateHouseGradeExtendTime";
        static final String SQL_ID_DELETE_OLD_HOUSE_GRADE = "deleteOldHouseGrade";
    }

    /**
     * 查询所有非见习的定级
     *
     * @return 结果
     * @author whc
     */
    @Transactional(readOnly = true)
    public List<BBS1101102Bean> findHouseGrade(BaseParam baseParam) {
        return this.findList(SqlId.SQL_ID_FIND_HOUSE_GRADE, baseParam);
    }

    /**
     * 查询常量表数据
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlHouseGradeConst> findHouseGradeConst(){
         return this.findList(SqlId.SQL_ID_FIND_HOUSE_GRADE_CONST,null);
    }

    //查询管家分类对应的产品分类
    @Transactional(readOnly = true)
    public List<SlHouseBuyerProduct> findHouseBuyerProduct(List<BBS1101102Bean> houseGradeList){
       Map<String,Object> map = new HashMap<>();
        map.put("houseGradeList",houseGradeList);
        return this.findList(map,SqlId.SQL_ID_FIND_HOUSE_BUYER_PRODUCT);
    }


    /**
     * 每月初始数据，非见习级冻品管家月初统一从中级起步
     *
     * @author whc
     */
    @Transactional
    public void monthInitHouseGrade() {
        //获取上个月日期
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        String beforeYearMonth = new SimpleDateFormat("yyyyMM").format(c.getTime());
        c.add(Calendar.MONTH, 1);
        String nowYearMonth = new SimpleDateFormat("yyyyMM").format(c.getTime());
        logger.info("执行"+nowYearMonth+"每月的batch开始");
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("flag","1");
        baseParam.setFilter("validYearMonth",beforeYearMonth);
        List<BBS1101102Bean> houseGradeList = this.findHouseGrade(baseParam);
//        Map<String,String> gradMap =  CodeMasterManager.findCodeMasterMap("HkGradeCode");
        if(!CollectionUtils.isEmpty(houseGradeList)){
            logger.info("修改等级为中级开始，共"+houseGradeList.size()+"条数据");
            int result = 0;
            //插入新数据  变更validYearMonth为当前月
            List<BBS1101102Bean> newHouseGradeList = new ArrayList<>();
            BBS1101102Bean newHouseGrade = null;
            for(BBS1101102Bean bbs1101102Bean : houseGradeList){
                newHouseGrade = new BBS1101102Bean();
                newHouseGrade.setGradeId(commonLogic.maxId("SL_HOUSE_GRADE", "GRADE_ID"));
                newHouseGrade.setSlCode(bbs1101102Bean.getSlCode());
                newHouseGrade.setHouseCode(bbs1101102Bean.getHouseCode());
                newHouseGrade.setLgcsAreaCode(bbs1101102Bean.getLgcsAreaCode());
                newHouseGrade.setLgcsAreaName(bbs1101102Bean.getLgcsAreaName());
                newHouseGrade.setHouseCategoryCode(bbs1101102Bean.getHouseCategoryCode());
                newHouseGrade.setHouseReclassifyCode(bbs1101102Bean.getHouseReclassifyCode());
                if(!StringUtil.isNullOrEmpty(bbs1101102Bean.getGradeCode()) && Integer.valueOf(bbs1101102Bean.getGradeCode()) <= 2){
                    newHouseGrade.setGradeCode("2");
                }else {
                    newHouseGrade.setGradeCode(bbs1101102Bean.getGradeCode());
                }
                newHouseGrade.setValidYearMonth(nowYearMonth);
                newHouseGrade.setEndTime(bbs1101102Bean.getEndTime());
                newHouseGrade.setExtendTime(bbs1101102Bean.getExtendTime());
                newHouseGrade.setStatus(bbs1101102Bean.getStatus());
                newHouseGrade.setRemark(bbs1101102Bean.getRemark());
                newHouseGrade.setDelFlg("0");
                newHouseGrade.setCrtId("batch");
                newHouseGrade.setCrtTime(bbs1101102Bean.getCrtTime());
                newHouseGrade.setUpdId("batch");
                newHouseGrade.setUpdTime(new Date());
                newHouseGrade.setActTime(new Date());
                newHouseGrade.setActId("batch");
                newHouseGrade.setVer(0);
                newHouseGradeList.add(newHouseGrade);
            }
            //修改状态 delFlag删除
            BaseParam baseParam1 = new BaseParam();
            Map<String,Object> map = new HashMap<>();
            map.put("houseGradeList",houseGradeList);
            baseParam1.setFilterMap(map);
            map.put("updTime",new Date());
            map.put("updId","batch");
            this.modify(SqlId.SQL_ID_DELETE_OLD_HOUSE_GRADE,baseParam1);


            if(!CollectionUtils.isEmpty(newHouseGradeList)){
                result += this.getBaseDao().batchInsert(SqlId.SQL_ID_SAVE_GRADE_CODE_MONTH,newHouseGradeList);
            }
            logger.info("修改等级为中级结束，共"+newHouseGradeList.size()+"条数据，成功修改"+result+"条");
        }
        logger.info("执行"+nowYearMonth+"每月的batch结束");
//        logger.info("继续执行每天的batch");
//        this.dayInitHouseGrade();
    }

    /**
     * 每天初始数据
     *
     * @author whc
     */
    @Transactional
    public void dayInitHouseGrade(){
        Calendar c = Calendar.getInstance();
        String nowYearMonth = new SimpleDateFormat("yyyyMM").format(c.getTime());
        logger.info("执行"+nowYearMonth+"每日的batch开始");

        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("validYearMonth",nowYearMonth);
        //查询见习期等级
        baseParam.setFilter("flag","2");
        List<BBS1101102Bean> novitiateHouseGradeList = this.findHouseGrade(baseParam);
        //取消资格LIST
        List<BBS1101102Bean> cancelList = new ArrayList<>();
        //设置展望期LIST
        List<BBS1101102Bean> settinglList = new ArrayList<>();
        //判断见习期的有效期
        if(!CollectionUtils.isEmpty(novitiateHouseGradeList)){
            for(BBS1101102Bean novitiateHouseGrade : novitiateHouseGradeList){
                Date crtTime = novitiateHouseGrade.getCrtTime();
                Calendar calender = Calendar.getInstance();
                calender.setTime(crtTime);
                calender.add(Calendar.MONTH, 1);
                int oneMonthResult = compare(new Date(),calender.getTime());
                //超过一个月
                if (oneMonthResult > 0) {
                    calender.add(Calendar.MONTH, 1);
                    int towMonthResult = compare(new Date(),calender.getTime());
                    //超过两个月
                    if(towMonthResult > 0){
                        //取消资格
                        novitiateHouseGrade.setStatus("9");
                        novitiateHouseGrade.setUpdId("batch");
                        novitiateHouseGrade.setUpdTime(new Date());
                        cancelList.add(novitiateHouseGrade);
                    }else {
                        //设置一个月展望期时间
                        //判断是否设置过展望期
                        if(null == novitiateHouseGrade.getExtendTime()){
                            novitiateHouseGrade.setExtendTime(calender.getTime());
                            novitiateHouseGrade.setUpdId("batch");
                            novitiateHouseGrade.setUpdTime(new Date());
                            settinglList.add(novitiateHouseGrade);
                        }
                    }
                }
            }
        }
        if(!CollectionUtils.isEmpty(cancelList)){
            //更新设置取消
            Map<String,Object> map = new HashMap<>();
            BaseParam baseParam1 = new BaseParam();
            map.put("cancelList",cancelList);
            map.put("updTime",new Date());
            map.put("updId","batch");
            baseParam1.setFilterMap(map);
            int result = this.modify(SqlId.SQL_ID_UPDATE_HOUSE_GRADE_STATUS,baseParam1);
            logger.info("取消冻品管家等级有"+cancelList.size()+"条，成功返回"+result+"条");
        }
        if(!CollectionUtils.isEmpty(settinglList)){
            //更新设置展望期
            int result = 0;
            for (BBS1101102Bean houseGrade:settinglList) {
                result += this.modify(SqlId.SQL_ID_UPDATE_HOUSE_GRADE_EXTENDTIME,houseGrade);
            }
            logger.info("更新定级展望期"+settinglList.size()+"条，成功返回"+result+"条");
        }
        //所有的等级数据   当月有效等级
        baseParam.setFilter("flag","0");
        List<BBS1101102Bean> houseGradeList = this.findHouseGrade(baseParam);
//        Map<String,String> gradMap =  CodeMasterManager.findCodeMasterMap("HkGradeCode");
        //查询常量表数据
        List<SlHouseGradeConst> gradeConstList = this.findHouseGradeConst();

        if(!CollectionUtils.isEmpty(houseGradeList)){
            //等级里管家分类对应的产品分类
            List<SlHouseBuyerProduct> houseBuyerProductList = this.findHouseBuyerProduct(houseGradeList);
            if(!CollectionUtils.isEmpty(houseBuyerProductList)){
                for(int i=0;i<houseGradeList.size();i++){
                    for(SlHouseBuyerProduct houseBuyerProduct : houseBuyerProductList){
                        if(houseGradeList.get(i).getHouseReclassifyCode().equals(houseBuyerProduct.getHouseReclassifyCode())){
                            //设置产品一级编码
                            houseGradeList.get(i).setPdClassCode(houseBuyerProduct.getByPdClassesCode());
                        }
                    }
                }
            }
        }

        String saleMonth = new SimpleDateFormat("yyyy-MM").format(c.getTime());
        //接口查询管家的销量
        RsRequest<BBS1101102Param> requestParam = new RsRequest<BBS1101102Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        BBS1101102Param bbs1101102Param = new BBS1101102Param();
        List<BBS1101101Bean> houseAccountList = new ArrayList<>();
        BBS1101101Bean bbs1101101Bean = null;
        if(!CollectionUtils.isEmpty(houseGradeList)){
            for(BBS1101102Bean bbs1101102Bean : houseGradeList){
                bbs1101101Bean = new BBS1101101Bean();
                bbs1101101Bean.setHouseCode(bbs1101102Bean.getHouseCode());
                bbs1101101Bean.setSaleMonth(saleMonth);
                bbs1101101Bean.setLgcsAreaCode(bbs1101102Bean.getLgcsAreaCode());
                bbs1101101Bean.setPdClassCode(bbs1101102Bean.getPdClassCode());
                houseAccountList.add(bbs1101101Bean);
            }
        }
        bbs1101102Param.setHouseAccountList(houseAccountList);
        requestParam.setParam(bbs1101102Param);
        String url = SystemServerManager.SoOrderServerManage.getSearchSales();
        if(StringUtil.isNullOrEmpty(url)){
            return;
        }
        RsResponse<BBS1101102Result> response = RestClientUtil.post(url, requestParam,
                new TypeReference<RsResponse<BBS1101102Result>>() {
                });

        //接受的数据
        List<BBS1101101Bean> salesList = new ArrayList<>();
        if(null != response && null != response.getResult()){
            salesList = response.getResult().getSalesList();
            logger.info("接口返回"+salesList.size()+"条数据");
        }

        if(!CollectionUtils.isEmpty(salesList) && !CollectionUtils.isEmpty(gradeConstList)){
            List<BBS1101102Bean> modifyHouseGradeList = new ArrayList<>();
            for(BBS1101102Bean houseGrade : houseGradeList){
                for(BBS1101101Bean result : salesList){
                    //判断管家
                    if(!StringUtil.isNullOrEmpty(houseGrade.getHouseCode()) && houseGrade.getHouseCode().equals(result.getHouseCode())
                            && !StringUtil.isNullOrEmpty(houseGrade.getLgcsAreaCode()) && houseGrade.getLgcsAreaCode().equals(result.getLgcsAreaCode())
                            && !StringUtil.isNullOrEmpty(houseGrade.getPdClassCode()) && houseGrade.getPdClassCode().equals(result.getPdClassCode())){
                        if(!StringUtil.isNullOrEmpty(result.getSales())){
                            BigDecimal sales = new BigDecimal(result.getSales());
                            Integer grade = Integer.MAX_VALUE;
                            for(SlHouseGradeConst houseGradeConst : gradeConstList){
                                //根据常量表  判断标准
                                //获取当前等级的标准
                                Integer gradeCode = Integer.valueOf(houseGradeConst.getGradeCode());
                                if(null == gradeCode){
                                    gradeCode = Integer.MAX_VALUE;
                                }
                                //最小标准
                                BigDecimal saleMin = new BigDecimal(houseGradeConst.getSaleMin());
                                // >=
                                if(sales.compareTo(saleMin) == 0 || sales.compareTo(saleMin) == 1){
                                    if(gradeCode.intValue() < grade.intValue()){
                                        grade = gradeCode;
                                    }
                                }
                            }
                            //设置新等级
                            if(grade != Integer.MAX_VALUE && grade.intValue() < Integer.valueOf(houseGrade.getGradeCode()).intValue()){
                                houseGrade.setGradeCode(String.valueOf(grade));
                                modifyHouseGradeList.add(houseGrade);
                            }
                        }
                    }
                }
            }
            //修改数据
            if(!CollectionUtils.isEmpty(modifyHouseGradeList)){
                logger.info("修改等级开始,共"+modifyHouseGradeList.size()+"条数据");
                int result = 0;
                for(BBS1101102Bean bbs1101102Bean : modifyHouseGradeList){
                    bbs1101102Bean.setUpdTime(new Date());
                    bbs1101102Bean.setUpdId("batch");
                    result += this.modify(SqlId.SQL_ID_UPDATE_GRADE_CODE_DAY,bbs1101102Bean);
                }
                logger.info("修改等级开始,共"+modifyHouseGradeList.size()+"条数据，成功修改"+result+"条");
            }
        }
        logger.info("执行"+nowYearMonth+"每日的batch结束");
    }

    @Transactional
    public void init(){
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        if(day == 1){
            this.monthInitHouseGrade();
        }else {
            this.dayInitHouseGrade();
        }
    }



    public static int compare(Date d1, Date d2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
        String str1 = sdf.format(d1);
        String str2 = sdf.format(d2);

        int result = str1.compareTo(str2);
        if (result > 0) {
            System.out.println(str1 + " 晚于 " + str2);
        } else if (result == 0) {
            System.out.println(str1 + " 等于 " + str2);
        } else {
            System.out.println(str1 + " 早于 " + str2);
        }
        return result;
    }



}
