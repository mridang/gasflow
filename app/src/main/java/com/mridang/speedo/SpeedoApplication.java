package com.mridang.speedo;

import android.app.Application;

import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(
        resNotifTickerText = R.string.crash_notif_ticker_text,
        resNotifTitle = R.string.crash_notif_title,
        resNotifText = R.string.crash_notif_text,
        mailTo = "mridang.agarwalla+speedo@gmail.com"
)
public class SpeedoApplication extends Application {
}
