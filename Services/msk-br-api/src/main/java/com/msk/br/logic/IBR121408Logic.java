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
import com.msk.br.bean.IBR121408RsBean;
import com.msk.br.bean.IBR121408RsPageResult;
import com.msk.br.bean.IBR121408RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrFileBuyerPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

/**
 * 分销买家池营销期公众买家分池买家注册管控
 * <p/>
 * Created by tao_zhifa on 2016/07/26.
 */
@Service
public class IBR121408Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121408Logic.class);


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId {
        String FIND_FILE_NAME_COUNT = "findFileNameCount";
        String FIND_UP_BRBUYER_INFO = "findUpBrBuyerInfo";
        String FIND_DOWN_BRBUYER_INFO = "findDownBrBuyerInfo";
        String DELETE_FLAG = "deleteFlag";
        String MODIFY_FILE_STATUS = "modifyFileStatus";
        String IS_EXIST = "isExist";
        String UPDATE_FILE_BUYER_POOL_BY_FILENAME = "updateFileBuyerPoolByFileName";
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
     * 删除excel
     * @param param
     * @return
     */
    @Transactional
    public int deleteFlag(IBR121408RsParam param){
        return super.modify(SqlId.DELETE_FLAG,param);
    }

    /**
     * 修改文件状态到不成功
     * @param param
     * @return
     */
    @Transactional
    public int modifyFileStatus(IBR121408RsParam param){
        return super.modify(SqlId.MODIFY_FILE_STATUS,param);
    }


    private int findFileNameCount(IBR121408RsParam param) {
        return super.getCount(SqlId.FIND_FILE_NAME_COUNT, param);
    }


    /**
     * 查询下列表产品池数据
     */
    @Transactional(readOnly = true)
    private List<IBR121408RsBean> findDownBrBuyerInfo(IBR121408RsParam param) {
        List<IBR121408RsBean> ibr121408BeanList = super.findList(SqlId.FIND_DOWN_BRBUYER_INFO, param);

        return ibr121408BeanList;
    }

    /**
     * 查询上列表产品池数据
     */
    @Transactional(readOnly = true)
    private
    @ResponseBody
    RsResponse<IBR121408RsPageResult> findUpBrBuyerInfo(IBR121408RsParam param) {
        RsRequest<IBR121408RsParam> request = new RsRequest<>();
        List<IBR121408RsBean> list = super.findList(SqlId.FIND_UP_BRBUYER_INFO, param);
        IBR121408RsParam ibr121408RsParam = new IBR121408RsParam();
        ibr121408RsParam.setHouseList(list);
        ibr121408RsParam.setLgcsAreaCode(param.getLgcsAreaCode());
        ibr121408RsParam.setCityCode(param.getCityCode());
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(ibr121408RsParam);
//        String url = "http://localhost:8280/msk-bs/api/bs/getHouseInfoById";
        String url = SystemServerManager.BsServerManage.getGetHouseInfoById();
        RsResponse<IBR121408RsPageResult> rslist = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121408RsPageResult>>() {
        });
        if (list == null || ("F").equals(rslist.getStatus())){
            return  null;
        }else {
            for(int i=0;i<rslist.getResult().getHouseList().size();i++){
                if(rslist.getResult().getHouseList().get(i).getIsChangeBuyers().equals("0")){
                    rslist.getResult().getHouseList().get(i).setIsChangeBuyers("否");
                }
                if(rslist.getResult().getHouseList().get(i).getIsChangeBuyers().equals("1")){
                    rslist.getResult().getHouseList().get(i).setIsChangeBuyers("是");
                }
            }
            return rslist;
        }
    }

    /**
     * 生成文件
     *
     * @param bean
     * @return
     */
    public String dataResolve(IBR121408RsParam bean) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        String fileId;
        //String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        if (StringUtil.isNullOrEmpty(bean.getFileId())) {
            //UUID
            fileId = UUID.randomUUID().toString();
            //bean.setFileServerIp(fileServerIp);
            bean.setFileName(bean.getFileName());
            bean.setFileStartTime(bean.getFileStartTime());
            bean.setFileEndTime(bean.getFileEndTime());
            bean.setFileCreateTime(currentDate);
            bean.setFileSuf("xlsx");
            bean.setDelFlg("0");
            bean.setVer(NumberConst.IntDef.INT_ONE);
            bean.setFileServerId(null);
        } else {
            fileId = bean.getFileId();
            bean.setUpdTime(DateTimeUtil.getCustomerDate());
        }
        bean.setFileId(fileId);
        this.addFileInfo(bean);
        return fileId;
    }


    public void createFileInformatica(IBR121408RsParam bean){

        IBR121408RsParam param = new IBR121408RsParam();
        param.setCityCode(bean.getCityCode());
        param.setLgcsAreaCode(bean.getLgcsAreaCode());
        List<IBR121408RsBean> pdPoolList = this.findDownBrBuyerInfo(bean);
        RsResponse<IBR121408RsPageResult> response = this.findUpBrBuyerInfo(param);
        List<IBR121408RsBean> houseList = new ArrayList<>();
        if (response != null){
            houseList = response.getResult().getHouseList();
        }
        //拼装数据
        Map<String, Object> map = new HashMap<>();
        System.out.println("输出EXCEL");
        map.put("HKGroupList", pdPoolList);
        map.put("title", bean.getFileName());
        map.put("houseList", houseList);
//        map.put("title", bean.getFileName());
        InputStream in = null;
        OutputStream out = null;
        String inputPath = "template/BR121408.xlsx";
        String outputPath = FileUtils.getSystemTmpDir() + "/" + bean.getFileId() + ".xlsx";

        try {
            File excelFile = new File(outputPath);
            in = getClass().getClassLoader().getResourceAsStream(inputPath);
            out = new FileOutputStream(excelFile);
            JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
            jew.excelWrite(map);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(bean.getFileId(), excelFile);
            Map<String, String> result = FileUploadUtil.uploadFiles(fileMap);
            if(!result.isEmpty()){
                FileUtils.deleteFile(excelFile);
            }

            bean.setFileServerId(result.get(bean.getFileId()));
            if(bean.getFileServerId() == null){
                modifyFileStatus(bean);
                return ;
            }
            bean.setFileStatus("1");
            this.addFileInfo(bean);
        } catch (FileNotFoundException e) {
            logger.error("模板文件不存在");
            modifyFileStatus(bean);
            return ;
//            throw new SystemException("模板文件不存在", e);
        } catch (IOException e) {
            logger.error("IO读写错误");
            modifyFileStatus(bean);
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
     * 插入EXCEL文件信息
     *
     * @param fileInfo
     */
    @Transactional
    private void addFileInfo(IBR121408RsParam fileInfo) {
        BaseParam param = new BaseParam();
        param.setFilter("fileId", fileInfo.getFileId());
        int count = super.getCount(param);
        if (NumberConst.IntDef.INT_ZERO == count) {
            fileInfo.setFileStartTime(fileInfo.getFileStartTime()+" 00:00:00");
            fileInfo.setFileEndTime(fileInfo.getFileEndTime() + " 23:59:59");
            super.save(fileInfo);
        } else {
            super.modify(fileInfo);
        }

    }
}
