package com.example.marmitech.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marmitech.databinding.ActivityApontamentoBinding
import com.example.marmitech.extension.viewBinding
import com.example.marmitech.apontamento.activity.FuncionarioActivity


class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityApontamentoBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivApontar.setOnClickListener {
            startActivity(Intent(this, FuncionarioActivity::class.java))
        }
        binding.tvApontar.setOnClickListener {
            startActivity(Intent(this, FuncionarioActivity::class.java))
        }

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}