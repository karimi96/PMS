package com.karimi.pms.helper
import android.content.Context
import android.content.SharedPreferences

class Session {

    private var ExtrasPref: SharedPreferences? = null
    private var extraEditor: SharedPreferences.Editor? = null

    private val PREF_EXTRAS = "SSP"

//    constructor(){
//        ExtrasPref = App().context?.getSharedPreferences(PREF_EXTRAS, Context.MODE_PRIVATE)

//        ExtrasPref = App.context.getSharedPreferences(PREF_EXTRAS, Context.MODE_PRIVATE)
//        extraEditor = ExtrasPref!!.edit()
//    }


    fun Session() {
        ExtrasPref = App().context?.getSharedPreferences(PREF_EXTRAS, Context.MODE_PRIVATE)
//        ExtrasPref = App.context.getSharedPreferences(PREF_EXTRAS, Context.MODE_PRIVATE)
        extraEditor = ExtrasPref!!.edit()
    }

    fun getInstance() : com.karimi.pms.helper.Session {
        return com.karimi.pms.helper.Session()
    }

    fun putExtra(key: String?, value: String?) {
        extraEditor!!.putString(key, value)
        extraEditor!!.commit()
    }

    fun putExtra(key: String?, value: Boolean?) {
        extraEditor!!.putBoolean(key, value!!)
        extraEditor!!.commit()
    }

    fun putExtra(key: String?, value: Int?) {
        extraEditor!!.putInt(key, value!!)
        extraEditor!!.commit()
    }

    fun putExtra(key: String?, value: Long?) {
        extraEditor!!.putLong(key, value!!)
        extraEditor!!.commit()
    }

    fun putExtra(key: String?, list: Set<String?>?) {
        extraEditor!!.putStringSet(key, list)
        extraEditor!!.commit()
    }

    fun getString(key: String?): String? {
        return ExtrasPref!!.getString(key, "")
    }

    fun getSet(key: String?): Set<String?>? {
        return ExtrasPref!!.getStringSet(key, null)
    }

    fun getInt(key: String?): Int {
        return ExtrasPref!!.getInt(key, 0)
    }

    fun getBoolean(key: String?): Boolean {
        return ExtrasPref!!.getBoolean(key, false)
    }

    fun getLong(key: String?): Long? {
        return ExtrasPref!!.getLong(key, 0)
    }

    fun getExtras(): Map<String?, *>? {
        return ExtrasPref!!.all
    }

    fun clearExtras(): Boolean {
        extraEditor!!.clear()
        return extraEditor!!.commit()
    }

    fun remove(key: String?) {
        extraEditor!!.remove(key)
        extraEditor!!.apply()
    }

/*    public void setUser(int type) {
        extraEditor.putInt(Config.USER, type);
        extraEditor.commit();
    }
    public int getUser() {
        return ExtrasPref.getInt(Config.USER, -1);
    }*/
}