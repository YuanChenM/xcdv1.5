package com.msk.bs.logic;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102111Bean;
import com.msk.bs.bean.BS2102111Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseGrade;
import com.msk.core.entity.SlHouseManage;

/**
 * 定级管理
 * Created by ren_qiang on 2016/8/18.
 */
@Service
public class BS2102111Logic  extends BaseLogic {

    private Logger logger = LoggerFactory.getLogger(BS2102111Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        String SQL_ID_FIND_HOUSE_PAGE_LIST = "findHousePageList";
        String SQL_ID_FIND_HOUSE_ACCOUNT = "findHouseAccount";
        String SQL_ID_FIND_HOUSE_MANAGE = "findHouseManage";
        String SQL_ID_FIND_HOUSE_GRADE = "findHouseGrade";
        String SQL_ID_UPDATE_HOUSE_GRADE = "modifyHouseGrade";
    }

    //查询所有的分类信息和定级信息
    @Transactional()
    public void findHouseManageAndGrade(BaseParam param){
        //查询冻品管家信息
        SlHouseAccount houseAccount = this.findOne(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT,param);
        if(houseAccount != null){
            //查询冻品管家所有分类信息
            param.setFilterObject("houseAccount",houseAccount);
            List<SlHouseManage> houseManageList = this.findList(SqlId.SQL_ID_FIND_HOUSE_MANAGE,param);
            //查询当前月份(包含注销的)冻品管家定级数据(该冻品管家现在的物流区下的数据)
            param.setFilter("validYearMonth",getTimeStr());
            param.setFilterObject("flagStatus",false);
            List<SlHouseGrade> houseGradeList = this.findList(SqlId.SQL_ID_FIND_HOUSE_GRADE,param);
            //对比数据
            List<SlHouseGrade> saveHouseGradeList = new ArrayList<>();
            if(!CollectionUtils.isEmpty(houseManageList)){
                if(!CollectionUtils.isEmpty(houseGradeList)){
                    for(SlHouseManage houseManage : houseManageList){
                        boolean flag = true;
                        for(SlHouseGrade houseGrade : houseGradeList){
                            if(houseManage.getHouseCategoryCode().equals(houseGrade.getHouseCategoryCode())
                                    && houseManage.getHouseReclassifyCode().equals(houseGrade.getHouseReclassifyCode())){
                                flag = false;
                            }
                        }
                        //没有找到对应的定级数据
                        if(flag){
                            SlHouseGrade slHouseGrade = this.setHouseGrade(houseManage,param);
                            saveHouseGradeList.add(slHouseGrade);
                        }
                    }
                }else {
                    Map<String,SlHouseManage> tempMap = new HashMap<>();
                    for(SlHouseManage hm : houseManageList){
                        String key = hm.getLgcsAreaCode()+"_" + hm.getHouseCategoryCode()+"_" + hm.getHouseReclassifyCode();
                        if(!tempMap.containsKey(key)){
                            tempMap.put(key,hm);
                            SlHouseGrade slHouseGrade = this.setHouseGrade(hm,param);
                            saveHouseGradeList.add(slHouseGrade);
                        }
                    }
                }
            }
            //插入定级数据
            if(!CollectionUtils.isEmpty(saveHouseGradeList)){
                //查询上个月的数据  假如上个月有数据的话  判断等级
                param.setFilter("validYearMonth",getLastDate());
                param.setFilterObject("flagStatus",false);
                List<SlHouseGrade> lastHouseGradeList = this.findList(SqlId.SQL_ID_FIND_HOUSE_GRADE,param);
                if(!CollectionUtils.isEmpty(lastHouseGradeList)){
                    Iterator<SlHouseGrade> iterator = lastHouseGradeList.iterator();
                    while(iterator.hasNext()){
                        SlHouseGrade houseGrade = iterator.next();
                        for(int i=saveHouseGradeList.size() -1;i>=0;i--){
                            if(houseGrade.getHouseCategoryCode().equals(saveHouseGradeList.get(i).getHouseCategoryCode())
                                    && houseGrade.getHouseReclassifyCode().equals(saveHouseGradeList.get(i).getHouseReclassifyCode())){
                                if(!StringUtils.isEmpty(houseGrade.getGradeCode()) && Integer.valueOf(houseGrade.getGradeCode()) <= 2){
                                    saveHouseGradeList.get(i).setGradeCode("2");
                                }
                                if(!StringUtils.isEmpty(houseGrade.getStatus()) && "9".equals(houseGrade.getStatus())){
                                    saveHouseGradeList.remove(i);
                                    iterator.remove();
                                }
                            }
                        }
                    }
                    //上个月数据delFlag改为1
                    logger.info("查询修改"+getLastDate()+"数据有"+lastHouseGradeList.size()+"条定级--begin");
                    int modifyResult = 0;
                    if(lastHouseGradeList.size() > 0){
                        modifyResult += this.modify(SqlId.SQL_ID_UPDATE_HOUSE_GRADE,param);
                    }
                    logger.info("修改成功"+modifyResult+"条数据--end");
                }

                logger.info("查询需要插入"+saveHouseGradeList.size()+"条定级");
                if(!CollectionUtils.isEmpty(saveHouseGradeList)){
                    Iterator<SlHouseGrade> iterator = saveHouseGradeList.iterator();
                    int num = 0;
                    while (iterator.hasNext()){
                        SlHouseGrade houseGrade = iterator.next();
                        if(!StringUtils.isEmpty(houseGrade.getStatus()) && "9".equals(houseGrade.getStatus())){
                            num++;
                            iterator.remove();
                        }
                    }
                    logger.info("去除注销状态："+num+"条数据");
                    int result = super.batchSave(saveHouseGradeList);
                    logger.info("成功插入"+result+"条数据");
                }
            }
        }
    }


