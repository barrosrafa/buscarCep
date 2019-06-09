package com.barros.buscarcep.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.barros.buscarcep.business.CepBusiness
import com.barros.buscarcep.business.CepListener
import com.barros.buscarcep.data.Repository
import com.barros.buscarcep.model.PostalCode
import kotlin.coroutines.coroutineContext

class CepViewModel(private val repository: Repository,
                   application: Application): AndroidViewModel(application), CepListener {

    private var business: CepBusiness = CepBusiness(repository, this)

    private val _postalCode = MutableLiveData<PostalCode>()
    val getPostalCode: LiveData<PostalCode>
    get() = _postalCode
    private fun setPostalCode(postalCode: PostalCode) {
        _postalCode.postValue(postalCode)
    }

    private val _error = MutableLiveData<String>()
    val getError: LiveData<String>
        get() = _error
    private fun setError(error: String) {
        _error.postValue(error)
    }

    override fun searchPostalCode(postalCode: String) {
        business.callApiPostalCode(postalCode)
    }

    override fun responsePostalCode(postalCode: PostalCode) {
        if(postalCode.cep != null && !postalCode.cep.isEmpty()) {
            setPostalCode(postalCode)
        } else {
            setError("CEP não encontrado!")
        }
    }

    override fun errorPostalCode() {
        setError("Ocorreu um problema ao consultar o CEP!")
    }
}