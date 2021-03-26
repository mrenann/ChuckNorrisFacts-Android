package com.mrenann.chucknorris_challenge_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrenann.chucknorris_challenge_android.databinding.ItemChucknorrisFactBinding
import com.mrenann.chucknorris_challenge_android.model.Fact

class FactsAdapter (
    var factsList: MutableList<Fact>,
    private val onFactClicked: (Fact?) -> Unit
): RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChucknorrisFactBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return factsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(factsList[position],onFactClicked)
    }

    class ViewHolder(
        private val binding: ItemChucknorrisFactBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: Fact, onClick: (Fact) -> Unit) = with(binding) {

            itemView.setOnClickListener {
                onClick(fact)
            }
        }
    }

}