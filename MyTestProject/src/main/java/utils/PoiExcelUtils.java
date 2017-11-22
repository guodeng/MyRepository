/**
 * 
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @ProjectName MyTestProject
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月22日 下午12:48:47 
 *
 * @UpdateTime 2017年11月22日 下午12:48:47 
 *
 * @Version 
 *
 * @desc
 *
 */
public class PoiExcelUtils {
	
	public  static void main(String args[]) throws IOException{
		creatExcel2007();
	}
	
	/**
	 * 创建 Excel 2007 或2007 版本以上的  Excel 文件
	 * @author zhouzhiwei
	 * @throws Exception
	 */
	public static void creatExcel2007() throws IOException {
		//实例化Workbook对象
		XSSFWorkbook workBook = createXSSFWorkbook();
		//文件输出流
		FileOutputStream os = new FileOutputStream("d:\\style_2007.xlsx");
		//将对象写入文件输出流中
		workBook.write(os);
		//关闭文件输出流
		os.close();
		System.out.println("创建成功 office 2007 excel");
	}

	public static XSSFWorkbook createXSSFWorkbook() {
		//实例化Workbook对象
		XSSFWorkbook workBook = new XSSFWorkbook();
		//构建工作薄
		XSSFSheet sheet = workBook.createSheet();
		//设置第二列的宽度为(Excel 中的行和列都是基于0开始的)
		sheet.setColumnWidth(1, 10000);
		//增加行
		XSSFRow row = sheet.createRow(1);
		// 设置行高(以像素为单位)
		row.setHeightInPoints(23);
		//创建样式对象
		XSSFCellStyle style = workBook.createCellStyle();
		// 设置字体
		XSSFFont font = workBook.createFont();          // 创建字体对象
		font.setFontHeightInPoints((short) 15);         // 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   // 设置粗体
		font.setFontName("黑体");                       // 设置为黑体字
		//将字体加入到样式对象中
		style.setFont(font);
		// 设置对齐方式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);   // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
		// 设置边框
		style.setBorderTop(HSSFCellStyle.BORDER_THICK);        // 顶部边框粗线
		style.setTopBorderColor(HSSFColor.RED.index);          // 设置为红色
		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);    // 底部边框双线
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);      // 左边边框
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);     // 右边边框
		// 格式化日期
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		//创建单元格
		XSSFCell cell = row.createCell(1);
		//填充单元格值
		cell.setCellValue(new Date());
		//应用样式对象
		cell.setCellStyle(style);
		return workBook;
	}
	
	/**
	 * 创建 Excel 2003 版本的  Excel 文件
	 * @author zhouzhiwei
	 * @throws Exception
	 */
	public static void creat2003Excel() throws Exception {
		//实例化  Workbook 对象
		HSSFWorkbook workBook = createHSSFWorkbook();
		//文件输出流
		FileOutputStream os = new FileOutputStream("style_2003.xls");
		//将对象写入文件输出流中
		workBook.write(os);
		//关闭文件输出流
		os.close();
		System.out.println("创建成功 office 2003 excel");
	}
	
	
	public static HSSFWorkbook createHSSFWorkbook(){
		//实例化  Workbook 对象
		HSSFWorkbook workBook = new HSSFWorkbook();
		//创建工作薄
		HSSFSheet sheet = workBook.createSheet();
		//设置第二列的宽度为(Excel 中的行和列都是基于0开始的)
		sheet.setColumnWidth(1, 10000);
		//创建行
		HSSFRow row = sheet.createRow(1);
		//设置行高
		row.setHeightInPoints(23);
		//创建样式对象
		HSSFCellStyle style = workBook.createCellStyle();
		// 设置字体
		HSSFFont font = workBook.createFont();          // 创建字体对象
		font.setFontHeightInPoints((short) 15);         // 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   // 设置粗体
		font.setFontName("黑体");                       // 设置为黑体字
		//将字体加入到样式对象中
		style.setFont(font);
		// 设置对齐方式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);   // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  // 垂直居中
		// 设置边框
		style.setBorderTop(HSSFCellStyle.BORDER_THICK);             // 顶部边框粗线
		style.setTopBorderColor(HSSFColor.RED.index);               // 设置为红色
		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);         // 底部边框双线
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);           // 左边边框
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);          // 右边边框
		// 格式化日期
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		//创建单元格
		HSSFCell cell = row.createCell(1);
		//填充单元格值
		cell.setCellValue(new Date());
		//设置单元格样式
		cell.setCellStyle(style);
		return workBook;
	}
	/**
	 * 解析  Excel 文件
	 * @author zhouzhiwei
	 * @param file 待解析的 Excel 文件路径
	 * @param sheetNumber Excel 文件的 sheet(工作表)个数
	 * @throws Exception
	 * @return List<List<Object>> 从 Excel文件中读取出来的内容
	 */
	public static List<List<Object>> readExcel(File file,int sheetNumber) throws Exception {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		//判断 Excel 文件,是 Excel 2003 还是 Excel 2007 以上的
		if ("xls".equals(extension)) {
			return readExcel2003(file,sheetNumber);
		} else if ("xlsx".equals(extension)) {
			return readExcel2007(file,sheetNumber);
		} else {
			throw new IOException("无法解析该类型!");
		}
	}
	
	public static List<List<Object>> readExcel(InputStream input,String fileName,int sheetNumber) throws Exception{
		List<List<Object>> list = new ArrayList<List<Object>>();
		if(StringUtils.isNotEmpty(fileName)){
			
			String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
			//判断 Excel 文件,是 Excel 2003 还是 Excel 2007 以上的
			if ("xls".equals(extension)) {
				list = readExcel2003(input,sheetNumber);
			} else if ("xlsx".equals(extension)) {
				list = readExcel2007(input,sheetNumber);
			} else if ("csv".equals(extension)) {
				list = readCSV(input,sheetNumber);
			} else {
				throw new IOException("无法解析该类型!");
			}
		}
		return list;
	}
