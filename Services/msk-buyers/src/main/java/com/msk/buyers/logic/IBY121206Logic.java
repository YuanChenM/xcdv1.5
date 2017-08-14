package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.file.FileUtils;
import com.hoperun.core.file.FtpUtils;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.buyers.bean.IBY121206RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.ConfigManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerPictures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * IBY121206Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121206Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121206Logic.class);
    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家证照图片信息获取
        static String SQL_FIND_PICTURES = "findPictures";
        //买家证照图片信息更新
        static String SQL_BUYER_PICTURES_MODIFY = "buyerPicturesModify";
        //买家证照图片信息插入
        static String SQL_BUYER_PICTURES_INSERT = "buyerPicturesInsert";
        //接口买家证照图片信息获取
        static String SQL_FIND_PICTURES_LIST = "findPicturesList";
        //验证买家id是否存在
        static String REG_BUYERS_ID = "regBuyersId";

    }
    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 买家证照信息更新接口
     * @param param
     * @return
     */
    @Transactional
    public int buyerLicencePicModify(IBY121206RsParam param) {
        //根据传入参数获取买家图片信息
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId", param.getBuyerId());
        ByBuyerPictures pictures = super.findOne(SqlId.SQL_FIND_PICTURES, inParam);

        ByBuyerPictures byPictures = new ByBuyerPictures();
        byPictures.setBuyerId(param.getBuyerId());
        byPictures.setUpdId(param.getUpdId());
        byPictures.setUpdTime(param.getUpdTime());
        byPictures.setBusLicPic(param.getPicLicensePath());
        byPictures.setOrgCertificatePic(param.getPicOrgStructurePath());
        byPictures.setTaxCertificatePic(param.getPicTaxRegistrationPath());
        byPictures.setFoodCertificatePic(param.getPicFoodCirculationPath());
        byPictures.setLegalCertificatePic(param.getPicCertPath());
        if (null == pictures) {
            return buyerPicturesSave(byPictures);
        } else {
            byPictures.setId(pictures.getId());
            return super.modify(SqlId.SQL_BUYER_PICTURES_MODIFY, byPictures);
        }
    }


    /**
     * 买家证照图片插入
     * @param insertParam
     * @return
     */
    @Transactional
    public int buyerPicturesSave(ByBuyerPictures insertParam){
        Long id = commonLogic.maxId("by_buyer_Pictures", "ID");
        insertParam.setId(id);
        insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.save(SqlId.SQL_BUYER_PICTURES_INSERT,insertParam);
    }

    /**
     * 将ftp临时目录中的文件下载到本地，然后上传到ftp文件目录
     * @param tempPath
     * @param tempName
     * @param buyerId
     * @return
     */
    public String[] getFileId(String tempPath,String tempName,String buyerId){
        //ftpIp地址
        String url = ConfigManager.getFtpIp();
        //ftp登陆密码
        String password=ConfigManager.getFtpPwd();
        //ftp登陆名称
        String userName = ConfigManager.getFtpUser();
        //端口号
        int port= NumberConst.IntDef.INT_TWENTY_ONE;
        FtpUtils ftpUtils = new FtpUtils(url,port,userName,password);
        /*File tempFile = new File(tempFilePath);
        //ftp临时文件路径
        String tempPathFull = tempFile.getPath();
        String tempPath = tempPathFull.substring(0,tempPathFull.lastIndexOf("\\") + 1);
        //ftp临时文件名称
        String tempName = tempFile.getName();*/
        //ftp临时文件后缀名
        String fileSuffix = FileUtils.getFileSuffix(tempName);
        //下载保存到本地文件名称
        UUID uuid = UUID.randomUUID();
        String localFileName = String.valueOf(uuid) + StringConst.DOT+fileSuffix;
        //本地文件路径
        String localPath = System.getProperty("java.io.tmpdir");
        //从ftp下载文件到本地目录
        ftpUtils.download(tempPath,tempName,new File(localPath + "/" + localFileName));
        //图片上传到文件服务器
        String uploadFilePath = ConfigManager.getFtpImageRootPath() + BuyersConst.BYPath.BYIMAGEPATH + "/" + buyerId + "/";
        //读取本地存储的文件
        File uploadFile = new File(localPath,localFileName);
        //上传到ftp服务器
        ftpUtils.upload(uploadFilePath,uploadFile);
        //删除本地文件
        uploadFile.delete();
        //返回插入DB的数据
        String[] insertParam = new String[NumberConst.IntDef.INT_TWO];
        insertParam[NumberConst.IntDef.INT_ZERO] = String.valueOf(uuid);
        insertParam[NumberConst.IntDef.INT_ONE] = fileSuffix;
        return insertParam;
    }

    /**
     * 买家图片信息查询接口
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public IBY121206RsParam buyerLicencePicFind(IBY121206RsParam param){
        //根据传入参数获取买家图片信息
        IBY121206RsParam licencePic = null;
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId",param.getBuyerId());
        ByBuyerPictures pictures = super.findOne(SqlId.SQL_FIND_PICTURES,inParam);
        if(null != pictures){
            licencePic = new IBY121206RsParam();
            licencePic.setBuyerId(param.getBuyerId());
            //营业执照图片路径
            licencePic.setPicLicensePath(pictures.getBusLicPic());
            //食品流通许可证图片路径
            licencePic.setPicFoodCirculationPath(pictures.getFoodCertificatePic());
            //组织机构代码证图片
            licencePic.setPicOrgStructurePath(pictures.getOrgCertificatePic());
            //税务登记证图片
            licencePic.setPicTaxRegistrationPath(pictures.getTaxCertificatePic());
            //法定代表人证件图片
            licencePic.setPicCertPath(pictures.getLegalCertificatePic());
        }
        return licencePic;
    }


    /**
     * 接口获取买家图片信息
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IBY121206RsParam> buyerLicencePicList(BuyerRelationParam param){
        //根据传入参数获取买家图片信息
        IBY121206RsParam licencePic = null;
        List<IBY121206RsParam> iby121206RsParamList = new ArrayList<>();
        List<ByBuyerPictures> picturesList = super.findList(SqlId.SQL_FIND_PICTURES_LIST,param);
        if(!CollectionUtils.isEmpty(picturesList)){
            for (ByBuyerPictures pictures : picturesList){
            licencePic = new IBY121206RsParam();
            licencePic.setBuyerId(pictures.getBuyerId());
            //图片上传文件路径
            String http = "http://";
            String url = ConfigManager.getFtpIp();
            String uploadPicFilePath = ConfigManager.getFtpImageRootPath();
            //营业执照图片路径
            if(!StringUtil.isNullOrEmpty(pictures.getBusLicPic()) && !StringUtil.isNullOrEmpty(pictures.getBusLicSuf())){
                String picLicensePath = http + url + uploadPicFilePath + BuyersConst.BYPath.BYIMAGEPATH + "/" + pictures.getBuyerId() +"/" + pictures.getBusLicPic() + StringConst.DOT + pictures.getBusLicSuf();
                licencePic.setPicLicensePath(picLicensePath);
            }
            //食品流通许可证图片路径
            if(!StringUtil.isNullOrEmpty(pictures.getFoodCertificatePic()) && !StringUtil.isNullOrEmpty(pictures.getFoodCertificateSuf())){
                String picFoodCirculationPath = http + url + uploadPicFilePath + BuyersConst.BYPath.BYIMAGEPATH + "/" + pictures.getBuyerId() +"/" + pictures.getFoodCertificatePic() + StringConst.DOT + pictures.getFoodCertificateSuf();
                licencePic.setPicFoodCirculationPath(picFoodCirculationPath);
            }
            //组织机构代码证图片
            if(!StringUtil.isNullOrEmpty(pictures.getOrgCertificatePic()) && !StringUtil.isNullOrEmpty(pictures.getOrgCertificateSuf())){
                String picOrgStructurePath = http + url + uploadPicFilePath + BuyersConst.BYPath.BYIMAGEPATH + "/" + pictures.getBuyerId() +"/" + pictures.getOrgCertificatePic() + StringConst.DOT + pictures.getOrgCertificateSuf();
                licencePic.setPicOrgStructurePath(picOrgStructurePath);
            }
            //税务登记证图片
            if(!StringUtil.isNullOrEmpty(pictures.getTaxCertificatePic()) && !StringUtil.isNullOrEmpty(pictures.getTaxCertificateSuf())){
                String picTaxRegistrationPath = http + url + uploadPicFilePath + BuyersConst.BYPath.BYIMAGEPATH + "/" + pictures.getBuyerId() +"/" + pictures.getTaxCertificatePic() + StringConst.DOT + pictures.getTaxCertificateSuf();
                licencePic.setPicTaxRegistrationPath(picTaxRegistrationPath);
            }
            //法定代表人证件图片
            if(!StringUtil.isNullOrEmpty(pictures.getLegalCertificatePic()) && !StringUtil.isNullOrEmpty(pictures.getLegalCertificateSuf())){
                String picCertPath = http + url + uploadPicFilePath +BuyersConst.BYPath.BYIMAGEPATH + "/" + pictures.getBuyerId() +"/" + pictures.getLegalCertificatePic() + StringConst.DOT + pictures.getLegalCertificateSuf();
                licencePic.setPicCertPath(picCertPath);
                 }
            }
            iby121206RsParamList.add(licencePic);
        }
        return iby121206RsParamList;
    }

    /**
     * 验证buyerId是否存在
     * @param buyerId
     * @return
     */
    public int regBuyersId(String buyerId){
       BaseParam baseParam = new BaseParam();
       baseParam.getFilterMap().put("buyerId",buyerId);
       return super.getCount(SqlId.REG_BUYERS_ID,baseParam);
    }

}
