package com.practise.asifshaikh.mviexample.counter

import io.reactivex.subjects.PublishSubject
import io.redgreen.oneway.test.MviTestRule
import org.junit.Test

class CounterModelTest {
    private val intentions = PublishSubject.create<CounterIntention>()

    private val testRule = MviTestRule<CounterState> { sourceEvents, timeline ->
        CounterModel.createSource(sourceEvents, intentions, timeline)
    }

    @Test
    fun `when source is created, then emit initial state`() {
        // when
        testRule.sourceIsCreated()

        // then
        testRule.assertStates(CounterState.ZERO)
    }

    @Test
    fun `when user presses +, increment counter by +1`() {
        // when
        testRule.startWith(CounterState(5, 0)) {
            clickIncrement()
        }

        // then
        testRule.assertStates(CounterState(6, 1))
    }

    @Test
    fun `when user presses -, decrement counter by -1`() {
        // when
        testRule.startWith(CounterState(10, 0)) {
            clickDecrement()
        }

        // then
        testRule.assertStates(CounterState(9, 1))
    }

    @Test
    fun `when source is restored, then emit last known state`() {
        // given
        val lastKnownState = CounterState(-7, 0)
        testRule.startWith(lastKnownState)
        testRule.sourceIsDestroyed()

        // when
        testRule.sourceIsRestored()

        // then
        testRule.assertStates(lastKnownState)
    }

    @Test
    fun `when user presses +, then increment click count`() {
        // given
        testRule.sourceIsCreated()

        // when
        clickIncrement()

        // then
        testRule.assertStates(CounterState.ZERO, CounterState(1, 1))
    }

    @Test
    fun `when user presses -, then increment click count`() {
        // given
        testRule.sourceIsCreated()

        // when
        clickDecrement()

        // then
        testRule.assertStates(CounterState.ZERO, CounterState(-1, 1))
    }

    private fun clickDecrement() {
        intentions.onNext(DecrementCounterIntention)
    }

    private fun clickIncrement() {
        intentions.onNext(IncrementCounterIntention)
    }
}
