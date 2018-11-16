package com.example.alter.testlocalization

import android.content.Context
import java.util.*

interface ILocaleHelper {

    fun getActualLocale(context: Context): Locale

    fun getActualLanguage(context: Context): String

    fun updateLocale(context: Context, typeLocale: ILocaleHelper.TypeLocale): Context

    fun setLocale(context: Context): Context

    enum class TypeLocale {
        LOCALE_EN,
        LOCALE_RU,
        LOCALE_UK,
        LOCALE_PL
    }
}
