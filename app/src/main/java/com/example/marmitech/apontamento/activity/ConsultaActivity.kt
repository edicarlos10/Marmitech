package com.example.marmitech.apontamento.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marmitech.R
import com.example.marmitech.apontamento.fragment.ConsultaFragment
import com.example.marmitech.databinding.ActivityConsultaBinding
import com.example.marmitech.extension.viewBinding

class ConsultaActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityConsultaBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        val consultaFragment = ConsultaFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(binding.clConsulta.id, consultaFragment)
            .commit()

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}