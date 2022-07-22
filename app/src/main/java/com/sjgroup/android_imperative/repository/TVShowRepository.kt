package com.sjgroup.android_imperative.repository

import com.sjgroup.android_imperative.models.TVShow
import com.sjgroup.android_imperative.network.services.TVShowService
import com.sjgroup.android_imperative.db.TVShowDao
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    private val tvShowService: TVShowService,
    private val tvShowDao: TVShowDao,
) {

    /**
     * Retrofit related
     */

    suspend fun apiTvShowPopular(page: Int) = tvShowService.apiTVShowPopular(page)
    suspend fun apiTvShowDetails(q: Int) = tvShowService.apiTVShowDetails(q)


    /**
     * Retrofit related
     */

    suspend fun getTvShowsFromDB() = tvShowDao.getTvShowsFromDB()
    suspend fun insertTvShowToDB(tvShow: TVShow) = tvShowDao.insertTvShowToDB(tvShow)
    suspend fun deleteTVShowsFromDB() = tvShowDao.deleteTvShowsFromDB()
}