package com.task.eurcurrencyconverter.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.spotspal.fanartec.core.MyApplication

object sharedPreferencesUtils {


    private var pref: SharedPreferences? = null
    const val PREFS_NAME = "preferences_key"


    fun saveObjectToSharedPreference(
        preferenceKey: String,
        `object`: Any?
    ) {
        val sharedPreferences = MyApplication.appInstance.getSharedPreferences(PREFS_NAME, 0)
        val sharedPreferencesEditor = sharedPreferences.edit()
        val gson = Gson()
        val serializedObject = gson.toJson(`object`)
        sharedPreferencesEditor.putString(preferenceKey, serializedObject)
        sharedPreferencesEditor.commit()
    }

    fun <GenericClass> getSavedObjectFromPreference(
        preferenceKey: String,
        classType: Class<GenericClass>
    ): GenericClass? {
        val sharedPreferences = MyApplication.appInstance.getSharedPreferences(PREFS_NAME, 0)
        if (sharedPreferences.contains(preferenceKey)) {
            val gson = Gson()
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType)
        }
        return null
    }

    fun removeSharedPreferences(preferenceKey: String)
    {
        val sharedPreferences = MyApplication.appInstance.getSharedPreferences(PREFS_NAME, 0)
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.remove(preferenceKey)
        sharedPreferencesEditor.commit()
    }

}