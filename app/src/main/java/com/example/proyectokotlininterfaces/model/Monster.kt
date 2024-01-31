package com.example.proyectokotlininterfaces.model


data class Monster (
    val name:String,
    val img_main: String,

    val type: String,
    val challenge_rating: String,
    val hit_points: Int,
    val armor_class: String,
    //Stats
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
    //otro
    val senses: String,
    //acciones
    val actions: List<Action>?,
    //desc
    val desc: String

)