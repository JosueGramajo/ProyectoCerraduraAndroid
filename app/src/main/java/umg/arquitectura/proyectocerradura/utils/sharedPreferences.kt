package umg.arquitectura.proyectocerradura.utils

import android.app.Activity
import android.content.Context

object SharedPreferences{

    val enrolledUser = "enrolledUser"

    fun writeSharedPreference(key : String, value : String, context : Activity){
        val sharedPref = context.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }
    fun getSharedPreference(key : String, context : Activity) : String{
        val sharedPref = context.getPreferences(Context.MODE_PRIVATE) ?: return ""
        val storedValue = sharedPref.getString(key, "")
        return storedValue!!
    }
}