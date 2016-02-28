package com.mridang.speedo;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Broadcast receiver class to help start or stop the traffic monitoring service when the phone's
 * battery saver mode is enabled or disabled
 */
public class PowerReceiver extends BroadcastReceiver {

    /**
     * Receiver method for the phone boot that starts the traffic monitoring service
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context appContext, Intent bootIntent) {
        Log.v("BatteryReceiver", "Received a power intent");
        if (PreferenceManager.getDefaultSharedPreferences(appContext).getBoolean("enabled", true) &&
                PreferenceManager.getDefaultSharedPreferences(appContext).getBoolean("lowpower", false)) {
            PowerManager mgrPower = (PowerManager) appContext.getSystemService(Context.POWER_SERVICE);
            if (mgrPower.isPowerSaveMode()) {
                Log.i("BatteryReceiver", "Power saving mode enabled. Stopping service");
                appContext.stopService(new Intent(appContext, TrafficService.class));
            } else {
                Log.i("BatteryReceiver", "Power saving mode disabled. Starting service");
                appContext.startService(new Intent(appContext, TrafficService.class));
            }
        }
    }
}