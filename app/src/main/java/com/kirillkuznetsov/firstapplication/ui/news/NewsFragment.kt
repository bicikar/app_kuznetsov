package com.kirillkuznetsov.firstapplication.ui.news

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kirillkuznetsov.firstapplication.ui.R
import com.kirillkuznetsov.firstapplication.ui.base.BaseFragment
import com.kirillkuznetsov.firstapplication.ui.databinding.FragmentNewsBinding

class NewsFragment : BaseFragment(R.layout.fragment_news) {

    private val viewBinding by viewBinding(FragmentNewsBinding::bind)

    private val viewModel: NewsViewModel by viewModels()
}