package com.msk.bs.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import com.msk.product.consts.TableNameDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/3/1.
 */
@Service
public class IBS2101104RsLogic extends BaseLogic {

    private Logger logger = LoggerFactory.getLogger(IBS2101104RsLogic.class);

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BS2102103Logic bs2102103Logic;
    @Autowired
    private BSBasicInfoLogic bsBasicInfoLogic;
    @Autowired
    private IBA2141203RsLogic iba2141203RsLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {

        static final String SQL_ID_FIND_HOUSE_BUYER_PRODUCT = "findHouseBuyerProduct";

        //修改顺序码
        static final String SQL_ID_UPDATE_BASE_INFO = "updateBaseInfo";

        //判断HouseAccount是否存在
        static final String SQL_ID_FIND_HOUSE_ACCOUNT_EXIST = "findHouseAccountExist";
        static final String SQL_ID_FIND_HOUSE_ACCOUNT_BASIC_INFO = "findHouseAccountBasicInfo";
        //判断HouseShowName是否存在
        static final String SQL_ID_FIND_HOUSE_SHOW_NAME_EXIST = "findHouseShowNameExist";
        //HouseTel判断是否存在
        static final String SQL_ID_FIND_HOUSE_ACCOUNT_EXIST_BY_TEL = "findHouseAccountExistByTel";
        //HouseTel判断是否存在
        static final String SQL_ID_FIND_HOUSE_ACCOUNT_BY_TEL_EXIST = "findHouseAccountByTelIsExist";
        //判断HouseShowName是否存在
        static final String SQL_ID_FIND_MODIFY_HOUSE_SHOW_NAME_EXIST = "findModifyHouseShowNameExist";
        //修改HouseTel判断是否存在
        static final String SQL_ID_FIND_MODIFY_HOUSE_ACCOUNT_EXIST_BY_TEL = "findModifyHouseAccountExistByTel";
        //判断买手手机号是否存在
        static final String SQL_ID_FIND_FIND_SL_BY_TEL = "findSlByTel";
        /*//判断买手名称是否存在
        static final String SQL_ID_FIND_FIND_SL_CONTACT_EXIST = "findSlContactExist";*/


        //根据slcode tel 查询冻品管家信息
        static final String SQL_ID_FIND_HOUSE_ACCOUNT_BY_SLCODE_ACCOUNT = "findHABySlCodeAndAccount";

        //插入定级
        static final String SQL_ID_FIND_SAVE_HOUSE_GRADE = "saveHouseGradeList";

        //删除定级
        static final String SQL_ID_DEL_HOUSE_GRADE = "delHouseGrade";

        //查询定级
        static final String SQL_ID_FIND_HOUSE_GRADE = "findHouseGrade";

        /**
         * 查询冻品管家用户是否存在
         */
        static final String SQL_ID_FIND_HOUSE_ACCUNT = "findHouseAccunt";
        /**
         * 查询卖家账户信息
         */
        static final String SQL_ID_FIND_SL_ACCUNT = "findSlAccount";
        /**
         * 保存冻品管家
         */
        static final String SQL_ID_SAVE_HOUSE_ACCUNT = "saveHouseAccunt";
        /**
         * 保存冻品管家经营区域
         */
        static final String SQL_ID_SAVE_SL_HOUSE_AREA = "saveSlHouseArea";
        /**
         * 保存买冻品管家产品分类
         */
        static final String SQL_ID_SAVE_SL_HOUSE_PRODUCT = "saveSlHouseProduct";

        /**
         * 删除冻品管家
         */
        static final String SQL_ID_DELETE_HOUSE_ACCUNT = "deleteHouseAccunt";
        /**
         * 删除冻品管家等级
         */
        static final String SQL_ID_DELETE_SL_HOUSE_AREA = "deleteSlHouseArea";
        /**
         * 删除冻品管家产品分类
         */
        static final String SQL_ID_DELETE_SL_HOUSE_PRODUCT = "deleteSlHouseProduct";
        /**
         * 更新冻品管家
         */
        static final String SQL_ID_UPDATE_HOUSE_ACCUNT = "updateHouseAccunt";
        /**
         * 更新冻品管家经营区域
         */
        static final String SQL_ID_UPDATE_SL_HOUSE_AREA = "updateSlHouseArea";
        /**
         * 更新冻品管家产品分类
         */
        static final String SQL_ID_UPDATE_SL_HOUSE_PRODUCT = "updateSlHouseProduct";
        /**
         * 根据id查询经营区域
         */
        static final String SQL_ID_FIND_SL_HOUSE_AREA = "findSlHouseArea";
        /**
         * 根据id查询冻品管家产品分类
         */
        static final String SQL_ID_FIND_SL_HOUSE_PRODUCT = "findSlHouseProduct";

        /**
         * 查询冻品管家账号
         */
        static final String SQL_ID_FIND_HOUSE_ACCOUNT = "findHouseAccount";

        /**
         * 查询冻品管家编号
         */
        static final String SQL_ID_FIND_HOUSE_CODEDIS = "findhouseCodeDis";

    }

