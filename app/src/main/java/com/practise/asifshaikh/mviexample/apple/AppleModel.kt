package com.practise.asifshaikh.mviexample.apple

import io.reactivex.Observable
import io.reactivex.rxkotlin.withLatestFrom
import io.redgreen.oneway.SourceEvent

object AppleModel {
    fun createSource(
            sourceEvents: Observable<SourceEvent>,
            intentions: Observable<AppleIntention>,
            timeline: Observable<AppleState>
    ): Observable<AppleState> {
        val sourceCreatedStates = sourceEvents
                .filter { it == SourceEvent.CREATED }
                .map { AppleState.INITIAL }

        val appleCountStates = intentions
                .ofType(AppleCountIntention::class.java)
                .map { it.count }
                .withLatestFrom(timeline) { count, state -> state.updateCount(count) }

        return Observable.mergeArray(sourceCreatedStates, appleCountStates)
    }
}
