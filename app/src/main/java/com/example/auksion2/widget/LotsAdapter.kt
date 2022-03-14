package com.example.auksion2.widget

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.auksion2.R
import com.example.auksion2.data.ShortLotBeans
import com.example.auksion2.listener.LotItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*


class LotsAdapter(
    private val list: MutableList<ShortLotBeans>,
    private val listener: LotItemClickListener
) :
    RecyclerView.Adapter<LotsAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return VH(view, listener)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData() {
        notifyDataSetChanged()
    }


    class VH(itemView: View, private val listener: LotItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun onBind(shortLotBeans: ShortLotBeans) {
            val imageUrl = getImageUrl(shortLotBeans.file_hash)
            Picasso.get().load(imageUrl).resize(100, 100).centerCrop().into(itemView.item_image)
            itemView.tv_name_please.text = shortLotBeans.name
            itemView.tv_id.text = "№ ${shortLotBeans.lot_number}"
            val sp = shortLotBeans.start_price.let { it2 ->
                if (it2 == null) return@let "0.0"
                Math.round(it2).toInt().toString()
            }
            itemView.tv_coast_starter.text = "Boshlang'ich narxi\n${sp}UZS"
            val zs = shortLotBeans.zaklad_summa.let {
                if (it == null) return@let "0.0"
                Math.round(it).toInt().toString()
            }
            itemView.tv_coast.text = "Zaklad pulini miqdori\n${zs}"
            itemView.setOnClickListener {
                listener.itemClicked(shortLotBeans)
            }
        }

        private fun getImageUrl(fileHash: String): String =
            "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=$fileHash&from_mobile=1"
    }
}