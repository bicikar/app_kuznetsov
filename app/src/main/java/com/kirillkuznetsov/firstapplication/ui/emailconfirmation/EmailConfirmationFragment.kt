package com.kirillkuznetsov.firstapplication.ui.emailconfirmation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kirillkuznetsov.firstapplication.ui.base.BaseFragment
import com.kirillkuznetsov.firstapplication.ui.R
import com.kirillkuznetsov.firstapplication.ui.databinding.FragmentEmailConfirmationBinding
import dagger.hilt.android.AndroidEntryPoint
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController


@AndroidEntryPoint
class EmailConfirmationFragment : BaseFragment(R.layout.fragment_email_confirmation) {

    private val viewBinding by viewBinding(FragmentEmailConfirmationBinding::bind)

    private val viewModel: EmailConfirmationViewModel by viewModels()

    private val millisRunning : Long = 20000
    private val countDownInterval : Long= 1000
    private val codeTimer = timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.firstname = savedInstanceState?.getString("SIGN_UP_FIRSTNAME") ?: ""
        viewModel.lastname = savedInstanceState?.getString("SIGN_UP_LASTNAME") ?: ""
        viewModel.nickname = savedInstanceState?.getString("SIGN_UP_NICKNAME") ?: ""
        viewModel.email = savedInstanceState?.getString("SIGN_UP_EMAIL") ?: ""
        viewModel.password = savedInstanceState?.getString("SIGN_UP_PASSWORD") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.confirmVerificationCodeButton.setOnClickListener {
            viewModel.confirmVerificationCode(
                code = viewBinding.verificationCodeEditText.getCode()
            )
        }
        viewBinding.confirmVerificationCodeButton.isEnabled = false
        viewBinding.verificationCodeEditText.onVerificationCodeFilledChangeListener = {
            viewBinding.confirmVerificationCodeButton.isEnabled = it
        }

        viewBinding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewBinding.sendAgainClickableText.isEnabled = false
        codeTimer.start()
        viewBinding.sendAgainClickableText.setOnClickListener {
            Toast.makeText(requireContext(), "Код отправлен", Toast.LENGTH_SHORT).show()
            viewBinding.sendAgainClickableText.isEnabled = false
            codeTimer.start()
        }

    }

    override fun onPause() {
        super.onPause()
        codeTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        codeTimer.cancel()
    }

    private fun timer() : CountDownTimer {
        return object: CountDownTimer(millisRunning,countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                viewBinding.sendInformation.isVisible = true
                val secondsUntilFinished = (millisUntilFinished/1000).toString()
                viewBinding.sendInformation.text = "Осталось: $secondsUntilFinished с"
            }

            override fun onFinish() {
                viewBinding.sendAgainClickableText.isEnabled = true
                viewBinding.sendInformation.isVisible = false
            }
        }
    }
}