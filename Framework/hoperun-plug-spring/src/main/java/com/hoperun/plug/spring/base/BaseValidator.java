package com.hoperun.plug.spring.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hoperun.core.bean.ExceptionMessage;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.ValidatorException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Base Validator.
 * 
 * @param <T> 校验对象类
 * @author jiang_nan
 */
public abstract class BaseValidator<T> {
    /** logger 日志 */
    private static Logger logger = LoggerFactory.getLogger(BaseValidator.class);

    /**
     * Validator Message Code定义
     * 
     * @author jiang_nan
     *
     */
    public interface Validator {
        /** MESSAGE_MAX:当前值已经大于最大值{0}. */
        public String MESSAGE_MAX = "validator.message.max";
        /** MESSAGE_MIN:当前值已经小于最小值{0}. */
        public String MESSAGE_MIN = "validator.message.min";
        /** MESSAGE_BETWEEN:应在{0}到{1}之间 */
        public String MESSAGE_BETWEEN = "validator.message.between";
        /** MESSAGE_COMPARE:{0}应该小于{1} */
        public String MESSAGE_COMPARE = "validator.message.compare";
        /** MESSAGE_EXIST:{0}已经存在了 */
        public String MESSAGE_EXIST = "validator.message.exist";
        /** MESSAGE_EMPTY:该项目不能为空！ */
        public String MESSAGE_EMPTY = "validator.message.empty";
        /** MESSAGE_EXCLUSIVE:信息已经被其他用户变更，请查询后再执行操作。 */
        public String MESSAGE_EXCLUSIVE = "validator.message.exclusive";
        /** MESSAGE_NUMBER:该项目必须是整数。 */
        public String MESSAGE_NUMBER = "validator.message.number";
        /** MESSAGE_DECIMAL:该项目必须是数值。 */
        public String MESSAGE_DECIMAL = "validator.message.decimal";
        /** MESSAGE_REQUIREDNUMBER:当前输入必须是数字类型 */
        public String MESSAGE_REQUIREDNUMBER = "validator.message.requiredNumber";
        /** MESSAGE_DATE:该项目必须是有效的日期! */
        public String MESSAGE_DATE = "validator.message.date";
        /** MESSAGE_PHONE:该项目的电话格式不正确! */
        public String MESSAGE_PHONE = "validator.message.phone";
        /** MESSAGE_MAIL:邮件格式不正确！ */
        public String MESSAGE_MAIL = "validator.message.mail";
        /** MESSAGE_MAXLENGTH:该项目不能超过{0}位！ */
        public String MESSAGE_MAXLENGTH = "validator.message.maxlength";
    }

    private List<ExceptionMessage> exceptionMessageList = new ArrayList<ExceptionMessage>();

    /**
     * 校验处理，有错误时扔出异常（接口方法）
     *
     * @param object 校验参数
     * @throws ValidatorException 校验异常
     */
    public void validator(T object) throws ValidatorException {
        if (object != null) {
            this.validatorData(object);
            if (exceptionMessageList != null && !exceptionMessageList.isEmpty()) {
                ValidatorException ex = new ValidatorException(exceptionMessageList);
                throw ex;
            }
        } else {
            logger.warn("校验参数不存在！");
        }
    }

    /**
     * 校验处理，有错误时不扔出异常（接口方法）
     *
     * @param object 校验参数
     * @return 错误消息
     */
    public List<ExceptionMessage> warnValidator(T object) {
        this.validatorData(object);
        return exceptionMessageList;
    }

    /**
     * 校验处理（抽象方法，由各子类实现）
     *
     * @param entity 校验参数
     */
    public abstract void validatorData(T entity);

    /**
     * @return the exceptionMessageList
     */
    public List<ExceptionMessage> getExceptionMessageList() {
        return exceptionMessageList;
    }

    /**
     * 验证指定的字段值是否为空
     * 
     * @param fieldName 字段名称
     * @param data 验证的数据
     */
    protected void validatorRequired(String fieldName, Object data) {
        validatorRequired(null, fieldName, data);
    }

    /**
     * 验证一行中指定的字段值是否为空
     * 
     * @param rowNum 行号
     * @param fieldName 字段名称
     * @param value 验证的数据
     */
    protected void validatorRequired(Integer rowNum, String fieldName, Object value) {
        boolean isNull = false;
        if (value instanceof String) {
            if (StringUtil.isEmpty((String) value)) {
                isNull = true;
            }
        } else if (null == value) {
            isNull = true;
        }
        if (isNull) {
            if (rowNum != null) {
                this.addErrorMessage(rowNum, fieldName, Validator.MESSAGE_EMPTY, null);
            } else {
                this.addErrorMessage(fieldName, Validator.MESSAGE_EMPTY, null);
            }
        }
    }

