/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: B:\\TAndroidTemp\\AIDL\\PapayaIBinder\\app\\src\\main\\aidl\\com\\papaya\\lenovo\\MyAidl.aidl
 */
package com.papaya.lenovo;

import android.util.Log;

/**
 * 定义的AIDL接口
 * 1.Binder Server
 * 2.onTransact 方法，由onTransact先来解析来自外部进程的调用,再调用实现了的sum方法
 * 3.sum 方法，服务实现sum方法
 * 4.服务端调用流程
 */
public interface MyAidl extends android.os.IInterface {
    public String TAG = "MyAidl_CLIENT";

    /**
     * 1.Stub抽象类，继承自Binder
     * 2.实现类MyAidl定义的sum接口
     */
    public static abstract class Stub extends android.os.Binder implements MyAidl {

        private static final String DESCRIPTOR = "com.papaya.lenovo.MyAidl";

        /**
         * 构造函数
         */
        public Stub() {
            Log.i(TAG, "Stub 构造");
            this.attachInterface(this, DESCRIPTOR);
        }


        /**
         * 供客户端进程调用，返回一个服务端进程的IBinder对象给客户端进程
         *
         * @param obj 服务端进程的IBinder
         * @return 服务端进程的IBinder
         */
        public static MyAidl asInterface(android.os.IBinder obj) {
            Log.i(TAG, "asInterface: 客户端调用asInterface获取服务端进程的IBinder的Proxy代理");

            if ((obj == null)) {
                return null;
            }
            //判断参数(也就是客户端传达过来的IBinder对象),是否和自己在同一个进程
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof MyAidl))) {
                return ((MyAidl) iin);//同一个进程
            }

            //进程间通信
            //将IBinder包装成一个Proxy对象
            return new Proxy(obj);
        }

        /**
         * Binder驱动
         *
         * @return
         */
        @Override
        public android.os.IBinder asBinder() {
            Log.i(TAG, "asBinder: Stub 服务端进程，获取到Binder驱动");
            return this;
        }


        /**
         * 0.客户端进程调用服务端进程的onTransact
         * 1.由onTransact先来解析来自外部进程的调用,再调用实现了的sum方法，即Proxy实现的sum方法
         * 2.Proxy是IBinder的一个代理,实现了IBinder(MyAidl)的sum方法
         * 2.该处由MyService的成员变量MyBinder来实现IBinder(Stub)的sum方法，具体的计算逻辑在sum实现
         *
         * @param code
         * @param data
         * @param reply
         * @param flags
         * @return
         * @throws android.os.RemoteException
         */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            Log.i(TAG, "onTransact: Stub 服务端进程");
            Log.w(TAG, Log.getStackTraceString(new RuntimeException("客户端进程调用服务端进程的onTransact")));
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_sum: {
                    data.enforceInterface(descriptor);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _arg1;
                    _arg1 = data.readInt();
                    int _result = this.sum(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        /**
         * 1.Proxy代理
         * 2.Proxy实现了MyAidl接口sum
         */
        private static class Proxy implements MyAidl {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * 0.将IBinder包装成一个Proxy对象
             * 1.客户端进程调用服务端进程的sum方法
             * 2.
             *
             * @param a
             * @param b
             * @return
             * @throws android.os.RemoteException
             */
            @Override
            public int sum(int a, int b) throws android.os.RemoteException {
                Log.i(TAG, "22 客户端调用自己Proxy的sum(),");
                Log.w(TAG, Log.getStackTraceString(new RuntimeException("22 客户端调用自己Proxy的sum()")));
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    //Stub.TRANSACTION_sum 写入函数名称
                    _data.writeInterfaceToken(DESCRIPTOR);//写标记
                    _data.writeInt(a);//写如函数参数
                    _data.writeInt(b);//写如函数参数

                    /**
                     * 客户端跨进程调用的顺序
                     */
                    //1.使用Parcelable来准备数据，把函数名称，函数参数都写入 _data(Binder驱动,C底层实现的),
                    //2.让 _reply接受返回值(Binder驱动,C底层实现的)
                    //3.最后使用IBinder的transact()方法,就可以把数据传给Binder的server端了
                    mRemote.transact(Stub.TRANSACTION_sum, _data, _reply, 0);
                    Log.i(TAG, "客户端进程在Proxy.sum()调用IBinder的transact()方法，跨进程把方法参数、方法名等通知IBinder服务端进程");

                    //使用_reply接受返回值
                    _reply.readException();
                    _result = _reply.readInt();//具体的返回值
                    Log.i(TAG, "最终执行结果: _result = " + _result);

                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        //写入函数名称
        static final int TRANSACTION_sum = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    }

    /**
     * MyAidl 接口方法，客户端调用顺序
     * 1.客户端调用Stub的sum方法
     * 2.调用Proxy的sum方法(客户端的Stub实现了sum接口)
     *
     * @param a
     * @param b
     * @return
     * @throws android.os.RemoteException
     */
    public int sum(int a, int b) throws android.os.RemoteException;
}
