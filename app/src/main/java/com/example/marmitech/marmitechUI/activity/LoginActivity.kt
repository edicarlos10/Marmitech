package com.example.marmitech.marmitechUI.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marmitech.databinding.ActivityLoginBinding
import com.example.marmitech.extension.viewBinding
import com.example.marmitech.marmitechUI.fragment.LoginFragment

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initFragment()
    }

    private fun initFragment() {
        val loginFragment = LoginFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(binding.constraintLogin.id, loginFragment)
            .commit()
    }
}