package com.app.hosbooking.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.app.hosbooking.Activity_Main;
import com.app.hosbooking.R;
import com.app.hosbooking.adapter.Adapter_MainHos_List;
import com.app.hosbooking.adapter.MainLogoList;
import com.app.hosbooking.global.Connects;
import com.app.hosbooking.global.Globals;
import com.app.hosbooking.global.URLs;
import com.app.hosbooking.util.UtilLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment_Home extends Fragment_Base
{
	private Activity_Main activity;
    private RelativeLayout baseView;

    private ImageView iv_profile, iv_menu;
    private GridView logolist;
    private Adapter_MainHos_List adapter_vlist;
    private ArrayList<MainLogoList> al_logolist = new ArrayList<MainLogoList>();
    private ViewPager mViewPagerProfile, mViewPagerMenu;

    private String TAG = "BJLee_Fragment_Home";
    
    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        baseView = (RelativeLayout) inflater.inflate(R.layout.fragment_home, null);
        
//	    SetFontAll.setFontsForAll("GodoM.ttf.mp3", getActivity().getAssets(), baseView);

        activity = (Activity_Main) getActivity();
        UtilLog.d(TAG,"Fragment_Home onCreateView =================== ");

        setLayout();
        setInit();
        return baseView;
    }

    
    private void setLayout()
    {
        logolist = (GridView) baseView.findViewById(R.id.hos_gridview);
        ImageView iv_back = (ImageView) baseView.findViewById(R.id.iv_back);

    }

    @SuppressLint("NewApi")
    private void setInit()
    {
        adapter_vlist = new Adapter_MainHos_List(activity, al_logolist);
        logolist.setAdapter(adapter_vlist);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG,"Fragment_Home onResume =================== ");

    }

    
    public void onBackPressed (){

    }

    public void checkInfo()
    {
        HashMap<String, String> param = new HashMap<String, String>();
        final String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        if(isOnline() == true)
        {
            String url = URLs.MEMBER_JOIN.getValue();
//            param.put("value1", value1.getText().toString().trim());
//            param.put("value2", value2.getText().toString().trim());

            Globals.connects.getData(activity, url, param, false, "5000", false, new Connects.ConnectCallback() {

                @Override
                public void onConnectTaskTimeout(String callback_url, int count)
                {
                }

                @Override
                public void onConnectTaskSuccess(String callback_url, String[] jsonResult)
                {
                    UtilLog.i(TAG, "goJoin result = " + jsonResult[1]);

                    if (jsonResult[0].equals("200"))
                    {

                        JSONObject jobject = null;
                        JSONObject jobject2 = null;
                        JSONArray jarray = null;
                        try {
                            jobject = new JSONObject(jsonResult[1]);

                            String result = jobject.getString("result");
                            if(result.equals("true")){

                            }

                            else{

                                jobject2 = jobject.getJSONObject("error");

                                UtilLog.d(TAG, "fail msg ==" + jobject2.getString("message"));
                                Toast.makeText(activity, jobject2.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {

                        }
                    }
                }

                @Override
                public void onConnectTaskFailed(String callback_url)
                {
                    UtilLog.i("BJLee", "onConnectTaskFailed");
                }
            });
        }
        else // 네트워크가 연결이 되지 않았을 경우
        {
            UtilLog.i("BJLee", "no network!!");
        }
    }

    private boolean isOnline() {
        try {
            ConnectivityManager conMan = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState(); // wifi
            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                return true;
            }
            NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState(); // mobile ConnectivityManager.TYPE_MOBILE
            if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }
}