    /**
     * 更新冻品管家账号信息
     *
     * @param slHouseAccount 参数
     * @return 结果
     */
    @Transactional
    public SlHouseAccount modifySlHouseAccount(IBS2101104SlHouseAccount slHouseAccount) {
        SlHouseAccount houseAccount = slHouseAccount;
        // 根据管家分类给备用字段赋值
        if (StringUtil.toSafeString(NumberConst.IntDef.INT_ZERO).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("1");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_ONE).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("1");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_TWO).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("1");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_THREE).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("1");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_FOUR).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("1");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_FIVE).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("1");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_SIX).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("1");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_SEVEN).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("1");
            houseAccount.setHouseCategory8("0");
        } else if (StringUtil.toSafeString(NumberConst.IntDef.INT_EIGHT).equals(houseAccount.getHouseCategory())) {
            houseAccount.setHouseCategory0("0");
            houseAccount.setHouseCategory1("0");
            houseAccount.setHouseCategory2("0");
            houseAccount.setHouseCategory3("0");
            houseAccount.setHouseCategory4("0");
            houseAccount.setHouseCategory5("0");
            houseAccount.setHouseCategory6("0");
            houseAccount.setHouseCategory7("0");
            houseAccount.setHouseCategory8("1");
        }
        // 判断管家id是否存在，存在即修改不存在即新增
        if (slHouseAccount.getHouseCode() == null || "".equals(slHouseAccount.getHouseCode())) {
            // 根据卖家编码查询卖家账号
            BaseParam baseParam = new BaseParam();
            baseParam.setFilter("slCode", slHouseAccount.getSlCode());
            SlSeller account = this.findOne(SqlId.SQL_ID_FIND_SL_ACCUNT, baseParam);
            if (account == null || "".equals(account.getSlAccount())) {
                throw new BusinessException("买家id为" + slHouseAccount.getSlCode() + "的买手账号不存在！无法生成冻品管家账号");
            } else {
                if (account.getSlCodeDis() == null || "".equals(account.getSlCodeDis())) {
                    throw new BusinessException("买家id为" + slHouseAccount.getSlCode() + "的买手编码不存在！无法生成冻品管家编码");
                }
                houseAccount.setCrtId(slHouseAccount.getLoginId());
                BaseParam param = new BaseParam();
                param.setFilter("houseAccount", account.getSlAccount() + StringConst.PRE);
                param.setFilter("houseCodeDis", account.getSlCodeDis() + StringConst.PRE);
                Integer slAccount = this.getCount(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT, param);
                if (slAccount == null || slAccount < 1) {
                    houseAccount.setHouseAccount(account.getSlAccount() + "01");
                } else {
                    slAccount = slAccount + 1;
                    if (slAccount < 10) {
                        houseAccount.setHouseAccount(account.getSlAccount() + "0" + StringUtil.toSafeString(slAccount));
                    } else {
                        houseAccount.setHouseAccount(account.getSlAccount() + StringUtil.toSafeString(slAccount));
                    }
                }
                Integer slCodeDis = this.getCount(SqlId.SQL_ID_FIND_HOUSE_CODEDIS, param);
                if (slCodeDis == null || slCodeDis < 1) {
                    houseAccount.setHouseCodeDis(account.getSlCodeDis() + "01" + houseAccount.getHouseCategory());
                } else {
                    slCodeDis = slCodeDis + 1;
                    if (slAccount < 10) {
                        houseAccount.setHouseCodeDis(account.getSlCodeDis() + "0" + StringUtil.toSafeString(slCodeDis)
                                + houseAccount.getHouseCategory());
                    } else {
                        houseAccount.setHouseCodeDis(account.getSlCodeDis() + StringUtil.toSafeString(slCodeDis)
                                + houseAccount.getHouseCategory());
                    }
                }
                houseAccount.setHouseCode(StringUtil
                        .toSafeString(commonLogic.maxId(TableNameDef.SL_HOUSE_ACCOUNT, "CONVERT(HOUSE_CODE,SIGNED)")));
                houseAccount.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));
                houseAccount.setVer(NumberConst.IntDef.INT_ONE);
                houseAccount.setCrtTime(new Date());
                int result = super.save(SqlId.SQL_ID_SAVE_HOUSE_ACCUNT, houseAccount);
                if (result < 1) {
                    throw new BusinessException("冻品管家账号信息新增失败，请检查参数是否正确！");
                }
            }
        } else {
            SlHouseAccount house = this.findHABySlCodeAndHouseCode(slHouseAccount);
            if (house == null) {
                throw new BusinessException("管家id" + slHouseAccount.getHouseCode() + "不存在");
            }
            houseAccount.setHouseCode(slHouseAccount.getHouseCode());
            houseAccount.setUpdId(slHouseAccount.getLoginId());
            houseAccount.setUpdTime(new Date());
            // 删除标志是否为空，不为空则删除
            if (slHouseAccount.getDelFlg() != null && !"".equals(slHouseAccount.getDelFlg())) {
                int result = super.modify(SqlId.SQL_ID_DELETE_HOUSE_ACCUNT, houseAccount);
                if (result < 1) {
                    throw new BusinessException("冻品管家账号信息删除失败，请检查参数是否正确！");
                }
            } else {
                if (null != houseAccount.getHouseCategory() && !"".equals(houseAccount.getHouseCategory())) {
                    StringBuilder sb = new StringBuilder(house.getHouseCodeDis());
                    sb.setCharAt(sb.length() - 1, houseAccount.getHouseCategory().toCharArray()[0]);
                    houseAccount.setHouseCodeDis(sb.toString());
                } else {
                    houseAccount.setHouseCodeDis(house.getHouseCodeDis());
                }
                int result = super.modify(SqlId.SQL_ID_UPDATE_HOUSE_ACCUNT, houseAccount);
                if (result < 1) {
                    throw new BusinessException("冻品管家账号信息修改失败，请检查参数是否正确！");
                }
            }
        }
        return houseAccount;
    }

    /**
     * 更新经营区域
     *
     * @param slAreaList 参数
     * @return 结果
     */
    @Transactional
    public String modifySlHouseArea(List<IBS2101104SlHouseArea> slAreaList, String accountCode) {
        for (IBS2101104SlHouseArea area : slAreaList) {
            SlHouseArea slHouseArea = area;
            // 经营区域id存在则修改，不存在则新增
            if (area.getSlAreaId() == null) {
                if (area.getHouseCode() == null || "".equals(area.getHouseCode())) {
                    area.setHouseCode(accountCode);
                }
                slHouseArea.setCrtId(area.getLoginId());
                slHouseArea.setSlAreaId(commonLogic.maxId("sl_house_area", "SL_AREA_ID"));
                slHouseArea.setVer(1);
                slHouseArea.setCrtTime(new Date());
                int result = super.save(SqlId.SQL_ID_SAVE_SL_HOUSE_AREA, slHouseArea);
                if (result < 1) {
                    throw new BusinessException("冻品管家经营区域信息新增失败，请检查参数是否正确！");
                }
            } else {
                // 检查区域id是否存在
                BaseParam param = new BaseParam();
                param.getFilterMap().put("slAreaId", area.getSlAreaId());
                int rs = this.getCount(SqlId.SQL_ID_FIND_SL_HOUSE_AREA, param);
                if (rs < 1) {
                    return "冻品管家经营区域id" + area.getSlAreaId() + "不存在";
                }
                slHouseArea.setUpdId(area.getLoginId());
                slHouseArea.setUpdTime(new Date());
                // 删除标志是否为空
                if (area.getDelFlg() != null && !"".equals(area.getDelFlg())) {
                    int result = super.modify(SqlId.SQL_ID_DELETE_SL_HOUSE_AREA, slHouseArea);
                    if (result < 1) {
                        throw new BusinessException("冻品管家经营区域信息删除失败，请检查参数是否正确！");
                    }
                } else {
                    int result = super.modify(SqlId.SQL_ID_UPDATE_SL_HOUSE_AREA, slHouseArea);
                    if (result < 1) {
                        throw new BusinessException("冻品管家经营区域信息修改失败，请检查参数是否正确！");
                    }
                }
            }
        }
        return "经营区域信息更新成功";
    }

    /**
     * 冻品管家产品分类更新
     *
     * @param housePdList 参数
     * @return 结果
     */
    @Transactional
    public String modifySlHouseProduct(List<IBS2101104SlHouseProduct> housePdList, SlHouseAccount account) {
        for (IBS2101104SlHouseProduct product : housePdList) {
            SlHouseProduct slHouseProduct = product;
            // 冻品管家产品分类id存在修改，不存在新增
            if (product.getPdId() == null) {
                if (product.getHouseCode() == null || "".equals(product.getHouseCode())) {
                    if (account.getHouseCode() == null || "".equals(account.getHouseCode())) {
                        throw new BusinessException("冻品管家ID不能为空");
                    }
                    slHouseProduct.setHouseCode(account.getHouseCode());
                }
                slHouseProduct.setCrtId(product.getLoginId());
                slHouseProduct.setPdId(commonLogic.maxId("sl_house_product", "PD_ID"));
                slHouseProduct.setVer(1);
                slHouseProduct.setCrtTime(new Date());
                int result = super.save(SqlId.SQL_ID_SAVE_SL_HOUSE_PRODUCT, slHouseProduct);
                if (result < 1) {
                    throw new BusinessException("冻品管家产品分类信息新增失败，请检查参数是否正确！");
                }
            } else {
                // 检查冻品管家产品分类id是否存在
                BaseParam param = new BaseParam();
                param.getFilterMap().put("pdId", product.getPdId());
                int rs = this.getCount(SqlId.SQL_ID_FIND_SL_HOUSE_PRODUCT, param);
                if (rs < 1) {
                    return "冻品管家产品分类id" + product.getPdId() + "不存在";
                }
                slHouseProduct.setUpdId(product.getLoginId());
                slHouseProduct.setUpdTime(new Date());
                // 检查删除标志是是否为空，不为空则删除操作，为空修改
                if (product.getDelFlg() != null && !"".equals(product.getDelFlg())) {
                    int result = super.modify(SqlId.SQL_ID_DELETE_SL_HOUSE_PRODUCT, slHouseProduct);
                    if (result < 1) {
                        throw new BusinessException("冻品管家产品分类信息删除失败，请检查参数是否正确！");
                    }
                } else {
                    int result = super.modify(SqlId.SQL_ID_UPDATE_SL_HOUSE_PRODUCT, slHouseProduct);
                    if (result < 1) {
                        throw new BusinessException("冻品管家产品分类信息修改失败，请检查参数是否正确！");
                    }
                }

            }
        }
        return "冻品管家产品分类更新成功";
    }

    /**
     * 修改或新增冻品管家信息
     *
     * @param ibs2101104RsParam 参数
     * @return 结果
     */
    @Transactional
    public SlHouseAccount editBSSeller(IBS2101104RsParam ibs2101104RsParam, BaseParam baseParam) {
        // 冻品管家账号信息
        IBS2101104SlHouseAccount slHouseAccount = ibs2101104RsParam.getSlHouseAccount();
        // 经营区域List
        List<IBS2101104SlHouseArea> slAreaList = ibs2101104RsParam.getSlAreaList();
        // 冻品管家产品分类list
        List<IBS2101104SlHouseProduct> housePdList = ibs2101104RsParam.getHousePdList();
        SlHouseAccount houseAccount = new SlHouseAccount();
        // 更新冻品管家账号信息
        if (slHouseAccount != null) {
            houseAccount = this.modifySlHouseAccount(slHouseAccount);
        }
        // 更新经营区域
        // if (!CollectionUtils.isEmpty(slAreaList) && slAreaList.size() > 0) {
        // this.modifySlHouseArea(slAreaList,accountCode);
        // }
        // 更新冻品管家产品分类
        if (!CollectionUtils.isEmpty(housePdList) && housePdList.size() > 0) {
            this.modifySlHouseProduct(housePdList, houseAccount);
        }
        if (slHouseAccount == null && CollectionUtils.isEmpty(housePdList)) {
            throw new BusinessException("参数不规范，请检查后提交。");
        }
        return slHouseAccount;
    }


    /**
     * 修改或新增冻品管家信息
     *
     * @param ibs2101104RsParam 参数
     * @param param             参数
     * @param slHouseTypeList   管家分类集合
     * @return 结果
     */
    @Transactional
    public RsResponse<SlHouseAccount> editHouseAccount(IBS2101104RsParam ibs2101104RsParam, BaseParam param,
                                                       List<BS2102107Bean> slHouseTypeList) {
        RsResponse<SlHouseAccount> rs = new RsResponse<SlHouseAccount>();
        rs.setReturnCode("1002");
        rs.setMessage("保存失败");
        if (StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseCode())) {
            //houseAccount
            int houseAccuntByAccount = this.findHouseAccountExist(ibs2101104RsParam.getSlHouseAccount());
            //houseShowName
            int houseAccuntByName = this.findHouseShowNameExist(ibs2101104RsParam.getSlHouseAccount());
            //housetTel
            int houseAccuntByTel = this.findHouseAccountExistByTel(ibs2101104RsParam.getSlHouseAccount());
            //slTel
            int slByTelCount = this.findSlByTel(ibs2101104RsParam.getSlHouseAccount());
            /*//slContact
            int slContactCount = this.findSlContactExist(ibs2101104RsParam.getSlHouseAccount());*/
            if (houseAccuntByAccount > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家账号或被其他管家账号或管家名称，或管家手机号使用！");
                return rs;
            } else if (houseAccuntByName > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家名称或被其他管家账号或管家名称，或管家手机号使用！");
                return rs;
            } else if (houseAccuntByTel > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家手机号或被其他管家账号或管家名称，或管家手机号使用！");
                return rs;
            } else if (slByTelCount > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家手机号已被其他买手使用！");
                return rs;
            } /*else if (slContactCount > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家名称已被其他买手使用！");
                return rs;
            }*/
        } else {
            //判断housetTel是否存在
            int houseAccuntTel = this.findModifyHouseAccountExistByTel(ibs2101104RsParam.getSlHouseAccount());
            //houseShowName
            int houseAccuntByName = this.findModifyHouseShowNameExist(ibs2101104RsParam.getSlHouseAccount());
            //slTel
            int slByTelCount = this.findSlByTel(ibs2101104RsParam.getSlHouseAccount());
            /*//slContact
            int slContactCount = this.findSlContactExist(ibs2101104RsParam.getSlHouseAccount());*/
            if (houseAccuntTel > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家手机号或被其他管家账号或管家名称，或管家手机号使用！");
                return rs;

            } else if (houseAccuntByName > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家名称或被其他管家账号或管家名称，或管家手机号使用！");
                return rs;
            } else if (slByTelCount > 0) {
                rs.setReturnCode("1003");
                rs.setMessage("该管家手机号已被其他买手使用！");
                return rs;
            }
        }
        int result = 0;
        // 冻品管家账号信息
        IBS2101104SlHouseAccount slHouseAccount = ibs2101104RsParam.getSlHouseAccount();

        if (StringUtil.isNullOrEmpty(slHouseAccount.getHouseGreade()) && !StringUtil.isNullOrEmpty(slHouseAccount.getGreade())) {
            slHouseAccount.setHouseGreade(slHouseAccount.getGreade());
        }


        // 获取物流区
        List<CityBean> vlgcsAreaList = getLgcsInfo(slHouseAccount.getVcityCode());
        if (CollectionUtils.isEmpty(vlgcsAreaList)) {
            logger.info("城市名称:" + ibs2101104RsParam.getVcityName() + ",区县名称:" + ibs2101104RsParam.getVdistrictName()
                    + ",没有获取到物流区");
            return rs;
        }

        slHouseAccount.setVareaCode(vlgcsAreaList.get(0).getAreaCode());
        slHouseAccount.setVlgcsAreaCode(vlgcsAreaList.get(0).getLgcsAreaCode());

        // 新增
        if (StringUtil.isNullOrEmpty(slHouseAccount.getHouseCode()) && findHouseAccountExist(slHouseAccount) == 0) {
            RsResponse<SlHouseAccount> saveResult = saveSlHouseManage(slHouseAccount, ibs2101104RsParam, param, vlgcsAreaList);
            return saveResult;
        }
        // 修改冻品管家信息
        else {
            // 根据卖家编码查询卖家账号
            BsBasicInfo basicInfo = bsBasicInfoLogic.findBsBasicInfo(slHouseAccount.getSlCode());
            if (basicInfo == null) {
                rs.setMessage("买手不存在,无法操作");
                return rs;
            }

            // 获取工作地址 户籍地址物流区信息
            setLgcsCode(ibs2101104RsParam, slHouseAccount);

            slHouseAccount.setUpdId(param.getUpdId());
            slHouseAccount.setUpdTime(new Date());

            //查询之前的数据
            SlHouseAccount oldHouseAccuntCount = this.findHouseAccountBasicInfo(ibs2101104RsParam.getSlHouseAccount());
            if (null == oldHouseAccuntCount) {
                rs.setReturnCode("1004");
                rs.setMessage("查询出错，管家信息不存在");
                return rs;
            }

            // 买手识别码(00)+买手类型码(2位)+买手地区码(3位)+买手地区顺序码(4位)+冻品管家地区码(3位)+冻品管家区县码(2位)+冻品管家买手顺序码(2位)
            String hkSeq = "";
            if (!StringUtil.isNullOrEmpty(oldHouseAccuntCount.getHouseCodeDis()) && oldHouseAccuntCount.getHouseCodeDis().length() >= 5) {
                hkSeq = oldHouseAccuntCount.getHouseCodeDis().substring(oldHouseAccuntCount.getHouseCodeDis().length() - 2, oldHouseAccuntCount.getHouseCodeDis().length());
            } else {
                hkSeq = String.valueOf(Integer.parseInt(StringUtil.isEmpty(basicInfo.getHkSeq()) == true ? "0" : basicInfo.getHkSeq()) + 1);
                hkSeq = hkSeq.length() > 1 ? hkSeq : "0" + hkSeq;
            }
            String houseCodeDis = slHouseAccount.getVcityCode() + slHouseAccount.getVdistrictCode() + hkSeq;
            slHouseAccount.setHouseCodeDis(houseCodeDis);

            result += super.modify(SqlId.SQL_ID_UPDATE_HOUSE_ACCUNT, slHouseAccount);
            RsResponse<List<SlHouseManage>> rsResponseHouseManage = new RsResponse<>();
            // 修改冻品管家分类
            if (CollectionUtils.isEmpty(slHouseTypeList)) {
                // 插入冻品管家分类
                rsResponseHouseManage = saveHouseType(ibs2101104RsParam, slHouseAccount, vlgcsAreaList, param);
                result += Integer.parseInt(rsResponseHouseManage.getReturnCode());
            } else {
                List<SlHouseManage> houseManageList = new ArrayList<>();
                for (int i = 0; i < ibs2101104RsParam.getHouseTypeList().size(); i++) {
                    String[] sonType = ibs2101104RsParam.getHouseTypeList().get(i).getTypeCode().split(",");
                    if (sonType != null && sonType.length > 0) {
                        for (int j = 0; j < sonType.length; j++) {
                            SlHouseManage houseManage = setSlHouseManage(ibs2101104RsParam, vlgcsAreaList, param,
                                    ibs2101104RsParam.getHouseTypeList().get(i).getParentTypeCode(), sonType[j]);
                            houseManageList.add(houseManage);
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(houseManageList) && houseManageList.size() > 0) {
                    // 去除相同选择
                    int len = houseManageList.size() - 1;
                    for (int i = len; i >= 0; i--) {
                        boolean isFlag = false;
                        for (Iterator it = slHouseTypeList.iterator(); it.hasNext(); ) {
                            SlHouseManage slHouseManage = (SlHouseManage) it.next();
                            if (houseManageList.get(i).getHouseCategoryCode()
                                    .equals(slHouseManage.getHouseCategoryCode())
                                    && houseManageList.get(i).getHouseReclassifyCode()
                                    .equals(slHouseManage.getHouseReclassifyCode())) {
                                it.remove();
                                isFlag = true;
                            }
                        }
                        if (isFlag) {
                            houseManageList.remove(i);
                        }
                    }
                    // 插入新数据
                    if (!CollectionUtils.isEmpty(houseManageList)) {
                        rsResponseHouseManage.setResult(houseManageList);
                        result += this.batchSave(houseManageList);
                        //插入等级
                        this.saveHouseGrade(houseManageList, param);
                    }
                    // 删除取消旧数据
                    for (SlHouseManage slHouseManage : slHouseTypeList) {
                        slHouseManage.setUpdId(param.getUpdId());
                        slHouseManage.setUpdTime(new Date());
                        result += bs2102103Logic.delHouseManage(slHouseManage);
                    }
                    if (!CollectionUtils.isEmpty(slHouseTypeList)) {
                        //查询定级
                        List<SlHouseGrade> houseGradeList = this.findHouseGrade(slHouseTypeList);
                        //删除定级
                        if (!CollectionUtils.isEmpty(houseGradeList)) {
                            this.delHouseGrade(houseGradeList, param);
                        }
                    }


                }
            }
            if (result > 0) {
                if (basicInfo != null && !StringUtil.isNullOrEmpty(basicInfo.getSlCodeDis()) && !StringUtil.isNullOrEmpty(slHouseAccount.getHouseCodeDis())) {
                    slHouseAccount.setHouseCodeDis(basicInfo.getSlCodeDis() + slHouseAccount.getHouseCodeDis());
                }
                rs.setReturnCode("1001");
                rs.setResult(slHouseAccount);
                rs.setMessage("保存成功");
            }
            //添加到冻品管家组
            this.saveHkGroup(rsResponseHouseManage.getResult(), slHouseAccount);
            return rs;
        }
    }


    /**
     * 获取冻品管家组信息
     *
     * @param houseManageList
     * @param houseAccount
     * @return
     */
    public List<BS2102115Bean> getHkGroup(List<SlHouseManage> houseManageList, SlHouseAccount houseAccount) {
        if (CollectionUtils.isEmpty(houseManageList)) {
            return null;
        }
        BaseParam baseParam = new BaseParam();
        baseParam.setFilterObject("houseManageList", houseManageList);
        List<SlHouseBuyerProduct> houseBuyerProductList = this.findList(SqlId.SQL_ID_FIND_HOUSE_BUYER_PRODUCT, baseParam);
        if (!CollectionUtils.isEmpty(houseBuyerProductList)) {
            //查询管家组信息
            List<BS2102115Bean> hkGroupList = new ArrayList<>();
            for (SlHouseBuyerProduct houseBuyerProduct : houseBuyerProductList) {
                logger.info("查询冻品管家组信息开始");
                RsRequest<IBS2101128RsParam> request = new RsRequest<IBS2101128RsParam>();
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setSiteCode("1");
                IBS2101128RsParam ibs2101128RsParam = new IBS2101128RsParam();
                ibs2101128RsParam.setLgcsAreaCode(houseAccount.getVlgcsAreaCode());
                ibs2101128RsParam.setCityCode(houseAccount.getVcityCode());
                ibs2101128RsParam.setBuyerType("01");
                ibs2101128RsParam.setClassesCode(houseBuyerProduct.getByPdClassesCode());
                ibs2101128RsParam.setMachiningCode(houseBuyerProduct.getByPdMachiningCode());
                request.setParam(ibs2101128RsParam);
                String url = SystemServerManager.BuyersReportServerManager.getQueryHkGroupInfo();// ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
                RsResponse<IBS2101128RsResult> rsResult =
                        RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101128RsResult>>() {
                        });
                if (rsResult.getResult() != null) {
                    List<BS2102115Bean> ibS2101128RsResultList = rsResult.getResult().getIbr121305RsBeanList();
                    hkGroupList.addAll(ibS2101128RsResultList);
                }
                logger.info("查询冻品管家组信息结束");
            }
            return hkGroupList;
        }
        return null;
    }


    /**
     * 添加到冻品管家组
     *
     * @param houseManageList
     * @param houseAccount
     * @return
     */
    public int saveHkGroup(List<SlHouseManage> houseManageList, SlHouseAccount houseAccount) {
        List<BS2102115Bean> ibs2101128RsResultList = this.getHkGroup(houseManageList, houseAccount);
        int result = 0;
        if (!CollectionUtils.isEmpty(ibs2101128RsResultList)) {
            logger.info("添加到冻品管家组---接口返回了" + ibs2101128RsResultList.size() + "条数据--begin");
            IBS2101129RsParam ibs2101129RsParam = new IBS2101129RsParam();
            IBS121306RsBean ibs121306RsBean = new IBS121306RsBean();
            ibs121306RsBean.setSlCode(houseAccount.getSlCode());
            ibs121306RsBean.setHouseCode(houseAccount.getHouseCode());
            ibs121306RsBean.setJoinTime(new Date());
            List<IBS121306RsBean> houseList = new ArrayList<>();
            houseList.add(ibs121306RsBean);
            ibs2101129RsParam.setHouseList(houseList);

            RsRequest<IBS2101129RsParam> request = new RsRequest<IBS2101129RsParam>();
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setSiteCode("1");
            for (BS2102115Bean bs2102115Bean : ibs2101128RsResultList) {
                ibs2101129RsParam.setHkGroupId(bs2102115Bean.getHkGroupId());
                request.setParam(ibs2101129RsParam);

                String url = SystemServerManager.BuyersReportServerManager.getUpdateHkGroupInfos();// ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
                RsResponse<Integer> rsResult =
                        RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                        });
                if (rsResult.getResult() != null) {
                    result += rsResult.getResult();
                }
            }
            logger.info("发送了" + ibs2101128RsResultList.size() + "条,返回成功记录为:" + result);
        }
        return result;
    }


    public List<CityBean> getLgcsInfo(String cityCode) {
        if (!StringUtil.isNullOrEmpty(cityCode)) {
            // 获取物流区
            DistrictParam vdistrictParam = new DistrictParam();
            vdistrictParam.setCityCodes(new String[]{cityCode});
            vdistrictParam.setFlag(0);
            List<CityBean> vlgcsAreaList = CommRestUtil.getProvinceCityList(vdistrictParam);
            return vlgcsAreaList;
        }
        return new ArrayList<>();
    }


    // 构造SlHouseManage
    private SlHouseManage setSlHouseManage(IBS2101104RsParam ibs2101104RsParam, List<CityBean> vlgcsAreaList,
                                           BaseParam param, String categoryCode, String reclassifyCode) {
        SlHouseManage houseManage = new SlHouseManage();
        houseManage.setMid(commonLogic.maxId("SL_HOUSE_MANAGE", "MID"));
        houseManage.setSlCode(ibs2101104RsParam.getSlHouseAccount().getSlCode());
        houseManage.setHouseCode(ibs2101104RsParam.getSlHouseAccount().getHouseCode());
        houseManage.setCreatetime(new Date());
        houseManage.setLgcsAreaCode(vlgcsAreaList.get(0).getLgcsAreaCode());
        houseManage.setLgcsAreaName(vlgcsAreaList.get(0).getLgcsAreaName());
        houseManage.setCityCode(ibs2101104RsParam.getSlHouseAccount().getVcityCode());
        houseManage.setCityName(ibs2101104RsParam.getVcityName());
        houseManage.setHouseCategoryCode(categoryCode);
        houseManage.setHouseReclassifyCode(reclassifyCode);
        houseManage.setPublicBuyers(0);
        houseManage.setVipBuyers(0);
        houseManage.setMarketingDays(0);
        houseManage.setIsChangeBuyers("0");
        houseManage.setDelFlg("0");
        houseManage.setCrtId(param.getCrtId());
        houseManage.setCrtTime(new Date());
        houseManage.setUpdId(param.getUpdId());
        houseManage.setUpdTime(new Date());
        houseManage.setActTime(new Date());
        houseManage.setActId(param.getActId());
        houseManage.setVer(0);
        return houseManage;
    }

    // 插入冻品管家信息
    private RsResponse<SlHouseAccount> saveSlHouseManage(IBS2101104SlHouseAccount slHouseAccount, IBS2101104RsParam ibs2101104RsParam,
                                                         BaseParam param, List<CityBean> vlgcsAreaList) {
        //冻品管家图片
        SlHouseIntroduce slHouseIntroduce = ibs2101104RsParam.getSlHouseIntroduce();
        RsResponse<SlHouseAccount> rs = new RsResponse<SlHouseAccount>();
        rs.setMessage("保存失败");
        rs.setReturnCode("1002");

        int result = 0;
        // 根据卖家编码查询卖家账号
        BsBasicInfo basicInfo = bsBasicInfoLogic.findBsBasicInfo(slHouseAccount.getSlCode());
        if (basicInfo == null || StringUtil.isNullOrEmpty(basicInfo.getSlAccount())) {
            logger.info("买家id为" + slHouseAccount.getSlCode() + "的买手账号不存在！无法生成冻品管家账号");
            rs.setMessage("买家id为" + slHouseAccount.getSlCode() + "的买手账号不存在！无法生成冻品管家账号");
            return rs;
        }
        if (StringUtil.isNullOrEmpty(basicInfo.getSlCodeDis())) {
            logger.info("买家id为" + slHouseAccount.getSlCode() + "的买手编码不存在！无法生成冻品管家编码");
            rs.setMessage("买家id为" + slHouseAccount.getSlCode() + "的买手编码不存在！无法生成冻品管家编码");
            return rs;
        }
        if (!StringUtil.isNullOrEmpty(basicInfo.getHkSeq())) {
            if (Integer.parseInt(basicInfo.getHkSeq()) >= 99) {
                logger.info("冻品管家买手顺序码:" + basicInfo.getHkSeq() + ",将超过2位，不予新增");
                rs.setMessage("买手顺序码超过99位");
                rs.setReturnCode("1003");
                return rs;
            }
        }

        // 获取工作地址 户籍地址 虚拟地址物流区信息
        setLgcsCode(ibs2101104RsParam, slHouseAccount);

        // 买手识别码(00)+买手类型码(2位)+买手地区码(3位)+买手地区顺序码(4位)+冻品管家地区码(3位)+冻品管家区县码(2位)+冻品管家买手顺序码(2位)
        String hkSeq = String.valueOf(Integer.parseInt(StringUtil.isEmpty(basicInfo.getHkSeq()) == true ? "0" : basicInfo.getHkSeq()) + 1);
        hkSeq = hkSeq.length() > 1 ? hkSeq : "0" + hkSeq;
        String houseCodeDis = slHouseAccount.getVcityCode() + slHouseAccount.getVdistrictCode() + hkSeq;
        // slHouseAccount.setAccountPsd(Md5Digest.digest(slHouseAccount.getAccountPsd()));
        slHouseAccount.setHouseCodeDis(houseCodeDis);
        slHouseAccount.setHouseCode(String.valueOf(commonLogic.maxId("SL_HOUSE_ACCOUNT", "HOUSE_CODE")));
        slHouseAccount.setDelFlg("0");
        slHouseAccount.setCrtId(param.getCrtId());
        slHouseAccount.setCrtTime(new Date());
        slHouseAccount.setUpdId(param.getUpdId());
        slHouseAccount.setUpdTime(new Date());
        slHouseAccount.setActTime(new Date());
        slHouseAccount.setActId(param.getActId());
        slHouseAccount.setVer(0);
        //默认为2：已认证
        slHouseAccount.setAuthStatus(2);

        result += super.save(SqlId.SQL_ID_SAVE_HOUSE_ACCUNT, slHouseAccount);
        if (result < 1) {
            logger.info("冻品管家账号信息新增失败，请检查参数是否正确！");
            return rs;
        }

        //修改冻品管家顺序码
        if (!StringUtil.isNullOrEmpty(basicInfo.getHkSeq())) {
            String newMemo10 = String.valueOf(Integer.parseInt(basicInfo.getHkSeq()) + 1);
            basicInfo.setHkSeq(newMemo10.length() > 1 ? newMemo10 : "0" + newMemo10);
        } else {
            basicInfo.setHkSeq("01");
        }
        result += super.modify(SqlId.SQL_ID_UPDATE_BASE_INFO, basicInfo);

        if (null != slHouseIntroduce) {
            slHouseIntroduce.setHouseCode(slHouseAccount.getHouseCode());
            result += this.saveSlHouseIntroduce(slHouseIntroduce, param);
        }
        // 插入冻品管家分类
        RsResponse<List<SlHouseManage>> rsResponseHouseManage = saveHouseType(ibs2101104RsParam, slHouseAccount, vlgcsAreaList, param);
        result += Integer.parseInt(rsResponseHouseManage.getReturnCode());
        if (result > 0) {
            slHouseAccount.setHouseCodeDis(basicInfo.getSlCodeDis() + slHouseAccount.getHouseCodeDis());
            rs.setReturnCode("1001");
            rs.setResult(slHouseAccount);
            rs.setMessage("保存成功");
        }

        //添加到冻品管家组
        this.saveHkGroup(rsResponseHouseManage.getResult(), slHouseAccount);
        return rs;
    }

    // 构造管家履历信息
    private SlHouseManageHis setHouseManageHis(SlHouseManage houseManage) {
        SlHouseManageHis manageHis = new SlHouseManageHis();
        manageHis.setHisMid(commonLogic.maxId("SL_HOUSE_MANAGE_HIS", "HIS_MID"));
        manageHis.setSlCode(houseManage.getSlCode());
        manageHis.setHouseCode(houseManage.getHouseCode());
        manageHis.setLgcsAreaCode(houseManage.getLgcsAreaCode());
        manageHis.setCityName(houseManage.getCityName());
        manageHis.setCityCode(houseManage.getCityCode());
        manageHis.setLgcsAreaName(houseManage.getLgcsAreaName());
        manageHis.setHouseCategoryCode(houseManage.getHouseCategoryCode());
        manageHis.setHouseReclassifyCode(houseManage.getHouseReclassifyCode());
        manageHis.setPublicBuyers(houseManage.getPublicBuyers());
        manageHis.setVipBuyers(houseManage.getVipBuyers());
        manageHis.setMarketingDays(houseManage.getMarketingDays());
        manageHis.setIsChangeBuyers(houseManage.getIsChangeBuyers());
        manageHis.setRemark(houseManage.getRemark());
        manageHis.setDelFlg(houseManage.getDelFlg());
        manageHis.setCrtId(houseManage.getCrtId());
        manageHis.setCrtTime(houseManage.getCrtTime());
        manageHis.setUpdId(houseManage.getUpdId());
        manageHis.setUpdTime(houseManage.getUpdTime());
        manageHis.setActId(houseManage.getActId());
        manageHis.setActTime(houseManage.getActTime());
        manageHis.setCreatetime(houseManage.getCreatetime());
        manageHis.setVer(houseManage.getVer());
        return manageHis;
    }

    // 设置物流区
    private void setLgcsCode(IBS2101104RsParam ibs2101104RsParam, SlHouseAccount slHouseAccount) {
        // 获取工作地址 户籍地址物流区信息
        List<CityBean> rlgcsAreaList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(slHouseAccount.getRcityCode())) {
            DistrictParam rdistrictParam = new DistrictParam();
            rdistrictParam.setCityCodes(new String[]{slHouseAccount.getRcityCode()});
            rdistrictParam.setFlag(0);
            rlgcsAreaList = CommRestUtil.getProvinceCityList(rdistrictParam);
        }
        if (!CollectionUtils.isEmpty(rlgcsAreaList)) {
            slHouseAccount.setRareaCode(rlgcsAreaList.get(0).getAreaCode());
            slHouseAccount.setRlgcsAreaCode(rlgcsAreaList.get(0).getLgcsAreaCode());
        } else {
            slHouseAccount.setRareaCode("");
            slHouseAccount.setRlgcsAreaCode("");
        }
        List<CityBean> lgcsAreaList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(slHouseAccount.getCityCode())) {
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityCodes(new String[]{slHouseAccount.getCityCode()});
            districtParam.setFlag(0);
            lgcsAreaList = CommRestUtil.getProvinceCityList(districtParam);
        }
        if (!CollectionUtils.isEmpty(lgcsAreaList)) {
            slHouseAccount.setAreaCode(lgcsAreaList.get(0).getAreaCode());
            slHouseAccount.setLgcsAreaCode(lgcsAreaList.get(0).getLgcsAreaCode());
        } else {
            slHouseAccount.setAreaCode("");
            slHouseAccount.setLgcsAreaCode("");
        }
    }

    /**
     * 新增冻品管家分类
     *
     * @param ibs2101104RsParam
     * @param slHouseAccount
     * @param vlgcsAreaList
     * @param param
     * @return
     */
    public RsResponse<List<SlHouseManage>> saveHouseType(IBS2101104RsParam ibs2101104RsParam, SlHouseAccount slHouseAccount,
                                                         List<CityBean> vlgcsAreaList, BaseParam param) {
        RsResponse<List<SlHouseManage>> rsResponse = new RsResponse<>();
        int result = 0;
        // 插入冻品管家分类
        SlHouseManage houseManage = null;
        List<SlHouseManage> houseManageList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ibs2101104RsParam.getHouseTypeList())) {
            for (int i = 0; i < ibs2101104RsParam.getHouseTypeList().size(); i++) {
                String[] sonType = ibs2101104RsParam.getHouseTypeList().get(i).getTypeCode().split(",");
                if (sonType != null && sonType.length > 0) {
                    for (int j = 0; j < sonType.length; j++) {
                        houseManage = new SlHouseManage();
                        houseManage.setMid(commonLogic.maxId("SL_HOUSE_MANAGE", "MID"));
                        houseManage.setSlCode(slHouseAccount.getSlCode());
                        houseManage.setHouseCode(slHouseAccount.getHouseCode());
                        houseManage.setCreatetime(new Date());
                        houseManage.setLgcsAreaCode(vlgcsAreaList.get(0).getLgcsAreaCode());
                        houseManage.setLgcsAreaName(vlgcsAreaList.get(0).getLgcsAreaName());
                        houseManage.setCityCode(slHouseAccount.getVcityCode());
                        houseManage.setCityName(ibs2101104RsParam.getVcityName());
                        houseManage
                                .setHouseCategoryCode(ibs2101104RsParam.getHouseTypeList().get(i).getParentTypeCode());
                        houseManage.setHouseReclassifyCode(sonType[j]);
                        houseManage.setPublicBuyers(0);
                        houseManage.setVipBuyers(0);
                        houseManage.setMarketingDays(0);
                        houseManage.setIsChangeBuyers("0");
                        houseManage.setDelFlg("0");
                        houseManage.setCrtId(param.getCrtId());
                        houseManage.setCrtTime(new Date());
                        houseManage.setUpdId(param.getUpdId());
                        houseManage.setUpdTime(new Date());
                        houseManage.setActTime(new Date());
                        houseManage.setActId(param.getActId());
                        houseManage.setVer(0);
                        houseManageList.add(houseManage);
                    }
                }
            }
        }
        // 插入管理分类
        if (!CollectionUtils.isEmpty(houseManageList)) {
            result += this.batchSave(houseManageList);
            //插入等级
            this.saveHouseGrade(houseManageList, param);
        }

        rsResponse.setReturnCode(result + "");
        rsResponse.setResult(houseManageList);
        return rsResponse;
    }

    /**
     * 保存冻品管家图片
     *
     * @param slHouseIntroduce
     * @return
     */
    @Transactional
    private int saveSlHouseIntroduce(SlHouseIntroduce slHouseIntroduce, BaseParam param) {
        int count = NumberConst.IntDef.INT_ZERO;
        slHouseIntroduce.setDelFlg("0");
        slHouseIntroduce.setCrtId(param.getCrtId());
        slHouseIntroduce.setCrtTime(new Date());
        slHouseIntroduce.setUpdId(param.getUpdId());
        slHouseIntroduce.setUpdTime(new Date());
        slHouseIntroduce.setActTime(new Date());
        slHouseIntroduce.setActId(param.getActId());
        slHouseIntroduce.setVer(0);
        count = iba2141203RsLogic.modifyIntroduce(slHouseIntroduce);
        if (count < 1) {
            logger.info("冻品管家相片新增失败，请检查参数是否正确！");
        }
        return count;
    }


    /**
     * 插入等级数据
     *
     * @param houseManageList
     * @return
     */
    @Transactional
    public int saveHouseGrade(List<SlHouseManage> houseManageList, BaseParam param) {
        int result = 0;
        if (!CollectionUtils.isEmpty(houseManageList)) {
            logger.info("插入等级开始，共有" + houseManageList.size() + "条记录");
            SlHouseGrade slHouseGrade = null;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            java.util.Date nextDate = cal.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            String validYearMonth = sdf.format(new java.util.Date());
            List<SlHouseGrade> houseGradeList = new ArrayList<>();
            for (SlHouseManage houseManage : houseManageList) {
                slHouseGrade = new SlHouseGrade();
                slHouseGrade.setGradeId(commonLogic.maxId("SL_HOUSE_GRADE", "GRADEID"));
                slHouseGrade.setSlCode(houseManage.getSlCode());
                slHouseGrade.setHouseCode(houseManage.getHouseCode());
                slHouseGrade.setLgcsAreaCode(houseManage.getLgcsAreaCode());
                slHouseGrade.setLgcsAreaName(houseManage.getLgcsAreaName());
                slHouseGrade.setHouseCategoryCode(houseManage.getHouseCategoryCode());
                slHouseGrade.setHouseReclassifyCode(houseManage.getHouseReclassifyCode());
                slHouseGrade.setGradeCode("3");
                slHouseGrade.setValidYearMonth(validYearMonth);
                slHouseGrade.setDelFlg("0");
                slHouseGrade.setCrtId(param.getCrtId());
                slHouseGrade.setCrtTime(new Date());
                slHouseGrade.setUpdId(param.getUpdId());
                slHouseGrade.setUpdTime(new Date());
                slHouseGrade.setActId(param.getActId());
                slHouseGrade.setActTime(new Date());
                slHouseGrade.setEndTime(nextDate);
                slHouseGrade.setStatus("0");
                slHouseGrade.setVer(0);
                houseGradeList.add(slHouseGrade);
            }
            if (!CollectionUtils.isEmpty(houseGradeList)) {
                Map<String, Object> map = new HashMap<>();
                map.put("houseGradeList", houseGradeList);
                BaseParam baseParam = new BaseParam();
                baseParam.setFilterMap(map);
                result = this.save(SqlId.SQL_ID_FIND_SAVE_HOUSE_GRADE, baseParam);
            }
        }
        logger.info("插入等级结束，共有" + houseManageList.size() + "条记录，成功返回" + result + "条");
        return result;
    }


    /**
     * 删除定级
     *
     * @param houseGradeList
     * @param param
     * @return
     */
    @Transactional
    public int delHouseGrade(List<SlHouseGrade> houseGradeList, BaseParam param) {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        if (!CollectionUtils.isEmpty(houseGradeList)) {
            Map<String, Object> map = new HashMap<>();
            map.put("houseGradeList", houseGradeList);
            BaseParam baseParam = new BaseParam();
            baseParam.setFilterMap(map);
            baseParam.setFilter("updId", param.getUpdId());
            baseParam.setFilter("updTime", nowDate);
            result = this.modify(SqlId.SQL_ID_DEL_HOUSE_GRADE, baseParam);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<SlHouseGrade> findHouseGrade(List<BS2102107Bean> slHouseTypeList) {
        //查询定级
        Map<String, Object> map = new HashMap<>();
        map.put("houseManage", slHouseTypeList);
        return this.findList(map, SqlId.SQL_ID_FIND_HOUSE_GRADE);
    }


    /**
     * 根据houseAccunt houseTel 判断冻品管家是否存在
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public int findHouseAccountExist(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return 0;
        }
        return this.getCount(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT_EXIST, ibs2101104SlHouseAccount);
    }

    @Transactional(readOnly = true)
    public SlHouseAccount findHouseAccountBasicInfo(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return null;
        }
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT_BASIC_INFO, ibs2101104SlHouseAccount);
    }

    /**
     * 判断
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public int findHouseShowNameExist(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return 0;
        }
        return this.getCount(SqlId.SQL_ID_FIND_HOUSE_SHOW_NAME_EXIST, ibs2101104SlHouseAccount);
    }

    /**
     * 修改houseAccunt HouseShowName 判断数据是否存在
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public int findModifyHouseShowNameExist(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return 0;
        }
        return this.getCount(SqlId.SQL_ID_FIND_MODIFY_HOUSE_SHOW_NAME_EXIST, ibs2101104SlHouseAccount);
    }

    /**
     * houseAccunt houseTel 判断数据是否存在
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public int findModifyHouseAccountExistByTel(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return 0;
        }
        return this.getCount(SqlId.SQL_ID_FIND_MODIFY_HOUSE_ACCOUNT_EXIST_BY_TEL, ibs2101104SlHouseAccount);
    }

    //判断手机号买手是否已使用
    @Transactional(readOnly = true)
    public int findSlByTel(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return 0;
        }
        return this.getCount(SqlId.SQL_ID_FIND_FIND_SL_BY_TEL, ibs2101104SlHouseAccount);
    }

    //判断管家名称买手是否已使用
    /*@Transactional(readOnly = true)
    public int findSlContactExist(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return 0;
        }
        return this.getCount(SqlId.SQL_ID_FIND_FIND_SL_CONTACT_EXIST, ibs2101104SlHouseAccount);
    }*/

    /**
     * 修改houseAccunt houseTel 判断数据是否存在
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public int findHouseAccountExistByTel(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return 0;
        }
        return this.getCount(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT_EXIST_BY_TEL, ibs2101104SlHouseAccount);
    }

    /**
     * 修改houseAccunt houseTel 判断数据是否存在
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public SlHouseAccount findHouseAccountByTelIsExist(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        if (StringUtil.isNullOrEmpty(ibs2101104SlHouseAccount.getHouseAccount())) {
            return null;
        }
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT_BY_TEL_EXIST, ibs2101104SlHouseAccount);
    }


    /**
     * 修改houseAccunt houseTel 判断数据是否存在
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public SlHouseAccount findHABySlCodeAndAccount(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT_BY_SLCODE_ACCOUNT, ibs2101104SlHouseAccount);
    }


    /**
     * 修改houseCode slCode 判断数据是否存在
     *
     * @param ibs2101104SlHouseAccount
     * @return
     */
    @Transactional(readOnly = true)
    public SlHouseAccount findHABySlCodeAndHouseCode(IBS2101104SlHouseAccount ibs2101104SlHouseAccount) {
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_ACCUNT, ibs2101104SlHouseAccount);
    }

}