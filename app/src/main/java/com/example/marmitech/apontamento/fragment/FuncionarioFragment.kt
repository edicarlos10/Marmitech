package com.example.marmitech.apontamento.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.base.Event
import com.example.marmitech.apontamento.ApontamentoViewModel
import com.example.marmitech.databinding.FragmentFuncionarioBinding
import com.example.marmitech.extension.showDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class FuncionarioFragment : Fragment() {
    companion object {
        fun newInstance(): FuncionarioFragment {
            val args = Bundle().apply { }
            val fragment = FuncionarioFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentFuncionarioBinding? = null
    private val apontamentoViewModel: ApontamentoViewModel by viewModel()
    private lateinit var listOfFuncionario: MutableList<Funcionario>
    private var turmaSelected: String? = null
    private var index = 0

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        apontamentoViewModel.loading.observe(this) { onLoading(it) }
        apontamentoViewModel.error.observe(this) { onError(it) }

        val sharedPref =
            activity?.getSharedPreferences("turma_selected", Context.MODE_PRIVATE) ?: return
        turmaSelected = sharedPref.getInt("turma_selected", 0).toString()

        apontamentoViewModel.allFuncionario.observe(this) { listFuncionario ->
            sucessGetFuncionarios(
                listFuncionario
            )
        }
        apontamentoViewModel.insertApontamento.observe(this) { apontamento ->
            sucessInsertApontamento(
                apontamento
            )
        }
    }

    private val binding get() = _binding!!


    private fun sucessInsertApontamento(apontamento: Boolean?) {
        apontamento?.let {
            if (it) {
                if (index < listOfFuncionario.size) {
                    binding.tvName.text = listOfFuncionario[index].nome
                } else {
                    activity?.showDialog("Apontamentos finalizados!", "Atenção", true)
                }
            }
        }
    }

    private fun sucessGetFuncionarios(listFuncionario: List<Funcionario>?) {
        if (!listFuncionario.isNullOrEmpty()) {

            val list = mutableListOf<Funcionario>()
            listFuncionario.iterator().forEach {
                list.add(it)
            }
            listOfFuncionario = list
            binding.tvName.text = listOfFuncionario[index].nome
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFuncionarioBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        turmaSelected?.let {
            binding.tvTurmaNumber.text = it
            apontamentoViewModel.getAllFuncionario(it.toLong())
        }

        binding.btYes.setOnClickListener {
            setMarmita(true)
        }

        binding.btNot.setOnClickListener {
            setMarmita(false)
        }
    }

    private fun setMarmita(pegou: Boolean) {
        val instance = Calendar.getInstance()
        instance.add(Calendar.DATE, 1)

        if (index <= listOfFuncionario.size) {
            val apontamento = Apontamento(
                listOfFuncionario[index].matricula,
                listOfFuncionario[index].turma?.toLong(),
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(instance.time),
                pegou
            )
            apontamentoViewModel.insertApontamento(apontamento)
            index++
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onLoading(loading: Boolean?) {
        loading?.let {
            if (loading == true) {
                binding.clSecond.visibility = View.GONE
                binding.progressBarApontamento.visibility = View.VISIBLE
            } else {
                binding.progressBarApontamento.visibility = View.GONE
                binding.clSecond.visibility = View.VISIBLE
            }
        }
    }

    private fun onError(error: Event.Error?) {
        if (error == null) return
        else activity?.showDialog("Desculpe não foi possível completar a ação, tente novamente.")
    }
}