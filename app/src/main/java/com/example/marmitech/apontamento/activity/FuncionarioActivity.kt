package com.example.marmitech.apontamento.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marmitech.apontamento.fragment.FuncionarioFragment
import com.example.marmitech.databinding.ActivityFuncionarioBinding

class FuncionarioActivity : AppCompatActivity() {

    companion object{
        const val TURMA_SELECTED = "turma_selected"
    }

    private lateinit var binding: ActivityFuncionarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras

        binding = ActivityFuncionarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val funcionarioFragment = FuncionarioFragment.newInstance(bundle?.getString(TURMA_SELECTED) ?: "")
        supportFragmentManager.beginTransaction()
            .add(binding.clFuncionarioActi.id, funcionarioFragment)
            .commit()

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}