package com.budi.submission.receipe;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import static com.budi.submission.receipe.SettingHelper.DEFAULT_MODE;

/**
 * Created by Budi Ardianata on 16/04/2020.
 * Project: submission
 * Email: budiardianata@windowslive.com
 */
public class App extends Application {
    static SharedPreferences preferences;
    @Override
    public void onCreate() {
        super.onCreate();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SettingHelper.applyTheme(preferences.getString("theme", DEFAULT_MODE));
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }
    public static int getLayout(){
        return preferences.getInt("layout",1);
    }

}
