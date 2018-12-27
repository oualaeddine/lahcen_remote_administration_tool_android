package com.google.services.security;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiManager;
import android.os.Handler;


/**
 * Created by ouala_eddine on 7/25/2018.
 * Project : trash_system_android_app.
 */
public class ConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
            SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
            switch (state) {
                case COMPLETED: {
                    new Handler().postDelayed(() -> {
                        if (ConnectivityHelper.isConnectedWifi(context)) {
                            new Synchronizer(context).sync();
                        }
                    }, 4000);
                    break;
                }
                case DISCONNECTED: {
                    break;
                }
            }
        }
    }
}
