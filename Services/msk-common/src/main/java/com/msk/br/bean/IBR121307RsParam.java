package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;

/**
 * Created by tao_zhifa on 2016/8/11.
 */
public class IBR121307RsParam extends BasePageParam {

    /** ID */
    private Long id;
    /** CLASSES_CODE */
    private String classesCode;
    /** CLASSES_NAME */
    private String classesName;
    // 0 为查询出一级产品,1 为查询出二级产品
    private String type;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }
}
