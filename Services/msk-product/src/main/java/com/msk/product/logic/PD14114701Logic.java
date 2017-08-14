package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.utils.FileUploadUtil;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141147Bean;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.security.krb5.internal.PAData;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by air on 2016/5/18.
 */

@Service
public class PD14114701Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author zhou_ling
     */
    interface SqlId {
        static final String SQL_ID_FIND_FIND_PD_FED_STD = "findFedStdList";
        static final String SQL_ID_FIND_BREED_NAME_FEANAME = "findName";
        static final String SQL_ID_MODIFY_STD_FLG = "modifyStdFlg";
        String SQL_ID_SAVEPIC="savepic";
        String SQL_ID_FINDPICLIST="findPicList";
        String SQL_ID_CHECKPIC="checkPic";
        String SQL_ID_MODIFYPIC="modifyPic";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public List<PD141147Bean> findPicList(String standardId){
        BaseParam param = new BaseParam();
        param.setFilter("standardId", standardId);
        // TODO 旧项目中写的findOne
        List<PD141147Bean> s =super.findList(SqlId.SQL_ID_FINDPICLIST,param);
        return s;
    }
    /**
     * 查询所有orgstdTime
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141147Bean> findListPdFedStd(BaseParam param) {
        return   this.findList(SqlId.SQL_ID_FIND_FIND_PD_FED_STD,param);
    }

    /**
     * 获取产品品种数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141124showNameBean> findBreedNameAndFea(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_BREED_NAME_FEANAME, param);
    }

    public static String getFormatInFile(MultipartFile s) throws Exception{
        CommonsMultipartFile cf= (CommonsMultipartFile)s; //这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File o = fi.getStoreLocation();

        try {
            // Create an image input stream on the image
            ImageInputStream iis = ImageIO.createImageInputStream(o);

            // Find all image readers that recognize the image format
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                // No readers found
                return null;
            }

            // Use the first reader
            ImageReader reader = iter.next();

            // Close stream
            iis.close();

            // Return the format name
            return reader.getFormatName();
        } catch (IOException e) {
            //
        }

        // The image could not be read
        return null;
    }


    /**
     * 保存修改数据
     * @param bean
     * @param param
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void saveAndEdit(PD141147Bean bean, BaseParam param) throws Exception{
        if (StringUtils.isNotBlank(bean.getStandardId().toString())) {
            // 判断产品标准是否存在
            param.setFilter("standardId", bean.getStandardId().toString());
            List<PD141147Bean> list = super.findList(param);
            if (list.size() <= NumberConst.IntDef.INT_ZERO) {

                //新增操作
                if (bean.getFedStdItemIdArray().length > NumberConst.IntDef.INT_ZERO) {

                    for (int i = 0; i < bean.getFedStdItemIdArray().length; i++) {
                        //第一行item时
                        PD141147Bean saveBean = new PD141147Bean();
                        saveBean.setFedBadVal(bean.getFedBadValArray()[i]);
                        saveBean.setFedGoodVal(bean.getFedGoodValArray()[i]);
                        saveBean.setFedNormalVal(bean.getFedNormalValArray()[i]);
                        saveBean.setFedStdItemId(bean.getFedStdItemIdArray()[i]);
                        saveBean.setStandardId(bean.getStandardId());
                        saveBean.setCrtId(param.getCrtId());
                        saveBean.setUpdId(param.getUpdId());
                        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
                        saveBean.setActId(param.getActId());
                        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        saveBean.setActTime(new Date());
                        saveBean.setUpdTime(new Date());
                        saveBean.setCrtTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        super.save(saveBean);

                        MultipartFile fedGoodValFile1 = bean.getFedGoodValFile1()[i];
                        MultipartFile fedGoodValFile2 = bean.getFedGoodValFile2()[i];
                        MultipartFile fedGoodValFile3 = bean.getFedGoodValFile3()[i];
                        MultipartFile fedGoodValFile4 = bean.getFedGoodValFile4()[i];

                        MultipartFile fedNormalValFile1 = bean.getFedNormalValFile1()[i];
                        MultipartFile fedNormalValFile2 = bean.getFedNormalValFile2()[i];
                        MultipartFile fedNormalValFile3 = bean.getFedNormalValFile3()[i];
                        MultipartFile fedNormalValFile4 = bean.getFedNormalValFile4()[i];

                        MultipartFile fedBadValFile1 = bean.getFedBadValFile1()[i];
                        MultipartFile fedBadValFile2 = bean.getFedBadValFile2()[i];
                        MultipartFile fedBadValFile3 = bean.getFedBadValFile3()[i];
                        MultipartFile fedBadValFile4 = bean.getFedBadValFile4()[i];

                        //如果上传的图片文件时空的，那就不动，如果非空就设置更新项
                        //通过standardId和ItemId查看，如果有就修改，不然就添加
                        param.setFilter("standardId",bean.getStandardId().toString());
                        param.setFilter("fedStdItemId",bean.getFedStdItemIdArray()[i]);
                        PD141147Bean s= this.findOne(SqlId.SQL_ID_CHECKPIC, param);
                        if(null==s){

                            //获得了一行的3个类别的12个图片，上传到服务器即可
                            if(fedGoodValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile1",value);
                                String fedGoodValFileType1 = getFormatInFile(fedGoodValFile1);
                                param.setFilter("fedGoodValFileType1",fedGoodValFileType1);

                            }else{
                                param.setFilter("fedGoodValFile1","");
                                param.setFilter("fedGoodValFileType1","");
                            }
                            if(fedGoodValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile2",value);
                                String fedGoodValFileType2 = getFormatInFile(fedGoodValFile2);
                                param.setFilter("fedGoodValFileType2",fedGoodValFileType2);

                            }else{
                                param.setFilter("fedGoodValFile2","");
                                param.setFilter("fedGoodValFileType2","");
                            }
                            if(fedGoodValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile3",value);
                                String fedGoodValFileType3 = getFormatInFile(fedGoodValFile3);
                                param.setFilter("fedGoodValFileType3",fedGoodValFileType3);

                            }else{
                                param.setFilter("fedGoodValFile3","");
                                param.setFilter("fedGoodValFileType3","");
                            }
                            if(fedGoodValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile4",value);
                                String fedGoodValFileType4 = getFormatInFile(fedGoodValFile4);
                                param.setFilter("fedGoodValFileType4",fedGoodValFileType4);

                            }else{
                                param.setFilter("fedGoodValFile4","");
                                param.setFilter("fedGoodValFileType4","");
                            }
                            //________________________________
                            if(fedNormalValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile1",value);
                                String fedNormalValFileType1 = getFormatInFile(fedNormalValFile1);
                                param.setFilter("fedNormalValFileType1",fedNormalValFileType1);

                            }else{
                                param.setFilter("fedNormalValFile1","");
                                param.setFilter("fedNormalValFileType1","");
                            }
                            if(fedNormalValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile2",value);
                                String fedNormalValFileType2 = getFormatInFile(fedNormalValFile2);
                                param.setFilter("fedNormalValFileType2",fedNormalValFileType2);

                            }else{
                                param.setFilter("fedNormalValFile2","");
                                param.setFilter("fedNormalValFileType2","");
                            }
                            if(fedNormalValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile3",value);
                                String fedNormalValFileType3 = getFormatInFile(fedNormalValFile3);
                                param.setFilter("fedNormalValFileType3",fedNormalValFileType3);

                            }else{
                                param.setFilter("fedNormalValFile3","");
                                param.setFilter("fedNormalValFileType3","");
                            }
                            if(fedNormalValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile4",value);
                                String fedNormalValFileType4 = getFormatInFile(fedNormalValFile4);
                                param.setFilter("fedNormalValFileType4",fedNormalValFileType4);

                            }else{
                                param.setFilter("fedNormalValFile4","");
                                param.setFilter("fedNormalValFileType4","");
                            }

                            //--------------------------------------
                            if(fedBadValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile1",value);

                                String fedBadValFileType1 = getFormatInFile(fedBadValFile1);
                                param.setFilter("fedBadValFileType1",fedBadValFileType1);

                            }else{
                                param.setFilter("fedBadValFile1","");
                                param.setFilter("fedBadValFileType1","");
                            }
                            if(fedBadValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile2",value);
                                String fedBadValFileType2 = getFormatInFile(fedBadValFile2);
                                param.setFilter("fedBadValFileType2",fedBadValFileType2);

                            }else{
                                param.setFilter("fedBadValFile2","");
                                param.setFilter("fedBadValFileType1","");
                            }
                            if(fedBadValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile3",value);
                                String fedBadValFileType3 = getFormatInFile(fedBadValFile3);
                                param.setFilter("fedBadValFileType3",fedBadValFileType3);

                            }else{
                                param.setFilter("fedBadValFile3","");
                                param.setFilter("fedBadValFileType3","");
                            }
                            if(fedBadValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile4",value);
                                String fedBadValFileType4 = getFormatInFile(fedBadValFile4);
                                param.setFilter("fedBadValFileType4",fedBadValFileType4);

                            }else{
                                param.setFilter("fedBadValFile4","");
                                param.setFilter("fedBadValFileType4","");

                            }
                            param.setFilter("standardId",bean.getStandardId().toString());
                            param.setFilter("fedStdItemId",bean.getFedStdItemIdArray()[i]);
                            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                            param.setActTime(new Date());
                            param.setCrtTime(new Date());
                            param.setUpdTime(new Date());
                            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                            this.save(SqlId.SQL_ID_SAVEPIC,param);

                        }else{
                            //获得了一行的3个类别的12个图片，上传到服务器即可

                            param.setFilter("standardId",bean.getStandardId().toString());
                            param.setFilter("fedStdItemId",bean.getFedStdItemIdArray()[i]);

                            if(fedGoodValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile1",value);
                                String fedGoodValFileType1 = getFormatInFile(fedGoodValFile1);
                                param.setFilter("fedGoodValFileType1",fedGoodValFileType1);

                            }else{
                                param.setFilter("fedGoodValFile1",s.getfGoodValFile1());
                                param.setFilter("fedGoodValFileType1",s.getFedGoodValFileType1());
                            }
                            if(fedGoodValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile2",value);
                                String fedGoodValFileType2 = getFormatInFile(fedGoodValFile2);
                                param.setFilter("fedGoodValFileType2",fedGoodValFileType2);

                            }else{
                                param.setFilter("fedGoodValFile2",s.getfGoodValFile2());
                                param.setFilter("fedGoodValFileType2",s.getFedGoodValFileType2());
                            }
                            if(fedGoodValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile3",value);
                                String fedGoodValFileType3 = getFormatInFile(fedGoodValFile3);
                                param.setFilter("fedGoodValFileType3",fedGoodValFileType3);

                            }else{
                                param.setFilter("fedGoodValFile3",s.getfGoodValFile3());
                                param.setFilter("fedGoodValFileType3",s.getFedGoodValFileType3());
                            }
                            if(fedGoodValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile4",value);
                                String fedGoodValFileType4 = getFormatInFile(fedGoodValFile4);
                                param.setFilter("fedGoodValFileType4",fedGoodValFileType4);

                            }else{
                                param.setFilter("fedGoodValFile4",s.getfGoodValFile4());
                                param.setFilter("fedGoodValFileType4",s.getFedGoodValFileType4());
                            }
                            //________________________________
                            if(fedNormalValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile1",value);
                                String fedNormalValFileType1 = getFormatInFile(fedNormalValFile1);
                                param.setFilter("fedNormalValFileType1",fedNormalValFileType1);

                            }else{
                                param.setFilter("fedNormalValFile1",s.getfNormalValFile1());
                                param.setFilter("fedNormalValFileType1",s.getFedNormalValFileType1());
                            }
                            if(fedNormalValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile2",value);
                                String fedNormalValFileType2 = getFormatInFile(fedNormalValFile2);
                                param.setFilter("fedNormalValFileType2",fedNormalValFileType2);

                            }else{
                                param.setFilter("fedNormalValFile2",s.getfNormalValFile2());
                                param.setFilter("fedNormalValFileType2",s.getFedNormalValFileType2());
                            }
                            if(fedNormalValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile3",value);
                                String fedNormalValFileType3 = getFormatInFile(fedNormalValFile3);
                                param.setFilter("fedNormalValFileType3",fedNormalValFileType3);

                            }else{
                                param.setFilter("fedNormalValFile3",s.getfNormalValFile3());
                                param.setFilter("fedNormalValFileType3",s.getFedNormalValFileType3());
                            }
                            if(fedNormalValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile4",value);
                                String fedNormalValFileType4 = getFormatInFile(fedNormalValFile4);
                                param.setFilter("fedNormalValFileType4",fedNormalValFileType4);

                            }else{
                                param.setFilter("fedNormalValFile4",s.getfNormalValFile4());
                                param.setFilter("fedNormalValFileType4",s.getFedNormalValFileType4());
                            }

                            //--------------------------------------
                            if(fedBadValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile1",value);

                                String fedBadValFileType1 = getFormatInFile(fedBadValFile1);
                                param.setFilter("fedBadValFileType1",fedBadValFileType1);

                            }else{
                                param.setFilter("fedBadValFile1",s.getfBadValFile1());
                                param.setFilter("fedBadValFileType1",s.getFedBadValFileType1());
                            }
                            if(fedBadValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile2",value);
                                String fedBadValFileType2 = getFormatInFile(fedBadValFile2);
                                param.setFilter("fedBadValFileType2",fedBadValFileType2);

                            }else{
                                param.setFilter("fedBadValFile2",s.getfBadValFile2());
                                param.setFilter("fedBadValFileType2",s.getFedBadValFileType2());
                            }
                            if(fedBadValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile3",value);
                                String fedBadValFileType3 = getFormatInFile(fedBadValFile3);
                                param.setFilter("fedBadValFileType3",fedBadValFileType3);

                            }else{
                                param.setFilter("fedBadValFile3",s.getfBadValFile3());
                                param.setFilter("fedBadValFileType3",s.getFedBadValFileType3());
                            }
                            if(fedBadValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile4",value);
                                String fedBadValFileType4 = getFormatInFile(fedBadValFile4);
                                param.setFilter("fedBadValFileType4",fedBadValFileType4);

                            }else{
                                param.setFilter("fedBadValFile4",s.getfBadValFile4());
                                param.setFilter("fedBadValFileType4",s.getFedBadValFileType4());

                            }
                            param.setUpdTime(new Date());
                            this.modify(SqlId.SQL_ID_MODIFYPIC,param);
                        }
                    }
                }

                 /* //新增结束改变standard表中的产品标准饲养标识
                param.setFilter("flg", String.valueOf(NumberConst.IntDef.INT_ONE));
                int upStd  = super.modify(SqlId.SQL_ID_MODIFY_STD_FLG,param);
                if(upStd<= NumberConst.IntDef.INT_ZERO)throw new BusinessException("更新产品种源标准标识异常,请联系管理员!");*/
            } else {

                //修改操作
                if (bean.getFedStdItemIdArray().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = 0; i < bean.getFedStdItemIdArray().length; i++) {
                        PD141147Bean saveBean = new PD141147Bean();
                        saveBean.setFedBadVal(bean.getFedBadValArray()[i]);
                        saveBean.setFedGoodVal(bean.getFedGoodValArray()[i]);
                        saveBean.setFedNormalVal(bean.getFedNormalValArray()[i]);
                        saveBean.setFedStdItemId(bean.getFedStdItemIdArray()[i]);
                        saveBean.setStandardId(bean.getStandardId());
                        saveBean.setUpdId(param.getUpdId());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        saveBean.setUpdTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        super.modify(saveBean);

                        MultipartFile fedGoodValFile1 = bean.getFedGoodValFile1()[i];
                        MultipartFile fedGoodValFile2 = bean.getFedGoodValFile2()[i];
                        MultipartFile fedGoodValFile3 = bean.getFedGoodValFile3()[i];
                        MultipartFile fedGoodValFile4 = bean.getFedGoodValFile4()[i];

                        MultipartFile fedNormalValFile1 = bean.getFedNormalValFile1()[i];
                        MultipartFile fedNormalValFile2 = bean.getFedNormalValFile2()[i];
                        MultipartFile fedNormalValFile3 = bean.getFedNormalValFile3()[i];
                        MultipartFile fedNormalValFile4 = bean.getFedNormalValFile4()[i];

                        MultipartFile fedBadValFile1 = bean.getFedBadValFile1()[i];
                        MultipartFile fedBadValFile2 = bean.getFedBadValFile2()[i];
                        MultipartFile fedBadValFile3 = bean.getFedBadValFile3()[i];
                        MultipartFile fedBadValFile4 = bean.getFedBadValFile4()[i];


                        //如果上传的图片文件时空的，那就不动，如果非空就设置更新项
                        //通过standardId和ItemId查看，如果有就修改，不然就添加
                        param.setFilter("standardId",bean.getStandardId().toString());
                        param.setFilter("fedStdItemId",bean.getFedStdItemIdArray()[i]);
                        PD141147Bean s= this.findOne(SqlId.SQL_ID_CHECKPIC, param);
                        if(null==s){

                            //获得了一行的3个类别的12个图片，上传到服务器即可
                            if(fedGoodValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile1",value);
                                String fedGoodValFileType1 = getFormatInFile(fedGoodValFile1);
                                param.setFilter("fedGoodValFileType1",fedGoodValFileType1);

                            }else{
                                param.setFilter("fedGoodValFile1","");
                                param.setFilter("fedGoodValFileType1","");
                            }
                            if(fedGoodValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile2",value);
                                String fedGoodValFileType2 = getFormatInFile(fedGoodValFile2);
                                param.setFilter("fedGoodValFileType2",fedGoodValFileType2);

                            }else{
                                param.setFilter("fedGoodValFile2","");
                                param.setFilter("fedGoodValFileType2","");
                            }
                            if(fedGoodValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile3",value);
                                String fedGoodValFileType3 = getFormatInFile(fedGoodValFile3);
                                param.setFilter("fedGoodValFileType3",fedGoodValFileType3);

                            }else{
                                param.setFilter("fedGoodValFile3","");
                                param.setFilter("fedGoodValFileType3","");
                            }
                            if(fedGoodValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile4",value);
                                String fedGoodValFileType4 = getFormatInFile(fedGoodValFile4);
                                param.setFilter("fedGoodValFileType4",fedGoodValFileType4);

                            }else{
                                param.setFilter("fedGoodValFile4","");
                                param.setFilter("fedGoodValFileType4","");
                            }
                            //________________________________
                            if(fedNormalValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile1",value);
                                String fedNormalValFileType1 = getFormatInFile(fedNormalValFile1);
                                param.setFilter("fedNormalValFileType1",fedNormalValFileType1);

                            }else{
                                param.setFilter("fedNormalValFile1","");
                                param.setFilter("fedNormalValFileType1","");
                            }
                            if(fedNormalValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile2",value);
                                String fedNormalValFileType2 = getFormatInFile(fedNormalValFile2);
                                param.setFilter("fedNormalValFileType2",fedNormalValFileType2);

                            }else{
                                param.setFilter("fedNormalValFile2","");
                                param.setFilter("fedNormalValFileType2","");
                            }
                            if(fedNormalValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile3",value);
                                String fedNormalValFileType3 = getFormatInFile(fedNormalValFile3);
                                param.setFilter("fedNormalValFileType3",fedNormalValFileType3);

                            }else{
                                param.setFilter("fedNormalValFile3","");
                                param.setFilter("fedNormalValFileType3","");
                            }
                            if(fedNormalValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile4",value);
                                String fedNormalValFileType4 = getFormatInFile(fedNormalValFile4);
                                param.setFilter("fedNormalValFileType4",fedNormalValFileType4);

                            }else{
                                param.setFilter("fedNormalValFile4","");
                                param.setFilter("fedNormalValFileType4","");
                            }

                            //--------------------------------------
                            if(fedBadValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile1",value);

                                String fedBadValFileType1 = getFormatInFile(fedBadValFile1);
                                param.setFilter("fedBadValFileType1",fedBadValFileType1);

                            }else{
                                param.setFilter("fedBadValFile1","");
                                param.setFilter("fedBadValFileType1","");
                            }
                            if(fedBadValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile2",value);
                                String fedBadValFileType2 = getFormatInFile(fedBadValFile2);
                                param.setFilter("fedBadValFileType2",fedBadValFileType2);

                            }else{
                                param.setFilter("fedBadValFile2","");
                                param.setFilter("fedBadValFileType1","");
                            }
                            if(fedBadValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile3",value);
                                String fedBadValFileType3 = getFormatInFile(fedBadValFile3);
                                param.setFilter("fedBadValFileType3",fedBadValFileType3);

                            }else{
                                param.setFilter("fedBadValFile3","");
                                param.setFilter("fedBadValFileType3","");
                            }
                            if(fedBadValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile4",value);
                                String fedBadValFileType4 = getFormatInFile(fedBadValFile4);
                                param.setFilter("fedBadValFileType4",fedBadValFileType4);

                            }else{
                                param.setFilter("fedBadValFile4","");
                                param.setFilter("fedBadValFileType4","");

                            }
                            param.setFilter("standardId",bean.getStandardId().toString());
                            param.setFilter("fedStdItemId",bean.getFedStdItemIdArray()[i]);
                            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                            param.setActTime(new Date());
                            param.setUpdTime(new Date());
                            param.setCrtTime(new Date());
                            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                            this.save(SqlId.SQL_ID_SAVEPIC,param);

                        }else{
                            //获得了一行的3个类别的12个图片，上传到服务器即可

                            param.setFilter("standardId",bean.getStandardId().toString());
                            param.setFilter("fedStdItemId",bean.getFedStdItemIdArray()[i]);

                            if(fedGoodValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile1",value);
                                String fedGoodValFileType1 = getFormatInFile(fedGoodValFile1);
                                param.setFilter("fedGoodValFileType1",fedGoodValFileType1);

                            }else{
                                param.setFilter("fedGoodValFile1",s.getfGoodValFile1());
                                param.setFilter("fedGoodValFileType1",s.getFedGoodValFileType1());
                            }
                            if(fedGoodValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile2",value);
                                String fedGoodValFileType2 = getFormatInFile(fedGoodValFile2);
                                param.setFilter("fedGoodValFileType2",fedGoodValFileType2);

                            }else{
                                param.setFilter("fedGoodValFile2",s.getfGoodValFile2());
                                param.setFilter("fedGoodValFileType2",s.getFedGoodValFileType2());
                            }
                            if(fedGoodValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile3",value);
                                String fedGoodValFileType3 = getFormatInFile(fedGoodValFile3);
                                param.setFilter("fedGoodValFileType3",fedGoodValFileType3);

                            }else{
                                param.setFilter("fedGoodValFile3",s.getfGoodValFile3());
                                param.setFilter("fedGoodValFileType3",s.getFedGoodValFileType3());
                            }
                            if(fedGoodValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedGoodValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedGoodValFile4",value);
                                String fedGoodValFileType4 = getFormatInFile(fedGoodValFile4);
                                param.setFilter("fedGoodValFileType4",fedGoodValFileType4);

                            }else{
                                param.setFilter("fedGoodValFile4",s.getfGoodValFile4());
                                param.setFilter("fedGoodValFileType4",s.getFedGoodValFileType4());
                            }
                            //________________________________
                            if(fedNormalValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile1",value);
                                String fedNormalValFileType1 = getFormatInFile(fedNormalValFile1);
                                param.setFilter("fedNormalValFileType1",fedNormalValFileType1);

                            }else{
                                param.setFilter("fedNormalValFile1",s.getfNormalValFile1());
                                param.setFilter("fedNormalValFileType1",s.getFedNormalValFileType1());
                            }
                            if(fedNormalValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile2",value);
                                String fedNormalValFileType2 = getFormatInFile(fedNormalValFile2);
                                param.setFilter("fedNormalValFileType2",fedNormalValFileType2);

                            }else{
                                param.setFilter("fedNormalValFile2",s.getfNormalValFile2());
                                param.setFilter("fedNormalValFileType2",s.getFedNormalValFileType2());
                            }
                            if(fedNormalValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile3",value);
                                String fedNormalValFileType3 = getFormatInFile(fedNormalValFile3);
                                param.setFilter("fedNormalValFileType3",fedNormalValFileType3);

                            }else{
                                param.setFilter("fedNormalValFile3",s.getfNormalValFile3());
                                param.setFilter("fedNormalValFileType3",s.getFedNormalValFileType3());
                            }
                            if(fedNormalValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedNormalValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedNormalValFile4",value);
                                String fedNormalValFileType4 = getFormatInFile(fedNormalValFile4);
                                param.setFilter("fedNormalValFileType4",fedNormalValFileType4);

                            }else{
                                param.setFilter("fedNormalValFile4",s.getfNormalValFile4());
                                param.setFilter("fedNormalValFileType4",s.getFedNormalValFileType4());
                            }

                            //--------------------------------------
                            if(fedBadValFile1.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile1);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile1",value);

                                String fedBadValFileType1 = getFormatInFile(fedBadValFile1);
                                param.setFilter("fedBadValFileType1",fedBadValFileType1);

                            }else{
                                param.setFilter("fedBadValFile1",s.getfBadValFile1());
                                param.setFilter("fedBadValFileType1",s.getFedBadValFileType1());
                            }
                            if(fedBadValFile2.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile2);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile2",value);
                                String fedBadValFileType2 = getFormatInFile(fedBadValFile2);
                                param.setFilter("fedBadValFileType2",fedBadValFileType2);

                            }else{
                                param.setFilter("fedBadValFile2",s.getfBadValFile2());
                                param.setFilter("fedBadValFileType2",s.getFedBadValFileType2());
                            }
                            if(fedBadValFile3.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile3);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile3",value);
                                String fedBadValFileType3 = getFormatInFile(fedBadValFile3);
                                param.setFilter("fedBadValFileType3",fedBadValFileType3);

                            }else{
                                param.setFilter("fedBadValFile3",s.getfBadValFile3());
                                param.setFilter("fedBadValFileType3",s.getFedBadValFileType3());
                            }
                            if(fedBadValFile4.getSize()>0){
                                String ro = MultipartFileTOFile(fedBadValFile4);
                                String value = "http://t-file.xianchida.com/_download/" + ro;
                                param.setFilter("fedBadValFile4",value);
                                String fedBadValFileType4 = getFormatInFile(fedBadValFile4);
                                param.setFilter("fedBadValFileType4",fedBadValFileType4);

                            }else{
                                param.setFilter("fedBadValFile4",s.getfBadValFile4());
                                param.setFilter("fedBadValFileType4",s.getFedBadValFileType4());

                            }
                            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                            param.setUpdTime(new Date());
                            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                            this.modify(SqlId.SQL_ID_MODIFYPIC,param);
                        }



                    }
                }
            }

        }
    }

    public String MultipartFileTOFile(MultipartFile multipartFile) throws Exception{
        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile; //这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        HashMap<String, File> map = new HashMap<String, File>();
        map.put("fedImg",file);
        Map<String, String> result = FileUploadUtil.uploadFiles(map);
        String str = result.toString();
        String ro=str.substring(str.indexOf("=")+1,str.indexOf("}"));
        return ro;
    }

}