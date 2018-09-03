package com.practise.asifshaikh.mviexample.counter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CounterState(val counter: Int, val clicks: Int) : Parcelable {
    companion object {
        val ZERO = CounterState(0, 0)
    }

    fun add(number: Int): CounterState =
            copy(counter = counter + number, clicks = clicks + 1)
}
