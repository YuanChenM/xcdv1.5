package com.msk.common.utils;

import com.msk.comm.exception.BusinessException;
import com.msk.inventory.bean.ISO152413ProductParamBean;
import com.msk.inventory.bean.ISO152413ParamBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/8/24.
 */
public class SplitListParamUtil {

    /**
     * 根据属性名对应到集合属性，并且按照规定的数量切分成多个PARAM对象
     * param必须实现Cloneable接口
     *
     * return 对应属性的切分后的list
     */
    public static List splitParam(Object param, int numberPer, String propertyName) throws Exception{
        if(param == null || numberPer < 1 || StringUtil.isEmpty(propertyName)){
            throw new BusinessException("非法参数(对象为空，或者切分数量小于1，或者无属性名称)，不能处理。");
        }

        List splitList = new ArrayList();
        Object findProperty = getFieldValueByName(propertyName, param);

        if("java.util.ArrayList".equals(findProperty.getClass().getName())){
            List listProperty = (java.util.ArrayList)findProperty;
            int size = listProperty.size();
            int times = listProperty.size()/numberPer+1;
            for(int i=0;i<times;i++){
                splitList.add(listProperty.subList(i * numberPer, (i + 1) * numberPer > size ? size : (i + 1) * numberPer));
            }
        }else{
            return splitList;
        }
        return splitList;
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
        List<ISO152413ProductParamBean> a = new ArrayList<ISO152413ProductParamBean>();
        ISO152413ProductParamBean b = null;
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        b = new ISO152413ProductParamBean();
        b.setBreedCode("1");
        a.add(b);
        ISO152413ParamBean testP = new ISO152413ParamBean();
        testP.setProducts(a);

        try {
            List<List> c = SplitListParamUtil.splitParam(testP, 4, "iso152413ProductParamBeanList");

            for (int i = 0; i < c.size(); i++) {
                List temp = c.get(i);
                System.out.println(temp.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
