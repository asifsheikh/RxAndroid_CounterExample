package com.practise.asifshaikh.mviexample.github.followers

import io.reactivex.Observable

interface GitHubApi {
    fun fetchFollowers(): Observable<List<User>>
}
