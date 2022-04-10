package com.example.testtasktutu.list_screen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.list_screen.presentation.interfaces.ListViewModelInterface
import com.example.testtasktutu.list_screen.viewmodel.ListViewModel
import com.example.testtasktutu.list_screen.viewmodel.ListViewModelFactory
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ListViewModelFactory
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val searchView = view.findViewById<SearchView>(R.id.search_view)

        (requireContext().applicationContext as MyApp).appComponent.inject(this)

        val viewModel: ListViewModelInterface =
            ViewModelProvider(this, vmFactory)[ListViewModel::class.java]

//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentContainerView, DetailsFragment(it.repositoryName))
//            .disallowAddToBackStack()
//            .commit()

        viewModel.adapterliveData.observe(viewLifecycleOwner) {
            recyclerView.adapter = it
        }

//        AppDatabase().loadData("1")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getAdapter(searchView.query.toString()) {
                    Toast.makeText(
                        requireContext(),
                        if (!it.description.isNullOrEmpty()) it.description else "Нет описания",
                        Toast.LENGTH_SHORT
                    ).show()
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