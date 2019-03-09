package com.papaya.jack.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.papaya.lenovo.MyAidl;


/**
 * Binder原理测试案例
 */
public class MyService extends Service {
    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 启动服务");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind 绑定binder,即Stub抽象方法的实现");
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 服务端继承抽象类MyAidl.Stub，实现抽象接口sum，执行sum算法
     */
    private class MyBinder extends MyAidl.Stub {

        @Override
        public int sum(int a, int b) throws RemoteException {
            Log.i(TAG, "服务端进程执行sum算法");
            Log.w(TAG, Log.getStackTraceString(new RuntimeException("哪里调用了服务端进程具体执行的sum算法")));
            //服务端执行算法
            int plus = a + b;
            return plus;
        }
    }
}
