package bai.bai.bai.demoaidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

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
            return mBooks;
        }

        @Override
        public void addBook(BookBean book) throws RemoteException {
            mBooks.add(book);
        }
    };

}
