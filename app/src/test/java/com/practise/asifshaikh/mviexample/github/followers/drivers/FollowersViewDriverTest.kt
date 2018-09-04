package com.practise.asifshaikh.mviexample.github.followers.drivers

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.practise.asifshaikh.mviexample.github.followers.FollowersState
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class FollowersViewDriverTest {
    @Test
    fun `it does nothing for initial state`() {
        // given
        val view = mock<FollowersView>()
        val viewDriver = FollowersViewDriver(view)
        val sourceSubject = PublishSubject.create<FollowersState>()
        val disposable = viewDriver.render(sourceSubject)

        // when
        sourceSubject.onNext(FollowersState.INITIAL)

        // then
        verifyZeroInteractions(view)

        disposable.dispose()
    }
}
