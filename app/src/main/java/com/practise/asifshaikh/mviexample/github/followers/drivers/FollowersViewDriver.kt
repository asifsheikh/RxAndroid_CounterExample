package com.practise.asifshaikh.mviexample.github.followers.drivers

import com.practise.asifshaikh.mviexample.github.followers.FollowersState
import com.practise.asifshaikh.mviexample.github.followers.NetworkStatus
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.redgreen.oneway.drivers.ViewDriver

class FollowersViewDriver(private val view: FollowersView) : ViewDriver<FollowersState> {
    override fun render(source: Observable<FollowersState>): Disposable {
        return source.subscribe { state ->
            when(state.fetchFollowersStatus){
                NetworkStatus.SUCCESS -> {
                    view.removeLoading()
                    view.removeFetchButton()
                    view.showFollowersList()
                }
                NetworkStatus.IN_FLIGHT -> {
                    view.showLoading()
                    view.disableFetchButton()
                }
                NetworkStatus.FAILED -> {
                    view.removeLoading()
                    view.showError()
                    view.enableFetchButton()
                }
                null -> {}
            }

        }
    }
}
