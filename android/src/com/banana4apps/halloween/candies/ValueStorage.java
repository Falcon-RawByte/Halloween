package com.banana4apps.halloween.candies;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

public class ValueStorage {

    private static String TAG = "ValueStorage";
    private static Context myActivity;
    public static Activity activity;
    public static SharedPreferences sPref;

    public ValueStorage(Context context)
    {
        myActivity = context;
        activity = (Activity) context;

    }

    public static void Initialize(Context context)
    {
        myActivity = context;
        activity = (Activity) context;
    }

    public static String getVar(String key) {
        try {
            sPref = myActivity.getSharedPreferences("var_" + activity.getPackageName(),
                    Context.MODE_PRIVATE);
            String sc = sPref.getString(key, "");
            return sc;
        } catch (Exception e) {
            return "";
        }
    }

    public static int getIntVar(String key)
    {
        String str = getVar(key);
        if (!str.equals(""))
            try { return Integer.parseInt(str); }
            catch(Exception e) {
                //Error code
                return 0;
            }
        else setVar(key, "0");
        return 0;
    }

    public static void setVar(String key, String val) {
        try {
            sPref = myActivity.getSharedPreferences("var_" + activity.getPackageName(),
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(key, val);
            ed.commit();
        } catch (Exception e) {
        }
    }

    public boolean isTablet() {
        return (myActivity.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
