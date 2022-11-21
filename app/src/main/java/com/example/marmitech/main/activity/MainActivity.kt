package com.example.marmitech.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marmitech.apontamento.activity.FuncionarioActivity
import com.example.marmitech.apontamento.activity.FuncionarioActivity.Companion.TURMA_SELECTED
import com.example.marmitech.databinding.ActivityApontamentoBinding
import com.example.marmitech.extension.viewBinding


class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityApontamentoBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras?.getString(TURMA_SELECTED) ?: ""

        binding.ivApontar.setOnClickListener {
            showFuncionarioIntent(bundle)
        }
        binding.tvApontar.setOnClickListener {
            showFuncionarioIntent(bundle)
        }

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showFuncionarioIntent(bundle: String) {
        val intent = Intent(this, FuncionarioActivity::class.java)
        intent.putExtra(TURMA_SELECTED, bundle)
        startActivity(intent)
    }
}