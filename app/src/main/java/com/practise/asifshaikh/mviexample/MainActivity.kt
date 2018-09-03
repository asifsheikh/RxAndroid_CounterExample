package com.practise.asifshaikh.mviexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.practise.asifshaikh.mviexample.counter.CounterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerLayout, CounterFragment())
                    .commit()
        }
    }
}
