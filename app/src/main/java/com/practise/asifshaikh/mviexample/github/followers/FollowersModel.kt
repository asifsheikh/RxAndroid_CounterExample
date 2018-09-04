package com.practise.asifshaikh.mviexample.github.followers

import io.reactivex.Observable
import io.redgreen.oneway.SourceEvent
import io.redgreen.oneway.usecases.SourceCreatedUseCase

object FollowersModel {
    fun createSource(sourceEvents: Observable<SourceEvent>): Observable<FollowersState> {
        return sourceEvents.compose(SourceCreatedUseCase(FollowersState.INITIAL))
    }
}
