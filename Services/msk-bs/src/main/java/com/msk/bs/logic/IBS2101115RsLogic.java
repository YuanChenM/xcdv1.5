package com.msk.bs.logic;

import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101115Param;
import com.msk.bs.bean.IBS2101115RsBean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BsBasicInfo;
import com.msk.product.consts.TableNameDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 同步卖家模块买手数据
 * Created by ni_shaotang on 2016/7/29.
 */
@Service
public class IBS2101115RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        static String SQL_ID_ACCOUNT_COUNT = "accountCount";//根据买手账户id获取买手是否存在
        static String SQL_ID_BS_BASIC_COUNT = "bsBasicCount";//根据买手id获取买手基本信息是否存在
        static String SQL_ID_SAVE_BS_BASIC_INFO = "saveBsBasicInfo";//保存买手基本信息
        static String SQL_ID_UPDATE_BS_BASIC_INFO = "updateBsBasicInfo";//修改买手基本信息
        static String SQL_ID_SAVE_BS_BASIC_INFO_HIS = "saveBsBasicInfoHis";//保存买手基本信息履历
    }

    /**
     * 保存买手登录信息和基本信息
     *
     * @param param
     * @return
     */
    @Transactional
    public int saveBuyerInfo(IBS2101115Param param) {
        int num = 0;
        if (null != param.getSlAccount()) {
            //获取买手登录信息是否存在
            int accountNum = this.getCount(SqlId.SQL_ID_ACCOUNT_COUNT, param);

            if (accountNum > 0) {//判断需要更新还是插入
                num = this.modify(param);//更新买手账户信息
            } else {
                if(!StringUtil.isNullOrEmpty(param.getOperationFlg())&&param.getOperationFlg().equals("1")) {//判断是否是新增请求
                    if(StringUtil.isNullOrEmpty(param.getSlTel())){
                        num = -3;//手机号码为空不能插入
                    }else if(null == param.getAuthStatus()){
                        num = -4;//认证状态为空不能插入
                    }else {
                        num = this.save(param);//保存买手账户信息
                    }
                }else {
                    num = -2;//数据不同步
                }
            }
        }

        if (null != param.getBsBasicInfo() && null != param.getBsBasicInfo().getSlCode()) {//买手基本信息id是否存在
            int basicNum = this.getCount(SqlId.SQL_ID_BS_BASIC_COUNT, param);
            if (basicNum > 0) {//判断需要更新还是插入
                //查询旧数据
                IBS2101115RsBean bsBasicInfoHis = this.findOne(param);
                bsBasicInfoHis.setCrtTime(param.getCrtTime());
                bsBasicInfoHis.setCrtId(param.getCrtId());
                bsBasicInfoHis.setUpdId(param.getUpdId());
                bsBasicInfoHis.setUpdTime(param.getUpdTime());
                bsBasicInfoHis.setActId(param.getActId());
                bsBasicInfoHis.setActTime(param.getActTime());
                bsBasicInfoHis.setHisId(commonLogic.maxId("BS_BASIC_INFO_HIS", "HIS_ID"));
                //添加履历
                this.save(SqlId.SQL_ID_SAVE_BS_BASIC_INFO_HIS, bsBasicInfoHis);

                num = this.modify(SqlId.SQL_ID_UPDATE_BS_BASIC_INFO, param);//更新买手基本信息
            } else {
                //获取买手登录信息是否存在
                param.setSlAccount(param.getBsBasicInfo().getSlAccount());
                int accountNum = this.getCount(SqlId.SQL_ID_ACCOUNT_COUNT, param);
                if(accountNum<1){
                    num = -1;//买手账户信息不存在。不能保存
                }else {
                    num = this.save(SqlId.SQL_ID_SAVE_BS_BASIC_INFO, param);//保存买手基本信息
                }
            }
        }
        return num;
    }
}
