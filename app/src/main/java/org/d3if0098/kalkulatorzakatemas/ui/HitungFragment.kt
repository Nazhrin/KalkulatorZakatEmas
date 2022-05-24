package org.d3if0098.kalkulatorzakatemas.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.database.zakatDb
import org.d3if0098.kalkulatorzakatemas.databinding.FragmentHitungBinding
import org.d3if0098.kalkulatorzakatemas.ui.hitung.HitungViewModel
import org.d3if0098.kalkulatorzakatemas.ui.hitung.ZakatViewModelFactory
import java.text.NumberFormat
import java.util.*

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding


    private val viewModel:HitungViewModel by lazy {
        val db = zakatDb.getInstance(requireContext())
        val factory = ZakatViewModelFactory(db.dao)
        ViewModelProvider(this,factory)[HitungViewModel::class.java]
    }
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHitungBinding.inflate(layoutInflater,
            container, false)
        setHasOptionsMenu(true)
        return binding.root
//        return inflater.inflate(R.layout.fragment_hitung, container, false)
    }
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.opsi_menu, menu)
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.menu_histori ->{
//                findNavController().navigate(
//                    R.id.action_hitungFragment_to_riwayatFragment
//                )
//                return true
//            }
//            R.id.menu_histori->{
//                findNavController().navigate(
//                    R.id.action_hitungFragment_to_pengertianFragment)
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hitung.setOnClickListener {
            viewModel.hitungZakat(binding.beratemasInput.text.toString())
        }
        viewModel.getHasilZakat.observe(viewLifecycleOwner){
            if (it!=null){
                binding.hasil.text = it
            }
        }
        viewModel.data.observe(viewLifecycleOwner,{
            if(it == null)return@observe
            Log.d("HitungFragment","Data Tersimpan. ID = ${it.id}")
        })
        binding.buttonRiwayat.setOnClickListener {
            findNavController().navigate(R.id.action_hitungFragment_to_riwayatFragment)
        }
    }
}