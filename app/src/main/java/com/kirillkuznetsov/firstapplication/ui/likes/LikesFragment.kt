package com.kirillkuznetsov.firstapplication.ui.likes

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kirillkuznetsov.firstapplication.ui.R
import com.kirillkuznetsov.firstapplication.ui.base.BaseFragment
import com.kirillkuznetsov.firstapplication.ui.databinding.FragmentLikesBinding

class LikesFragment : BaseFragment(R.layout.fragment_likes) {

    private val viewBinding by viewBinding(FragmentLikesBinding::bind)

    private val viewModel: LikesViewModel by viewModels()
}