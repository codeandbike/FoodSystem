package com.threebowl.foodsystem.views;

import com.threebowl.foodsystem.MainActivity;
import com.threebowl.foodsystem.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

/**
 * ====================================================
 * 
 * @Copyright (C) 2012-2013 ������ͼ��Ϣ�Ƽ����޹�˾
 * @All rights reserved
 * @filename :MainTabs.java
 * @date 2013-5-10
 * @time ����11:14:19
 * @author �����
 * @description��
 * 
 * @---------------����ά����汾��Ϣ---------------------------
 * @�汾��V1.0 ��д�ˣ������ ��������һ�δ���
 * 
 * 
 * @=====================================================
 */
public class MainTabs extends TabActivity implements OnCheckedChangeListener {
	private TabHost mHost;
	private RadioGroup mRadioGroup;
	private boolean isExit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintabs);
		mHost = this.getTabHost();

		// ���ѡ�
		mHost.addTab(mHost.newTabSpec("ONE").setIndicator("ONE")
				.setContent(new Intent(this, Activity_FirstPage.class)));
		mHost.addTab(mHost.newTabSpec("TWO").setIndicator("TWO")
				.setContent(new Intent(this, Activity_Style.class)));
		mHost.addTab(mHost.newTabSpec("THREE").setIndicator("THREE")
				.setContent(new Intent(this, Activity_Common.class)));
		mHost.addTab(mHost.newTabSpec("FOUR").setIndicator("FOUR")
				.setContent(new Intent(this, Activity_About.class)));

		mRadioGroup = (RadioGroup) findViewById(R.id.main_radio);
		mRadioGroup.setOnCheckedChangeListener(this);
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.radio_button0:
			mHost.setCurrentTabByTag("ONE");
			break;
		case R.id.radio_button1:
			mHost.setCurrentTabByTag("TWO");
			break;
		case R.id.radio_button2:
			mHost.setCurrentTabByTag("THREE");
			break;
		case R.id.radio_button3:
			mHost.setCurrentTabByTag("FOUR");
			break;
		}

	}

}
