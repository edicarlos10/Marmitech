package com.example.marmitech.apontamento.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.marmitech.appPeople.model.Funcionario
import com.example.domain.marmitech.base.Event
import com.example.marmitech.apontamento.ApontamentoViewModel
import com.example.marmitech.apontamento.activity.FuncionarioActivity.Companion.TURMA_SELECTED
import com.example.marmitech.databinding.FragmentFuncionarioBinding
import com.example.marmitech.extension.showDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class FuncionarioFragment : Fragment() {
    companion object {
        fun newInstance(turma: String): FuncionarioFragment {
            val args = Bundle().apply { }
            args.putSerializable(TURMA_SELECTED, turma)
            val fragment = FuncionarioFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentFuncionarioBinding? = null
    private val apontamentoViewModel: ApontamentoViewModel by viewModel()
    private var listOfFuncionario: Funcionario? = null
    private var turmaSelected: String? = null

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        arguments?.let {
            turmaSelected = it.getString(TURMA_SELECTED) ?: ""
        }

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
        TODO("Not yet implemented")
    }

    private fun sucessGetFuncionarios(listFuncionario: List<Funcionario>?) {
        TODO("Not yet implemented")
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
        }

        binding.btYes.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btYes.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
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
        else context?.showDialog("Desculpe não foi possível completar a ação, tente novamente.")
    }
}