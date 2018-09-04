package com.practise.asifshaikh.mviexample.github.followers

import com.practise.asifshaikh.mviexample.github.followers.NetworkStatus.*

data class FollowersState(
        val followers: List<User> = emptyList(),
        val fetchFollowersStatus: NetworkStatus? = null
) {
    companion object {
        val INITIAL = FollowersState()
    }

    fun fetchSuccessful(followers: List<User>): FollowersState =
            copy(followers = followers, fetchFollowersStatus = SUCCESS)

    fun fetchFailed(): FollowersState =
            copy(fetchFollowersStatus = FAILED)

    fun fetchInFlight(): FollowersState =
            copy(fetchFollowersStatus = IN_FLIGHT)
}
