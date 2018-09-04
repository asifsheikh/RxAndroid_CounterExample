package com.practise.asifshaikh.mviexample.github.followers

import io.reactivex.Observable
import io.redgreen.oneway.SourceEvent
import io.redgreen.oneway.usecases.SourceCreatedUseCase

object FollowersModel {
    fun createSource(
            sourceEvents: Observable<SourceEvent>,
            intentions: Observable<FollowersIntention>,
            gitHubApi: GitHubApi
    ): Observable<FollowersState> {
        val sourceCreatedStates = sourceEvents.compose(SourceCreatedUseCase(FollowersState.INITIAL))

        val fetchFollowersStates = intentions
                .ofType(FetchIntention::class.java)
                .switchMap { gitHubApi.fetchFollowers() }
                .flatMap { Observable.never<FollowersState>() }

        return Observable.mergeArray(
                sourceCreatedStates,
                fetchFollowersStates
        )
    }
}
