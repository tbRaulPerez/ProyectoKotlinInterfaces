package com.example.proyectokotlininterfaces.model

data class SpellResponse (
    val count: Int = 0,
    val results: List<Spell> = listOf()
)