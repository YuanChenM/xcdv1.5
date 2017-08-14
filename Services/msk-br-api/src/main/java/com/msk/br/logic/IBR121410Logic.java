package com.msk.br.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.SystemException;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.BR121406RsParam;
import com.msk.br.bean.IBR121408RsParam;
import com.msk.br.bean.IBR121410RsBean;
import com.msk.br.bean.IBR121410RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 *
 * <p/>
 * Created by zhao_chen on 2016/08/18.
 */
@Service
public class IBR121410Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121410Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    Date currentDate = DateTimeUtil.getCustomerDate();

    interface SqlId {
        String FIND_ONLINE_VIP_BY_POOL = "findOnlineVipByPool";
        String IS_EXIST = "isExist";
        String GET_HOUSE_LIST = "getHouseList";
        String DELETE_FLAG = "deleteFlag";
        String MODIFY_FILE_STATUS = "modifyFileStatus";

    }

    /**
     * 二级分类产品分销买家池专属会员分池会员及销售活动在线管控信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBR121410RsBean> findOnlineVipByPool(IBR121410RsParam param) {
        List<IBR121410RsBean> buyerInfoList = super.findList(SqlId.FIND_ONLINE_VIP_BY_POOL, param);
        return buyerInfoList;
    }

    /**
     * 删除excel
     * @param param
     * @return
     */
    @Transactional
    public int deleteFlag(IBR121410RsParam param){
        return super.modify(SqlId.DELETE_FLAG,param);
    }

    /**
     * 修改文件状态到不成功
     * @param param
     * @return
     */
    @Transactional
    public int modifyFileStatus(IBR121410RsParam param){
        return super.modify(SqlId.MODIFY_FILE_STATUS,param);
    }

    /**
     * 生成報表
     *
     * @param param
     */
    public String dataResolve(IBR121410RsParam param ) {
        Date currentDate = DateTimeUtil.getCustomerDate();

        String fileId;
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        if (StringUtil.isNullOrEmpty(param.getFileId())) {
            //UUID
            fileId = UUID.randomUUID().toString();
            param.setFileName(param.getFileName());
            //param.setFileServerIp(fileServerIp);
            param.setFileStartTime(param.getFileStartTime());
            param.setFileEndTime(param.getFileEndTime());
         /* param.setActId("Admin");
            param.setCrtId("Admin");
            param.setUpdId("Admin");
            param.setActTime(currentDate);
            param.setCrtTime(currentDate);
            param.setUpdTime(currentDate);*/
            param.setFileCreateTime(currentDate);
            param.setFileSuf("xlsx");
            param.setDelFlg("0");
            param.setVer(NumberConst.IntDef.INT_ONE);
        } else {
            fileId = param.getFileId();
            param.setUpdTime(DateTimeUtil.getCustomerDate());
        }
        param.setFileId(fileId);
        this.addFileInfo(param);
        return fileId;
    }


    public void createFileInformatica(IBR121410RsParam param,List<IBR121410RsBean> list){
        List<IBR121410RsBean> byPoolList = this.findOnlineVipByPool(param);
        /*List<IBR121410RsBean> houseList = this.getHouseList(param);*/
        //拼装数据
        Map<String, Object> map = new HashMap<>();
        System.out.println("输出EXCEL");
        map.put("byPoolList", byPoolList);
        map.put("houseList", list);
        map.put("title", param.getFileName());
        InputStream in = null;
        OutputStream out = null;
        String inputPath = "template/BR121410.xlsx";
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

    /**
     *
     * @param ibr121410RsParam
     */
    @Transactional
    private void addFileInfo(IBR121410RsParam ibr121410RsParam) {
        BaseParam param = new BaseParam();
        param.getFilterMap().put("fileId",ibr121410RsParam.getFileId());
        int count = super.getCount(param);
        if (NumberConst.IntDef.INT_ZERO == count) {
            ibr121410RsParam.setFileStartTime(ibr121410RsParam.getFileStartTime()+" 00:00:00");
            ibr121410RsParam.setFileEndTime(ibr121410RsParam.getFileEndTime() + " 23:59:59");
            super.save(ibr121410RsParam);
        } else {
            super.modify(ibr121410RsParam);
        }

    }


    /**
     *
     * @param param
     * @return
     */
    public Integer isExist(BaseParam param) {
        logger.info("");
        return this.getCount(SqlId.IS_EXIST, param);
    }


    public List<IBR121410RsBean> getHouseBasicInfo(IBR121410RsParam param){
        logger.info("添加通过管家ID列表获得管家信息列表接口");
        RsRequest<IBR121410RsParam> request = new RsRequest<IBR121410RsParam>();
        List<IBR121410RsBean>  list = null;
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
      //  String url ="http://localhost:8083/api/bs/getHouseInfoById";
      String url =SystemServerManager.BsServerManage.getGetHouseInfoById();
        RsResponse<IBR121410RsBean> houseObject = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121410RsBean>>() {});
      if( houseObject.getResult()!= null){
          list = houseObject.getResult().getHouseList();
      }
       return list;
    }

    /**
     * 查詢冻品管家列表
     *
     * @param param
     * @return
     */
    public  List<IBR121410RsBean> getHouseList(BaseParam param){
        return super.findList(SqlId.GET_HOUSE_LIST,param);
    }
    

}
