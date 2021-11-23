package com.kirillkuznetsov.firstapplication.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kirillkuznetsov.firstapplication.logBackstack
import com.kirillkuznetsov.firstapplication.logFragmentHierarchy
import com.kirillkuznetsov.firstapplication.ui.BuildConfig
import timber.log.Timber

open class BaseFragment : Fragment {

    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun onStart() {
        super.onStart()
        if (BuildConfig.DEBUG) {
            val logTag = "NavigationInfo"
            logFragmentHierarchy(logTag)
            try {
                findNavController().logBackstack(logTag)
            } catch (error: IllegalStateException) {
                Timber.e(error)
            }
        }
    }
}