/******************************解析 Excel 2003重载方法************************************************************/	
	
	/**
	 * 解析 Excel 2003
	 * @author zhouzhiwei
	 * @param file 待解析的 Excel 文件路径
	 * @param sheetNumber Excel文件的 sheet(工作表)个数
	 * @throws Exception
	 * @return List<List<Object>> 从 Excel文件中读取出来的内容
	 */
	public static List<List<Object>> readExcel2003(String fileName,int sheetNumber) throws Exception{
		if(StringUtils.isNotEmpty(fileName)||sheetNumber<=0){
			return null;
		}
		File file = new File(fileName);
		return readExcel2003(file,sheetNumber);
	}
	
	public static List<List<Object>> readExcel2003(File file,int sheetNumber) throws Exception{
		if(file==null||sheetNumber<=0){
			return null;
		}
		//实例化 Workbook对象
		FileInputStream inputstream = new FileInputStream(file);
		return readExcel2003(inputstream,sheetNumber);
	}
	
	public static List<List<Object>> readExcel2003(InputStream inputstream,int sheetNumber) throws Exception{
		if(inputstream==null||sheetNumber<=0){
			return null;
		}
		HSSFWorkbook hwb = new HSSFWorkbook(inputstream);
		return readExcel2003(hwb,sheetNumber);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<List<Object>> readExcel2003(HSSFWorkbook hwb,int sheetNumber) throws Exception{
		if(hwb==null||sheetNumber<=0){
			return null;
		}
		List list = new ArrayList();
		for(int k = 0; k < sheetNumber; k ++){
			HSSFSheet sheet = hwb.getSheetAt(k);
			//存储一个表单的数据，内层List<Object>存储每一行的数据
			List<List<Object>> tempList = new LinkedList<List<Object>>();
			Object value = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			//循环读取 sheet 中的行
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i ++) {
				row = sheet.getRow(i);
				//如果该行为空
				if (row == null) {
					continue;
				}
				//创建 List 对象用来保存从每行中读取出来的数据
				List<Object> linked = new LinkedList<Object>();
				//循环读取每行中单元格
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						value = "";
					} else {
						//判断单元格中的值类型
						value = BaseExcelUtils.getStringByHssftype(cell);
					}
					//将单元格的值添加到 linked 中
					linked.add(value);
				}
				//将 linked 对象添加到 list中
				tempList.add(linked);
			}
			//将整个sheet的内容添加到list中
			list.add(tempList);
		}
		return list;
	}
	
	
	
