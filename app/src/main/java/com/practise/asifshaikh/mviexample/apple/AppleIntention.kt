package com.practise.asifshaikh.mviexample.apple

sealed class AppleIntention
data class AppleCountIntention(val count: Int): AppleIntention()
