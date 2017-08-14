package com.msk.bs.logic;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBA2141112Param;
import com.msk.bs.bean.IBA2141112Result;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/13.
 */
@Service
public class IBA2141112RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        // 查询冻品管家的收货地址
        static  String FIND_ADD_INFO = "findAddInfo";
        // 查询买手收货地址
        static  String FIND_BUYERADDR_INFO = "findbuyerAddrInfo";
        // 删除买手收货地址
        static  String DELETE_BUYERS_ADDRESS = "deleteBuyersAddress";
        // 删除管家收货地址
        static  String DELETE_ADDRESS = "deleteAddress";
        // 修改管家收货地址
        static  String MODIFY_ADDRESS="modifyAddress";
        // 修改管家收货地址
        static  String MODIFY_BUYERS_ADDRESS="modifyBuyersAddress";
        // 查询买手slcode
        static  String SELECT_HOUSE_ACCOUNT="selectHouseAccount";
        // 根据收货地址ID 查询管家收货地址信息
        static  String FIND_HOUSE_INFO="findHouseBookInfo";
        // 根据收货地址ID 查询买手收货地址信息
        static  String FIND_BUYERS_INFO="findBuyersBookInfo";
        // 保存管家下面的买家地址
        static  String ADD_HOUSE_ADDR="addHouseAddr";
        // 保存买手地址
        static  String ADD_BUYERS_ADDR="addBuyersAddr";
    }

    /**
     * 根据买手id获取对应的收货地址
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBA2141112Result> findAddInfo(IBA2141112Param param){
        List<IBA2141112Result>  results = new ArrayList<>();
        /**AccessType 2-管家，3-买手***/
        if(param.getAccessType().equals("2")){
             results =  this.findList(SqlId.FIND_ADD_INFO, param);
        }else{
            results =  this.findList(SqlId.FIND_BUYERADDR_INFO, param);
        }
        String[] composeCodes = new String[results.size()];
        for (int i=0;i<results.size();i++){
            IBA2141112Result result = results.get(i);
            composeCodes[i] = result.getProvinceCode()+result.getCityCode()+result.getDistrictCode();
        }
        // 开始查询物流区接口，根据省编码+城市编码+区县编码 查询对应的名称
        DistrictParam districtParam = new DistrictParam();
        districtParam.setComposeCodes(composeCodes);
        List<DistrictBean> districtList = CommRestUtil.getRegion(districtParam);
        for (IBA2141112Result iba2141111Result :results){
            for (DistrictBean districtBean : districtList){
                // 获取区县名称
                if(iba2141111Result.getDistrictCode().equals(districtBean.getDistrictCode())){
                    iba2141111Result.setDistrictName(districtBean.getDistrictName());
                }
                // 获取城市名称
                if(iba2141111Result.getCityCode().equals(districtBean.getCityCode())){
                    iba2141111Result.setCityName(districtBean.getCityName());
                }
                // 获取省名称
                if(iba2141111Result.getProvinceCode().equals(districtBean.getProvinceCode())){
                    iba2141111Result.setProvinceName(districtBean.getProvinceName());
                }

                if(!StringUtil.isNullOrEmpty(iba2141111Result.getDistrictName())
                        && !StringUtil.isNullOrEmpty(iba2141111Result.getCityName())
                        && !StringUtil.isNullOrEmpty(iba2141111Result.getProvinceName())){
                    break;
                }
            }
        }
        return results;
    }


    @Transactional
    public int deleteHouseAddress(IBA2141112Param param){
        // 2-管家，3-买手
        int count = 0;
        if(param.getAccessType().equals("2") ){
            count =  this.modify(SqlId.DELETE_ADDRESS,param);
        }else{
            count =  this.modify(SqlId.DELETE_BUYERS_ADDRESS,param);
        }
        return  count;
    }

    /**
     * 根据登录的管家ID到表【冻品管家账户】中查出对应的买手ID
     * @param param
     * @return
     */
    @Transactional(readOnly = false)
    public String selectHouseAccount(IBA2141112Param param){
        String slcode = (String)this.findObject(SqlId.SELECT_HOUSE_ACCOUNT, param);
        return  slcode;
    }

    /**
     * 新增管家收货地址
     * @param param
     * @return
     */
    @Transactional
    public  int addHouseAddress(IBA2141112Param param){
        int insetCount = 0;
        if(param.getAccessType().equals("2")){
            insetCount =  this.save(SqlId.ADD_HOUSE_ADDR,param);
        }else{
            insetCount =  this.save(SqlId.ADD_BUYERS_ADDR,param);
        }
        return  insetCount;
    }

    /**
     * 编辑收货地址信息
     * @param param
     * @return
     */
    @Transactional
    public  int modifyHouseAddress(IBA2141112Param param){
        int modifyCount = 0 ;
        if(param.getAccessType().equals("2")){
            modifyCount =  this.modify(SqlId.MODIFY_ADDRESS,param);
        }else {
            modifyCount =  this.modify(SqlId.MODIFY_BUYERS_ADDRESS,param);
        }
        return  modifyCount;
    }

    /**
     * 根据收货地址ID获取收货地址信息
     * @param param
     * @return
     */
    @Transactional(readOnly = false)
    public IBA2141112Result findHouseBookInfo(IBA2141112Param param){
        if(param.getAccessType().equals("2")){
            return  this.findOne(SqlId.FIND_HOUSE_INFO, param);
        }else {
            return  this.findOne(SqlId.FIND_BUYERS_INFO, param);
        }
    }
}
