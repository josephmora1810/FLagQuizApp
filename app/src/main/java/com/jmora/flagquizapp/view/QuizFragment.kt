package com.jmora.flagquizapp.view

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.jmora.flagquizapp.R
import com.jmora.flagquizapp.database.DatabaseCopyHelper
import com.jmora.flagquizapp.database.FlagsDao
import com.jmora.flagquizapp.databinding.FragmentQuizBinding
import com.jmora.flagquizapp.model.FlagsModel

class QuizFragment : Fragment() {

    lateinit var fragmentQuizBinding : FragmentQuizBinding
    var correctNumber : Int = 0;
    var wrongNumber : Int = 0;
    var emptyNumber : Int = 0;
    var questionNumber : Int = 0;

    lateinit var correctFlag : FlagsModel;
    var wrongFlags = ArrayList<FlagsModel>();
    val dao = FlagsDao();
    var optionControl : Boolean = false;

    var flagList = ArrayList<FlagsModel>();
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentQuizBinding = FragmentQuizBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        flagList = dao.getRandomTenRecords(DatabaseCopyHelper(requireActivity()));


        for (flag in flagList){
            Log.d("Flag",flag.id.toString())
            Log.d("Flag",flag.countryName)
            Log.d("Flag",flag.flagName)
            Log.d("Flag","------------------------------------ SEPARATOR ------------------------------------")
        }

        showData()

        fragmentQuizBinding.buttonA.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonA);
        }
        fragmentQuizBinding.buttonB.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonB);
        }
        fragmentQuizBinding.buttonC.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonC);
        }
        fragmentQuizBinding.buttonD.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonD);
        }
        fragmentQuizBinding.buttonNext.setOnClickListener {
            questionNumber++

            if(questionNumber > 9){
                if(!optionControl){
                    emptyNumber++;
                }
                //Toast.makeText(requireActivity(),"Game Over", Toast.LENGTH_SHORT).show();
                val direction = QuizFragmentDirections.actionQuizFragmentToResultFragment(correctNumber,wrongNumber,emptyNumber);
                this.findNavController().navigate(
                    R.id.action_quizFragment_to_resultFragment,
                    direction.arguments,
                    NavOptions.Builder().setPopUpTo(R.id.homeFragment,false).build()
                );
            }else{
                showData()
                if(!optionControl){
                    emptyNumber++
                    fragmentQuizBinding.textViewEmpty.text = emptyNumber.toString();
                }else{
                    setButtonsToInitialProperties()
                }
            }
            optionControl = false

        }

        return fragmentQuizBinding.root
    }

    private fun showData(){
        fragmentQuizBinding.textViewQuestion.text = resources.getString(R.string.question_number).plus(questionNumber + 1)

        correctFlag = flagList[questionNumber];
        fragmentQuizBinding.imageViewFlag.setImageResource(resources.getIdentifier(
            correctFlag.flagName,
            "drawable",
            requireActivity().packageName)
        );

        wrongFlags = dao.getRandomThreeRecords(DatabaseCopyHelper(requireActivity()),correctFlag.id);

        val mixOptions = HashSet<FlagsModel>();
        mixOptions.clear();

        mixOptions.add(correctFlag);
        mixOptions.add(wrongFlags[0]);
        mixOptions.add(wrongFlags[1]);
        mixOptions.add(wrongFlags[2]);

        val options = ArrayList<FlagsModel>();
        options.clear();

        for (eachFlag in mixOptions){
            options.add(eachFlag);
        }

        fragmentQuizBinding.buttonA.text = options[0].countryName;
        fragmentQuizBinding.buttonB.text = options[1].countryName;
        fragmentQuizBinding.buttonC.text = options[2].countryName;
        fragmentQuizBinding.buttonD.text = options[3].countryName;
    }

    private fun answerControl(button : Button){
        val clickedOption = button.text.toString();
        val correctAnswer = correctFlag.countryName;
        if(clickedOption == correctAnswer){
            correctNumber++;
            fragmentQuizBinding.textViewCorrect.text = correctNumber.toString();
            button.setBackgroundColor(Color.GREEN);
        }else{
            wrongNumber++;
            fragmentQuizBinding.textViewWrong.text = wrongNumber.toString();
            button.setBackgroundColor(Color.RED);
            button.setTextColor(Color.WHITE);

            when(correctAnswer){
                fragmentQuizBinding.buttonA.text.toString() -> fragmentQuizBinding.buttonA.setBackgroundColor(Color.GREEN);
                fragmentQuizBinding.buttonB.text.toString() -> fragmentQuizBinding.buttonB.setBackgroundColor(Color.GREEN);
                fragmentQuizBinding.buttonC.text.toString() -> fragmentQuizBinding.buttonC.setBackgroundColor(Color.GREEN);
                fragmentQuizBinding.buttonD.text.toString() -> fragmentQuizBinding.buttonD.setBackgroundColor(Color.GREEN);
            }
        }

        fragmentQuizBinding.buttonA.isClickable = false;
        fragmentQuizBinding.buttonB.isClickable = false;
        fragmentQuizBinding.buttonC.isClickable = false;
        fragmentQuizBinding.buttonD.isClickable = false;

        optionControl = true;

    }

    private fun setButtonsToInitialProperties(){
        fragmentQuizBinding.buttonA.apply {
            setBackgroundColor(Color.WHITE) // El punto y coma aquí también es innecesario
            setTextColor(ContextCompat.getColor(requireContext(), R.color.pink))
            isClickable = true
        }

        // Deberías hacer lo mismo para los otros botones (B, C, D)
        fragmentQuizBinding.buttonB.apply {
            setBackgroundColor(Color.WHITE)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.pink))
            isClickable = true
        }
        fragmentQuizBinding.buttonC.apply {
            setBackgroundColor(Color.WHITE)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.pink))
            isClickable = true
        }
        fragmentQuizBinding.buttonD.apply {
            setBackgroundColor(Color.WHITE)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.pink))
            isClickable = true
        }
    }

}