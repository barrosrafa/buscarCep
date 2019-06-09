package com.barros.buscarcep.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.barros.buscarcep.business.CepBusiness
import com.barros.buscarcep.business.CepListener
import com.barros.buscarcep.data.Repository
import com.barros.buscarcep.model.PostalCode

class CepViewModel(private val repository: Repository,
                   application: Application): AndroidViewModel(application), CepListener {

    internal var business: CepBusiness = CepBusiness(repository, this)

    private val _postalCode = MutableLiveData<PostalCode>()
    val getPostalCode: LiveData<PostalCode>
    get() = _postalCode
    private fun setPostalCode(postalCode: PostalCode) {
        _postalCode.postValue(postalCode)
    }

    override fun searchPostalCode(postalCode: String) {
        business.callApiPostalCode(postalCode)
    }

    override fun responsePostalCode(postalCode: PostalCode) {
        setPostalCode(postalCode)
    }
}