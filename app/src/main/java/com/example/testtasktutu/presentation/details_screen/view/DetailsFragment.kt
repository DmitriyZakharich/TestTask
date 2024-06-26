package com.example.testtasktutu.presentation.details_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.ChangeBounds
import coil.load
import com.example.testtasktutu.R
import com.example.testtasktutu.databinding.FragmentDetailsBinding
import com.example.testtasktutu.presentation.common.KEY_LOGIN
import com.example.testtasktutu.presentation.details_screen.intent.DetailsIntent
import com.example.testtasktutu.presentation.details_screen.viewstate.DetailsState
import com.example.testtasktutu.presentation.details_screen.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        val login = arguments?.getString(KEY_LOGIN)
        login?.let {
            viewModel.handleIntent(DetailsIntent.FetchDetails(login = it))
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DetailsState.Idle -> {}
                is DetailsState.Loading -> {
                    with(binding) {
                        progressBar.visibility = View.VISIBLE
                        infoLayout.visibility = View.GONE
                    }
                }

                is DetailsState.Details -> {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        infoLayout.visibility = View.VISIBLE
                        avatar.load(it.data.avatarUrl)
                        login.text = it.data.login
                        name.text = it.data.name
                        location.text = it.data.location
                        createdAt.text = it.data.createdAt
                    }
                }

                is DetailsState.NoData -> {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        infoLayout.visibility = View.GONE
                    }
                    Toast.makeText(this.activity, getString(R.string.no_data_available), Toast.LENGTH_LONG).show()
                }
                is DetailsState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this.activity, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}