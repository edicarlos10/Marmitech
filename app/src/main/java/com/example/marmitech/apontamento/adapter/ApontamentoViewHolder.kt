package com.example.marmitech.apontamento.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marmitech.databinding.ConsultaItemBinding

class ApontamentoViewHolder(binding: ConsultaItemBinding) : RecyclerView.ViewHolder(binding.root) {
    var tvMatricula: TextView = binding.tvMatricula
    var tvTurma: TextView = binding.tvTuma
    var tvData: TextView = binding.tvData
    var tvPegouMarmita: TextView = binding.tvPegouMarmita
}