/************************************以下为解析Excel2007**************************************************************/
	/**
	 * 解析 Excel 2007
	 * @author zhouzhiwei
	 * @param file 待解析的 Excel 文件路径
	 * @param sheetNumber Excel文件的 sheet(工作表)个数
	 * @throws Exception
	 * @return List<List<Object>> 从 Excel文件中读取出来的内容
	 */
	public static List<List<Object>> readExcel2007(String fileName,int sheetNumber) throws Exception{
		if(StringUtils.isNotEmpty(fileName)||sheetNumber<=0){
			return null;
		}
		return readExcel2007(fileName,sheetNumber);
	}
	
	public static List<List<Object>> readExcel2007(File file,int sheetNumber) throws Exception{
	    if(file==null||sheetNumber<=0){
	    	return null;
	    }
		//实例化 Workbook对象
		FileInputStream inputstream = new FileInputStream(file);
		return readExcel2007(inputstream,sheetNumber);
	}
	
	public static List<List<Object>> readExcel2007(InputStream inputstream,int sheetNumber) throws Exception{
		if(inputstream==null||sheetNumber<=0){
			return null;
		}
		XSSFWorkbook hwb = new XSSFWorkbook(inputstream);
		return readExcel2007(hwb,sheetNumber);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<List<Object>> readExcel2007(XSSFWorkbook xwb,int sheetNumber) throws Exception {
		if(xwb==null||sheetNumber<=0){
			return null;
		}
		List list = new ArrayList();
		//实例化 Workbook对象
		for(int k = 0; k < sheetNumber; k ++){
			XSSFSheet sheet = xwb.getSheetAt(k);
			List<List<Object>> tempList = new LinkedList<List<Object>>();
			Object value = null;
			XSSFRow row = null;
			XSSFCell cell = null;
			//循环读取 sheet 中的行
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i ++) {
				row = sheet.getRow(i);
				//如果该行为空
				if (row == null) {
					continue;
				}
				//创建 List 对象用来保存从每行中读取出来的数据
				List<Object> linked = new LinkedList<Object>();
				//循环读取每行中的单元格
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					if(null != row.getCell(j)){
						row.getCell(j).setCellType(1);//设置该单元格cell的类型为字符串
					}
					cell = row.getCell(j);
					if (cell == null) {
						value = "";
					} else {
						//判断单元格中的值类型
						value = BaseExcelUtils.getStringByHssftype(cell);
					}
					//将单元格的值添加到 linked 中
					linked.add(value);
				}
				//将 linked 对象添加到 tempList中
				tempList.add(linked);
			}
			//将整个sheet中的内容添加到 list 中
			list.add(tempList);
		}
		return list;
	}
	
	/************************************以下为解析csv**************************************************************/
	@SuppressWarnings("unused")
	public static List<List<Object>> readCSV(InputStream inputstream,int sheetNumber){
		List<List<Object>> tempList = new LinkedList<List<Object>>();
		List<Object> linked = null;
		InputStreamReader reader = null;
		BufferedReader buff = null;
		String inStr = "";
		int index = 0;//行数
		try {
			reader = new InputStreamReader(inputstream, "GB2312");
			buff = new BufferedReader(reader);			
		} catch (Exception e) {
			
		}
		if(buff == null){
			return tempList;
		}
		try {
			while ((inStr = buff.readLine()) != null ) {
				index++;
				linked = new LinkedList<Object>();
				if(index == 1){//这个是文件的表头
					String header[] = inStr.split(",");
				}else{
					if(inStr.endsWith(",")){
						inStr = inStr + " ";
					}	
					String value[] = inStr.split(",");
					for (String string : value) {
						linked.add(string);
					}
					tempList.add(linked);
				}
			}
		} catch (IOException e) {
//			throw new Exception(BaseboxException.FILE_WRITE_ERROR);
		}
		return tempList;
	}
}
