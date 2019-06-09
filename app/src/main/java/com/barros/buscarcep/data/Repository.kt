package com.barros.buscarcep.data

import com.barros.buscarcep.model.PostalCode
import io.reactivex.Single

open class Repository(private val dataSource: DataSource): DataSource {
    override fun getPostalCode(cep: String): Single<PostalCode?> {
        return dataSource.getPostalCode(cep)
    }

    companion object {
        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: DataSource) =
                INSTANCE?: synchronized(Repository::class.java) {
                    INSTANCE?: Repository(remoteDataSource).also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
