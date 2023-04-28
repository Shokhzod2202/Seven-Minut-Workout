package com.example.a7minutworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    /*  Declaration of  GLOBAL VARIABLES starts */

    // Create an object starting with "Activity" + "YurActivityNameStart" + "Binding" .
    private var binding: ActivityExerciseBinding? = null
    //

    private var restTimer: CountDownTimer? = null // RestTimer for time you want to rest
    private var restProgress = 0
    private var BeginningTime = 10

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTime = 30

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    /*  Declaration of  GLOBAL VARIABLES ends */


    /* Overriding the onCreate function starts */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /* Setting Up Data binding starts */
        binding = ActivityExerciseBinding.inflate(layoutInflater)   // Assign bindingObject to layoutInflater  (fill)
        setContentView(binding?.root)   // Set Content View with (binding?.root)
        /* Setting Up Data binding ends */

        /* Enabling the Toolbar as an ActionBar starts */
        setSupportActionBar(binding?.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        /* Enabling the Toolbar as an ActionBar ends */

        /* Creating the Default Exercise list instance starts */
        exerciseList = Constants.defaultExerciseList()
        /* Creating the Default Exercise list instance starts */

        /* Enabling back navigation button starts */
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        /* Enabling back navigation button ends */

        /* Calling setupRestView function starts */
        setupRestView()
        /* Calling setupRestView function ends */
    }
    /* Overriding the onCreate function ends */

    /* Declaration of setupRestView function starts */
    private fun setupRestView(){
        // Change the visibility from restProgress to ExerciseProgress:
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        //

        // Checks if the resTtimer is already running. if so -> cancel it:
        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        //

        // Then set the rest progress bar:
        setRestProgressBar()
        //
    }
    /* Declaration of setupRestView function ends */

    /* Declaration of setupExerciseView function starts */
    private fun setupExerciseView(){

        // Change the visibility from restProgress to ExerciseProgress:
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        //

        // If exerciseTimer is running -> cancel it :
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        //

        // Set Exercise progress bar:
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
        //
    }
    /* Declaration of setupExerciseView function ends */


    /* Declaration of setRestProgressBar starts */
    private fun setRestProgressBar(){
        //Data Binding:
        binding?.progressBar?.progress = restProgress

        /* Creating the Object of the rest timer (instance) starts */
        restTimer = object: CountDownTimer(BeginningTime*1000.toLong() , 1000){

            // What do you want to do in every single Tick:
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = BeginningTime - restProgress
                binding?.tvTimer?.text = (BeginningTime - restProgress).toString()
            }
            // What do you want to do when it ends:
            override fun onFinish() {
                currentExercisePosition++
               setupExerciseView()
            }

        }.start()
        /* Creating the Object of the rest timer (instance) ends */

    }
    /* Declaration of setRestProgressBar ends */

    /* Declaration of setExerciseProgressBar starts */
    private fun setExerciseProgressBar(){

        // Assign ProgressBarExercise:
        binding?.progressBarExercise?.progress = exerciseProgress
        //

        /* Creating an exerciseTimer instance starts */
        exerciseTimer = object: CountDownTimer(exerciseTime*1000.toLong(), 1000){

            // Override function onTick:
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = exerciseTime - exerciseProgress
                binding?.tvTimerExercise?.text = (exerciseTime - exerciseProgress).toString()
            }
            //

            // Overriding function onFinish:
            override fun onFinish() {
                if(currentExercisePosition < exerciseList?.size!! -1){
                    setupRestView()
                }else{
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations! You have completed the 7 minut workout.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            //

        }.start()
        /* Creating an exerciseTimer instance ends */

    }
    /* Declaration of setExerciseProgressBar ends */

    /* Overriding onDestroy function starts */
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
    /* Overriding onDestroy function ends */

}
















