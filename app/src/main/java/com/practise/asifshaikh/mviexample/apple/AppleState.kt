package com.practise.asifshaikh.mviexample.apple

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppleState(
        val count: Int,
        val price: Int
): Parcelable {
    companion object {
        val INITIAL = AppleState(0, 0)
    }

    val totalPrice: Double
        get() = (count * price).toDouble()

    fun updateCount(count: Int): AppleState =
            copy(count = count)

    fun updatePrice(price: Int): AppleState =
            copy(price = price)
}
