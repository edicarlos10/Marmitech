package com.example.marmitech.apontamento.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.base.Event
import com.example.marmitech.apontamento.ApontamentoViewModel
import com.example.marmitech.apontamento.adapter.ApontamentoAdapter
import com.example.marmitech.databinding.FragmentConsultaBinding
import com.example.marmitech.extension.showDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConsultaFragment : Fragment() {

    companion object {
        fun newInstance() =
            ConsultaFragment().apply {
                val args = Bundle().apply { }
                val fragment = ConsultaFragment()
                fragment.arguments = args
                return fragment
            }
    }

    private var _binding: FragmentConsultaBinding? = null
    private val binding get() = _binding!!
    private val apontamentoViewModel: ApontamentoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apontamentoViewModel.loading.observe(this) { onLoading(it) }
        apontamentoViewModel.error.observe(this) { onError(it) }
        apontamentoViewModel.allApontamento.observe(this) { listApontamento ->
            sucessGetApontamentos(
                listApontamento
            )
        }
    }

    private fun sucessGetApontamentos(listApontamento: List<Apontamento>?) {
        listApontamento?.let {
            binding.rvApontamento.layoutManager = LinearLayoutManager(activity)
            binding.rvApontamento.setHasFixedSize(true)

            val adapter = ApontamentoAdapter(it)
            binding.rvApontamento.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsultaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apontamentoViewModel.getAllApontamento()
    }

    private fun onLoading(loading: Boolean?) {
        loading?.let {
            if (loading == true) {
                binding.clSecond.visibility = View.GONE
                binding.progressBarConsulta.visibility = View.VISIBLE
            } else {
                binding.progressBarConsulta.visibility = View.GONE
                binding.clSecond.visibility = View.VISIBLE
            }
        }
    }

    private fun onError(error: Event.Error?) {
        if (error == null) return
        else activity?.showDialog("Desculpe não foi possível completar a ação, tente novamente.")
    }
}