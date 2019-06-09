package com.barros.buscarcep.business

import com.barros.buscarcep.model.PostalCode

interface CepListener {

    fun searchPostalCode(cep: String)

    fun responsePostalCode(postalCode: PostalCode)
}
