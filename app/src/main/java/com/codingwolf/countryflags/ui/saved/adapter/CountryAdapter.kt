package com.codingwolf.countryflags.ui.saved.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingwolf.countryflags.domain.model.Country

class CountryAdapter(private val items: MutableList<Country>) :
    RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(parent)

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun updateItems(countries: List<Country>) {
        items.clear()
        items.addAll(countries)
        notifyDataSetChanged()
    }
}
