package com.mridang.speedo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

/**
 Broadcast receiver class to help start the traffic monitoring service when the phone boots up only
 if the service is enabled.
 */
public class BootReceiver extends BroadcastReceiver {

	/**
	 Receiver method for the phone boot that starts the traffic monitoring service
	 */
	@Override
	public void onReceive(Context appContext, Intent bootIntent) {
		if (PreferenceManager.getDefaultSharedPreferences(appContext).getBoolean("enabled", true)) {
			appContext.startService(new Intent(appContext, TrafficService.class));
		}
	}
}