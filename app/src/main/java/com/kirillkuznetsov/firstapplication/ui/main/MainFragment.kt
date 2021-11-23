package com.kirillkuznetsov.firstapplication.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kirillkuznetsov.firstapplication.ui.MainViewModel
import com.kirillkuznetsov.firstapplication.ui.R
import com.kirillkuznetsov.firstapplication.ui.base.BaseFragment
import com.kirillkuznetsov.firstapplication.ui.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            val navController =
                (childFragmentManager.findFragmentById(R.id.mainFragmentNavigationHost) as NavHostFragment).navController
            bottomNavigationView.setupWithNavController(navController)
        }
    }
}