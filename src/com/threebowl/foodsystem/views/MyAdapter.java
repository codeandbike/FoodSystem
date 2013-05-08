package com.threebowl.foodsystem.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.threebowl.foodsystem.FoodSystemApp;
import com.threebowl.foodsystem.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @date 2013-1-10
 * @time 下午8:32:19
 * @author 汪家栋
 * 
 *         类说明:
 */
public class MyAdapter extends BaseAdapter {
	
	private FoodSystemApp app;

	List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
	private LayoutInflater mInflater;
	private List<Bitmap> bitmaps;

	public MyAdapter(List<Map<String, Object>> mData, Context context,FoodSystemApp app) {
		// TODO Auto-generated constructor stub
		this.mData = mData;
		this.app = app;
		this.bitmaps = app.mBitmaps;
		
		this.mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public final class ViewHolder {
		public ImageView food_img;
		public TextView h2_text;
		public TextView span_text;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.content_itme, null);
			holder.food_img = (ImageView) convertView
					.findViewById(R.id.item_foodImg);
			holder.h2_text = (TextView) convertView.findViewById(R.id.item_foodName);
			holder.span_text = (TextView) convertView
					.findViewById(R.id.item_stuff);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}

//		holder.food_img.setBackgroundResource((Integer) mData.get(position).get(
//				"food_img"));
		//holder.food_img.setImageBitmap((Bitmap)mData.get(position).get("img"));
		holder.food_img.setImageBitmap(this.bitmaps.get(position));
		holder.h2_text.setText((String) mData.get(position).get("h2"));
		holder.span_text.setText((String) mData.get(position).get("span"));

		return convertView;
	}
}
