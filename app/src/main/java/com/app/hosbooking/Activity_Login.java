package com.app.hosbooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hosbooking.global.Connects;
import com.app.hosbooking.global.Globals;
import com.app.hosbooking.global.URLs;
import com.app.hosbooking.util.UtilLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Activity_Login extends Activity_Base {

	private Context mContext;

	public static Activity back_Activity_Login;
	private EditText et_email, et_pass;
	private Button btn_login;
	private TextView tv_facebook, tv_google, tv_join;
	private ImageView iv_back;

	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
    private String TAG = "BJLee_A_Login";
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

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
		et_email  = (EditText) findViewById(R.id.et_email);
		et_pass   = (EditText) findViewById(R.id.et_pass);

		btn_login   = (Button) findViewById(R.id.btn_login);

		tv_facebook   = (TextView) findViewById(R.id.f_login);
		tv_google   = (TextView) findViewById(R.id.g_login);
		tv_join   = (TextView) findViewById(R.id.tv_join);

		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    }
    
    private void setInit()
    {
		et_email.setText("lbjjang2@gmail.com");
		et_pass.setText("0000");

		tv_join.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Activity_Login.this, Activity_Join_Start.class));
			}
		});

		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(Activity_Start.back_Activity_Start!= null)
				{
					Activity_Start.back_Activity_Start.finish();
					Activity_Start.back_Activity_Start=null;
				}
				startActivity(new Intent(Activity_Login.this, Activity_Main.class));
				finish();
			}
		});
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

	public void goLogin()
	{
		HashMap<String, String> param = new HashMap<String, String>();
		final String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

		if(isOnline() == true)
		{
			String url = URLs.MEMBER_JOIN.getValue();
			param.put("email", et_email.getText().toString().trim());
			param.put("password", et_pass.getText().toString().trim());

			Globals.connects.getData(Activity_Login.this, url, param, false, "5000", false, new Connects.ConnectCallback() {

				@Override
				public void onConnectTaskTimeout(String callback_url, int count)
				{
				}

				@Override
				public void onConnectTaskSuccess(String callback_url, String[] jsonResult)
				{
					UtilLog.i(TAG, "goJoin result = " + jsonResult[1]);

					if (jsonResult[0].equals("200"))
					{

						JSONObject jobject = null;
						JSONObject jobject2 = null;
						JSONArray jarray = null;
						try {
							jobject = new JSONObject(jsonResult[1]);

							String result = jobject.getString("result");
							if(result.equals("true"))
							{
								UtilLog.d(TAG, "회원가입 완료");
								Toast.makeText(mContext, "회원 가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

								Globals.userID = et_email.getText().toString().trim();
								Globals.userPass = et_pass.getText().toString().trim();
								Globals.userIdx = jobject.getString("member_idx");
								Globals.userType = "1"; // 병원, 고객 구분 필요

								editor.putString("userID", et_email.getText().toString().trim());
								editor.putString("userPass", et_pass.getText().toString().trim());
								editor.commit();

								startActivity(new Intent(Activity_Login.this, Activity_Main.class));

								// 로그인 완료시 기존 떠있는 엑티비티 정리 필요
								/*if(Activity_Join_Start.back_Activity_Join_Start != null)
								{
									Activity_Join_Start.back_Activity_Join_Start.finish();
									Activity_Join_Start.back_Activity_Join_Start = null;
								}

								if(Activity_Join.back_Activity_Join != null)
								{
									Activity_Join.back_Activity_Join.finish();
									Activity_Join.back_Activity_Join = null;
								}

								if(Activity_Login.back_Activity_Login != null)
								{
									Activity_Login.back_Activity_Login.finish();
									Activity_Login.back_Activity_Login = null;
								}

								if(Activity_Intro.back_Activity != null)
								{
									Activity_Intro.back_Activity.finish();
									Activity_Intro.back_Activity = null;
								}*/

								finish();

							}
							else{

								jobject2 = jobject.getJSONObject("error");

								UtilLog.d(TAG, "fail msg ==" + jobject2.getString("message"));
								Toast.makeText(mContext, jobject2.getString("message"), Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {

						}
					}
				}

				@Override
				public void onConnectTaskFailed(String callback_url)
				{
					UtilLog.i("BJLee", "onConnectTaskFailed");
				}
			});
		}
		else // 네트워크가 연결이 되지 않았을 경우
		{
			UtilLog.i("BJLee", "no network!!");
		}
	}

	private boolean isOnline() {
		try {
			ConnectivityManager conMan = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState(); // wifi
			if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
				return true;
			}
			NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState(); // mobile ConnectivityManager.TYPE_MOBILE
			if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}
		return false;
	}
}