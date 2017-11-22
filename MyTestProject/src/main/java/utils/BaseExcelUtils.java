/**
 * 
 */
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @ProjectName MyTestProject
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月22日 上午11:42:25 
 *
 * @UpdateTime 2017年11月22日 上午11:42:25 
 *
 * @Version 
 *
 * @desc  此工具类中包含excel流转其他文件格式所需要的辅助方法
 *
 */
public class BaseExcelUtils {
   
	/**
     * 
     * @param input
     * @return 如果返回2003字符串，说明excel版本是2003，如果返回2007字符串，说明excel版本为2007及以上，如果返回为null则判断失败。
     * @throws IOException 
     */
	public static String checkExcelVersion(InputStream input) throws IOException{
		String flag = null;
		try {
			if(!input.markSupported()) {
				input = new PushbackInputStream(input, 8);
			}
			if(POIFSFileSystem.hasPOIFSHeader(input)) {
				flag = "2003";
			}
			if(POIXMLDocument.hasOOXMLHeader(input)) {
				flag = "2007";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 根据excel文件名判断excel文档版本，如果返回值为空，则此文件不是excel文件
	 * 如果返回2003字符串则excel版本为2003，如果返回null，则认为是2003，
	 * 如果返回2007字符串，则excel版本为2007
	 * @param fileName
	 * @return
	 */
	public static String checkExcelVersion(String fileName){
		String tempStr = null;
		String str = null;
		if(fileName!=null){
			str = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		    if("xls".equals(str)){
		    	tempStr = "2003";
		    }
		    if("xlsx".equals(str)){
		    	tempStr = "2007";
		    }
		}
		return tempStr;
	}
	
	/**
	 * 
	 * 根据POI的Cell对象，返回此对象所存的的值得字符串
	 * @param cell：cell对象
	 * @return 对象所存的的值得字符串
	 */
	@SuppressWarnings("deprecation")
	public static String  getStringByHssftype(Cell cell){
		 String cellvalue = "";
		 if(cell != null){
			 switch (cell.getCellType()) {
			    case Cell.CELL_TYPE_STRING:{//如果是字符型
			    	cellvalue = cell.getStringCellValue();
		    	    	break;
			    }
			  //如果是布尔类型
				case Cell.CELL_TYPE_BOOLEAN:{
					//cellvalue = cell.getBooleanCellValue();
					cellvalue = "";//此地方暂时不正确
					break;
				}
				//如果值为空
				case Cell.CELL_TYPE_BLANK:{
					cellvalue = "";
					break;
				}
			   // 如果当前Cell的Type为NUMERIC
	           case Cell.CELL_TYPE_NUMERIC:{
	           	 // 取得当前Cell的数值
	        	   if(HSSFDateUtil.isCellDateFormatted(cell)){
	        		   //zhuzy 2013-08-22
	        		   Date dateValue = cell.getDateCellValue();
	        		   DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	        		   cellvalue = formater.format(dateValue);
	        		   break;
	        	   }else{
	        		   //zhuzy 判断数字类型是否是格式为20130810类型的日期
	        		   int cellVal = (int)cell.getNumericCellValue();
	        		   Pattern pattern = Pattern.compile("((19|20)\\d\\d)(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])");
	        		   if(pattern.matcher(String.valueOf(cellVal)).matches()){
	        			   cellvalue = String.valueOf(cellVal);
	        		   }else{
	        			   cellvalue = String.valueOf((double)cell.getNumericCellValue());
	        		   }
	        		   break;
	        	   }
	           }
	           case Cell.CELL_TYPE_FORMULA:{
	           	// 判断当前的cell是否为Date
	               if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                   // 如果是Date类型则，转化为Data格式
	                   // 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
	                   // cellvalue =
	                   cell.getDateCellValue().toLocaleString();
	                   // 方法2：这样子的data格式是不带带时分秒的：2011-10-12
	                   Date date = cell.getDateCellValue();
	                   SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
	                   cellvalue = sdfs.format(date);
	               }
	               // 如果是纯数字
	               else {
	                   // 取得当前Cell的数值
	                   cellvalue = String.valueOf((int)cell.getNumericCellValue());
	               }
	               break;
	           }
			default:
				 cellvalue = "";
			}
		 }
		return cellvalue;
    }
}
