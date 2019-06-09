package com.barros.buscarcep.business

import com.barros.buscarcep.data.Repository
import com.barros.buscarcep.model.PostalCode

class CepBusiness(
    private val repository: Repository,
    private val businessListener: CepListener
) {

    fun callApiPostalCode(cep: String) {
        repository.getPostalCode(cep).subscribe(
            {
                it?.let { it1 -> onResponse(it1) }
        },
            {onError()})
    }

    private fun onResponse(response: PostalCode) {
        businessListener.responsePostalCode(response)
    }

    private fun onError() {
        businessListener.errorPostalCode()
    }
}
