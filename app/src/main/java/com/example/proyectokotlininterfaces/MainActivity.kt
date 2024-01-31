package com.example.proyectokotlininterfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectokotlininterfaces.model.MonsterResponse
import com.example.proyectokotlininterfaces.model.Monster
import com.example.proyectokotlininterfaces.model.Routes
import com.example.proyectokotlininterfaces.model.Spell
import com.example.proyectokotlininterfaces.model.SpellResponse
import com.example.proyectokotlininterfaces.ui.theme.ProyectoKotlinInterfacesTheme
import com.example.proyectokotlininterfaces.view.DetailMonster
import com.example.proyectokotlininterfaces.view.DetailSpell
import com.example.proyectokotlininterfaces.view.ModalDrawer
import com.example.proyectokotlininterfaces.view.MonsterItem
import com.example.proyectokotlininterfaces.view.NavBar
import com.example.proyectokotlininterfaces.view.SpellItem
import com.example.proyectokotlininterfaces.view.TopBar
import com.example.proyectokotlininterfaces.view.TopBarDetail
import com.example.proyectokotlininterfaces.viewmodel.MonsterViewModel
import com.example.proyectokotlininterfaces.viewmodel.SpellViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val monsterViewModel by viewModels<MonsterViewModel> (  )
    val spellViewModel by viewModels<SpellViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoKotlinInterfacesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.MonstersScreen.route ){
                        composable(Routes.MonstersScreen.route){
                            ScreenMonsters(navigationController, monsterViewModel, spellViewModel)
                        }
                        composable(Routes.MonsterDetailScreen.route,
                            arguments = listOf(navArgument("parametro"){
                                type = NavType.StringType
                            }
                            )
                        ){
                           backStackEntry ->
                            val monsterId = backStackEntry.arguments?.getString("parametro") ?: "Aboleth"
                            val monster: Monster? = monsterViewModel.response.results.find{it.name == monsterId}

                            ScreenMonsterDetail(monster, navigationController)
                        }
                        composable(Routes.SpellDetailScreen.route,
                            arguments = listOf(navArgument("parametro"){
                                type = NavType.StringType
                            }
                            )
                        ){
                            backStackEntry ->
                            val spellId = backStackEntry.arguments?.getString("parametro")?: "Haste"
                            val spell: Spell?= spellViewModel.response.results.find {it.name == spellId }

                            ScreenSpellDetail(spell = spell, navigationController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MonsterList(listaMonstruos: MonsterResponse, navController: NavHostController){
    LazyColumn {
        itemsIndexed(items = listaMonstruos.results){
            index, item ->
            MonsterItem(monster = item) {
                navController.navigate(Routes.MonsterDetailScreen.createRoute(item.name))
            }
        }
    }

}
@Composable
fun SpellList(listaSpells: SpellResponse, navController: NavHostController){
    LazyColumn{
        itemsIndexed(items = listaSpells.results){
            index, item ->
            SpellItem(spell = item){
                navController.navigate(Routes.SpellDetailScreen.createRoute(item.name))
            }
        }
    }
}



//---------------Screens----------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMonsters(navController: NavHostController, monsterViewModel: MonsterViewModel, spellViewModel: SpellViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    var selectedIndex by remember { mutableStateOf(1) }
    val scope = rememberCoroutineScope()

    var searchText by remember { mutableStateOf("") }

    ModalDrawer(drawerState = drawerState){
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar ={TopBar(onClickDrawer = {scope.launch{drawerState.open()}})},
            bottomBar = {NavBar(index = selectedIndex, onIndexSelected = { index -> selectedIndex = index})}
        ){contentPadding ->

            Column(Modifier.padding(contentPadding)) {


                if (selectedIndex ==1){
                    MonsterList(listaMonstruos = monsterViewModel.response, navController= navController)
                    monsterViewModel.getMonsterList()
                }
                if(selectedIndex == 2){
                    SpellList(listaSpells = spellViewModel.response, navController = navController)
                    spellViewModel.getSpellList()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMonsterDetail(monster: Monster?, navController: NavHostController){
    val snackbarHostState = remember { SnackbarHostState() }

    if(monster != null){
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar ={TopBarDetail(onClickDrawer = {navController.navigateUp()})}
        ){contentPadding ->

            Column(Modifier.padding(contentPadding)) {
                DetailMonster(monster = monster)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSpellDetail(spell: Spell?, navController: NavHostController){
    val snackbarHostState = remember { SnackbarHostState() }
    if(spell != null){
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar ={TopBarDetail(onClickDrawer = {navController.navigateUp()})}
        ){contentPadding ->

            Column(Modifier.padding(contentPadding)) {
                DetailSpell(spell = spell)
            }
        }
    }
}