package com.practise.asifshaikh.mviexample.github.followers.drivers

interface FollowersView {
    fun showLoading()
    fun disableFetchButton()
    fun removeLoading()
    fun showFollowersList()
    fun removeFetchButton()
    fun enableFetchButton()
    fun showError()
}
