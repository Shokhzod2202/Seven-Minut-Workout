package com.example.a7minutworkout

class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
) {

    /* Getter and Setter Functions Declaration starts */

    // Id:
    fun getId() : Int{
        return id
    }
    fun setId(id: Int){
        this.id = id
    }
    //

    // Name:
    fun getName() : String{
        return name
    }
    fun setName(name: String){
        this.name = name
    }
    //

    // Image:
    fun getImage() : Int{
        return image
    }
    fun setImage(image: Int){
        this.image = image
    }
    //

    // isCompleted:
    fun getIsComplete() : Boolean{
        return isCompleted
    }
    fun setIsComplete(isCompleted: Boolean){
        this.isCompleted = isCompleted
    }
    //

    // isSelected:
    fun getIsSelected() : Boolean{
        return isSelected
    }
    fun setIsSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }
    //

    /* Getter Setter Functions Declaration ends */


}