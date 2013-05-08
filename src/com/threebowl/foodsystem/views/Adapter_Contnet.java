package com.threebowl.foodsystem.views;

import java.util.List;

import com.threebowl.foodsystem.R;
import com.threebowl.foodsystem.views.MyAdapter.ViewHolder;

import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @date 2013-3-26
 * @time 下午10:11:24
 * @author 许度庆
 * 
 *         类说明:
 */
public class Adapter_Contnet extends BaseAdapter {

	private Context mContext;
	private List<String> mList_Span;
	private List<Bitmap> bitmaps;

	public Adapter_Contnet(Context context, List<String> spans,
			List<Bitmap> bitmaps) {
		this.mContext = context;
		this.mList_Span = spans;
		this.bitmaps = bitmaps;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList_Span.size();
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater.from(mContext);
			convertView = mInflater.inflate(R.layout.adapter_content, null);
			holder.span_text = (TextView) convertView
					.findViewById(R.id.adater_textview_span);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.adater_imageview_span);
			holder.id_text = (TextView) convertView
					.findViewById(R.id.adater_textview_id);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		holder.imageView.setImageBitmap(this.bitmaps.get(position));
		holder.span_text.setText(mList_Span.get(position));
		holder.id_text.setText(position + 1+"");
		return convertView;
	}

	public final class ViewHolder {

		public TextView span_text;
		public TextView id_text;
		public ImageView imageView;
	}

}
