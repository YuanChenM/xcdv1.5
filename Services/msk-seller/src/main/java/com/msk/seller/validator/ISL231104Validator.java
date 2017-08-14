package com.msk.seller.validator;

import com.hoperun.plug.spring.base.BaseValidator;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlAccount;
import com.hoperun.core.exception.BusinessException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by cx on 2016/2/16.
 */
public class ISL231104Validator extends BaseValidator<RsRequest<SlAccount>> {

    @Override
    public void validatorData(RsRequest<SlAccount> entity) {
        SlAccount slAccountParam = entity.getParam();
        if(null != slAccountParam){
            this.validateUserName(slAccountParam.getSlAccount());
            this.isMobile(slAccountParam.getSlTel());
            this.validatorRequired("卖家显示名称",slAccountParam.getSlShowName());
            this.validatorRequired("联系人姓名",slAccountParam.getSlContact());
            this.isLength(slAccountParam.getAccountPsd());
            this.validatorRequired("认证状态",slAccountParam.getAuthStatus());
            this.validatorRequired("版本号",slAccountParam.getVer());
            this.validatorRequired("更新者ID",slAccountParam.getUpdId());
        }
    }
    /**
     * 校验用户名 4-20位字符，支持中英文字母和阿拉伯数字及“-”“_”组合
     * @param
     * @return
     */
        public static boolean validateUserName(String userName) {
            String validateStr = "^[\\w\\-－＿[０-９]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$";
            boolean rs = false;
            int strLenth = getStrLength(userName);
            rs = matcher(validateStr, userName);
            if (!rs) {
                rs = false;
                throw new BusinessException("用户名格式为4-20位字符，支持中英文字母和阿拉伯数字及“-”“_”");
            }else if (strLenth < 4 || strLenth > 20) {
                throw new BusinessException("用户名长度为4-20位字符");
            }
            return rs;
        }

         //获取字符串的长度，对双字符（包括汉字）按两位计数
        public static int getStrLength(String value) {
            int valueLength = 0;
            String chinese = "[\u0391-\uFFE5]";
            for (int i = 0; i < value.length(); i++) {
                String temp = value.substring(i, i + 1);
                if (temp.matches(chinese)) {
                    valueLength += 2;
                } else {
                    valueLength += 1;
                }
            }
            return valueLength;
        }
      // 验证手机号
        public static boolean isMobile(String userName) {
            String validateStr = "^[1][3,4,5,8][0-9]{9}$";
            boolean rs = false;
            rs = matcher(validateStr, userName);
            if (!rs) {
                int strLenth = getStrLength(userName);
                if (strLenth != 11) {
                    rs = false;
                    throw new BusinessException("手机号必须位11位");
                }
            }
            return rs;
        }
    // 校验6-11位字符
        public static boolean isLength(String userName) {
            String validateStr = "[A-Za-z0-9]{6,12}";
            boolean rs = false;
            rs = matcher(validateStr, userName);
            if(!rs){
                rs = false;
                throw new BusinessException(" 登录密码为6-11位字符");
            }
            return rs;
        }
        private static boolean matcher(String reg, String string) {
            boolean tem = false;
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(string);
            tem = matcher.matches();
            return tem;
        }
}
