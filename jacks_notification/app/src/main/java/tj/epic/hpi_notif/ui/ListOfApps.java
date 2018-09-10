package tj.epic.hpi_notif.ui;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tj.epic.hpi_notif.R;
import tj.epic.hpi_notif.Utils.KeyValueApps;
import tj.epic.hpi_notif.adapters.AppAdapter;

public class ListOfApps extends ListActivity {

    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private AppAdapter listadapter = null;
    private int appNum = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            appNum = getIntent().getExtras().getInt("appNumber");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (appNum != 9999) {
            load();
        } else {    // broken app number
            Toast.makeText(this, "Error: broken application number", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void load() {
        packageManager = getPackageManager();

        new LoadApplications().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = applist.get(position);
        try {
            KeyValueApps.setApp(this, app.packageName, appNum);
            Toast.makeText(this, "App selected", Toast.LENGTH_SHORT).show();
            finish();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ListOfApps.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(ListOfApps.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();

        for (ApplicationInfo info : list) {
            try {
                if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    appList.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appList;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadapter = new AppAdapter(ListOfApps.this, R.layout.list_item, applist);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadapter);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ListOfApps.this, null, "Loading apps info");
            super.onPreExecute();
        }
    }
}
