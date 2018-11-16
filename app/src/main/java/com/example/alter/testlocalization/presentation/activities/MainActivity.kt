package com.example.alter.testlocalization.presentation.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alter.testlocalization.R
import com.example.alter.testlocalization.presentation.ILocalizationListener
import com.example.alter.testlocalization.presentation.fragments.MainFragment
import com.example.alter.testlocalization.utils.FragmentTransactionHelper

class MainActivity : BaseActivity() {
    val fragmentTransactionHelper = FragmentTransactionHelper
    val mLocalizationListener: ILocalizationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentTransactionHelper.addFragment(this, MainFragment.newInstance(this!!.mLocalizationListener!!), R.id.containerMainActivity, "", true)
//        mLocalizationListener = ILocalizationListener
    }
}
