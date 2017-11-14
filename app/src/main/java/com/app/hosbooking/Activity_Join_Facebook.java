package com.app.hosbooking;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;


public class Activity_Join_Facebook extends Activity_Base {

	private Context mContext;

	public static Activity back_Activity_Start;
	private ImageView iv_back;
	private String regid;
	
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
    private String TAG = "BJLee_A_Join_Facebook";
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_join_facebook);

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
		iv_back   = (ImageView) findViewById(R.id.back);

    }
    
    private void setInit()
    {
		iv_back.setOnClickListener(new OnClickListener() {
   		    @Override
   			public void onClick(View v) {
   		    	finish();
   			}
   		});
    }
   

    
    @Override
    public void onResume()
    {
        super.onResume();
 
    }
}