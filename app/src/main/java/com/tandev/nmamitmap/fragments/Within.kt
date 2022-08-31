package com.tandev.nmamitmap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.tandev.nmamitmap.R

class Within : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_within, container, false)

        var et: EditText = view.findViewById(R.id.etText)

        var bundle = Bundle()
        bundle.putString("key", et.text.toString())

        val fragmentOutside = Outside()
        fragmentOutside.arguments = bundle
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragmentOutside)
            .commit()

        return view
    }

}