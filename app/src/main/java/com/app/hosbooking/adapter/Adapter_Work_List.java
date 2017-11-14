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

public class Adapter_Work_List extends BaseAdapter
{
	 private Context mContext = null;
	 private List<String> sdcardpath;
	 private ArrayList<WorkList> mItemList = new ArrayList<WorkList>();

	int[] tmplogoimg = {R.drawable.sample1, R.drawable.work1, R.drawable.wrok2};

	 public Adapter_Work_List(Context con, ArrayList<WorkList> items){
	     this.mContext = con;
	     this.mItemList = items;
	 }
	  
	 // 이미지의 갯수 
	 @Override
	 public int getCount() {
	 
	  return 0;//mItemList.size();
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
	  v = View.inflate(mContext, R.layout.item_worklist, null);

//	  WorkList tmp = mItemList.get(position);

	  ImageView iv_logoimg = (ImageView) v.findViewById(R.id.logo_img);
	  TextView tv_title  = (TextView) v.findViewById(R.id.mid);
      TextView tv_name  = (TextView) v.findViewById(R.id.name);
	  TextView tv_desc  = (TextView) v.findViewById(R.id.desc);
	  TextView tv_state  = (TextView) v.findViewById(R.id.state);
	  TextView tv_type  = (TextView) v.findViewById(R.id.type);
	  TextView tv_price  = (TextView) v.findViewById(R.id.price);
	  iv_logoimg.setBackgroundResource(tmplogoimg[position]);

//	  Picasso.with(mContext).load(URLs.YOUTHUMB.getValue() + tmp.link + "/1.jpg").fit().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(thumb);
////	      thumb.setBackgroundResource(R.drawable.efline_info);
//      tv_name.setText(tmp.title);
//      tv_date.setText(tmp.date);
//      tv_desc.setText(tmp.desc);

      return v;
	 }
}
