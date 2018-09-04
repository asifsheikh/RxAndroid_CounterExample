package com.practise.asifshaikh.mviexample.github.followers.drivers

import com.practise.asifshaikh.mviexample.github.followers.FollowersState
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.redgreen.oneway.drivers.ViewDriver

class FollowersViewDriver(private val view: FollowersView) : ViewDriver<FollowersState> {
    override fun render(source: Observable<FollowersState>): Disposable {
        return source.subscribe()
    }
}
