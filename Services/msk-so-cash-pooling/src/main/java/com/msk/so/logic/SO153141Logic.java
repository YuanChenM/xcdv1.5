package com.msk.so.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.so.bean.SO153171Param;
import com.msk.so.bean.SOCp153141Bean;
import com.msk.so.utils.CheckUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by yang_yang
 */
@Service
public class SO153141Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153141Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    public CommonLogic commonLogic;

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_CHECK_USERID = "checkUserId";
        String SQL_ID_FIND_SOCPACCOUNTBOOK = "findSoCpAccountBook";
    }

    /**
     * 检查so_cp_account_book表中是否存在数据，如果不存在，调用接口插入数据
     */
    @Transactional
    public void checkData(LoginUser loginUser) {
        BasePageParam pageParam = new BasePageParam();
        pageParam.setPaging(false);
        List<SOCp153141Bean> soCp153141BeanList = super.findPageList(pageParam, SOCp153141Bean.class);

        // 设置请求参数
        RsRequest<SO153171Param> requestParam = new RsRequest<SO153171Param>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        requestParam.setParam(null);
        // url
        // String url = "http://127.0.0.1:8080/msk-seller/api/sl/slInfo/slBaseInfo/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlBaseInfoSearchServices();
        // 请求接口
        RsResponse<SO153171Param[]> response = RestClientUtil.post(url, requestParam,
            new TypeReference<RsResponse<SO153171Param[]>>() {});
        if (null != response.getResult() && "S".equals(response.getStatus())) {
            List<SO153171Param> paramList = Arrays.asList(response.getResult());
            // 如果list为空,插入数据
            if (CollectionUtils.isEmpty(soCp153141BeanList)) {
                // 插入调用接口中数据
                soCp153141BeanList = this.convertToSO153141List(paramList);
                for (SOCp153141Bean soCp153141Bean : soCp153141BeanList) {
                    Long maxId = getMaxId();
                    Date now = DateTimeUtil.getCustomerDate();
                    soCp153141Bean.setAccountBookId(maxId);
                    soCp153141Bean.setCrtId(loginUser.getEmplId());
                    soCp153141Bean.setCrtTime(now);
                    soCp153141Bean.setUpdId(loginUser.getEmplId());
                    soCp153141Bean.setUpdTime(now);
                    soCp153141Bean.setActId(loginUser.getEmplId());
                    soCp153141Bean.setActTime(now);
                    this.save(soCp153141Bean);
                }
            } else {
                List<SOCp153141Bean> so153141ParamList = this.convertToSO153141List(paramList);
                // 同步数据数据
                HashMap<String, SOCp153141Bean> paramMap = new HashMap<String, SOCp153141Bean>();
                for (SOCp153141Bean param : so153141ParamList) {
                    paramMap.put(param.getUserId(), param);
                }
                // 将paramMap中userId重复的去掉
                for (SOCp153141Bean bean : soCp153141BeanList) {
                    String userId = bean.getUserId();
                    paramMap.remove(userId);
                }
                // 将paramMap中剩下的数据 则是新增的用户数据，需插入数据库
                if (paramMap.size() > NumberConst.IntDef.INT_ZERO) {
                    Set<String> set = paramMap.keySet();
                    List<SOCp153141Bean> batchSaveList = new ArrayList<SOCp153141Bean>();
                    for (String lg : set) {
                        SOCp153141Bean bean = paramMap.get(lg);
                        Long maxId = getMaxId();
                        Date now = DateTimeUtil.getCustomerDate();
                        bean.setAccountBookId(maxId);
                        bean.setCrtId(loginUser.getEmplId());
                        bean.setCrtTime(now);
                        bean.setUpdId(loginUser.getEmplId());
                        bean.setUpdTime(now);
                        bean.setActId(loginUser.getEmplId());
                        bean.setActTime(now);
                        this.save(bean);
                    }
                }
            }
        }
    }

    private Long getMaxId() {
        return commonLogic.maxId("SO_CP_ACCOUNT_BOOK", "ACCOUNT_BOOK_ID");
    }

    private List<SOCp153141Bean> convertToSO153141List(List<SO153171Param> paramList) {
        if (CollectionUtils.isNotEmpty(paramList) && paramList.size() > NumberConst.IntDef.INT_ZERO) {
            List<SOCp153141Bean> resultList = new ArrayList<SOCp153141Bean>();
            for (int i = NumberConst.IntDef.INT_ZERO; i < paramList.size(); i++) {
                SOCp153141Bean so153141Bean = new SOCp153141Bean();
                so153141Bean.setUserId(paramList.get(i).getSlCode());
                so153141Bean.setUserNo(paramList.get(i).getSlCodeDis());
                so153141Bean.setUserRole(Integer.parseInt(paramList.get(i).getSlMainClass()));
                so153141Bean.setUserName(paramList.get(i).getSlName());
                resultList.add(so153141Bean);
            }
            return resultList;
        }
        return null;
    }

    /**
     * 账套一览
     *
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SOCp153141Bean> findSO153141List(BasePageParam pageParam) {

        logger.info("账套一览");

        // 验证日期
        boolean flag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("timeStart")),
            StringUtil.toSafeString(pageParam.getFilterMap().get("timeEnd")));
        if (!flag) {
            throw new BusinessException("日期选择不合理");
        }

        DbUtils.buildLikeCondition(pageParam, "accountBookName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "userNo", DbUtils.LikeMode.FRONT);

        String userRole = StringUtil.toSafeString(pageParam.getFilterMap().get("userRole"));
        if (!StringUtil.isNullOrEmpty(userRole)) {
            String[] userRoles = userRole.split(",");
            pageParam.getFilterMap().put("userRoles", userRoles);
        }

        PageResult<SOCp153141Bean> result = this.findPage(pageParam, SOCp153141Bean.class);

        return result;
    }

    /**
     * 查询 账套表 USER_ID
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<SOCp153141Bean> checkUserId(List<String> userIdList) {
        List<SOCp153141Bean> soCp153141BeanList = new ArrayList<SOCp153141Bean>();
        if (CollectionUtils.isNotEmpty(userIdList)) {
            BaseParam baseParam = new BaseParam();
            baseParam.getFilterMap().put("userIdList", userIdList);
            soCp153141BeanList = super.findList(SqlId.SQL_ID_CHECK_USERID, baseParam);
        }
        return soCp153141BeanList;
    }

    /**
     * 根据 USER_ID 查询 账套表
     *
     * @return
     */
    @Transactional(readOnly = true)
    public SOCp153141Bean findSoCpAccountBook(String accountBookId) {
        SOCp153141Bean soCp153141Bean = new SOCp153141Bean();
        if (!StringUtil.isEmpty(accountBookId)) {
            BaseParam baseParam = new BaseParam();
            baseParam.getFilterMap().put("accountBookId", accountBookId);
            soCp153141Bean = super.findOne(SqlId.SQL_ID_FIND_SOCPACCOUNTBOOK, baseParam);
        }
        return soCp153141Bean;
    }
}
