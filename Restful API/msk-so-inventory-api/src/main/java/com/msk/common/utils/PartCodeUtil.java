package com.msk.common.utils;

import com.msk.comm.exception.BusinessException;
import com.msk.inventory.bean.IvmPartsMasterBean;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by duan_kai on 2016/9/2.
 */
public class PartCodeUtil {

    private static String CLASSES_CODE = "classesCode";
    private static String MACHINING_CODE = "machiningCode";
    private static String BREED_CODE = "breedCode";
    private static String FEATURE_CODE = "featureCode";
    private static String WEIGHT_CODE = "weightCode";
    private static String GRADE_CODE = "gradeCode";

    private static String SL_CODE = "slCode";
    private static String SL_PD_CODE = "slPdCode";

    private static String SKU_CODE = "skuCode";
    private static String PD_CODE = "pdCode";

    public static void parseCodeForSkuCode(Object targetObject, String skuCode) throws Exception{
        if (StringUtils.isEmpty(skuCode)) {
            setFieldValueByName(PartCodeUtil.SL_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.SL_PD_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.SKU_CODE, null, targetObject);
        } else {
            if (skuCode.length() != 12) {
                throw new BusinessException("skuCode("+skuCode+")不符合规范！");
            }
            setFieldValueByName(PartCodeUtil.SL_CODE, skuCode.substring(0, 7), targetObject);
            setFieldValueByName(PartCodeUtil.SL_PD_CODE, skuCode.substring(7, 12), targetObject);
            setFieldValueByName(PartCodeUtil.SKU_CODE, skuCode, targetObject);
        }
    }

    public static void parseCodeForPdCode(Object targetObject, String pdCode) throws Exception{
        if (StringUtils.isEmpty(pdCode)) {
            setFieldValueByName(PartCodeUtil.CLASSES_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.MACHINING_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.BREED_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.FEATURE_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.WEIGHT_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.GRADE_CODE, null, targetObject);
            setFieldValueByName(PartCodeUtil.PD_CODE, null, targetObject);
        } else {
            if (pdCode.length() != 10) {
                throw new BusinessException("pdCode("+pdCode+")不符合规范！");
            }
            setFieldValueByName(PartCodeUtil.CLASSES_CODE, pdCode.substring(0, 2), targetObject);
            setFieldValueByName(PartCodeUtil.MACHINING_CODE, pdCode.substring(2, 3), targetObject);
            setFieldValueByName(PartCodeUtil.BREED_CODE, pdCode.substring(3, 5), targetObject);
            setFieldValueByName(PartCodeUtil.FEATURE_CODE, pdCode.substring(5, 7), targetObject);
            setFieldValueByName(PartCodeUtil.WEIGHT_CODE, pdCode.substring(7, 9), targetObject);
            setFieldValueByName(PartCodeUtil.GRADE_CODE, pdCode.substring(9, 10), targetObject);
            setFieldValueByName(PartCodeUtil.PD_CODE, pdCode, targetObject);
        }
    }

    public static String getPmExternalXml(Object targetObject) throws Exception{
        String classesCode = (String)getFieldValueByName(PartCodeUtil.CLASSES_CODE, targetObject);
        String machiningCode = (String)getFieldValueByName(PartCodeUtil.MACHINING_CODE, targetObject);
        String breedCode = (String)getFieldValueByName(PartCodeUtil.BREED_CODE, targetObject);
        String featureCode = (String)getFieldValueByName(PartCodeUtil.FEATURE_CODE, targetObject);
        String weightCode = (String)getFieldValueByName(PartCodeUtil.WEIGHT_CODE, targetObject);
        String gradeCode = (String)getFieldValueByName(PartCodeUtil.GRADE_CODE, targetObject);

        String slCode = (String)getFieldValueByName(PartCodeUtil.SL_CODE, targetObject);
        String slPdCode = (String)getFieldValueByName(PartCodeUtil.SL_PD_CODE, targetObject);

        String tempStr = (StringUtils.isEmpty(classesCode) ? "" : "<CLS_C>" + classesCode + "</CLS_C>")
                + (StringUtils.isEmpty(machiningCode) ? "" : "<MACH_C>" + machiningCode + "</MACH_C>")
                + (StringUtils.isEmpty(breedCode) ? "" : "<BRE_C>" + breedCode + "</BRE_C>")
                + (StringUtils.isEmpty(featureCode) ? "" : "<FEAT_C>" + featureCode + "</FEAT_C>")
                + (StringUtils.isEmpty(weightCode) ? "" : "<WEI_C>" + weightCode + "</WEI_C>")
                + (StringUtils.isEmpty(gradeCode) ? "" : "<GRAD_C>" + gradeCode + "</GRAD_C>")
                + (StringUtils.isEmpty(slCode) ? "" : "<SL_C>" + slCode + "</SL_C>")
                + (StringUtils.isEmpty(slPdCode) ? "" : "<SL_P_C>" + slPdCode + "</SL_P_C>");
        return tempStr;
    }

