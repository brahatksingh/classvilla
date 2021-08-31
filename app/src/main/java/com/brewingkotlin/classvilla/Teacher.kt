package com.brewingkotlin.classvilla

data class Teacher(
    val name: String,
    val email:String,
    val imageUrl: String,
    val about: String,
    val user_type: String,
    val uid: String
){
    /** Empty [Constructor] for Firebase */
    constructor() : this("","","","","","")

    constructor(name: String,email: String, imageUrl: String, about: String, uid: String) :
            this(name,email,imageUrl,about,"Student",uid = uid)

}