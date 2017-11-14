package com.app.hosbooking.util;

public class UtilLog
{
    
    public static final boolean IS_LOG = true;
    
    public static final boolean IS_REMOTE_LOG = false;
    
    public static StringBuilder remoteLog = new StringBuilder();
    
    public static void d(String tag, String msg)
    {
        if (IS_LOG)
        {
            android.util.Log.d(tag, msg);
        }
    }
    
    public static void d(String tag, String msg, Throwable tr)
    {
        if (IS_LOG)
        {
            android.util.Log.d(tag, msg, tr);
        }
    }
    
    public static void e(String tag, String msg)
    {
        if (IS_LOG)
        {
            android.util.Log.e(tag, msg);
        }
    }
    
    public static void e(String tag, String msg, Throwable tr)
    {
        if (IS_LOG)
        {
            android.util.Log.e(tag, msg, tr);
        }
    }
    
    public static void v(String tag, String msg)
    {
        if (IS_LOG)
        {
            android.util.Log.v(tag, msg);
        }
    }
    
    public static void v(String tag, String msg, Throwable tr)
    {
        if (IS_LOG)
        {
            android.util.Log.v(tag, msg, tr);
        }
    }
    
    public static void w(String tag, String msg)
    {
        if (IS_LOG)
        {
            android.util.Log.w(tag, msg);
        }
    }
    
    public static void w(String tag, String msg, Throwable tr)
    {
        if (IS_LOG)
        {
            android.util.Log.w(tag, msg, tr);
        }
    }
    
    public static void i(String tag, String msg)
    {
        if (IS_LOG)
        {
            android.util.Log.i(tag, msg);
        }
    }
    
    public static void i(String tag, String msg, Throwable tr)
    {
        if (IS_LOG)
        {
            android.util.Log.i(tag, msg, tr);
        }
    }
    
    public static void info(String msg)
    {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName();
        String logTag = className.substring(className.lastIndexOf(".") + 1, className.length());
        String MethodName = stackTraceElement.getMethodName();
        
        if (MethodName.contains("Adapter"))
        {
            if (msg.equals("{") || msg.equals("}")) { return; }
            
        }
        
        if (IS_LOG)
        {
            i(logTag, logTag + "::" + MethodName + "() " + msg);
        }
        
        if (IS_REMOTE_LOG)
        {
            remoteLog.append(logTag + "::" + MethodName + "() " + msg).append("\r\n");
        }
    }
    
    public static void error(Exception e)
    {
        if (IS_REMOTE_LOG)
        {
            StackTraceElement[] trace = e.getStackTrace();
            
            remoteLog.append("ERROR TRACE").append("\r\n");
            
            for (StackTraceElement element : trace)
            {
                remoteLog.append(element.toString()).append("\r\n");
            }
        }
    }
}
