package com.practise.asifshaikh.mviexample.apple

data class AppleState(val count: Int) {
    companion object {
        val INITIAL = AppleState(0)
    }

    fun updateCount(count: Int): AppleState =
            copy(count = count)
}
