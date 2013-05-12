package com.threebowl.foodsystem.views;

import java.util.List;

import com.threebowl.foodsystem.R;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	Context mContext;

	LayoutInflater mLayoutInflater;

	List<String> mItemText;
	List<Bitmap> mItemBitmap;

	public GridViewAdapter(Context context, List<String> ItemText,List<Bitmap> ItemBitmap) {
		this.mItemText = ItemText;
		this.mItemBitmap = ItemBitmap;

		mContext = context;

		mLayoutInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		// if (convertView == null)
		// {
		convertView = mLayoutInflater.inflate(R.layout.item_gird_push, null);
		// }

		 ImageView imageView = (ImageView)
		 convertView.findViewById(R.id.Grid_Item_Img);
		 imageView.setImageBitmap(mItemBitmap.get(position));
		 
		 TextView textView = (TextView)
		 convertView.findViewById(R.id.Grid_Item_Text);
		 textView.setText(mItemText.get(position));
		 
		 

		return convertView;

	}
	
	


}
