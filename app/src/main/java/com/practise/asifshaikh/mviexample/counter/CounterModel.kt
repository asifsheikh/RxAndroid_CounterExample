package com.practise.asifshaikh.mviexample.counter

import com.practise.asifshaikh.mviexample.counter.usecases.DecrementUseCase
import com.practise.asifshaikh.mviexample.counter.usecases.IncrementUseCase
import io.reactivex.Observable
import io.reactivex.Observable.mergeArray
import io.redgreen.oneway.SourceEvent
import io.redgreen.oneway.usecases.SourceCreatedUseCase
import io.redgreen.oneway.usecases.SourceRestoredUseCase

object CounterModel {
    fun createSource(
            sourceEvents: Observable<SourceEvent>,
            intentions: Observable<CounterIntention>,
            timeline: Observable<CounterState>
    ): Observable<CounterState> {
        /*
        val sourceCreatedStates = sourceEvents
                .filter { it == SourceEvent.CREATED }
                .map { CounterState.ZERO }

        val sourceRestoredState = sourceEvents
                .filter { it == SourceEvent.RESTORED }
                .withLatestFrom(timeline) { _, state -> state }

        val incrementStates = intentions
                .ofType(IncrementCounterIntention::class.java)
                .withLatestFrom(timeline) { _, state -> state.add(1) }

        val decrementStates = intentions
                .ofType(DecrementCounterIntention::class.java)
                .withLatestFrom(timeline) { _, state -> state.add(-1) }
        */

        return mergeArray(
                sourceEvents.compose(SourceCreatedUseCase(CounterState.ZERO)),
                sourceEvents.compose(SourceRestoredUseCase(timeline)),
                intentions.ofType(IncrementCounterIntention::class.java).compose(IncrementUseCase(timeline)),
                intentions.ofType(DecrementCounterIntention::class.java).compose(DecrementUseCase(timeline))
        )
    }
}
