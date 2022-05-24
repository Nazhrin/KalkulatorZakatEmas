package org.d3if0098.kalkulatorzakatemas.ui.riwayat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.database.ZakatDb
import org.d3if0098.kalkulatorzakatemas.databinding.FragmentRiwayatBinding
import org.d3if0098.kalkulatorzakatemas.ui.hitung.HitungViewModel
import org.d3if0098.kalkulatorzakatemas.ui.hitung.ZakatViewModelFactory

class RiwayatFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatBinding
    private val myAdapter: RiwayatAdapter by lazy {
        RiwayatAdapter(requireContext())
    }

    private val hitungViewModel: HitungViewModel by lazy {
        val db = ZakatDb.getInstance(requireContext())
        val factory = ZakatViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    private val riwayatViewModel: RiwayatViewModel by lazy {
        val db = ZakatDb.getInstance(requireContext())
        val factory = RiwayatViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[RiwayatViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRiwayatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            this.adapter = myAdapter
        }
        riwayatViewModel.deleted.observe(viewLifecycleOwner){
            if(it == true){
                Toast.makeText(requireContext(), "Data terhapus", Toast.LENGTH_SHORT).show()
            }
        }
        myAdapter.onButtonDeleteClick = {data->
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(R.string.konfirmasi_hapus)
                .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                    riwayatViewModel.hapusData(data)
                }
                .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()

        }

        hitungViewModel.data.observe(viewLifecycleOwner, {
            Log.d("RiwayatFragment", it.toString())
            myAdapter.submitList(it)
        })
    }
}
