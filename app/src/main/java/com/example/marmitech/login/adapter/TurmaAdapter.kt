package com.example.marmitech.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.domain.marmitech.appPeople.model.Turma
import com.example.marmitech.databinding.SpinnerItemBinding

class TurmaAdapter(
    private val turmaList: List<Turma>
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: TurmaViewHolder
        val itemBinding: SpinnerItemBinding

        if (convertView == null) {
            itemBinding = SpinnerItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            holder = TurmaViewHolder(itemBinding)
            view = itemBinding.root
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as TurmaViewHolder
        }

        holder.itemName.text = turmaList[position].codigo?.toString() ?: ""

        return view

    }

    override fun getItem(position: Int): Any {
        return turmaList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return turmaList.size
    }
}