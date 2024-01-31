package com.example.proyectokotlininterfaces.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.proyectokotlininterfaces.R
import com.example.proyectokotlininterfaces.model.Monster
import com.example.proyectokotlininterfaces.model.Routes
import com.example.proyectokotlininterfaces.model.Spell
import kotlinx.coroutines.launch

@Composable
fun MonsterItem(monster: Monster, onClick:(Monster) ->Unit){
    Card (
        modifier = Modifier
            .background(Color.LightGray)
            .padding(8.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onClick(monster) },
        shape = RoundedCornerShape(8.dp)
    ){
        Surface(){
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ){
                if(monster.img_main != null){
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(monster.img_main).build()
                        , modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                            .clip(RoundedCornerShape(30.dp))
                        , contentDescription = monster.name
                    )
                }else{
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(R.drawable.logodnd).build()
                        , modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.5f)
                            .clip(RoundedCornerShape(30.dp))
                        , contentDescription = monster.name
                    )
                }

                Column(verticalArrangement = Arrangement.Center,modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f)){
                    Text(text = monster.name, style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                    Text(text = monster.desc, style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}


@Composable
fun DetailMonster (monster: Monster){
    println(monster.toString())

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState(), enabled = true)) {
        
        
        Spacer(modifier = Modifier.height(15.dp))
        //Titulo
        Row (Modifier.align(Alignment.CenterHorizontally)){
            Text(text = monster.name, style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(4.dp),
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

        }
        //Imagen
        Row (
            Modifier
                .height(150.dp)
                .align(Alignment.CenterHorizontally)){
            if(monster.img_main != null){
                AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(monster.img_main).build()
                    , modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .fillMaxHeight()
                    , contentDescription = monster.name
                )
            }else{
                AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(R.drawable.logodnd).build()
                    , modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .fillMaxHeight()
                    , contentDescription = monster.name
                )
            }
        }
        //ATRIBUTOS PRINCIPALES
        Row(Modifier.align(Alignment.CenterHorizontally)){
            //Type
            Column {
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = "        Type", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text ="         " + monster.type, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.width(60.dp))
            //Challenge Rating
            Column {
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = "Challenge Rating", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = monster.challenge_rating, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(Modifier.align(Alignment.CenterHorizontally)){
            //Hit points
            Column {
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = "Hit Points", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = monster.hit_points.toString(), style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(60.dp))
            //Armor class
            Column {
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = "Armor Class", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row (Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = monster.armor_class, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }
        //CARACTERISTICAS
        Spacer(modifier = Modifier.height(30.dp))
        Row(Modifier.align(Alignment.CenterHorizontally)){
            Column {
                Text(text = "Strength:  " + monster.strength.toString(), style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = "Dexterity:  " + monster.dexterity.toString(), style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = "Constitution:  " + monster.constitution.toString(), style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = "Inteligence:  " + monster.intelligence.toString(), style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = "Wisdom:  " + monster.wisdom.toString(), style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Text(text = "Charisma:  " + monster.charisma.toString(), style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        //SENTIDOS
        Spacer(modifier = Modifier.height(30.dp))
        Row (Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "Senses", style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Row (Modifier.align(Alignment.CenterHorizontally)){
            Text(text = monster.senses, style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
        
        //ACCIONES
        if(monster.actions != null){
            Spacer(modifier = Modifier.height(30.dp))
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Actions", style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            for(action in monster.actions){
                Row(Modifier.padding(20.dp)){
                    Column {
                        Text(text = action.name, style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(4.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(text = action.desc, style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                }
            }
        }

        //DESCRIPCION
        if(monster.desc != null && !monster.desc.equals("")){
            Spacer(modifier = Modifier.height(30.dp))
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Description", style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row {
                Text(text = monster.desc, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun SpellItem(spell: Spell, onClick:(Spell) ->Unit){
    Card (
        modifier = Modifier
            .background(Color.LightGray)
            .padding(8.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onClick(spell) },
        shape = RoundedCornerShape(8.dp)
    ){
        Surface(){
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ){

                Column(verticalArrangement = Arrangement.Center,modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f)){
                    Text(text = spell.name, style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                    Text(text = spell.level + " " + spell.school + " spell", style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Composable
fun DetailSpell(spell: Spell) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState(), enabled = true)
    ) {


        Spacer(modifier = Modifier.height(15.dp))
        //Titulo
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = spell.name, style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(4.dp),
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

        }
        //ATRIBUTOS PRINCIPALES
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            //School
            Column(Modifier.padding(30.dp)) {
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "School", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = spell.school, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp),
                    )
                }
            }
            //level
            Column(Modifier.padding(30.dp)) {
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Level", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = spell.level, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            //range
            Column(Modifier.padding(30.dp)) {
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Range", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = spell.range, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp),
                    )
                }
            }
            //Duracion
            Column(Modifier.padding(30.dp)) {
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Duration", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = spell.duration, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            //Concentration
            Column(Modifier.padding(30.dp)) {
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Concentration", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = spell.range, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp),
                    )
                }
            }
            //Ritual
            Column(Modifier.padding(30.dp)) {
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Ritual", style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = spell.ritual, style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }

        //Components
        if(spell.components != null && !spell.components.equals("")){
            Spacer(modifier = Modifier.height(30.dp))
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Components", style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = spell.components, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
        //CASTING TIME
        if(spell.casting_time != null && !spell.casting_time.equals("")){
            Spacer(modifier = Modifier.height(30.dp))
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Casting time", style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = spell.casting_time, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
        //DESCRIPTION
        if(spell.desc != null && !spell.desc.equals("")){
            Spacer(modifier = Modifier.height(30.dp))
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Description", style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row (Modifier.align(Alignment.CenterHorizontally)){
                Text(text = spell.desc, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}

//BOTTOM BAR
@Composable
fun NavBar(index: Int, onIndexSelected:(Int) -> Unit){

    NavigationBar(containerColor = Color.LightGray, contentColor = Color.White) {
        NavigationBarItem(
            selected = index == 1,
            onClick = { onIndexSelected(1) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.monster_icon),
                    contentDescription = "Bestiary",
                    modifier = Modifier.height(25.dp)
                )
            },
            label = { Text(text = "Bestiary") }
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = { onIndexSelected(2) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.spells_icon),
                    contentDescription = "Bestiary",
                    modifier = Modifier.height(30.dp)
                )
            },
            label = { Text(text = "Spells") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawer(drawerState: DrawerState, scaffold: @Composable () -> Unit) {
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(text = "Configuracion") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Preferencias") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Temas") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } }
                )
            }
        }

    ) {
        scaffold()
    }
}
@ExperimentalMaterial3Api
@Composable
fun TopBar(onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Dungeons & Dragons", color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(208,85,58),
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {

        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun TopBarDetail(onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "", color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(208,85,58),
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {

        }
    )
}
