package com.sjgroup.android_imperative.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sjgroup.android_imperative.R
import com.sjgroup.android_imperative.activities.DetailActivity
import com.sjgroup.android_imperative.databinding.ItemEpisodeBinding
import com.sjgroup.android_imperative.models.Episode
import com.sjgroup.android_imperative.models.TVShow

class EpisodesAdapter(var activity: DetailActivity, var items: List<Episode>): BaseAdapter(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode,parent, false)
        return EpisodesVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val tvShow = items[position]
        if (holder is EpisodesVH){

            holder.binding.tvEpisodeName.text = "Episode ${tvShow.episode}: ${tvShow.name}"
            holder.binding.tvEpisodeAirDate.text = "Air Date: ${tvShow.airDate}"



        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class EpisodesVH(view :View):RecyclerView.ViewHolder(view){
        val binding = ItemEpisodeBinding.bind(view)
    }

}