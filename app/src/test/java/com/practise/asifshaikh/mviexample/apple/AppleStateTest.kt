package com.practise.asifshaikh.mviexample.apple

import org.junit.Assert.assertEquals
import org.junit.Test

class AppleStateTest {
    @Test
    fun `it should compute the total price`() {
        // given
        val appleState = AppleState(10, 20)

        // then
        assertEquals(200.0, appleState.totalPrice, 0.0)
    }
}
