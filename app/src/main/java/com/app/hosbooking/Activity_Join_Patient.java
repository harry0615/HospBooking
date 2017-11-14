package com.app.hosbooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Activity_Join_Patient extends Activity_Base {

	private Context mContext;

	public static Activity back_Activity_Start;
	private EditText et_email, et_pass, et_nick;
	private ImageView iv_back;
	private TextView tv_type1, tv_type2, tv_error;
	private Button btn_done;

	private String joinType = "designer";
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;

    private String TAG = "BJLee_A_Join_Email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_join_patient);

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
		et_email = (EditText) findViewById(R.id.email);
		et_pass = (EditText) findViewById(R.id.pass);
		et_nick = (EditText) findViewById(R.id.nickname);
		btn_done = (Button) findViewById(R.id.join_done);

		tv_type1 = (TextView) findViewById(R.id.title1);
		tv_type2 = (TextView) findViewById(R.id.title2);
		tv_error = (TextView) findViewById(R.id.desc3);
		et_pass.setFilters(new InputFilter[] {filterAlphaNum});

		et_pass.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
		et_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}

	private void setInit()
	{
		iv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});



		btn_done.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(et_email.getText().toString().equals(""))
					tv_error.setText("이메일을 입력하세요.");
				else if(et_pass.getText().toString().equals(""))
					tv_error.setText("비밀번호를 입력하세요.");
				else if(et_nick.getText().toString().equals(""))
					tv_error.setText("닉네임을 입력하세요.");
				else if(!checkEmail(et_email.getText().toString()))
					tv_error.setText("이메일 형식이 아닙니다.");
				else
				{
					tv_error.setVisibility(View.GONE);
//					goJoin();
				}
			}
		});
	}

	private boolean checkEmail(String email)
	{

		String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

		Pattern p = Pattern.compile(mail);

		Matcher m = p.matcher(email);

		return m.matches();

	}
	protected InputFilter filterAlphaNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, int end,
								   Spanned dest, int dstart, int dend) {

			Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
			if (!ps.matcher(source).matches()) {
				return "";
			}
			return null;
		}
	};



	public void goJoin()
	{
		HashMap<String, String> param = new HashMap<String, String>();
		final String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

		if(isOnline() == true)
		{
			String url = URLs.MEMBER_JOIN.getValue();
			param.put("email", et_email.getText().toString().trim());
			param.put("password", et_pass.getText().toString().trim());
			param.put("name", et_nick.getText().toString().trim());
			param.put("member_type", joinType);

			Globals.connects.getData(Activity_Join_Patient.this, url, param, false, "5000", false, new Connects.ConnectCallback() {

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
								Globals.userType = joinType;

								editor.putString("userID", et_email.getText().toString().trim());
								editor.putString("userPass", et_pass.getText().toString().trim());
								editor.commit();

								startActivity(new Intent(Activity_Join_Patient.this, Activity_Main.class));

								/*if(Activity_Start.back_Activity_Join_Start != null)
								{
									Activity_Start.back_Activity_Join_Start.finish();
									Activity_Start.back_Activity_Join_Start = null;
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