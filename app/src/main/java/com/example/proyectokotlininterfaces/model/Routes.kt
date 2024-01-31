package com.example.proyectokotlininterfaces.model

sealed class Routes (val route: String){
    object MonstersScreen: Routes("screenMonsters")
    object MonsterDetailScreen: Routes("screenMonsterDetail/{parametro}"){
        fun createRoute(parametro: String) = "screenMonsterDetail/$parametro"
    }
    object SpellDetailScreen: Routes("screenSpellDetail/{parametro}"){
        fun createRoute(parametro: String) = "screenSpellDetail/$parametro"
    }
}