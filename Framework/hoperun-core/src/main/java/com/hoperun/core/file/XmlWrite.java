package com.hoperun.core.file;

import com.hoperun.core.exception.SystemException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据Freemarker模板生成Xml文件
 * *@author jiang_nan
 * *@version 1.0
 **/
public class XmlWrite<T> {
    private  Configuration config = null;
    private String templateFile;

    /**
     * 默认构造函数
     * @param templatePatch 模板目录
     * @param templateFile 模板文件
     */
    public XmlWrite(String templatePatch,String templateFile){
        this.templateFile = templateFile;
        config = new Configuration();
        config.setClassForTemplateLoading(this.getClass(),templatePatch);
        //config.setDirectoryForTemplateLoading(new File(templatePatch));
        config.setDefaultEncoding("UTF-8");
//        try {
//            config.setClassForTemplateLoading(this.getClass(),templatePatch);
//            //config.setDirectoryForTemplateLoading(new File(templatePatch));
//            config.setDefaultEncoding("UTF-8");
//        } catch (IOException e) {
//           throw new SystemException(e);
//        }
    }

    /**
     * 根据模板文件生成输出XML文件。返回生成的XML文件路径
     * @param entity 传入参数
     * @param outputFile 输出文件名称
     * @return 生成的XML文件路径
     */
    public String createXml(T entity,String outputFile){
        BufferedWriter writer = null;
        String fileTemp = FileUtils.getSystemTmpDir() + File.separator + outputFile;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileTemp), "UTF-8"));
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("entity", entity);
            Template tpl = config.getTemplate(templateFile);
            tpl.setEncoding("UTF-8");
            tpl.process(paramMap, writer);
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        } catch (FileNotFoundException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        } catch (TemplateException e) {
            throw new SystemException(e);
        } finally {
            if (null != writer) {
                try {
                    writer.flush();
                    writer.close();
                }catch (IOException e){
                    throw new SystemException(e);
                }
            }
        }
        return fileTemp;
    }
}
