package com.app.hosbooking;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hosbooking.global.Globals;

import java.util.ArrayList;

public class Activity_Base extends FragmentActivity
{
    /**
     * Global
     */
    public Globals global;
    
    // 로그아웃을 위한 액티비티 리스트
    public static ArrayList<Activity> actList = new ArrayList<Activity>();
    
    private Typeface typeBold, typeRegular;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        global = Globals.getInstance(this);
    }
    
    public void makeAlert(final String msg)
    {
        try
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    new AlertDialog.Builder(Activity_Base.this).setMessage(msg).setPositiveButton(android.R.string.ok, null).setCancelable(false).create().show();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void makeToast(final String msg)
    {
        try
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    Toast.makeText(Activity_Base.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 폰트 일괄 적용
     * 
     * @param view
     * @param typeBoldViewId
     *            타입이 Bold인 view id
     */
    public void setFont(View view, int[] typeBoldViewId)
    {

    }
    
    /**
     * Bold 타입의 view id가 있는지 체크
     * 
     * @param viewId
     * @param viewBoldId
     * @return boolean
     */
    private boolean isCheckedBold(int viewId, int[] viewBoldId)
    {
        if (viewBoldId != null)
        {
            for (int id : viewBoldId)
            {
                if (id == viewId) return true;
            }
        }
        
        return false;
    }
    
    public void setFontBoldTypeFace(View v)
    {
        try
        {
            TextView view = (TextView) v;
            view.setTypeface(typeBold);
        }
        catch (Exception e)
        {
            try
            {
                EditText view = (EditText) v;
                view.setTypeface(typeBold);
            }
            catch (Exception e2)
            {
            }
        }
    }
    
    public void setFontRegularTypeFace(View v)
    {
        try
        {
            TextView view = (TextView) v;
            view.setTypeface(typeRegular);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                EditText view = (EditText) v;
                view.setTypeface(typeRegular);
            }
            catch (Exception e2)
            {
            }
        }
    }
}