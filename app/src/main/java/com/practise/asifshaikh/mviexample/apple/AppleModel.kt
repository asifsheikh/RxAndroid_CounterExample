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

        val applePriceStates = intentions
                .ofType(ApplePriceIntention::class.java)
                .map { it.price }
                .withLatestFrom(timeline) { price, state -> state.updatePrice(price) }

        val sourceRestoredStates = sourceEvents
                .filter { it == SourceEvent.RESTORED }
                .withLatestFrom(timeline) { _, state -> state }

        return Observable.mergeArray(
                sourceCreatedStates,
                sourceRestoredStates,
                appleCountStates,
                applePriceStates
        )
    }
}
