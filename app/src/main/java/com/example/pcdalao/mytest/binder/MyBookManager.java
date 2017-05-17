package com.example.pcdalao.mytest.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by pan jh on 2017/5/15.
 */

public interface MyBookManager extends IInterface {
    static final String DESCRIPTOR = "com.example.pcdalao.mytest.binder.IBookManager";

    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;

    public class BookManagerImpl extends Binder implements MyBookManager{
        public BookManagerImpl(){
            this.attachInterface(this,DESCRIPTOR);
        }

        public static MyBookManager asInterface(IBinder obj){
            if (obj==null){
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin!=null&&iin instanceof IBookManager){
                return (MyBookManager) iin;
            }
            return new BookManagerImpl.Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code){
                case INTERFACE_TRANSACTION:{
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_getBookList:{
                    data.enforceInterface(DESCRIPTOR);
                    List<Book> result = this.getBookList();
                    reply.writeNoException();
                    reply.writeTypedList(result);
                    return true;
                }
                case TRANSACTION_addBook:{
                    data.enforceInterface(DESCRIPTOR);
                    Book arg0;
                    if (0!=data.readInt()){
                        arg0=Book.CREATOR.createFromParcel(data);
                    }else{
                        arg0=null;
                    }
                    this.addBook(arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return null;
        }

        @Override
        public void addBook(Book book) throws RemoteException {

        }

        private static class Proxy implements MyBookManager{
            private IBinder mRemote;

            public Proxy(IBinder remote){
                mRemote=remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public List<Book> getBookList() throws RemoteException {
                return null;
            }

            @Override
            public void addBook(Book book) throws RemoteException {

            }
        }
    }
}
