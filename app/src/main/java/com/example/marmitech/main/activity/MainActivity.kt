package com.example.marmitech.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marmitech.apontamento.activity.ConsultaActivity
import com.example.marmitech.apontamento.activity.FuncionarioActivity
import com.example.marmitech.databinding.ActivityApontamentoBinding
import com.example.marmitech.extension.viewBinding


class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityApontamentoBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivApontar.setOnClickListener {
            showFuncionarioIntent()
        }
        binding.tvApontar.setOnClickListener {
            showFuncionarioIntent()
        }

        binding.ivConsultar.setOnClickListener {
            showConsultasIntent()
        }
        binding.tvConsultar.setOnClickListener {
            showConsultasIntent()
        }

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showFuncionarioIntent() {
        val intent = Intent(this, FuncionarioActivity::class.java)
        startActivity(intent)
    }

    private fun showConsultasIntent() {
        val intent = Intent(this, ConsultaActivity::class.java)
        startActivity(intent)
    }
}