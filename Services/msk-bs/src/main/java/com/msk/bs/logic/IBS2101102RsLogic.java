package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.bs.bean.IBS2101102RsResult;
import com.msk.bs.bean.IBS2101103RsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.district.bean.*;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.seller.bean.ISLSellerRsResult;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by cx on 2016/2/26.
 */
@Service
public class IBS2101102RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private BS2101101Logic bS2101101Logic;

    @Autowired
    private BS2101106Logic bs2101106Logic;
    @Autowired
    private BS2101109Logic bs2101109Logic;

    interface SqlId {
        /**
         * 更新买手店资质信息
         */
        static final String SQL_ID_MODIFY_OF_SL_BUYERSHOP = "modifyOfSlBuyerShop";
        /**
         * 更新买手基本信息
         */
        static final String SQL_ID_MODIFY_OF_UPDATE = "modifyOfUpdate";
        /**
         * 更新店铺基本信息
         */
        static final String SQL_ID_MODIFY_OF_SL_SHOP_INFO = "modifyOfSlShopInfo";

        static final String SQL_ID_FIND_SL_SHOP_INFO_BY_SLCODE = "findShopInfoBySlCode";
        /**
         * 删除账户信息
         */
        static final String SQL_ID_MODIFY_OF_DELFLG = "modifyOfDelFlg";
        /**
         * 删除买手基本信息
         */
        static final String SQL_ID_MODIFY_OF_DELFLG_AND_SLSELLER = "modifyOfDelFlgAndSlSeller";
        /**
         * 删除买手基本信息
         */
        static final String SQL_ID_MODIFY_OF_DELFLG_SL_SHOP_INFO = "modifyOfDelFlgAndSlBuyerShop";
        /**
         * 删除店铺基本信息
         */
        static final String SQL_ID_MODIFY_OF_DELFLG_AND_SL_BUYERSHOP = "modifyOfDelFlgDlShopInfo";

        /**
         * 保存买手基本信息
         */
        static final String SQL_ID_SAVE_SL_BUYERSHOP = "saveSlBuyershop";
        /**
         * 保存买手基本信息
         */
        static final String SQL_ID_SAVE_SLSELLER = "saveSlSeller";

        /**
         * 保存店铺基本信息
         */
        static final String SQL_ID_SAVE_SL_SHOP_INFO = "saveSlShopInfo";

        /**
         * 保存银行账户信息
         */
        static final String SQL_ID_GET_BANK_COUNT = "getBankCount";
        /**
         * 保存银行账户信息
         */
        static final String SQL_ID_SAVE_SL_BANK_ACCOUNT = "saveSlBsBankAccount";
        /**
         * 保存银行账户信息
         */
        static final String SQL_ID_UPDATE_SL_BS_BANK_ACCOUNT = "updateSlBsBankAccount";

        /**
         * 查询买手基本信息是否存在
         */
        static final String SQL_ID_FIND_SL_SELLER_YES_OR_NO = "findSlSellerYesOrNo";

        /**
         * 查询买手账户是否存在
         */
        static final String SQL_ID_FIND_SL_ACCOUNT_YESORNO = "findSlAccountYesOrNO";

        /**
         * 查询买手基本信息是否存在
         */
        static final String SQL_ID_FIND_SL_SELLER_YESORNO = "findSlSellerYesOrNo";
        /**
         * 查询买手店资质信息是否存在
         */
        static final String SQL_ID_FIND_SL_BUYERSHOP_YESORNO = "findSlBuyerShopYesOrNo";
        /**
         * 查询店铺基本信息是否存在
         */
        static final String SQL_ID_FIND_SL_SHOP_INFO_YESORNO = "findSlShopInfoYesOrNo";

        //根据slAccount 查询sl_basic_info
        static final String SQL_ID_FIND_SL_BASIC_INFO_BY_SL_ACCOUNT = "findBysSlAccount";

      /*  static final String SQL_ID_FIND_ACCOUNT_LIST = "findAccountList";

        static final String SQL_ID_FIND_SL_CODE_DIS_MAX = "findSlCodeDisMax";*/
        /**
         * 根据买手账户获取买手id
         */
        static final String SQL_ID_FIND_SL_SELLER_FOR_SL_ACCOUNT = "findSlSellerForSlAccount";
        //判断账户手机号码是否重复
        static final String SQL_ID_IS_ACCOUNT_TEL = "isAccountTel";
        // 根据slAccount和手机号码，校验买手唯一
        static final String CHECK_SL_TEL = "checkSlTel";
        //校验买手名称唯一
        static final String CHECK_SL_CONTACT = "checkSlContact";
        //校验买手账号唯一
        static final String CHECK_SL_ACCOUNT = "checkSlAccount";
        /*//校验买手名称是否和其他管家一致
        static final String FIND_HOUSE_SHOW_NAME = "findHouseShowName";*/
        //校验买手手机是否和其他管家一致
        static final String FIND_HOUSE_TEL = "findHouseTel";
        // 修改时根据slAccount和手机号码，校验买手唯一
        static final String CHECK_MODIFY_SL_TEL = "checkModifySlTel";
        //修改时校验买手名称唯一
        static final String CHECK_MODIFY_SL_CONTACT = "checkModifySlContact";
        //修改时校验买手账号唯一
        static final String CHECK_MODIFY_SL_ACCOUNT = "checkModifySlAccount";
    }

    /**
     * 编辑买手账户
     *
     * @param param
     * @return
     */
    @Transactional
    public IBS2101102RsResult editAccount(RsRequest<IBS2101102RsParam> param) {
        IBS2101102RsResult rs = new IBS2101102RsResult();
        IBS2101102RsParam iBS2101102RsParam = param.getParam();
        //基本信息code
        String slCode = "";
        //基本身份证号
        String idCard = "";
        //买手地址
        String address = "";
        //获取id
        String loginId = iBS2101102RsParam.getLoginId();
        //获取删除标志
        String delFlg = iBS2101102RsParam.getDelFlg();
        //获取版本好
        Integer ver = iBS2101102RsParam.getVer();
        //获取买手账户
        BsAccount bsAccount = iBS2101102RsParam.getSlAccount();
        //买手基本信息
        BsBasicInfo bsBasicInfo = iBS2101102RsParam.getSlSeller();

        //买手银行账户信息
        SlBsBankaccount slBsBankaccount = iBS2101102RsParam.getSlBsBankaccount();

        //买手店资质信息
        SlBuyershop slBuyershop = iBS2101102RsParam.getSlBuyerShop();

        //店铺基本信息
        SlShopInfo slShopInfo = iBS2101102RsParam.getSlShopInfo();

        BaseParam baseParam = new BaseParam();

        ISLSellerRsParam rsParam = new ISLSellerRsParam();

        /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
        Date date = DateTimeUtil.getCustomerDate();
        if (bsAccount != null) {
            bsAccount.setCrtId(loginId);
            bsAccount.setCrtTime(date);
            bsAccount.setUpdId(loginId);
            bsAccount.setUpdTime(date);
            bsAccount.setActId(loginId);
            bsAccount.setActTime(date);
            //注册来源默认为 1: 神农客
            bsAccount.setFromFlg(!StringUtil.isNullOrEmpty(bsAccount.getFromFlg()) ? bsAccount.getFromFlg() : "1");
        }
        if (bsBasicInfo != null) {
            bsBasicInfo.setCrtId(loginId);
            bsBasicInfo.setCrtTime(date);
            bsBasicInfo.setUpdId(loginId);
            bsBasicInfo.setUpdTime(date);
            bsBasicInfo.setActId(loginId);
            bsBasicInfo.setActTime(date);
        }
        if (slBsBankaccount != null) {
            slBsBankaccount.setCrtId(loginId);
            slBsBankaccount.setCrtTime(date);
            slBsBankaccount.setUpdId(loginId);
            slBsBankaccount.setUpdTime(date);
            slBsBankaccount.setActId(loginId);
            slBsBankaccount.setActTime(date);
        }
        if (slBuyershop != null) {
            slBuyershop.setCrtId(loginId);
            slBuyershop.setCrtTime(date);
            slBuyershop.setUpdId(loginId);
            slBuyershop.setUpdTime(date);
            slBuyershop.setActId(loginId);
            slBuyershop.setActTime(date);
            //合营优先顺方式  默认 2：随机
            slBuyershop.setSlSort(null != slBuyershop.getSlSort() ? slBuyershop.getSlSort() : 2);
        }
        if (slShopInfo != null) {
            slShopInfo.setCrtId(loginId);
            slShopInfo.setCrtTime(date);
            slShopInfo.setUpdId(loginId);
            slShopInfo.setUpdTime(date);
            slShopInfo.setActId(loginId);
            slShopInfo.setActTime(date);
        }
        rsParam.setCrtId(loginId);
        rsParam.setCrtTime(date);
        rsParam.setUpdId(loginId);
        rsParam.setUpdTime(date);
        rsParam.setActId(loginId);
        rsParam.setActTime(date);
        /**Add: 横展开添加共通设置 2016/09/23   BY  任强  End */

        Integer isSaveAccount = 0;


        if (null != bsAccount) {
            if (StringUtil.isNullOrEmpty(bsAccount.getSlAccount())) {
                throw new BusinessException("买手帐号不能为空");
            }
            //认证状态为空默认给他2:已认证
            bsAccount.setAuthStatus(2);

            bsAccount.setCrtId(loginId);
            //判断是否为删除操作
            if ("1".equals(delFlg)) {
                //删除账户信息(更新删除标志为“1”)
                bsAccount.setDelFlg("1");
                int num = bs2101109Logic.updateBsAccount(bsAccount);
                if (num == 0) {
                    throw new BusinessException("删除买手账户失败");
                }
            } else {

                //买手显示名称不存在，默认值赋值为联系人名称
                if (StringUtil.isNullOrEmpty(bsAccount.getSlShowName())) {
                    bsAccount.setSlShowName(bsAccount.getSlContact());
                }

                //获取买手登录信息是否存在
                baseParam.setFilter("slAccount", bsAccount.getSlAccount());
                int accountNum = super.getCount(SqlId.SQL_ID_FIND_SL_ACCOUNT_YESORNO, baseParam);
                if (accountNum > 0) {
                    int num = bs2101109Logic.updateBsAccount(bsAccount);
                    if (num == 0) {
                        throw new BusinessException("修改买手账户失败");
                    }
                } else {
                    //验证手机号码是否重复
                    int num = getCount(SqlId.SQL_ID_IS_ACCOUNT_TEL, bsAccount);
                    if (num > 0) {
                        throw new BusinessException("您保存的买手手机号码重复");
                    }
                    //Modif for Bug #2992 at 2016/11/23 by whc Start
                    int addNum = 0;
                    try {
                        addNum = bs2101109Logic.saveBsAccount(bsAccount);
                    } catch (Exception e) {
                        if (e.getMessage().contains("Duplicate")) {
                            throw new BusinessException("买手账号重复");
                        }
                    }
                    //Modif for Bug #2992 at 2016/11/23 by whc Start
                    if (addNum == 0) {
                        throw new BusinessException("添加买手账户失败");
                    }
                }
            }
        }
        if (null != bsBasicInfo) {

            //转义老接口数据
            this.translatedBasicInfo(bsBasicInfo, slBuyershop);

            if ("1".equals(delFlg)) {
                //删除账户信息(更新删除标志为“1”)
                bsBasicInfo.setDelFlg("1");
                int num = bs2101109Logic.updateBsBasicInfo(bsBasicInfo);
                if (num == 0) {
                    throw new BusinessException("删除买手基本信息失败");
                }
            } else {
                bsBasicInfo.setCrtId(loginId);
                //查询是否有对应的账户
                if (StringUtil.isNullOrEmpty(bsBasicInfo.getSlAccount())) {
                    throw new BusinessException("您保存的买手没有对应的账户信息");
                }
                baseParam.setFilter("slAccount", bsBasicInfo.getSlAccount());
                int num2 = super.getCount(SqlId.SQL_ID_FIND_SL_ACCOUNT_YESORNO, baseParam);
                if (num2 <= 0 && isSaveAccount <= 0) {
                    throw new BusinessException("您保存的买手没有对应的账户信息");
                }
                //Modif for 买手编码不对 at 2016/10/27 by zhukai Start
                if (StringUtil.isNullOrEmpty(bsBasicInfo.getCityCode())) {
                    throw new BusinessException("您保存的买手没有对应的城市");
                } else {
                    BsBasicInfo basicInfo = new BsBasicInfo();
                    // 根据cityCode查询买手顺序表中已注册对应的买手数量
                    Long buyerSeqnoCount = bs2101106Logic.findBuyerCountByCityCode(bsBasicInfo.getCityCode());
                    // buyerSeqnoCount,表示该地区没有买手注册
                    if (null == buyerSeqnoCount) {
                        buyerSeqnoCount = insertBsCitySeqno(bsBasicInfo);
                    } else {

                        basicInfo = bs2101106Logic.getBsBasicInfo(bsBasicInfo);

                        buyerSeqnoCount = updateBsCitySeqno(bsBasicInfo, basicInfo, buyerSeqnoCount);
                    }
                    //Modif for Bug #3792 at 2016/11/23 by whc Start
                    //如果接口传的编码为空   就去查询
                    if (bsBasicInfo != null && StringUtil.isNullOrEmpty(bsBasicInfo.getSlCodeDis()) && !StringUtil.isNullOrEmpty(bsBasicInfo.getSlAccount())) {
                        BaseParam bParam = new BaseParam();
                        bParam.setFilter("slAccount", bsBasicInfo.getSlAccount());
                        SlSeller slSeller = this.findOne(SqlId.SQL_ID_FIND_SL_BASIC_INFO_BY_SL_ACCOUNT, bParam);
                        if (slSeller != null) {
                            bsBasicInfo.setSlCodeDis(slSeller.getSlCodeDis());
                            bsBasicInfo.setCityCode(!StringUtil.isNullOrEmpty(bsBasicInfo.getCityCode()) ? bsBasicInfo.getCityCode() : slSeller.getCityCode());
                            bsBasicInfo.setMemo8(!StringUtil.isNullOrEmpty(bsBasicInfo.getMemo8()) ? bsBasicInfo.getMemo8() : slSeller.getMemo8());
                            bsBasicInfo.setSlCode(!StringUtil.isNullOrEmpty(bsBasicInfo.getSlCode()) ? bsBasicInfo.getSlCode() : slSeller.getSlCode());
                        }
                    }
                    //Modif for Bug #3792 at 2016/11/23 by whc Start
                    // 新增或者编辑信息
                    String slCodeDis = "";
                    if (StringUtil.isNullOrEmpty(bsBasicInfo.getSlCodeDis()) || basicInfo == null || !bsBasicInfo.getCityCode().equals(basicInfo.getCityCode())) {
                        String buyerDistCode = frontCompWithZore(buyerSeqnoCount, 4);
                        //  买手识别码(2位)+买手三级分类码(2位)+买手地区码(3位)+买手地区顺序码(4位)，共11位。
                        slCodeDis = "00" + bsBasicInfo.getMemo8() + bsBasicInfo.getCityCode() + buyerDistCode;
                    } else if (!StringUtil.isNullOrEmpty(bsBasicInfo.getSlCodeDis()) && !bsBasicInfo.getMemo8().equals(basicInfo.getMemo8())) {
                        int length = bsBasicInfo.getSlCodeDis().length();
                        slCodeDis = "00" + bsBasicInfo.getMemo8() + bsBasicInfo.getCityCode() + bsBasicInfo.getSlCodeDis().substring(length - 4, length);
                    } else {
                        // 编辑时偌什么都没修改则不需要修改买手编码
                        slCodeDis = bsBasicInfo.getSlCodeDis();
                    }
                    bsBasicInfo.setSlCodeDis(slCodeDis);
                    rs.setSlSeller(bsBasicInfo);
                }
                //设置地区信息
                this.dealSlSellerCodes(bsBasicInfo);
                if (StringUtil.isNullOrEmpty(bsBasicInfo.getSlCode())) {
                    Long maxId = this.commonLogic.maxId("BS_BASIC_INFO", "SL_CODE");
                    bsBasicInfo.setSlCode(maxId + "");
                    int num = bs2101109Logic.saveBsBasicInfo(bsBasicInfo);
                    if (num == 0) {
                        throw new BusinessException("新增买手基本信息失败");
                    }
                } else {
                    int num = bs2101109Logic.updateBsBasicInfo(bsBasicInfo);
                    if (num == 0) {
                        throw new BusinessException("修改买手基本信息失败");
                    }
                }
                slCode = bsBasicInfo.getSlCode();
                idCard = bsBasicInfo.getMemo7();
                address = bsBasicInfo.getMemo9();
                //保存买手银行账户信息
                if (null != slBsBankaccount) {
                    slBsBankaccount.setSlCode(bsBasicInfo.getSlCode());
                    this.saveBsBankAccountInfo(slBsBankaccount);
                }
            }
            rs.setHouseCode(slCode);
        }
        if (null != slBuyershop) {
            slBuyershop.setUpdTime(new Date());
            if (!StringUtil.isNullOrEmpty(slCode)) {
                slBuyershop.setSlCode(slCode);
            }
            //Modif for Bug #2565 at 2016/09/09 by ni_shaotang Start
            if (!StringUtil.isNullOrEmpty(address)) {
                slBuyershop.setSlAddress(address);
            }
            if (!StringUtil.isNullOrEmpty(idCard)) {
                slBuyershop.setSlIdcard(idCard);
            }
            //Modif for Bug #2565 at 2016/09/09 by ni_shaotang End
            if ("1".equals(delFlg)) {
                //删除账户信息(更新删除标志为“1”)
                int result = super.modify(SqlId.SQL_ID_MODIFY_OF_DELFLG_AND_SL_BUYERSHOP, slBuyershop);
                if (result < 1) {
                    throw new BusinessException("买手店铺资质信息删除失败，请检查参数是否正确！");
                }
            } else {
                baseParam.getFilterMap().put("slCode", slBuyershop.getSlCode());
                int num4 = super.getCount(SqlId.SQL_ID_FIND_SL_BUYERSHOP_YESORNO, baseParam);
                if (num4 > 0) {
                    //更新买手店资质信息
                    int result = super.modify(SqlId.SQL_ID_MODIFY_OF_SL_BUYERSHOP, slBuyershop);
                    if (result < 1) {
                        throw new BusinessException("买手店铺资质信息修改失败，请检查参数是否正确！");
                    }
                } else {
                    //保存买手店资质信息
                    slBuyershop.setSlCode(slBuyershop.getSlCode());
                    slBuyershop.setCrtTime(new Date());
                    int result = super.save(SqlId.SQL_ID_SAVE_SL_BUYERSHOP, slBuyershop);
                    if (result < 1) {
                        throw new BusinessException("买手店铺资质信息新增失败，请检查参数是否正确！");
                    }
                }
            }
        }

        if (null != slShopInfo) {
            slShopInfo.setUpdTime(new Date());
            if (!StringUtil.isNullOrEmpty(slCode)) {
                slShopInfo.setSlCode(slCode);
                //如果接口没有传shopid 则去查询
                if (null == slShopInfo.getShopId()) {
                    BaseParam bParam = new BaseParam();
                    bParam.setFilter("slCode", slShopInfo.getSlCode());
                    SlShopInfo shopInfo = this.findOne(SqlId.SQL_ID_FIND_SL_SHOP_INFO_BY_SLCODE, bParam);
                    if (null != shopInfo) {
                        slShopInfo.setShopId(shopInfo.getShopId());
                    }
                }

            }
            if ("1".equals(delFlg)) {
                //删除账户信息(更新删除标志为“1”)
                int result = super.modify(SqlId.SQL_ID_MODIFY_OF_DELFLG_SL_SHOP_INFO, slShopInfo);
                if (result < 1) {
                    throw new BusinessException("买手店铺信息删除失败，请检查参数是否正确！");
                }
            } else {
                if (null != slShopInfo.getShopId()) {
                    baseParam.getFilterMap().put("shopId", slShopInfo.getShopId());
                    int num5 = super.getCount(SqlId.SQL_ID_FIND_SL_SHOP_INFO_YESORNO, baseParam);
                    if (num5 <= 0) {
                        throw new BusinessException("您要修改的买手店铺信息shopId:" + slShopInfo.getShopId() + "不存在，请检查后提交！");
                    } else {
                        //更新店铺基本信息
                        int result = super.modify(SqlId.SQL_ID_MODIFY_OF_SL_SHOP_INFO, slShopInfo);
                        if (result < 1) {
                            throw new BusinessException("买手店铺信息修改失败，请检查参数是否正确！");
                        }
                    }
                } else {
                    if (StringUtil.isNullOrEmpty(slShopInfo.getSlCode())) {
                        throw new BusinessException("买手基本信息id不能为空！");
                    }
                    baseParam.getFilterMap().put("slCode", slShopInfo.getSlCode());
                    //验证店铺信息是否存在
                    int num5 = super.getCount(SqlId.SQL_ID_FIND_SL_SHOP_INFO_YESORNO, baseParam);
                    if (num5 > 0) {
                        throw new BusinessException("买手id为" + slShopInfo.getSlCode() + "的买手店铺信息已经存在，不能重复新增！");
                    }
                    //验证买手信息是否存在
                    int num6 = this.getCount(SqlId.SQL_ID_FIND_SL_SELLER_YES_OR_NO, baseParam);
                    if (num6 > 0 || !StringUtil.isNullOrEmpty(slCode)) {
                        //保存店铺基本信息
                        slShopInfo.setShopId(this.commonLogic.maxId("sl_shop_info", "SHOP_ID"));
                        slShopInfo.setCrtTime(new Date());
                        int result = super.save(SqlId.SQL_ID_SAVE_SL_SHOP_INFO, slShopInfo);
                        if (result < 1) {
                            throw new BusinessException("买手店铺信息新增失败，请检查参数是否正确！");
                        }
                    } else {
                        throw new BusinessException("买手基本信息不存在，请先保存买手基本信息！");
                    }
                }
            }
        }

        return rs;
    }

    /**
     * 更新买手顺序码
     *
     * @param bsBasicInfo
     * @param basicInfo
     * @param buyerSeqnoCount
     * @return
     */
    @Transactional
    private Long updateBsCitySeqno(BsBasicInfo bsBasicInfo, BsBasicInfo basicInfo, Long buyerSeqnoCount) {
        if (null == basicInfo || !bsBasicInfo.getCityCode().equals(basicInfo.getCityCode())) {
            buyerSeqnoCount = buyerSeqnoCount + 1;
            if (buyerSeqnoCount > 9999) {
                throw new BusinessException("该地区买手已达最大数");
            }
            // 更新买手地区顺序表
            SlBsCitySeqno slBsCitySeqno = new SlBsCitySeqno();
            slBsCitySeqno.setBsCount(buyerSeqnoCount);
            slBsCitySeqno.setCityCode(bsBasicInfo.getCityCode());
            bs2101106Logic.updateBuyerCountByCityCode(slBsCitySeqno);
        }
        return buyerSeqnoCount;
    }

    /**
     * 新增买手顺序码
     *
     * @param bsBasicInfo
     * @return
     */
    @Transactional
    private Long insertBsCitySeqno(BsBasicInfo bsBasicInfo) {
        SlBsCitySeqno slBsCitySeqno = new SlBsCitySeqno();
        slBsCitySeqno.setCityCode(bsBasicInfo.getCityCode());
        slBsCitySeqno.setActId(bsBasicInfo.getActId());
        slBsCitySeqno.setUpdId(bsBasicInfo.getUpdId());
        slBsCitySeqno.setCrtId(bsBasicInfo.getCrtId());
        slBsCitySeqno.setBsCount(StringUtil.toLong("1"));
        slBsCitySeqno.setCrtTime(new Date());
        slBsCitySeqno.setUpdTime(new Date());
        slBsCitySeqno.setActTime(new Date());
        bs2101106Logic.saveBsCitySeqno(slBsCitySeqno);
        return StringUtil.toLong("1"); // 该地区没有买手注册时默认给个1;
    }

    /*
　　* 0 指前面补充零
　　* formatLength 字符总长度为 formatLength
　　* d 代表为正数。
　　*/
    public static String frontCompWithZore(Long sourceDate, int formatLength) {
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        IBS2101103RsParam IBS2101103RsParam = (IBS2101103RsParam) param;
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
        }
        param.setFilter("slAccount", IBS2101103RsParam.getSlAccount());
        param.setFilter("accountPsd", IBS2101103RsParam.getAccountPsd());
        //Add for 添加查询条件属性 at 2016/10/08 by ni_shaotang Start
        param.setFilter("slCodeDis", IBS2101103RsParam.getSlCodeDis());
        //Add for 添加查询条件属性 at 2016/10/08 by ni_shaotang end
        param.setFilter("slCode", IBS2101103RsParam.getSlCode());
        return this.bS2101101Logic.findPageList(param, pageResult);
    }

    /**
     * 检查买手账户是否存在
     *
     * @param param
     * @return
     */
    @Transactional
    public int checkBsInfoExist(RsRequest<IBS2101102RsParam> param) {
        IBS2101102RsParam iBS2101102RsParam = param.getParam();
        BsAccount account = iBS2101102RsParam.getSlAccount();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slAccount", account.getSlAccount());
        baseParam.setFilter("slTel", account.getSlTel());
        int num2 = super.getCount(SqlId.SQL_ID_FIND_SL_ACCOUNT_YESORNO, baseParam);
        return num2;
    }

    /**
     * 添加买手相关信息
     *
     * @param param
     * @return
     */
    @Transactional
    public int saveAccount(RsRequest<IBS2101102RsParam> param) {
        IBS2101102RsParam iBS2101102RsParam = param.getParam();
        //买手显示名称不存在，默认值赋值为联系人名称
        if (StringUtil.isNullOrEmpty(iBS2101102RsParam.getSlAccount().getSlShowName())) {
            iBS2101102RsParam.getSlAccount().setSlShowName(iBS2101102RsParam.getSlAccount().getSlContact());
        }
        int result;
        //买手账号唯一
        result = this.getCount(SqlId.CHECK_SL_ACCOUNT, iBS2101102RsParam.getSlAccount());
        if (result > 0) {
            //该买手账号与其他买手账号或名称或手机相同
            result = -1;
        }
        //买手账号名称唯一
        if (result == 0) {
            //该买手名称与其他买手账号或名称或手机相同
            result = this.getCount(SqlId.CHECK_SL_CONTACT, iBS2101102RsParam.getSlAccount());
        }
        //买手账号手机唯一
        if (result == 0) {
            //该买手手机与其他买手账号或名称或手机相同
            result = this.getCount(SqlId.CHECK_SL_TEL, iBS2101102RsParam.getSlAccount());
        } else if (result > 0) {
            result = -2;
        }
        //校验买手名称是否和其他管家一致
        /*if (result == 0) {
            result = this.getCount(SqlId.FIND_HOUSE_SHOW_NAME, iBS2101102RsParam.getSlAccount());
        } else if (result > 0) {
            result = -3;
        }*/
        //校验买手手机是否和其他管家一致
        if (result == 0) {
            result = this.getCount(SqlId.FIND_HOUSE_TEL, iBS2101102RsParam.getSlAccount());
        } else if (result > 0) {
            result = -3;
        } else {
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
            //获取id
            String loginId = param.getLoginId();
            Date date = DateTimeUtil.getCustomerDate();
            iBS2101102RsParam.setCrtId(loginId);
            iBS2101102RsParam.setCrtTime(date);
            iBS2101102RsParam.setUpdId(loginId);
            iBS2101102RsParam.setUpdTime(date);
            iBS2101102RsParam.setActId(loginId);
            iBS2101102RsParam.setActTime(date);
            //买手显示名称不存在，默认值赋值为联系人名称
            if (StringUtil.isNullOrEmpty(iBS2101102RsParam.getSlAccount().getSlShowName())) {
                iBS2101102RsParam.getSlAccount().setSlShowName(iBS2101102RsParam.getSlAccount().getSlContact());
            }
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
            //认证状态为空默认给他1:认证中
            int countAccount = saveAccountInfo(iBS2101102RsParam);
            //添加买手基本信息
            int countBsBase = saveBsBaseInfo(iBS2101102RsParam);
            if (countAccount > NumberConst.IntDef.INT_ZERO && countBsBase > NumberConst.IntDef.INT_ZERO)
                return countBsBase;
            return NumberConst.IntDef.INT_ZERO;
        }
        return result;
    }

    // 修改时根据slAccount和手机号码，校验买手唯一
    public int checkModifySlTel(BsAccount bsAccount) {
        return this.getCount(SqlId.CHECK_MODIFY_SL_TEL, bsAccount);
    }

    //修改时校验买手名称唯一
    public int checkModifySlContact(BsAccount bsAccount) {
        return this.getCount(SqlId.CHECK_MODIFY_SL_CONTACT, bsAccount);
    }

    //修改时校验买手账号唯一
    public int checkModifySlAccount(BsAccount bsAccount) {
        return this.getCount(SqlId.CHECK_MODIFY_SL_ACCOUNT, bsAccount);
    }

    /*public int findHouseShowName(BsAccount bsAccount) {
        return this.getCount(SqlId.FIND_HOUSE_SHOW_NAME, bsAccount);
    }*/

    public int findHouseTel(BsAccount bsAccount) {
        return this.getCount(SqlId.FIND_HOUSE_TEL, bsAccount);
    }

    /**
     * 保存买手账户信息
     *
     * @param iBS2101102RsParam
     */
    public int saveAccountInfo(IBS2101102RsParam iBS2101102RsParam) {
        BsAccount bsAccount = iBS2101102RsParam.getSlAccount();
        int count = NumberConst.IntDef.INT_ZERO;
        if (null != bsAccount) {
            SlAccount slAccount = new SlAccount();
            bsAccount.setAuthStatus(NumberConst.IntDef.INT_TWO);
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
            bsAccount.setCrtId(iBS2101102RsParam.getCrtId());
            bsAccount.setCrtTime(iBS2101102RsParam.getCrtTime());
            bsAccount.setUpdId(iBS2101102RsParam.getUpdId());
            bsAccount.setUpdTime(iBS2101102RsParam.getUpdTime());
            bsAccount.setActId(iBS2101102RsParam.getActId());
            bsAccount.setActTime(iBS2101102RsParam.getActTime());
            /*bsAccount.setCrtTime(new Date());*/
            BeanUtils.copyProperties(bsAccount, slAccount);
            /*bsAccount.setCrtId(iBS2101102RsParam.getLoginId());*/
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  End */
            count = this.save(slAccount);
            if (count <= NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("添加买手账户失败");
            }
        }
        return count;
    }

    /**
     * 保存买手基本信息
     *
     * @param iBS2101102RsParam
     */
    @Transactional
    public int saveBsBaseInfo(IBS2101102RsParam iBS2101102RsParam) {
        int count = NumberConst.IntDef.INT_ZERO;
        BsBasicInfo bsBasicInfo = iBS2101102RsParam.getSlSeller();
        if (null != bsBasicInfo) {
            SlSeller slSeller = new SlSeller();
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
            bsBasicInfo.setCrtId(iBS2101102RsParam.getCrtId());
            bsBasicInfo.setCrtTime(iBS2101102RsParam.getCrtTime());
            bsBasicInfo.setUpdId(iBS2101102RsParam.getUpdId());
            bsBasicInfo.setUpdTime(iBS2101102RsParam.getUpdTime());
            bsBasicInfo.setActId(iBS2101102RsParam.getActId());
            bsBasicInfo.setActTime(iBS2101102RsParam.getActTime());
           /* bsBasicInfo.setCrtId(iBS2101102RsParam.getLoginId());
            bsBasicInfo.setCrtTime(new Date());*/
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  End */
            String slCode = StringUtil.toSafeString(commonLogic.maxId("sl_seller", "SL_CODE"));
            bsBasicInfo.setSlCode(slCode);
            //查询是否有对应的账户
            if (StringUtil.isNullOrEmpty(bsBasicInfo.getSlAccount())) {
                throw new BusinessException("您保存的买手没有对应的账户信息");
            }
            BaseParam baseParam = new BaseParam();
            baseParam.setFilter("slAccount", bsBasicInfo.getSlAccount());
            int num2 = super.getCount(SqlId.SQL_ID_FIND_SL_ACCOUNT_YESORNO, baseParam);
            if (num2 <= NumberConst.IntDef.INT_ZERO) {
                throw new BusinessException("您保存的买手没有对应的账户信息");
            }
            BeanUtils.copyProperties(bsBasicInfo, slSeller);
            count = this.save(SqlId.SQL_ID_SAVE_SLSELLER, slSeller);
            //添加银行账户信息
            SlBsBankaccount bsBankaccount = iBS2101102RsParam.getSlBsBankaccount();
            bsBankaccount.setSlCode(slCode);
            saveBsBankAccountInfo(bsBankaccount);
        }
        return count;
    }

    /**
     * 保存买手账户信息
     *
     * @param bsBankaccount
     * @return
     */
    @Transactional
    public void saveBsBankAccountInfo(SlBsBankaccount bsBankaccount) {
        if (null != bsBankaccount) {
            int count = this.getCount(SqlId.SQL_ID_GET_BANK_COUNT, bsBankaccount);
            if (count > 0) {
                int num = this.modify(SqlId.SQL_ID_UPDATE_SL_BS_BANK_ACCOUNT, bsBankaccount);
                if (num <= NumberConst.IntDef.INT_ZERO) {
                    throw new BusinessException("修改买手银行账户信息失败");
                }
            } else {
                /**Modify for Bug #3531 2016/11/1  zhukai start**/
                if (!StringUtil.isNullOrEmpty(bsBankaccount.getBankName()) || !StringUtil.isNullOrEmpty(bsBankaccount.getAccountName())
                        || !StringUtil.isNullOrEmpty(bsBankaccount.getBankNo())) {
                    Long accountId = commonLogic.maxId("sl_bs_bankaccount", "ACCOUNT_ID");
                    bsBankaccount.setAccountId(accountId);
                    int numer = this.save(SqlId.SQL_ID_SAVE_SL_BANK_ACCOUNT, bsBankaccount);
                    if (numer <= NumberConst.IntDef.INT_ZERO) {
                        throw new BusinessException("添加买手银行账户信息失败");
                    }
                }
                /**Modify for Bug #3531 2016/11/1  zhukai end**/
            }
        }
    }

    /**
     * 接口存在新老替换，对变更的属性进行过渡替换
     */
    private void translatedBasicInfo(BsBasicInfo bsBasicInfo, SlBuyershop slBuyershop) {
        //接口存在数据数据不统一。整合误差数据
        if (null != slBuyershop && !StringUtil.isNullOrEmpty(slBuyershop.getSlIdcard())) {//整合身份证号码
            bsBasicInfo.setMemo7(slBuyershop.getSlIdcard());
        }
        if (null != slBuyershop && !StringUtil.isNullOrEmpty(slBuyershop.getSlAddress())) {//整合买手地址
            bsBasicInfo.setMemo9(slBuyershop.getSlAddress());
        }
        if (null != slBuyershop && !StringUtil.isNullOrEmpty(slBuyershop.getSlAddress())) {//处理卖家类型
            bsBasicInfo.setSlMainClass(4);
        }
        /**
         *  Modif for Bug#2820 选择一个买手，点击管控 基本信息，此时买手分类为分销鸡产品，
         修改买手级别分类为区域站买手，保存成功后，再返回管控重新进入基本信息后，买手分类又变为分销鸡产品 at 2016/09/07 by zhu_kai1 start
         */
        if (StringUtil.isNullOrEmpty(bsBasicInfo.getMemo8())) {
            bsBasicInfo.setMemo8("01");//默认添加买手类型为：鸡产品分销买手
        }

        if (StringUtil.isNullOrEmpty(bsBasicInfo.getShopQua())) {
            bsBasicInfo.setShopQua("1");//默认买手开店资格为：有
        }
        if (StringUtil.isNullOrEmpty(String.valueOf(bsBasicInfo.getDistQua()))) {

            bsBasicInfo.setDistQua(1);//默认买手分销资格:有
        }
        /**
         *  Modif for Bug#2820 选择一个买手，点击管控 基本信息，此时买手分类为分销鸡产品，
         修改买手级别分类为区域站买手，保存成功后，再返回管控重新进入基本信息后，买手分类又变为分销鸡产品 at 2016/09/07 by zhu_kai1 end
         */
        if (StringUtil.isNullOrEmpty(bsBasicInfo.getCityCode())) {
            bsBasicInfo.setCityCode("001");//默认买手地区编码:上海
        }
    }

    private void dealSlSellerCodes(BsBasicInfo bsBasicInfo) {
        //处理物流区
        String lgcsAreaCode = bsBasicInfo.getLgcsAreaCode();
        if (!StringUtil.isNullOrEmpty(lgcsAreaCode)) {
            DistrictParam param = new DistrictParam();
            param.setLgcsAreaCode(lgcsAreaCode);
            List<LgcsAreaBean> logisticsAreaList = CommRestUtil.getLogisticsAreaList(param);
            bsBasicInfo.setLgcsAreaName(logisticsAreaList.get(0).getLgcsAreaName());
        }
        // 处理 省编码
        String provinceCode = bsBasicInfo.getProvinceCode();
        if (!StringUtil.isNullOrEmpty(provinceCode)) {
            ProvinceBean province = this.findProvince(provinceCode);
            bsBasicInfo.setProvinceName(province.getProvinceName());
            bsBasicInfo.setAreaCode(province.getAreaCode());
            bsBasicInfo.setAreaName(province.getAreaName());
        }
        // 处理 地区（含地级市)
        String cityCode = bsBasicInfo.getCityCode();
        if (!StringUtil.isNullOrEmpty(cityCode)) {
            CityBean city = this.findCity(cityCode);
            bsBasicInfo.setCityName(city.getCityName());
            // 处理 区（含县级市、县、区）
            String districtCode = bsBasicInfo.getDistrictCode();
            if (!StringUtil.isNullOrEmpty(districtCode)) {
                DistrictBean district = this.findDistrict(cityCode, districtCode);
                bsBasicInfo.setDistrictName(district.getDistrictName());
            }

        }
    }

    /**
     * 根据省代码，查询大区和省。
     * Created by xia_xiaojie on 2016/6/16.
     *
     * @param provinceCode 省代码
     * @return 大区和省
     */
    private ProvinceBean findProvince(String provinceCode) {
        if (!org.springframework.util.StringUtils.hasText(provinceCode)) {
            return new ProvinceBean();
        }

        DistrictParam param = new DistrictParam();
        param.setProvinceCode(provinceCode);
        List<ProvinceBean> provinces = CommRestUtil.getProvinceList(param);
        return CollectionUtils.isEmpty(provinces) ? new ProvinceBean() : provinces.get(0);
    }

    /**
     * 根据市代码，查询大区、物流区和市。替代旧findCity()。
     * Created by xia_xiaojie on 2016/6/16.
     *
     * @param cityCode 市代码
     * @return 大区、物流区和市
     */
    private CityBean findCity(String cityCode) {
        if (!org.springframework.util.StringUtils.hasText(cityCode)) {
            return new CityBean();
        }

        DistrictParam param = new DistrictParam();
        param.setCityCodes(new String[]{cityCode});
        param.setFlag(NumberConst.IntDef.INT_ZERO); //查询物流区
        List<CityBean> cities = CommRestUtil.getProvinceCityList(param);
        return CollectionUtils.isEmpty(cities) ? new CityBean() : cities.get(0);
    }

    /**
     * 根据市和县代码，查询大区、省、市和县
     * Created by xia_xiaojie on 2016/6/17.
     *
     * @param cityCode     市代码
     * @param districtCode 县代码
     * @return 大区、省、市和县
     */
    private DistrictBean findDistrict(String cityCode, String districtCode) {
        if (!org.springframework.util.StringUtils.hasText(cityCode) || !org.springframework.util.StringUtils.hasText(districtCode)) {
            return new DistrictBean();
        }

        DistrictParam param = new DistrictParam();
        param.setComposeCodes(new String[]{cityCode + districtCode});
        List<DistrictBean> districts = CommRestUtil.getDistrictList(param);
        return CollectionUtils.isEmpty(districts) ? new DistrictBean() : districts.get(0);
    }

}