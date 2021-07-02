package com.codingwolf.countryflags.ui.saved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.codingwolf.countryflags.databinding.ItemCountryBinding
import com.codingwolf.countryflags.domain.model.Country

class CountryViewHolder(private val binding: ItemCountryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country) {
        Glide
            .with(binding.root.context)
            .load(country.flagUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.imageViewFlag)

        binding.textViewCountryName.text = country.name
    }

    companion object {
        operator fun invoke(parent: ViewGroup) = CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}