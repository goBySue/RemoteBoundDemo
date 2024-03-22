package com.example.remotebounddemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class RemoteService extends Service {
    public RemoteService() {
    }

    static class IncomingHandler extends Handler {
        String TAG = "RemoteService";

        public IncomingHandler() {
            super(Looper.getMainLooper());

        }

        @Override
        public void handleMessage (Message msg){
            Bundle data = msg.getData();
            String dataString = data.getString("MyString");
            Log.i(TAG, "Message = " + dataString);
        }
    }

    final Messenger myMessanger = new Messenger( (new IncomingHandler()));

    @Override
    public IBinder onBind(Intent intent) {
       return myMessanger.getBinder();
    }
}