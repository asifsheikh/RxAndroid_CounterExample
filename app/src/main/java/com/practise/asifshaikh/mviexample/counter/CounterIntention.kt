package com.practise.asifshaikh.mviexample.counter

sealed class CounterIntention
object IncrementCounterIntention : CounterIntention()
object DecrementCounterIntention : CounterIntention()
