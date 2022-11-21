package com.example.marmitech.apontamento.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.marmitech.databinding.ActivityFuncionarioBinding
import com.example.marmitech.apontamento.fragment.FuncionarioFragment

class FuncionarioActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFuncionarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFuncionarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val funcionarioFragment = FuncionarioFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(binding.clFuncionarioActi.id, funcionarioFragment)
            .commit()

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}