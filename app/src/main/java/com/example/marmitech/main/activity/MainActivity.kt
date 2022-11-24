package com.example.marmitech.main.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.base.Event
import com.example.marmitech.apontamento.ApontamentoViewModel
import com.example.marmitech.apontamento.activity.ConsultaActivity
import com.example.marmitech.apontamento.activity.FuncionarioActivity
import com.example.marmitech.databinding.ActivityApontamentoBinding
import com.example.marmitech.extension.showDialog
import com.example.marmitech.extension.toJson
import com.example.marmitech.extension.viewBinding
import com.example.marmitech.extension.writeFile
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityApontamentoBinding::inflate)
    private val apontamentoViewModel: ApontamentoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        apontamentoViewModel.loading.observe(this) { onLoading(it) }
        apontamentoViewModel.error.observe(this) { onError(it) }
        apontamentoViewModel.allApontamento.observe(this) { listApontamento ->
            sucessGetApontamentos(
                listApontamento
            )
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

        setListeners()
    }

    private fun sucessGetApontamentos(listApontamento: List<Apontamento>?) {
        listApontamento?.let {
            writeFile(toJson(it))
        } ?: kotlin.run {
            this.showDialog("Não existe apontamentos para serem enviados.")
        }
    }

    private fun setListeners() {
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

        binding.tvIntegrar.setOnClickListener {
            apontamentoViewModel.getAllApontamento()
        }
        binding.ivIntegrar.setOnClickListener {
            apontamentoViewModel.getAllApontamento()
        }
    }

    private fun showFuncionarioIntent() {
        val intent = Intent(this, FuncionarioActivity::class.java)
        startActivity(intent)
    }

    private fun showConsultasIntent() {
        val intent = Intent(this, ConsultaActivity::class.java)
        startActivity(intent)
    }

    private fun onLoading(loading: Boolean?) {
        loading?.let {
            if (loading == true) {
                binding.clSecond.visibility = View.GONE
                binding.progressBarMain.visibility = View.VISIBLE
            } else {
                binding.progressBarMain.visibility = View.GONE
                binding.clSecond.visibility = View.VISIBLE
            }
        }
    }

    private fun onError(error: Event.Error?) {
        if (error == null) return
        else this.showDialog("Desculpe não foi possível completar a ação, tente novamente.")
    }
}