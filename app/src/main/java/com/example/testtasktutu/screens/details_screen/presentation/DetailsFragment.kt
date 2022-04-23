package com.example.testtasktutu.screens.details_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.screens.details_screen.domain.model.RepositoriesInfoDomain
import com.example.testtasktutu.screens.details_screen.presentation.interfaces.DetailsViewModel
import com.example.testtasktutu.screens.details_screen.viewmodel.DetailsViewModelFactory
import com.example.testtasktutu.screens.details_screen.viewmodel.DetailsViewModelImpl
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: DetailsViewModelFactory
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as MyApp).detailsScreenComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[DetailsViewModelImpl::class.java]
        viewModel.infoData.observe(viewLifecycleOwner, observer(view))

        val login = arguments?.getString("login")
        val name = arguments?.getString("name")
        if (login != null && name != null) viewModel.getData(login, name)
    }

    private fun observer(view: View) = Observer<RepositoriesInfoDomain> {
        view.findViewById<TextView>(R.id.repository_name).text = it.name
        view.findViewById<TextView>(R.id.description).text = it.description
        view.findViewById<TextView>(R.id.language).text = it.language
        view.findViewById<TextView>(R.id.stargazers_count).text = it.stargazers_count.toString()
        view.findViewById<TextView>(R.id.updated_at).text = it.updated_at
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}