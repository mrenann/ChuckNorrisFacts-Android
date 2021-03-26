package com.mrenann.chucknorris_challenge_android.adapter

import android.content.Intent
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mrenann.chucknorris_challenge_android.R
import com.mrenann.chucknorris_challenge_android.databinding.ItemChucknorrisFactBinding
import com.mrenann.chucknorris_challenge_android.model.Fact
import okhttp3.internal.immutableListOf
import java.util.*

class FactsAdapter(
    var factsList: MutableList<Fact> = mutableListOf(),
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

           setupValues(fact)

            itemView.setOnClickListener {
                onClick(fact)
            }

            binding.sharebtn.setOnClickListener {
                shareLink(fact)
            }

        }

        private fun shareLink(fact:Fact){
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${fact.url}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "${fact.value}")
            binding.root.context.startActivity(shareIntent)
        }

        private fun setupValues(fact: Fact){

            binding.tVfunFact.text = fact.value
            fact.value?.let{ valor->

                if(valor.length>80) binding.tVfunFact.textSize = 15F
                else binding.tVfunFact.textSize = 20F
            }

            if (fact.categories?.isEmpty() == false){
                binding.chipCategories.text = fact.categories?.get(0)?.toUpperCase(Locale.ROOT)
            }else{
                binding.chipCategories.text = binding.root.context.getString(R.string.uncategorized)
            }

        }
    }

}