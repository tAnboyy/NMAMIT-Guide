package com.tandev.nmamitmap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tandev.nmamitmap.R

class Outside : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_outside, container, false)

        val tv: TextView = view.findViewById(R.id.tvShowText)
        val args = this.arguments
        val inputData = args?.get("key")
        tv.text = inputData.toString()
        return view
    }

}