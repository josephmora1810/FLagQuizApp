package com.jmora.flagquizapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmora.flagquizapp.R
import com.jmora.flagquizapp.database.DatabaseCopyHelper
import com.jmora.flagquizapp.database.FlagsDao
import com.jmora.flagquizapp.databinding.FragmentQuizBinding
import com.jmora.flagquizapp.model.FlagsModel

class QuizFragment : Fragment() {

    lateinit var fragmentQuizBinding : FragmentQuizBinding
    var flagList = ArrayList<FlagsModel>();
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentQuizBinding = FragmentQuizBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val dao = FlagsDao();
        flagList = dao.getRandomTenRecords(DatabaseCopyHelper(requireActivity()));


        for (flag in flagList){
            Log.d("Flag",flag.id.toString())
            Log.d("Flag",flag.countryName)
            Log.d("Flag",flag.flagName)
            Log.d("Flag","------------------------------------ SEPARATOR ------------------------------------")
        }

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