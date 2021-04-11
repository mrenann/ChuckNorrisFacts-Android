package com.mrenann.chucknorris_challenge_android.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrenann.chucknorris_challenge_android.R
import com.mrenann.chucknorris_challenge_android.databinding.ItemChucknorrisFactBinding
import com.mrenann.chucknorris_challenge_android.model.Fact
import java.util.*

class FactsAdapter(
    val onShareClicked: (Fact?) -> Unit,
    var factsList: MutableList<Fact> = mutableListOf()
) : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChucknorrisFactBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return factsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(factsList[position],onShareClicked)
    }

    class ViewHolder(
        private val binding: ItemChucknorrisFactBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: Fact,onShareClicked: (Fact?) -> Unit) = with(binding) {

            setupValues(fact)
            binding.sharebtn.setOnClickListener { onShareClicked(fact) }

        }

        private fun setupValues(fact: Fact) {
            binding.apply {
                tVfunFact.text = fact.value

                fact.value?.let { valor ->
                    if (valor.length > 80)
                        tVfunFact.textSize = 15F
                    else
                        tVfunFact.textSize = 20F
                }

                if (fact.categories?.isEmpty() == false)
                    chipCategories.text = fact.categories[0].toUpperCase(Locale.ROOT)
                else
                    chipCategories.text = root.context.getString(R.string.uncategorized)
            }



        }
    }

}