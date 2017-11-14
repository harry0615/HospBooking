package com.app.hosbooking.connect;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

public class RequestAsyncTask extends AsyncTask<String, Integer, Boolean>
{
    private String[] jsonResult = null;
    
    private HashMap<String, String> paramMap;
    
    private Context context;
    
    private String url;
    
    private AsyncResult asyncResultListener;
    
    public interface AsyncResult
    {
        public void onAsyncSuccess(String callback_url, String[] jsonResult);
        
        public void onAsyncFailed(String callback_url);
    }
    
    public RequestAsyncTask(Context context, String url, HashMap<String, String> paramMap, AsyncResult asyncResultListener)
    {
        this.context = context;
        this.asyncResultListener = asyncResultListener;
        this.url = url;
        this.paramMap = paramMap;
    }
    
    protected void onPreExecute()
    {
        super.onPreExecute();
        
    }
    
    protected void onProgressUpdate(Integer... progress)
    {
    }
    
    protected void onPostExecute(Boolean result)
    {
        try
        {
            
            if (result)
            {
                if (url.equals(""))
                {
                    asyncResultListener.onAsyncFailed(url);
                }
                else
                {
                    asyncResultListener.onAsyncSuccess(url, jsonResult);
                }
            }
            else
            {
                asyncResultListener.onAsyncFailed(url);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            asyncResultListener.onAsyncFailed(url);
        }
    }
    
    protected Boolean doInBackground(String... arg)
    {
        // POST방식
        jsonResult = postConnection(url, paramMap);
        
        // Get방식
        // boolean isFirst = true;
        // StringBuilder parameter = new StringBuilder();
        // Iterator<String> keys = paramMap.keySet().iterator();
        // while (keys.hasNext())
        // {
        // String key = keys.next();
        // if (isFirst)
        // {
        // parameter.append("?" + key + "=" + paramMap.get(key));
        // isFirst = false;
        // }
        // else
        // {
        // parameter.append("&" + key + "=" + paramMap.get(key));
        // }
        // }
        // UtilLog.info(" Async URL : " + url.getValue() + parameter.toString());
        // jsonResult = getJsonWebData(url.getValue() + parameter.toString());
        
        UtilLog.info("jsonResult : " + jsonResult);
        
        if (jsonResult != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // public String postConnection(URLs url, HashMap<String, String> params)
    public String[] postConnection(String url, HashMap<String, String> params)
    {
        String[] response = new String[2];
        StringBuffer result = new StringBuffer();
        int status_code = 0;
        try
        {
            HttpClient client = new DefaultHttpClient();
            /* HttpPost post = new HttpPost(url.getValue()); */
            HttpPost post = new HttpPost(url);
            
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
            
            status_code = responsePOST.getStatusLine().getStatusCode();
            UtilLog.info("status_code : " + status_code);
            
            while ((line = br.readLine()) != null)
            {
                result.append(line);
            }
            
            Log.d("BJLee" , result.toString());
            
            response[0] = String.valueOf(status_code);
            
            response[1] = result.toString();
            
            client.getConnectionManager().shutdown();
            
            return response;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
