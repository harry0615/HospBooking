package com.app.hosbooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hosbooking.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_MainHos_List extends BaseAdapter
{
	 private Context mContext = null;
	 private List<String> sdcardpath;	
	 private ArrayList<MainLogoList> mItemList = new ArrayList<MainLogoList>();

	int[] tmplogoimg = {R.drawable.sample1, R.drawable.sample2, R.drawable.sample3, R.drawable.sample4};
	String[] title ={"초록 병원 서울 강남", "에스그린 병원 서울 서초"};
	String[] subtitle = {"[내과]서울특별시 서초구 서초동 1376-3", "[내과]서울특별시 서초구 서초4동 1317-23"};

	 public Adapter_MainHos_List(Context con, ArrayList<MainLogoList> items){
	     this.mContext = con;
	     this.mItemList = items;
	 }
	  
	 // 이미지의 갯수 
	 @Override
	 public int getCount() {
	 
	  return 2;//mItemList.size();
	 }
	 
	 @Override
	 public Object getItem(int position) {
	  // TODO Auto-generated method stub
	  return position ;
	 }
	 
	 // 선택된 이미지ID를 반환
	 @Override
	 public long getItemId(int position) {
	  // TODO Auto-generated method stub
	  return position;
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  		 
      View v = convertView;
	  v = View.inflate(mContext, R.layout.item_hoslist, null);

//	  MainLogoList tmp = mItemList.get(position);

	  ImageView logoimg = (ImageView) v.findViewById(R.id.logo_img);
	  TextView tv_title  = (TextView) v.findViewById(R.id.title);
      TextView tv_name  = (TextView) v.findViewById(R.id.name);
      TextView tv_price  = (TextView) v.findViewById(R.id.price);

		 tv_title.setText(title[position]);
		 tv_name.setText(subtitle[position]);


//	  logoimg.setBackgroundResource(tmplogoimg[position]);

//	  Picasso.with(mContext).load(URLs.YOUTHUMB.getValue() + tmp.link + "/1.jpg").fit().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(thumb);
////	      thumb.setBackgroundResource(R.drawable.efline_info);
//      tv_name.setText(tmp.title);
//      tv_date.setText(tmp.date);
//      tv_desc.setText(tmp.desc);

      return v;
	 }
}
