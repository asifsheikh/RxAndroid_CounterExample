package com.practise.asifshaikh.mviexample.counter.drivers

import com.practise.asifshaikh.mviexample.counter.CounterState
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.redgreen.oneway.drivers.ViewDriver

class CounterViewDriver(private val view: CounterView) : ViewDriver<CounterState> {
    override fun render(source: Observable<CounterState>): Disposable {
        return source.subscribe {
            state ->
            view.showCounter(state.counter)
            view.showClicks(state.clicks)
        }
    }
}
