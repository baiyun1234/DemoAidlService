package bai.bai.bai.demoaidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {

    private List<BookBean> mBooks = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mBooks.add(new BookBean("我是第一本", 11));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    IBookManager.Stub mBinder = new IBookManager.Stub(){

        @Override
        public List<BookBean> getBooks() throws RemoteException {
            Log.d("baibai", "BookService -- getBooks() -- 当前线程Name = " + Thread.currentThread().getName() + ", id = " + Thread.currentThread().getId());
            return mBooks;
        }

        @Override
        public void addBook(BookBean book) throws RemoteException {
            Log.d("baibai", "BookService -- addBook() -- 当前线程Name = " + Thread.currentThread().getName() + ", id = " + Thread.currentThread().getId());
            mBooks.add(book);
        }
    };

}
