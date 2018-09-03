package com.practise.asifshaikh.mviexample.apple

import io.reactivex.subjects.PublishSubject
import io.redgreen.oneway.test.MviTestRule
import org.junit.Test

class AppleModelTest {
    private val intentionsSubject = PublishSubject.create<AppleIntention>()
    private val mviTestRule = MviTestRule<AppleState> { sourceEvents, timeline ->
        AppleModel.createSource(sourceEvents, intentionsSubject, timeline)
    }

    @Test
    fun `when the source is created, then show initial state`() {
        //when
        mviTestRule.sourceIsCreated()

        //then
        mviTestRule.assertStates(AppleState.INITIAL)
    }

    @Test
    fun `when user updates apple count, then update the apple count`() {
        //when
        val count = 5
        val startState = AppleState.INITIAL
        mviTestRule.startWith(startState) {
            intentionsSubject.onNext(AppleCountIntention(count))
        }

        // then
        val updatedCountState = startState.updateCount(count)
        mviTestRule.assertStates(updatedCountState)
    }

    @Test
    fun `when user updates the apple price, then update the apple price`() {
        //when
        val price = 100
        val startState = AppleState.INITIAL
        mviTestRule.startWith(startState) {
            intentionsSubject.onNext(ApplePriceIntention(price))
        }

        //then
        val updatedPriceState = startState.updatePrice(price)
        mviTestRule.assertStates(updatedPriceState)
    }
}
