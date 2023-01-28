package com.example.testtasktutu.screens.list_screen.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.databinding.FragmentListBinding
import com.example.testtasktutu.screens.list_screen.presentation.adapter.CustomRecyclerAdapter
import com.example.testtasktutu.screens.list_screen.presentation.intent.ListIntent
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.ListViewModel
import com.example.testtasktutu.screens.list_screen.presentation.viewmodel.ListViewModelFactory
import com.example.testtasktutu.screens.list_screen.presentation.viewstate.ListState
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as MyApp).listScreenComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[ListViewModel::class.java]

        observeViewModel()

        binding.searchView.setOnQueryTextListener(onQueryTextListener(binding.searchView))
    }

    private fun onQueryTextListener(searchView: SearchView) =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String) = false

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.handleIntent(ListIntent.FetchUser(searchView.query.toString()))
                return false
            }
        }

    private fun observeViewModel() {
        viewModel.repos.observe(viewLifecycleOwner) {
            when (it) {
                is ListState.Idle -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this.activity, "Нет данных по этому имени", Toast.LENGTH_LONG).show()
                }
                is ListState.Loading -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ListState.Repos -> {
                    binding.progressBar.visibility = View.GONE

                    binding.recyclerView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = CustomRecyclerAdapter(it.repos)
                }
                is ListState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this.activity, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}