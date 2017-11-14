package com.app.hosbooking.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.app.hosbooking.Activity_Main;
import com.app.hosbooking.R;
import com.app.hosbooking.adapter.Adapter_MainHos_List;
import com.app.hosbooking.adapter.MainLogoList;
import com.app.hosbooking.util.UtilLog;

import java.util.ArrayList;


public class Fragment_Profile extends Fragment_Base
{
	private Activity_Main activity;
    private RelativeLayout baseView;
    private ImageView backButton;
    private ListView logolist;
    private Adapter_MainHos_List adapter_vlist;
    private ArrayList<MainLogoList> al_logolist = new ArrayList<MainLogoList>();

    private String TAG = "BJLee_Fragment_Profile";

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        baseView = (RelativeLayout) inflater.inflate(R.layout.fragment_profile, null);

//	    SetFontAll.setFontsForAll("GodoM.ttf.mp3", getActivity().getAssets(), baseView);

        activity = (Activity_Main) getActivity();
        UtilLog.d(TAG,"Fragment_Profile onCreateView =================== ");

        setLayout();
        setInit();
        return baseView;
    }


    private void setLayout()
    {





    }

    @SuppressLint("NewApi")
    private void setInit()
    {

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

    }


    public void onBackPressed (){

    }


   
}
