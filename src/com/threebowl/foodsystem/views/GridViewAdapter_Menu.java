package com.threebowl.foodsystem.views;

import java.util.List;
import java.util.Map;

import com.threebowl.foodsystem.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
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

public class GridViewAdapter_Menu extends BaseAdapter {

	Context mContext;

	LayoutInflater mLayoutInflater;

	List<String> mItemText;
	List<Bitmap> mItemBitmap;

	public GridViewAdapter_Menu(Context context, List<String> mItemText) {
		
		this.mItemText = mItemText;

		mContext = context;

		mLayoutInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
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
		convertView = mLayoutInflater.inflate(R.layout.item_gird_menu, null);
		// }

		// ImageView imageView = (ImageView)
		// convertView.findViewById(R.id.imageview);
		// imageView.setBackgroundDrawable(mMenuItemData.getDrawable(position));
		//
		//
		// TextView textView = (TextView)
		// convertView.findViewById(R.id.textview);
		// textView.setText(mMenuItemData.getTitle(position));

		return convertView;

	}

}
