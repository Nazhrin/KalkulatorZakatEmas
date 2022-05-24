package org.d3if0098.kalkulatorzakatemas.ui.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if0098.kalkulatorzakatemas.databinding.FragmentRiwayatBinding

class riwayatFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRiwayatBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}