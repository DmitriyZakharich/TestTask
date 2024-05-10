package com.example.testtasktutu.screens.presentation.list_screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testtasktutu.R
import com.example.testtasktutu.databinding.FragmentListBinding
import com.example.testtasktutu.screens.presentation.list_screen.adapter.CustomRecyclerAdapter
import com.example.testtasktutu.screens.presentation.list_screen.intent.ListIntent
import com.example.testtasktutu.screens.presentation.list_screen.viewmodel.ListViewModel
import com.example.testtasktutu.screens.presentation.list_screen.viewstate.ListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        viewModel.handleIntent(ListIntent.FetchList)
        restoringState()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ListState.Idle -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                is ListState.Loading -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ListState.Repos -> {
                    binding.progressBar.visibility = View.GONE

                    binding.recyclerView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = CustomRecyclerAdapter(it.repos) { bundle ->
                        viewModel.handleIntent(ListIntent.Navigate(bundle))
                    }
                }
                is ListState.Navigate -> {
                    findNavController().navigate(R.id.action_listFragment_to_detailsFragment,
                        it.bundle)
                }
                is ListState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this.activity, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun restoringState() {
        viewModel.handleIntent(ListIntent.RestoreState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}