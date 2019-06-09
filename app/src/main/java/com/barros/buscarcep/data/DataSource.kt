package com.barros.buscarcep.data

import com.barros.buscarcep.model.PostalCode
import io.reactivex.Single

interface DataSource {
    fun getPostalCode(cep: String): Single<PostalCode?>
}