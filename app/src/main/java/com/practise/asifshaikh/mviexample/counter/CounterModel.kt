package com.practise.asifshaikh.mviexample.counter

import io.reactivex.Observable
import io.reactivex.rxkotlin.withLatestFrom
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
        */
        val sourceCreatedStates = sourceEvents.compose(SourceCreatedUseCase(CounterState.ZERO))
        val sourceRestoredStates = sourceEvents.compose(SourceRestoredUseCase(timeline))

        val incrementStates = intentions
                .ofType(IncrementCounterIntention::class.java)
                .withLatestFrom(timeline) { _, state -> state.add(1) }

        val decrementStates = intentions
                .ofType(DecrementCounterIntention::class.java)
                .withLatestFrom(timeline) { _, state -> state.add(-1) }

        return Observable.mergeArray(
                sourceCreatedStates,
                sourceRestoredStates,
                incrementStates,
                decrementStates
        )
    }
}
