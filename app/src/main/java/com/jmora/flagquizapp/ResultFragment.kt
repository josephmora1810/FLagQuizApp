package com.jmora.flagquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmora.flagquizapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {


    lateinit var fragmentResultBinding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

        fragmentResultBinding.buttonNewQuiz.setOnClickListener {
            fragmentResultBinding.buttonNewQuiz.text = "Clicked"
        }

        fragmentResultBinding.buttonExit.setOnClickListener {
            fragmentResultBinding.buttonExit.text = "Clicked"
        }

        // Inflate the layout for this fragment
        return fragmentResultBinding.root
    }
}