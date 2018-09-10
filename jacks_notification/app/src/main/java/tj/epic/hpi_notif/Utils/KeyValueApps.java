package tj.epic.hpi_notif.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class KeyValueApps {
    private static String PREF_NAME = "appsInfo";

    public KeyValueApps() {
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setApp(Context context, String packageName, int appNumber) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("app"+appNumber, packageName);
        editor.apply();
    }

    public static void deleteApp(Context context, int appNumber) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("app"+appNumber, null);
        editor.apply();
    }

    public static String appInfo(Context context, int appNumber) {
        return getPrefs(context).getString("app"+appNumber, null);
    }
}