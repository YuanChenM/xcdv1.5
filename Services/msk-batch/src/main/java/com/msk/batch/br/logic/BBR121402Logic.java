package com.msk.batch.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.br.bean.BBR12140201Bean;
import com.msk.batch.br.bean.BBR12140201Param;
import com.msk.batch.br.bean.BBR12140202Bean;
import com.msk.batch.br.bean.BBR121404Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.BrByPoolFileInfo;
import com.msk.core.entity.BrOClaMachiningInfo;
import com.msk.core.entity.BrSetting;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 * BBR121402Logic
 *
 * @author jiang_tengfei
 */
@Service
public class BBR121402Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121402Logic.class);

    /**
     * SQL Map 中SQL ID定义
     *
     * @author jiang_tengfei
     */
    interface SqlId {
        /**
         * 获取产品池基础数据
         */
        static String FIND_ALL_PARAMS = "findAllParams";
        /**
         * 根据条件查询所有产品池数据
         */
        static String FIND_ALL_PD_POOL = "findAllPdPool";
        /**
         * 根据条件查询所有买家池数据
         */
        static String FIND_ALL_BUYER_POOL = "findAllBuyerPool";
        /**
         * 根据一二级分类查询所有产品分类
         */
        static String FIND_MACHINING_INFO_BY_CLASSES = "findMachiningInfoByClasses";
        /**
         * 查询需求等级配置
         */
        static String FIND_SETTING_BY_TYPE = "findSettingByType";
        /**
         * 插入EXCEL文件信息表信息
         */
        static String INSERT_FILE_INFO = "insertFileInfo";
        /**
         * 查询文件是否存在
         */
        static String IS_FILE_EXIST = "isFileExist";
        /**
         * 删除已存重复的上传文件信息
         */
        static String DELETE_FILE_INFO = "deleteFileInfo";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 删除已存重复的上传文件信息
     *
     * @param lastMonthStartDay
     * @param lastMonthEndDay
     */
    @Transactional
    public void deleteFileInfo(Date lastMonthStartDay, Date lastMonthEndDay) {
        BaseParam baseParam = new BaseParam();
        baseParam.setCrtTime(lastMonthStartDay);
        baseParam.setUpdTime(lastMonthEndDay);
        super.remove(SqlId.DELETE_FILE_INFO, baseParam);
    }

    /**
     * 根据标记获取所有条件
     *
     * @param flag 1为查询所有分销物流区 2为查询所有分销地区 3查询所有区县菜场 4物流区菜场 4地区菜场
     * @return
     */
    @Transactional(readOnly = true)
    public List<BBR12140201Param> getParamsByFlag(int flag) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", flag);
        List<BBR12140201Bean> bbr121402Beans = super.findList(map, SqlId.FIND_ALL_PARAMS);
        List<BBR12140201Param> bbr121402Params = null;
        if (!CollectionUtils.isEmpty(bbr121402Beans)) {
            bbr121402Params = new ArrayList<>();
            for (BBR12140201Bean bean : bbr121402Beans) {
                BBR12140201Param param = new BBR12140201Param();
                param.setBuyerCityCode(bean.getBuyerCityCode());
                param.setBuyerCityName(bean.getBuyerCityName());
                param.setBuyerDistrictCode(bean.getBuyerDistrictCode());
                param.setBuyerDistrictName(bean.getBuyerDistrictName());
                param.setBuyerLgcsAreaCode(bean.getBuyerLgcsAreaCode());
                param.setBuyerLgcsAreaName(bean.getBuyerLgcsAreaName());
                param.setBuyerType(bean.getBuyerType());
                param.setClassesCode(bean.getClassesCode());
                param.setClassesName(bean.getClassesName());
                param.setMachiningCode(bean.getMachiningCode());
                param.setMachiningName(bean.getMachiningName());
                param.setFlag(bean.getFlag());
                param.setMonth(bean.getMonth());
                bbr121402Params.add(param);
            }
        }
        return bbr121402Params;
    }

    /**
     * 插入EXCEL文件信息
     *
     * @param fileInfo
     */
    @Transactional
    public void addFileInfo(BrByPoolFileInfo fileInfo) {
        super.save(SqlId.INSERT_FILE_INFO, fileInfo);
    }

    /**
     * 装载买家池数据
     *
     * @param param
     * @return
     */
    public List<BBR121404Bean> buyersPoolDataResolve(BBR12140201Param param) {
        List<BBR121404Bean> bbr121404Beans = this.getBuyerPoolByParams(param);
        //新顺序list
        List<BBR121404Bean> bbr121404BeanLst = null;
        //声明新的买家池bean去接收拼装后的数据
        BBR121404Bean bean = null;
        if (!CollectionUtils.isEmpty(bbr121404Beans)) {
            bbr121404BeanLst = new ArrayList<>();
            //序号初始化为1
            int num = 1;
            //组装数据
            for (int i = 0; i < bbr121404Beans.size(); i++) {
                BBR121404Bean bbr121404Bean = bbr121404Beans.get(i);
                String buyerCode = bbr121404Bean.getBuyerCode();
                String buyerType = bbr121404Bean.getBuyerType();
                String marketCode = bbr121404Bean.getMarketCode();
                //生成顺序码
                bbr121404Bean.setSeqCode(this.dealSeqCode(buyerCode, buyerType));
                //生成序号
                if (i == 0) {
                    bbr121404Bean.setNum(String.valueOf(num));
                    bbr121404BeanLst.add(bbr121404Bean);
                } else {
                    BBR121404Bean previousBean = bbr121404Beans.get(i - 1);
                    if (!StringUtil.isEmpty(marketCode)) {
                        if (marketCode.equals(previousBean.getMarketCode())) {
                            num = num + 1;
                            bbr121404Bean.setNum(String.valueOf(num));
                            bean = this.dealBuyPoolData(bbr121404Bean);
                            bbr121404BeanLst.add(bean);
                        } else {
                            //添加一行小计
                            bbr121404BeanLst.add(this.getCountBean(previousBean, param.getFlag(), "小计", String.valueOf(num)));
                            //与上一个不相同 序号初始化为1
                            num = 1;
                            bbr121404Bean.setNum(String.valueOf(num));
                            bbr121404BeanLst.add(bbr121404Bean);
                        }
                    }
                    //最后一个添加总计
                    if (i == (bbr121404Beans.size() - 1)) {
                        //添加一行小计
                        bbr121404BeanLst.add(this.getCountBean(previousBean, param.getFlag(), "小计", String.valueOf(num)));
                        //添加一行总计
                        bbr121404BeanLst.add(this.getCountBean(previousBean, param.getFlag(), "总计", String.valueOf(bbr121404Beans.size())));
                    }
                }
            }
        }
        return bbr121404BeanLst;
    }

    /**
     * 拼装产品池数据
     *
     * @param param
     * @return
     */
    public List<BBR12140202Bean> pdPoolDataResolve(BBR12140201Param param) {
        List<BBR12140202Bean> bbr121403Beans = this.getPdPoolByParams(param);
        List<BrSetting> brSettings = this.getSettingByType();
        if (!CollectionUtils.isEmpty(bbr121403Beans)) {
            for (int i = 0; i < bbr121403Beans.size(); i++) {
                //排名
                int ranking = i + 1;
                BBR12140202Bean bbr121403Bean = bbr121403Beans.get(i);
                bbr121403Bean.setRaking(ranking);
                //循环遍历配置塞入需求等级 需求等级分为量大、量较大、一般
                if (!CollectionUtils.isEmpty(brSettings)) {
                    for (BrSetting setting : brSettings) {
                        String startValue = setting.getSettingStartValue();
                        String endValue = setting.getSettingEndValue();
                        if (!StringUtil.isEmpty(startValue) && !StringUtil.isEmpty(endValue)) {
                            if (ranking >= StringUtil.toInteger(startValue) && ranking <= StringUtil.toInteger(endValue)) {
                                bbr121403Bean.setDemandLevel(setting.getSettingName());
                                if (!StringUtil.isEmpty(setting.getSettingName())) {
                                    //更多产品： 一揽子产品之外的单品，不限定单品数量
                                    if (!setting.getSettingName().equals("一般")) {
                                        bbr121403Bean.setProdType("一揽子产品");
                                    } else {
                                        bbr121403Bean.setProdType("更多产品");
                                    }
                                }
                                break;
                            }
                        } else if (StringUtil.isEmpty(startValue) && !StringUtil.isEmpty(endValue)) {
                            if (ranking <= StringUtil.toInteger(endValue)) {
                                bbr121403Bean.setDemandLevel(setting.getSettingName());
                                if (!StringUtil.isEmpty(setting.getSettingName())) {
                                    //更多产品： 一揽子产品之外的单品，不限定单品数量
                                    if (!setting.getSettingName().equals("一般")) {
                                        bbr121403Bean.setProdType("一揽子产品");
                                    } else {
                                        bbr121403Bean.setProdType("更多产品");
                                    }
                                }
                                break;
                            }
                        } else if (!StringUtil.isEmpty(startValue) && StringUtil.isEmpty(endValue)) {
                            if (ranking >= StringUtil.toInteger(startValue)) {
                                bbr121403Bean.setDemandLevel(setting.getSettingName());
                                if (!StringUtil.isEmpty(setting.getSettingName())) {
                                    //更多产品： 一揽子产品之外的单品，不限定单品数量
                                    if (!setting.getSettingName().equals("一般")) {
                                        bbr121403Bean.setProdType("一揽子产品");
                                    } else {
                                        bbr121403Bean.setProdType("更多产品");
                                    }
                                }
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        return bbr121403Beans;
    }

    /**
     * 根据一二级分类查询所有产品分类
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<BrOClaMachiningInfo> getMachiningInfoByParams(BBR12140201Param param) {
        List<BrOClaMachiningInfo> infos = super.findList(SqlId.FIND_MACHINING_INFO_BY_CLASSES, param);
        return infos;
    }

    /**
     * 处理买家池数据，每类地区、区县、市场只显示1个名字
     *
     * @param bbr121404Bean
     * @return
     */
    private BBR121404Bean dealBuyPoolData(BBR121404Bean bbr121404Bean) {
        BBR121404Bean bean = new BBR121404Bean();
        bean.setCityCode("");
        bean.setCityName("");
        bean.setMarketName("");
        bean.setMarketCode("");
        bean.setDistrictName("");
        bean.setDistrictCode("");
        bean.setMarketLevelName("");
        bean.setMarketLevel("");
        bean.setSeqCode(bbr121404Bean.getSeqCode());
        bean.setNum(bbr121404Bean.getNum());
        bean.setBuyerCode(bbr121404Bean.getBuyerCode());
        bean.setMarketingsStatusName(bbr121404Bean.getMarketingsStatusName());
        bean.setMarketingsStatus(bbr121404Bean.getMarketingsStatus());
        bean.setBuyerId(bbr121404Bean.getBuyerId());
        bean.setBuyerType(bbr121404Bean.getBuyerType());
        bean.setBuyerName(bbr121404Bean.getBuyerName());
        bean.setBuyerTypeName(bbr121404Bean.getBuyerTypeName());
        bean.setLgcsAreaCode(bbr121404Bean.getLgcsAreaCode());
        bean.setLgcsAreaName(bbr121404Bean.getLgcsAreaName());
        bean.setOtherPdCode(bbr121404Bean.getOtherPdCode());
        return bean;
    }

    /**
     * 生成Excel
     *
     * @param param
     * @param title     可以做表头和文件名
     * @param modelName 模板名称
     */
    private void createExcel(BBR12140201Param param, String title, String modelName, BaseParam baseParam) {
        //查询出数据
        List<BBR12140202Bean> pdPoolLst = this.pdPoolDataResolve(param);
        //UUID
        String fileId = UUID.randomUUID().toString();
        BrByPoolFileInfo fileInfo = new BrByPoolFileInfo();
        //拼装数据
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("输出EXCEL");
        map.put("pdList", pdPoolLst);
        if (param.getFlag() == 0) {
            List<BBR121404Bean> buyerPoolLst = this.buyersPoolDataResolve(param);
            map.put("byPoolList", buyerPoolLst);
        } else {
            fileInfo.setMachiningCode(param.getMachiningCode());
            fileInfo.setClassesCode(param.getClassesCode());
        }
        map.put("title", title);
        InputStream in = null;
        OutputStream out = null;
        String inputPath = "template/BBR121402Template/" + modelName + ".xlsx";
        String outputPath = FileUtils.getSystemTmpDir() + "/" + fileId + ".xlsx";
        fileInfo.setDistrictCode(param.getBuyerDistrictCode());
        fileInfo.setBuyerType(param.getBuyerType());
        fileInfo.setLgcsAreaCode(param.getBuyerLgcsAreaCode());
        fileInfo.setFileCreateTime(DateTimeUtil.getCustomerDate());
        fileInfo.setCityCode(param.getBuyerCityCode());
        fileInfo.setFileSuf("xlsx");
        fileInfo.setFileName(title);
        Date date = DateTimeUtil.getCustomerDate();
        fileInfo.setFileCreateTime(date);
        fileInfo.setFileEndTime(param.getLastMonthEndDay());
        fileInfo.setFileStartTime(param.getLastMonthStartDay());

        fileInfo.setActId(baseParam.getActId());
        fileInfo.setActTime(baseParam.getActTime());
        fileInfo.setCrtId(baseParam.getCrtId());
        fileInfo.setCrtTime(baseParam.getCrtTime());
        fileInfo.setUpdId(baseParam.getUpdId());
        fileInfo.setUpdTime(baseParam.getUpdTime());
        try {
            File excelFile = new File(outputPath);
            in = getClass().getClassLoader().getResourceAsStream(inputPath);
            out = new FileOutputStream(excelFile);
            JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
            jew.excelWrite(map);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(fileId, excelFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
//            if(!result.isEmpty()){
//                FileUtils.deleteFile(excelFile);
//            }
            fileInfo.setFileId(fileId);
            fileInfo.setFileServerId(result.get(fileId));
            String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
            fileInfo.setFileServerIp(fileServerIp);
            this.addFileInfo(fileInfo);
        } catch (FileNotFoundException e) {
            logger.error("模板文件不存在");
            throw new SystemException("模板文件不存在", e);
        } catch (IOException e) {
            logger.error("IO读写错误");
            throw new SystemException("IO读写错误");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("输入流关闭错误");
                    throw new SystemException("输入流关闭错误");
                }
            }
            if (out != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("输出流关闭错误");
                    throw new SystemException("输出流关闭错误");
                }
            }
        }
    }

    /**
     * 生成文件名并生成Excel
     *
     * @param param
     */
    public void dataResolve(BBR12140201Param param, BaseParam baseParam) {
        if (param.getFlag() == 1) {
            //标准产品池
            StringBuffer titleBuffer1 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer1.append("物流区");
            titleBuffer1.append(param.getClassesName());
            titleBuffer1.append(param.getMachiningName());
            titleBuffer1.append("产品分销买家标准产品池产品目录");
            titleBuffer1.append(param.getYear());
            titleBuffer1.append("年");
            titleBuffer1.append(param.getMonth());
            titleBuffer1.append("月在线管控表");
            this.createExcel(param, titleBuffer1.toString(), "BBR121401", baseParam);
            //标准池 买家池
            StringBuffer titleBuffer2 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer2.append("物流区分销买家标准产品池-买家池");
            titleBuffer2.append(param.getYear());
            titleBuffer2.append("年");
            titleBuffer2.append(param.getMonth());
            titleBuffer2.append("月在线管控表");
            boolean isExist = this.findFileExist(titleBuffer2.toString());
            if (!isExist) {
                //赋值为0 查询物流区、地区、区县所有
                param.setFlag(0);
                this.createExcel(param, titleBuffer2.toString(), "BBR121402", baseParam);
            }
        } else if (param.getFlag() == 2) {
            //标准产品池
            StringBuffer titleBuffer1 = new StringBuffer(param.getBuyerCityName());
            titleBuffer1.append("地区");
            titleBuffer1.append(param.getClassesName());
            titleBuffer1.append(param.getMachiningName());
            titleBuffer1.append("产品分销买家标准产品池产品目录");
            titleBuffer1.append(param.getYear());
            titleBuffer1.append("年");
            titleBuffer1.append(param.getMonth());
            titleBuffer1.append("月在线管控表");
            this.createExcel(param, titleBuffer1.toString(), "BBR121401", baseParam);
            //标准池 买家池
            StringBuffer titleBuffer2 = new StringBuffer(param.getBuyerCityName());
            titleBuffer2.append("地区分销买家标准产品池-买家池");
            titleBuffer2.append(param.getYear());
            titleBuffer2.append("年");
            titleBuffer2.append(param.getMonth());
            titleBuffer2.append("月在线管控表");
            boolean isExist = this.findFileExist(titleBuffer2.toString());
            if (!isExist) {
                //赋值为0 查询物流区、地区、区县所有
                param.setFlag(0);
                this.createExcel(param, titleBuffer2.toString(), "BBR121403", baseParam);
            }
        } else if (param.getFlag() == 3) {
            //标准产品池
            StringBuffer titleBuffer1 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer1.append("物流区");
            titleBuffer1.append(param.getBuyerCityName());
            titleBuffer1.append("地区");
            titleBuffer1.append(param.getBuyerDistrictName());
            titleBuffer1.append("区县");
            titleBuffer1.append(param.getClassesName());
            titleBuffer1.append("产品菜场买家标准产品池产品目录");
            titleBuffer1.append(param.getYear());
            titleBuffer1.append("年");
            titleBuffer1.append(param.getMonth());
            titleBuffer1.append("月在线管控表");
            this.createExcel(param, titleBuffer1.toString(), "BBR121401", baseParam);
            //标准池 买家池
            StringBuffer titleBuffer2 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer2.append("物流区");
            titleBuffer2.append(param.getBuyerCityName());
            titleBuffer2.append("地区");
            titleBuffer2.append(param.getBuyerDistrictName());
            titleBuffer2.append("区县菜场买家标准产品池-买家池");
            titleBuffer2.append(param.getYear());
            titleBuffer2.append("年");
            titleBuffer2.append(param.getMonth());
            titleBuffer2.append("月在线管控表");
            boolean isExist = this.findFileExist(titleBuffer2.toString());
            if (!isExist) {
                //赋值为0 查询物流区、地区、区县所有
                param.setFlag(0);
                this.createExcel(param, titleBuffer2.toString(), "BBR121406", baseParam);
            }
        } else if (param.getFlag() == 4) {
            //标准产品池
            StringBuffer titleBuffer1 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer1.append("物流区");
            titleBuffer1.append(param.getClassesName());
            titleBuffer1.append("产品菜场买家标准产品池产品目录");
            titleBuffer1.append(param.getYear());
            titleBuffer1.append("年");
            titleBuffer1.append(param.getMonth());
            titleBuffer1.append("月在线管控表");
            this.createExcel(param, titleBuffer1.toString(), "BBR121401", baseParam);
            //标准池 买家池
            StringBuffer titleBuffer2 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer2.append("物流区菜场买家标准产品池-买家池");
            titleBuffer2.append(param.getYear());
            titleBuffer2.append("年");
            titleBuffer2.append(param.getMonth());
            titleBuffer2.append("月在线管控表");
            boolean isExist = this.findFileExist(titleBuffer2.toString());
            if (!isExist) {
                //赋值为0 查询物流区、地区、区县所有
                param.setFlag(0);
                this.createExcel(param, titleBuffer2.toString(), "BBR121404", baseParam);
            }
        } else if (param.getFlag() == 5) {
            //标准产品池
            StringBuffer titleBuffer1 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer1.append("物流区");
            titleBuffer1.append(param.getBuyerCityName());
            titleBuffer1.append("地区");
            titleBuffer1.append(param.getClassesName());
            titleBuffer1.append("产品菜场买家标准产品池产品目录");
            titleBuffer1.append(param.getYear());
            titleBuffer1.append("年");
            titleBuffer1.append(param.getMonth());
            titleBuffer1.append("月在线管控表");
            this.createExcel(param, titleBuffer1.toString(), "BBR121401", baseParam);
            //标准池 买家池
            StringBuffer titleBuffer2 = new StringBuffer(param.getBuyerLgcsAreaName());
            titleBuffer2.append("物流区");
            titleBuffer2.append(param.getBuyerCityName());
            titleBuffer2.append("地区菜场买家标准产品池-买家池");
            titleBuffer2.append(param.getYear());
            titleBuffer2.append("年");
            titleBuffer2.append(param.getMonth());
            titleBuffer2.append("月在线管控表");
            boolean isExist = this.findFileExist(titleBuffer2.toString());
            if (!isExist) {
                //赋值为0 查询物流区、地区、区县所有
                param.setFlag(0);
                this.createExcel(param, titleBuffer2.toString(), "BBR121405", baseParam);
            }
        }
    }

    /**
     * 拼装小计和总计行
     *
     * @param previousBean 前一个bean
     * @param flag         标记
     * @param commonStr    常量字段（小计，总计）
     * @param count        数量
     * @return
     */
    private BBR121404Bean getCountBean(BBR121404Bean previousBean, int flag, String commonStr, String count) {
        BBR121404Bean countBean = new BBR121404Bean();
        countBean.setNum(commonStr);
        countBean.setBuyerName(count);
        return countBean;
    }

    /**
     * 处理买家顺序码
     *
     * @param buyerCode 买家编码
     * @param buyerType 买家类型
     * @return
     */
    private String dealSeqCode(String buyerCode, String buyerType) {
        String seqCode = "";
        //买家顺序码生成 分销买家后3位 菜场买家后两位
        if (!StringUtil.isEmpty(buyerCode) && buyerCode.length() > 2) {
            if (!StringUtil.isEmpty(buyerType)) {
                if (buyerType.equals("01")) {
                    seqCode = buyerCode.substring(buyerCode.length() - 3, buyerCode.length());
                } else {
                    seqCode = buyerCode.substring(buyerCode.length() - 2, buyerCode.length());
                }
            }
        }
        return seqCode;
    }

    /**
     * 根据条件查询所有产品池数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    private List<BBR12140202Bean> getPdPoolByParams(BBR12140201Param param) {
        List<BBR12140202Bean> bbr121403Beans = super.findList(SqlId.FIND_ALL_PD_POOL, param);
        return bbr121403Beans;
    }

    /**
     * 根据条件查询所有买家池数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    private List<BBR121404Bean> getBuyerPoolByParams(BBR12140201Param param) {
        List<BBR121404Bean> bbr121404Beans = super.findList(SqlId.FIND_ALL_BUYER_POOL, param);
        return bbr121404Beans;
    }

    /**
     * 根据配置类型查询配置
     *
     * @return
     */
    @Transactional(readOnly = true)
    private List<BrSetting> getSettingByType() {
        BaseParam param = new BaseParam();
        List<BrSetting> settings = super.findList(SqlId.FIND_SETTING_BY_TYPE, param);
        return settings;
    }

    /**
     * 把产品信息合并入产品bean
     *
     * @param info
     * @param bean
     * @return
     */
    private BBR12140202Bean convertMachiningBeanToPdBean(BrOClaMachiningInfo info, BBR12140202Bean bean) {
        bean.setClaId(info.getClaId());
        bean.setClassCode(info.getClassesCode());
        bean.setChildType(info.getChildType());
        bean.setFeedPeriod(info.getFeedPeriod());
        bean.setFeedType(info.getFeedType());
        bean.setClassName(info.getClassesName());
        bean.setTraditionalName(info.getLocalName());
        bean.setMachinCode(info.getMachiningCode());
        bean.setMachinName(info.getMachiningName());
        bean.setPlaceCurrent(info.getPlaceCurrent());
        bean.setPlaceOrigin(info.getPlaceOrigin());
        bean.setScienceName(info.getScientificName());
        bean.setSource(info.getSource());
        bean.setSaleName(info.getSalesName());
        return bean;
    }

    /**
     * 查询文件是否存在
     *
     * @param fileName
     * @return
     */
    @Transactional(readOnly = true)
    private boolean findFileExist(String fileName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fileName", fileName);
        int flag = super.getCount(SqlId.IS_FILE_EXIST, map);
        if (flag > 0) {
            return true;
        } else {
            return false;
        }
    }

}
