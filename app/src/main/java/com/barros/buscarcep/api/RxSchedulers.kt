package com.barros.buscarcep.api

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun ui() = AndroidSchedulers.mainThread()

fun <T> Single<T>.defaultSchedulers() = this.subscribeOn(Schedulers.io()).observeOn(ui())