package com.example.marmitech.apontamento.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marmitech.apontamento.fragment.FuncionarioFragment
import com.example.marmitech.databinding.ActivityFuncionarioBinding
import com.example.marmitech.extension.viewBinding

class FuncionarioActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityFuncionarioBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val funcionarioFragment = FuncionarioFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(binding.clFuncionarioActi.id, funcionarioFragment)
            .commit()

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}