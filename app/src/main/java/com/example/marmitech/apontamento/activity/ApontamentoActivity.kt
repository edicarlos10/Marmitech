package com.example.marmitech.apontamento.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marmitech.R
import com.example.marmitech.databinding.ActivityLoginBinding
import com.example.marmitech.extension.viewBinding

class ApontamentoActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}