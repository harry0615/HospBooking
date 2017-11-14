package com.app.hosbooking.global;

import android.content.Context;
import android.widget.Toast;

public class Globals
{
    // public Contents contents;
    
//    public Prefs prefs;
    
//    public Methods method;
    
//    public Values values;
    
    public static Connects connects;
    
	public static int width = 0;
	public static int height = 0;
	
    private static Globals mInstance;
    public static int ACTIVITY_CONSTANT = 1101;
    
    public static String vtype ="1"; // 1:원터치 인증, 2:지문/핀인증
    public static String gcmid = "";
    
    public static String companyCode;
    public static String companyIdx;
    public static String userState;
    public static String companyName;
    
    public static String userID;
    public static String userPass;
    public static String userIdx;
    public static String userType;

    public static boolean blogin = false;
    
//    public static LoginInfoClass userJoinInfo = new LoginInfoClass();
    
    public static int bauth = 0; // 1:성공, 2:취소, 3:실패
    
    private Context context;
    
    public Globals(Context context)
    {
        this.context = context;
//        values = new Values();
//        prefs = new Prefs(context, this);
//        method = new Methods(context);
        
        connects = new Connects();
    }
    
    public static Globals getInstance(Context context)
    {
        if (mInstance == null) mInstance = new Globals(context);
        return mInstance;
    }
    
    public void makeToast(String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    
    public void resetGlobal()
    {
        mInstance = null;
    }
    
    
}
