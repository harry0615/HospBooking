package com.app.hosbooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.hosbooking.adapter.Adapter_Fragment_MainPager;
import com.app.hosbooking.global.Globals;
import com.app.hosbooking.util.ActivityReference;
import com.app.hosbooking.util.BackPressCloseHandler;
import com.app.hosbooking.util.UtilLog;

public class Activity_Main extends Activity_Base implements View.OnClickListener {

    private ViewPager mPager;
    public Context mContext;
    public static Context cntxofmain;
    public static Activity back_main_activity;
    private BackPressCloseHandler backPressCloseHandler;

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private Adapter_Fragment_MainPager pagerAdapter;

    private Button btn_one, btn_two, btn_three, btn_four, btn_login, btn_join;
    private TextView tv_name1, tv_name2, tv_name3, tv_name4, tv_title, tv_join;
    private RelativeLayout main1, main2, main3, main4;
    private int type = 0;

    private String TAG = "BJLee_A_Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        backPressCloseHandler = new BackPressCloseHandler(this);

        settings = getSharedPreferences("setting" , Activity.MODE_PRIVATE);
        editor = settings.edit();

        String blogined = settings.getString("blogin", "");
        blogined = "true";
        if(Globals.blogin)
        {
            setContentView(R.layout.activity_main);
            setLayout();
            setInit();
        }
        else if(blogined.equals(""))
        {
            setContentView(R.layout.activity_start);
            setLayout_login();
        }
        else {
            setContentView(R.layout.activity_main);
            setLayout();
            setInit();
        }
    }

    private void setLayout_login()
    {
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.blogin = true;
                startActivity(new Intent(Activity_Main.this, Activity_Main.class));
            }
        });
        btn_join = (Button)findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Main.this, Activity_Start.class));
            }
        });
    }

    private void setLayout()
    {
        mPager = (ViewPager) findViewById(R.id.webviewpager);

        main1 = (RelativeLayout)findViewById(R.id.a_main1);
        main2 = (RelativeLayout)findViewById(R.id.a_main2);
        main3 = (RelativeLayout)findViewById(R.id.a_main3);
        main4 = (RelativeLayout)findViewById(R.id.a_main4);

        btn_one = (Button)findViewById(R.id.main_button1);
        btn_two = (Button)findViewById(R.id.main_button2);
        btn_three = (Button)findViewById(R.id.main_button3);
        btn_four = (Button)findViewById(R.id.main_button4);

        tv_name1 = (TextView) findViewById(R.id.main_text1);
        tv_name2 = (TextView)findViewById(R.id.main_text2);
        tv_name3 = (TextView)findViewById(R.id.main_text3);
        tv_name4 = (TextView)findViewById(R.id.main_text4);

        main1.setOnClickListener(this);
        main2.setOnClickListener(this);
        main3.setOnClickListener(this);
        main4.setOnClickListener(this);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
    }
    private void setInit()
    {
        pagerAdapter = new Adapter_Fragment_MainPager(getSupportFragmentManager(), mContext, type);

        mPager.setOffscreenPageLimit(3);
        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(0);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position)
            {
                if(position == 0)
                {
                    btn_one.setBackgroundResource(R.drawable.home_icon_on);
                    btn_two.setBackgroundResource(R.drawable.health_icon_off);
                    btn_three.setBackgroundResource(R.drawable.reser_icon_off);
                    btn_four.setBackgroundResource(R.drawable.myinfo_icon_off);

                    tv_name1.setTextColor(Color.parseColor("#ffffff"));
                    tv_name2.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name3.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name4.setTextColor(Color.parseColor("#a9a9b4"));

                    main1.setBackgroundResource(R.drawable.pocus_area);
                    main2.setBackgroundColor(Color.parseColor("#ffffff"));
                    main3.setBackgroundColor(Color.parseColor("#ffffff"));
                    main4.setBackgroundColor(Color.parseColor("#ffffff"));

                }
                else if (position == 1){
                    btn_one.setBackgroundResource(R.drawable.home_icon_off);
                    btn_two.setBackgroundResource(R.drawable.health_icon_on);
                    btn_three.setBackgroundResource(R.drawable.reser_icon_off);
                    btn_four.setBackgroundResource(R.drawable.myinfo_icon_off);

                    tv_name1.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name2.setTextColor(Color.parseColor("#ffffff"));
                    tv_name3.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name4.setTextColor(Color.parseColor("#a9a9b4"));

                    main2.setBackgroundResource(R.drawable.pocus_area);
                    main1.setBackgroundColor(Color.parseColor("#ffffff"));
                    main3.setBackgroundColor(Color.parseColor("#ffffff"));
                    main4.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else if (position == 2){
                    btn_one.setBackgroundResource(R.drawable.home_icon_off);
                    btn_two.setBackgroundResource(R.drawable.health_icon_off);
                    btn_three.setBackgroundResource(R.drawable.reser_icon_on);
                    btn_four.setBackgroundResource(R.drawable.myinfo_icon_off);

                    tv_name1.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name2.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name3.setTextColor(Color.parseColor("#ffffff"));
                    tv_name4.setTextColor(Color.parseColor("#a9a9b4"));

                    main3.setBackgroundResource(R.drawable.pocus_area);
                    main2.setBackgroundColor(Color.parseColor("#ffffff"));
                    main1.setBackgroundColor(Color.parseColor("#ffffff"));
                    main4.setBackgroundColor(Color.parseColor("#ffffff"));
                }

                else if (position == 3){
                    btn_one.setBackgroundResource(R.drawable.home_icon_off);
                    btn_two.setBackgroundResource(R.drawable.health_icon_off);
                    btn_three.setBackgroundResource(R.drawable.reser_icon_off);
                    btn_four.setBackgroundResource(R.drawable.myinfo_icon_on);

                    tv_name1.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name2.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name3.setTextColor(Color.parseColor("#a9a9b4"));
                    tv_name4.setTextColor(Color.parseColor("#ffffff"));

                    main4.setBackgroundResource(R.drawable.pocus_area);
                    main2.setBackgroundColor(Color.parseColor("#ffffff"));
                    main3.setBackgroundColor(Color.parseColor("#ffffff"));
                    main1.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
            @Override
            public void onPageScrollStateChanged(int state)
            {
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
            }
        });

    }

    @Override
    public void onClick(View v) {
        UtilLog.d(TAG,"onClick =================== ");
        switch (v.getId()) {

            case R.id.a_main1:
            case R.id.main_button1:
            {
                btn_one.setBackgroundResource(R.drawable.home_icon_on);
                btn_two.setBackgroundResource(R.drawable.health_icon_off);
                btn_three.setBackgroundResource(R.drawable.reser_icon_off);
                btn_four.setBackgroundResource(R.drawable.myinfo_icon_off);

                tv_name1.setTextColor(Color.parseColor("#ffffff"));
                tv_name2.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name3.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name4.setTextColor(Color.parseColor("#a9a9b4"));

                main1.setBackgroundResource(R.drawable.pocus_area);
                main2.setBackgroundColor(Color.parseColor("#ffffff"));
                main3.setBackgroundColor(Color.parseColor("#ffffff"));
                main4.setBackgroundColor(Color.parseColor("#ffffff"));

                setCurrentInflateItem(0);
                break;
            }
            case R.id.a_main2:
            case R.id.main_button2:
            {
                btn_one.setBackgroundResource(R.drawable.home_icon_off);
                btn_two.setBackgroundResource(R.drawable.health_icon_on);
                btn_three.setBackgroundResource(R.drawable.reser_icon_off);
                btn_four.setBackgroundResource(R.drawable.myinfo_icon_off);

                tv_name1.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name2.setTextColor(Color.parseColor("#ffffff"));
                tv_name3.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name4.setTextColor(Color.parseColor("#a9a9b4"));

                main2.setBackgroundResource(R.drawable.pocus_area);
                main1.setBackgroundColor(Color.parseColor("#ffffff"));
                main3.setBackgroundColor(Color.parseColor("#ffffff"));
                main4.setBackgroundColor(Color.parseColor("#ffffff"));

                setCurrentInflateItem(1);
                break;
            }
            case R.id.a_main3:
            case R.id.main_button3:
            {
                btn_one.setBackgroundResource(R.drawable.home_icon_off);
                btn_two.setBackgroundResource(R.drawable.health_icon_off);
                btn_three.setBackgroundResource(R.drawable.reser_icon_on);
                btn_four.setBackgroundResource(R.drawable.myinfo_icon_off);

                tv_name1.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name2.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name3.setTextColor(Color.parseColor("#ffffff"));
                tv_name4.setTextColor(Color.parseColor("#a9a9b4"));

                main3.setBackgroundResource(R.drawable.pocus_area);
                main2.setBackgroundColor(Color.parseColor("#ffffff"));
                main1.setBackgroundColor(Color.parseColor("#ffffff"));
                main4.setBackgroundColor(Color.parseColor("#ffffff"));

                setCurrentInflateItem(2);
                break;
            }

            case R.id.a_main4:
            case R.id.main_button4:
            {
                btn_one.setBackgroundResource(R.drawable.home_icon_off);
                btn_two.setBackgroundResource(R.drawable.health_icon_off);
                btn_three.setBackgroundResource(R.drawable.reser_icon_off);
                btn_four.setBackgroundResource(R.drawable.myinfo_icon_on);

                tv_name1.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name2.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name3.setTextColor(Color.parseColor("#a9a9b4"));
                tv_name4.setTextColor(Color.parseColor("#ffffff"));

                main4.setBackgroundResource(R.drawable.pocus_area);
                main2.setBackgroundColor(Color.parseColor("#ffffff"));
                main3.setBackgroundColor(Color.parseColor("#ffffff"));
                main1.setBackgroundColor(Color.parseColor("#ffffff"));

                setCurrentInflateItem(3);
                break;
            }

        }
    }

    private void setCurrentInflateItem(int type){
        if(type==0){
            mPager.setCurrentItem(0);
        }else if(type==1){
            mPager.setCurrentItem(1);
        }else if(type==2){
            mPager.setCurrentItem(2);
        }
        else if(type==3){
            mPager.setCurrentItem(3);
        }
    }

    @Override
    public void onBackPressed() {
        Activity activity = ActivityReference.sActivityReference;
        if(activity != null) {
            activity.finish();
        }
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

}
