package com.example.auksion2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.auksion2.R
import com.example.auksion2.data.ShortLotBeans
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*


class LotsAdapter(private val list: MutableList<ShortLotBeans>) :
    RecyclerView.Adapter<LotsAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData() {
        notifyDataSetChanged()
    }


    class VH(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun onBind(shortLotBeans: ShortLotBeans) {
            val imageUrl = getImageUrl(shortLotBeans.file_hash)
            Picasso.get().load(imageUrl).resize(100, 100).centerCrop().into(itemView.item_image);
            itemView.tv_name_please.text = shortLotBeans.name
            itemView.tv_id.text = "№ ${shortLotBeans.lot_number}"
            itemView.tv_coast.text = "Zaklad pulini miqdori\n${shortLotBeans.zaklad_summa}"
            itemView.tv_coast_starter.text = "Boshlang'ich narxi\n${shortLotBeans.start_price} UZS"
        }

        private fun getImageUrl(fileHash: String): String =
            "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=" + fileHash + "&from_mobile=1"


    }
}