    /**
     * 验证指定的字段值是否超过最大长度
     * 
     * @param value 验证的数据
     * @param maxLength 最大长度
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorMaxLength(String value, int maxLength, String fieldName, boolean required) {
        validatorMaxLength(null, value, maxLength, fieldName, required);
    }

    /**
     * 验证指定的字段值是否超过最大长度
     * 
     * @param rowNum 行号
     * @param value 验证的数据
     * @param maxLength 最大长度
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorMaxLength(Integer rowNum, String value, int maxLength, String fieldName, boolean required) {

        if (this.validatorInit(rowNum, value, fieldName, required)) {
            return;
        }

        if (value.length() > maxLength) {
            if (rowNum != null) {
                this.addErrorMessage(rowNum, fieldName, Validator.MESSAGE_MAXLENGTH,
                    new String[] { String.valueOf(maxLength) });
            } else {
                this.addErrorMessage(fieldName, Validator.MESSAGE_MAXLENGTH,
                    new String[] { String.valueOf(maxLength) });
            }
        }
    }

    /**
     * 验证指定的字段值是否合法Email地址
     * 
     * @param value 验证的数据
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorEmail(String value, String fieldName, boolean required) {
        validatorEmail(null, value, fieldName, required);
    }

    /**
     * 验证指定的字段值是否合法Email地址
     * 
     * @param rowNum 行号
     * @param value 验证的数据
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorEmail(Integer rowNum, String value, String fieldName, boolean required) {

        if (validatorInit(rowNum, value, fieldName, required)) {
            return;
        }

        if (!ValidatorUtils.checkMail(value)) {
            if (rowNum != null) {
                this.addErrorMessage(rowNum, fieldName, Validator.MESSAGE_MAIL, null);
            } else {
                this.addErrorMessage(fieldName, Validator.MESSAGE_MAIL, null);
            }
        }
    }

    /**
     * 验证指定的字段值是否合法电话
     * 
     * @param value 验证的数据
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorPhone(String value, String fieldName, boolean required) {
        validatorPhone(null, value, fieldName, required);
    }

    /**
     * 验证指定的字段值是否合法电话
     * 
     * @param rowNum 行号
     * @param value 验证的数据
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorPhone(Integer rowNum, String value, String fieldName, boolean required) {
        if (validatorInit(rowNum, value, fieldName, required)) {
            return;
        }
        if (!ValidatorUtils.checkPhone(value)) {
            if (rowNum != null) {
                this.addErrorMessage(rowNum, fieldName, Validator.MESSAGE_PHONE, null);
            } else {
                this.addErrorMessage(fieldName, Validator.MESSAGE_PHONE, null);
            }
        }
    }

    /**
     * 验证指定的字段值是否超过最大值
     * 
     * @param value 验证的数据
     * @param maxValue 最大值
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorMax(BigDecimal value, BigDecimal maxValue, String fieldName, boolean required) {
        validatorMax(null, value, maxValue, fieldName, required);
    }

    /**
     * 验证指定的字段值是否超过最大值
     * 
     * @param rowNum 行号
     * @param value 验证的数据
     * @param maxValue 最大值
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorMax(Integer rowNum, BigDecimal value, BigDecimal maxValue, String fieldName,
        boolean required) {
        if (validatorInit(rowNum, value, fieldName, required)) {
            return;
        }

        if (value.compareTo(maxValue) > NumberConst.IntDef.INT_ZERO) {
            if (rowNum != null) {
                this.addErrorMessage(rowNum, fieldName, Validator.MESSAGE_MAX, new String[] { maxValue.toString() });
            } else {
                this.addErrorMessage(fieldName, Validator.MESSAGE_MAX, new String[] { maxValue.toString() });
            }
        }

    }

    /**
     * 检查数值区间
     * 
     * @param value 当前值
     * @param maxValue 最大值
     * @param minValue 最小值
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorBetween(BigDecimal value, BigDecimal maxValue, BigDecimal minValue, String fieldName,
        boolean required) {
        validatorBetween(null, value, maxValue, minValue, fieldName, required);
    }

    /**
     * 检查数值区间
     * 
     * @param rowNum 行号
     * @param value 当前值
     * @param maxValue 最大值
     * @param minValue 最小值
     * @param fieldName 字段名称
     * @param required 是否必须
     */
    protected void validatorBetween(Integer rowNum, BigDecimal value, BigDecimal maxValue, BigDecimal minValue,
        String fieldName, boolean required) {
        if (validatorInit(rowNum, value, fieldName, required)) {
            return;
        }

        if (value.compareTo(minValue) == -1 || value.compareTo(maxValue) == 1) {
            if (rowNum != null) {
                this.addErrorMessage(rowNum, fieldName, Validator.MESSAGE_BETWEEN,
                    new String[] { minValue.toString(), maxValue.toString() });
            } else {
                this.addErrorMessage(fieldName, Validator.MESSAGE_BETWEEN,
                    new String[] { minValue.toString(), maxValue.toString() });
            }
        }
    }

    /**
     * 验证指定的字段值是否必须
     * 
     * @param rowNum 行号
     * @param value 验证的数据
     * @param fieldName 字段名称
     * @param required 是否必须
     * @return 校验结果
     */
    private boolean validatorInit(Integer rowNum, Object value, String fieldName, boolean required) {
        if (!required && null == value) {
            return true;
        }
        int size = this.getExceptionMessageList().size();
        this.validatorRequired(rowNum, fieldName, value);
        int newSize = this.getExceptionMessageList().size();
        return size != newSize;
    }

    /**
     * 保存校验消息
     * 
     * @param fieldName 字段名称
     * @param messageCode 消息编码
     * @param args 消息参数
     */
    private void addErrorMessage(String fieldName, String messageCode, String[] args) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(fieldName, messageCode, args);
        this.exceptionMessageList.add(exceptionMessage);
    }

    /**
     * 保存校验消息
     * 
     * @param rowNum 行号
     * @param fieldName 字段名称
     * @param messageCode 消息编码
     * @param args 消息参数
     */
    private void addErrorMessage(int rowNum, String fieldName, String messageCode, String[] args) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(rowNum, fieldName, messageCode, args);
        this.exceptionMessageList.add(exceptionMessage);
    }

}
