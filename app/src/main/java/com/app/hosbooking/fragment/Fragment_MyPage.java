package com.app.hosbooking.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.hosbooking.Activity_Main;
import com.app.hosbooking.R;
import com.app.hosbooking.adapter.Adapter_MainHos_List;
import com.app.hosbooking.adapter.Adapter_MainRev_List;
import com.app.hosbooking.adapter.MainLogoList;

import java.util.ArrayList;


public class Fragment_MyPage extends Fragment_Base
{
	private Activity_Main activity;
    private RelativeLayout baseView, titlelayout;
    private ImageView backButton;
    private Adapter_MainRev_List adapter_vlist;
    private ArrayList<MainLogoList> al_logolist = new ArrayList<MainLogoList>();
    private GridView logolist;

    private String TAG = "BJLee_Fragment_MyPage";

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        baseView = (RelativeLayout) inflater.inflate(R.layout.fragment_mypage, null);

//	    SetFontAll.setFontsForAll("GodoM.ttf.mp3", getActivity().getAssets(), baseView);

        activity = (Activity_Main) getActivity();
        Log.d(TAG,"Fragment_MyPage onCreateView =================== ");

        setLayout();
        setInit();
        return baseView;
    }


    private void setLayout()
    {
//        backButton = (ImageView) baseView.findViewById(R.id.iv_back);
//        backButton.setOnClickListener(new OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        logolist = (GridView) baseView.findViewById(R.id.hos_gridview);

    }

    @SuppressLint("NewApi")
    private void setInit()
    {
        adapter_vlist = new Adapter_MainRev_List(activity, al_logolist);
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


    }


    public void onBackPressed (){

    }
 
}
