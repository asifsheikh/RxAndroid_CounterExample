package com.practise.asifshaikh.mviexample.counter.usecases

import com.practise.asifshaikh.mviexample.counter.CounterState
import com.practise.asifshaikh.mviexample.counter.IncrementCounterIntention
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.rxkotlin.withLatestFrom

class IncrementUseCase(
        private val timeline: Observable<CounterState>
) : ObservableTransformer<IncrementCounterIntention, CounterState> {
    override fun apply(incrementIntentions: Observable<IncrementCounterIntention>): ObservableSource<CounterState> {
        return incrementIntentions.withLatestFrom(timeline) { _, state -> state.add(1) }
    }
}
