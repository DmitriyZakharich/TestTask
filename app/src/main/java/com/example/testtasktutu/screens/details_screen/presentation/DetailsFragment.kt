package com.example.testtasktutu.screens.details_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.databinding.FragmentDetailsBinding
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.details_screen.presentation.interfaces.DetailsViewModel
import com.example.testtasktutu.screens.details_screen.viewmodel.DetailsViewModelFactory
import com.example.testtasktutu.screens.details_screen.viewmodel.DetailsViewModelImpl
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
        viewModel = ViewModelProvider(this, vmFactory)[DetailsViewModelImpl::class.java]
        viewModel.info.observe(viewLifecycleOwner, observer())

        val login = arguments?.getString("login")
        val name = arguments?.getString("name")
        if (login != null && name != null) viewModel.getData(login, name)
    }

    private fun observer() = Observer<GithubDetailRepoInfoDomain> {
        binding.progressBar.visibility = View.GONE

        binding.repositoryName.text = it.name
        binding.description.text = it.description
        binding.language.text = it.language
        binding.stargazersCount.text = it.stargazers_count.toString()
        binding.updatedAt.text = it.updated_at

        binding.repositoryName.visibility = View.VISIBLE
        binding.description.visibility = View.VISIBLE
        binding.language.visibility = View.VISIBLE
        binding.stargazersCount.visibility = View.VISIBLE
        binding.updatedAt.visibility = View.VISIBLE
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