    public static String getPmCode(Object targetObject) throws Exception{
        String classesCode = (String)getFieldValueByName(PartCodeUtil.CLASSES_CODE, targetObject);
        String machiningCode = (String)getFieldValueByName(PartCodeUtil.MACHINING_CODE, targetObject);
        String breedCode = (String)getFieldValueByName(PartCodeUtil.BREED_CODE, targetObject);
        String featureCode = (String)getFieldValueByName(PartCodeUtil.FEATURE_CODE, targetObject);
        String weightCode = (String)getFieldValueByName(PartCodeUtil.WEIGHT_CODE, targetObject);
        String gradeCode = (String)getFieldValueByName(PartCodeUtil.GRADE_CODE, targetObject);

        String slCode = (String)getFieldValueByName(PartCodeUtil.SL_CODE, targetObject);
        String slPdCode = (String)getFieldValueByName(PartCodeUtil.SL_PD_CODE, targetObject);

        String tempStr = (StringUtils.isEmpty(classesCode) ? "--" : classesCode)
                + (StringUtils.isEmpty(machiningCode) ? "-" : machiningCode)
                + (StringUtils.isEmpty(breedCode) ? "--" : breedCode)
                + (StringUtils.isEmpty(featureCode) ? "--" : featureCode)
                + (StringUtils.isEmpty(weightCode) ? "--" : weightCode)
                + (StringUtils.isEmpty(gradeCode) ? "-" : gradeCode)
                + (StringUtils.isEmpty(slCode) ? "-------" : slCode)
                + (StringUtils.isEmpty(slPdCode) ? "-----" : slPdCode);
        return tempStr;
    }

    private static void setFieldValueByName(String fieldName, String value, Object o) throws Exception{
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String setter = "set" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(setter, String.class);
            method.invoke(o, value);
        } catch (Exception e) {
            throw e;
        }
    }

    private static Object getFieldValueByName(String fieldName, Object o) throws Exception{
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void main(String[] arg){
        try{
            IvmPartsMasterBean a = new IvmPartsMasterBean();
            PartCodeUtil.parseCodeForPdCode(a, "0120304056");
            PartCodeUtil.parseCodeForSkuCode(a, "111111122222");

            System.out.println("getClassesCode:"+a.getClassesCode());
            System.out.println("getMachiningCode:"+a.getMachiningCode());
            System.out.println("getBreedCode:"+a.getBreedCode());
            System.out.println("getFeatureCode:"+a.getFeatureCode());
            System.out.println("getWeightCode:"+a.getWeightCode());
            System.out.println("getGradeCode:"+a.getGradeCode());
            System.out.println("getPdCode:"+a.getPdCode());

            System.out.println("getSlCode:"+a.getSlCode());
            System.out.println("getSlPdCode:"+a.getSlPdCode());
            System.out.println("getSkuCode:"+a.getSkuCode());

            System.out.println("getPmExternalXml:"+PartCodeUtil.getPmExternalXml(a));
            System.out.println("getPmCode:"+PartCodeUtil.getPmCode(a));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
