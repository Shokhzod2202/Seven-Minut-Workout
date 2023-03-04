package com.example.a7minutworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.a7minutworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    // GLOBAL VARIABLES:

    // Create an object starting with "Activity" + "YurActivityNameStart" + "Binding" .
    private var binding: ActivityExerciseBinding? = null
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var originalTime = 10

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



    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object: CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = originalTime - restProgress
                binding?.tvTimer?.text = (originalTime - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "Lest Start the Activity", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
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






