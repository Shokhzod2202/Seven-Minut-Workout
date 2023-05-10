package com.example.a7minutworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutworkout.databinding.ActivityExerciseBinding
import com.example.a7minutworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    /*  Declaration of  GLOBAL VARIABLES starts */

    // Create an object starting with "Activity" + "YurActivityNameStart" + "Binding" .
    private var binding: ActivityExerciseBinding? = null
    //


    private var restTimer: CountDownTimer? = null // RestTimer for time you want to rest
    private var restProgress : Long = 0
    private var restTimerDuration = 1
    private var isOn = true

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress : Long = 0
    private var exerciseTimerDuration = 1

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null
    private var exerciseAdapter : ExerciseStatusAdapter? = null

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

        /* Enabling back navigation button starts */
        binding?.toolbarExercise?.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        /* Enabling back navigation button ends */

        /* Creating the Default Exercise list instance starts */
        exerciseList = Constants.defaultExerciseList()
        /* Creating the Default Exercise list instance starts */

        /* Creating Text to speech object  start */
        tts = TextToSpeech(this, this)
        /* Creating Text to speech object  start */

        /* Calling setupRestView function starts */
        setupRestView()
        setupExerciseStatusRecycleView()
        /* Calling setupRestView function ends */
        binding?.progressBarExercise?.setOnClickListener {
            if (!isOn) {
                setExerciseProgressBar(exerciseProgress)
            } else {
                pauseExerciseTimer()
            }
            isOn = isOn != true
        }
        binding?.progressBar?.setOnClickListener {
            if (!isOn) {
                setRestProgressBar(restProgress)
            } else {
                pauseRestTimer()
            }
            isOn = isOn != true
        }
    }
    /* Overriding the onCreate function ends */

    override fun onBackPressed() {
        customDialogForBackButton()
    }

    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener{
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }

        dialogBinding.btnNo.setOnClickListener{
            customDialog.dismiss()
        }

        customDialog.show()
    }

    private fun setupExerciseStatusRecycleView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseAdapter

    }


    /* Declaration of setupRestView function starts */
    private fun setupRestView(){

        try{
            val soundURI =
                Uri.parse("android.resource://com.example.a7minutworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        }catch (e: Exception){
            e.printStackTrace()
        }



        // Change the visibility from restProgress to ExerciseProgress:
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        //

        // Checks if the resTtimer is already running. if so -> cancel it:
        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        // Speak out the Exercise name:
        speakOut("Get ready for ${exerciseList!![currentExercisePosition + 1].getName()} exercise")
        //

        binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()
        // Then set the rest progress bar:
        setRestProgressBar(restProgress)
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
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        //

        // If exerciseTimer is running -> cancel it :
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        //

        // Speak out the Exercise name:
        speakOut("Now, Start ${exerciseList!![currentExercisePosition].getName()} exercise")


        // Set Exercise progress bar:
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar(exerciseProgress)
        //
    }
    /* Declaration of setupExerciseView function ends */


    /* Declaration of setRestProgressBar starts */
    private fun setRestProgressBar(RestProgressL : Long){
        //Data Binding:
        binding?.progressBar?.max = restTimerDuration
        binding?.progressBar?.progress = (restProgress - RestProgressL).toInt()

        /* Creating the Object of the rest timer (instance) starts */
        restTimer = object: CountDownTimer(restTimerDuration*1000.toLong() - RestProgressL , 1000){

            // What do you want to do in every single Tick:
            override fun onTick(p0: Long) {
                restProgress = restTimerDuration*1000.toLong() - p0
                binding?.progressBar?.progress = ((restTimerDuration*1000.toLong() - restProgress)/1000).toInt()
                binding?.tvTimer?.text = (p0/1000).toString()
            }
            // What do you want to do when it ends:
            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()
            }

        }.start()
        /* Creating the Object of the rest timer (instance) ends */

    }
    /* Declaration of setRestProgressBar ends */

    /* Declaration of setExerciseProgressBar starts */
    private fun setExerciseProgressBar(exerciseProgressL : Long){

        // Assign ProgressBarExercise:
        binding?.progressBarExercise?.max = exerciseTimerDuration
        binding?.progressBarExercise?.progress = (exerciseProgress - exerciseProgressL).toInt()
        //

        /* Creating an exerciseTimer instance starts */
        exerciseTimer = object: CountDownTimer(exerciseTimerDuration*1000.toLong() - exerciseProgressL, 1000){

            // Override function onTick:
            override fun onTick(p0: Long) {

                exerciseProgress = exerciseTimerDuration*1000.toLong() - p0
                binding?.progressBarExercise?.progress = ((exerciseTimerDuration*1000.toLong() - exerciseProgress)/1000).toInt()
                binding?.tvTimerExercise?.text = (p0/1000 ).toString()
            }
            //

            // Overriding function onFinish:
            override fun onFinish() {



                if(currentExercisePosition < exerciseList?.size!! -1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsComplete(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()

                }else{
                    finish()
                    val intent = Intent(this@ExerciseActivity, EndActivity::class.java)
                    startActivity(intent)
                }

            }
            //

        }.start()
        /* Creating an exerciseTimer instance ends */

    }
    /* Declaration of setExerciseProgressBar ends */

    private fun pauseExerciseTimer(){
        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
        }
    }

    private fun pauseRestTimer(){
        if(restTimer != null){
            restTimer!!.cancel()
        }
    }


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

        if(player != null){
            player!!.stop()
        }

        // Shutting down the Text to speech feature when activity is destroyed
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        //

        binding = null
    }
    /* Overriding onDestroy function ends */


    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)

            if(result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language specified is not supported!")
            }
        }else{
            Log.e("TTS","Initialization Failed!")
        }
    }

    private fun speakOut(text:String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, " ")
    }





}
















