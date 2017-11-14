package com.app.hosbooking.global;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;


import com.app.hosbooking.connect.RequestAsyncTask;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Connects
{
    public long TIME_LIMIT = 15000;
    
    private RequestAsyncTask task;
    
    private Context context;
    
    public interface ConnectCallback
    {
        public void onConnectTaskFailed(String callback_url);
        
        public void onConnectTaskTimeout(String callback_url, int count);
        
        public void onConnectTaskSuccess(String callback_url, String[] jsonResult);
    }
    
    @SuppressLint("NewApi")
    public void getData(final Context context, final String url, HashMap<String, String> param, boolean isProg, String time_limit, boolean appFinish, final ConnectCallback callback)
    {
        this.context = context;
        
        if (time_limit != null)
        {
            try
            {
                TIME_LIMIT = Long.parseLong(time_limit);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            TIME_LIMIT = 15000;
        }
        
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        
        // 네트워크 연결 체크
        if (!wifi.isConnected() && !mobile.isConnected())
        {
            Toast toast = Toast.makeText(context, "네트워크애러", Toast.LENGTH_SHORT);
            toast.show();
            if (appFinish)
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        ((Activity) context).finish();
                    }
                }, 1000);
            }
            return;
        }
        
        try
        {
            if (isProg)
            {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {

                    }
                });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        task = new RequestAsyncTask(context, url, param, new RequestAsyncTask.AsyncResult() {
            
            @Override
            public void onAsyncSuccess(String callback_url, String[] jsonResult)
            {
                dismissProg();
                callback.onConnectTaskSuccess(callback_url, jsonResult);
            }
            
            @Override
            public void onAsyncFailed(String callback_url)
            {
                dismissProg();
                callback.onConnectTaskFailed(callback_url);
            }
        });
        
        new Thread(new Runnable() {
            
            @Override
            public void run()
            {
                try
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                    {
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get(TIME_LIMIT, TimeUnit.MILLISECONDS);
                    }
                    else
                    {
                        task.execute().get(TIME_LIMIT, TimeUnit.MILLISECONDS);
                    }
                }
                catch (TimeoutException e)
                {
                    task.cancel(true);
                    dismissProg();
                    e.printStackTrace();
                    callback.onConnectTaskTimeout(url, 1);
                }
                catch (Exception e)
                {
                    task.cancel(true);
                    dismissProg();
                    callback.onConnectTaskFailed(url);
                }
            }
        }).start();
    }
    
    // 로딩 다이얼로그 종료
    private void dismissProg()
    {
        try
        {

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
