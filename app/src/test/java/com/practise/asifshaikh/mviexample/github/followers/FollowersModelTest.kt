package com.practise.asifshaikh.mviexample.github.followers

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.redgreen.oneway.test.MviTestRule
import org.junit.Test

class FollowersModelTest {
    private val intentions = PublishSubject.create<FollowersIntention>()
    private val gitHubApi = mock<GitHubApi>()
    private val mviTestRule = MviTestRule<FollowersState> { sourceEvents, timeline ->
        FollowersModel.createSource(sourceEvents, intentions, timeline, gitHubApi)
    }

    @Test
    fun `when source is created, then show initial state`() {
        // when
        mviTestRule.sourceIsCreated()

        // then
        mviTestRule.assertStates(FollowersState.INITIAL)
    }

    @Test
    fun `when 'Fetch' is clicked, then make a network call`() {
        // given
        whenever(gitHubApi.fetchFollowers())
                .thenReturn(Observable.never())

        // when
        intentions.onNext(FetchIntention)

        // then
        verify(gitHubApi).fetchFollowers()
        verifyNoMoreInteractions(gitHubApi)
    }

    @Test
    fun `when 'Fetch' followers is successful, then show a list of followers`() {
        // given
        val followers = listOf(
                User("1", "asif", "https://aws.amazon.com/asif.jpg"),
                User("2", "sanyam", "https://aws.amazon.com/sanyam.jpg")
        )
        whenever(gitHubApi.fetchFollowers())
                .thenReturn(Observable.just(followers))

        // when
        mviTestRule.startWith(FollowersState.INITIAL) {
            intentions.onNext(FetchIntention)
        }

        // then
        val fetchSuccessfulState = FollowersState.INITIAL.fetchSuccessful(followers)
        mviTestRule.assertStates(fetchSuccessfulState)
    }

    @Test
    fun `when 'Fetch' followers fails, then show error`() {
        // given
        whenever(gitHubApi.fetchFollowers())
                .thenReturn(Observable.error(RuntimeException("Asif, not creative enough!")))

        // when
        mviTestRule.startWith(FollowersState.INITIAL) {
            intentions.onNext(FetchIntention)
        }

        // then
        val fetchFailedState = FollowersState.INITIAL.fetchFailed()
        mviTestRule.assertStates(fetchFailedState)
    }
}
