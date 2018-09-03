package com.practise.asifshaikh.mviexample.apple

import io.redgreen.oneway.test.MviTestRule
import org.junit.Test

class AppleModelTest {
    @Test
    fun `when the source is created, then show initial state`() {
        //given
        val mviTestRule = MviTestRule<AppleState> { sourceEvents, _ ->
            AppleModel.createSource(sourceEvents)
        }

        //when
        mviTestRule.sourceIsCreated()

        //then
        mviTestRule.assertStates(AppleState.INITIAL)
    }
}
