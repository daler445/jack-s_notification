package tj.epic.hpi_notif.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import tj.epic.hpi_notif.R;
import tj.epic.hpi_notif.Utils.JackReceiver;
import tj.epic.hpi_notif.Utils.KeyValueApps;
import tj.epic.hpi_notif.Utils.KeyValueTheme;
import tj.epic.hpi_notif.Utils.Notification;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private TextView app0txt;
    private Button app0btn;
    private ImageView app0icon;

    private TextView app1txt;
    private Button app1btn;
    private ImageView app1icon;

    private TextView app2txt;
    private Button app2btn;
    private ImageView app2icon;

    private TextView app3txt;
    private Button app3btn;
    private ImageView app3icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        app0txt = (TextView) findViewById(R.id.app0Name);
        app0btn = (Button) findViewById(R.id.app0Btn);
        app0icon = (ImageView) findViewById(R.id.app0Icon);

        app1txt = (TextView) findViewById(R.id.app1Name);
        app1btn = (Button) findViewById(R.id.app1Btn);
        app1icon = (ImageView) findViewById(R.id.app1Icon);

        app2txt = (TextView) findViewById(R.id.app2Name);
        app2btn = (Button) findViewById(R.id.app2Btn);
        app2icon = (ImageView) findViewById(R.id.app2Icon);

        app3txt = (TextView) findViewById(R.id.app3Name);
        app3btn = (Button) findViewById(R.id.app3Btn);
        app3icon = (ImageView) findViewById(R.id.app3Icon);

        load();

        darkTheme();

        createNotification();
        cancelNotification();

        restartService();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        restartService();
        load();
    }

    @Override
    protected void onResume() {
        try {
            restartService();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    private void load() {
        try {
            for (int i = 0; i < 4; i++) {
                String appPackage = KeyValueApps.appInfo(context, i);
                if (appPackage != null) {

                    PackageManager packageManager = getApplicationContext().getPackageManager();

                    switch (i) {
                        case 0:
                            app0txt.setText(packageManager.getApplicationLabel(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA)));
                            app0icon.setImageDrawable(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA).loadIcon(packageManager));
                            app0btn.setText(getString(R.string.delete_app_btn));
                            app0btn.setOnClickListener(new View.OnClickListener() { // delete app
                                @Override
                                public void onClick(View v) {
                                    KeyValueApps.deleteApp(context, 0);
                                    load();
                                }
                            });
                            break;
                        case 1:
                            app1txt.setText(packageManager.getApplicationLabel(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA)));
                            app1icon.setImageDrawable(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA).loadIcon(packageManager));
                            app1btn.setText(getString(R.string.delete_app_btn));
                            app1btn.setOnClickListener(new View.OnClickListener() { // delete app
                                @Override
                                public void onClick(View v) {
                                    KeyValueApps.deleteApp(context, 1);
                                    load();
                                }
                            });
                            break;
                        case 2:
                            app2txt.setText(packageManager.getApplicationLabel(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA)));
                            app2icon.setImageDrawable(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA).loadIcon(packageManager));
                            app2btn.setText(getString(R.string.delete_app_btn));
                            app2btn.setOnClickListener(new View.OnClickListener() { // delete app
                                @Override
                                public void onClick(View v) {
                                    KeyValueApps.deleteApp(context, 2);
                                    load();
                                }
                            });
                            break;
                        case 3:
                            app3txt.setText(packageManager.getApplicationLabel(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA)));
                            app3icon.setImageDrawable(packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA).loadIcon(packageManager));
                            app3btn.setText(getString(R.string.delete_app_btn));
                            app3btn.setOnClickListener(new View.OnClickListener() { // delete app
                                @Override
                                public void onClick(View v) {
                                    KeyValueApps.deleteApp(context, 3);
                                    load();
                                }
                            });
                    }
                } else {  // set new app
                    switch (i) {
                        case 0:
                            app0txt.setText(getString(R.string.empty_app_name_tv));
                            app0icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_empty));
                            app0btn.setText(getString(R.string.select_app_btn));
                            app0btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent openList = new Intent(MainActivity.this, ListOfApps.class);
                                    openList.putExtra("appNumber", 0);
                                    startActivity(openList);
                                }
                            });
                            break;
                        case 1:
                            app1txt.setText(getString(R.string.empty_app_name_tv));
                            app1icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_empty));
                            app1btn.setText(getString(R.string.select_app_btn));
                            app1btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent openList = new Intent(MainActivity.this, ListOfApps.class);
                                    openList.putExtra("appNumber", 1);
                                    startActivity(openList);
                                }
                            });
                        case 2:
                            app2txt.setText(getString(R.string.empty_app_name_tv));
                            app2icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_empty));
                            app2btn.setText(getString(R.string.select_app_btn));
                            app2btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent openList = new Intent(MainActivity.this, ListOfApps.class);
                                    openList.putExtra("appNumber", 2);
                                    startActivity(openList);
                                }
                            });
                        case 3:
                            app3txt.setText(getString(R.string.empty_app_name_tv));
                            app3icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_empty));
                            app3btn.setText(getString(R.string.select_app_btn));
                            app3btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent openList = new Intent(MainActivity.this, ListOfApps.class);
                                    openList.putExtra("appNumber", 3);
                                    startActivity(openList);
                                }
                            });
                    }
                }
            }
        } catch (NullPointerException | PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void darkTheme() {
        Switch switchDarkTheme = (Switch) findViewById(R.id.switchDarkTheme);

        Log.d("switch", "Status: " + KeyValueTheme.getTheme(MainActivity.this));

        if (KeyValueTheme.getTheme(MainActivity.this) == 1) {
            switchDarkTheme.setChecked(true);
        }

        switchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, "Dark Theme is activated", Toast.LENGTH_SHORT).show();
                    KeyValueTheme.setTheme(MainActivity.this, 1);
                }
                else {
                    Toast.makeText(context, "Dark Theme is disabled", Toast.LENGTH_SHORT).show();
                    KeyValueTheme.setTheme(MainActivity.this, 0);
                }
            }
        });
    }

    private void createNotification() {
        Button create_test_notification = (Button) findViewById(R.id.create_test_notification);
        create_test_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Notification created!", Toast.LENGTH_SHORT).show();
                Notification ntp = new Notification();
                ntp.createNotification(context);
            }
        });
    }
    private void cancelNotification() {
        Button cancelNotificationBtn = (Button) findViewById(R.id.cancel_notification);
        cancelNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Notification canceled!", Toast.LENGTH_SHORT).show();
                Notification ntp = new Notification();
                ntp.closeNotification(context);
            }
        });
    }

    private void restartService() {
        Intent service_kill = new Intent(context, JackReceiver.class);
        context.stopService(service_kill);

        Intent service = new Intent(context, JackReceiver.class);
        context.startService(service);
    }
}
