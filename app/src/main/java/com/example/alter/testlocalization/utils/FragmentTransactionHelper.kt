package com.example.alter.testlocalization.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.alter.testlocalization.R
import com.example.alter.testlocalization.presentation.activities.BaseActivity


object FragmentTransactionHelper {

    var flagVisibilityFragment: Boolean = false

    fun addFragment(context: FragmentActivity, fragment: Fragment?, viewID: Int, tag: String, canAddBackStack: Boolean) {
        val ft = context.supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.pop_out_right, R.animator.pop_in_left)
        if (canAddBackStack) ft.addToBackStack(tag)
        if (fragment != null) {
            ft.add(viewID, fragment, tag)
        }
        hideAllFragment(context, tag, ft)
        if (fragment != null) {
            ft.show(fragment)
        }
        ft.commit()
    }

    fun showFragment(context: FragmentActivity, tag: String, fragment: Fragment?){
        val ft = context.supportFragmentManager.beginTransaction()
        hideAllFragment(context, tag, ft)
        if (fragment != null) {
            ft.show(fragment)
        }
        ft.commit()
    }

    fun hideFragment(context: FragmentActivity, fragment: Fragment?){
        val ft = context.supportFragmentManager.beginTransaction()
        if (fragment != null) {
            ft.hide(fragment).commit()
        }
    }

    fun hideAllFragment(context: FragmentActivity, tag: String, fragmentTransaction: FragmentTransaction){
        val fragments = context.supportFragmentManager.fragments
        fragments.forEach { if(!it.tag.equals(tag)){ fragmentTransaction.hide(it) }
        }
    }

    fun removeFragment(context: FragmentActivity, fragment: Fragment?){
        val ft = context.supportFragmentManager.beginTransaction()
        if (fragment != null) {
            ft.remove(fragment).commit()
        }
    }

    fun replaceFragment(context: FragmentActivity, fragment: Fragment, tag: String,
                        canAddBackStack: Boolean, viewID: Int, needAnimate: Boolean) {
        val ft = context.supportFragmentManager.beginTransaction()
        hideAllFragment(context, tag, ft)
        if(needAnimate) ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.pop_out_right, R.animator.pop_in_left)
        ft.replace(viewID, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        if (canAddBackStack) { ft.addToBackStack(null) }
        ft.commit()
    }

    fun replaceFragment(context: BaseActivity,
                        fragment: Fragment, args: Bundle?, tag: String, canAddBackStack: Boolean, viewID: Int, needAnimate: Boolean) {
        fragment.arguments = args
        replaceFragment(context, fragment, tag, canAddBackStack, viewID, needAnimate)
    }

    fun popBackStack(context: FragmentActivity, numBackStack: Int) {
        val manager = context.supportFragmentManager
        val fragCount = manager.backStackEntryCount
        for (i in 0 until fragCount - numBackStack) {
            manager.popBackStack()
        }
    }

    fun isFragmentPresent(tag: String, fragmentManager: FragmentManager): Boolean {
        val frag = fragmentManager.findFragmentByTag(tag)
        return frag == null
    }

    fun isFragmentDisplay(id: Int, fragmentManager: FragmentManager): Fragment? {
        val frag = fragmentManager.findFragmentById(id)
        return frag
    }
}