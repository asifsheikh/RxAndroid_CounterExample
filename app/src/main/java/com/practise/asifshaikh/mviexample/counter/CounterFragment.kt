package com.practise.asifshaikh.mviexample.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.practise.asifshaikh.mviexample.R
import com.practise.asifshaikh.mviexample.counter.drivers.CounterView
import com.practise.asifshaikh.mviexample.counter.drivers.CounterViewDriver
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.redgreen.oneway.SourceEvent
import io.redgreen.oneway.android.OneWayFragment
import kotlinx.android.synthetic.main.counter_fragment.*

class CounterFragment : OneWayFragment<CounterState>(), CounterView {
    private val viewDriver = CounterViewDriver(this)

    private val intentionsGroup: CounterIntentionsGroup by lazy(LazyThreadSafetyMode.NONE) {
        CounterIntentionsGroup(incrementButton.clicks(), decrementButton.clicks())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.counter_fragment, container, false)
    }

    override fun source(
            sourceEvents: Observable<SourceEvent>,
            timeline: Observable<CounterState>
    ): Observable<CounterState> =
            CounterModel.createSource(sourceEvents, intentionsGroup.intentions(), timeline)

    override fun sink(source: Observable<CounterState>): Disposable =
            viewDriver.render(source)

    override fun showCounter(counter: Int) {
        counterTextView.text = counter.toString()
    }

    override fun showClicks(clicks: Int) {
        clicksTextView.text = clicks.toString()
    }
}
