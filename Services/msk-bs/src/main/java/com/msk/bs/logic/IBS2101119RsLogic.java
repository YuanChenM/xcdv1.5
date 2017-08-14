package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101119RsBean;
import com.msk.bs.bean.IBS2101119RsParam;
import com.msk.bs.bean.IBS2101119RsResult;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by ren_qiang on 2016/8/19.
 */
@Service
public class IBS2101119RsLogic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(IBS2101119RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        String SQL_ID_GET_HOUSE_BY_ID = "getHouseInfoById";

    }

    @Transactional(readOnly = true)
    public List<IBS2101119RsBean> getHouseInfoById(IBS2101119RsParam param){
        List<IBS2101119RsBean> list = this.findList(SqlId.SQL_ID_GET_HOUSE_BY_ID, param);
        /*if(!CollectionUtils.isEmpty(list)){
            for(IBS2101119RsBean bean :list ){
                String vAddress = bean.getvAddress();
                if(StringUtils.hasLength(vAddress)){
                    String vLgcsArea,vArea,vProvince,vCity,vDistrict;
                    String [] ss = vAddress.split(",");
                    int length = ss.length;
                    vLgcsArea = ss[0];
                    bean.setvLgcsArea(vLgcsArea);
                    if (length>1){
                        vArea = ss[1];
                        bean.setvArea(vArea);
                    }
                    else{
                        bean.setvArea("");
                    }
                   if(length>2){
                       vProvince = ss[2];
                       bean.setvProvince(vProvince);
                   }else{
                       bean.setvProvince("");
                   }
                    if(length>3){
                        vCity = ss[3];
                        bean.setvCity(vCity);
                    }
                    else{
                        bean.setvCity("");
                    }
                    if(length == 5){
                        vDistrict =ss[4];
                        bean.setvDistrict(vDistrict);
                    }
                    else{
                        bean.setvDistrict("");
                    }

                }

            }
        }*/
        return list;
    }
}
