package com.sjgroup.android_imperative.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sjgroup.android_imperative.R
import com.sjgroup.android_imperative.activities.MainActivity
import com.sjgroup.android_imperative.databinding.ItemTvShowBinding
import com.sjgroup.android_imperative.models.TVShow

class TVShowAdapter(var activity: MainActivity, var items: ArrayList<TVShow>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show,parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val tvShow : TVShow = items[position]
        if (holder is TvShowViewHolder){
            Glide.with(activity).load(tvShow.image_thumbnail_path).into(holder.binding.ivMovie)
            holder.binding.tvName.text = tvShow.name
            holder.binding.tvType.text = tvShow.network

            // click image
            ViewCompat.setTransitionName(holder.binding.ivMovie, tvShow.name)
            holder.binding.ivMovie.setOnClickListener {
                //Save TvShow into room
                activity.viewModel.insertTvShowDB(tvShow)
                //Call Details Activity
                activity.callDetailsActivity(tvShow, holder.binding.ivMovie)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TvShowViewHolder(view :View):RecyclerView.ViewHolder(view){
        val binding = ItemTvShowBinding.bind(view)
    }

    fun setNewTVShows(tvShows: ArrayList<TVShow>){
        items.addAll(tvShows)
        notifyDataSetChanged()
    }
}