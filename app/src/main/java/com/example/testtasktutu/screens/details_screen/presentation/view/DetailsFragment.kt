package com.example.testtasktutu.screens.details_screen.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.databinding.FragmentDetailsBinding
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.details_screen.presentation.intent.DetailsIntent
import com.example.testtasktutu.screens.details_screen.presentation.viewstate.DetailsState
import com.example.testtasktutu.screens.details_screen.viewmodel.DetailsViewModel
import com.example.testtasktutu.screens.details_screen.viewmodel.DetailsViewModelFactory
import com.example.testtasktutu.screens.list_screen.presentation.adapter.CustomRecyclerAdapter
import com.example.testtasktutu.screens.list_screen.presentation.intent.ListIntent
import com.example.testtasktutu.screens.list_screen.presentation.viewstate.ListState
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: DetailsViewModelFactory
    private lateinit var viewModel: DetailsViewModel

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as MyApp).detailsScreenComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[DetailsViewModel::class.java]
//        viewModel.info.observe(viewLifecycleOwner, observer())

        observeViewModel()

        val login = arguments?.getString("login")
        val name = arguments?.getString("name")
        if (login != null && name != null)
            viewModel.handleIntent(DetailsIntent.FetchDetails(login = login, name = name))
    }

    private fun observeViewModel() {
        viewModel.info.observe(viewLifecycleOwner) {
            when (it) {
                is DetailsState.Idle -> {}
                is DetailsState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.infoLayout.visibility = View.GONE
                }
                is DetailsState.Details -> {
                    binding.progressBar.visibility = View.GONE
                    binding.infoLayout.visibility = View.VISIBLE

                    binding.repositoryName.text = it.data.name
                    binding.description.text = it.data.description
                    binding.language.text = it.data.language
                    binding.stargazersCount.text = it.data.stargazers_count.toString()
                    binding.updatedAt.text = it.data.updated_at

                }
                is DetailsState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this.activity, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}