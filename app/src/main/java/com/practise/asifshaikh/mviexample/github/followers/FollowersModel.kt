package com.practise.asifshaikh.mviexample.github.followers

import arrow.core.Either
import io.reactivex.Observable
import io.reactivex.rxkotlin.withLatestFrom
import io.redgreen.oneway.SourceEvent
import io.redgreen.oneway.usecases.SourceCreatedUseCase
import io.redgreen.oneway.usecases.SourceRestoredUseCase

object FollowersModel {
    fun createSource(
            sourceEvents: Observable<SourceEvent>,
            intentions: Observable<FollowersIntention>,
            timeline: Observable<FollowersState>,
            gitHubApi: GitHubApi
    ): Observable<FollowersState> {
        val fetchIntentions = intentions.ofType(FetchIntention::class.java)

        val fetchInFlightStates = fetchIntentions
                .withLatestFrom(timeline) { _, state -> state.fetchInFlight() }

        val fetchFollowersNetworkStates = fetchIntentions
                .switchMap { fetchFollowersNetworkCall(gitHubApi) }
                .withLatestFrom(timeline) { either, state -> reduceToFollowersState(state, either) }

        return Observable.mergeArray(
                sourceEvents.compose(SourceCreatedUseCase(FollowersState.INITIAL)),
                sourceEvents.compose(SourceRestoredUseCase(timeline)),
                fetchInFlightStates,
                fetchFollowersNetworkStates
        )
    }

    private fun fetchFollowersNetworkCall(gitHubApi: GitHubApi): Observable<Either<Throwable, List<User>>> {
        return gitHubApi.fetchFollowers()
                .map { followers -> Either.right(followers) as Either<Throwable, List<User>> }
                .onErrorReturn { exception -> Either.left(exception) }
    }

    private fun reduceToFollowersState(
            state: FollowersState,
            either: Either<Throwable, List<User>>
    ): FollowersState = when (either) {
        is Either.Right -> state.fetchSuccessful(either.b)
        is Either.Left -> state.fetchFailed()
    }
}
