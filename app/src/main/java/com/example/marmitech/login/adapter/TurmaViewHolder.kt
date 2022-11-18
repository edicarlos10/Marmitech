package com.example.marmitech.login.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marmitech.databinding.SpinnerItemBinding

class TurmaViewHolder (
    itemBinding: SpinnerItemBinding
) : RecyclerView.ViewHolder(itemBinding.root){
    var itemName: TextView = itemBinding.text
}