package com.practise.asifshaikh.mviexample.github.followers

data class FollowersState(
        val followers: List<User> = emptyList(),
        val fetchFollowersStatus: NetworkStatus? = null
) {
    companion object {
        val INITIAL = FollowersState()
    }

    fun fetchSuccessful(followers: List<User>): FollowersState =
            copy(followers = followers)

    fun fetchFailed(): FollowersState =
            copy(fetchFollowersStatus = NetworkStatus.FAILED)
}
