package com.papaya.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.papaya.lenovo.MyAidl;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private MyAidl mMyAidl;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 绑定服务端，获取IBinder
     *
     * @param view
     */
    public void getIBinderStub(View view) {

        //绑定服务，获取服务端的IBinder代理Proxy
        Toast.makeText(this, "绑定服务端,获取IBinder", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setPackage("com.papaya.lenovo");//服务端报名
        intent.setAction("com.papaya.ACTION_TESTAIDL");//服务端ACTION
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 客户端调用服务端的求和方法
     *
     * @param view
     */
    public void calSum(View view) {
        Log.i(TAG, "calSum: mMyAidl = " + mMyAidl);
        if (mMyAidl != null) {
            try {
                Random random = new Random(System.currentTimeMillis());
                int a = random.nextInt(100);
                int b = a + 6;
                int sum = mMyAidl.sum(a, b);
                Log.i(TAG, "调用sum结果: sum = " + sum);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyAidl = MyAidl.Stub.asInterface(service);

            if (mMyAidl != null) {
                Log.i(TAG, "onServiceConnected: 客户端绑定服务端成功了,成功获取到了服务端的IBinder Stub MyAidl 的代理Proxy");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: 绑定服务解绑了");
        }
    };
}
