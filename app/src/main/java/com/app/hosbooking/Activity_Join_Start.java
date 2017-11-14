package com.app.hosbooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Activity_Join_Start extends Activity_Base {

	private Context mContext;

	public static Activity back_Activity_Start;
//	private TextView  tv_join1, tv_join2;
	private ImageView iv_button1, iv_button2;
	private String regid;
	
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
    private String TAG = "BJLee_A_Join_Start";
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_join_start);

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
//		tv_join1   = (TextView) findViewById(R.id.tv_join1);
//		tv_join2  = (TextView) findViewById(R.id.tv_join2);


    }
    
    private void setInit()
    {
//		tv_join1.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(Activity_Join_Start.this, Activity_Join_Patient.class));
//			}
//		});
//
//		tv_join2.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(Activity_Join_Start.this, Activity_Join_Hospital.class));
//			}
//		});
    }
   

    
    @Override
    public void onResume()
    {
        super.onResume();
 
    }
}