package com.example.nmamitmap

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.nmamitmap.databinding.ActivitySearchTab2Binding
import com.example.nmamitmap.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {
    private lateinit var binding: FragmentThirdBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentThirdBinding.inflate(layoutInflater)

        val listView = binding.listView;

        val names =
            arrayOf("Sanmathi Cafe", "Shabari", "Harshitha", "Shisha Food Court", "Oven Fresh");

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1, names
        )

        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(requireContext(), names[i] + " selected", Toast.LENGTH_SHORT).show();

//            val intent = Intent(requireContext(), MapsActivity::class.java)
////            var latlng = LatLng(16.099108, -22.812924);
//            var lat = 13.18331782186854
//            var lng = 74.93365618783577
//            intent.putExtra("key-lat", lat);
//            intent.putExtra("key-lng", lng);
//            intent.putExtra("viaIntent", 1);
//            requireContext().startActivity(intent)
        }


        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            // Responds to child chip checked/unchecked
//            Toast.makeText(
//                requireContext()@SearchTabActivity2,
//                "hi${checkedId.size}${checkedId.get(0)}",
//                Toast.LENGTH_LONG
//            ).show()
//            Toast.makeText(requireContext()@SearchTabActivity2, "hi${binding.chipFood.id}", Toast.LENGTH_LONG).show()

//            1
            if (checkedId.contains(binding.chipFood.id)) {
                //            food
                val listView = binding.listView;
                val names = arrayOf(
                    "Sanmathi Cafe",
                    "Shabari",
                    "Harshitha",
                    "Shisha Food Court",
                    "Oven Fresh",
                );

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(requireContext(), names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(requireContext(), MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    requireContext().startActivity(intent)
                }
            }

//            2
            if (checkedId.contains(binding.chipHangout.id)) {
                //            hangout
                val listView = binding.listView;
                val names = arrayOf("Sanmathi garden");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(requireContext(), names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(requireContext(), MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    requireContext().startActivity(intent)
                }
            }

            //            2
            if (checkedId.contains(binding.chipStore.id)) {
                //            shop
                val listView = binding.listView;
                val names = arrayOf("Shabari store");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(requireContext(), names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(requireContext(), MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    requireContext().startActivity(intent)
                }
            }

//            4
            if (checkedId.contains(binding.chipMedicine.id)) {
                //            medicine
                val listView = binding.listView;
                val names = arrayOf("gznitte");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(requireContext(), names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(requireContext(), MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    requireContext().startActivity(intent)
                }
            }

//            5
            if (checkedId.contains(binding.chipPrint.id)) {
                //            print
                val listView = binding.listView;
                val names = arrayOf("Ground Zero cyber");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(requireContext(), names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(requireContext(), MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    requireContext().startActivity(intent)
                }
            }

//            6
//            7
//            8
//            9
        }
    }
}
