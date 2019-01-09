package bai.bai.bai.demoaidlservice;

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
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private IAddAidlInterface mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        aboutService();

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("baibai", "服务端：相加按钮 -- 当前线程Name = " + Thread.currentThread().getName() + ", id = " + Thread.currentThread().getId());
                int x = Integer.parseInt(((EditText) findViewById(R.id.et_add_x)).getText().toString());
                int y = Integer.parseInt(((EditText) findViewById(R.id.et_add_y)).getText().toString());
                try {
                    Log.d("baibai", "服务端：相加结果：" + mService.add(x, y));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d("baibai", "服务端：子线程：相加按钮 -- 当前线程Name = " + Thread.currentThread().getName() + ", id = " + Thread.currentThread().getId());
                            Log.d("baibai", "服务端：子线程：相加结果：" + mService.add(6, 2));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

    }

    ServiceConnection mConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("baibai", "服务端：add 连接 Service 成功");
            mService = IAddAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void aboutService() {
        Intent intent = new Intent(this, AddService.class);
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }


}
