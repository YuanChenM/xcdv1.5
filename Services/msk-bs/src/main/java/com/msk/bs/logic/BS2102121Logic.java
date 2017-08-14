package com.msk.bs.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.imageio.ImageIO;

import com.hoperun.core.utils.StringUtil;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.msk.bs.bean.BS2102107Bean;
import com.msk.bs.bean.BS2102116Bean;
import com.msk.bs.bean.BS2102117Bean;
import com.msk.bs.bean.BS2102118Bean;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.core.entity.SlHouseAccount;

import sun.misc.BASE64Encoder;


/**
 * Created by wang_haichun on 2016/8/22.
 */
@Service
public class BS2102121Logic extends ExcelAsyncPrintFasterService {

    private Logger logger = LoggerFactory.getLogger(BS2102121Logic.class);

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param){
        Map<String,Object> mapParam = (Map) param;
        SlHouseAccount houseAccount = new SlHouseAccount();
        List<Map<String,?>> listParam = new ArrayList<>();
        Map<String,Object> paramMap = new HashMap<>();
        Map<String,Object> imageMap = new HashMap<>();
        imageMap.put("uploadUrl1","");
        imageMap.put("uploadUrl2","");

        List<BS2102107Bean> houseManageList = new ArrayList<>();
        List<BS2102116Bean> workList = new ArrayList<>();
        List<BS2102117Bean> eduList = new ArrayList<>();
        List<BS2102118Bean> trainList = new ArrayList<>();

        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("houseManage")){
            houseManageList = (List<BS2102107Bean>) mapParam.get("houseManage");
        }
        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("workList")){
            workList = (List<BS2102116Bean>) mapParam.get("workList");
        }
        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("eduList")){
            eduList = (List<BS2102117Bean>) mapParam.get("eduList");
        }
        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("trainList")){
            trainList = (List<BS2102118Bean>) mapParam.get("trainList");
        }

        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("houseAccount")){
            LinkedHashMap map = (LinkedHashMap) mapParam.get("houseAccount");
            houseAccount.setHouseShowName((String) ((Map) map).get("houseShowName"));
            houseAccount.setFlag1((String) ((Map) map).get("flag1"));
            houseAccount.setFlag7((String)((Map) map).get("flag7"));
            houseAccount.setHouseTel((String)((Map) map).get("houseTel"));
            houseAccount.setWechat((String)((Map) map).get("wechat"));
            houseAccount.setQq((String)((Map) map).get("qq"));
            houseAccount.setRhouseAddress((String)((Map) map).get("rhouseAddress"));
            houseAccount.setHouseAddress((String)((Map) map).get("houseAddress"));
            houseAccount.setSlIdcard((String)((Map) map).get("slIdcard"));
            //所属买手
            houseAccount.setFlag20((String)((Map) map).get("flag20"));
            houseAccount.setVhouseAddress((String)((Map) map).get("vhouseAddress"));
            houseAccount.setHouseCodeDis((String)((Map) map).get("houseCodeDis"));
            //管理区域申报
            houseAccount.setFlag19((String) ((Map) map).get("flag19"));


            if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("houseIntroduce")) {
                LinkedHashMap introduce = (LinkedHashMap) mapParam.get("houseIntroduce");
                if(!CollectionUtils.isEmpty(introduce)){
                    imageMap = this.setImg(imageMap, (String) ((Map) introduce).get("uploadUrl1"),"uploadUrl1");
                    imageMap = this.setImg(imageMap, (String) ((Map) introduce).get("uploadUrl2"),"uploadUrl2");
                }
            }
            houseAccount.setFlag18(imageMap.get("uploadUrl1").toString());
            houseAccount.setFlag17(imageMap.get("uploadUrl2").toString());
        }
        paramMap.put("images", imageMap);
        paramMap.put("houseAccount", houseAccount);
        paramMap.put("houseManage", houseManageList);
        paramMap.put("workList", workList);
        paramMap.put("eduList", eduList);
        paramMap.put("trainList",trainList);
        paramMap.put("sheetName","冻品管家信息数据");
        listParam.add(paramMap);
        return listParam;
    }


    private Map<String,Object> setImg(Map<String,Object> imageMap,String picPath,String key){
        try {
            if(org.springframework.util.StringUtils.hasLength(picPath)) {
                logger.info("图片地址："+picPath);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                URL url = new URL(picPath);

                URLConnection conn = null;
                conn = url.openConnection();
                String fileType = conn.getContentType();

                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                BufferedImage bufferedImage = ImageIO.read(dataInputStream);

                if(!StringUtil.isNullOrEmpty(fileType)){
                    if(fileType.indexOf("/") > -1){
                        fileType = fileType.substring(fileType.indexOf("/")+1,fileType.length());
                        fileType = fileType.toUpperCase();
                        logger.info("文件类型:"+fileType);
                        ImageIO.write(bufferedImage, fileType, bos);
                    }else {
                        ImageIO.write(bufferedImage, "JPEG", bos);
                    }
                }else {
                    ImageIO.write(bufferedImage, "JPEG", bos);
                }
                BASE64Encoder base64 = new BASE64Encoder();
                imageMap.put(key, base64.encode(bos.toByteArray()));
                if(null != bos)
                    bos = null;
                if(null != bufferedImage)
                    bufferedImage = null;
                if(null != dataInputStream)
                    dataInputStream.close();
            }
        } catch (Exception e) {
            try {
                throw new Exception("文件服务器存入Excel数据失败，具体原因请参考：" + e.getMessage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return imageMap;
    }
}
