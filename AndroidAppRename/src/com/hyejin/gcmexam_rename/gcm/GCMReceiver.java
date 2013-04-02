package com.hyejin.gcmexam_rename.gcm;

import android.content.Context;

import com.google.android.gcm.GCMBroadcastReceiver;
import com.hyejin.gcmexam_rename.R;

public class GCMReceiver extends GCMBroadcastReceiver {

	@Override
	protected String getGCMIntentServiceClassName(Context context) {
		return context.getString(R.string.gcm_service_class);
	}
}
