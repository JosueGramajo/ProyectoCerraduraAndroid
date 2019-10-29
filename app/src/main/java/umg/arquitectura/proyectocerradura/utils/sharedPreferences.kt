package umg.arquitectura.proyectocerradura.utils

import android.app.Activity
import android.content.Context

object SharedPreferences{

    val PREFS_FILENAME = "umg.cerradura.prefs"

    val enrolledUser = "enrolledUser"

    fun writeSharedPreference(key : String, value : String, context : Activity){
        val sharedPref = context.getSharedPreferences(PREFS_FILENAME, 0)
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }
    fun getSharedPreference(key : String, context : Activity) : String{
        val sharedPref = context.getSharedPreferences(PREFS_FILENAME, 0)
        val storedValue = sharedPref.getString(key, "")
        return storedValue!!
    }
}