package com.example.marmitech.apontamento.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.marmitech.appPeople.model.Apontamento
import com.example.marmitech.databinding.ConsultaItemBinding

class ApontamentoAdapter (val list: List<Apontamento>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ConsultaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApontamentoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = (holder as ApontamentoViewHolder)
        val item = list[position]

        view.tvMatricula.text = item.matricula.toString()
        view.tvTurma.text = item.turma.toString()
        view.tvData.text = item.date

        if(item.pegou == true){
            view.tvPegouMarmita.text = "Sim"
        } else {
            view.tvPegouMarmita.text = "Não"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}