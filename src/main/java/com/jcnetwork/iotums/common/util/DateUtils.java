package com.jcnetwork.iotums.common.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
  
 /**
 * 文件名：DateUtils.java 日期处理相关工具类
 */
public class DateUtils {
      
    /**定义常量**/
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
      
    
    /**
     * 获取系统当前时间
     * @return 
     */
    public static Date getDate() {
        return new Date();
    }
   
      
    /**
     * 获取指定格式的系统当前时间
     * @param type 日期格式
     * @return
     */
    public static String getFormatDate(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }
    
    /**
     * 使用预设格式提取日期
     * @param strDate 日期字符串
     * @return
     */
    public static Date strToDate(String strDate) {
        return strToDate(strDate,DATE_FULL_STR);
    }
      
    /**
     * 使用用户格式提取日期
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date strToDate(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    /**
     * 使用预设格式提取字符串日期
     * @param strDate 日期字符串
     * @return
     */
    public static String dateToStr(Date date) {
        return dateToStr(date,DATE_FULL_STR);
    }
      
    /**
     * 使用用户格式提取字符串日期
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
      
}