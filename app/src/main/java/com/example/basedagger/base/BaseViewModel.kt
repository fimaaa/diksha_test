package com.example.basedagger.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>() : ViewModel() {

    private var mNavigator: WeakReference<N>? = null
    private val mCompositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

    var compositeDisposable = mCompositeDisposable

    var navigator: N?
        get() {
            return mNavigator?.get()
        }
        set(value) {
            mNavigator = WeakReference<N>(value)
        }
}