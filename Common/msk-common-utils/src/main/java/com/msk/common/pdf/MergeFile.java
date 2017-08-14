package com.msk.common.pdf;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件合并处理类,现提供PDF合并操作.
 * @author jiang_nan
 * @version 1.0
 * @see com.msk.common.pdf.ApachePdfBoxMergeFile
 */
public interface MergeFile {
    /**
     * 根据传入的多个文件输入流,创建一个新的文件
     * @param fileName 新的文件名称
     * @param fileInputStream 需要合并的File输出流
     */
    void mergeFile(String fileName,InputStream ...fileInputStream);
    /**
     * 根据传入的多个文件输入流和输出流进行文件合并操作
     * @param outputStream 合并成功的output Stram
     * @param fileInputStream 需要合并的File输出流
     */
    void mergeFile(OutputStream outputStream,InputStream ...fileInputStream);
}
