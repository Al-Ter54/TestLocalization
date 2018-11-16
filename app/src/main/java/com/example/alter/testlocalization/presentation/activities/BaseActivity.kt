package com.example.alter.testlocalization.presentation.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.alter.testlocalization.LocaleHelperImpl
import com.example.alter.testlocalization.utils.local_helper.ObscuraContextWrapper



open class BaseActivity : AppCompatActivity() {
    protected override fun attachBaseContext(newBase: Context?) {
        if (newBase != null && LocaleHelperImpl().getActualLocale(newBase!!).toLanguageTag() != null) {
            super.attachBaseContext(ObscuraContextWrapper.wrap(newBase, LocaleHelperImpl().getActualLocale(newBase!!).toLanguageTag()))
        }
    }
}