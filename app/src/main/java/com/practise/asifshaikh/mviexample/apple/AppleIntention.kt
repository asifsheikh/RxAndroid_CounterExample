package com.practise.asifshaikh.mviexample.apple

sealed class AppleIntention
data class AppleCountIntention(val count: Int): AppleIntention()
data class ApplePriceIntention(val price : Int): AppleIntention()
