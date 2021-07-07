package com.cvitirinyu.codingchallenge.starwars.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.databinding.ListItemMissionBinding

/**
 * Adapter for the [RecyclerView] in [MissionListFragment].
 */
class MissionAdapter: ListAdapter<StarWarsMission, RecyclerView.ViewHolder>(MissionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MissionViewHolder(
            ListItemMissionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mission = getItem(position)
        (holder as MissionViewHolder).bind(mission)
    }

    class MissionViewHolder(
        private val binding: ListItemMissionBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
               navigateToMissionDetails()
            }
        }

        fun navigateToMissionDetails() {
            // TODO
        }

        fun bind(item: StarWarsMission) {
            binding.apply {
                mission = item
                executePendingBindings()
            }
        }
    }
}

private class MissionDiffCallback : DiffUtil.ItemCallback<StarWarsMission>() {

    override fun areItemsTheSame(oldItem: StarWarsMission, newItem: StarWarsMission): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StarWarsMission, newItem: StarWarsMission): Boolean {
        return oldItem == newItem
    }
}