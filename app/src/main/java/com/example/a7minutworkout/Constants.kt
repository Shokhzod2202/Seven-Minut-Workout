package com.example.a7minutworkout

object Constants {

    /* Declaration of a function that creates a default Exercise List starts */
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        // Creating the arraylist of Exercise models:
        val defaultExerciseList = ArrayList<ExerciseModel>()
        //

        // Creating instance of Exercise Model:
        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(jumpingJacks)
        //

        // Creating instance of Exercise Model:
        val abnormalCrunch = ExerciseModel(
            2,
            "Abnormal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(abnormalCrunch)
        //

        // Creating instance of Exercise Model:
        val highKneesRunning = ExerciseModel(
            3,
            "High Knees Running in Place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(highKneesRunning)
        //

        // Creating instance of Exercise Model:
        val lunge = ExerciseModel(
            4,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(lunge)
        //

        // Creating instance of Exercise Model:
        val plank = ExerciseModel(
            5,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(plank)
        //

        // Creating instance of Exercise Model:
        val pushUp = ExerciseModel(
            6,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(pushUp)
        //

        // Creating instance of Exercise Model:
        val pushUpAndRotation = ExerciseModel(
            7,
            "Push Up and Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(pushUpAndRotation)
        //

        // Creating instance of Exercise Model:
        val sidePlank = ExerciseModel(
            8,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(sidePlank)
        //

        // Creating instance of Exercise Model:
        val squat = ExerciseModel(
            9,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(squat)
        //

        // Creating instance of Exercise Model:
        val stepUpOntoChair = ExerciseModel(
            10,
            "Step Up Onto Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(stepUpOntoChair)
        //

        // Creating instance of Exercise Model:
        val tricepsDipOnChair = ExerciseModel(
            11,
            "Triceps Dip On Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(tricepsDipOnChair)
        //

        // Creating instance of Exercise Model:
        val wallSit = ExerciseModel(
            12,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        //

        // Adding that model to the default list:
        defaultExerciseList.add(wallSit)
        //



        return defaultExerciseList
    }
    /* Declaration of a function that creates a defaultExerciseList ends */

}