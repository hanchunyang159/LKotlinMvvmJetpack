package com.hxz.learn.utils;

import android.os.Build;
import android.util.Log;

import java.util.Locale;


/**
 * Created by 韩湘子 on  2019/5/11
 * Email:15901419691@163.com
 * Dec: log打印日志
 */
public class Logger {


    private static final int OFFSET = 6;
    /**单次输出最长日志*/
    private static final int MAX_LENGTH = 3 * 1024;
    public static final int DEBUG = Log.DEBUG;
    public static final int ERROR = Log.ERROR;
    public static final int INFO = Log.INFO;
    public static final int VERBOSE = Log.VERBOSE;
    public static final int WARN = Log.WARN;



    private Logger() {

    }


    /**
     * 0：dalvik.system.VMStack.getThreadStackTrace(Native Method)
     * 1：java.lang.Thread.getStackTrace(Thread.java)
     * 2：Logger.getMethodNames()
     * 3：Logger.i(Logger.java)
     * 4：print(int level, Object obj)
     * 5：调用i方法的类
     */
    public static String getMethodNames(int offset) {
        StackTraceElement trace = Thread.currentThread().getStackTrace()[offset];
        return String.format(Locale.getDefault(), "(%s:%d).%s",
                trace.getFileName(), trace.getLineNumber(), trace.getMethodName());
    }

    public static void v(Object msg) {
        printLog(VERBOSE, msg);
    }

    public static void d(Object msg) {
        printLog(DEBUG, msg);
    }

    public static void i(Object msg) {
        printLog(INFO, msg);
    }

    public static void w(Object msg) {
        printLog(WARN, msg);
    }

    public static void e(Object msg) {
        printLog(ERROR, msg);
    }

    private static void printLog(int level, Object obj) {


        String origin = obj == null ? "null" : obj.toString();
        int strLen = origin.length();
        if (strLen < MAX_LENGTH) {
            print(level, origin);
        } else {
            StringBuilder builder = new StringBuilder(origin);
            int start = 0;
            int end = MAX_LENGTH;
            String msg;
            do {
                msg = builder.substring(start, end);
                print(level, msg);
                start = end;
                end += MAX_LENGTH;
            } while (end < strLen);
            msg = builder.substring(start, strLen);
            print(level, msg);
        }
    }

    private static void print(int level, String msg) {
        switch (level) {
            case VERBOSE:
                Log.v(getMethodNames(OFFSET), msg);
                break;
            case DEBUG:
                Log.d(getMethodNames(OFFSET), msg);
                break;
            case INFO:
                Log.i(getMethodNames(OFFSET), msg);
                break;
            case WARN:
                Log.w(getMethodNames(OFFSET), msg);
                break;
            case ERROR:
                Log.e(getMethodNames(OFFSET), msg);
                break;
            default:
                Log.w("Logger", "没有预设的输出等级" + msg);
                break;
        }
    }
}
