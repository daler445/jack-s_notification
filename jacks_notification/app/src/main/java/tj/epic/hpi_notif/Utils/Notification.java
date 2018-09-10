package tj.epic.hpi_notif.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import tj.epic.hpi_notif.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Notification {

    private static final int NOTIFICATION_ID = 73;

    public Notification() {
    }

    public void createNotification(Context context) {
        try {

            RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_layout);

            PackageManager packageManager = context.getPackageManager();

            String app0 = KeyValueApps.appInfo(context, 0);
            String app1 = KeyValueApps.appInfo(context, 1);
            String app2 = KeyValueApps.appInfo(context, 2);
            String app3 = KeyValueApps.appInfo(context, 3);

            if (app0 != null) {
                ApplicationInfo app0info = packageManager.getApplicationInfo(app0, PackageManager.GET_META_DATA);
                Intent intent0 = packageManager.getLaunchIntentForPackage(app0info.packageName);
                PendingIntent pIntent0 = PendingIntent.getActivity(context, 0, intent0, 0);
                contentView.setImageViewBitmap(R.id.app0icon, drawable2Bitmap( packageManager.getApplicationInfo(app0, PackageManager.GET_META_DATA).loadIcon(packageManager)) );
                Log.d("Notification", "Icon int: " + app0info.icon);
                contentView.setOnClickPendingIntent(R.id.app0icon, pIntent0);
            }

            if (app1 != null) {
                ApplicationInfo app1info = packageManager.getApplicationInfo(app1, PackageManager.GET_META_DATA);
                Intent intent1 = packageManager.getLaunchIntentForPackage(app1info.packageName);
                PendingIntent pIntent1 = PendingIntent.getActivity(context, 0, intent1, 0);
                contentView.setImageViewBitmap(R.id.app1icon, drawable2Bitmap( packageManager.getApplicationInfo(app1, PackageManager.GET_META_DATA).loadIcon(packageManager)) );
                Log.d("Notification", "Icon int: " + app1info.icon);
                contentView.setOnClickPendingIntent(R.id.app1icon, pIntent1);
            }

            if (app2 != null) {
                ApplicationInfo app2info = packageManager.getApplicationInfo(app2, PackageManager.GET_META_DATA);
                Intent intent2 = packageManager.getLaunchIntentForPackage(app2info.packageName);
                PendingIntent pIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
                contentView.setImageViewBitmap(R.id.app2icon, drawable2Bitmap( packageManager.getApplicationInfo(app2, PackageManager.GET_META_DATA).loadIcon(packageManager)) );
                Log.d("Notification", "Icon int: " + app2info.icon);
                contentView.setOnClickPendingIntent(R.id.app2icon, pIntent2);
            }

            if (app3 != null) {
                ApplicationInfo app3info = packageManager.getApplicationInfo(app3, PackageManager.GET_META_DATA);
                Intent intent3 = packageManager.getLaunchIntentForPackage(app3info.packageName);
                PendingIntent pIntent3 = PendingIntent.getActivity(context, 0, intent3, 0);
                contentView.setImageViewBitmap(R.id.app3icon, drawable2Bitmap( packageManager.getApplicationInfo(app3, PackageManager.GET_META_DATA).loadIcon(packageManager)) );
                Log.d("Notification", "Icon int: " + app3info.icon);
                contentView.setOnClickPendingIntent(R.id.app3icon, pIntent3);
            }

            if (KeyValueTheme.getTheme(context) == 1) {
                contentView.setInt(R.id.notification_layout, "setBackgroundColor", R.color.colorAccent);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.ic_headphone)
                    .setOngoing(true)
                    .setPriority(android.app.Notification.PRIORITY_MAX);

            android.app.Notification notificationObj = builder.build();

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_ID, notificationObj);
            Log.d("Notification", "Created");
        } catch (NullPointerException | PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
