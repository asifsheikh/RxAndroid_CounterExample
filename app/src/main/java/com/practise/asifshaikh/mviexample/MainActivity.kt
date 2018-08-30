package com.practise.asifshaikh.mviexample

import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import com.practise.asifshaikh.mviexample.counter.*
import com.practise.asifshaikh.mviexample.counter.drivers.CounterView
import com.practise.asifshaikh.mviexample.counter.drivers.CounterViewDriver
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.redgreen.oneway.SourceEvent
import io.redgreen.oneway.android.OneWayActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : OneWayActivity<CounterState>(), CounterView {
    private val viewDriver = CounterViewDriver(this)
    private val intentions: Observable<CounterIntention> by lazy {
        Observable.merge(
                incrementButton.clicks().map { IncrementCounterIntention }.share(),
                decrementButton.clicks().map { DecrementCounterIntention }.share()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun source(
            sourceEvents: Observable<SourceEvent>,
            timeline: Observable<CounterState>
    ): Observable<CounterState> =
            CounterModel.createSource(sourceEvents, intentions, timeline)

    override fun sink(source: Observable<CounterState>): Disposable =
            viewDriver.render(source)

    override fun showCounter(counter: Int) {
        counterTextView.text = counter.toString()
    }
}
