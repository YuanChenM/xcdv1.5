package com.msk.comm.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by shi_yuxi on 2016/9/19.
 */
public class AllSheetData extends BaseBean{
    //sheetName
    private String sheetName;
    //参数
    private Map parameter;
    //集合数据
    private List<Map> dataSource;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Map getParameter() {
        return parameter;
    }

    public void setParameter(Map parameter) {
        this.parameter = parameter;
    }

    public List<Map> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Map> dataSource) {
        this.dataSource = dataSource;
    }
}
