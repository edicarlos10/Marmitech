package com.example.marmitech.apontamento.fragment

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.domain.marmitech.base.Event
import com.example.marmitech.apontamento.ApontamentoViewModel
import com.example.marmitech.apontamento.adapter.ApontamentoAdapter
import com.example.marmitech.databinding.FragmentConsultaBinding
import com.example.marmitech.extension.OnItemClickListener
import com.example.marmitech.extension.addOnItemClickListener
import com.example.marmitech.extension.showDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

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
        apontamentoViewModel.updateApontamento.observe(this){apontamento -> sucessEdit(apontamento)}
    }

    private fun sucessEdit(edited: Boolean?) {
        edited?.let {
            if(it){
                apontamentoViewModel.getAllApontamento()
            }
        }
    }

    private fun sucessGetApontamentos(listApontamento: List<Apontamento>?) {
        listApontamento?.let {
            binding.rvApontamento.layoutManager = LinearLayoutManager(activity)
            binding.rvApontamento.setHasFixedSize(true)

            val adapter = ApontamentoAdapter(it)
            binding.rvApontamento.adapter = adapter
            binding.rvApontamento.addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    alertToEdit(adapter, position)

                }
            })
        }
    }

    private fun alertToEdit(
        adapter: ApontamentoAdapter,
        position: Int
    ) {
        val instance = Calendar.getInstance()
        instance.add(Calendar.DATE, 1)

        val date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(instance.time)

        var alertDialog: AlertDialog? = null
        try {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Editando")
            builder.setMessage("Você quer alterar a entrega para a matricula?: " + adapter.list[position].matricula)
                .setPositiveButton("Sim") { _, _ ->
                    apontamentoViewModel.updateApontamento(
                        Apontamento(
                            adapter.list[position].matricula,
                            adapter.list[position].turma,
                            date,
                            adapter.list[position].pegou != true
                        )
                    )
                }
                .setNegativeButton("Não") { _, _ ->

                }

            alertDialog = builder.create()
            alertDialog.show()
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "Não foi possível, tente novamente",
                Toast.LENGTH_LONG
            ).show()
        }

        alertDialog?.getButton(DialogInterface.BUTTON_POSITIVE)
            ?.setTextColor(Color.BLUE)
        alertDialog?.getButton(DialogInterface.BUTTON_NEGATIVE)
            ?.setTextColor(Color.BLUE)
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