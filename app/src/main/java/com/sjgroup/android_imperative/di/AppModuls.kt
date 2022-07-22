package com.sjgroup.android_imperative.di

import android.app.Application
import com.sjgroup.android_imperative.network.Server.IS_TESTER
import com.sjgroup.android_imperative.network.Server.SERVER_DEVELOPMEnT
import com.sjgroup.android_imperative.network.Server.SERVER_PRODUCTION
import com.sjgroup.android_imperative.network.services.TVShowService
import com.sjgroup.android_imperative.db.AppDatabase
import com.sjgroup.android_imperative.db.TVShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModuls {

    /**
     * Retrofit related
     */

    @Provides
    fun server():String{
        if (IS_TESTER) return SERVER_DEVELOPMEnT
        return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun retrofitClient():Retrofit{
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun tvShowService():TVShowService{
        return retrofitClient().create(TVShowService::class.java)
    }



    /**
     * Room related
     */

    @Provides
    @Singleton
    fun appDatabase(context: Application): AppDatabase{
        return AppDatabase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun dao(appDatabase: AppDatabase): TVShowDao{
        return appDatabase.getTvShowDao()
    }

}