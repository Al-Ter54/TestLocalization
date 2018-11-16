package com.example.alter.testlocalization.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alter.testlocalization.ILocaleHelper
import com.example.alter.testlocalization.LocaleHelperImpl
import com.example.alter.testlocalization.R
import com.example.alter.testlocalization.R.id.*
import com.example.alter.testlocalization.presentation.ILocalizationListener


class MainFragment : Fragment() {

    private var mOnClickListener : View.OnClickListener? = null
    private var mLocaleListener : ILocalizationListener? = null
    private var mLocaleHelperImpl: LocaleHelperImpl = LocaleHelperImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_main, container, false)
        mOnClickListener = View.OnClickListener {
            checkActualTextView(it.id)
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(onLocaleListener: ILocalizationListener) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        mLocaleListener = onLocaleListener
                    }
                }
    }

    fun getTextViewList() : ArrayList<ViewLocalization> {
        var list : ArrayList<ViewLocalization> = ArrayList()
        list.add(ViewLocalization(R.id.tvEnglish, tvEnglish, ILocaleHelper.TypeLocale.LOCALE_EN))
        list.add(ViewLocalization(R.id.tvRussian, tvRussian, ILocaleHelper.TypeLocale.LOCALE_RU))
        list.add(ViewLocalization(R.id.tvUkrainian, tvUkrainian, ILocaleHelper.TypeLocale.LOCALE_UK))
        list.add(ViewLocalization(R.id.tvPolish, tvPolish, ILocaleHelper.TypeLocale.LOCALE_PL))
        return list
    }

    fun checkActualTextView(item: Int){
        getTextViewList().forEach {
            if(it.mId == item) {
                switchLocale(it.mId)
                Thread.sleep(2_000)
                mLocaleListener!!.restartActivity() // ToDo Handler
                it.mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_done_24px, 0)
            }
            else {
                it.mTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun switchLocale(id : Int){
        when (id) {
            R.id.tvEnglish -> mLocaleHelperImpl.updateLocale(this.context!!, ILocaleHelper.TypeLocale.LOCALE_EN)
            R.id.tvRussian -> mLocaleHelperImpl.updateLocale(this.context!!, ILocaleHelper.TypeLocale.LOCALE_RU)
            R.id.tvUkrainian -> mLocaleHelperImpl.updateLocale(this.context!!, ILocaleHelper.TypeLocale.LOCALE_UK)
            R.id.tvPolish -> mLocaleHelperImpl.updateLocale(this.context!!, ILocaleHelper.TypeLocale.LOCALE_PL)
        }
    }
}