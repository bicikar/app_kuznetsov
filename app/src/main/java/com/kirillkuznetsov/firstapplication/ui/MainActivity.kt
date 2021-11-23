package com.kirillkuznetsov.firstapplication.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToAuthorizationStatus()
    }

    private fun subscribeToAuthorizationStatus() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isAuthorizedFlow.collect {
                    showSuitableNavigationFlow(it)
                }
            }
        }
    }

    private fun showSuitableNavigationFlow(isAuthorized: Boolean) {
        val navController = findNavController(R.id.mainActivityNavigationHost)
        when (isAuthorized) {
            true -> {
                if (navController.backQueue.any { it.destination.id == R.id.registered_user_nav_graph}) {
                    return
                }
                navController.navigate(R.id.action_registeredUserNavGraph)
            }
            false -> {
                if (navController.backQueue.any { it.destination.id == R.id.guest_nav_graph}) {
                    return
                }
                navController.navigate(R.id.action_guestNavGraph)
            }
        }
    }
}

