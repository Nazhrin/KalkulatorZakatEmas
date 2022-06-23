package org.d3if0098.kalkulatorzakatemas.ui.hitung

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.database.ZakatDb
import org.d3if0098.kalkulatorzakatemas.databinding.FragmentHitungBinding

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding


    private val viewModel:HitungViewModel by lazy {
        val db = ZakatDb.getInstance(requireContext())
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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.opsi_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_pengertian) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_pengertianFragment)
            return true
        }
        if(item.itemId == R.id.menu_orang){
            findNavController().navigate(
                R.id.action_hitungFragment_to_orangFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hitung.setOnClickListener {
            validasi()
            viewModel.hitungZakat(binding.beratemasInput.text.toString())
        }
        viewModel.getHasilZakat.observe(viewLifecycleOwner){
            if (it!=null){
                binding.hasil.text = it
            }
        }
        viewModel.data.observe(viewLifecycleOwner,{
            if(it == null)return@observe
        })
        binding.buttonRiwayat.setOnClickListener {
            findNavController().navigate(R.id.action_hitungFragment_to_riwayatFragment)
        }
        binding.reset.setOnClickListener { reset() }
        binding.buttonBagikan.setOnClickListener { bagiData() }
        viewModel.scheduleUpdater(requireActivity().application)


    }
    @SuppressLint("StringFormatMatches")
    private fun bagiData(){
        val messsage = getString(R.string.bagikan,
        binding.beratemasInput.text,
        binding.hasil.text
        )
        val bagiIntent = Intent(Intent.ACTION_SEND)
        bagiIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, messsage)
        if (bagiIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(bagiIntent)
        }
    }
    private fun validasi(){
        if(TextUtils.isEmpty(binding.beratemasInput.text)){
            Toast.makeText(requireActivity(),R.string.validasi_berat, Toast.LENGTH_LONG).show()
            return
        }

    }
    private fun reset(){
        binding.beratemasInput.setText("")
        binding.hasil.setText("Jumlah rupiah yang harus dikeluarkan adalah : ")
    }

}