    private SlHouseGrade setHouseGrade(SlHouseManage houseManage,BaseParam param){
        SlHouseGrade slHouseGrade = new SlHouseGrade();
        slHouseGrade.setGradeId(commonLogic.maxId("SL_HOUSE_GRADE","GRADE_ID"));
        slHouseGrade.setSlCode(houseManage.getSlCode());
        slHouseGrade.setHouseCode(houseManage.getHouseCode());
        slHouseGrade.setLgcsAreaCode(houseManage.getLgcsAreaCode());
        slHouseGrade.setLgcsAreaName(houseManage.getLgcsAreaName());
        slHouseGrade.setHouseCategoryCode(houseManage.getHouseCategoryCode());
        slHouseGrade.setHouseReclassifyCode(houseManage.getHouseReclassifyCode());
        slHouseGrade.setGradeCode("3");
        slHouseGrade.setValidYearMonth(this.getTimeStr());
        slHouseGrade.setEndTime(nextMonth());
        slHouseGrade.setStatus("0");
        slHouseGrade.setCrtId(param.getCrtId());
        slHouseGrade.setCrtTime(new java.util.Date());
        slHouseGrade.setUpdId(param.getUpdId());
        slHouseGrade.setUpdTime(new java.util.Date());
        slHouseGrade.setActId(param.getActId());
        slHouseGrade.setActTime(new java.util.Date());

       return slHouseGrade;
    }


    @Transactional(readOnly = true)
    public PageResult<BS2102111Bean> findHousePageList(BS2102111Param param){
        param.setValidYearMonth(getTimeStr());
        PageResult<BS2102111Bean> pageResult = new PageResult<BS2102111Bean>();
        pageResult = this.findPage(param, BS2102111Bean.class);
        if(pageResult != null && !CollectionUtils.isEmpty(pageResult.getData())){
            return pageResult;
        }
        else{
            pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            pageResult.setData(new ArrayList<BS2102111Bean>());
        }
        return pageResult;
    }

