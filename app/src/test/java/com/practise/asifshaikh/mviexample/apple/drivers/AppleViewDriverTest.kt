package com.practise.asifshaikh.mviexample.apple.drivers

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.practise.asifshaikh.mviexample.apple.AppleState
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class AppleViewDriverTest {
    @Test
    fun `it renders count, price and total price`() {
        // given
        val view = mock<AppleView>()
        val viewDriver = AppleViewDriver(view)
        val sourceSubject = PublishSubject.create<AppleState>()
        val appleState = AppleState(1, 10)
        viewDriver.render(sourceSubject)

        // when
        sourceSubject.onNext(appleState)

        // then
        verify(view).showCount(appleState.count)
        verify(view).showPrice(appleState.price)
        verify(view).showTotalPrice(appleState.totalPrice)
        verifyNoMoreInteractions(view)
    }
}
