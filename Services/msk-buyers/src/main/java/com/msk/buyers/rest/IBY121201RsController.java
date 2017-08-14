package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BuyerAccountRsParam;
import com.msk.buyers.bean.IBY121201RsParam;
import com.msk.buyers.bean.IBY121202RsParam;
import com.msk.buyers.logic.IBY121201Logic;
import com.msk.buyers.logic.IBY121202Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.ByAccessAccount;
import com.msk.core.entity.ByBuyerAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * IBY121201RsController.
 *
 * @author zhou_yajun
 */
@RestController
public class IBY121201RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121201RsController.class);

    @Autowired
    private IBY121201Logic iby121201Logic;
    @Autowired
    private IBY121202Logic iby121202Logic;

    /**
     * 买家注册接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/account/register",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ByBuyerAccount> buyerRegister(@RequestBody RsRequest<IBY121201RsParam> param) {
        RsResponse<ByBuyerAccount> rs = new RsResponse<>();
        if (param.getParam() != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);

            String message = iby121201Logic.buyerRegister(param.getParam());
            if (!StringUtil.isNullOrEmpty(message)) {
                rs.setMessage(message);
            /*rs.setMessage(message);*/
                rs.setStatus(SystemConst.RsStatus.FAIL);
            } else {
                rs.setMessage("买家注册成功");
                ByBuyerAccount account = iby121201Logic.getBuyerAccount(param.getParam());
                rs.setResult(account);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
            }

        } else {
            rs.setMessage("传参为空，买家注册失败");
            rs.setStatus(SystemConst.RsStatus.FAIL);
        }
        return rs;
    }

    /**
     * 买家登录接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/account/login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121202RsParam> buyerLogin(@RequestBody RsRequest<BuyerAccountRsParam> param) {
        RsResponse<IBY121202RsParam> rs = new RsResponse<>();
        if (param.getParam() != null) {
            ByBuyerAccount byBuyerAccount = new ByBuyerAccount();
            byBuyerAccount.setAccountName(param.getParam().getAccountName());
            byBuyerAccount.setAccountPass(param.getParam().getAccountPass());
            if (param.getParam().getLoginMode() != null) {
                if (param.getParam().getLoginMode() == NumberConst.IntDef.INT_ONE) {
                    //通过手机号验证码确定登陆
                    ByBuyerAccount buyerAccount = iby121201Logic.findBuyerAccountByTel(byBuyerAccount);
                    if (null != buyerAccount) {
                        IBY121202RsParam buyerInfo = iby121202Logic.findBuyerById(buyerAccount.getBuyerId());
                        rs.setStatus(SystemConst.RsStatus.SUCCESS);
                        rs.setResult(buyerInfo);
                        rs.setMessage("登录成功");
                    } else {
                        rs.setStatus(SystemConst.RsStatus.FAIL);
                        rs.setMessage("买家不存在");
                    }
                } else {
                    if (iby121201Logic.findAccountByName(byBuyerAccount) == NumberConst.IntDef.INT_ONE) {
                        ByBuyerAccount accountInfo = iby121201Logic.buyerLogin(byBuyerAccount);
                        if (null != accountInfo) {
                            IBY121202RsParam buyerInfo = iby121202Logic.findBuyerById(accountInfo.getBuyerId());
                            rs.setStatus(SystemConst.RsStatus.SUCCESS);
                            rs.setResult(buyerInfo);
                            rs.setMessage("登录成功");
                        } else {
                            rs.setStatus(SystemConst.RsStatus.FAIL);
                            rs.setMessage("输入密码错误");
                        }
                    } else {
                        rs.setStatus(SystemConst.RsStatus.FAIL);
                        rs.setMessage("输入账号错误");
                    }
                }

            } else {
                if (iby121201Logic.findAccountByName(byBuyerAccount) == NumberConst.IntDef.INT_ONE) {
                    ByBuyerAccount accountInfo = iby121201Logic.buyerLogin(byBuyerAccount);
                    if (null != accountInfo) {
                        IBY121202RsParam buyerInfo = iby121202Logic.findBuyerById(accountInfo.getBuyerId());
                        rs.setStatus(SystemConst.RsStatus.SUCCESS);
                        rs.setResult(buyerInfo);
                        rs.setMessage("登录成功");
                    } else {
                        rs.setStatus(SystemConst.RsStatus.FAIL);
                        rs.setMessage("输入密码错误");
                    }
                } else {
                    rs.setStatus(SystemConst.RsStatus.FAIL);
                    rs.setMessage("输入账号错误");
                }
            }
        }
        return rs;
    }


    /**
     * 买家重设密码
     *
     * @param param param
     * @return 结果
     * @author zhou_ling
     */
    @RequestMapping(value = "/by/password/reset",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Object> resetPasswordUpdate(@RequestBody RsRequest<ByBuyerAccount> param) {

        if (param.getParam() != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);
        }
        RsResponse<Object> rs = new RsResponse<>();
        //重置新密码是手机号后六位
        String password = param.getParam().getTelNo();
        String accountPassword = password.substring(password.length() - 8, password.length());
        param.getParam().setAccountPass(accountPassword);

        int resultCount = iby121201Logic.resetPassword(param.getParam());
        if (resultCount == NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("密码重置失败！");
        } else {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("密码重置成功！");
        }
        return rs;
    }

    /**
     * 通过买家id,买家账号,买家旧密码,更新新密码
     *
     * @param param param
     * @return 结果
     * @author yuan_chen
     */
    @RequestMapping(value = "/by/account/updatePwd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Object> buyerUpdate(@RequestBody RsRequest<IBY121201RsParam> param) {
        if (param.getParam() != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
        }

        RsResponse<Object> rs = new RsResponse<>();
        BaseParam baseParam = new BaseParam();
        baseParam.setUpdId(param.getParam().getUpdId());
        baseParam.getFilterMap().put("buyerId", param.getParam().getBuyerId());
        baseParam.getFilterMap().put("accountName", param.getParam().getAccountName());
        baseParam.getFilterMap().put("oldAccountPass", param.getParam().getOldAccountPass());
        baseParam.getFilterMap().put("newAccountPass", param.getParam().getNewAccountPass());
        baseParam.getFilterMap().put("updId", param.getParam().getUpdId());
        baseParam.getFilterMap().put("updTime", param.getParam().getUpdTime());

        if (iby121201Logic.findAccountByName(param.getParam()) == 1) {
            int updateResult = iby121201Logic.updatePassword(baseParam);
            if (updateResult == NumberConst.IntDef.INT_ONE) {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("密码更新成功!");
            } else {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("密码更新失败!");
            }
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("输入账号错误");
        }

        return rs;
    }

    /**
     * 前台手机验证成功后,通过手机号直接修改密码
     *
     * @param param param
     * @return 结果
     * @author yuan_chen
     */
    @RequestMapping(value = "/by/account/updatePwdByTel",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Object> buyerUpdateByTel(@RequestBody RsRequest<IBY121201RsParam> param) {
        if (param.getParam() != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
        }
        RsResponse<Object> rs = new RsResponse<>();
        BaseParam baseParam = new BaseParam();
        baseParam.setUpdId(param.getParam().getUpdId());
        baseParam.getFilterMap().put("telNo", param.getParam().getTelNo());
        baseParam.getFilterMap().put("newAccountPass", param.getParam().getNewAccountPass());

        baseParam.getFilterMap().put("updId", param.getParam().getUpdId());
        baseParam.getFilterMap().put("updTime", param.getParam().getUpdTime());

        param.getParam().setAccountName(param.getParam().getTelNo());
        if (iby121201Logic.findAccountByName(param.getParam()) == 1) {
            int updateResult = iby121201Logic.updatePasswordByTel(baseParam);
            if (updateResult == NumberConst.IntDef.INT_ONE) {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("密码更新成功!");
            } else {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("密码更新失败!");
            }
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("输入手机号错误!");
        }

        return rs;
    }

    /**
     * 通路人员登录接口
     *
     * @param param param
     * @return 结果
     * @author yuan_chen
     */
    @RequestMapping(value = "/by/access/login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ByAccessAccount> accessLogin(@RequestBody RsRequest<ByAccessAccount> param) {
        RsResponse<ByAccessAccount> rs = new RsResponse<>();
        ByAccessAccount byAccessAccount = iby121201Logic.accessLogin(param.getParam());
        if (null == byAccessAccount) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("用户名或密码错误,请重新输入");
        } else {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(byAccessAccount);
            rs.setMessage("登录成功");
        }
        return rs;
    }

    /**
     * SSO登录验证接口
     *
     * @param param param
     * @return 结果
     * @author yuan_chen
     */
    @RequestMapping(value = "/by/sso/login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> ssoLogin(@RequestBody RsRequest<IBY121201RsParam> param) {
        RsResponse<String> rs = new RsResponse<>();
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap(BuyersConst.ByInterfaceToken.TYPE);
        ByAccessAccount byAccessAccount = iby121201Logic.validateSSOLogin(param.getParam().getAccountName());
        if (codeMasterMap.containsKey(param.getParam().getInterfaceToken()) && null != byAccessAccount) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("登录成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("用户名或密码错误,请重新输入");
        }
        return rs;
    }

    /**
     * 通过手机号码查询买家基本信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/basicInfo/queryByTel", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121202RsParam> queryBuyeryBasicInfoByTel(@RequestBody RsRequest<ByBuyerAccount> param) {
        RsResponse<IBY121202RsParam> rs = new RsResponse<>();
        ByBuyerAccount accountInfo = iby121201Logic.queryBuyeryBasicInfoByTel(param.getParam());
        if (null != accountInfo) {
            IBY121202RsParam buyerInfo = iby121202Logic.findBuyerById(accountInfo.getBuyerId());
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(buyerInfo);
            rs.setMessage("处理成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该手机号码还未注册");
        }
        return rs;
    }

}
