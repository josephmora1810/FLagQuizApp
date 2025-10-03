package com.jmora.flagquizapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmora.flagquizapp.R
import com.jmora.flagquizapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var fragmentHomeBinding : FragmentHomeBinding

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)


       fragmentHomeBinding.buttonStart.setOnClickListener {
            fragmentHomeBinding.buttonStart.text = "Clicked"
       }

       // Inflate the layout for this fragment
        return fragmentHomeBinding.root
    }

}