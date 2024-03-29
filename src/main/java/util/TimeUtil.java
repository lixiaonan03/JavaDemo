package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * @author lixiaonan
 * 功能描述: 时间处理的工具类
 * 时 间： 2019-09-29 15:46
 */
public class TimeUtil {

    private final static long BBT_SECOND = 1;
    private final static long BBT_MINITE = 60 * BBT_SECOND;
    private final static long BBT_HOUR = 60 * BBT_MINITE;
    private final static long BBT_DAY = 24 * BBT_HOUR;
    private final static long BBT_MONTH = 30 * BBT_DAY;
    private final static long BBT_YEAR = 12 * BBT_MONTH;


    private static final ThreadLocal<SimpleDateFormat> SDF_THREAD_LOCAL = new ThreadLocal<>();

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String FORMAT_YSEMS = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * 日期格式 MM-dd
     */
    private static final String FORMAT_MM_DD = "MM-dd";
    /**
     * 日期格式 yyyy年MM月dd日 HH:mm
     */
    private static final String FORMAT_YYYY_MM_DD_HH_MM_CHINA = "yyyy年MM月dd日 HH:mm";
    /**
     * 日期格式 yyyy-MM-dd HH:mm
     */
    private static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * 日期格式 MM月dd日 HH:mm
     */
    private static final String FORMAT_MM_DD_HH_MM_CHINA = "MM月dd日 HH:mm";

    /**
     * 获取给定的时间戳离现在过去了过久
     * @param timestamp 妙级时间戳
     * @return
     */
    public static String getTimeAgo(long timestamp) {
        long curStamp = System.currentTimeMillis() / 1000;
        long delta = curStamp - timestamp;
        if (delta <= 0) {
            return "刚刚";
        } else {
            if (delta < 1 * BBT_MINITE) {
                return "刚刚";
            } else if (delta < 2 * BBT_MINITE) {
                return "1分钟前";
            } else if (delta < 60 * BBT_MINITE) {
                long minites = delta / BBT_MINITE;
                return String.format("%d分钟前", minites);
            } else if (delta < 90 * BBT_MINITE) {
                return "1小时前";
            } else if (delta < 24 * BBT_HOUR) {
                long hours = delta / BBT_HOUR;
                return String.format("%d小时前", hours);
            } else if (delta < 48 * BBT_HOUR) {
                return "昨天";
            } else if (delta < 30 * BBT_DAY) {
                long days = delta / BBT_DAY;
                return String.format("%d天前", days);
            } else if (delta < 12 * BBT_MONTH) {
                long months = delta / BBT_MONTH;
                return months <= 1 ? "1个月前" : String.format("%d个月前", months);
            } else {
                long years = delta / BBT_YEAR;
                return years <= 1 ? "1年前" : String.format("%d年前", years);
            }
        }
    }

    /**
     * 获取当前的时间戳
     * @return long型时间戳
     */
    public static long getCurrentTime() {
        long millis = System.currentTimeMillis();
        return millis;
    }

    /**
     * 秒值转化为 yyyy-MM-dd HH:mm:ss 格式的字符串
     * @param seconds  秒数
     * @return  转换值 异常返回 ""
     */
    public static String secondsToString(final long seconds) {
        try {
            return millisToString(seconds*1000L, getDefaultFormat());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前的时间字符串的
     * @return
     */
    public static String getNowTimeForStr(){
        return millisToString(getCurrentTime(),getDefaultFormatYesMS());
    }

    /**
     * 秒数转换为想要的格式
     * @param seconds  秒数
     * @param format   想要转换成的字符串格式
     * @return 转换完成的字符串 异常返回 ""
     */
    public static String secondsToString(final long seconds,String format) {
        try {
            return millisToString(seconds*1000L, getDateFormat(format));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



    /**
     * 毫秒转换成相关的时间格式的
     * @param millis The milliseconds.
     * @param format The format.
     * @return the formatted time string
     */
    public static String millisToString(final long millis,final DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * 获取默认的时间格式的
     * @return
     */
    public static SimpleDateFormat getDefaultFormat() {
        return getDateFormat(FORMAT);
    }
    /**
     * 获取默认的时间格式的(带毫秒的)
     * @return
     */
    private static SimpleDateFormat getDefaultFormatYesMS() {
        return getDateFormat(FORMAT_YSEMS);
    }

    /**
     * 根据传入的时间格式字符串获取时间格式
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat simpleDateFormat = SDF_THREAD_LOCAL.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            SDF_THREAD_LOCAL.set(simpleDateFormat);
        } else {
            simpleDateFormat.applyPattern(pattern);
        }
        return simpleDateFormat;
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//        sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        String en = sdf.format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String zh =  sdf1.format(new Date());
        System.out.println("en==="+en);
        try {
            Date lxn11 = sdf1.parse(en);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("zh==="+zh);
        
        


        Date date = new Date();
        DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.CHINA);

        // 中国日期
        String string = formater.format(date);
        System.out.println("中国日期：\t"+string);
        // 加拿大日期
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.CANADA);
        System.out.println("加拿大日期：\t"+formater.format(date));
        // 日本日期
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.JAPAN);
        System.out.println("日本日期：\t"+formater.format(date));
        // 法国日期
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.FRANCE);
        System.out.println("法国日期：\t"+formater.format(date));
        // 德国日期
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.GERMAN);
        System.out.println("德国日期：\t"+formater.format(date));
        // 意大利日期
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.ITALIAN);
        System.out.println("意大利日期：\t"+formater.format(date));
        // 意大利日期
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.ENGLISH);
        System.out.println("美国日期：\t"+formater.format(date));
    }
}
