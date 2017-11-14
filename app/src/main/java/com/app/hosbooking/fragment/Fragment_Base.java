package com.app.hosbooking.fragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import java.util.HashMap;

public class Fragment_Base extends Fragment
{
    public HashMap<String, Object> bundle;
    
    public InputMethodManager imm;
    
    public SharedPreferences pref;
    
    public Editor editor;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    
    public Object getBundle(String key, Object type)
    {
        if (type == Boolean.class)
        {
            if (bundle == null) { return false; }
            
            if (bundle.get(key) == null) { return false; }
            
            return bundle.get(key);
        }
        else if (type == String.class)
        {
            if (bundle == null) { return ""; }
            
            if (bundle.get(key) == null) { return ""; }
            
            return bundle.get(key);
        }
        else if (type == Integer.class)
        {
            if (bundle == null) { return 0; }
            
            if (bundle.get(key) == null) { return 0; }
            
            return bundle.get(key);
        }
        
        return "";
    }
    
}