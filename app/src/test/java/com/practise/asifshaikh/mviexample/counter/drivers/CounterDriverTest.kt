package com.practise.asifshaikh.mviexample.counter.drivers

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.practise.asifshaikh.mviexample.counter.CounterState
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class CounterDriverTest {
    @Test
    fun `it renders the counter value`() {
        // given
        val view = mock<CounterView>()
        val viewDriver = CounterViewDriver(view)
        val sourceSubject = PublishSubject.create<CounterState>()
        val counter = 12

        // when
        val disposable = viewDriver.render(sourceSubject)
        sourceSubject.onNext(CounterState(counter, 0))

        // then
        verify(view).showCounter(counter)

        disposable.dispose()
    }
}
