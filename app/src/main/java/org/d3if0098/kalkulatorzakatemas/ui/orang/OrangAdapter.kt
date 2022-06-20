package org.d3if0098.kalkulatorzakatemas.ui.orang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if0098.kalkulatorzakatemas.data.model.Orang
import org.d3if0098.kalkulatorzakatemas.databinding.ListOrangBinding

class OrangAdapter : RecyclerView.Adapter<OrangAdapter.ViewHolder>(){
    private val data = mutableListOf<Orang>()

    fun updateData(newData: List<Orang>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ListOrangBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(orang: Orang) = with(binding){
            tittle.text = orang.kategori
            //ieuteh keur ngaubah anu di fragment
            //ngke nyien modelna
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListOrangBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}