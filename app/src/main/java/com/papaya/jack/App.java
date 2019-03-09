package com.papaya.jack;

import android.app.Application;
import android.content.Intent;

import com.papaya.jack.service.MyService;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService();
    }

    private void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
}
