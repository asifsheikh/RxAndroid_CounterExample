package com.practise.asifshaikh.mviexample.apple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.changes
import com.practise.asifshaikh.mviexample.R
import com.practise.asifshaikh.mviexample.apple.drivers.AppleView
import com.practise.asifshaikh.mviexample.apple.drivers.AppleViewDriver
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.redgreen.oneway.SourceEvent
import io.redgreen.oneway.android.OneWayFragment
import kotlinx.android.synthetic.main.apple_fragment.*
import kotlin.LazyThreadSafetyMode.NONE

class AppleFragment : OneWayFragment<AppleState>(), AppleView {
    private val viewDriver = AppleViewDriver(this)

    private val intentions by lazy(NONE) {
        Observable.merge(
                countSeekBar.changes().skipInitialValue().share().map { AppleCountIntention(it) },
                priceSeekBar.changes().skipInitialValue().share().map { ApplePriceIntention(it) }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View =
            inflater.inflate(R.layout.apple_fragment, container, false)

    override fun source(
            sourceEvents: Observable<SourceEvent>,
            timeline: Observable<AppleState>
    ): Observable<AppleState> {
        return AppleModel.createSource(
                sourceEvents,
                intentions,
                timeline
        )
    }

    override fun sink(source: Observable<AppleState>): Disposable {
        return viewDriver.render(source)
    }

    override fun showCount(count: Int) {
        countTextView.text = count.toString()
    }

    override fun showPrice(price: Int) {
        priceTextView.text = price.toString()
    }

    override fun showTotalPrice(totalPrice: Double) {
        totalPriceTextView.text = totalPrice.toString()
    }
}
