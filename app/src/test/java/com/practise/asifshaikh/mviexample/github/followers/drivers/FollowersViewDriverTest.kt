package com.practise.asifshaikh.mviexample.github.followers.drivers

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.practise.asifshaikh.mviexample.github.followers.FollowersState
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Before
import org.junit.Test

class FollowersViewDriverTest {
    // given
    private val view = mock<FollowersView>()
    private val viewDriver = FollowersViewDriver(view)
    private val sourceSubject = PublishSubject.create<FollowersState>()
    private lateinit var disposable: Disposable

    @Before
    fun setup() {
        disposable = viewDriver.render(sourceSubject)
    }

    @After
    fun tearDown() {
        disposable.dispose()
    }

    @Test
    fun `it does nothing for initial state`() {
        // when
        sourceSubject.onNext(FollowersState.INITIAL)

        // then
        verifyZeroInteractions(view)
    }

    @Test
    fun `it can render loading state`() {
        // when
        sourceSubject.onNext(FollowersState.INITIAL.fetchInFlight())

        // then
        verify(view).showLoading()
        verify(view).disableFetchButton()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render success state`() {
        //when
        val fetchInFlightStatus = FollowersState.INITIAL.fetchInFlight()
        sourceSubject.onNext(fetchInFlightStatus.fetchSuccessful(emptyList()))

        //then
        verify(view).removeLoading()
        verify(view).showFollowersList()
        verify(view).removeFetchButton()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render failed state`() {
        //when
        val fetchInFlightStatus = FollowersState.INITIAL.fetchInFlight()
        sourceSubject.onNext(fetchInFlightStatus.fetchFailed())

        //then
        verify(view).removeLoading()
        verify(view).enableFetchButton()
        verify(view).showError()

        verifyNoMoreInteractions(view)

    }
}
