package com.practise.asifshaikh.mviexample.apple

data class AppleState(val count: Int, val price: Int) {
    companion object {
        val INITIAL = AppleState(0, 0)
    }

    fun updateCount(count: Int): AppleState =
            copy(count = count)

    fun updatePrice(price: Int): AppleState =
            copy(price = price)
}
