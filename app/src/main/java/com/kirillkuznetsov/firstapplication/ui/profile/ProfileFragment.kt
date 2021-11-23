package com.kirillkuznetsov.firstapplication.ui.profile

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kirillkuznetsov.firstapplication.ui.R
import com.kirillkuznetsov.firstapplication.ui.base.BaseFragment
import com.kirillkuznetsov.firstapplication.ui.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewBinding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()
}