package com.msk.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msk.common.utils.FreemarkerUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.msk.common.bean.ControllerResult;
import com.msk.common.utils.RestTest;
import com.msk.common.utils.TestUtils;

/**
 * *ScanClassTest
 * *@author jiang_nan
 * *@version 1.0
 */
public class ScanClassTest {
    @Test
    public void testScanClassTest() throws MalformedURLException, ClassNotFoundException, FileNotFoundException {
        URLClassLoader classLoader = TestUtils.getClassLoader();
        URLClassLoader testClassLoader = TestUtils.getTestClassLoad();
        String testResource = TestUtils.getTestResource();
        String packageName = "com.msk.seller.rest";
        String packagePath = TestUtils.getRestPackagePatch(packageName);
        File packagePathDir = new File(packagePath);
        Collection<File> files = TestUtils.getClassFileList(packagePathDir);
        String  baseUrl = "http://10.20.16.152:8888/msk-seller/api/v1/";
        RestTest restTest = new RestTest(files,packageName,classLoader,testResource,baseUrl);
        List<ControllerResult> listPage = restTest.testRest();
        restTest.writerFile(listPage,testClassLoader);
    }

}
