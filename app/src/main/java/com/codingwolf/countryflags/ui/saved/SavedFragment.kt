package com.codingwolf.countryflags.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwolf.countryflags.databinding.FragmentSavedBinding
import com.codingwolf.countryflags.ui.saved.adapter.CountryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by viewModel()

    private lateinit var adapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSavedBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSearchView()
        initObservers()
    }

    private fun initRecyclerView() {
        adapter = CountryAdapter(mutableListOf())
        binding.recyclerViewCountries.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCountries.adapter = adapter
    }

    private fun initSearchView() {
        binding.searchViewFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let(viewModel::searchCountry)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean =
                if (newText.isNullOrBlank()) {
                    viewModel.loadSavedCountries()
                    true
                } else {
                    false
                }

        })
    }

    private fun initObservers() {
        viewModel.countries.observe(viewLifecycleOwner, adapter::updateItems)

        viewModel.loadSavedCountries()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
