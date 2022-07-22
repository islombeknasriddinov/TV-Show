package com.sjgroup.android_imperative.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sjgroup.android_imperative.models.TVShow

@Dao
interface TVShowDao {
    @Query("SELECT * FROM tv_show")
    suspend fun getTvShowsFromDB(): List<TVShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowToDB(tvShow: TVShow)

    @Query("DELETE FROM tv_show ")
    suspend fun deleteTvShowsFromDB()

}
