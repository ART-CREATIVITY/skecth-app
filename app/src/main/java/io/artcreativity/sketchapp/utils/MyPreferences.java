package io.artcreativity.sketchapp.utils;

import android.content.SharedPreferences;
import android.util.Log;

public class MyPreferences {
    private final SharedPreferences preferences;
    private static MyPreferences myPreferences;

    private MyPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public static MyPreferences getInstance(SharedPreferences preferences) {
        if(myPreferences==null) {
            myPreferences = new MyPreferences(preferences);
        }
        return myPreferences;
    }

    public void saveFirstLogin(boolean state) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PreferenceKey.LOGIN_KEY.name(), state);
        editor.apply();
    }

    public boolean isFirstLogin() {
        Log.d("TAG", "isFirstLogin: " + PreferenceKey.LOGIN_KEY.name());
        return preferences.getBoolean(PreferenceKey.LOGIN_KEY.name(), true);
    }

}
