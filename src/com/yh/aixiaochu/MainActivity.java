package com.yh.aixiaochu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.yh.aixiaochu.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ���ÿɽ���
		getWindow().getDecorView().setDrawingCacheEnabled(true);

		// ��ʼ��ϵͳ����
		SystemUtil.init();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					SystemUtil.screenCap();
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}) {
//		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void btnClick(View view) {
		TextView s = (TextView) findViewById(R.id.statusView);
		String status = s.getText().toString();
		if (status.equals(getResources().getString(R.string.runtext))) {
			// ���������л���������
			stopService(new Intent(this, GamePlayService.class));
			s.setText(getResources().getString(R.string.noruntext));
		} else {
			// δ�����л�������
			startService(new Intent(this, GamePlayService.class));
			s.setText(getResources().getString(R.string.runtext));
		}
	}
}
