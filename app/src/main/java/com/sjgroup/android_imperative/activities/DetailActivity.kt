package com.sjgroup.android_imperative.activities

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.sjgroup.android_imperative.adapters.EpisodesAdapter
import com.sjgroup.android_imperative.adapters.TVShortAdapter
import com.sjgroup.android_imperative.databinding.ActivityDetailBinding
import com.sjgroup.android_imperative.models.Details
import com.sjgroup.android_imperative.models.Episode
import com.sjgroup.android_imperative.viewModel.DetailsViewModel
import com.sjgroup.android_imperative.utills.Logger
import java.util.*
import kotlin.collections.ArrayList

class DetailActivity : BaseActivity() {
    private val TAG = DetailActivity::class.java.simpleName
    private val viewModel: DetailsViewModel by viewModels()
    lateinit var binding: ActivityDetailBinding
    lateinit var adapter: EpisodesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        initObserves()
        binding.rvShorts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.ivClose.setOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }

        binding.rvEpisodes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val extras  = intent.extras
        val show_id = extras!!.getLong("show_id")
        val show_img = extras!!.getString("show_img")
        val show_name = extras!!.getString("show_name")
        val show_network = extras!!.getString("show_network")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val imageTransactionName = extras.getString("iv_movie")
            binding.ivDetails.transitionName = imageTransactionName
        }

        binding.tvName.text = show_name.toString()
        binding.tvName.text = show_network.toString()
        Glide.with(this).load(show_img).into(binding.ivDetails)

        viewModel.apiTVShowDetails(show_id.toInt())
    }

    private fun initObserves(){
        viewModel.tvShowDetails.observe(this) {
            Logger.d(TAG, it.toString())
            refreshAdapter(it.tvShow.pictures)
            refreshEpisodesAdapter(it.tvShow.episodes)
            binding.tvDetails.text = it.tvShow.description
        }

        viewModel.errorMessage.observe(this) {
            Logger.d(TAG, it.toString())
        }

        viewModel.isLoading.observe(this){
            Logger.d(TAG, it.toString())
            if (it){
                binding.pbLoading.visibility = View.VISIBLE
            }else{
                binding.pbLoading.visibility = View.GONE
            }
        }
    }


    private fun refreshAdapter(items: List<String>) {
        val adapter = TVShortAdapter(this, items)
        binding.rvShorts.adapter = adapter
    }

    private fun refreshEpisodesAdapter(items: List<Episode>){
         adapter = EpisodesAdapter(this, items)
        binding.rvEpisodes.adapter = adapter
    }
}