package com.practise.asifshaikh.mviexample.counter.usecases

import com.practise.asifshaikh.mviexample.counter.CounterState
import com.practise.asifshaikh.mviexample.counter.DecrementCounterIntention
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.rxkotlin.withLatestFrom

class DecrementUseCase(
        private val timeline: Observable<CounterState>
) : ObservableTransformer<DecrementCounterIntention, CounterState> {
    override fun apply(decrementIntentions: Observable<DecrementCounterIntention>): ObservableSource<CounterState> {
        return decrementIntentions.withLatestFrom(timeline) { _, state -> state.add(-1) }
    }
}
