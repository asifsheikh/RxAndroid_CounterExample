package com.practise.asifshaikh.mviexample.apple.drivers

import com.practise.asifshaikh.mviexample.apple.AppleState
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.redgreen.oneway.drivers.ViewDriver

class AppleViewDriver(private val view: AppleView) : ViewDriver<AppleState> {
    override fun render(source: Observable<AppleState>): Disposable {
        return source.subscribe { state ->
            view.showCount(state.count)
            view.showPrice(state.price)
            view.showTotalPrice(state.totalPrice)
        }
    }
}
