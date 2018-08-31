package com.practise.asifshaikh.mviexample.counter

import io.reactivex.Observable
import io.redgreen.oneway.IntentionsGroup

class CounterIntentionsGroup(
        private val incrementClicks: Observable<Unit>,
        private val decrementClicks: Observable<Unit>
) : IntentionsGroup<CounterIntention> {
    override fun intentions(): Observable<CounterIntention> {
        return Observable.merge(
                increment().share(),
                decrement().share()
        )
    }

    private fun increment(): Observable<IncrementCounterIntention> =
            incrementClicks.map { IncrementCounterIntention }

    private fun decrement(): Observable<DecrementCounterIntention> =
            decrementClicks.map { DecrementCounterIntention }
}
