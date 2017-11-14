package com.app.hosbooking.connect;

import com.app.hosbooking.global.URLs;
import com.app.hosbooking.util.UtilLog;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class RequestHttpThread extends Thread
{
    private URLs url;
    
    private HashMap<String, String> params;
    
    private ThreadResult callback;
    
    private Timer timer;
    
    private int delayedCount = 0;
    
    private boolean isTimeouted = false;
    
    public void setCallback(ThreadResult callback)
    {
        this.callback = callback;
    }
    
    public interface ThreadResult
    {
        public void onThreadSuccess(URLs callback_url, String jsonResult);
        
        public void onThreadFailed(URLs callback_url);
        
        public void onThreadTimeout(URLs callback_url, int count);
    }
    
    public RequestHttpThread(URLs url, HashMap<String, String> params)
    {
        this.url = url;
        this.params = params;
        
        startTimer();
    }
    
    /**
     * 타이머 시작
     */
    private void startTimer()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                UtilLog.info("count : " + delayedCount);
                delayedCount++;
                isTimeouted = true;
                callback.onThreadTimeout(url, delayedCount);
                timer.cancel();
            }
        }, 15000, 15000);
    }
    
    @Override
    public void run()
    {
        super.run();
        
        StringBuffer result = new StringBuffer();
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url.getValue());
            
            Set<String> s = params.keySet();
            Iterator<String> it = s.iterator();
            
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            while (it.hasNext())
            {
                String key = it.next();
                param.add(new BasicNameValuePair(key, params.get(key)));
            }
            
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(param, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(responsePOST.getEntity().getContent()));
            
            while ((line = br.readLine()) != null)
            {
                result.append(line);
            }
            
            if (!isTimeouted)
            {
                UtilLog.info("http result : " + result.toString());
                
                if (timer != null) timer.cancel();
                
                if (result.toString().equals(""))
                    callback.onThreadFailed(url);
                else
                    callback.onThreadSuccess(url, result.toString());
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if (!isTimeouted)
            {
                callback.onThreadFailed(url);
            }
            
        }
        
    }
    
}
