package com.sjgroup.android_imperative.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.sjgroup.android_imperative.adapters.HistoryAdapter
import com.sjgroup.android_imperative.adapters.TVShowAdapter
import com.sjgroup.android_imperative.databinding.ActivityHistoryBinding
import com.sjgroup.android_imperative.models.TVShow
import com.sjgroup.android_imperative.utills.Logger
import com.sjgroup.android_imperative.viewModel.MainViewModel

class HistoryActivity : BaseActivity() {
    private val TAG = HistoryActivity::class.java.simpleName
    val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityHistoryBinding
    lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        viewModel.getTvShowsFromDB()
        refreshAdapter(ArrayList())
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvHistory.layoutManager = gridLayoutManager


        initObserves()
    }

    private fun refreshAdapter(items: ArrayList<TVShow>){
        adapter = HistoryAdapter(this, items)
        binding.rvHistory.adapter = adapter
    }

    private fun initObserves() {
        // Retrofit
//        viewModel.tvShowsFromApi.observe(viewLifecycleOwner) {
//            Logger.d(TAG, it.size.toString())
//            adapterTvShowAdapter.submitList(it)
//        }
        viewModel.errorMessage.observe(this) {
            Logger.d(TAG, it.toString())
        }
        viewModel.isLoading.observe(this) {
            Logger.d(TAG, it.toString())
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }

        // Room

        viewModel.tvShowFromDb.observe(this){
            if (it.size > 0){
                binding.tvEmptyMessage.visibility = View.GONE
            } else {
                binding.tvEmptyMessage.visibility = View.VISIBLE
            }
            adapter.setNewTVShows(it)
        }
    }

    fun callDetailsActivity(tvShow: TVShow, sharedImageView: ImageView) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("show_id", tvShow.id)
        intent.putExtra("show_img", tvShow.image_thumbnail_path)
        intent.putExtra("show_name", tvShow.name)
        intent.putExtra("show_network", tvShow.network)
        intent.putExtra("iv_movie", ViewCompat.getTransitionName(sharedImageView))

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, sharedImageView, ViewCompat.getTransitionName(sharedImageView)!!
        )
        startActivity(intent, options.toBundle())
    }
}