package com.jmora.flagquizapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jmora.flagquizapp.R
import com.jmora.flagquizapp.database.DatabaseCopyHelper
import com.jmora.flagquizapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var fragmentHomeBinding : FragmentHomeBinding

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)


       createAndOpenDatabase();

       fragmentHomeBinding.buttonStart.setOnClickListener {
           // Al momento de hacer click no vamos a ir al otro fragment con intent o bundle sino con navigation
           val direction = HomeFragmentDirections.actionHomeFragmentToQuizFragment();
           it.findNavController().navigate(direction);
           //this.findNavController();
           //requireActivity().findNavController();
       }
       // Inflate the layout for this fragment
        return fragmentHomeBinding.root
    }

    private fun createAndOpenDatabase(){
        try {
            val helper = DatabaseCopyHelper(requireContext());
            helper.createDataBase();
            helper.openDataBase();
        }catch (e : Exception){
            e.printStackTrace();
        }
    }

}