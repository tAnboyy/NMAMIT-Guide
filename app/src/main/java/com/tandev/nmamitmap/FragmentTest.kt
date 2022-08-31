package com.tandev.nmamitmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tandev.nmamitmap.R
import com.tandev.nmamitmap.fragments.Outside
import com.tandev.nmamitmap.fragments.Within

class FragmentTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)

        val fragmentWithin = Within()

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragmentWithin).commit()
    }
}