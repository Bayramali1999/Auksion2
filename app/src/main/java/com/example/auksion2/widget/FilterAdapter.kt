package com.example.auksion2.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.auksion2.R
import com.example.auksion2.listener.LotItemClickListener


class FilterAdapter(
    private val list: MutableList<String>,
    private val listener: LotItemClickListener
) :
    RecyclerView.Adapter<FilterAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_view, parent, false)
        return VH(view, listener)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size


    class VH(itemView: View, private val listener: LotItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun onBind(shortLotBeans: String) {
        }
    }

}