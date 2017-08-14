package com.msk.common.pdf;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.*;

/**
 * 根据Apache 提供的pdf box包里面合并PDF文件进行的PDF合并操作的封装.
 *
 */
public class ApachePdfBoxMergeFile implements MergeFile{
    @Override
    public void mergeFile(OutputStream outputStream, InputStream... fileInputStream) {
        PDFMergerUtility mergePdf = this.createPDFMergerUtility(fileInputStream);
        this.mergeDocuments(mergePdf,outputStream);
    }

    @Override
    public void mergeFile(String fileName,InputStream... fileInputStream) {
        PDFMergerUtility mergePdf = this.createPDFMergerUtility(fileInputStream);
        try {
            OutputStream outputStream = new FileOutputStream(new File(fileName));
            this.mergeDocuments(mergePdf,outputStream);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("合并PDF失败,文件不存在");
        }
    }

    private void mergeDocuments(PDFMergerUtility mergePdf,OutputStream outputStream){
        mergePdf.setDestinationStream(outputStream);
        try {
            mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (IOException e) {
            throw new NullPointerException("合并PDF失败");
        }
    }

    private PDFMergerUtility createPDFMergerUtility(InputStream... fileInputStream){
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        if(fileInputStream == null){
            throw new NullPointerException("合并PDF失败,因为传入的PDF File Input Stream为空");
        }
        for (InputStream inputStream : fileInputStream){
            mergePdf.addSource(inputStream);
        }
        return mergePdf;
    }

}
