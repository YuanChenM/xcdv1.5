package com.hoperun.file.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ConfigInfo
 *
 * @author jiang_nan
 * @version 1.0
 **/
@Component("configInfo")
public class ConfigInfo {
    @Value("#{configProperties['test']}")
    private String test;
    @Value("#{configProperties['fileServerPath']}")
    private String fileServerPath;
    public String getTest() {
        return test;
    }
    public String getFileServerPath() {
        return fileServerPath;
    }
}
