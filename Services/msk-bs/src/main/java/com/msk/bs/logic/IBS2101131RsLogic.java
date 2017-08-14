package com.msk.bs.logic;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.*;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.server.SystemServerManager;
import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseEducationHis;
import com.msk.core.entity.SlHouseIntroduce;
import com.msk.core.entity.SlHouseWorkHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gao_min on 2016/10/12.
 */
@Service
public class IBS2101131RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId {
        // 查询管家code和管家名称.
        static String FIND_HOUSE_ACCOUNT_INFO = "findHouseAccountInfo";
        // 根据管家code查询自我介绍详情.
        static String FIND_HOUSE_INTRODUCE_INFO = "findHouseIntroduceInfo";
        // 根据管家code查询工作履历 .
        static String FIND_HOUSE_WORK_INFO = "findHouseWorkInfo";
        // 根据管家code查询教育履历.
        static String FIND_HOUSE_EDUCATION_INFO = "findHouseEducationInfo";
    }

    /**
     * 管家服务经历查询
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBS2101131RsResult findHouseKeeperList(IBS2101131RsParam param) {
        IBS2101131RsResult rsResult = new IBS2101131RsResult();
        // 查询管家code和管家名称.
        List<SlHouseAccount> slHouseAccountList = this.findList(SqlId.FIND_HOUSE_ACCOUNT_INFO, param);
        List<IBS2101131RsBean> ibs2101131RsBeanList = new ArrayList<IBS2101131RsBean>();

        if (null != slHouseAccountList && slHouseAccountList.size() > 0) {
            for (SlHouseAccount slHouseAccount : slHouseAccountList) {

                IBS2101131RsBean iBS2101131RsBean = new IBS2101131RsBean();
                // 管家名称.
                iBS2101131RsBean.setHouseKeeperName(slHouseAccount.getHouseShowName());
                if (!StringUtil.isNullOrEmpty(slHouseAccount.getHouseCode())) {
                    // 管家code.
                    param.setHouseCode(slHouseAccount.getHouseCode());
                    // 根据管家code查询自我介绍详情.
                    SlHouseIntroduce slHouseIntroduce = this.findOne(SqlId.FIND_HOUSE_INTRODUCE_INFO, param);
                    if (null != slHouseIntroduce) {
                        // 自我介绍.
                        iBS2101131RsBean.setHouseKeeperIntroduce(slHouseIntroduce.getIntroduce());
                        // 服务心得.
                        iBS2101131RsBean.setHouseKeeperServiceCommit(slHouseIntroduce.getServiceCommit());
                        // 工作照.
                        iBS2101131RsBean.setHouseKeeperImg(slHouseIntroduce.getUploadUrl3());

                        if (!StringUtil.isNullOrEmpty(slHouseIntroduce.getUploadUrl3())) {
                            // 0：润和后天上传文件服务器的方式保存的文件URL..
                            iBS2101131RsBean.setHouseKeeperImgServerType(0);
                            iBS2101131RsBean.setHouseKeeperImg(SystemServerManager.CommonServerManager.getMskFlieDownLoadServers() + slHouseIntroduce.getUploadUrl3());
                        }
                    }
                    // 根据管家code查询工作履历 .
                    List<IBS2101132RsBean> houseWorkList = new ArrayList<IBS2101132RsBean>();
                    List<SlHouseWorkHis> slHouseWorkHisList = this.findList(SqlId.FIND_HOUSE_WORK_INFO, param);
                    if (null != slHouseWorkHisList && slHouseWorkHisList.size() > 0) {
                        for (SlHouseWorkHis slHouseWorkHis : slHouseWorkHisList) {
                            IBS2101132RsBean iBS2101132RsBean = new IBS2101132RsBean();
                            // 公司名称.
                            iBS2101132RsBean.setWorkComp(slHouseWorkHis.getWorkComp());
                            // 开始日期(yyyyMM).
                            iBS2101132RsBean.setWorkStart(slHouseWorkHis.getWorkStart());
                            // 结束日期(yyyyMM).
                            iBS2101132RsBean.setWorkEnd(slHouseWorkHis.getWorkEnd());
                            // 职务.
                            iBS2101132RsBean.setWorkPosition(slHouseWorkHis.getWorkPosition());
                            // 岗位.
                            iBS2101132RsBean.setWorkStation(slHouseWorkHis.getWorkStation());
                            houseWorkList.add(iBS2101132RsBean);
                        }
                        iBS2101131RsBean.setHouseWorkList(houseWorkList);
                    }
                    // 根据管家code查询教育履历.
                    List<SlHouseEducationHis> slHouseEducationHisList = this.findList(SqlId.FIND_HOUSE_EDUCATION_INFO, param);
                    List<IBS2101133RsBean> houseEduList = new ArrayList<IBS2101133RsBean>();

                    if (null != slHouseEducationHisList && slHouseEducationHisList.size() > 0) {
                        for (SlHouseEducationHis slHouseEducationHis : slHouseEducationHisList) {
                            IBS2101133RsBean iBS2101133RsBean = new IBS2101133RsBean();
                            // 教育单位.
                            iBS2101133RsBean.setEduComp(slHouseEducationHis.getEduComp());
                            // 开始日期(yyyyMM).
                            iBS2101133RsBean.setEduStart(slHouseEducationHis.getEduStart());
                            // 结束日期(yyyyMM).
                            iBS2101133RsBean.setEduEnd(slHouseEducationHis.getEduEnd());
                            houseEduList.add(iBS2101133RsBean);
                        }
                        iBS2101131RsBean.setHouseEduList(houseEduList);
                    }
                }
                ibs2101131RsBeanList.add(iBS2101131RsBean);
            }
            rsResult.setHousekeeperList(ibs2101131RsBeanList);

        }
        return rsResult;
    }
}
