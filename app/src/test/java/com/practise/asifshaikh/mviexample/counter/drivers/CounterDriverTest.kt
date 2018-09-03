package com.practise.asifshaikh.mviexample.counter.drivers

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.practise.asifshaikh.mviexample.counter.CounterState
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class CounterDriverTest {
    private val view = mock<CounterView>()
    private val viewDriver = CounterViewDriver(view)
    private val sourceSubject = PublishSubject.create<CounterState>()

    @Test
    fun `it renders the counter value and clicks value`() {
        // given
        val counter = 12
        val clicks = 12

        // when
        val disposable = viewDriver.render(sourceSubject)
        sourceSubject.onNext(CounterState(counter, clicks))

        // then
        verify(view).showClicks(clicks)
        verify(view).showCounter(counter)
        verifyNoMoreInteractions(view)

        disposable.dispose()
    }
}
