package com.example.testtasktutu.details_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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


        val viewModel = ViewModelProvider(this, vmFactory)[DetailsViewModel::class.java]

        val name = arguments?.getString("name")
        name?.let { viewModel.getData(it) }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}