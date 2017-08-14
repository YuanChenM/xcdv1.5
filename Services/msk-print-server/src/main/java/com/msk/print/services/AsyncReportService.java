package com.msk.print.services;

import com.alibaba.fastjson.JSON;
import com.msk.print.bean.AsyncPDFInfoRequestEntity;
import com.msk.print.controller.PrintController;
import com.msk.print.dao.RedisExtendsUtils;
import com.msk.print.report.pdf.jasperreports.JasperReportsUtils;
import com.msk.print.utils.FileUploadUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jxls.area.XlsArea;
import org.jxls.command.ImageCommand;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.common.ImageType;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;
import org.jxls.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供异步调用生成订单方式 service。 Created by ye_wenchen on 2016/7/13.
 */
@Service("AsyncReportService")
public class AsyncReportService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(AsyncReportService.class);

	// 任务未完成状态。
	private final static String TASK_STATUS_RUNNING = "0";
	// 任务完成状态。
	private final static String TASK_STATUS_COMPLETED = "1";
	// 任务报错中断状态。
	private final static String TASK_STATUS_ERROR = "2";

	// 指定存储异步方式任务信息的 redis 操作库索引编号。
	private final static Integer REDIS_DATABASE_INDEX = 10;

	private final static String TEMPLATE = "template";

	@Autowired
	private RedisExtendsUtils redisExtendsUtils;

	public void generatePDFData(AsyncPDFInfoRequestEntity info)
			throws Exception {
		LOGGER.debug("=============generatePDFData==============");

		// 至文件服务器获取数据源。
		Map<String, ?> dataMap = getDataSource(info.getFileCode());
		List<Map<String, ?>> dataSourceList = (List<Map<String, ?>>) dataMap
				.get("dataSource");
		Map<String, Object> parameter = (Map<String, Object>) dataMap
				.get("parameter");

		// 获取PDF模板访问路径。
		updateTaskStatus(info.getTaskID(), TASK_STATUS_RUNNING,
				"正在获取 PDF 打印格式。", info.getModelName());
		String templatePath = getTemplateCodePath(info);
		InputStream templateInputStream = AsyncReportService.class
				.getResourceAsStream(templatePath);

		// 生成PDF。
		updateTaskStatus(info.getTaskID(), TASK_STATUS_RUNNING, "正在生成 PDF 内容。",
				info.getModelName());
		byte[] pdfSource = JasperReportsUtils.exportReportToPdfStreamByJasper(
				parameter, dataSourceList, templateInputStream);

		// 存入文件服务器。
		updateTaskStatus(info.getTaskID(), TASK_STATUS_RUNNING,
				"PDF 数据生成成功，正在通过文件服务器中转。", info.getModelName());
		String fileKey = info.getTaskID();
		String fileID = null;
		try {
			Map<String, String> fileMap = savePDFData(pdfSource, fileKey);
			fileID = fileMap.get(fileKey);
		} catch (IOException e) {
			throw new IOException("文件服务器存入PDF数据失败，具体原因请参考：" + e.getMessage());
		}
		// TODO:更新业务服务状态
		updateTaskStatus(info.getTaskID(), TASK_STATUS_COMPLETED, fileID,
				info.getModelName());
	}

	/**
	 * 获取PDF模板访问路径。
	 *
	 * @param info
	 * @return
	 */
	private String getTemplateCodePath(AsyncPDFInfoRequestEntity info) {
		String templateCode = info.getTemplateCode();
		StringBuffer sb = new StringBuffer();
		sb.append("/pdf/template/");
		sb.append(info.getModelName()).append("/");
		sb.append(templateCode);
		sb.append(".jasper");
		String fullPath = sb.toString();

		return fullPath;
	}

	/**
	 * 获取PDF数据源。
	 *
	 * @return
	 */
	private Map<String, ?> getDataSource(String fileCode) {
		// 至文件服务器获取数据。
		String jsonStringData = FileUploadUtil.getFileString(fileCode);
		Map<String, ?> orderDetailMap = (Map<String, ?>) JSON
				.parse(jsonStringData);
		return orderDetailMap;
	}

	/**
	 * 保存PDF文件至文件服务器。
	 *
	 * @param p_PDFData
	 */
	private Map<String, String> savePDFData(byte[] p_PDFData, String p_fileKey)
			throws IOException {
		InputStream PDFDataInputStream = new ByteArrayInputStream(p_PDFData);
		Map<String, InputStream> fileUploadMap = new HashMap<>();
		fileUploadMap.put(p_fileKey, PDFDataInputStream);
		Map<String, String> result = FileUploadUtil
				.uploadStreamFiles(fileUploadMap);

		return result;
	}

	private void updateTaskStatus(String taskID, String status, String info,
			String modelName) {
		// String url = ConfigManager.getTaskStatusUpdateUrl(modelName);
		// AsyncReportTaskInfo taskInfo = new AsyncReportTaskInfo(taskID,
		// status, info);
		// RestClientUtil.asyncPost(url, taskInfo);
		redisExtendsUtils.setDatabase(REDIS_DATABASE_INDEX);
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		map.put("info", info);
		redisExtendsUtils.saveRedisMap(taskID, map);
	}

	public void generateExcelData(AsyncPDFInfoRequestEntity info)
			throws Exception {
		LOGGER.info("excel 开始！");

		try (InputStream inputStream = PrintController.class
				.getResourceAsStream("/excel/template/" + info.getModelName()
						+ "/" + info.getTemplateCode() + ".xlsx");
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ByteArrayOutputStream byteArrayOutputStreamResult = new ByteArrayOutputStream()) {
			LOGGER.info("excel 造数据开始！");
			updateTaskStatus(info.getTaskID(), TASK_STATUS_RUNNING,
					"正在获取 EXCEL 数据。", info.getModelName());
			List<Map<String, ?>> excelDataList = getExcelDataSource(
					info.getFileCode());
			List<String> sheetNames = new ArrayList<>();
			Map<String, String> images = new HashMap<String, String>();
			if (excelDataList != null) {
				for (Map<String, ?> sheetData : excelDataList) {
					sheetNames.add((String) sheetData.get("sheetName"));
					images = (Map<String, String>) sheetData.get("images");
				}
			}
			LOGGER.info("excel 造数据结束！");
			Context context = PoiTransformer.createInitialContext();
			context.putVar("allExcelData", excelDataList);
			context.putVar("sheetNames", sheetNames);

			// 加载图片
			if (null != images && images.size() > 0) {
				BASE64Decoder base64 = new BASE64Decoder();
				byte[] emptyImage = {};
				for (Map.Entry<String, String> entry : images.entrySet()) {
					context.putVar(entry.getKey(),
							"".equals(entry.getValue()) ? emptyImage
									: base64.decodeBuffer(entry.getValue()));
				}
			}

			long begin = System.currentTimeMillis();
			// 写入excel数据
			updateTaskStatus(info.getTaskID(), TASK_STATUS_RUNNING,
					"正在生成 EXCEL 内容。", info.getModelName());

			JxlsHelper jxlsHelper = JxlsHelper.getInstance();
			jxlsHelper.setUseFastFormulaProcessor(false);
			Transformer transformer = jxlsHelper.createTransformer(inputStream,
					byteArrayOutputStream);
			jxlsHelper.processTemplate(context, transformer);
			PoiTransformer poiTransformer = (PoiTransformer) transformer;
			Workbook workbook = poiTransformer.getWorkbook();
			workbook.removeSheetAt(0);
			workbook.write(byteArrayOutputStreamResult);
			// 存入文件服务器。
			updateTaskStatus(info.getTaskID(), TASK_STATUS_RUNNING,
					"EXCEL 数据生成成功，正在通过文件服务器中转。", info.getModelName());
			long end = System.currentTimeMillis();
			LOGGER.info("写数据总时间： " + (end - begin));
			byte[] b = byteArrayOutputStreamResult.toByteArray();
			String fileCode = "";
			try (InputStream in = new ByteArrayInputStream(b)) {
				String taskId = info.getTaskID();
				Map<String, InputStream> map = new HashMap<>();
				map.put(taskId, in);
				fileCode = FileUploadUtil.uploadStreamFiles(map).get(taskId);
				LOGGER.info("文件code:" + fileCode);
			} catch (Exception e) {
				throw new Exception(
						"文件服务器存入Excel数据失败，具体原因请参考：" + e.getMessage());
			}
			// 成功
			updateTaskStatus(info.getTaskID(), TASK_STATUS_COMPLETED, fileCode,
					info.getModelName());
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Excel数据源。
	 *
	 * @return
	 */
	private List<Map<String, ?>> getExcelDataSource(String fileCode) {
		// 至文件服务器获取数据。
		String jsonStringData = FileUploadUtil.getFileString(fileCode);
		LOGGER.info("data:" + jsonStringData);
		List<Map<String, ?>> excelDataList = (List<Map<String, ?>>) JSON
				.parse(jsonStringData);
		return excelDataList;
	}
}