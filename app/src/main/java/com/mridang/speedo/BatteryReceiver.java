package com.mridang.speedo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Broadcast receiver class to help start or stop the traffic monitoring service when the phone's
 * battery level is low or okay.
 */
public class BatteryReceiver extends BroadcastReceiver {

    /**
     * Receiver method for the phone boot that starts the traffic monitoring service
     */
    @Override
    public void onReceive(Context appContext, Intent ittIntent) {
        Log.v("BatteryReceiver", "Received a battery intent");
        if (PreferenceManager.getDefaultSharedPreferences(appContext).getBoolean("enabled", true) &&
                PreferenceManager.getDefaultSharedPreferences(appContext).getBoolean("lowpower", false)) {
            if (ittIntent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
                Log.i("BatteryReceiver", "Battery low. Stopping service");
                appContext.stopService(new Intent(appContext, TrafficService.class));
            } else {
                Log.i("BatteryReceiver", "Battery okay. Starting service");
                appContext.startService(new Intent(appContext, TrafficService.class));
            }
        }
    }
}