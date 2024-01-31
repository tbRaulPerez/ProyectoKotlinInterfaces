package com.example.proyectokotlininterfaces.model


data class Spell(
    val name:String,
    val desc:String,

    val range:String,
    val components:String,
    val material:String,
    val ritual:String,
    val duration: String,
    val concentration: String,
    val casting_time:String,
    val level: String,
    val school:String
)
