package com.practise.asifshaikh.mviexample.apple

import io.reactivex.Observable
import io.redgreen.oneway.SourceEvent

object AppleModel {
    fun createSource(sourceEvents: Observable<SourceEvent>): Observable<AppleState> {
        return sourceEvents
                .filter { it == SourceEvent.CREATED }
                .map { AppleState.INITIAL }
    }
}
