package com.example.a7minutworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    // GLOBAL VARIABLES:

    // Create an object starting with "Activity" + "YurActivityNameStart" + "Binding" .
    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null

    private var restProgress = 0
    private var BeginningTime = 10

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTime = 30



    // onCreate:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Setting Up:
        // Assign bindingObject to layoutInflater  (fill)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        // Set Content View with (binding?.root)
        setContentView(binding?.root)

        // Enabling the Toolbar as an ActionBar.
        setSupportActionBar(binding?.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        // Enabling back navigation button.
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()

        }
        setupRestView()
    }

    private fun setupRestView(){
        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()

    }

    private fun setupExerciseView(){

        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.text = "Exercise Name"
        binding?.flExerciseView?.visibility = View.VISIBLE
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
    }



    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object: CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = BeginningTime - restProgress
                binding?.tvTimer?.text = (BeginningTime - restProgress).toString()
            }

            override fun onFinish() {
               setupExerciseView()
            }

        }.start()
    }


    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object: CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = exerciseTime - exerciseProgress
                binding?.tvTimerExercise?.text = (exerciseTime - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "30 seconds are over, lets go to the rest view", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding = null
    }


}









        // TIMER ON CLICK:
//        binding?.flProgressBar?.setOnClickListener {
//            if (!isOn) {
//                startTimer(pauseOffset)
//            } else {
//                pauseTimer()
//            }
//            isOn = isOn != true
//
//        }
//
//    }






