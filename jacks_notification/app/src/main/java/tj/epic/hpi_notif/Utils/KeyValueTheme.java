package tj.epic.hpi_notif.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class KeyValueTheme {

    private static String PREF_NAME = "themeStatus";

    public KeyValueTheme() {
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setTheme(Context context, int status) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt("dark", status);
        editor.apply();
    }
    public static int getTheme(Context context) {
        return getPrefs(context).getInt("dark", 0);
    }

}
