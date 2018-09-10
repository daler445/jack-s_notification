package tj.epic.hpi_notif.Utils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class JackReceiver extends Service {

    private BroadcastReceiver jackReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("JR", "onCreate");
        registerJackBroadcastReceiver();
    }

    @Override
    public void onDestroy() {
        Log.d("JR", "onDestroy");
        unregisterReceiver(jackReceiver);
        jackReceiver = null;
    }

    private void registerJackBroadcastReceiver() {
        Log.d("JR", "on void call");
        jackReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("JR", "onReceive");
                Notification ntp = new Notification();
                if (Intent.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
                    int state = intent.getIntExtra("state", -1);
                    switch (state) {
                        case 0: // headset un-plugged
                            Log.d("JR", "on un-plug");
                            ntp.closeNotification(context);
                            break;
                        case 1: // headset plugged in
                            Log.d("JR", "on plug-in");
                            ntp.createNotification(context);
                            break;
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(jackReceiver, filter);
    }
}