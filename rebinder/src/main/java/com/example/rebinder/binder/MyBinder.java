package com.example.rebinder.binder;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by XJ on 2018/2/27 0027.
 */

public class MyBinder extends Binder {
    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        return super.onTransact(code, data, reply, flags);
    }
}
