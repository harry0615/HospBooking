package com.app.hosbooking.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.app.hosbooking.Activity_Main;
import com.app.hosbooking.R;
import com.app.hosbooking.adapter.Adapter_Work_List;
import com.app.hosbooking.adapter.WorkList;
import com.app.hosbooking.util.UtilLog;

import java.util.ArrayList;

public class Fragment_WorkList extends Fragment_Base
{
    private Activity_Main activity;
    private RelativeLayout baseView;
    private ListView worklist;
    private Adapter_Work_List vlist;
    private ArrayList<WorkList> al_worklist = new ArrayList<WorkList>();

    private String TAG = "BJLee_Fragment_WorkList";

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        baseView = (RelativeLayout) inflater.inflate(R.layout.fragment_myhealth, null);

//	    SetFontAll.setFontsForAll("GodoM.ttf.mp3", getActivity().getAssets(), baseView);

        activity = (Activity_Main) getActivity();
        UtilLog.d(TAG,"Fragment_WorkList onCreateView =================== ");

        setLayout();
        setInit();
        return baseView;
    }


    private void setLayout()
    {
        worklist = (ListView) baseView.findViewById(R.id.logo_listview);


    }

    @SuppressLint("NewApi")
    private void setInit()
    {
        vlist = new Adapter_Work_List(activity, al_worklist);
        worklist.setAdapter(vlist);
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
