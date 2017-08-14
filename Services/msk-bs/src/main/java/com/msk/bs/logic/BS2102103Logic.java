package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102103Bean;
import com.msk.bs.bean.BS2102103Param;
import com.msk.bs.bean.BS2102107Bean;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseGrade;
import com.msk.core.entity.SlHouseManage;
import com.msk.core.entity.SlHouseManageHis;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wang_haichun on 2016/8/2.
 */
@Service
public class BS2102103Logic extends BaseLogic{

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){super.setBaseDao(baseDao);}

    interface SqlId{
        static final String INSERT_SL_HOUSE_MANAGER = "insertHouseManager";
        static final String FIND_EXTISTS_SL_HOUSE_MANAGER = "findExists";
        static final String FIND_SL_HOUSE_MANAGER_BY_MID = "findHouseManageByMid";
        static final String DEL_SL_HOUSE_MANAGE_BY_MID = "delHouseManageByMid";
        /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  Start */
        static final String GET_GRADE_COUNT = "getGradeCount";
        static final String SAVE_GRADE = "saveGrade";
        /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  End */
    }

    /**
     * 获取管家管理设置
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<BS2102107Bean> findAllHouseManage(BS2102103Param param){
        param.setFilter("validYearMonth",getTimeStr());
        return this.findPage(param,BS2102107Bean.class);
    }

    /**
     * 增加管家管理设置
     * @return
     */
    @Transactional
    public int addHouseManager(SlHouseManage param,SlHouseGrade slHouseGrade){
        int count = this.getCount(SqlId.FIND_EXTISTS_SL_HOUSE_MANAGER,param);
        /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  Start */
        int gradeCount = this.getCount(SqlId.GET_GRADE_COUNT,param);
        //如果等级表里不存在，则执行插入操作
        if(gradeCount == 0){
            this.save(SqlId.SAVE_GRADE,slHouseGrade);
        }
        /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  End */
        if(count > 0){
            return -1;
        }
        return this.save(SqlId.INSERT_SL_HOUSE_MANAGER,param);
    }

    /**
     * 获取地区信息
     * @param param
     * @return
     */
    public List<CityBean> getCityList(DistrictParam param){
        List<CityBean> cityBeanList = CommRestUtil.getCityList(param);
        return cityBeanList;
    }


    @Transactional
    public int updateAndSave(List<SlHouseManage> bean,BaseParam baseParam){
        int modAndUpdState = 0;
        SlHouseManageHis manageHis = null;
        List<SlHouseManageHis> houseManageHisList = new ArrayList<>();
        //获取修改前的数据
        BS2102103Param bs2102103Param = new BS2102103Param();
        bs2102103Param.setSlHouseManageList(bean);

        if(!CollectionUtils.isEmpty(bean)){
            List<SlHouseManage> slHouseManageList = this.findList(SqlId.FIND_SL_HOUSE_MANAGER_BY_MID,bs2102103Param);
            if(!CollectionUtils.isEmpty(slHouseManageList) && bean.size() == slHouseManageList.size()){
                for(int i=0;i<bean.size();i++){
                    //构造原数据对象
                    manageHis = new SlHouseManageHis();
                    manageHis.setHisMid(commonLogic.maxId("SL_HOUSE_MANAGE_HIS","HIS_MID"));
                    manageHis.setSlCode(slHouseManageList.get(i).getSlCode());
                    manageHis.setHouseCode(slHouseManageList.get(i).getHouseCode());
                    manageHis.setLgcsAreaCode(slHouseManageList.get(i).getLgcsAreaCode());
                    manageHis.setCityName(slHouseManageList.get(i).getCityName());
                    manageHis.setCityCode(slHouseManageList.get(i).getCityCode());
                    manageHis.setLgcsAreaName(slHouseManageList.get(i).getLgcsAreaName());
                    manageHis.setHouseCategoryCode(slHouseManageList.get(i).getHouseCategoryCode());
                    manageHis.setHouseReclassifyCode(slHouseManageList.get(i).getHouseReclassifyCode());
                    manageHis.setPublicBuyers(slHouseManageList.get(i).getPublicBuyers());
                    manageHis.setVipBuyers(slHouseManageList.get(i).getVipBuyers());
                    manageHis.setMarketingDays(slHouseManageList.get(i).getMarketingDays());
                    manageHis.setIsChangeBuyers(slHouseManageList.get(i).getIsChangeBuyers());
                    manageHis.setRemark(slHouseManageList.get(i).getRemark());
                    manageHis.setDelFlg(slHouseManageList.get(i).getDelFlg());
                    manageHis.setCrtId(slHouseManageList.get(i).getCrtId());
                    manageHis.setCrtTime(slHouseManageList.get(i).getCrtTime());
                    manageHis.setUpdId(slHouseManageList.get(i).getUpdId());
                    manageHis.setUpdTime(slHouseManageList.get(i).getUpdTime());
                    manageHis.setActId(slHouseManageList.get(i).getActId());
                    manageHis.setActTime(slHouseManageList.get(i).getActTime());
                    manageHis.setCreatetime(slHouseManageList.get(i).getCreatetime());
                    manageHis.setVer(slHouseManageList.get(i).getVer());

                    houseManageHisList.add(manageHis);

                    //修改更新数据的时间/ID
                    bean.get(i).setUpdId(baseParam.getUpdId());
                    bean.get(i).setUpdTime(new Date());

                    int modState = modifyHouseManage(bean.get(i));
                    modAndUpdState += modState;
                }
            }

        }

        if(!CollectionUtils.isEmpty(houseManageHisList)){
            saveHouseManage(houseManageHisList);
        }
        return modAndUpdState;
    }


    @Transactional
    public int modifyHouseManage(SlHouseManage slHouseManage){
        return this.modify(slHouseManage);
    }

    @Transactional
    public int saveHouseManage(List<SlHouseManageHis> houseManageHisList){
        return this.batchSave(houseManageHisList);
    }

    @Transactional
    public int delHouseManage(SlHouseManage slHouseManage){
        return this.modify(SqlId.DEL_SL_HOUSE_MANAGE_BY_MID,slHouseManage);
    }

    private String getTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String str = sdf.format(new java.util.Date());
        return  str;
    }

}
