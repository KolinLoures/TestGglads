package com.example.kolin.testgglads.presentation.service;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by kolin on 26.01.2017.
 */

public class ServicePreferences {

    private static final String PREF_LAST_COUNT_RESULT = "lastResultCount";
    private static final String PREF_CATEGORY_SUBSCRIBE = "category_subscribe";


    public static int getLastResultCount(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getInt(PREF_LAST_COUNT_RESULT, 0);
    }

    public static void setLastResultCount(Context context, int lastResultCount) {
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_LAST_COUNT_RESULT, lastResultCount)
                .apply();
    }

    public static String getSubscribedCategory(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(PREF_CATEGORY_SUBSCRIBE, null);
    }

    public static void setSubscribeToCategory(Context context, String category) {
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_CATEGORY_SUBSCRIBE, category)
                .apply();
    }

}
