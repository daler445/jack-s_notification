package tj.epic.hpi_notif.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartOnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                Intent service = new Intent(context, JackReceiver.class);
                context.startService(service);
            }
        } catch (NullPointerException e) {
            Log.d("BS", "Error: " + e.toString());
        }
    }
}
