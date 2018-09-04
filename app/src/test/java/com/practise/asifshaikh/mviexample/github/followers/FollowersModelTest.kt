package com.practise.asifshaikh.mviexample.github.followers

import io.redgreen.oneway.test.MviTestRule
import org.junit.Test

class FollowersModelTest {
    @Test
    fun `when source is created, then show initial state`() {
        // given
        val mviTestRule = MviTestRule<FollowersState> { sourceEvents, _ ->
            FollowersModel.createSource(sourceEvents)
        }

        // when
        mviTestRule.sourceIsCreated()

        // then
        mviTestRule.assertStates(FollowersState.INITIAL)
    }
}
