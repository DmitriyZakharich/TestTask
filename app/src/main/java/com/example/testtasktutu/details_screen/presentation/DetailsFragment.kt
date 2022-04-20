package com.example.testtasktutu.details_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.details_screen.viewmodel.DetailsViewModel
import com.example.testtasktutu.details_screen.viewmodel.DetailsViewModelFactory
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: DetailsViewModelFactory
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as MyApp).detailsScreenComponent.inject(this)

        val repositoryName = view.findViewById<TextView>(R.id.repository_name)
        val description = view.findViewById<TextView>(R.id.description)
        val language = view.findViewById<TextView>(R.id.language)
        val stargazersCount = view.findViewById<TextView>(R.id.stargazers_count)
        val updatedAt = view.findViewById<TextView>(R.id.updated_at)

        viewModel = ViewModelProvider(this, vmFactory)[DetailsViewModel::class.java]

        val login = arguments?.getString("login")
        val name = arguments?.getString("name")

        viewModel.infoData.observe(viewLifecycleOwner){
            repositoryName.text = it.name
            description.text = it.description
            language.text = it.language
            stargazersCount.text = it.stargazers_count.toString()
            updatedAt.text = it.updated_at
        }

        if (login != null && name != null)
            viewModel.getData(login, name)
    }


    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}