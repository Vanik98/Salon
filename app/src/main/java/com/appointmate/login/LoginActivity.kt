package com.appointmate.login

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.appointmate.base.utils.constants.SalonConstants.EMPTY_STRING
import com.appointmate.login.model.VerifySuccessCodeEnum
import com.example.salon.R
import com.example.salon.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModel<LoginViewModel>()
    private var number = EMPTY_STRING
    private var verifyNumber = EMPTY_STRING
    private var isVerifyView = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupListeners()
        observeLiveData()
    }

    private fun setupListeners() {
        binding.num0Button.setOnClickListener { setNumber(0) }
        binding.num1Button.setOnClickListener { setNumber(1) }
        binding.num2Button.setOnClickListener { setNumber(2) }
        binding.num3Button.setOnClickListener { setNumber(3) }
        binding.num4Button.setOnClickListener { setNumber(4) }
        binding.num5Button.setOnClickListener { setNumber(5) }
        binding.num6Button.setOnClickListener { setNumber(6) }
        binding.num7Button.setOnClickListener { setNumber(7) }
        binding.num8Button.setOnClickListener { setNumber(8) }
        binding.num9Button.setOnClickListener { setNumber(9) }
        binding.confirmButton.setOnClickListener { confirm() }
        binding.verifyButton.setOnClickListener { verify() }
        binding.resendCodeTextView.setOnClickListener { resend() }
        binding.deleteImageView.setOnClickListener { deleteNumber() }
        binding.backImageView.setOnClickListener { back() }
    }

    private fun observeLiveData() {
        viewModel.successCode.observe(this) {
            when (VerifySuccessCodeEnum.from(it)) {
                VerifySuccessCodeEnum.SUCCESSFUL -> {
                    //TODO: open Home Activity
                }
                VerifySuccessCodeEnum.FAILED -> {
                    Toast.makeText(this, "Verify number is wrong", Toast.LENGTH_SHORT).show()
                }
                VerifySuccessCodeEnum.UNDEFINED -> {}
            }
        }
    }

    private fun setNumber(n: Int) {
        when (isVerifyView) {
            false -> {
                if (number.length < 16) {
                    number += n
                    if (number.length % 3 != 0) {
                        number += " "
                    }
                    binding.phoneNumberTextView.text = number
                } else {
                    Toast.makeText(this, "The number of digits exceeds the phone number length limit", Toast.LENGTH_SHORT).show()
                }
            }
            true -> {
                if (verifyNumber.length < 6) {
                    when (verifyNumber.length) {
                        0 -> binding.verifyNum1TextView.text = n.toString()
                        1 -> binding.verifyNum2TextView.text = n.toString()
                        2 -> binding.verifyNum3TextView.text = n.toString()
                        3 -> binding.verifyNum4TextView.text = n.toString()
                        4 -> binding.verifyNum5TextView.text = n.toString()
                        5 -> binding.verifyNum6TextView.text = n.toString()
                    }
                    verifyNumber += n
                }
                if (verifyNumber.length == 6) {
                    changeVerifyNumberButtonColor(true)
                }
            }
        }

    }

    private fun confirm() {
        if(number.length < 9){
            Toast.makeText(this, "The number is wrong", Toast.LENGTH_SHORT).show()
        }else{
            changeView(true)
            viewModel.sendSms(number)
        }
    }

    private fun verify() {
        if (verifyNumber.length == 6) {
            viewModel.verifyCode(verifyNumber.toInt())
        } else {

        }
    }


    private fun changeView(changeToVerifyView: Boolean) {
        binding.backImageView.isVisible = changeToVerifyView
        binding.confirmGroup.isVisible = !changeToVerifyView
        binding.verifyGroup.isVisible = changeToVerifyView
        isVerifyView = changeToVerifyView
    }

    private fun resend() {

    }

    private fun deleteNumber() {
        when (isVerifyView) {
            false -> {
                if (number.length > 5) {
                    number = number.trim().dropLast(1)
                }
                binding.phoneNumberTextView.text = number
            }
            true -> {
                changeVerifyNumberButtonColor(false)
                verifyNumber = verifyNumber.trim().dropLast(1)
                when (verifyNumber.length) {
                    0 -> binding.verifyNum1TextView.text = EMPTY_STRING
                    1 -> binding.verifyNum2TextView.text = EMPTY_STRING
                    2 -> binding.verifyNum3TextView.text = EMPTY_STRING
                    3 -> binding.verifyNum4TextView.text = EMPTY_STRING
                    4 -> binding.verifyNum5TextView.text = EMPTY_STRING
                    5 -> binding.verifyNum6TextView.text = EMPTY_STRING
                }
            }
        }
    }

    private fun back() {
        changeVerifyNumberButtonColor(false)
        changeView(false)
        verifyNumber = EMPTY_STRING
        binding.verifyNum1TextView.text = EMPTY_STRING
        binding.verifyNum2TextView.text = EMPTY_STRING
        binding.verifyNum3TextView.text = EMPTY_STRING
        binding.verifyNum4TextView.text = EMPTY_STRING
        binding.verifyNum5TextView.text = EMPTY_STRING
        binding.verifyNum6TextView.text = EMPTY_STRING
    }

    private fun changeVerifyNumberButtonColor(isEnabled: Boolean) {
        binding.verifyButton.isEnabled = isEnabled
        binding.verifyButton.backgroundTintList = if (isEnabled) {
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.color_4_1))
        } else {
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.color_1_5))
        }
    }
}