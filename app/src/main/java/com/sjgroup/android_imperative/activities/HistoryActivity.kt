package com.sjgroup.android_imperative.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.sjgroup.android_imperative.R
import com.sjgroup.android_imperative.adapters.TVShowAdapter
import com.sjgroup.android_imperative.databinding.ActivityHistoryBinding
import com.sjgroup.android_imperative.databinding.ActivityMainBinding
import com.sjgroup.android_imperative.viewModel.MainViewModel

class HistoryActivity : BaseActivity() {
    private val TAG = HistoryActivity::class.java.simpleName
    val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityHistoryBinding
    lateinit var adapter: TVShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        //viewModel.tvShowFromDb()

    }
}