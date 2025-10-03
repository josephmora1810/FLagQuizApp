package com.jmora.flagquizapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmora.flagquizapp.R
import com.jmora.flagquizapp.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    lateinit var fragmentQuizBinding : FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentQuizBinding = FragmentQuizBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        fragmentQuizBinding.buttonA.setOnClickListener {
            fragmentQuizBinding.buttonA.text = "Clicked"
        }
        fragmentQuizBinding.buttonB.setOnClickListener {
            fragmentQuizBinding.buttonB.text = "Clicked"
        }
        fragmentQuizBinding.buttonC.setOnClickListener {
            fragmentQuizBinding.buttonC.text = "Clicked"
        }
        fragmentQuizBinding.buttonD.setOnClickListener {
            fragmentQuizBinding.buttonD.text = "Clicked"
        }
        fragmentQuizBinding.buttonNext.setOnClickListener {
            fragmentQuizBinding.buttonNext.text = "Clicked"
        }

        return fragmentQuizBinding.root
    }
}