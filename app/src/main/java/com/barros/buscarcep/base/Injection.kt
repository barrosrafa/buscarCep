package com.barros.buscarcep.base

import android.content.Context
import com.barros.buscarcep.data.RemoteDataSource
import com.barros.buscarcep.data.Repository

object Injection {

    fun provideRepository(context: Context): Repository {
        return Repository.getInstance(RemoteDataSource)
    }
}