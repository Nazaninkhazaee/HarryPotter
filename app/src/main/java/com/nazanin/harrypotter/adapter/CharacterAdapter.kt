package com.nazanin.harrypotter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nazanin.harrypotter.databinding.CharacterItemBinding
import com.nazanin.harrypotter.domin.CharacterModel

class CharacterAdapter(val onClickListener: OnClickListener): ListAdapter<CharacterModel,
        CharacterAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterProperty = getItem(position)
        holder.bind(characterProperty)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(characterProperty)
        }
    }

    class OnClickListener(val clickListener: (character: CharacterModel) -> Unit) {
        fun onClick(character: CharacterModel) = clickListener(character)
    }

    class ViewHolder(private var binding: CharacterItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterModel) {
            binding.character = character
            binding.executePendingBindings()
        }
    }


}