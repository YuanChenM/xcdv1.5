package com.msk.inventory.service.impl;

import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.IvmCargoIdentityBean;
import com.msk.inventory.entity.IvmCargoIdentity;
import com.msk.inventory.service.ICargoIdentityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/15.
 */
@Service
public class CargoIdentityServiceImpl extends BaseService implements ICargoIdentityService {

    @Transactional
    public void insertOneCargoIdentity(IvmCargoIdentityBean sqlBean) {
        this.save("insertOneCargo",sqlBean);
    }

    @Transactional
    public List<IvmCargoIdentity> selectCargoIdentityList(IvmCargoIdentityBean sqlBean){
        List<IvmCargoIdentity>  cargoIdentityList=  new ArrayList<IvmCargoIdentity>();
        cargoIdentityList= this.findList("selectCargoIdentity",sqlBean);
        return cargoIdentityList;
    }

    @Transactional
    public int selectCargoIdentityCount(IvmCargoIdentityBean sqlBean) {
        int pageCount = this.getCount("countCargoIdentity",sqlBean);
        return pageCount;
    }

    @Transactional
    public void updateCargoIdentity(IvmCargoIdentityBean sqlBean) {
        this.modify("updateCargoIdentity",sqlBean);
    }

    /**
     * 根据dateCode查找对应的seq，找到则更新seq，未查到则新增dateCode+seq
     * @param dateCode
     * @return 返回SEQ
     */
    public int getCargoIdentity(String dateCode){
        IvmCargoIdentityBean cargoIdentity = new IvmCargoIdentityBean();
        cargoIdentity.setDateCode(dateCode);
        List<IvmCargoIdentity> cargoIdentityList =selectCargoIdentityList(cargoIdentity);
        if(cargoIdentityList != null && cargoIdentityList.size() == 1){
            int seq = cargoIdentityList.get(0).getSeq();
            cargoIdentity.setSeq(seq+1);
            updateCargoIdentity(cargoIdentity);
            return seq+1;
        }else {
            cargoIdentity.setSeq(1);
            cargoIdentity.setCrtId("system");
            cargoIdentity.setCrtTime(DateTimeUtil.getCustomerDate());
            insertOneCargoIdentity(cargoIdentity);
            return 1;
        }
    }

    /**
     * 货物身份分配
     * @param  time
     * @return String
     */
    public String getLoadNo(Date time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateCode = sdf.format(time);
        int seq = getCargoIdentity(dateCode);

        DecimalFormat df = new DecimalFormat("00000");
        String seqStr = df.format(seq);
        return dateCode+seqStr;
    }
}
