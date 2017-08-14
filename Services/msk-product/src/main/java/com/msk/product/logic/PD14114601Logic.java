package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.PdOrgStd;
import com.msk.core.entity.PdOrgStdItem;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD14114601Bean;
import com.msk.product.bean.PdOrgStdImg;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PD14114601Logic
 *
 * @author xhy
 */
@Service
public class PD14114601Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_FIND_PD_ORG_STD = "findOrgStdList";
        static final String SQL_ID_FIND_BREED_NAME_FEANAME = "findName";
        static final String SQL_ID_SAVE_ORG_IMG = "saveOrgImg";

        static final String SQL_ID_MODIFY_IMG_ORG = "modifyOrgImg";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询所有orgstdTime
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public List<PdOrgStdItem> findListPdOrgStd(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_FIND_PD_ORG_STD, param);
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

    /**
     * 保存修改数据
     *
     * @param bean
     * @param param
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void saveAndEdit(PD14114601Bean bean, BaseParam param) throws IOException {

        if (StringUtils.isNotBlank(bean.getStandardId().toString())) {
            param.setFilter("standardId", bean.getStandardId().toString());
            List<PD14114601Bean> list = super.findList(param);

            Map<String, File> fileMap = new HashMap<String, File>();
            if (list.size() <= NumberConst.IntDef.INT_ZERO) {
                //新增操作
                if (bean.getOrgStdItemIdArray().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = 0; i < bean.getOrgStdItemIdArray().length; i++) {
                        PdOrgStd pd = new PdOrgStd();
                        pd.setOrgBadVal(bean.getOrgBadValArray()[i]);
                        pd.setOrgGoodVal(bean.getOrgGoodValArray()[i]);
                        pd.setOrgNormalVal(bean.getOrgNormalValArray()[i]);
                        pd.setOrgStdItemId(bean.getOrgStdItemIdArray()[i]);
                        pd.setStandardId(bean.getStandardId());
                        pd.setCrtId(param.getCrtId());
                        pd.setUpdId(param.getUpdId());
                        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
                        pd.setActId(param.getActId());
                        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        pd.setActTime(new Date());
                        pd.setCrtTime(new Date());
                        pd.setUpdTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        PdOrgStdImg imgOrg = this.imgInfoDo(bean, i, fileMap);

                        imgOrg.setCrtId(param.getCrtId());
                        imgOrg.setUpdId(param.getUpdId());
                        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
                        imgOrg.setActId(param.getActId());
                        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        imgOrg.setActTime(new Date());
                        imgOrg.setUpdTime(new Date());
                        imgOrg.setCrtTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        fileMap.clear();
                        super.save(pd);
                        super.save(SqlId.SQL_ID_SAVE_ORG_IMG, imgOrg);
                    }
                }
            } else {
                //修改操作
                if (bean.getOrgStdItemIdArray().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < bean.getOrgStdItemIdArray().length; i++) {
                        PdOrgStd pd = new PdOrgStd();
                        pd.setOrgBadVal(bean.getOrgBadValArray()[i]);
                        pd.setOrgGoodVal(bean.getOrgGoodValArray()[i]);
                        pd.setOrgNormalVal(bean.getOrgNormalValArray()[i]);
                        pd.setOrgStdItemId(bean.getOrgStdItemIdArray()[i]);
                        pd.setStandardId(bean.getStandardId());
                        pd.setUpdId(param.getUpdId());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        pd.setUpdTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        PdOrgStdImg imgOrg = this.imgInfoDo(bean, i, fileMap);
                        imgOrg.setUpdId(param.getUpdId());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                        imgOrg.setUpdTime(new Date());
                        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                        fileMap.clear();
                        super.modify(SqlId.SQL_ID_MODIFY_IMG_ORG, imgOrg);
                        super.modify(pd);
                    }
                }
            }

        }
    }

    /**
     * 通用方法,转换类型
     *
     * @param orgMGoodValFile1
     * @return
     */
    public static File conversionBeComeFile(MultipartFile orgMGoodValFile1) {
        CommonsMultipartFile commonM = (CommonsMultipartFile) orgMGoodValFile1;
        DiskFileItem disFile = (DiskFileItem) commonM.getFileItem();
        File file = disFile.getStoreLocation();
        return file;
    }

    /**
     * 通用方法,获取文件后缀名
     *
     * @param fileAllName
     * @return String
     */
    public static String suffixName(String fileAllName) {
        return fileAllName.substring(fileAllName.lastIndexOf("."), fileAllName.length());

    }

    /**
     * 共同方法
     *
     * @param bean
     * @param i
     * @param fileMap
     * @return
     * @throws java.io.IOException
     */
    private PdOrgStdImg imgInfoDo(PD14114601Bean bean, int i, Map<String, File> fileMap) throws IOException {
        //保存图片操作
        MultipartFile orgMGoodValFile1 = bean.getOrgMGoodValFile1()[i];
        MultipartFile orgMGoodValFile2 = bean.getOrgMGoodValFile2()[i];
        MultipartFile orgMGoodValFile3 = bean.getOrgMGoodValFile3()[i];
        MultipartFile orgMGoodValFile4 = bean.getOrgMGoodValFile4()[i];

        MultipartFile normalMValFile1 = bean.getOrgMNormalValFile1()[i];
        MultipartFile normalMValFile2 = bean.getOrgMNormalValFile2()[i];
        MultipartFile normalMValFile3 = bean.getOrgMNormalValFile3()[i];
        MultipartFile normalMValFile4 = bean.getOrgMNormalValFile4()[i];

        MultipartFile badMValFile1 = bean.getOrgMBadValFile1()[i];
        MultipartFile badMValFile2 = bean.getOrgMBadValFile2()[i];
        MultipartFile badMValFile3 = bean.getOrgMBadValFile3()[i];
        MultipartFile badMValFile4 = bean.getOrgMBadValFile4()[i];

        File orgGoodValFile1 = this.conversionBeComeFile(orgMGoodValFile1);
        File orgGoodValFile2 = this.conversionBeComeFile(orgMGoodValFile2);
        File orgGoodValFile3 = this.conversionBeComeFile(orgMGoodValFile3);
        File orgGoodValFile4 = this.conversionBeComeFile(orgMGoodValFile4);

        File normalValFile1 = this.conversionBeComeFile(normalMValFile1);
        File normalValFile2 = this.conversionBeComeFile(normalMValFile2);
        File normalValFile3 = this.conversionBeComeFile(normalMValFile3);
        File normalValFile4 = this.conversionBeComeFile(normalMValFile4);

        File badValFile1 = this.conversionBeComeFile(badMValFile1);
        File badValFile2 = this.conversionBeComeFile(badMValFile2);
        File badValFile3 = this.conversionBeComeFile(badMValFile3);
        File badValFile4 = this.conversionBeComeFile(badMValFile4);

        //上传服务器,返回唯一id   优良图片
        if (StringUtils.isNotBlank(orgMGoodValFile1.getOriginalFilename())) {
            fileMap.put("orgGoodValFile1", orgGoodValFile1);
        }
        if (StringUtils.isNotBlank(orgMGoodValFile2.getOriginalFilename())) {
            fileMap.put("orgGoodValFile2", orgGoodValFile2);
        }
        if (StringUtils.isNotBlank(orgMGoodValFile3.getOriginalFilename())) {
            fileMap.put("orgGoodValFile3", orgGoodValFile3);
        }
        if (StringUtils.isNotBlank(orgMGoodValFile4.getOriginalFilename())) {
            fileMap.put("orgGoodValFile4", orgGoodValFile4);
        }
        //合格图片
        if (StringUtils.isNotBlank(normalMValFile1.getOriginalFilename())) {
            fileMap.put("normalValFile1", normalValFile1);
        }
        if (StringUtils.isNotBlank(normalMValFile2.getOriginalFilename())) {
            fileMap.put("normalValFile2", normalValFile2);
        }
        if (StringUtils.isNotBlank(normalMValFile3.getOriginalFilename())) {
            fileMap.put("normalValFile3", normalValFile3);
        }
        if (StringUtils.isNotBlank(normalMValFile4.getOriginalFilename())) {
            fileMap.put("normalValFile4", normalValFile4);
        }
        //差值图片
        //合格图片
        if (StringUtils.isNotBlank(badMValFile1.getOriginalFilename())) {
            fileMap.put("badValFile1", badValFile1);
        }
        if (StringUtils.isNotBlank(badMValFile2.getOriginalFilename())) {
            fileMap.put("badValFile2", badValFile2);
        }
        if (StringUtils.isNotBlank(badMValFile3.getOriginalFilename())) {
            fileMap.put("badValFile3", badValFile3);
        }
        if (StringUtils.isNotBlank(badMValFile4.getOriginalFilename())) {
            fileMap.put("badValFile4", badValFile4);
        }
        Map<String, String> nameMap = FileUploadUtil.uploadFiles(fileMap);
        //上传图片成功,保存数据到img表中
        PdOrgStdImg ImgOrg = new PdOrgStdImg();
        ImgOrg.setStandardId(bean.getStandardId());
        ImgOrg.setOrgStdItemId(bean.getOrgStdItemIdArray()[i]);
        //保存图片 优良图片
        if (StringUtils.isNotBlank(nameMap.get("orgGoodValFile1"))) {
            ImgOrg.setOrgGoodValFile1(ConfigManager.getFileServer() + "/" + nameMap.get("orgGoodValFile1"));
            ImgOrg.setOrgGoodValFiletype1(this.suffixName(orgMGoodValFile1.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("orgGoodValFile2"))) {
            ImgOrg.setOrgGoodValFile2(ConfigManager.getFileServer() + "/" + nameMap.get("orgGoodValFile2"));
            ImgOrg.setOrgGoodValFiletype2(this.suffixName(orgMGoodValFile2.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("orgGoodValFile3"))) {
            ImgOrg.setOrgGoodValFile3(ConfigManager.getFileServer() + "/" + nameMap.get("orgGoodValFile3"));
            ImgOrg.setOrgGoodValFiletype3(this.suffixName(orgMGoodValFile3.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("orgGoodValFile4"))) {
            ImgOrg.setOrgGoodValFile4(ConfigManager.getFileServer() + "/" + nameMap.get("orgGoodValFile4"));
            ImgOrg.setOrgGoodValFiletype4(this.suffixName(orgMGoodValFile4.getOriginalFilename()));
        }
        //合格图片
        if (StringUtils.isNotBlank(nameMap.get("normalValFile1"))) {
            ImgOrg.setOrgNormalValFile1(ConfigManager.getFileServer() + "/" + nameMap.get("normalValFile1"));
            ImgOrg.setOrgNormalValFiletype1(this.suffixName(normalMValFile1.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("normalValFile2"))) {
            ImgOrg.setOrgNormalValFile2(ConfigManager.getFileServer() + "/" + nameMap.get("normalValFile2"));
            ImgOrg.setOrgNormalValFiletype2(this.suffixName(normalMValFile2.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("normalValFile3"))) {
            ImgOrg.setOrgNormalValFile3(ConfigManager.getFileServer() + "/" + nameMap.get("normalValFile3"));
            ImgOrg.setOrgNormalValFiletype3(this.suffixName(normalMValFile3.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("normalValFile4"))) {
            ImgOrg.setOrgNormalValFile4(ConfigManager.getFileServer() + "/" + nameMap.get("normalValFile4"));
            ImgOrg.setOrgNormalValFiletype4(this.suffixName(normalMValFile4.getOriginalFilename()));
        }
        //差值图片
        if (StringUtils.isNotBlank(nameMap.get("badValFile1"))) {
            ImgOrg.setOrgBadValFile1(ConfigManager.getFileServer() + "/" + nameMap.get("badValFile1"));
            ImgOrg.setOrgBadValFiletype1(this.suffixName(badMValFile1.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("badValFile2"))) {
            ImgOrg.setOrgBadValFile2(ConfigManager.getFileServer() + "/" + nameMap.get("badValFile2"));
            ImgOrg.setOrgBadValFiletype2(this.suffixName(badMValFile2.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("badValFile3"))) {
            ImgOrg.setOrgBadValFile3(ConfigManager.getFileServer() + "/" + nameMap.get("badValFile3"));
            ImgOrg.setOrgBadValFiletype3(this.suffixName(badMValFile3.getOriginalFilename()));
        }
        if (StringUtils.isNotBlank(nameMap.get("badValFile4"))) {
            ImgOrg.setOrgBadValFile4(ConfigManager.getFileServer() + "/" + nameMap.get("badValFile4"));
            ImgOrg.setOrgBadValFiletype4(this.suffixName(badMValFile4.getOriginalFilename()));
        }
        return ImgOrg;
    }
}
