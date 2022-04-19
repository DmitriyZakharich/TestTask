package com.example.testtasktutu.list_screen.presentation

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
import com.example.testtasktutu.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.list_screen.presentation.interfaces.ListViewModelInterface
import com.example.testtasktutu.list_screen.viewmodel.ListViewModel
import com.example.testtasktutu.list_screen.viewmodel.ListViewModelFactory
import java.util.*
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ListViewModelFactory
    private lateinit var viewModel: ListViewModelInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val searchView = view.findViewById<SearchView>(R.id.search_view)

        (requireContext().applicationContext as MyApp).listScreenComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory)[ListViewModel::class.java]

        viewModel.adapter.observe(viewLifecycleOwner) {
            recyclerView.adapter = it
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getAdapter(searchView.query.toString()) {
                    val bundle = Bundle()
                    bundle.putString("name", it.name)
                    requireActivity().findNavController(viewId = R.id.nav_host_fragment)
                        .navigate(resId = R.id.action_listFragment_to_detailsFragment,
                            args = bundle)
                }
                return false
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}