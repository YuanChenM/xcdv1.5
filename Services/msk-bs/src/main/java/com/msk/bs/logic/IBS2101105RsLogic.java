package com.msk.bs.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101105RsParam;
import com.msk.bs.bean.IBS2101105RsResult;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.BsBasicInfo;
import com.msk.core.entity.SlHouseManage;

/**
 * Created by gyh on 2016/3/17.
 */
public class IBS2101105RsLogic  extends BaseLogic {

    @Autowired
    private BS2101102Logic bS2101102Logic;
    @Autowired
    private BSBasicInfoLogic bsBasicInfoLogic;

    /**
     * 查询冻品管家信息
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<IBS2101105RsResult> search(IBS2101105RsParam param){
        List<IBS2101105RsResult> rs = this.findList(SqlId.SQL_ID_FIND_HOUSE_ACCUNT,param);
        for(IBS2101105RsResult result:rs){
            for(int i=0;i<rs.size();i++){
                //拼接管家编码
                BsBasicInfo bsBasicInfo = bsBasicInfoLogic.findBsBasicInfo(rs.get(i).getSlCode());
                if(bsBasicInfo != null && !StringUtil.isNullOrEmpty(bsBasicInfo.getSlCodeDis())){
                    rs.get(i).setHouseCodeDis(bsBasicInfo.getSlCodeDis() + rs.get(i).getHouseCodeDis());
                }
            }
        }
        return rs;
    }


    @Override
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        IBS2101105RsParam ibs2101105RsParam = (IBS2101105RsParam) param;
        if(param.getPageCount()==0||param.getPageNo()==0){
            param.setPaging(false);
        }else {
            param.setPaging(true);
        }

        param.setFilter("slCode",ibs2101105RsParam.getSlCode());
        param.setFilter("houseAccount",ibs2101105RsParam.getHouseAccount());
        param.setFilter("houseTel",ibs2101105RsParam.getHouseTel());
        param.setFilter("houseCode",ibs2101105RsParam.getHouseCode());
        param.setFilter("accountPsd",ibs2101105RsParam.getAccountPsd());
        param.setFilter("provinceCode1",ibs2101105RsParam.getVprovinceCode());
        param.setFilter("cityCode1",ibs2101105RsParam.getVcityCode());
        param.setFilter("districtCode1",ibs2101105RsParam.getVdistrictCode());
        param.setFilter("vhouseAddress",ibs2101105RsParam.getVhouseAddress());
        param.setFilter("houseCategory",ibs2101105RsParam.getHouseCategory());
        param.setFilter("greade",ibs2101105RsParam.getGreade());
        param.setFilter("fromFlg",ibs2101105RsParam.getFromFlg());
        /*Modify Bug #3592  增加判断冻品管家分类查询  begin  whc 21016.11.08*/
        List<IBS2101105RsResult> rs= this.bS2101102Logic.findPageList(param,pageResult);
        /*Modify Bug #3592  增加判断冻品管家分类查询  begin  whc 21016.11.08*/
        if(!CollectionUtils.isEmpty(rs)){
            for(int i=0;i<rs.size();i++){
                //拼接管家编码
                BsBasicInfo bsBasicInfo = bsBasicInfoLogic.findBsBasicInfo(rs.get(i).getSlCode());
                if(bsBasicInfo != null && !StringUtil.isNullOrEmpty(bsBasicInfo.getSlCodeDis())){
                    rs.get(i).setHouseCodeDis(bsBasicInfo.getSlCodeDis() + rs.get(i).getHouseCodeDis());
                    /*Modify Bug #3592  增加判断冻品管家分类查询  begin  whc 21016.11.08*/
                    //放入传传来的管家分类
                    if(!StringUtil.isNullOrEmpty(ibs2101105RsParam.getHouseCategory())){
                        rs.get(i).setHouseCategory(ibs2101105RsParam.getHouseCategory());
                    }
                    /*Modify Bug #3592  增加判断冻品管家分类查询  begin  whc 21016.11.08*/
                }
            }
        }
        return (List<T>) rs;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    interface SqlId {
        /**
         * 查询冻品管家信息
         */
        static final String SQL_ID_FIND_HOUSE_ACCUNT = "findHouseAccunt";
        static final String SQL_ID_FIND_HOUSE_MANAGE = "findHouseManage";

    }

}
