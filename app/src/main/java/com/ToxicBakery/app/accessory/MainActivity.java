package com.ToxicBakery.app.accessory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import biz.source_code.usb.UsbIso;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    UsbIso usbIso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        usbIso = new UsbIso(1, 1, 3072);

        // Need real interface otherwise crash crash time
        //usbIso.setInterface(0, 0);

        usbIso.preallocateRequests(1000);

        usbIso.getRequest();
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            usbIso.dispose();
        } catch (IOException e) {
            Log.e(TAG, "Dispose Failed", e);
        }
    }

}

