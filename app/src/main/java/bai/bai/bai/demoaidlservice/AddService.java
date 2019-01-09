package bai.bai.bai.demoaidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AddService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    IAddAidlInterface.Stub mBinder = new IAddAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int x, int y) throws RemoteException {
            Log.d("baibai", "AddService -- add -- 当前线程Name = " + Thread.currentThread().getName() + ", id = " + Thread.currentThread().getId());
            return x + y;
        }
    };

}
