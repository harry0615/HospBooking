package com.app.hosbooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class Activity_Intro extends Activity_Base {

	private Context mContext;

	public static Activity back_Activity_Start;
	private Button btn_login, btn_join;
	private String regid;
	
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
    private String TAG = "BJLee_A_Start";
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_intro);

        mContext = this;
 	    
 	   if(android.os.Build.VERSION.SDK_INT > 9) {
       	 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       	 StrictMode.setThreadPolicy(policy);
     	}

        startHandler.sendEmptyMessageDelayed(0, 2000);

    }

    public Handler startHandler = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent;

            intent = new Intent(getApplicationContext(), Activity_Start.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    };

}