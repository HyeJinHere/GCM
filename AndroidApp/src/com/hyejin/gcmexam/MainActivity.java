package com.hyejin.gcmexam;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {
	static final String SENDER_ID = "1000439876088"; // project id
	private TextView mDisplay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

		
		mDisplay = (TextView) findViewById(R.id.textView1);

		final String regId = GCMRegistrar.getRegistrationId(this);
		Log.i(TAG, "registration id =====&nbsp; " + regId);

		if (regId.equals("")) {
			GCMRegistrar.register(this, SENDER_ID);
		} else {
			Log.v(TAG, "Already registered");
		}
		mDisplay.setText(regId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		GCMRegistrar.unregister(this);
	}
}
