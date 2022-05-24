package org.d3if0098.kalkulatorzakatemas.ui.riwayat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.database.ZakatEntity
import org.d3if0098.kalkulatorzakatemas.databinding.ListRiwayatBinding
import java.text.SimpleDateFormat
import java.util.*

class RiwayatAdapter(private val ctx: Context) :
    ListAdapter<ZakatEntity, RiwayatAdapter.ViewHolder>(DIFF_CALLBACK) {

    lateinit var onButtonDeleteClick: ((ZakatEntity) -> Unit)

        companion object {
            private val DIFF_CALLBACK =
                object : DiffUtil.ItemCallback<ZakatEntity>() {
                    override fun areItemsTheSame(
                        oldData: ZakatEntity, newData: ZakatEntity
                    ): Boolean {
                        return oldData.id == newData.id
                    }
                    override fun areContentsTheSame(
                        oldData: ZakatEntity, newData: ZakatEntity
                    ): Boolean {
                        return oldData == newData
                    }
                }
        }
        inner class ViewHolder(
            private val binding: ListRiwayatBinding
        ) : RecyclerView.ViewHolder(binding.root){
            private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
                Locale("id", "ID"))
            fun bind (item: ZakatEntity) = with(binding){
                tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
                uangZakatTextView.text = ctx.getString(R.string.zakat_args,item.uang)
                beratEmasTextView.text = ctx.getString(R.string.berat_args_1_s,item.berat)
                buttonHapus.setOnClickListener {
                    onButtonDeleteClick.invoke(item)
                }

                Log.d("Riwayat Adapter: ",item.berat)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRiwayatBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
