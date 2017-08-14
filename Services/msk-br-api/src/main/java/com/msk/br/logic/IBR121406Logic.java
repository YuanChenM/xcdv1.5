package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.BR121406RsBean;
import com.msk.br.bean.BR121406RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 * 分类买家标准产品池
 * Created by tao_zhifa on 2016/7/26.
 */
@Service
public class IBR121406Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BR121401Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String FIND_CHICKEN_OR_DUCK_PRODUCTS = "findChickenOrDuckProducts";
        String FIND_CHICKEN_OR_DUCK_MACHINING_PRODUCTS = "findChickenOrDuckMachiningProducts";
        String IS_EXIST = "isExist";
        String UPDATE_FILE_BUYER_POOL_BY_FILENAME = "updateFileBuyerPoolByFileName";
        String DELETE_FLAG = "deleteFlag";
        String MODIFY_FILE_STATUS = "modifyFileStatus";
    }

    public int isExist(BaseParam param) {
        return this.getCount(SqlId.IS_EXIST, param);
    }

    /**
     * 删除已生成文件
     */
    @Transactional
    public int updateFileBuyerPoolByFileName(BaseParam param) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setUpdId("by");
        param.setUpdTime(currentDate);
        return this.modify(SqlId.UPDATE_FILE_BUYER_POOL_BY_FILENAME, param);
    }
    
    /**
     * 查询鸡/鸭一级产品excle数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<BR121406RsBean> findChickenOrDuckProducts(BR121406RsParam param) {
        return super.findList(SqlId.FIND_CHICKEN_OR_DUCK_PRODUCTS, param);
    }

    /**
     * 查询鸡/鸭 二级产品excle数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<BR121406RsBean> findChickenOrDuckMachiningProducts(BR121406RsParam param) {
        return super.findList(SqlId.FIND_CHICKEN_OR_DUCK_MACHINING_PRODUCTS, param);
    }

    /**
     * 插入EXCEL文件信息
     *
     * @param
     */
    @Transactional
    public void addFileInfo(BR121406RsParam br121406RsParam) {
        BaseParam param = new BaseParam();
        param.setFilter("fileId", br121406RsParam.getFileId());
        int count = super.getCount(param);
        if (NumberConst.IntDef.INT_ZERO == count) {
            br121406RsParam.setFileStartTime(br121406RsParam.getFileStartTime()+" 00:00:00");
            br121406RsParam.setFileEndTime(br121406RsParam.getFileEndTime() + " 23:59:59");
            super.save(br121406RsParam);
        } else {
            super.modify(br121406RsParam);
        }
    }

    /**
     * 删除excel
     * @param param
     * @return
     */
    @Transactional
    public int deleteFlag(BR121406RsParam param){
        return super.modify(SqlId.DELETE_FLAG,param);
    }

    /**
     * 修改文件状态到不成功
     * @param param
     * @return
     */
    @Transactional
    public int modifyFileStatus(BR121406RsParam param){
        return super.modify(SqlId.MODIFY_FILE_STATUS,param);
    }


    public String dataResolve(BR121406RsParam param) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        List<BR121406RsBean> pdPoolList = null;
        String fileId;
        /** Modif for Bug#2847 at 2016/09/23 by yuan_zhifei Start*/
        param.setMarketingsPeriodName("");
        param.setMarketingsStatus("");
        param.setMarketId("");
        /** Modif for Bug#2847 at 2016/09/23 by yuan_zhifei End*/
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        if (StringUtil.isNullOrEmpty(param.getFileId())) {
            //UUID
            fileId = UUID.randomUUID().toString();
            //param.setFileServerIp(fileServerIp);
            param.setFileName(param.getFileName());
            param.setFileStartTime(param.getFileStartTime());
            param.setFileEndTime(param.getFileEndTime());
            param.setFileCreateTime(currentDate);
            param.setFileSuf("xlsx");
            param.setDelFlg("0");
            param.setVer(NumberConst.IntDef.INT_ONE);
        } else {
            fileId = param.getFileId();
            param.setUpdTime(DateTimeUtil.getCustomerDate());
        }
        param.setFileId(fileId);
        if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("1"))
                || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("1"))) {
            //鸡鸭一级产品白条鸡白条鸭二级产品
            pdPoolList = this.findChickenOrDuckProducts(param);
            param.setFileName(pdPoolList.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime());
        } else if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("23"))
                || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("23"))) {
            //鸡鸭一级产品鸡鸭分割副产品二级产品
            pdPoolList = this.findChickenOrDuckMachiningProducts(param);
            param.setFileName(pdPoolList.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime());
        }

        addFileInfo(param);
        return fileId;
    }

    /**
     * 创建excel
     *
     * @param param
     */
    public void createFileInformatica(BR121406RsParam param) {
        List<BR121406RsBean> pdPoolList = null;
        if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("1"))
                || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("1"))) {
            //鸡鸭一级产品白条鸡白条鸭二级产品
            pdPoolList = this.findChickenOrDuckProducts(param);
            param.setFileName(pdPoolList.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime());
        } else if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("23"))
                || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("23"))) {
            //鸡鸭一级产品鸡鸭分割副产品二级产品
            pdPoolList = this.findChickenOrDuckMachiningProducts(param);
            param.setFileName(pdPoolList.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime());
        }
        //拼装数据
        Map<String, Object> map = new HashMap<>();
        System.out.println("输出EXCEL");
        map.put("pdList", pdPoolList);
        map.put("title", param.getFileName());
        InputStream in = null;
        OutputStream out = null;
        String inputPath = null;
        if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("1"))
                || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("1"))) {
            inputPath = "template/BR12140601.xlsx";
        } else if ((param.getClassesCode().equals("01") && param.getMachiningCodeU().equals("23"))
                || (param.getClassesCode().equals("02") && param.getMachiningCodeU().equals("23"))) {
            inputPath = "template/BR12140602.xlsx";
        }

        String outputPath = FileUtils.getSystemTmpDir() + "/" + param.getFileId() + ".xlsx";

        try {
            File excelFile = new File(outputPath);
            in = getClass().getClassLoader().getResourceAsStream(inputPath);
            out = new FileOutputStream(excelFile);
            JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
            jew.excelWrite(map);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(param.getFileId(), excelFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
            if (!result.isEmpty()) {
                FileUtils.deleteFile(excelFile);
            }

            param.setFileServerId(result.get(param.getFileId()));
            if(param.getFileServerId() == null){
                modifyFileStatus(param);
                return ;
            }

            this.addFileInfo(param);
        } catch (FileNotFoundException e) {
            logger.error("模板文件不存在");
            modifyFileStatus(param);
            return ;
//            throw new SystemException("模板文件不存在", e);
        } catch (IOException e) {
            logger.error("IO读写错误");
            modifyFileStatus(param);
            return ;
//            throw new SystemException("IO读写错误");
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

}
