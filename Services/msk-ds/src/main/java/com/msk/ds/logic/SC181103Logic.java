package com.msk.ds.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CommCodeMasterConst;
import com.msk.common.logic.CommonLogic;
import com.msk.ds.bean.SC181103Bean;
import com.msk.ds.bean.SC181103Param;
import com.msk.ds.bean.SC182101Bean;
import com.msk.ds.rest.comm.RestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231199RsBean;
import com.msk.seller.bean.ISL231199RsPageBean;
import com.msk.seller.bean.ISL231199RsParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


/**
 * 发货计划单录入
 *
 * @author li_kai1 on 2016/6/12.
 */
@Service
public class SC181103Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC181103Logic.class);
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 创建供应分销流水ID
     * @return 供应分销流水ID
     */
    public long creatSuppDsId() {
        return commonLogic.maxId("DS_SUPP_DIST_NUM","SUPP_DS_ID");
    }

    /**
     * 创建虚拟库存实际流水ID
     * @return 虚拟库存实际流水ID
     */
    public long creatStockActualId() {
        return commonLogic.maxId("DS_PD_VIRTUAL_STOCK_ACTUAL","VIRTUAL_STOCK_ACTUAL_ID");
    }

    /**
     * 创建虚拟库存计划流水ID
     * @return 虚拟库存计划流水ID
     */
    public long creatStockPlanId() {
        return commonLogic.maxId("DS_PD_VIRTUAL_STOCK_PLAN","VIRTUAL_STOCK_PLAN_ID");
    }

    /**
     * 保存所有数据到DB
     * @param sc181103Param
     */
    @Transactional
    public void saveParam(SC181103Param sc181103Param){
        logger.info("要保存的数据处理开始");
        saveDistNum(sc181103Param);
        List<SC181103Param> dataList = setStockPlanAndActual(sc181103Param);
        saveStockPlanAndActual(dataList);
    }

    /**
     * 保存数据到供应商分销数量表
     * @param sc181103Param
     */
    @Transactional
    public void saveDistNum(SC181103Param sc181103Param) {
        logger.info("保存数据到供应商分销数量表开始");
        /**设置供应分销流水ID*/
        sc181103Param.setSuppDsId(creatSuppDsId());
        /**设置分销通道:默认4*/
        sc181103Param.setDistType(NumberConst.IntDef.INT_FOUR);
        /**设置分销数量*/
//        setDistNum(sc181103Param);
        Date customerDate = DateTimeUtil.getCustomerDate();
        /**设置共通字段*/
        //modify by zhang_jiaxing start in 2016/9/12
        sc181103Param.setActTime(customerDate);
        sc181103Param.setCrtTime(customerDate);
        sc181103Param.setUpdTime(customerDate);
        //modify by zhang_jiaxing end in 2016/9/12
        //Modify for 2719 at 2016/09/22 by likai Start
        //设置产品编码（原9位）
//        sc181103Param.setPdCode(sc181103Param.getPdCode() + sc181103Param.getGradeCode());
        //Modify for 2719 at 2016/09/22 by likai End
        /**验证插入数据非空*/
        if (StringUtil.isNullOrEmpty(sc181103Param.getDistMonth())||StringUtil.isNullOrEmpty(sc181103Param.getLgcsCode())
                ||StringUtil.isNullOrEmpty(sc181103Param.getSuppCode())||StringUtil.isNullOrEmpty(sc181103Param.getPdCode())
                ||StringUtil.isNullOrEmpty(sc181103Param.getGradeCode())
                ||sc181103Param.getBrandType()<=NumberConst.IntDef.INT_ZERO
                ||!DateTimeUtil.isValidDate(sc181103Param.getCrtTime())
                )  {
            throw new BusinessException("要发货的产品不存在！");
        }
        //分销数量表结果集
        int resultNum = this.save(SqlId.SQL_SAVE_DIST_NUM,sc181103Param);
        if(resultNum > NumberConst.IntDef.INT_ZERO) {
            logger.info("供应商分销数量表数据插入成功");
        }
        else{
            logger.info("供应商分销数量表数据插入失败");
            throw new BusinessException("数据有误插入失败！请联系管理员");
        }
    }

    /**
     * 设置产品虚拟库存计划表和实际表信息
     * @param sc181103Param
     */

    public List<SC181103Param> setStockPlanAndActual(SC181103Param sc181103Param) {
        logger.info("设置plan和actual表数据");

        //保存数据的list
        List<SC181103Param> dataList = new ArrayList<SC181103Param>();

        /**计算半旬和分销月度，插入四条数据*/
        String halfCode = sc181103Param.getHalfCode();
        String distMonth = sc181103Param.getDistMonth();
        for (int i = NumberConst.IntDef.INT_ONE; i < NumberConst.IntDef.INT_FIVE; i++){
            SC181103Param param = new SC181103Param();
            BeanUtils.copyProperties(sc181103Param,param);
//            Integer distMonthNum = StringUtil.toInteger(distMonth);
            Integer halfCodeNum = StringUtil.toInteger(halfCode);
            //Modify for 3660 on 2016/11/24 by likai Start
            Date distMonthDate = DateTimeUtil.parseDate(distMonth,DateTimeUtil.FORMAT_YEAR_MONTH);

            int tempHalfCode = (halfCodeNum + i - NumberConst.IntDef.INT_TWO) % NumberConst.IntDef.INT_SIX;
            if (tempHalfCode == NumberConst.IntDef.INT_ZERO) {
                halfCodeNum = NumberConst.IntDef.INT_SIX ;
            }else{
                halfCodeNum = tempHalfCode ;
            }
            switch (halfCodeNum) {
                case NumberConst.IntDef.INT_ONE :
                    if (i == NumberConst.IntDef.INT_THREE || i == NumberConst.IntDef.INT_FOUR){
                        distMonthDate = DateTimeUtil.addMonth(distMonthDate,NumberConst.IntDef.INT_ONE);
                    }
                    break;
                case NumberConst.IntDef.INT_TWO :
                    if (i == NumberConst.IntDef.INT_FOUR){
                        distMonthDate = DateTimeUtil.addMonth(distMonthDate,NumberConst.IntDef.INT_ONE);
                    }
                    break;
                case NumberConst.IntDef.INT_SIX :
                    if (i == NumberConst.IntDef.INT_ONE){
                        distMonthDate = DateTimeUtil.addMonth(distMonthDate,NumberConst.IntDef.INT_N_ONE);
                    }
                    break;
            }
            /**设置计划录入日期和计划数量*/
            BigDecimal packNum = StringUtil.toBigDecimal(param.getPackNum());
            /**取保存数据到分销数量表的时间为录入时间*/
            param.setAdJustDate(sc181103Param.getCrtTime());
            param.setOrigPlanNum(packNum);
            param.setOldPlanNum(packNum);
            param.setNewPlanNum(packNum);

            /**设置实际录入日期和数量数量*/
            param.setOldActualNum(packNum);
            param.setNewActualNum(packNum);
            param.setInputDate(sc181103Param.getCrtTime());

            //设置虚拟库存计划流水Id
            param.setStockPlanId(creatStockPlanId());
            //设置虚拟库存实际流水Id
            param.setStockActualId(creatStockActualId());
            //设置分销月度
            String distMonthStr = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH,distMonthDate);
            param.setDistMonth(distMonthStr);
            //Modify for 3660 on 2016/11/24 by likai End
            //设置半旬码
            param.setHalfCode(halfCodeNum.toString());

            //设置产品库存类型
            Integer stockTypeNum = (Integer)i;
            param.setPdStockType(stockTypeNum.toString());
            /**验证数据是否为空*/
            if (StringUtil.isNullOrEmpty(param.getPdStockType())||StringUtil.isNullOrEmpty(param.getHalfCode())
                    ||StringUtil.isNullOrEmpty(param.getClassesCode())||StringUtil.isNullOrEmpty(param.getFeatureCode())
                    ||StringUtil.isNullOrEmpty(param.getOutSpec())||StringUtil.isNullOrEmpty(param.getBreedCode())
                    ||StringUtil.isNullOrEmpty(param.getNormsCode())||param.getOutNetWeight() == null
                    )  {
                throw new BusinessException("要保存的产品不存在！");
            }
            dataList.add(param);
        }
        return dataList;
    }

    /**
     * 产品虚拟库存实际表和计划表数据处理
     * @param dataList
     */
    @Transactional
    public void saveStockPlanAndActual(List<SC181103Param> dataList) {
        logger.info("plan和actual表插入数据开始执行");
        if (CollectionUtils.isNotEmpty(dataList)) {
            List<SC181103Param> alreadyExistPlanParam = new ArrayList<SC181103Param>();
            List<SC181103Param> alreadyExistActualParam = new ArrayList<SC181103Param>();
            for (SC181103Param checkParam : dataList) {
                if (!initData(checkParam,SqlId.SQL_BATCH_SAVE_PLAN)) {
                    alreadyExistPlanParam.add(checkParam);
                }

                if (!initData(checkParam,SqlId.SQL_BATCH_SAVE_ACTUAL)) {
                    alreadyExistActualParam.add(checkParam);
                }

            }
            //Add for #2704 at 2016/09/18 by li_kai1 Start
            BaseParam selectParam = new BaseParam();
            Map<String, SC181103Bean> planResultMap = new HashMap<String, SC181103Bean>();
            Map<String, SC181103Bean> actualResultMap = new HashMap<String, SC181103Bean>();
            if (CollectionUtils.isNotEmpty(alreadyExistPlanParam)) {
                logger.info("plan已存在数据,处理开始");
                selectParam.setFilterObject("planList", alreadyExistPlanParam);
                List<SC181103Bean> planList = this.findList(SqlId.SQL_FIND_SUM_PLAN_EXISTED, selectParam);
                for (SC181103Bean planResult : planList) {
                    String key = planResult.getDistMonth()+planResult.getLgcsCode()+planResult.getSuppCode()
                            +planResult.getPdStockType()+planResult.getHalfCode()+planResult.getNormsCode()+planResult.getPdCode();
                    planResultMap.put(key, planResult);
                    planResult.setUpdId(alreadyExistPlanParam.get(NumberConst.IntDef.INT_ZERO).getUpdId());
                    planResult.setUpdTime(DateTimeUtil.getCustomerDate());
                    if(this.modify(SqlId.SQL_MODIFY_PLAN_DELETE_FLG_EXISTED,planResult) == NumberConst.IntDef.INT_ZERO) {
                        throw new BusinessException("产品虚拟库存计划表删除失败，请和管理员联系!");
                    }

                }
                for (SC181103Param param : alreadyExistPlanParam) {
                    SC181103Param saveParam = new SC181103Param();
                    BeanUtils.copyProperties(param, saveParam);
                    String key = saveParam.getDistMonth()+saveParam.getLgcsCode()+saveParam.getSuppCode()
                            +saveParam.getPdStockType()+saveParam.getHalfCode()+saveParam.getNormsCode()+saveParam.getPdCode();
                    BigDecimal sumOrigPlanNum = planResultMap.get(key).getOrigPlanNum();
                    BigDecimal sumOldPlanNum = planResultMap.get(key).getOldPlanNum();
                    BigDecimal sumNewPlanNum = planResultMap.get(key).getNewPlanNum();
                    saveParam.setOrigPlanNum(DecimalUtil.add(saveParam.getOrigPlanNum(), sumOrigPlanNum));
                    saveParam.setOldPlanNum(DecimalUtil.add(saveParam.getOldPlanNum(), sumOldPlanNum));
                    saveParam.setNewPlanNum(DecimalUtil.add(saveParam.getNewPlanNum(), sumNewPlanNum));
                    //Add for 3369 at 2016/10/19 by likai Start
                    this.validateDeliveryNum(saveParam);
                    //Add for 3369 at 2016/10/19 by likai End
                    if (this.save(SqlId.SQL_SAVE_STOCK_PLAN_NOT_INIT, saveParam) == NumberConst.IntDef.INT_ZERO) {
                        throw new BusinessException("产品虚拟库存计划表新增失败，请和管理员联系!");
                    }
                }
                logger.info("plan已存在数据,处理结束");
            }
            if (CollectionUtils.isNotEmpty(alreadyExistActualParam)) {
                logger.info("actual已存在数据,处理开始");

                selectParam.setFilterObject("actualList", alreadyExistActualParam);
                List<SC181103Bean> actualList = this.findList(SqlId.SQL_FIND_SUM_ACTUAL_EXISTED, selectParam);
                for (SC181103Bean actualResult : actualList) {
                    String key = actualResult.getDistMonth()+actualResult.getLgcsCode()+actualResult.getSuppCode()
                            +actualResult.getPdStockType()+actualResult.getHalfCode()+actualResult.getNormsCode()+actualResult.getPdCode();
                    actualResultMap.put(key, actualResult);
                    actualResult.setUpdId(alreadyExistActualParam.get(NumberConst.IntDef.INT_ZERO).getUpdId());
                    actualResult.setUpdTime(DateTimeUtil.getCustomerDate());
                    if(this.modify(SqlId.SQL_MODIFY_ACTUAL_DELETE_FLG_EXISTED,actualResult) == NumberConst.IntDef.INT_ZERO) {
                        throw new BusinessException("产品虚拟库存实际表删除失败，请和管理员联系!");
                    }

                }
                for (SC181103Param param : alreadyExistActualParam) {
                    SC181103Param saveParam = new SC181103Param();
                    BeanUtils.copyProperties(param, saveParam);
                    String key = saveParam.getDistMonth()+saveParam.getLgcsCode()+saveParam.getSuppCode()
                            +saveParam.getPdStockType()+saveParam.getHalfCode()+saveParam.getNormsCode()+saveParam.getPdCode();
                    BigDecimal sumOldActualNum = actualResultMap.get(key).getOldActualNum();
                    BigDecimal sumNewActualNum = actualResultMap.get(key).getNewActualNum();
                    saveParam.setOldActualNum(DecimalUtil.add(saveParam.getOldActualNum(), sumOldActualNum));
                    saveParam.setNewActualNum(DecimalUtil.add(saveParam.getNewActualNum(), sumNewActualNum));
                    //Add for 3369 at 2016/10/19 by likai Start
                    this.validateDeliveryNum(saveParam);
                    //Add for 3369 at 2016/10/19 by likai End
                    if (this.save(SqlId.SQL_SAVE_STOCK_ACTUAL_NOT_INIT, saveParam) == NumberConst.IntDef.INT_ZERO) {
                        throw new BusinessException("产品虚拟库存实际表新增失败，请和管理员联系!");
                    }
                }
                logger.info("actual已存在数据,处理结束");
            }
            //Add for #2704 at 2016/09/18 by li_kai1 End

        }

    }

    /**
     * 判断DB是否存在当前月度的数据(不存在则初始化数据)
     * @param sc181103Param
     *
     */
    @Transactional
    public Boolean checkMonthData(SC181103Param sc181103Param,String sqlId) {
        logger.info("验证数据是否存在");
        //查询DB是否存在该数据
        if (SqlId.SQL_BATCH_SAVE_PLAN.equals(sqlId)) {
            sqlId = SqlId.SQL_CHECK_PLAN_NUM;
        }else {
            sqlId = SqlId.SQL_CHECK_ACTUAL_NUM;
        }
        int dbCheckNum = this.getCount(sqlId,sc181103Param);

        if (dbCheckNum == NumberConst.IntDef.INT_ZERO) {

            return false;
        } else {
            return true;
        }

    }

    /**
     * 初始化DB数据
     * @param sc181103Param
     * @param sql
     */
    @Transactional
    public boolean initData(SC181103Param sc181103Param, String sql) {
        logger.info("初始化数据开始");
        boolean result = false;
        List<SC181103Param> initDataList = new ArrayList<SC181103Param>();

        for (int i = NumberConst.IntDef.INT_ONE; i<NumberConst.IntDef.INT_FIVE; i++) {
            for (int j = NumberConst.IntDef.INT_ONE; j<NumberConst.IntDef.INT_SEVEN; j++) {
                SC181103Param initParam = new SC181103Param();
                BeanUtils.copyProperties(sc181103Param,initParam);
                if (sql.equals(SqlId.SQL_BATCH_SAVE_PLAN)) {
                    if (!StringUtil.toString(i).equals(initParam.getPdStockType()) || !StringUtil.toString(j).equals(initParam.getHalfCode())){
                        //设置虚拟库存计划流水Id
                        initParam.setStockPlanId(creatStockPlanId());
                        initParam.setOrigPlanNum(BigDecimal.ZERO);
                        initParam.setOldPlanNum(BigDecimal.ZERO);
                        initParam.setNewPlanNum(BigDecimal.ZERO);
                    }

                } else {
                    if (!StringUtil.toString(i).equals(initParam.getPdStockType()) || !StringUtil.toString(j).equals(initParam.getHalfCode())){
                        //设置虚拟库存实际流水Id
                        initParam.setStockActualId(creatStockActualId());
                        initParam.setOldActualNum(BigDecimal.ZERO);
                        initParam.setNewActualNum(BigDecimal.ZERO);
                    }

                }
                initParam.setAdJustDate(sc181103Param.getAdJustDate());
                initParam.setInputDate(sc181103Param.getInputDate());
                initParam.setPdStockType(StringUtil.toString(i));
                initParam.setHalfCode(StringUtil.toString(j));
                if (!checkMonthData(initParam,sql)) {

                    initDataList.add(initParam);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(initDataList)) {

            int initDataResult = this.getBaseDao().batchInsert(sql,initDataList);
            if (initDataResult <= NumberConst.IntDef.INT_ZERO) {
                throw  new BusinessException("初始化对应月份数据失败!");
            } else {
                logger.info("初始化数据成功!");
                result = true;
            }
        }
        //重新设置参数的crtTime,并作为录入时间
        Date newAdJustDate = DateTimeUtil.add(sc181103Param.getAdJustDate(),NumberConst.IntDef.INT_ZERO,
                NumberConst.IntDef.INT_ZERO,NumberConst.IntDef.INT_ZERO,NumberConst.IntDef.INT_ZERO,
                NumberConst.IntDef.INT_ZERO,NumberConst.IntDef.INT_TWO);
        sc181103Param.setCrtTime(newAdJustDate);
        return result;
    }

    //Add for 3369 at 2016/1019 by likai Start
    public void validateDeliveryNum(SC181103Param param) {
        if (null != param) {
            int zero = NumberConst.IntDef.INT_ZERO;
            int fourteen = NumberConst.IntDef.INT_FOURTEEN;
            String oldPlanNum = param.getOldPlanNum().abs().toPlainString();
            String[] oldPlanArray = oldPlanNum.split("\\.");
            String oldPlanDot = oldPlanArray[zero];

            String newPlanNum = param.getNewPlanNum().abs().toPlainString();
            String[] newPlanArray = newPlanNum.split("\\.");
            String newPlanDot = newPlanArray[zero];

            String origPlanNum = param.getOrigPlanNum().abs().toPlainString();
            String[] origPlanArray = origPlanNum.split("\\.");
            String origPlanDot = origPlanArray[zero];

            String newActualNum = param.getNewActualNum().abs().toPlainString();
            String[] newActualArray = newActualNum.split("\\.");
            String newActualDot = newActualArray[zero];

            String oldActualNum = param.getOldActualNum().abs().toPlainString();
            String[] oldActualArray = oldActualNum.split("\\.");
            String oldActualDot = oldActualArray[zero];

            if (fourteen < oldPlanDot.length()) {
                throw new BusinessException("原计划发货数量整数长度大于14位!oldPlanNum:" + oldPlanDot);
            }
            if (fourteen < newPlanDot.length()) {
                throw new BusinessException("新计划发货数量整数长度大于14位!newPlanNum:" + newPlanDot);
            }
            if (fourteen < origPlanDot.length()) {
                throw new BusinessException("原始计划发货数量整数长度大于14位!origPlanNum:" + origPlanDot);
            }
            if (fourteen < oldActualDot.length()) {
                throw new BusinessException("原实际发货数量整数长度大于14位!oldActualNum:" + oldActualDot);
            }
            if (fourteen < newActualDot.length()) {
                throw new BusinessException("新实际发货数量整数长度大于14位!newActualNum:" + newActualDot);
            }
        }
    }

    /**
     * OEM申报情况一览
     * @param pageParam
     * @return
     */
    @Transactional
    public PageResult<SC181103Bean> findSC181103BeansList(SC181103Param pageParam) {
        logger.info("发货录入单一览画面显示数据查询");
        /**调用接口*/
        ISL231199RsParam isl231199RsParam = new ISL231199RsParam();
        isl231199RsParam.setSlCode(pageParam.getSuppCode());
        isl231199RsParam.getFilterMap().put("breedName", pageParam.getFilterMap().get("breedName"));
        isl231199RsParam.getFilterMap().put("featureName",pageParam.getFilterMap().get("featureName"));
        /**是否需要计算startSize*/
        isl231199RsParam.setPageNo(pageParam.getStartPos()/pageParam.getPageSize()+NumberConst.IntDef.INT_ONE);
//        isl231199RsParam.setStartSize(2);
        isl231199RsParam.setPageCount(pageParam.getPageSize());
        PageResult<SC181103Bean> result = new PageResult<SC181103Bean>();

        /**拼装品牌类型的组合条件*/
        String brandType = StringUtil.toSafeString(pageParam.getFilterMap().get("brandType"));
       if(!StringUtil.isNullOrEmpty(brandType)){
            String[] brandTypes = brandType.split(",");
           isl231199RsParam.getFilterMap().put("brandTypes",brandTypes);
       }

        ISL231199RsPageBean isl231199RsPageBean = RestUtil.getLgcsSellerInfoSearch(isl231199RsParam);
        List<ISL231199RsBean> rsPageResult = isl231199RsPageBean.getPageResult();
        List<SC181103Bean> data = new ArrayList<SC181103Bean>();
        if (CollectionUtils.isNotEmpty(rsPageResult)){

            for (ISL231199RsBean isl231199RsBean : rsPageResult){
                SC181103Bean sc181103Bean = new SC181103Bean();
                BeanUtils.copyProperties(isl231199RsBean, sc181103Bean);
//                    sc181103Bean.setLgcsCode(isl231199RsBean.getLgcsCode());
//                    sc181103Bean.setLgcsName(isl231199RsBean.getLgcsName());
//                    sc181103Bean.setSuppCode(isl231199RsBean.getSuppCode());
//                    sc181103Bean.setSuppName(isl231199RsBean.getSuppName());
//                    sc181103Bean.setSlQltGradeCode(isl231199RsBean.getSlQltGradeCode());
//                    sc181103Bean.setSlMainClass(isl231199RsBean.getSlmainClass());
//                    sc181103Bean.setClassesCode(isl231199RsBean.getPdClassesCode());
//                    sc181103Bean.setMachiningCode(isl231199RsBean.getPdMachiningCode());
//                    sc181103Bean.setBreedCode(isl231199RsBean.getPdBreedCode());
//                    sc181103Bean.setFeatureCode(isl231199RsBean.getPdFeatureCode());
//                    sc181103Bean.setWeightCode(isl231199RsBean.getPdWeightCode());
//                    sc181103Bean.setPdCode(isl231199RsBean.getPdCode());
                data.add(sc181103Bean);
            }
        }
        result.setData(data);
        result.setRecordsTotal(isl231199RsPageBean.getTotalCount());
        /**设置产品信息*/
        setProductInfo(result.getData(), pageParam);
        if(result.getRecordsTotal() > 0){
            //获取集合
            List<SC181103Bean> list = result.getData();
            List<String> slCodeList = new ArrayList<String>();
            //将供应商编码作为查询条件传入调用卖家接口获得生产商名称
            for(SC181103Bean bean:list){
                slCodeList.add(bean.getSuppCode());
            }
            //调用接口获取生产商名称并放入结果集中
            Map<String,String> map = RestUtil.getEPNameBySuppCode(slCodeList);

            //在返回对象里放入调用接口得来的生产商名
            for(SC181103Bean bean:list){
                bean.setEpName("");
                String value = map.get(bean.getSuppCode());
                if(!StringUtil.isEmpty(value)){
                    bean.setEpName(value);
                }
            }
        }

        return result;
    }

    /**
     * 设置分页结果产品信息
     */
    public void setProductInfo (List<SC181103Bean> resultList, SC181103Param pageParam) {
        logger.info("设置分页结果集的产品信息");
        if (CollectionUtils.isNotEmpty(resultList)){
            List<String> pdCodes = new ArrayList<String>();
            Map<String, PDInfoResult> pdMap = new HashMap<String, PDInfoResult>();
            for (SC181103Bean sc181103Bean : resultList) {
                pdCodes.add(sc181103Bean.getPdCode());
            }
            PDInfoParam pdInfoParam = new PDInfoParam();
            pdInfoParam.setPdCodes(pdCodes);
            //Modify for 2719 at 2016/09/22 by likai Start
//            pdInfoParam.setGradeFlag("YES");
            pdInfoParam.setGradeFlag("YES");
            //Modify for 2719 at 2016/09/22 by likai End
            List<PDInfoResult> pdInfoResults = RestUtil.getProductInfoList(pdInfoParam);
            for (PDInfoResult pdInfoResult : pdInfoResults) {
                pdMap.put(pdInfoResult.getPdCode(),pdInfoResult);
            }
            for(SC181103Bean sc181103Bean : resultList){
                PDInfoResult productInfo = pdMap.get(sc181103Bean.getPdCode());
                if (productInfo == null) {
                    productInfo = new PDInfoResult();
                }
                sc181103Bean.setClassesCode(StringUtil.toSafeString(productInfo.getClassesCode()));
                sc181103Bean.setClassesName(StringUtil.toSafeString(productInfo.getClassesName()));
                sc181103Bean.setMachiningCode(StringUtil.toSafeString(productInfo.getMachiningCode()));
                sc181103Bean.setMachiningName(StringUtil.toSafeString(productInfo.getMachiningName()));
                sc181103Bean.setBreedName(StringUtil.toSafeString(productInfo.getBreedName()));
                sc181103Bean.setBreedCode(StringUtil.toSafeString(productInfo.getBreedCode()));
                sc181103Bean.setFeatureCode(StringUtil.toSafeString(productInfo.getFeatureCode()));
                sc181103Bean.setFeatureName(StringUtil.toSafeString(productInfo.getFeatureName()));
                sc181103Bean.setWeightCode(StringUtil.toSafeString(productInfo.getWeightCode()));
                sc181103Bean.setWeightName(StringUtil.toSafeString(productInfo.getWeightName()));
                sc181103Bean.setOutNetWeight(DecimalUtil.getBigDecimal(productInfo.getWeightVal()));
                //Add for 2719 at 2016/09/22 by likai Start
                sc181103Bean.setGradeCode(StringUtil.toSafeString(productInfo.getGradeCode()));
                sc181103Bean.setGradeName(StringUtil.toSafeString(productInfo.getGradeName()));
                //Add for 2719 at 2016/09/22 by likai End
                sc181103Bean.setNormsCode(StringUtil.toSafeString(productInfo.getNormsCode()));
                sc181103Bean.setNormsName(StringUtil.toSafeString(productInfo.getNormsName()));
//                sc181103Bean.setOutNetWeight(productInfo.getNetweightOut());
                sc181103Bean.setOutSpec(StringUtil.toSafeString(productInfo.getNormsOut()));
                //TODO
                /**设置产品品牌类型(目前是把产品质量等级设置为品牌类型后续会有变化)*/
                if (sc181103Bean.getSlQltGradeCode() != null) {
                    sc181103Bean.setBrandType(sc181103Bean.getSlQltGradeCode());
                } else {
                    sc181103Bean.setBrandType(NumberConst.IntDef.INT_ZERO);
                }
                /**产品等级默认置为A2*/
                //Modify for 2719 at 2016/09/22 by likai Start
//                sc181103Bean.setGradeCode("2");
//                sc181103Bean.setGradeName("A2");
                //Modify for 2719 at 2016/09/22 by likai End
                /**设置产品分销月度*/
                sc181103Bean.setDistMonth(pageParam.getDistMonth());
                //发货数量默认置为0
                sc181103Bean.setPackNum(Integer.toString(NumberConst.IntDef.INT_ZERO));
                sc181103Bean.setDeliveryBoxes(Integer.toString(NumberConst.IntDef.INT_ZERO));
                //设置半旬码默认值
                sc181103Bean.setHalfCode(StringUtil.toString(getHalfCode(
                        DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DD, DateTimeUtil.getCustomerDate()))));
                sc181103Bean.setHalfName(getHalfName(StringUtil.toInteger(sc181103Bean.getHalfCode())));

            }
        }

    }
    //Add for 3369 at 2016/1019 by likai End

    /**
     * 设置分销月度
     */
    public String getDistMonth () {
        //获取分销月度
        Date currentDate = DateTimeUtil.getCustomerDate();
        String currentDay = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DD, currentDate);
        // 分销月份
        String distMonth = null;
        if (StringUtil.toInteger(currentDay) >= halfDeparture.DEPARTURE_1) {
            // 21号开始属于下个分销月度
            Date addMonthDate = DateTimeUtil.addMonth(currentDate,NumberConst.IntDef.INT_ONE);
            distMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, addMonthDate);
        } else {
            distMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, currentDate);
        }
        return distMonth;
    }

    /**
     * 获取区域List
     * @return
     */
    public List<SC181103Bean> findAreaList() {
        logger.info("获取区域List");
//        List<SC181103Bean> result = super.findList(SqlId.SQL_ID_FIND_AREA_LIST, param);
        List<SC181103Bean> result = new ArrayList<SC181103Bean>();
        List<ISL231199RsBean> resultList = RestUtil.getAreaList();
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (ISL231199RsBean isl231199RsBean : resultList) {
                SC181103Bean sc181103Bean = new SC181103Bean();
                sc181103Bean.setLgcsCode(isl231199RsBean.getLgcsCode());
                sc181103Bean.setLgcsName(isl231199RsBean.getLgcsName());
                result.add(sc181103Bean);
            }
        }
        return result;
    }

    /**
     * 获取供应商List
     * @return
     */
    public List<SC181103Bean> findSuppList(SC181103Param param) {
        logger.info("获取供应商List");
        List<SC181103Bean> result = new ArrayList<SC181103Bean>();
        ISL231199RsParam isl231199RsParam = new ISL231199RsParam();
        List<ISL231199RsBean> resultList = RestUtil.getSuppName(isl231199RsParam);
        String slCode="";
        if(CommCodeMasterConst.LoginUserType.SUPPLIER_USER_TYPE.equals(param.getUserType())){
            ISL231193RsParam param1 = new ISL231193RsParam();
            param1.setSlAccount(param.getCrtId());
            slCode = RestUtil.queryslEpData(param1).getSlCode();

           for(ISL231199RsBean isl231199RsBean : resultList){
               if(isl231199RsBean.getSuppCode().equals(slCode)){
                   SC181103Bean sc181103Bean = new SC181103Bean();
                   sc181103Bean.setSuppCode(isl231199RsBean.getSuppCode());
                   sc181103Bean.setSuppName(isl231199RsBean.getSuppName());
                   result.add(sc181103Bean);
                   return result;
               }
           }
        }
            for (ISL231199RsBean isl231199RsBean : resultList) {
                SC181103Bean sc181103Bean = new SC181103Bean();
                sc181103Bean.setSuppCode(isl231199RsBean.getSuppCode());
                sc181103Bean.setSuppName(isl231199RsBean.getSuppName());
                result.add(sc181103Bean);
            }


        return result;
    }

    /**
     * 获取半旬
     *
     * @param currentDay 传入对象
     * @return 半旬
     */
    public int getHalfCode(String currentDay) {
        int halfCode = NumberConst.IntDef.INT_ZERO;
        if (Integer.parseInt(currentDay) < halfDeparture.DEPARTURE_1) {
            if (Integer.parseInt(currentDay) >= halfDeparture.DEPARTURE_6) {
                halfCode = NumberConst.IntDef.INT_SIX;
            } else {
                if (Integer.parseInt(currentDay) >= halfDeparture.DEPARTURE_5) {
                    halfCode = NumberConst.IntDef.INT_FIVE;
                } else {
                    if (Integer.parseInt(currentDay) >= halfDeparture.DEPARTURE_4) {
                        halfCode = NumberConst.IntDef.INT_FOUR;
                    } else {
                        halfCode = NumberConst.IntDef.INT_THREE;
                    }
                }
            }
        } else {
            if (Integer.parseInt(currentDay) < halfDeparture.DEPARTURE_2) {
                halfCode = NumberConst.IntDef.INT_ONE;
            } else {
                halfCode = NumberConst.IntDef.INT_TWO;
            }
        }
        return halfCode;
    }

    /**
     * 获取半旬名称
     *
     * @param halfCode 传入对象
     * @return 半旬名称
     */
    public String getHalfName(int halfCode) {
        String halfName = null;
        halfName = CodeMasterManager.getCodeMasterName("HalfCodeType", StringUtil.toSafeString(halfCode));
        return halfName;
    }

    interface SqlId {
        String SQL_SAVE_DIST_NUM = "saveDistNum";   //保存到供应商分销数量表
//        String SQL_SAVE_PD_VIRTUAL_STOCK_ACTUAL = "saveStockActual";    //保存到产品虚拟库存实际表
//        String SQL_SAVE_PD_VIRTUAL_STOCK_PLAN = "saveStockPlan";    //保存到产品虚拟库存计划表
        String SQL_CHECK_PLAN_NUM = "checkPlanNum";
        String SQL_CHECK_ACTUAL_NUM = "checkActualNum";
        String SQL_BATCH_SAVE_PLAN = "batchSavePlan";
        String SQL_BATCH_SAVE_ACTUAL ="batchSaveActual";
        String SQL_FIND_SUM_PLAN_EXISTED = "findSumPlanExisted";
        String SQL_FIND_SUM_ACTUAL_EXISTED = "findSumActualExisted";
        String SQL_MODIFY_PLAN_DELETE_FLG_EXISTED = "modifyPlanDeleteFlgExisted";
        String SQL_MODIFY_ACTUAL_DELETE_FLG_EXISTED = "modifyActualDeleteFlgExisted";
        String SQL_SAVE_STOCK_PLAN_NOT_INIT = "saveStockPlanNotInit";
        String SQL_SAVE_STOCK_ACTUAL_NOT_INIT = "saveStockActualNotInit";
//        String SQL_MODIFY_DIST_NUM = "modifyDistNum";       //更新产品分销数量表
        String ss = "ss";
    }

    interface halfDeparture {
        // 21号
        int DEPARTURE_1 = NumberConst.IntDef.INT_TWENTY_ONE;
        // 26号
        int DEPARTURE_2 = NumberConst.IntDef.INT_TWENTY_SIX;
        // 1号
        int DEPARTURE_3 = NumberConst.IntDef.INT_ONE;
        // 6号
        int DEPARTURE_4 = NumberConst.IntDef.INT_SIX;
        // 11号
        int DEPARTURE_5 = NumberConst.IntDef.INT_ELEVEN;
        // 16号
        int DEPARTURE_6 = NumberConst.IntDef.INT_SIXTEEN;
    }

};