    @Transactional
    public Integer insertHouseGrade(Collection<BS2102111Bean> beans,BS2102111Param param){
        List<BS2102111Bean> list = new ArrayList<>();
        List<BS2102111Bean> list2 = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, +1);
        java.util.Date nextDate = cal.getTime();
        Integer cot = null ;
        for(BS2102111Bean bean : beans){
            String validYearMonth = getTimeStr();
            //如果管家等级为见习，则设置有效截止日期 当前日期+30
            if(StringUtils.hasLength(bean.getGradeCode())){
                if("3".equals(bean.getGradeCode())){
                    bean.setEndTime(nextDate);
                    bean.setStatus("0");
                }
                else {
                    bean.setStatus("1");
                    bean.setEndTime(null);
                }
            }
            else {
                bean.setEndTime(null);
            }

            /*param.setSlCode(bean.getSlCode());
            param.setHouseCode(bean.getHouseCode());
            param.setHouseCategoryCode(bean.getHouseCategoryCode());
            param.setHouseReclassifyCode(bean.getHouseReclassifyCode());
            param.setValidYearMonth(bean.getValidYearMonth());
            param.setLgcsAreaCode(bean.getLgcsAreaCode());*/
            param.setGradeId(bean.getGradeId());
            BS2102111Bean bs2102111Bean = this.findOne(param);
            if(bs2102111Bean != null){
                /*String houseCategoryCode1 = "";
                String houseCategoryCode2 = "";
                String houseReclassifyCode1 = "";
                String houseReclassifyCode2 = "";
                String lgcsAreaCode1 = "";
                String lgcsAreaCode2 = "";

                if(StringUtils.hasLength(bs2102111Bean.getHouseCategoryCode())){
                    houseCategoryCode1 = bs2102111Bean.getHouseCategoryCode();
                }
                if(StringUtils.hasLength(bean.getHouseCategoryCode())){
                    houseCategoryCode2 = bean.getHouseCategoryCode();
                }
                if(StringUtils.hasLength(bs2102111Bean.getHouseReclassifyCode())){
                    houseReclassifyCode1 = bs2102111Bean.getHouseReclassifyCode();
                }
                if(StringUtils.hasLength(bean.getHouseReclassifyCode())){
                    houseReclassifyCode2 = bean.getHouseReclassifyCode();
                }
                if(StringUtils.hasLength(bs2102111Bean.getLgcsAreaCode())){
                    lgcsAreaCode1 = bs2102111Bean.getLgcsAreaCode();
                }
                if(StringUtils.hasLength(bean.getLgcsAreaCode())){
                    lgcsAreaCode2 = bean.getLgcsAreaCode();
                }
                if(bs2102111Bean.getSlCode().equals(bean.getSlCode()) && bs2102111Bean.getHouseCode().equals(bean.getHouseCode())
                       && houseReclassifyCode1.equals(houseReclassifyCode2)
                                && bs2102111Bean.getValidYearMonth().equals(bean.getValidYearMonth())
                                  &&houseCategoryCode1.equals(houseCategoryCode2) && lgcsAreaCode1.equals(lgcsAreaCode2)
                        ){
                    if(!bs2102111Bean.getGradeCode().equals(bean.getGradeCode())){
                        bean.setValidYearMonth(validYearMonth);
                        bean.setUpdId(param.getUpdId());
                        bean.setUpdTime(param.getUpdTime());
                        bean.setActId(param.getActId());
                        bean.setActTime(param.getActTime());
                        cot = this.modify(bean);
                        if(cot == 0){
                            throw new BusinessException("修改失败！");
                        }
                    }

                }*/
                bean.setValidYearMonth(validYearMonth);
                bean.setUpdId(param.getUpdId());
                bean.setUpdTime(param.getUpdTime());
                cot = this.modify(bean);
                if(cot == 0){
                    throw new BusinessException("修改失败！");
                }
            }else{
                long gradeId =  commonLogic.maxId("SL_HOUSE_GRADE", "GRADE_ID");
                bean.setGradeId(gradeId);
                bean.setCrtId(param.getCrtId());
                bean.setCrtTime(param.getCrtTime());
                bean.setUpdId(param.getUpdId());
                bean.setUpdTime(param.getUpdTime());
                bean.setActId(param.getActId());
                bean.setActTime(param.getActTime());
                bean.setValidYearMonth(validYearMonth);
                list.add(bean);
            }

        }

        if(!CollectionUtils.isEmpty(list)){
            cot = super.batchSave(list);
        }
        return cot;
    }

    private String getTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String str = sdf.format(new java.util.Date());
        return  str;
    }

    private java.util.Date nextMonth(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        java.util.Date nextDate = cal.getTime();
        return nextDate;
    }

    private String getLastDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String str = sdf.format(cal.getTime());
        return  str;
    }
}
