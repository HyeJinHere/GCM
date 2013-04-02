package com.hyejin.gcmexam_rename;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		new Thread() {
			@Override
			public void run() {
				registerGSM();
			}
		}.start();
	}

	private void registerGSM() {
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

		final String regId = GCMRegistrar.getRegistrationId(this);
		if (regId.equals("")) {
			GCMRegistrar.register(this, getString(R.string.gcm_project_id));
		} else {
			Log.v("aa", "GCM already registered");
		}
	}

	@Override
	protected void onDestroy() {
		GCMRegistrar.unregister(this);
		GCMRegistrar.onDestroy(this);
		super.onDestroy();
	}
}
