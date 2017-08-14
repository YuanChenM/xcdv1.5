package com.msk.bs.logic;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102113Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;

/**
 * Created by wang_haichun on 2016/8/19.
 */
@Service
public class BS2102113Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BS2102115Logic bs2102115Logic;
    @Autowired
    private BS2102116Logic bs2102116Logic;
    @Autowired
    private BS2102117Logic bs2102117Logic;
    @Autowired
    private BS2101107Logic bs2101107Logic;

    interface SqlId {
        static final String SQL_ID_FIND_HOUSEWORK = "findHouseWork";
        static final String SQL_ID_FIND_HOUSEEDU = "findHouseEdu";
        static final String SQL_ID_FIND_HOUSETRAIN = "findHouseTrain";

        static final String SQL_ID_SAVE_HOUSEWORK = "saveHouseWork";
        static final String SQL_ID_SAVE_HOUSEWORK_HIS = "saveHouseWorkHis";

        static final String SQL_ID_SAVE_HOUSEEDU = "saveHouseEdu";
        static final String SQL_ID_SAVE_HOUSEEDU_HIS = "saveHouseEduHis";

        static final String SQL_ID_SAVE_HOUSETRAIN = "saveHouseTrain";
        static final String SQL_ID_SAVE_HOUSETRAIN_HIS = "saveHouseTrainHis";

        static final String SQL_ID_SAVE_HOUSEINTRODUCE = "saveIntroduceInfoByHouseCode";
        static final String SQL_ID_UPDATE_HOUSEINTRODUCE = "updateIntroduceInfoByHouseCode";
    }

    /**
     * 新增个人简历
     *
     * @param bs2102113Param
     * @param baseParam
     * @return
     */
    @Transactional
    public int saveResume(BS2102113Param bs2102113Param, BaseParam baseParam) {

        List<SlHouseWork> houseWorkList = bs2102113Param.getHouseWorkList();
        List<SlHouseEducation> houseEduList = bs2102113Param.getHouseEduList();
        List<SlHouseTraining> houseTrainList = bs2102113Param.getHouseTrainList();

        int result = 0;
        if (!CollectionUtils.isEmpty(houseWorkList)) {
            List<SlHouseWork> workList = new ArrayList<>();
            //工作经历履历
            List<SlHouseWorkHis> workHisList = new ArrayList<>();

            for (int i = 0; i < houseWorkList.size(); i++) {
                if (null != houseWorkList.get(i).getWorkId()) {
                    houseWorkList.get(i).setUpdId(baseParam.getUpdId());
                    houseWorkList.get(i).setUpdTime(new Date());
                    //保存工作经历履历
                    //查询原来的数据
                    SlHouseWork slHouseWork = bs2102115Logic.findWorkById(houseWorkList.get(i).getWorkId());
                    SlHouseWorkHis slHouseWorkHis = setWorkHis(slHouseWork, baseParam);
                    slHouseWorkHis.setActFlg("1");
                    workHisList.add(slHouseWorkHis);
                    //更新数据
                    result += bs2102115Logic.modifyWork(houseWorkList.get(i));
                } else {
                    houseWorkList.get(i).setWorkId(commonLogic.maxId("SL_HOUSE_WORK", "WORK_ID"));
                    houseWorkList.get(i).setDelFlg("0");
                    houseWorkList.get(i).setCrtId(baseParam.getCrtId());
                    houseWorkList.get(i).setCrtTime(new Date());
                    houseWorkList.get(i).setUpdId(baseParam.getUpdId());
                    houseWorkList.get(i).setUpdTime(new Date());
                    houseWorkList.get(i).setActTime(new Date());
                    houseWorkList.get(i).setActId(baseParam.getActId());
                    houseWorkList.get(i).setVer(0);
                    workList.add(houseWorkList.get(i));
                    //保存工作经历履历
                    SlHouseWorkHis slHouseWorkHis = setWorkHis(houseWorkList.get(i), baseParam);
                    workHisList.add(slHouseWorkHis);
                }
            }
            if (!CollectionUtils.isEmpty(workList) && workList.size() > 0) {
                result += super.getBaseDao().batchInsert(SqlId.SQL_ID_SAVE_HOUSEWORK, workList);
            }
            if (!CollectionUtils.isEmpty(workHisList) && workHisList.size() > 0) {
                result += saveWorkHis(workHisList);
            }
        }
        if (!CollectionUtils.isEmpty(houseEduList)) {
            List<SlHouseEducation> eduList = new ArrayList<>();

            //教育经历履历
            List<SlHouseEducationHis> eduHisList = new ArrayList<>();

            for (int i = 0; i < houseEduList.size(); i++) {
                if (null != houseEduList.get(i).getEduId()) {
                    houseEduList.get(i).setUpdId(baseParam.getUpdId());
                    houseEduList.get(i).setUpdTime(new Date());
                    //保存教育经历履历
                    //查询原来的数据
                    SlHouseEducation slHouseEducation = bs2102116Logic.findEduById(houseEduList.get(i).getEduId());
                    SlHouseEducationHis slHouseEducationHis = setEduHis(slHouseEducation, baseParam);
                    slHouseEducationHis.setActFlg("1");
                    eduHisList.add(slHouseEducationHis);
                    //更新数据
                    result += bs2102116Logic.modifyEdu(houseEduList.get(i));
                } else {
                    houseEduList.get(i).setEduId(commonLogic.maxId("SL_HOUSE_EDUCATION", "EDU_ID"));
                    houseEduList.get(i).setDelFlg("0");
                    houseEduList.get(i).setCrtId(baseParam.getCrtId());
                    houseEduList.get(i).setCrtTime(new Date());
                    houseEduList.get(i).setUpdId(baseParam.getUpdId());
                    houseEduList.get(i).setUpdTime(new Date());
                    houseEduList.get(i).setActTime(new Date());
                    houseEduList.get(i).setActId(baseParam.getActId());
                    houseEduList.get(i).setVer(0);
                    eduList.add(houseEduList.get(i));
                    //保存教育经历履历
                    SlHouseEducationHis slHouseEducationHis = setEduHis(houseEduList.get(i), baseParam);
                    eduHisList.add(slHouseEducationHis);
                }
            }
            if (!CollectionUtils.isEmpty(eduList) && eduList.size() > 0) {
                result += super.getBaseDao().batchInsert(SqlId.SQL_ID_SAVE_HOUSEEDU, eduList);
            }
            if (!CollectionUtils.isEmpty(eduHisList) && eduHisList.size() > 0) {
                result += saveEduHis(eduHisList);
            }
        }
        if (!CollectionUtils.isEmpty(houseTrainList)) {
            List<SlHouseTraining> trainList = new ArrayList<>();

            //培训经历履历
            List<SlHouseTrainingHis> trainHisList = new ArrayList<>();

            for (int i = 0; i < houseTrainList.size(); i++) {
                if (null != houseTrainList.get(i).getTrainId()) {
                    houseTrainList.get(i).setUpdId(baseParam.getUpdId());
                    houseTrainList.get(i).setUpdTime(new Date());
                    //保存培训经历履历
                    //查询原来的数据
                    SlHouseTraining slHouseTraining = bs2102117Logic.findTrainById(houseTrainList.get(i).getTrainId());
                    SlHouseTrainingHis slHouseTrainingHis = setTrainHis(slHouseTraining, baseParam);
                    slHouseTrainingHis.setActFlg("1");
                    trainHisList.add(slHouseTrainingHis);
                    //更新数据
                    result += bs2102117Logic.modifyTrain(houseTrainList.get(i));
                } else {
                    houseTrainList.get(i).setTrainId(commonLogic.maxId("SL_HOUSE_TRAINING", "TRAIN_ID"));
                    houseTrainList.get(i).setDelFlg("0");
                    houseTrainList.get(i).setCrtId(baseParam.getCrtId());
                    houseTrainList.get(i).setCrtTime(new Date());
                    houseTrainList.get(i).setUpdId(baseParam.getUpdId());
                    houseTrainList.get(i).setUpdTime(new Date());
                    houseTrainList.get(i).setActTime(new Date());
                    houseTrainList.get(i).setActId(baseParam.getActId());
                    houseTrainList.get(i).setVer(0);
                    trainList.add(houseTrainList.get(i));
                    //保存培训经历履历
                    SlHouseTrainingHis slHouseTrainingHis = setTrainHis(houseTrainList.get(i), baseParam);
                    trainHisList.add(slHouseTrainingHis);
                }
            }
            if (!CollectionUtils.isEmpty(trainList) && trainList.size() > 0) {
                result += super.getBaseDao().batchInsert(SqlId.SQL_ID_SAVE_HOUSETRAIN, trainList);
            }
            if (!CollectionUtils.isEmpty(trainHisList) && trainHisList.size() > 0) {
                result += saveTrainHis(trainHisList);
            }
        }
        //更新管家信息
        if (bs2102113Param.getHouseIntroduce() != null) {
            SlHouseIntroduce slHouseInfo = bs2102113Param.getHouseIntroduce();
            SlHouseIntroduce slHouseIntroduce = bs2101107Logic.findIntroduceInfoByHouseCode(slHouseInfo);

            if (null != slHouseIntroduce) {
                // 更新自我介绍情报表——个人感情、服务承诺.
                slHouseIntroduce.setUpdId(baseParam.getUpdId());
                slHouseIntroduce.setUpdTime(new Date());
                slHouseIntroduce.setIntroduce(slHouseInfo.getIntroduce());
                slHouseIntroduce.setServiceCommit(slHouseInfo.getServiceCommit());
                slHouseIntroduce.setUploadUrl1(slHouseInfo.getUploadUrl1());
                slHouseIntroduce.setUploadUrl2(slHouseInfo.getUploadUrl2());
                result += super.modify(SqlId.SQL_ID_UPDATE_HOUSEINTRODUCE, slHouseIntroduce);
            } else {
                // 保存自我介绍——个人感情、服务承诺.
                slHouseInfo.setIntId(commonLogic.maxId("SL_HOUSE_INTRODUCE", "INT_ID"));
                slHouseInfo.setCrtId(baseParam.getCrtId());
                slHouseInfo.setCrtTime(new Date());
                slHouseInfo.setActTime(new Date());
                slHouseInfo.setActId(baseParam.getActId());
                slHouseInfo.setUpdId(baseParam.getUpdId());
                slHouseInfo.setUpdTime(new Date());
                slHouseInfo.setDelFlg("0");
                slHouseInfo.setVer(0);
                result += super.save(SqlId.SQL_ID_SAVE_HOUSEINTRODUCE, slHouseInfo);
            }

        }
        return result;
    }


    @Transactional(readOnly = true)
    public Map<String, Object> findResume(String slCode, String houseCode) {
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode", slCode);
        baseParam.setFilter("houseCode", houseCode);
        //查询工作经历
        List<SlHouseWork> houseWorkList = this.findList(SqlId.SQL_ID_FIND_HOUSEWORK, baseParam);
        //查询学习经历
        List<SlHouseEducation> houseEduList = this.findList(SqlId.SQL_ID_FIND_HOUSEEDU, baseParam);
        //查询培训经历
        List<SlHouseTraining> houseTrainList = this.findList(SqlId.SQL_ID_FIND_HOUSETRAIN, baseParam);

        Map<String, Object> map = new HashMap<>();
        map.put("work", houseWorkList);
        map.put("edu", houseEduList);
        map.put("train", houseTrainList);
        return map;
    }

    //保存工作履历
    @Transactional
    public int saveWorkHis(List<SlHouseWorkHis> workHisList) {
        if (!CollectionUtils.isEmpty(workHisList)) {
            return super.getBaseDao().batchInsert(SqlId.SQL_ID_SAVE_HOUSEWORK_HIS, workHisList);
        }
        return 0;
    }

    //保存教育履历
    @Transactional
    public int saveEduHis(List<SlHouseEducationHis> eduHisList) {
        if (!CollectionUtils.isEmpty(eduHisList)) {
            return super.getBaseDao().batchInsert(SqlId.SQL_ID_SAVE_HOUSEEDU_HIS, eduHisList);
        }
        return 0;
    }

    //保存培训履历
    @Transactional
    public int saveTrainHis(List<SlHouseTrainingHis> trainHisList) {
        if (!CollectionUtils.isEmpty(trainHisList)) {
            return super.getBaseDao().batchInsert(SqlId.SQL_ID_SAVE_HOUSETRAIN_HIS, trainHisList);
        }
        return 0;
    }

    private SlHouseWorkHis setWorkHis(SlHouseWork slHouseWork, BaseParam baseParam) {
        //保存工作经历履历
        SlHouseWorkHis slHouseWorkHis = new SlHouseWorkHis();
        slHouseWorkHis.setWorkHisId(commonLogic.maxId("SL_HOUSE_WORK_HIS", "WORK_HIS_ID"));
        slHouseWorkHis.setWorkId(slHouseWork.getWorkId());
        slHouseWorkHis.setSlCode(slHouseWork.getSlCode());
        slHouseWorkHis.setHouseCode(slHouseWork.getHouseCode());
        slHouseWorkHis.setWorkStart(slHouseWork.getWorkStart());
        slHouseWorkHis.setWorkEnd(slHouseWork.getWorkEnd());
        slHouseWorkHis.setWorkComp(slHouseWork.getWorkComp());
        slHouseWorkHis.setWorkPosition(slHouseWork.getWorkPosition());
        slHouseWorkHis.setWorkStation(slHouseWork.getWorkStation());
        slHouseWorkHis.setActFlg("0");
        slHouseWorkHis.setDelFlg("0");
        slHouseWorkHis.setCrtId(baseParam.getCrtId());
        slHouseWorkHis.setCrtTime(new Date());
        slHouseWorkHis.setUpdId(baseParam.getUpdId());
        slHouseWorkHis.setUpdTime(new Date());
        slHouseWorkHis.setActTime(new Date());
        slHouseWorkHis.setActId(baseParam.getActId());
        slHouseWorkHis.setVer(0);
        return slHouseWorkHis;
    }

    private SlHouseEducationHis setEduHis(SlHouseEducation slHouseEducation, BaseParam baseParam) {
        SlHouseEducationHis slHouseEducationHis = new SlHouseEducationHis();
        slHouseEducationHis.setEduHisId(commonLogic.maxId("SL_HOUSE_EDUCATION_HIS", "EDU_HIS_ID"));
        slHouseEducationHis.setEduId(slHouseEducation.getEduId());
        slHouseEducationHis.setSlCode(slHouseEducation.getSlCode());
        slHouseEducationHis.setHouseCode(slHouseEducation.getHouseCode());
        slHouseEducationHis.setEduStart(slHouseEducation.getEduStart());
        slHouseEducationHis.setEduEnd(slHouseEducation.getEduEnd());
        slHouseEducationHis.setEduComp(slHouseEducation.getEduComp());
        slHouseEducationHis.setEduRecord(slHouseEducation.getEduRecord());
        slHouseEducationHis.setEduDegree(slHouseEducation.getEduDegree());
        slHouseEducationHis.setActFlg("0");
        slHouseEducationHis.setDelFlg("0");
        slHouseEducationHis.setCrtId(baseParam.getCrtId());
        slHouseEducationHis.setCrtTime(new Date());
        slHouseEducationHis.setUpdId(baseParam.getUpdId());
        slHouseEducationHis.setUpdTime(new Date());
        slHouseEducationHis.setActTime(new Date());
        slHouseEducationHis.setActId(baseParam.getActId());
        slHouseEducationHis.setVer(0);
        return slHouseEducationHis;
    }

    private SlHouseTrainingHis setTrainHis(SlHouseTraining slHouseTraining, BaseParam baseParam) {
        SlHouseTrainingHis slHouseTrainingHis = new SlHouseTrainingHis();
        slHouseTrainingHis.setTrainHisId(commonLogic.maxId("SL_HOUSE_TRAINING_HIS", "TRAIN_HIS_ID"));
        slHouseTrainingHis.setTrainId(slHouseTraining.getTrainId());
        slHouseTrainingHis.setSlCode(slHouseTraining.getSlCode());
        slHouseTrainingHis.setHouseCode(slHouseTraining.getHouseCode());
        slHouseTrainingHis.setTrainStart(slHouseTraining.getTrainStart());
        slHouseTrainingHis.setTrainEnd(slHouseTraining.getTrainEnd());
        slHouseTrainingHis.setTrainComp(slHouseTraining.getTrainComp());
        slHouseTrainingHis.setTrainCertificate(slHouseTraining.getTrainCertificate());
        slHouseTrainingHis.setActFlg("0");
        slHouseTrainingHis.setDelFlg("0");
        slHouseTrainingHis.setCrtId(baseParam.getCrtId());
        slHouseTrainingHis.setCrtTime(new Date());
        slHouseTrainingHis.setUpdId(baseParam.getUpdId());
        slHouseTrainingHis.setUpdTime(new Date());
        slHouseTrainingHis.setActTime(new Date());
        slHouseTrainingHis.setActId(baseParam.getActId());
        slHouseTrainingHis.setVer(0);
        return slHouseTrainingHis;
    }
}
