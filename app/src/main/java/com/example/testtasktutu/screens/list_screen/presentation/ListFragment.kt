package com.example.testtasktutu.screens.list_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.databinding.FragmentDetailsBinding
import com.example.testtasktutu.databinding.FragmentListBinding
import com.example.testtasktutu.screens.list_screen.presentation.interfaces.ListViewModel
import com.example.testtasktutu.screens.list_screen.viewmodel.ListViewModelImpl
import com.example.testtasktutu.screens.list_screen.viewmodel.ListViewModelFactory
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as MyApp).listScreenComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[ListViewModelImpl::class.java]
        viewModel.adapter.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = it
        }

        binding.searchView.setOnQueryTextListener(onQueryTextListener(binding.searchView))
    }

    private fun onQueryTextListener(searchView: SearchView) =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String) = false

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getAdapter(searchView.query.toString())
                return false
            }
        }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}