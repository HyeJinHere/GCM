package com.hyejin.gcmexam;

import static com.example.gcm.CommonUtilities.SENDER_ID;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.demo.app.DemoActivity;
import com.google.android.gcm.demo.app.R;

public class GCMIntentService extends GCMBaseIntentService {

	String c2dm_msg = null;

	public GCMIntentService() {
		super(SENDER_ID);
	}

	private static final String TAG = "===GCMIntentService===";

	public void onReceive(Context context, Intent intent) {

	}

	@Override
	protected void onRegistered(Context arg0, String registrationId) {
		Log.i(TAG, "Device registered: regId = " + registrationId);
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		Log.i(TAG, "unregistered = " + arg1);
	}

	@Override
	protected void onMessage(Context arg0, Intent arg1) {
		Log.i(TAG, "CONTEXT : = " + arg0);
		Log.i(TAG, "new message= ");
		if (arg1.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {

			c2dm_msg = arg1.getExtras().getString("msg");
			GET_GCM();
			Log.i(TAG, c2dm_msg);

		}

	}

	public void GET_GCM() {

		Thread thread = new Thread(new Runnable() {
			public void run() {

				handler.sendEmptyMessage(0);
			}
		});
		thread.start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			Context context = getApplicationContext();
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, c2dm_msg, duration);
			toast.show();
			c2dm_msg = null;

		}
	};

	public void showMsg(String msg, int option) {
		Toast.makeText(this, msg, option).show();
	}

	@Override
	protected void onError(Context arg0, String errorId) {
		Log.i(TAG, "Received error: " + errorId);
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		return super.onRecoverableError(context, errorId);
	}
	
	/**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {
        int icon = R.drawable.ic_stat_gcm;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, DemoActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }
}
