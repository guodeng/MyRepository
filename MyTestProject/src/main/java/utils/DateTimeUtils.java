package utils;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @ProjectName MyTestProject
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月22日 上午11:07:15 
 *
 * @UpdateTime 2017年11月22日 上午11:07:15 
 *
 * @Version 
 *
 * @desc  日期转换工具类
 *
 */
public class DateTimeUtils {

	public final static String FORMATyyyyMMdd = "yyyyMMdd";
	
	public final static String FORMATyyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	public final static String FORMAT_yyyy = "yyyy";
	
	public final static String FORMAT_yyyy_MM = "yyyy-MM";
	
    public final static String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

    public final static String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    
    public final static String FORMAT_yyyy_nian_MM_yue_mm_ri = "yyyy年MM月dd日";
    
    public final static String FORMAT_yyyy_nian_M_yue_m_ri = "yyyy年M月d日";
    
    public final static String FORMAT_yyyyXMMxdd = "yyyy/MM/dd";
  public final static String FORMAT_HHmmss = "HH:mm:ss";

    
    /**日期显示中文
     * 例：
     * 2008-10-20->二〇〇八年十月二十日
     * 2008-11-21->二〇〇八年十一月二十一日
     * 2008-01-29->二〇〇八年一月二十九日
     * @param strDate yyyy-MM-dd
     * @return 中文显示日期
     */
    public static String convertChar2ChineseChar(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return "";
        }
        String year = strDate.substring(0, 4);
        String month = strDate.substring(5, 7);
        String day = strDate.substring(8, 10);
      return convertChar2ChineseOne(year) + "年"
        + convertChar2ChineseTwo(month) + "月"
        + convertChar2ChineseTwo(day) + "日";
    }
    
    private static String convertChar2ChineseTwo(String str) {
        int num = Integer.parseInt(str);
        if (num < 10) {
            return convertChar2ChineseOne("" + num);
        }
        int tenNum = Integer.parseInt(str.substring(0, 1));//十位
        int singleNum = Integer.parseInt(str.substring(1));//个位
        String ten = "十";
        if (tenNum > 1) {
            ten = convertChar2ChineseOne("" + tenNum) + ten;
        }
        String single = "";
        if (singleNum > 0) {
            single = convertChar2ChineseOne("" + singleNum);
        }
        return ten + single;
    }
    
    private static String convertChar2ChineseOne(String str) {
        str = str.replace('0', '〇');
        str = str.replace('1', '一');
        str = str.replace('2', '二');
        str = str.replace('3', '三');
        str = str.replace('4', '四');
        str = str.replace('5', '五');
        str = str.replace('6', '六');
        str = str.replace('7', '七');
        str = str.replace('8', '八');
        str = str.replace('9', '九');
        return str;
    }
    
    /**
     * Date转换到Calendar
     * @param date 要转换的Date
     * @return Calendar
     */
    public static Calendar date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 设置指定的Calendar“时、分、妙”为零
     * @param calendar Calendar
     */
    public static void setTimeZero(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    /**
     * 得到当前时间的字符串yyyy-MM-dd
     * @return String
     */
    public static String now2StrDate() {
        return now2Str(FORMAT_yyyy_MM_dd);
    }

    /**
     * 得到当前时间的字符串yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String now2StrDateTime() {
        return now2Str(FORMAT_yyyy_MM_dd_HH_mm_ss);
    }
    
    /**
     * 得到当前时间的字符串yyyyMMdd
     * @return String
     */
    public static String now2StrDateWithOutToken() {
        return now2Str(FORMATyyyyMMdd);
    }

    /**
     * 得到当前时间的字符串yyyyMMddHHmmss
     * @return String
     */
    public static String now2StrDateTimeWithOutToken() {
        return now2Str(FORMATyyyyMMddHHmmss);
    }

    /**
     * 得到当前时间的字符串
     * @param format 字符串格式
     * @return String
     */
    public static String now2Str(String format) {
        return DateFormatUtils.format(new Date(), format);
    }

    /**
     * Date转换到字符串yyyy-MM-dd
     * @param date Date
     * @return String yyyy-MM-dd
     */
    public static String date2StrDate(Date date) {
        return DateFormatUtils.format(date, FORMAT_yyyy_MM_dd);
    }

    /**
     * Date转换到字符串yyyy/MM/dd
     * @param date Date
     * @return String yyyy-MM-dd
     */
    public static String date2StrXieDate(Date date) {
        return DateFormatUtils.format(date, FORMAT_yyyyXMMxdd);
    }

    /**
     * Date转换到字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2StrDate(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    /**
     * Date转换到字符串yyyy-MM-dd HH:mm:ss
     * @param date Date
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String date2StrDateTime(Date date) {
        return DateFormatUtils.format(date, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }

  /**
   * 给定指定的时间加一秒，
   * 也就是向后推迟一秒
   * @param date
   * @return
   */
    public static String plusOneSeconds(Date date){
      if(null != date){
        final long mill = date.getTime() + 1000;
        return date2StrDateTime(new Date(mill));
      }
      return null;
    }

    public static Date plusOneSeconds2(Date date){
      if(null != date){
        final long mill = date.getTime() + 1000;
        return new Date(mill);
      }
      return null;
    }

    /**
     * Calendar转换到字符串yyyy-MM-dd
     * @param calendar Calendar
     * @return String yyyy-MM-dd
     */
    public static String calendar2StrDate(Calendar calendar) {
        return date2StrDate(calendar.getTime());
    }

    /**
     * Calendar转换到字符串
     * @param calendar Calendar
     * @param format 格式
     * @return 日期字符串
     */
    public static String calendar2StrDate(Calendar calendar, String format) {
    	return date2StrDate(calendar.getTime(), format);
    }

    /**
     * Calendar转换到字符串yyyy-MM-dd HH:mm:ss
     * @param calendar Calendar
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String calendar2StrDateTime(Calendar calendar) {
        return date2StrDateTime(calendar.getTime());
    }

    /**
     * 字符串yyyy-MM-dd转换到Calendar类型
     * @param dateStr yyyy-MM-dd
     * @return Calendar
     */
    public static Calendar strDate2Calendar(String dateStr) {
        return str2Calendar(dateStr, FORMAT_yyyy_MM_dd);
    }

    /**
     * 字符串yyyy-MM-dd转换到Date类型
     * @param dateStr yyyy-MM-dd
     * @return Date
     */
    public static Date strDate2Date(String dateStr) {
        return str2Date(dateStr, FORMAT_yyyy_MM_dd);
    }

    /**
     * 字符串yyyy-MM-dd HH:mm:ss转换到Calendar类型
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @return Calendar
     */
    public static Calendar strDateTime2Calendar(String dateStr) {
        return str2Calendar(dateStr, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 字符串yyyy-MM-dd HH:mm:ss转换到Date类型
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date strDateTime2Date(String dateStr) {
        return str2Date(dateStr, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 字符串转换到Date类型
     * @param dateStr 需要转换的字符串
     * @param format 转换格式
     * @return Date
     */
    public static Date str2Date(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
      return dateFormat.parse(dateStr, new ParsePosition(0));
    }

    /**
     * 字符串转换到Calendar类型
     * @param dateStr 需要转换的字符串
     * @param format 转换格式
     * @return Calendar
     */
    public static Calendar str2Calendar(String dateStr, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(str2Date(dateStr, format));
        return calendar;
    }

    /**
     *  得到当前日期的Calendar类型
     * @return Calendar;
     */
    public static Calendar now2Calendar() {
        return Calendar.getInstance();
    }

    /**
     * 日期时间字符串比较(大于)
     * @param targetDateStr
     * @param dateStr
     * @return targetDateStr > dateStr 返回true，否则返回false
     */
    public static boolean greater(String targetDateStr, String dateStr) {
    	return Long.valueOf(targetDateStr.replaceAll("[-\\s:]",""))
    			> Long.valueOf(dateStr.replaceAll("[-\\s:]",""));
    }

    /**
     * 日期时间字符串比较(相等)
     * @param targetDateStr
     * @param dateStr
     * @return targetDateStr == dateStr 返回true，否则返回false
     */
    public static boolean equales(String targetDateStr, String dateStr) {
    	return Long.valueOf(targetDateStr.replaceAll("[-\\s:]",""))
    			== Long.valueOf(dateStr.replaceAll("[-\\s:]",""));
    }

    /**
     * 日期加减计算
     * @param date 日期字符串，格式yyyy_MM_dd
     * @param day 正为加负为减
     * @return 日期字符串
     */
    public static String dateStrCalculate(String date, int day) {
    	Calendar nowCalender = DateTimeUtils.strDate2Calendar(date);
    	nowCalender.add(Calendar.DAY_OF_MONTH, day);
    	return calendar2StrDate(nowCalender, FORMAT_yyyy_MM_dd);
    }

    /**
     * 日期加减计算
     * @param date 日期字符串，格式yyyy_MM_dd_HH_mm_ss
     * @param day 正为加负为减
     * @return 日期字符串
     */
    public static String dateTimeStrCalculate(String date, int day) {
    	Calendar nowCalender = DateTimeUtils.strDateTime2Calendar(date);
    	nowCalender.add(Calendar.DAY_OF_MONTH, day);
    	return calendar2StrDate(nowCalender, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获得两个日期字符串相差多少天
     * @param tarDate 日期字符串
     * @param curDate 日期字符串
     * @return 天数
     */
    public static int dateStrCalculate(String tarDate, String curDate) {
    	return (int)((DateTimeUtils.strDate2Calendar(tarDate).getTimeInMillis()
    			- DateTimeUtils.strDate2Calendar(curDate).getTimeInMillis()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取日期对应的星期几
     * @param date 日期字符串，格式yyyy_MM_dd
     * @return int
     */
    public static int getWeekdayFromDateStr(String date) {
    	return DateTimeUtils.str2Calendar(date, DateTimeUtils.FORMAT_yyyy_MM_dd).get(
				Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某个月的总天数
     * @param date 日期字符串，格式yyyy_MM
     * @return int
     */
    public static int getDaysOfMonthFromDateStr(String date) {
    	return str2Calendar(date, FORMAT_yyyy_MM).getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 月份加减计算
     * @param date
     * @param month
     */
    public static String monthStrCalculate(String date,int month){
    	Calendar nowCalender = DateTimeUtils.strDate2Calendar(date);
    	nowCalender.add(Calendar.MONTH, month);
    	return calendar2StrDate(nowCalender, FORMAT_yyyy_MM_dd);
    }

    /**
     * 获取某一时间段内所有日期
    * @Title: main
    * @Description: TODO
    * @param @param args
    * @return void
    * @throws
     */

    public static List<Date> getDatesByTimeInterval(String startTime,String endTime ) throws Exception{
    	List<Date> dateList = new ArrayList<Date>();
    	Calendar cl = Calendar.getInstance();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date start = format.parse(startTime);
    	dateList.add(start);
    	Date end  = format.parse(endTime);
    	cl.setTime(start);
    	boolean bContinure = true;
    	while(bContinure){
    		cl.add(Calendar.DAY_OF_MONTH, 1);
    		if(end.after(cl.getTime())){
    			dateList.add(cl.getTime());
    		}else{
    			break;
    		}
    	}
    	dateList.add(end);
    	return dateList;
    }

    public static void main(String[] args) {
    	//System.out.println(DateTimeUtils.strDate2Calendar("2013-03-10").getTimeInMillis());
    	//System.out.println(DateTimeUtils.strDate2Calendar("2013-02-12").getTimeInMillis());
    	//System.out.println(DateTimeUtils.dateStrCalculate("2013-03-05", "2013-02-12"));
    	System.out.println(monthStrCalculate("2013-011-12",2));
    	 /**
         * 得到当前时间的字符串
         * @param format 字符串格式
         * @return String
         * @see org.apache.commons.lang.time.DateFormatUtils.DateFormatUtils#format(java.util.Date, String)
         */
         System.out.println(date2StrXieDate(new Date()));
    }
    /**
     * Date转换到字符串
     * @param timestamp
     * @param format
     * @return
     */
    public static String date2StrDate2(Timestamp timestamp, String format) {
      if(null == timestamp){
        timestamp = new Timestamp(System.currentTimeMillis());
      }
      return DateTimeUtils.date2StrDate(new Date(timestamp.getTime()), format);
    }
}
