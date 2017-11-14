package com.app.hosbooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class Activity_Start extends Activity_Base {

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
		setContentView(R.layout.activity_start);
		back_Activity_Start = this;

        mContext = this;
 	    
 	   if(android.os.Build.VERSION.SDK_INT > 9) {
       	 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       	 StrictMode.setThreadPolicy(policy);
     	}

		setLayout();
		setInit();
    }
    
    private void setLayout()
    {
		btn_login   = (Button) findViewById(R.id.btn_login);
		btn_join  = (Button) findViewById(R.id.btn_join);
    }
    
    private void setInit()
    {
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Activity_Start.this, Activity_Login.class));
			}
		});

		btn_join.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Activity_Start.this, Activity_Join_Start.class));
			}
		});
    }
   

    
    @Override
    public void onResume()
    {
        super.onResume();
 
    }
}