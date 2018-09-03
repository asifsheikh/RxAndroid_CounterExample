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
    fun `when user increases apple count, then update the apple count`() {
        //when
        mviTestRule.startWith(AppleState.INITIAL) {
            intentionsSubject.onNext(AppleCountIntention(5))
        }

        // then
        mviTestRule.assertStates(AppleState(5))
    }
}
