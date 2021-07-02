package com.codingwolf.countryflags.ui.flags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.codingwolf.countryflags.R
import com.codingwolf.countryflags.databinding.FragmentFlagsBinding
import com.codingwolf.countryflags.domain.model.Country
import com.codingwolf.countryflags.ui.util.DialogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlagsFragment : Fragment() {

    private var _binding: FragmentFlagsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FlagsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFlagsBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.checkBoxSaved.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed) viewModel.likeCountry(isChecked)
        }
        binding.buttonFind.setOnClickListener {
            viewModel.findCountry(binding.editTextCountry.text.toString())
        }
    }

    private fun initObservers() {
        with(viewModel) {
            viewState.observe(viewLifecycleOwner, ::observeViewState)
        }
    }

    private fun observeViewState(flagsViewState: FlagsViewState) {
        resetViewState()
        when (flagsViewState) {
            FlagsViewState.InvalidCode -> invalidCountryNameState()
            is FlagsViewState.CountryFound -> countryFoundState(flagsViewState.country)
            FlagsViewState.NotFound -> notFoundState()
        }
    }
    private fun invalidCountryNameState() {
        DialogUtil.displayOkDialog(
            requireContext(),
            R.string.invalid_country_code_title,
            R.string.invalid_country_code_message
        )
    }

    private fun notFoundState() {
        DialogUtil.displayOkDialog(
            requireContext(),
            R.string.country_not_found_title,
            R.string.country_not_found_message
        )
    }

    private fun countryFoundState(country: Country) {
        binding.cardViewFlag.visibility = View.VISIBLE

        binding.textViewCountryCode.text = country.name
        binding.checkBoxSaved.isChecked = country.saved

        Glide
            .with(requireContext())
            .load(country.flagUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.imageViewFlag)
    }

    private fun resetViewState() {
        binding.cardViewFlag.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}