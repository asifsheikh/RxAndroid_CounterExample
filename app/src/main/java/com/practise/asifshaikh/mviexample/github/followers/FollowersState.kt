package com.practise.asifshaikh.mviexample.github.followers

data class FollowersState(
        val followers: List<User> = emptyList()
) {
    companion object {
        val INITIAL = FollowersState()
    }

    fun fetchSuccessful(followers: List<User>): FollowersState =
            copy(followers = followers)
}
