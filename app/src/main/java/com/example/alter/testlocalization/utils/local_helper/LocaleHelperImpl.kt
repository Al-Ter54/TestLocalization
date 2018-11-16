package com.example.alter.testlocalization

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.util.*

class LocaleHelperImpl
@SuppressLint("CommitPrefEdits")
constructor() : ILocaleHelper {
    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    override fun setLocale(context: Context): Context {
        return setNewLocale(context)
    }

    override fun getActualLocale(context: Context): Locale {
        mSharedPreferences = context.getSharedPreferences("LOCALE", Context.MODE_PRIVATE)
        return Locale(mSharedPreferences!!.getString("Locale", "en"))
    }

    override fun getActualLanguage(context: Context): String {
        mSharedPreferences = context.getSharedPreferences("LOCALE", Context.MODE_PRIVATE)
        return mSharedPreferences!!.getString("Language", "English")
    }

    override fun updateLocale(context: Context, typeLocale: ILocaleHelper.TypeLocale): Context {
        mSharedPreferences = context.getSharedPreferences("LOCALE", Context.MODE_PRIVATE)
        mEditor = mSharedPreferences!!.edit()
        when (typeLocale) {
            ILocaleHelper.TypeLocale.LOCALE_EN -> {
                mActualLanguage = context.resources.getString(R.string.english)
                mActualLocale = US
                mEditor!!.putString("Locale", mActualLocale.toLanguageTag())
                mEditor!!.putString("Language", mActualLanguage)
                mEditor!!.commit()
                getSwitchLanguage(context, mActualLocale)
            }
            ILocaleHelper.TypeLocale.LOCALE_RU -> {
                mActualLanguage = context.resources.getString(R.string.russian)
                mActualLocale = RU
                mEditor!!.putString("Locale", mActualLocale.toLanguageTag())
                mEditor!!.putString("Language", mActualLanguage)
                mEditor!!.commit()
                getSwitchLanguage(context, mActualLocale)
            }
            ILocaleHelper.TypeLocale.LOCALE_UK -> {
                mActualLanguage = context.resources.getString(R.string.ukrainian)
                mActualLocale = UK
                mEditor!!.putString("Locale", mActualLocale.toLanguageTag())
                mEditor!!.putString("Language", mActualLanguage)
                mEditor!!.commit()
                getSwitchLanguage(context, mActualLocale)
            }
            ILocaleHelper.TypeLocale.LOCALE_PL -> {
                mActualLanguage = context.resources.getString(R.string.polish)
                mActualLocale = PL
                mEditor!!.putString("Locale", mActualLocale.toLanguageTag())
                mEditor!!.putString("Language", mActualLanguage)
                mEditor!!.commit()
                getSwitchLanguage(context, mActualLocale)
            }
        }
        return context
    }

    private fun setNewLocale(context: Context): Context {
        var localDefaultLang = Locale.getDefault()
        var newLang:ILocaleHelper.TypeLocale
        when (localDefaultLang) {
            LocaleHelperImpl.US -> newLang = ILocaleHelper.TypeLocale.LOCALE_EN
        LocaleHelperImpl.UK -> newLang = ILocaleHelper.TypeLocale.LOCALE_UK
        LocaleHelperImpl.RU -> newLang = ILocaleHelper.TypeLocale.LOCALE_RU
        LocaleHelperImpl.PL -> newLang = ILocaleHelper.TypeLocale.LOCALE_PL
            else -> {newLang = ILocaleHelper.TypeLocale.LOCALE_EN}
        }
        return updateLocale(context, newLang)
    }

    private fun getSwitchLanguage(context: Context, locale: Locale) {
        val res = context.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(locale)
        res.updateConfiguration(conf, dm)
    }

    companion object {
        val US = Locale("en")
        val RU = Locale("ru")
        val UK = Locale("uk")
        val PL = Locale("pl")
        private var mActualLocale = US
        private var mActualLanguage = "English"
    }
}
