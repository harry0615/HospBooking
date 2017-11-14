package com.app.hosbooking.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.hosbooking.fragment.Fragment_Home;
import com.app.hosbooking.fragment.Fragment_MyPage;
import com.app.hosbooking.fragment.Fragment_Profile;
import com.app.hosbooking.fragment.Fragment_WorkList;

public class Adapter_Fragment_MainPager extends FragmentPagerAdapter
{
    private static final int FIRST_PAGE = 0, SECOND_PAGE = 1, THIRD_PAGE = 2, THIRD_FOUR = 3;
    private static final int NUM_OF_PAGES = 4;
    private int vtype;
    private Context mContext;
    
    public Adapter_Fragment_MainPager(FragmentManager fm, Context context, int type)
    {
        super(fm);
        mContext  = context;
        vtype = type;
    }
    
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        switch (position)
        {
            case FIRST_PAGE:
                fragment = new Fragment_Home();
                break;
            
            case SECOND_PAGE:
                fragment = new Fragment_WorkList();
                break;
                
            case THIRD_PAGE:
            fragment = new Fragment_MyPage();
            break;

            case THIRD_FOUR:
                fragment = new Fragment_Profile();
                break;
        }
        
        return fragment;
    }
    
    @Override
    public int getCount()
    {
        return NUM_OF_PAGES;
    }
    
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
