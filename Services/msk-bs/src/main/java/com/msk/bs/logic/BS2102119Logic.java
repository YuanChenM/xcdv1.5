package com.msk.bs.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.imageio.ImageIO;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.BS2102125Bean;
import com.msk.common.service.ExcelAsyncPrintFasterService;

import sun.misc.BASE64Encoder;


/**
 * Created by wang_haichun on 2016/8/22.
 */
@Service
public class BS2102119Logic extends ExcelAsyncPrintFasterService {

    //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 start
   /* @Override
    public Map<String, ?> getDataSource(Object param) {
        LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) param;
        //读取图片
        return map;
    }*/
    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param){
        Map<String,Object> mapParam = (Map) param;
        BS2102125Bean bean = new BS2102125Bean();
        List<BS2102125Bean> list = new ArrayList<>();
        List<Map<String,?>> listParam = new ArrayList<>();
        Map<String,Object> paramMap = new HashMap<>();
        Map<String,Object> imageMap = new HashMap<>();
        imageMap.put("image", "");
        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("bsBasicInfo")){
            LinkedHashMap map = (LinkedHashMap) mapParam.get("bsBasicInfo");
            paramMap.put("memo7", ((Map) map).get("memo7"));
            paramMap.put("memo9", ((Map) map).get("memo9"));
            paramMap.put("memo15", ((Map) map).get("memo15"));
            paramMap.put("memo1", ((Map) map).get("memo1"));
            paramMap.put("memo2", ((Map) map).get("memo2"));
            paramMap.put("slCodeDis", ((Map) map).get("slCodeDis"));
            paramMap.put("agentType", ((Map) map).get("agentType"));
            paramMap.put("distribution", ((Map) map).get("distribution"));
            paramMap.put("demesne", ((Map) map).get("demesne"));
            paramMap.put("registerSource", ((Map) map).get("registerSource"));
            paramMap.put("cityName", ((Map) map).get("cityName"));
            paramMap.put("lgcsAreaName", ((Map) map).get("lgcsAreaName"));
            bean.setMemo1((String)((Map) map).get("memo1"));
            bean.setMemo7((String)((Map) map).get("memo7"));
            bean.setMemo9((String) ((Map) map).get("memo9"));
            bean.setMemo15((String) ((Map) map).get("memo15"));
            bean.setMemo15(StringUtil.isNullOrEmpty(bean.getMemo15()) ? "" : bean.getMemo15());
            bean.setMemo2((String)((Map) map).get("memo2"));
            bean.setSlCodeDis((String)((Map) map).get("slCodeDis"));
            bean.setAgentType((String)((Map) map).get("agentType"));
            bean.setDistribution((String) ((Map) map).get("distribution"));
            bean.setDemesne((String) ((Map) map).get("demesne"));
            bean.setRegisterSource((String) ((Map) map).get("registerSource"));
            bean.setCityName((String) ((Map) map).get("cityName"));
            bean.setLgcsAreaName((String) ((Map) map).get("lgcsAreaName"));
            try {
                String picture = bean.getMemo15();
                if(org.springframework.util.StringUtils.hasLength(picture)) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    URL url = new URL(picture);
                    URLConnection conn = null;
                    conn = url.openConnection();
                    String fileType = conn.getContentType();

                    DataInputStream dataInputStream = new DataInputStream(url.openStream());
                    BufferedImage bufferedImage = ImageIO.read(dataInputStream);
                    if(!StringUtil.isNullOrEmpty(fileType)){
                        if(fileType.indexOf("/") > -1){
                            fileType = fileType.substring(fileType.indexOf("/")+1,fileType.length());
                            fileType = fileType.toUpperCase();
                            ImageIO.write(bufferedImage, fileType, bos);
                        }else {
                            ImageIO.write(bufferedImage, "JPEG", bos);
                        }
                    }else {
                        ImageIO.write(bufferedImage, "JPEG", bos);
                    }
                    BASE64Encoder base64 = new BASE64Encoder();
                    imageMap.put("image", base64.encode(bos.toByteArray()));
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
        }
        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("bsLevel")){
            LinkedHashMap map = (LinkedHashMap) mapParam.get("bsLevel");
            paramMap.put("oneLever", ((Map) map).get("oneLever"));
            paramMap.put("twoLever", ((Map) map).get("twoLever"));
            paramMap.put("threeLever", ((Map) map).get("threeLever"));
            bean.setOneLever((String)((Map) map).get("oneLever"));
            bean.setTwoLever((String) ((Map) map).get("twoLever"));
            bean.setThreeLever((String) ((Map) map).get("threeLever"));
        }
        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("slAccount")){
            LinkedHashMap map = (LinkedHashMap) mapParam.get("slAccount");
            paramMap.put("flag1", ((Map) map).get("flag1"));
            paramMap.put("slContact", ((Map) map).get("slContact"));
            paramMap.put("accountName", ((Map) map).get("accountName"));
            bean.setAccountName((String) ((Map) map).get("accountName"));
            bean.setFlag1((String) ((Map) map).get("flag1"));
            bean.setSlContact((String) ((Map) map).get("slContact"));
        }
        if(MapUtils.isNotEmpty(mapParam) && mapParam.containsKey("bsSexAndBankInfo")){
            LinkedHashMap map = (LinkedHashMap) mapParam.get("bsSexAndBankInfo");
            paramMap.put("bankName", ((Map) map).get("bankName"));
            paramMap.put("bankNo", ((Map) map).get("bankNo"));
            bean.setBankName((String) ((Map) map).get("bankName"));
            bean.setBankNo((String) ((Map) map).get("bankNo"));
        }
        list.add(bean);
        paramMap.put("list",list);
        paramMap.put("sheetName","买手信息数据");
        paramMap.put("images", imageMap);
        listParam.add(paramMap);
        return listParam;
    }
    //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 end
}
