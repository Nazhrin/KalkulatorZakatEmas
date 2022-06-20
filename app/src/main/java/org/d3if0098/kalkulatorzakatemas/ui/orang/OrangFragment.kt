package org.d3if0098.kalkulatorzakatemas.ui.orang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.databinding.FragmentOrang2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrangFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrangFragment : Fragment() {
    private lateinit var viewModel: OrangViewModel


    private lateinit var binding:FragmentOrang2Binding
    private lateinit var myAdapter: OrangAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter = OrangAdapter()
        viewModel = ViewModelProvider(this).get(OrangViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrang2Binding.inflate(layoutInflater, container, false)
        binding.recyclerView.adapter = myAdapter
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}