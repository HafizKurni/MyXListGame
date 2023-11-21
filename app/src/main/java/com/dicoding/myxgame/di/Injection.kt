package com.dicoding.myxgame.di

import android.content.Context
import com.dicoding.myxgame.data.Repository

object Injection {
        fun provideRepository(context: Context): Repository = Repository.getInstance(context)
    }
