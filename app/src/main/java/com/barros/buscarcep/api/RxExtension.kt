package com.barros.buscarcep.api

import io.reactivex.Single

inline fun <T> singleCallable(crossinline func: () -> T): Single<T> {
    return Single.fromCallable { func.invoke() }.defaultSchedulers()
}